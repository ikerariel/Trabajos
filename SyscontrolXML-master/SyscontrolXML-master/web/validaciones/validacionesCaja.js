$(document).ready(function () {
    opcionesBotonoesCaja();
    getapertura();
    getcierre();
//------------------------------------------------//
//------------------------------------------------//

});


//--CALCULA EL TOTAL DE RECARGA CON CADA TIPEO DE TECLADO-////
function totalRecargas() {
    var v_tigo = $('#v_montotigo').val().replace(/\./g, '');
    var v_claro = $('#v_montoclaro').val().replace(/\./g, '');
    var v_personal = $('#v_montopersonal').val().replace(/\./g, '');
    var v_vox = $('#v_montovox').val().replace(/\./g, '');

    var valor = 0;

    if (v_tigo === "") {
        v_tigo = 0;
    }
    if (v_claro === "") {
        v_claro = 0;
    }
    if (v_personal === "") {
        v_personal = 0;
    }
    if (v_vox === "") {
        v_vox = 0;
    }

    valor = valor + parseInt(v_tigo) + parseInt(v_claro) + parseInt(v_personal) + parseInt(v_vox);
    $('#total_recarga').val(valor);
    if ($('#total_recarga').val() === "") {

    } else {
        puntodecimal('total_recarga');
    }
}
//--------------------------------------------/////
//--------------------------------------------/////





//--CALCULA EL TOTAL DE GIROS Y BILLETERA CON CADA TIPEO DE TECLADO-////
function totalGiroBilletera() {

    var v_girotigo = $('#giro_tigo').val().replace(/\./g, '');
    var v_giroclaro = $('#giro_claro').val().replace(/\./g, '');
    var v_giropersonal = $('#giro_personal').val().replace(/\./g, '');
    var v_girovox = $('#giro_vox').val().replace(/\./g, '');

    var valor = 0;

    if (v_girotigo === "") {
        v_girotigo = 0;
    }
    if (v_giroclaro === "") {
        v_giroclaro = 0;
    }
    if (v_giropersonal === "") {
        v_giropersonal = 0;
    }
    if (v_girovox === "") {
        v_girovox = 0;
    }

    valor = valor + parseInt(v_girotigo) + parseInt(v_giroclaro) + parseInt(v_giropersonal) + parseInt(v_girovox);
    $('#total_girobilletera').val(valor);
    if ($('#total_girobilletera').val() === "") {

    } else {
        puntodecimal('total_girobilletera');
    }
}
//--------------------------------------------/////
//--------------------------------------------/////





//--CALCULA EL TOTAL GRAL DE RECARGA Y GIROS CON CADA TIPEO DE TECLADO-////
function totalGral() {
    total = 0;
    var recarga = $('#total_recarga').val().replace(/\./g, '');
    var giro = $('#total_girobilletera').val().replace(/\./g, '');
    if (recarga === "") {
        recarga = 0;
    }
    if (giro === "") {
        giro = 0;
    }

    total = total + parseInt(recarga) + parseInt(giro);
    $('#total_general').val(total);
    if ($('#total_general').val() === "") {

    } else {
        puntodecimal('total_general');
    }
}
//--------------------------------------------/////
//--------------------------------------------/////


