<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>焦点切换</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel='stylesheet' href='http://fonts.googleapis.com/css?family=Open+Sans+Condensed:300,700' type='text/css'>
	<link rel="stylesheet" href="static/jsindex/css/edslider.css">
	<link rel="stylesheet" href="static/jsindex/css/styles.css">
	<link rel="stylesheet" href="static/jsindex/css/animate-custom.css">

  </head>
  
 <body>

	<div class="container">
		<ul class="mySlideshow">
			<li class="first">
				<a href="#" target="_blank" class="animated fadeInLeft">
					<img src="images/hd-logo.png" width="330" height="255" alt="Harley-Davidson">
				</a>
				<div class="animated fadeInRight">It's time to ride!</div>
			</li>
			<li class="second">
				<img src="images/second-title.png" class="animated fadeInRight">
			</li>
			<li class="third">
				<img src="images/third-title.png" class="animated fadeInLeft">
			</li>
			<li class="fourth">
				<img src="images/fourth.jpg" class="animated fadeInRight">
			</li>
			<li class="fifth">
				<img src="images/fifth-title.png" class="animated fadeInLeft">
			</li>
		</ul>
	</div>

	
	<script src="static/jsindex/js/jquery-1.11.0.min.js"></script>
	<script src="static/jsindex/js/jquery.edslider.js"></script>
	<script>
		$(document).ready(function(){
			//Call plugin
			$(".mySlideshow").edslider({
				width : '100%',
				height: 500
			});
		});
	</script>
</body>
</html>
