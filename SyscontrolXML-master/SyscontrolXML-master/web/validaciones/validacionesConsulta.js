$(document).ready(function () {

    opcionBotonesRecargas();
    consultadato();
    agregarCombo();



});
function consultadato() {
    $('#btnconsultar').click(function () {
        var op = $('#v_opciones').val();
        if (op === "Número Linea") {
            getconsultarecarga(2);
        } else if (op === "Cédula") {
            getconsultarecarga(7);
        }
    });
    verificarcampoentero('v_consultadato');
}
function opcionBotonesRecargas() {

    $('#v_consultadato').keyup(function () {
        verificarcampoentero('v_consultadato');
        $('#v_consultadato').css('font-weight', 'bold');

    });



}

var index = 0;
function getconsultarecarga(v) {
    $('#mitablaconsultarecarga').find('tbody').find('tr').empty();
    $('#mitablaconsultagiro').find('tbody').find('tr').empty();

    js = {
        'opcion': v,
        'v_valor': $('#v_consultadato').val()
    };
    $.ajax({
        url: "/syscontrol/recargaSERVLETXML",
        type: 'POST',
        data: js,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                console.log(resp);
                ticket = "<button class='btn btn-sm btn-outline-info' title='Generar Ticket' onclick=\"$(\'#prod" + index + "\');generarticket(3)\">Ticket</button>";
                index++;
                $("#mitablaconsultarecarga").append($("<tr id=\'cod" + index + "\'>").append($(
                        "<td style=display:none>" + valor.iddetallerecargas + "</td>" +
                        "<td>" + valor.operadora + "</td>" +
                        "<td>" + "0" + valor.nrodestino + "</td>" +
                        "<td>" + valor.montorecarga + "</td>" +
                        "<td>" + valor.nrotransaccion + "</td>" +
                        "<td>" + valor.fecharecarga + "</td>" +
                        "<td>" + ticket + " </td>")));
            });

        }
    });
    if (parseInt(v) === 2) {
        getconsultagiro(8);
    } else if (parseInt(v) === 7) {
        getconsultagiro(10);
    }

}


var i = 0;
function getconsultagiro(v_v) {

    giro = {
        'opcion': v_v,
        'v_nrodes': $('#v_consultadato').val(),
        'v_nroorig': $('#v_consultadato').val(),
        'v_ci': $('#v_consultadato').val()
    };
    $.ajax({
        url: "/syscontrol/giroSERVLETXML",
        type: 'POST',
        data: giro,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, valor) {
                ticket = "<button class='btn btn-sm btn-outline-info' title='Generar Ticket' onclick=\"$(\'#prod" + i + "\');generarticket(4)\">Ticket</button>";
                i++;
                $("#mitablaconsultagiro").append($("<tr id=\'cod" + i + "\'>").append($(
                        "<td style='display: none'>" + valor.iddetallegiro + "</td>" +
                        "<td>" + valor.operadora + "</td>" +
                        "<td>" + "0" + valor.nroorigen + "</td>" +
                        "<td>" + "0" + valor.nrodestino + "</td>" +
                        "<td>" + valor.operacion + "</td>" +
                        "<td>" + valor.montogirobilletera + "</td>" +
                        "<td>" + valor.porcentaje + "</td>" +
                        "<td>" + valor.montoenvio + "</td>" +
                        "<td>" + valor.fecha + "</td>" +
                        "<td>" + ticket + " </td>")));
            });

        }
    });
}


function agregarCombo() {

    var array = ["Número Linea", "Cédula"];

    for (var i in array) {
        document.getElementById("v_opciones").innerHTML += "<option value='" + array[i] + "'>" + array[i] + "</option>";
    }

}