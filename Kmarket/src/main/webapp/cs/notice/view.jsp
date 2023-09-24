<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideNotice.jsp"/>
						<article>
							<nav>
								<h2 class="title">${notice.title}</h2>
								<span class="date">${notice.fullRdate}</span>
							</nav>
							
							<div class="content">
								<p>
									<c:if test="${cate1 eq 1}">[${cate.cate1_name}]</c:if>
									${notice.content}<br/><br/>
								</p>
								<p>
									※ 피싱 관련 피해신고<br/><br/>
									▶ 경찰청 사이버수사국 (국번없이)182 :
									http://cyberbureau.police.go.kr<br/>
									▶ KISA 인터넷침해대응센터 (국번없이)118 :
									http://www.krcert.or.kr<br/>
									감사합니다.<br/>
								</p>
							</div>
							<div>
								<a href="${ctxPath}/cs/notice/list.do?cate1=${cate1}" class="btnList">목록보기</a>
							</div>
						</article>
					</section>
				</div>
			</section>
<%@ include file="../_footer.jsp" %>