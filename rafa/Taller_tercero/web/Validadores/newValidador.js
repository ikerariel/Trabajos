

$(document).ready(function () {

    $(":text").val("");


});

function nuevoUsuario(){
           user = {
            "opcion": 8

        };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
////            alert(resp);
//            $("#codigo").val(resp);
            $.each(resp, function (indice, value) {
                $("#Usuario").val(value.usu_nombre);
                $("#Usuario").removeAttr('disabled');
            });

        },

        error: function () {
//            $("#errorultimocodigo").show();
//            window.setTimeout(function () {
//                $("#errorultimocodigo").fadeTo(1500, 0).slideUp(500, function () {
//                    $(this).remove();
//                });
//            }, 1100);
        }
    });


}


