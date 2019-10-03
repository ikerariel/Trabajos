$(document).ready(function () {

    cambioEstadoPresuCompras();
    MostrarPresupuestoCompra();
});
function limpiarcampoPresupuesto() {
    window.location.reload();
}
//FUNCIONES DE TRANSACCIONES----------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoC": $('#codigo').val(),
        "Presucompracuota": $('#PresuCompNroCuota').val(),
        "Presucompramonto": $('#PresuCompMonto').val(),
        "PresucompraNfactu": $('#PresuCompNroFactura').val(),
        "Presucompraintervalo": $('#PresuCompIntervalo').val(),
        "Presucomprafecha": $('#PresuCompFecha').val(),
        "Presucompratipo": $('#PresuCompidTipo').val(),
        "Presucompraprovee": $('#PresuCompIdProvee').val(),
        "Presucomprasucur": $('#PresuCompIdSucursal').val(),
        "Presucomprausua": $('#PresuCompIdUsuario').val(),
        "Presucompraestado": $('#PresuCompIdEstado').val(),
        "PresuPedidoCompra": $('#PresuCompPedidoC').val()
    };
}

function getcodigoPresuComp() {
    controlBotonesNuevoPresu();
    $("#PresuCompIdProvee").val(null);
    $("#PresuCompUsuario").val($('#vUser').val());
    crearJSON(1);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigo").val(resp);
            $("#PresuCompProvee").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
function controlBotonesNuevoPresu() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPresupuestoCompra a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estadoPresuC').val() === 'CONFIRMADO' || $('#estadoPresuC').val() === 'ANULADO') {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS-----------------------------------------------------------
//--------------
function fechaactualPresuCompra() {
    var fecha = new Date();
    $('#PresuCompFecha').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------
//FUNCIONES DE TRANSACCIONES-----------
function MostrarEstadoPresu() {
//    alert("llega al usuario")
    user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#PresuCompIdEstado").val(value.idestado);
                $("#PresuCompEstado").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}
function MostrarUsuarioPresu() {
//    alert("llega al usuario")
    user = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#PresuCompIdUsuario").val(value.idusuario);
                $("#PresuCompUsuario").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });
}
function MostrarModalProveedorPresu() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaProveedoresPresu").append($("<tr>").append($(
                        "<td>" + value.id_prov + "</td>" +
                        "<td>" + value.prov_nombre + "</td>")));
            });
        }
    });
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS------------
function abrirproveedorPresu() {
    if ($('#PresuCompProvee').val() === "") {
        $('#ModalProveedorPresu').modal('show');
        $('#miTablaProveedoresPresu').find('tbody').find('tr').empty();
        MostrarModalProveedorPresu();
    } else {
    }
}//----------
function seleccionarProveedor() {
    $('#miTablaProveedoresPresu tr').click(function () {
        $('#PresuCompIdProvee').val($(this).find("td").eq(0).html());
        $('#PresuCompProvee').val($(this).find("td").eq(1).html());
        $('#PresuCompPedidoC').focus();
        $('#ModalProveedorPresu').modal('hide');
    });
}//----------
function buscadorTablaProveedPresuC() {
    var tableReg = document.getElementById('miTablaProveedoresPresu');
    var searchText = document.getElementById('filtrarProveedorPresu').value.toLowerCase();
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
function AbrirPedidoCompraPresu() {
    if ($('#PresuCompPedidoC').val() === "") {
        $('#ModalPedidoCompraPresu').modal('show');
        $('#miTablaPedidoCompraPresu').find('tbody').find('tr').empty();
        MostrarPedidoCompraPresu();
    } else {
    }
}//---------------
function MostrarPedidoCompraPresu() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPedidoCompraPresu").append($("<tr>").append($("<td>" + value.pcomp_nro + "</td>" +
                        "<td>" + value.pcomp_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS------------
function seleccionarPedidoCompraPresu() {
    $('#miTablaPedidoCompraPresu tr').click(function () {
        $('#PresuCompPedidoC').val($(this).find("td").eq(0).html());
        $('#PresuCompPedidoC').focus();
        $('#ModalPedidoCompraPresu').modal('hide');
    });
}//---------------
function buscadorTablaPedidoCompraPresu() {
    var tableReg = document.getElementById('miTablaPedidoCompraPresu');
    var searchText = document.getElementById('filtrarPedidoCompraPresu').value.toLowerCase();
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
function RecuperarDetallePedidoC() {
    datosDetalleJSON = {
        "opcion": 6,
        "nroPedidoPresuC": $('#PresuCompPedidoC').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                //alert(resp);
                $.each(resp, function (indice, value) {
                    subtotal = value.precio * value.cantidad;
                    $('#miTablaDetallePresuCompra').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.precio + "</td>\n\
                                    <td>" + value.cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                });
            } else {
                alert('Datos no encontrados..');
                $("#PresuCompPedidoC").focus();
            }
            calcularmontoPresu();
        }
    });
}
var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;

