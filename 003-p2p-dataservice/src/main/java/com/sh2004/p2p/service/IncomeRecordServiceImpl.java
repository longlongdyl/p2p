package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sh2004.p2p.eneity.BidInfo;
import com.sh2004.p2p.eneity.FinanceAccount;
import com.sh2004.p2p.eneity.IncomeRecord;
import com.sh2004.p2p.eneity.LoanInfo;
import com.sh2004.p2p.mapper.BidInfoMapper;
import com.sh2004.p2p.mapper.FinanceAccountMapper;
import com.sh2004.p2p.mapper.IncomeRecordMapper;
import com.sh2004.p2p.mapper.LoanInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalApplicationListenerAdapter;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import tk.mybatis.mapper.entity.Example;

import java.util.Calendar;
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
@Service(interfaceClass = IncomeRecordService.class, timeout = 10000, version = "1.0.0")
@Component
@Slf4j
public class IncomeRecordServiceImpl implements IncomeRecordService {
    @Autowired
    private IncomeRecordMapper incomeRecordMapper;
    @Autowired
    private BidInfoMapper bidInfoMapper;
    @Autowired
    private LoanInfoMapper loanInfoMapper;
    @Autowired
    private FinanceAccountMapper financeAccountMapper;


    @Override
    public List<IncomeRecord> selectByUserId(Integer id) {

        return incomeRecordMapper.selectByUserId(id);
    }

    @Override
    @Transactional
    public void insertIncomeRecord() {
        boolean flag = false;
        java.text.DecimalFormat df = new java.text.DecimalFormat("#.00");

        Example example = new Example(LoanInfo.class);
        example.createCriteria().andEqualTo("productStatus", 1);
        List<LoanInfo> loanInfoList = loanInfoMapper.selectByExample(example);
        for (LoanInfo loanInfo : loanInfoList) {
            List<BidInfo> bidInfoList = bidInfoMapper.selectByLoanId(loanInfo.getId());
            for (BidInfo bidInfo : bidInfoList) {
                IncomeRecord incomeRecord = new IncomeRecord();
                incomeRecord.setBidId(bidInfo.getId());
                incomeRecord.setBidMoney(bidInfo.getBidMoney());
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.MONTH, loanInfo.getCycle());
                incomeRecord.setIncomeDate(cal.getTime());
                incomeRecord.setIncomeStatus(0);
                incomeRecord.setLoanId(loanInfo.getId());
                incomeRecord.setUid(bidInfo.getUid());
                if (loanInfo.getProductType() == 0) {
                    Double money = bidInfo.getBidMoney() * loanInfo.getRate() / 36500 * loanInfo.getCycle();
                    money = Double.parseDouble(df.format(money));
                    incomeRecord.setIncomeMoney(money);
                } else {
                    Double money = bidInfo.getBidMoney() * loanInfo.getRate() / 36500 * loanInfo.getCycle() * 30;
                    money = Double.parseDouble(df.format(money));
                    incomeRecord.setIncomeMoney(money);
                }
                int i = incomeRecordMapper.insertSelective(incomeRecord);
                if (i > 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            if (!flag) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return;
            }
            loanInfo.setProductStatus(2);
            loanInfoMapper.updateByPrimaryKeySelective(loanInfo);
        }
    }

    @Override
    @Transactional
    public void generateIncomeBack() {
        List<IncomeRecord> incomeRecords = incomeRecordMapper.selectStatus();

        for (IncomeRecord incomeRecord : incomeRecords) {
            Example example = new Example(FinanceAccount.class);
            example.createCriteria().andEqualTo("uid",incomeRecord.getUid());
            FinanceAccount financeAccount = financeAccountMapper.selectOneByExample(example);
            Double money = financeAccount.getAvailableMoney()+incomeRecord.getIncomeMoney();
            financeAccount.setAvailableMoney(money);
            financeAccountMapper.updateByPrimaryKeySelective(financeAccount);
            incomeRecord.setIncomeStatus(1);
            incomeRecordMapper.updateByPrimaryKeySelective(incomeRecord);
        }
    }


}