///----LOS BOTONES INICIAN CON SUS FUNCIONES----
///----AL HACER CLICK O TIPEAR EN LOS CAMPOS EJECTAN LAS FUNCIONES---////
function opcionesBotonoesCaja() {
    $('#btnguardarapertura').click(function () {
        mensajeApertura();
    });
    $('#btnagregarMontos').click(function () {
        verificaapertura();
        vaciarcampos();
        $('#btnguardarapertura').show();
    });
    $('#giro_tigo').keyup(function () {
        verificarcampoentero('giro_tigo');
        puntodecimal('giro_tigo');
        totalRecargas();
        totalGiroBilletera();
        totalGral();
    });
    $('#giro_claro').keyup(function () {
        verificarcampoentero('giro_claro');
        puntodecimal('giro_claro');
        totalRecargas();
        totalGiroBilletera();
        totalGral();
    });
    $('#giro_personal').keyup(function () {
        verificarcampoentero('giro_personal');
        puntodecimal('giro_personal');
        totalRecargas();
        totalGiroBilletera();
        totalGral();
    });
    $('#giro_vox').keyup(function () {
        verificarcampoentero('giro_vox');
        puntodecimal('giro_vox');
        totalRecargas();
        totalGiroBilletera();
        totalGral();
    });
    $('#v_montotigo').keyup(function () {
        verificarcampoentero('v_montotigo');
        puntodecimal('v_montotigo');
        totalRecargas();
        totalGiroBilletera();
        totalGral();
    });
    $('#v_montoclaro').keyup(function () {
        verificarcampoentero('v_montoclaro');
        puntodecimal('v_montoclaro');
        totalRecargas();
        totalGiroBilletera();
        totalGral();
    });
    $('#v_montopersonal').keyup(function () {
        verificarcampoentero('v_montopersonal');
        puntodecimal('v_montopersonal');
        totalRecargas();
        totalGiroBilletera();
        totalGral();
    });
    $('#v_montovox').keyup(function () {
        verificarcampoentero('v_montovox');
        puntodecimal('v_montovox');
        totalRecargas();
        totalGiroBilletera();
        totalGral();
    });

}
///--------------------------------------------------------//////
///--------------------------------------------------------//////


////---GUARDA LA APERTURA Y EL DETALLE DE RECARGAS Y GIROS/BILLETERAS---///
function guardaraperturas() {
    var cargatigo = $('#v_montotigo').val().replace(/\./g, '');
    var cargaclaro = $('#v_montoclaro').val().replace(/\./g, '');
    var cargapersonal = $('#v_montopersonal').val().replace(/\./g, '');
    var cargavox = $('#v_montovox').val().replace(/\./g, '');
    var girotigoo = $('#giro_tigo').val().replace(/\./g, '');
    var giroclaro = $('#giro_claro').val().replace(/\./g, '');
    var giropersonal = $('#giro_personal').val().replace(/\./g, '');
    var girovox = $('#giro_vox').val().replace(/\./g, '');

    if (cargatigo === "") {
        cargatigo = 0;
    }
    if (cargaclaro === "") {
        cargaclaro = 0;
    }
    if (cargapersonal === "") {
        cargapersonal = 0;
    }
    if (cargavox === "") {
        cargavox = 0;
    }
    if (girotigoo === "") {
        girotigoo = 0;
    }
    if (giroclaro === "") {
        giroclaro = 0;
    }
    if (giropersonal === "") {
        giropersonal = 0;
    }
    if (girovox === "") {
        girovox = 0;
    }
    datosJSOND = {
        "opcion": 1,
        "aper_comentario": $('#v_observación').val(),
        "aper_idusuario": 1,
        "aper_idsucursal": 1,
        "aper_montotigo": cargatigo,
        "aper_montoclaro": cargaclaro,
        "aper_montopersonal": cargapersonal,
        "aper_montovox": cargavox,
        "aper_girotigo": girotigoo,
        "aper_giroclaro": giroclaro,
        "aper_giropersonal": giropersonal,
        "aper_girovox": girovox

    };
    $.ajax({
        url: "/syscontrol/aperturaSERVLETXML",
        type: 'POST',
        data: datosJSOND,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#mitablaapertura').find('tbody').find('tr').empty();
            getapertura();
            $('#apertura').modal('hide');
            getcierre();
            location.reload();


        },
        error: function () {
            $.alert('No se puedo realizar la operación !!');
        }
    });



}
///--------------------------------------------------------//////
///--------------------------------------------------------//////
////---ACTUALIZA APERTURA---///
function actualizarapertura(cod, num) {
    switch (num) {
        case 1:
            updatedatos = {
                "opcion": 4,
                "aper_codapertura": cod,
                "v_valor": num,
                "aper_comentario": $('#v_observación').val(),
                "aper_idusuario": 1,
                "aper_idsucursal": 1,
                "aper_montotigo": $('#v_montotigo').val().replace(/\./g, ''),
                "aper_montoclaro": $('#v_montoclaro').val().replace(/\./g, ''),
                "aper_montopersonal": $('#v_montopersonal').val().replace(/\./g, ''),
                "aper_montovox": $('#v_montovox').val().replace(/\./g, ''),
                "aper_girotigo": $('#giro_tigo').val().replace(/\./g, ''),
                "aper_giroclaro": $('#giro_claro').val().replace(/\./g, ''),
                "aper_giropersonal": $('#giro_personal').val().replace(/\./g, ''),
                "aper_girovox": $('#giro_vox').val().replace(/\./g, '')

            };
            $.ajax({
                url: "/syscontrol/aperturaSERVLETXML",
                type: 'POST',
                data: updatedatos,
                cache: false,
                dataType: 'text',
                success: function () {
                    $.alert('Guardado correctamente !!');
                    $('#mitablaapertura').find('tbody').find('tr').empty();
                    getapertura();
                    $('#apertura').modal('hide');
                    location.reload();

                },
                error: function () {
                    $.alert('No se puedo realizar la operación !!');
                }
            });
            break;

        case 2:
            updatedatos = {
                "opcion": 4,
                "aper_codapertura": cod,
                "v_valor": num


            };
            $.ajax({
                url: "/syscontrol/aperturaSERVLETXML",
                type: 'POST',
                data: updatedatos,
                cache: false,
                dataType: 'text',
                success: function () {
                    $.alert('Guardado correctamente !!');
                    $('#mitablaapertura').find('tbody').find('tr').empty();
                    getapertura();
                    $('#apertura').modal('hide');
                    location.reload();

                },
                error: function () {
                    $.alert('No se puedo realizar la operación !!');
                }
            });
            break;
    }




}
///--------------------------------------------------------//////
///--------------------------------------------------------//////





