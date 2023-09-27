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
	
	$('.csDelete').click(function() {
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
	        url: "/Kmarket/admin/qna/seletDelete.do",
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
	
	// 2차 상세 유형 불러오기
    const cate2 = $('#boardCate2');
    const cate3 = $('#boardCate3');

    function updateQNAList() {
        const boardCate2 = cate2.val();
        const boardCate3 = cate3.val();

        if (boardCate2 !== '0' && boardCate3 !== '0') {
            const url = "${ctxPath}/admin/qna/list.do?group=3&pg=1&cate1=" + boardCate2 + "&cate2=" + boardCate3;
            window.location.href = url; // Redirect to the updated URL
        }
    }

    $(cate2).change(function() {
        const selectedCate2 = $(this).val();
        const jsonData = {
            "jsonCate2": selectedCate2 
        }

        // AJAX request to update the second category dropdown
        $.ajax({
            url: './list.do',
            type: 'post',
            data: jsonData,
            dataType: 'json',
            success: function(data) {
                // Update the second category dropdown options
                cate3.empty();
                cate3.append($('<option>', {
                    value: '0',
                    text: '2차 선택'
                }));
                for (let i = 0; i < data.categorys.length; i++) {
                    const category = data.categorys[i];
                    cate3.append($('<option>', {
                        value: category.cate2,
                        text: category.cate2_name
                    }));
                }

                updateQNAList();
            }
        });
    });

    $('#boardCate3').change(updateQNAList);
	
	// 상세유형 선택 안 할 시 insert 진행 막기
	$('.btnSubmit').click(function(e) {
		e.preventDefault();
		if(cate2.val() < 1) {
			alert('1차 상세유형을 선택해주세요.');
			return false;
		}
		
		if(cate3.val() < 1) {
			alert('2차 상세유형을 선택해주세요.');
			return false;
		}
		
		$('#qna_write_submit').submit();
	})
	
});

</script>

<main>
    <%@ include file="../_aside.jsp" %>
    <section id="admin-cs-list">
        <nav>
            <h3>문의사항</h3>
            <p>
                HOME > 고객센터 > <strong>문의목록</strong>
            </p>
        </nav>
        <section>
            <div>
				<!-- type1은 회원게시판에서 클릭했을 시 회원으로 설정되게끔. -->
				<select name="boardCate2" id="boardCate2">
					<option value="0">1차 선택</option>
					<c:forEach var="main_cate" items="${cate1List}">
						<option value="${main_cate.cate1}" ${cate1 eq main_cate.cate1?'selected':''}>${main_cate.cate1_name}</option>
					</c:forEach>
				</select>
				<!--  type2는 jsonData로 받아와서 동적처리. -->
				<select name="boardCate3" id="boardCate3">
					<option value="0">2차 선택</option>
					<c:forEach var="sub_cate" items="${cate2List}">
						<option value="${sub_cate.cate2}">${sub_cate.cate2_name}</option>
					</c:forEach>
				</select>
            </div>
            <table class="qna">
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
            
            <input type="button" value="선택 삭제" class="csDelete"/>
            

		<c:if test="${cate1 == null && cate2 == null}">
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
		</c:if>
		
		<c:if test="${cate1 != null && cate2 != null}">
		<div class="paging">
		    <c:if test="${pageGroupStart > 1}">
		        <span class="prev">
		            <a href="${ctxPath}/admin/qna/list.do?group=3&pg=${pageGroupStart - 1}&cate1=${cate1}&cate2=${cate2}"><&nbsp;이전</a>
		        </span>
		    </c:if>  
		    <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">     
		        <span class="num">
		            <a href="${ctxPath}/admin/qna/list.do?group=3&pg=${i}&cate1=${cate1}&cate2=${cate2}" class="num ${currentPage == i ? 'on current':''}">${i}</a>
		        </span>
		    </c:forEach>  
		    <c:if test="${pageGroupEnd < lastPageNum}">   
		        <span class="next">
		            <a href="${ctxPath}/admin/qna/list.do?group=3&pg=${pageGroupEnd + 1}&cate1=${cate1}&cate2=${cate2}">다음&nbsp;></a>
		        </span>
		    </c:if>    
		</div>
		</c:if>


        </section>
    </section>
</main>
<%@ include file="../_footer.jsp" %>