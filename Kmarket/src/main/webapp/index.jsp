<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="./_header.jsp" %>
<script>
	$(function() {
		
		if(${success eq '102'}) {
			alert('[경고] 비정상적인 접근')
			
		}else if(${success eq '104'}) {
			alert('서비스 이용 권한이 없습니다.')
			
		}else if(${success eq '200'}){
			alert('로그아웃 되었습니다.');
		}
	});
</script>
	<%@ include file="./_toolBar.jsp" %>
        <main>
            <!-- 카테고리/베스트 상품 영역 -->
            <aside>
                <!-- 카테고리 -->
                <ul class="category">
	                <li>
	                	<i class="fa fa-bars" aria-hidden="true"></i>카테고리
	                </li>
	                <li>
	                	<a href="${ctxPath}/product/list.do">
	                		<span><i class="fas fa-shop"></i></span>전체
	                	</a>
	                </li>
	                <c:forEach var="ct1" items="${category1}">
                	<li>
	                    <a href="${ctxPath}/product/list.do?cate1=${ct1.cate1No}">
		                    <span><i class="${ct1.cate1Icon}"></i></span>${ct1.c1Name}
		                    <i class="fas fa-angle-right"></i>
	                    </a>
	                    <ol>
	                    	<c:forEach var="acate" items="${allCate}">
	                    		<c:if test="${acate.cate1No eq ct1.cate1No}">
			                    <li><a href="${ctxPath}/product/list.do?cate1=${acate.cate1No}&cate2=${acate.cate2No}">${acate.c2Name}</a></li>
			                    </c:if>
		                    </c:forEach>
	                    </ol>
	                </li>
	                </c:forEach>
	                
                </ul>

                <!-- 베스트상품 배너 -->
                <article class="best" style="position: static; top: 0px;">
                    <h1><i class="fas fa-crown"></i>베스트상품</h1>
                    <ol>
                    	<c:forEach var="item" items="${bestItems}" varStatus="status">
                        <li>
	                        <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">
	                            <div class="thumb">
		                            <i>${status.count}</i>
		                            <img src="${ctxPath}/thumb/${item.prodCate1}/${item.prodCate2}/${item.thumb2}" alt="${item.prodName}" />
	                            </div>
	                            <h2>${item.prodName }</h2>
	                            <div class="org_price">
		                            <del>${item.priceWithComma}</del>
		                            <span>${item.discount}%</span>
	                            </div>
	                            <div class="dis_price">
	                            	<ins>${item.disPriceWithComma}</ins>
	                            </div>
	                        </a>
                        </li>
                        </c:forEach>
                    </ol>
                </article>
            </aside>
            <section>
                <!-- 슬라이더 영역 -->
                <section class="slider">
                <ul>
                    <li>
                        <a href="#"><img src="${ctxPath}/images/slider_item1.jpg" alt="item1"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="${ctxPath}/images/slider_item2.jpg" alt="item2"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="${ctxPath}/images/slider_item3.jpg" alt="item3"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="${ctxPath}/images/slider_item4.jpg" alt="item4"/></a>
                    </li>
                    <li>
                        <a href="#"><img src="${ctxPath}/images/slider_item5.jpg" alt="item5"/></a>
                    </li>
                </ul>
                </section>
                <!-- 히트상품 영역 -->
                <section class="hit">
	                <h3><span>히트상품</span></h3>
	                <c:forEach var="item" items="${hitItems}">
	                <article>
	                    <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">
	                    <div class="thumb">
	                        <img class="pop" src="${ctxPath}/thumb/${item.prodCate1}/${item.prodCate2}/${item.thumb2}" alt="${item.prodName}" />
	                    </div>
	                    <h2>${item.prodName}</h2>
	                    <p>${item.descript}</p>
	                    <div class="org_price">
	                    	<c:if test="${item.discount ne 0 }">
	                        <del>${item.priceWithComma}</del>
	                        <span>${item.discount}%</span>
	                        </c:if>
	                    </div>
	                    <div class="dis_price">
	                        <ins>${item.disPriceWithComma}</ins>
	                        <c:choose>
		                        <c:when test="${item.delivery eq 0}">
		                        	<span class="free">무료배송</span>
		                        </c:when>
		                        <c:otherwise>
		                        	<span>배송비 ${item.deliveryWithComma}</span>
		                        </c:otherwise>
	                        </c:choose>
	                    </div>
	                    </a>
	                </article>
	                </c:forEach>
                </section>
                <!-- 추천상품 영역 -->
                <section class="recommend">
                <h3><span>추천상품</span></h3>
                	<c:forEach var="item" items="${rcmdItems}">
	                <article>
	                    <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">
	                    <div class="thumb">
	                        <img class="pop" src="${ctxPath}/thumb/${item.prodCate1}/${item.prodCate2}/${item.thumb2}" alt="t1" />
	                    </div>
	                    <h2>${item.prodName}</h2>
	                    <p>${item.descript}</p>
	                    <div class="org_price">
	                        <c:if test="${item.discount ne 0 }">
	                        <del>${item.priceWithComma}</del>
	                        <span>${item.discount}%</span>
	                        </c:if>
	                    </div>
	                    <div class="dis_price">
	                        <ins>${item.disPriceWithComma}</ins>
	                        <c:choose>
		                        <c:when test="${item.delivery eq 0}">
		                        	<span class="free">무료배송</span>
		                        </c:when>
		                        <c:otherwise>
		                        	<span>배송비 ${item.deliveryWithComma}</span>
		                        </c:otherwise>
	                        </c:choose>
	                    </div>
	                    </a>
	                </article>
                	</c:forEach>
                </section>
                
                
                <!-- 최신상품 영역 -->
                <section class="new">
                <h3><span>최신상품</span></h3>
	                <c:forEach var="item" items="${currentItems}">
	                <article>
	                    <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">
	                    <div class="thumb">
	                        <img class="pop" src="${ctxPath}/thumb/${item.prodCate1}/${item.prodCate2}/${item.thumb2}" alt="t1" />
	                    </div>
	                    <h2>${item.prodName}</h2>
	                    <p>${item.descript}</p>
	                    <div class="org_price">
	                        <c:if test="${item.discount ne 0 }">
	                        <del>${item.priceWithComma}</del>
	                        <span>${item.discount}%</span>
	                        </c:if>
	                    </div>
	                    <div class="dis_price">
	                        <ins>${item.disPriceWithComma}</ins>
	                        <c:choose>
		                        <c:when test="${item.delivery eq 0}">
		                        	<span class="free">무료배송</span>
		                        </c:when>
		                        <c:otherwise>
		                        	<span>배송비 ${item.deliveryWithComma}</span>
		                        </c:otherwise>
	                        </c:choose>
	                    </div>
	                    </a>
	                </article>
                	</c:forEach>
                </section>
                <!-- 할인상품 영역 -->
                <section class="discount">
                <h3><span>할인상품</span></h3>
	                <c:forEach var="item" items="${discntItems}">
	                <article>
	                    <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">
	                    <div class="thumb">
	                        <img class="pop" src="${ctxPath}/thumb/${item.prodCate1}/${item.prodCate2}/${item.thumb2}" alt="t1" />
	                    </div>
	                    <h2>${item.prodName}</h2>
	                    <p>${item.descript}</p>
	                    <div class="org_price">
	                        <c:if test="${item.discount ne 0 }">
	                        <del>${item.priceWithComma}</del>
	                        <span>${item.discount}%</span>
	                        </c:if>
	                    </div>
	                    <div class="dis_price">
	                        <ins>${item.disPriceWithComma}</ins>
	                        <c:choose>
		                        <c:when test="${item.delivery eq 0}">
		                        	<span class="free">무료배송</span>
		                        </c:when>
		                        <c:otherwise>
		                        	<span>배송비 ${item.deliveryWithComma}</span>
		                        </c:otherwise>
	                        </c:choose>
	                    </div>
	                    </a>
	                </article>
                	</c:forEach>
                </section>
            </section>
        </main>
<%@ include file="./_footer.jsp" %>