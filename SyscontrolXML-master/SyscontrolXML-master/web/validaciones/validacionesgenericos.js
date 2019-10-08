$(document).ready(function () {
    fechaactual();
    comboestado();
    verificarApertura_paraRecarga();
    opcioninf();
    cargarcombooperacion();
    

});

//------FECHA ACTUAL-----///
function fechaactual() {
    var fv = new Date();
    $('#v_fechaactual').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
    $('#fecha_ciere').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
    $('#vFechaalta').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());
    $('#apeFecha').val(fv.getDate() + "/" + (fv.getMonth() + 1) + "/" + fv.getFullYear());

}

//--------------------------/////
//--------------------------/////

function opcioninf() {
    $('#btninf_pago').click(function () {
        $('#btninfrecarga').hide();
        $('#btninfgiro').hide();
        $('#btninfpago').show();
        $('#divCombo').show();
        $('#v_informes').modal('show');
       
        $('#txt_inf').html('Informe por Rango de Fechas - PAGOS');
    });
    $('#btninfpago').click(function () {
        var oper = $('#vOpcioninforme').val();
        var estado = $('#vOpcionpago').val();
        if (parseInt(oper) === 1 && parseInt(estado) === 7) {
            infpagos(5);
        } else if (parseInt(oper) === 1 && parseInt(estado) === 6) {
            infpagos(1);
        }else if(parseInt(oper) === 2 && parseInt(estado) === 7){
             infpagos(6);
        }else if(parseInt(oper) === 2 && parseInt(estado) === 6){
            infpagos(2);
        }



//       
    });
    $('#btninfgiro').click(function () {
        infpagosgiro();

//       
    });
    $('#btninfrecarga').click(function () {
        infrecarga();

//       
    });
    $('#btninf_recarga').click(function () {
        $('#btninfrecarga').show();
        $('#btninfgiro').hide();
        $('#divCombo').hide();
        $('#btninfpago').hide();
        $('#v_informes').modal('show');
        $('#txt_inf').html('Informe por Rango de Fechas - Recargas de Saldo');
    });
    $('#btninf_giro').click(function () {
        $('#btninfrecarga').hide();
        $('#btninfgiro').show();
        $('#btninfpago').hide();
        $('#divCombo').hide();
        $('#v_informes').modal('show');
        $('#txt_inf').html('Informe por Rango de Fechas - Giros/Billetera');
    });
}


///--VERIFICA SI EXISTE ALGUNA APERTURA ABIERTA PARA PODER HABILITAR EL BOTON GUARDAR DE LA RECARGA--////
function verificarApertura_paraRecarga() {
    aperturaDatos = {
        'opcion': 2
    };
    $.ajax({
        url: "/syscontrol/aperturaSERVLETXML",
        type: 'POST',
        data: aperturaDatos,
        cache: false,
        success: function (resp) {
            var v_estadoabierto;
            var v_estadocerrado;
//             if (JSON.stringify(resp) != '[]') {
            $.each(resp, function (indice, valor) {
                if (valor.estado === 'ABIERTA') {
                    v_estadoabierto = 1;

                } else {
                    v_estadocerrado = 2;

                }

            });
//             }else{
//                 
//             }

            if (v_estadoabierto === 1) {
                $('#btnguardarecarga').show();
                $('#btnguardargiro').show();

            } else {

            }

        }

    });
}
//--------------------------------------------------------------------///
//--------------------------------------------------------------------///



//--VERIFICA CADA CAMPO, SOLO PERMITE NUMEROS --////
function verificarcampoentero(campo) {

    var variable = $('#' + campo).val();
    var resultado = isNaN(variable);
    switch (resultado) {
        case true:
            $('#' + campo).focus();
            $('#' + campo).val(null);
            break;
        case false:
            break;
        default :
            break;
    }

}
//--------------------------------------------/////
//--------------------------------------------/////