function calcularmontoPresu() {
    monto = 0;
    acumu = 0;
    $('#miTablaDetallePresuCompra').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(5).html());
        acumu = acumu + monto;
    });
    $('#total').val(acumu);
    tindex++;
}
function updatemonto(valormonto, ind) {
    var monto = $('#total').val();
    var calculo = monto - valormonto;
    $('#total').val(calculo);
    calculo = 0;
    monto = 0;
}//------------
function SeleccionarDetallePresuCompra() {
    $('#miTablaDetallePresuCompra tr').click(function () {
        $('#codMerca').val($(this).find("td").eq(0).html());
        $('#codgenericiMerca').val($(this).find("td").eq(1).html());
        $('#nombreMerca').val($(this).find("td").eq(2).html());
        $('#precioMerca').val($(this).find("td").eq(3).html());
        $('#cantidadMerca').val(3);
        $('#cantidadMerca').focus();
    });
}//------------------
function MostrarSucursalPresupuesto() {
//    alert("llega al usuario")
    user = {
        "opcion": 7
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#PresuCompIdSucursal").val(value.idsucursal);
                $("#PresuCompSucursal").val(value.suc_descripcion);
            });
        },
        error: function () {
        }
    });
}
function AbrirtipoComprasPresu() {
    if ($('#PresuCompTipo').val() === "") {
        $('#ModalPresuTipo').modal('show');
        $('#miTablatipocompraPresu').find('tbody').find('tr').empty();
        MostrartipocomprasPresu();
    } else {
    }
}//----------
function MostrartipocomprasPresu() {
    crearJSON(14);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablatipocompraPresu").append($("<tr>").append($(
                        "<td>" + value.tipo_codigo + "</td>" +
                        "<td>" + value.tipo_descri + "</td>")));
            });
        }
    });
}
function seleccionartipocompraPresu() {
    $('#miTablatipocompraPresu tr').click(function () {
        $('#PresuCompidTipo').val($(this).find("td").eq(0).html());
        $('#PresuCompTipo').val($(this).find("td").eq(1).html());
        $('#PresuCompIntervalo').focus();
        $('#ModalPresuTipo').modal('hide');
        OcultarCampo();
        calculomonto();
    });
}//----------
function OcultarCampo() {
    var a = $('#PresuCompTipo').val();
    if (a === 'contado') {
        $('#PresuCompNroCuota').val(0);
        $('#PresuCompIntervalo').hide();
        $('#PresuCompNroCuota').hide();
    } else if (a === 'creditos') {
        $('#PresuCompIntervalo').val(15);
        $('#PresuCompNroCuota').val(1);
        $('#PresuCompIntervalo').focus();
    }
}
function calculomonto() {
    if ($('#PresuCompTipo').val() === 'contado') {
        $('#PresuCompMonto').val($('#total').val());
    } else if ($('#PresuCompTipo').val() === 'creditos') {
        var valormonto = $('#PresuCompNroCuota').val();
        var monto = $('#total').val();
        var calculo = parseInt(monto) / parseInt(valormonto);
        $('#PresuCompMonto').val(calculo);
        valormonto = 1;
    }

}
function controlarcampost() {
    var a = $('#PresuCompTipo').val();
    var re = isNaN(a);
    if (re === true) { //si el valor es texto

    } else {
        alert('SOLO TEXTO');
        $('#PresuCompTipo').val(null);
    }
}
function buscadorTablatipocompraPresu() {
    var tableReg = document.getElementById('miTablatipocompraPresu');
    var searchText = document.getElementById('filtrartipocompraPresu').value.toLowerCase();
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
function AbrirModalMercaderiaPresu() {
    if ($('#codgenericiMerca').val() === "") {
        $('#ModalMercaderiaPresu').modal('show');
        $('#miTablaMercaderiaCompraPresu').find('tbody').find('tr').empty();
        MostrarMercaderiacompraPresu();
    } else {
    }
}
function MostrarMercaderiacompraPresu() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMercaderiaCompraPresu").append($("<tr>").append($(
                        "<td style=display:none>" + value.idmercaderia + "</td>" +
                        "<td>" + value.codigogenerico + "</td>" +
                        "<td>" + value.mer_descripcion + "</td>" +
                        "<td>" + value.mer_precio + "</td>")));
            });
        }
    });
}
function seleccionarMercaderiaCompra() {
    $('#miTablaMercaderiaCompraPresu tr').click(function () {
        $('#codMerca').val($(this).find("td").eq(0).html());
        $('#codgenericiMerca').val($(this).find("td").eq(1).html());
        $('#cantidadMerca').val(1);
        $('#nombreMerca').val($(this).find("td").eq(2).html());
        $('#precioMerca').val($(this).find("td").eq(3).html());
        $('#cantidadMerca').focus();
        $('#ModalMercaderiaPresu').modal('hide');
    });
}
function buscadorTablaMercaderiaCompraPresu() {
    var tableReg = document.getElementById('miTablaMercaderiaCompraPresu');
    var searchText = document.getElementById('filtrarMercaderiaCompraPresu').value.toLowerCase();
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
function CargarMercaCompraGrillaPresu() {
    var cod = $('#codgenericiMerca').val();
    var codigo;
    $('#miTablaDetallePresuCompra').find('tbody').find('tr').each(function () {
        codigo = $(this).find("td").eq(1).html();
        if (cod === codigo) {
            alert('La mercaderia ya fue cargada, desea sustituirlo?');
            $(this).find("td").remove();
        }
    });
    agregarFilaMercaCompraPresu();
}
function agregarFilaMercaCompraPresu() {
    //idmaterial
    var v_codMaterialG = $('#codgenericiMerca').val();
    var v_codmaterial = $('#codMerca').val();
    var v_descripcion = $('#nombreMerca').val();
    var v_precio = $('#precioMerca').val();
    var v_cant = $('#cantidadMerca').val();
    subtotal = v_precio * v_cant;
    $('#miTablaDetallePresuCompra').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\" src='Recursos/img/delete.png' width=14 height=14/></td>\n\
            </tr>");
    calcularmontoPresu();
    $('#codgenericiMerca').val(null);
    $('#codgenericiMerca').focus;
    $('#nombreMerca').val(null);
    $('#precioMerca').val(null);
    $('#cantidadMerca').val(null);
    calculomonto();

}//-----------------------

