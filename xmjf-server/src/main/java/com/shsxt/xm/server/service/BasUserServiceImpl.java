package com.shsxt.xm.server.service;

import com.shsxt.xm.api.po.BasUser;
import com.shsxt.xm.api.service.IBasUserService;
import com.shsxt.xm.server.db.dao.BasUserDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Service
public class BasUserServiceImpl implements IBasUserService {
    @Resource
    private BasUserDao userDao;
    @Override
    public BasUser queryBasUserById(Integer id) {
        return userDao.queryBasUserById(id);
    }

    @Override
    public BasUser queryBasUserByPhone(String phone) {
        return userDao.queryBasUserByPhone(phone);
    }
}
