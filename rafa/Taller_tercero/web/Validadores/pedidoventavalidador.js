$(document).ready(function () {

//    fechaactual();
    allPedidoVenta();
    cambioEstadoPediVE();
});

function limpiarcampoventa(){
   window.location.reload();
}

function codigopedidoVe() {
    controlBotonesPedidoV();
    $("#PVclient").val(null);
    $("#PVusuar").val($('#vUser').val());
          datosPVJSON = {
        "opcion": 1
    };
//    crearJSON(1);
//    document.getElementById('usuario').value = document.getElementById('usenameD').value;
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
        data:datosPVJSON ,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            $("#PVcodigo").val(resp);
            $("#PVclient").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function controlBotonesPedidoV() {
    v = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPedidoVe a', function () {
            v = ($(this).attr('id'));
            if (v === 'btnNuevo' && $('#estpediVenta').val() === 'CONFIRMADO' || $('#estpediVenta').val() === 'ANULADO') {               
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            } else {
                document.getElementById("btnGuardar").style.display = '';
                document.getElementById("btnGuardarModificado").style.display = 'none';
            }
        });
    });
}

function fechaactualPediV() {
    var fecha = new Date();
    $('#PVfecha').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}

function MostrarEstadoPediV() {
//    alert("llega al usuario")
        datosPVJSON = {
        "opcion": 2
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
        data: datosPVJSON,
        type: 'POST',
        success: function (resp) {
//            alert(resp);
            $.each(resp, function (indice, value) {
                $("#idPVestad").val(value.idestado);
                $("#PVestad").val(value.descri_estado);
            });
        },
        error: function () {
        }
    });
}

function abrirpediVeCli() {
    if ($('#PVclient').val() === "") {
        $('#ModalClientePediVe').modal('show');
        $('#miTablaClientePediVe').find('tbody').find('tr').empty();
        MostrarModalClientePediVe();
    } else {
    }
}//----------

function MostrarModalClientePediVe() {
    crearJSON(3);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
        type: 'POST',
        data: datosPVJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaClientePediVe").append($("<tr>").append($(
                        "<td>" + value.idclientes + "</td>" +
                        "<td>" + value.cli_razonsocial + "</td>")));
            });
        }
    });
}

function seleccionarCliente() {
    $('#miTablaClientePediVe tr').click(function () {
        $('#idPVclient').val($(this).find("td").eq(0).html());
        $('#PVclient').val($(this).find("td").eq(1).html());
        $('#PVobserv').focus();
        $('#ModalClientePediVe').modal('hide');
    });
}//----------

function buscadorTablaClientePediV() {
    var tableReg = document.getElementById('miTablaClientePediVe');
    var searchText = document.getElementById('filtrarClientPediV').value.toLowerCase();
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

function AbrirModalMercaderiaPediVent() {
    if ($('#idmaterialGenerico').val() === "") {
        $('#ModalMercaderiaPediV').modal('show');
        $('#miTablaMercaderiaPediV').find('tbody').find('tr').empty();
        MostrarMercaderiaPediVent();
    } else {
    }
}
function MostrarMercaderiaPediVent() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
        type: 'POST',
        data: datosPVJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaMercaderiaPediV").append($("<tr>").append($(
                        "<td style=display:none>" + value.idmercaderia + "</td>" +
                        "<td>" + value.codigogenerico + "</td>" +
                        "<td>" + value.mer_descripcion + "</td>" +
                        "<td>" + value.mer_precio + "</td>")));
            });
        }
    });
}

function seleccionarMercaderiaPedidoV() {
    $('#miTablaMercaderiaPediV tr').click(function () {
        $('#idmaterial').val($(this).find("td").eq(0).html());
        $('#idmaterialGenerico').val($(this).find("td").eq(1).html());
        $('#idcantidad').val(1);
        $('#iddescripcion').val($(this).find("td").eq(2).html());
        $('#idpreci').val($(this).find("td").eq(3).html());
        $('#idcantidad').focus();
        $('#ModalMercaderiaPediV').modal('hide');
    });
}

