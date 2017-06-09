<%@ page language="java" import="java.util.*,com.tmall.dao.* , com.tmall.model.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <div class="homepageCategoryProducts">
  <c:forEach items="${cbeans}" var="c">
	<div class="eachHomepageCategoryProducts">
			<div class="left-mark"></div>
			<span class="categoryTitle">${c.name}</span>
			<div style="clear:both"></div>
				  
					<c:forEach items="${ c.product}" var="p" varStatus="ps">
					<c:if test="${ps.count<=5}">
					<div class="productItem">
						<a href="product?pid=${p.id}"> <img width="100px" src="./img/productSingle_middle/${p.firstProductImage.id}.jpg"></a>
						<a class="productItemDescLink" href="product?pid=${p.id}">
							<span class="productItemDesc">[热销]
							${fn:substring(p.name, 0, 20)}
							</span>
					    </a>
						<span class="productPrice">
							${p.promotePrice}
						</span>
					</div>
					</c:if>
				</c:forEach>
					<div style="clear:both"></div>	
		</div>	
	</c:forEach>	
	<img id="endpng" class="endpng" src="img/site/end.png">
	</div>	
