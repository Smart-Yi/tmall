<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="boughtDiv">
	<div class="orderType">
		<div class="selectedOrderType"><a orderStatus="all" href="#nowhere">所有订单</a></div>
		<div><a  orderStatus="waitPay" href="#nowhere">待付款</a></div>
		<div><a  orderStatus="waitDelivery" href="#nowhere">待发货</a></div>
		<div><a  orderStatus="waitConfirm" href="#nowhere">待收货</a></div>
		<div><a  orderStatus="waitReview" href="#nowhere" class="noRightborder">待评价</a></div>
		<div class="orderTypeLastOne"><a class="noRightborder">&nbsp;</a></div>
	</div>
	<div style="clear:both"></div>
	<div class="orderListTitle">
		<table class="orderListTitleTable">
			<tr>
				<td>宝贝 </td>
				<td width="100px">单价</td>
				<td width="100px">数量</td>
				<td width="120px">实付款</td>
				<td width="100px">交易操作</td>
			</tr>
		</table>
	
	</div>
	
	<div class="orderListItem">
	
		<c:forEach items="${obeans}" var="obean" >
		
			<table class="orderListItemTable" orderStatus="${obean.status }" oid="${obean.id }">
				<tr class="orderListItemFirstTR">
					<td colspan="2">
					<b>${obean.createDate}</b> 
					<span>订单号: ${obean.orderCode } ${obean.status }
					</span>
					</td>
					<td  colspan="2"><img width="13px" src="img/site/orderItemTmall.png">天猫商场</td>
					<td colspan="1">
						<a class="wangwanglink" href="#nowhere">
							<div class="orderItemWangWangGif"></div>
						</a>
						
					</td>
					<td class="orderItemDeleteTD">
						<a class="deleteOrderLink" oid="${obean.id }" href="#nowhere">
							<span  class="orderListItemDelete glyphicon glyphicon-trash"></span>
						</a>
					</td>
				</tr>
				
				
				<c:forEach items="${ obean.orderItems}" var="oibean" varStatus="st">
				
					<tr class="orderItemProductInfoPartTR" >
						<td class="orderItemProductInfoPartTD"><img width="80" height="80" src="img/productSingle_middle/${oibean.product.firstProductImage.id }.jpg"></td>
						<td class="orderItemProductInfoPartTD">
							<div class="orderListItemProductLinkOutDiv">
								<a href="product?pid=${oibean.product.id }">${oibean.product.name }</a>
								<div class="orderListItemProductLinkInnerDiv">
											<img src="img/site/creditcard.png" title="支持信用卡支付">
											<img src="img/site/7day.png" title="消费者保障服务,承诺7天退货">
											<img src="img/site/promise.png" title="消费者保障服务,承诺如实描述">						
								</div>
							</div>
						</td>
						<td  class="orderItemProductInfoPartTD" width="100px">
						
							<div class="orderListItemProductOriginalPrice">￥<fmt:formatNumber type="number" value="${oibean.product.orignalPrice}" minFractionDigits="2"/></div>
							<div class="orderListItemProductPrice">￥<fmt:formatNumber type="number" value="${oibean.product.promotePrice}" minFractionDigits="2"/></div>
								
		
						</td>
					<c:if test="${st.count==1 && obean.status !='waitReview'}">
						 
							<td valign="top" rowspan="${fn:length(obean.orderItems)}" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
								<span class="orderListItemNumber">${obean.totalNumber}</span>
							</td>
							<td valign="top" rowspan="${fn:length(obean.orderItems)}" width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
								<div class="orderListItemProductRealPrice">￥<fmt:formatNumber  minFractionDigits="2"  maxFractionDigits="2" type="number" value="${obean.total}"/></div>
								<div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
							</td>
							<td valign="top" rowspan="${fn:length(obean.orderItems)}" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
								<c:if test="${obean.status=='waitConfirm' }">
									<a href="confirmPay?oid=${obean.id}">
										<button class="orderListItemConfirm">确认收货</button>
									</a>
								</c:if>
								
								<c:if test="${obean.status=='waitPay' }">
									<a href="alipay?orderid=${obean.id }">
										<button class="orderListItemConfirm" link="/alipay?orderid=${obean.id }">付款</button>
									</a>								
								</c:if>	
								
								<c:if test="${obean.status=='waitDelivery' }">
									<span>待发货</span>
									<a href="delivery?id=${obean.id}">
									<button class="btn btn-info btn-sm ask2delivery" link="delivery?id=${obean.id}">催卖家发货</button> 
									</a>
								</c:if>
								
							</td>						
						</c:if>
						<c:if test="${obean.status=='waitReview' }">
							<td valign="top" rowspan="${fn:length(obean.orderItems)}" class="orderListItemNumberTD orderItemOrderInfoPartTD" width="100px">
								<span class="orderListItemNumber">${obean.totalNumber}</span>
							</td>
							<td valign="top" rowspan="${fn:length(obean.orderItems)}" width="120px" class="orderListItemProductRealPriceTD orderItemOrderInfoPartTD">
								<div class="orderListItemProductRealPrice">￥<fmt:formatNumber  minFractionDigits="2"  maxFractionDigits="2" type="number" value="${obean.total}"/></div>
								<div class="orderListItemPriceWithTransport">(含运费：￥0.00)</div>
							</td>
							
							<td valign="top" rowspan="${fn:length(obean.orderItems)}" class="orderListItemButtonTD orderItemOrderInfoPartTD" width="100px">
									<a href="review?oid=${obean.id}&pid=${oibean.product.id}">
										<button  class="orderListItemReview">评价</button>
									</a>
							</td>		
							
							
						</c:if>			
					</tr>
				</c:forEach>
			</table>
	</c:forEach>
	
	</div>
	
</div>
									