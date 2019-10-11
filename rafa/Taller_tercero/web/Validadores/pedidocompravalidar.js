/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    fechaactual();
    allPedidos();
    cambioEstadoPedido();
});
function limpiarcampopedidoc() {
    window.location.reload();
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS-----------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
function reportes() {
    valor = $("#v_nropedido").val();
    var cod = 1;
      window.open(`reportesCompra_v.jsp?codigo=${cod}&pcomp_nro=${valor}`, "_blank");

}
function fechaactual() {
    var fecha = new Date();
    $('#fechapedido').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function abrir() {
    if ($('#idmaterialGenerico').val() === "") {
        $('#grillaProducto').modal('show');
        $('#miTablaMercaderia').find('tbody').find('tr').empty();
        allMercaderia();

    } else {

    }
}
function seleccionMercaderia() {
    $('#miTablaMercaderia tr').click(function () {
        $('#idmaterial').val($(this).find("td").eq(0).html());
        $('#idmaterialGenerico').val($(this).find("td").eq(1).html());
        $('#idcantidad').val(1);
        $('#iddescripcion').val($(this).find("td").eq(2).html());
        $('#idpreci').val($(this).find("td").eq(3).html());
        $('#idcantidad').focus();
        $('#grillaProducto').modal('hide');

    });
}
function selecTablaMercaderiaDet() {
    $('#miTablaDetalleMercaderia tr').click(function () {
        $('#idmaterial').val($(this).find("td").eq(0).html());
        $('#idmaterialGenerico').val($(this).find("td").eq(1).html());
        $('#iddescripcion').val($(this).find("td").eq(2).html());
        $('#idpreci').val($(this).find("td").eq(3).html());
        $('#idcantidad').val(3);
        $('#idmaterialGenerico').focus();
    });
}
function numeroDecimal(...uno) {
    for (let numero of uno) {
        var num = document.getElementById(numero).value.replace(/\./g, '');
        if (!isNaN(num)) {
            num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
            num = num.split('').reverse().join('').replace(/^[\.]/, '');
            document.getElementById(numero).value = num;
            $('#' + numero).css('font-weight', 'bold');

        } else {
            alert('Solo se permiten numeros');
            document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');

        }
    }

}
function CargarMercaderia() {
    var ban = false;
    if ($('#idmaterialGenerico').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#idmaterialGenerico').val();
        var codigo;
        $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(1).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    agregarFilaMercaderia();
                } else {
                    ban = true;
                }

            } else {

            }
        });
        if (ban === false) {
            agregarFilaMercaderia();
        }
    }
}
var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;
var indpe=0;
function agregarFilaMercaderia() {
    var v_codMaterialG = $('#idmaterialGenerico').val();
    var v_codmaterial = $('#idmaterial').val();
    var v_descripcion = $('#iddescripcion').val();
    var v_cant = $('#idcantidad').val();
    var v_precio = $('#idpreci').val();

    subtotal = v_precio * v_cant;

    $('#miTablaDetalleMercaderia').append("<tr id=\'prod" + indpe + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><img onclick=\"$(\'#prod" + indpe + "\');removepco();calcularmontopedic()\" src='Recursos/img/delete.png' width=14 height=14/></td>\n\
            </tr>");
    $('#idmaterialGenerico').val(null);
    $('#iddescripcion').val(null);
    $('#idmaterialGenerico').focus;
    $('#idcantidad').val(null);
    $('#idpreci').val(null);
    calcularmontopedic();
}

function removepco() {
    $('#miTablaDetalleMercaderia tr').click(function () {
        $(this).closest('tr').remove();

    });
}

function calcularmontopedic() {
    monto = 0;
    acumu = 0;
    $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(5).html());
        acumu = acumu + monto;
    });
    $('#total').val(acumu);
    numeroDecimal('total');
}

