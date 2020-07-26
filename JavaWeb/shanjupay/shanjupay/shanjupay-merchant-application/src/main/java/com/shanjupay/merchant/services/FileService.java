package com.shanjupay.merchant.services;

import com.shanjupay.common.domain.BusinessException;

public interface FileService {

    /**
     * 上传文件
     * @param bytes 文件字节数组
     * @param filename 文件名
     * @return  文件访问的绝对路径
     * @throws BusinessException
     */
    public  String  upload(byte[] bytes,String filename) throws BusinessException;
}
