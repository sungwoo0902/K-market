<%@ include file="../_header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
      <section id="cs">
        <div class="qna">
          <nav>
            <div>
              <p>홈<span>></span>문의하기</p>
            </div>
          </nav>
          <section class="view">
            <aside>
              <h2>문의하기</h2>
              <ul>
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
                <h2 class="title"><input type="text" name="title" value="${board.title}" readonly/></h2>                
                <p>
                  <span><input type="text" name="uid" value=${board.uid} readonly/></span>
                  <span><input type="text" name="rDate" value=${board.rDate} readonly/></span>
                  
                </p>
              </nav>

              <div class="content">
                <p>
                  개인회원에서 법인회원(사업자 회원)으로 전환은 불가하므로 법인회원(사업자 회원) 전환은 신규 가입으로 진행을 해야 합니다.
                </p>
                <p>
                  ※ 피싱 관련 피해신고<br /><br />
                  ▶ 경찰청 사이버수사국 (국번없이)182 :
                  http://cyberbureau.police.go.kr<br />
                  ▶ KISA 인터넷침해대응센터 (국번없이)118 :
                  http://www.krcert.or.kr<br />
                  감사합니다.<br />
                </p>
              </div>
              <a href="./list.jsp" class="btnList">목록보기</a>
            </article>
          </section>
        </div>
      </section>

      <footer>
        <ul>
          <li><a href="#">회사소개</a></li>
          <li><a href="#">서비스이용약관</a></li>
          <li><a href="#">개인정보처리방침</a></li>
          <li><a href="#">전자금융거래약관</a></li>
        </ul>
        <%@ include file="../_header.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