function buscadorTablaMercaderiaPediVe() {
    var tableReg = document.getElementById('miTablaMercaderiaPediV');
    var searchText = document.getElementById('filtrarMercaderiaPedidoV').value.toLowerCase();
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

function CargarMercaderiaPediV() {
      var ban = false;
    if ($('#idmaterialGenerico').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#idmaterialGenerico').val();
        var codigo;
        $('#miTablaDetalleMercaderiaPediVe').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(1).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    agregarFilaMercaderiaPediV();
                } else {
                    ban = true;
                }

            } else {

            }
        });
        if (ban === false) {
           agregarFilaMercaderiaPediV();
        }
   } 
}

var subtotal = 0;
var tindex = 0;
var monto = 0;
var acumu = 0;

function agregarFilaMercaderiaPediV() {
    var v_codMaterialG = $('#idmaterialGenerico').val();
    var v_codmaterial = $('#idmaterial').val();
    var v_descripcion = $('#iddescripcion').val();
    var v_cant = $('#idcantidad').val();
    var v_precio = $('#idpreci').val();
    
    subtotal = v_precio * v_cant;
    
    $('#miTablaDetalleMercaderiaPediVe').append("<tr id=\'prod" + tindex + "\'>\
            <td style=display:none>" + v_codmaterial + "</td>\n\
            <td>" + v_codMaterialG + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><img onclick=\"$(\'#prod" + tindex + "\').remove();calcularmontopediVe()\" src='Recursos/img/delete.png' width=14 height=14/></td>\n\
            </tr>");
            
    $('#idmaterialGenerico').val(null);
    $('#iddescripcion').val(null);
    $('#idcantidad').val(null);
    $('#idpreci').val(null);
    $('#idmaterialGenerico').focus();    
    calcularmontopediVe();
    
}

function calcularmontopediVe() {
    monto = 0;
    acumu = 0;
    $('#miTablaDetalleMercaderiaPediVe').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(5).html());
        acumu = acumu + monto;
    });
    $('#total').val(acumu);
    numeroDecimal('total');
    $("#PVtotal").val($('#total').val());
}

function updatemonto(valormonto, ind) {
    var monto = $('#total').val();
    var calculo = monto - valormonto;
    $('#total').val(calculo);
    calculo = 0;
    monto = 0;
}

function numeroDecimal(...uno) {
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

function crearJSON(id) {
    datosPVJSON = {
        "opcion": id,
        "codigoPV": $('#PVcodigo').val(),
        "fechaPV": $('#PVfecha').val(),
        "totalPV": $('#PVtotal').val(),
        "usuarioPV": $('#CodvUser').val(),
        "clientePV": $('#idPVclient').val(),
        "observPV": $('#PVobserv').val(),
        "estadoPV": $('#idPVestad').val(),
        "depositoPV": $('#Coddepo').val()
    };
}

function  InsertarPediVentas() {
    var dato = "";
    $('#miTablaDetalleMercaderiaPediVe').find('tbody').find('tr').each(function () {
        dato = $(this).find("td").eq(0).html();
    });
    if (dato === "") {
        alert('No hay detalle que guardar..!');
        $("#idmaterialGenerico").focus();
    } else {
        if ($('#PVobserv').val() === "") {
            alert('Debe ingresar todos los datos requeridos para la consulta..');
            $("#idmaterialGenerico").focus();
        } else {
            var opcion = confirm('Desea Guardar el Pedido.?');
            if (opcion === true) {
                CabeceraJSON = {
                    "opcion": 5,
                    "fechaPV": $('#PVfecha').val(),
                    "totalPV": $('#PVtotal').val().replace(/\./g, ''),
                    "usuarioPV": $('#CodvUser').val(),
                    "clientePV": $('#idPVclient').val(),
                    "observPV": $('#PVobserv').val(),
                    "estadoPV": $('#idPVestad').val(),
                    "depositoPV": $('#Coddepo').val()
                };
                $.ajax({
                    url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
                    data: CabeceraJSON,
                    type: 'POST',
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        DetalleMercaderiaPV();
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
function  DetalleMercaderiaPV() {
    $('#miTablaDetalleMercaderiaPediVe').find('tbody').find('tr').each(function () {
        DetalleJSON = {
            "opcion": 6,
            "codigoPV": $('#PVcodigo').val(),
            "idmercaPV": $(this).find("td").eq(0).html(),
            "precioPV": $(this).find("td").eq(3).html(),
            "cantiPV": $(this).find("td").eq(4).html()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
            data: DetalleJSON,
            type: 'POST',           
            cache: false,
            dataType: 'text',
            success: function () {
            },
            error: function () {
            }
        });
    });
}
function allPedidoVenta() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
        type: 'POST',
        data: datosPVJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaPediVe").append($("<tr>").append($("<td>" + value.pedi_ven_nro + "</td>" +
                        "<td>" + value.pedi_fecha + "</td>" +
                        "<td>" + value.usu_nombre + "</td>" +
                        "<td>" + value.cli_razonsocial + "</td>" +
                        "<td>" + value.descri_estado + "</td>")));
            });
        }
    });
}

