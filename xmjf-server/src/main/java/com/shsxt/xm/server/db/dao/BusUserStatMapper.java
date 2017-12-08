package com.shsxt.xm.server.db.dao;

import com.shsxt.xm.api.po.BusUserStat;
import com.shsxt.xm.api.po.BusUserStatKey;

public interface BusUserStatMapper {
    int deleteByPrimaryKey(BusUserStatKey key);

    int insert(BusUserStat record);

    int insertSelective(BusUserStat record);

    BusUserStat selectByPrimaryKey(BusUserStatKey key);

    int updateByPrimaryKeySelective(BusUserStat record);

    int updateByPrimaryKey(BusUserStat record);
}