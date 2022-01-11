var systemMsg = {
    PW_SUCCESS: '사용가능한 비밀번호 입니다.',
    PW_LENGTH_ERROR: '8~12자리로 입력해주세요.',
    PW_EMPTY_ERROR: '비밀번호는 공백 없이 입력해주세요',
    PW_SPE_ERROR: '영문 숫자 특수문자 중 2가지 이상을 혼합해 주세요',
    PW_WARNING: '보안이 낮은 비밀번호입니다.',
    PW_INFO: '영문,숫자를 이용하여 8~12자리로 입력해주세요.',
    PW_CONFIRM: '비밀번호와 일치합니다.',
    PW_CONFIRM_ERROR: '비밀번호와 일치하지 않습니다.',
    REQUIRED_ERROR: '필수 입력값입니다',
    EMAIL_ERROR: '올바른 이메일 형식으로 적어주세요',
    URL_ERROR: 'http:// 혹은 https://를 적어주세요',
    USER_ID_INFO: '아이디는 6자리 ~ 12자리 이내 영문 숫자만 입력해주세요',
    USER_ID_CNT_ERROR: '아이디를 6자리 ~ 12자리 이내로 입력해주세요.',
    USER_ID_EMPTY_ERROR: '아이디는 공백없이 입력해주세요.',
    USER_ID_KO_ERROR: '아이디는 영문,숫자만 입력해주세요.',
    PHONE_ERROR: '유효하지 않는 전화번호입니다.',
}
$(document).ready(function() {
    $('.text-input-wrap').on('focus mouseover', 'input', idFocusHandler);
    $('.text-input-wrap').on('blur mouseleave', 'input', idBlurHandler);
    $('.text-input-wrap').on('input', '#userId', userIdValidationHandler);
    $('.text-input-wrap').on('input', '#userEmail', userEmailValidation);
    $('.text-input-wrap').on('input', '#userPwd', userPwdValidationHandler);
    $('.text-input-wrap').on('input', '#userPwdConfirm', userPwdConfirmValidation);
    $('.text-input-wrap').on('input', '#userPhone', userPhoneValidation);
    $('.text-input-wrap').on('click', '#checkId', checkId);
    $('.text-input-wrap').on('click', '#checkGroupCode', checkGroupCode);

    // $('.text-input-wrap input').hover(textInputMouseOverHandler,textInputMouseleaveHandler);
});

function idFocusHandler() {
    var $input = $(this);
    var val = $input.val();
    var hasFocus = $input.is(':focus');
    var name = $input.attr('name');
    if (val.length > 0) {
        if (name === 'userId') {
            userIdValidation($input);
        } else if (name === 'userPwd') {
            userPwdValidation($input);
        }
        labelAndInputOn($input);
    } else {
        if (!hasFocus) {
            labelAndInputOn($input);
            if (name === 'userId') {
                showText($input, systemMsg.USER_ID_INFO, 'info');
            } else if (name === 'userPwd') {
                showText($input, systemMsg.PW_INFO, 'info');
            }
        }
    }
}

function idBlurHandler() {
    var $input = $(this);
    var hasFocus = $input.is(':focus');
    var textBox = $input.parent().find('.input-msg');
    var hasError = textBox.hasClass('error');
    if (!hasFocus) {
        if (!hasError) {
            labelAndInputOff($input);
            removeText($input);
        }
    }
}

function labelAndInputOn($input) {
    var label = $input.prev();
    label.addClass('on');
    $input.addClass('on');
}

function labelAndInputOff($input) {
    var label = $input.prev();
    label.removeClass('on');
    $input.removeClass('on');
}

function showText($input, text, systemCode) {
    var textBox = $input.parent().find('.input-msg');
    var span = convertTextToSpan(text);
    resetInputMsgClass(textBox)
    textBox.addClass('on');
    textBox.append(span);
    textBox.addClass(systemCode);

}

function removeText($input) {
    var textBox = $input.parent().find('.input-msg');
    textBox.removeClass('on')
    textBox.find('span').remove();
}

function resetInputMsgClass(textBox) {
    textBox.removeClass('info error success');
}

function userIdValidationHandler() {
    var $input = $(this);
    userIdValidation($input);
}

function userPwdValidationHandler() {
    var $input = $(this);
    userPwdValidation($input);
}

function userIdValidation($input) {
    var val = $input.val();
    var result = checkUserId(val);
    if (val.length === 0) {
        removeText($input);
        return;
    }
    if (!result.status) {
        removeText($input);
        showText($input, result.msg, 'error');
    } else {
        removeText($input);
    }
}

function userEmailValidation() {
    var $input = $(this);
    var val = $input.val();
    var result = checkEmail(val);
    if (val.length === 0) {
        removeText($input);
        return;
    }
    if (!result.status) {
        removeText($input);
        showText($input, result.msg, 'error');
    } else {
        removeText($input);
    }
}

