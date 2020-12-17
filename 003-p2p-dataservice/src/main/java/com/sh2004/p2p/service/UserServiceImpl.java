package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sh2004.p2p.constant.Constant;
import com.sh2004.p2p.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/15 17:28
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */


@Service(interfaceClass = UserService.class,timeout = 10000,version = "1.0.0")
@Component
@Slf4j
public class UserServiceImpl implements UserService {


    @Autowired
    private UserMapper userMapper;
    @Autowired
    RedisTemplate<Object,Object> redisTemplate;


    @Override
    public Long queryUserCount() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Long userCount = (Long) redisTemplate.opsForValue().get(Constant.USERCOUNT);
        if (null == userCount){
            synchronized (this){
                userCount = (Long) redisTemplate.opsForValue().get(Constant.USERCOUNT);
                if (null == userCount){
                    userCount = userMapper.queryUserCount();
                    redisTemplate.opsForValue().set(Constant.USERCOUNT,userCount,30, TimeUnit.SECONDS);
                }
            }
        }
        return userCount;
    }
}
