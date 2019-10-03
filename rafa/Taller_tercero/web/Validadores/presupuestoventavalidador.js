
$(document).ready(function () {
  
    cambioEstadoPresuVenta();
    MostrarPresupuestoVentas();
});
function limpiarcampoPresuVe(){
   window.location.reload();
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codigoV": $('#presuVcodigo').val(),
        "Presuventacuota": $('#PresuVentNroCuota').val(),
        "Presuventamonto": $('#PresuVentMonto').val(),
        "PresuventaNfactu": $('#PresuCompNroFactura').val(),
        "Presuventaintervalo": $('#PresuVentIntervalo').val(),
        "Presuventafecha": $('#PresuVentFecha').val(),
        "Presuventatipo": $('#PresuVentidTipo').val(),
        "Presuventacli": $('#PresuVentIdClient').val(),
        "Presuventausua": $('#PresuVentIdUsuario').val(),
        "Presuventaestado": $('#PresuVentIdEstado').val(),
        "PresuPedidoventa": $('#PresuVentPedidoC').val()
    };
}

function getcodigoPresuVe() {
    controlBotonesNuevoPresuVe();
    $("#PresuVentPedidoV").val(null);
    $("#PresuVentUsuario").val($('#vUser').val());
    crearJSON(1);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#presuVcodigo").val(resp);
            $("#PresuVentPedidoV").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function controlBotonesNuevoPresuVe() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPresupuestoVenta a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estadoPresuVe').val() === 'CONFIRMADO' || $('#estadoPresuVe').val() === 'ANULADO') {               
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}

function fechaactualPresuVenta() {
    var fecha = new Date();
    $('#PresuVentFecha').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------

function MostrarEstadoPresuVe() {
//    alert("llega al usuario")
        user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#PresuVentIdEstado").val(value.idestado);
                $("#PresuVentEstado").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}

function abrirClientePresuVe() {
    if ($('#PresuVentClient').val() === "") {
        $('#ModalClientePresuVe').modal('show');
        $('#miTablaClientePresuVe').find('tbody').find('tr').empty();
        MostrarModalClientPresuVe();
    } else {
    }
}//----------

function MostrarModalClientPresuVe() {
    crearJSON(3);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaClientePresuVe").append($("<tr>").append($(
                        "<td>" + value.idclientes + "</td>" +
                        "<td>" + value.cli_razonsocial + "</td>")));
            });
        }
    });
}
function seleccionarClienteVe() {
    $('#miTablaClientePresuVe tr').click(function () {
        $('#PresuVentIdClient').val($(this).find("td").eq(0).html());
        $('#PresuVentClient').val($(this).find("td").eq(1).html());
        $('#PresuVentPedidoV').focus();
        $('#ModalClientePresuVe').modal('hide');
    });
}//----------
function buscadorTablaclientePresuVe() {
    var tableReg = document.getElementById('miTablaClientePresuVe');
    var searchText = document.getElementById('filtrarClientPresuVe').value.toLowerCase();
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

function AbrirPedidoVentaPresuVe() {
    if ($('#PresuVentPedidoV').val() === "") {
        $('#ModalPedidoVentaPresuVe').modal('show');
        $('#miTablaPedidoVentaPresuVe').find('tbody').find('tr').empty();
        MostrarPedidoVentPresuV();
    } else {
    }
}//---------------
function MostrarPedidoVentPresuV() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPedidoVentaPresuVe").append($("<tr>").append($("<td>" + value.pedi_ven_nro + "</td>" +
                        "<td>" + value.pedi_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td style=display:none>" + value.idclientes + "</td>" +
                        "<td style=display:none>" + value.cli_razonsocial + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
//FUNCIONES SECUNDARIOS VALIDADACIONES CREADOS------------
function seleccionarPedidoVentPresuVE() {
    $('#miTablaPedidoVentaPresuVe tr').click(function () {
        $('#PresuVentPedidoV').val($(this).find("td").eq(0).html());
        $('#PresuVentIdClient').val($(this).find("td").eq(3).html());
        $('#PresuVentClient').val($(this).find("td").eq(4).html());
        $('#PresuVentPedidoV').focus();
        $('#ModalPedidoVentaPresuVe').modal('hide');
    });
}//---------------
function buscadorTablaPedidoVentaPresuVe() {
    var tableReg = document.getElementById('miTablaPedidoVentaPresuVe');
    var searchText = document.getElementById('filtrarPedidoVentaPresuV').value.toLowerCase();
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

function RecuperarDetallePedidoVe() {    
    datosDetalleJSON = {
        "opcion": 5,
        "nroPedidoPresuV": $('#PresuVentPedidoV').val()
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                    //alert(resp);
                $.each(resp, function (indice, value) {
                    subtotal = value.detpedi_precio * value.detpedi_cant;
                    $('#miTablaDetallePresuVent').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.detpedi_precio + "</td>\n\
                                    <td>" + value.detpedi_cant + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();calcularmontoPresuVE()\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");                    
                });
            } else {
                alert('Datos no encontrados..');
                $("#codgenericiMerca").focus();
            }
            calcularmontoPresuVE();
        }
    });
}
var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;

