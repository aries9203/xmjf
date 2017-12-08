package com.shsxt.xm.web.controller;

import com.shsxt.xm.api.constant.P2PConstant;
import com.shsxt.xm.api.exceptions.ParamsExcetion;
import com.shsxt.xm.api.model.ResultInfo;
import com.shsxt.xm.api.po.BasUser;
import com.shsxt.xm.api.service.IBasUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by lp on 2017/12/7.
 */
@Controller
@RequestMapping("user")
public class BasUserController {
    @Resource
    private IBasUserService basUserService;




    @RequestMapping("queryBasUserById")
    @ResponseBody
    public BasUser queryBasUserById(Integer id){
        return  basUserService.queryBasUserById(id);
    }

    @RequestMapping("register")
    @ResponseBody
    public ResultInfo userRegister (String phone, String picCode, String code, String password, HttpSession session){
        ResultInfo resultInfo = new ResultInfo();
        String sessionPicCode = (String) session.getAttribute(P2PConstant.PICTURE_VERIFY_CODE);
        if(StringUtils.isBlank(sessionPicCode)){
            resultInfo.setCode(P2PConstant.OPS_FAILED_CODE);
            resultInfo.setMsg("验证码已失效!");
            return  resultInfo;
        }
        if(!picCode.equals(sessionPicCode)){
            resultInfo.setCode(P2PConstant.OPS_FAILED_CODE);
            resultInfo.setMsg("验证码不匹配!");
            return  resultInfo;
        }
        Date sessionTime = (Date) session.getAttribute(P2PConstant.PHONE_VERIFY_CODE_EXPIRE_TIME+phone);
        if(null==sessionTime){
            resultInfo.setCode(P2PConstant.OPS_FAILED_CODE);
            resultInfo.setMsg("手机验证码已失效!");
            return  resultInfo;

        }
        Date currTime = new Date();
        long time = (currTime.getTime()-sessionTime.getTime())/1000;
        if(time>180){
            resultInfo.setCode(P2PConstant.OPS_FAILED_CODE);
            resultInfo.setMsg("手机验证码已失效!");
            return  resultInfo;
        }
        String sessionCode = (String) session.getAttribute(P2PConstant.PHONE_VERIFY_CODE+phone);
        if(!sessionCode.equals(code)){
            resultInfo.setCode(P2PConstant.OPS_FAILED_CODE);
            resultInfo.setMsg("手机验证码不正确!");
            return  resultInfo;
        }

        try {
            basUserService.saveBasUser(phone,password);
            session.removeAttribute(P2PConstant.PICTURE_VERIFY_CODE);
            session.removeAttribute(P2PConstant.PHONE_VERIFY_CODE+phone);
            session.removeAttribute(P2PConstant.PHONE_VERIFY_CODE_EXPIRE_TIME+phone);
        } catch (ParamsExcetion e) {
            e.printStackTrace();
            resultInfo.setCode(P2PConstant.OPS_FAILED_CODE);
            resultInfo.setMsg(e.getErrorMsg());
        }catch (Exception e) {
            resultInfo.setCode(P2PConstant.OPS_FAILED_CODE);
            resultInfo.setMsg(P2PConstant.OPS_FAILED_MSG);
        }
        resultInfo.setCode(200);
        return  resultInfo;

    }
}
