$(document).ready(function () {
    opcionesFacturacion();
});
function opcionesFacturacion() {
    getfacturas();
    comboCondVenta();
    numaletras();
    $('#ftotalventa').val("0");
    $('#totalVenta').val("0");
    $('#valor10').val("0");
    $('#valor5').val("0");
    $('#totalIVA').val("0");
    $('#btnguardarVenta').click(function () {
        var nfilas = $('#mitablaDetlleVentas tr').length - 1;
        if (parseInt(nfilas) < 1) {
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

        } else if ($('#fclienteci').val() === "" || $('#fclientenombre').val() === "" ||
                $('#fvendedor').val() === "") {
            $.confirm({
                title: 'AVISO!',
                content: 'Algunos datos no fueron cargados correctamente.!',
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
            mensajeVenta();
        }


    });
    $('#fclienteci').blur(function () {
        getclientefactura();
    });
    $('#btnguardarclientefacturaicon').click(function () {
        guardarclientefactura();
    });
    $('#fCODarticulo').click(function () {
        $('#v_articulosfacturacion').modal('show');
        getarticulos();
    });
    $('#fbuscadorArticulo').keyup(function () {
        buscadorgenericotable('miTablaarticulos', 'fbuscadorArticulo');
    });
    $('#btnanularfactura').click(function () {
        $('#v_anulacionfactura').modal('show');
    });
    $('#btnanular').click(function () {
        if ($('#v_facanular').val() === "") {
            $.confirm({
                title: 'AVISO!',
                content: 'Debes ingresar una Factura.. ',
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
           anularfactura();
        }

    });
}
function seleccionAriticulo() {
    $('#miTablaarticulos tr').click(function () {
        $('#fCODarticulo').val($(this).find("td").eq(0).html());
        $('#fCODproducto').val($(this).find("td").eq(1).html());
        $('#fcantidad').val(1);
        $('#fprecio').val($(this).find("td").eq(2).html());
        $('#idpreci').val($(this).find("td").eq(3).html());
        $('#fcantidad').focus();
        $('#v_articulosfacturacion').modal('hide');

    });
}

function guardarclientefactura() {
    clientej = {
        'opcion': 3,
        'v_nombrecliente': $('#v_modalfnombre').val(),
        'v_cedulacliente': $('#v_modalfcedula').val().replace(/\./g, '')
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: clientej,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#v_clientefacturacion').modal('hide');
            $('#fclienteci').focus();
            $('#fclientenombre').val($('#v_modalfnombre').val());

        }

    });
}
function getclientefactura() {
    cliente = {
        "opcion": 2,
        "v_cicliente": $('#fclienteci').val().replace(/\./g, '')
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: cliente,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, value) {
                    $('#fclientenombre').val(value.nombrecliente);
                    $('#fDIcliente').val(value.idcliente);


                });
            } else {
                var v_cedula = $('#fclienteci').val().replace(/\./g, '');
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
                                    $('#v_clientefacturacion').modal('show');
                                    $('#v_modalfcedula').val($('#fclienteci').val());
                                    $('#v_modalfnombre').focus();
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
function comboCondVenta() {

    var array = ["Contado", "Credito"];
    var cont = 0;
    for (var i in array) {
        cont++;
        document.getElementById("fpago").innerHTML += "<option value='" + cont + "'>" + array[i] + "</option>";
    }

}

var index = 0;
function getarticulos() {
    $('#miTablaarticulos').find('tbody').find('tr').empty();
    jsonarticulos = {
        'opcion': 2
    };
    $.ajax({
        url: "/syscontrol/facturacionSERVLETXML",
        type: 'POST',
        data: jsonarticulos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var canstock = valor.cantidad_stock;
                if (canstock < 20) {
                    agregar = "<button class='btn btn-sm btn-outline-danger' title='Agregar Registro' onclick=\"$(\'#prod" + index + "\');agregarArticulo()\">Agregar</button>";
                } else {
                    agregar = "<button class='btn btn-sm btn-outline-primary' title='Agregar Registro' onclick=\"$(\'#prod" + index + "\');agregarArticulo()\">Agregar</button>";
                }
                index++;

                $("#miTablaarticulos").append($("<tr id=\'cod" + index + "\' >").append($(
                        "<td> " + valor.idarticulo + "</td>" +
                        "<td>" + valor.descripcion + "</td>" +
                        "<td>" + valor.precio_venta + "</td>" +
                        "<td>" + valor.impuesto + "</td>" +
                        "<td>" + valor.cantidad_stock + "</td>" +
                        "<td >" + agregar + "</td>" +
                        "<td style=display:none> " + valor.idimpuesto + "</td>")));
            });

        }
    });
}

