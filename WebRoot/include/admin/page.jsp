<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<nav>
		<ul class="pagination">
			<li>
				<a href="?page.start=0"> 首页</a>
			</li>
			
			<li>
				<c:if test="${page.start!=0}">
				<a href="?page.start=${page.start - page.count}"> 上一页</a>
				</c:if>
				<c:if test="${page.start==0}">
				<a>上一页</a>
				</c:if>
			</li>
			
			<li>
				<c:if test="${page.start + page.count < page.totalPage}">
				<a href="?page.start=${page.start + page.count}"> 下一页</a>
				</c:if>
				<c:if test="${page.start + page.count >= page.totalPage}">
				<a> 下一页</a>
				</c:if>
			</li>
			<li>
				<a href="?page.start=${page.totalPage - page.count}"> 尾页</a>
			</li>
		</ul>
	</nav>
