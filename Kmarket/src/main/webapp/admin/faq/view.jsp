<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>


</script>
<main>
    <%@ include file="../_aside.jsp" %>
    <section id="admin-cs-view">
	     <nav>
	            <h3>자주묻는질문</h3>
	            <p>
	                HOME > 고객센터 > <strong>자주묻는질문</strong>
	            </p>
	        </nav>
	    <article>
			<table class="cstable">
				<tr class="cstable1">
					<td>유형</td>
					<td>${faq.cate1_name}-${faq.cate2_name}</td>
				</tr>
				<tr class="cstable2">
					<td>제목</td>
					<td>${faq.title}</td>
				</tr>
				<tr class="cstable3">
					<td>내용</td>
					<td style="white-space: pre-wrap;">${faq.content}</td>
				</tr>
			</table>
            <a href="${ctxPath}/admin/faq/delete.do?no=${faq.no}" class="btnViewDelete">삭제</a>
            <a href="${ctxPath}/admin/faq/modify.do?no=${faq.no}" class="btnViewModify">질문수정</a>
            <a href="${ctxPath}/admin/faq/list.do?group=2&pg=1" class="btnList">목록</a>
		</article>
	</section>
</main>	
<%@ include file="../_footer.jsp" %>