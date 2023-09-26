<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../_header.jsp" %>
<script>
$(function(){
	
	$('input[name=all]').change(function(){
		const isChecked = $(this).is(':checked');
		
		if(isChecked){
			// 전체선택
			$('input[name=chk]').prop('checked', true);
		}else{
			// 전체해제
			$('input[name=chk]').prop('checked', false);
		}
		
	});
	
	$('.productDelete').click(function() {
        var checkBoxArr = [];
        
        // 체크된 체크박스를 순회하면서 데이터 추출
        $('input[name=chk]:checked').each(function() {
            var $row = $(this).closest('tr'); // 현재 체크박스가 속한 행

            var sellerValue = $row.find('td:eq(8)').text(); // 8번째 열의 데이터 (판매자)
            var prodNoValue = $row.find('td:eq(3)').text(); // 3번째 열의 데이터 (상품코드)

            checkBoxArr.push(sellerValue, prodNoValue);
        });

        console.log(checkBoxArr);
		
        var confirmDelete = confirm("선택한 상품을 삭제하시겠습니까?");
        
	    $.ajax({
	        type: "GET",
	        url: "${ctxPath}/admin/product/selcteDelete.do",
	        traditional: true,
	        data: {
	            checkBoxArr: checkBoxArr
	        },
	        success: function(result) {
	            console.log(result);
	        },
	        error: function(xhr, status, error) {
	            alert(error);
	        }
	    });
	});
	
	$('.btnSearch').click(function() {

		var seller = $('td:eq(8)').text();
		
	    
	    var search = $('input[name=search]').val();
	    // 선택한 검색 카테고리 값을 가져옴
	    var searchCategory = $('#searchCategory').val();
	    
	    // 서버로 전송
	    if(${sessUser.level ne 7}){
	    	 window.location.href = "${ctxPath}/admin/product/list.do?search=" + search + "&seller=" + seller + "&searchCategory=" + searchCategory;
	    } else {
	    	window.location.href = "${ctxPath}/admin/product/list.do?search=" + search + "&searchCategory=" + searchCategory;
	    }
	   
	    	
	});
	
	$(".dtnProductDelete").click(function (e) {
		e.preventDefault();
		$('.delete').submit();
	})

});

</script>

<main>
    <%@ include file="../_aside.jsp" %>
    <section id="admin-product-list">
        <nav>
            <h3>상품목록</h3>
            <p>
                HOME > 상품관리 > <strong>상품목록</strong>
            </p>
        </nav>
        <section>
            <div>
                <select name="searchCategory" id="searchCategory">
				    <option value="search1">상품명</option>
				    <option value="search2">상품코드</option>
				    <option value="search3">제조사</option>
				    <option value="search4">판매자</option>
				</select>
                <input type="text" name="search">
                <input type="button" value="검색" class="btnSearch">
            </div>
            <table>
                <tr>
                    <th><input type="checkbox" name="all"></th>
                    <th>이미지</th>
                    <th>상품명</th>
                    <th style="width: 50px">상품코드</th>
                    <th style="width: 50px">판매가격</th>
                    <th>할인율</th>
                    <th>포인트</th>
                    <th>재고</th>
                    <th>판매자</th>
                    <th>조회</th>
                    <th>관리</th>
                </tr>
				<c:forEach var="product" items="${products}">
                <tr>
                    <td><input type="checkbox" name="chk"></td>
                    <td><img src="${ctxPath}/thumb/${product.prodCate1}/${product.prodCate2}/${product.thumb1}" class="thumb"></td>
                    <td>${product.prodName}</td>
                    <td style="text-align: center;">${product.prodNo}</td>
                    <td style="text-align: center;">${product.price}</td>
                    <td>${product.discount}</td>
                    <td>${product.point}</td>
                    <td>${product.stock}</td>
                    <td>${product.seller}</td>
                    <td>${product.hit}</td>
                    <td>
                    	<form action="${ctxPath}/admin/product/delete.do" method="post" class="delete">
                    	<input type="hidden" name="no" value="${product.prodNo}">
                        <a href="#" class="dtnProductDelete">[삭제]</a>
                        </form>
                    </td>
                    
                </tr>
                </c:forEach>
            </table>
      
            <input type="button" value="선택 삭제" class="productDelete"/>
            
            <c:if test="${search == null}">
            <div class="paging">
            <c:if test="${pageGroupStart > 1}">
                <span class="prev">
                    <a href="${ctxPath}/admin/product/list.do?pg=${pageGroupStart - 1}"><&nbsp;이전</a>
                </span>
            </c:if>  
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">     
                <span class="num">
                    <a href="${ctxPath}/admin/product/list.do?pg=${i}" class="num ${currentPage == i ? 'on current':''}">${i}</a>
                </span>
            </c:forEach>  
            <c:if test="${pageGroupEnd < lastPageNum}">   
                <span class="next">
                    <a href="${ctxPath}/admin/product/list.do?pg=${pageGroupEnd + 1}">다음&nbsp;></a>
                </span>
            </c:if>    
            </div>
            </c:if>
             <c:if test="${search != null}">
            <div class="paging">
            <c:if test="${pageGroupStart > 1}">
                <span class="prev">
                    <a href="${ctxPath}/admin/product/list.do?pg=${pageGroupStart - 1}&search=${search}&searchCategory=${searchCategory}"><&nbsp;이전</a>
                </span>
            </c:if>  
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">     
                <span class="num">
                    <a href="${ctxPath}/admin/product/list.do?pg=${i}&search=${search}&searchCategory=${searchCategory}" class="num ${currentPage == i ? 'on current':''}">${i}</a>
                </span>
            </c:forEach>  
            <c:if test="${pageGroupEnd < lastPageNum}">   
                <span class="next">
                    <a href="${ctxPath}/admin/product/list.do?pg=${pageGroupEnd + 1}&search=${search}&searchCategory=${searchCategory}">다음&nbsp;></a>
                </span>
            </c:if>    
            </div>
             </c:if>
        </section>

        <p class="ico info">
            <strong>Tip!</strong>
            전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
        </p>
    </section>
</main>
<%@ include file="../_footer.jsp" %>