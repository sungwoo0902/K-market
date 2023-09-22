<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<aside>
        <!-- Global Navigation Bar -->
        <ul id="gnb">
            <li>
                <a href="#"><i class="fa fa-cogs" aria-hidden="true"></i>환경설정</a>
                <ol>
                    <li><a href="${ctxPath}/admin/config/info.jsp">기본환경정보</a></li>
                    <li><a href="${ctxPath}/admin/config/banner.jsp">배너관리</a></li>
                </ol>
            </li>
            <li>
                <a href="#"><i class="fas fa-store" aria-hidden="true"></i>상점관리</a>
                <ol>
                    <li><a href="#">판매자현황</a></li>
                    <li><a href="#">재고관리</a></li>
                </ol>
            </li>
            <c:if test="${sessUser.level eq 7}">
            <li>
                <a href="#"><i class="fa fa-users" aria-hidden="true"></i>회원관리</a>
                <ol>
                    <li><a href="#">회원현황</a></li>
                    <li><a href="#">포인트관리</a></li>
                    <li><a href="#">비회원관리</a></li>
                    <li><a href="#">접속자집계</a></li>
                </ol>
            </li>
            </c:if>
            <li>
                <a href="#"><i class="fas fa-box-open" aria-hidden="true"></i>상품관리</a>
                <ol id="menu">
                    <li><a href="${ctxPath}/admin/product/list.do">상품현황</a></li>
                    <li><a href="${ctxPath}/admin/product/register.do">상품등록</a></li>
                </ol>
            </li>          
            <li>
                <a href="#"><i class="fa fa-credit-card" aria-hidden="true"></i>주문관리</a>
                <ol>
                    <li><a href="#">주문현황</a></li>
                    <li><a href="#">매출현황</a></li>
                    <li><a href="#">결제관리</a></li>
                    <li><a href="#">배송관리</a></li>
                </ol>
            </li>
            <c:if test="${sessUser.level eq 7}">
            <li>
                <a href="#"><i class="fa fa-pencil-square-o" aria-hidden="true"></i>고객센터</a>
                <ol>
                    <li><a href="${ctxPath}/admin/notice/list.do?group=1&pg=1">공지사항</a></li>
                    <li><a href="${ctxPath}/admin/faq/list.do?group=2&pg=1">자주묻는질문</a></li>
                    <li><a href="${ctxPath}/admin/qna/list.do?group=3&pg=1">고객문의</a></li>
                </ol>
            </li>
            </c:if>
        </ul>
    </aside>