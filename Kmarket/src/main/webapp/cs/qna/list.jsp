<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideQna.jsp"/>
						<article>
								<nav>
									<h1>${qna_name_dis.cate1_name}</h1>
									<h2>${qna_name_dis.cate1_discription}</h2>
								</nav>
						<table>
						<c:forEach var="article_qna_list" items="${articles_qna_lists}">
							<c:if test="${article_qna_list.group == 3}">
								<tr>
									<td><a href="./view.do?no=${article_qna_list.no}">${article_qna_list.title}</a></td>
									<td>${article_qna_list.maskingUid}</td>
									<td>${article_qna_list.rdate}</td>
								</tr>
							</c:if>
						</c:forEach>
						</table>
						
						
						<div class="page">
							<c:if test="${pageGroupStart > 1}">
								<a href="/Kmarket/qna/list.do?cate1=${cate1}&pg=${pageGroupStart - 1}&search=${search}" class="prev">이전</a>
							</c:if>
							<c:forEach var="i" begin="${pageGorupStart}" end="${pageGroupEnd}">
								<a href="/Kmarket/qna/list.do?cate1=${cate1}&pg=${i}&${search}" class="num on ${currentPage == i?'current':'off'}">${i}</a>
							</c:forEach>
							<c:if test="${pageGorupEnd < lastPageNum}">
								<a href="/Kmarket/qna/list.do?cate1=${cate1}&pg=${pageGroupEnd + 1}&search=${search}" class="next">다음</a>
							</c:if>
						</div>
							<a href="./qna_write.do" class="btnWrite">문의하기</a>
						</article>
					</section>
				</div>
			</section>
<%@ include file = "../_footer.jsp" %>