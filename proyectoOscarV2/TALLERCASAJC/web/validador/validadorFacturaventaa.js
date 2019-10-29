$(document).ready(function () {
//    obtenerNroFactura();
    listaparametros();
//    condicionCobro();
    listartipopago();
    obtenerventa();
    listarvendedor();
    fechafac();
    condicionventa();
    listararticulos();
    listadocumentos();
    numLetras();



});
function fechafac() {
    var fv = new Date();
    $('#v_fechafac').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
    $('#_fecha').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
    $('#fechavto_v').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
}

function obtenerNroFactura() {
    json = {
        "opcion": 1,
        "caja_v": $('#nrocaja_v option:selected').text()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: json,
        cache: false,
        success: function (data) {
            if (JSON.stringify(data) != '[]') {
                $.each(data, function (indice, valor) {
                    $('#idventa_v').val(valor.idgenerico);
                    $('#nrofac_v').val(valor.descripgenerico);
                });

            } else {
                $('#nrofac_v').val(null);
            }


        },
        error: function () {
        }

    });
}
function obtenerventa() {
    jsonv = {
        "opcion": 9
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsonv,
        cache: false,
        success: function (data) {

            $('#idventa_v').val(data);


        },
        error: function () {
        }

    });
}
function listararticulos() {
    articulos = {
        "opcion": 20,
        "codDepo": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: articulos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                var cant = value.cantidad;
                if (cant > 0) {
                    $("#listaarti").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + " Cantidad : " + value.cantidad + "</option>");

                }

            });

        }
    });
}
function listadocumentos() {
    articulos = {
        "opcion": 21
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: articulos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#vtipodocumento").append("<option value= \"" + value.idgenerico + "\"> " + value.descripgenerico + "</option>");

            });

        }
    });
}
function listaparametros() {
    parametros = {
        "opcion": 12

    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: parametros,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {

                $('#tabladetalleparametros').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + value.id_timbrado + "</td>\n\
            <td>" + value.tipodocumento + "</td>\n\
            <td>" + value.numero + "</td>\n\
            <td>" + value.inicio_fecha + "</td>\n\
            <td>" + value.vencimientos + "</td>\n\
            <td>" + value.est_descripcion + "</td>\n\
            <td>" + value.fac_caja + "</td></tr>");


            });

        }
    });
}
function listarvendedor() {
    vendedor = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: vendedor,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#listavendedor").append("<option value= \"" + value.idgenerico + "\"> " + value.descripgenerico + "</option>");

            });

        }
    });
}
function listartipopago() {
    tipopago = {
        "opcion": 6
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: tipopago,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#tipopago_v").append("<option value= \"" + value.idgenerico + "\"> " + value.descripgenerico + "</option>");

            });

        }
    });
}
function obtenerarticulos() {
    var exis = 0;
    art = {
        "opcion": 19,
        "codArticulo": $('#articulo_v').val(),
        "coddepos": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                exis = value.cantidad;
                if (exis > 0) {
                    $('#producto_v').val(value.art_descripcion);
                    $('#punitario_v').val(value.precventas);
                    $('#impuesto_v').val(value.impuesto);
                    $('#idimpuesto_v').val(value.id_impuesto);
                    $('#canti_v').focus();
                    $('#canti_v').val(1);
                    valores('punitario_v');

                } else {
                    alert("PRODUCTO SIN STOCK...");
                }

            });

        }
    });

}
function obtenervendedor() {
    _vendedor = {
        "opcion": 4,
        "cod_vend": $('#vendedor_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: _vendedor,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#vendedormombre_v').val(value.descripgenerico);
                $('#articulo_v').focus();


            });

        }
    });
}
function obtenerCliente() {
    jsonc = {
        "opcion": 2,
        "ci_v": $('#cedula_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsonc,
        cache: false,
        success: function (data) {
            if (JSON.stringify(data) != '[]') {
                $.each(data, function (indice, valor) {
                    $('#idcliente_v').val(valor.idgenerico);
                    $('#razonsocial_v').val(valor.descripgenerico);
                    $('#cedula_v').val(valor.cedula);
                    $('#vendedor_v').focus();
                });

            } else {
                var consulta = confirm('Cliente no Registrado, desea agregar ?');
                if (consulta) {
                    $('#viewCliente').modal('show');
                    $('#ruc_v').val($('#cedula_v').val());
                    $('#cliente_v').focus();
                }
            }

        },
        error: function () {

        }

    });
}
function actufactura() {
    jsoncfac = {
        "opcion": 7,
        "fac": $('#idfactura_v').val(),
        "estado_v": 6
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsoncfac,
        cache: false,
        success: function () {

        },
        error: function () {

        }

    });





}
function anularfactura() {
    anufac = {
        "opcion": 11,
        "numerofac_v": $('#nrofactura_v').val(),
        "codestado_v": 2
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: anufac,
        cache: false,
        success: function (resp) {
            alert('FACTURA ANULADA');
        },
        error: function () {

        }

    });


}

