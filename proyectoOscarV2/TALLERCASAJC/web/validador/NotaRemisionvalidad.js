$(document).ready(function () {
    cambioEstadosNRemision();
    MostrarNotaRemision();
    listafacturas();
//    MostrarArticulosRemision();
//    MostrarModalProveedoresRemision();
//    MostrarFacrurasComprasRemision();
});

//FUNCIONES DE TRANSACCIONES ----------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------

function informeremision(v) {
    var nroremi = $('#_nro_NreC').val();
    if (parseInt(v) === 5) {
        var cod = v;
        window.open(`reportesCompra_v.jsp?codigo=${cod}&id_notaremi=${nroremi}`, "_blank");
        location.reload();
    }

    

}
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoR": $('#codigoNRemision').val(),
        "Nre_fecha": $('#_fecha_Nre').val(),
        "Nre_nro": $('#_nro_Nre').val(),
        "Nre_obse": $('#_obse_Nre').val(),
        "Nre_estado": $('#_Idestado_Nre').val(),
        "Nre_usuario": $('#_Idusuario_Nre').val(),
        "Nre_proveedor": $('#_Idproveedor_Nre').val(),
        "Nre_sucursal": $('#_Idsucursal_Nre').val(),
        "Nre_factcompra": $('#_Idfactcompra_Nre').val()
    };
}

function getcodigoRemision() {
    $('#btnguardarRemisión').show();
    $('#btnmodificarRemision').hide();
//    controlBotonesNuevo();
    $("#_proveedor_Nre").val(null);
    crearJSON(1);

// document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigoNRemision").val(resp);
            $("#_proveedor_Nre").focus();
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
function MostrarEstadosRemision() {
//    alert("llega al usuario")
    user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#_Idestado_Nre").val(value.id_estado);
                $("#_estado_Nre").val(value.est_descripcion);
            });
        },
        error: function () {
        }
    });
}
function MostrarUsuariosRemision() {
//    alert("llega al usuario")
    user = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#_Idusuario_Nre").val(value.id_usuario);
                $("#_usuario_Nre").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });
}
function MostrarModalProveedoresRemision() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaProveedoresRemision").append($("<tr>").append($(
                        "<td style=display:none>" + value.id_proveedor + "</td>" +
                        "<td>" + value.ras_social + "</td>")));
            });
        }
    });
}


function MostrarFacturasComprasRemision() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaFacturasComprasRemision").append($("<tr>").append($(
                        "<td>" + value.id_compra + "</td>" +
                        "<td>" + value.co_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));
            });
        }
    });
}
function RecuperarDetFacturasComprasRemision() {
    $('#miTablaDetNotaRemision').find('tbody').find('tr').empty();
    datosDetalleJSON = {
        "opcion": 6,
        "id_FacturacompraC": $('#_Idfactcompra_Nre').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            
              if (JSON.stringify(resp) != '[]') {
                var nro = JSON.stringify(resp);
                var nrof = JSON.parse(nro);
                var nrofac = nrof[0].nrofact;
                if (parseInt(nrofac) > 0 ) {
                    alert('La factura  ya fue procesada..');
                } else {
                    var v = JSON.stringify(resp);
                    var vv = JSON.parse(v);
                    var estado = vv[0].id_estado;
                    if (parseInt(estado) === 1) {
                        $.each(resp, function (indice, value) {
                            $('#idfactura').val(value.id_compra);
                    subtotal = value.precio_detcomp * value.cantidad_detcomp;
                    tindex++;
                    $('#miTablaDetNotaRemision').append("<tr id=\'prod" + tindex + "\'>\
                                  <td>" + value.id_articulo + "</td>\n\
                                    <td>" + value.art_descripcion + "</td>\n\
                                     <td>" + value.precio_detcomp + "</td>\n\
                                    <td>" + value.cantidad_detcomp + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove();calcularmonto();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");

                        });
                     calcularmonto();
                    } else {
                        alert('Presupuesto Pendiente.!!');
                    }

                }



            } else {
                alert('Datos no encontrados..');
            }
        
        }
    });
}
function MostrarSucursalesRemision() {
//    alert("llega al usuario")
    user = {
        "opcion": 7
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#_Idsucursal_Nre").val(value.id_sucursal);
                $("#_sucursal_Nre").val(value.suc_descripcion);
            });
        },
        error: function () {
        }
    });
}
function MostrarArticulosRemision() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaArticulosRemision").append($("<tr>").append($(
                        "<td style=display:none>" + value.id_articulo + "</td>" +
                        "<td>" + value.codigenerico + "</td>" +
                        "<td>" + value.art_descripcion + "</td>" +
                        "<td>" + value.preccompras + "</td>")));
            });
        }
    });
}


