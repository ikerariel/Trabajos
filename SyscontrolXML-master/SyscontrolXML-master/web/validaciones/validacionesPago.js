$(document).ready(function () {
    opcionesbotonespagos();
    pagoscombos();



});

//-----VALIDACIONES DE GIROS BILLERTERA---/////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////

function opcionesbotonespagos() {


    $('#btnguardarpago').click(function () {

        var vMontopagado = $('#v_modalmontopagar').val().replace(/\./g, '');
        var vMontopagar = $('#v_modalmonto').val().replace(/\./g, '');
        if (parseInt(vMontopagado) === parseInt(vMontopagar) ||
                parseInt(vMontopagar) === null) {
            guardarpago(6, 1);
        } else {
            $.confirm({
                title: 'AVISO!',
                content: 'El monto a pagar debe ser igual al monto adeudado.. ',
                type: 'red',
                buttons: {
                    Ok: {
                        text: 'OK',
                        btnClass: 'btn-dark',
                        keys: ['enter', 'shift'],
                        action: function () {

                        }
                    }

                }

            });
        }

    });
    $('#btnguardarpagorecarga').click(function () {
        var vMontopagador = $('#v_modalmontopagar').val().replace(/\./g, '');
        var vMontopagarr = $('#v_modalmonto').val().replace(/\./g, '');
        if (parseInt(vMontopagador) === parseInt(vMontopagarr) ||
                parseInt(vMontopagarr) === null) {
            guardarpagorecarga(6, 1);
        } else {
            $.confirm({
                title: 'AVISO!',
                content: 'El monto a pagar debe ser igual al monto adeudado.. ',
                type: 'red',
                buttons: {
                    Ok: {
                        text: 'OK',
                        btnClass: 'btn-dark',
                        keys: ['enter', 'shift'],
                        action: function () {

                        }
                    }

                }

            });
        }

    });

    $('#v_detallegiro').change(function () {
        getpagosgiros();

    });
    $('#v_detallerecarga').change(function () {
        getpagorecarga();
    });
    $('#v_modalmontopagar').keyup(function () {
        puntodecimal('v_modalmontopagar');
    });
    $('#vBuscarRegistro').keyup(function () {
        buscadorgenericotable('mitablapagos', 'vBuscarRegistro');
    });
    $('#vBuscarRegistrorecarga').keyup(function () {
        buscadorgenericotable('mitablapagosrecargas', 'vBuscarRegistrorecarga');
    });

}




