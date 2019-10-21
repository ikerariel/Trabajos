$(document).ready(function () {
    opcionesCompras();
    cambioEstadosFCompras();
    MostrarFacturasCompras();
    MostrarArticulos();
    MostrarModalProveedores();
    MostrarOrdenComprass();
});

//FUNCIONES DE TRANSACCIONES----------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------


function opcionesCompras() {
    $('#btnNuevoCompras').click(function () {
        var nCell = $('#miTablaPlanillaCompra tr').length - 1;
        if (parseInt(nCell) < 1) {
            $('#codigo').val("1");
        } else {
            var num;
            $('#miTablaPlanillaCompra').each(function () {
                num = parseInt($(this).find("td").eq(0).html());
            });
            $('#codigo').val(parseInt(num) + 1);
        }

        $('#btnguardarCompra').show();
        $('#btnmModificarCompra').hide();
        vaciarCamposNuevo();
//         getCodigo();
    });
}


function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoC": $('#codigo').val(),
        "Fco_cantcuota": $('#factuCompNroCuota').val(),
        "Fco_monto": $('#factuCompMonto').val(),
        "Fco_nrofact": $('#factuCompNroFactura').val(),
        "Fco_intervalo": $('#factuCompIntervalo').val(),
        "Fco_fecha": $('#factuCompFecha').val(),
        "Fco_tipo": $('#factuCompTipo').val(),
        "Fco_proveedor": $('#factuCompIdProvee').val(),
        "Fco_sucursal": $('#factuCompIdSucursal').val(),
        "Fco_usuario": $('#factuCompIdUsuario').val(),
        "Fco_estado": $('#factuCompIdEstado').val(),
        "Fco_ordencompra": $('#factuCompOrdenC').val()
    };
}

function getCodigo() {
    $('#btnguardarCompra').show();
    $('#btnmModificarCompra').hide();
    vaciarCamposNuevo();
    $("#factuCompProvee").val(null);

    js = {
        'opcion': 1
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        data: js,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            alert(resp);
            $("#codigo").val(resp);
            $("#factuCompProvee").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
///////////////////////////////ESTADOS ANULAR CONFIRMAR APROBADO////////////////////////////////////////////////////

//function controlBotonesNuevo() {
//    v = "";
//    $(document).ready(function () {
//        $('body').on('click', '#botonesFacturaCompra a', function () {
//            v = ($(this).attr('id'));
//            if (v === 'btnNuevo' && $('#estadofacturaP').val() === 'CONFIRMADO' || $('#estadofacturaP').val() === 'ANULADO') {
//                document.getElementById("btnGuardar").style.display = '';
//                document.getElementById("btnGuardarModificado").style.display = 'none';
//            } else {
//                document.getElementById("btnGuardar").style.display = '';
//                document.getElementById("btnGuardarModificado").style.display = 'none';
//            }
//        });
//    });
//}
function MostrarEstados() {
//    alert("llega al usuario")
    user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#factuCompIdEstado").val(value.id_estado);
                $("#factuCompEstado").val(value.est_descripcion);
            });
        },
        error: function () {
        }
    });
}
function MostrarUsuarios() {
//    alert("llega al usuario")
    user = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#factuCompIdUsuario").val(value.id_usuario);
                $("#factuCompUsuario").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });
}
function MostrarModalProveedores() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaProveedores").append($("<tr>").append($(
                        "<td style=display:none>" + value.id_proveedor + "</td>" +
                        "<td>" + value.ras_social + "</td>")));
            });
        }
    });
}


