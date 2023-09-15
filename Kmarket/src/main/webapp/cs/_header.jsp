<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 개발/배포에서 ContextPath 포함 여부에 따른 동적처리 -->
<c:set var="ctxPath" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>케이마켓 고객센터</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" href="./css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>    
    <script src="../js/gnb.js"></script>
    <script>
	$(document).ready(function(){
		$(".theme-link").click(function(){
			// 현재 클릭한 링크를 강조하기 위해 "on" 클래스를 추가한다.
			$(".theme-link").removeClass("on");
			$(this).addClass("on");
		});
	});
	</script>
    
  </head>
  <body>
    <div id="wrapper">
      <header>
        <div class="top">
          <div>
            <p>
              <a href="../member/login.jsp">로그인</a>
              <a href="../member/register.jsp">회원가입</a>
              <a href="#">마이페이지</a>
              <a href="#"
                ><i class="fa fa-shopping-cart" aria-hidden="true"></i
                >&nbsp;장바구니</a
              >
            </p>
          </div>
        </div>
        <div class="logo">
          <div>
            <a href="#"><img src="./images/logo.png" alt="로고" />고객센터</a>
          </div>
        </div>
      </header>