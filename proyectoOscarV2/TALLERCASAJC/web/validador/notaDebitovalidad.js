$(document).ready(function () {

    fechaactual();
    allnotasDebitos();
    actualizarestadoND();
});
function fechaactual() {
    var fecha = new Date();
    $('#fechanotadebito').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function abrirVentana() {
    $('#btnM').hide();
    $('#btnGuardar').show();
    getcodND();
}

function informeND() {
    var ND = $('#v_nroND').val();
        var cod = 8;
        window.open(`reportesCompra_v.jsp?codigo=${cod}&id_notadecompra=${ND}`, "_blank");
        location.reload();

    

}
var tindex = 0;

function agregarFilaND() {
    var _notaD = $('#observND').val();
    var _importe = $('#importeND').val();

    if (_notaD === "") {
        alert('No se encuentra algun concepto cargado..!!');
    } else {
        if (_importe === "") {
            alert('No se ecuentra importe cargado..!!');
        } else {
            var v_nrofac = $('#nrofacturaND').val();
            var v_obs = $('#observND').val();
            var v_importe = $('#importeND').val();

            // °°°°style=display:none°°° PARA ocultar ej. "Nro Factura"
            tindex++;
            $('#miTablaDetalleND').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_nrofac + "</td>\n\
            <td>" + v_obs + "</td>\n\
            <td>" + v_importe + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove();calculoND();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");
            $('#importeND').val(null);
            $('#observND').val(null);
            $('#observND').focus;
        }
        calculoND();
    }
}//-----------------------

function crearJSON(id) {
    datosJSON = {
        "opcion": id
    };
}
function allnotasDebitos() {
    crearJSON(1);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaNotaDebito").append($("<tr>").append($(
                        "<td style=display;none>" + value.id_notadecompra + "</td>" +
                        "<td>" + value.fecha + "</td>" +
                        "<td bgcolor='#d9edf7'>" + value.est_descripcion + "</td>")));
            });
        }
    });
}

function  insertarND() {
    var _nfilas = $('#miTablaDetalleND tr').length - 1; // funcion para contar filas de una tabla
    if (parseInt(_nfilas) <= 0) {
        alert('No hay registros para guardar..!!');
    } else {
        if ($('#NroNotaDebitos').val() === "" || $('#NroTimbrados').val() === "" || $('#nrofacturaND').val() === "") {
            alert('Algunos datos no fueron cargados correctamente..');
        } else {
            var opcion = confirm('Desea Guardar el registro..?');
            if (opcion === true) {
                datos = {
                    "opcion": 2,
                    "_nrodebito": $('#NroNotaDebitos').val(),
                    "_nrotimbradoDebito": $('#NroTimbrados').val(),
                    "_codcompra": $('#idcompraND').val(),
                    "_codusuario": $('#idusersession_v').val(),
                    "_observacionND": $('#observacionND').val()
                };

                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                    type: 'POST',
                    data: datos,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        setTimeout(function () {
                            insetarDetalleND();
                        }, 1200);
                        alert("Registro guardado correctamente.!!");
                    },
                    error: function () {
                    }

                });

            } else {

            }

        }
    }
}

function  insetarDetalleND() {
    setTimeout(function () {
        $('#miTablaDetalleND').find('tbody').find('tr').each(function () {
            datosDetalleND = {
                "opcion": 4,
                "ND_codigoD": $('#codigoND').val(),
                "ND_importe": $(this).find("td").eq(2).html().replace(/\./g, ''),
                "NT_comentario": $(this).find("td").eq(1).html()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                type: 'POST',
                data: datosDetalleND,
                cache: false,
                dataType: 'text',
                success: function () {

                },
                error: function () {
                }
            });
        });
    }, 2000);
}

function getcodND() {
    crearJSON(3);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigoND").val(resp);
            $("#nrofacturaND").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function selectDetalleNr() {
    $('#miTablaNotaDebito tr').click(function () {
        $('#v_nroND').val($(this).find("td").eq(0).html());
        $('#v_estado').val($(this).find("td").eq(2).html());

    });
}

function recuperarDetalleND() {
    $('#btnGuardar').hide();
    $('#btnM').show();
    if ($('#v_nroNR').val() === "") {
        alert('Seleecione una Nota de Debito para visualizar..');
    } else {
        if ($('#v_estado').val() === 'Aprobado') {
            alert('La nota ya fue APROBADA..');
        } else {
            json = {
                "opcion": 5,
                "_nroND": $('#v_nroND').val()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
                type: 'POST',
                data: json,
                cache: false,
                success: function (resp) {
//                  alert(resp);
                    if (JSON.stringify(resp) != '[]') {
                        $('#ventanaNotaDebito').modal('show');
                        $('#miTablaDetalleND').find('tbody').find('tr').empty(); //codigo para vaciar una tabla     
                        $.each(resp, function (indice, value) {
                            ///RECUPERA LA CABECERA/////////
                            $("#codigoND").val($("#v_nroND").val());
                            $("#fechanotadebito").val(value.fecha);
                            $("#estadoND").val(value.estado);
                            $("#NroNotaDebitos").val(value.nro_notadebito);
                            $("#NroTimbrados").val(value.nro_timbradonotadebito);
                            $("#nrofacturaND").val(value.factura);
                            $("#idcompraND").val(value.id_compra);
                            $("#observacionND").val(value.observacion);

                            tindex++;
                            $('#miTablaDetalleND').append("<tr id=\'prod" + tindex + "\'>\
                             <td style=display:none>" + '0' + "</td>\n\
                             <td>" + value.conceptos + "</td>\n\
                                <td>" + value.importe + "</td>\n\
                               <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove();calculoND();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td>\n\
                                </tr>");

                        });
                        calculoND();
                    } else {
                        alert('Datos no encontrados..');
                    }
                }
            });
        }
    }
}

