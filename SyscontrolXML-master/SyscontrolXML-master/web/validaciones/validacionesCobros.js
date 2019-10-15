$(document).ready(function () {
   comboTipoDoc();
});


function comboTipoDoc() {

    var array = ["Cedula", "Nro.Factura"];
    var cont = 0;
    for (var i in array) {
        cont++;
        document.getElementById("ctipodoac").innerHTML += "<option value='" + cont + "'>" + array[i] + "</option>";
    }

}


