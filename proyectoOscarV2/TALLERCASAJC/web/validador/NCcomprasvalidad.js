$(document).ready(function () {
    opcionesNDC();
});


function opcionesNDC() {
    fechaNC();
    getplanillaNC();
    getProductos();
    actualizarestadoNC(); 
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

    $('#btnguardarNC').click(function () {
        insertarNotaC();
    });
    $('#btnmodificarNC').click(function () {
        recuperarNC();
    });
    $('#btnmodificarNCredito').click(function () {
        updateNCD();
    });

}


function fechaNC() {
    var fecha = new Date();
    $('#fechaNC').val(fecha.getDate() + "/" + (fecha.getMonth() + 1) + "/" + fecha.getFullYear());
}//----------

var indx = 0;
function recuperarFacturaNC() {
    $('#mitabladetalleNC').find('tbody').find('tr').empty();
    json = {
        "opcion": 7,
        "nrofacturaNC": $('#nrofacturaNC').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
        type: 'POST',
        data: json,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var nro = JSON.stringify(resp);
                var n = JSON.parse(nro);
                var fac = n[0].nrofact;
                if (parseInt(fac) > 0) {
                    alert('La factura  ya fue procesada..');
                } else {
                    var v = JSON.stringify(resp);
                    var vv = JSON.parse(v);
                    var estado = vv[0].id_estado;
//                    alert(estado);
                    if (parseInt(estado) === 1) {
                        $.each(resp, function (indice, value) {
                            $('#idnrofacturaNC').val(value.id_compra);
                            $('#depositoNC').val(value.id_deposito);
                            subtotal = value.precio_detcomp * value.cantidad_detcomp;
                            indx++;
                            $('#mitabladetalleNC').append("<tr id=\'prod" + indx + "\'>\
                                  <td>" + value.id_articulo + "</td>\n\
                                    <td>" + value.art_descripcion + "</td>\n\
                                     <td>" + value.precio_detcomp + "</td>\n\
                                    <td>" + value.cantidad_detcomp + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + indx + "\').remove(); sumamonto();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");

                        });
                        sumamonto();
                    } else {
                        alert('Factura Pendiente.!!');
                    }

                }



            } else {
                alert('Datos no encontrados..');
            }

        }
    });
}
function sumamonto() {
    monto = 0;
    acumu = 0;
    $('#mitabladetalleNC').find('tbody').find('tr').each(function () {
        monto = parseInt($(this).find("td").eq(4).html());
        acumu = acumu + monto;
    });
    $('#montodebitar').val(acumu);
    numeroDecimal('montodebitar');
    indx++;
}

