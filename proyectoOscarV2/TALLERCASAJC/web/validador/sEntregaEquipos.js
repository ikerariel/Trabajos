$(document).ready(function () {
    opcionesEntregaEquipos();
});

function opcionesEntregaEquipos() {
    getEQ();
    getOtEQ();
    $('#btnNuevoEQ').click(function () {
        $('#mitabladetalleEQ').find('tbody').find('tr').empty();
        $('#btnguardarEQ').show();
        $('#btntmodificarEQ').hide();
        $('#ventanaEQ').modal('show');
        var nCell = $('#mitablaEQ tr').length - 1;
        if (parseInt(nCell) < 1) {
            $('#codigoEQ').val("1");
        } else {
            var num;
            $('#mitablaEQ').each(function () {
                num = parseInt($(this).find("td").eq(0).html());
            });
            $('#codigoEQ').val(parseInt(num) + 1);
        }
    });
    $('#btnguardarEQ').click(function () {
        insertarEQ(1, 1, 1);
    });
    $('#btntmodificarEQ').click(function () {
        insertarEQ(1, 2, 2);
    });
    $('#btncloseEQ').click(function () {
        location.reload();
    });
    $('#btnConfirmarEQ').click(function () {
        actualizarEstadoEQ(1);
    });
    $('#btnRevertirEQ').click(function () {
        actualizarEstadoEQ(3);
    });
    $('#mitablaEQ').click(function () {
        seleccionEQ();
    });

    $('#btnInformeEQ').click(function () {
        if ($('#nroEQ').val() === "") {
            alert('Debes seleccionar un registro.');
        } else {
            var v = $('#nroEQ').val();
            var valor = 7;
            window.open(`reportesgenericos.jsp?id_entregaequipos=${v}&vCodigo=${valor}`, "_blank");
        }


    });
}


function getOtEQ() {
    json = {
        "opcion": 4
    };
    $.ajax({
        url: "/TALLERCASAJC/sEntregaEquiposSERVLET",
        type: 'POST',
        data: json,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#listOTeq").append("<option  value= \"" + value.id_ordenttabajo + "\"> " + " Fecha : " + value.fecha + "" + " Cliente : " + value.cliente + "</option>");



            });

        }
    });
}


var idx = 0;
function recuperarOTeq() {
    detallejson = {
        'opcion': 5,
        'eqCodOT': $('#nrorecOdentrabajoEQ').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sEntregaEquiposSERVLET",
        type: 'POST',
        data: detallejson,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var vOT = JSON.stringify(resp);
                var vOteq = JSON.parse(vOT);
                var nroOT = vOteq[0].nrOOT;
                if (parseInt(nroOT) != "") {
                    alert("El numero de Orden de Trabajo ya fue procesada.!!");
                } else {

                    $.each(resp, function (indice, value) {
                        $('#fechaOrdentrabajoEQ').val(value.fecha);
                        $('#clinteNombreEQ').val(value.cliente);
                        $('#idclienteEQ').val(value.id_cliente);
                        $('#obsEQ').val(value.orden);

                        $('#mitabladetalleEQ').append("<tr id=\'prod" + idx + "\'>\
            <td>" + value.id_articulo + "</td>\n\
            <td>" + value.articulo + "</td>\n\
            <td>" + value.cantidad + "</td>\n\
            <td><button disabled type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idx + "\').remove();totalPServicio()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td></tr>");
                    });

                }

            } else {
                alert('Datos no encontrados..');
            }
        }
    });



}

var indicex = 0;
function getEQ() {
    $('#mitablaOrdenTrabajo').find('tbody').find('tr').empty();
    jsonEQ = {
        'opcion': 6
    };
    $.ajax({
        url: "/TALLERCASAJC/sEntregaEquiposSERVLET",
        type: 'POST',
        data: jsonEQ,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var id = valor.id_estado;
                var color;
                if (parseInt(id) === 3) {
                    color = '#d9edf7';
                    btn = "<a class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro' onclick=\"$(\'#prod" + indicex + "\');deleteEQ()\"></a>";
                    vbtn = "<a class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' onclick=\"$(\'#prod" + indicex + "\');updateEQ()\"></a>";
                } else if (parseInt(id) === 1) {
                    color = '#acffac';
                    btn = "<a disabled='' class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro'></a>";
                    vbtn = "<a disabled='' class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' ></a>";
                }
                $("#mitablaEQ").append($("<tr>").append($(
                        "<td>" + valor.id_entregaequipos + "</td>" +
                        "<td>" + valor.fecha + "</td>" +
                        "<td>" + valor.cliente + "</td>" +
                        "<td bgcolor=" + color + ">" + valor.estado + "</td>" +
                        "<td>" + valor.usuario + "</td>" +
                        "<td style='text-align: center'> " + vbtn + "  " + btn + "</td>")));
            });


        }

    });
}

