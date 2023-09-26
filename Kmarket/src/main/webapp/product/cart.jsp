<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="./_header.jsp" %>
<script>
	$(function(){
		
		
		//*******************************************************//
		//******************** 체크박스 전체 선택 **********************//
		//*******************************************************//
		$('#selectAll').on('change',function(){
			// 전체 선택 체크박스의 체크 상태를 가져옴
	        var isChecked = $(this).prop('checked');
			
	        // 각 상품의 체크박스 상태를 변경
	        $('input[name^="cartNo"]').prop('checked', isChecked);
	        
	     	// 합산 값을 초기화 (누를때마다 초기화 해야되기 때문에 밖으로 빼면 안됨)
	        var totalOrderAmount = 0;
	        var totalCountAmount = 0;
	        var totalOrgPriceAmount = 0;
	        var totalDiscountAmount = 0;
	        var totalDeliveryAmount = 0;
	        var totalPointAmount = 0;
	     	
	     	// 체크된 체크박스들의 cart.total 값을 합산
	        $('input[name^="cartNo"]:checked').each(function() {
	        	// 합산 값을 초기화(누를때마다 초기화 해야되기 때문에 밖으로 빼면 안됨)
	            var orderTotal = parseFloat($(this).closest('tr').find('td:last').text().replace(/,/g, ''));
	            var countTotal = parseFloat($(this).closest('tr').find('td:eq(2)').text().replace(/,/g, ''));
	            var orgPriceTotal = parseFloat($(this).closest('tr').find('td:eq(3)').text().replace(/,/g, ''));
	            var discountTotal = parseFloat($(this).closest('tr').find('td:eq(4)').text().replace(/,/g, ''));
	            var pointTotal = parseFloat($(this).closest('tr').find('td:eq(5)').text().replace(/,/g, ''));
	            var deliveryTotal = parseFloat($(this).closest('tr').find('td:eq(6)').text().replace(/,/g, ''));
	            
	            // 할인된 금액과 정가의 차액 
	            discountTotal = orderTotal - orgPriceTotal - deliveryTotal;
	            
	            totalOrderAmount += orderTotal;
	            totalOrgPriceAmount += orgPriceTotal;
	            totalDiscountAmount += discountTotal;
	            totalDeliveryAmount += deliveryTotal;
	            totalPointAmount += pointTotal;
	        });
	     	// 합산된 값을 전체주문금액(td#totalOrderAmount)에 입력
	        $('#totalOrderAmount').text(totalOrderAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
	        $('#totalCountAmount').text($('input[name^="cartNo"]:checked').length + '개');
	        $('#totalOrgPriceAmount').text(totalOrgPriceAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
	        $('#totalDiscountAmount').text(totalDiscountAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
	        // 배송비 총합이 0이면 무료라고 입력
	        if(totalDeliveryAmount == 0){
	        	$('#totalDeliveryAmount').text('무료');
	        }else{
	        	$('#totalDeliveryAmount').text(totalDeliveryAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');	        	
	        }
	        $('#totalPointAmount').text(totalPointAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'점');
		});
		
		//*******************************************************//
		//******************** 개별 체크박스 클릭 **********************//
		//*******************************************************//
	    $('input[name^="cartNo"]').on('change', function() {
	        // 합산 값을 초기화(누를때마다 초기화 해야되기 때문에 밖으로 빼면 안됨)
	        var totalOrderAmount = 0;
	        var totalCountAmount = 0;
	        var totalOrgPriceAmount = 0;
	        var totalDiscountAmount = 0;
	        var totalDeliveryAmount = 0;
	        var totalPointAmount = 0;
	        
	        
	        // 체크된 체크박스들의 cart.total 값을 합산
	        $('input[name^="cartNo"]:checked').each(function() {
	        	var orderTotal = parseFloat($(this).closest('tr').find('td:last').text().replace(/,/g, ''));
	            var countTotal = parseFloat($(this).closest('tr').find('td:eq(2)').text().replace(/,/g, ''));
	            var orgPriceTotal = parseFloat($(this).closest('tr').find('td:eq(3)').text().replace(/,/g, ''));
	            var discountTotal = parseFloat($(this).closest('tr').find('td:eq(4)').text().replace(/,/g, ''));
	            var pointTotal = parseFloat($(this).closest('tr').find('td:eq(5)').text().replace(/,/g, ''));
	            var deliveryTotal = parseFloat($(this).closest('tr').find('td:eq(6)').text().replace(/,/g, ''));
	            
	            // 할인된 금액과 정가의 차액 
	            discountTotal = orderTotal - orgPriceTotal - deliveryTotal;
	            
	            totalOrderAmount += orderTotal;
	            totalOrgPriceAmount += orgPriceTotal;
	            totalDiscountAmount += discountTotal;
	            totalDeliveryAmount += deliveryTotal;
	            totalPointAmount += pointTotal;
	        });
	        // 합산된 값을 전체주문금액(td#totalOrderAmount)에 입력
	        $('#totalOrderAmount').text(totalOrderAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
	        $('#totalCountAmount').text($('input[name^="cartNo"]:checked').length + '개');
	        $('#totalOrgPriceAmount').text(totalOrgPriceAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
	        $('#totalDiscountAmount').text(totalDiscountAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');
	        // 배송비 총합이 0이면 무료라고 입력
	        if(totalDeliveryAmount == 0){
	        	$('#totalDeliveryAmount').text('무료');
	        }else{
	        	$('#totalDeliveryAmount').text(totalDeliveryAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'원');	        	
	        }
	        $('#totalPointAmount').text(totalPointAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ",")+'점');
	    });
	  	//*******************************************************//
		//********************** 선택삭제 클릭 ************************//
		//*******************************************************//
		const ctxPath = $('#ctxPath').val();
		const selectedCartNos = [];
		const uid = $('#uid').val();
		
		
		$('#del').click(function(e){
			e.preventDefault();
			// 체크돼있는 prodNo를 배열로 만들어 넣음
			$('input[name^="cartNo"]:checked').each(function(){
				
				selectedCartNos.push($(this).val());
			});
			// 체크돼있는 prodNo를 배열이 없다면 돌려보냄.
			if(selectedCartNos.length === 0){
				alert('선택된 상품이 없습니다.');
				return;
			}
			// 체크된 prodNo 전체와 uid를 jsonData로 만듬
			const jsonData ={
					"uid": uid,
					"selectedCartNos" : selectedCartNos
			};
			if(confirm('선택된 상품을 장바구니에서 삭제하시겠습니까?')){
				// jsonData를 전송
				// traditional: true는 배열을 ajax로 보낼때 있어야됨.
				$.ajax({
					url: '/Kmarket/product/cart.do',
					type: 'post',
					data: jsonData,
					traditional: true,
					dataType: 'json',
					success: function(data){
						// 삭제가 성공적으로 됐을때 알림 띄워주고 창 새로고침
						if(data.deleteResult == 1){
							alert('장바구니에서 삭제되었습니다.');
							window.location.href = ctxPath+'/product/cart.do?uid='+uid;
						}else{
							return;
						}
					}
				})// ajax end
			}else{
				return;
			}// 삭제 confirm end
		}); // 선택삭제 click end

		//*******************************************************//
		//******************* 선택된 상품 order *********************//
		//*******************************************************//
		$('input[name="order"]').click(function(e){
			e.preventDefault();
			//alert('order click');
			
			// 체크돼있는 prodNo를 배열로 만들어 넣음
			$('input[name^="cartNo"]:checked').each(function(){
				
				selectedCartNos.push($(this).val());
			});
			if(selectedCartNos.length === 0){
				alert('주문할 상품을 선택해주세요.');
				return;
			}
			
	        var ordTotPrice = parseFloat($('#totalOrderAmount').text().replace(/,/g, ''));
	        var ordCount = parseFloat($('#totalCountAmount').text().replace(/,/g, ''));;
	        var ordPrice = parseFloat($('#totalOrgPriceAmount').text().replace(/,/g, ''));;
	        var cleanedOrdDiscount = $('#totalDiscountAmount').text().replace(/[-,원]/g, ''); // '-'와 ',' 그리고 '원'을 모두 제거
	        var ordDiscount = parseFloat(cleanedOrdDiscount);
	        if($('#totalDeliveryAmount').text() != '무료'){
	        	var ordDelivery = parseFloat($('#totalDeliveryAmount').text().replace(/,/g, ''));;
	        }else{
	        	var ordDelivery = 0;
	        }
	        var savePoint = parseFloat($('#totalPointAmount').text().replace(/,/g, ''));;
            
            console.log('ordCount'+ordCount);
            console.log('ordPrice'+ordPrice);
            console.log('ordDiscount'+ordDiscount);
            console.log('ordDelivery'+ordDelivery);
            console.log('savePoint'+savePoint);
            console.log('ordTotPrice'+ordTotPrice);
            
			const jsonData = {"selectedCartNos": selectedCartNos,
					   		  "ordTotPrice": ordTotPrice,
					   		  "ordCount": ordCount,
					   		  "ordPrice": ordPrice,
					   		  "ordDiscount": ordDiscount,
					   		  "ordDelivery": ordDelivery,
					   		  "savePoint": savePoint
							}
			
			$.ajax({
				url: '${ctxPath}/product/order.do',
				type: 'post',
				data: jsonData,
				traditional: true,
				dataType: 'json',
				success: function(data){
					
					if(data.result == 1){
						alert('선택된 상품들을 주문합니다.');
						window.location.href = ctxPath+'/product/order.do';
					}
				}
			})
		}); // order click end
		
		//*******************************************************//
		//******************** 장바구니 비었을때 **********************//
		//*******************************************************//
		var isCartsEmpty = ${empty carts};
		
		if(isCartsEmpty){
			$('tr.empty').css('display', 'table-row');
		}// isCartsEmpty end
		
		
	});// end
</script>
<main id="product">
    <%@ include file="./_aside.jsp" %>
    <!-- 장바구니 페이지 시작 -->
    <section class="cart">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>장바구니</h1>
            <p>
                HOME > 
                <span>패션·의류·뷰티</span>
                 > 
                 <strong>장바구니</strong>
            </p>
        </nav>
        <form action="#">
            <!-- 장바구니 목록 -->
            <table>
                <thead>
                    <tr>
                        <th>
                            <input type="checkbox" name="all" id="selectAll">
                        </th>
                        <th>상품명</th>
                        <th>총수량</th>
                        <th>판매가</th>
                        <th>할인</th>
                        <th>포인트 적립</th>
                        <th>배송비</th>
                        <th>소계</th>
                    </tr>
                </thead>
                <tbody>
                    <tr class="empty">
                        <td colspan="7">장바구니에 상품이 없습니다.</td>
                    </tr>
                    <c:forEach var="cart" items="${carts}">
                    <tr>
                        <td>
                        	<input type="checkbox" name="cartNo" value="${cart.cartNo}">
                        	<input type="hidden" name="prodNo" value="${cart.prodNo}">
                        	<input type="hidden" name="ctxPath" id="ctxPath" value="${ctxPath}">
                        	<input type="hidden" name="uid" id="uid" value="${sessUser.uid}">
                        </td>
                        <td>
                            <article>
                                <a href="${ctxPath}/product/view.do?cate1=${cart.prodCate1}&cate2=${cart.prodCate2}&prodNo=${cart.prodNo}">
                                    <img src="${ctxPath}/thumb/${cart.prodCate1}/${cart.prodCate2}/${cart.thumb1}" alt="">
                                </a>
                                <div>
                                    <h2>
                                        <a href="${ctxPath}/product/view.do?cate1=${cart.prodCate1}&cate2=${cart.prodCate2}&prodNo=${cart.prodNo}">${cart.prodName}</a>
                                    </h2>
                                    <p>상품설명:${cart.descript}</p>
                                </div>
                            </article>
                        </td>
                        <td><fmt:formatNumber value="${cart.count}" pattern="#,###" />개</td>
                        <c:if test="${cart.discount ne 0}">
                        	<td>
						        <span class="throughPrice"><fmt:formatNumber value="${cart.orgPrice * cart.count}" pattern="#,###" />원</span>
						        <fmt:formatNumber value="${cart.total - cart.delivery}" pattern="#,###" />원
						    </td>
                        </c:if>
                        <c:if test="${cart.discount eq 0}">
                        	<td><fmt:formatNumber value="${cart.total}" pattern="#,###" />원</td>
                        </c:if>
                        <c:if test="${cart.discount ne 0}">
                        	<td>${cart.discount}%</td>
                        </c:if>
                        <c:if test="${cart.discount eq 0}">
                        	<td>0</td>
                        </c:if>
                        <c:if test="${cart.point ne 0}">
                        	<td><fmt:formatNumber value="${(cart.total/100) * cart.point}" pattern="#,###" />점</td>
                        </c:if>
                        <c:if test="${cart.point eq 0}">
                        	<td>0</td>
                        </c:if>
                        <c:if test="${cart.delivery ne 0}">
                        	<td><fmt:formatNumber value="${cart.delivery}" pattern="#,###" />원</td>
                        </c:if>
                        <c:if test="${cart.delivery eq 0}">
                        	<td class="free-delivery">${cart.delivery}</td>
                        </c:if>
                        <td><fmt:formatNumber value="${cart.total}" pattern="#,###" />원</td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="button" name="del" id="del" value="선택삭제">
            <!-- 장바구니 전체합계 -->
            <div class="total">
                <h2>전체합계</h2>
                <table border="0">
                    <tbody>
                        <tr>
                            <td>상품수</td>
                            <td id="totalCountAmount">0개</td>
                        </tr>
                        <tr>
                            <td>상품금액</td>
                            <td id="totalOrgPriceAmount">0원</td>
                        </tr>
                        <tr>
                            <td>할인금액</td>
                            <td id="totalDiscountAmount">0원</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td id="totalDeliveryAmount">무료</td>
                        </tr>
                        <tr>
                            <td>적립 포인트</td>
                            <td id="totalPointAmount">0점</td>
                        </tr>
                        <tr>
                            <td>전체주문금액</td>
                            <td id="totalOrderAmount">0원</td>
                        </tr>
                    </tbody>
                </table>
                <input type="submit" name="order" value="주문하기">
            </div>
        </form>
    </section>
    <!-- 장바구니 페이지 끝 -->
</main>
<%@ include file="./_footer.jsp" %>