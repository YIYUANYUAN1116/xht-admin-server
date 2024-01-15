package com.xht.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.unit.DataUnit;
import com.xht.manager.properties.MinioProperties;
import com.xht.manager.service.FileUploadService;
import com.xht.model.vo.common.ResultCodeEnum;
import com.xht.service.exception.CustomException;
import io.minio.*;
import io.minio.errors.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/24  17:31
 */
@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Autowired
    private MinioProperties minioProperties;

    @Autowired
    private MinioClient minioClient;

    @Override
    public String upload(MultipartFile file) {

        try {
            //创建bucket
            boolean b = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioProperties.getBucketName()).build());
            if (!b){
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioProperties.getBucketName()).build());
            }else {
                log.info("Bucket "+minioProperties.getBucketName()+" already exists.");
            }
            //获取上传文件名称
            // 1 每个上传文件名称唯一的   uuid生成 01.jpg
            //2 根据当前日期对上传文件进行分组 20230910
            String dateDir = DateUtil.format(new Date(), "yyyyMMdd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = dateDir + "/" + uuid + file.getOriginalFilename();

            //文件上传
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .object(filename)
                    .stream(file.getInputStream(),file.getSize(),-1)
                    .build());

            return minioProperties.getEndpointUrl()+"/"+minioProperties.getBucketName()+"/"+filename;
        }catch (Exception e){
            e.printStackTrace();
            throw new CustomException(ResultCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void delete(String path){
        try {
            String bucketName = minioProperties.getBucketName();
            int index = path.indexOf(bucketName+"/");
            String objectName = path.substring(index+bucketName.length());
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(minioProperties.getBucketName())
                    .object(objectName).build());
        } catch (Exception e) {
            throw new CustomException("删除失败",999);
            //http://123.60.189.149:9001/xht-bucket/20231226/5ed82ee717634ee5bd0500b3360aafbfR.png
        }
    }
}
