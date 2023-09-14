<%@ include file="./_header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<script>
	$(document).ready(function(){
		$(".theme-link").click(function(){
			// 현재 클릭한 링크를 강조하기 위해 "on" 클래스를 추가한다.
			$(".theme-link").removeClass("on");
			$(this).addClass("on");
		});
	});
</script>
      <section id="cs">
        <div class="qna">
          <nav>
            <div>
              <p>홈<span>></span>문의하기</p>
            </div>
          </nav>
          <section class="list">
            <aside>
              <h2>문의하기</h2>
              <ul>
                <li class="theme-link"><a href="./qna_list_member.jsp">회원</a></li>
                <li class="theme-link"><a href="./qna_list_coupon_event.jsp">쿠폰/이벤트</a></li>
                <li class="theme-link"><a href="./qna_list_order_payment.jsp">주문/결제</a></li>
                <li class="theme-link"><a href="./qna_list_delivery.jsp">배송</a></li>
                <li class="theme-link"><a href="./qna_list_cancel_return_exchange.jsp">취소/반품/교환</a></li>
                <li class="theme-link"><a href="./qna_list_travel_lodgment_airline">여행/숙박/항공</a></li>
                <li class="theme-link"><a href="./qna_list_safe.jsp">안전거래</a></li>
              </ul>
            </aside>
            <article>
              <nav>
                <h1>회원</h1>
                <h2>회원관련 문의 내용입니다.</h2>
              </nav>
              <table>
                <tr>
                  <td><a href="./view.html">[가입] 가입 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="./view.html">[탈퇴] 탈퇴 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="#">[회원정보] 회원정보 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="#">[로그인] 회원정보 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="#">[로그인] 회원정보 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="#">[로그인] 회원정보 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="#">[회원정보] 회원정보 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="#">[회원정보] 회원정보 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="#">[탈퇴] 회원정보 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
                <tr>
                  <td><a href="#">[탈퇴] 회원정보 문의내용</a></td>
                  <td>chh**</td>
                  <td>2022.11.21</td>
                </tr>
              </table>

              <div class="page">
                <a href="#" class="prev">이전</a>
                <a href="#" class="num on">1</a>
                <a href="#" class="num">2</a>
                <a href="#" class="num">3</a>
                <a href="#" class="next">다음</a>
              </div>

              <a href="./qna_write.jsp" class="btnWrite">문의하기</a>

            </article>
          </section>
        </div>
      </section>
<%@ include file = "./_footer.jsp" %>