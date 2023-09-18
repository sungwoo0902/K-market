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
	
	$('.productDelete').click(function(){
		$('#formCheck').submit();
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
                    <option value="search1">전체</option>
                    <option value="search2">고객서비스</option>
                    <option value="search3">안전거래</option>
                    <option value="search4">위해상품</option>
                    <option value="search5">이벤트당첨</option>
                </select>
                <input type="text" name="search">
            </div>
            <form id="formCheck" action="${ctxPath}/admin/notice/delete.do" method="get">
            <table>
                <tr>
                    <th><input type="checkbox" name="all"></th>
                    <th>번호</th>
                    <th>유형</th>
                    <th>제목</th>
                    <th>조회</th>
                    <th>날짜</th>
                    <th>관리</th>
                </tr>
				<c:forEach var="notice" items="${notices}">
				<tr>
                    <td><input type="checkbox" name="chk"></td>
                    <th>${notices.no}</th>
                    <th>${notices.cate2}</th>
                    <th>${notices.title}</th>
                    <th>${notices.hit}</th>
                    <th>${notices.rDate}</th>
                    <td>
                        <a href="${ctxPath}/admin/notice/delete.do?uid=${product.seller}&no=${product.prodNo}">[삭제]</a>
                        <a href="#">[수정]</a>
                    </td>                   
                </tr>
                </c:forEach>
            </table>
            </form>
            
            <input type="button" value="선택 삭제" class="productDelete"/>
			
            <div class="paging">
            <c:if test="${pageGroupStart > 1}">
                <span class="prev">
                    <a href="${ctxPath}/admin/product/list.do?pg=${pageGroupStart - 1}&seller=${sessUser.uid}"><&nbsp;이전</a>
                </span>
            </c:if>  
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">     
                <span class="num">
                    <a href="${ctxPath}/admin/product/list.do?pg=${i}&seller=${sessUser.uid}" class="num ${currentPage == i ? 'on current':''}">${i}</a>
                </span>
            </c:forEach>  
            <c:if test="${pageGroupEnd < lastPageNum}">   
                <span class="next">
                    <a href="${ctxPath}/admin/product/list.do?pg=${pageGroupEnd + 1}&seller=${sessUser.uid}">다음&nbsp;></a>
                </span>
            </c:if>    
            </div>
        </section>
    </section>
</main>
<%@ include file="../_footer.jsp" %>