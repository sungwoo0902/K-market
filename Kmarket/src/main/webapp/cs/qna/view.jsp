<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideQna.jsp"/>
						<article>
							<nav>
								<h2 class="title">[${qna.cate3_name}] ${qna.title}</h2>
								<p>
									<span>아이디 마스킹 처리해야함</span>
									<span>${qna.rDate}</span>
								</p>
							</nav>
							
							<div class="content">
								<p>
									${qna.title}
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
							<a href="${ctxPath}/cs/qna/list.do?cate1=${cate1}" class="btnList">목록보기</a>
						</article>
					</section>
				</div>
			</section>
<%@ include file="../_footer.jsp" %>