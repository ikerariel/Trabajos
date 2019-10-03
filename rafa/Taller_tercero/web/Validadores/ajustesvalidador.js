
function getcodigoAjustes() {
    controlBotonesNuevoAjuste();
    $("#idmotivo").val(null);
    crearJSON(1);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ajustescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigoAjus").val(resp);
            $("#motivoAjuste").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
function controlBotonesNuevoAjuste() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesAjuste a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estadoajuste').val() === 'CONFIRMADO' || $('#estadoajuste').val() === 'ANULADO') {               
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}
function fechaactualAjuste() {
    var fecha = new Date();
    $('#fechaAjuste').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------
function MostrarEstadoAjuste() {
//    alert("llega al usuario")
        user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ajustescontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#idestadAjuste").val(value.idestado);
                $("#estadAjuste").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}
function MostrarUsuarioAjuste() {
//    alert("llega al usuario")
    user = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ajustescontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#idusuaAjuste").val(value.idusuario);
                $("#usuarioAjuste").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });
}
function abrirmotivoajust() {
    if ($('#motivoAjuste').val() === "") {
        $('#ModalAjuste').modal('show');
        $('#miTablaMotivoAjuste').find('tbody').find('tr').empty();
        MostrarModalMotivoAjuste();
    } else {
    }
}//----------
function MostrarModalMotivoAjuste() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ajustescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMotivoAjuste").append($("<tr>").append($(
                        "<td>" + value.idmot_ajus + "</td>" +
                        "<td>" + value.ajustmotiv_descri + "</td>")));
            });
        }
    });
}
function seleccionarMotivoAjuste() {
    $('#miTablaMotivoAjuste tr').click(function () {
        $('#idmotivo').val($(this).find("td").eq(0).html());
        $('#motivoAjuste').val($(this).find("td").eq(1).html());
        $('#motivoAjuste').focus();
        $('#ModalAjuste').modal('hide');
    });
}//----------
function buscadorTablaMotivoAjust() {
    var tableReg = document.getElementById('miTablaMotivoAjuste');
    var searchText = document.getElementById('filtrarMotivoA').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++){
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++){
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)){
                found = true;
            }
        }if (found){
                tableReg.rows[i].style.display = '';
            } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//--------------
function AbrirModalMercaderiaAjuste() {
    if ($('#idmercadGenerico').val() === "") {
        $('#ModalMercaderiaAjuste').modal('show');
        $('#miTablaMercadAjuste').find('tbody').find('tr').empty();
        MostrarMercaderiaAjuste();
    } else {
    }
}
function MostrarMercaderiaAjuste() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ajustescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMercadAjuste").append($("<tr>").append($(
                        "<td style=display:none>" + value.idmercaderia + "</td>" +
                        "<td>" + value.codigogenerico + "</td>" +
                        "<td>" + value.mer_descripcion + "</td>" +
                        "<td>" + value.mer_precio + "</td>")));
            });
        }
    });
}
function seleccionarMercaderiaAjuste() {
    $('#miTablaMercadAjuste tr').click(function () {
        $('#idmercad').val($(this).find("td").eq(0).html());
        $('#idmercadGenerico').val($(this).find("td").eq(1).html());
        $('#idcanti').val(1);
        $('#iddescrip').val($(this).find("td").eq(2).html());
        $('#idcanti').focus();
        $('#ModalMercaderiaAjuste').modal('hide');
    });
}
function buscadorTablaMercaderiaAjuste() {
    var tableReg = document.getElementById('miTablaMercadAjuste');
    var searchText = document.getElementById('filtrarMercaderiaAjuste').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++){
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++){
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)){
                found = true;
            }
        }if (found){
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//---------------
function CargarMercaGrillaAjuste() {
    var cod = $('#idmercadGenerico').val();
    var codigo;
    $('#miTablaDetalleAjustes').find('tbody').find('tr').each(function () {
        codigo = $(this).find("td").eq(1).html();
        if (cod === codigo) {
            alert('La mercaderia ya fue cargada, desea sustituirlo?');
            $(this).find("td").remove();
        }
    });
    agregarFilaMercaAjus();
}
function agregarFilaMercaAjus() {
    //idmaterial
    var v_codMaterialG = $('#idmercadGenerico').val();
    var v_codmaterial = $('#idmercad').val();
    var v_descripcion = $('#iddescrip').val();
    var v_precio = $('#idprecio').val();
    var v_cant = $('#idcanti').val();
    subtotal = v_precio * v_cant;
    $('#miTablaDetalleAjustes').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\" src='Recursos/img/delete.png' width=14 height=14/></td>\n\
            </tr>");
    calcularmontoAjus();
    $('#idmercadGenerico').val(null);
    $('#idmercadGenerico').focus;
    $('#iddescrip').val(null);
    $('#idcanti').val(null);
}//-----------------------
function SeleccionarDetalleAjuste() {
    $('#miTablaDetalleAjustes tr').click(function () {
        $('#idmercad').val($(this).find("td").eq(0).html());
        $('#idmercadGenerico').val($(this).find("td").eq(1).html());
        $('#iddescrip').val($(this).find("td").eq(2).html());
        $('#idcanti').val(3);
        $('#idcanti').focus();
    });
}//------------------
var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;

