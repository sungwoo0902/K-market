<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	$(function(){
		
		// 포인트 사용 버튼 구현중
		$('#applyDiscount').click(function(){
			
			var discountPoint = parseInt($('#discountPoint').val());
			
			var currentPoint = parseInt($("#currentPoint").text());
			
			if(!isNaN(discountPoint) & discountPoint > 0){
				// 입력된 포인트 또는 현재 포인트 중 작은 값을 할인 금액으로 사용
				var discountAmount = Math.min(discountPoint, currentPoint); 
				// 입력 필드에 할인된 포인트 설정
	            $("#discountPoint").val(discountAmount); 

	            // 전체 주문 금액 업데이트
	            // 주문 상품 금액
	            var orderPrice = parseInt("${order.ordPrice}"); 
	         	// 배송비
	            var orderDelivery = parseInt("${order.ordDelivery}"); 
	         	// 총 주문 금액
	            var totalOrderPrice = orderPrice + orderDelivery; 
	         	// 할인 후 총 주문 금액
	            var updatedTotal = totalOrderPrice - discountAmount; 

	            // 할인 금액 및 전체 주문 금액 업데이트
	            $("#orderDiscount").text(discountAmount + "원");
	            $("#totalOrderPrice").text(updatedTotal + "원");
	        } else {
	            alert("포인트는 5000점 이상이어야 합니다.");
	        }
		});// applyDiscount click end
		
	}); //end

</script>

