$(document).ready(function () {
    allImpuesto();

});

function reportesImpuest (){
    window.open("ReportesVista/reportesimpuestos.jsp");
}
function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_impu').val(),
        "nomb": $('#descr_impu').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/impuestocontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_impu").val(resp);
            $("#descr_impu").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarImpuestoSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/impuestocontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_impu").val(value.descri_impuesto);
                $("#descr_impu").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarimpu() {
    $('#miTabla tr').click(function () {
        $('#cod_impu').val($(this).find("td").eq(0).html());
        $('#descr_impu').val($(this).find("td").eq(1).html());
    });
}
function campovacioimpu(){  
    var a = $('#descr_impu').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_impu').focus();
    }else{
        amb(1);
    }  
}
function controlarCampoimpu() {
    var dato;
    var ciudad = $('#descr_impu').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_impu').val(null);
            $('#descr_impu').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampoimpu(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/impuestocontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro realizado Correctamente...!!!');
            location.reload();
            //allClientes();
        },
        error: function () {
            alert('Error Durante la operacion del Registro...!!!');
        }
    });
}

//function eliminar() {
//    $('table tr').click(function () {
//        $('#codigo').val($(this).find("td").eq(0).html());
//        $('#nombre').val($(this).find("td").eq(1).html());
//    });
//}
//
//function modificar() {
//    $('table tr').click(function () {
//        $('#codigo').val($(this).find("td").eq(0).html());
//        $('#nombre').val($(this).find("td").eq(1).html());
//    });
//}

function allImpuesto() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/impuestocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idimpuesto+"</td>"+
                                                        "<td>"+value.descri_impuesto+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}


