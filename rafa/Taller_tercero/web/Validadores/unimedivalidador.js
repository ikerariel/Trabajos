
$(document).ready(function () {
    allunidadm();
//    $(":text").val("");

});

function reportesUnidadM (){
    window.open("ReportesVista/reportesunidadmedidas.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_medida').val(),
        "nomb": $('#descr_medida').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/unimedicontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_medida").val(resp);
            $("#descr_medida").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarunidadmSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/unimedicontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_medida").val(value.unidad_descric);
                $("#descr_medida").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarunid() {
    $('#miTabla tr').click(function () {
        $('#cod_medida').val($(this).find("td").eq(0).html());
        $('#descr_medida').val($(this).find("td").eq(1).html());
    });
}
function campovaciounid(){  
    var a = $('#descr_medida').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_medida').focus();
    }else{
        amb(1);
    }  
}
function controlarCampounid() {
    var dato;
    var ciudad = $('#descr_medida').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_medida').val(null);
            $('#descr_medida').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampounid(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/unimedicontrol",
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

function allunidadm(){
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/unimedicontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.iduni_medi+"</td>"+
                                                        "<td>"+value.unidad_descric+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}


