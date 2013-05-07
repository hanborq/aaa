<%@ page session="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="org.springframework.util.StringUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<link href="css/bigdata.css" rel="stylesheet" type="text/css" />
<script language="javascript">
function openReg() {
  window.location.href="https://"+"<%=request.getServerName()%>"+":"+"<%=request.getServerPort()%>"+"/reg.jsp";
}
</script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome to Bigdata</title>
</head>
<body>
<table width="1000" align="center">
  <tr><td><%@ include file="head.jsp" %></td></tr>
  <tr><td><span class="STYLE4">用户登录</span></td></tr>
  <tr><td><hr></td></tr>
  <tr><td>
		<form method="post"
			action="<%=response.encodeRedirectURL("login"
                    + (StringUtils.hasText(request.getQueryString()) ? "?"
                            + request.getQueryString() : ""))%>">
		<table class="STYLE2">
			<tr>
				<td width="300">注册邮箱</td>
				<td width="600"><input id="username" name="username" tabindex="1" accesskey="n"></input></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input type="password" id="password" name="password" tabindex="2" accesskey="p" /></td>
			</tr>
			<tr>
				<td>
          <input type="hidden" name="lt" value="${flowExecutionKey}" />
          <input type="hidden" name="_eventId" value="submit" />
				</td>
				<td>
          <input type="submit" accesskey="l" value="登录" tabindex="4" />
          <input type="button" value="注册新用户" onClick="javascript:openReg()">
				</td>
			</tr>
		</table>
		</form>
		</td></tr>
  <tr><td><hr></td></tr>
</table>
</body>
</html>
