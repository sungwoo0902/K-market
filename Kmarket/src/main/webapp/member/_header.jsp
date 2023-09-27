<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>케이마켓::대한민국 1등 온라인 쇼핑몰</title>
    <link rel="stylesheet" href="./css/style.css"/>
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <link rel="icon" href="${ctxPath}/images/favicon.ico" type="image/x-icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script>
		$(function() {
			// 구현되지 않은 서비스 및 2차 개발 예정인 서비스 알림
			$('.unready').click(function() {
				alert('해당 서비스는 구현중에 있습니다.\n불편을 끼쳐드려 죄송합니다.');
			});
		});
	</script>
</head>
<body>
    <div id="wrapper">
        <header>
            <div class="top">
                <div>
					<c:choose>
					<c:when test="${sessUser eq null}">
                    <a href="${ctxPath}/member/login.do">로그인</a>
                    <a href="${ctxPath}/member/join.do">회원가입</a>
                    </c:when>
                    <c:otherwise>
                    <a href="${ctxPath}/member/logout.do">로그아웃</a>
                    <a href="#" class="unready">마이페이지</a>
                    </c:otherwise>
                    </c:choose>
                    <!-- 
                    <a href="#"><i class="fa fa-shopping-cart" aria-hidden="true">
                     -->
                </div>
            </div>
            <div class="logo">
                <div>
                    <a href="${ctxPath}/index.do">
                        <img src="../images/header_logo.png" alt="로고">
                    </a>
                </div>
            </div>
        </header>