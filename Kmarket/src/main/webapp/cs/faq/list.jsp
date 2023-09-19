<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideFaq.jsp"/>
<script>
	$(function() {
		$('.more').click(function(e){
			e.preventDefault();
			
			let item = $(this).parent().find('> li:nth-child(n+4)');
			let displayStatus = item.css('display');
			
			if(displayStatus == 'none'){
				item.slideDown(100);
				$(this).text('간단히 보기');
			}else{
				item.slideUp(100);
				$(this).text('더보기');
			}
		});
		
		// 스크롤링 기능 추가
		$('h3 a').click(function(e){
			e.preventDefault();
			let targetId = $(this).attr('href');
            let targetOffset = $(targetId).offset().top;
            $('html, body').animate({ scrollTop: targetOffset }, 500);
        });
	});
</script>
	<article>
		<c:forEach var="article_faq_list" items="${articles_faq_lists}">              
			<nav>
				<h1>${article_faq_list.cate1_name}</h1>
				<h2>${article_faq_list.cate1_discription}</h2>
			<c:forEach var = "article_faq_list" items="${articles_faq_lists}">
			<div>
				<h3>${article_faq_list.cate2_name}</h3>
				<ul>
					<li><a href="./view.do?no=${article_faq_list.no}"><span>Q.</span>${article_faq_list.title}</a></li>
					<li class="more"><a href="#">더보기</a></li>
				</ul>
			</div>
			</c:forEach>
			</nav>
		</c:forEach>
	</article>
</section>
</div>
</section>
	
<%@ include file = "../_footer.jsp" %>