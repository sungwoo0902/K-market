<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="./_header.jsp" %>
<script>

	$(function () {
	    const inputCount = $('.inputCount');
	    const increase = $('.increase');
	    const decrease = $('.decrease');
	 	// 숫자가 아닌 다른 값 입력시 이전 값 입력을 위한 초기화
	    let prevCount = 1; 
	 	let price = $('#org_price').val();
	 	const discount = $('#discount').val();
	 	const totalPrice = $('.totalPrice');
	 	
	 	if(discount != 0){
	 		price = $('#dis_price').val();
	 	}
	
	 	//***********************************************//
	    //************ 입력값이 변경될 때 이벤트 처리 ************//
	    //***********************************************//
	    inputCount.on('input', function () {
	        let currentValue = parseInt(inputCount.val()) || 1; // 현재 값이 숫자가 아니면 최솟값(1)로 설정
	        //const max = parseInt(inputCount.attr('max'));
	        
	        // 숫자인 경우만 prevCount 업데이트
	        if (/^\d+$/.test(inputCount.val())) {
	            prevCount = currentValue;
	        }
	        
	        totalPrice.text($.numberWithCommas(parseInt(price * prevCount)));
	    }); // input end
		
	  	//***********************************************//
	    //*** input focus, focusout할 때 input의 숫자 저장 ***//
	    //***********************************************//
	    inputCount.on('focus', function () {
	        prevCount = $(this).val();
	    });
	    inputCount.on('focusout', function () {
	        inputCount.val(prevCount);
	        console.log('inputCount :'+inputCount.val());
	        console.log('discount :'+discount);
	        console.log('price :'+price);
	    }); // focus, focusout end
	    
	    //***********************************************//
		//***************** +버튼 클릭 함수 *****************//
	    //***********************************************//
		$(increase).click(function () {
	        let currentValue = parseInt(inputCount.val());
	
	        // 만약 현재 값이 max 값 미만이면 1을 더하고 업데이트
	        if (currentValue < 999) {
	            currentValue += 1;
	            inputCount.val(currentValue);
	        }
	        totalPrice.text($.numberWithCommas(parseInt(price * inputCount.val())));
	    }); // increase click end 
	    
	  	//***********************************************//
		//***************** -버튼 클릭 함수 *****************//
		//***********************************************//
	    $(decrease).click(function () {
	        let currentValue = parseInt(inputCount.val());
	
	        // 만약 현재 값이 최솟값 초과이면 1을 빼고 업데이트
	        if (currentValue > 1) {
	            currentValue -= 1;
	            inputCount.val(currentValue);
	        }
	        totalPrice.text($.numberWithCommas(parseInt(price * inputCount.val())));
	    }); // decrease click end 
		
	  	//***********************************************//
		//*************** 0이하 입력시 1로 변경 ***************//
		//***********************************************//
	    inputCount.blur(function () {
	        let currentValue = parseInt(inputCount.val());
	        // 현재 값이 최솟값 미만일 경우 최솟값으로 설정
	        if (currentValue < 1) {
	            inputCount.val(prevCount);
	        }
	    }); //inputCount blur end
	    
	  	//***********************************************//
	    //*************** 숫자 3자리 콤마 함수 ***************//
	    //***********************************************//
	    $.numberWithCommas = function (x) {
	  	  return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
	  	}
	    
	  	//***********************************************//
	    //***************** 장바구니 버튼 구현 *****************//
	    //***********************************************//
	    const formCart = $('#formCart');
	    const ctxPath = $('#ctxPath').val();
	    const uid = $('#uid').val();
	  	
	    const prodNo = $('#prodNo').val();
	  	const point = $('#point').val();
	  	const delivery = $('#delivery').val();
	    
	    $('.cart').click(function(e){
	    	if(${sessUser == null}){
	    		alert('로그인 후 이용 가능합니다.');
	    		return false;
	    	}
	    	
	    	e.preventDefault();
	    	//alert('장바구니');
	    	console.log(inputCount.val());
	    	console.log(price);	   
			const orgPrice = $('#org_price').val();
			const totalPrice = parseInt(inputCount.val() * price) + parseInt(delivery);
			console.log('orgPrice : '+orgPrice);
	    	// jsonData 초기화
	    	const jsonData = {
      	    		"prodNo" : prodNo,
      	    		"inputCount" : inputCount.val(),
      	    		"price" : orgPrice,
      	    		"discount" : discount,
      	    		"point" : point,
      	    		"delivery" : delivery,
      	    		"totalPrice" : totalPrice
      	    	};
    			// 위의 jsonData를 insertCartController로 보낸다
	    		$.ajax({
      	    		url: '${ctxPath}/product/insertCart.do',
      	    		type: 'post',
      	    		data: jsonData,
      	    		dataType: 'json',
      	    		success: function(data){
      	    			console.log(data);
      	    			console.log(data.result);
      	    			
      	    			// (해당 상품이 장바구니에 있는 경우)
      	    			// 만약 insertCartController에서 받아온 result 값이 1이라면 아래를 실행
      	    			if(data.result == 1){
      	    				
      	    				// 해당 상품을 또 장바구니에 추가할건지 확인받는다.
      	    				if(confirm('해당 상품이 이미 장바구니에 있습니다. 추가하시겠습니까?')){
      	    					// 장바구니에 또 추가할거라면 cartResult 값을 1으로 설정하여 보낸다.
      	    					const commitData = Object.assign({},jsonData,{"cartResult":1});
      	    					
      	    					$.ajax({
      	    						url: '${ctxPath}/product/insertCart.do',
      	    	      	    		type: 'post',
      	    	      	    		data: commitData,
      	    	      	    		dataType: 'json',
      	    	      	    		success: function(data){
      	    	      	    			console.log('commitData');
      	    	      	    			console.log(data.cartresult);
      	    	      	    		}
      	    					}); // ajax end
      	    					if(confirm('장바구니에 추가되었습니다. 지금 장바구니로 이동하시겠습니까?')){
      	    		      	    	console.log('jsonData :'+JSON.stringify(jsonData));
      	    		      	    	window.location.href = ctxPath+'/product/cart.do';
	      	    		    	}else{
	      	    		      	    	console.log('jsonData :'+JSON.stringify(jsonData));
	      	    		    		return;
	      	    		    	}
      	    				// 해당 상품이 장바구니에 이미 있기 때문에 취소한다.
      	    				}else{
      	    					// 장바구니에 추가하지 않는다면 cartResult 값을 0으로 설정하여 보낸다.
      	    					const passData = Object.assign({},jsonData,{"cartResult":0});
      	    					
      	    					$.ajax({
      	    						url: '${ctxPath}/product/insertCart.do',
      	    	      	    		type: 'post',
      	    	      	    		data: passData,
      	    	      	    		dataType: 'json',
      	    	      	    		success: function(data){
      	    	      	    			console.log('passData');
      	    	      	    			console.log(data.cartResult);
      	    	      	    		}
      	    					}); // ajax end
      	    				}
      	    				
      	    			// (해당 상품이 장바구니에 없는 경우)
      	    			// 만약 insertCartController에서 받아온 result 값이 0이라면 아래를 실행
      	    			}else if(data.result == 0){
      	    				$.ajax({
      	        	    		url: '${ctxPath}/product/insertCart.do',
      	        	    		type: 'post',
      	        	    		data: jsonData,
      	        	    		dataType: 'json',
      	        	    		success: function(data){
      	        	    			
      	        	    		}
      	    				}); // ajax end
      	    				if(confirm('장바구니에 추가되었습니다. 지금 장바구니로 이동하시겠습니까?')){
	      	  	      	    	console.log('jsonData :'+JSON.stringify(jsonData));
	      	  	      	    	window.location.href = '${ctxPath}/product/cart.do';
		      	  	    	}else{
	      	  	      	    	console.log('jsonData :'+JSON.stringify(jsonData));
		      	  	    		return;
		      	  	    	}
      	    			}
      	    		} // 전체 ajax success end
      	    	}); // 전체 ajax end
	    }); // cart click end
	    
	    
	    
		//***************** 즉시 구매 **************************************************//
		$('.order').click(function() {
			if(${sessUser == null}){
	    		alert('로그인 후 이용 가능합니다.');
	    		return false;
	    	}
			const ordTotPrice = (parseInt(inputCount.val()) * parseInt(price)) + parseInt(delivery);
			const ordCount = inputCount.val();
			const ordPrice = (parseInt($('#org_price').val())) * parseInt(inputCount.val());
			const ordDiscount = Math.ceil((ordPrice / 100) * discount);
			const ordDelivery = delivery;
			const savePoint = Math.ceil(((ordTotPrice - delivery) / 100) * point);
			
			
			// jsonData 초기화
	    	const jsonData = {
      	    		"prodNo" : prodNo,
      	    		"ordCount" : inputCount.val(),
      	    		"ordPrice" : ordPrice,
      	    		"ordDiscount" : (price * inputCount.val())/100 * discount,
      	    		"discountPercent": discount,
      	    		"savePoint" : savePoint,
      	    		"ordDelivery" : delivery,
      	    		"ordTotPrice" : ordTotPrice
      	    	};
			
			console.log('ordTotPrice :'+ ordTotPrice);
			console.log('ordCount :'+ ordCount);
			console.log('ordPrice :'+ ordPrice);
			console.log('ordDiscount :'+ ordDiscount);
			console.log('ordDelivery :'+ ordDelivery);
			console.log('savePoint :'+ savePoint);
			console.log('-------------------');

			$.ajax({
					url: '${ctxPath}/product/order.do',
    	    		type: 'post',
    	    		data: jsonData,
    	    		dataType: 'json',
    	    		success: function(data){
    	    			console.log('order complete');
    	    			window.location.href = '${ctxPath}/product/order.do';
    	    		}
				}); // ajax end
    	}); // order click end..
	    
	    
	    
		// 상품평보기 클릭시 자동 스크롤 **************************************************//
	    $('.goToReview').click(function(e) {
	    	e.preventDefault();
	    	
	    	const review = document.querySelector('.yes');
	        if(review) {
	            review.scrollIntoView({ behavior: 'smooth' });
	        }else {
	        	alert('등록된 리뷰가 없습니다.');
	        }
		}); // go to review End....
		
		// 리뷰페이지 이동시 자동 스크롤
		if(${pg ne null}) {
			const review = document.querySelector('.review');
			const reviewPos = review.getBoundingClientRect().top;
			
			window.scrollTo({
				top: window.scrollY + reviewPos,
				behavior: 'smooth',
			});
		}

	}); // end
