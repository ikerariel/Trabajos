$(document).ready(function () {
    (function ($) {
        $('#filtrarMarcas').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarMarcas tr').hide();
            $('.buscarMarcas tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allMarcas();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idmarca": $('#codmarca').val(),
        "descrimarca": $('#descrmarca').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/Marcascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codmarca").val(resp);
            $("#descrmarca").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarMarcasporID() {
   // alert('Llega recuperarMarcasporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/Marcascontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrmarca").val(value.mar_descripcion);
                $("#descrmarca").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambMarcas(id) {
    crearJSON(id);
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/Marcascontrol",
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
    $('#miTablaMarcas tr').click(function () {
        $('#codmarca').val($(this).find("td").eq(0).html());
        $('#descrmarca').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allMarcas() {
//alert("Llega allMarcas");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Marcascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaMarcas").append($("<tr>").append($("<td>"+value.id_marca+"</td>"+
                                                        "<td>"+value.mar_descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioMarcas() { //Para verificar campos vacio
    var a = $('#descrmarca').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrmarca');
    } else {
        ambMarcas(1);
    }
}

function ControlarCampoMarcas(){  // Para que no se repita nombre
    var dato;
    var Marcas = $('#descrmarca').val();
    // alert(Marcas);
    $('#miTablaMarcas tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Marcas) {
            alert('ESTA MARCA YA EXISTE');
            $('#descrmarca').val(null); //Vaciar Campos
            $('#descrmarca').focus(); 
        } else {
        }
    });
}
function reportesMarcas() {
    window.open("reportesMarcas.jsp");
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