
$(document).ready(function () {
    opcionBotonesRecargas();
    combooperadora();
    gerrecarga();
    combopagorecarga();

});

function generarcodigo() {

    var a = Math.random() * 99999999999999999;
    var m = a.toString().substr(1, 4);
    alert(m);

}

function opcionBotonesRecargas() {
        $('#v_ciclienterecarga').keyup(function () {
        puntodecimal('v_ciclienterecarga');

    });
      $('#v_ciclienterecarga').blur(function () {
        getcclienterecarga();

    });
       $('#btnguardarclienterecarga').click(function () {
        guardarclienter();

    });
    $('#v_montorecarga').keyup(function () {
        verificarcampoentero('v_montorecarga');
        puntodecimal('v_montorecarga');

    });
    $('#v_prefijo').keyup(function () {
        verificarcampoentero('v_prefijo');
        $('#v_prefijo').css('font-weight', 'bold');

    });
    $('#v_operadora').click(function () {
        obtenerprefijo();

    });
    $('#v_nrolinea').keyup(function () {
        verificarcampoentero('v_nrolinea');
        $('#v_nrolinea').css('font-weight', 'bold');

    });
    $('#v_nrotransaccion').keyup(function () {
        verificarcampoentero('v_nrotransaccion');
        $('#v_nrotransaccion').css('font-weight', 'bold');

    });
    $('#btnguardarecarga').click(function () {
        if ($('#v_montorecarga').val() === "" || $('#v_nrolinea').val() === "") {
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
            guardarrecarga();
        }

    });
    $('#btnnuevacarga').click(function () {
        $('#v_montorecarga').focus();

    });
}


function guardarrecarga() {
    var v = Math.random() * 99999999999999999;
    var nrooperacion = v.toString().substr(1, 4);
//    var prefijo = $('#v_prefijo').val();
    var nro = $('#v_nrolinea').val();
//    var numero = prefijo + "" + nro;
    var nrotrasac=0;
    var trnsc = $('#v_nrotransaccion').val();
    if (parseInt(trnsc) === null) {
        nrotrasac = 0;
    }else{
        nrotrasac;
    }
    datosrecarga = {
        "opcion": 1,
        "rec_montorecarga": $('#v_montorecarga').val().replace(/\./g, ''),
        "rec_nrodestino": nro,
        "rec_nrotransaccion": nrotrasac,
        "rec_codoperadora": $('#v_operadora').val(),
        "rec_obervacion": $('#v_observacion').val(),
        "rec_nrooperacion": nrooperacion,
        "rec_cliente": $('#v_idclienterecarga').val(),
        "rec_pago": $('#v_pagorecarga').val(),
        "rec_idusuer": $('#vCodIDuser').val()

    };
    $.ajax({
        url: "/syscontrol/recargaSERVLETXML",
        type: 'POST',
        data: datosrecarga,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#mitablarecarga').find('tbody').find('tr').empty();
            gerrecarga();
            vaciarcamposcargas();
            location.reload();

        },
        error: function () {
            $.alert('No se puedo realizar la operación !!');
        }
    });



}


function obtenerprefijo() {
    $("#v_prefijo").empty();
    $("#v_prefijo").val(null);
    $("#v_prefijo").removeAttr('disabled', true);
    datosprefijo = {
        'opcion': 2,
        'v_codoperadora': $('#v_operadora').val()
    };
    $.ajax({
        url: "/Control/recargaSERVLETXML",
        type: 'POST',
        data: datosprefijo,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#v_prefijo").append("<option> " + value.descripcion + "</option>");


            });

        }

    });
}


function combooperadora() {
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
                $("#v_operadora").append("<option value= \"" + value.idprefijo + "\"> " + value.descripcion + "</option>");

            });

        }
    });
}