</script>
<%@ include file="../_toolBar.jsp" %>
<main id="product">
    <%@ include file="./_aside.jsp" %>
    <!-- 상품 상세페이지 시작 -->
    <section class="view">
        <!-- 제목, 페이지 네비게이션 -->
        <nav>
            <h1>상품보기</h1>
            <p>
                HOME > 
                <span>${prod.c1Name}</span>
                >
                <strong>${prod.c2Name}</strong>
            </p>
        </nav>
        <!-- 상품 전체 정보 내용 -->
        <article class="info">
            <div class="image">
                <img src="${ctxPath}/thumb/${prod.prodCate1}/${prod.prodCate2}/${prod.thumb3}" alt="상품이미지">
            </div>
            <div class="summary">
                <nav>
                    <h1>${prod.company}</h1>
                    <h2>
                        상품번호&nbsp;:&nbsp;
                        <span>${prod.prodNo}</span>
                    </h2>
                </nav>
                <nav>
                    <h3>${prod.prodName}</h3>
                    <!-- 상품 설명 없으면 다르게 출력되게 해야함 -->
                    <p>상품설명 : 
                    <c:choose>
                    	<c:when test="${prod.descript == null || prod.descript.equals('')}">상세페이지 참고</c:when>
                    	<c:otherwise>${prod.descript}</c:otherwise>
                    </c:choose>
                    </p>
                    <!-- 별점 처리 해야함 -->
                    <h5 class="rating star${prod.score}">
                    <a href="#" class="goToReview">상품평보기</a>
                    </h5>
                </nav>
                <nav>
                
                	<!-- 할인율이 있다면 가격 아래에 정가와 할인율 표시 -->
                	<c:if test="${prod.discount ne 0}">
	                    <div class="org_price">
	                        <del>
	                            <fmt:formatNumber value="${prod.price}" pattern="#,###" />
	                        </del>
	                        <span>
	                            ${prod.discount}%
	                        </span>
	                    </div>
                    </c:if>
                    <!-- 할인율이 없다면 가격만 표시(이름은 disPrice지만 할인율이 없다면 정가임) -->
                    <div class="dis_price">
                        <ins>
                            <fmt:formatNumber value="${prod.disPrice}" pattern="#,###" />
                        </ins>
                    </div>
                </nav>
                <nav>
                	<c:if test="${prod.delivery eq 0}">
                    	<span class="free-delivery">무료배송</span>
                    </c:if>
                	<c:if test="${prod.delivery ne 0}">
                    	<span class="delivery">배송비 <fmt:formatNumber value="${prod.delivery}" pattern="#,###" />원</span>
                    </c:if>
                    <span class="arrival">${week.equals('월')?'글피':'모레'}(${week}) ${day} 도착예정</span>
                    <span class="desc">본 상품은 국내배송만 가능합니다.</span>
                </nav>
                <nav>
                    <span class="card cardfree">
                        <i>아이콘</i>
                        무이자할부
                    </span>
                    &nbsp;&nbsp;
                    <span class="card cardadd">
                        <i>아이콘</i>
                        카드추가혜택
                    </span>
                </nav>
                <nav>
                    <span class="origin">원산지-${prod.origin}</span>
                </nav>
                <img src="${ctxPath}/images/vip_plcc_banner.png" alt="100원만 결제해도 1만원 적립" class="banner">
                <div class="count">
                    <button class="decrease">-</button>
                    <!-- 수량 입력 칸은 3자리 이상 입력하지 못하게끔 함 -->
                    <input type="text" class="inputCount" name="num" min="1" max="${prod.stock}" value="1" maxlength="3" pattern="\d*">
                    <button class="increase">+</button>
                </div>
                <div class="total">
                	<!-- 화면을 불러왔을 때 할인율이 없다면 정가 표시 -->
                	<c:if test="${prod.discount eq 0}">
                    	<span class="totalPrice"><fmt:formatNumber value="${prod.price}" pattern="#,###" /></span>
                    </c:if>
                    <!-- 화면을 불러왔을 때 할인율이 없다면 할인가 표시 -->
                	<c:if test="${prod.discount ne 0}">
                    	<span class="totalPrice"><fmt:formatNumber value="${prod.disPrice}" pattern="#,###" /></span>
                    </c:if>
                    <em>총 상품금액</em>
                </div>
                <form id="formCart" action="" method="post">
                	<!-- 현재 테스트용 아이디 나중에 sessUser로 수정 -->
	                <input type="hidden" id="uid" name="uid" value="${sessUser.uid}">
	                <input type="hidden" id="thumb1" name="thumb1" value="${prod.thumb1}">
	                <input type="hidden" id="prodNo" name="prodNo" value="${prod.prodNo}">
	                <input type="hidden" id="prodName" name="prodName" value="${prod.prodName}">
	                <input type="hidden" id="descript" name="descript" value="${prod.descript}">
	                <input type="hidden" id="inputCount" name="inputCount" value="1">
			        <input type="hidden" id="org_price" name="org_price" value="${prod.price}">
			        <input type="hidden" id="dis_price" name="dis_price" value="${prod.disPrice}">
			        <input type="hidden" id="discount" name="discount" value="${prod.discount}">
			        <input type="hidden" id="point" name="point" value="${prod.point}">
			        <input type="hidden" id="delivery" name="delivery" value="${prod.delivery}">
			        <input type="hidden" id="totalPrice" name="totalPrice" value="${prod.price}">
			        <input type="hidden" id="cate1" name="cate1" value="${cate1}">
			        <input type="hidden" id="cate2" name="cate2" value="${cate2}">
			        <input type="hidden" id="ctxPath" name="ctxPath" value="${ctxPath}">
			        <input type="hidden" id="target" name="target" value="cart">
		        </form>
                <div class="button">
                    <input type="button" class="cart" value="장바구니">
                    <input type="button" class="order" value="구매하기">
                </div>
            </div>
        </article>
        <!-- 상품 정보 내용 -->
        <article class="detail">
            <nav>
                <h1>상품정보</h1>
            </nav>
            <!-- 상품상세페이지 이미지 -->
            <img src="${ctxPath}/thumb/${prod.prodCate1}/${prod.prodCate2}/${prod.detail}" alt="상세페이지1">
            <!-- <img src="./images/860x460.png" alt="상세페이지2"> -->
            <!-- <img src="./images/860x460.png" alt="상세페이지3"> -->
        </article>
        <!-- 상품 정보 제공 고시 내용 -->
        <article class="notice">
            <nav>
                <h1>상품 정보 제공 고시</h1>
                <p>[전자상거래에 관한 상품정보 제공에 관한 고시] 항목에 의거 등록된 정보입니다.</p>
            </nav>
            <table border="0">
                <tbody>
                    <tr>
                        <td>상품번호</td>
                        <td>${prod.prodNo}</td>
                    </tr>
                    <tr>
                        <td>상품상태</td>
                        <td>${prod.status}</td>
                    </tr>
                    <tr>
                        <td>부가세 면세여부</td>
                        <td>${prod.duty}</td>
                    </tr>
                    <tr>
                        <td>영수증발행</td>
                        <td>${prod.receipt}</td>
                    </tr>
                    <tr>
                        <td>사업자구분</td>
                        <td>${prod.bizType}</td>
                    </tr>
                    <tr>
                        <td>브랜드</td>
                        <td>${prod.prodCompany}</td>
                    </tr>
                    <tr>
                        <td>원산지</td>
                        <td>${prod.origin}</td>
                    </tr>
                </tbody>
            </table>
            <table border="0">
                <tbody>
                    <tr>
                        <td>제품소개</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>색상</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>치수</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>제조자/수입국</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>제조국</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>취급시 주의사항</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>제조연월</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>품질보증기준</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>A/S 책임자와 전화번호</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td>주문후 예상 배송기간</td>
                        <td>상세페이지 참고</td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            구매, 교환, 반품, 배송, 설치 등과 관련하여 추가비용, 제한조건 등의 특이사항이 있는 경우
                        </td>
                    </tr>
                </tbody>
            </table>
            <p class="notice">
                소비자가 전자상거래등에서 소비자 보호에 관한 법률 제 17조 제1항 또는 제3항에 따라
                 청약철회를 하고 동법 제 18조 제1항 에 따라 청약철회한 물품을 판매자에게 
                반환하였음에도 불구 하고 결제 대금의 환급이 3영업일을
                 넘게 지연된 경우, 소비자 는 전자상거래등에서 소비자보호에 관한 법률 제18조 제2항 및
                  동법 시행령 제21조 2에 따라 지연일수에 대하여 전상법 시행령으로 정하는 
                이율을 곱하여 산정한 지연이자(“지연배상금”)를 신청할 수 있습니다. 아울러, 교환∙반품∙보증
                 및 결제대금의 환급신청은 [나의쇼핑정보]에서 하실 수 있으며,
                 자세한 문의는 개별 판매자에게 연락하여 주시기 바랍니다.
            </p>
        </article>
        <!-- 상품 리뷰 내용 -->
        <article class="review ${(prod.review ne 0)?'yes':'no'}">
            <nav>
                <h1>상품리뷰</h1>
            </nav>
            <ul>
            
            	<c:forEach var="re" items="${review}">
                <li>
                    <div>
                        <h5 class="rating star${re.rating}">${re.prodName}</h5>
                        <span>${re.maskingUid} ${re.rdate}</span>
                    </div>
                    <h3>${re.prodName}</h3>
                    <p>
                        ${re.content}
                    </p>
                </li>
                </c:forEach>
                <c:if test="${review.size() < 1}">
                    <p style="font-size: 14px; text-align: center; margin: 30px 0">
                        작성된 리뷰가 없습니다.
                    </p>
                </c:if>
                
            </ul>
            <div class="paging">
            	<c:if test="${pageGroupStart > 1}">
                <span class="prev">
                    <a href="${ctxPath}/product/view.do?cate1=${cate1}&cate2=${cate2}&prodNo=${prod.prodNo}&pg=${pageGroupStart - 1}">〈&nbsp;이전</a>
                </span>
                </c:if>
                
                <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">
                <span class="num">
                    <a href="${ctxPath}/product/view.do?cate1=${cate1}&cate2=${cate2}&prodNo=${prod.prodNo}&pg=${i}" class="${currentPage == i?'on':'off'}">${i}</a>
                </span>
                </c:forEach>
                
                <c:if test="${pageGroupEnd < lastPageNum}">
                <span class="next">
                    <a href="${ctxPath}/product/view.do?cate1=${cate1}&cate2=${cate2}&prodNo=${prod.prodNo}&pg=${pageGroupEnd + 1}">다음&nbsp;〉</a>
                </span>
                </c:if>
            </div>
        </article>
    </section>
    <!-- 상품 상세페이지 끝 -->
</main>
<%@ include file="./_footer.jsp" %>