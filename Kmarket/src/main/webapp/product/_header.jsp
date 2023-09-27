<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>케이마켓::대한민국 1등 온라인 쇼핑몰</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="./css/style.css"/>
    <link rel="icon" href="${ctxPath}/images/favicon.ico" type="image/x-icon">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
		$(function() {
			// 구현되지 않은 서비스 및 2차 개발 예정인 서비스 알림
			$('.unready').click(function() {
				alert('아직 준비 중인 서비스입니다.\n불편을 끼쳐드려 죄송합니다.');
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
                    <span>
                    	<span style="color: #2eb275; font-weight: bold;">${sessUser.name}</span>님 반갑습니다.
                   	</span>
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
                </div>
            </div>
            <div class="logo">
                <div>
                    <a href="${ctxPath}/index.do">
                        <img src="${ctxPath}/images/header_logo.png" alt="로고">
                    </a>
                    <form action="#">
                        <input type="text" name="keyword">
                        <button>
                            <i class="fa fa-search" aria-hidden="true">

                            </i>
                        </button>
                    </form>
                </div>
            </div>
            <div class="menu">
                <div>
                    <ul>
                        <li><a href="${ctxPath}/product/list.do?type=7">히트상품</a></li>
	                    <li><a href="${ctxPath}/product/list.do?type=4">추천상품</a></li>
	                    <li><a href="${ctxPath}/product/list.do?type=6">최신상품</a></li>
	                    <li><a href="#" class="unready">인기상품</a></li>
	                    <li><a href="${ctxPath}/product/list.do?type=8">할인상품</a></li>
                    </ul>
                    <ul>
                        <li>
                            <a href="#" class="unready">쿠폰존</a>
                        </li>
                        <li>
                            <a href="#" class="unready">사용후기</a>
                        </li>
                        <li>
                            <a href="#" class="unready">개인결제</a>
                        </li>
                        <li>
                            <a href="${ctxPath}/cs">고객센터</a>
                        </li>
                        <li>
                            <a href="${ctxPath}/cs/faq/list.do?cate1=1">FAQ</a>
                        </li>
                    </ul>
                </div>
            </div>
        </header>