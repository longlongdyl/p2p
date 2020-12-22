package com.sh2004.p2p.service;

import com.sh2004.p2p.eneity.FinanceAccount;
import com.sh2004.p2p.result.Result;

import javax.servlet.http.HttpSession;

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
public interface FinanceAccountService {
    Double selectByUserId(Integer id);

}
