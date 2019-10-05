$(document).ready(function () {
//    $(":text").val("");
    cambioEstadoNotaRemi();
    mostrarplanillaNotaRemi();
});
function limpiarcampoNotaremi(){
   window.location.reload();
}

function getcodigoNotaRemi() {// funciones de transaccion
    controlBotonNuevoRemi();
    $("#observaRemi").val(null);
    crearJSON(1);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigoRemi").val(resp);
            $("#proveeRemi").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
function controlBotonNuevoRemi() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesRemi a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estadoRemi').val() === 'CONFIRMADO' || $('#estadoRemi').val() === 'ANULADO') {               
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}

function fechaactualRemi() {// funciones muestra Fecha actual
    var fecha = new Date();
    $('#fechaRemi').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------

function mostrarEstadoRemi() {// funciones de transaccion
//    alert("llega al usuario")
    user = {
        "opcion":2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#idestadRemi").val(value.idestado);
                $("#estadoRemision").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}
function mostrarUsuarioRemi() {// funciones de transaccion
//    alert("llega al usuario")
    user = {
        "opcion": 3
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#idusuaRemi").val(value.idusuario);
                $("#usuarioRemi").val(value.usu_nombre);
            });
        },
        error: function () {
        }
    });
}
function abrirproveedorRemi() {// funciones secundarios validaciones creadas
    if ($('#proveeRemi').val() === "") {
        $('#ModalProveeRemi').modal('show');
        $('#miTablaProveedoresRemi').find('tbody').find('tr').empty();
        mostrarProveeRemi();
    } else {
    }
}//----------
function mostrarProveeRemi() {// funciones de transaccion
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaProveedoresRemi").append($("<tr>").append($(
                        "<td>" + value.id_prov + "</td>" +
                        "<td>" + value.prov_nombre + "</td>")));
            });
        }
    });
}
function seleccionarProveedorRemi() {// funciones secundarios validaciones creadas
    $('#miTablaProveedoresRemi tr').click(function () {
        $('#idproveedorRemi').val($(this).find("td").eq(0).html());
        $('#proveeRemi').val($(this).find("td").eq(1).html());
        $('#facturacompRemi').focus();
        $('#ModalProveeRemi').modal('hide');
    });
}//----------
function buscadorTablaProveeRemi() {// funciones secundarios validaciones creadas
    var tableReg = document.getElementById('miTablaProveedoresRemi');
    var searchText = document.getElementById('filtrarProveedorRemi').value.toLowerCase();
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
function AbrirFacturaRemi() {// funciones secundarios validaciones creadas
    if ($('#facturacompRemi').val() === "") {
        $('#ModalFacturaRemi').modal('show');
        $('#miTablaFacturaRemi').find('tbody').find('tr').empty();
        mostrarFacturaNota();
    } else {
    }
}//---------------
function mostrarFacturaNota(){// funciones de transaccion   
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaFacturaRemi").append($("<tr>").append($(
                        "<td>" + value.idcompra + "</td>" +
                        "<td>" + value.comp_fecha + "</td>" +
                        "<td>" + value.prov_nombre + "</td>" +
                        "<td>" + value.comp_nrofact + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function seleccionarFacturaRemi() {// funciones secundarios validaciones creadas
    $('#miTablaFacturaRemi tr').click(function () {
        $('#idfacturacompRemi').val($(this).find("td").eq(0).html());
        $('#facturacompRemi').val($(this).find("td").eq(3).html());
        $('#facturacompRemi').focus();
        $('#ModalFacturaRemi').modal('hide');
    });
}//----------
function buscadorTablaFacturaRemi() {// funciones secundarios validaciones creadas
    var tableReg = document.getElementById('miTablaFacturaRemi');
    var searchText = document.getElementById('filtrarFacturaRemi').value.toLowerCase();
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
function RecuperarDetalleFacturaRemi() {   // funciones de transaccion   
    datosDetalleJSON = {
        "opcion": 6,
        "nroFacturaRemi": $('#idfacturacompRemi').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                    //alert(resp);
                $.each(resp, function (indice, value) {
                    $('#miTablaDetalleNotaRemision').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.detfact_cantidad + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");                   
                });
            } else {
                alert('Datos no encontrados..');
                $("#observaRemi").focus();
            }
        }
    });
}
var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;

