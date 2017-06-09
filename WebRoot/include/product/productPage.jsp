<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<title>模仿天猫官网 ${pbean.name }</title>
<div class="categoryPictureInProductPageDiv">
	<img class="categoryPictureInProductPage" src="img/category/${pbean.category.id }.jpg">
</div>
<div class="productPageDiv">
	<%@include file="imgAndInfo.jsp" %>
	
	<%@include file="productReview.jsp" %>
	
	<%@include file="productDetail.jsp" %>
</div>