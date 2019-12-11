package com.itheima.utils;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * 操作阿里云服务器的工具类
 */
public class ALiYunUtils {

    // Endpoint以杭州为例，其它Region请按实际情况填写。
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";
    // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
    private static String accessKeyId = "你的accessKeyId";
    private static String accessKeySecret = "你的accessKeySecret";
    private static String bucketName = "你的bucketName";

    public static void writeFile(byte[] bytes, String fileName) {
        // 创建OSSClient实例。
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        ossClient.putObject(bucketName, fileName, new ByteArrayInputStream(bytes));

        // 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void fileDownload(OutputStream os, String fileName) throws IOException {

        // 创建OSSClient实例。
        OSS ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        // ossObject包含文件所在的存储空间名称、文件名称、文件元信息以及一个输入流。
        OSSObject ossObject = ossClient.getObject(bucketName, fileName);

        // 读取文件内容。
        InputStream is = ossObject.getObjectContent();
        IOUtils.copy(is, os);

        // 关闭OSSClient。
        ossClient.shutdown();
    }
}
