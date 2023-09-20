<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
	
	$('.noticeDelete').click(function(){
		$('#formNotice').submit();
	});
	
	 $('.noticeWrite').click(function(){
	    window.location.href = "/Kmarket/admin/notice/write.do?group=1"; 
	 });
});

</script>

<main>
    <%@ include file="../_aside.jsp" %>
    <section id="admin-notice-list">
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
            <form id="formNotice" action="#" method="get">
            <table>
                <tr>
                    <th><input type="checkbox" name="all"></th>
                    <th>번호</th>
                    <th>유형</th>
                    <th>제목</th>
                    <th>날짜</th>
                    <th>관리</th>
                </tr>
				<c:forEach var="notice" items="${notices}">
				<tr>
                    <td><input type="checkbox" name="chk"></td>
                    <td>${notice.no}</td>
                    <td>${notice.cate1_name}</td>
                    <td><a href="${ctxPath}/admin/notice/view.do?group=1&no=${notice.no}">${notice.title}</a></td>
                   	<td>${notice.rdate}</td>
                    <td>
                        <a href="#">[삭제]</a>
                        <a href="#">[수정]</a>
                    </td>                   
                </tr>
                </c:forEach>
            </table>
            </form>
            
            <input type="button" value="선택 삭제" class="noticeDelete"/>
            <input type="button" value="공지 작성" class="noticeWrite"/>
			
            <div class="paging">
            <c:if test="${pageGroupStart > 1}">
                <span class="prev">
                    <a href="${ctxPath}/admin/notice/list.do?group=1&pg=${pageGroupStart - 1}"><&nbsp;이전</a>
                </span>
            </c:if>  
            <c:forEach var="i" begin="${pageGroupStart}" end="${pageGroupEnd}">     
                <span class="num">
                    <a href="${ctxPath}/admin/notice/list.do?group=1&pg=${i}" class="num ${currentPage == i ? 'on current':''}">${i}</a>
                </span>
            </c:forEach>  
            <c:if test="${pageGroupEnd < lastPageNum}">   
                <span class="next">
                    <a href="${ctxPath}/admin/notice/list.do?group=1&pg=${pageGroupEnd + 1}">다음&nbsp;></a>
                </span>
            </c:if>    
            </div>
        </section>
    </section>
</main>
<%@ include file="../_footer.jsp" %>