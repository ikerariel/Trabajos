$(document).ready(function () {
    opcionesFacturacion();

});
function opcionesFacturacion() {
    getfacturas();
    comboCondVenta();
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
    $('#fbuscadorArticulo').keyup(function () {

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

    for (var i in array) {
        document.getElementById("fpago").innerHTML += "<option value='" + array[i] + "'>" + array[i] + "</option>";
    }

}

var index = 0;
function getarticulos() {
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
          <td ><button type=button title='Quitar el registro de la lista' style=text-align:center class='btn btn-sm btn-danger' onclick=\"$(\'#prod" + idx + "\').remove(),subtotal()\">Quitar</button></td>\n\
            </tr>");

//    $('#canti_v').focus();
//    subtotal();

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
                if (cantFac < 12) {
//                           $.confirm({
//                title: 'AVISO!',
//                content: 'Solo quedan "'+cantFac+'" facturas. ',
//                type: 'red',
//                buttons: {
//                    Ok: {
//                        text: 'OK',
//                        btnClass: 'btn-dark',
//                        keys: ['enter', 'shift'],
//                        action: function () {
//
//                        }
//                    }
//
//                }
//
//            });
                } else {

                }
                $('#fnrofactura').val('Nro. Fac : ' + valor.numerodocumento);
                $('#fcaja').val(valor.caja);
                $('#fcajero').val(valor.cajero);
                $('#fIDfactura').val(valor.iddocfactura);

            });

        }
    });
}
