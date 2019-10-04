$(document).ready(function () {
    opcionesOrdenTrabajo();



});
function opcionesOrdenTrabajo() {
    getOrdenTrabajo();
     traerOTLIST();
    $('#nroCarac').html('0');
    $('#btnNuevoOrdenTrabajo').click(function () {
        $('#mitabladetalleOrdentrabajo').find('tbody').find('tr').empty();
        $('#btnguardarOrdentrabajo').show();
        $('#btntmodificarOrdentrabajo').hide();
        $('#ventanaOrdenTrabajo').modal('show');
        var nCell = $('#mitablaOrdenTrabajo tr').length - 1;
        if (parseInt(nCell) < 1) {
            $('#codigoOrdentrabajo').val("1");
        } else {
            var num;
            $('#mitablaOrdenTrabajo').each(function () {
                num = parseInt($(this).find("td").eq(0).html());
            });
            $('#codigoOrdentrabajo').val(parseInt(num) + 1);
        }



    });
    $('#obsOrdentrabajopresupuesto').dblclick(function () {
        var observ = $('#obsOrdentrabajopresupuesto').val();
        $('#ventaObservacion').modal('show');
        $('#ventaObservacion').css('padding-top', '100px');
        $('#vObservacion').html(observ);
    });

    $('#obsOrdentrabajo').keyup(function () {
        if ($('#obsOrdentrabajo').val() != "") {
            var count = $('#obsOrdentrabajo').val().length;
            if (parseInt(count) === 150) {
                $('#nroCarac').css('color', 'red');
            }
            $('#nroCarac').html(count);

        }

    });
    $('#btnguardarOrdentrabajo').click(function () {
        insetarOT(2, 1);

    });
    $('#btntmodificarOrdentrabajo').click(function () {
        insetarOT(2, 2);
        setTimeout(function () {
            alert('Registro Guardado');
            $('#ventanaOrdenTrabajo').modal('hide');
            location.reload();
        }, 1200);


    });
    $('#btncloseOrdentrabajo').click(function () {
        location.reload();

    });
    $('#mitablaOrdenTrabajo').click(function () {
        seleccionOT();

    });
    $('#btnConfirmarOrdenTrabajo').click(function () {
        actualizarestadoOT(1);

    });
    $('#btnRevertirOrdenTrabajo').click(function () {
        actualizarestadoOT(3);

    });
    $('#btnInformeOrdenTrabajo').click(function () {
        if ($('#nroOrdenTrabajo').val() === "") {
            alert('Debes seleccionar un registro.');
        } else {
            var v = $('#nroOrdenTrabajo').val();
            var valor = 6;
            window.open(`reportesgenericos.jsp?id_ordenttabajo=${v}&vCodigo=${valor}`, "_blank");
        }


    });
}


