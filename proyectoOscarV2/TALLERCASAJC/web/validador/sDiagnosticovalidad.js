$(document).ready(function () {
    getDiagnosticos();
    opcionesDiagnostico();


});

function opcionesDiagnostico() {
    traerarticulosD();
    traerRecepcionList();
    $("#mitabladetallediagnostico").prop('disabled', true);
    $('#btnNuevoDiagnostico').click(function () {
        $('#nrorecpecionDiagnostico').val(null);
        $('#clinteNombrediagnostico').val(null);
        $('#obsDiagnostico').val(null);
        $('#nrorecpecionDiagnostico').val(null);
        $('#fecharecpe').val(null);
        $('#clinteNombrediagnostico').val(null);
        $('#v_articulosdiagnostico').val(null);
        $('#obsDiagnostico').val(null);
        $('#mitabladetallediagnostico').find('tbody').find('tr').empty();
        $('#btnguardarDiagnostico').show();
        $('#btntmodificarDiagnostico').hide();
        $('#ventanaDiagnostico').modal('show');
        var nCell = $('#mitablaDiagnostico tr').length - 1;
        if (parseInt(nCell) < 1) {
            $('#codigoDiagnostico').val("1");
        } else {
            var num;
            $('#mitablaDiagnostico').each(function () {
                num = parseInt($(this).find("td").eq(0).html());
            });
            $('#codigoDiagnostico').val(parseInt(num) + 1);
        }



    });
    $('#btnguardarDiagnostico').click(function () {

        insertardiagonostico(2, 1, 1);
    });
    $('#btntmodificarDiagnostico').click(function () {
        insertardiagonostico(2, 2, 2);
    });
    $('#btncloseDiagnostico').click(function () {
        location.reload();
    });

    $('#btnConfirmarDiagnostico').click(function () {
        actualizarEstadoDiagnostico(1);
    });
    $('#btnRevertirDiagnostico').click(function () {
        actualizarEstadoDiagnostico(3);
    });
    $('#mitablaDiagnostico').click(function () {
        seleccionDiagnostico();
    });
    $('#filtroDiagnostico').keyup(function () {
        buscardortabla('mitablaDiagnostico', 'filtroDiagnostico');
    });
    $('#btnInformeDiagnostico').click(function () {
        if ($('#nroDiagnostico').val() === "") {
            alert('Debes seleccionar un registro.');
        } else {
            var v = $('#nroDiagnostico').val();
            var valor = 4;
            window.open(`reportesgenericos.jsp?id_diagnostico=${v}&vCodigo=${valor}`, "_blank");
        }


    });


}


function insertardiagonostico(op, caso, dtalle) {

    jsonSrecpcion = {
        'opcion': op,
        'dValor': caso,
        'dCodrecepcion': $('#nrorecpecionDiagnostico').val(),
        'dUsuario': $('#idusersession_v').val(),
        'dObserv': $('#obsDiagnostico').val(),
        'dNrodiganostico': $('#codigoDiagnostico').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sDiagnosticoSERVLET",
        type: 'POST',
        data: jsonSrecpcion,
        cache: false,
        dataType: 'text',
        success: function () {
            deletejson = {
                'opcion': 4,
                'dnroDiagnostico': $('#codigoDiagnostico').val()
            };
            $.ajax({
                url: "/TALLERCASAJC/sDiagnosticoSERVLET",
                type: 'POST',
                data: deletejson,
                cache: false,
                dataType: 'text',
                success: function (resp) {
                    setTimeout(function () {
                        $('#mitabladetallediagnostico').find('tbody').find('tr').each(function () {
                            jsonDetaller = {
                                'opcion': 5,
                                'dnroDiagnostico': $('#codigoDiagnostico').val(),
                                'opDetalle': dtalle,
                                'dCodartic': $(this).find("td").eq(0).html(),
                                'dCant': $(this).find("td").eq(2).html()
                            };
                            $.ajax({
                                url: "/TALLERCASAJC/sDiagnosticoSERVLET",
                                type: 'POST',
                                data: jsonDetaller,
                                cache: false,
                                dataType: 'text',
                                success: function (resp) {
                                    $('#ventanaDiagnostico').modal('hide');
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






function traerarticulosD() {
    articlosD = {
        "opcion": 20,
        "codDepo": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: articlosD,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#listaarticulosD").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + "</option>");

            });

        }
    });
}
function traerRecepcionList() {
    jsonrep = {
        "opcion": 4
    };
    $.ajax({
        url: "/TALLERCASAJC/sRecepcionSERVLET",
        type: 'POST',
        data: jsonrep,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                var estado = value.id_estado;
                if (parseInt(estado) === 1) {
                    $("#lisrecepc").append("<option value= \"" + value.id_recepcion + "\"> " + "Fecha : " + value.fecha + "" + " / Cliente :" + value.cliente + "</option>");

                }

            });

        }
    });
}

function traerArti() {
    art = {
        "opcion": 19,
        "codArticulo": $('#v_articusD').val(),
        "coddepos": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#descriparticuloRecepcion').val(value.art_descripcion);
                $('#cantArtRecepcion').val(1);
                $('#cantArtRecepcion').focus();

            });

        }
    });

}
function valores(numero) {
    var num = document.getElementById(numero).value.replace(/\./g, '');
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        document.getElementById(numero).value = num;
    } else {
        alert('Solo se permiten numeros');
        document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');
    }

}



function cargaGrillaD() {
    var ban = false;
    if ($('#v_articusD').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#v_articusD').val();
        var codigo;
        $('#mitabladetallediagnostico').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest('tr').remove();
                    ban = true;
                    vCargarGrillaD();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            vCargarGrillaD();
        }






    }

}
var inx = 0;
function vCargarGrillaD() {
    //idmaterial
    var v_codmaterial = $('#v_articusD').val();
    var v_descripcion = $('#descriparticuloRecepcion').val();
    var v_cant = $('#cantArtRecepcion').val();

    $('#mitabladetallediagnostico').append("<tr id=\'prod" + inx + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + inx + "\');remove()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td></tr>");

    $('#v_articusD').val(null);
    $('#v_articusD').focus;
    $('#cantArtRecepcion').val(null);
    $('#descriparticuloRecepcion').val(null);
}
function remove() {
    $('#mitabladetallediagnostico tr').click(function () {
       $(this).closest('tr').remove();

    });
}


