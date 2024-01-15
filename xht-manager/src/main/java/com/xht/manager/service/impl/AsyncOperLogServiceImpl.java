package com.xht.manager.service.impl;

import com.xht.log.service.AsyncOperLogService;
import com.xht.manager.mapper.SysOperLogMapper;
import com.xht.model.entity.system.SysOperLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/29  17:57
 */
@Service
public class AsyncOperLogServiceImpl implements AsyncOperLogService {

    @Autowired
    private SysOperLogMapper sysOperLogMapper;

    //保存日志数据
    @Override
    public void saveSysOperLog(SysOperLog sysOperLog) {
        sysOperLogMapper.insert(sysOperLog);
    }



}
