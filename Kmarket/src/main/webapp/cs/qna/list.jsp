<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideQna.jsp"/>
						<article>
							<nav>
								<h1>${cate.cate1_name}</h1>
								<h2>${cate.cate1_discription}</h2>
							</nav>
							<table>
							<c:forEach var="qna_list" items="${qna_lists}">
								<c:if test="${qna_list.group == 3}">
									<tr>
										<td><a href="./view.do?cate1=${qna_list.cate1}&no=${qna_list.no}" class="btnQna">[${qna_list.cate2_name}] <c:out value="${qna_list.title}"/></a></td>
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
							<c:if test="${qna_lists.size() < 1}">
								<tr>
									<td style="text-align: center; font-size: 14px; padding: 50px 0;">
										<i class="fa-regular fa-face-sad-tear" style="color: #2eb275; font-size: 100px;"></i><br><br>
										등록된 문의 내용이 없습니다.
									</td>
								</tr>
							</c:if>
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
							<c:if test="${qna_lists.size() < 1}">
							<div class="page" style="font-size: 14px; height: 34px;">
							</div>
							</c:if>
							<c:if test="${sessUser ne null}">
							<a href="${ctxPath}/cs/qna/write.do?cate1=${cate1}" class="btnWrite">문의하기</a>
							</c:if>
						</article>
					</section>
				</div>
			</section>
<%@ include file = "../_footer.jsp" %>