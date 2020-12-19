package com.sh2004.p2p.mapper;

import com.sh2004.p2p.eneity.IncomeRecord;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @ProjectName: p2p
 * @Package: com.sh2004.p2p.mapper
 * @Description: java类作用描述
 * @Author: 邓禹龙
 * @CreateDate: 2020/12/17 19:07
 * @Version: 1.0
 * <p>
 * Copyright: Copyright (c) 2020
 */
@Repository
public interface IncomeRecordMapper extends Mapper<IncomeRecord> {

    List<IncomeRecord> selectByUserId(Integer id);
}