function calcularmontoPresuVE() {
    monto = 0;
    acumu = 0;
    $('#miTablaDetallePresuVent').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(5).html());
        acumu = acumu + monto;
    });
    $('#total').val(acumu);
    numeroDecimalPresuV('total');
}
function updatemonto(valormonto, ind) {
    var monto = $('#total').val();
    var calculo = monto - valormonto;
    $('#total').val(calculo);
    calculo = 0;
    monto = 0;
}//------------

function numeroDecimalPresuV(...uno) {
    for (let numero of uno) {
        var num = document.getElementById(numero).value.replace(/\./g, '');
        if (!isNaN(num)) {
            num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
            num = num.split('').reverse().join('').replace(/^[\.]/, '');
            document.getElementById(numero).value = num;
            $('#'+numero).css('font-weight', 'bold');
        } else {
            alert('Solo se permiten numeros');
            document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');           
        }
    }
}

function AbrirtipoVentaPresuV() {
    if ($('#PresuVentTipo').val() === "") {
        $('#ModalPresuVeTipo').modal('show');
        $('#miTablatipoventPresuVe').find('tbody').find('tr').empty();
        MostrartipoventaPresuV();
    } else {
    }
}//----------
function MostrartipoventaPresuV() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablatipoventPresuVe").append($("<tr>").append($(
                        "<td>" + value.tipo_codigo + "</td>" +
                        "<td>" + value.tipo_descri + "</td>")));
            });
        }       
    });
}
function seleccionartipoventaPresuV() {
    $('#miTablatipoventPresuVe tr').click(function () {
        $('#PresuVentidTipo').val($(this).find("td").eq(0).html());
        $('#PresuVentTipo').val($(this).find("td").eq(1).html());
        $('#PresuVentIntervalo').focus();
        $('#ModalPresuVeTipo').modal('hide');
        OcultarCampoVe();
        calculomontoVe();
    });
}//----------
function OcultarCampoVe(){  
    var a = $('#PresuVentTipo').val();
    if(a === "contado"){
        $('#PresuVentNroCuota').val(1);
        $('#PresuVentIntervalo').hide();
        $('#PresuVentNroCuota').hide();
    }else{
        $('#PresuVentIntervalo').val(0);
        $('#PresuVentNroCuota').val(1);
        $('#PresuVentIntervalo').focus();
    }  
}
function calculomontoVe() {
    var valormonto = $('#PresuVentNroCuota').val();
    var monto = $('#total').val().replace(/\./g, '');
    var calculo = monto / valormonto;
    $('#PresuVentMonto').val(calculo);
    numeroDecimalPresuV('PresuVentMonto');
    valormonto = 1;
}
function controlarcampost(){
    var a = $('#PresuVentTipo').val();
    var re = isNaN(a);
  if(re===true){ //si el valor es texto
    
  }else{
      alert('SOLO TEXTO');
      $('#PresuVentTipo').val(null);
  }
}
function buscadorTablatipoventaPresuVe() {
    var tableReg = document.getElementById('miTablatipoventPresuVe');
    var searchText = document.getElementById('filtrartipoventPresuV').value.toLowerCase();
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

function AbrirModalMercaderiaPresuVe() {
    if ($('#codgenericiMerca').val() === "") {
        $('#ModalMercaderiaPresuVe').modal('show');
        $('#miTablaMercaderiaVentaPresuVe').find('tbody').find('tr').empty();
        MostrarMercaderiaventaPresuV();
    } else {
    }
}
function MostrarMercaderiaventaPresuV() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMercaderiaVentaPresuVe").append($("<tr>").append($(
                        "<td style=display:none>" + value.idmercaderia + "</td>" +
                        "<td>" + value.codigogenerico + "</td>" +
                        "<td>" + value.mer_descripcion + "</td>" +
                        "<td>" + value.mer_precio + "</td>")));
            });
        }
    });
}
function seleccionarMercaderiaVenta() {
    $('#miTablaMercaderiaVentaPresuVe tr').click(function () {
        $('#codMerca').val($(this).find("td").eq(0).html());
        $('#codgenericiMerca').val($(this).find("td").eq(1).html());
        $('#cantidadMerca').val(1);
        $('#nombreMerca').val($(this).find("td").eq(2).html());
        $('#precioMerca').val($(this).find("td").eq(3).html());
        $('#cantidadMerca').focus();
        $('#ModalMercaderiaPresuVe').modal('hide');
    });
}
function buscadorTablaMercaderiaVentaPresuVe() {
    var tableReg = document.getElementById('miTablaMercaderiaVentaPresuVe');
    var searchText = document.getElementById('filtrarMercaderiaVentaPresuV').value.toLowerCase();
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

function agregarFilaMercaVentaPresuVe() {
    //idmaterial
    var v_codMaterialG = $('#codgenericiMerca').val();
    var v_codmaterial = $('#codMerca').val();
    var v_descripcion = $('#nombreMerca').val();
    var v_precio = $('#precioMerca').val();
    var v_cant = $('#cantidadMerca').val();
    subtotal = v_precio * v_cant;
    $('#miTablaDetallePresuVent').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove();calcularmontoPresuVE()\n\
            \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>"); 
    calcularmontoPresuVE();
    $('#codgenericiMerca').val(null);
    $('#codgenericiMerca').focus();
    $('#nombreMerca').val(null);
    $('#precioMerca').val(null);
    $('#cantidadMerca').val(null);
}//-----------------------

function  InsertarVentaPresuVe() {
    var dato = "";
    $('#miTablaDetallePresuVent').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#codgenericiMerca").focus();
    } else {
        if ($('#PresuVentClient').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#codgenericiMerca").focus();
        } else {
            var opcion = confirm('Desea Guardar Presupuesto de Compras..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 8,
                    "codigoV": $('#presuVcodigo').val(),
                    "Presuventafecha": $('#PresuVentFecha').val(),
                    "Presuventacuota": $('#PresuVentNroCuota').val(),
                    "Presuventamonto": $('#PresuVentMonto').val().replace(/\./g, ''),
                    "Presuventaintervalo": $('#PresuVentIntervalo').val(),
                    "PresuPedidoventa": $('#PresuVentPedidoV').val(),                   
                    "Presuventacli": $('#PresuVentIdClient').val(),
                    "Presuventausua": $('#CodvUser').val(),
                    "Presuventaestado": $('#PresuVentIdEstado').val(),
                    "Presuventatipo": $('#PresuVentidTipo').val(),                    
                    "Presuventadepos": $('#Coddepo').val()                    
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        InsertarDetalleVentaPresuV();
                        alert("Presupuesto de Compras guardado correctamente.!!");
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
function  InsertarDetalleVentaPresuV() {
    $('#miTablaDetallePresuVent').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 9,
            "codigoP": $('#presuVcodigo').val(),
            "idmercaP": $(this).find("td").eq(0).html(),
            "precioP": $(this).find("td").eq(3).html(),
            "cantiP": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
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

function recuperaDetallePresuV() {
    controlBotonesPresuVent();
    if ($('#PresuVeNro').val() === "") {
        alert('Seleecione planilla para visualizar..');
    } else {
        $('#ventanaPresupuestoVenta').modal('show');
        $('#miTablaDetallePresuVent').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 10,
            "nroPresuVenta": $('#PresuVeNro').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#presuVcodigo").val(value.idpresupuestoventa);
                        $("#PresuVentFecha").val(value.pres_fecha);
                        $("#PresuVentEstado").val(value.descri_estado);
                        $("#PresuVentUsuario").val(value.usu_nombre);
                        $("#PresuVentClient").val(value.cli_razonsocial);
                        $("#PresuVentPedidoV").val(value.pedi_ven_nro);
                        $("#PresuVentidTipo").val(value.tipo_codigo);
                        $("#PresuVentTipo").val(value.tipo_descri);
                        $("#PresuVentIntervalo").val(value.pres_intervalo);
                        $("#PresuVentNroCuota").val(value.pres_cantcuota);
                        $("#PresuVentMonto").val(value.pres_monto);
                        ///////BLOQUEA LOS CAMPOS//////
                        $("#PresuVentFecha").prop('disabled', true);
                        $("#PresuVentEstado").prop('disabled', true);
                        $("#PresuVentUsuario").prop('disabled', true);
                        $("#PresuVentClient").prop('disabled', true);
                        $("#PresuVentPedidoV").prop('disabled', true);
                        //$("#PresuVentTipo").prop('disabled', true);
                        //$("#PresuVentIntervalo").prop('disabled', true);
                        //$("#PresuVentNroCuota").prop('disabled', true);
                        $("#PresuVentMonto").prop('disabled', true);
                        subtotal = value.detpresvent_precio * value.detpresvent_cantidad;
                        $('#miTablaDetallePresuVent').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.detpresvent_precio + "</td>\n\
                                    <td>" + value.detpresvent_cantidad + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();calcularmontoPresuVE()\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>"); 
                    });
                    $('#presuVcodigo').val($('#PresuVeNro').val());
                } else {
                    alert('Datos no encontrados..');
                    $("#codgenericiMerca").focus();
                }
                calcularmontoPresuVE();
            }
        });
    }
}