var idx = 0;
function recuperarPresupuestoOT() {
    detallejson = {
        'opcion': 1,
        'otNroPresupuesto': $('#nropresupuestoOrdentrabajo').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sOrdenTrabajoSERVLET",
        type: 'POST',
        data: detallejson,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var vDiagnostico = JSON.stringify(resp);
                var vvDiagnostico = JSON.parse(vDiagnostico);
                var nrOrecpcion = vvDiagnostico[0].nropresupuesto;
                if (parseInt(nrOrecpcion) != "") {
                    alert("El numero de PRESUPUESTO ya fue procesada.!!");
                } else {
                    var v = JSON.stringify(resp);
                    var vv = JSON.parse(v);
                    var estado = vv[0].id_estado;
                    if (parseInt(estado) === 1) {
                        $.each(resp, function (indice, value) {
                            $('#fechapresupuestoOrdentrabajo').val(value.fecha);
                            $('#clinteOrdentrabajo').val(value.cliente);
                            $('#obsOrdentrabajopresupuesto').val(value.observacion);
                        });
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


function cargaGrillaOT() {
    var ban = false;
    if ($('#fechapresupuestoOrdentrabajo').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#codigoOrdentrabajo').val();
        var codp = $('#nropresupuestoOrdentrabajo').val();
        var codigo;
        var codigop;
        $('#mitabladetalleOrdentrabajo').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            codigop = $(this).find("td").eq(2).html();
            if (cod === codigo || codp === codigop) {
                var sms = confirm('Orden de trabajo cargado en la grilla, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    vCargarGrillaOT();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            vCargarGrillaOT();
        }






    }

}
var i_dex = 0;
function vCargarGrillaOT() {
    //idmaterial
    var v_nroOT = $('#codigoOrdentrabajo').val();
    var v_fechaOT = $('#fechaOrdentrabajo').val();
    var v_nropresupuesto = $('#nropresupuestoOrdentrabajo').val();
    var v_fechapresupuesto = $('#fechapresupuestoOrdentrabajo').val();
    var v_cliente = $('#clinteOrdentrabajo').val();
    var v_fechaentrega = $('#fechaentregaOrdentrabajo').val();
    var v_obpresupuesto = $('#obsOrdentrabajopresupuesto').val();
    var v_obsOT = $('#obsOrdentrabajo').val();


    $('#mitabladetalleOrdentrabajo').append("<tr id=\'prod" + i_dex + "\'>\
            <td>" + v_nroOT + "</td>\n\
            <td>" + v_fechaOT + "</td>\n\
            <td>" + v_nropresupuesto + "</td>\n\
            <td>" + v_fechapresupuesto + "</td>\n\
            <td>" + v_cliente + "</td>\n\
            <td>" + v_fechaentrega + "</td>\n\
            <td>" + v_obpresupuesto + "</td>\n\
            <td>" + v_obsOT + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + i_dex + "\').remove();totalPServicio()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td></tr>");


    var num = parseInt(v_nroOT) + 1;
    $('#codigoOrdentrabajo').val(num);
    $('#nropresupuestoOrdentrabajo').val(null);
    $('#fechapresupuestoOrdentrabajo').val(null);
    $('#clinteOrdentrabajo').val(null);
    $('#fechaentregaOrdentrabajo').val(null);
    $('#obsOrdentrabajopresupuesto').val(null);
    $('#obsOrdentrabajo').val(null);
    $('#nropresupuestoOrdentrabajo').focus();

}




function insetarOT(op, caso) {

    JSONOT = {
        'opcion': op,
        'otCaso': caso,
        'otDiagnostico': $('#obsOrdentrabajopresupuesto').val(),
        'otUsuario': $('#idusersession_v').val(),
        'otOrden': $('#obsOrdentrabajo').val(),
        'otPresupuesto': $('#nropresupuestoOrdentrabajo').val(),
        'otfechaentrega': $('#fechaentregaOrdentrabajo').val(),
        'otCodOT': $('#codigoOrdentrabajo').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sOrdenTrabajoSERVLET",
        type: 'POST',
        data: JSONOT,
        cache: false,
        dataType: 'text',
        success: function (resp) {


        }

    });



}
function seleccionOT() {
    $('#mitablaOrdenTrabajo tr').click(function () {
        $('#nroOrdenTrabajo').val($(this).find("td").eq(0).html());
        $('#estadoOrdenTrabajo').val($(this).find("td").eq(5).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoPServicio').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoOrdenTrabajo').style.color = "#000000";
            document.getElementById('estadoOrdenTrabajo').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoOrdenTrabajo').style.background = "firebrick";
            document.getElementById('estadoOrdenTrabajo').style.color = "#ffffff";
        }
    });
}//----------------------------

var idx = 0;
function getOrdenTrabajo() {
    $('#mitablaOrdenTrabajo').find('tbody').find('tr').empty();
    jsonOTALL = {
        'opcion': 4
    };
    $.ajax({
        url: "/TALLERCASAJC/sOrdenTrabajoSERVLET",
        type: 'POST',
        data: jsonOTALL,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var id = valor.id_estado;
                var color;
                if (parseInt(id) === 3) {
                    color = '#d9edf7';
                    btn = "<a class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro' onclick=\"$(\'#prod" + idx + "\');deleteOT()\"></a>";
                    vbtn = "<a class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' onclick=\"$(\'#prod" + idx + "\');updateOT()\"></a>";
                } else if (parseInt(id) === 1) {
                    color = '#acffac';
                    btn = "<a disabled='' class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro'></a>";
                    vbtn = "<a disabled='' class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' ></a>";
                }
                $("#mitablaOrdenTrabajo").append($("<tr>").append($(
                        "<td>" + valor.id_ordenttabajo + "</td>" +
                        "<td>" + valor.fecha + "</td>" +
                        "<td>" + valor.fechaentrega + "</td>" +
                        "<td>" + valor.id_presuserv + "</td>" +
                        "<td>" + valor.usuario + "</td>" +
                        "<td bgcolor=" + color + ">" + valor.estado + "</td>" +
                        "<td style='text-align: center'> " + vbtn + "  " + btn + "</td>")));
            });


        }

    });
}

