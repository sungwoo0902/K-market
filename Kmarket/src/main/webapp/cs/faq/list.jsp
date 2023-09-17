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
							<nav>
								<h1>회원</h1>
								<h2>가장 자주 묻는 질문입니다.</h2>
							</nav>
							<div>
								<h3>가입</h3>
								<ul>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 1</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 2</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 3</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 4</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 5</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 6</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 7</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 8</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 9</a></li>
									<li><a href="./view.jsp"><span>Q.</span>개인회원과 법인회원에 차이가 있나요? 10</a></li>
									<li class="more"><a href="#">더보기</a></li>
								</ul>
							</div>
							<div>
								<h3>탈퇴</h3>
								<ul>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 1</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 2</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 3</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 4</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 5</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 6</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 7</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 8</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 9</a></li>
									<li><a href="#"><span>Q.</span>회원탈퇴 후 재가입이 가능한가요? 10</a></li>
									<li class="more"><a href="#">더보기</a></li>
								</ul>
							</div>
							<div>
								<h3>회원정보</h3>
								<ul>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 1</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 2</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 3</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 4</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 5</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 6</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 7</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 8</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 9</a></li>
									<li><a href="#"><span>Q.</span>회원정보를 수정하고 싶어요. 10</a></li>
									<li class="more"><a href="#">더보기</a></li>
								</ul>
							</div>
							<div>
								<h3>로그인</h3>
								<ul>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 1</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 2</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 3</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 4</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 5</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 6</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 7</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 8</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 9</a></li>
									<li><a href="#"><span>Q.</span>로그인에 문제가 있어요. 10</a></li>
									<li class="more"><a href="#">더보기</a></li>
								</ul>
							</div>
						</article>
					</section>
				</div>
			</section>
<%@ include file = "../_footer.jsp" %>