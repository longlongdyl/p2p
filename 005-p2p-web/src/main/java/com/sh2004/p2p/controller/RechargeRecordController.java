package com.sh2004.p2p.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sh2004.p2p.service.RechargeRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.controller
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/21 23:06
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Controller
public class RechargeRecordController {
    @Reference(interfaceClass = RechargeRecordService.class,timeout = 20000,version = "1.0.0")
    private RechargeRecordService rechargeRecordService;

    @RequestMapping("/toRecharge")
    public String toRecharge(){
        return "toRecharge";
    }
}
