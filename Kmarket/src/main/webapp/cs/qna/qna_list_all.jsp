<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
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
              	<li class="theme-link"><a href="./qna_list_all.do">전체</a></li>
                <li class="theme-link"><a href="./qna_list_member.do">회원</a></li>
                <li class="theme-link"><a href="./qna_list_coupon_event.do">쿠폰/이벤트</a></li>
                <li class="theme-link"><a href="./qna_list_order_payment.do">주문/결제</a></li>
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
              	<c:forEach var="board" items="${boards}">
	                <tr>
	                	<td>${pageStartNum = pageStartNum - 1}</td>
		                <td><a href="./view.do?no=${article.no}">${article.title}[${article.comment}]</a></td>
		                <td>${article.nick}</td>
		                <td>${article.rdate}</td>
		                <td>${article.hit}</td>
	                </tr>
	          	</c:forEach>  
              </table>

              <div class="page">
                <c:if test="${pageGroupStart > 1}">
            		<a href="/Kmarket/qna_list_cancel_return_exchange.do?pg=${pageGroupStart - 1}&search=${search}" class="prev">이전</a>
            	</c:if>
            	<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
            		<a href="/Kmarket/qna_list_cancel_return_exchange?pg=${i}&search=${search}" class="num ${currentPage == i?'current':'off'}">${i}</a>
            	</c:forEach>
            	<c:if test="${pageGroupEnd < lastPageNum}">
            		<a href="/Kmarket/qna_list_cancel_return_exchange?pg=${pageGroupEnd + 1}&search=${search}" class="next">다음</a>
            	</c:if>
              </div>

              <a href="./qna_write.jsp" class="btnWrite">문의하기</a>

            </article>
          </section>
        </div>
      </section>
<%@ include file = "../_footer.jsp" %>