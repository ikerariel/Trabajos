
$(document).ready(function () {
    allMarca();
//    $(":text").val("");

});

function reportesMarca (){
    window.open("ReportesVista/reportesmarcas.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_marca').val(),
        "nomb": $('#descr_marca').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/marcascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_marca").val(resp);
            $("#descr_marca").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarMarcaSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/marcascontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_marca").val(value.marca_descri);
                $("#descr_marca").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarmarca() {
    $('#miTabla tr').click(function () {
        $('#cod_marca').val($(this).find("td").eq(0).html());
        $('#descr_marca').val($(this).find("td").eq(1).html());
    });
}
function campovaciomarca(){  
    var a = $('#descr_marca').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_marca').focus();
    }else{
        amb(1);
    }  
}
function controlarCampomarca() {
    var dato;
    var ciudad = $('#descr_marca').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_marca').val(null);
            $('#descr_marca').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampomarca(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/marcascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Operacion realizado Correctamente...!!!');
            location.reload();
            //allClientes();
        },
        error: function () {
            alert('Error Durante la Operacion...!!!');
        }
    });
}

function allMarca() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/marcascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idmarca+"</td>"+
                                                        "<td>"+value.marca_descri+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}


