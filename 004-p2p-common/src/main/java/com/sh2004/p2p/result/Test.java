package com.sh2004.p2p.result;

import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.result
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/18 22:28
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public class Test {
    public static void main(String[] args) throws IOException, ParseException {
        String json = "{\"RequestId\":\"E770E0A6-5813-4549-9309-5D53C5586B4E\",\"Message\":\"OK\",\"BizId\":\"148402708363234081^0\",\"Code\":\"OK\"}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        Object requestId = jsonObject.get("RequestId");
        System.out.println(requestId);


    }
}
