$(document).ready(function () {
    validad();
    getCombos(4, 'listacajeros');
    getCombos(5, 'listaDoc');
    getCombos(8, 'aperCaja');


});

function validacionAperturaVenta() {


    var opcion = false;
    var vCaja;
    var vCajero;
    var vEstado;
    var v_Caja = $('#aperCaja').val();
    var v_Cajero = $('#listacajeros').val();
    var v_TipoDoc = $('#listaDoc').val();
    var v_MontoAper = $('#apeMontoapertura').val().replace(/\./g, '');
    var v_CodTimbrado = $('#aperFactTimrbados').val();
    $('#mitablaaperturaCierreCajaVentas').each(function () {
        vCaja = $(this).find("td").eq(7).html(); //caja
        vCajero = $(this).find("td").eq(8).html();//cajero
        vEstado = $(this).find("td").eq(6).html();//estado de la apertura

    });
    if (vCaja === v_Caja && vEstado === "ABIERTA") {
        $.alert({
            title: 'AVISO..!!',
            icon: 'glyphicon glyphicon-remove',
            content: 'La Caja selecciona esta Abierta..!',
            type: 'red',
            animation: 'scaleY'


        });
    } else
    if (vCajero === v_Cajero && vEstado === "ABIERTA") {
        $.alert({
            title: 'AVISO..!!',
            icon: 'glyphicon glyphicon-remove',
            content: 'El Cajero se encuentra en una Caja Abierta.!',
            type: 'red',
            animation: 'scaleY'


        });

    } else {
        $.confirm({
            title: 'Guardar',
            content: 'Desea Guardar los Registros ?',
            buttons: {
                Si: {
                    text: 'SI',
                    btnClass: 'btn-success',
                    keys: ['enter', 'shift'],
                    action: function () {
                        jsonAperVenta = {
                            'opcion': 9,
                            'aperMonto': v_MontoAper,
                            'aperCaja': v_Caja,
                            'aperCajero': v_Cajero,
                            'aperSucursal': 1,
                            'aperUsuario': $('#vCodIDuser').val(),
                            'aperestado': 1,
                            'aperTimbrado': v_CodTimbrado
                        };
                        $.ajax({
                            url: "/syscontrol/ventasSERVLET",
                            type: 'POST',
                            data: jsonAperVenta,
                            cache: false,
                            dataType: 'text',
                            success: function () {
                                getAperCierreVentas();
                            }

                        });
                    }
                },
                No: {
                    text: 'No',
                    btnClass: 'btn-red',
                    keys: ['enter', 'shift'],
                    action: function () {
                        $.alert('Cancelado !!');
                    }
                }
            }
        });

    }




}

