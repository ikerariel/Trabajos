
$(document).ready(function () {

    (function ($) {
        $('#filtrar').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscar tr').hide();
            $('.buscar tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});


function listaBuscadores(id) {
//alert("Llega listaBuscadore s" + id);
 datosJSON = {
        "opcion": id
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/buscadorcontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $('#buscarRegistro').html(resp);
        }
    });
}


