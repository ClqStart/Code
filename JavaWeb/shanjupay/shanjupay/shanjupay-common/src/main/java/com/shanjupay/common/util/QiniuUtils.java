package com.shanjupay.common.util;

import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import com.qiniu.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URLEncoder;
import java.util.UUID;

public class QiniuUtils {

    /**
     * logger
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(QiniuUtils.class);

    /**
     * 文件上传的方法
     *
     * @param accessKey
     * @param secretKey
     * @param bucket
     * @param bytes
     * @param fileName  外部传过来和七牛云文件名一样
     */
    public static void upload2qiniu(String accessKey, String secretKey, String bucket, byte[] bytes, String fileName) throws RuntimeException {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);


        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = fileName;

        try {
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            LOGGER.error("上传文件到七牛：{}", ex.getMessage());
            try {
                LOGGER.error(r.bodyString());
            } catch (QiniuException ex2) {
               ex2.printStackTrace();
            }
            throw new RuntimeException(r.toString());
        }

    }

    private static void getDownloadurl() throws UnsupportedEncodingException {

        String fileName = "http://qakyetc36.bkt.clouddn.com";
        String domainOfBucket = "http://qakyetc36.bkt.clouddn.com";
        String encodedFileName = URLEncoder.encode(fileName, "utf-8").replace("+", "%20");
        String publicUrl = String.format("%s/%s", domainOfBucket, encodedFileName);
        String accessKey = "8aeVohAfnwBS56wZ4NERi_eyzgAp-GAXYTK2Ntw8";
        String secretKey = "dQfukR81NmuO-tK_vo4EbZsdQCI2UVCSsl_34x5V";
        Auth auth = Auth.create(accessKey, secretKey);
        long expireInSeconds = 3600;//1小时，可以自定义链接过期时间
        String finalUrl = auth.privateDownloadUrl(publicUrl, expireInSeconds);
        System.out.println(finalUrl);
    }

    private static void testUpLoad() throws IOException {
        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huanan());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String accessKey = "8aeVohAfnwBS56wZ4NERi_eyzgAp-GAXYTK2Ntw8";
        String secretKey = "dQfukR81NmuO-tK_vo4EbZsdQCI2UVCSsl_34x5V";
        String bucket = "clqstore";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = UUID.randomUUID().toString() + ".png";

        FileInputStream fileInputStream = null;

        String filePath = "C:\\Users\\10958\\Pictures\\3.jpg";

        try {
            fileInputStream = new FileInputStream(new File(filePath));
            byte[] bytes = IOUtils.toByteArray(fileInputStream);

            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            Response response = uploadManager.put(bytes, key, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            System.out.println(putRet.key);
            System.out.println(putRet.hash);
        } catch (QiniuException ex) {
            Response r = ex.response;
            System.err.println(r.toString());
            try {
                System.err.println(r.bodyString());
            } catch (QiniuException ex2) {
                //ignore
            }
        }
    }

    public static void main(String[] args) throws IOException {
        QiniuUtils.testUpLoad();
    }
}
