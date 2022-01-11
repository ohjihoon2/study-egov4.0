$(document).ready(function() {
    // 장치세부관리 탭 클릭시
    $('.nav-btn-box button:not(.active)').click(function () {
        $('.nav-btn-box button').removeClass('active');
        $(this).addClass('active');
        pageParam();
    });

    // 장소별 탭 클릭시
    $('#search li:not(.on)').click(function () {
        $('#search li').removeClass('on');
        $(this).addClass('on');
        pageParam();
    }) ;

    //페이지네이션클릭시
    $('#pagination ul li:not(.disabled, .on)').click(function () {
        $('#pagination ul li').removeClass('on');
        $(this).addClass('on');
        pageParam();
    });


    // 상세페이지 이동
    $('.content-card > div').click(function () {
        var seq = $(this).data('val');
        $common.goPage('/adm/deviceMonitor/deviceMngDetail/' + seq);
    });

    // 장치세부관리 이동
    $('#deviceMng').click(function () {
        var seq = $(this).data('val');
        $common.goPage('/adm/deviceMonitor/deviceMng/' + seq);
    });
});

//페이지 파람설정
function pageParam() {
    var seq = $('#seq').val();
    var param = {
        status : $('.nav-btn-box').find('.active').data('val'),
        search : $('#search li.on').data('val'),
        pageNum : $('#pagination ul .on').find('a').data('val')
    };
    $common.getGoPage('/adm/deviceMonitor/'+seq , param);
}

