package com.xht.log.service;

import com.xht.model.entity.system.SysOperLog;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/29  17:55
 */
public interface AsyncOperLogService {
    void saveSysOperLog(SysOperLog sysOperLog) ;
}
