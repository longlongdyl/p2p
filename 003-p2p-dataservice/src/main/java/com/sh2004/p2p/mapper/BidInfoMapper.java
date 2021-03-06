package com.sh2004.p2p.mapper;

import com.sh2004.p2p.eneity.BidInfo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/15 17:15
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Repository
public interface BidInfoMapper extends Mapper<BidInfo> {
    Double queryTotalMoney();

    List<Map<String,String>> queryTop5User();

    List<Map<String, String>> selectByUserId(Integer id);

    List<BidInfo> selectByLoanId(Integer id);


}
