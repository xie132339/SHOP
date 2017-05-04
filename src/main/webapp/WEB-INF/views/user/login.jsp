<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
ResourceBundle res = ResourceBundle.getBundle("messageResources_zh_CN");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<form id="saves" action="" method="post">
  		<input type="text" id="namesd" name="name">
  		<input type="password" id="pass" name="password">
  		<input type="button"  onclick="saveLogins()" value="GO">
  	</form>
  	<form method="post" id="userFormTable" action="<%=path %>/userLogin/userLoginInit.action">
  		<input type="hidden" value="<%=path %>" id="paths">
  		<input type="hidden" name="files" value="UserExec.xls">
		<input type="hidden" name="title" value="UserExec">
  		<table>
  			<tr><td>
  				<input onclick="oncli()" type="submit" value="查询">
  				<input id="exec" type="button" value="导出">
  				<input type="button" value="下载模板" onclick="onHref('<%=path %>/commonUpload/downloadTemplates.do?downloadPath=&fileName=导入模板.xls')">
  				<input id="file" type="file" name="filename">
  				<input type="button" onclick="downl()" value="导入">
  			</td></tr>
  			<tr>
  				<th>Id<%=res.getString("userName") %> </th>
  				<th>N</th>
  				<th>P</th>
  			</tr>
  				<c:forEach items="${list }" var="l">
  					<tr>
  						<td>${l.id }</td>
  						<td>${l.name }</td>
  						<td>${l.password }</td>
  					</tr>
  				</c:forEach>
  		</table>
  	</form>
  	
  	<script src="static/jsindex/js/jquery-1.11.0.min.js"></script>
  <script type="text/javascript">
  	$("#exec").click(function(){
  		var _form = $("#userFormTable");
  		var _action = $(_form).attr("action");
  		_form.attr("action", "userLogin/importUser.do");
		_form.submit();
		_form.attr("action", _action);
  	}); 
  	function downl(){
  		var formData = new FormData(document.getElementById("userFormTable"));
  		console.info(formData);
		$.ajax({
				url : $("#paths").val()+"/userLogin/downUser.do",
				type : "POST",
				data : formData,
				dataType : "json",
				async: false,
				cache: false,
				contentType: false,
				processData: false,
				error : function(data){
					alert("s");
				},
				success : function(data) {
					var result = data;
					alert(result.message);
				}
			});
		}
		
	function onHref(url){
		var gotoLink = document.createElement('a');
	    gotoLink.href = url;
	    document.body.appendChild(gotoLink);
	    gotoLink.click();
	}
	
	function saveLogins(){
  		/* var name = $("#namesd").val();
  		var passd = $("#pass").val();
  		var data = {
  			"pass" : passd,
  			"name" : name
  		}; */
  		//console.info(JSON.stringify(data));
  		var formData = new FormData(document.getElementById("saves"));
  		$.ajax({
  			url : $("#paths").val()+"/userLogin/saveLogin.do",
			type : "POST",
			data : formData,
			dataType : "json",
  		});
  	}
		</script>
  </body>
</html>