function controlBotonesPresuVent() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPresupuestoVenta a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnModificar' && $('#estadoPresuVe').val() === 'CONFIRMADO' || $('#estadoPresuVe').val() === 'ANULADO') {
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
function MostrarPresupuestoVentas() {
    crearJSON(11);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPlanillaVentaPresu").append($("<tr>").append($("<td>" + value.idpresupuestoventa + "</td>" +
                        "<td>" + value.pres_fecha + "</td>" +
                        "<td>" + value.cli_razonsocial + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.tipo_descri + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}
function seleccionarPresupuestoVe() {
    $('#miTablaPlanillaVentaPresu tr').click(function () {
        $('#PresuVeNro').val($(this).find("td").eq(0).html());
        $('#estadoPresuVe').val($(this).find("td").eq(5).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoPresuVe').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoPresuVe').style.color = "#000000";
            document.getElementById('estadoPresuVe').style.background = "#4F6";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoPresuVe').style.background = "#4F6AD7";
            document.getElementById('estadoPresuVe').style.color = "#ffffff";
        }
        if (estado === 'ANULADO') {
            document.getElementById('estadoPresuVe').style.background = "firebrick";
            document.getElementById('estadoPresuVe').style.color = "#ffffff";
        }
    });
}//---------------------------
function buscadorPlanillaVentPresu() {
    var tableReg = document.getElementById('miTablaPlanillaVentaPresu');
    var searchText = document.getElementById('filtrarPlanillaVePresu').value.toLowerCase();
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

function  EliminarDetalleMercaderiaPresuV() {
        datosDetalleJSON = {
            "opcion": 12,
            "codigoP": $('#presuVcodigo').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            dataType: 'text',
            success: function () {
                ModificarPresuVentas();
                InsertarDetalleVentaPresuV();
                alert("Presupuesto modificado correctamente.!!");
                window.location.reload();
            },
            error: function () {
            }
        });
}
function  ModificarPresuVentas() {
        datosMJSON = {
            "opcion": 13,
            "codigoP": $('#presuVcodigo').val(),
            "cancuotaP": $('#PresuVentNroCuota').val(),
            "montoP": $('#PresuVentMonto').val().replace(/\./g, ''),
            "intervaloP": $('#PresuVentIntervalo').val(),
            "tipoP": $('#PresuVentidTipo').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
            type: 'POST',
            data: datosMJSON,
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
}

function cambioEstadoPresuVenta() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPresupuestoVenta a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estadoPresuVe').val() === "") {
                    alert('Seleccione un presupuesto de compras.!');
                } else if ($('#estadoPresuVe').val() === 'PENDIENTE' || $('#estadoPresuVe').val() === 'ANULADO') {
                    alert('El presupuesto aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estadoPresuVe').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular el presupuesto.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 14,
                            "CambioEstadoPresuV": 6,
                            "PresupuestoVNro": $('#PresuVeNro').val()
                        };
                        confirmarPresupuesVenta();
                        alert('Presupuesto Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estadoPresuVe').val() === "") {
                    alert('Seleccione un presupuesto de Ventas.!');
                } else if ($('#estadoPresuVe').val() === 'CONFIRMADO' || $('#estadoPresuVe').val() === 'ANULADO') {
                    alert('El presupuesto ya fué Confirmado o esta Anulada..');
                } else if ($('#estadoPresuVe').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar el presupuesto de Ventas.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 14,
                            "CambioEstadoPresuV": 2,
                            "PresupuestoVNro": $('#PresuVeNro').val()
                        };
                        confirmarPresupuesVenta();
                        alert('Presupuesto Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estadoPresuVe').val() === "") {
                    alert('Seleccione un presupuesto de Ventas.!');
                } else if ($('#estadoPresuVe').val() === 'PENDIENTE' || $('#estadoPresuVe').val() === 'ANULADO') {
                    alert('El presupuesto no se puede Revertir..');
                } else if ($('#estadoPresuVe').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir el presupuesto.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 14,
                            "CambioEstadoPresuV": 1,
                            "PresupuestoVNro": $('#PresuVeNro').val()
                        };
                        confirmarPresupuesVenta();
                        alert('El presupuesto ha vuelto a su estado de Origen.!!');
                    }
                }
            }
        });
    });
}
function confirmarPresupuesVenta() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/presupuestoventacontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPlanillaVentaPresu').find('tbody').find('tr').empty();
            MostrarPresupuestoVentas();
        },
        error: function () {
        }
    });
}


