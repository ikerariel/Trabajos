$(document).ready(function () {
    opcionsOrden();

});
function comboConCompra() {

    var array = ["Contado", "Credito"];
    var cont = 0;
    for (var i in array) {
        cont++;
        document.getElementById("condpago").innerHTML += "<option value='" + cont + "'>" + array[i] + "</option>";
    }

}

function opcionsOrden() {
    comboConCompra();
    allOrdenCompras();
    allart();
    estadoOrdenCompra();
    $('#btnNuevoOrden').click(function () {
        getcodigoOrdenCompras();
        $('#ventanaOrdenCompra').modal('show');
        $('#btnGuardarOrdencompra').show();
        $('#btnModificarOrdencompra').hide();
        $('#usuarioOrden').val($('#usertext_v').val());
        $('#estadoOrden').val('PENDIENTE');
    });
    $('#condpago').change(function () {
        var op = $('#condpago').val();
        if (parseInt(op) === 1) {
            $("#interorden").prop('disabled', true);
            $("#cantcuotaorden").prop('disabled', true);
            $("#cantcuotaorden").prop('disabled', true);
        }
        if (parseInt(op) === 2) {
            $("#interorden").prop('disabled', false);
            $("#cantcuotaorden").prop('disabled', false);
            $("#cantcuotaorden").prop('disabled', false);
            $("#interorden").focus();
        }

    });

}

