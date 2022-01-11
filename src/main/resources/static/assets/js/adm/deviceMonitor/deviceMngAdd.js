$(document).ready(function() {
    // 장치추가 버튼 클릭시
    $('#add').click(function () {
        var seq = $(this).data('val');

        var param = {
            serialNo: $('#serialNo').val(),
            deviceNm: $('#deviceNm').val(),
            deviceLoc: $('#deviceLoc').val(),
            volume: $('#volume').val(),
            ipAddr: $('#ipAddr').val()
        }

        if($common.validationFocus(param)) {
            $common.postAjax('/adm/deviceMonitor/deviceMng/add/' + seq, param, '장치를 추가하였습니다.');
        }
    });
});

