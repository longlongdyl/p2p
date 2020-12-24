package com.sh2004.p2p.service;

import com.sh2004.p2p.eneity.BidInfo;
import com.sh2004.p2p.result.Result;

import java.util.List;
import java.util.Map;

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
public interface BidInfoService {
    Double queryTotalMoney();

    List<Map<String,String>> queryTop5User();

    List<Map<String,String>> selectByUserId(Integer id);

    List<BidInfo> selectByLoanId(Integer id);

    Result investLoan(String money, String id, String uid ,String incomeMoney,String cycle);
}