function calcularCuota() {
    var cant = $('#cantcuotaorden').val();
    var montototal = $('#montoOrdentotal').val().replace(/\./g, '');
    var cuota = parseInt(montototal) / parseInt(cant);
    $('#montocuota').val(Math.trunc(cuota));
    numeroDecimal('montocuota');
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS-----------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
//
//REPORTES ORDEN DE COMPRAS
function reportesOrdenCompras() {
    if ($('#ordenNro').val() === "") {
        alert('Debes seleccionar una Orden de Compra..');
        $('#ordenNro').focus();
    } else {
        valor = $('#ordenNro').val();
        window.open("reportesOrdenCompras.jsp?cod=" + valor + "", "_blank");
    }

}

var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;

function fechaactualL() {
    controlBotonesNueva();
    var fecha = new Date();
    $('#fechaOrden').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function controlBotonesNueva() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesOrdenCompras a', function () {
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

function buscadorTablaArticulos() {
    var tableReg = document.getElementById('miTablaArti');
    var searchText = document.getElementById('filtrarArti').value.toLowerCase();
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
function abrirArticulos() {
    if ($('#idartiGenerico').val() === "") {
        $('#grillaArti').modal('show');
        $('#miTablaArti').find('tbody').find('tr').empty();
        allart();

    } else {

    }
}

function seleccionArticulosSS() {
    $('#miTablaArti tr').click(function () {
        $('#idarti').val($(this).find("td").eq(0).html());
        $('#idartiGenerico').val($(this).find("td").eq(1).html());
        $('#idcanti').val(1);
        $('#iddescrip').val($(this).find("td").eq(2).html());
        $('#PrecioArti').val($(this).find("td").eq(3).html());
        $('#idcanti').focus();
        $('#grillaArti').modal('hide');

    });
}
function seleccionDetArticulosOrden() {
    $('#miTablaDetOrdenCompras tr').click(function () {
        $('#idartiGenerico').val($(this).find("td").eq(0).html());
        $('#iddescrip').val($(this).find("td").eq(1).html());
        $('#PrecioArti').val($(this).find("td").eq(2).html());
        $('#idcanti').val($(this).find("td").eq(3).html());
//        $('#idcanti').val(3);
        $('#idartiGenerico').focus();
    });
}

function cargarDeatalleOrden() {
    var ban = false;
    if ($('#idartiGenerico').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#idartiGenerico').val();
        var codigo;
        $('#mitablaOrdendetalle').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    agregarfilaorden();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            agregarfilaorden();
        }
    }

}
var indorden = 0;
function agregarfilaorden() {
    var v_codmaterial = $('#idartiGenerico').val();
    var v_descripcion = $('#iddescrip').val();
    var v_precio = $('#PrecioArti').val();
    var v_cant = $('#idcanti').val();

    subtotal = v_precio * v_cant;
    indorden++;
    $('#mitablaOrdendetalle').append("<tr id=\'prod" + indorden + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + indorden + "\').remove(); calmonto(4);\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td>\n\
            </tr>");

    calmonto(4);
    $('#idartiGenerico').val(null);
    $('#idartiGenerico').focus;
    $('#idcanti').val(null);
    $('#iddescrip').val(null);
    $('#PrecioArti').val(null);

}
//            CALCULO DE MONTOS TOTALES

function calmonto(valor) {
    setTimeout(function () {
        monto = 0;
        acumu = 0;

        $('#mitablaOrdendetalle').find('tbody').find('tr').each(function () {
            monto = parseInt($(this).find("td").eq(valor).html());
            acumu = acumu + monto;
        });
        $('#totalorden').val(acumu);
        numeroDecimal('totalorden');
        $('#montoOrdentotal').val($('#totalorden').val());
        numeroDecimal('montoOrdentotal');

    }, 1200);

}

function updatemonto(valormonto, ind) {
    var monto = $('#total').val();
    var calculo = monto - valormonto;
    $('#total').val(calculo);

    calculo = 0;
    monto = 0;
}

function abrirproveedoreSS() {
    if ($('#proveeOrden').val() === "") {
        $('#grillaProveed').modal('show');
        $('#miTablaProveedores').find('tbody').find('tr').empty();
        allProveedores();

    } else {

    }
}
function seleccionProveedoresS() {
    $('#miTablaProveedores tr').click(function () {
        $('#idproveedor').val($(this).find("td").eq(0).html());
        $('#proveeOrden').val($(this).find("td").eq(1).html());
        $('#proveeOrden').focus();
        $('#grillaProveed').modal('hide');

    });
}
function buscadorTablaProveedoresS() {
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
        $('#pedidoOrden').val($(this).find("td").eq(0).html());
        $('#pedidoOrden').focus();
        $('#grillaPedidos').modal('hide');

    });
}
function abrirpedi2() {
    if ($('#pedidoOrden').val() === "") {
        $('#grillaPedidos').modal('show');
        $('#miTablaPedidos').find('tbody').find('tr').empty();
        listarPedidosCompras();

    } else {

    }
}
function seleccionOrdenComprasS() {
    $('#miTablaOrdenC tr').click(function () {
        $('#ordenNro').val($(this).find("td").eq(0).html());
        $('#estadOrdenP').val($(this).find("td").eq(5).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadOrdenP').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadOrdenP').style.color = "#000000";
            document.getElementById('estadOrdenP').style.background = "PaleGoldenrod";
        }
        if (estado === 'AUTORIZADO') {

            document.getElementById('estadOrdenP').style.background = "firebrick";
            document.getElementById('estadOrdenP').style.color = "#ffffff";
        }

    });
}
function buscadorPlanillaOrdenComprasS() {
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

//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------


//FUNCIONES DE TRANSACCIONES----------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoC": $('#codigo').val(),
        "fechaC": $('#fechaOrden').val(),
        "sucurC": $('#sucursalOrden').val(),
        "proveeC": $('#proveeOrden').val(),
        "PcompC": $('#pedidoOrden').val(),
        "usuaC": $('#idusuaOrden').val(),
        "estadoC": $('#idestadOrden').val(),

        "Artcodigo": $('#idartiGenerico').val(),
        "Artgenerico": $('#codgenericoarti').val(),
        "Artprecio": $('#precioarti').val(),
        "Artdescri": $('#descriarti').val()


    };
}
function getcodigoOrdenCompras() {
    $("#proveeOrden").val(null);
    $('#miTablaDetOrdenCompras').find('tbody').find('tr').empty();
    crearJSON(1);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
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

//   para insertar la orden de compras//////////////////////////////////////////////////////////////////////////
//   para insertar la orden de compras///////////////////////////////////////////////////////////////////////////////

function  InsertarOrdenComprasS() {
    var dato = "";
    $('#mitablaOrdendetalle').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#idartiGenerico").focus();
    } else {
        if ($('#usuarioOrden').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#idartiGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar orden de Compras..?');
            if (opcion === true) {
                var intervalo;
                var montocuota;
                var cantcuota;
                var op = $('#condpago').val();
                if (parseInt(op) === 1) {
                    intervalo = 0;
                    montocuota = 0;
                    cantcuota = 0;
                } else if (parseInt(op) === 2) {
                    intervalo = $('#interorden').val();
                    montocuota = $('#montocuota').val().replace(/\./g, '');
                    cantcuota = $('#cantcuotaorden').val();
                }
                datosCabeceraJSON = {
                    "opcion": 2,
                    "sucurC": $('#codsucursal_v').val(),
                    "proveeC": $("#idproveed").val(),
                    "PcompC": $('#nroPresupuesto').val(),
                    "usuaC": $('#idusersession_v').val(),
                    "condicionPago": op,
                    "intervalo": intervalo,
                    "montoC": montocuota,
                    "cantiC": cantcuota
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        insertarDetalleArticuloOrd();
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
function  ModificarOrdenCompra() {
    var dato = "";
    $('#mitablaOrdendetalle').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#idartiGenerico").focus();
    } else {
        if ($('#usuarioOrden').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#idartiGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar orden de Compras..?');
            if (opcion === true) {
                var intervalo;
                var montocuota;
                var cantcuota;
                var op = $('#condpago').val();
                if (parseInt(op) === 1) {
                    intervalo = 0;
                    montocuota = 0;
                    cantcuota = 0;
                } else if (parseInt(op) === 2) {
                    intervalo = $('#interorden').val();
                    montocuota = $('#montocuota').val().replace(/\./g, '');
                    cantcuota = $('#cantcuotaorden').val();
                }
                datosCabeceraJSON = {
                    "opcion": 21,
                    "nroOrdenC": $('#codigo').val(),
                    "sucurC": $('#codsucursal_v').val(),
                    "proveeC": $("#idproveed").val(),
                    "PcompC": $('#nroPresupuesto').val(),
                    "usuaC": $('#idusersession_v').val(),
                    "condPago": op,
                    "nroIntervalo": intervalo,
                    "montocuota": montocuota,
                    "cantcuota": cantcuota
                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deleteArticuloOrden();
                        setTimeout(function () {
                            insertarDetalleArticuloOrd();
                            alert("Orden de Compras guardado correctamente.!!");
                            window.location.reload();
                        }, 1100);

                    },
                    error: function () {
                    }
                });

            } else {

            }
        }
    }

}

function  insertarDetalleArticuloOrd() {
    $('#mitablaOrdendetalle').find('tbody').find('tr').each(function () {
        ds = {
            "opcion": 3,
            "codigoD": $('#codigo').val(),
            "idartiD": $(this).find("td").eq(0).html(),
            "precioD": $(this).find("td").eq(2).html(),
            "cantiD": $(this).find("td").eq(3).html(),
            "impuD": $(this).find("td").eq(5).html()
        };

        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
            type: 'POST',
            data: ds,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });

}
function  deleteArticuloOrden() {
    js = {
        "opcion": 22,
        "nroOrdecompra": $('#codigo').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
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
//                url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
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
//function  ModificarDetOrdenComprasS() {
//    $('#miTablaDetOrdenCompras').find('tbody').find('tr').each(function () {
//        datosDetalleJSON = {
//            "opcion": 4,
//            "codigoD": $('#codigo').val(),
//            "idartiD": $(this).find("td").eq(0).html(),
//            "precioD": $(this).find("td").eq(3).html(),
//            "cantiD": $(this).find("td").eq(4).html()
//        };
//        $.ajax({
//            url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
//            type: 'POST',
//            data: datosDetalleJSON,
//            cache: false,
//            dataType: 'text',
//            success: function () {
//                alert("Detalle Modificado Correctamente...!!");
//                window.location.reload();
//            },
//            error: function () {
//            }
//        });
//    });
//
//}

function getEstados() {
//    alert("llega al usuario")
    user = {
        "opcion": 8
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#idestadOrden").val(value.id_estado);
                $("#estadoOrden").val(value.est_descripcion);
            });
        },
        error: function () {
        }
    });

}

