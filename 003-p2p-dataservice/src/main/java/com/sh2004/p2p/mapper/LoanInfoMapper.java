package com.sh2004.p2p.mapper;

import com.sh2004.p2p.eneity.LoanInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/15 17:26
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Repository
public interface LoanInfoMapper extends Mapper<LoanInfo> {
    Double queryAvgRate();
}
