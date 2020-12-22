package com.sh2004.p2p.service;

import com.sh2004.p2p.eneity.IncomeRecord;

import java.util.List;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.service
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/15 17:12
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
public interface IncomeRecordService {
    List<IncomeRecord> selectByUserId(Integer id);

    //void investLoan(Integer bidId ,String money, String id, String uid, String incomeMoney);
}
