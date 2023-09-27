<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideFaq.jsp"/>
						<article>
							<nav>
								<h2 class="title"><span>Q.</span><c:out value="${faq.title}"/></a></h2>                
							</nav>
							
							<div class="content" style="margin-top:-106px">
								<p style="white-space: pre-wrap;">
									<br><c:out value="${faq.content}" escapeXml="true"/>
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
								<a href="${ctxPath}/cs/faq/list.do?cate1=${cate1}" class="btnList">목록보기</a>
							</div>
						</article>
					</section>
				</div>
			</section>
<%@ include file="../_footer.jsp" %>