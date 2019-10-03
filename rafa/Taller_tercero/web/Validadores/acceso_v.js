
$(document).ready(function () {
    obtenerPermiso();

});

function obtenerDatos(){
    jacsondato = {
            "opcion": 1,
            "user": $('#nombreuser').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/accservlet",
            data: jacsondato,
            type: 'POST',
            success: function (resp) {
            
                if (JSON.stringify(resp) != '[]') {
                    $.each(resp, function (indice, value) {
                    $('#Codsucursal').val(value.idsucursal);
                    $('#coddeposito').val(value.iddeposito);
                    });
                } else {
                      alert('el usuario no existe');
                      location.reload();
                }
            },
            error: function () {
            }
        });
}

function obtenerPermiso() {

        dato = {
            "opcion": 1,
            "user": $('#vUser').val()
        };
        $.ajax({
            url: "http://localhost:8084/Taller_tercero/accservlet",
            data: dato,
            type: 'POST',
            success: function (resp) {
            
                if (JSON.stringify(resp) != '[]') {
                    $.each(resp, function (indice, value) {
                        let vPerfl = value.idperfil;
                        $('#CodvUser').val(value.idusuario);
                        permisos(vPerfl);
//                    $('#usernameD').val(value.usu_nombre);
                    });
                } else {

                }
            },
            error: function () {
            }
        });
}

function permisos(valor){
    switch (valor){
        case 1:
             $('#menu_compras').show();
             $('#me_pedidocompras').show();
             $('#me_presucompra').show();
             $('#me_ordencompra').show();
             $('#me_faccompra').show();
             $('#me_Notaremision').show();
             $('#me_notacredito').show();
             $('#me_ajuste').show();
             $('#menu_referencialC').show();
             $('#me_deposito').show();
             $('#me_categoria').show();
             $('#me_estado').show();
             $('#me_estante').show();
             $('#me_sector').show();
             $('#me_impuesto').show();
             $('#me_marcas').show();
             $('#me_mercaderia').show();
             $('#me_motivoajuste').show();
             $('#me_motivomerma').show();
             $('#me_procedencia').show();
             $('#menu_referencialV').show();
             $('#me_caja').show();
             $('#me_cliente').show();
             $('#me_cargos').show();
             $('#me_funcionario').show();
             $('#me_ciudades').show();
             $('#me_perfil').show();
             $('#me_unidadmedi').show();
             $('#me_proveedor').show();
             $('#me_sucursal').show();
             $('#me_usuario').show();
             $('#me_timbrado').show();
             $('#menu_FacturacionV').show();
             $('#me_apertura').show();
             $('#me_pedidoventa').show();
             $('#me_presuventa').show();
      
            break;
        case 3:
             $('#menu_compras').show();
             $('#menu_referencialV').show();
             $('#me_presucompra').show();
             $('#me_ordencompra').show();
             $('#me_faccompra').show();
             $('#me_proveedor').show();
      
            break;    
        case 4:
             $('#menu_compras').show();
             $('#me_pedidocompras').show();
             $('#menu_referencialV').show();
             $('#me_Notaremision').show();
             $('#me_proveedor').show();
             $('#me_notacredito').show();
      
            break; 
        
    }
}


