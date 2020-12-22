package com.sh2004.p2p.util;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.serialize.support.json.FastJsonObjectInput;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.util
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/19 15:48
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class Aliyun {
    public static CommonResponse code(String phone,String code){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "xx", "xx");
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "注册验证");
        request.putQueryParameter("TemplateCode", "SMS_3105078");
        request.putQueryParameter("TemplateParam", "{\"code\":\""+code+"\",\"product\":\"动力节点\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            System.out.println(response.getData());
            return response;
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }
}
