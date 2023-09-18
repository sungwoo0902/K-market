<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="cs">
	<div class="qna">
		<nav>
			<div>
				<p>홈<span>></span>문의하기</p>
			</div>
		</nav>
		<section class="${board}">
			<aside>
				<h2>문의하기</h2>
				<ul>
					<li class="${(3>0)?'on':'off'}"><a href="./list.do">회원</a></li>
					<li class="${(3>0)?'on':'off'}"><a href="./list.do">쿠폰/이벤트</a></li>
					<li class="${(3>0)?'on':'off'}"><a href="./list.do">주문/결제</a></li>
					<li class="${(3>0)?'on':'off'}"><a href="./list.do">배송</a></li>
					<li class="${(3>0)?'on':'off'}"><a href="./list.do">취소/반품/교환</a></li>
					<li class="${(3>0)?'on':'off'}"><a href="./list.do">여행/숙박/항공</a></li>
					<li class="${(3>0)?'on':'off'}"><a href="./list.do">안전거래</a></li>
				</ul>
			</aside>