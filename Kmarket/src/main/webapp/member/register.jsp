<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../js/zipcode.js"></script>
<script src="../js/autoHyphen.js"></script>
<script src="../js/validation.js"></script>
<script src="../js/duplication.js"></script>
<script>
	if(${(location > 1) || (location < 0) || (location eq null)}){
		location.href = './login.do?success=102';
	}
</script>
        <main id="member">
            <div class="register">
                <nav><h1>일반 회원가입</h1></nav>
                <form action="#" method="post" id="formRegNormal">
                	<input type="hidden" name="location" value="${location}">
                    <section>
                        <table>
                            <caption>필수 정보입력</caption>
                            <tr>
                                <th><span class="essential">*</span>아이디</th>
                                <td>
                                    <input type="text" name="km_uid" placeholder="아이디를 입력">
                                    <input type="button" id="check" value="중복 체크">
                                    <span class="msgId">영문, 숫자로 4~12자까지 설정해 주세요.</span>
                                </td>
                            </tr>

                            <tr>
                                <th><span class="essential">*</span>비밀번호</th>
                                <td>
                                    <input type="password" name="km_pass" placeholder="비밀번호를 입력">
                                    <span class="msgPass">영문, 숫자, 특수문자를 조합하여 8~12자까지 설정해 주세요.</span>
                                </td>
                            </tr>

                            <tr>
                                <th><span class="essential">*</span>비밀번호확인</th>
                                <td>
                                    <input type="password" name="km_pass2" placeholder="비밀번호를 확인">
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
                                <td>
                                	<input type="text" name="km_name" placeholder="이름을 입력">
                               		<span class="msgName"></span>
                               	</td>
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
                                <td>
                                	<input type="email" name="km_email" placeholder="이메일 입력">
                                	<span class="msgEmail"></span>
                               	</td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>휴대폰</th>
                                <td>
                                    <input type="text" name="km_hp" maxlength="13" class="auto2" placeholder="휴대폰번호 입력">
                                    <span class="msgHp">휴대번호 11자리를 입력하세요.</span>
                                </td>
                            </tr>
                            <tr class="addr">
                                <th>주소</th>
                                <td>
                                    <div>
	                                    <input type="text" name="km_zip" id="zip" placeholder="우편번호 입력 클릭" readonly>
	                                    <input type="button" onclick="zipcode()" value="우편번호입력">
                                    </div>
                                    <div><input type="text" name="km_addr1" id="addr1" size="50" placeholder="주소를 검색하세요" readonly></div>
                                    <div><input type="text" name="km_addr2" id="addr2" size="50" placeholder="상세주소를 입력하세요"></div>
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
<%@ include file="./_footer.jsp" %>