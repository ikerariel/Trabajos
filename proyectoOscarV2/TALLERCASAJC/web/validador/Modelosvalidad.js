/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function () {
    (function ($) {
        $('#filtrarModelos').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarModelos tr').hide();
            $('.buscarModelos tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allModelos();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idmodelo": $('#codmodelo').val(),
        "descrimodelo": $('#descrmodelo').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/ModelosServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codmodelo").val(resp);
            $("#descrmodelo").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarModelosporID() {
   // alert('Llega recuperarMarcasporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/ModelosServlet",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrmodelo").val(value.model_descri);
                $("#descrmodelo").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambModelos(id) {
    crearJSON(id);
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/ModelosServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Realizado Correctamente...!!!');
            location.reload();
            //allCiudades();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function recuperar() {
    $('#miTablaModelos tr').click(function () {
        $('#codmodelo').val($(this).find("td").eq(0).html());
        $('#descrmodelo').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allModelos() {
//alert("Llega allMarcas");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/ModelosServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaModelos").append($("<tr>").append($("<td>"+value.id_modelo+"</td>"+
                                                        "<td>"+value.model_descri+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioModelos() { //Para verificar campos vacio
    var a = $('#descrmodelo').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrmodelo');
    } else {
        ambModelos(1);
    }
}

function ControlarCampoModelos(){  // Para que no se repita nombre
    var dato;
    var Marcas = $('#descrmodelo').val();
    // alert(Marcas);
    $('#miTablaModelos tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Marcas) {
            alert('ESTE MODELO YA EXISTE');
            $('#descrmodelo').val(null); //Vaciar Campos
            $('#descrmodelo').focus(); 
        } else {
        }
    });
}

function reportesModelos() {
    window.open("reportesModelos.jsp");
}

function validarsololetras(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
        patron =/[A-Za-z\s]/;
        te = String.fromCharCode(tecla);
    return patron.test(te);
}
//onkeypress="return validarsololetras(event)"

//function SolotextoMarcas(){
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