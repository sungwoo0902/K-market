<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideQna.jsp"/>
						<article>
							<nav>
								<h2 class="title">[${qna.cate2_name}] <c:out value="${qna.title}"/></h2>
								<p>
									<span>${qna.maskingUid}</span>
									<span>${qna.rdate}</span>
								</p>
							</nav>
							
							<div class="content" style="margin-top:-106px">
								<p style="white-space: pre-wrap;">
									<br><c:out value="${qna.content}" escapeXml="true"/>
								</p>
							</div>
							<c:if test="${ans ne null}">
							<div class="answer">
								<h2 class="title">☞ [답변] ${ans.title}</h2>
								<p style="white-space: pre-wrap;">
									<c:out value="${ans.content}" escapeXml="true"/>
								</p>
							</div>
							</c:if>
							<div>
								<a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}" class="btnList">목록보기</a>
								<c:if test="${qna.uid eq sessUser.uid}">
								<a href="${ctxPath}/cs/qna/modify.do?cate1=${cate1}&no=${qna.no}" class="btnModi">수정하기</a>
								</c:if>
							</div>
						</article>
					</section>
				</div>
			</section>
<%@ include file="../_footer.jsp" %>