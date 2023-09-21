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
                <select name="search">
                    <option value="search1">상품명</option>
                    <option value="search1">상품코드</option>
                    <option value="search1">제조사</option>
                    <option value="search1">판매자</option>
                </select>
                <input type="text" name="search">
            </div>
            <form id="formCheck" action="${ctxPath}/admin/faq/delete.do" method="get">
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
                    <td>${faq.discount}</td>
                    <td><a href="${ctxPath}/admin/faq/view.do?group=2&no=${faq.no}">${faq.title}</a></td>
                    <td>${faq.rdate}</td>
                    <td>
                        <a href="${ctxPath}/admin/faq/delete.do?no=${faq.no}">[삭제]</a>
                        <a href="${ctxPath}/admin/faq/modify.do?no=${faq.no}">[수정]</a>
                    </td>
                    
                </tr>
                </c:forEach>
            </table>
            </form>
            
            <input type="button" value="선택 삭제" class="faqDelete"/>
			
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

        <p class="ico info">
            <strong>Tip!</strong>
            전자상거래 등에서의 상품 등의 정보제공에 관한 고시에 따라 총 35개 상품군에 대해 상품 특성 등을 양식에 따라 입력할 수 있습니다.
        </p>
    </section>
</main>
<%@ include file="../_footer.jsp" %>