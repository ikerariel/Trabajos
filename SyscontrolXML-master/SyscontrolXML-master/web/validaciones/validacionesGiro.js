$(document).ready(function () {
    combooperadoragiro();
    opcionesBotonoesGiro();
    combooperacion();
    getgiro();
    combopago();

});
function opcionesBotonoesGiro() {
    $('#v_lineaorigen').keyup(function () {
        verificarcampoentero('v_lineaorigen');
        $('#v_lineaorigen').css('font-weight', 'bold');

    });
    $('#v_lineadestino').keyup(function () {
        verificarcampoentero('v_lineadestino');
        $('#v_lineadestino').css('font-weight', 'bold');

    });
    $('#v_montogiro').keyup(function () {
        verificarcampoentero('v_montogiro');
        puntodecimal('v_montogiro');

    });
    $('#v_cicliente').keyup(function () {
        puntodecimal('v_cicliente');

    });
    $('#v_montogiro').keyup(function () {
        calculoenvio();

    });
    $('#v_cicliente').blur(function () {
        getccliente();

    });
    $('#btnguardarcliente').click(function () {
        guardarcliente();

    });
    $('#btnguardargiro').click(function () {
        if ($('#v_nombrecliente').val() === "" || $('#v_cicliente').val() === ""
                || $('#v_montogiro').val() === "" || $('#v_lineadestino').val() === ""
                || $('#v_lineaorigen').val() === "") {
            $.confirm({
                title: 'AVISO!',
                content: 'Algunos campos no fueron cargados correctamente.. ',
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
        } else {
            guardargiro();
        }

    });
}

function combooperadoragiro() {
    datooperadora = {
        "opcion": 3
    };
    $.ajax({
        url: "/syscontrol/recargaSERVLETXML",
        type: 'POST',
        data: datooperadora,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#v_operadoragiro").append("<option value= \"" + value.idprefijo + "\"> " + value.descripcion + "</option>");

            });

        }
    });
}

