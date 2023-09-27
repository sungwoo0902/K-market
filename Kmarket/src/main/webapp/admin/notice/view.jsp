<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>


</script>
<main>
    <%@ include file="../_aside.jsp" %>
    <section id="admin-cs-view">
	     <nav>
	            <h3>공지사항</h3>
	            <p>
	                HOME > 고객센터 > <strong>공지사항</strong>
	            </p>
	        </nav>
	    <article>
			<table class="cstable">
				<tr class="cstable1">
					<td>유형</td>
					<td>${notice.cate1_name}</td>
				</tr>
				<tr class="cstable2">
					<td>제목</td>
					<td>${notice.title}</td>
				</tr>
				<tr class="cstable3">
					<td>내용</td>
					<td style="white-space: pre-wrap;">${notice.content}</td>
				</tr>
			</table>
            <a href="${ctxPath}/admin/notice/delete.do?no=${notice.no}" class="btnViewDelete">삭제</a>
            <a href="${ctxPath}/admin/notice/modify.do?group=${notice.group}&no=${notice.no}" class="btnViewModify">공지수정</a>
            <a href="${ctxPath}/admin/notice/list.do?group=1&pg=1" class="btnList">목록</a>
		</article>
	</section>
</main>			
<%@ include file="../_footer.jsp" %>