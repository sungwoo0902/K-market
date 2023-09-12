<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>케이마켓::대한민국 1등 온라인 쇼핑몰</title>
    <link rel="stylesheet" href="./css/style.css"/>
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css"/>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
</head>
<body>
    <div id="wrapper">
        <header>
            <div class="top">
                <div>
                    <a href="./member/login.html">로그인</a>
                    <a href="#">회원가입</a>
                    <a href="#">마이페이지</a>
                    <a href="#"><i class="fa fa-shopping-cart" aria-hidden="true">
                        ::before
                    </i>&nbsp;장바구니</a>
                </div>
            </div>
            <div class="logo">
                <div>
                    <a href="../index.html">
                        <img src="../images/header_logo.png" alt="로고">
                    </a>
                </div>
            </div>
        </header>

        <main id="member">
            <div class="join">
                <img src="./images/member_join_tit_welcome.gif" alt="케이마켓에 오신것을 환영합니다.">
                <div class="type">

                    <div class="normal">
                        <img src="./images/member_join_tit_normal.gif" alt="구매회원 가입">
                        <div class="txt">
                            <img src="./images/member_join_txt_normal.gif" alt="개인 구매회원">
                            <a href="./signup.do?type=normal">
                                <img src="./images/member_join_btn1.gif" alt="회원가입">
                            </a>
                        </div>
                    </div>

                    <div class="seller">
                        <img src="./images/member_join_tit_seller.gif" alt="판매회원 가입">
                        <div class="txt">
                            <img src="./images/member_join_txt_seller.gif" alt="개인 구매회원">
                            <a href="./signup.do?type=seller">
                                <img src="./images/member_join_btn2.gif" alt="회원가입">
                            </a>
                        </div>
                    </div>
                </div>
                <img src="./images/member_join_banner1.jpg" alt="신규회원을 위한 기분 좋은 혜택">
                <img src="./images/member_join_banner2.jpg" alt="사업자회원을 위한 기분 좋은 혜택">
            </div>
        </main>

        <footer>
            <ul>
                <li><a href="#">회사소개</a></li>
                <li><a href="#">서비스이용약관</a></li>
                <li><a href="#">개인정보처리방침</a></li>
                <li><a href="#">전자금융거래약관</a></li>
            </ul>
            <div>
                <p>
                    <img src="../images/footer_logo.png" alt="로고">
                </p>
                <p>
                    <strong>(주)KMARKET</strong>
                    <br>
                    부산시 강남구 테헤란로 152 (역삼동 강남파이낸스센터)
                    <br>
                    대표이사 : 홍길동
                    <br>
                    사업자등록번호 : 220-81-83676 사업자정보확인
                    <br>
                    통신판매업신고 : 강남 10630호 Fax : 02-589-8842 
                </p>
                <p>
                    <strong>고객센터</strong>
                    <br>
                    Tel : 1234-5678 (평일 09:00~18:00)
                    <br>
                    스마일클럽/SVIP 전용 : 1522-5700 (365일 09:00~18:00)
                    <br>
                    경기도
                    <br>
                    Fax : 051-123-4567 | Mail : kmarket@kmarket.co.kr
                    <br>
                </p>
            </div>
        </footer>
    </div>
</body>
</html>