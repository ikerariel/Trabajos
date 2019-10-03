
$(document).ready(function () {
    allProcedencia();
//    $(":text").val("");

});

function reportesProcedenc (){
    window.open("ReportesVista/reportesprocedencias.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_procedencia').val(),
        "nomb": $('#descr_procedencia').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/procedenciascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_procedencia").val(resp);
            $("#descr_procedencia").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarProcedenciaSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/procedenciascontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_procedencia").val(value.proce_descri);
                $("#descr_procedencia").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarproce() {
    $('#miTabla tr').click(function () {
        $('#cod_procedencia').val($(this).find("td").eq(0).html());
        $('#descr_procedencia').val($(this).find("td").eq(1).html());
    });
}
function campovacioproce(){  
    var a = $('#descr_procedencia').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_procedencia').focus();
    }else{
        amb(1);
    }  
}
function controlarCampoproce() {
    var dato;
    var ciudad = $('#descr_procedencia').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_procedencia').val(null);
            $('#descr_procedencia').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampoproce(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/procedenciascontrol",
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

function allProcedencia(){
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/procedenciascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idprocedencia+"</td>"+
                                                        "<td>"+value.proce_descri+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