function updateEQ() {
    var eq = 0;
    $('#mitablaEQ tr').click(function () {
        eq = parseInt($(this).find("td").eq(0).html());
        openViewEQ(eq);
    });
    function openViewEQ(vCod) {
        $('#btnguardarEQ').hide();
        $('#btntmodificarEQ').show();
        $('#ventanaEQ').modal('show');
        setTimeout(function () {
            getdetalleEQ(vCod);
        }, 1200);


    }
}
function deleteEQ() {
    var nroEQ = 0;
    var est;
    $('#mitablaEQ tr').click(function () {
        nroEQ = parseInt($(this).find("td").eq(0).html());
        est = $(this).find("td").eq(3).html();
        verDPS(nroEQ, est);
    });
    function verDPS(v1, v2) {
        var sms = confirm("Desea Anular el Registro ?");
        if (sms) {
            if (v2 === "PENDIENTE") {
                updateEEQUIPO(v1, 2);

            } else {
                alert('Registro CONFIRMADO, no se puede ANULAR.!!');
            }
        }
    }


}

function actualizarEstadoEQ(estado) {
    if ($('#nroEQ').val() === "") {
        alert('Debes seleccionar un registo..');
    } else {
        var sms;
        var confir;
        var codEQ = $('#nroEQ').val();
        var codestado = $('#estadoEQ').val();
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
                    updateEEQUIPO(codEQ, estado);
                    alert('Registro  Confirmado');

                }

            }

            if (estado === 2) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    updateEEQUIPO(codEQ, estado);
                    alert('Registro  Anulado');
                }
            }
            if (estado === 3) {
                if (codestado === "PENDIENTE") {
                    alert(confir);
                } else {
                    updateEEQUIPO(codEQ, estado);
                    alert('Registro  Revertido');
                }

            }

        } else {

        }
    }

}
function updateEEQUIPO(codEQ, estado) {
    estadojson = {
        'opcion': 7,
        'eqEstado': estado,
        'eqCodEequipo': codEQ
    };
    $.ajax({
        url: "/TALLERCASAJC/sEntregaEquiposSERVLET",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function () {
            location.reload();
        }

    });


}
var dex = 0;
function getdetalleEQ(v) {
    JSONOT = {
        'opcion': 8,
        'eqCodEQ': v
    };
    $.ajax({
        url: "/TALLERCASAJC/sEntregaEquiposSERVLET",
        type: 'POST',
        data: JSONOT,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $.each(resp, function (indice, value) {
                    $('#codigoEQ').val(value.id_entregaequipos);
                    $('#fechaEQ').val(value.fecha);
                    $('#nrorecOdentrabajoEQ').val(value.id_ordenttabajo);
                    $('#fechaOrdentrabajoEQ').val(value.fechaordentrabajo);
                    $('#clinteNombreEQ').val(value.cliente);
                    $('#idclienteEQ').val(value.id_cliente);
                    $('#obsEQ').val(value.observacion);

                    $('#mitabladetalleEQ').append("<tr id=\'prod" + dex + "\'>\
            <td>" + value.id_articulo + "</td>\n\
            <td>" + value.articulo + "</td>\n\
            <td>" + value.cantidad + "</td>\n\
            <td><button disabled type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + dex + "\').remove();totalPServicio()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td></tr>");
                });
            });
        }

    });



}

function insertarEQ(op, caso, dtalle) {

    jsonEQ = {
        'opcion': op,
        'eqValor': caso,
        'eqCliente': $('#idclienteEQ').val(),
        'eqUsuario': $('#idusersession_v').val(),
        'eqObservacion': $('#obsEQ').val(),
        'eqCodEntreq': $('#codigoEQ').val(),
        'eqOrdenTrabajo': $('#nrorecOdentrabajoEQ').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sEntregaEquiposSERVLET",
        type: 'POST',
        data: jsonEQ,
        cache: false,
        dataType: 'text',
        success: function () {
            deletejson = {
                'opcion': 3,
                'eqCodEq': $('#codigoEQ').val()
            };
            $.ajax({
                url: "/TALLERCASAJC/sEntregaEquiposSERVLET",
                type: 'POST',
                data: deletejson,
                cache: false,
                dataType: 'text',
                success: function (resp) {
                    setTimeout(function () {
                        $('#mitabladetalleEQ').find('tbody').find('tr').each(function () {
                            jsonDetallePS = {
                                'opcion': 2,
                                'eqCodEntreqD': $('#codigoEQ').val(),
                                'eqDetalle': dtalle,
                                'eqArticulo': $(this).find("td").eq(0).html(),
                                'eqCantidad': $(this).find("td").eq(2).html()
                            };
                            $.ajax({
                                url: "/TALLERCASAJC/sEntregaEquiposSERVLET",
                                type: 'POST',
                                data: jsonDetallePS,
                                cache: false,
                                dataType: 'text',
                                success: function (resp) {
                                    $('#ventanaPServicio').modal('hide');
                                    location.reload();
                                }
                            });
                        });
                    }, 1200);

                }
            });

        }

    });



}

function seleccionEQ() {
    $('#mitablaEQ tr').click(function () {
        $('#nroEQ').val($(this).find("td").eq(0).html());
        $('#estadoEQ').val($(this).find("td").eq(3).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoPServicio').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoEQ').style.color = "#000000";
            document.getElementById('estadoEQ').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoEQ').style.background = "firebrick";
            document.getElementById('estadoEQ').style.color = "#ffffff";
        }
    });
}//----------------------------