function calcularmonto() {
    monto = 0;
    acumu = 0;
    $('#miTablaDetalleNotaRemision').find('tbody').find('tr').each(function () {
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
function abrirModalMercaderiaRemi() {
    if ($('#codgenericiMerca').val() === "") {
        $('#ModalMercaderia').modal('show');
        $('#miTablaMercaderiaRemi').find('tbody').find('tr').empty();
        MostrarMercaderia();
    } else {
    }
}
function MostrarMercaderia() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMercaderiaRemi").append($("<tr>").append($(
                        "<td style=display:none>" + value.idmercaderia + "</td>" +
                        "<td>" + value.codigogenerico + "</td>" +
                        "<td>" + value.mer_descripcion + "</td>" +
                        "<td>" + value.mer_precio + "</td>")));
            });
        }
    });
}
function seleccionarMercaderiaRemi() {
    $('#miTablaMercaderiaRemi tr').click(function () {
        $('#idmercad').val($(this).find("td").eq(0).html());
        $('#codgenericiMerca').val($(this).find("td").eq(1).html());
        $('#cantidadMerca').val(1);
        $('#nombreMerca').val($(this).find("td").eq(2).html());
        $('#precioMerca').val($(this).find("td").eq(3).html());
        $('#cantidadMerca').focus();
        $('#ModalMercaderia').modal('hide');
    });
}
function buscadorTablaMercaderiaRemi() {
    var tableReg = document.getElementById('miTablaMercaderiaRemi');
    var searchText = document.getElementById('filtrarMercaderiaRemi').value.toLowerCase();
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
function CargarMercaRemiGrilla() {
    var cod = $('#codgenericiMerca').val();
    var codigo;
    $('#miTablaDetalleNotaRemision').find('tbody').find('tr').each(function () {
        codigo = $(this).find("td").eq(1).html();
        if (cod === codigo) {
            alert('La mercaderia ya fue cargada, desea sustituirlo?');
            $(this).closest("tr").remove();
        }
    });
    agregarFilaMercaRemi();
}
var ind=0;
function agregarFilaMercaRemi() {
    //idmaterial
    var v_codMaterialG = $('#codgenericiMerca').val();
    var v_codmaterial = $('#idmercad').val();
    var v_descripcion = $('#nombreMerca').val();
    var v_precio = $('#precioMerca').val();
    var v_cant = $('#cantidadMerca').val();
    $('#miTablaDetalleNotaRemision').append("<tr id=\'prod" + ind + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td><img onclick=\"$(\'#prod" 
            + ind + "\').remove();\" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
    
    $('#codgenericiMerca').val(null);
    $('#codgenericiMerca').focus;
    $('#nombreMerca').val(null);
    $('#precioMerca').val(null);
    $('#cantidadMerca').val(null);
}//-----------------------
function SeleccionarDetalleFacturaRemi() {
    $('#miTablaDetalleNotaRemision tr').click(function () {
        $('#codMerca').val($(this).find("td").eq(0).html());
        $('#codgenericiMerca').val($(this).find("td").eq(1).html());
        $('#nombreMerca').val($(this).find("td").eq(2).html());
        $('#cantidadMerca').val(3);
        $('#cantidadMerca').focus();
    });
}//------------------


function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoR": $('#codigoRemi').val(),
        "Remiobserv": $('#observaRemi').val(),
        "Remifecha": $('#fechaRemi').val(),
        "Remiprovee": $('#idproveedorRemi').val(),
        "Remiestad": $('#idestadRemi').val(),
        "Remiusu": $('#idusuaRemi').val(),
        "RemifactuC": $('#idfacturacompRemi').val()  
    };
}