var idx = 0;
function getpagosgiros() {
    $('#mitablapagos').find('tbody').find('tr').empty();
    giro = {
        'opcion': 11,
        'v_codpago': $('#v_detallegiro').val()
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: giro,
        cache: false,
        success: function (resp) {
            var tv = 0;
            var vt = 0;
            $.each(resp, function (indice, valor) {
                var e = valor.idestado;
                var fechaD;
                if (e === 6) {
                    pagar = "<button class='btn btn-sm btn-outline-danger' style=display:none >Pagar</button>";
                    delec = "<button class='btn btn-sm btn-outline-danger'title='Eliminar Pago' onclick=\"$(\'#prod" + idx + "\');deletepago(1)\">Eliminar</button>";
                    ticket = "<button class='btn btn-sm btn-outline-primary' title='Generar Ticket' onclick=\"$(\'#prod" + idx + "\');generarticket(5)\">Ticket</button>";
                    fechaD = valor.fechapago;
                    $('#td_fecha').html('Fecha Pagada');
                        $('#textgiro').html('Pagados :');
                          $('#v_totalpagopend').css('color', 'green');
                    vt = valor.montoenvio;
                    tv = tv + vt;
                } else if (e === 7) {
                    pagar = "<button class='btn btn-sm btn-outline-danger'data-toggle='modal' title='Procesar Pago' onclick=\"$(\'#prod" + idx + "\');pagos(1)\">Pagar</button>";
                    ticket = "<button class='btn btn-sm btn-outline-primary'style=display:none title='Generar  Ticket' onclick=\"$(\'#prod" + idx + "\');\">Ticket</button>";
                    delec = "<button class='btn btn-sm btn-outline-danger'title='Eliminar Pago' style=display:none>Eliminar</button>";
                    fechaD = valor.fecha;
                    $('#td_fecha').html('Fecha Recarga');
                        $('#textgiro').html('Pago Pendiente :');
                          $('#v_totalpagopend').css('color', 'red');
                    vt = valor.montoenvio;
                    tv = tv + vt;

                }

                idx++;
                $("#mitablapagos").append($("<tr id=\'cod" + idx + "\'>").append($(
                        "<td style='display: none'>" + valor.iddetallegiro + "</td>" +
                        "<td>" + valor.cliente + "</td>" +
                        "<td>" + valor.operacion + "</td>" +
                        "<td>" + valor.montoenvio + "</td>" +
                        "<td>" + fechaD + "</td>" +
                        "<td>" + pagar + "" + ticket + "" + delec + " </td>" +
                        "<td style=display:none>" + valor.idpago + " </td>")));
            });

//            alert(tv);
            $('#v_totalpagopend').val(tv);
            puntodecimal('v_totalpagopend');
            $('#v_totalpagopend').css('text-align', 'center');
            $('#v_totalpagopend').css('font-size', '18pt');
        }
    });
}
function deletepago(valor) {
    if (parseInt(valor) === 1) {
        var ci = 0;
        $('#mitablapagos tr').click(function () {
            ci = $(this).find("td").eq(0).html();
        });
        $.confirm({
            title: 'Eliminar Pago',
            content: 'Desea eliminar el pago ?',
            buttons: {
                Si: {
                    text: 'SI',
                    btnClass: 'btn-success',
                    keys: ['enter', 'shift'],
                    action: function () {
                        guardarpago(7, 2, ci);

                    }
                },
                No: {
                    text: 'No',
                    btnClass: 'btn-red',
                    keys: ['enter', 'shift'],
                    action: function () {
                        $.alert('Cancelado !!');
                    }
                }
            }
        });
    } else if (parseInt(valor) === 2) {
        var vCi = 0;
        $('#mitablapagosrecargas tr').click(function () {
            vCi = $(this).find("td").eq(0).html();
        });
        $.confirm({
            title: 'Eliminar Pago',
            content: 'Desea eliminar el pago ?',
            buttons: {
                Si: {
                    text: 'SI',
                    btnClass: 'btn-success',
                    keys: ['enter', 'shift'],
                    action: function () {
                        guardarpagorecarga(7, 2, vCi);

                    }
                },
                No: {
                    text: 'No',
                    btnClass: 'btn-red',
                    keys: ['enter', 'shift'],
                    action: function () {
                        $.alert('Cancelado !!');
                    }
                }
            }
        });
    }



}


function pagos(vCodigo) {
    if (parseInt(vCodigo) === 1) {
        $('#btnguardarpago').show();
        $('#btnguardarpagorecarga').hide();
        $('#v_pagos').modal('show');
        var ci;
        var v_ci;
        var v_nombre;
        var v_monto;
        var v_codg;
        $('#mitablapagos tr').click(function () {
            ci = $(this).find("td").eq(1).html();
            v_codg = $(this).find("td").eq(0).html();
            v_monto = $(this).find("td").eq(3).html();
            $('#v_modalname').val(ci);
            $('#v_modalmonto').val(v_monto);
            $('#v_girocod').val(v_codg);
            puntodecimal('v_modalmonto');
        });
    } else if (parseInt(vCodigo) === 2) {
        $('#btnguardarpago').hide();
        $('#btnguardarpagorecarga').show();
        $('#v_pagos').modal('show');
        var v_ci;
        var vCi;
        var vNombre;
        var vMmonto;
        var vCodg;
        $('#mitablapagosrecargas tr').click(function () {
            v_ci = $(this).find("td").eq(1).html();
            vCodg = $(this).find("td").eq(0).html();
            vMmonto = $(this).find("td").eq(4).html();
//            vCi = v_ci.toString().substr(1,8);
//            vNombre = v_ci.toString().substr(9);
            $('#v_modalname').val(v_ci);
            $('#v_modalmonto').val(vMmonto);
            $('#v_girocod').val(vCodg);
            puntodecimal('v_modalmonto');
        });
    }



}

