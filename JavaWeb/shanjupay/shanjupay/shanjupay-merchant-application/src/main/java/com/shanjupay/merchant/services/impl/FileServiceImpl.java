package com.shanjupay.merchant.services.impl;

import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.util.QiniuUtils;
import com.shanjupay.merchant.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileServiceImpl implements FileService {

    @Value("${oss.qiniu.url}")
    private String qiniuUrl;
    @Value("${oss.qiniu.accessKey}")
    private String accessKey;
    @Value("${oss.qiniu.secretKey}")
    private String secretKey;
    @Value("${oss.qiniu.bucket}")
    private String bucket;

    /**
     *
     * @param bytes 文件字节数组
     * @param filename 文件名
     * @return 文件的绝对路径
     * @throws BusinessException
     */
    @Override
    public String upload(byte[] bytes, String filename) throws BusinessException {
        try{
            QiniuUtils.upload2qiniu(accessKey,secretKey,bucket,bytes,filename);
        }catch (RuntimeException e){
            e.printStackTrace();
            throw  new BusinessException(CommonErrorCode.E_100106);
        }

        return qiniuUrl+filename;
    }
}
