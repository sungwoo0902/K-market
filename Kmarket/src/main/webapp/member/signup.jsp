<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	
	window.onload = function() {
		
		const ag1 = document.getElementsByName('agree1')[0];
		const ag2 = document.getElementsByName('agree2')[0];
		const ag3 = document.getElementsByName('agree3')[0];
		const ag4 = document.getElementsByName('agree4')[0];
		
		const agree = document.querySelector('.agree');
		
		agree.addEventListener('click', function() {
			
			if(!ag1.checked) {
				if(${type eq "normal"}) {
					alert('이용약관에 동의해주세요.');
				}else {
					alert('판매자 세금납부약관에 동의해주세요.');
				}
				return;
				
			}else if(!ag2.checked) {
				alert('전자금융거래 이용약관에 동의해주세요.')
				return;
				
			}else if(!ag3.checked) {
				alert('개인정보 취급방침에 동의해주세요.')
				return;
				
			}else if(!ag4.checked) {
				alert('위치정보 이용약관에 동의해주세요.')
				return;
				
			}
			
			if(${type eq "normal"}) {
				location.href = '/Kmarket/member/register.do';
			}else {
				location.href = '/Kmarket/member/registerSeller.do';
			}
		});
	}
	
</script>
        <main id="member">
            <div class="signup">
                <nav>
                    <h1>약관동의</h1>
                </nav>
                <section>
					<c:choose>
						<c:when test="${type eq 'normal'}">
							<h3><span class="essential">(필수)</span>케이마켓 이용약관</h3>
                    		<textarea class="terms" readonly>${member_terms.terms}</textarea>
						</c:when>
						<c:otherwise>
							<h3><span class="essential">(필수)</span>판매자 세금납부약관</h3>
                    		<textarea class="tax" readonly>${member_terms.tax}</textarea>
						</c:otherwise>
					</c:choose>
					<label><input type="checkbox" name="agree1">동의합니다.</label>
					
                    <h3><span class="essential">(필수)</span>전자금융거래 이용약관</h3>
                    <textarea class="financial" readonly>${member_terms.finance}</textarea>
                    <label><input type="checkbox" name="agree2">동의합니다.</label>

                    <h3><span class="essential">(필수)</span>개인정보 수집동의</h3>
                    <textarea class="privacy" readonly>${member_terms.privacy}</textarea>
                    <label><input type="checkbox" name="agree3">동의합니다.</label>
                </section>

                <section>
                    <h3><span class="optional">(선택)</span>위치정보 이용약관</h3>
                    <textarea class="location" readonly>${member_terms.location}</textarea>
                    <label><input type="checkbox" name="agree4">동의합니다.</label>
                </section>
                
                <div>
                    <input type="button" class="agree" value="동의하기">
                </div>

            </div>
        </main>
<%@ include file="./_footer.jsp" %>