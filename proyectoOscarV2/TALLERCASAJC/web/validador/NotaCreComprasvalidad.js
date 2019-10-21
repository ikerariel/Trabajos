$(document).ready(function () {
//    combos('comboproveedor', 3);
//    combos('combotipomneda', 4);
    fechaac();
//    $('#coddeposito').val($('#coddeposito_v').val());
//    $('#depositodescrip').val($('#depos_v').val());
    getarticulos();
    getNotaCreCompras();
});

function verNotaCreCompras() {
    $('#mitabladetallenotacrecompras').find('tbody').find('tr').each(function () {
        alert($(this).find("td").eq(5).html());
    });
}
function fechaacNotaCreCompras() {
    var fv = new Date();
    $('#fechanotacrecompras').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
}
function insertarNotaCreCompras(op, caso) {
    movimiento = {
        'opcion': op,
        'vv_caso': caso,
        '_nronocred': $('#NCnronocred_').val(),
        '_nrotimbrado': $('#NCnrotimbrado_').val(),
        '_obsnocred': $('#NCobsnocred_').val(),
        '_codusuario': $('#NCcodusuario_').val(),
        '_codnotacrecompra': $('#NCcodnotacrecompra_').val(),
        '_nrofacturaC': $('#NCnrofacturaC_').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
        type: 'POST',
        data: movimiento,
        cache: false,
        dataType: 'text',
        success: function () {
            deletejson = {
                'opcion': 7,
                '_codnotacrecompra': $('#NCcodnotacrecompra_').val()
            };
            $.ajax({
                url: "/TALLERCASAJC/NotaCreComprasControl",
                type: 'POST',
                data: deletejson,
                cache: false,
                dataType: 'text',
                success: function (resp) {
                    $('#mitabladetallenotacrecompras').find('tbody').find('tr').each(function () {
                        movimiento = {
                            'opcion': 2,
                            '_codarticulo': $(this).find("td").eq(0).html(),
                            '_cantidad': $(this).find("td").eq(3).html(),
                            '_preciounitario': $(this).find("td").eq(2).html().replace(/\./g, ''),
                            '_codnotacrecompra': $('#NCcodnotacrecompra_').val()
                        };
                        $.ajax({
                            url: "/TALLERCASAJC/NotaCreComprasControl",
                            type: 'POST',
                            data: movimiento,
                            cache: false,
                            dataType: 'text',
                            success: function (resp) {
                                $('#ventananotacrecompras').modal('hide');
                                location.reload();
                            }
                        });
                    });
                }
            });
        }
    });
}

function modificarNotaCreCompras() {
    movimiento = {
        'opcion': 1,
        '_nronocred': $('#NCnronocred_').val(),
        '_nrotimbrado': $('#NCnrotimbrado_').val(),
        '_obsnocred': $('#NCobsnocred_').val(),
        '_codusuario': $('#NCcodusuario_').val()
    };
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
        type: 'POST',
        data: movimiento,
        cache: false,
        dataType: 'text',
        success: function () {

            $('#mitabladetallenotacrecompras').find('tbody').find('tr').each(function () {
                movimiento = {
                    'opcion': 2,
                    '_codarticulo': $(this).find("td").eq(0).html(),
                    '_cantidad': $(this).find("td").eq(3).html(),
                    '_preciounitario': $(this).find("td").eq(2).html().replace(/\./g, '')

                };
                $.ajax({
                    url: "/TALLERCASAJC/NotaCreComprasControl",
                    type: 'POST',
                    data: movimiento,
                    cache: false,
                    dataType: 'text',
                    success: function (resp) {
                        $('#ventananotacrecompras').modal('hide');
                        getpresupuesto();
                    }
                });
            });
        }

    });


}
////////////////////////////////////////////////////////////////////
function combosNotaCreCompras(cb, cod) {
    combosjson = {
        "opcion": cod
    };
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
        type: 'POST',
        data: combosjson,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $('#' + cb).append("<option value= \"" + value.cod + "\"> " + value.descrip + "</option>");

            });

        }
    });
}
/////////////////////////////////////////////////////////////////////////////////////


