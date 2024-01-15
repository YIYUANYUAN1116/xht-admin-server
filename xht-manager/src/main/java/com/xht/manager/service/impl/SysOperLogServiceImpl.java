package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xht.manager.mapper.SysOperLogMapper;
import com.xht.manager.service.SysOperLogService;
import com.xht.model.entity.product.Brand;
import com.xht.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 操作日志记录 服务实现类
 * </p>
 *
 * @author xht
 * @since 2023-12-29
 */
@Service
public class SysOperLogServiceImpl extends ServiceImpl<SysOperLogMapper, SysOperLog> implements SysOperLogService {



    @Override
    public Page<SysOperLog> listPage(Integer page, Integer limit, Date date) {
        LambdaQueryWrapper<SysOperLog> queryWrapper = new LambdaQueryWrapper<>();
        if (date != null){
            queryWrapper.eq(SysOperLog::getCreateTime,date);
        }

        return baseMapper.selectPage(new Page<>(page,limit),queryWrapper);
    }
}
