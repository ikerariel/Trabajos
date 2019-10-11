$(document).ready(function () {
    opcionesPservicios();
    getPServicio();

});

function opcionesPservicios() {
    traerrepuestos();
    agregarComboCodPago();
    traerDiagnosticoList();
    $('#btnNuevoPServicio').click(function () {
        $('#mitabladetallePServicio').find('tbody').find('tr').empty();
        $('#btnguardarPServicio').show();
        $('#btntmodificarPServicio').hide();
        $('#ventanaPServicio').modal('show');
        var nCell = $('#mitablaPServicio tr').length - 1;
        if (parseInt(nCell) < 1) {
            $('#codigoPServicio').val("1");
        } else {
            var num;
            $('#mitablaPServicio').each(function () {
                num = parseInt($(this).find("td").eq(0).html());
            });
            $('#codigoPServicio').val(parseInt(num) + 1);
        }



    });
    $('#btnConfirmarPServicio').click(function () {
        actualizarEstadoPservicio(1);
    });
    $('#btnRevertirPServicio').click(function () {
        actualizarEstadoPservicio(3);
    });
    $('#mitablaPServicio').click(function () {
        seleccionPServicio();
    });
    $('#btnguardarPServicio').click(function () {
        insertarpSevicio(1, 1, 1);
    });
    $('#btntmodificarPServicio').click(function () {
        insertarpSevicio(1, 2, 2);
    });
    $('#btnclosePservicio').click(function () {
        location.reload();
    });
    $('#btnInformePServicio').click(function () {
        if ($('#nroPServicio').val() === "") {
            alert('Debes seleccionar un registro.');
        } else {
            var v = $('#nroPServicio').val();
            var valor = 5;
            window.open(`reportesgenericos.jsp?id_presuserv=${v}&vCodigo=${valor}`, "_blank");
        }


    });
}
function traerrepuestos() {
    jsonrepuestos = {
        "opcion": 20,
        "codDepo": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: jsonrepuestos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                var tipoar = value.id_tipoarticulo;
                if (tipoar === 2) {
                    $("#listaarticulosPS").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + "</option>");

                }

            });

        }
    });
}
function traerDiagnosticoList() {
    jsondiasgnos = {
        "opcion": 1
    };
    $.ajax({
        url: "/TALLERCASAJC/sDiagnosticoSERVLET",
        type: 'POST',
        data: jsondiasgnos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                var tipoar = value.id_estado;
                if (tipoar === 1) {
                    $("#lisdiagnostico").append("<option value= \"" + value.id_diagnostico + "\"> " + "Fecha : " + value.fecha + "" + " / Cliente :" + value.razonsocial + "</option>");
                }

            });

        }
    });
}
function traerResp() {
    art = {
        "opcion": 19,
        "codArticulo": $('#v_articusPS').val(),
        "coddepos": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#descripArtPServicio').val(value.art_descripcion);
                $('#precioPServicio').val(value.precventas);
                numeroDecimal('precioPServicio');
                $('#cantArtPServicio').val(1);
                $('#cantArtPServicio').focus();

            });

        }
    });

}

function cargaGrillaPS() {
    var ban = false;
    if ($('#v_articusPS').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#v_articusPS').val();
        var codigo;
        $('#mitabladetallePServicio').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    vCargarGrillaPS();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            vCargarGrillaPS();
        }






    }

}
var i_dex = 0;
function vCargarGrillaPS() {
    //idmaterial
    var v_codmaterial = $('#v_articusPS').val();
    var v_descripcion = $('#descripArtPServicio').val();
    var v_precio = $('#precioPServicio').val().replace(/\./g, '');
    var v_cant = $('#cantArtPServicio').val();
    subtotal = v_precio * v_cant;

    $('#mitabladetallePServicio').append("<tr id=\'prod" + i_dex + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + i_dex + "\');removep();totalPServicio()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td></tr>");
    totalPServicio();
    $('#v_articusPS').val(null);
    $('#v_articusPS').focus;
    $('#descripArtPServicio').val(null);
    $('#precioPServicio').val(null);
    $('#cantArtPServicio').val(null);
}
function removep() {
    $('#mitabladetallePServicio tr').click(function () {
       $(this).closest('tr').remove();

    });
}

function totalPServicio() {
    monto = 0;
    acumu = 0;

    $('#mitabladetallePServicio').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(4).html());
        acumu = acumu + monto;

    });
    $('#totalPServicio').val(acumu);
