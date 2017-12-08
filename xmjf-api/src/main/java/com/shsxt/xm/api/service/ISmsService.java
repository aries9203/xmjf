package com.shsxt.xm.api.service;

public interface ISmsService {


    //发送手机验证码
    public void sendPhoneSms(String phone,String code,Integer type);


}
