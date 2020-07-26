package com.shanjupay.transaction.service;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shanjupay.common.cache.Cache;
import com.shanjupay.common.domain.BusinessException;
import com.shanjupay.common.domain.CommonErrorCode;
import com.shanjupay.common.util.RedisUtil;
import com.shanjupay.transaction.api.PayChannelService;
import com.shanjupay.transaction.api.dto.PayChannelDTO;
import com.shanjupay.transaction.api.dto.PayChannelParamDTO;
import com.shanjupay.transaction.api.dto.PlatformChannelDTO;
import com.shanjupay.transaction.convert.PayChannelParamConvert;
import com.shanjupay.transaction.convert.PlatformChannelConvert;
import com.shanjupay.transaction.entity.AppPlatformChannel;
import com.shanjupay.transaction.entity.PayChannelParam;
import com.shanjupay.transaction.entity.PlatformChannel;
import com.shanjupay.transaction.mapper.AppPlatformChannelMapper;
import com.shanjupay.transaction.mapper.PayChannelParamMapper;
import com.shanjupay.transaction.mapper.PlatformChannelMapper;
import com.shanjupay.transaction.mapper.PlatformPayChannelMapper;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PayChannelServiceImpl implements PayChannelService {

    @Autowired
    PlatformChannelMapper platformChannelMapper;
    @Autowired
    AppPlatformChannelMapper appPlatformChannelMapper;
    @Autowired
    PlatformPayChannelMapper platformPayChannelMapper;
    @Autowired
    PayChannelParamMapper payChannelParamMapper;
    @Autowired
    Cache cache;

    /**
     * 查询平台类型
     *
     * @return
     * @throws BusinessException 查询表：platform_channel
     */
    @Override
    public List<PlatformChannelDTO> queryPlatformChannel() throws BusinessException {
        List<PlatformChannel> platformChannels = platformChannelMapper.selectList(null);
        return PlatformChannelConvert.INSTANCE.listentity2listdto(platformChannels);
    }

    @Override
    public void bindPlatformChannelForApp(String appId, String platformChannelCodes) throws BusinessException {
        AppPlatformChannel appPlatformChannel = appPlatformChannelMapper.selectOne(new LambdaQueryWrapper<AppPlatformChannel>()
                .eq(AppPlatformChannel::getAppId, appId)
                .eq(AppPlatformChannel::getPlatformChannel, platformChannelCodes));
        if (appPlatformChannel == null) {

            AppPlatformChannel entity = new AppPlatformChannel();
            entity.setAppId(appId);
            entity.setPlatformChannel(platformChannelCodes);//服务类型code
            appPlatformChannelMapper.insert(entity);
        }

    }

    @Override
    public int queryAppBindPlatformChannel(String appId, String platformChannel) throws BusinessException {
        AppPlatformChannel appPlatformChannel = appPlatformChannelMapper.selectOne(new LambdaQueryWrapper<AppPlatformChannel>()
                .eq(AppPlatformChannel::getAppId, appId)
                .eq(AppPlatformChannel::getPlatformChannel, platformChannel));
        if (appPlatformChannel != null) {
            return 1;
        } else {
            return 0;
        }

    }

    @Override
    public List<PayChannelDTO> queryPayChannelByPlatformChannel(String platformChannelCode) throws BusinessException {
        return platformPayChannelMapper.selectPayChannelByPlatformChannel(platformChannelCode);
    }

    @Transactional
    @Override
    public void savePayChannelParam(PayChannelParamDTO payChannelParam) throws BusinessException {
        if (payChannelParam == null || payChannelParam.getChannelName() == null || payChannelParam.getParam() == null) {
            throw new BusinessException(CommonErrorCode.E_300010);
        }
        //根据应用、服务类型、支付渠道查询一条记录
        //根据应用于服务类型查询对应的绑定Id
        Long appPlatformChannelId = selectIdByAppPlatformChannel(payChannelParam.getAppId(), payChannelParam.getPlatformChannelCode());

        if (appPlatformChannelId == null) {
            throw new BusinessException(CommonErrorCode.E_300009);
        }
        // 根据应用与服务的绑定Id和支付渠道查询payChannelParam的一条记录
        PayChannelParam entity = payChannelParamMapper.selectOne(new LambdaQueryWrapper<PayChannelParam>()
                .eq(PayChannelParam::getAppPlatformChannelId, appPlatformChannelId)
                .eq(PayChannelParam::getPayChannel, payChannelParam.getPayChannel()));

        //如果存在进行跟新
        if (entity != null) {
            entity.setChannelName(payChannelParam.getChannelName());
            entity.setParam(payChannelParam.getParam());
            payChannelParamMapper.updateById(entity);
        } else {
            //否则添加配置
            PayChannelParam entityNew = PayChannelParamConvert.INSTANCE.dto2entity(payChannelParam);
            entityNew.setId(null);
            entityNew.setAppPlatformChannelId(appPlatformChannelId);
            payChannelParamMapper.insert(entityNew);
        }

        updateCache(payChannelParam.getAppId(),payChannelParam.getPlatformChannelCode());

    }

    /**
     *
     * @param appId 应用id
     * @param platformChannel 服务类型code
     * @return
     */
    @Override
    public List<PayChannelParamDTO> queryPayChannelParamByAppAndPlatform(String appId, String platformChannel) {

        String keyBuilder = RedisUtil.keyBuilder(appId, platformChannel);
        Boolean exists = cache.exists(keyBuilder);
        if(exists){
            String PayChannelParamDTO_String = cache.get(keyBuilder);
            return JSON.parseArray(PayChannelParamDTO_String, PayChannelParamDTO.class);
        }

        Long appPlatformChannelId = selectIdByAppPlatformChannel(appId, platformChannel);
        if (appPlatformChannelId == null) {
            return null;
        }

        List<PayChannelParam> channelParamList = payChannelParamMapper.selectList(new LambdaQueryWrapper<PayChannelParam>()
                .eq(PayChannelParam::getAppPlatformChannelId, appPlatformChannelId));
        List<PayChannelParamDTO> payChannelParamDTOS = PayChannelParamConvert.INSTANCE.listentity2listdto(channelParamList);

        updateCache(appId,platformChannel);
        return  payChannelParamDTOS;
    }

    @Override
    public PayChannelParamDTO queryParamByAppPlatformAndPayChannel(String appId, String platformChannel, String payChannel) {
        List<PayChannelParamDTO> payChannelParamDTOS = queryPayChannelParamByAppAndPlatform(appId, platformChannel);
        for (PayChannelParamDTO payChannelParamDTO : payChannelParamDTOS) {
            if(payChannelParamDTO.getPayChannel().equals(payChannel)){
                return payChannelParamDTO;
            }
        }
        return null;
    }

    /**
     * 根据应用于服务类型查询到服务参数配置列表放入到redis缓存
     * @param appId 应用id
     * @param platformChannelCode  服务类型code
     */
    private void updateCache(String appId,String platformChannelCode){

        String redisKey = RedisUtil.keyBuilder(appId, platformChannelCode);
        Boolean exists = cache.exists(redisKey);
        if(exists){
            cache.del(redisKey);
        }
        Long appPlatformChannelId = selectIdByAppPlatformChannel(appId, platformChannelCode);
        if (appPlatformChannelId != null) {
            List<PayChannelParam> channelParamList = payChannelParamMapper.selectList(new LambdaQueryWrapper<PayChannelParam>()
                    .eq(PayChannelParam::getAppPlatformChannelId, appPlatformChannelId));
            List<PayChannelParamDTO> payChannelParamDTOS = PayChannelParamConvert.INSTANCE.listentity2listdto(channelParamList);

            if(payChannelParamDTOS !=null){
                cache.set(redisKey, JSON.toJSON(payChannelParamDTOS).toString());
            }
        }
    }

    private Long selectIdByAppPlatformChannel(String appId, String platformChannelCode) {
        AppPlatformChannel appPlatformChannel = appPlatformChannelMapper.selectOne(new LambdaQueryWrapper<AppPlatformChannel>()
                .eq(AppPlatformChannel::getAppId, appId)
                .eq(AppPlatformChannel::getPlatformChannel, platformChannelCode));
        if (appPlatformChannel != null) {
            return appPlatformChannel.getId();
        }
        return null;
    }

}
