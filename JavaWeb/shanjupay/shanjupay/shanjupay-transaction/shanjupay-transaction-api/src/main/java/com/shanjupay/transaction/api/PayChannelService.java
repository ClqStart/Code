package com.shanjupay.transaction.api;


import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.transaction.api.dto.PayChannelDTO;
import com.shanjupay.transaction.api.dto.PayChannelParamDTO;
import com.shanjupay.transaction.api.dto.PlatformChannelDTO;

import java.util.List;

public interface PayChannelService {

    /**获取平台服务类型
     * @return
     * */
    List<PlatformChannelDTO> queryPlatformChannel() throws BusinessException;

    /**
     *为app绑定平台服务类型
     *@param appId 应用id
     *@param platformChannelCodes 平台服务类型列表
     */
    void bindPlatformChannelForApp(String appId,String platformChannelCodes)throws BusinessException;

    /**
     * 应用是否已经绑定了某个服务类型
     * @param  appId
     * @paramplat  formChannel
     * @return 已绑定返回1，否则返回0
     */
     int queryAppBindPlatformChannel(String appId,String platformChannel)throws BusinessException;

    /**
     *根据平台服务类型获取支付渠道列表
     *@param platformChannelCode
     *@return
     */
    List<PayChannelDTO> queryPayChannelByPlatformChannel(String platformChannelCode)throws BusinessException;

    /**
     *保存支付渠道参数
     *@param payChannelParam 商户原始支付渠道参数
     */
    void savePayChannelParam(PayChannelParamDTO payChannelParam)throws BusinessException;

    /**
     * 根据应用和服务类型查询支付渠道参数列表
     * @param appId 应用id
     * @param platformChannel 服务类型code
     * @return
     */
    List<PayChannelParamDTO> queryPayChannelParamByAppAndPlatform(String appId,String platformChannel);

    /**
     * 根据应用、服务类型和支付渠道的代码查询该支付渠道的参数配置信息
     * @param appId 应用id
     * @param platformChannel 服务类型code
     * @param payChannel  支付渠道代码
     * @return
     */
    PayChannelParamDTO queryParamByAppPlatformAndPayChannel(String appId,String platformChannel,String payChannel);
}
