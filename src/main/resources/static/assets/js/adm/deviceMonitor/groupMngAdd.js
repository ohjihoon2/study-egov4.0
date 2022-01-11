$(document).ready(function() {
    // 코드생성 버튼 클릭시
    $('#groupCodeCreate').click(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");

        $.ajax({
            type: "POST",
            url: "/adm/groupMng/createGroupCode",
            contentType: "application/json",
            // 토큰 사용시
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            success: function (res) {
                $('#groupCode').val(res);
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                return alert("네트워크 통신을 실패하였습니다.");
            }
        });
    });
    //주소 검색 버튼 클릭시
    $('#adrSearch').click(function () {
        adrSearch()
    });

    // 추가하기 버튼 클릭시
    $('#add').click(function () {
        var param = {
            groupCode: $('#groupCode').val(),
            groupName: $('#groupName').val(),
            bizNo: $('#bizNo').val(),
            ceoNm: $('#ceoNm').val(),
            tel: $('#tel').val(),
            groupType: $('#groupType').val(),
            addr: $('#addr').val(),
            sigungu: $('#sigungu').val(),
            sido: $('#sido').val(),
            dong: $('#dong').val(),
            addrDetail: $('#addrDetail').val(),
            ipAddr: $('#ipAddr').val()
        }

        if($common.validationFocus(param)) {
            $common.postAjax('/adm/groupMng/add', param, '업체를 추가하였습니다.');
        }
    });
});

//주소검색
function adrSearch() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var roadAddr = data.roadAddress; // 도로명 주소 변수
            var extraRoadAddr = ''; // 참고 항목 변수

            // 법정동명이 있을 경우 추가한다. (법정리는 제외)
            // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
            if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                extraRoadAddr += data.bname;
            }

            // 건물명이 있고, 공동주택일 경우 추가한다.
            if(data.buildingName !== '' && data.apartment === 'Y'){
                extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
            }

            // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
            if(extraRoadAddr !== ''){
                extraRoadAddr = ' (' + extraRoadAddr + ')';
            }
            // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
            if(roadAddr !== ''){
                document.getElementById("addr").value = roadAddr + extraRoadAddr;
            }

            // 날씨API를 위한정보
            if(data.bname.indexOf('동') != -1) {
                document.getElementById("dong").value = data.bname;
            }
            else {
                document.getElementById("dong").value = data.bname1;
            }
            document.getElementById('sido').value = data.sido;
            document.getElementById("sigungu").value = data.sigungu;

        }
    }).open();
}

