package com.sh2004.p2p.result;

import com.alibaba.dubbo.common.json.ParseException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

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
    public static void main(String[] args) {
        Double money = 1.235546;
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");
        money = Double.parseDouble(df.format(money));
        System.out.println(money);
    }

}