function agregarArticulo() {
    $('#miTablaarticulos tr').click(function () {
        $('#fCODarticulo').val($(this).find("td").eq(0).html());
        $('#fCODproducto').val($(this).find("td").eq(1).html());
        $('#fcantidad').val(1);
        $('#fprecio').val($(this).find("td").eq(2).html());
        $('#fimpuesto').val($(this).find("td").eq(3).html());
        $('#fIDimpuesto').val($(this).find("td").eq(6).html());

        $('#v_articulosfacturacion').modal('hide');
        puntodecimal('fprecio');
        $('#fcantidad').focus();

    });
}

function CargarArticulo() {
    var ban = false;
    if ($('#fCODarticulo').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#fCODarticulo').val();
        var codigo;
        $('#mitablaDetlleVentas').find('tbody').find('tr').each(function () {
            codigo = parseInt($(this).find("td").eq(0).html());
            if (parseInt(cod) === codigo) {
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
var idx = 0;
function agregarfilaventas() {
    var v_cod = $('#fCODarticulo').val();
    var v_producto = $('#fCODproducto').val();
    var v_cantidad = $('#fcantidad').val();
    var v_precioUnitario = $('#fprecio').val().replace(/\./g, '');
    var v_subtotal = v_cantidad * v_precioUnitario;
    var v_impuesto = $('#fimpuesto').val();
    var v_idimpuesto = $('#fIDimpuesto').val();
    var v_impu10 = "0";
    var v_impu5 = "0";
    var v_impuexe = "0";
    if (v_impuesto === '10%') {
        v_impu10 = v_impuesto;
    } else {
        if (v_impuesto === '5%') {
            v_impu5 = v_impuesto;
        } else {
            if (v_impuesto === 'EXENTA') {
                v_impuexe = v_impuesto;
            }
        }

    }
    idx++;
    $('#mitablaDetlleVentas').append("<tr id=\'prod" + idx + "\'>\
            <td>" + v_cod + "</td>\n\
            <td>" + v_producto + "</td>\n\
            <td>" + v_precioUnitario + "</td>\n\
            <td>" + v_cantidad + "</td>\n\
            <td>" + v_impu10 + "</td>\n\
            <td>" + v_impu5 + "</td>\n\
            <td>" + v_impuexe + "</td>\n\
            <td>" + v_subtotal + "</td>\n\
             <td style='display: none'>" + v_idimpuesto + "</td>\n\
          <td ><button type=button title='Quitar el registro de la lista' style=text-align:center class='btn btn-sm btn-danger' onclick=\"$(\'#prod" + idx + "\').remove();totalventa();removeventa()\">Quitar</button></td>\n\
            </tr>");
    totalventa();
    $('#fCODarticulo').val(null);
    $('#fCODproducto').val(null);
    $('#fcantidad').val(null);
    $('#fprecio').val(null);
    $('#fimpuesto').val(null);
    $('#fIDimpuesto').val(null);
    $('#fCODarticulo').focus();



}
function removeventa() {
    var nfila = $('#mitablaDetlleVentas tr').length - 1;
    if (parseInt(nfila) <= 1) {
        $('#ftotalventa').val("0");
        $('#totalVenta').val("0");
        $('#valor10').val("0");
        $('#valor5').val("0");
        $('#totalIVA').val("0");
    } else {
    }
}

function totalventa() {
    setInterval(function () {
        var total = 0;
        var total10 = 0;
        var total5 = 0;
        var totaliva = 0;
        var valorimp = 0;
        var acumu = 0;
        var acumu10 = 0;
        var acumu5 = 0;
        var acumtotaliva = 0;
        $('#mitablaDetlleVentas').find('tbody').find('tr').each(function () {
            total = parseInt($(this).find("td").eq(7).html());
            valorimp = parseInt($(this).find("td").eq(8).html());
            if (valorimp === 1) {
                total10 = parseInt($(this).find("td").eq(7).html()) / 11;
                acumu10 = acumu10 + total10;
            } else
            if (valorimp === 2) {
                total5 = parseInt($(this).find("td").eq(7).html()) / 21;
                acumu5 = acumu5 + total5;
            }
            acumu = acumu + total;
            $('#ftotalventa').val(acumu);
            $('#totalVenta').val(acumu);
            $('#valor10').val(Math.trunc(acumu10));
            $('#valor5').val(Math.trunc(acumu5));
            $('#totalVenta').css('font-size', '15pt');
            $('#totalVenta').css('color', 'blue');
            $('#totalVenta').css('font-weight', 'bold');
        });
        numaletras();
        puntodecimal('totalVenta');
        puntodecimal('valor10');
        puntodecimal('valor5');

        var iva5 = $('#valor5').val().replace(/\./g, '');
        var iva10 = $('#valor10').val().replace(/\./g, '');
        totaliva = totaliva + parseInt(iva5) + parseInt(iva10);
        $('#totalIVA').val(Math.trunc(totaliva));
        puntodecimal('totalIVA');

        $('#valor10').css('font-size', '8pt');
        $('#valor5').css('font-size', '8pt');
        $('#totalIVA').css('font-size', '8pt');
    }, 500);
}
function  getfacturas() {
    jsonfactura = {
        'opcion': 1,
        'codigoCajero': $('#vCodIDuser').val()
    };
    $.ajax({
        url: "/syscontrol/facturacionSERVLETXML",
        type: 'POST',
        data: jsonfactura,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var cantFac = valor.cant;
                if (cantFac < 5) {
                    $.confirm({
                        title: 'AVISO!',
                        content: 'Solo quedan "' + cantFac + '" facturas. ',
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

                }
                $('#fnrofactura').val('Nro. Fac : ' + valor.numerodocumento);
                $('#fcaja').val(valor.caja);
                $('#fcajero').val(valor.cajero);
                $('#fIDfactura').val(valor.iddocfactura);
                $('#fIdapertura').val(valor.idaperturacierre);

            });

        }
    });
}

function numaletras() {
    $('#ftotalventa').val(function (e) {
        document.getElementById("numTexto").innerHTML = NumeroALetras(this.value);
        $('#numTexto').css('font-size', '8pt');
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

function mensajeVenta() {
    $.confirm({
        title: 'Guardar',
        content: 'Desea Guardar la Venta ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {
                    guardarVenta();
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
// 
//}
function guardarVenta() {
    jsonVenta = {
        "opcion": 3,
        "fidcliente": $('#fDIcliente').val(),
        "fidfactura": $('#fIDfactura').val(),
        "fidvemdedor": 3,
        "fidaperturacierre": $('#fIdapertura').val(),
        "fidcondventa": $('#fpago').val(),
        "fmontoventa": $('#totalVenta').val().replace(/\./g, '')
    };
    $.ajax({
        url: "/syscontrol/facturacionSERVLETXML",
        type: 'POST',
        data: jsonVenta,
        cache: false,
        dataType: 'text',
        success: function () {
            setTimeout(function () {
                insertarVentaDetalle();
                $.alert('Guardado correctamente !!');
                $('#mitablaDetlleVentas').find('tbody').find('tr').empty();
                location.reload();
            }, 1000);
        },
        error: function () {
            $.alert('No se puedo realizar la operación !!');
        }
    });

}


function  insertarVentaDetalle() {
    $('#mitablaDetlleVentas').find('tbody').find('tr').each(function () {
        jsonventaDetalle = {
            "opcion": 4,
            "fidarticulo": parseInt($(this).find("td").eq(0).html()),
            "fcantidad": $(this).find("td").eq(3).html(),
            "fpreciou": $(this).find("td").eq(2).html(),
            "fidimpuesto": parseInt($(this).find("td").eq(8).html())
        };

//        alert(jsonventaDetalle.fidarticulo);
        $.ajax({
            url: "/syscontrol/facturacionSERVLETXML",
            type: 'POST',
            data: jsonventaDetalle,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });
}
function  getanulado() {
    josnfactura = {
        "opcion": 5,
        "nrofac": $('#v_facanular').val()
    };
    $.ajax({
        url: "/syscontrol/facturacionSERVLETXML",
        type: 'POST',
        data: josnfactura,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#v_ciclienteanular').val(valor.cedula);
                $('#v_clienteanular').val(valor.nombrecliente);
                $('#v_idfacAnular').val(valor.iddocfactura);
            });

        },
        error: function () {
        }
    });
}
function  anularfactura() {
    anufac = {
        "opcion": 6,
        "codFactura": $('#v_idfacAnular').val()
    };
    $.ajax({
        url: "/syscontrol/facturacionSERVLETXML",
        type: 'POST',
        data: anufac,
        cache: false,
        dataType:'text',
        success: function (resp) {
             $.alert('Factura Anulada!');
             location.reload();
        },
        error: function () {
        }
    });
}



