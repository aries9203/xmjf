package com.shsxt.xm.server.db.dao;

import com.shsxt.xm.api.po.BasUserInfo;

public interface BasUserInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BasUserInfo record);

    int insertSelective(BasUserInfo record);

    BasUserInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BasUserInfo record);

    int updateByPrimaryKey(BasUserInfo record);
}