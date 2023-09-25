<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        		<li><a href="#"><img src="./thumb/10/10/0c903d75-0be6-4a6a-9067-665f2aacc97a.jpg"/></a></li>
        	</ul>
       		<a href="${ctxPath}/product/cart.do"><i class="fas fa-cart-shopping fa-lg"><br><span>카트</span></i></a>
       		<a href="#" class="up"><i class="fas fa-angles-up fa-lg"><br><span>위로</span></i></a>
        </article>