function  InsertarNotaRemision() {

    var dato = "";
    $('#miTablaDetNotaRemision').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiArti").focus();
    } else {
        if ($('#_Idfactcompra_Nre').val() === "" || $('#nroremision').val() === "" 
                || $('#_obse_Nre').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
        } else {
            var opcion = confirm('Desea Guardar Nota Remision..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 9,
                    "Nre_nro": $('#nroremision').val(),
                    "Nre_obse": $('#_obse_Nre').val(),
                    "Nre_factcompra": $('#idfactura').val(),
                    "Nre_usuario": $('#idusersession_v').val()

                };
                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        InsertarDetNotaRemision();
                        alert("Nota Remision guardado correctamente.!!");
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

function  InsertarDetNotaRemision() {
    $('#miTablaDetNotaRemision').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 10,
            "codigoRD": $('#codigoNRemision').val(),
            "idartiRD": $(this).find("td").eq(0).html(),
            "precioRD": $(this).find("td").eq(2).html(),
            "cantiRD": $(this).find("td").eq(3).html()
        };
        $.ajax({
            url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
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
function  deleteNRemision() {
    datosDetalleJSON = {
        "opcion": 15,
        "codNR": $('#codigoNRemision').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
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
function MostrarNotaRemision() {
    crearJSON(11);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPlanillaRemisionN").append($("<tr>").append($(
                        "<td>" + value.id_notaremi + "</td>" +
                        "<td>" + value.fecha_notaremi + "</td>" +
                        "<td>" + value.nro_notaremi + "</td>" +
                        "<td>" + value.obser_notaremi + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.est_descripcion + "</td>")));
            });
        }
    });
}

function cambioEstadosNRemision() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesNotaRemision a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnularNR') {
                if ($('#estadoRemisionC').val() === "") {
                    alert('Seleccione una Remision compras.!');
                } else if ($('#estadoRemisionC').val() === 'CONFIRMADO') {
                    alert('La NotaRemision ya fue confirmado o ya esta Anulada..');
                } else if ($('#estadoRemisionC').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Anular la nota remision.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoNR": 2,
                            "NotaReCNro": $('#_nro_NreC').val()
                        };
                        confirmarNotaRemision();
                        alert('Nota Remision Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmarNR') {
                if ($('#estadoRemisionC').val() === "") {
                    alert('Seleccione una nota remison.!');
                } else if ($('#estadoRemisionC').val() === 'CONFIRMADO') {
                    alert('La nota remision ya fué Confirmado o esta Anulada..');
                } else if ($('#estadoRemisionC').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar la nota remision.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoNR": 1,
                            "NotaReCNro": $('#_nro_NreC').val()
                        };
                        confirmarNotaRemision();
                        alert('Nota Remision Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertirNR') {
                if ($('#estadoRemisionC').val() === "") {
                    alert('Seleccione una nota remision de Compras.!');
                } else if ($('#estadoRemisionC').val() === 'PENDIENTE') {
                    alert('La nota remision no se puede Revertir..');
                } else if ($('#estadoRemisionC').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir la nota remision.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 12,
                            "CambioEstadoNR": 3,
                            "NotaReCNro": $('#_nro_NreC').val()
                        };
                        confirmarNotaRemision();
                        alert('La nota remision ha vuelto a su estado de Origen.!!');
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

function confirmarNotaRemision() {
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPlanillaRemisionN').find('tbody').find('tr').empty();
            MostrarNotaRemision();
        },
        error: function () {
        }
    });
}


