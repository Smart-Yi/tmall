<%@ page language="java"  pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	 <script src="./js/jquery.min.js"></script>
	<script src="./js/bootstrap.min.js"></script>
	<link href="./css/style.css" rel="stylesheet">
	<link href="./css/bootstrap.min.css" rel="stylesheet">
<script>
function formatMoney(num){
	num = num.toString().replace(/\$|\,/g,'');  
	if(isNaN(num))  
	    num = "0";  
	sign = (num == (num = Math.abs(num)));  
	num = Math.floor(num*100+0.50000000001);  
	cents = num%100;  
	num = Math.floor(num/100).toString();  
	if(cents<10)  
	cents = "0" + cents;  
	for (var i = 0; i < Math.floor((num.length-(1+i))/3); i++)  
	num = num.substring(0,num.length-(4*i+3))+','+  
	num.substring(num.length-(4*i+3));  
	return (((sign)?'':'-') + num + '.' + cents);  
}
function checkEmpty(id, name){
	var value = $("#"+id).val();
	if(value.length==0){
		
		$("#"+id)[0].focus();
		return false;
	}
	return true;
}
$(function(){
	$("#login").click(function(){
		var page = "loginService" ;
		var value = "name=" + $("#name").val() + "&password=" + $("#password").val() ;
		$.ajax({
		url:page,
		data:value,
		type:"post",
		success:function(result){
				if(result == "true"){
						 window.location.href = "/tmall";//跳转到主页
					}else{
						$("div.loginErrorMessageDiv").show();
						$("span.errorMessage").html(result);
					}
				}
		})	
	});
});

$(function(){
	
/*	$("a.productDetailTopReviewLink").click(function(){
		$("div.productReviewDiv").show();
		$("div.productDetailDiv").hide();
	});
	$("a.productReviewTopPartSelectedLink").click(function(){
		$("div.productReviewDiv").hide();
		$("div.productDetailDiv").show();		
	});
	
	$("span.leaveMessageTextareaSpan").hide();
	$("img.leaveMessageImg").click(function(){
		
		$(this).hide();
		$("span.leaveMessageTextareaSpan").show();
		$("div.orderItemSumDiv").css("height","100px");
	});
*/	
	$("div#footer a[href$=#nowhere]").click(function(){
		alert("模仿天猫的连接，并没有跳转到实际的页面");
	});
	

	$("a.wangwanglink").click(function(){
		alert("模仿旺旺的图标，并不会打开旺旺");
	});
	$("a.notImplementLink").click(function(){
		alert("这个功能没做，蛤蛤~");
	});
	

});
$(function(){
	$("#name").keyup(function(){
		$("div.loginErrorMessageDiv").hide();	
	});
})
</script>	
<script>

</script>
</head>

<body>








<div id="loginDiv">

	<div class="simpleLogo">
		<img src="./img/simpleLogo.png">
	</div>

	
	<img id="loginBackgroundImg" class="loginBackgroundImg" src="./img/loginBackground.png">
	
		<div id="loginSmallDiv" class="loginSmallDiv">
			<div class="loginErrorMessageDiv">
				<div class="alert alert-danger">
				  <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
				  	<span  id = "sh" class="errorMessage"></span>
				 
				</div>
			</div>
			<div></div>	
			<div class="login_acount_text">账户?登录</div>
			<div class="loginInput ">
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-user"></span>
				</span>
				<input id="name" name="name" placeholder="手机/会员名/邮箱" type="text">			
			</div>
			
			<div class="loginInput ">
				<span class="loginInputIcon ">
					<span class=" glyphicon glyphicon-lock"></span>
				</span>
				<input id="password" name="password" type="password" placeholder="密码">
			</div>
			<span class="text-danger">不要输入真实的天猫账号密码</span><br><br>
			<div>
				<a class="notImplementLink" href="http://how2j.cn/tmall/login.jsp#nowhere">忘记登录密码</a> 
				<a href="register.jsp" class="pull-right">免费注册</a> 
			</div>
			<div style="margin-top:20px">
				<button id = "login" class="btn btn-block redButton" type="submit">登录</button>
			</div>
		</div>	


</div>	
	</body></html>