function grabarcliente() {
    cliente = {
        "opcion": 10,
        "ruc_cliente": $('#ruc_v').val(),
        "razonsocial_cliente": $('#cliente_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: cliente,
        cache: false,
        success: function () {
            alert('GUARDADOR');
        },
        error: function () {

        }

    });

}
function grabartimbrado() {
    timbrados = {
        "opcion": 13,
        "nrotimbrado_v": $('#_timbrado').val(),
        "fvto_v": $('#_fechavto').val(),
        "coduser_v": $('#idusersession_v').val(),
        "establecimiento_v": $('#_nroexpe').val(),
        "caja_v": $('#_nrocaja').val(),
        "fdesde_v": $('#_facdesde').val(),
        "fhasta_v": $('#_fachasta').val(),
        "tipodocuemtno": $('#vtipodocumento').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: timbrados,
        cache: false,
        dataType: 'text',
        success: function (resp) {
            setTimeout(function () {
                guardarDetallefactura();
                alert('Guardado correctamente');
                location.reload();
            }, 1200);
        },
        error: function () {

        }

    });


}

//function abricobro() {
//    valores('subtotal_v');
//    $('#cobroview').modal('show');
//    $('#v_totalcobro').val($('#subtotal_v').val());
//    $('#factura_cobro').val($('#nrofac_v').val());
//    $('#v_clienteci').val($('#cedula_v').val());
//    $('#v_clientenombre').val($('#razonsocial_v').val());
//    $('#diferencia_v').val($('#v_totalcobro').val());
//
//}
function calculocuotaVenta() {
    if ($('#subtotal_v').val() === "0") {
        alert('No se encuetra registros..');
    } else {
        var cancuota = $('#cantcuota_v').val();
        var montoventa = $('#subtotal_v').val().replace(/\./g, '');
        var montocuota = parseInt(montoventa) / parseInt(cancuota);
        $('#montocuota_v').val(Math.trunc(montocuota));
        numeroDecimal('montocuota_v');
    }

}
function guardarventa() {
    var filas = $('#v_tablaDetalle tr').length - 1;
    var fac = $('#nrofac_v').val();
    var cedu = $('#cedula_v').val();
    var rzonsocial = $('#razonsocial_v').val();
    var vende = $('#vendedor_v').val();
    var vendenombre = $('#vendedormombre_v').val();
    if (parseInt(filas) <= 0) {
        alert('No hay detalle a guardar...');
    } else {
        if (fac === "" || cedu === "" || rzonsocial === "" || vende === "" || vendenombre === "") {
            alert('Algunos datos no fueron cargados correctamente');
        } else {
            var resp = confirm("Desea guardar la VENTA ?");
            var condventa = $('#condventa_v').val();
            var pedido = $('#v_nroPedidofact').val();
            var tipo;
            if (parseInt(condventa) === 1) {
                tipo = $('#subtotal_v').val().replace(/\./g, '');
            } else if (parseInt(condventa) === 2) {
                tipo = $('#montocuota_v').val().replace(/\./g, '');
            }
            if(parseInt(pedido)>0){
                pedido = $('#v_nroPedidofact').val();
            }else{
                pedido=0;
            }
            if (resp) {
                venta = {
                    "opcion": 5,
                    "nrofac_v": $('#nrofac_v').val(),
                    "condVenta": $('#condventa_v').val(),
                    "idcliente_v": $('#idcliente_v').val(),
                    "idusuario_v": $('#idusersession_v').val(),
                    "iddeposito_v": $('#coddeposito_v').val(),
                    "idvendendor_v": $('#vendedor_v').val(),
                    "codapertura": $('#codapertura_ap').val(),
                    "codIdfacura": $('#idfactura_v').val(),
                    "vCantCuota": $('#cantcuota_v').val(),
                    "vMontoCuota": tipo,
                    "vIntervalo": $('#intervalo_v').val(),
                    "vFechaVto": $('#fechavto_v').val(),
                    "codpedidoventa": pedido
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
                    type: 'POST',
                    data: venta,
                    cache: false,
                    dataType: 'text',
                    success: function (resp) {
                        setTimeout(function () {
                            guardarDetalle();
                            //        guardarCobro();
                        }, 1000);
                        setTimeout(function () {
                            actufactura();
                            alert('Venta Guardada.!!');
                            location.reload();
                        }, 1250);

                    },
                    error: function () {

                    }

                });
            } else {

            }


        }

    }


}


