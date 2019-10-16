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


var index = 0;
function getCobros() {
    var cfac;
    var cCi;
    var op;
    op = $('#ctipodoac').val();
    if (parseInt(op) === 1) {
        cCi = $('#fclienteci').val();
        cfac = "";
    } else if (parseInt(op) === 2) {
        cCi = "";
        cfac = $('#fclienteci').val();
        ;
    }

    $('#mitblaCobrosDettalle').find('tbody').find('tr').empty();
    js = {
        'opcion': 11,
        'cfac': cfac,
        'cCi': cCi
    };
    $.ajax({
        url: "/syscontrol/ventasSERVLET",
        type: 'POST',
        data: js,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                if (parseInt(op) === 1) {
                    $('#fclientenombre').val(valor.nombrecliente);
                } else if (parseInt(op) === 2) {
                    $('#fclientenombre').val(valor.cliente);
                }
                ticket = "<button class='btn btn-sm btn-danger' title='Generar Ticket' onclick=\"$(\'#prod" + index + "\');cobrarCta()\">Cobrar</button>";
                index++;
                $("#mitblaCobrosDettalle").append($("<tr id=\'cod" + index + "\'>").append($(
                        "<td>" + valor.idcobro + "</td>" +
                        "<td>" + valor.numerodocumento + "</td>" +
                        "<td>" + valor.fechasaldo + "</td>" +
                        "<td>" + valor.saldo + "</td>" +
                        "<td>" + valor.importe + "</td>" +
                        "<td>" + valor.estado + "</td>" +
                        "<td>" + ticket + " </td>")));
            });
            setTimeout(function (){
                totalCobro(); 
            },1200);
                 
            
           

        }
    });

}

function cobrarCta() {
    $('#v_clientefacturacion').modal('show');
}


function totalCobro() {
$('#totalfactura').val("0");
$('#totalcobrado').val("0");
    var totalfac = 0;
    var totalf = 0;
    var totalcob = 0;
    var totlacobrado = 0;

    $('#mitblaCobrosDettalle').find('tbody').find('tr').each(function () {
        totalfac = parseInt($(this).find("td").eq(3).html());
        totlacobrado = parseInt($(this).find("td").eq(4).html());

        totalf = totalf + totalfac;
        totalcob = totalcob + totlacobrado;

        $('#totalfactura').val(totalf);
        $('#totalcobrado').val(totalcob);

        $('#totalfactura').css('font-size', '13pt');
        $('#totalfactura').css('color', 'red');
        $('#totalfactura').css('font-weight', 'bold');
        $('#totalcobrado').css('font-size', '13pt');
        $('#totalcobrado').css('color', 'green');
        $('#totalcobrado').css('font-weight', 'bold');
    });

    puntodecimal('totalfactura');
    puntodecimal('totalcobrado');


}