function validad() {
    getTimbrados();
    getAperCierreVentas();
    $('#aperIDfacTimbrados').hide();
    $('#vNrotimbrado').blur(function () {
        var counNrotimb = $('#vNrotimbrado').val().length;
        verificarcampoentero('vNrotimbrado');
        if (parseInt(counNrotimb) === 8) {
        } else {
            $.confirm({
                title: 'AVISO!',
                content: 'El número de timbrado debe contener 8 carácteres.. ',
                type: 'red',
                buttons: {
                    Ok: {
                        text: 'OK',
                        btnClass: 'btn-dark',
                        keys: ['enter', 'shift'],
                        action: function () {
                            var texto = $('#vNrotimbrado').val();
                            var tex = texto.substring(0, texto.length - 1);
                            $('#vNrotimbrado').val(tex);

                        }
                    }

                }

            });
        }

    });
    $('#vNroCaja').blur(function () {
        var counNrotimb = $('#vNroCaja').val().length;
        verificarcampoentero('vNroCaja');
        if (parseInt(counNrotimb) != 3) {
            $.confirm({
                title: 'AVISO!',
                content: 'El número de caja  debe contener superar 3 carácteres.. ',
                type: 'red',
                buttons: {
                    Ok: {
                        text: 'OK',
                        btnClass: 'btn-dark',
                        keys: ['enter', 'shift'],
                        action: function () {
                            var texto = $('#vNroCaja').val();
                            var tex = texto.substring(0, texto.length - 1);
                            $('#vNroCaja').val(tex);

                        }
                    }

                }

            });
        }

    });
    $('#vEstablecimiento').keyup(function () {
        var counNrotimb = $('#vEstablecimiento').val().length;
        if (parseInt(counNrotimb) > 3) {
            $.confirm({
                title: 'AVISO!',
                content: 'El número de establecimiento no debe superar 3 carácteres.. ',
                type: 'red',
                buttons: {
                    Ok: {
                        text: 'OK',
                        btnClass: 'btn-dark',
                        keys: ['enter', 'shift'],
                        action: function () {
                            var texto = $('#vEstablecimiento').val();
                            var tex = texto.substring(0, texto.length - 1);
                            $('#vEstablecimiento').val(tex);

                        }
                    }

                }

            });
        }
        verificarcampoentero('vEstablecimiento');
    });
    $('#vNroDesde').keyup(function () {
        var counNrotimb = $('#vNroDesde').val().length;
        if (parseInt(counNrotimb) > 7) {
            $.confirm({
                title: 'AVISO!',
                content: 'El número de factura no debe superar 7 carácteres.. ',
                type: 'red',
                buttons: {
                    Ok: {
                        text: 'OK',
                        btnClass: 'btn-dark',
                        keys: ['enter', 'shift'],
                        action: function () {
                            var texto = $('#vNroDesde').val();
                            var tex = texto.substring(0, texto.length - 1);
                            $('#vNroDesde').val(tex);

                        }
                    }

                }

            });
        }
        verificarcampoentero('vNroDesde');
    });
    $('#vNroHasta').keyup(function () {
        var counNrotimb = $('#vNroHasta').val().length;
        if (parseInt(counNrotimb) > 7) {
            $.confirm({
                title: 'AVISO!',
                content: 'El número de factura no debe superar 7 carácteres.. ',
                type: 'red',
                buttons: {
                    Ok: {
                        text: 'OK',
                        btnClass: 'btn-dark',
                        keys: ['enter', 'shift'],
                        action: function () {
                            var texto = $('#vNroHasta').val();
                            var tex = texto.substring(0, texto.length - 1);
                            $('#vNroHasta').val(tex);

                        }
                    }

                }

            });
        }
        verificarcampoentero('vNroHasta');
    });


    $('#btnGuardarTimbrado').click(function () {
        insertarTimbrado(1, 1);
    });
    $('#btngenerarSQ').click(function () {
        generarSecuenciaDocumentos();
    });
    $('#vMverificacion').change(function () {
        if ($('#vMverificacion').is(':checked')) {
            if ($('#vMNrotimbrado').val() === "" || $('#vMEstablecimiento').val() === ""
                    || $('#vMNroCaja').val() === "" || $('#vMNroDesde').val() === ""
                    || $('#vMNroHasta').val() === "" | $('#vMFechaalta').val() === ""
                    || $('#vMfechainicio').val() === "" || $('#vMfechafin').val() === "") {
                $('#vMverificacion').prop('checked', false);
                $.alert({
                    title: 'AVISO..!!',
                    icon: 'glyphicon glyphicon-remove',
                    content: 'Algunos campos no fueron cargados correctamente..!',
                    type: 'red',
                    animation: 'scaleY'


                });

            } else {
                $('#btnGuardarTimbrado').show();
            }

        } else {
            $('#btnGuardarTimbrado').hide();
        }

    });

    $('#apeMontoapertura').keyup(function () {
        puntodecimal('apeMontoapertura');
    });
    $('#apeMontoapertura').blur(function () {
        var monto = $('#apeMontoapertura').val().replace(/\./g, '');
        if (parseInt(monto) < 100000) {
            $.alert({
                title: 'AVISO..!!',
                icon: 'glyphicon glyphicon-remove',
                content: 'El monto de Apertura debe ser mayor a 100.000GS!',
                type: 'red',
                animation: 'scaleY'


            });
        }
    });
    $('#listaDoc').change(function () {
        gettipofacturaTimbrado();
    });
    $('#btnGuardarAperVenta').click(function () {
        if ($('#apeMontoapertura').val() === "" || $('#aperFactTimrbados').val() === "") {
            $.alert({
                title: 'AVISO..!!',
                icon: 'glyphicon glyphicon-remove',
                content: 'Algunso datos no fueron cargados correctamente..',
                type: 'red',
                animation: 'scaleY'


            });
        } else {
            validacionAperturaVenta();
        }

    });
}