function  insertarpresuCompra() {
    var tipocompra = $('#PresuCompTipo').val();
    if (tipocompra === "") {
        alert("Debes cargar el tipo de compra.!!");
    } else {

        var dato = "";
        $('#miTablaDetallePresuCompra').find('tbody').find('tr').each(function () {
            dato = $(this).find("td").eq(0).html();
        });
        if (dato === "") {
            alert('No hay detalle que guardar..!');
            $("#codgenericiMerca").focus();
        } else {
            if ($('#PresuCompProvee').val() === "") {
                alert('Debe ingresar todos los datos requeridos para la consulta..');
                $("#codgenericiMerca").focus();
            } else {
                var opcion = confirm('Desea Guardar Presupuesto de Compras..?');
                if (opcion === true) {
                    var pedidoC = $('#PresuCompPedidoC').val();
                    var pedido = 0;
                    if (pedidoC != "") {
                        pedido = pedidoC;
                    } else {
                        pedido = 0;
                    }
                    datoscaberaordecompra = {
                        "opcion": 9,
                        "pcValor": 1,
                        "Presucomprafecha": $('#PresuCompFecha').val(),
                        "Presucompracuota": $('#PresuCompNroCuota').val(),
                        "Presucompramonto": $('#PresuCompMonto').val(),
                        "Presucompraintervalo": $('#PresuCompIntervalo').val(),
                        "PresuPedidoCompra": pedido,
                        "Presucompraprovee": $('#PresuCompIdProvee').val(),
                        "Presucomprausua": $('#CodvUser').val(),
                        "Presucompraestado": $('#PresuCompIdEstado').val(),
                        "Presucompratipo": $('#PresuCompidTipo').val(),
                        "coddeposito": $('#Coddepo').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
                        type: 'POST',
                        data: datoscaberaordecompra,
                        cache: false,
                        dataType: 'text',
                        success: function () {
                            setTimeout(function () {
                                InsertarDetalleCompraPresu();
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

}
function  ModificarPresupuestoCopmra() {
    var dato = "";
    $('#miTablaDetallePresuCompra').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiMerca").focus();
    } else {
        if ($('#PresuCompProvee').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiMerca").focus();
        } else {
            var opcion = confirm('Desea Guardar Presupuesto de Compras..?');
            if (opcion === true) {
                    var pedidoC = $('#PresuCompPedidoC').val();
                    var pedido = 0;
                    if (pedidoC != "") {
                        pedido = pedidoC;
                    } else {
                        pedido = 0;
                    }
                datosCabeceraJSON = {
                    "opcion": 9,
                    "pcValor": 2,
                    "Presucomprafecha": $('#PresuCompFecha').val(),
                    "Presucompracuota": $('#PresuCompNroCuota').val(),
                    "Presucompramonto": $('#PresuCompMonto').val(),
                    "Presucompraintervalo": $('#PresuCompIntervalo').val(),
                    "PresuPedidoCompra": pedido,
                    "Presucompraprovee": $('#PresuCompIdProvee').val(),
                    "Presucomprausua": $('#CodvUser').val(),
                    "Presucompraestado": $('#PresuCompIdEstado').val(),
                    "Presucompratipo": $('#PresuCompidTipo').val(),
                    "coddeposito": $('#Coddepo').val(),
                    "codPresupuesto": $('#codigo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deletedetallepcompra();
                        setTimeout(function () {
                            InsertarDetalleCompraPresu();
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
function  InsertarDetalleCompraPresu() {
    $('#miTablaDetallePresuCompra').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 10,
            "codigoD": $('#codigo').val(),
            "idmercaD": $(this).find("td").eq(0).html(),
            "precioD": $(this).find("td").eq(3).html(),
            "cantiD": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
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
    alert("Presupuesto de Compras guardado correctamente.!!");
    window.location.reload();
}
function  deletedetallepcompra() {
    datosDetalleJSON = {
        "opcion": 15,
        "codPresupcompra": $('#codigo').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
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
function MostrarPresupuestoCompra() {
    crearJSON(11);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPlanillaCompraPresu").append($("<tr>").append($("<td>" + value.idpresupuestocomp + "</td>" +
                        "<td>" + value.presup_fecha + "</td>" +
                        "<td>" + value.prov_nombre + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.tipo_descri + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function seleccionarPresupuestoCompras() {
    $('#miTablaPlanillaCompraPresu tr').click(function () {
        $('#PresuNro').val($(this).find("td").eq(0).html());
        $('#estadoPresuC').val($(this).find("td").eq(5).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoPresuC').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoPresuC').style.color = "#000000";
            document.getElementById('estadoPresuC').style.background = "#4F6";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoPresuC').style.background = "#4F6AD7";
            document.getElementById('estadoPresuC').style.color = "#ffffff";
        }
        if (estado === 'ANULADO') {
            document.getElementById('estadoPresuC').style.background = "firebrick";
            document.getElementById('estadoPresuC').style.color = "#ffffff";
        }
    });
}//---------------------------
function buscadorPlanillaCompraPresu() {
    var tableReg = document.getElementById('miTablaPlanillaCompraPresu');
    var searchText = document.getElementById('filtrarPlanillaCompraPresu').value.toLowerCase();
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

function cambioEstadoPresuCompras() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPresupuestoCompra a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estadoPresuC').val() === "") {
                    alert('Seleccione un presupuesto de compras.!');
                } else if ($('#estadoPresuC').val() === 'PENDIENTE' || $('#estadoPresuC').val() === 'ANULADO') {
                    alert('El presupuesto aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estadoPresuC').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular el presupuesto.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoPresup": 6,
                            "PresupuestoCNro": $('#PresuNro').val()
                        };
                        confirmarPresupuestCompra();
                        alert('Presupuesto Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estadoPresuC').val() === "") {
                    alert('Seleccione un presupuesto de compras.!');
                } else if ($('#estadoPresuC').val() === 'CONFIRMADO' || $('#estadoPresuC').val() === 'ANULADO') {
                    alert('El presupuesto ya fué Confirmado o esta Anulada..');
                } else if ($('#estadoPresuC').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar el presupuesto de compras.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoPresup": 2,
                            "PresupuestoCNro": $('#PresuNro').val()
                        };
                        confirmarPresupuestCompra();
                        alert('Presupuesto Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estadoPresuC').val() === "") {
                    alert('Seleccione un presupuesto de Compras.!');
                } else if ($('#estadoPresuC').val() === 'PENDIENTE' || $('#estadoPresuC').val() === 'ANULADO') {
                    alert('El presupuesto no se puede Revertir..');
                } else if ($('#estadoPresuC').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir el presupuesto.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoPresup": 1,
                            "PresupuestoCNro": $('#PresuNro').val()
                        };
                        confirmarPresupuestCompra();
                        alert('El presupuesto ha vuelto a su estado de Origen.!!');
                    }
                }
            }
        });
    });
}
function confirmarPresupuestCompra() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPlanillaCompraPresu').find('tbody').find('tr').empty();
            MostrarPresupuestoCompra();
        },
        error: function () {
        }
    });
}

function controlBotonesPresuCompra() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPresupuestoCompra a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnModificar' && $('#estadoPresuC').val() === 'CONFIRMADO' || $('#estadoPresuC').val() === 'ANULADO') {
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
function recuperaDetallePresuCompra() {
    controlBotonesPresuCompra();

    if ($('#PresuNro').val() === "") {
        alert('Seleecione planilla para visualizar..');
    } else if ($('#estadoPresuC').val() === "CONFIRMADO") {
        alert("El presupuesto ya fue confirmado, no se puede modificar..");
    } else {
        $('#ventanaPresupuestoCompra').modal('show');
        $('#miTablaDetallePresuCompra').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 13,
            "nroPresuCompra": $('#PresuNro').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/presupuestocompracontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#codigo").val(value.idpresupuestocomp);
                        $("#PresuCompFecha").val(value.presup_fecha);
                        $("#PresuCompEstado").val(value.descri_estado);
                        $("#PresuCompUsuario").val(value.usu_nombre);
                        $("#PresuCompProvee").val(value.prov_nombre);
                        $("#PresuCompIdProvee").val(value.id_prov);
                        $("#PresuCompPedidoC").val(value.pcomp_nro);
                        $("#PresuCompTipo").val(value.tipo_descri);
                        $("#PresuCompidTipo").val(value.tipo_codigo);
                        $("#PresuCompIntervalo").val(value.presup_intervalo);
                        $("#PresuCompNroCuota").val(value.presup_cantcuota);
                        $("#PresuCompMonto").val(value.presup_monto);
                        ///////BLOQUEA LOS CAMPOS//////
                        $("#PresuCompFecha").prop('disabled', true);
                        $("#PresuCompEstado").prop('disabled', true);
                        $("#PresuCompUsuario").prop('disabled', true);
                        $("#PresuCompProvee").prop('disabled', false);
                        $("#PresuCompPedidoC").prop('disabled', false);
                        $("#PresuCompTipo").prop('disabled', false);
                        $("#PresuCompIntervalo").prop('disabled', false);
                        $("#PresuCompNroCuota").prop('disabled', false);
                        $("#PresuCompMonto").prop('disabled', false);
                        subtotal = value.detprescomp_precio * value.detprescomp_cantidad;
                        $('#miTablaDetallePresuCompra').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.detprescomp_precio + "</td>\n\
                                    <td>" + value.detprescomp_cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                    });
                    $('#codigo').val($('#PresuNro').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#codgenericiMerca").focus();
                }
                calcularmontoPresu();
            }
        });
    }
}
function SoloNumerosPresu(input) {
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