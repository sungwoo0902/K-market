<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="./_aside${group}.jsp"/>
				<article>
					<nav>
						<h1>회원</h1>
						<h2>회원관련 문의 내용입니다.</h2>
					</nav>
					<table>
						<c:forEach var="board" items="${boards}">
						<tr>
							<td>${pageStartNum = pageStartNum - 1}</td>
							<td><a href="./view.do?no=${article.no}">${article.title}[${article.comment}]</a></td>
							<td>${article.nick}</td>
							<td>${article.rdate}</td>
							<td>${article.hit}</td>
						</tr>
						</c:forEach>
					</table>
					
					
					<div class="page">
					    <c:if test="${pageGroupStart > 1}">
							<a href="/Kmarket/qna_list_cancel_return_exchange.do?pg=${pageGroupStart - 1}&search=${search}" class="prev">이전</a>
						</c:if>
						<c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
							<a href="/Kmarket/qna_list_cancel_return_exchange?pg=${i}&search=${search}" class="num ${currentPage == i?'current':'off'}">${i}</a>
						</c:forEach>
						<c:if test="${pageGroupEnd < lastPageNum}">
							<a href="/Kmarket/qna_list_cancel_return_exchange?pg=${pageGroupEnd + 1}&search=${search}" class="next">다음</a>
						</c:if>
					</div>
					
					<a href="./qna_write.jsp" class="btnWrite">문의하기</a>
				
				</article>
			</section>
		</div>
	</section>
<%@ include file = "../_footer.jsp" %>