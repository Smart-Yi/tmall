<%@ page language="java" import="java.util.* ,com.tmall.model.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="searchProducts">
		<c:forEach  items="${pbeans}" var="p" >
		<div class="productUnit" price=${p.promotePrice}>
			<a href="product?pid=${p.id}">
				<img class="productImage" src="./img/productSingle/${p.firstProductImage.id}.jpg">
			</a>
			<span class="productPrice"><fmt:formatNumber value="${p.promotePrice}" type="currency"/></span>
			<a class="productLink" href="product?pid=${p.id}">
			 ${p.name}
			</a>
			<a class="tmallLink" href="/product?pid=${p.id}">天猫专卖</a>

			<div class="show1 productInfo">
				<span class="monthDeal ">月成交 <span class="productDealNumber">${p.saleCount}</span></span>
				<span class="productReview">评价<span class="productReviewNumber">${reviewCount }</span></span>
				<span class="wangwang"><img src="./img/site/wangwang.png"></span>
			</div>
		</div>
		</c:forEach>
		<c:if test="${empty pbeans}">
		<div class="noMatch">没有满足条件的产品</div>
		</c:if>>
	<div style="clear:both"></div>
</div>