function controlBotonesNotaRemision() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesNotaRemision a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnModificarNR' && $('#estadoRemisionC').val() === 'CONFIRMADO' || $('#estadoRemisionC').val() === 'ANULADO') {
//                $("#btnGuardar").attr("disabled", true);
                document.getElementById("btnGuardarNR").style.display = 'none';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardarNR").style.display = 'none';
                document.getElementById("btnGuardarModificado").style.display = '';
            }
        });
    });
}
function vaciarCamposNuevoRemision() {
    $("#_fecha_Nre").val(null);
    $("#_nro_Nre").val(null);
    $("#_obse_Nre").val(null);
    $("#_Idestado_Nre").val(null);
    $("#_Idusuario_Nre").val(null);
    $("#_usuario_Nre").val(null);
    $("#_Idproveedor_Nre").val(null);
    $("#_proveedor_Nre").val(null);
    $("#_Idsucursal_Nre").val(null);
    $("#_sucursal_Nre").val(null);
    $("#_Idfactcompra_Nre").val(null);
    $('#miTablaDetNotaRemision').find('tbody').find('tr').empty();
    document.getElementById('btnGuardarModificado').style.display = "none";
    document.getElementById('btnGuardarNR').style.display = "";
}
function recuperarNotaRemision() {
    if ($('#estadoRemisionC').val() === "CONFIRMADO" || $('#estadoRemisionC').val() === "ANULADO") {
        $('#ventanaNotaRemision').modal('show');
        document.getElementById('btnGuardarModificado').style.display = "none";
        document.getElementById('btnGuardarNR').style.display = "none";
        recuperarDetNotaRemision();
        ///////DESBLOBLOQUEA LOS CAMPOS//////
        $("#_fecha_Nre").prop('disabled', true);
        $("#_nro_Nre").prop('disabled', true);
        $("#_obse_Nre").prop('disabled', true);

        $("#_estado_Nre").prop('disabled', true);
        $("#_usuario_Nre").prop('disabled', true);
        $("#_proveedor_Nre").prop('disabled', true);
        $("#_sucursal_Nre").prop('disabled', true);
        $("#_Idfactcompra_Nre").prop('disabled', true);

    } else {
        $('#ventanaNotaRemision').modal('show');
        document.getElementById('btnGuardarModificado').style.display = "";
        document.getElementById('btnGuardarNR').style.display = "none";
        recuperarDetNotaRemision();
        ///////DESBLOBLOQUEA LOS CAMPOS//////
        $("#_fecha_Nre").prop('disabled', false);
        $("#_nro_Nre").prop('disabled', false);
        $("#_obse_Nre").prop('disabled', false);

        $("#_estado_Nre").prop('disabled', false);
        $("#_usuario_Nre").prop('disabled', false);
        $("#_proveedor_Nre").prop('disabled', false);
        $("#_sucursal_Nre").prop('disabled', false);
        $("#_Idfactcompra_Nre").prop('disabled', false);
    }
}
var ind=0;
function recuperarDetNotaRemision() {
    if($('#estadoRemisionC').val() === 'PENDIENTE'){
        $('#btnmodificarRemision').show();
        $('#btnguardarRemisión').hide();
        $('#ventanaNotaRemision').modal('show');
  
    $('#miTablaDetNotaRemision').find('tbody').find('tr').empty();
    dsatos = {
        "opcion": 13,
        "nroNotaRe": $('#_nro_NreC').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
        type: 'POST',
        data: dsatos,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, value) {
                    $("#codigoNRemision").val(value.id_notaremi);
                    $("#_fecha_Nre").val(value.fecha_notaremi);
                    $("#_Idfactcompra_Nre").val(value.co_nrofact);
                    $("#idfactura").val(value.id_compra);
                    $("#nroremision").val(value.nro_notaremi);
                    $("#_obse_Nre").val(value.obser_notaremi);

                    subtotal = value.precionotaremi * value.cantinotaremi;
                    $('#miTablaDetNotaRemision').append("<tr id=\'prod" + ind + "\'>\
                                    <td>" + value.id_articulo + "</td>\n\
                                    <td>" + value.art_descripcion + "</td>\n\
                                    <td>" + value.precionotaremi + "</td>\n\
                                    <td>" + value.cantinotaremi + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + ind + "\').remove();calcularmonto();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");
                });
            } else {
                alert('Datos no encontrados..');
            }
            calcularmonto();
        }
    });
     }else{
        alert('DEBES SELECCIONAR UN REGISTRO O ESTA CONFIRMADO');
    }

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

