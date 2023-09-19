<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideNotice.jsp"/>
	<article>
		<nav>
			<h1>${article_notice_list.cate1_name}</h1>
			<h2>${article_notice_list.cate1_discription}</h2>
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
				<a href="/Kmarket/qna/list.do?pg=${pageGroupStart - 1}&search=${search}" class="prev">이전</a>
			</c:if>
			<c:forEach var="i" begin="${pageGorupStart}" end="${pageGroupEnd}">
				<a href="/Kmarket/qna/list.do?pg=${i}&${search}" class="num ${currentPage == i?'current':'off'}">${i}</a>
			</c:forEach>
			<c:if test="${pageGorupEnd < lastPageNum}">
				<a href="/Kmarket/qna/list.do?pg=${pageGroupEnd + 1}&search=${search}" class="next">다음</a>
			</c:if>
		</div>
	</article>
	</section>
	</div>
</section>
	
	
<%@ include file = "../_footer.jsp" %>