function updateOT() {
    var ot = 0;

    $('#mitablaOrdenTrabajo tr').click(function () {
        ot = parseInt($(this).find("td").eq(0).html());
        openViewOT(ot);
    });
    function openViewOT(vCod) {
        $('#btnguardarOrdentrabajo').hide();
        $('#btntmodificarOrdentrabajo').show();
        $('#ventanaOrdenTrabajo').modal('show');
        setTimeout(function () {
            getdetalleOT(vCod);
        }, 1200);


    }
}
function deleteOT() {
    var nroOT = 0;
    var est;
    $('#mitablaOrdenTrabajo tr').click(function () {
        nroOT = parseInt($(this).find("td").eq(0).html());
        est = $(this).find("td").eq(5).html();
        verDPS(nroOT, est);
    });
    function verDPS(v1, v2) {
        var sms = confirm("Desea Anular el Registro ?");
        if (sms) {
            if (v2 === "PENDIENTE") {
                updateOTRABAJO(v1, 2);

            } else {
                alert('Registro CONFIRMADO, no se puede ANULAR.!!');
            }
        }
    }


}
function getdetalleOT(v) {
    JSONOT = {
        'opcion': 3,
        'otCODIGO': v
    };
    $.ajax({
        url: "/TALLERCASAJC/sOrdenTrabajoSERVLET",
        type: 'POST',
        data: JSONOT,
        cache: false,
//        dataType: 'text',
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#codigoOrdentrabajo').val(valor.id_ordenttabajo);
                $('#fechaOrdentrabajo').val(valor.fecha);
                $('#nropresupuestoOrdentrabajo').val(valor.id_presuserv);
                $('#fechapresupuestoOrdentrabajo').val(valor.fechapresupuesto);
                $('#clinteOrdentrabajo').val(valor.cliente);
                $('#fechaentregaOrdentrabajo').val(valor.fechaentrega);
                $('#obsOrdentrabajopresupuesto').val(valor.diagnostico);
                $('#obsOrdentrabajo').val(valor.orden);
            });
        }

    });



}


function actualizarestadoOT(estado) {
    if ($('#nroOrdenTrabajo').val() === "") {
        alert('Debes seleccionar un registo..');
    } else {
        var sms;
        var confir;
        var CODOT = $('#nroOrdenTrabajo').val();
        var codestado = $('#estadoOrdenTrabajo').val();
        if (estado === 1) {
            sms = "Desea Confirmar el Registro??";
            confir = "El Registro ya esta CONFIRMADO..!";
        }
        if (estado === 2) {
            sms = "Desea Anulaar el Registro??";
            confir = "El Registro ya esta CONFIRMADO..!";
        }
        if (estado === 3) {
            sms = "Desea Revertir el Registro??";
            confir = "El Registro aún sigue sin CONFIRMAR..!";
        }

        var v_sms = confirm(sms);
        if (v_sms === true) {
            if (estado === 1) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    updateOTRABAJO(CODOT, estado);
                    alert('Registro  Confirmado');

                }

            }

            if (estado === 2) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    updateOTRABAJO(CODOT, estado);
                    alert('Registro  Anulado');
                }
            }
            if (estado === 3) {
                if (codestado === "PENDIENTE") {
                    alert(confir);
                } else {
                    updateOTRABAJO(CODOT, estado);
                    alert('Registro  Revertido');
                }

            }

        } else {

        }
    }

}

function updateOTRABAJO(codOT, estado) {
    estadojson = {
        'opcion': 5,
        'otCodEstado': estado,
        'otCodOTrabajo': codOT
    };
    $.ajax({
        url: "/TALLERCASAJC/sOrdenTrabajoSERVLET",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function () {
            location.reload();
        }

    });


}

function traerOTLIST() {
    jsonOTLIST = {
        "opcion": 4
    };
    $.ajax({
        url: "/TALLERCASAJC/sPresupuestoServiciosSERVLET",
        type: 'POST',
        data: jsonOTLIST,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                var tipoar = value.id_estado;
                if (tipoar === 1) {
                    $("#lispresupuesto").append("<option value= \"" + value.id_presuserv + "\"> " + "Fecha : " + value.fecha + "" + " / Cliente :" + value.cliente + "</option>");
                }

            });

        }
    });
}