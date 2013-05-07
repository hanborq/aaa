<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="css/bigdata.css" rel="stylesheet" type="text/css" />
<script language="javascript" src="js/bigdata-aaa.js" ></script>
<script language="javascript" src="js/jquery.js" ></script>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Bigdata</title>
</head>
<body>
<table width="1000" align="center">
  <tr><td><%@ include file="head.jsp" %></td></tr>
  <tr><td><span class="STYLE4">注册新用户</span></td></tr>
  <tr><td><hr></td></tr>
  <tr><td><span class="STYLE2">1 填写注册信息</span> <span class="STYLE3">2 激活用户</span> <span class="STYLE3">3 注册成功</span> </td></tr>
  <tr><td><hr></td></tr>
  <tr><td>
    <table class="STYLE2">
      <tr>
        <td width="300">注册邮箱</td>
        <td width="600"><input id="username" name="username" type="text"></td>
      </tr>
      <tr>
        <td>密码</td>
        <td><input id="password" name="password" type="password"></td>
      </tr>
      <tr>
        <td>确认密码</td>
        <td><input id="password1" type="password"></td>
      </tr>
      <tr>
        <td>用户名</td>
        <td><input id="dispname" name="dispname" type="text"></td>
      </tr>
      <tr>
        <td></td>
        <td><button id="confirm" onClick="javascript:req()">注册</button></td>
      </tr>
    </table>
  </td></tr>
  <tr><td><hr></td></tr>
</table>
</body>
</html>