function MostrarOrdenComprass() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaOrdenComp").append($("<tr>").append($(
                        "<td>" + value.id_pedidocompra + "</td>" +
                        "<td>" + value.pcompra_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));
            });
        }
    });
}
var indDetorden = 0;
function RecuperarDetOrdenComprass() {
    datosDetalleJSON = {
        "opcion": 6,
        "id_ordencompraC": $('#factuCompOrdenC').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var ord = JSON.stringify(resp);
                var orden = JSON.parse(ord);
                var nro = orden[0].nroorden;
                if (parseInt(nro) > 0) {
                    alert('La Orden de Compra ya fue procesada..');
                } else {
                    var v = JSON.stringify(resp);
                    var vv = JSON.parse(v);
                    var estado = vv[0].id_estado;
                    if (parseInt(estado) === 1) {
                        $.each(resp, function (indice, value) {
                            $('#factuCompProvee').val(value.proveedor);
                            $('#factuCompIdProvee').val(value.id_proveedor);
                            $('#fidtipocompra').val(value.id_condicionpago);
                            $('#factuCompTipo').val(value.condcompra);
                            $('#factuCompIntervalo').val(value.intervalo);
                            $('#factuCompNroCuota').val(value.cant_cuota);
                            $('#factuCompMonto').val(value.montocuota);
                            numeroDecimal('factuCompMonto');
                            indDetorden++;
                            subtotal = value.precio_detorden * value.cantidad_detorden;
                            $('#miTablaDetFacturasCompras').append("<tr id=\'prod" + indDetorden + "\'>\
                                     <td >" + value.id_articulo + "</td>\n\
                                    <td>" + value.art_descripcion + "</td>\n\
                                    <td>" + value.cantidad_detorden + "</td>\n\
                                    <td>" + value.precio_detorden + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td style=display:none>" + value.id_impuesto + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + indDetorden + "\').remove();calcularmonto();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");

                        });
                        calcularmonto();
                    } else {
                        alert('Orden De Compra Pendiente.!!');
                    }

                }



            } else {
                alert('Datos no encontrados..');
                $("#pedidoOrden").focus();
            }


        }
    });
}
function MostrarSucursales() {
//    alert("llega al usuario")
    user = {
        "opcion": 7
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#factuCompIdSucursal").val(value.id_sucursal);
                $("#factuCompSucursal").val(value.suc_descripcion);
            });
        },
        error: function () {
        }
    });
}
function MostrarArticulos() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaArticulosCompras").append($("<tr>").append($(
                        "<td style=display:none>" + value.id_articulo + "</td>" +
                        "<td>" + value.codigenerico + "</td>" +
                        "<td>" + value.art_descripcion + "</td>" +
                        "<td>" + value.preccompras + "</td>" +
                        "<td style=display:none>" + value.id_impuesto + "</td>")));
            });
        }
    });
}


function  InsertarFacturasCompras() {

    var dato = "";
    $('#miTablaDetFacturasCompras').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiArti").focus();
    } else {
        if ($('#factuCompProvee').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiArti").focus();
        } else {
            var opcion = confirm('Desea Guardar Factura Compras..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 9,
                    "Fco_cantcuota": $('#factuCompNroCuota').val(),
                    "Fco_monto": $('#factuCompMonto').val().replace(/\./g, ''),
                    "Fco_nrofact": $('#factuCompNroFactura').val(),
                    "Fco_intervalo": $('#factuCompIntervalo').val(),
                    "Fco_proveedor": $('#factuCompIdProvee').val(),
                    "Fco_deposito": $('#coddeposito_v').val(),
                    "Fco_usuario": $('#idusersession_v').val(),
                    "Fco_ordencompra": $('#factuCompOrdenC').val(),
                    "Fco_condCompra": $('#fidtipocompra').val()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        InsertarDetFacturasCompras();
                        alert("Factura Compras guardado correctamente.!!");
                        window.location.reload();

                    },
                    error: function () {
                    }
                });
            } else {

            }
        }
    }


}
function  InsertarDetFacturasCompras() {
    $('#miTablaDetFacturasCompras').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 10,
            "codigoD": $('#codigo').val(),
            "idartiD": $(this).find("td").eq(0).html(),
            "precioD": $(this).find("td").eq(2).html(),
            "cantiD": $(this).find("td").eq(3).html(),
            "codimpuesto": $(this).find("td").eq(5).html()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });
}
function  deleteCompra() {
    js = {
        "opcion": 15,
        "nroCompra": $('#codigo').val()

    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: js,
        cache: false,
        dataType: 'text',
        success: function () {
        },
        error: function () {
        }
    });
}
function MostrarFacturasCompras() {
    crearJSON(11);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPlanillaCompra").append($("<tr>").append($(
                        "<td>" + value.id_compra + "</td>" +
                        "<td>" + value.co_nrofact + "</td>" +
                        "<td>" + value.co_fecha + "</td>" +
                        "<td>" + value.ras_social + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));
            });
        }
    });
}

