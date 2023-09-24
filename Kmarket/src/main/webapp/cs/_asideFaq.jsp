<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="cs">
	<div class="faq">
		<nav>
			<div>
				<p>
					홈
					<span>></span>
					자주묻는 질문
					<span>></span>
					<strong>${cate.cate1_name}</strong>
				</p>
			</div>
		</nav>
		<section class="${board}">
			<aside>
				<h2>자주묻는 질문</h2>
				<ul>
					<li class="${(cate1 eq 1)?'on':'off'}"><a href="./list.do?cate1=1">회원</a></li>
					<li class="${(cate1 eq 2)?'on':'off'}"><a href="./list.do?cate1=2">쿠폰/이벤트</a></li>
					<li class="${(cate1 eq 3)?'on':'off'}"><a href="./list.do?cate1=3">주문/결제</a></li>
					<li class="${(cate1 eq 4)?'on':'off'}"><a href="./list.do?cate1=4">배송</a></li>
					<li class="${(cate1 eq 5)?'on':'off'}"><a href="./list.do?cate1=5">취소/반품/교환</a></li>
					<li class="${(cate1 eq 6)?'on':'off'}"><a href="./list.do?cate1=6">여행/숙박/항공</a></li>
					<li class="${(cate1 eq 7)?'on':'off'}"><a href="./list.do?cate1=7">안전거래</a></li>
				</ul>
	  		</aside>