function calculoND() {
    setTimeout(function () {
        monto = 0;
        acumu = 0;
        $('#miTablaDetalleND').find('tbody').find('tr').each(function () {
            monto = parseInt($(this).find("td").eq(2).html());
            acumu = acumu + monto;
        });
        $('#montoAcreditar').val(acumu);
        tindex++;
        numeroDecimal('montoAcreditar');


    }, 1000);

}

function consultaFactura() {
    jsonfactura = {
        "opcion": 6,
        "_nroFactura": $('#nrofacturaND').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        type: 'POST',
        data: jsonfactura,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var nro = JSON.stringify(resp);
                var n = JSON.parse(nro);
                var fac = n[0].nrofact;
                if (parseInt(fac) > 0) {
                    alert('La factura  ya fue procesada..');
                } else {
                    var v = JSON.stringify(resp);
                    var vv = JSON.parse(v);
                    var estado = vv[0].id_estado;
                    if (parseInt(estado) === 1) {
                        $.each(resp, function (indice, value) {
                            $('#observND').focus();
                            $('#idcompraND').val(value.id_compra);

                        });
                    } else {
                        alert('Factura Pendiente.!!');
                    }

                }

            } else {
                alert('Datos no encontrados..');
            }
        }
    });

}




function updateCabecera() {
    var opcion = confirm('Desea Modificar el registro..?');
    if (opcion === true) {
        datosCabecera = {
            "opcion": 8,
            "_nrodebito": $('#NroNotaDebitos').val(),
            "_nrotimbradoDebito": $('#NroTimbrados').val(),
            "_codcompra": $('#idcompraND').val(),
            "_codusuario": $('#idusersession_v').val(),
            "_observacionNDV": $('#observacionND').val(),
            "_codND": $('#codigoND').val()
        };

        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
            type: 'POST',
            data: datosCabecera,
            cache: false,
            dataType: 'text',
            success: function () {

                alert("Registro modificado correctamente.!!");
                updateDetalle();
                setTimeout(function () {
                    insetarDetalleND();
                }, 1200);
            },
            error: function () {
            }

        });

    } else {

    }


}


function updateDetalle() {
    jsonDetalle = {
        "opcion": 9,
        "nroND": $('#codigoND').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        type: 'POST',
        data: jsonDetalle,
        cache: false,
        success: function () {

        }
    });

}

function actualizarestadoND() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonoesND a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnularND') {
                if ($('#v_estado').val() === "") {
                    alert('Seleccione un registro.!');
                } else if ($('#v_estado').val() === 'CONFIRMADO') {
                    alert('El registro no se puede confirmar.');
                } else {
                    var opcion = confirm('Desea Anular el registro.??');
                    if (opcion === true) {
                        datosNC = {
                            "opcion": 10,
                            "codEstado": 2,
                            "codND": $('#v_nroND').val()
                        };
                        confirmarND();
                        alert('Registro Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmarND') {
                if ($('#v_estado').val() === "") {
                    alert('Seleccione un registro!');
                } else if ($('#v_estado').val() === 'CONFIRMADO') {
                    alert('El registro ya fue confirmado..');
                } else {
                    var opcion = confirm('Desea Confirmar el registro.??');
                    if (opcion === true) {
                        datosNC = {
                            "opcion": 10,
                            "codEstado": 1,
                            "codND": $('#v_nroND').val()
                        };
                        confirmarND();
                        alert('Registro Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertirND') {
                if ($('#v_estado').val() === "") {
                    alert('Seleccione un registro!');
                } else if ($('#v_estado').val() === 'PENDIENTE') {
                    alert('El registro no se puede Revertir..');
                } else {
                    var opcion = confirm('Desea Revertir el registro.??');
                    if (opcion === true) {
                        datosNC = {
                            "opcion": 10,
                            "codEstado": 3,
                            "codND": $('#v_nroND').val()
                        };
                        confirmarND();
                        alert('Registro revertido con exito.!!');
                    }
                }


            }
        });
    });
}

function confirmarND() {
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/notaDebitocontrol",
        type: 'POST',
        data: datosNC,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaNotaDebito').find('tbody').find('tr').empty();
            allnotasDebitos();
        },
        error: function () {

        }
    });
}