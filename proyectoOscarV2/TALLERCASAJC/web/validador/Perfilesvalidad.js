$(document).ready(function () {
    (function ($) {
        $('#filtrarPerfiles').keyup(function () {
            var rex = new RegExp($(this).val(), 'i');
            $('.buscarPerfiles tr').hide();
            $('.buscarPerfiles tr').filter(function () {
                return rex.test($(this).text());
            }).show();
        });
    }(jQuery));
});
$(document).ready(function () {
    allPerfiles();
    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "idperfil": $('#codperfil').val(),
        "descriperfil": $('#descrperfil').val()
        
    };
}

function  getUltimoCodigo() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "Perfilescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Perfilescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codperfil").val(resp);
            $("#descrperfil").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  recuperarPerfilesporID() {
   // alert('Llega recuperarPerfilesporID ');
    crearJSON(6);
    
    $.ajax({
        // url: "Perfilescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Perfilescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descrperfil").val(value.perf_descripcion);
                $("#descrperfil").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  ambPerfiles(id) {
    crearJSON(id);
    $.ajax({
        // url: "Perfilescontrol",
        url: "http://localhost:8084/TALLERCASAJC/Perfilescontrol",
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
    $('#miTablaPerfiles tr').click(function () {
        $('#codperfil').val($(this).find("td").eq(0).html());
        $('#descrperfil').val($(this).find("td").eq(1).html());
    });
}

//function modificar() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html());
//    });
//}

function allPerfiles() {
//alert("Llega allPerfiles");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/TALLERCASAJC/Perfilescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTablaPerfiles").append($("<tr>").append($("<td>"+value.id_perfil+"</td>"+
                                                        "<td>"+value.perf_descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacioPerfiles() { //Para verificar campos vacio
    var a = $('#descrperfil').val(); //nombre del campos
    if (a === "") {
        alert('campo vacio');
        $('#descrperfil');
    } else {
        ambPerfiles(1);
    }
}

function ControlarCampoPerfiles(){  // Para que no se repita nombre
    var dato;
    var Perfiles = $('#descrperfil').val();
    // alert(Perfiles);
    $('#miTablaPerfiles tr').each(function () {
        dato = $(this).find('td').eq(1).html();
        if (dato === Perfiles) {
            alert('ESTE PERFIL YA EXISTE');
            $('#descrperfil').val(null); //Vaciar Campos
            $('#descrperfil').focus(); 
        } else {
        }
    });
}

function reportesPerfiles() {
    window.open("reportesPerfiles.jsp");
}

function validarsololetras(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
        patron =/[A-Za-z\s]/;
        te = String.fromCharCode(tecla);
    return patron.test(te);
}