function pagoscombos() {
    datopago = {
        "opcion": 9
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: datopago,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                if (value.idtipooperacion === 6 || value.idtipooperacion === 7) {
                    $("#v_detallegiro").append("<option value= \"" + value.idtipooperacion + "\"> " + value.descripcion + "</option>");
                    $("#v_detallerecarga").append("<option value= \"" + value.idtipooperacion + "\"> " + value.descripcion + "</option>");
                }


            });

        }
    });
    setTimeout(function () {
        getpagosgiros();
        getpagorecarga();
    }, 1000);

}
function guardarpago(v_estado, v_Cod, vCodgiro) {

    if (parseInt(v_Cod) === 2) {
        saveall(v_estado, v_Cod, vCodgiro);
    } else {
        $.confirm({
            title: 'Guardar',
            content: 'Desea Guardar el pago ?',
            buttons: {
                Si: {
                    text: 'SI',
                    btnClass: 'btn-success',
                    keys: ['enter', 'shift'],
                    action: function () {
                        saveall(v_estado, v_Cod, vCodgiro);
                    }
                },
                No: {
                    text: 'No',
                    btnClass: 'btn-red',
                    keys: ['enter', 'shift'],
                    action: function () {
                        $.alert('Cancelado !!');
                    }
                }
            }
        });
    }

    function saveall(v_estado, v_Cod, vCodgiro) {
        var vOper = Math.random() * 99999999999999999;
        var vOperacion = vOper.toString().substr(1, 4);
        var codigogiro;
        if (parseInt(v_Cod) === 2) {
            codigogiro = vCodgiro;
        } else if (parseInt(v_Cod) === 1) {
            codigogiro = $('#v_girocod').val();
        }
        jsonpago = {
            'opcion': 12,
            'vCod': v_Cod,
            'v_estad': v_estado,
            'v_montpagado': $('#v_modalmontopagar').val().replace(/\./g, ''),
            'v_codgiro': codigogiro,
            'v_nrooper': vOperacion
        };
        $.ajax({
            url: "/syscontrol/giroSERVLETXML",
            type: 'POST',
            data: jsonpago,
            cache: false,
            dataType: 'text',
            success: function () {
                $.alert('Guardado correctamente !!');
                location.reload();

            }, error: function () {
                $.alert('No se puedo realizar la operación !!');
            }

        });
    }

}

///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////////////////////



