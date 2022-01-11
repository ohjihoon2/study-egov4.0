$(document).ready(function() {
    // 장치관리 바로가기 클릭시
    $('#device').click(function () {
        var seq = $('#seq').val();
        $common.goPage('/adm/deviceMonitor/deviceMng/' + seq);
    });

    // 삭제버튼 클릭시
    $('#delete').click(function () {
        if(confirm("정말로 삭제 하시겠습니까?")) {
            var seq = $('#seq').val();
            $common.deleteAjax('/adm/groupMng/delete/' + seq, '업체정보를 삭제하였습니다.')
        }
    });

    // 수정하기 클릭시
    $('#update').click(function () {
        var seq = $('#seq').val();
        $common.goPage('/adm/groupMng/update/' + seq);
    });
});
