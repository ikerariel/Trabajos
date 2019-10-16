$(document).ready(function () {
    (function ($) {
        $('#filtrarTiposTarjetas').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarTiposTarjetas tr').hide();
            $('.buscarTiposTarjetas tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allTiposTarjetas();
    $(":text").val("");
});

//conecta con jsp "codtarjeta" naranja
//conecta con control "idtarjeta" naranja
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idtipotarjeta": $('#codtipotarjeta').val(),
        "tarjtipo": $('#tarjetipo').val()
        
    };
}

function  getUltimoCodigoTiposTarjetas() {
    crearJSON(5);
    //alert('Llega a getUltimoCodigoTarjetas');
    $.ajax({
        // url: "Tarjetascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TiposTarjetasServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codtipotarjeta").val(resp);
            $("#tarjetipo").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarTiposTarjetasporID() {
   // alert('Llega recuperarTarjetasporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Tarjetascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TiposTarjetasServlet",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#tarjetipo").val(value.tarj_tipo);
                $("#tarjetipo").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambTiposTarjetas(id) {
    crearJSON(id);
    $.ajax({
        // url: "Tarjetascontrol",
        url: "http://localhost:8084/TALLERCASAJC/TiposTarjetasServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allTarjetas();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaTiposTarjetas tr').click(function () {
        $('#codtipotarjeta').val($(this).find("td").eq(0).html());
        $('#tarjetipo').val($(this).find("td").eq(1).html());
    });
}


function allTiposTarjetas() {
//alert("Llega allTarjetas");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/TiposTarjetasServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaTiposTarjetas").append($("<tr>").append($("<td>"+value.id_tipotarjeta+"</td>"+
                                                        "<td>"+value.tarj_tipo+"</td>")));
                
            });
        }
    });
}

function campovacioTiposTarjetas() { //Para verificar campos vacio
    var a = $('#tarjetipo').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#tarjetipo');
    } else {
        ambTiposTarjetas(1);
    }
}

function ControlarCampoTiposTarjetas(){  // Para que no se repita nombre
    var dato;
    var Marcas = $('#tarjetipo').val();
    // alert(Marcas);
    $('#miTablaMarcas tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Marcas) {
            alert('ESTE TIPO DE TARJETA YA EXISTE');
            $('#tarjetipo').val(null); //Vaciar Campos
            $('#tarjetipo').focus(); 
        } else {
        }
    });
}
function reportesTiposTarjetas() {
    window.open("reportesTiposTarjetas.jsp");
}

function validarsololetras(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
        patron =/[A-Za-z\s]/;
        te = String.fromCharCode(tecla);
    return patron.test(te);
}
//onkeypress="return validarsololetras(event)"

//function SolotextoTiposTarjetas(){
//    var num = $('#descrmarca').val();
//    var re = isNaN(num);
//  if(re===true){ //si el valor es texto    
//  }else{
//      alert('SOLO TEXT');
//      $('#descrmarca').val(null);
//  }
//}
//onchange=" SolotextoCaja()”

function ValidacionesSoloNumeros(input) {
    var num = input.value.replace(/\./g, '');
//    alert("estees" +num);
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        input.value = num;
    } else {
        alert('Solo se permiten numeros');
        input.value = input.value.replace(/[^\d\.]*/g, '');
    }
}//--------------
//onkeyup="ValidacionesSoloNumeros(this)" onchange="ValidacionesSoloNumeros(this)"
