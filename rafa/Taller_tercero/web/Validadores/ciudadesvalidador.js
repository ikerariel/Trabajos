$(document).ready(function () {
    allCiudad();
    $('#btnNuevo').show();
    $('#btnReporte').show();
    //$("#descr_ciudad").prop('disabled', true);
//    $(":text").val("");

});

//function reportesciudades (){
//    window.open("ReportesVista.reportesciudades.jsp?cod=" + 1 +" ", "_blank");
//}

function reportesciud (){
    window.open("ReportesVista/reportesciudades.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_ciudad').val(),
        "nomb": $('#descr_ciudad').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/ciudadescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_ciudad").val(resp);
            $("#descr_ciudad").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarCiudadSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/ciudadescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_ciudad").val(value.ciu_descripcion);
                $("#descr_ciudad").val.focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}


function  amb(id) {
    crearJSON(id);
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/ciudadescontrol",
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

function allCiudad() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/ciudadescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idciudad+"</td>"+
                                                        "<td>"+value.ciu_descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}
function selecciudad() {
    $('#miTabla tr').click(function () {
        $('#cod_ciudad').val($(this).find("td").eq(0).html());
        $('#descr_ciudad').val($(this).find("td").eq(1).html());
        $('#btnModificar').show();
        $('#btnAnular').show();
        $('#btnLimpiar').show();
        document.getElementById("btnNuevo").style.display = 'none';
        document.getElementById("btnInsertar").style.display = 'none';
        document.getElementById("btnReporte").style.display = 'none';
        $('#descr_ciudad').removeAttr("disabled");
    });
}
function campovaciociudad(){  
    var a = $('#descr_ciudad').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_ciudad').focus();
    }else{
        amb(1);
    }  
}
function controlarCampociudad() {
    var dato;
    var ciudad = $('#descr_ciudad').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_ciudad').val(null);
            $('#descr_ciudad').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampociudad(){
   window.location.reload(); 
}

function Solotexto(){
    var num = $('#descr_ciudad').val();
    var re = isNaN(num);
  if(re===true){ //si el valor es texto
    
  }else{
      alert('SOLO TEXT');
      $('#descr_ciudad').val(null);
  }
}
function abilitarInsertCiu(){
    $('#btnInsertar').show();
    $('#btnLimpiar').show();
    $('#descr_ciudad').focus();
    $('#descr_ciudad').removeAttr("disabled");
   document.getElementById("btnNuevo").style.display = 'none'; 
   document.getElementById("btnReporte").style.display = 'none'; 
}



