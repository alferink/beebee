$(document).ready(function () {
    $('.dropdown-toggle').dropdown();
});

$(document).ready(function () {
    $(function () {
        $('select.rating').each(function () {
            var readonly = $(this).prop('disabled')
            $(this).barrating({
                theme: 'fontawesome-stars',
                readonly: readonly
            })
        })
    });
});

$(document).ready(function () {
    $(function () {
        $('form.confirm').submit(function (evt) {
            return confirm($(this).data('confirm-message'));
        });
    });
});

$(document).ready(function () {
    $('.colorpicker-component').colorpicker({});
});