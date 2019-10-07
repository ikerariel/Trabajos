$(document).ready(function () {
   
});

function infreportes(v) {
    var fdesde = $('#vFechadesde').val();
    var fhasata = $('#vFechahasta').val();
    var vUser = $('#vUser').val();
    if (parseInt(v) === 1) { //giro pagados
        var cod = 2;
        window.open(`reportesCompra.jsp?fhasta=${fdesde}&fhasta=${fhasata}&user=${vUser}&codigo=${cod}`, "_blank");
    }
   
    location.reload();

}