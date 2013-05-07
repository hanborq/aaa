<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="../css/bigdata.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="../js/bigdata-aaa.js" ></script>
<script language="javascript" src="../js/jquery.js" ></script>

<%@page import="com.bigdata.aaa.db.User"%><html>
<%
  User user = (User)session.getAttribute("USER");
%>
<script language="javascript">
var accessKeyList;
var selectIndex;
</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Bigdata</title>
</head>
<body>
<table width="1000" align="center">
  <tr><td><%@ include file="../head.jsp" %></td></tr>
  <tr><td><span class="STYLE4">用户信息</span></td></tr>
  <tr><td><hr></td></tr>
  <tr><td><a class="STYLE3" href="index.jsp">基本信息</a> <a class="STYLE3" href="changepwd.jsp">修改密码</a> <span class="STYLE2">访问凭证</span> </td></tr>
  <tr><td><hr></td></tr>
  <tr><td>
  <button id="newAccessKeyButton" onClick="javascript:newAccessKey()" >创建</button>
  <button id="activateAccessKeyButton" onClick="javascript:activateAccessKey()">激活</button>
  <button id="deactivateAccessKeyButton" onClick="javascript:deactivateAccessKey()">失效</button>
  <button id="delAccessKeyButton" onClick="javascript:delAccessKey()">删除</button>
  <button id="viewAccessKeyButton" onClick="javascript:viewAccessKey()">查看密钥</button>
  </td></tr>
  <tr><td><hr></td></tr>
  <tr><td id="list">
  </td></tr>
  <tr><td><hr></td></tr>
</table>
</body>
</html>

<script language="javascript">
  window.onLoad = listAccessKey();
</script>

