package com.xht.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/24  17:35
 */
@Data
@ConfigurationProperties(prefix = "xht.minio")
public class MinioProperties {
    private String endpointUrl;
    private String accessKey;
    private String secreKey;
    private String bucketName;
}
