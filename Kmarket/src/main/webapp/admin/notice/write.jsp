<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
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
			url: '${ctxPath}/admin/notice/write.do',
			type: 'POST',
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
		
		$('.noticeRegister').submit();
	})
});
</script>
<main>
	<%@ include file="../_aside.jsp" %>
    <section id="admin-notice-register">
        <nav>
            <h3>공지사항</h3>
            <p>
                HOME > 고객센터 > <strong>공지사항</strong>
            </p>
        </nav>
        <article>
            <form action="${ctxPath}/admin/notice/write.do" method="post">
            <input type="hidden" name="type" value="write"/>
			<input type="hidden" name="uid" value="${sessUser.uid}"/>
                <section>
                    <table>
                    	<tr>
							<td>공지유형</td>
							<td>
								<!-- type1은 회원게시판에서 클릭했을 시 회원으로 설정되게끔. -->
								<select name="boardCate2" id="boardCate2">
									<option value="0">1차 선택</option>
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
                            <td><textarea class="csTitle" name="title" placeholder="제목을 입력하세요"></textarea></td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td><textarea class="csArea" name="content" placeholder="내용을 입력하세요."></textarea></td>
                        </tr>
                    </table>
                </section>
                <input type="submit" value="등록하기" class="csRegister">
            </form>
        </article>
    </section>
</main>
<%@ include file="../_footer.jsp" %>