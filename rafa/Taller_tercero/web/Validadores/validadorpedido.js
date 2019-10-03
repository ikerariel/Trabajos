$(document).ready(function() {
    alert('llega a funcion js');
 //   $("#datepicker").datepicker();
  // cargarComboSucursal();
   // cargargrillaBuscardor();

});
var acu = 0;
var tindex = 0;
var separador = '/@/';
var numero;
var calculo = 0;
var ope;
var datoRecu = null;
var modifica = false;
var elimina = false;


function autonumpedidos() {
    $.get('controladores/controlpedido.jsp', 'bandera=1', function(datos) {
        $('#pkpedidocompra').val(datos);
        $('#pkpedidocompra').prop('disabled', true);
        $('#idFuncionario').focus();
    });
}


function cargarComboSucursal() {
    $.get('controladores/controlpedido.jsp', 'bandera=8', function(datos) {
        var datoRecuperado = datos.split(separador);
        $('#combosucu').html(datoRecuperado[0]);
    });
}



function recuperardatofuncionario() {

     $.get('controladores/controlpedido.jsp', 'bandera=5&pk_funcionario=' + $('#idFuncionario').val() + '', function (datos) {
         alert(datos);
         var datosrecuperado=datos.split(separador);
          $('#funcio_nombre').val(datosrecuperado[0]);
          

    });
}




function RecuperoDatoPedidos() {
    $.get('controladores/controlpedido.jsp', 'bandera=9&pedido_codigo=' + $('#pkpedidocompra').val(), function(valorRecuperado) {
        var datoRecuperadoPed = valorRecuperado.split(separador);
        if (datoRecuperadoPed.length === 1) {
            alert("El codigo que ingreso no existe");
        } else {
            $('#datepicker').val(datoRecuperadoPed[0]);
            $('#idFuncionario').val(datoRecuperadoPed[1]);
           $('#funcio_nombre').val(datoRecuperadoPed[2]);
            $('#pedidonumero').val(datoRecuperadoPed[3]);
            $('#combosucu').html(datoRecuperadoPed[4]);

        }



        //$('#pkcargo').prop('disabled', true);        
        //$('#descripcargo').val(valor);

    });

}
function RecuperoDatoDetalle() {
    $.get('controladores/controlpedido.jsp', 'bandera=10&pedido_codigo=' + $('#pkpedidocompra').val(), function(valorRecuperado) {
        var datoRecuperadoPed = valorRecuperado.split(separador);
        if (datoRecuperadoPed.length === 1) {
            alert("El codigo que ingreso no existe");
       
        }
        
        var cod = $('#codProducto').val(datoRecuperadoPed[0]);
        var des = $('#descripedido').val(datoRecuperadoPed[1]);
        var cant = $('#cantpedido').val(datoRecuperadoPed[2]);
     
        tindex++;

        $('#tabladetalleproductos tr:last').after("<tr id=\'prod" + tindex + "\'>\n\
                                            <td style=' width: 5%;'>" + datoRecuperadoPed[0].toString() + "</td>\n\
                                            <td style=' width: 50%;'>" + datoRecuperadoPed[1].toString() + "</td>\n\
                                            <td style=' width: 5%;'>" + datoRecuperadoPed[2].toString() + "</td>\n\
                                            <td onclick=\"acu2=0;update();\" style=' width: 5%;'><img onclick=\"$(\'#prod" + tindex + "\').remove();\" src='resources/images/Delete-icon.png'/></td>\n\
                                      </tr>");
        $('#grabar').prop('disabled', false);//visible
    });

}






function RecuperoDatosEliminarPedidosCab() {
    $.get('controladores/controlpedido.jsp', 'bandera=9&pedido_codigo=' + $('#pkpedidocompra').val(), function(valorRecuperado) {
        var datoRecuperadoPed = valorRecuperado.split(separador);
        if (datoRecuperadoPed.length === 1) {
            alert("El codigo que ingreso no existe");
        } else {
            $('#datepicker').val(datoRecuperadoPed[0]);
            $('#idFuncionario').val(datoRecuperadoPed[1]);
           $('#funcio_nombre').val(datoRecuperadoPed[2]);
            $('#pedidonumero').val(datoRecuperadoPed[3]);
            $('#combosucu').html(datoRecuperadoPed[4]);

        }



        //$('#pkcargo').prop('disabled', true);        
        //$('#descripcargo').val(valor);

    });

}




