$(document).ready(function() {
    // 삭제버튼 클릭시
    $('#delete').click(function () {
        var seq = $(this).data('val');
        // 미정
        // $common.goPage('/adm/deviceMonitor/deviceMng/' + seq);
    });
    // 수정버튼 클릭시
    $('#update').click(function () {
        var seq = $(this).data('val');
        $common.goPage('/adm/deviceMonitor/deviceMng/update/' + seq);
    });
});

