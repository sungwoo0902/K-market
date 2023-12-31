<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>케이마켓 고객센터</title>
		<link rel="stylesheet" href="${ctxPath}/cs/css/style.css">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
		<link rel="icon" href="${ctxPath}/images/favicon.ico" type="image/x-icon">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
		<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
		<script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
		<script>
			$(function() {
				if(${success eq '102'}) {
					alert('본인 게시글만 수정 가능합니다.');
				}
			});
		</script>
	</head>
	<body>
		<div id="wrapper">
			<header>
				<div class="top">
					<div>
						<p>
							<c:if test="${sessUser != null}">
								<span>
			                    	<span style="color: #2eb275; font-weight: bold;">${sessUser.name}</span>님 반갑습니다.
			                   	</span>
							</c:if>
							<a href="${ctxPath}/index.do" style="margin-left: 8px;">홈</a>
							<c:choose>
				                <c:when test="${sessUser eq null}">
					                <a href="${ctxPath}/member/login.do">로그인</a>
					                <a href="${ctxPath}/member/join.do">회원가입</a>
				                </c:when>
				                <c:otherwise>
					                <c:if test="${sessUser.type eq 2 or sessUser.type eq 3}">
					                	<a href="${ctxPath}/admin/index.do">관리자</a>
					                </c:if>
				                		<a href="${ctxPath}/member/logout.do">로그아웃</a>
					                <c:if test="${sessUser.type eq 1}">
						                <a href="#" class="unready">마이페이지</a>
						                <a href="${ctxPath}/product/cart.do"><i class="fa fa-shopping-cart" aria-hidden="true">
						                </i>&nbsp;장바구니</a>
					                </c:if>
				                </c:otherwise>
			                </c:choose>
						</p>
					</div>
					</div>
					<div class="logo">
						<div>
							<a href="${ctxPath}/cs"><img src="${ctxPath}/cs/images/logo.png" alt="로고" />고객센터</a>
						</div>
					</div>
				</header>