function cambioEstadosFCompras() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesFacturasCompras a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estadofacturaP').val() === "") {
                    alert('Seleccione una factura compras.!');
                } else if ($('#estadofacturaP').val() === 'CONFIRMADO') {
                    alert('La factura ya fue CONFIRMADA..');
                } else if ($('#estadofacturaP').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Anular la factura.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoC": 2,
                            "FacturaCNro": $('#nrofacturaP').val()
                        };
                        confirmarFacturasCompras();
                        alert('Factura Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estadofacturaP').val() === "") {
                    alert('Seleccione una factura compras.!');
                } else if ($('#estadofacturaP').val() === 'CONFIRMADO') {
                    alert('La factura ya fué Confirmado o esta Anulada..');
                } else if ($('#estadofacturaP').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar la factura copras.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoC": 1,
                            "FacturaCNro": $('#nrofacturaP').val()
                        };
                        confirmarFacturasCompras();
                        alert('Factura Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estadofacturaP').val() === "") {
                    alert('Seleccione una factura de Compras.!');
                } else if ($('#estadofacturaP').val() === 'PENDIENTE') {
                    alert('La factura no se puede Revertir..');
                } else if ($('#estadofacturaP').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir la factura.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoC": 3,
                            "FacturaCNro": $('#nrofacturaP').val()
                        };
                        confirmarFacturasCompras();
                        alert('La factura ha vuelto a su estado de Origen.!!');
                    }
                }
            }
//            else if (btn === 'btnNuevo') {
//                document.getElementById('btnGuardarModificado').style.display = "none";
//                document.getElementById('btnGuardar').style.display = "";
//            } else if (btn === 'btnModificar') {
//                document.getElementById('btnGuardar').style.display = "none";
//                document.getElementById('btnGuardarModificado').style.display = "";
//            }

        });
    });
}

function confirmarFacturasCompras() {
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPlanillaCompra').find('tbody').find('tr').empty();
            MostrarFacturasCompras();
        },
        error: function () {
        }
    });
}

function confirmarFacturasCompas() {
    if ($('#estadofacturaP').val() === "") {
        alert('Seleccione un pedido.!');
    } else {
        if ($('#estadofacturaP').val() === 'PENDIENTE') {
            var opcion = confirm('Desea confirmar el pedido.??');
            if (opcion === true) {
            }
        } else {
            if ($('#estadofacturaP').val() === 'CONFIRMADO') {
                alert('El pedido ya fue confirmado..');
            }
        }
    }
}

function controlBotonesFacturasCompras() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesFacturaCompra a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnModificar' && $('#estadofacturaP').val() === 'CONFIRMADO' || $('#estadofacturaP').val() === 'ANULADO') {
//                $("#btnGuardar").attr("disabled", true);
                document.getElementById("btnGuardar").style.display = 'none';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = 'none';
                document.getElementById("btnGuardarModificado").style.display = '';
            }
        });
    });
}
function vaciarCamposNuevo() {
    $("#factuCompNroCuota").val(null);
    $("#factuCompMonto").val(null);
    $("#factuCompNroFactura").val(null);
    ;
    $("#factuCompIntervalo").val(null);
    $("#factuCompFecha").val(null);
    $("#factuCompTipo").val(null);
    $("#factuCompIdProvee").val(null);
    $("#factuCompProvee").val(null);
    $("#factuCompIdSucursal").val(null);
    $("#factuCompSucursal").val(null);
    $("#factuCompIdUsuario").val(null);
    $("#factuCompUsuario").val(null);
    $("#factuCompEstado").val(null);
    $("#factuCompOrdenC").val(null);
    $('#miTablaDetFacturasCompras').find('tbody').find('tr').empty();
    document.getElementById('btnGuardarModificado').style.display = "none";
    document.getElementById('btnGuardar').style.display = "";
}
function recuperarCompra() {
    if ($('#estadofacturaP').val() === "") {
        alert('Debes seleccionar un registro');
    } else {
        if ($('#estadofacturaP').val() === "PENDIENTE") {
            $('#ventanaFacturasCompras').modal('show');
            document.getElementById('btnguardarCompra').style.display = "none";
            document.getElementById('btnmModificarCompra').style.display = "";
            recuperarDetFacturaCompras();
            ///////DESBLOBLOQUEA LOS CAMPOS//////
//        $("#factuCompNroCuota").prop('disabled', true);
//        $("#factuCompMonto").prop('disabled', true);
//        $("#factuCompNroFactura").prop('disabled', true);
//        $("#factuCompIntervalo").prop('disabled', true);
//        $("#factuCompFecha").prop('disabled', true);
//        $("#factuCompTipo").prop('disabled', true);
//        $("#factuCompProvee").prop('disabled', true);
//        $("#factuCompSucursal").prop('disabled', true);
//        $("#factuCompUsuario").prop('disabled', true);
//        $("#factuCompEstado").prop('disabled', true);
//        $("#factuCompOrdenC").prop('disabled', true);
        } else {
            alert('La compra fue proesada, ya no se puede modificar');
        }
    }
}
function recuperarDetFacturaCompras() {
    $('#miTablaDetFacturasCompras').find('tbody').find('tr').empty();
    datosDetalleJSON = {
        "opcion": 13,
        "nroFacturaC": $('#nrofacturaP').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                $.each(resp, function (indice, value) {
                    ///RECUPERA LA CABECERA/////////
                    $("#factuCompNroCuota").val(value.co_cantcuota);
                    $("#codigo").val(value.id_compra);
                    $("#factuCompMonto").val(value.co_monto);
                    $("#factuCompNroFactura").val(value.co_nrofact);
                    $("#factuCompIntervalo").val(value.co_intervalo);
                    $("#factuCompFecha").val(value.co_fecha);
                    $("#factuCompIdProvee").val(value.id_proveedor);
                    $("#factuCompProvee").val(value.proveedor);
                    $("#factuCompOrdenC").val(value.id_ordencompra);
                    $("#factuCompTipo").val(value.condpago);
                    $("#fidtipocompra").val(value.id_condicionpago);
                    numeroDecimal('factuCompMonto');

                    subtotal = value.precio_detcomp * value.cantidad_detcomp;
                    tindex++;
                    $('#miTablaDetFacturasCompras').append("<tr id=\'prod" + tindex + "\'>\
                                    <td >" + value.id_articulo + "</td>\n\
                                    <td>" + value.art_descripcion + "</td>\n\
                                    <td>" + value.precio_detcomp + "</td>\n\
                                    <td>" + value.cantidad_detcomp + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td style=display:none>" + value.id_impuesto + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove();calcularmonto();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td>\n\
                                    <td style=display:none>" + value.id_impuesto + "</td></tr>");
                });
            } else {
                alert('Datos no encontrados..');
            }
            calcularmonto();
        }
    });

}
//---------FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS -------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------

