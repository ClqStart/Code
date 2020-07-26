package com.shanjupay.merchant.services;


import com.shanjupay.common.domain.BusinessException;

public interface SmsService {
    /**
     * 获取短信验证码
     *
     * @paramphone @return
     */
    String sendMsg(String phone) ;


    /**
     **校验验证码，抛出异常则校验无效
     *@paramve rifiyKey 验证码key
     *@paramverifiy Code验证码
     */
     void checkVerifiyCode(String verifiyKey,String verifiyCode) throws BusinessException;

}