/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    (function ($) {
        $('#filtrarCargos').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarCargos tr').hide();
            $('.buscarCargos tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allCargos();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idcargo": $('#codcargo').val(),
        "descri": $('#descr').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/CargosServlet",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codcargo").val(resp);
            $("#descr").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarCargosporID() {
   // alert('Llega recuperarCArgosporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/CargosServlet",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr").val(value.descripcion);
                $("#descr").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambCargos(id) {
    crearJSON(id);
    $.ajax({
        // url: "Marcascontrol",
        url: "http://localhost:8084/TALLERCASAJC/CargosServlet",
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
    $('#miTablaCargos tr').click(function () {
        $('#codcargo').val($(this).find("td").eq(0).html());
        $('#descr').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allCargos() {
//alert("Llega allCargos");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/CargosServlet",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaCargos").append($("<tr>").append($("<td>"+value.idcargo+"</td>"+
                                                        "<td>"+value.descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioCargos() { //Para verificar campos vacio
    var a = $('#descr').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descr');
    } else {
        ambCargos(1);
    }
}

function ControlarCampoCargos(){  // Para que no se repita nombre
    var dato;
    var Cargos = $('#descr').val();
    // alert(Cargos);
    $('#miTablaCargos tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Cargos) {
            alert('ESTE CARGO YA EXISTE');
            $('#descr').val(null); //Vaciar Campos
            $('#descr').focus(); 
        } else {
        }
    });
}

function reportesCargos() {
    window.open("reportesCargos.jsp");
}

function validarsololetras(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
        patron =/[A-Za-z\s]/;
        te = String.fromCharCode(tecla);
    return patron.test(te);
}