////--RECUPERA LA LISTA DE APERTURAS CREADAS, SOLO LAS APERTURAS EN ESTADO
////-- ABIERTO Y CERRADO--////////////////////////////////////////////////
var index = 0;
function getapertura() {
    aperturaDatos = {
        'opcion': 2
    };
    $.ajax({
        url: "/syscontrol/aperturaSERVLETXML",
        type: 'POST',
        data: aperturaDatos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                if (valor.estado === 'CERRADA') {
                    var opcion = "<span class='badge badge-pill badge-danger'>" + valor.estado + "</span>";
                    var bonton = "<button class='btn btn-sm btn-outline-success' disabled=''>Modificar</button>";
                    var eliminar = "<button class='btn btn-sm btn-outline-danger' disabled=''>Eliminar</button>";
                } else {
                    opcion = "<span class='badge badge-pill badge-success'>" + valor.estado + "</span>";
                    bonton = "<button class='btn btn-sm btn-outline-success' onclick=\"$(\'#prod" + index + "\');modificarapertura()\">Modificar</button>";
                    eliminar = "<button class='btn btn-sm btn-outline-danger' onclick=\"$(\'#prod" + index + "\');eliminarapertura()\">Eliminar</button>";
                    $('#btnguardarecarga').show();
                }
                index++;
                $("#mitablaapertura").append($("<tr id=\'cod" + index + "\'>").append($(
                        "<td>" + valor.idapertura + "</td>" +
                        "<td>" + valor.fecha_apertura + "</td>" +
                        "<td>" + valor.totalrecarga + "</td>" +
                        "<td>" + valor.totalgiro + "</td>" +
                        "<td>" + valor.totalgral + "</td>" +
                        "<td style='text-align: center'>" + opcion + "</td>" +
                        "<td>" + bonton + " " + eliminar + "</td>" +
                        "<td>" + valor.usuario + " </td>")));
            });


        }

    });
}

