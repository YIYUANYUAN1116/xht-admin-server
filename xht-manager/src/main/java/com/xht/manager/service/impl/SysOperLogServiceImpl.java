package com.xht.manager.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.xht.manager.mapper.SysOperLogMapper;
import com.xht.manager.service.SysOperLogService;
import com.xht.model.entity.system.SysOperLog;
import org.springframework.stereotype.Service;

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

}
