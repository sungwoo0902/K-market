<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	$(function() {
		
		$('.loginBtn').click(function(e) {
			e.preventDefault();
			if($('input[name=uid]').val()=='' || $('input[name=pass]').val()=='') {
				alert('아이디, 비밀번호를 모두 입력해주세요.');
				
			} else {
				$(this).parent().submit();
			}
		});

		if(${success eq '100'}) {
			alert('회원가입에 실패하였습니다.\n다시 시도하거나 고객센터로 문의해주세요.')
			
		}else if(${success eq '101'}) {
			alert('로그인에 실패했습니다.\n아이디, 비밀번호를 다시 확인해주세요.')
		
		}else if(${success eq '102'}) {
			alert('[경고] 비정상적인 접근을 통한 회원가입')
			
		}else if(${success eq '103'}) {
			alert('로그인 후 이용할 수 있는 서비스입니다.')
			
		}else if(${success eq '104'}) {
			alert('서비스 이용 권한이 없습니다.')
			
		}else if(${success eq '200'}) {
			alert('성공적으로 회원가입 됐습니다.')
		}
	});
</script>
        <main id="member">
            <div class="login">
                <nav>
                    <h1>로그인</h1>
                </nav>
                <form action="${ctxPath}/member/login.do" method="post">
                	<input type="hidden" value="${bfAddr}" name="bfAddr">
                    <table border="0">
                        <tr>
                            <td>아이디</td>
                            <td>
                                <input type="text" name="uid" placeholder="아이디 입력">
                            </td>
                        </tr>
                        <tr>
                            <td>비밀번호</td>
                            <td>
                                <input type="password" name="pass" placeholder="비밀번호 입력">
                            </td>
                        </tr>
                    </table>
                    <input class="loginBtn" type="submit" value="로그인">
                    <span>
                        <label>
                            <input type="checkbox" name="auto">자동로그인
                        </label>
                        <a href="#" class="unready">아이디찾기</a>
                        <a href="#" class="unready">비밀번호찾기</a>
                        <a href="${ctxPath}/member/join.do">회원가입</a>
                    </span>
                    <a href="https://safelogin.kr/sauth/regist?site_code=NA&sub_code=0" target="_blank" rel="noopener noreferrer" class="banner">
                        <img src="./images/member_login_banner.jpg" alt="1만원 할인 쿠폰 받기">
                    </a>
                </form>
                <img src="./images/member_certifi_logo.gif" alt="banner">
            </div>
        </main>
<%@ include file="./_footer.jsp" %>