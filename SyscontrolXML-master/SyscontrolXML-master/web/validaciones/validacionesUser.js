$(document).ready(function (){
    opciones();
});

function opciones() {
    $('#IDinputUsuario').blur(function () {
        DatosUsuario();
    });
}

function  DatosUsuario() {
    dato = {
        "opcion": 1,
        "user": $('#IDinputUsuario').val()
    };

    $.ajax({
        url: "/syscontrol/accesouserSERVLETXML",
        data: dato,
        type: 'POST',
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, value) {
                    $("#vCodUser").val(value.idusuario);


                });
            } else {
                $('#IDinputUsuario').val(null);
                $('#vCodUser').val(null);
                $('#IDinputUsuario').focus();
            }
        },
        error: function () {

        }

    });


}