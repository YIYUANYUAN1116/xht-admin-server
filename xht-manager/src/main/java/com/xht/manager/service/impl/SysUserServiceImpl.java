package com.xht.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xht.manager.config.SysUserDetails;
import com.xht.manager.custom.jwt.HutoolJwtValidatorUtils;
import com.xht.manager.mapper.SysUserMapper;
import com.xht.manager.service.SysRoleService;
import com.xht.manager.service.SysUserRoleService;
import com.xht.manager.service.SysUserService;
import com.xht.model.dto.system.LoginDto;
import com.xht.model.entity.system.SysRole;
import com.xht.model.entity.system.SysUser;
import com.xht.model.vo.common.ResultCodeEnum;
import com.xht.model.vo.system.DoAssignUserRole;
import com.xht.model.vo.system.ToAssignUserVo;
import com.xht.model.vo.system.SysUserVo;
import com.xht.service.exception.CustomException;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/18  20:07
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper,SysUser> implements SysUserService {

    @Resource
    SysUserMapper sysUserMapper;

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Autowired
    private HutoolJwtValidatorUtils hutoolJwtValidatorUtils;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;


    @Override
    public SysUser selectUserInfoByUserName(String username) {
        return sysUserMapper.selectUserInfoByUserName(username);
    }

    @Override
    public String login(LoginDto loginDto) {
        //1.验证码校验
        String captcha = loginDto.getCaptcha();
        String captchaKey = loginDto.getCodeKey();

        if (StrUtil.isEmpty(captcha) || StrUtil.isEmpty(captchaKey)) {
            throw new CustomException(ResultCodeEnum.VALIDATE_CODE_ERROR);
        }

        String cacheData = redisTemplate.opsForValue().get(captchaKey);
        if (StrUtil.isEmpty(cacheData)) {
            // 未查询到ID对应的验证码
            throw new CustomException(ResultCodeEnum.VALIDATE_CODE_EXPIRED);
        }
        // 校验验证码是否输入正确
        if (!cacheData.equalsIgnoreCase(captcha)) {
            throw new CustomException(ResultCodeEnum.VALIDATE_CODE_ERROR);
        }
        // 删除验证码...
        redisTemplate.delete(captchaKey);


        //登录校验
        String userName = loginDto.getUserName();
        String password = loginDto.getPassword();

        if (StrUtil.isEmpty(userName) || StrUtil.isEmpty(password)) {
            throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
        }

        String token = null;
        try {
            SysUserDetails userDetails = (SysUserDetails) userDetailsService.loadUserByUsername(loginDto.getUserName());
            if (!passwordEncoder.matches(loginDto.getPassword(),userDetails.getPassword())){
                throw new CustomException(ResultCodeEnum.LOGIN_ERROR);
            }

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            token = hutoolJwtValidatorUtils.generateToken(userDetails);

            String jsonString = JSON.toJSONString(userDetails, JSONWriter.Feature.NullAsDefaultValue);
            // 把登录成功用户信息放到redis里面
            // key : token   value: 用户信息
            redisTemplate.opsForValue()
                    .set("user:login"+token,
                            JSON.toJSONString(userDetails),
                            7,
                            TimeUnit.DAYS);
        }catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    @Override
    public void logout(String token) {
        redisTemplate.delete("user:login" + token);
    }

    @Override
    public Page<SysUserVo> getAll(Integer page, Integer limit,String userName) {
        Page<SysUser> sysUserPage = new Page<>(page, limit);

        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<SysUser>();
        if (StringUtils.hasLength(userName)){
            wrapper.like(SysUser::getUserName,userName);
        }
        Page<SysUser> selectPage = baseMapper.selectPage(sysUserPage,wrapper );
        List<SysUser> records = selectPage.getRecords();
        List<SysUserVo> sysUserVos = new ArrayList<>();
        for (SysUser record : records) {
            SysUserVo sysUserVo = new SysUserVo();

            record.setPassword("");
            List<SysRole> sysRole =  sysRoleService.getByUserId(record.getId());
            if (!sysRole.isEmpty()) record.setRoleName(sysRole.get(0).getRoleName());

            BeanUtils.copyProperties(record,sysUserVo);
            sysUserVos.add(sysUserVo);
        }

        Page<SysUserVo> sysUserVoPage = new Page<>();
        BeanUtils.copyProperties(selectPage,sysUserVoPage);
        sysUserVoPage.setRecords(sysUserVos);
        return sysUserVoPage;
    }

    @Override
    public void saveOrUpdateVo(SysUserVo sysUserVo) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(sysUserVo,sysUser);
        this.saveOrUpdate(sysUser);
    }

    @Override
    public ToAssignUserVo toAssign(Long userId) {
        ToAssignUserVo toAssignUserVo = new ToAssignUserVo();
        List<SysRole> list = sysRoleService.list();
        toAssignUserVo.setAllRolesList(list);
        List<SysRole> byUserId = sysRoleService.getByUserId(userId);
        toAssignUserVo.setAssignRoles(byUserId);
        return toAssignUserVo;
    }

    @Override
    public void doAssignRole(DoAssignUserRole doAssignUserRole) {
        sysUserRoleService.doAssignRole(doAssignUserRole);
    }

    @Override
    @Transactional
    public void removeByIdWithRe(Long userId) {
        sysUserRoleService.removeByUserId(userId);
        baseMapper.delete(userId);
    }

    @Override
    @Transactional
    public void removeBatchByIdsWithRe(List<Long> userIds) {
        for (Long userid : userIds) {
            removeByIdWithRe(userid);
        }
    }


}
