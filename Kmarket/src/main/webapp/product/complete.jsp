<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<main id="product">
    <%@ include file="./_aside.jsp" %>
    <!-- 결제완료 페이지 시작 -->
    <section class="complete">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>주문완료</h1>
            <p>
                HOME > 장바구니 > 주문결제 > 
                <strong>주문완료</strong>
            </p>
        </nav>
        <!-- 완료 멘트 -->
        <article class="message">
            <h2>
                고객님의 주문이 정상적으로 완료되었습니다.
                <i class="far fa-smile" aria-hidden="true"></i>
            </h2>
            <p>
                즐거운 쇼핑이 되셨습니까? 항상 고객님을 최우선으로 생각하는 케이마켓이 되겠습니다.
            </p>
        </article>
        <!-- 상품정보 -->
        <article class="info">
            <h1>상품정보</h1>
            <table border="0">
                <tbody>
                    <tr>
                        <th>상품명</th>
                        <th>상품금액</th>
                        <th>할인금액</th>
                        <th>수량</th>
                        <th>주문금액</th>
                    </tr>
                    <c:forEach var="orderItem" items="${orderItems}">
                    <tr>
                        <td>
                            <article>
                                <img src="${ctxPath}/thumb/${orderItem.prodCate1}/${orderItem.prodCate2}/${orderItem.thumb1}" alt="">
                                <div>
                                    <h2>
                                        <a href="${ctxPath}/product/view.do?cate1=${orderItem.prodCate1}&cate2=${orderItem.prodCate2}&prodNo=${orderItem.prodNo}">${orderItem.prodName}</a>
                                    </h2>
                                    <p>${orderItem.descript}</p>
                                </div>
                            </article>
                        </td>
                        <td><fmt:formatNumber value="${orderItem.price}" pattern="#,###" />원</td>
                        <c:if test="${orderItem.discount ne 0}">
                        	<td><fmt:formatNumber value="${(orderItem.price * orderItem.count)/100 * orderItem.discount}" pattern="#,###" />원</td>
                        </c:if>
                        <c:if test="${orderItem.discount eq 0}">
                        	<td>-</td>
                        </c:if>
                        <td><fmt:formatNumber value="${orderItem.count}" pattern="#,###" /></td>
                        <td><fmt:formatNumber value="${orderItem.total - orderItem.delivery}" pattern="#,###" />원</td>
                    </tr>
                    </c:forEach>
                    
                    <tr class="total">
                        <td colspan="4"></td>
                        <td>
                            <table border="0">
                                <tbody>
                                    <tr>
                                        <td>총 상품금액</td>
                                        <td>
                                            <span><fmt:formatNumber value="${order.ordPrice}" pattern="#,###" /></span>
                                            원
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>총 할인금액</td>
                                        <td>
                                            <span>-<fmt:formatNumber value="${order.ordDiscount + order.usedPoint}" pattern="#,###" /></span>
                                            원
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>배송비</td>
                                        <td>
                                            <span><fmt:formatNumber value="${order.ordDelivery}" pattern="#,###" /></span>
                                            원
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>총 결제금액</td>
                                        <td>
                                            <span><fmt:formatNumber value="${order.ordTotPrice}" pattern="#,###" /></span>
                                            원
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </tbody>
            </table>
        </article>
        <!-- 주문정보 -->
        <article class="order">
            <h1>주문정보</h1>
            <table border="0">
                <tbody>
                    <tr>
                        <td>주문번호</td>
                        <td>${order.ordNo}</td>
                        <td rowspan="3">총 결제금액</td>
                        <td rowspan="3">
                            <span><fmt:formatNumber value="${order.ordTotPrice}" pattern="#,###" /></span>
                            원
                        </td>
                    </tr>
                    <tr>
                        <td>결제방법</td>
                        <td>${order.ordPaymentName}</td>
                    </tr>
                    <tr>
                        <td>주문자/연락처</td>
                        <td>${sessUser.name}/${sessUser.hp}</td>
                    </tr>
                </tbody>
            </table>
        </article>
        <!-- 배송정보 -->
        <article class="delivery">
            <h1>배송정보</h1>
            <table border="0">
                <tbody>
                    <tr>
                        <td>수취인</td>
                        <td>${order.recipName}</td>
                        <td>주문자 정보</td>
                    </tr>
                    <tr>
                        <td>연락처</td>
                        <td>${order.recipHp}</td>
                        <td rowspan="2">
                             ${sessUser.name}<br>
                              ${sessUser.hp}
                        </td>
                    </tr>
                    <tr>
                        <td>배송지 주소</td>
                        <td>${order.recipAddr1} ${order.recipAddr2}</td>
                    </tr>
                </tbody>
            </table>
        </article>
        <!-- 꼭 알아두세요.-->
        <article class="alert">
            <h1>꼭 알아두세요.</h1>
            <ul>
                <li>
                    <span>
                        케이마켓은 통신판매중개자이며 통신판매의 당사자가 아닙니다. 따라서 케이마켓은 상품, 거래정보 및 거래에 대하여 책임을 지지 않습니다.
                    </span>
                </li>
                <li>
                    <span>
                        구매주문내역, 배송상태 확인, 구매영수증 출력, 구매취소/반품/교환은 사이트 상단의 주문/배송조회에서 확인 할 수 있습니다.
                    </span>
                </li>
                <li>
                    <span>
                        고객님의 주문이 체결된 후 상품품절 및 단종 등에 의해 배송이 불가능할 경우, 전자상거래등에서의 소비자 보호에 관한 법률 제15조 2항에 의거하여 3영업일(공휴일 제외)
                        이내에 자동으로 취소될 수 있으며, 이 경우 취소 안내 메일이 고객님께 발송되오니 양지 바랍니다.
                    </span>
                </li>
                <li>
                    <span>
                        극히 일부 상품에 대해 수량부족, 카드결제승인 오류등의 사례가 간혹 있을 수 있으니 `나의쇼핑정보`에서 다시 한번 확인해 주세요.
                    </span>
                </li>
                <li>
                    <span>
                        현금잔고로 구매하셨을 경우, 나의 쇼핑정보에서 입금확인이 되었는지를 다시 한번 확인해 주세요.
                    </span>
                </li>
                <li>
                    <span>
                        배송주소를 추가하거나 변경, 삭제 등의 관리는 `나의쇼핑정보 > 나의정보` 에서 가능합니다.
                    </span>
                </li>
            </ul>
        </article>
    </section>
    <!-- 결제완료 페이지 끝 -->
</main>
<%@ include file="./_footer.jsp" %>