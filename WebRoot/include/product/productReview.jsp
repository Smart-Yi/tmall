<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="productReviewDiv" >
	<div class="productReviewTopPart">
		<a  href="#nowhere" class="productReviewTopPartSelectedLink">商品详情</a>
		<a  href="#nowhere" class="selected">累计评价 <span class="productReviewTopReviewLinkNumber">${reviewTotal}</span> </a>
	</div>
	
	<div class="productReviewContentPart">
		<c:forEach items="${rbeans}" var="r">
		<div class="productReviewItem">
		
			<div class="productReviewItemDesc">
				<div class="productReviewItemContent">
					${r.content}
				</div>
				<div class="productReviewItemDate">${r.createDate }</div>
			</div>
			<div class="productReviewItemUserInfo">
			
				${r.user.anonymousName}<span class="userInfoGrayPart">（匿名）</span>
			</div>
			
			<div style="clear:both"></div>
		
		</div>
		</c:forEach>
	</div>
</div>
