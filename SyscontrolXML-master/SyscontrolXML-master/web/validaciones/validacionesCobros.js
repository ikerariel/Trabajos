$(document).ready(function () {
    comboTipoDoc();
    opiconesCobro();
});
function opiconesCobro() {
    $('#v_tipocobro').change(function () {
        condicionCobro();
    });
    $('#v_montocobrar').keyup(function () {
        puntodecimal('v_montocobrar');
    });
    $('#btnguardarCobro').click(function () {
        var n = $('#tabladetallecobros tr').length - 1;
        if (parseInt(n) < 1) {
            $.confirm({
                title: 'AVISO!',
                content: 'No hay detalle que Guardar!',
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

        }else if( parseInt($('#diferencia_v').val()) > 0){
                      $.confirm({
                title: 'AVISO!',
                content: 'EL cobro no se pudo realziar, verifique los importes cargados..!',
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
             insertarCobro();
        }
       
    });
}

function comboTipoDoc() {

    var array = ["Cedula", "Nro.Factura"];
    var cont = 0;
    for (var i in array) {
        cont++;
        document.getElementById("ctipodoac").innerHTML += "<option value='" + cont + "'>" + array[i] + "</option>";
    }

}


var index = 0;
function getCobros() {
    var cfac;
    var cCi;
    var op;
    op = $('#ctipodoac').val();
    if (parseInt(op) === 1) {
        cCi = $('#fclienteci').val();
        cfac = "";
    } else if (parseInt(op) === 2) {
        cCi = "";
        cfac = $('#fclienteci').val();
    }

    $('#mitblaCobrosDettalle').find('tbody').find('tr').empty();
    js = {
        'opcion': 11,
        'cfac': cfac,
        'cCi': cCi
    };
    $.ajax({
        url: "/syscontrol/ventasSERVLET",
        type: 'POST',
        data: js,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                if (parseInt(op) === 1) {
                    $('#fclientenombre').val(valor.nombrecliente);
                } else if (parseInt(op) === 2) {
                    $('#fclientenombre').val(valor.cliente);
                }
                ticket = "<button class='btn btn-sm btn-danger' title='Generar Ticket' onclick=\"$(\'#prod" + index + "\');cobrarCta()\">Cobrar</button>";
                index++;
                $("#mitblaCobrosDettalle").append($("<tr id=\'cod" + index + "\'>").append($(
                        "<td>" + valor.idcobro + "</td>" +
                        "<td>" + valor.numerodocumento + "</td>" +
                        "<td>" + valor.fechasaldo + "</td>" +
                        "<td>" + valor.saldo + "</td>" +
                        "<td>" + valor.importe + "</td>" +
                        "<td>" + valor.estado + "</td>" +
                        "<td>" + ticket + " </td>")));
            });
            setTimeout(function () {
                totalCobro();
            }, 1200);
        }
    });
}

function cobrarCta() {
    var codigoCobro;
    var nrofac;
    var montofac;
    var cliente;
    $("#mitblaCobrosDettalle tr").click(function () {
        datos = {
            'codigoCobro': $(this).find("td").eq(0).html(),
            'nrofac': $(this).find("td").eq(1).html(),
            'montofac': $(this).find("td").eq(3).html(),
            'cliente': $('#fclientenombre').val()
        };
//        codigoCobro = $(this).find("td").eq(0).html();
//        nrofac = $(this).find("td").eq(1).html();
//        montofac = $(this).find("td").eq(3).html();
//        cliente = $('#fclientenombre').val();
        $('#v_modalnrofac').val(datos.nrofac);
        $('#v_totalcobro').val(datos.montofac);
        $('#v_clientenombre').val(datos.cliente);
        $('#v_idcobro').val(datos.codigoCobro);
        puntodecimal('v_totalcobro');
    });
    $('#cobroview').modal('show');
//    $('#v_clientefacturacion').va();
//    $('#v_clientefacturacion').va();
//    $('#v_clientefacturacion').va();
}


function totalCobro() {
    $('#totalfactura').val("0");
    $('#totalcobrado').val("0");
    var totalfac = 0;
    var totalf = 0;
    var totalcob = 0;
    var totlacobrado = 0;
    $('#mitblaCobrosDettalle').find('tbody').find('tr').each(function () {
        totalfac = parseInt($(this).find("td").eq(3).html());
        totlacobrado = parseInt($(this).find("td").eq(4).html());
        totalf = totalf + totalfac;
        totalcob = totalcob + totlacobrado;
        $('#totalfactura').val(totalf);
        $('#totalcobrado').val(totalcob);
        $('#totalfactura').css('font-size', '13pt');
        $('#totalfactura').css('color', 'red');
        $('#totalfactura').css('font-weight', 'bold');
        $('#totalcobrado').css('font-size', '13pt');
        $('#totalcobrado').css('color', 'green');
        $('#totalcobrado').css('font-weight', 'bold');
    });
    puntodecimal('totalfactura');
    puntodecimal('totalcobrado');
}


function condicionCobro() {
    var valor = $('#v_tipocobro').val();
    switch (parseInt(valor)) {
        case 1:
            $('#v_montocobrar').focus();
            $("#v_chque").hide();
            $("#v_tarjeta").hide();
            $('#texcobro_v').html('Cobro en Efectivo..');
            break;
        case 2:
            $("#v_tarjeta").show();
            $("#v_chque").hide();
            $('#texcobro_v').html('Cobro en Tarjeta..');
            break;
        case 3:
            $("#v_chque").show();
            $("#v_tarjeta").hide();
            $('#texcobro_v').html('Cobro en Cheque..');
            break;
    }
}

var indx = 0;
function agregarfilacobro() {
    var cobrado = $('#totalcobro_v').val().replace(/\./g, '');
    var totalfact = $('#v_totalcobro').val().replace(/\./g, '');
    var montocobrar = $('#v_montocobrar').val().replace(/\./g, '');
    var diferencia = $('#diferencia_v').val().replace(/\./g, '');
    var totalcargado = parseInt(montocobrar) + parseInt(cobrado);
      if (parseInt(diferencia) > 0) {
                  $.confirm({
            title: 'AVISO!',
            content: 'El cobro no debe superar el monto de la factura.. ',
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
      
    }  else {
        var v_tcobro = $('#v_tipocobro').val();
        var v_tcobrodescrip = $('#v_tipocobro option:selected').text();
        var v_nrocheque = $('#nrochque_ch').val();
        var v_bcocheque = $('#banco_che').val();
        var v_bcochequedescripcion = $('#banco_che option:selected').text();
        var v_idtipochque = $('#tipocheque_ch').val();
        var v_tipocheque = $('#tipocheque_ch option:selected').text();
        var v_tipotarjeta = $('#tarjettipo_t').val();
        var v_tipotarjetadescripcion = $('#tarjettipo_t option:selected').text();
        var v_identidademisora = $('#entemisora_t').val();
        var v_entidademisora = $('#entemisora_t option:selected').text();
        var v_nroboletatarjeta = $('#nroboleta_t').val();
        var v_montocobrar = $('#v_montocobrar').val();
        switch (parseInt(v_tcobro)) {
            case 1:
                v_bcocheque = 0;
                v_nrocheque = 0;
                v_bcochequedescripcion = 0;
                v_tipotarjeta = 0;
                v_tipotarjetadescripcion = 0;
                v_nroboletatarjeta = 0;
                v_identidademisora = 0;
                v_entidademisora = 0;
                v_idtipochque = 0;
                v_tipocheque = 0;
                break;
            case 2:
                v_bcocheque = 0;
                v_nrocheque = 0;
                v_bcochequedescripcion = 0;
                v_idtipochque = 0;
                v_tipocheque = 0;
                break;
            case 3:
                v_tipotarjeta = 0;
                v_tipotarjetadescripcion = 0;
                v_nroboletatarjeta = 0;
                v_identidademisora = 0;
                v_entidademisora = 0;
                break;
        }

        indx++;
        $('#tabladetallecobros').append("<tr id=\'prod" + indx + "\'>\
            <td style='display: none'>" + v_tcobro + "</td>\n\
            <td>" + v_tcobrodescrip + "</td>\n\
            <td>" + v_nrocheque + "</td>\n\
            <td style='display: none'>" + v_bcocheque + "</td>\n\
            <td>" + v_bcochequedescripcion + "</td>\n\
            <td style='display: none'>" + v_tipotarjeta + "</td>\n\
            <td style='display: none'>" + v_identidademisora + "</td>\n\
            <td>" + v_entidademisora + "</td>\n\
            <td>" + v_tipotarjetadescripcion + "</td>\n\
            <td>" + v_nroboletatarjeta + "</td>\n\
            <td>" + v_montocobrar + "</td>\n\
            <td ><button type=button title='Quitar el registro de la lista' style=text-align:center class='btn btn-sm btn-danger' onclick=\"$(\'#prod" + indx + "\').remove(),totalcobro()\">Quitar</button></td>\n\
            <td style='display: none'>" + v_idtipochque + "</td>\n\
            <td style='display: none'>" + v_tipocheque + "</td>\n\
            </tr>");
        totalcobro();
    }
}
function totalcobro() {
    var total = 0;
    $('#tabladetallecobros tbody').find('tr').each(function (i, el) {
        total += parseFloat($(this).find('td').eq(10).text().replace(/\./g, ''));
    });
    $('#totalcobro_v').val(total);
    puntodecimal('diferencia_v');
    puntodecimal('totalcobro_v');
    var _acobrar = $('#v_totalcobro').val().replace(/\./g, '');
    var _cobrado = $('#totalcobro_v').val().replace(/\./g, '');
    var dif = parseInt(_acobrar) - parseInt(_cobrado);
    $('#diferencia_v').val(dif);
    puntodecimal('diferencia_v');
    indx++;
}



function insertarCobro() {

    $('#tabladetallecobros').find('tbody').find('tr').each(function () {
        cobro = {
            'opcion': 12,
            'codCobro': $('#v_idcobro').val(),
            'importeCobro': $(this).find("td").eq(10).html().replace(/\./g, ''),
            'codTipoCobro': $(this).find("td").eq(0).html()
        };
        $.ajax({
            url: "/syscontrol/ventasSERVLET",
            type: 'POST',
            data: cobro,
            cache: false,
            dataType: 'text',
            success: function () {
                $.alert('Guardado correctamente !!');
                location.reload();
            }

        });
    });

}
