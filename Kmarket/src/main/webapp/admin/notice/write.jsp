<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../_header.jsp" %>
<script>
	$(function() {
		
		// 2차 상세 유형 불러오기
		const cate1 = $('#boardCate1');
		const cate2 = $('#boardCate2');
		let selectCate = null;
		
		$(cate1).change(function() {
			const selectedCate1 = $(this).val();
			
			console.log(selectedCate1);
			
			const jsonData = {
				"type": "json", 
				"jsonCate2": selectedCate1 
			}
			
			$.ajax({
				url: './write.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data) {
					// 소분류 초기화
					cate2.empty();
					cate2.append($('<option>', {
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
			if(cate1.val() < 1) {
				alert('1차 상세유형을 선택해주세요.');
				return false;
			}
			
			if(cate2.val() < 1) {
				alert('2차 상세유형을 선택해주세요.');
				return false;
			}
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
                <section>
                    <table>
                    	<tr>
							<td>공지유형</td>
							<td>
								<!-- type1은 회원게시판에서 클릭했을 시 회원으로 설정되게끔. -->
								<select name="boardCate1" id="boardCate1">
									<option value="0">1차 선택</option>
									<c:forEach var="cate" items="${group}">
										<option value="${cate.group}" ${cate eq group ? 'selected' : ''}>${cate.cate1_name}</option>
									</c:forEach>
								</select>
								<!--  type2는 jsonData로 받아와서 동적처리. -->
								<select name="boardCate2" id="boardCate2">
									<option value="0">2차 선택</option>
									<c:forEach var="sub_cate" items="${sub_cate1}">
										<option value="${cate.cate1}">${cate.cate1_name}</option>
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
                            <td><textarea class="noticeTitle" name="content" placeholder="제목을 입력하세요"></textarea></td>
                        </tr>
                        <tr>
                            <td>내용</td>
                            <td><textarea class="noticeArea" name="content" placeholder="내용을 입력하세요."></textarea></td>
                        </tr>
                    </table>
                </section>
                <input type="submit" value="등록하기">
            </form>
        </article>
    </section>
</main>
<%@ include file="../_footer.jsp" %>