//    $('#montototalpventa').css({border:'transparent', align:'center'});
    $('#totalPServicio').css('font-weight:', 'bold');
    numeroDecimal('totalPServicio');
//    $('#codarticulopedidoventa').focus;
}

function seleccionPServicio() {
    $('#mitablaPServicio tr').click(function () {
        $('#nroPServicio').val($(this).find("td").eq(0).html());
        $('#estadoPServicio').val($(this).find("td").eq(3).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoPServicio').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoPServicio').style.color = "#000000";
            document.getElementById('estadoPServicio').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoPServicio').style.background = "firebrick";
            document.getElementById('estadoPServicio').style.color = "#ffffff";
        }
    });
}//----------------------------

function actualizarEstadoPservicio(estado) {
    if ($('#nroPServicio').val() === "") {
        alert('Debes seleccionar un registo..');
    } else {
        var sms;
        var confir;
        var codPservicio = $('#nroPServicio').val();
        var codestado = $('#estadoPServicio').val();
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
                    updateEstadoPservicio(codPservicio, estado);
                    alert('Registro  Confirmado');

                }

            }

            if (estado === 2) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    updateEstadoPservicio(codPservicio, estado);
                    alert('Registro  Anulado');
                }
            }
            if (estado === 3) {
                if (codestado === "PENDIENTE") {
                    alert(confir);
                } else {
                    updateEstadoPservicio(codPservicio, estado);
                    alert('Registro  Revertido');
                }

            }

        } else {

        }
    }

}

function updateEstadoPservicio(codPservicio, estado) {
    estadojson = {
        'opcion': 5,
        'psEstado': estado,
        'psNroPs': codPservicio
    };
    $.ajax({
        url: "/TALLERCASAJC/sPresupuestoServiciosSERVLET",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function () {
            location.reload();
        }

    });


}
var idx = 0;
function recuperarPServicioDiagnostico() {

    $('#mitabladetallePServicio').find('tbody').find('tr').empty();
    detallejson = {
        'opcion': 6,
        'psNroDiagnostico': $('#nroDiagnosticoPServicio').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sPresupuestoServiciosSERVLET",
        type: 'POST',
        data: detallejson,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var vDiagnostico = JSON.stringify(resp);
                var vvDiagnostico = JSON.parse(vDiagnostico);
                var nrOrecpcion = vvDiagnostico[0].nrodiagnostico;
                if (parseInt(nrOrecpcion) != "") {
                    alert("El numero de Recepción ya fue procesada.!!");
                } else {
                    var v = JSON.stringify(resp);
                    var vv = JSON.parse(v);
                    var estado = vv[0].id_estado;
                    if (parseInt(estado) === 1) {
                        $.each(resp, function (indice, value) {
                            $('#nroDiagnosticoPServicio').val(value.id_diagnostico);
                            $('#fechaPservicioDiagnostico').val(value.fecha);
                            $('#clinteNombrePsdiagnostico').val(value.cliente);
                            $('#obsPsdiagnostico').val(value.diganostico);
                        });
                    } else {
                        alert('Diagnóstico Pendiente.!!');
                    }
                }

            } else {
                alert('Datos no encontrados..');
            }
        }
    });



}

