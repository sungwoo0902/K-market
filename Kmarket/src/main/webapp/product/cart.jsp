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
	            
	         	// 정가로 계산한 소계 값
	            orgPriceTotal = orgPriceTotal * countTotal;
	            // 할인된 금액과 정가의 차액 
	            discountTotal = orderTotal - orgPriceTotal;
	            // 할인된 금액의 소계로 계산한 포인트
	            pointTotal = (orderTotal/100) *  pointTotal;
	            
	            totalOrderAmount += orderTotal;
	            totalCountAmount += countTotal;
	            totalOrgPriceAmount += orgPriceTotal;
	            totalDiscountAmount += discountTotal;
	            totalDeliveryAmount += deliveryTotal;
	            totalPointAmount += pointTotal;
	        });
	     	// 합산된 값을 전체주문금액(td#totalOrderAmount)에 입력
	        $('#totalOrderAmount').text(totalOrderAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	        $('#totalCountAmount').text(totalCountAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	        $('#totalOrgPriceAmount').text(totalOrgPriceAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	        $('#totalDiscountAmount').text(totalDiscountAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	        if(totalDeliveryAmount == 0){
	        	$('#totalDeliveryAmount').text('무료');
	        }else{
	        	$('#totalDeliveryAmount').text(totalDeliveryAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));	        	
	        }
	        $('#totalPointAmount').text(totalPointAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
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
	            
	            // 정가로 계산한 소계 값
	            orgPriceTotal = orgPriceTotal * countTotal;
	            // 할인된 금액과 정가의 차액 
	            discountTotal = orderTotal - orgPriceTotal;
	            // 할인된 금액의 소계로 계산한 포인트
	            pointTotal = (orderTotal/100) *  pointTotal;
	            
	            totalOrderAmount += orderTotal;
	            totalCountAmount += countTotal;
	            totalOrgPriceAmount += orgPriceTotal;
	            totalDiscountAmount += discountTotal;
	            totalDeliveryAmount += deliveryTotal;
	            totalPointAmount += pointTotal;
	        });
	        // 합산된 값을 전체주문금액(td#totalOrderAmount)에 입력
	        $('#totalOrderAmount').text(totalOrderAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	        $('#totalCountAmount').text(totalCountAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	        $('#totalOrgPriceAmount').text(totalOrgPriceAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	        $('#totalDiscountAmount').text(totalDiscountAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	        // 배송비 총합이 0이면 무료라고 입력
	        if(totalDeliveryAmount == 0){
	        	$('#totalDeliveryAmount').text('무료');
	        }else{
	        	$('#totalDeliveryAmount').text(totalDeliveryAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));	        	
	        }
	        $('#totalPointAmount').text(totalPointAmount.toFixed(0).replace(/\B(?=(\d{3})+(?!\d))/g, ","));
	    });
	  	//*******************************************************//
		//******************** 개별 체크박스 클릭 **********************//
		//*******************************************************//
		
		
		
		
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
                        <th>포인트</th>
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
                        <td><input type="checkbox" name="cartNo" value="${cart.cartNo}"></td>
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
                        <td><fmt:formatNumber value="${cart.count}" pattern="#,###" /></td>
                        <td><fmt:formatNumber value="${cart.orgPrice}" pattern="#,###" /></td>
                        <td>${cart.discount}%</td>
                        <td><fmt:formatNumber value="${cart.point}" pattern="#,###" /></td>
                        <c:if test="${cart.delivery ne 0}">
                        <td><fmt:formatNumber value="${cart.delivery}" pattern="#,###" /></td>
                        </c:if>
                        <c:if test="${cart.delivery eq 0}">
                        <td class="free-delivery">${cart.delivery}</td>
                        </c:if>
                        <td><fmt:formatNumber value="${cart.total}" pattern="#,###" /></td>
                    </tr>
                    </c:forEach>
                </tbody>
            </table>
            <input type="button" name="del" value="선택삭제">
            <!-- 장바구니 전체합계 -->
            <div class="total">
                <h2>전체합계</h2>
                <table border="0">
                    <tbody>
                        <tr>
                            <td>상품수</td>
                            <td id="totalCountAmount">0</td>
                        </tr>
                        <tr>
                            <td>상품금액</td>
                            <td id="totalOrgPriceAmount">0</td>
                        </tr>
                        <tr>
                            <td>할인금액</td>
                            <td id="totalDiscountAmount">0</td>
                        </tr>
                        <tr>
                            <td>배송비</td>
                            <td id="totalDeliveryAmount">무료</td>
                        </tr>
                        <tr>
                            <td>적립 포인트</td>
                            <td id="totalPointAmount">0</td>
                        </tr>
                        <tr>
                            <td>전체주문금액</td>
                            <td id="totalOrderAmount">0</td>
                        </tr>
                    </tbody>
                </table>
                <input type="submit" name value="주문하기">
            </div>
        </form>
    </section>
    <!-- 장바구니 페이지 끝 -->
</main>
<%@ include file="./_footer.jsp" %>