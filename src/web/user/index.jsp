<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="../css/bigdata.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="../js/bigdata-aaa.js" ></script>
<script language="javascript" src="../js/jquery.js" ></script>

<%@page import="com.bigdata.aaa.db.User"%>
<%@page import="java.util.Date"%><html>
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
  <tr><td><span class="STYLE2">基本信息</span> <a class="STYLE3" href="changepwd.jsp">修改密码</a> <a class="STYLE3" href="accesskey.jsp">访问凭证</a> </td></tr>
  <tr><td><hr></td></tr>
  <tr><td>
    <table class="STYLE2">
      <tr>
        <td width="300">用户ID</td>
        <td width="600"><%=user.getUserID() %></td>
      </tr>
      <tr>
        <td>注册邮箱</td>
        <td><%=user.getUsername() %></td>
      </tr>
      <tr>
        <td>用户名</td>
        <td><%=user.getDispname() %></td>
      </tr>
      <tr>
        <td>注册时间</td>
        <td><%=new Date(user.getRegTime()) %></td>
      </tr>
      <tr>
        <td>激活时间</td>
        <td><%=new Date(user.getActTime()) %></td>
      </tr>
    </table>
  </td></tr>
  <tr><td><hr></td></tr>
</table>
</body>
</html>