function  insertarNotaC() {
    if ($('#nroNC').val() === "" || $('#timbradoNC').val() === "" ||
            $('#nrofacturaNC').val() === "" || $('#obserNC').val() === "") {
        alert('Algunos datos no fueron cargados correctamente..');
    } else {

        var dato = "";
        $('#mitabladetalleNC').find('tbody').find('tr').each(function () {
            dato = $(this).find("td").eq(0).html();
        });
        if (dato === "") {
            alert('No hay detalle que guardar..!');
            $("#codgenericiMerca").focus();
        } else {
            var opcion = confirm('Desea Guardar Nota credito debito..?');
            if (opcion === true) {
                datosCabeceraJSON = {
                    "opcion": 1,
                    "vvcaso": 1,
                    "_nronocred": $('#nroNC').val(),
                    "_nrotimbrado": $('#timbradoNC').val(),
                    "_obsnocred": $('#obserNC').val(),
                    "_codusuario": $('#idusersession_v').val(),
                    "_nrofacturaC": $('#idnrofacturaNC').val(),
                    "_deposito": $('#depositoNC').val()
                };
                $.ajax({
                    url: "/TALLERCASAJC/NotaCreComprasControl",
                    type: 'POST',
                    data: datosCabeceraJSON,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        setTimeout(function () {
                            insertarDetalleNotaC();
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



function  updateNCD() {
    var dato = "";
    $('#mitabladetalleNC').find('tbody').find('tr').each(function () {
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
                datos = {
                    "opcion": 1,
                    "vvcaso": 2,
                    "_nronocred": $('#nroNC').val(),
                    "_codnotacrecompra": $('#codigoNC').val(),
                    "_nrotimbrado": $('#timbradoNC').val(),
                    "_obsnocred": $('#obserNC').val(),
                    "_codusuario": $('#idusersession_v').val(),
                    "_nrofacturaC": $('#idnrofacturaNC').val(),
                    "_depositonro": $('#depositoNC').val()
                };
                $.ajax({
                    url: "/TALLERCASAJC/NotaCreComprasControl",
                    type: 'POST',
                    data: datos,
                    cache: false,
                    dataType: 'text',
                    success: function () {
                        deleteNC();
                        setTimeout(function () {
                            insertarDetalleNotaC()
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
function  insertarDetalleNotaC() {
    $('#mitabladetalleNC').find('tbody').find('tr').each(function () {
        datosDetalleJSON = {
            "opcion": 2,
            "_codnotacrecompra": $('#codigoNC').val(),
            "_codarticulo": $(this).find("td").eq(0).html(),
            "_cantidad": $(this).find("td").eq(3).html(),
            "_preciounitario": $(this).find("td").eq(2).html()
        };
        $.ajax({
            url: "/TALLERCASAJC/NotaCreComprasControl",
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

function  deleteNC() {
    datosDetalleJSON = {
        "opcion": 5,
        "_codnotacrecompra": $('#codigoNC').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
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


function getplanillaNC() {
    js = {
        'opcion': 3
    };
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
        type: 'POST',
        data: js,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#mitablaNC").append($("<tr>").append($("<td>" + value.id_notacrecompra + "</td>" +
                        "<td>" + value.fecha_nocred + "</td>" +
                        "<td>" + value.usuario + "</td>" +
                        "<td>" + value.estado + "</td>")));
            });
        }
    });
}
var ixInde = 0;
function recuperarNC() {
    if ($('#nronc').val() === "") {
        alert('Seleecione planilla para visualizar..');
    } else {
        $('#ventanaNC').modal('show');
        $('#btnmodificarNCredito').show();
        $('#btnguardarNC').hide();
        $('#mitabladetalleNC').find('tbody').find('tr').empty();
        detallejs = {
            "opcion": 4,
            "nronotacrecompra": $('#nronc').val()
        };
        $.ajax({
            url: "/TALLERCASAJC/NotaCreComprasControl",
            type: 'POST',
            data: detallejs,
            cache: false,
            success: function (resp) {
                if (JSON.stringify(resp) != '[]') {
//                    alert(resp);
                    $.each(resp, function (indice, value) {
                        ///RECUPERA LA CABECERA/////////
                        $("#codigoNC").val(value.id_notacrecompra);
                        $("#nrofacturaNC").val(value.co_nrofact);
                        $("#idnrofacturaNC").val(value.id_compra);
                        $("#obserNC").val(value.obs_nocred);
                        $("#fechaNC").val(value.fecha_nocred);
                        $("#nroNC").val(value.nro_nocred);
                        $("#timbradoNC").val(value.nro_timbrado);
                        $("#depositoNC").val(value.id_deposito);

                        subtotal = value.preciouni_detnocre * value.cantidad_detnocre;
                        ixInde++;
                        $('#mitabladetalleNC').append("<tr id=\'prod" + ixInde + "\'>\
                                    <td >" + value.id_articulo + "</td>\n\
                                    <td>" + value.articulo + "</td>\n\
                                    <td>" + value.preciouni_detnocre + "</td>\n\
                                    <td>" + value.cantidad_detnocre + "</td>\n\
                                    <td>" + subtotal + "</td>\n\
                                    <td><button type=button title='Quitar el registro de la lista' \n\
            style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + ixInde + "\').remove(); sumamonto();\">\n\
            <span class='glyphicon glyphicon-remove'></span></button></td></tr>");
                    });
//                    $('#codigo').val($('#nroNotaP').val());
                } else {
                    alert('Datos no encontrados..');
//                    $("#nroNotaP").focus();
                }
                sumamonto();
            }
        });
    }

}

function seleccionNCplanilla() {
    $('#mitablaNC tr').click(function () {
        $('#nronc').val($(this).find("td").eq(0).html());
        $('#estadonc').val($(this).find("td").eq(3).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadonc').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadonc').style.color = "#000000";
            document.getElementById('estadonc').style.background = "PaleGoldenrod";
        }
        if (estado === 'AUTORIZADO') {
            document.getElementById('estadonc').style.background = "firebrick";
            document.getElementById('estadonc').style.color = "#ffffff";
        }
    });
}//---------------
//------------

function getProductos() {
    articulos = {
        "opcion": 20,
        "codDepo": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: articulos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#lisprodutcos").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + " Cantidad : " + value.cantidad + "</option>");

            });

        }
    });
}
function obtProducto() {
    art = {
        "opcion": 19,
        "codArticulo": $('#v_produtos').val(),
        "coddepos": $('#coddeposito_v').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
        type: 'POST',
        data: art,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#descripproducto').val(value.art_descripcion);
                $('#precioproducto').val(value.preccompras);
                $('#cantproducto').val(1);
                $('#cantproducto').focus();
                numeroDecimal('precioproducto');


            });

        }
    });

}



function verificarfilaNC() {
    var ban = false;
    if ($('#v_produtos').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#v_produtos').val();
        var codigo;
        $('#mitabladetalleNC').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    cargarfilaNC();
                } else {
                    ban = true;
                }

            } else {

            }

        });
        if (ban === false) {
            cargarfilaNC();
        }
    }



}
var tindex = 0;
function cargarfilaNC() {
    //idmaterial
    var v_codmaterial = $('#v_produtos').val();
    var v_descripcion = $('#descripproducto').val();
    var v_precio = $('#precioproducto').val();
    var v_cant = $('#cantproducto').val();
    subtotal = (v_precio.replace(/\./g, '')) * v_cant;
    tindex++;
    $('#mitabladetalleNC').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td></tr>");


    setTimeout(function () {
        sumamonto();
    }, 1000);

    $('#v_produtos').val(null);
    $('#v_produtos').focus;
    $('#descripproducto').val(null);
    $('#precioproducto').val(null);
    $('#cantproducto').val(null);
}



function actualizarestadoNC() {
    var btn = "";
    $(document).ready(function () {
        $('body').on('click', '#botonesNotaCreCompras a', function () {
            btn = ($(this).attr('id'));
            if (btn === 'btnAnularNC') {
                if ($('#estadonc').val() === "") {
                    alert('Seleccione un registro.!');
                } else if ($('#estadonc').val() === 'CONFIRMADO') {
                    alert('EL registro aún esta Pendiente de Confirmación o ya esta Anulada..');
                } else if ($('#estadonc').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Anular el registro.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 6,
                            "_estado": 2,
                            "_notacrecompra": $('#nronc').val()
                        };
                        confirmarNota();
                        alert('Registro Anulado con éxito.!!');
                    }
                }
            } else if (btn === 'btnConfirmarNC') {
                if ($('#estadonc').val() === "") {
                    alert('Seleccione un registro.!');
                } else if ($('#estadonc').val() === 'CONFIRMADO') {
                    alert('El registro ya fué Confirmado o esta Anulada..');
                } else if ($('#estadonc').val() === 'PENDIENTE') {
                    var opcion = confirm('Desea Confirmar el registro.??');
                    if (opcion === true) {
                        datoJson = {
                            "opcion": 6,
                            "_estado": 1,
                            "_notacrecompra": $('#nronc').val()
                        };
                        confirmarNota();
                        alert('Registro Confirmado con éxito.!!');
                    }
                }
            } else if (btn === 'btnRevertirNc') {
                if ($('#estadonc').val() === "") {
                    alert('Seleccione un registro.!');
                } else if ($('#estadonc').val() === 'PENDIENTE') {
                    alert('El registro no se puede Revertir..');
                } else if ($('#estadonc').val() === 'CONFIRMADO') {
                    var opcion = confirm('Desea Revertir el registro.??');
                    if (opcion === true) {
                        datoJson = {
                           "opcion": 6,
                            "_estado": 3,
                            "_notacrecompra": $('#nronc').val()
                        };
                        confirmarNota();
                        alert('Registro Revertido.!!');
                    }
                }
            }
        });
    });
}
function confirmarNota() {
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
        type: 'POST',
        data: datoJson,
        cache: false,
        dataType: 'text',
        success: function () {
            $('#mitablaNC').find('tbody').find('tr').empty();
            getplanillaNC();

        },
        error: function () {
        }
    });
}