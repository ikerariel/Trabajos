
$(document).ready(function () {
    allEstan();

});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#codigo').val(),
        "nomb": $('#descr_est').val()
        
    };
}

function reportesEstante (){
    window.open("ReportesVista/reportesestantes.jsp");
}

function  autonumericoestante() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/estantecontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codigo").val(resp);
            $("#descr_est").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarEstanteSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/estantecontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_est").val(value.estan_descri);
                $("#descr_est").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function selecestan() {
    $('#miTabla tr').click(function () {
        $('#codigo').val($(this).find("td").eq(0).html());
        $('#descr_est').val($(this).find("td").eq(1).html());
    });
}
function campovacioestan(){  //para verificar campo vacio
    var a = $('#descr_est').val();// nombre del campos
    if(a === ""){
        alert('campo vacio');
        $('#descr_est').focus();
    }else{
        amb(1);
    }  
}
function controlarCampoestan() {// para que no se repita los nombres
    var dato;
    var ciudad = $('#descr_est').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_est').val(null);//vaciar campos
            $('#descr_est').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampoestan(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/estantecontrol",
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
function allEstan() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/estantecontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idestante+"</td>"+
                                                        "<td>"+value.estan_descri+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}


