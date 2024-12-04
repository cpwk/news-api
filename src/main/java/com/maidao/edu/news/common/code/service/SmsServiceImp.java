package com.maidao.edu.news.common.code.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.maidao.edu.news.common.util.L;
import com.maidao.edu.news.common.util.StringUtils;
import com.maidao.edu.news.common.code.model.CodeCache;
import com.maidao.edu.news.common.code.model.CodeErrors;
import com.maidao.edu.news.common.code.model.SmsConfig;
import com.maidao.edu.news.common.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建人:chenpeng
 * 创建时间:2019-08-05 10:09
 * Version 1.8.0_211
 * 项目名称：news
 * 类名称:SmsServiceImp
 * 类描述:短信验证码服务层接口的一个具体实现类
 **/

@Service
public class SmsServiceImp implements SmsService, CodeErrors {

    private static final int VCODE_LENGTH = 6;

    @Autowired
    private SmsConfig smsConfig;

    @Override
    public void sendVcode(String key, String mobile) throws Exception {
        if (StringUtils.isEmpty(key)) {

        }
        if (!StringUtils.isChinaMobile(mobile)) {
            throw new ServiceException(ERR_MOBILE_INVALID);
        }
        String code = StringUtils.randomNumericString(VCODE_LENGTH);
        if (sendSms(mobile, code)) {
            CodeCache.saveCode(key, code);
            CodeCache.saveMobile(key, mobile);
        }
    }

    private boolean sendSms(String mobile, String code) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", smsConfig.getAccessKeyId(), smsConfig
                .getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain(smsConfig.getDomain());
        request.setVersion(smsConfig.getVersion());
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobile);
        request.putQueryParameter("SignName", smsConfig.getSignName());
        request.putQueryParameter("TemplateCode", smsConfig.getTemplateCode());
        Map<String, String> param = new HashMap<>();
        param.put("code", code);

        request.putQueryParameter("TemplateParam", JSON.toJSONString(param));
        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject data = JSON.parseObject(response.getData());
            if ("OK".equals(data.getString("Message"))) {
                L.info("Send sms of vcode.mobile:" + mobile + ",vcode:");
                return true;
            } else {
                L.error(response.getData());
                return false;
            }
        } catch (ServerException e) {
            throw new ServiceException(ERR_DETAILED_MESSAGE, e.getMessage());
        } catch (ClientException e) {
            throw new ServiceException(ERR_ALIYUN_EXCEPTION);
        }
    }
}
