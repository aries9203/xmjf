package com.shsxt.xm.server.service;

import com.alibaba.fastjson.JSON;
import com.shsxt.xm.api.po.BasUser;
import com.shsxt.xm.api.service.IBasUserService;
import com.shsxt.xm.api.service.ISmsService;
import com.shsxt.xm.api.utils.AssertUtil;
import com.shsxt.xm.server.SmsType;
import com.shsxt.xm.server.constant.TaoBaoConstant;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Service
public class SmsServiceImpl implements ISmsService {

    @Resource
    private IBasUserService basUserService;

    @Override
    public void sendPhoneSms(String phone, String code, Integer type) {
        AssertUtil.isTrue(StringUtils.isBlank(phone),"手机号不能为空");
        AssertUtil.isTrue(StringUtils.isBlank(code),"验证码不能为空!");
        AssertUtil.isTrue(!SmsType.REGISTER.getType().equals(type)&&!SmsType.NOTIFY.getType().equals(type)
        ,"短信类型不一致");

        if(SmsType.REGISTER.getType().equals(type)){

            BasUser basUser =   basUserService.queryBasUserByPhone(phone);

            AssertUtil.isTrue(basUser!=null,"该手机号注册过了");
            doSend(phone,code);
            
        }
        if(type.equals(SmsType.NOTIFY.getType())){
            doSend(phone,code);
        }

    }

    private void doSend(String phone, String code) {

        TaobaoClient client = new DefaultTaobaoClient(TaoBaoConstant.SERVER_URL, TaoBaoConstant.APP_KEY, TaoBaoConstant.APP_SECRET);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("");
        req.setSmsType(TaoBaoConstant.SMS_TYPE);
        req.setSmsFreeSignName(TaoBaoConstant.SMS_FREE_SIGN_NAME);

        Map<String,String> map =  new HashMap<>();
        map.put("code",code);

        req.setSmsParamString(JSON.toJSONString(map));
        req.setRecNum(phone);
        req.setSmsTemplateCode(TaoBaoConstant.SMS_TEMATE_CODE);
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
            boolean b = rsp.isSuccess();
            AssertUtil.isTrue(!rsp.isSuccess(),"短信发送失败");
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
    }

}