///-------RECUPERA EL INDICE DEL FILA SELECCIONADA-----////
function opcionModificarEliminar() {
    $('#mitablaapertura tr').click(function () {
        var codapertura = $(this).find("td").eq(0).html();
        obtenerapertura(codapertura);


    });
}
////---------------------------------------------------------------//////
////---------------------------------------------------------------//////




///-------RECUPERA LOS DATOS DE LA APERTURA POR EL IDAPERTURA----//////

function obtenerapertura(cod) {
    vaciarcampos();
    datos = {
        'opcion': 3,
        'aper_nroapertura': cod
    };
    $.ajax({
        url: "/syscontrol/aperturaSERVLETXML",
        type: 'POST',
        data: datos,
        cache: false,
        success: function (resp) {
//            $('#apertura').modal('show');
            $('#btnguardarapertura').hide();
            $('#btnupdateapertura').show();
            $.each(resp, function (indice, value) {
                $('#v_montotigo').val(value.montotigo);
                $('#v_montoclaro').val(value.montoclaro);
                $('#v_montopersonal').val(value.montopersonal);
                $('#v_montovox').val(value.montovox);
                $('#giro_tigo').val(value.giromontotigo);
                $('#giro_claro').val(value.giromontoclaro);
                $('#giro_personal').val(value.giromontopersonal);
                $('#giro_vox').val(value.giromontovox);
                $('#v_observación').val(value.comentario);
            });
            puntodecimal('v_montotigo', 'v_montoclaro',
                    'v_montopersonal', 'v_montovox', 'giro_tigo', 'giro_claro',
                    'giro_personal', 'giro_vox');
            totalRecargas();
            totalGiroBilletera();
            totalGral();


        }

    });
}
////---------------------------------------------------------------//////
////---------------------------------------------------------------//////



///----LIMPIA LOS CAMPOS----///////

function vaciarcampos() {
    $('#v_montotigo').val(null);
    $('#v_montoclaro').val(null);
    $('#v_montopersonal').val(null);
    $('#v_montovox').val(null);
    $('#giro_tigo').val(null);
    $('#giro_claro').val(null);
    $('#giro_personal').val(null);
    $('#giro_vox').val(null);
    $('#total_recarga').val(null);
    $('#total_girobilletera').val(null);
    $('#total_general').val(null);

}
////---------------------------------------------------------------//////
////---------------------------------------------------------------//////

///----VERIFICA SI HAY ALGÚN APERTURA ABIERTA
///---SI HAY NO PERMITIRÁ CARGAR UNA NUEVA APERTURA,CASO CONTRARIO HABILITA--////
function verificaapertura() {
    var valor = "";
      nFilas = $("#mitablaapertura tr").length - 1;
      if(parseInt(nFilas)>0){
           $('#mitablaapertura').find('tbody').each(function () {
        valor = $(this).find("td").eq(5).text();
        if (valor === "CERRADA") {
            $('#apertura').modal('show');

        } else {
            $.confirm({
                title: 'AVISO!',
                content: 'Aún hay apertuas abiertas.. ',
                type: 'red',
                buttons: {
                    Ok: {
                        text: 'OK',
                        btnClass: 'btn-dark',
                        keys: ['enter', 'shift'],
                        action: function () {

                        }
                    }

                }

            });
        }
    });
      }else{
           $('#apertura').modal('show');
      }
   

}
////---------------------------------------------------------------//////
////---------------------------------------------------------------//////


function modificarapertura() {
    var codapertura = 0;
    $('#mitablaapertura tr').click(function () {
        codapertura = $(this).find("td").eq(0).html();
        obtenerapertura(codapertura);
    });
    $('#apertura').modal('show');
    $('#btnupdateapertura').click(function () {
        actualizarapertura(codapertura, 1);
    });
}



