<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	  <script src="./js/jquery.min.js"></script>
	<link href="./css/bootstrap.min.css" rel="stylesheet">
	 <script src="./js/bootstrap.min.js"></script>
	<link href="./css/style.css" rel="stylesheet">
</head>
<body>

<form method="post" action="registerService" class="registerForm">


<div class="registerDiv">
	<div class="registerErrorMessageDiv">
		<div class="alert alert-danger" role="alert">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
		  	<span class="errorMessage"></span>
		</div>		
	</div>

	
	<table class="registerTable" align="center">
		<tbody><tr>
			<td class="registerTip registerTableLeftTD">设置会员名</td>
			<td></td>
		</tr>
		<tr>
			<td class="registerTableLeftTD">登陆名</td>
			<td class="registerTableRightTD"><input id="name" name="name" placeholder="会员名一旦设置成功，无法修改"> </td>
		</tr>
		<tr>
			<td class="registerTip registerTableLeftTD">设置登陆密码</td>
			<td class="registerTableRightTD">登陆时验证，保护账号信息</td>
		</tr>		
		<tr>
			<td class="registerTableLeftTD">登陆密码</td>
			<td class="registerTableRightTD"><input id="password" name="password" type="password" placeholder="设置你的登陆密码"> </td>
		</tr>
		<tr>
			<td class="registerTableLeftTD">密码确认</td>
			<td class="registerTableRightTD"><input id="repeatpassword" type="password" placeholder="请再次输入你的密码"> </td>
		</tr>
				
		<tr>
			<td colspan="2" class="registerButtonTD">
				<button type="submit">提   交</button></a>
			</td>
		</tr>				
	</tbody></table>
</div>
</form>
</body>
</html>
