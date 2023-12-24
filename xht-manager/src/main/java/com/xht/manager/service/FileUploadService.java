package com.xht.manager.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author : YIYUANYUAN
 * @description : 文件上传
 * @date: 2023/12/24  17:30
 */
public interface FileUploadService {

    String upload(MultipartFile file);
}
