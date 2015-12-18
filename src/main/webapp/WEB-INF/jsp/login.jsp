<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/WEB-INF/jsp/base/baselib.jspf" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SSO单点登录系统</title>

</head>
<body>
	<div style="min-width:250px;min-height:150px;" id="login_panel"></div>
</body>
<script type="text/javascript" src="${ctx}/resources/js/login/login.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/login/loginItem.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/login/loginLabel.js"></script>
</html>