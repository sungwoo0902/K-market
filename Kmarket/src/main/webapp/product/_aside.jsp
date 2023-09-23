<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 공통 aside 시작 -->
    <aside>
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
    </aside>
    <!-- 공통 aside 끝 -->