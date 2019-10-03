$(document).ready(function () {

//    fechaactual();
    MostrarListaAperturaCi();
//    cambioEstadoPediVE();
});

function getcodigoApertura() {
    controlBotonesNuevoAper();
    $("#timbrAper").val(null);
//    $("#montoCierre").val(0);
    $("#usuarioAper").val($('#vUser').val());
    crearJSON(1);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#codigoAper").val(resp);
            $("#timbrAper").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}
function controlBotonesNuevoAper() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesAperturaCierre a', function () {
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
function fechaactualAperturaC() {
    var fecha = new Date();
    $('#fechaAper').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
//    $('#fechaCierre').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------

function MostrarEstadoAper() {
//    alert("llega al usuario")
        user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#idestadoAper").val(value.idestado);
                $("#estadoAper").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}

function abrirCajeros() {
    if ($('#cajeroAper').val() === "") {
        $('#ModalCajeros').modal('show');
        $('#miTablaAper').find('tbody').find('tr').empty();
        MostrarModalCajeros();
    } else {
    }
}//----------

function MostrarModalCajeros() {
    crearJSON(3);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaAper").append($("<tr>").append($(
                        "<td>" + value.idusuario + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.per_descripcion + "</td>" +
                        "<td>" + value.suc_descripcion + "</td>")));
            });
        }
    });
}

function seleccionarCajeros() {
    $('#miTablaAper tr').click(function () {
        $('#idcajeroAper').val($(this).find("td").eq(0).html());
        $('#cajeroAper').val($(this).find("td").eq(1).html());
        $('#cajaAper').focus();
        $('#ModalCajeros').modal('hide');
    });
}//----------

function buscadorTablaCajero() {
    var tableReg = document.getElementById('miTablaAper');
    var searchText = document.getElementById('filtrarCajero').value.toLowerCase();
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

function abrirCaj() {
    if ($('#cajaAper').val() === "") {
        $('#ModalCaj').modal('show');
        $('#miTablaCaj').find('tbody').find('tr').empty();
        MostrarModalCaj();
    } else {
    }
}//----------

function MostrarModalCaj() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaCaj").append($("<tr>").append($(
                        "<td>" + value.idcaja + "</td>" +
                        "<td>" + value.caja_descripcion + "</td>")));
            });
        }
    });
}

function seleccionarCaj() {
    $('#miTablaCaj tr').click(function () {
        $('#idcajaAper').val($(this).find("td").eq(0).html());
        $('#cajaAper').val($(this).find("td").eq(1).html());
        $('#montoAper').focus();
        $('#ModalCaj').modal('hide');
    });
}//----------

function buscadorTablaCaj() {
    var tableReg = document.getElementById('miTablaCaj');
    var searchText = document.getElementById('filtrarCaj').value.toLowerCase();
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

function SoloNumerosAper(input) {
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

function abriraperTimbrado() {
    if ($('#timbrAper').val() === "") {
        $('#ModalTimbrado').modal('show');
        $('#miTablaTimb').find('tbody').find('tr').empty();
        MostrarModalTimbr();
    } else {
    }
}//----------

function MostrarModalTimbr() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaTimb").append($("<tr>").append($(
                        "<td>" + value.idtimbrado + "</td>" +
                        "<td>" + value.tim_numero + "</td>" +
                        "<td>" + value.descri_estado + "</td>" +
                        "<td>" + value.tim_fechavence + "</td>")));
            });
        }
    });
}

function seleccionarTimb() {
    $('#miTablaTimb tr').click(function () {
        $('#idtimbAper').val($(this).find("td").eq(0).html());
        $('#timbrAper').val($(this).find("td").eq(1).html());
        $('#cajeroAper').focus();
        $('#ModalTimbrado').modal('hide');
    });
}//----------

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "AperCod": $('#codigoAper').val(),
        "AperMonto": $('#montoAper').val(),
        "AperFecha": $('#fechaAper').val(),
        "AperCaja": $('#idcajaAper').val(),
        "AperUsua": $('#CodvUser').val(),
        "AperEsta": $('#idestadoAper').val(),
        "CierMont": $('#montoCierre').val(),
        "CierreFecha": $('#fechaCierre').val(),
        "AperCajero": $('#cajeroAper').val()
    };
}

function  InsertAperturaCierres() {
        if ($('#montoAper').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#montoAper").focus();
        } else {
            var opcion = confirm('Desea Guardar Apertura cierre..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 5,
                    "AperCod": $('#codigoAper').val(),
                    "AperMonto": $('#montoAper').val().replace(/\./g, ''),
                    "AperFecha": $('#fechaAper').val(),
                    "AperCaja": $('#idcajaAper').val(),
                    "AperUsua": $('#CodvUser').val(),
                    "AperEsta": $('#idestadoAper').val(),
                    "AperDepos": $('#Coddepo').val(),
                    "AperTimbr": $('#idtimbAper').val(),
                    "AperCajero": $('#cajeroAper').val(),
//                    "CierMont": $('#montoCierre').val(),
//                    "CierreFecha": $('#fechaCierre').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        alert("Apertura guardado correctamente.!!");
                        window.location.reload();
                    },
                    error: function () {
                    }
                });
            } else {

            }
        }   
}

