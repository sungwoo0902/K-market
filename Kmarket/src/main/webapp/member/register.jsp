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
                    <a href="#"><i class="fas fa-shopping-cart" aria-hidden="true"></i>&nbsp;장바구니</a>
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
            <div class="register">
                <nav><h1>일반 회원가입</h1></nav>
                <form action="#" method="POST">
                    <section>
                        <table>
                            <caption>필수 정보입력</caption>
                            <tr>
                                <th><span class="essential">*</span>아이디</th>
                                <td>
                                    <input type="text" name="km_uid" placeholder="아이디를 입력" required>
                                    <span class="msgId">영문, 숫자로 4~12자까지 설정해 주세요.</span>
                                </td>
                            </tr>

                            <tr>
                                <th><span class="essential">*</span>비밀번호</th>
                                <td>
                                    <input type="password" name="km_pass" placeholder="비밀번호를 입력" required>
                                    <span class="msgPass">영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.</span>
                                </td>
                            </tr>

                            <tr>
                                <th><span class="essential">*</span>비밀번호확인</th>
                                <td>
                                    <input type="password" name="km_pass" placeholder="비밀번호를 확인" required>
                                    <span class="msgPass">비밀번호 재입력</span>
                                </td>
                            </tr>
                        </table>
                    </section>
                    <section>
                        <table>
                            <caption>기본 정보입력</caption>
                            <tr>
                                <th><span class="essential">*</span>이름</th>
                                <td><input type="text" name="km_name" placeholder="이름을 입력" required></td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>성별</th>
                                <td>
                                    <label><input type="radio" name="km_gender" value="1" checked>&nbsp;남</label>
                                    <label><input type="radio" name="km_gender" value="2">&nbsp;여</label>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>EMAIL</th>
                                <td><input type="email" name="km_email" placeholder="이메일 입력" required></td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>휴대폰</th>
                                <td>
                                    <input type="text" name="km_hp" maxlength="13" placeholder="휴대폰번호 입력" required>
                                    <span class="msgHp"> - 포함 13자리를 입력하세요.</span>
                                </td>
                            </tr>
                            <tr class="addr">
                                <th>주소</th>
                                <td>
                                    <div><input type="text" name="km_zip" id="zip" placeholder="우편번호 입력 클릭" readonly></div>
                                    <div><input type="text" name="km_addr1" id="addr1" size="50" placeholder="주소를 검색하세요" readonly></div>
                                    <div><input type="text" name="km_adrr2" id="addr2" size="50" placeholder="상세주소를 입력하세요"></div>
                                </td>
                            </tr>
                        </table>
                    </section>
                    <div>
                        <input type="submit" class="join" value="회원가입">
                    </div>
                </form>
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