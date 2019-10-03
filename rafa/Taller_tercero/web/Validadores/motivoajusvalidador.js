
$(document).ready(function () {
    allmotiaju();
//    $(":text").val("");

});

function reportesMotivoA (){
    window.open("ReportesVista/reportesmotivoajuste.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#codigo').val(),
        "nomb": $('#descr_aju').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/motivoajuscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codigo").val(resp);
            $("#descr_aju").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarMotivoajusteSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/motivoajuscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_aju").val(value.ajustmotiv_descri);
                $("#descr_aju").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarmotivo() {
    $('#miTabla tr').click(function () {
        $('#codigo').val($(this).find("td").eq(0).html());
        $('#descr_aju').val($(this).find("td").eq(1).html());
    });
}
function campovaciomotivo(){  
    var a = $('#descr_aju').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_aju').focus();
    }else{
        amb(1);
    }  
}
function controlarCampomotivo() {
    var dato;
    var ciudad = $('#descr_aju').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_aju').val(null);
            $('#descr_aju').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampomotivo(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/motivoajuscontrol",
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
function allmotiaju() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/motivoajuscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idmot_ajus+"</td>"+
                                                        "<td>"+value.ajustmotiv_descri+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}