function guardarDetalle() {
    $('#v_tablaDetalle').find('tbody').find('tr').each(function () {
        datosdetalle = {
            "opcion": 8,
            "codarticulo_v": $(this).find("td").eq(0).html(),
            "codventa_v": $('#idventa_v').val(),
            "cantidad_v": $(this).find("td").eq(2).html(),
            "preciou_v": $(this).find("td").eq(3).html(),
            "codimpuesto_v": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
            type: 'POST',
            data: datosdetalle,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });
}
function guardarDetallefactura() {
    $('#tablaparametros').find('tbody').find('tr').each(function () {
        datosdetallefac = {
            "opcion": 14,
            "secuenca_v": $(this).find("td").eq(0).html(),
            "numfactura_v": $(this).find("td").eq(3).html()

        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
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
function condicionventa() {
    var valor = $('#condventa_v').val();


    if (parseInt(valor) === 1) {
        $("#cantcuota_v").prop('disabled', true);
        $("#montocuota_v").prop('disabled', true);
        $("#fechavto_v").prop('disabled', true);
        $("#intervalo_v").prop('disabled', true);
        $("#cantcuota_v").val('0');
        $("#montocuota_v").val('0');
        $("#intervalo_v").val('0');
    } else {
        if (parseInt(valor) === 2) {
            $("#cantcuota_v").removeAttr('disabled', true);
            $("#montocuota_v").removeAttr('disabled', true);
            $("#fechavto_v").removeAttr('disabled', true);
            $("#cantcuota_v").val(null);
            $("#montocuota_v").val(null);
            $("#intervalo_v").val('30');
        }
    }

}
function tipoFact() {
    var valor = $('#opcFacturacion_v').val();


    if (parseInt(valor) === 1) {
        $("#v_nroPedidofact").prop('disabled', true);
        $("#cedula_v").focus();
    } else {
        if (parseInt(valor) === 2) {
            $("#v_nroPedidofact").removeAttr('disabled', true);
            $("#v_nroPedidofact").focus();

        }
    }

}

function cargatablaVentas() {
    var ban = false;
    if ($('#articulo_v').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#articulo_v').val();
        var codigo;
        $('#v_tablaDetalle').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    agregarfilaventas();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            agregarfilaventas();
        }






    }

}
var tindex = 0;
function agregarfilaventas() {

    var v_cod = $('#articulo_v').val();
    var v_producto = $('#producto_v').val();
    var v_cantidad = $('#canti_v').val();
    var v_precioUnitario = $('#punitario_v').val().replace(/\./g, '');
    var v_subtotal = v_cantidad * v_precioUnitario;
    var v_impuesto = $('#impuesto_v').val();
    var v_idimpuesto = $('#idimpuesto_v').val();
    var v_impu10 = "0";
    var v_impu5 = "0";
    var v_impuexe = "0";
    if (v_impuesto === 'IVA 10%') {
        v_impu10 = v_impuesto;
    } else {
        if (v_impuesto === 'IVA 5%') {
            v_impu5 = v_impuesto;
        } else {
            if (v_impuesto === 'EXENTA') {
                v_impuexe = v_impuesto;
            }
        }

    }
    tindex++;
    $('#v_tablaDetalle').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_cod + "</td>\n\
            <td>" + v_producto + "</td>\n\
            <td>" + v_cantidad + "</td>\n\
            <td>" + v_precioUnitario + "</td>\n\
            <td style='display: none'>" + v_idimpuesto + "</td>\n\
            <td>" + v_impu5 + "</td>\n\
            <td>" + v_impu10 + "</td>\n\
            <td>" + v_impuexe + "</td>\n\
            <td>" + v_subtotal + "</td>\n\
          <td ><button type=button title='Quitar el registro de la lista' style=text-align:center class='btn btn-sm btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove(),subtotal()\">Quitar</button></td>\n\
            </tr>");

    $('#canti_v').focus();
    subtotal();

}
var idxx = 0;
function traerPedidoVenta() {
    jsonc = {
        "opcion": 19,
        "facPedidoVenta": $('#v_nroPedidofact').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: jsonc,
        cache: false,
        success: function (data) {
            if (JSON.stringify(data) != '[]') {
                var valor = JSON.stringify(data);
                var vValor = JSON.parse(valor);
                var ped= vValor[0].pedido;
                if (parseInt(ped) === 0) {
                    $.each(data, function (indice, value) {
                        $('#cedula_v').val((value.cedula));
                        obtenerCliente();
                        $('#vendedor_v').val((value.idvendedor));
                        obtenervendedor();
                        var v_impuesto = value.impuesto;
                        var v_idimpuesto = value.id_impuesto;
                        var v_impu10 = "0";
                        var v_impu5 = "0";
                        var v_impuexe = "0";
                        if (v_impuesto === 'IVA 10%') {
                            v_impu10 = v_impuesto;
                        } else {
                            if (v_impuesto === 'IVA 5%') {
                                v_impu5 = v_impuesto;
                            } else {
                                if (v_impuesto === 'EXENTA') {
                                    v_impuexe = v_impuesto;
                                }
                            }

                        }
                        sSubtoral = value.precio * value.cantidad;
                        $('#v_tablaDetalle').append("<tr id=\'prod" + idxx + "\'>\
                                    <td >" + value.id_articulo + "</td>\n\
                                    <td>" + value.articulo + "</td>\n\
                                    <td>" + value.cantidad + "</td>\n\
                                    <td>" + value.precio + "</td>\n\
                                    <td style='display: none'>" + value.id_impuesto + "</td>\n\
                                    <td>" + v_impu5 + "</td>\n\
                                    <td>" + v_impu10 + "</td>\n\
                                    <td>" + v_impuexe + "</td>\n\
                                    <td>" + sSubtoral + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idxx + "\').remove();subtotal();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td>\n\
            </tr>");

                    });
                    setTimeout(function () {
                        subtotal();
                    }, 1200);

                } else {
                    alert('Pedido de Venta ya fue procesada.!!');
                }

            } else {
                alert('Datos no encontrados..');
                $("#v_nroPedidofact").focus();
            }

        },
        error: function () {

        }

    });
}

function subtotal() {
    monto = 0;
    acumu = 0;

    $('#v_tablaDetalle').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(8).html());
        acumu = acumu + monto;

    });
    $('#subtotal_v').val(acumu);

    valores('subtotal_v');

    $('#totoles').val($('#subtotal_v').val().replace(/\./g, ''));
    numLetras();

    tindex++;
