<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>


</script>
<main>
    <%@ include file="../_aside.jsp" %>
    <section id="admin-cs-view">
	     <nav>
	            <h3>문의사항</h3>
	            <p>
	                HOME > 고객센터 > <strong>문의사항</strong>
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
					<td>${faq.content}<br/><br/>
					※ 피싱 관련 피해신고<br/><br/>
					▶ 경찰청 사이버수사국 (국번없이)182 :
					http://cyberbureau.police.go.kr<br/>
					▶ KISA 인터넷침해대응센터 (국번없이)118 :
					http://www.krcert.or.kr<br/>
					감사합니다.<br/></td>
				</tr>
			</table>
				<input type="button" value="삭제" class="btnViewDelete"/>
	            <input type="button" value="답변작성" class="btnViewModify"/>
	            <input type="button" value="목록" class="btnList"/>
		</article>
	</section>
</main>	
<%@ include file="../_footer.jsp" %>