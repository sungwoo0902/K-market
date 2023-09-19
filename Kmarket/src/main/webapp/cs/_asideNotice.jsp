<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<section id="cs">
	<div class="notice">
		<nav>
			<div>
				<p>홈<span>></span>공지사항</p>
			</div>
		</nav>
		<section class="${board}">
			<aside>
				<h2>공지사항</h2>
				<ul>
					<li class="${(cate1 eq 1)?'on':'off'}"><a href="./list.do">전체</a></li>
					<li class="${(cate1 eq 2)?'on':'off'}"><a href="./list.do">고객서비스</a></li>
					<li class="${(cate1 eq 3)?'on':'off'}"><a href="./list.do">안전거래</a></li>
					<li class="${(cate1 eq 4)?'on':'off'}"><a href="./list.do">위해상품</a></li>
					<li class="${(cate1 eq 5)?'on':'off'}"><a href="./list.do">이벤트당첨</a></li>
				</ul>
			</aside>