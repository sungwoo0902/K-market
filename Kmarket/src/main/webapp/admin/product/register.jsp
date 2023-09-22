<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<script>
	$(function(){

		const cate1 = $('#category1');
		const cate2 = $('#category2');
		let selectCate = null;
		
		$(cate1).change(function(){
			const selectedCate1 = $(this).val();
			
			console.log(selectedCate1);
			
			$.ajax({
				url: '${ctxPath}/category.do',
				type: 'post',
				data: {category1: selectedCate1},
				dataType: 'json',
				success: function(data){
					console.log(data);
					
					// 기존 옵션 제거
				    cate2.empty();
					
				    console.log('empty complete'+data);
				    
				    // "2차 분류 선택" 옵션 추가
				    cate2.append($('<option>', {
				        value: 'cate0',
				        text: '2차 분류 선택'
				    }));
					
				    console.log('append complete'+data);
				 	// 받아온 JSON 데이터를 기반으로 2차 분류 옵션 추가
				    for (let i = 0; i < data.cate2s.length; i++) {
				        const category = data.cate2s[i];
				        cate2.append($('<option>', {
				            value: category.cate2No,
				            text: category.c2Name
				        }));
				    }
				},
				error: function(error){
					console.error('Error:',error);
				}
			});
			
		});// cate1 click end
		
		
		// 할인율 radio 체크
		const percentRadio = $('#percentRadio');
		const absoluteRadio = $('#absoluteRadio');
		const percentInput = $('#percent');
		const absoluteInput = $('#absolute');
		
		$(percentRadio).change(function(){
			if(percentRadio.is(':checked')){
			 	absoluteRadio.prop('checked', false);
			 	absoluteInput.attr('readonly', true);
		        percentInput.prop('readonly', false).focus();
			}
		})
		
		$(absoluteRadio).change(function(){
			if(absoluteRadio.is(':checked')){
				percentRadio.prop('checked', false);
		        percentInput.attr('readonly', true);
		        absoluteInput.prop('readonly', false).focus();
			}
		})
		// radio 체크 end
		
		// 폼 action 변경
		$('.btnGO').click(function(e) {
			e.preventDefault();
			
			const cate1 = $('#category1').val();
			const cate2 = $('#category2').val();
			
			$('#registerProduct').attr('action', '${ctxPath}/admin/product/register.do?cate1='+cate1+'&cate2='+cate2).submit();
			
		});
		
	});// end

</script>
<main>
	<%@ include file="../_aside.jsp" %>
    <section id="admin-product-register">
        <nav>
            <h3>상품등록</h3>
            <p>
                HOME > 상품관리 > <strong>상품등록</strong>
            </p>
        </nav>
        <article>
            <form action="${ctxPath}/admin/product/register.do" method="post" enctype="multipart/form-data" id="registerProduct">
            	<!-- sessUser 설정 후 적용 -->
            	<input type="hidden" name="seller" value="a12345">
                <section>
                    <h4>상품분류</h4>
                    <p>
                        기본분류는 반드시 선택하셔야 합니다. 하나의 상품에 1개의 분류를 지정 합니다.
                    </p>
                    <table>
                        <tr>
                            <td>1차 분류</td>
                            <td>
                                <select id="category1" name="prodCate1">
                                    <option value="cate0">1차 분류 선택</option>
                                		<c:forEach var="cate1" items="${cate1s}">
	                                    <option value="${cate1.cate1No}">${cate1.c1Name}</option>
                                		</c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>2차 분류</td>
                            <td>
                                <select id="category2" name="prodCate2">
                                <option value="cate0">2차 분류 선택</option>
                                		<c:forEach var="cate2" items="${cate2s}">
	                                    <option value="${cate2.cate2No}">${cate2.c2Name}</option>
                                		</c:forEach>
                                </select>
                            </td>
                        </tr>
                    </table>
                </section>

                <section>
                    <h4>기본정보</h4>
                    <p>
                        기본정보는 반드시 입력해야 합니다.
                    </p>
                    <table>
                        <tr>
                            <td>상품명</td>
                            <td><input type="text" name="prodName"></td>
                        </tr>
                        <tr>
                            <td>기본설명</td>
                            <td>
                                <span>상품명 하단에 상품에 대한 추가적인 설명이 필요한 경우에 입력</span>
                                <input type="text" name="descript">
                            </td>
                        </tr>
                        <tr>
                            <td>제조사</td>
                            <td><input type="text" name="company" required></td>
                        </tr>
                        <tr>
                            <td>판매가격</td>
                            <td><input type="text" name="price" required oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">원</td>
                        </tr>
                        <tr>
                            <td>할인율</td>
                            <td>
                                <span>0을 입력하면 할인율 없음</span>
                                <input type="text" name="discount"  id="absolute" oninput="this.value = Math.min(99, Math.max(0, this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1')));">%                  
                            </td>
                        </tr>
                        <tr>
                            <td>포인트</td>
                            <td>
                                <span>0을 입력하면 포인트 없음</span>
                                <input type="text" name="point" value="1" required oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">%
                            </td>
                        </tr>
                        <tr>
                            <td>재고수량</td>
                            <td><input type="text" name="stock" required oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">개</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td>
                                <span>0을 입력하면 배송비 무료</span>
                                <input type="text" name="delivery" required oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">원
                            </td>
                        </tr>
                        <tr>
                            <td>상품 썸네일</td>
                            <td>
                                <span>크기 190 x 190, 상품 목록에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb1" required>

                                <span>크기 230 x 230, 상품 목록에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb2" required>

                                <span>크기 456 x 456, 상품 목록에 출력될 이미지 입니다. </span>
                                <input type="file" name="thumb3" required>
                            </td>
                        </tr>
                        <tr>
                            <td>상세 상품정보</td>
                            <td>
                                <span>크기 가로 940px 높이 제약없음, 크기 최대 1MB, 상세페이지 상품정보에 출력될 이미지 입니다.</span>
                                <input type="file" name="detail" required>
                            </td>
                        </tr>
                    </table>
                </section>

                <section>
                    <h4>상품정보 제공고시</h4>
                    <p>
                        [전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록해야 되는 정보입니다.
                    </p>
                    <table>
                        <tr>
                            <td>상품상태</td>
                            <td><input type="text" name="status" value="새상품" required></td>
                        </tr>
                        <tr>
                            <td>부가세 면세여부</td>
                            <td><input type="text" name="duty" value="과세상품" required></td>
                        </tr>
                        <tr>
                            <td>영수증발행</td>
                            <td><input type="text" name="receipt" value="발행가능 - 신용카드 전표, 온라인 현금영수증" required></td>
                        </tr>
                        <tr>
                            <td>사업자구분</td>
                            <td><input type="text" name="bizType" value="사업자 판매자" required></td>
                        </tr>
                        <tr>
                            <td>원산지</td>
                            <td><input type="text" name="origin" value="국내산" required></td>
                        </tr>
                    </table>
                </section>
                
                <input type="submit" value="등록하기" class="btnGO">
            </form>
        </article>

        <p class="ico alert">
            <strong>Warning!</strong>
            전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
        </p>
    </section>
</main>
<%@ include file="../_footer.jsp" %>