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
	
	$('.csWrite').click(function(){
		   window.location.href = "${ctxPath}/admin/faq/write.do?group=2"; 
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
	        url: "${ctxPath}/admin/faq/seletDelete.do",
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

    // Function to update the FAQ list based on selected categories
    function updateFAQList() {
        const boardCate2 = cate2.val();
        const boardCate3 = cate3.val();

        if (boardCate2 !== '0' && boardCate3 !== '0') {
            const url = "${ctxPath}/admin/faq/list.do?group=2&pg=1&cate1=" + boardCate2 + "&cate2=" + boardCate3;
            window.location.href = url; // Redirect to the updated URL
        }
    }

    $(cate2).change(function() {
        const selectedCate2 = $(this).val();
        const jsonData = {
            "jsonCate2": selectedCate2 
        }

        $.ajax({
            url: './list.do',
            type: 'post',
            data: jsonData,
            dataType: 'json',
            success: function(data) {
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

                updateFAQList();
            }
        });
    });

    $('#boardCate3').change(updateFAQList);

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
            <h3>자주묻는 질문</h3>
            <p>
                HOME > 고객센터 > <strong>자주묻는질문</strong>
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
            <table class="faq">
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
                <tr class="row">
                    <td><input type="checkbox" name="chk"></td>
                    <td>${faq.no}</td>
                    <td>${faq.cate1_name}</td>
                    <td>${faq.cate2_name}</td>
                    <td><a href="${ctxPath}/admin/faq/view.do?group=2&no=${faq.no}">${faq.title}</a></td>
                    <td>${faq.rdate}</td>
                    <td>
                        <a href="${ctxPath}/admin/faq/delete.do?group=2&pg=1&no=${faq.no}">[삭제]</a>
                        <a href="${ctxPath}/admin/faq/modify.do?no=${faq.no}">[수정]</a>
                    </td>                  
                </tr>
                </c:forEach>
            </table>
            
            <input type="button" value="선택 삭제" class="csDelete"/>
            <input type="button" value="글 작성" class="csWrite"/>
	
        </section>
    </section>
</main>
<%@ include file="../_footer.jsp" %>