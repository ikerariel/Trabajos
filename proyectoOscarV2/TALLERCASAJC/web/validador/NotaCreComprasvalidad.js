
$(document).ready(function () {
    opcionesNDC();
    alert('hola');
});

function opcionesNDC() {
    alert('HOLA');
    $('#btnNuevoNC').click(function () {
        $('#mitabladetalleNC').find('tbody').find('tr').empty();
        $('#btnguardarNC').show();
        $('#btnmodificarNC').hide();
        $('#ventanaNC').modal('show');
        var nCell = $('#mitablaNC tr').length - 1;
        if (parseInt(nCell) < 1) {
            $('#codigoNC').val("1");
        } else {
            var num;
            $('#mitablaNC').each(function () {
                num = parseInt($(this).find("td").eq(0).html());
            });
            $('#codigoNC').val(parseInt(num) + 1);
        }
    });

}






//FUNCIONES DE TRANSACCIONES----------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoN": $('#codigo').val(),
        "tiponota": $('#notatipo').val(),
        "fechanota": $('#notaFecha').val(),
        "motivonota": $('#notaMotivo').val(),
        "factunota": $('#notafactuComp').val(),
        "usunota": $('#notaIdUsuario').val(),
        "estadnota": $('#notaIdEstado').val(),
        "proveenota": $('#notaidProveedor').val()
    };
}
function getcodigoNota() {
    controlBotonesNuevo();
    $("#notatipo").val(null);
    crearJSON(1);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigo").val(resp);
            $("#notafactuComp").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
function controlBotonesNuevo() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesNota a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estadoNotaP').val() === 'CONFIRMADO' || $('#estadoNotaP').val() === 'ANULADO') {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}
function mostrarEstadoN() {
//    alert("llega al usuario")
    user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#notaIdEstado").val(value.idestado);
                $("#notaEstado").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}
function mostrarUsuarioN() {
//    alert("llega al usuario")
    user = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#notaIdUsuario").val(value.idusuario);
                $("#notaUsuario").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });
}
function mostrarProveeNota() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaProveedoresNota").append($("<tr>").append($(
                        "<td>" + value.id_prov + "</td>" +
                        "<td>" + value.prov_nombre + "</td>")));
            });
        }
    });
}
function abrirproveedorNota() {
    if ($('#notaProveedor').val() === "") {
        $('#ModalProveedorNota').modal('show');
        $('#miTablaProveedoresNota').find('tbody').find('tr').empty();
        mostrarProveeNota();
    } else {
    }
}//----------
function seleccionarProveedorNota() {
    $('#miTablaProveedoresNota tr').click(function () {
        $('#notaidProveedor').val($(this).find("td").eq(0).html());
        $('#notaProveedor').val($(this).find("td").eq(1).html());
        $('#factuCompOrdenC').focus();
        $('#ModalProveedorNota').modal('hide');
    });
}//----------
function buscadorTablaProveeNota() {
    var tableReg = document.getElementById('miTablaProveedoresNota');
    var searchText = document.getElementById('filtrarProveedorNota').value.toLowerCase();
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
function mostrarFacturaNota() {

    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaFacturaNota").append($("<tr>").append($(
                        "<td>" + value.idcompra + "</td>" +
                        "<td>" + value.comp_fecha + "</td>" +
                        "<td>" + value.prov_nombre + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
var tind = 0;
function RecuperarDetalleFacturaNota() {
    $('#miTablaDetalleNota').find('tbody').find('tr').empty();
    datosDetalleJSON = {
        "opcion": 6,
        "nroFacturaN": $('#notafactuComp').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var vorden = JSON.stringify(resp);
                var orden = JSON.parse(vorden);
                var porden = orden[0].nrocompra;
                if (porden != "") {
                    alert("La Factua Compra ya fue procesada..!!");
                } else {
                    $.each(resp, function (indice, value) {
                        subtotal = value.detfact_precio * value.detfact_cantidad;
                        $('#miTablaDetalleNota').append("<tr id=\'prod" + tind + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.detfact_precio + "</td>\n\
                                    <td>" + value.detfact_cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tind + "\').remove();calcularmonto()\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                    });
                }

            } else {
                alert('Datos no encontrados..');
                $("#notatipo").focus();
            }
            calcularmonto();
        }
    });
}
function MostrarMercaderia() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
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
function  insertarNota() {
    if ($('#notatipo').val() === "" || $('#notafactuComp').val() === "" ||
            $('#notaMotivo').val() === "") {
        alert('Algunos datos no fueron cargados correctamente..');
    } else {

        var dato = "";
        $('#miTablaDetalleNota').find('tbody').find('tr').each(function () {
            dato = $(this).find("td").eq(0).html();
        });
        if (dato === "") {
            alert('No hay detalle que guardar..!');
            $("#codgenericiMerca").focus();
        } else {
            if ($('#notatipo').val() === "") {
                alert('Debe ingresar todos los datos requeridos para la consulta..');
                $("#codgenericiMerca").focus();
            } else {
                var opcion = confirm('Desea Guardar Nota credito debito..?');
                if (opcion === true) {
                    datosCabeceraJSON = {
                        "opcion": 8,
                        "dcValor": 1,
                        "tiponota": $('#notatipo').val(),
                        "fechanota": $('#notaFecha').val(),
                        "motivonota": $('#notaMotivo').val(),
                        "factunota": $('#notafactuComp').val(),
                        "usunota": $('#CodvUser').val(),
                        "codDepo": $('#Coddepo').val()
                    };
                    $.ajax({
                        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
                        type: 'POST',
                        data: datosCabeceraJSON,
                        cache: false,
                        dataType: 'text',
                        success: function () {
                            setTimeout(function () {
                                insertarDetalleNota();
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

function  updateNCD() {
    var dato = "";
    $('#miTablaDetalleNota').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiMerca").focus();
    } else {
        if ($('#notatipo').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiMerca").focus();
        } else {
            var opcion = confirm('Desea Guardar Nota credito debito..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 8,
                    "dcValor": 2,
                    "tiponota": $('#notatipo').val(),
                    "fechanota": $('#notaFecha').val(),
                    "motivonota": $('#notaMotivo').val(),
                    "factunota": $('#notafactuComp').val(),
                    "usunota": $('#CodvUser').val(),
                    "codND": $('#codigo').val(),
                    "codDepos": $('#Coddepo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deleteNCD();
                        setTimeout(function () {
                            insertarDetalleNota();
                        }, 2000);


                    },
                    error: function () {
                    }
                });
            } else {

            }
        }
    }
}
function  insertarDetalleNota() {
    $('#miTablaDetalleNota').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 9,
            "codigoD": $('#codigo').val(),
            "idmercaD": $(this).find("td").eq(0).html(),
            "precioD": $(this).find("td").eq(3).html(),
            "cantiD": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
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
    alert("Nota credito guardado correctamente.!!");
    window.location.reload();
}


function  insertarDetalleNotaND() {
    $('#miTablaDetalleNota').find('tbody').find('tr').each(function () {
        jsonND = {
            "opcion": 14,
            "condND": $('#codigo').val(),
            "motivoND": $(this).find("td").eq(0).html(),
            "cantND": $(this).find("td").eq(2).html(),
            "precioND": $(this).find("td").eq(1).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
            type: 'POST',
            data: jsonND,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });
    alert("Nota credito guardado correctamente.!!");
    window.location.reload();
}
function  deleteNCD() {
    datosDetalleJSON = {
        "opcion": 13,
        "codNCD": $('#codigo').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
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
function mostrarplanillaNota() {
    crearJSON(10);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPlanillaNota").append($("<tr>").append($("<td>" + value.idcred_deb + "</td>" +
                        "<td>" + value.nocred_tipo + "</td>" +
                        "<td>" + value.nocred_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function cambioEstadoNota() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesNota a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estadoNotaP').val() === "") {
                    alert('Seleccione una factura compras.!');
                } else if ($('#estadoNotaP').val() === 'PENDIENTE' || $('#estadoNotaP').val() === 'ANULADO') {
                    alert('La factura aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estadoNotaP').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular la factura.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 11,
                            "CambioEstadoN": 6,
                            "NotacreNro": $('#nroNotaP').val()
                        };
                        confirmarNota();
                        alert('Factura Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estadoNotaP').val() === "") {
                    alert('Seleccione una factura compras.!');
                } else if ($('#estadoNotaP').val() === 'CONFIRMADO' || $('#estadoNotaP').val() === 'ANULADO') {
                    alert('La factura ya fué Confirmado o esta Anulada..');
                } else if ($('#estadoNotaP').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar la factura copras.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 11,
                            "CambioEstadoN": 2,
                            "NotacreNro": $('#nroNotaP').val()
                        };
                        confirmarNota();
                        alert('Factura Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estadoNotaP').val() === "") {
                    alert('Seleccione una factura de Compras.!');
                } else if ($('#estadoNotaP').val() === 'PENDIENTE' || $('#estadoNotaP').val() === 'ANULADO') {
                    alert('La factura no se puede Revertir..');
                } else if ($('#estadoNotaP').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir la factura.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 11,
                            "CambioEstadoN": 1,
                            "NotacreNro": $('#nroNotaP').val()
                        };
                        confirmarNota();
                        alert('La factura ha vuelto a su estado de Origen.!!');
                    }
                }
            }
        });
    });
}
function confirmarNota() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPlanillaNota').find('tbody').find('tr').empty();
            mostrarplanillaNota();
        },
        error: function () {
        }
    });
}
function confirmarNotaCredito() {
    if ($('#estadoNotaP').val() === "") {
        alert('Seleccione un pedido.!');
    } else {
        if ($('#estadoNotaP').val() === 'PENDIENTE') {
            var opcion = confirm('Desea confirmar el pedido.??');
            if (opcion === true) {
            }
        } else {
            if ($('#estadoNotaP').val() === 'CONFIRMADO') {
                alert('El pedido ya fue confirmado..');
            }
        }
    }
}
function controlBotonesNota() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesNota a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnModificar' && $('#estadoNotaP').val() === 'CONFIRMADO' || $('#estadoNotaP').val() === 'ANULADO') {
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
function recuperarDetalleNotaC() {
    controlBotonesNota();
    if ($('#nroNotaP').val() === "") {
        alert('Seleecione planilla para visualizar..');
    } else {
        $('#ventanaNota').modal('show');
        $('#miTablaDetalleNota').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 12,
            "nroNotaC": $('#nroNotaP').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/notacreditodebitocontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#notatipo").val(value.nocred_tipo);
                        $("#notaFecha").val(value.nocred_fecha);
                        $("#notaMotivo").val(value.nocred_motivo);
                        $("#notafactuComp").val(value.idcompra);
                        $("#notaUsuario").val(value.usu_nombre);
                        $("#notaEstado").val(value.descri_estado);
                        $("#notaProveedor").val(value.prov_nombre);
                        ///////BLOQUEA LOS CAMPOS//////
                        $("#notatipo").prop('disabled', true);
                        $("#notaFecha").prop('disabled', true);
                        $("#notaMotivo").prop('disabled', false);
                        $("#notafactuComp").prop('disabled', false);
                        $("#notaUsuario").prop('disabled', true);
                        $("#notaEstado").prop('disabled', true);
                        $("#notaProveedor").prop('disabled', true);
                        subtotal = value.cred_deb_precio * value.cred_deb_cantidad;
                        $('#miTablaDetalleNota').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.cred_deb_precio + "</td>\n\
                                    <td>" + value.cred_deb_cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                    });
                    $('#codigo').val($('#nroNotaP').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#nroNotaP").focus();
                }
                calcularmonto();
            }
        });
    }
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS-----------------------------------------------------------
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
function fechaactualNota() {
    var fecha = new Date();
    $('#notaFecha').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------
function AbrirFacturaNota() {
    if ($('#notafactuComp').val() === "") {
        $('#ModalFacturaNota').modal('show');
        $('#miTablaFacturaNota').find('tbody').find('tr').empty();
        mostrarFacturaNota();
    } else {
    }
}//---------------
function seleccionaFacturaNota() {
    $('#miTablaFacturaNota tr').click(function () {
        $('#notafactuComp').val($(this).find("td").eq(0).html());
        $('#notafactuComp').focus();
        $('#ModalFacturaNota').modal('hide');
    });
}//---------------
function buscadorTablaNota() {
    var tableReg = document.getElementById('miTablaFacturaNota');
    var searchText = document.getElementById('filtrarFacturaNota').value.toLowerCase();
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
    $('#miTablaDetalleNota').find('tbody').find('tr').each(function () {
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
function SeleccionarDetalleFacturaNota() {
    $('#miTablaDetalleNota tr').click(function () {
        $('#codMerca').val($(this).find("td").eq(0).html());
        $('#codgenericiMerca').val($(this).find("td").eq(1).html());
        $('#nombreMerca').val($(this).find("td").eq(2).html());
        $('#precioMerca').val($(this).find("td").eq(3).html());
        $('#cantidadMerca').val(3);
        $('#cantidadMerca').focus();
    });
}//------------------
function abrirModalMercaderiaNota() {
    if ($('#codgenericiMerca').val() === "") {
        $('#ModalMercaderia').modal('show');
        $('#miTablaMercaderiaCompra').find('tbody').find('tr').empty();
        MostrarMercaderia();
    } else {
    }
}
function seleccionarMercaderiaNota() {
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
function buscadorTablaMercaderiaNota() {
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
function CargarMercaNotaGrilla() {
//    var tip = $('#notatipo').val();
//    if (tip === 'DEBITO') {
//        agregafilaND();
//    } else {

    var cod = $('#codgenericiMerca').val();
    var codigo;
    $('#miTablaDetalleNota').find('tbody').find('tr').each(function () {
        codigo = $(this).find("td").eq(1).html();
        if (cod === codigo) {
            alert('La mercaderia ya fue cargada, desea sustituirlo?');
            $(this).closest("tr").remove();
        }
    });
    agregarFilaMercaNota();
//    }



}
function agregarFilaMercaNota() {
    //idmaterial
    var v_codMaterialG = $('#codgenericiMerca').val();
    var v_codmaterial = $('#codMerca').val();
    var v_descripcion = $('#nombreMerca').val();
    var v_precio = $('#precioMerca').val();
    var v_cant = $('#cantidadMerca').val();
    subtotal = v_precio * v_cant;
    $('#miTablaDetalleNota').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><img onclick=\"$(\'#prod"
            + tindex + "\');removenc();calcularmonto();\" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
    calcularmonto();
    $('#codgenericiMerca').val(null);
    $('#codgenericiMerca').focus;
    $('#nombreMerca').val(null);
    $('#precioMerca').val(null);
    $('#cantidadMerca').val(null);
}//-----------------------


function removenc() {
    $('#miTablaDetalleNota tr').click(function () {
        $(this).closest('tr').remove();

    });
}
function agregafilaND() {
    //idmaterial
    var v_descripcion = $('#nombreMerca').val();
    var v_precio = $('#precioMerca').val().replace(/\./g, '');
    var v_cant = $('#cantidadMerca').val();
    subtotal = v_precio * v_cant;
    $('#miTablaDetalleNota').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><img onclick=\"$(\'#prod"
            + tindex + "\').remove();updatemonto( " + subtotal + ", "
            + tindex + ")\" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
    calcularmonto();
    $('#nombreMerca').val(null);
    $('#nombreMerca').focus;
    $('#precioMerca').val(null);
    $('#cantidadMerca').val(null);
}//-----------------------
function seleccionarNotaPlanilla() {
    $('#miTablaPlanillaNota tr').click(function () {
        $('#nroNotaP').val($(this).find("td").eq(0).html());
        $('#estadoNotaP').val($(this).find("td").eq(4).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoNotaP').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoNotaP').style.color = "#000000";
            document.getElementById('estadoNotaP').style.background = "PaleGoldenrod";
        }
        if (estado === 'AUTORIZADO') {
            document.getElementById('estadoNotaP').style.background = "firebrick";
            document.getElementById('estadoNotaP').style.color = "#ffffff";
        }
    });
}//---------------------------
function buscadorPlanillaNota() {
    var tableReg = document.getElementById('miTablaPlanillaNota');
    var searchText = document.getElementById('filtrarPlanillaNota').value.toLowerCase();
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
