<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="../css/bigdata.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="../js/bigdata-aaa.js" ></script>
<script language="javascript" src="../js/jquery.js" ></script>

<%@page import="com.bigdata.aaa.db.User"%><html>
<%
  User user = (User)session.getAttribute("USER");
%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Bigdata</title>
</head>
<body>
<table width="1000" align="center">
  <tr><td><%@ include file="../head.jsp" %></td></tr>
  <tr><td><span class="STYLE4">用户信息</span></td></tr>
  <tr><td><hr></td></tr>
  <tr><td><a class="STYLE3" href="index.jsp">基本信息</a> <span class="STYLE2">修改密码</span> <a class="STYLE3" href="accesskey.jsp">访问凭证</a> </td></tr>
  <tr><td><hr></td></tr>
  <tr><td>
    <table class="STYLE2">
      <tr>
        <td width="300">旧密码</td>
        <td width="600"><input id="oldpassword" type="password"></td>
      </tr>
      <tr>
        <td>新密码</td>
        <td><input id="newpassword" type="password"></td>
      </tr>
      <tr>
        <td>重复新密码</td>
        <td><input id="newpassword2" type="password"></td>
      </tr>
      <tr>
        <td></td>
        <td><button onClick="changepwd()">重置密码</button></td>
      </tr>
    </table>
  </td></tr>
  <tr><td><hr></td></tr>
</table>
</body>
</html>
