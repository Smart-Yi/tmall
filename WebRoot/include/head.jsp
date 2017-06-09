<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	  <script src="./js/jquery.min.js"></script>
	   <script src="./js/bootstrap.min.js"></script>
	<link href="./css/bootstrap.min.css" rel="stylesheet">
	<link href="./css/style.css" rel="stylesheet">
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
	$("a.productDetailTopReviewLink").click(function(){
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

</script>	
</head>
<body>
<nav class="top ">
		<div class="top_middle">
		<a href="/tmall">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
			天猫首页
		</a>	
		<span>喵，欢迎来天猫</span>
			<c:choose>
				<c:when test="${!empty user}">
					<a href="">${user.username}</a>
					<a href="logout">退出</a>
				</c:when>
				<c:otherwise>
					<a href="login.jsp">请登录</a>
					<a href="register.jsp">注册</a>
				</c:otherwise>
			</c:choose>
			<c:if test="">

			</c:if>
				
		<span class="pull-right">
			<a href="myOrder">我的订单</a>
			<a href="/forecart">
			<span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
			购物车<strong>0</strong>件</a>		
		</span>
			</div>
</nav>
