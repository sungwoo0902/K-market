<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<jsp:include page="../_asideQna.jsp"/>
<script>
	$(function() {
		
		// 2차 상세 유형 불러오기
		const cate2 = $('#boardCate2');
		const cate3 = $('#boardCate3');
		let selectCate = null;
		
		$(cate2).change(function() {
			const selectedCate2 = $(this).val();
			
			console.log(selectedCate2);
			
			const jsonData = {
				"type": "json", 
				"jsonCate2": selectedCate2 
			}
			
			$.ajax({
				url: './modify.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data) {
					// 소분류 초기화
					cate3.empty();
					cate3.append($('<option>', {
						value: '0',
						text: '2차 선택'
					}));
					
					// 소분류 동적처리
					for(let i=0 ; i<data.categorys.length ; i++) {
						const category = data.categorys[i];
						
						console.log('category : ' + category);
						
						cate3.append($('<option>', {
							value: category.cate2,
							text: category.cate2_name
						}));
					}
				}
			});
		});
		
		// 상세유형 선택 안 할 시 insert 진행 막기
		$('.btnSubmit').click(function(e) {
			e.preventDefault();
			if(cate2.val() < 1) {
				alert('1차 상세유형을 선택해주세요.');
				return false;
			}
			
			if(cate3.val() < 1) {
				alert('2차 상세유형을 선택해주세요.');
				return false;
			}
			
			$('#modifySubmit').submit();
		})
	});
</script>
						<article class="wrt">
							<form action="#" method="post" id="modifySubmit">
								<input type="hidden" name="no" value="${qna.no}">
								<input type="hidden" name="type" value="modify">
								<table>
									<tr>
										<td>문의유형</td>
										<td>
											<select name="boardCate2" id="boardCate2">
												<option value="0">1차 선택</option>
												<c:forEach var="main_cate" items="${cate1List}">
													<option value="${main_cate.cate1}" ${cate1 eq main_cate.cate1?'selected':''}>${main_cate.cate1_name}</option>
												</c:forEach>
											</select>
											<select name="boardCate3" id="boardCate3">
												<option value="0">2차 선택</option>
												<c:forEach var="sub_cate" items="${cate2List}">
													<option value="${sub_cate.cate2}" ${cate2 eq sub_cate.cate2?'selected':''}>${sub_cate.cate2_name}</option>
												</c:forEach>
											</select>
										</td>
									</tr>
									<tr>
										<td>문의제목</td>                  
										<td><input type="text" name="title" value="${qna.title}"/></td>
									</tr>                
									<tr>
										<td>문의내용</td>                  
										<td><textarea name="content" placeholder="내용을 입력하세요.">${qna.content}</textarea></td>
									</tr>
								</table>
								<div>
									<input type="submit" class="btnSubmit" value="수정하기"/>
									<a href="${ctxPath}/cs/qna/view.do?cate1=${cate1}&no=${no}" class="btnList">취소하기</a>
								</div>
							</form>
						</article>
					</section>
				</div>
			</section>
<%@ include file="../_footer.jsp" %>