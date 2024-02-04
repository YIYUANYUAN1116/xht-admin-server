package com.xht.manager.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.xht.model.entity.product.Brand;
import com.xht.model.entity.system.SysOperLog;

import java.util.Date;


/**
 * <p>
 * 操作日志记录 服务类
 * </p>
 *
 * @author xht
 * @since 2023-12-29
 */
public interface SysOperLogService extends IService<SysOperLog> {


    Page<SysOperLog> listPage(Integer page, Integer limit, Date startTime,Date endTime);
}