function getUsuarios() {
//    alert("llega al usuario")
    user = {
        "opcion": 7
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
////            alert(resp);
//            $("#codigo").val(resp);
            $.each(resp, function (indice, value) {
                $("#idusuaOrden").val(value.id_usuario);
                $("#usuarioOrden").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });

}

function allart() {
    art = {
        "opcion": 9
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
//                alert(value.id_articulo);

//                $("#listaarticulos").append("<option value=\""+value.id_articulo+"\"> "+value.art_descripcion+"<\option>");
                $("#listaarticulos").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + "</option>");
//                $("#miTablaArti").append($("<tr>").append($(
//                        "<td style=display:none>" + value.id_articulo + "</td>" +
//                        "<td>" + value.art_descripcion + "</td>" +
//                        "<td>" + value.preccompras + "</td>")));

            });

        }
    });
}
// PROCESO PARA HACER CON LA LISTA DE ARTICULOS EN VISTA
function getArti() {
    art = {
        "opcion": 19,
        "codArticulo": $('#idartiGenerico').val(),
        "coddepos": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#iddescrip').val(value.art_descripcion);
                $('#PrecioArti').val(value.preccompras);
                $('#idcanti').focus();
                $('#idcanti').val(1);
            });

        }
    });
}
function allProveedores() {
    crearJSON(14);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
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

function listarPedidosCompras() {
    crearJSON(10);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPedidos").append($("<tr>").append($(
                        "<td>" + value.id_pedidocompra + "</td>" +
                        "<td>" + value.pcompra_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));
            });
        }
    });
}

