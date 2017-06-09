<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="productDetailDiv" >
	<div class="productDetailTopPart">
		<a href="#nowhere" class="productDetailTopPartSelectedLink selected">商品详情</a>
		<a href="#nowhere" class="productDetailTopReviewLink">累计评价 <span class="productDetailTopReviewLinkNumber">${reviewTotal}</span> </a>
	</div>
	
	
	<div class="productParamterPart">
		<div class="productParamter">产品参数：</div>
		
		<div class="productParamterList">
			<c:forEach items="${pvbeans}" var="pv">
				<span>${pv.property.name}:  ${fn:substring(pv.value, 0, 12)} </span>
			</c:forEach>
				
			
		</div>
		<div style="clear:both"></div>
	</div>
	
	<div class="productDetailImagesPart">
			<c:forEach items="${pbean.productDetailImage}" var="dimg">
				<img src="img/productDetail/${dimg.id }.jpg">
			</c:forEach>
			
	</div>
</div>
