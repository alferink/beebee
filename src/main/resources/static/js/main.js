$(document).ready(function () {
    $('.dropdown-toggle').dropdown();
});

$(document).ready(function () {
    $(function () {
        $('select.rating').barrating({
            theme: 'fontawesome-stars',
        });
    });
});

$(document).ready(function () {
    $(function () {
        $('form.delete').submit(function (evt) {
            return confirm($(this).data('confirm-message'));
        });
    });
});