function ValidacionesSoloNumeros(input) {
    var num = input.value.replace(/\./g, '');
//    alert("estees" +num);
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        input.value = num;
    } else {
        alert('Solo se permiten numeros');
        input.value = input.value.replace(/[^\d\.]*/g, '');
    }
}//--------------
function fechaactualCompras() {
    var fecha = new Date();
    $('#factuCompFecha').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------
function abrirproveedores() {
    if ($('#factuCompProvee').val() === "") {
        $('#ModalProveedor').modal('show');
        $('#miTablaProveedores').find('tbody').find('tr').empty();
        MostrarModalProveedores();
    } else {
    }
}//----------
function seleccionarProveedores() {
    $('#miTablaProveedores tr').click(function () {
        $('#factuCompIdProvee').val($(this).find("td").eq(0).html());
        $('#factuCompProvee').val($(this).find("td").eq(1).html());
        $('#factuCompOrdenC').focus();
        $('#ModalProveedor').modal('hide');
    });
}//----------
function buscadorTablaProveedores() {
    var tableReg = document.getElementById('miTablaProveedores');
    var searchText = document.getElementById('filtrarProveedor').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++) {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
                found = true;
            }
        }
        if (found) {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//--------------



function abrirDetalleOrden() {
    if ($('#factuCompOrdenC').val() === "") {
        $('#ModalOrdenCompra').modal('show');
        $('#miTablaOrdenCom').find('tbody').find('tr').empty();
        MostrarOrdenComprass();
    } else {
    }
}//---------------
function seleccionarOrdenComprass() {
    $('#miTablaOrdenComp tr').click(function () {
        $('#factuCompOrdenC').val($(this).find("td").eq(0).html());
        $('#factuCompOrdenC').focus();
        $('#ModalOrdenCompra').modal('hide');
    });
}//---------------

function buscadorTablaOrdenComprass() {
    var tableReg = document.getElementById('miTablaOrdenCom');
    var searchText = document.getElementById('filtrarOrdenCompra').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++) {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
                found = true;
            }
        }
        if (found) {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//---------------

//--------------------------------------------------------



function  ModificarDetOrdenComprass() {
    var dato = "";
    $('#miTablaDetFacturasCompras').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiArti").focus();
    } else {
        if ($('#factuCompProvee').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiArti").focus();
        } else {
            var opcion = confirm('Desea Guardar Factura Compras..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 14,
                    "Fco_cantcuota": $('#factuCompNroCuota').val(),
                    "Fco_monto": $('#factuCompMonto').val().replace(/\./g, ''),
                    "Fco_nrofact": $('#factuCompNroFactura').val(),
                    "Fco_intervalo": $('#factuCompIntervalo').val(),
                    "Fco_proveedor": $('#factuCompIdProvee').val(),
                    "Fco_deposito": $('#coddeposito_v').val(),
                    "Fco_usuario": $('#idusersession_v').val(),
                    "Fco_ordencompra": $('#factuCompOrdenC').val(),
                    "Fco_condCompra": $('#fidtipocompra').val(),
                    "Fco_idcompra": $('#codigo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deleteCompra();
                        setTimeout(function () {
                            InsertarDetFacturasCompras();
                            alert("Factura Compras guardado correctamente.!!");
                            window.location.reload();
                        }, 1200);


                    },
                    error: function () {
                    }
                });
            } else {

            }
        }
    }

}