var idxx = 0;
function getpagorecarga() {
    $('#mitablapagosrecargas').find('tbody').find('tr').empty();
    recarga = {
        'opcion': 8,
        'vCodestadorecarga': $('#v_detallerecarga').val()
    };
    $.ajax({
        url: "/syscontrol/recargaSERVLETXML",
        type: 'POST',
        data: recarga,
        cache: false,
        success: function (resp) {
            var ttal = 0;
            var vacum = 0;
            $.each(resp, function (indice, valor) {
                var e = valor.idestado;
                var fechaD;
                if (e === 6) {
                    Rpagar = "<button class='btn btn-sm btn-outline-danger' style=display:none >Pagar</button>";
                    Rdelec = "<button class='btn btn-sm btn-outline-danger'title='Eliminar Pago' onclick=\"$(\'#prod" + idxx + "\');deletepago(2)\">Eliminar</button>";
                    Rticket = "<button class='btn btn-sm btn-outline-primary' title='Generar Ticket' onclick=\"$(\'#prod" + idxx + "\');generarticket(6)\">Ticket</button>";
                    fechaD = valor.fechapagorecarga;
                    $('#td_fechar').html('Fecha Pagada');
                      $('#txtpagorecarga').html('Pagados :');
                          $('#v_totalpagopendrecarga').css('color', 'green');
                    ttal = valor.montorecarga;
                    vacum=vacum+ttal;
                } else if (e === 7) {
                    Rpagar = "<button class='btn btn-sm btn-outline-danger'data-toggle='modal' title='Procesar Pago' onclick=\"$(\'#prod" + idxx + "\');pagos(2)\">Pagar</button>";
                    Rticket = "<button class='btn btn-sm btn-outline-primary'style=display:none title='Generar  Ticket' onclick=\"$(\'#prod" + idxx + "\');\">Ticket</button>";
                    Rdelec = "<button class='btn btn-sm btn-outline-danger'title='Eliminar Pago' style=display:none>Eliminar</button>";
                    fechaD = valor.fecharecarga;
                    $('#td_fechar').html('Fecha Recarga');
                    $('#txtpagorecarga').html('Pago Pendinte :');
                    $('#v_totalpagopendrecarga').css('color', 'red');
                     ttal = valor.montorecarga;
                    vacum=vacum+ttal;
                    
                }

                idxx++;
                $("#mitablapagosrecargas").append($("<tr id=\'cod" + idxx + "\'>").append($(
                        "<td style='display: none'>" + valor.iddetallerecargas + "</td>" +
                        "<td>" + valor.cliente + "</td>" +
                        "<td>" + valor.nrodestino + "</td>" +
                        "<td>" + valor.operacion + "</td>" +
                        "<td>" + valor.montorecarga + "</td>" +
                        "<td>" + fechaD + "</td>" +
                        "<td>" + Rpagar + "" + Rticket + "" + Rdelec + " </td>" +
                        "<td style='display: none'>" + valor.idpagorecarga + " </td>")));
            });
      $('#v_totalpagopendrecarga').val(vacum);
            puntodecimal('v_totalpagopendrecarga');
            $('#v_totalpagopendrecarga').css('text-align', 'center');
            $('#v_totalpagopendrecarga').css('font-size', '18pt');
        }
    });
}

function guardarpagorecarga(vEstado, vOpcion, vCodrecarga) {

    if (parseInt(vOpcion) === 2) {
        saveRecarga(vEstado, vOpcion, vCodrecarga);
    } else {
        $.confirm({
            title: 'Guardar',
            content: 'Desea Guardar el pago ?',
            buttons: {
                Si: {
                    text: 'SI',
                    btnClass: 'btn-success',
                    keys: ['enter', 'shift'],
                    action: function () {
                        saveRecarga(vEstado, vOpcion, vCodrecarga);
                    }
                },
                No: {
                    text: 'No',
                    btnClass: 'btn-red',
                    keys: ['enter', 'shift'],
                    action: function () {
                        $.alert('Cancelado !!');
                    }
                }
            }
        });
    }

    function saveRecarga(vEstado, vOpcion, vCodrecarga) {
        var vOperrec = Math.random() * 99999999999999999;
        var vOperacionrec = vOperrec.toString().substr(1, 4);
        var codigorecarga;
        if (parseInt(vOpcion) === 2) {
            codigorecarga = vCodrecarga;
        } else if (parseInt(vOpcion) === 1) {
            codigorecarga = $('#v_girocod').val();
        }
        jsonpago = {
            'opcion': 9,
            'vCodi': vOpcion,
            'vEstad': vEstado,
            'vMontpagado': $('#v_modalmontopagar').val().replace(/\./g, ''),
            'vCodrecarga': codigorecarga,
            'vNroopracion': vOperacionrec
        };
        $.ajax({
            url: "/syscontrol/recargaSERVLETXML",
            type: 'POST',
            data: jsonpago,
            cache: false,
            dataType: 'text',
            success: function () {
                $.alert('Guardado correctamente !!');
                location.reload();

            }, error: function () {
                $.alert('No se puedo realizar la operación !!');
            }

        });
    }

}


