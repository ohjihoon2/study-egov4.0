$(document).ready(function() {

    // 검색시
    $('#keyword').keydown(function (key) {
        if(key.keyCode == 13) {
            var param = {
                keyword : $('#keyword').val(),
                pageNum : 1
            }
            $common.getGoPage('/adm/groupMng', param)
        }
    }) ;

    //페이지네이션클릭시
    $('#pagination ul li:not(.disabled, .on)').click(function () {
        var seq = $('#seq').val();

        var param = {
            keyword : $('#hiddenKeyword').val(),
            pageNum : $(this).find('a').data('val')
        }
        $common.getGoPage('/adm/groupMng', param)
    });

    //상세페이지 이동
    $('#grid tbody tr').click(function () {
        var seq = $(this).data('val');
        $common.goPage('/adm/groupMng/' + seq);
    }) ;
});

