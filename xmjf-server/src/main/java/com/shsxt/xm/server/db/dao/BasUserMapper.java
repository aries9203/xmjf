package com.shsxt.xm.server.db.dao;

import com.shsxt.xm.api.po.BasUser;

public interface BasUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasUser record);

    int insertSelective(BasUser record);

    BasUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasUser record);

    int updateByPrimaryKey(BasUser record);
}