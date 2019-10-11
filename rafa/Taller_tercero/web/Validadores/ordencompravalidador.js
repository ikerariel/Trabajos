
$(document).ready(function () {

    cambioEstadoOrdenC();
    allOrdenCompra();

});
function limpiarcampoOrdenp() {
    window.location.reload();
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS-----------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//function reportesOrdenComp() {
//    valor = $("#ordenNro").val();
//    window.open("ReportesVista/reportesOrdenComp.jsp?cod=" + valor + "", "_blank");
//
//}
var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;


function reportesordencompra() {
    valor = $("#ordenNro").val();
    var cod = 4;
      window.open(`reportesCompra_v.jsp?codigo=${cod}&ordenc_nro=${valor}`, "_blank");

}

function fechaactual() {
    controlBotonesNueva();
    var fecha = new Date();
    $('#fechaOrden').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function controlBotonesNueva() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesOrdenCompra a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estadOrdenP').val() === 'CONFIRMADO' || $('#estadOrdenP').val() === 'ANULADO') {
//                $("#btnGuardar").attr("disabled", true);
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}

function buscadorTablaMercad() {
    var tableReg = document.getElementById('miTablaMercad');
    var searchText = document.getElementById('filtrarMercad').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}
function abrirMerca() {
    if ($('#idmercadGenerico').val() === "") {
        $('#grillaMercad').modal('show');
        $('#miTablaMercad').find('tbody').find('tr').empty();
        allMercad();

    } else {

    }
}

function seleccionMercad() {
    $('#miTablaMercad tr').click(function () {
        $('#idmercad').val($(this).find("td").eq(0).html());
        $('#idmercadGenerico').val($(this).find("td").eq(1).html());
        $('#idcanti').val(1);
        $('#iddescrip').val($(this).find("td").eq(2).html());
        $('#PrecioMer').val($(this).find("td").eq(3).html());
        $('#idcanti').focus();
        $('#grillaMercad').modal('hide');

    });
}
function seleccionDetalleMerecadOrden() {
    $('#miTablaDetalleOrdenCompra tr').click(function () {
        $('#idmercad').val($(this).find("td").eq(0).html());
        $('#idmercadGenerico').val($(this).find("td").eq(1).html());
        $('#iddescrip').val($(this).find("td").eq(2).html());
        $('#PrecioMer').val($(this).find("td").eq(3).html());
        $('#idcanti').val(3);
        $('#idmercadGenerico').focus();
    });
}
function CargarMercaderiaGrilla() {
    var cod = $('#idmercadGenerico').val();
    var codigo;
    $('#miTablaDetalleOrdenCompra').find('tbody').find('tr').each(function () {
        codigo = $(this).find("td").eq(1).html();

        if (cod === codigo) {
            alert('La mercaderia ya fue cargada, desea sustituirlo?');
            $(this).closest("tr").remove();
        }
    });
    agregarFilaMercad();
}
var index02;
function agregarFilaMercad() {

    //idmaterial
    var v_codMaterialG = $('#idmercadGenerico').val();
    var v_codmaterial = $('#idmercad').val();
    var v_descripcion = $('#iddescrip').val();
    var v_precio = $('#PrecioMer').val();
    var v_cant = $('#idcanti').val();

    subtotal = v_precio * v_cant;

    $('#miTablaDetalleOrdenCompra').append("<tr id=\'prod" + index02 + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><img onclick=\"$(\'#prod" + index02 + "\');removeorden();calcularmonto();\" src='Recursos/img/delete.png' width=14 height=14/></td>\n\
            </tr>");
    calcularmonto();
    $('#idmercadGenerico').val(null);
    $('#idmercadGenerico').focus;
    $('#idcanti').val(null);
    $('#iddescrip').val(null);
    $('#PrecioMer').val(null);
//    $('#total').val(subtotal);


}


function removeorden() {
    $('#miTablaDetalleOrdenCompra tr').click(function () {
       $(this).closest('tr').remove();

    });
}

function calcularmonto() {
    monto = 0;
    acumu = 0;

    $('#miTablaDetalleOrdenCompra').find('tbody').find('tr').each(function () {
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
}
//function selecc() {
//    $('#miTabla tr').click(function () {
//        $('#total').val($(this).find("td").eq(5).html());
//    });
//}        
function SoloNumerosOrden(input) {
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

}
function abrirprov() {
    if ($('#proveeOrden').val() === "") {
        $('#grillaProveed').modal('show');
        $('#miTablaProveedores').find('tbody').find('tr').empty();
        allProveedor();

    } else {

    }
}
function seleccionProvee() {
    $('#miTablaProveedores tr').click(function () {
        $('#idproveedor').val($(this).find("td").eq(0).html());
        $('#proveeOrden').val($(this).find("td").eq(1).html());
        $('#proveeOrden').focus();
        $('#grillaProveed').modal('hide');

    });
}
function buscadorTablaProveed() {
    var tableReg = document.getElementById('miTablaProveedores');
    var searchText = document.getElementById('filtrarProveedor').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}
function buscadorTablaPedi() {
    var tableReg = document.getElementById('miTablaPedidos');
    var searchText = document.getElementById('filtrarPedi').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}
function seleccionPedidos() {
    $('#miTablaPedidos tr').click(function () {
        $('#pedoidoOrden').val($(this).find("td").eq(0).html());
        $('#pedoidoOrden').focus();
        $('#grillaPedidos').modal('hide');

    });
}
function abrirpedi() {
    if ($('#pedoidoOrden').val() === "") {
        $('#grillaPedidos').modal('show');
        $('#miTablaPedidos').find('tbody').find('tr').empty();
        listarPedidos();

    } else {

    }
}
function seleccionOrdenCompras() {
    $('#miTablaOrdenC tr').click(function () {
        $('#ordenNro').val($(this).find("td").eq(0).html());
        $('#estadOrdenP').val($(this).find("td").eq(5).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadOrdenP').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadOrdenP').style.color = "#000000";
            document.getElementById('estadOrdenP').style.background = "#4F6";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadOrdenP').style.background = "#4F6AD7";
            document.getElementById('estadOrdenP').style.color = "#ffffff";
        }
        if (estado === 'ANULADO') {
            document.getElementById('estadOrdenP').style.background = "firebrick";
            document.getElementById('estadOrdenP').style.color = "#ffffff";
        }

    });
}
function buscadorPlanillaOrden() {
    var tableReg = document.getElementById('miTablaOrdenC');
    var searchText = document.getElementById('filtrarOrdenC').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}
function abrirPresupuesto() {
    if ($('#presupuestoOrden').val() === "") {
        $('#grillaPresupuesto').modal('show');
        $('#miTablaPresupuesto').find('tbody').find('tr').empty();
        listarPresupuesto();

    } else {

    }
}
function seleccionPresupuesto() {
    $('#miTablaPresupuesto tr').click(function () {
        $('#presupuestoOrden').val($(this).find("td").eq(0).html());
        $('#presupuestoOrden').focus();
        $('#grillaPresupuesto').modal('hide');
    });
}

function buscadorTablaPresupuesto() {
    var tableReg = document.getElementById('miTablaPresupuesto');
    var searchText = document.getElementById('filtrarPresupuesto').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------


//FUNCIONES DE TRANSACCIONES----------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoC": $('#codigo').val(),
        "fechaC": $('#fechaOrden').val(),
        "proveeC": $('#proveeOrden').val(),
        "usuaC": $('#idusuaOrden').val(),
        "PcompC": $('#pedoidoOrden').val(),
        "estadoC": $('#idestadOrden').val(),
        "presupC": $('#presupuestoOrden').val(),
        "Mercodigo": $('#codmerca').val(),
        "Merprecio": $('#codgenericomerca').val(),
        "Merdescri": $('#descrimerca').val(),
        "Mergenerico": $('#preciomerca').val()
    };
}
function getcodigoOrden() {
    controlBotonesOrdenCnuevo();
    $("#proveeOrden").val(null);
    $("#usuarioOrden").val($('#vUser').val());
    crearJSON(1);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {

            $("#codigo").val(resp);
            $("#proveeOrden").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');

        }
    });

}
function  InsertarOrden() {
    var dato = "";
    $('#miTablaDetalleOrdenCompra').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#idmercadGenerico").focus();
    } else {
        if ($('#usuarioOrden').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#idmercadGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar orden de Compras..?');
            if (opcion === true) {
                var pedidoCO = $('#pedoidoOrden').val();
                var pedido = 0;
                if (pedidoCO != "") {
                    pedido = pedidoCO;
                } else {
                    pedido = 0;
                }
                datosCabeceraJSON = {
                    "opcion": 2,
                    "oValor": 1,
                    "fechaC": $('#fechaOrden').val(),
                    "proveeC": $('#idproveedor').val(),
                    "usuaC": $('#CodvUser').val(),
                    "PcompC": pedido,
                    "estadoC": $('#idestadOrden').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        DetalleMercaderiaOrden();
                        alert("Orden de Compras guardado correctamente.!!");
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
function  modificarOrdenC() {
    var dato = "";
    $('#miTablaDetalleOrdenCompra').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#idmercadGenerico").focus();
    } else {
        if ($('#usuarioOrden').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#idmercadGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar orden de Compras..?');
            if (opcion === true) {
                var pedidoCO = $('#pedoidoOrden').val();
                var pedido = 0;
                if (pedidoCO != "") {
                    pedido = pedidoCO;
                } else {
                    pedido = 0;
                }
                datosCabeceraJSON = {
                    "opcion": 2,
                    "oValor": 2,
                    "fechaC": $('#fechaOrden').val(),
                    "proveeC": $('#idproveedor').val(),
                    "usuaC": $('#CodvUser').val(),
                    "PcompC": pedido,
                    "estadoC": $('#idestadOrden').val(),
                    "oCodOrden": $('#codigo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deletedetalleOrden();
                        setTimeout(function () {
                            DetalleMercaderiaOrden();
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
function  DetalleMercaderiaOrden() {
    $('#miTablaDetalleOrdenCompra').find('tbody').find('tr').each(function () {
        DATOS = {
            "opcion": 3,
            "codigoF": $('#codigo').val(),
            "idmercaF": $(this).find("td").eq(0).html(),
            "cantiF": $(this).find("td").eq(4).html(),
            "precioF": $(this).find("td").eq(3).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
            type: 'POST',
            data: DATOS,
            cache: false,
            dataType: 'text',
            success: function () {

            },
            error: function () {
            }
        });

    });
    alert("Orden de Compras guardado correctamente.!!");
    window.location.reload();

}
function  deletedetalleOrden() {
    datosDetalleJSON = {
        "opcion": 19,
        "oNroOrden": $('#codigo').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
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
//function  ModificarDetalleOrdenCo() {
//    var dato = "";
//    $('#miTablaDetalleOrdenCompra').find('tbody').find('tr').each(function () {
//        dato = $(this).find("td").eq(0).html();
//    });
//    if (dato === "") {
//        alert('No hay detalle que guardar..!');
//        $("#idmercadGenerico").focus();
//    } else {
//        var opcion = confirm()('Desea Modificar orden de Compras.?');
//        if (opcion === true) {
//            datosDetalleJSON = {
//                "opcion": 4,
//                "codigoD": $('#codigo').val(),
//                "idmercaD": $(this).find("td").eq(0).html(),
//                "precioD": $(this).find("td").eq(3).html(),
//                "cantiD": $(this).find("td").eq(4).html()
//            };
//            $.ajax({
//                url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
//                type: 'POST',
//                data: datosCabeceraJSON,
//                cache: false,
//                dataType: 'text',
//                success: function () {
//                    alert("Detalle Modificado Correctamente...!!");
//                    window.location.reload();
//                },
//                error: function () {
//                }
//            });
//        } 
//        else {
//        }
//    }
//}
function  ModificarDetalleOrdenCo() {
    $('#miTablaDetalleOrdenCompra').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 4,
            "codigoD": $('#codigo').val(),
            "idmercaD": $(this).find("td").eq(0).html(),
            "precioD": $(this).find("td").eq(3).html(),
            "cantiD": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            dataType: 'text',
            success: function () {
                alert("Detalle Modificado Correctamente...!!");
                window.location.reload();
            },
            error: function () {
            }
        });
    });

}

function getestadoOrden() {
//    alert("llega al usuario")
    user = {
        "opcion": 8
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#idestadOrden").val(value.idestado);
                $("#estadoOrden").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });

}
function getusuarioOrden() {
//    alert("llega al usuario")
    user = {
        "opcion": 7
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
////            alert(resp);
//            $("#codigo").val(resp);
            $.each(resp, function (indice, value) {
                $("#idusuaOrden").val(value.idusuario);
                $("#usuarioOrden").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });

}
function allMercad() {
    crearJSON(9);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMercad").append($("<tr>").append($("<td style=display:none>" + value.idmercaderia + "</td>" +
                        "<td>" + value.codigogenerico + "</td>" +
                        "<td>" + value.mer_descripcion + "</td>" +
                        "<td>" + value.mer_precio + "</td>")));

            });

        }
    });
}
function allProveedor() {
    crearJSON(14);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaProveedores").append($("<tr>").append($("<td style=display:none>" + value.id_prov + "</td>" +
                        "<td>" + value.prov_nombre + "</td>")));

            });

        }
    });
}
function listarPedidos() {
    crearJSON(10);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPedidos").append($("<tr>").append($("<td>" + value.pcomp_nro + "</td>" +
                        "<td>" + value.pcomp_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function allOrdenCompra() {
    crearJSON(15);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaOrdenC").append($("<tr>").append($("<td>" + value.ordenc_nro + "</td>" +
                        "<td>" + value.ordenc_fecha + "</td>" +
                        "<td>" + value.prov_nombre + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.pcomp_nro + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));

            });

        }
    });
}
function recuperarDetallePedido() {

    datosDetalleJSON = {
        "opcion": 11,
        "nroPedido": $('#pedoidoOrden').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {

            if (JSON.stringify(resp) != '[]') {
                var vpedido = JSON.stringify(resp);
                var p = JSON.parse(vpedido);
                var pedido = p[0].nropedido;
                if (pedido != "") {
                    alert("El Pedido ya se encuentra procesado..!!");
                } else {
                    $.each(resp, function (indice, value) {
                        subtotal = value.precio * value.cantidad;
                        $('#miTablaDetalleOrdenCompra').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.precio + "</td>\n\
                                    <td>" + value.cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");

                    });
                }

            } else {
                alert('Datos no encontrados..');
                $("#pedoidoOrden").focus();
            }
            calcularmonto();
        }
    });
}
function confirmarOrdenC() {
    if ($('#estadOrdenP').val() === "") {
        alert('Seleccione un pedido.!');
    } else {
        if ($('#estadOrdenP').val() === 'PENDIENTE') {
            var opcion = confirm('Desea confirmar el pedido.??');
            if (opcion === true) {

            }
        } else {
            if ($('#estadOrdenP').val() === 'CONFIRMADO') {
                alert('El pedido ya fue confirmado..');
            }
        }
    }


}
function controlBotonesOrdenCnuevo() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesOrdenCompra a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estadOrdenP').val() === 'CONFIRMADO' || $('#estadOrdenP').val() === 'ANULADO' || $('#estadOrdenP').val() === 'PENDIENTE') {
//                $("#btnGuardar").attr("disabled", true);
                document.getElementById("btnGuardar").style.display = 'none';
                document.getElementById("btnModificar").style.display = 'none';
            } else {
                if (v === 'btnNuevo' && $('#estadOrdenP').val() === '') {

                    document.getElementById("btnGuardar").style.display = '';
                    document.getElementById("btnModificar").style.display = 'none';

                }
            }
        });
    });
}
function controlBotonesOrdenC() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesOrdenCompra a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnRecuperar' && $('#estadOrdenP').val() === 'CONFIRMADO' || $('#estadOrdenP').val() === 'ANULADO') {
//                $("#btnGuardar").attr("disabled", true);
                document.getElementById("btnGuardar").style.display = 'none';
                document.getElementById("btnModificar").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = 'none';
                document.getElementById("btnModificar").style.display = '';
            }
        });
    });
}
function cambioEstadoOrdenC() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesOrdenCompra a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estadOrdenP').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#estadOrdenP').val() === 'PENDIENTE' || $('#estadOrdenP').val() === 'ANULADO') {
                    alert('El pedido aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estadOrdenP').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CambioEstado": 6,
                            "nroOrdenCo": $('#ordenNro').val()
                        };
                        confirmarOrdenCompra();
                        alert('Pedido Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estadOrdenP').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#estadOrdenP').val() === 'CONFIRMADO' || $('#estadOrdenP').val() === 'ANULADO') {
                    alert('El pedido ya fué Confirmado o esta Anulada..');
                } else if ($('#estadOrdenP').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CambioEstado": 2,
                            "nroOrdenCo": $('#ordenNro').val()
                        };
                        confirmarOrdenCompra();
                        alert('Pedido Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estadOrdenP').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#estadOrdenP').val() === 'PENDIENTE' || $('#estadOrdenP').val() === 'ANULADO') {
                    alert('El pedido no se puede Revertir..');
                } else if ($('#estadOrdenP').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CambioEstado": 1,
                            "nroOrdenCo": $('#ordenNro').val()
                        };
                        confirmarOrdenCompra();
                        alert('El pedido ha vuelto a su estado de Origen.!!');
                    }
                }


            }
        });
    });
}
function confirmarOrdenCompra() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaOrdenC').find('tbody').find('tr').empty();
            allOrdenCompra();
        },
        error: function () {

        }
    });
}
function recuperarDetalleOrdenCO() {

    controlBotonesOrdenC();
    if ($('#ordenNro').val() === "") {
        alert('Seleecione un pedido para visualizar..');
    } else if ($('#estadOrdenP').val() === "CONFIRMADO") {
        alert("La Orden de Compra ya fue confirmada, no se puede modificar.!");
    } else {

        $('#ventanaOrdenCompra').modal('show');
        $('#miTablaDetalleOrdenCompra').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 16,
            "nroOrdenB": $('#ordenNro').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#fechaOrden").val(value.ordenc_fecha);
                        $("#estadoOrden").val(value.descri_estado);
                        $("#proveeOrden").val(value.prov_nombre);
                        $("#usuarioOrden").val(value.usu_nombre);
                        $("#pedoidoOrden").val(value.pcomp_nro);
                        $("#idproveedor").val(value.id_prov);

                        ///////BLOQUEA LOS CAMPOS//////
                        $("#usuarioOrden").prop('disabled', true);
                        $("#proveeOrden").prop('disabled', false);
                        $("#pedoidoOrden").prop('disabled', false);
//                        $("#idmaterial").prop('disabled', true);
//                        $("#idcantidad").prop('disabled', true);
                        subtotal = value.precio_orden * value.cant_orden;
                        $('#miTablaDetalleOrdenCompra').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.precio_orden + "</td>\n\
                                    <td>" + value.cant_orden + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");

                    });
                    $('#codigo').val($('#ordenNro').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#ordenNro").focus();
                }
                calcularmonto();
            }
        });


    }
}
function  insertarMercaderia() {
    if ($('#codgenericomerca').val() === "") {
        alert('Debe ingresar todos los datos requeridos para la consulta..');
        $("#codgenericomerca").focus();
    } else {
        var opcion = confirm('Desea Guardar orden de Compras..?');
        if (opcion === true) {
            datosCabeceraJSON = {
                "opcion": 17,
                "Merprecio": $('#codgenericomerca').val(),
                "Merdescri": $('#descrimerca').val(),
                "Mergenerico": $('#preciomerca').val()
            };
            $.ajax({
                url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
                type: 'POST',
                data: datosCabeceraJSON,
                cache: false,
                dataType: 'text',
                success: function () {
                    alert("La mercaderia se guardo correctamente.!!");
                    $("#descrimerca").val("");
                },
                error: function () {
                }
            });
        } else {
        }
    }
}
//function  insertarMercaderia() {
//    crearJSON(17);
//    $.ajax({
//        // url: "clientesControl",
//        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
//        data: datosJSON,
//        type: 'POST',
//        dataType: 'text',
//        success: function () {
////            insertarDetalle();
//        },
//        error: function () {
//            alert('Error Durante la Insercion del Registro...!!!');
//        }
//    });
////    insertarDetalle();
//}
function getcodigoMercaderia() {
    $("#codgenericomerca").val(null);
    crearJSON(18);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {

            $("#codmerca").val(resp);
            $("#codgenericomerca").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
function listarPresupuesto() {
    crearJSON(19);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPresupuesto").append($("<tr>").append($("<td>" + value.idpresupuestocomp + "</td>" +
                        "<td>" + value.presup_fecha + "</td>" +
                        "<td>" + value.tipo_descri + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function recuperarDetallePresupuesto() {
    datosDetalleJSON = {
        "opcion": 20,
        "nroPresupuest": $('#presupuestoOrden').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ordencompracontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {

            if (JSON.stringify(resp) != '[]') {
                //alert(resp);
                $.each(resp, function (indice, value) {
                    subtotal = value.detprescomp_precio * value.detprescomp_cantidad;
                    $('#miTablaDetalleOrdenCompra').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.detprescomp_precio + "</td>\n\
                                    <td>" + value.detprescomp_cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                });
            } else {
                alert('Datos no encontrados..');
                $("#presupuestoOrden").focus();
            }
            calcularmonto();
        }
    });
}

