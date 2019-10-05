
$(document).ready(function () {

    cambioEstadoFCompras();
    MostrarFacturaCompra();
});
//FUNCIONES DE TRANSACCIONES----------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
function pasarCampos() {
    $("#factuCompNroCuota").focus();
}
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoFa": $('#codigo').val(),
        "fcompracuota": $('#factuCompNroCuota').val(),
        "fcompramonto": $('#factuCompMonto').val(),
        "fcompraNfactu": $('#factuCompNroFactura').val(),
        "fcompraintervalo": $('#factuCompIntervalo').val(),
        "fcomprafecha": $('#factuCompFecha').val(),
        "fcompraprovee": $('#factuCompIdProvee').val(),
        "fcomprausua": $('#factuCompIdUsuario').val(),
        "fcompraestado": $('#factuCompIdEstado').val(),
        "fcompratipo": $('#factuidtipocompras').val(),
        "fcompraordenc": $('#factuCompOrdenC').val()
    };
}
function getcodigoCompra() {
    controlBotonesNuevo();
    $("#factuCompProvee").val(null);
    crearJSON(1);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigo").val(resp);
            $("#factuCompProvee").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
function controlBotonesNuevo() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesFacturaCompra a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estadofacturaP').val() === 'CONFIRMADO' || $('#estadofacturaP').val() === 'ANULADO') {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}
function MostrarEstado() {
//    alert("llega al usuario")
    user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#factuCompIdEstado").val(value.idestado);
                $("#factuCompEstado").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}
function MostrarUsuario() {
//    alert("llega al usuario")
    user = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#factuCompIdUsuario").val(value.idusuario);
                $("#factuCompUsuario").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });
}
function MostrarModalProveedor() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaProveedores").append($("<tr>").append($(
                        "<td style=display:none>" + value.id_prov + "</td>" +
                        "<td>" + value.prov_nombre + "</td>")));
            });
        }
    });
}
function MostrarOrdenCompra() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaOrdenCompra").append($("<tr>").append($(
                        "<td>" + value.ordenc_nro + "</td>" +
                        "<td>" + value.ordenc_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function RecuperarDetalleOrdenC() {
    datosDetalleJSON = {
        "opcion": 6,
        "nroOrdenC": $('#factuCompOrdenC').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                //alert(resp);
                $.each(resp, function (indice, value) {
                    subtotal = value.precio_orden * value.cant_orden;
                    $('#miTablaDetalleFacturaCompra').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.precio_orden + "</td>\n\
                                    <td>" + value.cant_orden + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                });
            } else {
                alert('Datos no encontrados..');
                $("#factuCompSucursal").focus();
            }
            calcularmonto();
        }
    });
}
function MostrarSucursalF() {
//    alert("llega al usuario")
    user = {
        "opcion": 7
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#factuIdSucursal").val(value.idsucursal);
                $("#factuSucursal").val(value.suc_descripcion);
            });
        },
        error: function () {
        }
    });
}
function MostrarMercaderia() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMercaderiaCompra").append($("<tr>").append($(
                        "<td style=display:none>" + value.idmercaderia + "</td>" +
                        "<td>" + value.codigogenerico + "</td>" +
                        "<td>" + value.mer_descripcion + "</td>" +
                        "<td>" + value.mer_precio + "</td>")));
            });
        }
    });
}
function  inserFacCompra() {
    var dato = "";
    $('#miTablaDetalleFacturaCompra').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiMerca").focus();
    } else {
        if ($('#factuCompProvee').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiMerca").focus();
        } else {
            var opcion = confirm('Desea Guardar Factura Compras..?');
            if (opcion === true) {
                var pedidofc = $('#factuCompOrdenC').val();
                var pedido = 0;
                if (pedidofc != "") {
                    pedido = pedidofc;
                } else {
                    pedido = 0;
                }
                dadoscompra = {
                    "opcion": 9,
                    "cValor": 1,
                    "codigoC": $('#codigo').val(),
                    "fcompracuota": $('#factuCompNroCuota').val(),
                    "fcompramonto": $('#faccuotamonto').val().replace(/\./g, ''),
                    "fcompraNfactu": $('#factuCompNroFactura').val(),
                    "fcompraintervalo": $('#factuCompIntervalo').val(),
                    "fcomprafecha": $('#factuCompFecha').val(),
                    "fcompraprovee": $('#factuCompIdProvee').val(),
                    "fcomprausua": $('#factuCompIdUsuario').val(),
                    "fcompraestado": $('#factuCompIdEstado').val(),
                    "fcompratipo": $('#factuidtipocompras').val(),
                    "fcompraordenc": pedido,
                    "fdeposito": $('#Coddepo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
                    type: 'POST',
                    data: dadoscompra,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        InsertarDetalleFacturaCopr();

                    },
                    error: function () {
                    }
                });
            } else {

            }
        }
    }
}
function  updatfacCompra() {
    var dato = "";
    $('#miTablaDetalleFacturaCompra').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiMerca").focus();
    } else {
        if ($('#factuCompProvee').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiMerca").focus();
        } else {
            var opcion = confirm('Desea Guardar Factura Compras..?');
            if (opcion === true) {
                var pedidofc = $('#factuCompOrdenC').val();
                var pedido = 0;
                if (pedidofc != "") {
                    pedido = pedidofc;
                } else {
                    pedido = 0;
                }
                datosCabeceraJSON = {
                    "opcion": 9,
                    "cValor": 2,
                    "codCompra": $('#codigo').val(),
                    "fcompracuota": $('#factuCompNroCuota').val(),
                    "fcompramonto": $('#faccuotamonto').val().replace(/\./g, ''),
                    "fcompraNfactu": $('#factuCompNroFactura').val(),
                    "fcompraintervalo": $('#factuCompIntervalo').val(),
                    "fcomprafecha": $('#factuCompFecha').val(),
                    "fcompraprovee": $('#factuCompIdProvee').val(),
                    "fcomprausua": $('#CodvUser').val(),
                    "fcompratipo": $('#factuidtipocompras').val(),
                    "fcompraordenc": pedido,
                    "fdeposito": $('#Coddepo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deleteFacturaCompra();
                        setTimeout(function () {
                            InsertarDetalleFacturaCopr();
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
function  InsertarDetalleFacturaCopr() {
    $('#miTablaDetalleFacturaCompra').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 10,
            "codigoFa": $('#codigo').val(),
            "idmercaFa": $(this).find("td").eq(0).html(),
            "precioFa": $(this).find("td").eq(3).html(),
            "cantiFa": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
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
    alert("Factura Compras guardado correctamente.!!");
    window.location.reload();
}
function  deleteFacturaCompra() {
    datosDetalleJSON = {
        "opcion": 15,
        "codFacCompra": $('#codigo').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        dataType: 'text',
        success: function () {
        },
        error: function () {
        }
    });
}
function  generarCtas() {
    jsontas = {
        "opcion": 16,
        "codCompra": $('#nrofacturaP').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: jsontas,
        cache: false,
        dataType: 'text',
        success: function () {
        },
        error: function () {
        }
    });
}
function MostrarFacturaCompra() {
    crearJSON(11);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPlanillaCompra").append($("<tr>").append($("<td>" + value.idcompra + "</td>" +
                        "<td>" + value.comp_nrofact + "</td>" +
                        "<td>" + value.comp_fecha + "</td>" +
                        "<td>" + value.tipo_descri + "</td>" +
                        "<td>" + value.prov_nombre + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function cambioEstadoFCompras() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesFacturaCompra a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estadofacturaP').val() === "") {
                    alert('Seleccione una factura compras.!');
                } else if ($('#estadofacturaP').val() === 'PENDIENTE' || $('#estadofacturaP').val() === 'ANULADO') {
                    alert('La factura aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estadofacturaP').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular la factura.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoC": 6,
                            "FacturaCNro": $('#nrofacturaP').val()
                        };
                        confirmarFacturaCompra();
                        alert('Factura Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estadofacturaP').val() === "") {
                    alert('Seleccione una factura compras.!');
                } else if ($('#estadofacturaP').val() === 'CONFIRMADO' || $('#estadofacturaP').val() === 'ANULADO') {
                    alert('La factura ya fué Confirmado o esta Anulada..');
                } else if ($('#estadofacturaP').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar la factura copras.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoC": 2,
                            "FacturaCNro": $('#nrofacturaP').val()
                        };
                        confirmarFacturaCompra();
                        setTimeout(function (){
                                generarCtas(); 
                        },2000);
                        alert('Factura Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estadofacturaP').val() === "") {
                    alert('Seleccione una factura de Compras.!');
                } else if ($('#estadofacturaP').val() === 'PENDIENTE' || $('#estadofacturaP').val() === 'ANULADO') {
                    alert('La factura no se puede Revertir..');
                } else if ($('#estadofacturaP').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir la factura.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoC": 1,
                            "FacturaCNro": $('#nrofacturaP').val()
                        };
                        confirmarFacturaCompra();
                        alert('La factura ha vuelto a su estado de Origen.!!');
                    }
                }
            }
        });
    });
}
function confirmarFacturaCompra() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPlanillaCompra').find('tbody').find('tr').empty();
            MostrarFacturaCompra();
            
        },
        error: function () {
        }
    });
}
function confirmarFacturaC() {
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

function controlBotonesFacturaC() {
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
function recuperarDetalleFacturaC() {
    controlBotonesFacturaC();
    if ($('#nrofacturaP').val() === "") {
        alert('Seleecione planilla para visualizar..');
    } else if ($('#estadofacturaP').val() === 'CONFIRMADO') {
        alert('La compra fue confiramda, ya no se puede modificar.!!');
    } else {
        $('#ventanaFacturaCompra').modal('show');
        $('#miTablaDetalleFacturaCompra').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 13,
            "nroFacturaC": $('#nrofacturaP').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#factuCompNroCuota").val(value.comp_cantcuota);
                        $("#factuCompMonto").val(value.comp_monto);
                        $("#factuCompNroFactura").val(value.comp_nrofact);
                        $("#factuCompIntervalo").val(value.comp_intervalo);
                        $("#factuCompFecha").val(value.comp_fecha);
                        $("#facturatipoC").val(value.tipo_descri);
                        $("#factuCompProvee").val(value.prov_nombre);
                        $("#factuCompIdProvee").val(value.id_prov);
                        $("#factuidtipocompras").val(value.tipo_codigo);
                        $("#factuCompSucursal").val(value.suc_descripcion);
                        $("#factuCompUsuario").val(value.usu_nombre);
                        $("#factuCompEstado").val(value.descri_estado);
                        $("#factuCompOrdenC").val(value.ordenc_nro);
                        ///////BLOQUEA LOS CAMPOS//////
                        $("#factuCompNroCuota").prop('disabled', true);
                        $("#factuCompMonto").prop('disabled', true);
                        $("#factuCompNroFactura").prop('disabled', false);
                        $("#factuCompIntervalo").prop('disabled', true);
                        $("#factuCompFecha").prop('disabled', true);
                        $("#facturatipoC").prop('disabled', false);
                        $("#factuCompProvee").prop('disabled', false);
                        $("#factuCompSucursal").prop('disabled', true);
                        $("#factuCompUsuario").prop('disabled', true);
                        $("#factuCompEstado").prop('disabled', true);
                        $("#factuCompOrdenC").prop('disabled', true);
                        subtotal = value.detfact_precio * value.detfact_cantidad;
                        $('#miTablaDetalleFacturaCompra').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.detfact_precio + "</td>\n\
                                    <td>" + value.detfact_cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                    });
                    $('#codigo').val($('#nrofacturaP').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#nrofacturaP").focus();
                }
                calcularmonto();
            }
        });
    }
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS-----------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
function controlarcampor() {
    var num = $('#factuCompMonto').val();
    var re = isNaN(num);
    if (re === true) { //si el valor es texto

    } else {
        alert('SOLO TEXT');
        $('#factuCompMonto').val(null);
    }
}


