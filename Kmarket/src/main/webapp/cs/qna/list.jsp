<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideQna.jsp"/>
						<article>
								<nav>
									<h1>${qna_name_dis.cate1_name}</h1>
									<h2>${qna_name_dis.cate1_discription}</h2>
								</nav>
						<table>
						<c:forEach var="qna_list" items="${qna_lists}">
							<c:if test="${qna_list.group == 3}">
								<tr>
									<td><a href="./view.do?cate1=${qna_list.cate1}&no=${qna_list.no}">[${qna_list.cate2_name}] ${qna_list.title}</a></td>
									<c:if test="${qna_list.answer eq 0}">
										<td style="font-size: 14px; color: grey;">검토중</td>
									</c:if>
									<c:if test="${qna_list.answer ne 0}">
										<td style="font-size: 14px; color: green;">답변완료</td>
									</c:if>
									<td>${qna_list.maskingUid}</td>
									<td>${qna_list.rdate}</td>
								</tr>
							</c:if>
						</c:forEach>
						</table>
						
						
						<div class="page">
						    <c:if test="${pageGroupStart > 1}">
						        <a href="${ctxPath}/cs/qna/list.do?cate1=${notice_list.cate1}&pg=${pageGroupStart - 1}" class="prev">이전</a>
						    </c:if>
						    <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
						        <c:choose>
						            <c:when test="${currentPage == i}">
						                <a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}&pg=${i}" class="num on current">${i}</a>
						            </c:when>
						            <c:otherwise>
						                <a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}&pg=${i}" class="num">${i}</a>
						            </c:otherwise>
						        </c:choose>
						    </c:forEach>
						    <c:if test="${pageGroupEnd < lastPageNum}">
						        <a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}&pg=${pageGroupEnd + 1}" class="next">다음</a>
						    </c:if>
						</div>
							<a href="${ctxPath}/cs/qna/write.do?cate1=${cate1}" class="btnWrite">문의하기</a>
						</article>
					</section>
				</div>
			</section>
<%@ include file = "../_footer.jsp" %>