var idx = 0;
function getDiagnosticos() {
    $('#mitablaDiagnostico').find('tbody').find('tr').empty();
    sRecepcionesjosn = {
        'opcion': 1
    };
    $.ajax({
        url: "/TALLERCASAJC/sDiagnosticoSERVLET",
        type: 'POST',
        data: sRecepcionesjosn,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var id = valor.id_estado;
                var color;
                if (parseInt(id) === 3) {
                    color = '#d9edf7';
                    btn = "<a class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro' onclick=\"$(\'#prod" + idx + "\');deleteDiagonostico()\"></a>";
                    vbtn = "<a class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' onclick=\"$(\'#prod" + idx + "\');updateDiagnostico()\"></a>";
                } else if (parseInt(id) === 1) {
                    color = '#acffac';
                    btn = "<a disabled='' class='btn btn-md btn-danger glyphicon glyphicon-remove' title='Eliminar Registro'></a>";
                    vbtn = "<a disabled='' class='btn btn-md btn-success glyphicon glyphicon-edit' title='Modificar Registro' ></a>";
                }
                $("#mitablaDiagnostico").append($("<tr>").append($(
                        "<td>" + valor.id_diagnostico + "</td>" +
                        "<td>" + valor.fecha + "</td>" +
                        "<td>" + valor.razonsocial + "</td>" +
                        "<td bgcolor=" + color + ">" + valor.est_descripcion + "</td>" +
                        "<td>" + valor.usu_nombre + "</td>" +
                        "<td style='text-align: center' >" + btn + " " + vbtn + "</td>")));
            });


        }

    });
}

function updateDiagnostico() {
    var diag = 0;
    $('#mitablaDiagnostico tr').click(function () {
        diag = parseInt($(this).find("td").eq(0).html());
        openViewD(diag);
    });
    function openViewD(vCod) {
        $('#btnguardarDiagnostico').hide();
        $('#btntmodificarDiagnostico').show();
        $('#ventanaDiagnostico').modal('show');
        $('#mitabladetallediagnostico').find('tbody').find('tr').empty();
        setTimeout(function () {
            getDetalleRececionDiagnostico(vCod);
        }, 1200);


    }
}
function deleteDiagonostico() {
    var nroD = 0;
    var est;
    $('#mitablaDiagnostico tr').click(function () {
        nroD = parseInt($(this).find("td").eq(0).html());
        est = $(this).find("td").eq(3).html();
        verD(nroD, est);
    });
    function verD(v1, v2) {
        var sms = confirm("Desea Anular el Registro ?");
        if (sms) {
            if (v2 === "PENDIENTE") {
                updateEstadoDiagnostico(v1, 2);

            } else {
                alert('Registro CONFIRMADO, no se puede ANULAR.!!');
            }
        }
    }


}



