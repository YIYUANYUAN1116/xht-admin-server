package com.xht.manager.config;

import com.xht.manager.properties.MinioProperties;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/26  22:17
 */
@Configuration
public class MinioConfig {

    @Autowired
    private MinioProperties minioProperties;

    @Bean
    public MinioClient minioClient(){
        //创建对象
        return MinioClient.builder()
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecreKey())
                .endpoint(minioProperties.getEndpointUrl())
                .build();
    }
}