var idx = 0;
function getPServicio() {
    $('#mitablaPServicio').find('tbody').find('tr').empty();
    pServicio = {
        'opcion': 4
    };
    $.ajax({
        url: "/TALLERCASAJC/sPresupuestoServiciosSERVLET",
        type: 'POST',
        data: pServicio,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var id = valor.id_estado;
                var color;
                if (parseInt(id) === 3) {
                    color = '#d9edf7';
                    btn = "<a class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro' onclick=\"$(\'#prod" + idx + "\');deletePServicio()\"></a>";
                    vbtn = "<a class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' onclick=\"$(\'#prod" + idx + "\');updatePServicio()\"></a>";
                } else if (parseInt(id) === 1) {
                    color = '#acffac';
                    btn = "<a disabled='' class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro'></a>";
                    vbtn = "<a disabled='' class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' ></a>";
                }
                $("#mitablaPServicio").append($("<tr>").append($(
                        "<td>" + valor.id_presuserv + "</td>" +
                        "<td>" + valor.fecha + "</td>" +
                        "<td>" + valor.cliente + "</td>" +
                        "<td bgcolor=" + color + ">" + valor.estado + "</td>" +
                        "<td>" + valor.usuario + "</td>" +
                        "<td style='text-align: center'> " + vbtn + "  " + btn + "</td>")));
            });


        }

    });
}
function updatePServicio() {
    var ps = 0;
    $('#mitablaPServicio tr').click(function () {
        ps = parseInt($(this).find("td").eq(0).html());
        openViewDPS(ps);
    });
    function openViewDPS(vCod) {
        $('#btnguardarPServicio').hide();
        $('#btntmodificarPServicio').show();
        $('#ventanaPServicio').modal('show');
        $('#mitabladetallePServicio').find('tbody').find('tr').empty();
        setTimeout(function () {
            getDetallePSservicio(vCod);
        }, 1200);


    }
}
function deletePServicio() {
    var nroD = 0;
    var est;
    $('#mitablaPServicio tr').click(function () {
        nroD = parseInt($(this).find("td").eq(0).html());
        est = $(this).find("td").eq(3).html();
        verDPS(nroD, est);
    });
    function verDPS(v1, v2) {
        var sms = confirm("Desea Anular el Registro ?");
        if (sms) {
            if (v2 === "PENDIENTE") {
                updateEstadoPservicio(v1, 2);

            } else {
                alert('Registro CONFIRMADO, no se puede ANULAR.!!');
            }
        }
    }


}


var indicex = 0;
function getDetallePSservicio(cod) {
    detallejsonPS = {
        'opcion': 7,
        'psCodPServicio': cod
    };
    $.ajax({
        url: "/TALLERCASAJC/sPresupuestoServiciosSERVLET",
        type: 'POST',
        data: detallejsonPS,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#codigoPServicio').val(value.id_presuserv);
                $('#fechaPServicio').val(value.fecha);
                $('#nroDiagnosticoPServicio').val(value.id_diagnostico);
                $('#fechaPservicioDiagnostico').val(value.fechaDiagnostico);
                $('#clinteNombrePsdiagnostico').val(value.cliente);
                $('#obsPsdiagnostico').val(value.observacion);
                subtotal = value.precio * value.cantidad;
                $("#mitabladetallePServicio").append($("<tr id=\'prod" + indicex + "\'>").append($(
                        "<td>" + value.id_articulo + "</td>" +
                        "<td>" + value.articulo + "</td>" +
                        "<td>" + value.precio + "</td>" +
                        "<td>" + value.cantidad + "</td>" +
                        "<td>" + subtotal + "</td>" +
                        "<td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + indicex + "\').remove();totalPServicio()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td>")));


                totalPServicio();
            });

        }

    });
}
function insertarpSevicio(op, caso, dtalle) {

    jsonPServicio = {
        'opcion': op,
        'psValor': caso,
        'spNroDiagnostico': $('#nroDiagnosticoPServicio').val(),
        'spUsuario': $('#idusersession_v').val(),
        'spNropresupuesto': $('#codigoPServicio').val(),
        'spNcondPago': $('#cbm_condPago').val(),
        'spObservacion': $('#obsPsdiagnostico').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sPresupuestoServiciosSERVLET",
        type: 'POST',
        data: jsonPServicio,
        cache: false,
        dataType: 'text',
        success: function () {
            deletejson = {
                'opcion': 3,
                'spCodPservicio': $('#codigoPServicio').val()
            };
            $.ajax({
                url: "/TALLERCASAJC/sPresupuestoServiciosSERVLET",
                type: 'POST',
                data: deletejson,
                cache: false,
                dataType: 'text',
                success: function (resp) {
                    setTimeout(function () {
                        $('#mitabladetallePServicio').find('tbody').find('tr').each(function () {
                            jsonDetallePS = {
                                'opcion': 2,
                                'spCodigoPS': $('#codigoPServicio').val(),
                                'psDetalle': dtalle,
                                'psCodartic': $(this).find("td").eq(0).html(),
                                'psCant': $(this).find("td").eq(3).html(),
                                'psPrecio': $(this).find("td").eq(2).html()
                            };
                            $.ajax({
                                url: "/TALLERCASAJC/sPresupuestoServiciosSERVLET",
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


function agregarComboCodPago() {

    var array = ["CONTADO", "CREDITO"];
    var count = 0;
    for (var i in array) {
        count++;
        document.getElementById("cbm_condPago").innerHTML += "<option value='" + count + "'>" + array[i] + "</option>";
    }

}