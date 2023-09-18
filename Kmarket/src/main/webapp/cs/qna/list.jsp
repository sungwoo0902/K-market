<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideQna.jsp"/>
						<article>
						<c:forEach var="board1" items="${boards1}">
							<nav>
								<h1>${board1.cate2_name}</h1>
								<h2>${board1.cate2_discription}</h2>
							</nav>
						<table>
						</c:forEach>
						<c:forEach var="board2" items=${boards2}>
							<c:if test="${board2.boardCate == 3}">
								<tr>
									<td><a href="./view.do?no=${board2.no}">${board2.title}</a></td>
									<td>${board2.uid}</td>
									<td>${board2.rDate}</td>
								</tr>
							</c:if>
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
							<a href="./qna_write.do" class="btnWrite">문의하기</a>
						</article>
<%@ include file = "../_footer.jsp" %>