function RecuperoDatosEliminarPedidosDet() {
    $.get('controladores/controlpedido.jsp', 'bandera=10&pedido_codigo=' + $('#pkpedidocompra').val() + '', function(valorRecuperado) {
        var datoRecuperadoPed = valorRecuperado.split(separador);
        if (datoRecuperadoPed.length === 1) {
            alert("El codigo que ingreso no existe");
        } else {
            $('#codProducto').val(datoRecuperadoPed[0]);
            $('#descripedido').val(datoRecuperadoPed[1]);
            $('#cantpedido').val(datoRecuperadoPed[2]);
            
         tindex++;

        $('#tabladetalleproductos tr:last').after("<tr id=\'prod" + tindex + "\'>\n\
                                            <td style=' width: 5%;'>" + datoRecuperadoPed[0].toString() + "</td>\n\
                                            <td style=' width: 50%;'>" + datoRecuperadoPed[1].toString() + "</td>\n\
                                            <td style=' width: 5%;'>" + datoRecuperadoPed[2].toString() + "</td>\n\
                                            <td onclick=\"acu2=0;update();\" style=' width: 5%;'><img onclick=\"$(\'#prod" + tindex + "\').remove();\" src='resources/images/Delete-icon.png'/></td>\n\
                                      </tr>");    
            
            if (confirm("Desea Eliminar")) {
                eliminarPedido();
            } else {
                limpiezaped();
            }
        }
    });
}


function eliminarPedido() {
    $.get('controladores/controlpedido.jsp', 'bandera=13&pedido_codigo=' + $('#pkpedidocompra').val() + '', function (datos) {
        var datoRecuperadoEliminar = datos.split(separador);
        if (datoRecuperadoEliminar.length === 1) {
            alert("Error Durante La Eliminacion");
            limpiezaped();
        } else {
            //LimpiarMarca2();
            alert("Eliminado Cabecera");

            $.get('controladores/controlpedido.jsp', 'bandera=14&pedido_codigo=' + $('#pkpedidocompra').val() + '', function (datos) {

                alert("Eliminado Detalle");
            });

            limpiezaped();
        }
    });
}







function limpiar_deta() {
    $('#codProducto').val(null);
    $('#descripedido').val(null);
    $('#cantpedido').val(null);
    $('#codProducto').focus();

}

function cargargrillaBuscardor() {

    $.get('controladores/controlpedido.jsp', 'bandera=4', function(datos) {
        $('#buscador').html(datos);
        filtro();

    });
}


function agregarFilasPedido() {
    //alert('Llega');

    var cod = $('#codProducto').val();
    var des = $('#descripedido').val();
    var cant = $('#cantpedido').val();
    tindex++;
    $('#tabladetalleproductos tr:last').after("<tr id=\'prod" + tindex + "\'>\n\
                                            <td style=' width: 5%;'>" + cod + "</td>\n\
                                            <td style=' width: 60%;'>" + des + "</td>\n\
                                            <td style=' width: 5%;'>" + cant + "</td>\n\
                                           <td style=' width: 5%;'><img onclick=\"$(\'#prod" + tindex + "\').remove();\" src='resources/images/LOGO_VEMAY_tablas.png'/></td>\n\
                                            <td style=' width: 5%;'><img onclick=\"$(\'#prod" + tindex + "\').remove();\" src='resources/imgages/Delete-icon.png'/></td>\n\
                                      </tr>");
}





function verificarFilasTabla() {
    var estado = true;
    alert($('#tabladetalleproductos tr').length);
    // alert($('#tabladetalleproductos >tbody >tr').length);
    var codaVeredicar = $('#codProducto').val();
    if ($('#tabladetalleproductos tr').length === 1) {
        // if ($('#tabladetalleproductos >tbody >tr').length === 1) {
        return estado;
    } else {
        //  alert("codaVeredicar : " + codaVeredicar);
        $('#tabladetalleproductos tr').each(function() {
            var Referencia = $(this).find("td").eq(0).html();
            // alert("Referencia : " +Referencia);
            if (codaVeredicar === Referencia) {
                alert("No puede duplicar los productos");
                estado = false;
            }
        });
    }
    return estado;
}



