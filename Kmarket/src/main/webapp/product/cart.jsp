<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<main id="product">
    <%@ include file="./_aside.jsp" %>
    <!-- 장바구니 페이지 시작 -->
    <section class="cart">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>장바구니</h1>
            <p>
                HOME > 
                <span>패션·의류·뷰티</span>
                 > 
                 <strong>장바구니</strong>
            </p>
        </nav>
        <form action="#">
            <!-- 장바구니 목록 -->
            <table>
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" name="all">
                        </th>
                        <th>상품명</th>
                        <th>총수량</th>
                        <th>판매가</th>
                        <th>할인</th>
                        <th>포인트</th>
                        <th>배송비</th>
                        <th>소계</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="empty">
                        <td colspan="7">장바구니에 상품이 없습니다.</td>
                    </tr>
                    <c:forEach var="cart" items="${carts}">
                    <tr>
                        <td><input type="checkbox" name></td>
                        <td>
                            <article>
                                <a href="${ctxPath}/product/view.do?cate1=${cart.prodCate1}&cate2=${cart.prodCate2}&prodNo=${cart.prodNo}">
                                    <img src="${ctxPath}/thumb/${cart.prodCate1}/${cart.prodCate2}/${cart.thumb1}" alt="">
                                </a>
                                <div>
                                    <h2>
                                        <a href="#">${cart.prodName}</a>
                                    </h2>
                                    <p>상품설명:${cart.descript}</p>
                                </div>
                            </article>
                        </td>
                        <td><fmt:formatNumber value="${cart.count}" pattern="#,###" /></td>
                        <td><fmt:formatNumber value="${cart.price}" pattern="#,###" /></td>
                        <td>${cart.discount}%</td>
                        <td><fmt:formatNumber value="${cart.point}" pattern="#,###" /></td>
                        <c:if test="${cart.delivery ne 0}">
                        <td><fmt:formatNumber value="${cart.delivery}" pattern="#,###" /></td>
                        </c:if>
                        <c:if test="${cart.delivery eq 0}">
                        <td class="free-delivery"></td>
                        </c:if>
                        <td><fmt:formatNumber value="${cart.total}" pattern="#,###" /></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="button" name="del" value="선택삭제">
            <!-- 장바구니 전체합계 -->
            <div class="total">
                <h2>전체합계</h2>
                <table border="0">
                    <tbody>
                        <tr>
                            <td>상품수</td>
                            <td class="selectTotalCount">1</td>
                        </tr>
                        <tr>
                            <td>상품금액</td>
                            <td class="selectTotal">27000</td>
                        </tr>
                        <tr>
                            <td>할인금액</td>
                            <td>-1000</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td>0</td>
                        </tr>
                        <tr>
                            <td>포인트</td>
                            <td>260</td>
                        </tr>
                        <tr>
                            <td>전체주문금액</td>
                            <td>26000</td>
                        </tr>
                    </tbody>
                </table>
                <input type="submit" name value="주문하기">
            </div>
        </form>
    </section>
    <!-- 장바구니 페이지 끝 -->
</main>
<%@ include file="./_footer.jsp" %>