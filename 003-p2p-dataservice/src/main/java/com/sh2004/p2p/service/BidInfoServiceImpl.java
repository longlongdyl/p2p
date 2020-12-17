package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sh2004.p2p.constant.Constant;
import com.sh2004.p2p.eneity.BidInfo;
import com.sh2004.p2p.mapper.BidInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/15 17:14
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service(interfaceClass = BidInfoService.class,timeout = 10000,version = "1.0.0")
@Component
@Slf4j
public class BidInfoServiceImpl implements BidInfoService {
    @Autowired
    private BidInfoMapper bidInfoMapper;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;

    @Override
    public Double queryTotalMoney() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Double totalMoney = (Double) redisTemplate.opsForValue().get(Constant.TOTALMONEY);
        if (null == totalMoney){
            synchronized (this){
                totalMoney = (Double) redisTemplate.opsForValue().get(Constant.TOTALMONEY);
                if (null == totalMoney){
                    totalMoney = bidInfoMapper.queryTotalMoney();
                    redisTemplate.opsForValue().set(Constant.TOTALMONEY,totalMoney,30, TimeUnit.SECONDS);
                }
            }
        }
        return totalMoney;
    }

    @Override
    public List<Map<String,String>> queryTop5User() {
        return bidInfoMapper.queryTop5User();

    }
}
