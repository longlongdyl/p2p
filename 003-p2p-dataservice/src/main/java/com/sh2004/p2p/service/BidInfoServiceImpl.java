package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sh2004.p2p.constant.Constant;
import com.sh2004.p2p.eneity.BidInfo;
import com.sh2004.p2p.eneity.IncomeRecord;
import com.sh2004.p2p.eneity.LoanInfo;
import com.sh2004.p2p.mapper.BidInfoMapper;
import com.sh2004.p2p.mapper.FinanceAccountMapper;
import com.sh2004.p2p.mapper.IncomeRecordMapper;
import com.sh2004.p2p.mapper.LoanInfoMapper;
import com.sh2004.p2p.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;


import java.util.Calendar;
import java.util.Date;
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
@Service(interfaceClass = BidInfoService.class, timeout = 10000, version = "1.0.0")
@Component
@Slf4j
public class BidInfoServiceImpl implements BidInfoService {
    @Autowired
    private BidInfoMapper bidInfoMapper;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;
    @Autowired
    private IncomeRecordMapper incomeRecordMapper;
    @Autowired
    private LoanInfoMapper loanInfoMapper;

    @Autowired
    RedisTemplate<Object, Object> redisTemplate;

    @Override
    public Double queryTotalMoney() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        Double totalMoney = (Double) redisTemplate.opsForValue().get(Constant.TOTALMONEY);
        if (null == totalMoney) {
            synchronized (this) {
                totalMoney = (Double) redisTemplate.opsForValue().get(Constant.TOTALMONEY);
                if (null == totalMoney) {
                    totalMoney = bidInfoMapper.queryTotalMoney();
                    redisTemplate.opsForValue().set(Constant.TOTALMONEY, totalMoney, 30, TimeUnit.SECONDS);
                }
            }
        }
        return totalMoney;
    }

    @Override
    public List<Map<String, String>> queryTop5User() {
        return bidInfoMapper.queryTop5User();

    }

    @Override
    public List<Map<String, String>> selectByUserId(Integer id) {
        return bidInfoMapper.selectByUserId(id);
    }

    @Override
    public List<BidInfo> selectByLoanId(Integer id) {
        return bidInfoMapper.selectByLoanId(id);
    }

    @Override
    @Transactional
    public Result investLoan(String money, String id, String uid, String incomeMoney, String cycle) {
        BidInfo bidInfo = new BidInfo();
        bidInfo.setBidMoney(Double.parseDouble(money));
        bidInfo.setLoanId(Integer.parseInt(id));
        bidInfo.setUid(Integer.parseInt(uid));
        bidInfo.setBidTime(new Date());
        bidInfo.setBidStatus(1);
        bidInfoMapper.insertSelective(bidInfo);


        int i = financeAccountMapper.investLoan(money, uid);
        if (i == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("余额不足");
        }


        int j = loanInfoMapper.investLoan(id, money);
        if (j == 0) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return Result.error("该产品余额不足");
        }
        LoanInfo loanInfo = loanInfoMapper.selectByPrimaryKey(Integer.parseInt(id));
        if (loanInfo.getLeftProductMoney() == 0 && loanInfo.getProductStatus() != 1) {
            loanInfo.setProductStatus(1);
            loanInfo.setProductFullTime(new Date());
            loanInfoMapper.updateByPrimaryKeySelective(loanInfo);
            return Result.error("投资成功");
        } else {
            return Result.success("投资成功");
        }
    }
}
