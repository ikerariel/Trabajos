$(document).ready(function () {

});

function infreportes(v) {
    var fdesde = $('#vFechadesde').val();
    var fhasta = $('#vFechahasta').val();
    var vUser = $('#vUser').val();
    if (parseInt(v) === 2) {
        var cod = 2;
        window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}`, "_blank");
    }

    location.reload();

}


function ver() {
    var fdesde = $('#vFechadesde').val();
    var fhasta = $('#vFechahasta').val();
    var vUser = $('#vUser').val();
    var cod = 0;
    if ($('#vFechadesde').val() === "" || $('#vFechahasta').val() === "") {
        alert('Debes ingresar las rangos de fechas solicitadas .!!');
    } else {
        if ($('#vlibrocompra').is(':checked') && $('#vComprasresumen').is(':checked')) {
            alert('Solo debes Seleciona una ocpión!!');
            $('#vlibrocompra').prop('checked', false);
            $('#vComprasresumen').prop('checked', false);

        } else if ($('#vlibrocompra').is(':checked')) {
            $('#vComprasresumen').prop('checked', false);
            cod = 3;
            window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}`, "_blank");
            location.reload();
        } else if ($('#vComprasresumen').is(':checked')) {
            $('#vlibrocompra').prop('checked', false);
               window.open(`reportesCompra_v.jsp?fdesde=${fdesde}&fhasta=${fhasta}&vUser=${vUser}&codigo=${cod}`, "_blank");
            location.reload();
        } else if ($('#vlibrocompra').prop('checked', false) && $('#vComprasresumen').prop('checked', false)) {
            alert('Solo debes Seleciona una ocpión!!');
        } else {

        }

    }
    


}
;