function combooperacion() {
    datooperacion = {
        "opcion": 1
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: datooperacion,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#v_tipooperacion").append("<option value= \"" + value.idtipooperacion + "\"> " + value.descripcion + "</option>");

            });

        }
    });
}
function combopago() {
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
                    $("#v_pago").append("<option value= \"" + value.idtipooperacion + "\"> " + value.descripcion + "</option>");
                }


            });

        }
    });
}
function getccliente() {
    cliente = {
        "opcion": 2,
        "v_cicliente": $('#v_cicliente').val().replace(/\./g, '')
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: cliente,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, value) {
                    $('#v_nombrecliente').val(value.nombrecliente);
                    $('#v_idcliente').val(value.idcliente);


                });
            } else {
                var v_cedula = $('#v_cicliente').val().replace(/\./g, '');
                if (v_cedula.length < 6) {
                    $.confirm({
                        title: 'AVISO!',
                        content: 'El número de cédula debe ser mayor a 6 dígitos.. ',
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
                } else {
                    $.confirm({
                        title: 'AVISO!',
                        content: 'Cliente no registrado, desea registrarlo ??',
                        type: 'red',
                        buttons: {
                            Si: {
                                text: 'SI',
                                btnClass: 'btn-success',
                                keys: ['enter', 'shift'],
                                action: function () {
                                    $('#v_cliente').modal('show');
                                    $('#v_modalcedula').val($('#v_cicliente').val());
                                    $('#v_modalnombre').focus();
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


        }
    });
}

function guardarcliente() {
    cliente = {
        'opcion': 3,
        'v_nombrecliente': $('#v_modalnombre').val(),
        'v_cedulacliente': $('#v_modalcedula').val().replace(/\./g, '')
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: cliente,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#v_cliente').modal('hide');
            $('#v_cicliente').focus();
            $('#v_nombrecliente').val($('#v_modalnombre').val());

        }

    });
}
function guardargiro() {
    var pago = 0;
    var operacion = $('#v_tipooperacion').val();
    var tipopago = $('#v_pago').val();
    switch (parseInt(operacion)) {
        case 1: //giro
            pago = tipopago;
            break;
        case 2: //billetera
            pago = tipopago;
            break;
        case 3: //retiro efectivo
            pago = 9;
            break;
        case 4: //reversion
            pago = 9;
            break;
        case 5: //retiro billetera
            pago = 9;
            break;
    }
    var vv = Math.random() * 99999999999999999;
    var nrooperacion = vv.toString().substr(1, 4);
    var trnsc = $('#v_nrotransacciongiro').val();
    if (trnsc === "") {
        trnsc = 0;
    }
    var por = $('#v_porsentaje option:selected').html();
    if (por === "SIN PORCENTAJE.") {
        por = 0;
    } else {
        por = '5%';
    }
    giro = {
        'opcion': 4,
        'v_nrodestino': $('#v_lineadestino').val(),
        'v_nroorigen': $('#v_lineaorigen').val(),
        'v_montogirobilletera': $('#v_montogiro').val().replace(/\./g, ''),
        'v_codoperadora': $('#v_operadoragiro').val(),
        'v_nrotransaccion': trnsc,
        'v_nrooperacion': nrooperacion,
        'v_codcliente': $('#v_idcliente').val(),
        'v_codoperacion': $('#v_tipooperacion').val(),
        'v_porcentaje': por,
        'v_montoenvio': $('#v_totalenvio').val().replace(/\./g, ''),
        'v_codpago': pago,
        'v_CodIDuser': $('#vCodIDuser').val()
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: giro,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#mitablagiros').find('tbody').find('tr').empty();
            getgiro();
            location.reload();


        }

    });
}

function calculoenvio() {
    var v = $('#v_tipooperacion').val();
    if (parseInt(v) === 1) {
        $("#v_porsentaje").attr('disabled', true);
        $('#v_porsentaje option:selected').html('5%');
        var monto = $('#v_montogiro').val().replace(/\./g, '');
        var call = (parseInt(monto) * 5) / 100;
        var resul = call + parseInt(monto);
        $('#v_totalenvio').val(resul);
        puntodecimal('v_totalenvio');
        $('#txtmonto').html('Total Envió');

    } else
    if (parseInt(v) === 3) {
        $("#v_porsentaje").attr('disabled', true);
        var monto = $('#v_montogiro').val().replace(/\./g, '');
        $('#v_totalenvio').val(monto);
        puntodecimal('v_totalenvio');
        $('#txtmonto').html('Total Retiro');
    } else
    if (parseInt(v) === 4) {
        $("#v_porsentaje").attr('disabled', true);
        $('#v_porsentaje option:selected').html('SIN PORCENTAJE.');
        var monto = $('#v_montogiro').val().replace(/\./g, '');
        $('#v_totalenvio').val(monto);
        puntodecimal('v_totalenvio');
        $('#txtmonto').html('Total Reversión');

    } else if (parseInt(v) === 2) {
        $("#v_porsentaje").removeAttr('disabled', true);
        if ($('#v_porsentaje option:selected').html() === '5%') {
            var monto = $('#v_montogiro').val().replace(/\./g, '');
            var call = (parseInt(monto) * 5) / 100;
            var resul = call + parseInt(monto);
            $('#v_totalenvio').val(resul);
            puntodecimal('v_totalenvio');
            $('#txtmonto').html('Total Billetera');
        }
        if ($('#v_porsentaje option:selected').html() === 'SIN PORCENTAJE.') {
            var monto = $('#v_montogiro').val().replace(/\./g, '');
            $('#v_totalenvio').val(monto);
            puntodecimal('v_totalenvio');
            $('#txtmonto').html('Total Billetera');
        }



    } else if (parseInt(v) === 5) {
        $("#v_porsentaje").removeAttr('disabled', true);
        if ($('#v_porsentaje option:selected').html() === '5%') {
            var monto = $('#v_montogiro').val().replace(/\./g, '');
            var call = (parseInt(monto) * 5) / 100;
            var resul = call + parseInt(monto);
            $('#v_totalenvio').val(resul);
            puntodecimal('v_totalenvio');
            $('#txtmonto').html('Total Billetera');
        }
        if ($('#v_porsentaje option:selected').html() === 'SIN PORCENTAJE.') {
            var monto = $('#v_montogiro').val().replace(/\./g, '');
            $('#v_totalenvio').val(monto);
            puntodecimal('v_totalenvio');
            $('#txtmonto').html('Total Retiro Billetera');
        }



    }
}


var index = 0;
function getgiro() {
    giro = {
        'opcion': 5
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: giro,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                eliminar = "<button class='btn btn-sm btn-outline-danger' title='Eliminar Registro' onclick=\"$(\'#prod" + index + "\');eliminargiros()\">Eliminar</button>";
                ticket = "<button class='btn btn-sm btn-outline-info' title='Generar Ticket' onclick=\"$(\'#prod" + index + "\');generarticket(2)\">Ticket</button>";
                index++;
                $("#mitablagiros").append($("<tr id=\'cod" + index + "\'>").append($(
                        "<td style='display: none'>" + valor.iddetallegiro + "</td>" +
                        "<td>" + valor.operadora + "</td>" +
                        "<td>" + "0" + valor.nroorigen + "</td>" +
                        "<td>" + "0" + valor.nrodestino + "</td>" +
                        "<td>" + valor.operacion + "</td>" +
                        "<td>" + valor.montogirobilletera + "</td>" +
                        "<td>" + valor.porcentaje + "</td>" +
                        "<td>" + valor.montoenvio + "</td>" +
                        "<td>" + valor.fecha + "</td>" +
                        "<td>" + eliminar + " " + ticket + " </td>")));
            });
            var filas = $('#mitablagiros tr').length - 1;
            if (parseInt(filas) > 0) {
                totlagirobilletera();


            } else {

            }
        }
    });
}
function totlagirobilletera() {
    setTimeout(function () {
        var num;
        var oper;
        var montogiro = 0;
        var montobilletera = 0;
        var montoretiro = 0;
        var montoreverision = 0;
        var montogiroacum = 0;
        var montobilleteraacum = 0;
        var montoretiroacum = 0;
        var montoreversionaucm = 0;
        $('#mitablagiros').find('tbody').find('tr').each(function () {
            oper= $(this).find("td").eq(4).html();
            if (oper === 'GIRO') {
                montogiro = parseInt($(this).find("td").eq(7).html());
                montogiroacum = montogiroacum + montogiro;
            } else if (oper === 'BILLETERA') {
                montobilletera = parseInt($(this).find("td").eq(7).html());
                montobilleteraacum = montobilleteraacum + montobilletera;
            } else if (oper === 'RETIRO EFECT.') {
                montoretiro = parseInt($(this).find("td").eq(7).html());
                montoretiroacum = montoretiroacum + montoretiro;
            } else if (oper === 'REVERSIÓN') {
                montoreverision = parseInt($(this).find("td").eq(7).html());
                montoreversionaucm = montoreversionaucm + montoreverision;
            }

        });
        $('#v_totalGiro').val(montogiroacum);
        $('#v_totalBilletera').val(montobilleteraacum);
        $('#v_totalRetiro').val(montoretiroacum);
        $('#v_totalReversion').val(montoreversionaucm);
        puntodecimal('v_totalGiro', 'v_totalBilletera', 'v_totalRetiro', 'v_totalReversion');
    }, 1500);
}

function eliminargiro(cod) {
    datogiro = {
        'opcion': 6,
        'v_codgiro': cod
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: datogiro,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#mitablagiros').find('tbody').find('tr').empty();
            getgiro();

        }, error: function () {
            $.alert('No se puedo realizar la operación !!');
        }

    });
}


