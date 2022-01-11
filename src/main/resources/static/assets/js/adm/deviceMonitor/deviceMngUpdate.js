$(document).ready(function() {
    // 수정버튼 클릭시
    $('#update').click(async function () {
        var seq = $(this).data('val');

        var param = {
            serialNo: $('#serialNo').val(),
            deviceNm: $('#deviceNm').val(),
            deviceLoc: $('#deviceLoc').val(),
            volume: $('#volume').val(),
            ipAddr: $('#ipAddr').val()
        }
        if($common.validationFocus(param)) {
            $common.patchAjax('/adm/deviceMonitor/deviceMng/update/' + seq, param, '장치를 수정하였습니다.');
        }
    });
});

