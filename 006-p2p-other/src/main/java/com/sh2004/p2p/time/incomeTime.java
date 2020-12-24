package com.sh2004.p2p.time;

import com.alibaba.dubbo.config.annotation.Reference;
import com.sh2004.p2p.service.IncomeRecordService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: p2pNow
 * @Package: com.sh2004.p2p.time
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/24 19:27
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Component
public class incomeTime {

    @Reference(interfaceClass = IncomeRecordService.class,version = "1.0.0",timeout = 20000)
    IncomeRecordService incomeRecordService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void generateIncomePlan(){
        incomeRecordService.insertIncomeRecord();
    }


    @Scheduled(cron = "0/5 * * * * ?")
    public void generateIncomeBack(){

        incomeRecordService.generateIncomeBack();

    }

}
