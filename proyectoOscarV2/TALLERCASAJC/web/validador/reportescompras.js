$(document).ready(function () {
    opcionesreportes();
});

//function infreportes(v) {
//    var fdesde = $('#vFechadesde').val();
//    var fhasta = $('#vFechahasta').val();
//    var vUser = $('#vUser').val();
//    if (parseInt(v) === 2) {
//        var cod = 2;
//        window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}`, "_blank");
//    }
//
//    location.reload();
//
//}

function opcionesreportes() {
    $('#btngenerarinforme').click(function () {
        generarInforme();
    });
}

function generarInforme() {
    var fdesde = $('#vFechadesde').val();
    var fhasta = $('#vFechahasta').val();
    var vUser = $('#usertext_v').val();
    var vProveedor = $('#vProveedor').val();
    var vEstado = $('#vEstado').val();
    if ($('#vFechadesde').val() === "" || $('#vFechahasta').val() === ""
            ) {
        alert('Debes ingresar las rangos de fechas solicitadas .!!');
    } else if (parseInt(vProveedor) != 5 && $('#vlibrocompra').is(':checked')) {
        alert('Solo debes Seleciona una ocpión!!');

    } else if (parseInt(vProveedor) != 5 && $('#vComprasresumen').is(':checked')) {
        alert('Solo debes Seleciona una ocpión!!');

    } else if (parseInt(vEstado) != 5 && $('#vlibrocompra').is(':checked')) {
        alert('Solo debes Seleciona una ocpión!!');

    } else if (parseInt(vEstado) != 5 && $('#vComprasresumen').is(':checked')) {
        alert('Solo debes Seleciona una ocpión!!');
    
    } else if (parseInt(vEstado) != 5 && parseInt(vProveedor) != 5 ) {
        alert('Solo debes Seleciona una ocpión!!');
    
    } else if ($('#vlibrocompra').is(':checked') && $('#vComprasresumen').is(':checked') ) {
        alert('Solo debes Seleciona una ocpión!!');

    } else if ($('#vlibrocompra').is(':checked')) {
        var cod=2;
        window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}`, "_blank");
        location.reload();
      
    } else if (parseInt(vProveedor)<5) {
        var cod=1;
       window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}&id_proveedor=${vProveedor}`, "_blank");
        location.reload();
    } else if (parseInt(vEstado)<5) {
        var cod=3;
       window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}&id_estado=${vEstado}`, "_blank");
        location.reload();
      

    } else if ($('#vComprasresumen').is(':checked')) {
        alert('marcado resumen!!');

    } else if (parseInt(vProveedor) === 5 && parseInt(vEstado) === 5 &&
            $('#vlibrocompra').prop('checked', false) && $('#vComprasresumen').prop('checked', false)) {
        alert('Solo debes Seleciona una Opcion!!');
    }


//        if(){
//            
//        }
//    if ($('#vlibrocompra').is(':checked') && $('#vComprasresumen').is(':checked')) {
//        alert('Solo debes Seleciona una ocpión!!');
//        $('#vlibrocompra').prop('checked', false);
//        $('#vComprasresumen').prop('checked', false);
//
//    }
//    if ($('#vlibrocompra').is(':checked')) {
//        $('#vComprasresumen').prop('checked', false);
//        cod = 2;
//        window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}`, "_blank");
//        location.reload();
//    }
//    if ($('#vComprasresumen').is(':checked')) {
//        cod = 3;
//        $('#vlibrocompra').prop('checked', false);
//        window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}`, "_blank");
//        location.reload();
//    }
//    if (parseInt(vProveedor) > 0) {
//        cod = 1;
////            $('#vlibrocompra').prop('checked', false);
//        window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}&id_proveedor=${vProveedor}`, "_blank");
//        location.reload();
////        } if ($('#vlibrocompra').prop('checked', false) && $('#vComprasresumen').prop('checked', false)) {
//        alert('Solo debes Seleciona una ocpión!!');
}






