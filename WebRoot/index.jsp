<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
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
  
  	<form action="${pageContext.request.contextPath }/admin/login" method="post">
  		username:<input type="text" name="username"/>
  		passwors:<input type="password" name="password"/>
  		<input type="submit" value="登录"/>
  	</form>
   
   <!-- 
    <form action="${pageContext.request.contextPath }/superadmin/addAdmin" method="post">
   		username:<input type="text" name="username"/>
  		password:<input type="password" name="password"/>
  		nick:<input type="text" name="nick"/>
  		<input type="submit" value="注册"/>
    </form>
    -->
    <!--  
    <form action="${pageContext.request.contextPath }/superadmin/updateSuperAdminPassword" method="post">
   		oldPassword:<input type="password" name="oldPassword"/>
  		newPassword:<input type="password" name="newPassword"/>
  		reqnewPassword:<input type="password" name="reqnewPassword"/>
  		<input type="submit" value="提交"/>
    </form>
     -->
  </body>
</html>