//    montoLetras();
}

function valores(numero) {
    var num = document.getElementById(numero).value.replace(/\./g, '');
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        document.getElementById(numero).value = num;
    } else {
        alert('Solo se permiten numeros');
        document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');
    }

}

function cargarFactura() {
    $('#_tablefact').find('tbody').find('tr').empty();
    $('#fac').html($('#nrofac_v').val());
    $('#ruc').html($('#cedula_v').val());
    $('#cliente').html($('#razonsocial_v').val());
    $('#fccha').html($('#v_fechafac').val());

    $('#v_tablaDetalle').find('tbody').find('tr').each(function () {
        datosTabla = {
            "_cant": $(this).find("td").eq(2).html(),
            "_descrip": $(this).find("td").eq(1).html(),
            "_punitario": $(this).find("td").eq(3).html(),
            "_exent": $(this).find("td").eq(7).html(),
            "_iva5": $(this).find("td").eq(5).html(),
            "_iva10": $(this).find("td").eq(6).html()
        };
        $("#_tablefact").append($("<tr>").append($(
                "<td>" + datosTabla._cant + "</td>" +
                "<td>" + datosTabla._descrip + "</td>" +
                "<td>" + datosTabla._punitario + "</td>" +
                "<td>" + datosTabla._exent + "</td>" +
                "<td>" + datosTabla._iva5 + "</td>" +
                "<td style='background: #ffffcc'>" + datosTabla._iva10 + "</td>")));
    });
}
_index = 0;
function generarparametros() {
    var cont = 0;
    var _expe = $('#_nroexpe').val();
    var _caja = $('#_nrocaja').val();
    var _facdesde = $('#_facdesde').val();
    var _fachasta = $('#_fachasta').val();
    for (var i = 0, i = parseInt(_facdesde); i <= parseInt(_fachasta); i++) {
        cont++;
        var v11 = JSON.parse([i]);
        var v13 = v11.toString();
        var v14 = v13.padStart(7, "0000000");
        $('#tablaparametros').append("<tr id=\'prod" + _index + "\'>\
                 <td>" + cont + "</td>\n\
                 <td>" + _expe + "</td>\n\
                 <td>" + _caja + "</td>\n\
                 <td>" +
                v14 + "</td>\n\
                </tr>");

    }
}




