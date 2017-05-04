<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.ilonw.module.lis.common.*" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<!--[if IE 7]>  <html class="ie7 lt-ie10">  <![endif]-->
<!--[if IE 8]>  <html class="ie8 lt-ie10">  <![endif]-->
<!--[if IE 9]>  <html class="ie9 lt-ie10">  <![endif]-->
<!--[if gt IE 9]>   
<!--> <html> <!--<![endif]-->
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>注册 － 丽诗</title>
	<meta name="description" content="丽诗是白领时尚消费品牌。建立300万全球时尚品牌商品库，更好的满足白领的时尚消费需求。" />
	<meta name="keywords" content="丽诗,丽诗官网,丽诗首页,丽诗登录,导购,白领,网购" />

	 <link rel="stylesheet" type="text/css" href="<%=Constants.ILONW_STYLE %>login/login.css" />
	 <script type="text/javascript" src="<%=Constants.ILONW_STYLE %>jquery/jquery-1.8.3.min.js"></script>
</head>
<body>
	<%
  		/***************************获取IP信息**********************************/
	  	String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length()==0||"unknwn".equalsIgnoreCase(ip)){
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length()==0||"unknwn".equalsIgnoreCase(ip)){
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length()==0||"unknwn".equalsIgnoreCase(ip)){
			ip = request.getRemoteAddr();
		}
  	%>
	<div class="wrap">
    <div class="reg_wrap">
        <div class="head"><a href="javascript:;;" class="reg_logo"></a></div>
        <div class="main">
            <div class="ad_wrap"><img src="<%=Constants.ILONW_STYLE %>login/ad_img.jpg" width="370" height="353" /></div>
            <div class="reg_form" id="mob_reg">
                <form id="registerForm" method="POST" enctype="application/json" action="registeruser.htm">
                    <h3><a href="login.htm?ie=utf-8&f=8&rsv_bp=1&tn=monline_3_dg&rsv_pq=e26f34a900040c19&rsv_t=20daYbdZrZl5XR9Cu1t3l4jJjMOv2t%2FnftXPshwHb%2FySbkn8d4NyhEvk1OgH30oLJbmo&rqlang=cn&rsv_enter=1&inputT=12&rsv_sug3=46&rsv_sug1=33&rsv_sug7=100&rsv_sug2=0&rsv_sug4=9042">登录</a>注册丽诗</h3>
                      <div class="loginErrorMessage none_f" style="font-size:14px;text-align:center;">${msgs}</div>
                    <div class="reg_list">
                        <p class="reg_box">
                            <input class="reg_txt" id="usertel" name="usertel" type="text" placeholder="手机号"/><span></span><span class="tel_icon"></span>
                        </p>
                    </div>
                    <div class="reg_list">
                        <p class="reg_box">
                            <input class="reg_txt" id="usernike" name="usernike" type="text" placeholder="昵称"/><span></span><span class="user_icon"></span>
                        </p>
                    </div>
                    <div class="reg_list">
                        <p class="reg_box">
                            <input class="reg_txt" id="userpwd" name="userpwd" type="password" placeholder="密码"/><span></span><span class="pwd_icon"></span>
                        </p>
                    </div>
                    <div class="reg_list">
                        <p class="reg_box">
                            <input class="reg_txt" id="conf_password" type="password" placeholder="确认密码"/><span></span><span class="pwd_icon"></span>
                        </p>
                    </div>
                    <div class="reg_btn_wrap">
                    	 <input type = "hidden" name = "usercreatetime"  id="createtime"/>
                    	 <input type = "hidden" name = "userip" value = "<%=ip %>" id="userips"/>
                         <input type="submit" class="reg_btn" value="立即注册"/>
                         <!-- <a id="LikBasket" title="立即注册" href="javascript:;;" class="reg_btn"><i></i>立即注册</a> -->
                    </div>
                    <div class="agree_reg">
                        <input type="checkbox" checked="checked" id="agreement" name="agreement" />同意<a href="javascript:;;" target="_blank">《丽诗注册条款》</a>
                    </div>
                </form>
                <div class="fast_login">
                    <h3> &nbsp;&nbsp;<a href="home.htm" style="text-decoration:none;">去商城逛逛</a></h3>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
     $(function(){
     	var tmpDate = new Date();
 		var day = tmpDate.getDate();
 		var month= tmpDate.getMonth() + 1;
 		var year= tmpDate.getFullYear();
     	var hours = tmpDate.getHours();
     	var minutes = tmpDate.getMinutes();
     	var seconds = tmpDate.getSeconds();

     	month = ((month < 10) ? "0" : "") + month;
     	day = ((day < 10) ? "0" : "") + day;
     	hours = ((hours < 10) ? "0" : "") + hours;
     	minutes = ((minutes < 10) ? "0" : "") + minutes;
     	seconds = ((seconds < 10) ? "0" : "") + seconds;

     	var createtime = year + "-" + month + "-" + day + " " + hours + ":" + minutes + ":" + seconds;
     	$("#createtime").val(createtime);
     	
      /*   var usertel = $("#usertel").val();
     	var usernike = $("#usernike").val();
     	var userpwd = $("#userpwd").val();
     	var usercreatetime = $("#createtime").val(createtime);
     	var userip = $("#userip").val();
     	$("#LikBasket").on("click",function(event){
		    event.preventDefault();//使a自带的方法失效，即无法调整到href中的URL
		    $.ajax({
		           url: "registeruser.htm",
		           type: "POST",
		           data:{usertel:usertel,usernike:usernike,userpwd:userpwd,usercreatetime:usercreatetime,userip:userip},
		           success: function(data){
		        	   if(data == "success"){
		        		   alert("添加成功");
		        	   }else{
		        		   alert("添加失败");
		        	   }
		           },
		    });
		}); */
     })
     
     /*   var usertel = $("#usertel").val();
     	var usernike = $("#usernike").val();
     	var userpwd = $("#userpwd").val();
     	var usercreatetime = $("#createtime").val(createtime);
     	var userip = $("#userip").val();
    	 $(".reg_btn").click(function(){
    		 $.ajax({
				url:"registeruser.htm",
				type:"post",
				data:{"usertel":usertel,"usernike":usernike,"userpwd":userpwd,"usercreatetime":usercreatetime,"userip":userip},
				dataType:"json",
				contentType:'application/json;charset=UTF-8',
				success:function(data){
					if(data=="success"){
						alert("success");
					}else{
						alert("error");
					}
				},
				error:function(e){
   		        	alert("err");   
   		        },   
			});
	   });  */
</script>
</body>
</html>