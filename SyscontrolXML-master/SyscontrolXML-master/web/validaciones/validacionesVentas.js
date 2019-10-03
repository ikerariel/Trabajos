$(document).ready(function () {
    validad();


});

function validad() {
    getTimbrados();
    $('#vNrotimbrado').keyup(function () {
        var counNrotimb = $('#vNrotimbrado').val().length;
        if (parseInt(counNrotimb) > 8) {
            $.confirm({
                title: 'AVISO!',
                content: 'El número de timbrado no debe superar 8 carácteres.. ',
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
        verificarcampoentero('vNrotimbrado');
    });
    $('#vNroCaja').keyup(function () {
        var counNrotimb = $('#vNroCaja').val().length;
        if (parseInt(counNrotimb) > 3) {
            $.confirm({
                title: 'AVISO!',
                content: 'El número de caja no debe superar 3 carácteres.. ',
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
        verificarcampoentero('vNroCaja');
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

function infTimbrado(v_cod) {
    var t = 0;
    $('#mitablaTimbrados tr').click(function () {
        t = $(this).find("td").eq(0).html();
        v_timbrados(t, v_cod);
    });

    function v_timbrados(valor,cod) {
        var vUser = $('#vUsername').val();
        window.open(`reportesVentas.jsp?idtimbrado=${valor}&vUser=${vUser}&vcodigo=${cod}`, "_blank");
        location.reload();

    }

}