function updatemonto(valormonto, ind) {
    var monto = $('#total').val();
    var calculo = monto - valormonto;
    $('#total').val(calculo);
    calculo = 0;
    monto = 0;
}
function seleccion() {
    $('#miTablaPedidos tr').click(function () {
        $('#v_nropedido').val($(this).find("td").eq(0).html());
        $('#v_estado').val($(this).find("td").eq(4).html());
        $('#v_obs').val($(this).find("td").eq(2).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#v_estado').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('v_estado').style.color = "#000000";
            document.getElementById('v_estado').style.background = "#4F6";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('v_estado').style.background = "#4F6AD7";
            document.getElementById('v_estado').style.color = "#ffffff";
        }
        if (estado === 'ANULADO') {
            document.getElementById('v_estado').style.background = "firebrick";
            document.getElementById('v_estado').style.color = "#ffffff";
        }
    });
}
function buscadorTablaPedido() {
    var tableReg = document.getElementById('miTablaPedidos');
    var searchText = document.getElementById('filtrarPedido').value.toLowerCase();
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
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}

function buscadorTablaMercaderia() {
    var tableReg = document.getElementById('miTablaMercaderia');
    var searchText = document.getElementById('filtrarMercaderia').value.toLowerCase();
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
        "codigov": $('#codigo').val(),
        "fechav": $('#fechapedido').val(),
        "estadov": $('#estado').val(),
        "usuariov": $('#Usuario').val(),
        "observacionv": $('#observ').val()
    };
}
function getcodigo_v() {
    controlBotonesNuevoPedido();
    $("#observ").val(null);
    $("#usuario").val($('#vUser').val());
    crearJSON(1);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {

            $("#codigo").val(resp);
            $("#observ").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
function controlBotonesNuevoPedido() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPedido a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#v_estado').val() === 'CONFIRMADO' || $('#v_estado').val() === 'ANULADO') {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}
function allMercaderia() {
    crearJSON(10);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMercaderia").append($("<tr>").append($("<td style=display:none>" + value.idmercaderia + "</td>" +
                        "<td>" + value.codigogenerico + "</td>" +
                        "<td>" + value.mer_descripcion + "</td>" +
                        "<td>" + value.mer_precio + "</td>")));

            });

        }
    });
}
function allPedidos() {
    crearJSON(9);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPedidos").append($("<tr>").append($("<td>" + value.pcomp_nro + "</td>" +
                        "<td>" + value.pcomp_fecha + "</td>" +
                        "<td>" + value.descri_estado + "</td>" +
                        "<td>" + value.observacion + "</td>" +
                        "<td>" + value.usu_nombre + "</td>")));

            });

        }
    });
}
function  InsertarPedidoCompra() {
    if ($('#observ').val() === "") {
        alert('Algunos datos no fueron cargados correctamente..');
    } else {
        var dato = "";
        $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
            dato = $(this).find("td").eq(0).html();
        });
        if (dato === "") {
            alert('No hay detalle que guardar..!');
            $("#idmaterialGenerico").focus();
        } else {
            if ($('#usuario').val() === "") {
                alert('Debe ingresar todos los datos requeridos para la consulta..');
                $("#idmaterialGenerico").focus();
            } else {
                var opcion = confirm('Desea Guardar el Pedido.?');
                if (opcion === true) {
                    datosCabeceraJSON = {
                        "opcion": 2,
                        "pdValor": 1,
                        "fechav": $('#fechapedido').val(),
                        "usuariov": $('#CodvUser').val(),
                        "observacionv": $('#observ').val(),
                        "depositov": $('#Coddepo').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
                        type: 'POST',
                        data: datosCabeceraJSON,
                        cache: false,
                        dataType: 'text',
                        success: function () {
                            DetalleMercaderia();
                            alert("Pedido guardado correctamente.!!");
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


}
function  DetalleMercaderia() {
    $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 3,
            "codigoD": $('#codigo').val(),
            "idmercaV": $(this).find("td").eq(0).html(),
            "preciov": $(this).find("td").eq(3).html(),
            "cantidadv": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
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
function  EliminarDetalleMercaderia() {
    var dato = "";
    $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#idmaterialGenerico").focus();
    } else {
        if ($('#usuario').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#idmaterialGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar el Pedido.?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 2,
                    "pdValor": 2,
                    "fechav": $('#fechapedido').val(),
                    "usuariov": $('#CodvUser').val(),
                    "observacionv": $('#observ').val(),
                    "depositov": $('#Coddepo').val(),
                    "nropedido": $('#codigo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        setTimeout(function () {
                            datosDetalleJSON = {
                                "opcion": 4,
                                "codigoD": $('#codigo').val()
                            };
                            $.ajax({
                                url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
                                type: 'POST',
                                data: datosDetalleJSON,
                                cache: false,
                                dataType: 'text',
                                success: function () {
                                    DetalleMercaderia();
                                    alert("Pedido guardado correctamente.!!");
                                    window.location.reload();
                                },
                                error: function () {
                                }
                            });

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

function recuperarDetallePedidoCompras() {
    var estadopc = $('#v_estado').val();
    if (estadopc === 'CONFIRMADO') {
        alert('Pedido Confirmado ya no se puede modificar.!!');
    } else {
        controlBotones();
        if ($('#v_nropedido').val() === "") {
            alert('Seleecione un pedido para visualizar..');
        } else {
            $('#ventanaPedido').modal('show');
            $('#miTablaDetalleMercaderia').find('tbody').find('tr').empty();
            datosDetalleJSON = {
                "opcion": 11,
                "nropedidov": $('#v_nropedido').val()
            };
            $.ajax({
                url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
                type: 'POST',
                data: datosDetalleJSON,
                cache: false,
                success: function (resp) {
                    if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                        $.each(resp, function (indice, value) {
                            ///RECUPERA LA CABECERA/////////
                            $("#fechapedido").val(value.pcomp_fecha);
                            $("#estado").val(value.descri_estado);
                            $("#usuario").val(value.usu_nombre);
                            $("#observ").val(value.observacion);

                            ///////BLOQUE LOS CAMPOS//////
                            $("#usuario").prop('disabled', true);
                            $("#observ").prop('disabled', false);
                            //$("#idmaterialGenerico").prop('disabled', true);
                            $("#idmaterial").prop('disabled', true);
                            //$("#idcantidad").prop('disabled', true);
                            subtotal = value.precio * value.cantidad;
                            $('#miTablaDetalleMercaderia').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.precio + "</td>\n\
                                    <td>" + value.cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();calcularmontopedic();;\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                        });
                        $('#codigo').val($('#v_nropedido').val());
                    } else {
                        alert('Datos no encontrados..');
                        $("#nrosolicitud").focus();
                    }
                    calcularmontopedic();
                }
            });


        }
    }

}
function  ModificarDetallepedidoc() {
    $('#miTablaDetalleMercaderia').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 4,
            "codigoD": $('#codigo').val(),
            "idmercaV": $(this).find("td").eq(0).html(),
            "preciov": $(this).find("td").eq(3).html(),
            "cantidadv": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
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
function confirmar() {
    if ($('#v_estado').val() === "") {
        alert('Seleccione un pedido.!');
    } else {
        if ($('#v_estado').val() === 'PENDIENTE') {
            var opcion = confirm('Desea confirmar el pedido.??');
            if (opcion === true) {

            }
        } else {
            if ($('#v_estado').val() === 'CONFIRMADO') {
                alert('El pedido ya fue confirmado..');
            }
        }
    }


}
function controlBotones() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPedido a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnRecuperar' && $('#v_estado').val() === 'CONFIRMADO' || $('#v_estado').val() === 'ANULADO') {
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

function cambioEstadoPedido() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPedido a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#v_estado').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#v_estado').val() === 'PENDIENTE' || $('#v_estado').val() === 'ANULADO') {
                    alert('El pedido aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#v_estado').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CDestado": 6,
                            "nroPd": $('#v_nropedido').val()
                        };
                        confirmarPedido();
                        alert('Pedido AnuladBo con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#v_estado').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#v_estado').val() === 'CONFIRMADO' || $('#v_estado').val() === 'ANULADO') {
                    alert('El pedido ya fué Confirmado o esta Anulada..');
                } else if ($('#v_estado').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CDestado": 2,
                            "nroPd": $('#v_nropedido').val()
                        };
                        confirmarPedido();
                        alert('Pedido Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#v_estado').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#v_estado').val() === 'PENDIENTE' || $('#v_estado').val() === 'ANULADO') {
                    alert('El pedido no se puede Revertir..');
                } else if ($('#v_estado').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 13,
                            "CDestado": 1,
                            "nroPd": $('#v_nropedido').val()
                        };
                        confirmarPedido();
                        alert('El pedido ha vuelto a su estado de Origen.!!');
                    }
                }


            }
        });
    });
}
function confirmarPedido() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidocompracontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPedidos').find('tbody').find('tr').empty();
            allPedidos();
        },
        error: function () {

        }
    });
}
//---------------------------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------------------