function  insertarNotaRemi() {
    var dato = "";
    $('#miTablaDetalleNotaRemision').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiMerca").focus();
    } else {
        if ($('#observaRemi').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiMerca").focus();
        } else {
            var opcion = confirm('Desea Guardar Nota credito debito..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 8,
                    "rValor": 1,
                    "Remiobserv": $('#observaRemi').val(),
                    "Remifecha": $('#fechaRemi').val(),
                    "Remiestad": $('#idestadRemi').val(),
                    "Remiusu": $('#CodvUser').val(),
                    "RemifactuC": $('#idfacturacompRemi').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        insertarDetalleNotaRemi();
                       
                    },
                    error: function () {
                    }
                });
            } else {
            }
        }
    }
}
function  updaterNotaRemi() {
    var dato = "";
    $('#miTablaDetalleNotaRemision').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
        alert(dato);
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiMerca").focus();
    } else {
        if ($('#observaRemi').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiMerca").focus();
        } else {
            var opcion = confirm('Desea Guardar Nota credito debito..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 8,
                    "rValor": 2,
                    "Remiobserv": $('#observaRemi').val(),
                    "Remifecha": $('#fechaRemi').val(),
                    "Remiusu": $('#CodvUser').val(),
                    "RemifactuC": $('#idfacturacompRemi').val(),
                    "codNotaremision": $('#codigoRemi').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deleNotRemision();
                        setTimeout(function (){
                             insertarDetalleNotaRemi();
                        },1200);
                       ;
                       
                    },
                    error: function () {
                    }
                });
            } else {
            }
        }
    }
}
function  insertarDetalleNotaRemi() {
    $('#miTablaDetalleNotaRemision').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 9,
            "codigoR": $('#codigoRemi').val(),
            "idmercaR": $(this).find("td").eq(0).html(),
            "cantiR": $(this).find("td").eq(3).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
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
function  deleNotRemision() {
        jsondelete = {
            "opcion": 13,
            "codNRemision": $('#codigoRemi').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
            type: 'POST',
            data: jsondelete,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
}
function mostrarplanillaNotaRemi() {
    crearJSON(10);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPlanillaNotaRemi").append($("<tr>").append($("<td>" + value.idnotaremi + "</td>" +
                        "<td>" + value.fecharemi + "</td>" +
                        "<td style=display:none>" + value.prov_nombre + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.comp_nrofact + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function seleccionarNotaRemiPlanilla() {
    $('#miTablaPlanillaNotaRemi tr').click(function () {
        $('#RemiNro').val($(this).find("td").eq(0).html());
        $('#estadoRemi').val($(this).find("td").eq(5).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoRemi').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoRemi').style.color = "#000000";
            document.getElementById('estadoRemi').style.background = "#4F6";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoRemi').style.background = "#4F6AD7";
            document.getElementById('estadoRemi').style.color = "#ffffff";
        }
        if (estado === 'ANULADO') {
            document.getElementById('estadoRemi').style.background = "firebrick";
            document.getElementById('estadoRemi').style.color = "#ffffff";
        }
    });
}//---------------------------
function buscadorPlanillaNotaRemi() {
    var tableReg = document.getElementById('miTablaPlanillaNotaRemi');
    var searchText = document.getElementById('filtrarPlanillaNotaRemi').value.toLowerCase();
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
function cambioEstadoNotaRemi() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesRemi a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estadoRemi').val() === "") {
                    alert('Seleccione una Nota de Remision.!');
                } else if ($('#estadoRemi').val() === 'PENDIENTE' || $('#estadoRemi').val() === 'ANULADO') {
                    alert('La Nota aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estadoRemi').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular la Nota de Remision.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 11,
                            "CambioEstadoR": 6,
                            "NotaRemiNro": $('#RemiNro').val()
                        };
                        confirmarNotaRemi();
                        alert('Nota Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estadoRemi').val() === "") {
                    alert('Seleccione una Nota de Remision.!');
                } else if ($('#estadoRemi').val() === 'CONFIRMADO' || $('#estadoRemi').val() === 'ANULADO') {
                    alert('La Nota ya fué Confirmado o esta Anulada..');
                } else if ($('#estadoRemi').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar la Nota de Remision.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 11,
                            "CambioEstadoR": 2,
                            "NotaRemiNro": $('#RemiNro').val()
                        };
                        confirmarNotaRemi();
                        alert('Nota Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estadoRemi').val() === "") {
                    alert('Seleccione una Nota de remision.!');
                } else if ($('#estadoRemi').val() === 'PENDIENTE' || $('#estadoRemi').val() === 'ANULADO') {
                    alert('La Nota no se puede Revertir..');
                } else if ($('#estadoRemi').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir la Nota de Remision.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 11,
                            "CambioEstadoR": 1,
                            "NotaRemiNro": $('#RemiNro').val()
                        };
                        confirmarNotaRemi();
                        alert('La Nota ha vuelto a su estado de Origen.!!');
                    }
                }
            }
        });
    });
}
function confirmarNotaRemi() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPlanillaNotaRemi').find('tbody').find('tr').empty();
            mostrarplanillaNotaRemi();
        },
        error: function () {
        }
    });
}
function recuperarDetalleNotaRemi() {
    controlBotonesNotaRemi();
    if ($('#RemiNro').val() === "") {
        alert('Seleecione planilla para visualizar..');
    } else {
        $('#ventanaRemi').modal('show');
        $('#miTablaDetalleNotaRemision').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 12,
            "nroNotaRemi": $('#RemiNro').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/notaremisioncontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#codigoRemi").val(value.idnotaremi);
                        $("#observaRemi").val(value.observacionremi);
                        $("#fechaRemi").val(value.fecharemi);
                        $("#proveeRemi").val(value.prov_nombre);
                        $("#estadoRemision").val(value.descri_estado);
                        $("#usuarioRemi").val(value.usu_nombre);
                        $("#facturacompRemi").val(value.comp_nrofact);
                        $("#idfacturacompRemi").val(value.idcompra);
                        ///////BLOQUEA LOS CAMPOS//////
                        $("#observaRemi").prop('disabled', false);
                        $("#fechaRemi").prop('disabled', true);
                        $("#proveeRemi").prop('disabled', true);
                        $("#estadoRemision").prop('disabled', true);
                        $("#usuarioRemi").prop('disabled', true);
                        $("#facturacompRemi").prop('disabled', false);
                        $('#miTablaDetalleNotaRemision').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.cantidadremi + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>"); 
                    });
                    $('#codigo').val($('#nroNotaP').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#nroNotaP").focus();
                }
            }
        });
    }
}
function controlBotonesNotaRemi() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesRemi a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnModificar' && $('#estadoRemi').val() === 'CONFIRMADO' || $('#estadoRemi').val() === 'ANULADO') {
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

