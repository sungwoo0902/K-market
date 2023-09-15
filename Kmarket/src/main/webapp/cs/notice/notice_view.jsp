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
	
	//버튼 클릭 시 이벤트 처리
	document.addEventListener("DOMContentLoaded", function () {
	  // btnList 버튼을 클릭하면 실행될 함수
	  document.querySelector(".btnList").addEventListener("click", function () {
	    // 모든 li 태그 숨기기
	    const liElements = document.querySelectorAll(".theme-link");
	    liElements.forEach(function (li) {
	      li.style.display = "none";
	    });
	  });
	});
	
	$(document).ready(function(){
		$(".theme-link").click(function(){
			// 현재 클릭한 링크를 강조하기 위해 "on" 클래스를 추가한다.
			$(".theme-link").removeClass("on");
			$(this).addClass("on");
		});
		
		// 버튼 클릭 시 이벤트 처리
		$(".btnList").click(function(){
			// 현재 페이지의 URL을 가져오고, 현재 theme-link 클래스가 실행중인 페이지로 이동한다.
			const currentPageURL = window.location.href;
			const currentThemeLink = $(".theme-link.on a").attr("href");
			
			// 현재 theme-link의 URL로 이동
			window.location.href = currentThemeLink;
		})
	});
</script>
      <section id="cs">
        <div class="notice">
          <nav>
            <div>
              <p>홈<span>></span>공지사항</p>
            </div>
          </nav>
          <section class="view">
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
                <h2 class="title"><input type="text" name="title" value="${board.title}" readonly></h2>
                <span class="date">${board.rDate}</span>
              </nav>

              <div class="content">
                <p>${board.content}</p>
              </div>
              <a href="./notice_list_all.jsp" class="btnList">목록보기</a>
            </article>
          </section>
        </div>
      </section>
<%@ include file = "../_footer.jsp" %>