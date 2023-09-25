<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
</script>
<%@ include file="../_toolBar.jsp" %>
<main id="product">
    <%@ include file="./_aside.jsp" %>
    <section class="list">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>상품목록</h1>
            <!-- 처리해야됨 -->
            <p>
                ${(cate eq null)?'전체보기':'HOME >'} 
                <span>${cate.c1Name}</span>
                <c:if test="${cate.c2Name ne null}">></c:if> 
                <strong>${cate.c2Name}</strong>
            </p>
        </nav>
        <!-- 정렬 메뉴 -->
        <ul class="sort">
            <li>
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=1" class="${type eq '1'?'on':'off'}">판매많은순</a>
            </li>
            <li>
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=2" class="${type eq '2'?'on':'off'}">낮은가격순</a>
            </li>
            <li>
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=3" class="${type eq '3'?'on':'off'}">높은가격순</a>
            </li>
            <li>
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=4" class="${type eq '4'?'on':'off'}">평점높은순</a>
            </li>
            <li>
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=5" class="${type eq '5'?'on':'off'}">후기많은순</a>
            </li>
            <li>
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=6" class="${type eq '6'?'on':'off'}">최근등록순</a>
            </li>
        </ul>
        <!-- 상품목록 -->
        <table border="0">
            <tbody>
            	<c:forEach var="prod" items="${products}">
                <tr>
                    <td>
                        <a href="${ctxPath}/product/view.do?cate1=${prod.prodCate1}&cate2=${prod.prodCate2}&prodNo=${prod.prodNo}" class="thumb">
                            <img src="${ctxPath}/thumb/${prod.prodCate1}/${prod.prodCate2}/${prod.thumb2}" alt="상품이미지">
                        </a>
                    </td>
                    <td>
                        <h3 class="name"><a href="${ctxPath}/product/view.do?cate1=${cate1}&cate2=${cate2}&prodNo=${prod.prodNo}">${prod.prodName}</a></h3>
                        <a href="#" class="desc">${prod.descript}</a>
                    </td>
                    <td>
                        <ul>
                            <li>
                                <ins class="dis-price">
                                    <fmt:formatNumber value="${prod.disPrice}" pattern="#,###" />
                                </ins>
                            </li>
                            
                            <!-- 할인율이 있다면 할인표시를 띄우고 아니라면 정가의 할인 표시 띄우기 -->
                            <c:if test="${prod.discount ne 0}">
                            <li>
                                <del class="org-price">
                                    <fmt:formatNumber value="${prod.price}" pattern="#,###" />
                                </del>
                                <span class="discount">
                                    ${prod.discount}%
                                </span>
                            </li>
                            </c:if>
                            
                            <!-- 배송비도 무료배송이 아니라면 정상적으로 나오게끔 수정 예정 -->
                            <li>
                            	<c:if test="${prod.delivery eq 0}">
                                <span class="free-delivery"></span>
                                </c:if>
                                <c:if test="${prod.delivery ne 0}">
                                <span class="free-delivery has-delivery">배송비 <fmt:formatNumber value="${prod.delivery}" pattern="#,###" />원</span>
                                </c:if>
                            </li>
                        </ul>
                    </td>
                    <td>
                        <h4 class="seller">
                            <i class="fas fa-home" aria-hidden="true">
                            </i>
                            &nbsp;${prod.company}
                        </h4>
                        <!-- 나중에 등급별로 다르게 나오게끔 수정 예정 -->
                        <h5 class="badge ${prod.level eq 4 ? 'power':(prod.level eq 5?'great':(prod.level eq 6 ?'power great':''))}">배경지키기</h5>
                        <h6 class="rating star${prod.score}">상품평${(prod.score eq 0)?'이 없습니다.':''}</h6>
                    </td>
                </tr>
                </c:forEach>
                <c:if test="${products.size() < 1}">
                	<div class="prodListNull">
	                	<i class="fas fa-store-slash"></i><br><br>
	                	등록된 상품이 없습니다.
                	</div>
                </c:if>
            </tbody>
        </table>
        <!-- 상품목록 페이지번호 -->
        <div class="paging">
        <c:if test="${pageGroupStart > 1}">
            <span class="prev">
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=${type}&pg=${pageGroupStart - 1}">〈&nbsp;이전</a>
            </span>
        </c:if>
        <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
            <span class="num">
            <!-- 대분류 소분류 구분 -->
            	<c:if test="${cate2 ne null}">
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=${type}&pg=${i}" class="num ${currentPage == i?'on':'off'}">${i}</a>
            	</c:if>
            	<c:if test="${cate2 eq null}">
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&type=${type}&pg=${i}" class="num ${currentPage == i?'on':'off'}">${i}</a>
            	</c:if>
            </span>
        </c:forEach>
        <c:if test="${pageGroupEnd < lastPageNum}">
            <span class="next">
                <a href="${ctxPath}/product/list.do?cate1=${cate1}&cate2=${cate2}&type=${type}&pg=${pageGroupEnd + 1}">다음&nbsp;〉</a>
            </span>
        </c:if>
        </div>
    </section>
</main>
<%@ include file="./_footer.jsp" %>