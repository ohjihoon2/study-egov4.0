$(document).ready(function() {
    // 장치현황 탭 클릭시
    $('.head-btn-box button:not(.active)').click(function () {
        var param = {
            status : $(this).data('val'),
            sigungu : $('#hiddenSigungu').val(),
            keyword : $('#hiddenKeyword').val(),
            pageNum : $('#pagination ul .on').find('a').data('val')
        }
        $common.getGoPage('/adm/deviceMonitor', param)
    });

    //페이지네이션클릭시
    $('#pagination ul li:not(.disabled, .on)').click(function () {
        var param = {
            status : $('.head-btn-box').find('.active').data('val'),
            sigungu : $('#hiddenSigungu').val(),
            keyword : $('#hiddenKeyword').val(),
            pageNum : $(this).find('a').data('val')
        }
        $common.getGoPage('/adm/deviceMonitor', param)
    });

    // 검색시
    $('#keyword').keydown(function (key) {
        if(key.keyCode == 13) {
            var param = {
                status : 'air',
                sigungu : $('#sigungu').val(),
                keyword : $('#keyword').val(),
                pageNum : 1
            }
            $common.getGoPage('/adm/deviceMonitor', param)
        }
    }) ;

    // 상세페이지 이동
    $('#grid tbody tr').click(function () {
        var seq = $(this).data('val');
        $common.goPage('/adm/deviceMonitor/' + seq);
    }) ;
});

