package com.sh2004.p2p;

import com.sh2004.p2p.eneity.BBidInfo;

public interface BBidInfoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(BBidInfo record);

    int insertSelective(BBidInfo record);

    BBidInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BBidInfo record);

    int updateByPrimaryKey(BBidInfo record);
}