function allOrdenCompras() {
    crearJSON(15);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {

                $("#miTablaOrdenC").append($("<tr>").append($(
                        "<td>" + value.id_ordcompra + "</td>" +
                        "<td>" + value.ordenc_fecha + "</td>" +
                        "<td>" + value.ras_social + "</td>" +
                        "<td>" + value.id_presucompra + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));
            });
        }
    });
}

var vindex = 0;
function recuperarPresupuestoDetalle() {

    datosDetalleJSON = {
        "opcion": 11,
        "nroPedido": $('#nroPresupuesto').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {

            if (JSON.stringify(resp) != '[]') {
                var p = JSON.stringify(resp);
                var pre = JSON.parse(p);
                var presupuesto = pre[0].nropresupuesto;
                if (parseInt(presupuesto) > 0 ) {
                    alert('El presupuesto ya fue procesado..');
                } else {
                    var v = JSON.stringify(resp);
                    var vv = JSON.parse(v);
                    var estado = vv[0].id_estado;
                    if (parseInt(estado) === 1) {
                        $.each(resp, function (indice, value) {
                            $('#proveeOrden').val((value.proveedor));
                            $('#idproveed').val((value.id_proveedor));
                            subtotal = value.preciounitario * value.cantidad;
                            vindex++;
                            $('#mitablaOrdendetalle').append("<tr id=\'prod" + vindex + "\'>\
                                    <td >" + value.id_articulo + "</td>\n\
                                    <td>" + value.articulo + "</td>\n\
                                    <td>" + value.preciounitario + "</td>\n\
                                    <td>" + value.cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td style=display:none>" + value.id_impuesto + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + vindex + "\').remove(); calmonto(4);\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td>\n\
            </tr>");

                        });
                        calmonto(4);
                    } else {
                        alert('Presupuesto Pendiente.!!');
                    }

                }



            } else {
                alert('Datos no encontrados..');
                $("#pedidoOrden").focus();
            }
        }
    });
}