//function getarticulosNotaCreCompras() {
//    articulos = {
//        "opcion": 20,
//        "codDepo": $('#coddeposito_v').val()
//    };
//    $.ajax({
//        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
//        type: 'POST',
//        data: articulos,
//        cache: false,
//        success: function (resp) {
//            $.each(resp, function (indice, value) {
//                $("#lisart").append("<option value= \"" + value.id_articulo + "\"> " + value.art_descripcion + " Cantidad : " + value.cantidad + "</option>");
//            });
//        }
//    });
//}

//function obarticulos() {
//    art = {
//        "opcion": 19,
//        "codArticulo": $('#v_articulos').val(),
//        "coddepos": $('#coddeposito_v').val()
//    };
//    $.ajax({
//        url: "http://localhost:8084/TALLERCASAJC/OrdenComprascontrol",
//        type: 'POST',
//        data: art,
//        cache: false,
//        success: function (resp) {
//            $.each(resp, function (indice, value) {
//                $('#descriparticulo').val(value.art_descripcion);
//                $('#precioarticulo').val(value.preccompras);
//                $('#cantarticulo').val(1);
//                $('#cantarticulo').focus();
//                valores('precioarticulo');
//            });
//        }
//    });
//}

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



function verificarfilaNotaCreCompras() {
    var ban = false;
    if ($('#_articulos').val() === "") {
        alert('DEBES INGRESAR UN ARTICULO');
    } else {
        var cod = $('#_articulos').val();
        var codigo;
        var vdetalle = 0;
        $('#mitabladetallenotacrecompras').find('tbody').find('tr').each(function () {
            codigo = $(this).find("td").eq(0).html();
            if (cod === codigo) {
                vdetalle = $(this).find("td").eq(5).html();
                var sms = confirm('Articulo cargado, desea sustituirlo ??');
                if (sms === true) {
                    $(this).closest("tr").remove();
                    ban = true;
                    cargarfilaNotaCreCompras(vdetalle);
                } else {
                    ban = true;
                }
            } else {
            }
        });
        if (ban === false) {
            cargarfilaNotaCreCompras(vdetalle);
        }
    }
}

var tindex = 0;
function cargarfilaNotaCreCompras(v) {
    //idmaterial
    var v_codmaterial = $('#_articulos').val();
    var v_descripcion = $('#descriparticulo').val();
    var v_precio = $('#precioarticulo').val();
    var v_cant = $('#cantarticulo').val();
    var codD = v;

    subtotal = (v_precio.replace(/\./g, '')) * v_cant;
    tindex++;
    $('#mitabladetallenotacrecompras').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + v_codmaterial + "</td>\n\
            <td>" + v_descripcion + "</td>\n\
            <td>" + v_precio + "</td>\n\
            <td>" + v_cant + "</td>\n\
            <td>" + subtotal + "</td>\n\
            <td style=display:none>" + codD + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + tindex + "\').remove();updatemonto( " + subtotal + ", " + tindex + ")\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td></tr>");


    totalesNotaCreCompras();
    $('#_articulos').val(null);
    $('#_articulos').focus;
    $('#descriparticulo').val(null);
    $('#precioarticulo').val(null);
    $('#cantarticulo').val(null);
}

function totalesNotaCreCompras() {
    setTimeout(function () {
        $('#totalarticulos').val(null);
        monto = 0;
        acumu = 0;
        $('#mitabladetallenotacrecompras').find('tbody').find('tr').each(function () {
            monto = parseInt($(this).find("td").eq(4).html());
            acumu = acumu + monto;
        });
        $('#totalarticulos').val(acumu);
        numeroDecimal('totalarticulos');
        tindex++;
        
    }, 1800);
// valores('totalarticulos');
}

