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
				$(this).find('a').text('간단히 보기');
			}else{
				item.slideUp(100);
				$(this).find('a').text('더보기');
			}
		});
	});
</script>
	<article>
		<nav>
			<h1>${cate.cate1_name}</h1>
			<h2>${cate.cate1_discription}</h2>
		</nav>
		<c:forEach var="cate" items="${cate2List}" varStatus="status">
		    <div>
		        <h3>${cate.cate2_name}</h3>
		        <ul>
		            <c:forEach var="faq" items="${faq_lists}">
		                <c:if test="${faq.cate2 eq status.count}">
		                    <li><a href="./view.do?cate1=${faq.cate1}&no=${faq.no}"><span>Q.</span><c:out value="${faq.title}"/></a></li>
		                </c:if>
		            </c:forEach>
		            <li class="more"><a href="#">더보기</a></li>
		        </ul>
		    </div>
		</c:forEach>

	</article>
</section>
</div>
</section>
	
<%@ include file = "../_footer.jsp" %>