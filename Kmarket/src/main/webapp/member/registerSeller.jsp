<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="../js/zipcode.js"></script>
<script src="../js/autoHyphen.js"></script>
<script src="../js/validation.js"></script>
<script src="../js/duplication.js"></script>
        <main id="member">
            <div class="registerSeller">
                <nav><h1>판매자 회원가입</h1></nav>

                <form action="${ctxPath}/member/registerSeller.do" method="post" id="formRegSeller">
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
                            <caption>판매자 정보입력</caption>
                            <tr>
                                <th><span class="essential">*</span>회사명</th>
                                <td>
                                    <input type="text" name="kms_company" placeholder="회사명 입력">
                                    <span class="msgCompany">(주)포함 입력, 예) (주)케이마켓</span>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>대표자</th>
                                <td>
                                	<input type="text" name="kms_ceo" placeholder="대표자 입력">
                                	<span class="msgCeo"></span>
                               	</td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>사업자등록번호</th>
                                <td>
                                    <input type="text" name="kms_corp_reg" class="auto3" maxlength="12" placeholder="사업자등록번호 입력">
                                    <span class="msgCorp">사업자등록번호 10자리 입력, 예) 123-45-67890</span>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>통신판매업신고 번호</th>
                                <td>
                                    <input type="text" name="kms_online_reg" placeholder="통신판매업신고 번호 입력">
                                    <span class="msgOnline">- 표시 포함 예) 강남-12345, 제 1-01-23-4567호, 2017-경기성남-0011</span>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>전화번호</th>
                                <td>
                                    <input type="text" name="kms_tel" class="auto1" maxlength="12" placeholder="전화번호 입력">
                                    <span class="msgTel">지역번호 포함, 예) 02-234-1234</span>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>팩스번호</th>
                                <td>
                                    <input type="text" name="kms_fax" class="auto1" maxlength="12" placeholder="팩스번호 입력">
                                    <span class="msgFax">지역번호 포함, 예) 02-234-1234, 070-234-1234</span>
                                </td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>EMAIL</th>
                                <td>
                                	<input type="email" name="km_email" placeholder="이메일 입력">
                                	<span class="msgEmail"></span>
                                </td>
                            </tr>
                            <tr class="addr">
                                <th>회사주소</th>
                                <td>
                                    <div>
                                    	<input type="text" name="km_zip" id="zip" placeholder="우편번호 입력 클릭">
                                    	<input type="button" onclick="zipcode()" value="우편번호입력">
                                   	</div>
                                    <div><input type="text" name="km_addr1" id="addr1" size="50" placeholder="주소를 검색하세요" readonly></div>
                                    <div><input type="text" name="km_addr2" id="addr2" size="50" placeholder="상세주소를 입력하세요"></div>
                                </td>
                            </tr>
                        </table>
                    </section>
                    <section>
                        <table>
                            <caption>담당자 정보입력</caption>
                            <tr>
                                <th>
                                    <span class="essential">*</span>이름
                                </th>
                                <td>
                                    <input type="text" name="km_name" placeholder="이름을 입력">
                                </td>
                            </tr>
                            <tr>
                                <th><span class="essential">*</span>휴대폰</th>
                                <td>
                                    <input type="text" name="km_hp" maxlength="13" class="auto2" placeholder="휴대폰번호 입력">
                                    <span class="msgHp">- 포함 13자리를 입력하세요.</span>
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