package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sh2004.p2p.eneity.IncomeRecord;
import com.sh2004.p2p.mapper.IncomeRecordMapper;
import com.sh2004.p2p.mapper.LoanInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/17 19:06
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service(interfaceClass = IncomeRecordService.class,timeout = 10000,version = "1.0.0")
@Component
@Slf4j
public class IncomeRecordServiceImpl implements IncomeRecordService {
    @Autowired
    private IncomeRecordMapper incomeRecordMapper;



    @Override
    public List<IncomeRecord> selectByUserId(Integer id) {

        return incomeRecordMapper.selectByUserId(id);
    }

    @Override
    public void investLoan(Integer bidId ,String money, String id, String uid, String incomeMoney) {
        IncomeRecord incomeRecord = new IncomeRecord();
        incomeRecord.setBidId(bidId);
        incomeRecord.setBidMoney(Double.parseDouble(money));
        //这个日期有问题,收入日期应该为new date + 收益的月数 比如6个月 以后再改
        incomeRecord.setIncomeDate(new Date());
        incomeRecord.setIncomeStatus(0);
        incomeRecord.setLoanId(Integer.parseInt(id));
        incomeRecord.setUid(Integer.parseInt(uid));
        incomeRecord.setIncomeMoney(Double.parseDouble(incomeMoney));
        incomeRecordMapper.insertSelective(incomeRecord);

    }
}