function getNotaCreCompras() {
    $('#mitablanotacrecompras').find('tbody').find('tr').empty();
    presupuestoJSON = {
        'opcion': 3
    };
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
        type: 'POST',
        data: presupuestoJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $("#mitablanotacrecompras").append($("<tr>").append($(
                        "<td>" + valor.id_notacrecompra + "</td>" +
                        "<td>" + valor.fecha_nocred + "</td>" +
                        "<td>" + valor.nro_nocred + "</td>" +
                        "<td>" + valor.obs_nocred + "</td>" +
                        "<td bgcolor='#d9edf7'>" + valor.estado + "</td>" +
                        "<td bgcolor='#d9edf7'>" + valor.usuario + "</td>")));
            });
        }
    });
}

function buscarNotaCreCompras() {
    var tableReg = document.getElementById('mitablanotacrecompras');
    var searchText = document.getElementById('filtronotacrecompras').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";
// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++) {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++) {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1)) {
                found = true;
            }
        }
        if (found) {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}//--------------

function seleccionNotaCreCompras() {
    $('#mitablanotacrecompras tr').click(function () {
        $('#nronotacrecompra').val($(this).find("td").eq(0).html());
        $('#estadonotacrecompra').val($(this).find("td").eq(4).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
         //         * v_nroPlanilla*/
        var estado = $('#estadonotacrecompra').val();
        if (estado === 'PENDIENTE') {
            document.getElementById('estadonotacrecompra').style.color = "#000000";
            document.getElementById('estadonotacrecompra').style.background = "PaleGoldenrod";
        }
        if (estado === 'CONFIRMADO') {
            document.getElementById('estadonotacrecompra').style.background = "firebrick";
            document.getElementById('estadonotacrecompra').style.color = "#ffffff";
        }
    });
}//----------------------------

function abrirnuevoNotaCreCompras() {
//    $('#_articulos').val(null);
//    $('#descriparticulo').val(null);
//    $('#precioarticulo').val(null);
//    $('#cantarticulo').val(null);
//    $('#totalarticulos').val(null);
//    
//    $('#codigoNrofacturasCompras').val(null);
//     $("#codigoNrofacturasCompras").prop('disabled', false);
//     
//    $('#mitabladetallenotacrecompras').find('tbody').find('tr').empty();
//    $('#notacrecompras').show();
//    $('#btntmodificarnotacrecompras').hide();
    $('#ventanaNC').modal('show');
//    var num;
//    $('#mitabladetalleNC').each(function () {
//        num = parseInt($(this).find("td").eq(0).html());
//
//    });
//    $('#codigoNC').val(parseInt(num) + 1);

}


function getdetalleNotaCreCompras() {
    $('#mitabladetallenotacrecompras').find('tbody').find('tr').empty();
    $('#btnguardarnotacrecompras').hide();
    $('#totalarticulos').val(null);
    $('#btntmodificarnotacrecompras').show();
    $('#_articulos').val(null);
    $('#descriparticulo').val(null);
    $('#precioarticulo').val(null);
    $('#cantarticulo').val(null);
    detallejson = {
        'opcion': 6,
        'nronotacrecompra': $('#nronotacrecompra').val()
    };
   
    $.ajax({
        url: "/TALLERCASAJC/NotaCreComprasControl",
        type: 'POST',
        data: detallejson,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $('#codigonotacrecompras').val(valor.id_notacrecompra);
                $('#NCnrofacturaC_').val(valor.id_compra);
                 $("#NCnrofacturaC_").prop('disabled', true);
                $('#NCnrofacturaC_').val(valor.id_compra);
                sum = valor.precio_detcomp * valor.cantidad_detcomp;
                tindex++;
                $('#mitabladetallenotacrecompras').append("<tr id=\'prod" + tindex + "\'>\
            <td>" + valor.id_articulo + "</td>\n\
            <td>" + valor.articulo + "</td>\n\
            <td>" + valor.preciounitario + "</td>\n\
            <td>" + valor.cantidad + "</td>\n\
            <td>" + sum + "</td>\n\
            <td style=display:none>" + valor.iddetalle + "</td>\n\
            <td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idx + "\').remove();updatemonto( " + sum + ", " + tindex + ")\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></tr>");



            });

        }

    });
    totales();
}
 ////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////
    