function insertarTimbrado(op, codventa) {
    $.confirm({
        title: 'Guardar',
        content: 'Desea Guardar los Registros ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {
                    jsonTimbrado = {
                        'opcion': op,
                        'vOpcion': codventa,
                        'vNrotimbrado': $('#vNrotimbrado').val(),
                        'vFechaVinicio': $('#vfechainicio').val(),
                        'vFechaVfin': $('#vfechafin').val(),
                        'vEstablecimiento': $('#vEstablecimiento').val(),
                        'vCaja': $('#vNroCaja').val(),
                        'vFacturadesde': $('#vNroDesde').val(),
                        'vFacturahasta': $('#vNroHasta').val(),
                        'vCodUser': $('#vCodIDuser').val(),
                        'vCodTipodocumento': $('#vTipoDocumento').val(),
                        'vCodSucursal': 1
                    };
                    $.ajax({
                        url: "/syscontrol/ventasSERVLET",
                        type: 'POST',
                        data: jsonTimbrado,
                        cache: false,
                        dataType: 'text',
                        success: function () {
                            setTimeout(function () {
                                $('#miTablaGenerada').find('tbody').find('tr').each(function () {
                                    jsonfacturas = {
                                        'opcion': 3,
                                        'vNroDoc': $(this).find("td").eq(4).html(),
                                        'vSecuencia': $(this).find("td").eq(2).html()
                                    };
                                    $.ajax({
                                        url: "/syscontrol/ventasSERVLET",
                                        type: 'POST',
                                        data: jsonfacturas,
                                        cache: false,
                                        dataType: 'text',
                                        success: function (resp) {
                                            $('#v_ventanaSQ').modal('hide');
                                            $.alert("Secuencias Generadas.!!")
                                            location.reload();
                                        }
                                    });
                                });
                            }, 1200);

                        }

                    });

                }
            },
            No: {
                text: 'No',
                btnClass: 'btn-red',
                keys: ['enter', 'shift'],
                action: function () {
                    $.alert('Cancelado !!');
                }
            }
        }
    });

}

function cerrarCaja(codigo) {
    $.confirm({
        title: 'Cerrar Caja',
        content: 'Desea Cerar la Caja ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {
                    jsonCerarCaja = {
                        'opcion': 10,
                        'codApertura': codigo
                    };
                    $.ajax({
                        url: "/syscontrol/ventasSERVLET",
                        type: 'POST',
                        data: jsonCerarCaja,
                        cache: false,
                        dataType: 'text',
                        success: function () {
//                            getAperCierreVentas();
                        }

                    });

                }
            },
            No: {
                text: 'No',
                btnClass: 'btn-red',
                keys: ['enter', 'shift'],
                action: function () {
                    $.alert('Cancelado !!');
                }
            }
        }
    });

}



