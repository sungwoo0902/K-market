<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<main>
	<%@ include file="../_aside.jsp" %>
    <section id="admin-cs-register">
        <nav>
            <h3>공지사항</h3>
            <p>
                HOME > 고객센터 > <strong>공지사항</strong>
            </p>
        </nav>
        <article>
            <form action="${ctxPath}/admin/notice/modify.do" method="post">
			<input type="hidden" name="no" value="${modify.no}"/>
                <section>
                    <table>
                    	<tr>
							<td>공지유형</td>
							<td>
								<!-- type1은 회원게시판에서 클릭했을 시 회원으로 설정되게끔. -->
								<select name="boardCate2">
									<option value="0">유형선택</option>
									<c:forEach var="main_cate" items="${cate1List}">
										<option value="${main_cate.cate1}" ${cate1 eq main_cate.cate1?'selected':''}>${main_cate.cate1_name}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
                    </table>
                </section>
                <section>
                    <table>
                        <tr>
                            <td>제목</td>
                            <td><textarea class="csTitle" name="title">${modify.title}</textarea></td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td><textarea class="csArea" name="content">${modify.content}</textarea></td>
                        </tr>
                    </table>
                </section>
                <input type="submit" value="수정하기" class="csModify">           
            </form>
            	<a href="${ctxPath}/admin/notice/view.do?no=${modify.no}" class="csCancle">취소</a>
        </article>
    </section>
</main>
<%@ include file="../_footer.jsp" %>