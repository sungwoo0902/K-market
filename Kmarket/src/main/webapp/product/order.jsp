<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="./js/zipcode.js"></script>
<script>
	$(function(){
		let prevPoint = 0;
		$('#usePoint').focusout(function(){
			var usePoint = parseInt($(this).val());
			var hasPoint = parseFloat($("#hasPoint").text());
			// 현재 포인트 초과 입력시 현재 보유 포인트 값을 입력할거임
			var correctPoint = Math.min(usePoint, hasPoint);
			
			// 입력된 값이 숫자가 아니면 이전 prevPoint 값으로 복원
	        if (!/^\d+$/.test(usePoint)) {
	            $(this).val(prevPoint);
	        }
			
			// 현재 보유 포인트와 비교하여 입력된 값이 더 크면 현재 보유 포인트 값으로 업데이트
	        if (usePoint > hasPoint) {
	            $(this).val(correctPoint);
	        }
			
			console.log('prevPoint : '+prevPoint);
			// 숫자인 경우만 prevPoint 업데이트
	        if (/^\d+$/.test(correctPoint)) {
	        	prevPoint = correctPoint;
	        }
		});
		$('#usePoint').focus(function(){
			prevCount = $(this).val();
		});
		
		// 포인트 사용 버튼 구현중
		const ordTotPrice = $('.ordTotPrice').val();
		$('#applyDiscount').click(function(){
			
			if(prevPoint < 1){
				alert('사용할 포인트를 입력해주세요');
				$('.usePoint').text('0원');
				$($('#ordTotPrice')).text($.numberWithCommas(ordTotPrice)+'원');
				return;
			}
			
			var usePoint = $('#usePoint').val(); 
			if(!isNaN(usePoint) & usePoint > 0){
				 
				$('.usePoint').text('-'+$.numberWithCommas(usePoint)+'원');
				
				const disOrdTotPrice = ordTotPrice - $('#usePoint').val();
				
				console.log('ordTotPrice :'+ordTotPrice);
				console.log('usePoint :'+$('#usePoint').val());
				console.log('disOrdTotPrice :'+disOrdTotPrice);
				
				$($('#ordTotPrice')).text($.numberWithCommas(disOrdTotPrice)+'원');
	        } else {
	            alert("포인트는 5000점 이상이어야 합니다.");
	        }
		});// applyDiscount click end
		
		//***********************************************//
	    //*************** 숫자 3자리 콤마 함수 ***************//
	    //***********************************************//
	    $.numberWithCommas = function (x) {
	  	  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	  	}
		
		
	 	// 라디오 버튼이 변경될 때 이벤트 리스너를 추가합니다.
	 	let ordPayment = 11;
	    $("input[name='payment']").change(function() {
	        // 선택된 라디오 버튼의 value 값을 가져온다.
	        var selectedPayment = $("input[name='payment']:checked").val();

	        ordPayment = selectedPayment;
	        console.log(selectedPayment);
	    });
		
	  	//***********************************************//
	    //****************** 결제 하기 클릭 ******************//
	    //***********************************************//
	    const savePoint = parseFloat($('.savePoint').text().replace(/[^0-9.]+/g, ''));
	    $('#ordComplete').click(function(e){
	    	e.preventDefault();
			console.log(savePoint);
			const usedPoint = parseFloat($('.usePoint').text().replace(/[^0-9.]+/g, ''));
			const completeTotPrice =  parseFloat($('#ordTotPrice').text().replace(/[^0-9.]+/g, ''));
			const recipName = $('#orderer').val();
			const recipHp = $('#hp').val();
			const recipZip = $('#zip').val();
			const recipAddr1 = $('#addr1').val();
			const recipAddr2 = $('#addr2').val();
			let ordCompletePayment = ordPayment;
			console.log('ordCompletePayment : '+ordCompletePayment);
			let ordComplete = 0;
			if(ordCompletePayment == 21 || ordCompletePayment == 22){
				ordComplete = 1;
			}else{
				ordComplete = 2;
			}
			
			if(recipName == ""){
				alert('받으실 분의 이름을 입력하세요.');
				return;
			}else if(recipHp == ""){
				alert('받으실 분의 휴대폰 번호를 입력하세요.');
				return;
			}else if(recipZip == ""){
				alert('받으실 분의 주소를 입력하세요.');
				return;
			}
			// 이름이 주어진 형식과 일치하는지 검사
			if (!/^[a-zA-Z가-힣]{2,}$/.test(recipName)) {
			    alert('이름이 유효하지 않습니다. 2자리 이상의 영문자 또는 한글만 입력하세요.');
			    return;
			}
			
			// 휴대폰 번호가 주어진 형식과 일치하는지 검사
			if (!/^\d{3}-\d{4}-\d{4}$/.test(recipHp)) {
			    alert('휴대폰 번호가 유효하지 않습니다. - 포함 11자리를 입력해주세요.');
			    return;
			}
			
			const jsonData = {
					"usedPoint": usedPoint,
					"completeTotPrice": completeTotPrice,
					"recipName": recipName,
					"recipHp": recipHp,
					"recipZip": recipZip,
					"recipAddr1": recipAddr1,
					"recipAddr2": recipAddr2,
					"ordCompletePayment": ordCompletePayment,
					"ordComplete": ordComplete,
					"savePoint": savePoint
			};
			
			$.ajax({
				url: '${ctxPath}/product/complete.do',
				type: 'post',
				data: jsonData,
				dataType: 'json',
				success: function(data){
					if(data.result == 1){
						alert('주문이 완료되었습니다. 24시간 이내 미입금시 취소됩니다.');
					}else if(data.result == 2){
						alert('결제가 완료되었습니다.');
					}
					window.location.href = '${ctxPath}/product/complete.do';
				}
			});
	    });
	}); //end

