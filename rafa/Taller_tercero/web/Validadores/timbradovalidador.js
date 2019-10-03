$(document).ready(function () {
//opcionCliente();
    allTimbrados();
//    $('#btnNuevo').show();
//    $('#btnReporte').show();
//    $(":text").val("");
//    nuevoListarCiudad();
});
function codigoTimbrado() {
//    controlBotonesPedidoV();
    $("#usuariotimb").val($('#vUser').val());
          datosPVJSON = {
        "opcion": 1
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/timbradocontrol",
        data:datosPVJSON ,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigotimb").val(resp);
            $("#nrotimbr").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function fechaactualTimb() {
    var fecha = new Date();
    $('#fechaini').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function MostrarEstadoTimb() {
//    alert("llega al usuario")
        datosPVJSON = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/timbradocontrol",
        data: datosPVJSON,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#idestadotimb").val(value.idestado);
                $("#estadotimb").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}

_index = 0;
function generarparametrosTimb() {
    var cont = 0;
    var _expe = $('#factexpe').val();
    var _caja = $('#factcaja').val();
    var _facdesde = $('#factdesde').val();
    var _fachasta = $('#factasta').val();
    for (var i = 0, i = parseInt(_facdesde); i <= parseInt(_fachasta); i++) {
        cont++;
        $('#tablaparametrosTimb').append("<tr id=\'prod" + _index + "\'>\
                 <td>" + cont + "</td>\n\
                 <td>" + _expe + "</td>\n\
                 <td>" + _caja + "</td>\n\
                 <td>" +
                [i] + "</td>\n\
                </tr>");

    }
}

function grabartimbrado() {
    timbrados = {
        "opcion": 3,
        "timNumero": $('#nrotimbr').val(),
        "timFechavenc": $('#fechaven').val(),
        "timEstado": $('#idestadotimb').val(),
        "timFechainic": $('#fechaini').val(),
        "timUsuario": $('#CodvUser').val(),
        "timEstabl": $('#factexpe').val(),
        "timDesde": $('#factdesde').val(),
        "timAsta": $('#factasta').val(),
        "timCaja": $('#factcaja').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/timbradocontrol",
        type: 'POST',
        data: timbrados,
        cache: false,
        success: function () {
        },
        error: function () {
        }
    });
    setTimeout(function () {
        guardarDetalleNrofactura();
        alert('Guardado correctamente');
    }, 2000);
}

function guardarDetalleNrofactura() {
    $('#tablaparametrosTimb').find('tbody').find('tr').each(function () {
        datosdetallefac = {
            "opcion": 4,
            "timNumero": $('#nrotimbr').val(),
            "timNroFactura": $(this).find("td").eq(3).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/timbradocontrol",
            type: 'POST',
            data: datosdetallefac,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });
}

function allTimbrados() {
    datosJSON = {
        "opcion": 5
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/timbradocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaTimbrados").append($("<tr>").append($("<td>" + value.idtimbrado + "</td>" +
                        "<td>" + value.tim_numero + "</td>" + "<td>" + value.tim_fechavence + "</td>" +
                        "<td>" + value.descri_estado + "</td>" + "<td>" + value.tim_fechainicio + "</td>" +
                        "<td>" + value.usu_nombre + "</td>")));
            });
        }
    });
}