var index = 0;
function gerrecarga() {
    recargadatos = {
        'opcion': 4
    };
    $.ajax({
        url: "/syscontrol/recargaSERVLETXML",
        type: 'POST',
        data: recargadatos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                eliminar = "<button class='btn btn-sm btn-outline-danger' title='Eliminar Registro' onclick=\"$(\'#prod" + index + "\');eliminarrecarga()\">Eliminar</button>";
                ticket = "<button class='btn btn-sm btn-outline-info' title='Generar Ticket' onclick=\"$(\'#prod" + index + "\');generarticket(1)\">Ticket</button>";
                index++;
                $("#mitablarecarga").append($("<tr id=\'cod" + index + "\'>").append($(
                        "<td style=display:none>" + valor.idapertura + "</td>" +
                        "<td style=display:none>" + valor.iddetallerecargas + "</td>" +
                        "<td>" + valor.operadora + "</td>" +
                        "<td>" + "0" + valor.nrodestino + "</td>" +
                        "<td>" + valor.montorecarga + "</td>" +
                        "<td>" + valor.nrotransaccion + "</td>" +
                        "<td>" + valor.fecharecarga + "</td>" +
                        "<td>" + eliminar + " " + ticket + " </td>")));
            });

            var filas = $('#mitablarecarga tr').length - 1;
            if (parseInt(filas) > 0) {
                saldosporapertura();
                recargastotales();
                saldodisponibles();


            } else {

            }
        }
    });
}

function eliminarrecargas(cod) {
    datosrecarga = {
        'opcion': 5,
        'v_codrecarga': cod
    };
    $.ajax({
        url: "/syscontrol/recargaSERVLETXML",
        type: 'POST',
        data: datosrecarga,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#mitablarecarga').find('tbody').find('tr').empty();
            gerrecarga();

        }, error: function () {
            $.alert('No se puedo realizar la operación !!');
        }

    });
}
////---------------------------------------------------------------//////
////---------------------------------------------------------------//////


function vaciarcamposcargas() {
    $('#v_nrolinea').val(null);
    $('#v_nrotransaccion').val(null);
    $('#v_observacion').val(null);
    $('#v_montorecarga').val(null);
    $('#v_montorecarga').focus();
}


//--MUESTRA EN CADA CAMPO LAS RECARGAS TOTALES POR OPERADORA--////
function recargastotales() {
    setTimeout(function () {
        var num;
        var operadora;
        var montotigo = 0;
        var montoclaro = 0;
        var montopersonal = 0;
        var montovox = 0;
        var tigoacum = 0;
        var claroacum = 0;
        var personalacum = 0;
        var voxacum = 0;
        $('#mitablarecarga').find('tbody').find('tr').each(function () {
            operadora = $(this).find("td").eq(2).html();
            if (operadora === 'TIGO') {
                montotigo = parseInt($(this).find("td").eq(4).html());
                tigoacum = tigoacum + montotigo;
            } else if (operadora === 'CLARO') {
                montoclaro = parseInt($(this).find("td").eq(4).html());
                claroacum = claroacum + montoclaro;
            } else if (operadora === 'PERSONAL') {
                montopersonal = parseInt($(this).find("td").eq(4).html());
                personalacum = personalacum + montopersonal;
            } else if (operadora === 'VOX') {
                montovox = parseInt($(this).find("td").eq(4).html());
                voxacum = voxacum + montovox;
            }

        });
        $('#v_totaltigo').val(tigoacum);
        $('#v_totalclaro').val(claroacum);
        $('#v_totalpersonal').val(personalacum);
        $('#v_totalvox').val(voxacum);
        puntodecimal('v_totaltigo', 'v_totalclaro', 'v_totalpersonal', 'v_totalvox');
    }, 1500);
}
//---------------------------------------------------------------///
//---------------------------------------------------------------///




//-----RECUPERA LOS SALDOS CARGADOS EN LA APERTURA POR OPERADORA--/////
function saldosporapertura() {
    setTimeout(function () {
        var num;
        $('#mitablarecarga').each(function () {
            num = parseInt($(this).find("td").eq(0).html());

        });
        obenersaldos(num);
    }, 1500);
}
//---------------------------------------------------------------///
//---------------------------------------------------------------///



