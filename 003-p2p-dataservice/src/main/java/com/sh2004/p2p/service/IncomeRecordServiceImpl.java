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
        //tkmybatis 查询标记为1(已经满标)的产品
        Example example = new Example(LoanInfo.class);
        example.createCriteria().andEqualTo("productStatus", 1);
        List<LoanInfo> loanInfoList = loanInfoMapper.selectByExample(example);
        //遍历满标投资产品
        for (LoanInfo loanInfo : loanInfoList) {
            //查询所有此满标产品对应的投资记录 投资记录表中含有所有投资信息
            List<BidInfo> bidInfoList = bidInfoMapper.selectByLoanId(loanInfo.getId());
            for (BidInfo bidInfo : bidInfoList) {
                //新建计划收益对象
                IncomeRecord incomeRecord = new IncomeRecord();
                incomeRecord.setBidId(bidInfo.getId());
                incomeRecord.setBidMoney(bidInfo.getBidMoney());
                //从产品收益日期中取出收益周期(例如6,默认为月) 利用cal 方法的ADD 在现有时间上加上收益周期
                Date date = new Date();
                Calendar cal = Calendar.getInstance();
                cal.setTime(date);
                cal.add(Calendar.MONTH, loanInfo.getCycle());
                incomeRecord.setIncomeDate(cal.getTime());
                //将转账给客户没有状态设为0(1为已经转账)
                incomeRecord.setIncomeStatus(0);
                incomeRecord.setLoanId(loanInfo.getId());
                incomeRecord.setUid(bidInfo.getUid());
                //这里是因为数据库中 新手包的周期为7 而单位为天不是月,所有将它提出来 不用*30
                if (loanInfo.getProductType() == 0) {
                    Double money = bidInfo.getBidMoney() * loanInfo.getRate() / 36500 * loanInfo.getCycle();
                    money = Double.parseDouble(df.format(money));
                    incomeRecord.setIncomeMoney(money);
                } else {
                    Double money = bidInfo.getBidMoney() * loanInfo.getRate() / 36500 * loanInfo.getCycle() * 30;
                    money = Double.parseDouble(df.format(money));
                    incomeRecord.setIncomeMoney(money);
                }
                //新建计划收益 如果新建成功 flag为true
                int i = incomeRecordMapper.insertSelective(incomeRecord);
                if (i > 0) {
                    flag = true;
                } else {
                    flag = false;
                }
            }
            //如果flag为false 则回滚
            if (!flag) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return;
            }
            //将产品状态改为2,(0 未满标 1满标 2满标已生成计划)
            loanInfo.setProductStatus(2);
            loanInfoMapper.updateByPrimaryKeySelective(loanInfo);
        }
    }

    @Override
    @Transactional
    public void generateIncomeBack() {
        //查询所有日期小于现在的收益计划
        List<IncomeRecord> incomeRecords = incomeRecordMapper.selectStatus();
        //查出来后进行转账给账户
        for (IncomeRecord incomeRecord : incomeRecords) {
            Example example = new Example(FinanceAccount.class);
            example.createCriteria().andEqualTo("uid",incomeRecord.getUid());
            FinanceAccount financeAccount = financeAccountMapper.selectOneByExample(example);
            Double money = financeAccount.getAvailableMoney()+incomeRecord.getIncomeMoney();
            financeAccount.setAvailableMoney(money);
            financeAccountMapper.updateByPrimaryKeySelective(financeAccount);
            //将收益计划状态改为1 (已转账给客户)
            incomeRecord.setIncomeStatus(1);
            incomeRecordMapper.updateByPrimaryKeySelective(incomeRecord);
        }
    }


}