function montoLetras() {

    montojson = {
        "opcion": 20,
        "monto": $('#subtotal_v').val().replace(/\./g, '')
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ventaSERVLET",
        type: 'POST',
        data: montojson,
        cache: false,
        dataType: 'text',
        success: function (resp) {
            $('#montoLetrasV').html(resp);
        },
        error: function () {
        }
    });


}



function numLetras() {
    $('#totoles').val(function (e) {
        document.getElementById("montoLetrasV").innerHTML = NumeroALetras(this.value);
        $('#montoLetrasV').css('color', 'blue');
    });

    function Unidades(num) {

        switch (num)
        {
            case 1:
                return "UN";
            case 2:
                return "DOS";
            case 3:
                return "TRES";
            case 4:
                return "CUATRO";
            case 5:
                return "CINCO";
            case 6:
                return "SEIS";
            case 7:
                return "SIETE";
            case 8:
                return "OCHO";
            case 9:
                return "NUEVE";
        }

        return "";
    }
    function Decenas(num) {
        decena = Math.floor(num / 10);
        unidad = num - (decena * 10);

        switch (decena)
        {
            case 1:
            switch (unidad)
            {
                case 0:
                    return "DIEZ";
                case 1:
                    return "ONCE";
                case 2:
                    return "DOCE";
                case 3:
                    return "TRECE";
                case 4:
                    return "CATORCE";
                case 5:
                    return "QUINCE";
                default:
                    return "DIECI" + Unidades(unidad);
            }
            case 2:
            switch (unidad)
            {
                case 0:
                    return "VEINTE";
                default:
                    return "VEINTI" + Unidades(unidad);
            }
            case 3:
                return DecenasY("TREINTA", unidad);
            case 4:
                return DecenasY("CUARENTA", unidad);
            case 5:
                return DecenasY("CINCUENTA", unidad);
            case 6:
                return DecenasY("SESENTA", unidad);
            case 7:
                return DecenasY("SETENTA", unidad);
            case 8:
                return DecenasY("OCHENTA", unidad);
            case 9:
                return DecenasY("NOVENTA", unidad);
            case 0:
                return Unidades(unidad);
        }
    }//Unidades()

    function DecenasY(strSin, numUnidades) {
        if (numUnidades > 0)
            return strSin + " Y " + Unidades(numUnidades)

        return strSin;
    }//DecenasY()

    function Centenas(num) {

        centenas = Math.floor(num / 100);
        decenas = num - (centenas * 100);

        switch (centenas)
        {
            case 1:
                if (decenas > 0)
                    return "CIENTO " + Decenas(decenas);
                return "CIEN";
            case 2:
                return "DOSCIENTOS " + Decenas(decenas);
            case 3:
                return "TRESCIENTOS " + Decenas(decenas);
            case 4:
                return "CUATROCIENTOS " + Decenas(decenas);
            case 5:
                return "QUINIENTOS " + Decenas(decenas);
            case 6:
                return "SEISCIENTOS " + Decenas(decenas);
            case 7:
                return "SETECIENTOS " + Decenas(decenas);
            case 8:
                return "OCHOCIENTOS " + Decenas(decenas);
            case 9:
                return "NOVECIENTOS " + Decenas(decenas);
        }

        return Decenas(decenas);
    }//Centenas()

    function Seccion(num, divisor, strSingular, strPlural) {
        cientos = Math.floor(num / divisor)
        resto = num - (cientos * divisor)

        letras = "";

        if (cientos > 0)
            if (cientos > 1)
                letras = Centenas(cientos) + " " + strPlural;
            else
                letras = strSingular;

        if (resto > 0)
            letras += "";

        return letras;
    }//Seccion()

    function Miles(num) {
        divisor = 1000;
        cientos = Math.floor(num / divisor)
        resto = num - (cientos * divisor)

        strMiles = Seccion(num, divisor, "MIL", "MIL");
        strCentenas = Centenas(resto);

        if (strMiles == "")
            return strCentenas;

        return strMiles + " " + strCentenas;

        //return Seccion(num, divisor, "UN MIL", "MIL") + " " + Centenas(resto);
    }//Miles()

    function Millones(num) {
        divisor = 1000000;
        cientos = Math.floor(num / divisor)
        resto = num - (cientos * divisor)

        strMillones = Seccion(num, divisor, "UN MILLON", "MILLONES");
        strMiles = Miles(resto);

        if (strMillones == "")
            return strMiles;

        return strMillones + " " + strMiles;

        //return Seccion(num, divisor, "UN MILLON", "MILLONES") + " " + Miles(resto);
    }//Millones()

    function NumeroALetras(num, centavos) {
        var data = {
            numero: num,
            enteros: Math.floor(num),
            centavos: (((Math.round(num * 100)) - (Math.floor(num) * 100))),
            letrasCentavos: "",
        };
        if (centavos == undefined || centavos == false) {
            data.letrasMonedaPlural = "GUARANIES";
            data.letrasMonedaSingular = "GUARANIES";
        } else {
            data.letrasMonedaPlural = "CENTIMOS";
            data.letrasMonedaSingular = "CENTIMO";
        }

        if (data.centavos > 0)
            data.letrasCentavos = "CON " + NumeroALetras(data.centavos, true);

        if (data.enteros == 0)
            return "CERO " + data.letrasMonedaPlural + " " + data.letrasCentavos;
        if (data.enteros == 1)
            return Millones(data.enteros) + " " + data.letrasMonedaSingular + " " + data.letrasCentavos;


        else
            return Millones(data.enteros) + " " + data.letrasMonedaPlural + " " + data.letrasCentavos;
    }


}