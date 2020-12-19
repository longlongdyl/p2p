package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sh2004.p2p.constant.Constant;
import com.sh2004.p2p.eneity.FinanceAccount;
import com.sh2004.p2p.eneity.User;
import com.sh2004.p2p.mapper.FinanceAccountMapper;
import com.sh2004.p2p.mapper.UserMapper;
import com.sh2004.p2p.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
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
    @Autowired
    private FinanceAccountMapper financeAccountMapper;


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

    @Override
    public User Login(User user) {
        return userMapper.queryLoginUser(user);
    }

    @Override
    public String insertUser(String phone, String loginPassword) {
        Example example = new Example(User.class);
        example.createCriteria().andEqualTo("phone",phone);
        int i = userMapper.selectCountByExample(example);
        if (i!=0){
            return "手机号码已经存在";
        }else {
            User user = new User();
            user.setPhone(phone);
            user.setLoginPassword(loginPassword);
            user.setAddTime(new Date());
            userMapper.insertSelective(user);
            //送888大礼包
            example= new Example(User.class);
            example.createCriteria().andEqualTo
                    ("phone",user.getPhone()).
                    andEqualTo("loginPassword",user.getLoginPassword());
            User newUser = userMapper.selectOneByExample(example);
            FinanceAccount financeAccount = new FinanceAccount();
            financeAccount.setUid(newUser.getId());
            financeAccount.setAvailableMoney(888D);
            financeAccountMapper.insertSelective(financeAccount);
            return "注册成功";
        }
    }
}