//--SE AGREGA EL PUNTO DECIMAL A LOS CAMPOS--////
function puntodecimal(...uno) {
    for (let numero of uno) {
        var num = document.getElementById(numero).value.replace(/\./g, '');
        if (!isNaN(num)) {
            num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
            num = num.split('').reverse().join('').replace(/^[\.]/, '');
            document.getElementById(numero).value = num;
            $('#' + numero).css('font-weight', 'bold');

        } else {
//            alert('Solo se permiten numeros');
            document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');
        }
    }


}
//--------------------------------------------/////
//--------------------------------------------/////


//---------MENSAJES DE CONFIRMACION DE APERTURA DE RECARGAS Y GIROS-----------/////
function mensajeApertura() {

    $.confirm({
        title: 'Guardar',
        content: 'Desea Guardar los Registros ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {
                    guardaraperturas();
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
//--------------------------------------------/////
//--------------------------------------------/////



//---------MENSAJES DE CONFIRMACION DE RECARGAS-----------/////
function guardarrecarga() {
    $.confirm({
        title: 'Guardar',
        content: 'Desea Guardar la Recarga ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {

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
//--------------------------------------------/////
//--------------------------------------------/////



//---------ELIMINA LA APERTURA ( ANULA EN LA BD )-----------/////
function eliminarapertura() {
    var cod = 0;
    $('#mitablaapertura tr').click(function () {
        cod = $(this).find("td").eq(0).html();
    });
    $.confirm({
        title: 'Eliminar',
        content: 'Desea Eliminar el Registro ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {
                    consultarecargas(cod);
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
//--------------------------------------------/////
//--------------------------------------------/////

//---------ELIMINA LA RECARGA -----------/////
function eliminarrecarga() {
    var cod = 0;
    $('#mitablarecarga tr').click(function () {
        cod = $(this).find("td").eq(1).html();
    });
    $.confirm({
        title: 'Eliminar',
        content: 'Desea Eliminar el Registro ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {
                    eliminarrecargas(cod);
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
//--------------------------------------------/////
//--------------------------------------------/////
//
//
//
//
//
//---------ELIMINA LA RECARGA -----------/////
function eliminargiros() {
    var cod = 0;
    $('#mitablagiros tr').click(function () {
        cod = $(this).find("td").eq(0).html();
    });
    $.confirm({
        title: 'Eliminar',
        content: 'Desea Eliminar el Registro ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {
                    eliminargiro(cod);
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
//--------------------------------------------/////
//--------------------------------------------/////
//
//
//
//
//
//---------CERRAR APERTURA -----------/////
function cierre_apertura() {
    var cod = 0;
    $('#mitablacierreapertura tr').click(function () {
        cod = $(this).find("td").eq(0).html();
    });
    $.confirm({
        title: 'Cierre de Caja',
        content: 'Desea Cerrar la Caja ?',
        buttons: {
            Si: {
                text: 'SI',
                btnClass: 'btn-success',
                keys: ['enter', 'shift'],
                action: function () {
                    cerrarapertura(cod);
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
//--------------------------------------------/////
//--------------------------------------------/////




function generarticket(vcod) {
    if (vcod === 1) {
        var codrecarga = 0;
        $('#mitablarecarga tr').click(function () {
            codrecarga = $(this).find("td").eq(1).html();
            v_generarticket(codrecarga, vcod);
        });
    } else if (vcod === 2) {
        var vgiro = 0;
        $('#mitablagiros tr').click(function () {
            vgiro = $(this).find("td").eq(0).html();
            v_generarticket(vgiro, vcod);
        });
    } else if (vcod === 3) {
        var crecarga = 0;
        $('#mitablaconsultarecarga tr').click(function () {
            crecarga = $(this).find("td").eq(0).html();
            v_generarticket(crecarga, 1);
        });
    } else if (vcod === 4) {
        var cgiro = 0;
        $('#mitablaconsultagiro tr').click(function () {
            cgiro = $(this).find("td").eq(0).html();
            v_generarticket(cgiro, 2);
        });
    } else if (vcod === 5) {
        var vPago = 0;
        $('#mitablapagos tr').click(function () {
            vPago = parseInt($(this).find("td").eq(6).html());
//            alert(vPago);
            v_generarticket(vPago, 3);
        });
    } else if (vcod === 6) {
        var vPagor = 0;
        $('#mitablapagosrecargas tr').click(function () {
            vPagor = parseInt($(this).find("td").eq(7).html());
//            alert(vPagor);
            v_generarticket(vPagor, 4);
        });
    }

    function v_generarticket(cod, valor) {
//        window.open("reporteticket.jsp?iddetallerecargas=" + cod + " ", "_blank");
        window.open(`reporteticket.jsp?id=${cod}&vcodigo=${valor}`, "_blank");
//        window.open(`reporteticket.jsp?iddetallerecargas=${cod}&fechahasta=${hasta}`,"_blank");

    }

}

function informecierre(v_cod) {

    if (v_cod === 1) {
        var codcierre = 0;
        $('#mitablacierreapertura tr').click(function () {
            codcierre = $(this).find("td").eq(0).html();
            v_cierrre(codcierre, v_cod);
        });

    } else if (v_cod === 2) {
        var codcierregiro = 0;
        $('#mitablacierreapertura tr').click(function () {
            codcierregiro = $(this).find("td").eq(0).html();
            v_cierrre(codcierregiro, v_cod);
        });
    }
    function v_cierrre(cod, valor) {
        window.open(`reportesgenericos.jsp?idapertura=${cod}&vcodigo=${valor}`, "_blank");
        location.reload();
//        window.open("reportesgenericos.jsp?idapertura=" + cod + " ", "_blank");

    }

}


function buscadorgenericotable(tabla, buscador) {
    var tableReg = document.getElementById(tabla);
    var searchText = document.getElementById(buscador).value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}
function cargarcombooperacion() {

    var array = ["Giro/Billetera", "Recarga de Saldo"];
    var varray = ["1", "2"];

    for (var i in array) {
        document.getElementById("vOpcioninforme").innerHTML += "<option value='" + varray[i] + "'>" + array[i] + "</option>";
    }

}



function infpagosgiro() {
    var cod = 3;
    var fdesde = $('#vFechadesde').val();
    var fhasata = $('#vFechahasta').val();
    var vUser = $('#vUsername').val();
    window.open(`reporteporfecha.jsp?fechadesde=${fdesde}&fechahasta=${fhasata}&user=${vUser}&codigo=${cod}`, "_blank");
    location.reload();
}

function infrecarga() {
    var cod = 4;
    var fdesde = $('#vFechadesde').val();
    var fhasata = $('#vFechahasta').val();
    var vUser = $('#vUsername').val();
    window.open(`reporteporfecha.jsp?fechadesde=${fdesde}&fechahasta=${fhasata}&user=${vUser}&codigo=${cod}`, "_blank");
    location.reload();
}

function infpagos(v) {
    var fdesde = $('#vFechadesde').val();
    var fhasata = $('#vFechahasta').val();
    var vUser = $('#vUsername').val();
    if (parseInt(v) === 1) { //giro pagados
        var cod = 1;
        window.open(`reporteporfecha.jsp?fechadesde=${fdesde}&fechahasta=${fhasata}&user=${vUser}&codigo=${cod}`, "_blank");
    } else if (parseInt(v) === 2) {
        var cod = 2; //recargas pagados
        window.open(`reporteporfecha.jsp?fechadesde=${fdesde}&fechahasta=${fhasata}&user=${vUser}&codigo=${cod}`, "_blank");
    } else if (v === 5) {//pendientes de pago giros/billetera
        var cod = 5;
        window.open(`reporteporfecha.jsp?fechadesde=${fdesde}&fechahasta=${fhasata}&user=${vUser}&codigo=${cod}`, "_blank");
    } else if (parseInt(v) === 6) {//pendientes de pago giros/billetera
        var cod = 6;
        window.open(`reporteporfecha.jsp?fechadesde=${fdesde}&fechahasta=${fhasata}&user=${vUser}&codigo=${cod}`, "_blank");
    }
    location.reload();

}


function comboestado() {
    datopago = {
        "opcion": 9
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: datopago,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                if (value.idtipooperacion === 6 || value.idtipooperacion === 7) {
                    $("#vOpcionpago").append("<option value= \"" + value.idtipooperacion + "\"> " + value.descripcion + "</option>");
                }


            });

        }
    });
}