<main id="product">
    <%@ include file="./_aside.jsp" %>
    <!-- 주문 페이지 시작 -->
    <section class="order">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>주문결제</h1>
            <p>
                HOME > 장바구니 > 
                <strong>주문결제</strong>
            </p>
        </nav>
        <form action="#">
            <!-- 주문 상품 목록 -->
            <table>
                <thead>
                    <tr>
                        <th>상품명</th>
                        <th>총수량</th>
                        <th>판매가</th>
                        <th>배송비</th>
                        <th>소계</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="empty">
                        <td colspan="7">장바구니에 상품이 없습니다.</td>
                    </tr>
                    <c:forEach var="item" items="${orderItems}">
                    <tr>
                        <td>
                            <article>
                                <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">
                                    <img src="${ctxPath}/thumb/${item.prodCate1}/${item.prodCate2}/${item.thumb1}" alt="">
                                </a>
                                <div>
                                    <h2>
                                        <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">${item.prodName}</a>
                                    </h2>
                                    <p>상품설명:${item.descript}</p>
                                </div>
                            </article>
                        </td>
                        <td><fmt:formatNumber value="${item.count}" pattern="#,###" />개</td>
                        <c:if test="${item.discount ne 0}">
                        	<td>
						        <span class="throughPrice"><fmt:formatNumber value="${item.price * item.count}" pattern="#,###" />원</span>
						        <fmt:formatNumber value="${item.total}" pattern="#,###" />원
						    </td>
                        </c:if>
                        <c:if test="${item.discount eq 0}">
                        	<td><fmt:formatNumber value="${item.price * item.count}" pattern="#,###" />원</td>
                        </c:if>
                        <c:if test="${item.delivery eq 0}">
                        	<td class="free-delivery">0</td>
                        </c:if>
                        <c:if test="${item.delivery ne 0}">
                        	<td class="has-delivery"><fmt:formatNumber value="${item.delivery}" pattern="#,###" />원</td>
                        </c:if>
                        <td><fmt:formatNumber value="${item.total+item.delivery}" pattern="#,###" />원</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- 최종 결제 정보 -->
            <div class="final">
                <h2>최종결제 정보</h2>
                <table border="0">
                    <tbody>
                        <tr>
                            <td>총</td>
                            <td><fmt:formatNumber value="${order.ordCount}" pattern="#,###" /> 건</td>
                        </tr>
                        <tr>
                            <td>상품금액</td>
                            <td><fmt:formatNumber value="${order.ordPrice}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td>할인금액</td>
                            <td><fmt:formatNumber value="${order.ordDiscount}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td><fmt:formatNumber value="${order.ordDelivery}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td>포인트 할인</td>
                            <td>0</td>
                        </tr>
                        <tr>
                            <td>전체주문금액</td>
                            <td><fmt:formatNumber value="${order.ordTotPrice}" pattern="#,###" />원</td>
                        </tr>
                    </tbody>
                </table>
                <input type="button" name value="결제하기">
            </div>
            <!-- 배송정보 -->
            <article class="delivery">
                <h1>배송정보</h1>
                <table>
                    <tbody>
                        <tr>
                            <td>주문자</td>
                            <td>
                                <input type="text" name="orderer" value="${member.name}">
                            </td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td>
                                <input type="text" name="hp" value="${member.hp}">
                                <span>- 포함 입력</span>
                            </td>
                        </tr>
                        <tr>
                            <td>우편번호</td>
                            <td>
                                <input type="text" name="zip" value="${member.zip}">
                                <input type="button" value="검색">
                            </td>
                        </tr>
                        <tr>
                            <td>기본주소</td>
                            <td>
                                <input type="text" name="addr1" value="${member.addr1}">
                            </td>
                        </tr>
                        <tr>
                            <td>상세주소</td>
                            <td>
                                <input type="text" name="addr2" value="${member.addr2}">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </article>
            <!-- 할인정보 -->
            <article class="discount">
                <h1>할인정보</h1>
                <div>
                    <p>
                        현재 포인트 : 
                        <span id="currenPoint">${member.point}</span>
                        점
                    </p>
                    <label>
                    	<c:if test="${member.point < 5000}">
                        	<input type="text" id="discountPoint" name="point" readonly>
                        </c:if>
                    	<c:if test="${member.point >= 5000}">
                        	<input type="text" id="discountPoint" name="point">
                        </c:if>
                        점
                        <input type="button" id="applyDiscount" value="적용">
                    </label>
                    <span>포인트 5000점 이상이면 현금처럼 사용 가능합니다.</span>
                </div>
            </article>
            <!-- 결제방법 -->
            <article class="payment">
                <h1>결제방법</h1>
                <div>
                    <span>신용카드</span>
                    <p>
                        <label>
                        	<c:if test="${order.ordPayment eq 101}">
                            <input type="radio" name="payment" value="type1" checked>
                            </c:if>
                        	<c:if test="${order.ordPayment ne 101}">
                            <input type="radio" name="payment" value="type1">
                            </c:if>
                            신용카드 결제
                        </label>
                        <label>
                            <input type="radio" name="payment" value="type2">
                            체크카드 결제
                        </label>
                    </p>
                </div>
                <div>
                    <span>계좌이체</span>
                    <p>
                        <label>
                            <input type="radio" name="payment" value="type3">
                            실시간 계좌이체
                        </label>
                        <label>
                            <input type="radio" name="payment" value="type4">
                            무통장 입금
                        </label>
                    </p>
                </div>
                <div>
                    <span>기타</span>
                    <p>
                        <label>
                            <input type="radio" name="payment" value="type3">
                            휴대폰 결제
                        </label>
                        <label>
                            <input type="radio" name="payment" value="type4">
                            카카오페이
                            <img src="./images/ico_kakaopay.gif" alt="카카오페이">
                        </label>
                    </p>
                </div>
            </article>
            <!-- 경고 -->
            <article class="alert">
                <ul>
                    <li>
                        <span>
                            케이마켓의 모든 판매자는 안전거래를 위해 구매금액, 결제수단에 상관없이 모든거래에 대하여 케
                            이마켓 유한책임회사의 구매안전서비스(에스크로)를 제공하고 있습니다.
                        </span>
                    </li>
                    <li>
                        <span>
                            케이마켓 유한책임회사의 전자금융거래법에 의해 결제대금예치업 등록번호는 02-006-00008 입니다.
                        </span>
                    </li>
                    <li>
                        <span>
                            등록여부는 금융감독원 홈페이지(www.fss.or.kr)의 업무자료>인허가업무안내>전자금융업등록현황
                            에서 확인하실수 있습니다.
                        </span>
                    </li>
                </ul>
            </article>
        </form>
    </section>
    <!-- 주문 페이지 끝 -->
</main>
<%@ include file="./_footer.jsp" %>