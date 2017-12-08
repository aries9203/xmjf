package com.shsxt.xm.server.db.dao;

import com.shsxt.xm.api.po.BasUser;
import org.apache.ibatis.annotations.Param;

public interface BasUserDao {
    public BasUser queryBasUserById(@Param("id")Integer id);

    BasUser queryBasUserByPhone(@Param("phone")String phone);
}