function cambioEstadoPediVE() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesPedidoVe a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnular') {
                if ($('#estpediVenta').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#estpediVenta').val() === 'PENDIENTE' || $('#estpediVenta').val() === 'ANULADO') {
                    alert('El pedido aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estpediVenta').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Anular el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 8,
                            "CDestado": 6,
                            "nroPd": $('#nropediVenta').val()
                        };
                        confirmarPedidoVe();
                        alert('Pedido Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmar') {
                if ($('#estpediVenta').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#estpediVenta').val() === 'CONFIRMADO' || $('#estpediVenta').val() === 'ANULADO') {
                    alert('El pedido ya fué Confirmado o esta Anulada..');
                } else if ($('#estpediVenta').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 8,
                            "CDestado": 2,
                            "nroPd": $('#nropediVenta').val()
                        };
                        confirmarPedidoVe();
                        alert('Pedido Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertir') {
                if ($('#estpediVenta').val() === "") {
                    alert('Seleccione un pedido.!');
                } else if ($('#estpediVenta').val() === 'PENDIENTE' || $('#estpediVenta').val() === 'ANULADO') {
                    alert('El pedido no se puede Revertir..');
                } else if ($('#estpediVenta').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir el pedido.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 8,
                            "CDestado": 1,
                            "nroPd": $('#nropediVenta').val()
                        };
                        confirmarPedidoVe();
                        alert('El pedido ha vuelto a su estado de Origen.!!');
                    }
                }


            }
        });
    });
}

function confirmarPedidoVe() {
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#miTablaPediVe').find('tbody').find('tr').empty();
            allPedidoVenta();
        },
        error: function () {

        }
    });
}

function seleccionarPediVe() {
    $('#miTablaPediVe tr').click(function () {
        $('#nropediVenta').val($(this).find("td").eq(0).html());
        $('#estpediVenta').val($(this).find("td").eq(4).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estpediVenta').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estpediVenta').style.color = "#000000";
            document.getElementById('estpediVenta').style.background = "#4F6";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estpediVenta').style.background = "#4F6AD7";
            document.getElementById('estpediVenta').style.color = "#ffffff";
        }
        if (estado === 'ANULADO') {
            document.getElementById('estpediVenta').style.background = "firebrick";
            document.getElementById('estpediVenta').style.color = "#ffffff";
        }
    });
}

function buscadorTablaPedidoVe() {
    var tableReg = document.getElementById('miTablaPediVe');
    var searchText = document.getElementById('filtrarPedidoVe').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++){
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)){
                found = true;
            }
        }if (found){
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
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

function controlBotonPediVeen() {
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

function  EliminarDetalleMercaderiaPediVe() {
        datosDetalleJSON = {
            "opcion": 10,
            "codigoPV": $('#PVcodigo').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
            type: 'POST',
            data: datosDetalleJSON,
            cache: false,
            dataType: 'text',
            success: function () {
                ModificarPedidoVentas();
                DetalleMercaderiaPV();
                alert("Pedido modificado correctamente.!!");
                window.location.reload();
            },
            error: function () {
            }
        });
}
function  ModificarPedidoVentas() {
        datosMJSON = {
            "opcion": 11,
            "codigoPV": $('#PVcodigo').val(),
            "totalPV": $('#PVtotal').val().replace(/\./g, ''),
            "observPV": $('#PVobserv').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/pedidosventacontrol",
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

