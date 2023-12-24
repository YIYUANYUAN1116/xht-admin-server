import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author : YIYUANYUAN
 * @description :
 * @date: 2023/12/24  17:04
 */
public class FileUploadTest {
    public static void main(String[] args) {
        //创建minio对象
        MinioClient minioClient = MinioClient.builder()
                .endpoint("http://123.60.189.149:9001")
                .credentials("admin", "yzd2021$$")
                .build();

        try {
            boolean b = minioClient.bucketExists(BucketExistsArgs.builder().bucket("xht-bucket").build());
            if (b){
                System.out.println("Bucket 'spzx-bucket' already exists.");
            }else {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("xht-bucket").build());
            }

            FileInputStream fileInputStream = new FileInputStream("C:\\Users\\YIYUANYUAN\\Desktop\\QQ图片20221211154738.jpg");
            PutObjectArgs build = PutObjectArgs.builder().bucket("xht-bucket")
                    .stream(fileInputStream, fileInputStream.available(), -1)
                    .object("01.jpg")
                    .build();
            minioClient.putObject(build);

            String fileUrl = "http://123.60.189.149/xht-bucket/01.jpg" ;
            System.out.println(fileUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
