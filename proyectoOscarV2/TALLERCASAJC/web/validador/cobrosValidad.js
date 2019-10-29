$(document).ready(function () {


});


var idx = 0;
function getcobro() {
    $('#mitblacobros').find('tbody').find('tr').empty();
    pcobros = {
        'opcion': 1,
        'cedula': $('#v_docu').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/cobrosSERVLET",
        type: 'POST',
        data: pcobros,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#v_cliente').val(valor.nombrecliente);
                $(this).find("td").css('color', 'blue');
                color = '#d9edf7';
                btn = "<a class='btn btn-md btn-danger' title='Registrar Cobros' onclick=\"$(\'#prod" + idx + "\');viewCobro();\">Cobrar</a>";
                idx++;
                $("#mitblacobros").append($("<tr id=\'prod" + idx + "\'>").append($(
                        "<td style=display:none>" + valor.id_cuencob + "</td>" +
                        "<td>" + valor.secuencia + "</td>" +
                        "<td>" + valor.fecha_cuencob + "</td>" +
                        "<td>" + valor.nrofactura + "</td>" +
                        "<td bgcolor=" + color + ">" + valor.cliente + "</td>" +
                        "<td>" + valor.saldo_cuencob + "</td>" +
                        "<td>" + valor.tipoventa + "</td>" +
                        "<td style=display:none>" + valor.id_venta + "</td>" +
                        "<td style='text-align: center'> " + btn + "</td>")));
            });
            totaldeuda();

        }

    });
}

function totaldeuda() {
    monto = 0;
    acumu = 0;

    $('#mitblacobros').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(5).html());
        acumu = acumu + monto;

    });
    $('#vTotaldeuda').val(acumu);
    $('#vTotaldeuda').css('font-weight:', 'bold');
    numeroDecimal('vTotaldeuda');
}

function viewCobro() {

    $('#mitblacobros tr').click(function () {
        datosTabla = {
            "_codCobro": $(this).find("td").eq(0).html(),
            "_total": $(this).find("td").eq(5).html(),
            "_factura": $(this).find("td").eq(2).html(),
            "_codventa": $(this).find("td").eq(7).html()
        };
        $('#cobroview').modal('show');
        $('#v_totalcobro').val(datosTabla._total);
        $('#factura_cobro').val(datosTabla._factura);
        $('#v_clienteci').val($('#v_docu').val());
        $('#v_clientenombre').val($('#v_cliente').val());
        $('#diferencia_v').val(datosTabla._total);
        $('#vCodigoCobro').val(datosTabla._codCobro);
        $('#vCodventa').val(datosTabla._codventa);
            numeroDecimal('v_totalcobro');
    numeroDecimal('diferencia_v');
    $('#totalcobro_v').val('0');

    });




}


function condicionVentaCobro() {
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


function guardarCobro() {
    var n = $('#tabladetallecobros tr').length - 1;
    if (parseInt(n) < 1) {
            alert('No hay detalle que Guardar!');
    } else if (parseInt($('#diferencia_v').val()) > 0) {
     
            alert('EL cobro no se pudo realizar, verifique los importes cargados..!');

    } else {
        $('#tabladetallecobros').find('tbody').find('tr').each(function () {
            detallecobro = {
                "opcion": 16,
                "impote_v": $(this).find("td").eq(10).html().replace(/\./g, ''),
                "idtipopago_v": $(this).find("td").eq(0).html(),
                "codigocobro": $('#vCodigoCobro').val()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
                type: 'POST',
                data: detallecobro,
                cache: false,
                success: function () {
                },
                error: function () {
                }
            });

        });
        setTimeout(function () {
            cobrodetalle();
        }, 2000);
    }



}
function cobrodetalle() {
    var cobro = 0;
    $('#tabladetallecobros').find('tbody').find('tr').each(function () {
        cobro = $(this).find("td").eq(0).html();
        switch (parseInt(cobro)) {
            case 2:
                tarjeta = {
                    "opcion": 17,
                    "codCobrov": $('#vCodigoCobro').val(),
                    "nroboleta_v": $(this).find("td").eq(9).html(),
                    "entidademisora_v": $(this).find("td").eq(6).html(),
                    "tipotarjeta_v": $(this).find("td").eq(5).html()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
                    type: 'POST',
                    data: tarjeta,
                    cache: false,
                    success: function () {
                    },
                    error: function () {
                    }
                });
                break;
            case 3:
                Cheque = {
                    "opcion": 18,
                    "codCobro": $('#vCodigoCobro').val(),
                    "nrocheque_v": $(this).find("td").eq(2).html(),
                    "tipocheque_v": $(this).find("td").eq(12).html(),
                    "banco_v": $(this).find("td").eq(3).html()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
                    type: 'POST',
                    data: Cheque,
                    cache: false,
                    success: function () {
                    },
                    error: function () {
                    }
                });
                break;
            default :
                break;

        }
    });
    alert('Venta facturada');
    location.reload();
}


var indx = 0;
function cargarfilacobro() {
    var cobrado = $('#totalcobro_v').val().replace(/\./g, '');
    var totalfact = $('#v_totalcobro').val().replace(/\./g, '');
    var montocobrar = $('#v_montocobrar').val().replace(/\./g, '');
    var diferencia = $('#diferencia_v').val().replace(/\./g, '');
    var totalcargado = parseInt(montocobrar) + parseInt(cobrado);
    if (parseInt(totalcargado) > parseInt(totalfact)) {
        alert('El cobro no debe superar el monto de la factura.. ');
    } else {
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
    numeroDecimal('diferencia_v');
    numeroDecimal('totalcobro_v');
    var _acobrar = $('#v_totalcobro').val().replace(/\./g, '');
    var _cobrado = $('#totalcobro_v').val().replace(/\./g, '');
    var dif = parseInt(_acobrar) - parseInt(_cobrado);
    $('#diferencia_v').val(dif);
    numeroDecimal('diferencia_v');
    indx++;

}