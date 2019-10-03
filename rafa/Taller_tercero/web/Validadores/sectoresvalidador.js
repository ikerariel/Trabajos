
$(document).ready(function () {
    allsector();

});

function reportesSector (){
    window.open("ReportesVista/reportessectores.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_sector').val(),
        "nomb": $('#descr_sector').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/sectorescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_sector").val(resp);
            $("#descr_sector").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarsectorSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/sectorescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_sector").val(value.sect_descri);
                $("#descr_sector").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarsector() {
    $('#miTabla tr').click(function () {
        $('#cod_sector').val($(this).find("td").eq(0).html());
        $('#descr_sector').val($(this).find("td").eq(1).html());
    });
}
function campovaciosector(){  
    var a = $('#descr_sector').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_sector').focus();
    }else{
        amb(1);
    }  
}
function controlarCamposector() {
    var dato;
    var ciudad = $('#descr_sector').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_sector').val(null);
            $('#descr_sector').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcamposector(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/sectorescontrol",
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

function allsector(){
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/sectorescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idsector+"</td>"+
                                                        "<td>"+value.sect_descri+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}