var idxSc = 0;
function generarSecuenciaDocumentos() {
    var _v1 = $('#vNroDesde').val();
    var _v2 = $('#vNroHasta').val();
//    var  v1 = _v1.padStart(7,"0000000");
    var _cont;
    var v = 0;

    if (parseInt(_v2) < parseInt(_v1)) {
        $.alert({
            title: 'AVISO..!!',
            icon: 'glyphicon glyphicon-remove',
            content: 'Rango de valores inválidos..!',
            type: 'red',
            animation: 'scaleY'

        });
        $('#vNroDesde').focus();
    } else {
        if (_v1 === "" || _v2 === "" || _v1 <= 0 || _v2 <= 0) {
            $.alert({
                title: 'AVISO..!!',
                icon: 'glyphicon glyphicon-remove',
                content: 'No se ha encontrado rango de documentos..!',
                type: 'red',
                animation: 'scaleY'
            });
            $('#vNroDesde').focus();
        } else {
            var nroD = $("#vNroDesde").val().padStart(7, "0000000");
            var nroH = $("#vNroHasta").val().padStart(7, "0000000");
            $("#v_Cargando").modal('show');
            $('#v_ventanaSQ').modal('show');
            $("#vMNrotimbrado").val($("#vNrotimbrado").val());
            $("#vMEstablecimiento").val($("#vEstablecimiento").val());
            $("#vMNroCaja").val($("#vNroCaja").val());
            $("#vMNroDesde").val(nroD);
            $("#vMNroHasta").val(nroH);
            $("#vMFechaalta").val($("#vFechaalta").val());
            $("#vMfechainicio").val($("#vfechainicio").val());
            $("#vMfechafin").val($("#vfechafin").val());

            window.setTimeout(function () {
                $('#vTXT').html("Generación de Secuencias - ");
                $('#miTablaGenerada').find('tbody').find('tr').empty();
                $("#v_Cargando").modal('hide');
                var nroEstab = $('#vEstablecimiento').val();
                var nroCaja = $('#vNroCaja').val();
                for (var i = 0, i = parseInt(_v1); i <= parseInt(_v2); i++) {
                    v++;
                    var v11 = JSON.parse([i]);
                    var v13 = v11.toString();
                    var v14 = v13.padStart(7, "0000000");
                    var span = "<span class='alert-success'>Habilitado</span>";
                    $('#miTablaGenerada').append("<tr id=\'prod" + idxSc + "\'>\
                 <td style=display:none>" + nroEstab + "</td>\n\
                 <td style=display:none> " + nroCaja + "</td>\n\
                 <td >" + v + "</td>\n\
                 <td style=display:none>" + v14 + "</td>\n\
                 <td>" + nroEstab + "-" + nroCaja + "-" + v14 + "</td>\n\
                 <td>" + span + "</td>\n\
                </tr>");

                }
            }, 4000);





        }
    }


}

var idxTimbrado = 0;
function getTimbrados() {
    $('#mitablaTimbrados').find('tbody').find('tr').empty();
    jsonTimbrado = {
        'opcion': 2
    };
    $.ajax({
        url: "/syscontrol/ventasSERVLET",
        type: 'POST',
        data: jsonTimbrado,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var id = valor.idestado;
                var color;
                if (parseInt(id) === 5) {
                    color = '#d9edf7';
                    Informe = "<button class='btn btn-sm btn-outline-primary' onclick=\"$(\'#prod" + idxTimbrado + "\');infTimbrado(1)\">Informe</button>";
                } else if (parseInt(id) === 10) {
                    color = '#acffac';
                    Informe = "<button class='btn btn-sm btn-outline-primary' onclick=\"$(\'#prod" + idxTimbrado + "\');infTimbrado(1)\">Informe</button>";

                }
                $("#mitablaTimbrados").append($("<tr>").append($(
                        "<td>" + valor.idtimbrado + "</td>" +
                        "<td>" + valor.tipodocumento + "</td>" +
                        "<td>" + valor.numero + "</td>" +
                        "<td>" + valor.fecha_carga + "</td>" +
                        "<td>" + valor.fecha_vigencia_inicio + "</td>" +
                        "<td>" + valor.fecha_vigencia_final + "</td>" +
                        "<td>" + valor.factura_desde + "</td>" +
                        "<td>" + valor.factura_hasta + "</td>" +
                        "<td bgcolor=" + color + ">" + valor.estado + "</td>" +
                        "<td style='text-align: center'> " + Informe + "</td>")));
            });


        }

    });
}


