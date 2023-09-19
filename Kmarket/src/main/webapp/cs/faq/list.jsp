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
			  
			}else{
				item.slideUp(100);
			}
		});
	})
</script>
		<article>
			<c:forEach var="board1" items="${boards1}">              
				<nav>
					<h1>${board1.cate1_name}</h1>
					<h2>${board1.cate1_discription}</h2>
				</nav>
			</c:forEach>
			<c:forEach var = "board2" items=${boards2}>
				<c:if test="${board2.group == 2}">
				<div>
					<h3>${board2.cate2_name}</h3>
					<ul>
						<li><a href="./view.do?no=${board2.no}"><span>Q.</span>${board2.title}</a></li>
						<li class="more"><a href="#">더보기</a></li>
					</ul>
				</div>
				</c:if>
			</c:forEach>
			<c:forEach var = "board2" items=${boards2}>
				<c:if test="${board2.group == 2}">
				<div>
					<h3>${board2.cate2_name}</h3>
					<ul>
						<li><a href="./view.do?no=${board2.no}"><span>Q.</span>${board2.title}</a></li>
						<li class="more"><a href="#">더보기</a></li>
					</ul>
				</div>
				</c:if>
			</c:forEach>
			<c:forEach var = "board2" items=${boards2}>
				<c:if test="${board2.group == 2}">
				<div>
					<h3>${board2.cate2_name}</h3>
					<ul>
						<li><a href="./view.do?no=${board2.no}"><span>Q.</span>${board2.title}</a></li>
						<li class="more"><a href="#">더보기</a></li>
					</ul>
				</div>
				</c:if>
			</c:forEach>
			<c:forEach var = "board2" items=${boards2}>
				<c:if test="${board2.group == 2}">
				<div>
					<h3>${board2.cate2_name}</h3>
					<ul>
						<li><a href="./view.do?no=${board2.no}"><span>Q.</span>${board2.title}</a></li>
						<li class="more"><a href="#">더보기</a></li>
					</ul>
				</div>
				</c:if>
			</c:forEach>
		</article>
		</section>
	</div>
</section>
<%@ include file = "../_footer.jsp" %>