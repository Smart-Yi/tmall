<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<title>模仿天猫官网-${cbean.name}</title>	
<div id="category">
	<div class="categoryPageDiv">
		<img src="img/category/${cbean.id}.jpg">
		<%@include file="sortBar.jsp"%>
		<%@include file="productsByCategory.jsp"%>
	</div>
</div>