</script>

<main id="product">
    <%@ include file="./_aside.jsp" %>
    <!-- 주문 페이지 시작 -->
    <section class="order">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>주문결제</h1>
            <p>
                HOME > 장바구니 > 
                <strong>주문결제</strong>
            </p>
        </nav>
        <form action="#">
            <!-- 주문 상품 목록 -->
            <table>
                <thead>
                    <tr>
                        <th>상품명</th>
                        <th>총수량</th>
                        <th>판매가</th>
                        <th>배송비</th>
                        <th>소계</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="empty">
                        <td colspan="7">장바구니에 상품이 없습니다.</td>
                    </tr>
                    <c:forEach var="item" items="${orderItems}">
                    <tr>
                        <td>
                            <article>
                                <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">
                                    <img src="${ctxPath}/thumb/${item.prodCate1}/${item.prodCate2}/${item.thumb1}" alt="">
                                </a>
                                <div>
                                    <h2>
                                        <a href="${ctxPath}/product/view.do?cate1=${item.prodCate1}&cate2=${item.prodCate2}&prodNo=${item.prodNo}">${item.prodName}</a>
                                    </h2>
                                    <p>상품설명:${item.descript}</p>
                                </div>
                            </article>
                        </td>
                        <td><fmt:formatNumber value="${item.count}" pattern="#,###" />개</td>
                        <c:if test="${item.discount ne 0}">
                        	<td>
						        <span class="throughPrice"><fmt:formatNumber value="${item.price * item.count}" pattern="#,###" />원</span>
						        <fmt:formatNumber value="${item.total - item.delivery}" pattern="#,###" />원
						    </td>
                        </c:if>
                        <c:if test="${item.discount eq 0}">
                        	<td><fmt:formatNumber value="${item.total - item.delivery}" pattern="#,###" />원</td>
                        </c:if>
                        <c:if test="${item.delivery eq 0}">
                        	<td class="free-delivery">0</td>
                        </c:if>
                        <c:if test="${item.delivery ne 0}">
                        	<td class="has-delivery"><fmt:formatNumber value="${item.delivery}" pattern="#,###" />원</td>
                        </c:if>
                        <td><fmt:formatNumber value="${item.total}" pattern="#,###" />원</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <!-- 최종 결제 정보 -->
            <div class="final">
                <h2>최종결제 정보</h2>
                <input class="ordTotPrice" type="hidden" value="${order.ordTotPrice}">
                <table border="0">
                    <tbody>
                        <tr>
                            <td>총</td>
                            <td><fmt:formatNumber value="${order.ordCount}" pattern="#,###" /> 건</td>
                        </tr>
                        <tr>
                            <td>상품금액</td>
                            <td class="ordPrice"><fmt:formatNumber value="${order.ordPrice}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td>할인금액</td>
                            <td class="ordDiscount"><fmt:formatNumber value="-${order.ordDiscount}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td>적립금</td>
                            <td class="savePoint"><fmt:formatNumber value="${order.savePoint}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td class="ordDelivery"><fmt:formatNumber value="${order.ordDelivery}" pattern="#,###" />원</td>
                        </tr>
                        <tr>
                            <td>포인트 할인</td>
                            <td class="usePoint">0원</td>
                        </tr>
                        <tr>
                            <td>전체주문금액</td>
                            <td id="ordTotPrice"><fmt:formatNumber value="${order.ordTotPrice}" pattern="#,###" />원</td>
                        </tr>
                    </tbody>
                </table>
                <input type="button" id="ordComplete" value="결제하기">
            </div>
            <!-- 배송정보 -->
            <article class="delivery">
                <h1>배송정보</h1>
                <table>
                    <tbody>
                        <tr>
                            <td>주문자</td>
                            <td>
                                <input type="text" id="orderer" name="orderer" value="${member.name}" placeholder="이름을 입력" required>
                            </td>
                        </tr>
                        <tr>
                            <td>휴대폰</td>
                            <td>
                                <input type="text" id="hp" name="hp" value="${member.hp}" maxlength="13" placeholder="휴대폰번호 입력" required>
                                <span>- 포함 입력</span>
                            </td>
                        </tr>
                        <tr>
                            <td>우편번호</td>
                            <td>
                                <input type="text" id="zip" name="zip" value="${member.zip}" readonly required>
                                <input type="button" value="검색" onclick="zipcode()">
                            </td>
                        </tr>
                        <tr>
                            <td>기본주소</td>
                            <td>
                                <input type="text" id="addr1"  name="addr1" value="${member.addr1}" readonly>
                            </td>
                        </tr>
                        <tr>
                            <td>상세주소</td>
                            <td>
                                <input type="text" id="addr2" name="addr2" value="${member.addr2}" placeholder="상세주소를 입력하세요">
                            </td>
                        </tr>
                    </tbody>
                </table>
            </article>
            <!-- 할인정보 -->
            <article class="discount">
                <h1>할인정보</h1>
                <div>
                    <p>
                        현재 포인트 : 
                        <span id="hasPoint">${member.point}</span>
                        점
                    </p>
                    <label>
                    	<c:if test="${member.point < 5000}">
                        	<input type="text" id="usePoint" name="point" readonly>
                        </c:if>
                    	<c:if test="${member.point >= 5000}">
                        	<input type="text" id="usePoint" name="point">
                        </c:if>
                        점
                        <input type="button" id="applyDiscount" value="적용">
                    </label>
                    <span>포인트 5000점 이상이면 현금처럼 사용 가능합니다.</span>
                </div>
            </article>
            <!-- 결제방법 -->
            <article class="payment">
                <h1>결제방법</h1>
                <div>
                    <span>신용카드</span>
                    <p>
                        <label>
                        	<c:if test="${order.ordPayment eq 11}">
                            <input type="radio" name="payment" value="11" checked>
                            </c:if>
                        	<c:if test="${order.ordPayment ne 11}">
                            <input type="radio" name="payment" value="11">
                            </c:if>
                            신용카드 결제
                        </label>
                        <label>
                            <input type="radio" name="payment" value="12">
                            체크카드 결제
                        </label>
                    </p>
                </div>
                <div>
                    <span>계좌이체</span>
                    <p>
                        <label>
                            <input type="radio" name="payment" value="21">
                            실시간 계좌이체
                        </label>
                        <label>
                            <input type="radio" name="payment" value="22">
                            무통장 입금
                        </label>
                    </p>
                </div>
                <div>
                    <span>기타</span>
                    <p>
                        <label>
                            <input type="radio" name="payment" value="31">
                            휴대폰 결제
                        </label>
                        <label>
                            <input type="radio" name="payment" value="32">
                            카카오페이
                            <img src="./images/ico_kakaopay.gif" alt="카카오페이">
                        </label>
                    </p>
                </div>
            </article>
            <!-- 경고 -->
            <article class="alert">
                <ul>
                    <li>
                        <span>
                            케이마켓의 모든 판매자는 안전거래를 위해 구매금액, 결제수단에 상관없이 모든거래에 대하여 케
                            이마켓 유한책임회사의 구매안전서비스(에스크로)를 제공하고 있습니다.
                        </span>
                    </li>
                    <li>
                        <span>
                            케이마켓 유한책임회사의 전자금융거래법에 의해 결제대금예치업 등록번호는 02-006-00008 입니다.
                        </span>
                    </li>
                    <li>
                        <span>
                            등록여부는 금융감독원 홈페이지(www.fss.or.kr)의 업무자료>인허가업무안내>전자금융업등록현황
                            에서 확인하실수 있습니다.
                        </span>
                    </li>
                </ul>
            </article>
        </form>
    </section>
    <!-- 주문 페이지 끝 -->
</main>
<%@ include file="./_footer.jsp" %>