package com.shsxt.xm.api.service;

import com.shsxt.xm.api.po.BasUser;

public interface IBasUserService {
    public BasUser queryBasUserById(Integer id);

    BasUser queryBasUserByPhone(String phone);

    public void saveBasUser(String phone,String password );


}