function calcularmontoAjus() {
    monto = 0;
    acumu = 0;
    $('#miTablaDetalleAjustes').find('tbody').find('tr').each(function () {
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
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoC": $('#codigoAjus').val(),
        "Presucompracuota": $('#fechaAjuste').val(),
        "Presucompramonto": $('#estadAjuste').val(),
        "PresucompraNfactu": $('#usuarioAjuste').val(),
        "Presucompraintervalo": $('#motivoAjuste').val()
    };
}
function  InsertarAjuste() {
    var dato = "";
    $('#miTablaDetalleAjustes').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#motivoAjuste").focus();
    } else {
        if ($('#PresuCompProvee').val() === "") {
            alert('Debe ingresar todos los datos requeridos..');
            $("#idmercadGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar Ajuste..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 6,
                    "codigoAj": $('#codigoAjus').val(),
                    "Ajustefecha": $('#fechaAjuste').val(),
                    "Ajustemotivo": $('#idmotivo').val(),
                    "Ajusteusua": $('#idusuaAjuste').val(),
                    "Ajusteestad": $('#idestadAjuste').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/ajustescontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        InsertarDetalleAjustes();
                        alert("Ajuste guardado correctamente.!!");
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
function  InsertarDetalleAjustes() {
    $('#miTablaDetalleAjustes').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 7,
            "codigoD": $('#codigoAjus').val(),
            "idmercaD": $(this).find("td").eq(0).html(),
            "cantiD": $(this).find("td").eq(3).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/ajustescontrol",
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
$(document).ready(function () {
  
    cambioEstadoAjustes();
    MostrarAjuste();
});


function MostrarAjuste() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ajustescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaAjuste").append($("<tr>").append($("<td>" + value.idajuste + "</td>" +
                        "<td>" + value.ajuste_fecha + "</td>" +
                        "<td>" + value.ajustmotiv_descri + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function seleccionarListaAjuste() {
    $('#miTablaAjuste tr').click(function () {
        $('#ajusteNro').val($(this).find("td").eq(0).html());
        $('#estadoajuste').val($(this).find("td").eq(4).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoajuste').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoajuste').style.color = "#000000";
            document.getElementById('estadoajuste').style.background = "#4F6";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoajuste').style.background = "#4F6AD7";
            document.getElementById('estadoajuste').style.color = "#ffffff";
        }
        if (estado === 'ANULADO') {
            document.getElementById('estadoajuste').style.background = "firebrick";
            document.getElementById('estadoajuste').style.color = "#ffffff";
        }
    });
}//---------------------------
function cambioEstadoAjustes() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesAjuste a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estadoajuste').val() === "") {
                    alert('Seleccione un Ajuste.!');
                } else if ($('#estadoajuste').val() === 'PENDIENTE' || $('#estadoajuste').val() === 'ANULADO') {
                    alert('El Ajuste aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estadoajuste').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular el Ajuste.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 9,
                            "CambioEstadoAjust": 6,
                            "AjustesNro": $('#ajusteNro').val()
                        };
                        confirmarAjustes();
                        alert('Ajuste Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estadoajuste').val() === "") {
                    alert('Seleccione un Ajuste.!');
                } else if ($('#estadoajuste').val() === 'CONFIRMADO' || $('#estadoajuste').val() === 'ANULADO') {
                    alert('El Ajuste ya fué Confirmado o esta Anulada..');
                } else if ($('#estadoajuste').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar el Ajuste.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 9,
                            "CambioEstadoAjust": 2,
                            "AjustesNro": $('#ajusteNro').val()
                        };
                        confirmarAjustes();
                        alert('Ajuste Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estadoajuste').val() === "") {
                    alert('Seleccione un Ajuste.!');
                } else if ($('#estadoajuste').val() === 'PENDIENTE' || $('#estadoajuste').val() === 'ANULADO') {
                    alert('El Ajuste no se puede Revertir..');
                } else if ($('#estadoajuste').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir el Ajuste.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 9,
                            "CambioEstadoAjust": 1,
                            "AjustesNro": $('#ajusteNro').val()
                        };
                        confirmarAjustes();
                        alert('El Ajuste ha vuelto a su estado de Origen.!!');
                    }
                }
            }
        });
    });
}
function confirmarAjustes() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ajustescontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaAjuste').find('tbody').find('tr').empty();
            MostrarAjuste();
        },
        error: function () {
        }
    });
}
function buscadorPlanillaAjuste() {
    var tableReg = document.getElementById('miTablaAjuste');
    var searchText = document.getElementById('filtrarPlanillaAjuste').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++){
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++){
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)){
                found = true;
            }
        }if (found){
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//---------------
function recuperaDetalleAjustes() {
    controlBotonesAjuste();
    if ($('#ajusteNro').val() === "") {
        alert('Seleecione planilla para visualizar..');
    } else {
        $('#ventanaAjuste').modal('show');
        $('#miTablaDetalleAjustes').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 10,
            "nroAjustes": $('#ajusteNro').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/ajustescontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#codigoAjus").val(value.idajuste);
                        $("#fechaAjuste").val(value.ajuste_fecha);
                        $("#estadAjuste").val(value.descri_estado);
                        $("#usuarioAjuste").val(value.usu_nombre);
                        $("#motivoAjuste").val(value.ajustmotiv_descri);
                        ///////BLOQUEA LOS CAMPOS//////
                        $("#fechaAjuste").prop('disabled', true);
                        $("#estadAjuste").prop('disabled', true);
                        $("#usuarioAjuste").prop('disabled', true);
                        $("#motivoAjuste").prop('disabled', true);
                        subtotal = value.detprescomp_precio * value.detprescomp_cantidad;
                        $('#miTablaDetalleAjustes').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.ajuste_cantidad + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>"); 
                    });
                    $('#codigoAjus').val($('#ajusteNro').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#idmercadGenerico").focus();
                }
                calcularmontoAjus();
            }
        });
    }
}
function controlBotonesAjuste() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesAjuste a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnModificar' && $('#estadoajuste').val() === 'CONFIRMADO' || $('#estadoajuste').val() === 'ANULADO') {
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


