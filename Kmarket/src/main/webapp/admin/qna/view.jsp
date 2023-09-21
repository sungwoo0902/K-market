<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>


</script>
<main>
    <%@ include file="../_aside.jsp" %>
    <article id="admin-notice-view">
		<nav>
			<h2 class="title">${qna.title}</h2>
			<span class="date">${qna.fullRdate}</span>
		</nav>
		
		<div class="content">
			<p>
				${qna.content}<br/><br/>
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
			<input type="button" value="삭제" class="btnViewDelete"/>
            <input type="button" value="수정" class="btnViewModify"/>
            <input type="button" value="목록" class="btnList"/>
	</article>
</main>		
<%@ include file="../_footer.jsp" %>