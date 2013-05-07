<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="css/bigdata.css" rel="stylesheet" type="text/css" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Bigdata</title>
</head>
<body>
<table width="1000" align="center">
  <tr><td><%@ include file="head.jsp" %></td></tr>
  <tr><td><span class="STYLE4">登录成功</span></td></tr>
  <tr><td><hr></td></tr>
  <a class="STYLE2" href="<%=request.getServerName()%>:<%=request.getServerPort() %>/index">帐户信息</a>
  <tr><td><hr></td></tr>
</table>
</body>
</html>
