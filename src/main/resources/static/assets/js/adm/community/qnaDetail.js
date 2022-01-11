$(document).ready(function() {
    $('#comment').click(function () {
        $('.comment-btn-box').show();
    })
    $('#commentCancel').click(function () {
        $('.comment-btn-box').hide();
        $('#comment').val('');
        $('#comment').height(25);
    })
});