function puntodecimal(...uno) {
    for (let numero of uno) {
        var num = document.getElementById(numero).value.replace(/\./g, '');
        if (!isNaN(num)) {
            num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
            num = num.split('').reverse().join('').replace(/^[\.]/, '');
            document.getElementById(numero).value = num;
            $('#' + numero).css('font-weight', 'bold');

        } else {
//            alert('Solo se permiten numeros');
            document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');
        }
    }


}
function ValidacionesSoloNumerosFac(input) {
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
function fechaactualCompra() {
    var fecha = new Date();
    $('#factuCompFecha').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------
function abrirproveedor() {
    if ($('#factuCompProvee').val() === "") {
        $('#ModalProveedor').modal('show');
        $('#miTablaProveedores').find('tbody').find('tr').empty();
        MostrarModalProveedor();
    } else {
    }
}//----------
function seleccionarProveedor() {
    $('#miTablaProveedores tr').click(function () {
        $('#factuCompIdProvee').val($(this).find("td").eq(0).html());
        $('#factuCompProvee').val($(this).find("td").eq(1).html());
        $('#factuCompOrdenC').focus();
        $('#ModalProveedor').modal('hide');
    });
}//----------
function buscadorTablaProveed() {
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
function AbrirOrdenCompra() {
    if ($('#factuCompOrdenC').val() === "") {
        $('#ModalOrdenCompra').modal('show');
        $('#miTablaOrdenCompra').find('tbody').find('tr').empty();
        MostrarOrdenCompra();
    } else {
    }
}//---------------
function seleccionarOrdenCompra() {
    $('#miTablaOrdenCompra tr').click(function () {
        $('#factuCompOrdenC').val($(this).find("td").eq(0).html());
        $('#factuCompOrdenC').focus();
        $('#ModalOrdenCompra').modal('hide');
    });
}//---------------
function buscadorTablaOrdenCompra() {
    var tableReg = document.getElementById('miTablaOrdenCompra');
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
var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;

function calcularmonto() {
    monto = 0;
    acumu = 0;
    $('#miTablaDetalleFacturaCompra').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(5).html());
        acumu = acumu + monto;
    });
    $('#total').val(acumu);
    tindex++;
    $('#factuCompMonto').val($('#total').val());
    puntodecimal('factuCompMonto');
}
function updatemonto(valormonto, ind) {
    var monto = $('#total').val();
    var calculo = monto - valormonto;
    $('#total').val(calculo);
    calculo = 0;
    monto = 0;
}//------------
function SeleccionarDetalleFacturaCompra() {
    $('#miTablaDetalleFacturaCompra tr').click(function () {
        $('#codMerca').val($(this).find("td").eq(0).html());
        $('#codgenericiMerca').val($(this).find("td").eq(1).html());
        $('#nombreMerca').val($(this).find("td").eq(2).html());
        $('#precioMerca').val($(this).find("td").eq(3).html());
        $('#cantidadMerca').val(3);
        $('#cantidadMerca').focus();
    });
}//------------------
function abrirModalMercaderia() {
    if ($('#codgenericiMerca').val() === "") {
        $('#ModalMercaderia').modal('show');
        $('#miTablaMercaderiaCompra').find('tbody').find('tr').empty();
        MostrarMercaderia();
    } else {
    }
}
function seleccionarMercaderiaCompra() {
    $('#miTablaMercaderiaCompra tr').click(function () {
        $('#codMerca').val($(this).find("td").eq(0).html());
        $('#codgenericiMerca').val($(this).find("td").eq(1).html());
        $('#cantidadMerca').val(1);
        $('#nombreMerca').val($(this).find("td").eq(2).html());
        $('#precioMerca').val($(this).find("td").eq(3).html());
        $('#cantidadMerca').focus();
        $('#ModalMercaderia').modal('hide');
    });
}
function buscadorTablaMercaderiaCompra() {
    var tableReg = document.getElementById('miTablaMercaderiaCompra');
    var searchText = document.getElementById('filtrarMercaderiaCompra').value.toLowerCase();
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
function CargarMercaCompraGrilla() {
    var cod = $('#codgenericiMerca').val();
    var codigo;
    $('#miTablaDetalleFacturaCompra').find('tbody').find('tr').each(function () {
        codigo = $(this).find("td").eq(1).html();
        if (cod === codigo) {
            alert('La mercaderia ya fue cargada, desea sustituirlo?');
            $(this).find("td").remove();
        }
    });
    agregarFilaMercaCompra();
}
function agregarFilaMercaCompra() {
    //idmaterial
    var v_codMaterialG = $('#codgenericiMerca').val();
    var v_codmaterial = $('#codMerca').val();
    var v_descripcion = $('#nombreMerca').val();
    var v_precio = $('#precioMerca').val();
    var v_cant = $('#cantidadMerca').val();
    subtotal = v_precio * v_cant;
    $('#miTablaDetalleFacturaCompra').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\" src='Recursos/img/delete.png' width=14 height=14/></td>\n\
            </tr>");
    calcularmonto();

    $('#codgenericiMerca').val(null);
    $('#codgenericiMerca').focus;
    $('#nombreMerca').val(null);
    $('#precioMerca').val(null);
    $('#cantidadMerca').val(null);
}//-----------------------
function seleccionarFacturaCompras() {
    $('#miTablaPlanillaCompra tr').click(function () {
        $('#nrofacturaP').val($(this).find("td").eq(0).html());
        $('#estadofacturaP').val($(this).find("td").eq(6).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
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
function buscadorPlanillaCompra() {
    var tableReg = document.getElementById('miTablaPlanillaCompra');
    var searchText = document.getElementById('filtrarPlanillaCompra').value.toLowerCase();
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


function tipoComprasAbrir() {
    if ($('#facturatipoC').val() === "") {
        $('#ModalFacturaTipo').modal('show');
        $('#miTablatipocompra').find('tbody').find('tr').empty();
        MostrarModaltipocompras();
    } else {
    }
}//----------
function seleccionartipocompra() {
    $('#miTablatipocompra tr').click(function () {
        $('#factuidtipocompras').val($(this).find("td").eq(0).html());
        $('#facturatipoC').val($(this).find("td").eq(1).html());
        $('#factuCompOrdenC').focus();
        $('#ModalFacturaTipo').modal('hide');
        OcultarCampocomp();

    });
}//----------
function OcultarCampocomp() {
    var a = $('#facturatipoC').val();
    if (a === "contado") {

        $("#factuCompNroCuota").prop('disabled', true);
        $("#factuCompIntervalo").prop('disabled', true);
        $("#factuCompMonto").prop('disabled', true);
        $("#factuCompIntervalo").val(0);
        $("#factuCompNroCuota").val(0);
        $("#faccuotamonto").val(0);
    } else if (a === "creditos") {
        $("#factuCompNroCuota").prop('disabled', false);
        $("#factuCompIntervalo").val(30);
        $("#factuCompMonto").prop('disabled', false);
    }
}
function calculomontoconp() {
    var valormonto = $('#factuCompMonto').val().replace(/\./g, '');
    var cant = $('#factuCompNroCuota').val();
    if ($('#factuCompMonto').val() === "" || $('#total').val() === "") {
        $('#faccuotamonto').val(0);
    } else {
        var calculo =  valormonto / cant;
        $('#faccuotamonto').val(calculo);
        valormonto = 1;
        puntodecimal('faccuotamonto');
    }


}
function buscadorTablatipocompra() {
    var tableReg = document.getElementById('miTablatipocompra');
    var searchText = document.getElementById('filtrartipocompra').value.toLowerCase();
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

function MostrarModaltipocompras() {
    crearJSON(14);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/facturacompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablatipocompra").append($("<tr>").append($(
                        "<td>" + value.tipo_codigo + "</td>" +
                        "<td>" + value.tipo_descri + "</td>")));
            });
        }

    });
}

