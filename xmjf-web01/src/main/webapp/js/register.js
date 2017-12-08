/**
 * Created by lp on 2017/12/8.
 */
$(function () {
    // 给图片标签绑定点击事件
/*    $(".validImg").click(function () {
        $(this).attr("src",ctx+"/img/getPictureVerifyImage?time="+new Date());
    })*/
    $(".validImg").click(function () {
        swithPicCode();
    });
    function  swithPicCode() {
        $(".validImg").attr("src",ctx+"/img/getPictureVerifyImage?time="+new Date());
    }



    $("#clickMes").click(function () {
        var phone = $("#phone").val();
        var code = $("#code").val();
        if(isEmpty(phone)){
            layer.tips("手机号码不能为空","#phone");
            return
        }
        if(isEmpty(code)){
            layer.tips("验证码不能为空",".validImg");
            return
        }
        var  _this=$(this);
        $.ajax({
            type:"post",
            url:ctx+"/sms/sendPhoneSms",
            data:{
                phone:phone,
                picCode:code,
                type:1
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    time(_this);
                }else{
                    layer.tips(data.msg,"#clickMes");
                }
            }
        })



    });

    var wait = 10;
    function time(o) {
        if(0==wait){
            o.removeAttr("disabled");
            o.val('获取验证码');
            o.css("color", '#ffffff');
            o.css("background","#fcb22f");
            wait = 10;
        }else {
            o.attr("disabled", true);
            o.css("color", '#fff');
            o.css("background", '#ddd');
            o.val("重新发送(" + wait + "s)");
            wait--;
            setTimeout(function() {
                time(o)
            }, 1000)
        }
        
    }
    //注册点击事件
    $("#register").click(function () {
        var phone=$("#phone").val();
        var picCode=$("#code").val();
        var code=$("#verification").val();
        var password=$("#password").val();
        if(isEmpty(phone)){
            layer.tips("手机号不能为空!","#phone");
            return;
        }
        if(isEmpty(picCode)){
            layer.tips("请输入图形验证码!","#clickMes");
            swithPicCode();
            return;
        }
        if(isEmpty(code)){
            layer.tips("请输入手机验证码!","#verification");
            swithPicCode();
            return;
        }
        if(isEmpty(password)){
            layer.tips("请输入密码!","#password");
            swithPicCode();
            return;
        }
        $.ajax({
            type:"post",
            url:ctx+"/user/register",
            data:{
                picCode:picCode,
                code:code,
                phone:phone,
                password:password
            },
            dataType:"json",
            success:function (data) {
                if(data.code==200){
                    layer.tips("注册成功!","#register") ;
                    setTimeout(function () {
                        window.location.href=ctx+"/login";
                        
                    },1500);
                }else{
                    layer.tips(data.msg,"#register");
                }
            }
        })
    })

});
