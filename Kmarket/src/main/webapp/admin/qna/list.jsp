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
	
	$('.qnaDelete').click(function() {
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
	        url: "/Kmarket/admin/qna/delete.do",
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
	
});

</script>

<main>
    <%@ include file="../_aside.jsp" %>
    <section id="admin-qna-list">
        <nav>
            <h3>문의사항</h3>
            <p>
                HOME > 고객센터 > <strong>문의목록</strong>
            </p>
        </nav>
        <section>
            <div>
                <select name="search">
                    <option value="search1">상품명</option>
                    <option value="search1">상품코드</option>
                    <option value="search1">제조사</option>
                    <option value="search1">판매자</option>
                </select>
                <select name="search">
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
                    <th>작성자</th>
                    <th>날짜</th>
                    <th>관리</th>
                </tr>
				<c:forEach var="qna" items="${qnas}">
                <tr>
                    <td><input type="checkbox" name="chk"></td>
                    <td>${qna.no}</td>
                    <td>${qna.cate1_name}</td>
                    <td>${qna.cate2_name}</td>
                    <td><a href="${ctxPath}/admin/qna/view.do?group=3&no=${qna.no}">${qna.title}</a></td>
                    <td>${qna.uid}</td>
                    <td>${qna.rdate}</td>
                    <td>
                        <a href="${ctxPath}/admin/qna/delete.do?no=${qna.no}">[삭제]</a>
                    </td>                  
                </tr>
                </c:forEach>
            </table>
            
            <input type="button" value="선택 삭제" class="qnaDelete"/>
			
            <div class="paging">
            <c:if test="${pageGroupStart > 1}">
                <span class="prev">
                    <a href="${ctxPath}/admin/qna/list.do?group=3&pg=${pageGroupStart - 1}"><&nbsp;이전</a>
                </span>
            </c:if>  
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">     
                <span class="num">
                    <a href="${ctxPath}/admin/qna/list.do?group=3&pg=${i}" class="num ${currentPage == i ? 'on current':''}">${i}</a>
                </span>
            </c:forEach>  
            <c:if test="${pageGroupEnd < lastPageNum}">   
                <span class="next">
                    <a href="${ctxPath}/admin/qna/list.do?group=3&pg=${pageGroupEnd + 1}">다음&nbsp;></a>
                </span>
            </c:if>    
            </div>
        </section>
    </section>
</main>
<%@ include file="../_footer.jsp" %>