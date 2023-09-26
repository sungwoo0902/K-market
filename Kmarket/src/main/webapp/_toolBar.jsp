<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="./css/style.css"/>
<link rel="stylesheet" href="../css/style.css"/>
		<script>
			$(function() {
				$('.up').click(function(e) {
					e.preventDefault();
					const top = document.getElementsByClassName('top')[0];
					top.scrollIntoView({ behavior: 'smooth' });
				});
			});
		</script>
		<article id="toolBar">
        	<ul>
        		<li>최근본상품</li>
        		<c:forEach var="late" items="${latelyProduct}">
        		<li><a href="${ctxPath}/product/view.do?prodNo=${late.prodNo}"><img src="${ctxPath}/thumb/${late.thumb1}"/></a></li>
        		</c:forEach>
        	</ul>
       		<a href="${ctxPath}/product/cart.do"><i class="fas fa-cart-shopping fa-lg"><br><span>카트</span></i></a>
       		<a href="#" class="up"><i class="fas fa-angles-up fa-lg"><br><span>위로</span></i></a>
        </article>