function seleccionDiagnostico() {
    $('#mitablaDiagnostico tr').click(function () {
        $('#nroDiagnostico').val($(this).find("td").eq(0).html());
        $('#estadoDiagnostico').val($(this).find("td").eq(3).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadoDiagnostico').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadoDiagnostico').style.color = "#000000";
            document.getElementById('estadoDiagnostico').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadoDiagnostico').style.background = "firebrick";
            document.getElementById('estadoDiagnostico').style.color = "#ffffff";
        }
    });
}//----------------------------


var idx = 0;
function recuperarRecepcionDiagnostico() {

    $('#mitabladetallediagnostico').find('tbody').find('tr').empty();
    detallejson = {
        'opcion': 3,
        'nroRecepDiagnostico': $('#nrorecpecionDiagnostico').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/sDiagnosticoSERVLET",
        type: 'POST',
        data: detallejson,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var vRecepcion = JSON.stringify(resp);
                var vvRecepcion = JSON.parse(vRecepcion);
                var nrOrecpcion = vvRecepcion[0].nrorecepcion;
                if (parseInt(nrOrecpcion) != "") {
                    alert("El numero de Recepción ya fue procesada.!!");
                } else {
                    var v = JSON.stringify(resp);
                    var vv = JSON.parse(v);
                    var estado = vv[0].id_estado;
                    if (parseInt(estado) === 1) {
                        $.each(resp, function (indice, value) {
                            $('#fecharecpe').val(value.fecha);
                            $('#obsDiagnostico').val(value.observacion);
                            $('#obsDiagnostico').focus();
                            $('#clinteNombrediagnostico').val(value.cliente);
                            $("#mitabladetallediagnostico").append($("<tr id=\'prod" + idx + "\'>").append($(
                                    "<td>" + value.id_articulo + "</td>" +
                                    "<td>" + value.articulo + "</td>" +
                                    "<td>" + value.cantidad + "</td>" +
                                    "<td ><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idx + "\').remove()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td>")));



                        });
                    } else {
                        alert('Recepción Pendiente.!!');
                    }
                }

            } else {
                alert('Datos no encontrados..');
            }
        }
    });



}

var indexx = 0;
function getDetalleRececionDiagnostico(cod) {
    detallejsonR = {
        'opcion': 7,
        'nroDiagnostico': cod
    };
    $.ajax({
        url: "/TALLERCASAJC/sDiagnosticoSERVLET",
        type: 'POST',
        data: detallejsonR,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#codigoDiagnostico').val(value.id_diagnostico);
                $('#fechaDiagnostico').val(value.fecha);
                $('#fecharecpe').val(value.fecha);
                $('#nrorecpecionDiagnostico').val(value.id_recepcion);
                $('#clinteNombrediagnostico').val(value.cliente);
                $('#obsDiagnostico').val(value.diganostico);
                $("#mitabladetallediagnostico").append($("<tr id=\'prod" + indexx + "\'>").append($(
                        "<td>" + value.id_articulo + "</td>" +
                        "<td>" + value.articulo + "</td>" +
                        "<td>" + value.cantidad + "</td>" +
                        "<td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + indexx + "\').remove()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td>")));



            });

//       recuperarRecepcionDiagnostico();

        }

    });
}

function actualizarEstadoDiagnostico(estado) {



    if ($('#nroDiagnostico').val() === "") {
        alert('Debes seleccionar un registo..');
    } else {
        var sms;
        var confir;
        var codDiagnostico = $('#nroDiagnostico').val();
        var codestado = $('#estadoDiagnostico').val();
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
                    updateEstadoDiagnostico(codDiagnostico, estado);
                    alert('Registro  Confirmado');

                }

            }

            if (estado === 2) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    updateEstadoDiagnostico(codDiagnostico, estado);
                    alert('Registro  Anulado');
                }
            }
            if (estado === 3) {
                if (codestado === "PENDIENTE") {
                    alert(confir);
                } else {
                    updateEstadoDiagnostico(codDiagnostico, estado);
                    alert('Registro  Revertido');
                }

            }

        } else {

        }
    }

}

function updateEstadoDiagnostico(codDiagnostico, estado) {
    estadojson = {
        'opcion': 6,
        'dEstado': estado,
        'dNroDiagnostico': codDiagnostico
    };
    $.ajax({
        url: "/TALLERCASAJC/sDiagnosticoSERVLET",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function () {
            location.reload();
        }

    });


}