function userPwdValidation($input) {
    var val = $input.val();
    var result = checkPw(val);
    if (val.length === 0) {
        removeText($input);
        return;
    }
    if (!result.status) {
        removeText($input);
        showText($input, result.msg, 'error');
    } else {
        removeText($input);
    }
}

function userPwdConfirmValidation() {
    var $input = $(this);
    var val = $input.val();
    var result = checkPwEqPwConfirm(val);
    if (val.length === 0) {
        removeText($input);
        return;
    }
    console.log(result);
    if (!result.status) {
        removeText($input);
        showText($input, result.msg, 'error');
    } else {
        removeText($input);
        showText($input, result.msg, 'success');
    }
}

function userPhoneValidation() {
    var $input = $(this);
    var val = $input.val();
    var result = checkNumber(val);
    if (val.length === 0) {
        removeText($input);
        return;
    }
    if (!result.status) {
        removeText($input);
        showText($input, result.msg, 'error');
    } else {
        removeText($input);
    }
}

function convertTextToSpan(text) {
    return '<span>' + text + '</span>'
}

function checkPw(pw) {
    var obj = {};
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    if(pw.length < 8 || pw.length > 12){
        obj.status = false;
        obj.msg = systemMsg.PW_LENGTH_ERROR;
        return obj;
    }else if(pw.search(/\s/) != -1){
        obj.status = false;
        obj.msg = systemMsg.PW_EMPTY_ERROR;
        return obj;
    }else if((num < 0 && eng < 0) || (eng < 0 && spe < 0) || (spe < 0 && num < 0)){
        obj.status = false;
        obj.msg = systemMsg.PW_SPE_ERROR;
        return obj;
    }else {
        obj.status = true;
        return obj;
    }
}

function checkPwEqPwConfirm() {
    var pwVal = $('#userPwd').val();
    var pwConfirmVal = $('#userPwdConfirm').val();

    var obj = {};
    if (pwVal === pwConfirmVal) {
        obj.status = true;
        obj.msg = systemMsg.PW_CONFIRM;
        return obj;
    } else {
        obj.status = false;
        obj.msg = systemMsg.PW_CONFIRM_ERROR;
        return obj;
    }
}

function checkRequired(value) {
    return value !== undefined && value !== null && value.length > 0;
}

function checkEmail(value) {
    var obj = {};
    var check = /^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$/.test( value );
    if (check) {
        obj.status = true;
        return obj
    } else {
        obj.status = false;
        obj.msg = systemMsg.EMAIL_ERROR;
        return obj
    }
}

function checkUrl(value) {
    return /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z0-9\u00a1-\uffff][a-z0-9\u00a1-\uffff_-]{0,62})?[a-z0-9\u00a1-\uffff]\.)+(?:[a-z\u00a1-\uffff]{2,}\.?))(?::\d{2,5})?(?:[/?#]\S*)?$/i.test( value );
}

function checkNumber(value) {
    var obj = {};
    if (/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(value)) {
        obj.status = true;
        return obj;
    }
    obj.status = false;
    obj.msg = systemMsg.PHONE_ERROR;
    return obj;
}

function checkUserId(str) {
    var id = str;
    var obj = {};
    if ((id.length < 6) || (id.length > 20)) {
        obj.status = false;
        obj.msg = systemMsg.USER_ID_CNT_ERROR;
        return obj;
    }
    if (checkSpace(id)) {
        obj.status = false;
        obj.msg = systemMsg.USER_ID_EMPTY_ERROR;
        return obj;
    }
    if (!checkEngNum(id)) {
        obj.status = false;
        obj.msg = systemMsg.USER_ID_KO_ERROR;
        return obj;
    }
    obj.status = true;
    return obj;
}

function checkEngNum(str) {
    var regExp = /[a-zA-Z0-9]/g;
    if(regExp.test(str)){
        return true;
    }else{
        return false;
    }
}
function checkSpace(str) {
    if(str.search(/\s/) !== -1) {
        return true;
    }else{
        return false;
    }
}

function checkId() {
    var $input = $('#userId')
    var obj = {
        userId: $input.val()
    };
    var jqxhr = $common.sendAjax('/checkId', 'POST', 'application/json', JSON.stringify(obj));
    jqxhr.done(function(data) {
        if (data === "SUCCESS") {
            showText($input, '사용가능한 아이디 입니다.', 'success');
        } else {
            showText($input, '중복되는 아이디가 있습니다.', 'error');
        }
    });
    jqxhr.fail(function(e) {
        console.log(e)
    })
    // sendAjax
}

function checkGroupCode() {
    var $input = $('#groupCode')
    var obj = {
        groupCode: $input.val()
    };
    var jqxhr = $common.sendAjax('/checkCode', 'POST', 'application/json', JSON.stringify(obj));
    jqxhr.done(function(data) {
        console.log(data);
        if (data === "SUCCESS") {
            // showText($input, '사용가능한 아이디 입니다.', 'success');
        } else {
            // showText($input, '중복되는 아이디가 있습니다.', 'error');
        }
    });
    jqxhr.fail(function(e) {
        console.log(e)
    })
    // sendAjax
}