function recuperarmodificarNotaCreCompras() {
    if ($('#estadonotacrecompra').val() === "") {
        alert('Debes seleccionar un registro');
    } else if ($('#estadonotacrecompra').val() === "CONFIRMADO") {
        alert('No se puede modificar el registro, el mismo se encuentra CONFIRMADO');
    }
    if ($('#estadonotacrecompra').val() === "PENDIENTE") {
        $('#ventananotacrecompra').modal('show');
        $('#codigopresupuesto').val($('#nroprespuesto').val());
        getdetallepresupuesto();



    } else {

    }
}
function actualizarpresupuesto(estado) {
    if ($('#nroprespuesto').val() === "") {
        alert('Debes seleccionar un registo..');
    } else {
        var sms;
        var confir;
        var codpresupuesto = $('#nroprespuesto').val();
        var codestado = $('#estadopresupuesto').val();
        if (estado === 1) {
            sms = "Desea Confirmar el presupuesto??";
            confir = "El presupuesto ya esta confirmado..!";
        }
        if (estado === 2) {
            sms = "Desea Anulaar el presupuesto??";
            confir = "El presupuesto no se puede Anular, el mismo ya esta CONFIRMADO..!!";
        }
        if (estado === 3) {
            sms = "Desea Revertir la operación??";
            confir = "El presupuesto no se puede REVERTIR, el mismo esta PENDIENTE..!!";
        }

        var v_sms = confirm(sms);
        if (v_sms === true) {
            if (estado === 1) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    actualizarestados(codpresupuesto, estado);
                }
            }
            if (estado === 2) {
                if (codestado === "CONFIRMADO") {
                    alert(confir);
                } else {
                    actualizarestados(codpresupuesto, estado);
                }
            }
            if (estado === 3) {
                if (codestado === "PENDIENTE") {
                    alert(confir);
                } else {
                    actualizarestados(codpresupuesto, estado);
                }
            }

        } else {

        }
    }

}

function informepresupuesto() {
    if ($('#nroprespuesto').val() === "") {
        alert('DEBES SELECCIONAR UN REGISTRO');
    } else {
        var cod = $('#nroprespuesto').val();
        var valor = 1;
        window.open(`reportesgenericos.jsp?id_presucompra=${cod}&vCodigo=${valor}`, "_blank");

//        });

    }

}
function actualizarestados(codpresupuesto, estado) {
    estadojson = {
        'opcion': 8,
        'v_estado': estado,
        'v_presupuesto': codpresupuesto
    };
    $.ajax({
        url: "/TALLERCASAJC/presupuestoControl",
        type: 'POST',
        data: estadojson,
        cache: false,
        dataType: 'text',
        success: function () {
            location.reload();
        }

    });


}

var idxpreu = 0;
function recuperarDetallePedido() {
    $('#mitabladetallepresupuesto').find('tbody').find('tr').empty();
    datosDetalleJSON = {
        "opcion": 11,
        "nropedidov": $('#codigoNropedido').val()
    };
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/PedidosComprasServlet",
        type: 'POST',
        data: datosDetalleJSON,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                var v = JSON.stringify(resp);
                var vv = JSON.parse(v);
//                for(var i in vv){
                var estado = vv[0].id_estado;
//                }
                if (parseInt(estado) === 1) {
                    $.each(resp, function (indice, value) {
                        idxpreu++;
                        $("#mitabladetallepresupuesto").append($("<tr id=\'prod" + idxpreu + "\'>").append($(
                                "<td>" + value.id_articulo + "</td>" +
                                "<td>" + value.art_descripcion + "</td>" +
                                "<td style='color:red'>" + "0" + "</td>" +
                                "<td>" + value.cantidad + "</td>" +
                                "<td style='color:red'>" + "0" + "</td>" +
                                "<td><button type=button title='Quitar el registro de la lista' \n\
                                 style='align-content:center' class='btn btn-danger' onclick=\"$(\'#prod" + idxpreu + "\').remove()\">\n\
                                 <span class='glyphicon glyphicon-remove'></span></button></td>")));



                    });
                } else {
                    alert('Pedido Pendiente.!!');
                }


            } else {
                alert('Datos no encontrados..');
            }
        }
    });



}



