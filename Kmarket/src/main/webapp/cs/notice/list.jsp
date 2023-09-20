<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideNotice.jsp"/>
	<article>
		<nav>
			<h1>${notice_name_dis.cate1_name}</h1>
			<h2>${notice_name_dis.cate1_discription}</h2>
		</nav>
		<table>
	<c:forEach var="article_notice_list" items="${articles_notice_lists}">
			<tr>
				<td><a href="./view.do?no=${article_notice_list.no}">${article_notice_list.title}</a></td>
				<td>${article_notice_list.rdate}</td>
			</tr>
	</c:forEach>
		</table>
			
		<div class="page">
			<c:if test="${pageGroupStart > 1}">
				<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}&pg=${pageGroupStart - 1}" class="prev">이전</a>
			</c:if>
			<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
				<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}&pg=${i}&search=${search}" class="num on ${currentPage == i ?'on current':''}">${i}</a>
			</c:forEach>
			<c:if test="${pageGroupEnd < lastPageNum}">
				<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}&pg=${pageGroupEnd + 1}" class="next">다음</a>
			</c:if>
		</div>
	</article>
	</section>
	</div>
</section>
	
	
<%@ include file = "../_footer.jsp" %>