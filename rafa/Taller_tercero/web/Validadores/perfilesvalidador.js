
$(document).ready(function () {
    allPerfil();
//    $(":text").val("");

});

function reportesPerfil (){
    window.open("ReportesVista/reportesperfiles.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_perfil').val(),
        "nomb": $('#descr_perfil').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/perfilescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_perfil").val(resp);
            $("#descr_perfil").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarPerfilSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/perfilescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_perfil").val(value.per_descripcion);
                $("#descr_perfil").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarperfil() {
    $('#miTabla tr').click(function () {
        $('#cod_perfil').val($(this).find("td").eq(0).html());
        $('#descr_perfil').val($(this).find("td").eq(1).html());
    });
}
function campovacioperfil(){  
    var a = $('#descr_perfil').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_perfil').focus();
    }else{
        amb(1);
    }  
}
function controlarCampoperfil() {
    var dato;
    var ciudad = $('#descr_perfil').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_perfil').val(null);
            $('#descr_perfil').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampoperfil(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/perfilescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            //alert('Registro Insertado Correctamente...!!!');
            location.reload();
            //allClientes();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function allPerfil() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/perfilescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idperfil+"</td>"+
                                                        "<td>"+value.per_descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}