function MostrarListaAperturaCi() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaApertCi").append($("<tr>").append($("<td>" + value.idapercierre + "</td>" +
                        "<td>" + value.aper_monto + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.cajero + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}

function Most() {
//    alert("llega al usuario")
        user = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
        data: user,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#idestadoAper").val(value.idestado);
                $("#estadoAper").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}
function MostrarListaApertCierre() {
//    controlBotonPediVeen();
    if ($('#apertNro').val() === "") {
        alert('Seleecione un pedido para visualizar..');
    } else {
        $('#ventanaAperCierre').modal('show');
        datosAJSON = {
            "opcion": 8,
            "nroApert": $('#apertNro').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/aperturacierrecontrol",
            type: 'POST',
            data: datosAJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#fechaAper").val(value.fecha_apertura);
                        $("#estadoAper").val(value.descri_estado);
                        $("#usuarioAper").val(value.usu_nombre);
                        $("#timbrAper").val(value.tim_numero);
                        $("#cajeroAper").val(value.cajero);
                        $("#cajaAper").val(value.caja_descripcion);
                        $("#montoAper").val(value.aper_monto);

                        ///////BLOQUE LOS CAMPOS//////
//                        $("#fechaAper").prop('disabled', true);
//                        $("#estadoAper").prop('disabled', true);
//                        $("#usuarioAper").prop('disabled', true);
//                        $("#timbrAper").prop('disabled', true);
//                        $("#cajeroAper").prop('disabled', true);
//                        $("#cajaAper").prop('disabled', true);
//                        $("#montoAper").prop('disabled', true);
                    });
                    $('#codigoAper').val($('#apertNro').val());
                } else {
                    alert('Datos no encontrados..');
                }
            }
        });


    }
}
function recuperarDetallePediVe() {
    controlBotonPediVeen();
    if ($('#nropediVenta').val() === "") {
        alert('Seleecione un pedido para visualizar..');
    } else {
        $('#ventanaPedidoVenta').modal('show');
        $('#miTablaDetalleMercaderiaPediVe').find('tbody').find('tr').empty();
        datosDetalleJSON = {
            "opcion": 9,
            "nropedidov": $('#nropediVenta').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#PVfecha").val(value.pedi_fecha);
                        $("#PVestad").val(value.descri_estado);
                        $("#PVusuar").val(value.usu_nombre);
                        $("#PVclient").val(value.cli_razonsocial);
                        $("#PVobserv").val(value.obsevacion_pven);
                        $("#PVtotal").val(value.pedi_total);

                        ///////BLOQUE LOS CAMPOS//////
                        $("#PVfecha").prop('disabled', true);
                        $("#PVestad").prop('disabled', true);
                        $("#PVusuar").prop('disabled', true);
                        $("#PVclient").prop('disabled', true);
                        //$("#PVobserv").prop('disabled', true);
                        $("#PVtotal").prop('disabled', true);
                        subtotal = value.detpedi_precio * value.detpedi_cant;
                        $('#miTablaDetalleMercaderiaPediVe').append("<tr id=\'prod" + tindex + "\'>\
                                    <td style=display:none>" + value.idmercaderia + "</td>\n\
                                    <td>" + value.codigogenerico + "</td>\n\
                                    <td>" + value.mer_descripcion + "</td>\n\
                                    <td>" + value.detpedi_precio + "</td>\n\
                                    <td>" + value.detpedi_cant + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><img onclick=\"$(\'#prod" + tindex + "\').remove();calcularmontopediVe();;\n\
                                    \" src='Recursos/img/delete.png' width=14 height=14/></td></tr>");
                    });
                    $('#PVcodigo').val($('#nropediVenta').val());
                } else {
                    alert('Datos no encontrados..');
                }
                calcularmontopediVe();
            }
        });


    }
}
function controlBotonApertura() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPedidoVe a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnRecuperar' && $('#estpediVenta').val() === 'CONFIRMADO' || $('#estpediVenta').val() === 'ANULADO') {
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
function seleccionarListaAper() {
    $('#miTablaApertCi tr').click(function () {
        $('#apertNro').val($(this).find("td").eq(0).html());
        $('#estadApert').val($(this).find("td").eq(4).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadApert').val();
        if (estado === 'ABIERTO') {
            document.getElementById('estadApert').style.color = "#000000";
            document.getElementById('estadApert').style.background = "#4F6";
        }
        if (estado === 'CERRADO') {
            document.getElementById('estadApert').style.background = "firebrick";
            document.getElementById('estadApert').style.color = "#ffffff";
        }
    });
}