//---MUESTRA LA DIFERENCIA ENTRE EL SALDO REGISTRADO EN LA APERTURA Y LA CARGA
//---QUE SE ESTA HACIENDO, VA CONTROLANDO EL SALDO DISPONIBLE POR OPERADORA PARA LA CARGA--////
function saldodisponibles() {
    setTimeout(function () {
        var tigo_apertura = $('#v_saldotigo').val().replace(/\./g, '');
        var claro_apertura = $('#v_saldoclaro').val().replace(/\./g, '');
        var personal_apertura = $('#v_saldopersonal').val().replace(/\./g, '');
        var vox_apertura = $('#v_saldovox').val().replace(/\./g, '');

        var tigo_recargatotal = $('#v_totaltigo').val().replace(/\./g, '');
        var claro_recargatotal = $('#v_totalclaro').val().replace(/\./g, '');
        var personal_recargatotal = $('#v_totalpersonal').val().replace(/\./g, '');
        var vox_recargatotal = $('#v_totalvox').val().replace(/\./g, '');

        var tigo_disponible = parseInt(tigo_apertura) - parseInt(tigo_recargatotal);
        $('#v_saldodisptigo').val(tigo_disponible);
        var claro_disponible = parseInt(claro_apertura) - parseInt(claro_recargatotal);
        $('#v_saldodispclaro').val(claro_disponible);
        var personal_disponible = parseInt(personal_apertura) - parseInt(personal_recargatotal);
        $('#v_saldodisppersonal').val(personal_disponible);
        var vox_disponible = parseInt(vox_apertura) - parseInt(vox_recargatotal);
        $('#v_saldodispvox').val(vox_disponible);


        puntodecimal('v_saldodisptigo', 'v_saldodispclaro', 'v_saldodisppersonal', 'v_saldodispvox');


    }, 1800);
}
//---------------------------------------------------------------///
//---------------------------------------------------------------///


function obenersaldos(cod) {
    datos = {
        'opcion': 3,
        'aper_nroapertura': cod
    };
    $.ajax({
        url: "/syscontrol/aperturaSERVLETXML",
        type: 'POST',
        data: datos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#v_saldotigo').val(value.montotigo);
                $('#v_saldoclaro').val(value.montoclaro);
                $('#v_saldopersonal').val(value.montopersonal);
                $('#v_saldovox').val(value.montovox);
            });

        }

    });
}

function combopagorecarga() {
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
                    $("#v_pagorecarga").append("<option value= \"" + value.idtipooperacion + "\"> " + value.descripcion + "</option>");
                }


            });

        }
    });
}

function getcclienterecarga() {
    cliente = {
        "opcion": 2,
        "v_cicliente": $('#v_ciclienterecarga').val().replace(/\./g, '')
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: cliente,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, value) {
                    $('#v_nombreclienterecarga').val(value.nombrecliente);
                    $('#v_idclienterecarga').val(value.idcliente);


                });
            } else {
                var v_cedula =$('#v_ciclienterecarga').val().replace(/\./g, '');
                if(v_cedula.length <6){
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
                }else{
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
                                $('#v_clienterecarga').modal('show');
                                $('#v_modalcedularecarga').val($('#v_ciclienterecarga').val());
                                $('#v_modalnombrerecarga').focus();
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

function guardarclienter() {
    cliente = {
        'opcion': 3,
        'v_nombrecliente': $('#v_modalnombrerecarga').val(),
        'v_cedulacliente': $('#v_modalcedularecarga').val().replace(/\./g, '')
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: cliente,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#v_clienterecarga').modal('hide');
            $('#v_ciclienterecarga').focus();
            $('#v_nombreclienterecarga').val($('#v_modalnombrerecarga').val());

        }

    });
}