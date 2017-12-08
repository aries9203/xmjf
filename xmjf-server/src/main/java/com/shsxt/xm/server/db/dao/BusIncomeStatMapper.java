package com.shsxt.xm.server.db.dao;

import com.shsxt.xm.api.po.BusIncomeStat;

public interface BusIncomeStatMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BusIncomeStat record);

    int insertSelective(BusIncomeStat record);

    BusIncomeStat selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BusIncomeStat record);

    int updateByPrimaryKey(BusIncomeStat record);
}