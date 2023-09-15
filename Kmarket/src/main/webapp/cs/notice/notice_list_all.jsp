<%@ include file="../_header.jsp" %>
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
        <div class="notice">
          <nav>
            <div>
              <p>홈<span>></span>공지사항</p>
            </div>
          </nav>
          <section class="list">
            <aside>
              <h2>공지사항</h2>
              <ul>
                <li class="theme-link"><a href="./cs/notice/notice_list_all.jsp">전체</a></li>
                <li class="theme-link"><a href="./cs/notice/notice_list_customerservice.jsp">고객서비스</a></li>
                <li class="theme-link"><a href="./cs/notice/notice_list_safe.jsp">안전거래</a></li>
                <li class="theme-link"><a href="./cs/notice/notice_list_danger.jsp">위해상품</a></li>
                <li class="theme-link"><a href="./cs/notice/notice_list_event.jsp">이벤트당첨</a></li>
              </ul>
            </aside>
            <article>
              <nav>
                <h1>전체</h1>
                <h2>공지사항 전체 내용입니다.</h2>
              </nav>

              <table>
              <c:forEach var="board" items="${boards}"> 
	        	<c:if test="${board.boardCate1 == 1}">   
		           	<ul>
		           		<li><a href="./notice/notice_list_all.do?no=${board.no}">${board.title}</a></li>
	        	  		<li>${board.uid}</li>
	        			<li>${board.rDate}</li>
		           	</ul>
	           	</c:if>
	          </c:forEach>   
              </table>

              <div class="page">
        		<c:if test="${pageGroupStart > 1}">
            		<a href="/Kmarket/notice/notice_list_all.do?pg=${pageGroupStart - 1}&search=${search}" class="prev">이전</a>
            	</c:if>
            	<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
            		<a href="/Kmarket/notice/notice_list_all.do?pg=${i}&search=${search}" class="num ${currentPage == i?'current':'off'}">${i}</a>
            	</c:forEach>
            	<c:if test="${pageGroupEnd < lastPageNum}">
            		<a href="/Kmarket/notice/notice_list_all.do?pg=${pageGroupEnd + 1}&search=${search}" class="next">다음</a>
            	</c:if>
        	  </div>
            </article>
          </section>
        </div>
      </section>
<%@ include file = "../_footer.jsp" %>