var idxAperCierre = 0;
function getAperCierreVentas() {
    $('#mitablaaperturaCierreCajaVentas').find('tbody').find('tr').empty();
    jsonAperCierreVentas = {
        'opcion': 7
    };
    $.ajax({
        url: "/syscontrol/ventasSERVLET",
        type: 'POST',
        data: jsonAperCierreVentas,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var id = valor.idestado;
                var color;
                var fcierre;
                if (parseInt(id) === 1) {
                    color = '#d9edf7';
                    Informe = "<button class='btn btn-sm btn-outline-primary' onclick=\"$(\'#prod" + idxAperCierre + "\')\">Informe</button>";
                    cerrarv = "<button class='btn btn-sm btn-outline-danger' onclick=\"$(\'#prod" + idxAperCierre + "\');caja()\">Cerrar</button>";
                } else if (parseInt(id) === 2) {
                    color = 'red';
                    Informe = "<button class='btn btn-sm btn-outline-primary' onclick=\"$(\'#prod" + idxAperCierre + "\')\">Informe</button>";
                    cerrarv = "<button disabled class='btn btn-sm btn-outline-danger'>Cerrar</button>";
                }
                $("#mitablaaperturaCierreCajaVentas").append($("<tr id=\'cod" + idxAperCierre + "\'>").append($(
                        "<td>" + valor.idaperturacierre + "</td>" +
                        "<td>" + valor.fecha_apertura + "</td>" +
                        "<td>" + valor.monto_apertura + "</td>" +
                        "<td>" + valor.cajero + "</td>" +
                        "<td>" + valor.caja + "</td>" +
                        "<td style='text-align: center'>" + valor.fecha_cierre + "</td>" +
                        "<td bgcolor=" + color + ">" + valor.estado + "</td>" +
                        "<td style=display:none>" + valor.idcaja + "</td>" +
                        "<td style=display:none>" + valor.idcajero + "</td>" +
                        "<td style='text-align: center'> " + Informe + "" + cerrarv + "</td>")));

                $('#mitablaaperturaCierreCajaVentas').each(function () {
                    var v = $(this).find("td").eq(5).html();
                    if (v === 'undefined')
                        $(this).find("td").eq(5).html("   /   /    ");

                });
            });


        }

    });
}

function caja() {
    var num;
    $('#mitablaaperturaCierreCajaVentas tr').click(function () {
        num = $(this).find("td").eq(0).html();
        v_cerrarCaja(num);
    });
    function v_cerrarCaja(valor) {
        $.confirm({
            title: 'Cerrar Caja',
            content: 'Desea Cerar la Caja ?',
            buttons: {
                Si: {
                    text: 'SI',
                    btnClass: 'btn-success',
                    keys: ['enter', 'shift'],
                    action: function () {
                        jsonCerarCaja = {
                            'opcion': 10,
                            'codApertura': valor
                        };
                        $.ajax({
                            url: "/syscontrol/ventasSERVLET",
                            type: 'POST',
                            data: jsonCerarCaja,
                            cache: false,
                            dataType: 'text',
                            success: function () {
//                            getAperCierreVentas();
                            }

                        });

                    }
                },
                No: {
                    text: 'No',
                    btnClass: 'btn-red',
                    keys: ['enter', 'shift'],
                    action: function () {
                        $.alert('Cancelado !!');
                    }
                }
            }
        });

    }
}

function getCombos(cod, variable) {
    $('#' + variable).find('tbody').find('tr').empty();
    jsonCajero = {
        'opcion': cod
    };
    $.ajax({
        url: "/syscontrol/ventasSERVLET",
        type: 'POST',
        data: jsonCajero,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                $("#" + variable).append("<option value= \"" + valor.idGenerico + "\"> " + valor.decripGenerico + "</option>");
            });


        }

    });
}
function gettipofacturaTimbrado() {
//    $('#aperFactTimrbados').find('tbody').find('tr').empty();
    josn = {
        'opcion': 6,
        'vTipoDoc': $('#listaDoc').val()
    };
    $.ajax({
        url: "/syscontrol/ventasSERVLET",
        type: 'POST',
        data: josn,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {
                $.each(resp, function (indice, valor) {
                    $('#aperFactTimrbados').val(valor.numero);
                    $('#aperIDfacTimbrados').val(valor.idtimbrado);
                    $("#aperFactTimrbados").append("<option value= \"" + valor.idtimbrado + "\"> " + valor.numero + "</option>");
                });
            } else {
                $('#aperFactTimrbados').val(null);
                $('#aperIDfacTimbrados').val(null);
            }



        }

    });
}

function seleccion() {
    var a = $('#listacajeros option:selected').text();
    alert(a);
}

function infTimbrado(v_cod) {
    var t = 0;
    $('#mitablaTimbrados tr').click(function () {
        t = $(this).find("td").eq(0).html();
        v_timbrados(t, v_cod);
    });

    function v_timbrados(valor, cod) {
        var vUser = $('#vUsername').val();
        window.open(`reportesVentas.jsp?idtimbrado=${valor}&vUser=${vUser}&vcodigo=${cod}`, "_blank");
        location.reload();

    }

}