function confirmarOrdenCompras() {
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

function controlBotonesOrdenCompras() {
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

function estadoOrdenCompra() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesOrdenCompra a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnularOrden') {
                if ($('#estadOrdenP').val() === "") {
                    alert('Seleccione una Orden de Compra.!');
                } else if ($('#estadOrdenP').val() === 'CONFIRMADO') {
                    alert('La Orden de Comprao ya no se puede Anular..');
                } else {
                    var opcion = confirm('Desea Anular el la Orden de Compra.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CambioEstado": 2,
                            "nroOrdenCo": $('#ordenNro').val()
                        };
                        confirmarOrdenCompras();
                        alert('Pedido Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmarOrden') {
                if ($('#estadOrdenP').val() === "") {
                    alert('Seleccione una Orden de Compra.!');
                } else if ($('#estadOrdenP').val() === 'CONFIRMADO') {
                    alert('la Orden ya fué Confirmado o esta Anulada..');
                } else {
                    var opcion = confirm('Desea Confirmar la Orden de Compra.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CambioEstado": 1,
                            "nroOrdenCo": $('#ordenNro').val()
                        };
                        confirmarOrdenCompras();
                        alert('Pedido Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertirOrden') {
                if ($('#estadOrdenP').val() === "") {
                    alert('Seleccione una Orden de Compra.!');
                } else if ($('#estadOrdenP').val() === 'PENDIENTE') {
                    alert('La Orden de Compra no se puede Revertir..');
                } else {
                    var opcion = confirm('Desea Revertir la Orden de Compra.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CambioEstado": 3,
                            "nroOrdenCo": $('#ordenNro').val()
                        };
                        confirmarOrdenCompras();
                        alert('El pedido ha vuelto a su estado de Origen.!!');
                    }
                }


            }
        });
    });
}
function confirmarOrdenCompras() {
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaOrdenC').find('tbody').find('tr').empty();
            allOrdenCompras();
        },
        error: function () {

        }
    });
}

var indexordenV = 0;
function traerDetalleOrdenCompra() {
    $('#btnModificarOrdencompra').show();
    $('#btnGuardarOrdencompra').hide();
    if ($('#ordenNro').val() === "") {
        alert('Seleecione un pedido para visualizar..');
    } else {
        $('#ventanaOrdenCompra').modal('show');
        $('#mitablaOrdendetalle').find('tbody').find('tr').empty();
        datosOrden = {
            "opcion": 16,
            "nroOrden": $('#ordenNro').val()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
            type: 'POST',
            data: datosOrden,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
                    $.each(resp, function (indice, value) {
                        $("#fechaOrden").val(value.ordenc_fecha);
                        $("#estadoOrden").val(value.est_descripcion);
                        $("#proveeOrden").val(value.ras_social);
                        $("#nroPresupuesto").val(value.id_presucompra);
                        $("#usuarioOrden").val(value.usu_nombre);
                        $("#idproveed").val(value.id_proveedor);

                        $("#condpago").val(value.id_condicionpago);
                        $("#interorden").val(value.intervalo);
                        $("#montocuota").val(value.montocuota);
                        $("#cantcuotaorden").val(value.cant_cuota);
                        numeroDecimal('montocuota');

                        $("#usuarioOrden").prop('disabled', true);
                        $("#proveeOrden").prop('disabled', true);
                        $("#pedoidoOrden").prop('disabled', true);
                        subtotal = value.cantidad_detorden * value.precio_detorden;
                        indexordenV++;
                        $('#mitablaOrdendetalle').append("<tr id=\'prod" + indexordenV + "\'>\
                                    <td>" + value.id_articulo + "</td>\n\
                                    <td>" + value.art_descripcion + "</td>\n\
                                    <td>" + value.precio_detorden + "</td>\n\
                                    <td>" + value.cantidad_detorden + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td style=display:none>" + value.id_impuesto + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + indexordenV + "\').remove();calmonto(4);\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");

                    });
                    $('#codigo').val($('#ordenNro').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#ordenNro").focus();
                }
                calmonto(4);
            }
        });


    }
}
function  insertarArticulos() {
    if ($('#codgenericoarti').val() === "") {
        alert('Debe ingresar todos los datos requeridos para la consulta..');
        $("#codgenericoarti").focus();
    } else {
        var opcion = confirm('Desea Guardar orden de Compras..?');
        if (opcion === true) {
            datosCabeceraJSON = {
                "opcion": 17,
                "Artiprecio": $('#codgenericoarti').val(),
                "Artiescri": $('#descriarti').val(),
                "Artigenerico": $('#precioarti').val()
            };
            $.ajax({
                url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
                type: 'POST',
                data: datosCabeceraJSON,
                cache: false,
                dataType: 'text',
                success: function () {
                    alert("El articulo se guardo correctamente.!!");
                    $("#descriarti").val("");
                },
                error: function () {
                }
            });
        } else {
        }
    }
}

function getcodigoArticulos() {
    $("#codgenericoarti").val(null);
    crearJSON(18);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {

            $("#codarti").val(resp);
            $("#codgenericoarti").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');

        }
    });

}