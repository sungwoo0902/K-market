/**
 * 
 */
// 메뉴
   // jQuery를 사용하여 클릭 이벤트 처리
    $(document).ready(function () {
        $("#gnb li").click(function () {
            // 클릭한 항목의 하위 메뉴를 토글하여 표시/숨김 처리합니다.
            var subMenu = $(this).find("ol");
            if (subMenu.is(":visible")) {
                subMenu.slideUp(200);
            } else {
                subMenu.slideDown(200);
            }
        });
    });
