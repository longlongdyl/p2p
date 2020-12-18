package com.sh2004.p2p.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.sh2004.p2p.eneity.FinanceAccount;
import com.sh2004.p2p.mapper.FinanceAccountMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/17 21:45
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Service(interfaceClass = FinanceAccountService.class,timeout = 10000,version = "1.0.0")
@Component
@Slf4j
public class FinanceAccountServiceImpl implements FinanceAccountService {
    @Autowired
    private FinanceAccountMapper financeAccountMapper;
    @Override
    public Double selectByUserId(Integer id) {
        Example example = new Example(FinanceAccount.class);
        example.createCriteria().andEqualTo("uid",id);
        FinanceAccount financeAccount = financeAccountMapper.selectOneByExample(example);
        return financeAccount.getAvailableMoney();
    }
}
