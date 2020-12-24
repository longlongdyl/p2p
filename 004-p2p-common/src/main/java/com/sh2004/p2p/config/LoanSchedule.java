/*
package com.sh2004.p2p.config;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;

import java.time.LocalDateTime;
import java.util.Date;

*/
/**
 * @ProjectName: p2pNow
 * @Package: com.sh2004.p2p.scheduled
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/23 21:47
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 *//*

@Configuration
@EnableScheduling
public class LoanSchedule implements SchedulingConfigurer {


    @Mapper
    public interface CronMapper {
        @Select("select cron from cron limit 1")
        public String getCron();
    }

    @Mapper
    public interface UserMapper {
        @Select("select username from user limit 1")
        public String getUser();
    }

    @Autowired
    @SuppressWarnings("all")
    CronMapper cronMapper;

    @Autowired
    @SuppressWarnings("all")
    UserMapper userMapper;
    */
/**
     * 执行定时任务.
     *//*

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {

        taskRegistrar.addTriggerTask(
                //1.添加任务内容(Runnable)
                () -> System.out.println(userMapper.getUser()),
                //2.设置执行周期(Trigger)
                triggerContext -> {
                    //2.1 从数据库获取执行周期
                    String cron = cronMapper.getCron();
                    //2.2 合法性校验.
                    if (StringUtils.isEmpty(cron)) {
                        // Omitted Code ..
                    }
                    //2.3 返回执行周期(Date)
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                }
        );
    }
}
*/
