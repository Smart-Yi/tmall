<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="categoryProducts">
		<c:forEach items="${pbeans}" var="p">
		<div class="productUnit" price="${p.promotePrice}">
			<div class="productUnitFrame">
				<a href="product?pid=${p.id }">
					<img class="productImage" src="img/productSingle_middle/${p.firstProductImage.id}.jpg">
				</a>
				<span class="productPrice"><fmt:formatNumber type="number" value="${p.promotePrice}" minFractionDigits="2"/></span>
				<a class="productLink" href="product?pid=${p.id }">
				  ${fn:substring(p.name, 0, 50)}
				</a>
				
				<a  class="tmallLink" href="foreproduct?pid=265">天猫专卖</a>
	
				<div class="show1 productInfo">
					<span class="monthDeal ">月成交 <span class="productDealNumber">58笔</span></span>
					<span class="productReview">评价<span class="productReviewNumber">${p.reviewCount}</span></span>
					<span class="wangwang">
					<a class="wangwanglink" href="#nowhere">
						<img src="img/site/wangwang.png">
					</a>
					
					</span>
				</div>
			</div>
		</div>
		</c:forEach>
		
		<div style="clear:both"></div>
</div>
		