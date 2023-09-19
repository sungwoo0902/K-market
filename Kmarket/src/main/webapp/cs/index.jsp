<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
      <section id="cs">
        <div class="main">
          <h1 class="title"><strong>케이마켓</strong>이 도와드릴게요!</h1>              
          <section class="notice">
            <h1>공지사항<a href="./notice/list.do">전체보기</a></h1>
            <ul>
            <c:forEach var="article_notice_list" items="${articles_notice_lists}">
              	<li>
              		<a href="./notice/notice_list_all.do?no=${article_notice_list.no}" class="title">${article_notice_list.title}</a>
              		<span class="date">${article_notice_list.rdate}</span>
              	</li>
            </c:forEach>
            </ul>
          </section>
        
          <section class="faq">
            <h1>자주 묻는 질문<a href="./cs/faq/list.do">전체보기</a>
            </h1>
            <ol>
              <li>
                <a href="./cs/faq/list.do"><span>회원</span></a>
              </li>
              <li>
                <a href="./cs/faq/list.do"><span>쿠폰/이벤트</span></a>
              </li>
              <li>
                <a href="./cs/faq/list.do"><span>주문/결제</span></a>
              </li>
              <li>
                <a href="./cs/faq/list.do"><span>배송</span></a>
              </li>
              <li>
                <a href="./cs/faq/list.do"><span>취소/반품/교환</span></a>
              </li>
              <li>
                <a href="./cs/faq/list.do"><span>여행/숙박/항공</span></a>
              </li>
              <li>
                <a href="./cs/faq/list.do"><span>안전거래</span></a>
              </li>
            </ol>
          </section>
        
          <section class="qna">
            <h1>문의하기<a href="./qna/list.do">전체보기</a></h1>
            <ul>
            <c:forEach var="article_qna_list" items="${articles_qna_lists}">
            	<li>
                	<a href="./qna/view.do?no=${article_qna_list.no}" class="title">${article_qna_list.title}</a>
                	<p>
                  		<span class="uid">${article_qna_list.uid}</span>
                  		<span class="date">${article_qna_list.rdate}</span>
                	</p>
            	</li>
            </c:forEach>
            </ul>
            <a href="./cs/qna/write.do" class="ask">문의글 작성 ></a>
          </section>

          <section class="tel">
            <h1>
              1:1 상담이 필요하신가요?
            </h1>

            <article>
              <div>
                <h3>고객센터 이용안내</h3>
                <p>
                  <span>일반회원/비회원</span><br>
                  <strong>1566-0001</strong><span>(평일 09:00 ~ 18:00)</span>
                </p>
                <p>
                  <span>스마일클럽 전용</span><br>
                  <strong>1566-0002</strong><span>(365일 09:00 ~ 18:00)</span>
                </p>
              </div>
            </article>
            <article>
              <div>
                <h3>판매상담 이용안내</h3>
                <p>
                  <span>판매고객</span><br>
                  <strong>1566-5700</strong><span>(평일 09:00 ~ 18:00)</span>
                </p>
                <p>
                  <a href="#">판매자 가입 및 서류 접수 안내 〉</a>
                </p>                
              </div>
            </article>
          </section>        
      </section>
<%@ include file = "./_footer.jsp" %>