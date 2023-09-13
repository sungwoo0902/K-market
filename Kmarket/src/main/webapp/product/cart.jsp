<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>케이마켓::대한민국 1등 온라인 쇼핑몰</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://kit.fontawesome.com/20962f3e4b.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css">
    <link rel="stylesheet" href="../css/style.css"/>
    <link rel="stylesheet" href="./css/style.css"/>
</head>
<body>
    <div id="wrapper">
        <header>
            <div class="top">
                <div>
                    <a href="./member/login.html">로그인</a>
                    <a href="#">회원가입</a>
                    <a href="#">마이페이지</a>
                    <a href="#"><i class="fa fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니</a>
                </div>
            </div>
            <div class="logo">
                <div>
                    <a href="../index.html">
                        <img src="../images/header_logo.png" alt="로고">
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
                        <li>
                            <a href="#">히트상품</a>
                        </li>
                        <li>
                            <a href="#">추천상품</a>
                        </li>
                        <li>
                            <a href="#">최신상품</a>
                        </li>
                        <li>
                            <a href="#">인기상품</a>
                        </li>
                        <li>
                            <a href="#">할인상품</a>
                        </li>
                    </ul>
                    <ul>
                        <li>
                            <a href="#">쿠폰존</a>
                        </li>
                        <li>
                            <a href="#">사용후기</a>
                        </li>
                        <li>
                            <a href="#">개인결제</a>
                        </li>
                        <li>
                            <a href="#">고객센터</a>
                        </li>
                        <li>
                            <a href="#">FAQ</a>
                        </li>
                    </ul>
                </div>
            </div>
        </header>
        <main id="product">
            <!-- 공통 aside 시작 -->
            <aside>
                <ul class="category">
                    <li>
                        <i class="fa fa-bars" aria-hidden="true">
                        </i>
                        카테고리
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-tshirt" aria-hidden="true">
                            </i>
                            패션·의류·뷰티
                        </a>
                        <ol>
                            <li>
                                <a href="#">남성의류</a>
                            </li>
                            <li>
                                <a href="#">여성의류</a>
                            </li>
                            <li>
                                <a href="#">잡화</a>
                            </li>
                            <li>
                                <a href="#">뷰티</a>
                            </li>
                        </ol>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-laptop" aria-hidden="true">
                            </i>
                            가전·디지털
                        </a>
                        <ol>
                            <li>
                                <a href="#">노트북/PC</a>
                            </li>
                            <li>
                                <a href="#">가전</a>
                            </li>
                            <li>
                                <a href="#">휴대폰</a>
                            </li>
                            <li>
                                <a href="#">기타</a>
                            </li>
                        </ol>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-utensils" aria-hidden="true">
                            </i>
                            식품·생필품
                        </a>
                        <ol>
                            <li>
                                <a href="#">신선식품</a>
                            </li>
                            <li>
                                <a href="#">가공식품</a>
                            </li>
                            <li>
                                <a href="#">건강식품</a>
                            </li>
                            <li>
                                <a href="#">생필품</a>
                            </li>
                        </ol>
                    </li>
                    <li>
                        <a href="#">
                            <i class="fas fa-home" aria-hidden="true">
                            </i>
                            홈·문구·취미
                        </a>
                        <ol>
                            <li>
                                <a href="#">가구/DIY</a>
                            </li>
                            <li>
                                <a href="#">침구·커튼</a>
                            </li>
                            <li>
                                <a href="#">생활용품</a>
                            </li>
                            <li>
                                <a href="#">사무용품</a>
                            </li>
                        </ol>
                    </li>
                </ul>
            </aside>
            <!-- 공통 aside 끝 -->
            <!-- 장바구니 페이지 시작 -->
            <section class="cart">
                <!-- 제목, 페이지 네비게이션 -->
                <nav>
                    <h1>장바구니</h1>
                    <p>
                        HOME > 
                        <span>패션·의류·뷰티</span>
                         > 
                         <strong>장바구니</strong>
                    </p>
                </nav>
                <form action="#">
                    <!-- 장바구니 목록 -->
                    <table>
                        <thead>
                            <tr>
                                <th>
                                    <input type="checkbox" name="all">
                                </th>
                                <th>상품명</th>
                                <th>총수량</th>
                                <th>판매가</th>
                                <th>할인</th>
                                <th>포인트</th>
                                <th>배송비</th>
                                <th>소계</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="empty">
                                <td colspan="7">장바구니에 상품이 없습니다.</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name></td>
                                <td>
                                    <article>
                                        <a href="#">
                                            <img src="./images/80x80.png" alt="">
                                        </a>
                                        <div>
                                            <h2>
                                                <a href="#">상품명</a>
                                            </h2>
                                            <p>상품설명</p>
                                        </div>
                                    </article>
                                </td>
                                <td>1</td>
                                <td>27000</td>
                                <td>5%</td>
                                <td>270</td>
                                <td>무료배송</td>
                                <td>27000</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name></td>
                                <td>
                                    <article>
                                        <a href="#">
                                            <img src="./images/80x80.png" alt="">
                                        </a>
                                        <div>
                                            <h2>
                                                <a href="#">상품명</a>
                                            </h2>
                                            <p>상품설명</p>
                                        </div>
                                    </article>
                                </td>
                                <td>1</td>
                                <td>27000</td>
                                <td>5%</td>
                                <td>270</td>
                                <td>무료배송</td>
                                <td>27000</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox" name></td>
                                <td>
                                    <article>
                                        <a href="#">
                                            <img src="./images/80x80.png" alt="">
                                        </a>
                                        <div>
                                            <h2>
                                                <a href="#">상품명</a>
                                            </h2>
                                            <p>상품설명</p>
                                        </div>
                                    </article>
                                </td>
                                <td>1</td>
                                <td>27000</td>
                                <td>5%</td>
                                <td>270</td>
                                <td>무료배송</td>
                                <td>27000</td>
                            </tr>
                        </tbody>
                    </table>
                    <input type="button" name="del" value="선택삭제">
                    <!-- 장바구니 전체합계 -->
                    <div class="total">
                        <h2>전체합계</h2>
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td>상품수</td>
                                    <td>1</td>
                                </tr>
                                <tr>
                                    <td>상품금액</td>
                                    <td>27000</td>
                                </tr>
                                <tr>
                                    <td>할인금액</td>
                                    <td>-1000</td>
                                </tr>
                                <tr>
                                    <td>배송비</td>
                                    <td>0</td>
                                </tr>
                                <tr>
                                    <td>포인트</td>
                                    <td>260</td>
                                </tr>
                                <tr>
                                    <td>전체주문금액</td>
                                    <td>26000</td>
                                </tr>
                            </tbody>
                        </table>
                        <input type="submit" name value="주문하기">
                    </div>
                </form>
            </section>
            <!-- 장바구니 페이지 끝 -->
        </main>
<%@ include file="../_footer.jsp" %>