var index = 0;
function getcierre() {
    cierredatos = {
        'opcion': 5
    };
    $.ajax({
        url: "/syscontrol/aperturaSERVLETXML",
        type: 'POST',
        data: cierredatos,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                var estado = valor.idestado;
                if (estado === 1) {
                    Informe = "<button class='btn btn-sm btn-outline-primary' onclick=\"$(\'#prod" + index + "\');informecierre(1)\">Informe Recarga</button>";
                    Informes = "<button class='btn btn-sm btn-outline-info' onclick=\"$(\'#prod" + index + "\');informecierre(2)\">Informe Giro</button>";
                    cierre = "<button class='btn btn-sm btn-outline-danger' onclick=\"$(\'#prod" + index + "\');cierre_apertura()\">Cierre</button>";
                } else if (estado === 2) {
                    Informe = "<button class='btn btn-sm btn-outline-primary' onclick=\"$(\'#prod" + index + "\');informecierre(1)\">Informe Recarga</button>";
                    Informes = "<button class='btn btn-sm btn-outline-info' onclick=\"$(\'#prod" + index + "\');informecierre(2)\">Informe Giro</button>";
                    cierre = "<button class='btn btn-sm btn-outline-danger' disabled=''>Cierre</button>";
                }

                index++;
                $("#mitablacierreapertura").append($("<tr id=\'cod" + index + "\'>").append($(
                        "<td>" + valor.idapertura + "</td>" +
                        "<td>" + valor.fecha_apertura + "</td>" +
                        "<td>" + valor.Tigo + "</td>" +
                        "<td>" + valor.Claro + "</td>" +
                        "<td>" + valor.Personal + "</td>" +
                        "<td>" + valor.Vox + "</td>" +
                        "<td>" + Informe + " " + Informes + "</td>" +
                        "<td>" + cierre + " </td>")));
            });


        }

    });
}



function cerrarapertura(cod) {
    cerrar = {
        'opcion': 6,
        'nroapertura': cod
    };
    $.ajax({
        url: "/syscontrol/aperturaSERVLETXML",
        type: 'POST',
        data: cerrar,
        cache: false,
        dataType: 'text',
        success: function () {
            $.alert('Guardado correctamente !!');
            $('#mitablacierreapertura').find('tbody').find('tr').empty();
            getcierre();
            location.reload();
        }

    });
}


function consultarecargas(cod) {
    consulta = {
        'opcion': 6,
        'v_nroapertura': cod
    };
    $.ajax({
        url: "/syscontrol/recargaSERVLETXML",
        type: 'POST',
        data: consulta,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {

                $.confirm({
                    title: 'AVISO!',
                    content: 'La Apertura no puede ser Eliminada, la misma ya cuenta con recargas guardadas.. ',
                    type: 'red',
                    buttons: {
                        Ok: {
                            text: 'OK',
                            btnClass: 'btn-dark',
                            keys: ['enter', 'shift'],
                            action: function () {

                            }
                        }

                    }

                });

            } else {
                actualizarapertura(cod, 2);
            }


        }
    });
}
function consultainformerecarga(cod) {
    consultajson = {
        'opcion': 6,
        'v_nroapertura': cod
    };
    $.ajax({
        url: "/syscontrol/recargaSERVLETXML",
        type: 'POST',
        data: consultajson,
        cache: false,
        success: function (resp) {
            if (JSON.stringify(resp) != '[]') {

                $.confirm({
                    title: 'AVISO!',
                    content: 'No se encuentra datos a visualizar ',
                    type: 'red',
                    buttons: {
                        Ok: {
                            text: 'OK',
                            btnClass: 'btn-dark',
                            keys: ['enter', 'shift'],
                            action: function () {

                            }
                        }

                    }

                });

            } else {

            }


        }
    });
}