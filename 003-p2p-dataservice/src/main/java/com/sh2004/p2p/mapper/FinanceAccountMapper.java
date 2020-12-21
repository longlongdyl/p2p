package com.sh2004.p2p.mapper;

import com.sh2004.p2p.eneity.FinanceAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/17 21:46
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Repository
public interface FinanceAccountMapper extends Mapper<FinanceAccount> {
    void investLoan(@Param("money") String money, @Param("uid") String uid);
}
