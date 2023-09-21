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
	
	$('.faqDelete').click(function() {
        var checkBoxArr = [];
        
        // 체크된 체크박스를 순회하면서 데이터 추출
        $('input[name=chk]:checked').each(function() {
            var $row = $(this).closest('tr'); // 현재 체크박스가 속한 행

            var prodNoValue = $row.find('td:eq(1)').text(); // 3번째 열의 데이터 (상품코드)

            checkBoxArr.push(prodNoValue);
        });

        console.log(checkBoxArr);
		
        var confirmDelete = confirm("선택한 게시글을 삭제하시겠습니까?");
        
	    $.ajax({
	        type: "GET",
	        url: "/Kmarket/admin/faq/delete.do",
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
	
	$('.faqWrite').click(function(){
		window.location.href = "/Kmarket/admin/faq/write.do?group=2"; 
	});
	
	const cate1 = $('#category1');
	const cate2 = $('#category2');
	let selectCate = null;
	
	$(cate1).change(function(){
		const selectedCate1 = $(this).val();
		
		console.log(selectedCate1);
		
		$.ajax({
			url: '/Kmarket/category.do',
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
	
	
});

</script>

<main>
    <%@ include file="../_aside.jsp" %>
    <section id="admin-faq-list">
        <nav>
            <h3>자주묻는 질문</h3>
            <p>
                HOME > 고객센터 > <strong>자주묻는질문</strong>
            </p>
        </nav>
        <section>
            <div>
                <select name="search">
                	<option value="search1">전체</option>
                    <option value="search1">상품명</option>
                    <option value="search1">상품코드</option>
                    <option value="search1">제조사</option>
                    <option value="search1">판매자</option>
                </select>
                <select name="search">
                	<option value="search1">전체</option>
                    <option value="search1">상품명</option>
                    <option value="search1">상품코드</option>
                    <option value="search1">제조사</option>
                    <option value="search1">판매자</option>
                </select>
            </div>
            <table>
                <tr>
                    <th><input type="checkbox" name="all"></th>
					<th>번호</th>
                    <th>1차 유형</th>
                    <th>2차 유형</th>
                    <th>제목</th>
                    <th>날짜</th>
                    <th>관리</th>
                </tr>
				<c:forEach var="faq" items="${faqs}">
                <tr>
                    <td><input type="checkbox" name="chk"></td>
                    <td>${faq.no}</td>
                    <td>${faq.cate1_name}</td>
                    <td>${faq.cate2_name}</td>
                    <td><a href="${ctxPath}/admin/faq/view.do?group=2&no=${faq.no}">${faq.title}</a></td>
                    <td>${faq.rdate}</td>
                    <td>
                        <a href="${ctxPath}/admin/faq/delete.do?no=${faq.no}">[삭제]</a>
                        <a href="${ctxPath}/admin/faq/modify.do?no=${faq.no}">[수정]</a>
                    </td>                  
                </tr>
                </c:forEach>
            </table>
            
            <input type="button" value="선택 삭제" class="faqDelete"/>
            <input type="button" value="글 작성" class="faqWrite"/>
			
            <div class="paging">
            <c:if test="${pageGroupStart > 1}">
                <span class="prev">
                    <a href="${ctxPath}/admin/faq/list.do?group=2&pg=${pageGroupStart - 1}"><&nbsp;이전</a>
                </span>
            </c:if>  
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">     
                <span class="num">
                    <a href="${ctxPath}/admin/faq/list.do?group=2&pg=${i}" class="num ${currentPage == i ? 'on current':''}">${i}</a>
                </span>
            </c:forEach>  
            <c:if test="${pageGroupEnd < lastPageNum}">   
                <span class="next">
                    <a href="${ctxPath}/admin/faq/list.do?group=2&pg=${pageGroupEnd + 1}">다음&nbsp;></a>
                </span>
            </c:if>    
            </div>
        </section>
    </section>
</main>
<%@ include file="../_footer.jsp" %>