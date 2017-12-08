package com.shsxt.xm.server.db.dao;

import com.shsxt.xm.api.po.SysAppSettings;

public interface SysAppSettingsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SysAppSettings record);

    int insertSelective(SysAppSettings record);

    SysAppSettings selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SysAppSettings record);

    int updateByPrimaryKey(SysAppSettings record);
}