//----------------------------------------------------------------------------------------------------------



var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;

function calcularmonto() {
    setTimeout(function () {
        monto = 0;
        acumu = 0;
        $('#miTablaDetFacturasCompras').find('tbody').find('tr').each(function () {
            monto = parseInt($(this).find("td").eq(4).html());
            acumu = acumu + monto;
        });
        $('#vtotalCompra').val(acumu);
        tindex++;
        numeroDecimal('vtotalCompra');


    }, 1000);

}
function updatemonto(valormonto, ind) {
    var monto = $('#total').val();
    var calculo = monto - valormonto;
    $('#total').val(calculo);
    calculo = 0;
    monto = 0;
}//------------
function SeleccionarDetFacturasCompras() {
    $('#miTablaDetFacturasCompras tr').click(function () {
        $('#codArti').val($(this).find("td").eq(0).html());
        $('#codgenericiArti').val($(this).find("td").eq(1).html());
        $('#nombreArti').val($(this).find("td").eq(2).html());
        $('#precioArti').val($(this).find("td").eq(3).html());
        $('#cantidadArti').val(3);
        $('#cantidadArti').focus();
    });
}//------------------
function abrirModalArticulos() {
    if ($('#codgenericiArti').val() === "") {
        $('#ModalArticulos').modal('show');
        $('#miTablaArticulosCompras').find('tbody').find('tr').empty();
        MostrarArticulos();
    } else {
    }
}
function seleccionarArticulosCompras() {
    $('#miTablaArticulosCompras tr').click(function () {
        $('#codArti').val($(this).find("td").eq(0).html());
        $('#codgenericiArti').val($(this).find("td").eq(1).html());
        $('#cantidadArti').val(1);
        $('#nombreArti').val($(this).find("td").eq(2).html());
        $('#precioArti').val($(this).find("td").eq(3).html());
        $('#codImpuesto').val($(this).find("td").eq(4).html());
        $('#cantidadArti').focus();
        $('#ModalArticulos').modal('hide');
    });
}
function buscadorTablaArticulosCompras() {
    var tableReg = document.getElementById('miTablaArticulosCompras');
    var searchText = document.getElementById('filtrarArticulosCompras').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++) {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
                found = true;
            }
        }
        if (found) {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//---------------
function CargarArtiComprasGrilla() {
    
       var ban = false;
    if ($('#codArti').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#codArti').val();
        var codigo;
        $('#miTablaDetFacturasCompras').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                agregarFilaArtiCompras();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
          agregarFilaArtiCompras();
        }
    }

}
var dix = 0;
function agregarFilaArtiCompras() {
    //idmaterial
    var v_codmaterial = $('#codArti').val();
    var v_descripcion = $('#nombreArti').val();
    var v_precio = $('#precioArti').val();
    var v_cant = $('#cantidadArti').val();
    var v_imp = $('#codImpuesto').val();
    subtotal = v_precio * v_cant;
    $('#miTablaDetFacturasCompras').append("<tr id=\'prod" + dix + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td style=display:none>" + v_imp + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + dix + "\').remove();calcularmonto();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");
    calcularmonto();
    $('#codgenericiArti').val(null);
    $('#codgenericiArti').focus;
    $('#nombreArti').val(null);
    $('#precioArti').val(null);
    $('#cantidadArti').val(null);
}//-----------------------
function seleccionarFacturasCompras() {
    $('#miTablaPlanillaCompra tr').click(function () {
        $('#nrofacturaP').val($(this).find("td").eq(0).html());
        $('#estadofacturaP').val($(this).find("td").eq(5).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadofacturaP').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadofacturaP').style.color = "#000000";
            document.getElementById('estadofacturaP').style.background = "PaleGoldenrod";
        }
        if (estado === 'AUTORIZADO') {
            document.getElementById('estadofacturaP').style.background = "firebrick";
            document.getElementById('estadofacturaP').style.color = "#ffffff";
        }
    });
}//---------------------------
function buscadorPlanillaCompras() {
    var tableReg = document.getElementById('miTablaPlanillaCompras');
    var searchText = document.getElementById('filtrarPlanillaCompras').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++) {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
                found = true;
            }
        }
        if (found) {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//---------------



