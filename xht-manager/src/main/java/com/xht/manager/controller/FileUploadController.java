package com.xht.manager.controller;

import com.xht.manager.service.FileUploadService;
import com.xht.model.vo.common.Result;
import com.xht.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author : YIYUANYUAN
 * @description : 文件上传接口
 * @date: 2023/12/24  17:31
 */
@RestController
@RequestMapping("/admin/system")
@Tag(name = "文件上传接口")
public class FileUploadController {
    @Autowired
    private FileUploadService fileUploadService;

    // <input type="file" name="file"/>
    @PostMapping("/fileUpload")
    public Result fileUpload(MultipartFile file) {
        //1 获取上传的文件
        //2 调用service的方法上传，返回minio路径
        String url = fileUploadService.upload(file);
        return Result.build(url, ResultCodeEnum.SUCCESS);
    }
}