function filtro() {
    $(".filtrar tr:has(td)").each(function() {
        var t = $(this).text().toLowerCase();
        $("<td class='indexColumn'></td>")
                .hide().text(t).appendTo(this);
    });
    //Agregar el comportamiento al texto (se selecciona por el ID) 
    $("#texto_a_buscar").keyup(function() {
        var s = $(this).val().toLowerCase().split(" ");
        $(".filtrar tr:hidden").show();
        $.each(s, function() {
            $(".filtrar tr:visible .indexColumn:not(:contains('"
                    + this + "'))").parent().hide();
        });
    });

//    $('table tr').click(function () {
//        $('#codProducto').val($(this).find("td").eq(0).html());
//        $('#descripcion').val($(this).find("td").eq(1).html());
//        $('#busqueda').dialog("close");
//        
//    });
}


function seleccionarFila() {
    $('table tr').click(function() {
        $('#codProducto').val($(this).find("td").eq(0).html());
        $('#descripedido').val($(this).find("td").eq(1).html());
        $('#cantpedido').focus();
        $('#busqueda').dialog("close");
    });

}



function limpiezaped() {
    $('#pkpedidocompra').val(null);
    $('#datepicker').val(null);
    $('#idFuncionario').val(null);
    $('#funcio_nombre').val(null);
    $('#pedidonumero').val(null);
    $('#listarComboSucu').val(null);
    $('#codProducto').val(null);
    $('#descripedido').val(null);
    $('#cantpedido').val(null);


    $("#tabladetalleproductos").find("tr:gt(0)").remove();
    deshaTextPedido();
    deshabilitarBotonesPedidos();
}



function grabarPedido() {
     if (operacion=== 1){
    $.get('controladores/controlpedido.jsp', 'bandera=2&pedido_codigo=' + $('#pkpedidocompra').val() + '&pedidofecha=' + $('#datepicker').val() + '&pk_funcionario=' + $('#idFuncionario').val() +
            '&nro_pedido=' + $('#pedidonumero').val() + '&fk_Sucu=' + $('#listarComboSucu').val(), function(datos) {
        var datoRecuperado = datos.split(separador);

        if (datoRecuperado.length === 1) {
            alert("Error al Insertar la Cabezera");
            return;
        } else {
            alert('Guarda con exito ');
            //detalle
            $('#tabladetalleproductos tr').each(function() {
                var codProducto = $(this).find("td").eq(0).html();
                if (codProducto !== null) {
                    var codProducto = $(this).find("td").eq(0).html();
                    var cantProducto = $(this).find("td").eq(2).html();
                    $.get('controladores/controlpedido.jsp', 'bandera=3&pedido_codigo=' + $('#pkpedidocompra').val() + '&codigoProducto=' + codProducto + '&cantidaProducto=' + cantProducto, function(datos) {
                    });
                }
            });
   
            limpiezaped();

        }
    });
    }
     }
     
function ModificarPedido() {
        if (operacion=== 2){
        $.get('controladores/controlpedido.jsp', 'bandera=11&pedido_codigo=' + $('#pkpedidocompra').val() + '&pedidofecha=' + $('#datepicker').val() + '&pk_funcionario=' + $('#idFuncionario').val() + 
                '&nro_pedido=' + $('#pedidonumero').val() + '&fk_Sucu=' + $('#listarComboSucu').val(), function(datosMod) {
             var varUpdate=datosMod.split(separador);
            if(varUpdate.length === 1){
                alert("No se pudo realizar la actualizacion");
                return;
            } else {
                alert("Actualizacion de la Cabecera");
                
                $('#tabladetalleproductos tr').each(function () {
                var codProducto = $(this).find("td").eq(0).html();
                if (codProducto !== null) {
                    var codProducto = $(this).find("td").eq(0).html();
                    var cantProducto = $(this).find("td").eq(2).html();
                    
                    $.get('controladores/controlpedido.jsp', 'bandera=12&pedido_codigo=' + $('#pkpedidocompra').val() + '&codigoProducto=' + codProducto + '&cantidaProducto=' + cantProducto, function (datosMod) {

                    });
                }

            });
                limpiezaped();
                //CargaTablaFamilias();
            }
        });
        
    }
    
    
    
}


function ver_reporte() {
    $.get('controladores/controlpedido.jsp', 'bandera=6', function() {
    });
}

function ver_reportepedido(codigo_pedido) {
//    alert('codigo_pedido ' + codigo_pedido);
    $.get('controladores/controlpedido.jsp', 'bandera=7&filtro_reporte=' + codigo_pedido + '', function() {
    });
}