function fechaactualRemision() {
    var fecha = new Date();
    $('#_fecha_Nre').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------

function abrirproveedoresRemision() {
    if ($('#NotaRemiProvee').val() === "") {
        $('#ModalProveedorRemision').modal('show');
        $('#miTablaProveedoresRemision').find('tbody').find('tr').empty();
        MostrarModalProveedoresRemision();
    } else {
    }
}//----------


function seleccionarProveedoresRemision() {
    $('#miTablaProveedoresRemision tr').click(function () {
        $('#_Idproveedor_Nre').val($(this).find("td").eq(0).html());
        $('#_proveedor_Nre').val($(this).find("td").eq(1).html());
        $('#_Idfactcompra_Nre').focus();
        $('#ModalProveedorRemision').modal('hide');
    });
}//----------
function buscadorTablaProveedoresRemision() {
    var tableReg = document.getElementById('miTablaProveedoresRemision');
    var searchText = document.getElementById('filtrarProveedorRemision').value.toLowerCase();
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

function AbrirFacturasComprasRemision() {
    if ($('#_Idfactcompra_Nre').val() === "") {
        $('#ModalFacturasComprasRemision').modal('show');
        $('#miTablaFacturasComprasRemision').find('tbody').find('tr').empty();
        MostrarFacturasComprasRemision();
    } else {
    }
}//---------------
function seleccionarFaccturasComprasRemision() {
    $('#miTablaFacturasComprasRemision tr').click(function () {
        $('#_Idfactcompra_Nre').val($(this).find("td").eq(0).html());
        $('#_Idfactcompra_Nre').focus();
        $('#ModalFacturasComprasRemision').modal('hide');
    });
}//---------------

function buscadorTablaFacturasComprasRemision() {
    var tableReg = document.getElementById('miTablaFacturasComprasRemision');
    var searchText = document.getElementById('filtrarFacturasComprasRemision').value.toLowerCase();
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



function  ModificarDetFacturasComprasRemision() {
    var dato = "";
    $('#miTablaDetNotaRemision').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiArti").focus();
    } else {
        if ($('#_Idfactcompra_Nre').val() === "" || $('#nroremision').val() === "" 
                || $('#_obse_Nre').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiArti").focus();
        } else {
            var opcion = confirm('Desea Guardar Nota Remision..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 14,
                    "Nre_nro": $('#nroremision').val(),
                    "Nre_obse": $('#_obse_Nre').val(),
                    "Nre_usuario": $('#idusersession_v').val(),
                    "Nre_factcompra": $('#idfactura').val(),
                    "Nre_notaremi": $('#codigoNRemision').val()

                };

                $.ajax({
                    url: "http://localhost:8084/TALLERCASAJC/NotaRemisionServlet",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deleteNRemision();
                        setTimeout(function () {
                            InsertarDetNotaRemision();
                            alert("Nota Remision guardado correctamente.!!");
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
        $('#miTablaDetNotaRemision').find('tbody').find('tr').each(function () {
            monto = parseInt($(this).find("td").eq(4).html());
            acumu = acumu + monto;
        });
        $('#total').val(acumu);
        numeroDecimal('total');
        tindex++;
    }, 1200);
}
function updatemonto(valormonto, ind) {
    var monto = $('#total').val();
    var calculo = monto - valormonto;
    $('#total').val(calculo);
    calculo = 0;
    monto = 0;
}//------------
function SeleccionarDetNotaRemision() {
    $('#miTablaDetNotaRemision tr').click(function () {
        $('#codArti').val($(this).find("td").eq(0).html());
        $('#codgenericiArti').val($(this).find("td").eq(1).html());
        $('#nombreArti').val($(this).find("td").eq(2).html());
        $('#precioArti').val($(this).find("td").eq(3).html());
        $('#cantidadArti').val(3);
        $('#cantidadArti').focus();
    });
}//------------------
function abrirModalArticulosRemision() {
    if ($('#codgenericiArti').val() === "") {
        $('#ModalArticulosRemision').modal('show');
        $('#miTablaArticulosRemion').find('tbody').find('tr').empty();
        MostrarArticulosRemision();
    } else {
    }
}

function seleccionarArticulosRemision() {
    $('#miTablaArticulosRemision tr').click(function () {
        $('#codArti').val($(this).find("td").eq(0).html());
        $('#codgenericiArti').val($(this).find("td").eq(1).html());
        $('#cantidadArti').val(1);
        $('#nombreArti').val($(this).find("td").eq(2).html());
        $('#precioArti').val($(this).find("td").eq(3).html());
        $('#cantidadArti').focus();
        $('#ModalArticulosRemision').modal('hide');
    });
}
function buscadorTablaArticulosRemision() {
    var tableReg = document.getElementById('miTablaArticulosRemision');
    var searchText = document.getElementById('filtrarArticulosRemision').value.toLowerCase();
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
function CargarArtiRemisionGrilla() {
        var ban = false;
    if ($('#codArti').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#codArti').val();
        var codigo;
        $('#miTablaDetNotaRemision').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                  agregarFilaArtiRemision();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
              agregarFilaArtiRemision();
        }
    }


}
var d = 0;
function agregarFilaArtiRemision() {
    //idmaterial
    var v_codMaterialG = $('#codgenericiArti').val();
    var v_codmaterial = $('#codArti').val();
    var v_descripcion = $('#nombreArti').val();
    var v_precio = $('#precioArti').val();
    var v_cant = $('#cantidadArti').val();
    subtotal = v_precio * v_cant;
        $('#miTablaDetNotaRemision').append("<tr id=\'prod" + d + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + d + "\').remove();calcularmonto();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");
   
    calcularmonto();
    $('#codgenericiArti').val(null);
    $('#codgenericiArti').focus;
    $('#nombreArti').val(null);
    $('#precioArti').val(null);
    $('#cantidadArti').val(null);
}//-----------------------


function seleccionarNotaRemision() {
    $('#miTablaPlanillaRemisionN tr').click(function () {
        $('#_nro_NreC').val($(this).find("td").eq(0).html());
        $('#estadoRemisionC').val($(this).find("td").eq(5).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoRemisionC').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoRemisionC').style.color = "#000000";
            document.getElementById('estadoRemisionC').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoRemisionC').style.background = "firebrick";
            document.getElementById('estadoRemisionC').style.color = "#ffffff";
        }
    });
}//---------------------------
function buscadorPlanillaRemision() {
    var tableReg = document.getElementById('miTablaPlanillaRemisionN');
    var searchText = document.getElementById('filtrarPlanillaRemisionN').value.toLowerCase();
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

function listafacturas() {
    crearJSON(11);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/FacturasComprascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                  $("#listafacturas").append("<option value= \"" + value.co_nrofact + "\"> " + value.co_fecha + "</option>");
    
            });
        }
    });
}