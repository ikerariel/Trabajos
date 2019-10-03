
$(document).ready(function () {
    
      allEstado();
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#codigo').val(),
        "nomb": $('#descr_estad').val()
        
    };
}

function reportesEstado (){
    window.open("ReportesVista/reportesestados.jsp");
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/estadocontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codigo").val(resp);
            $("#descr_estad").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarEstadoSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/estadocontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_estad").val(value.descri_estado);
                $("#descr_estad").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function selecestado() {
    $('#miTabla tr').click(function () {
        $('#codigo').val($(this).find("td").eq(0).html());
        $('#descr_estad').val($(this).find("td").eq(1).html());
//        $('#descri_sucur').val($(this).find("td").eq(2).html());
    });
}
function campovacioestado(){  
    var a = $('#descr_estad').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_estad').focus();
    }else{
        amb(1);
    }  
}
function controlarCampoestado() {
    var dato;
    var ciudad = $('#descr_estad').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_estad').val(null);
            $('#descr_estad').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampoestado(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/estadocontrol",
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

function allEstado() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/estadocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idestado+"</td>"+
                                                        "<td>"+value.descri_estado+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}



