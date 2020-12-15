package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sh2004.p2p.constant.Constant;
import com.sh2004.p2p.eneity.LoanInfo;
import com.sh2004.p2p.mapper.LoanInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/15 17:25
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service(interfaceClass = LoanInfoService.class,timeout = 10000,version = "1.0.0")
@Component
@Slf4j
public class LoanInfoServiceImpl implements LoanInfoService {
    @Autowired
    private LoanInfoMapper loanInfoMapper;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;
    @Override
    public Double queryAvgRate() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Double avgRate = (Double) redisTemplate.opsForValue().get(Constant.AVGRATE);
        if (null == avgRate){
            synchronized (this){
                avgRate = (Double) redisTemplate.opsForValue().get(Constant.AVGRATE);
                if (null == avgRate){
                    avgRate = loanInfoMapper.queryAvgRate();
                    redisTemplate.opsForValue().set(Constant.AVGRATE,avgRate,30, TimeUnit.SECONDS);
                }
            }
        }
        return avgRate;
    }

    @Override
    public List<LoanInfo> queryAllLoanInfo() {
        return loanInfoMapper.selectAll();
    }

}
