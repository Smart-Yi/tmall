<%@ page language="java" import="java.util.*,com.tmall.dao.*,com.tmall.model.*" pageEncoding="UTF-8"  isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<script>
function showProductsAsideCategorys(cid){
	$("div.eachCategory[cid="+cid+"]").css("background-color","white");
	$("div.eachCategory[cid="+cid+"] a").css("color","#87CEFA");
	$("div.productsAsideCategorys[cid="+cid+"]").show();
}


function hideProductsAsideCategorys(cid){
	$("div.eachCategory[cid="+cid+"]").css("background-color","#e2e2e3");
	$("div.eachCategory[cid="+cid+"] a").css("color","#000");
	$("div.productsAsideCategorys[cid="+cid+"]").hide();
}
$(function(){
    $("div.eachCategory").mouseenter(function(){
        var cid = $(this).attr("cid");
        showProductsAsideCategorys(cid);
    });
    $("div.eachCategory").mouseleave(function(){
        var cid = $(this).attr("cid");
        hideProductsAsideCategorys(cid);
    });
    $("div.productsAsideCategorys").mouseenter(function(){
    	var cid = $(this).attr("cid");
    	showProductsAsideCategorys(cid);
    });
    $("div.productsAsideCategorys").mouseleave(function(){
    	var cid = $(this).attr("cid");
    	hideProductsAsideCategorys(cid);
    });
	
	$("div.rightMenu span").mouseenter(function(){
		var left = $(this).position().left;
		var top = $(this).position().top;
		var width = $(this).css("width");
		var destLeft = parseInt(left) + parseInt(width)/2;
		$("img#catear").css("left",destLeft);
		$("img#catear").css("top",top-20);
		$("img#catear").fadeIn(500);
				
	});
	$("div.rightMenu span").mouseleave(function(){
		$("img#catear").hide();
	});
	
	var left = $("div#carousel-of-product").offset().left;
	$("div.categoryMenu").css("left",left-20);
	$("div.categoryWithCarousel div.head").css("margin-left",left);
	$("div.productsAsideCategorys").css("left",left-20);
	
	
});
</script>
<img src="img/site/catear.png" id="catear" class="catear" />
<div class="categoryWithCarousel">
<div class="headbar show1">
	<div class="head ">
	
		<span style="margin-left:10px" class="glyphicon glyphicon-th-list"></span>
		<span style="margin-left:10px" >商品分类</span>
		
	</div>
	
	<div class="rightMenu">
		<span><a href=""><img src="img/site/chaoshi.png"/></a></span>
		<span><a href=""><img src="img/site/guoji.png"/></a></span>
		<c:forEach items="${cbeans}" var="cbean" varStatus="vs">
			<c:if test="${vs.count<=4}">
				<span>
					<a href="category?cid=${cbean.id }">
						${cbean.name}
				</a></span>		
			</c:if>
		</c:forEach>		
	</div>
	
</div>
<!-- CategoryMenu -->
<div style="position: relative">
	<div class="categoryMenu">
		<c:forEach items="${cbeans}" var="cbean">
			<div cid=${cbean.id } class="eachCategory" style="background-color: rgb(226, 226, 227);">
				<span class="glyphicon glyphicon-link"></span>
				<a href="category?cid=${cbean.id }" style="color: rgb(0, 0, 0);">
					${cbean.name}
				</a>
			</div>
		</c:forEach>	
</div>  
</div>









<!-- Categorytitle -->
<div style="position: relative;left: 0;top: 0;">
<script>
$(function(){
	$("div.productsAsideCategorys div.row a").each(function(){
		var v = Math.round(Math.random() *6);
		if(v == 1)
			$(this).css("color","#87CEFA");
	});
});
</script>
<c:forEach items="${cbeans}" var="c" >
	<div cid="${c.id }"  class="productsAsideCategorys">
		<c:forEach items="${c.productByRow}" var="pl">
			<div class="row show1">
			<c:forEach items="${pl}" var="p">
				<c:if test="${!empty p.subTitle}">
						<a href="product?pid=${p.id}">
							<c:forEach items="${fn:split(p.subTitle, ' ')}" var="title" varStatus="st">
								
								<c:if test="${st.index==0}">
									${title}
								</c:if>
							</c:forEach>
						</a>
				</c:if>
			</c:forEach>
				<div class="seperator"></div>
			</div>	
		</c:forEach>	
	</div>	
</c:forEach>>
</div>
<%@include file="lunbo.jsp" %>

<div class="carouselBackgroundDiv">
</div>

</div>














