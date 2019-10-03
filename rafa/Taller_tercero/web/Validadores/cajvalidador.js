
$(document).ready(function () {
    allCaja();
    $('#btnNuevo').show();
    $('#btnReporte').show();
//    $("#descr_caja").prop('disabled', true);
//    $(":text").val("");
});

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_caja').val(),
        "nomb": $('#descr_caja').val()
        
    };
}
function reportescaja (){
    window.open("ReportesVista/reportescajas.jsp");
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/cajacontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_caja").val(resp);
            $("#descr_caja").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarCajaSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    $('#btnModificar').show();
        $('#btnAnular').show();
        $('#btnLimpiar').show();
        document.getElementById("btnNuevo").style.display = 'none';
        document.getElementById("btnInsertar").style.display = 'none';
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/cajacontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_caja").val(value.caja_descripcion);
                $("#descr_caja").val.focus();
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
        url: "http://localhost:8084/Taller_tercero/cajacontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Operacion Exitosa...!!!');
            location.reload();
            //allClientes();
        },
        error: function () {
            alert('Error Durante la Operacion...!!!');
        }
    });
}
function allCaja() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/cajacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idcaja+"</td>"+
                                                        "<td>"+value.caja_descripcion+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
}

function campovacio(){  
    var a = $('#descr_caja').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_caja').focus();
    }else{
        amb(1);
    }  
}
function controlarCampo() {
    var dato;
    var ciudad = $('#descr_caja').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_caja').val(null);
            $('#descr_caja').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampo(){
   window.location.reload(); 
}

function abilitarInsert(){
    $('#btnInsertar').show();
    $('#btnLimpiar').show();
    $('#descr_caja').focus();
    $('#descr_caja').removeAttr("disabled");
   document.getElementById("btnNuevo").style.display = 'none'; 
   document.getElementById("btnReporte").style.display = 'none'; 
}

//function SolotextoCaja(){
//    var num = $('#descr_caja').val();
//    var re = isNaN(num);
//  if(re===true){ //si el valor es texto    
//  }else{
//      alert('SOLO TEXT');
//      $('#descr_caja').val(null);
//  }
//}

function selecc() {
    $('#miTabla tr').click(function () {
        $('#cod_caja').val($(this).find("td").eq(0).html());
        $('#descr_caja').val($(this).find("td").eq(1).html());
        $('#btnModificar').show();
        $('#btnAnular').show();
        $('#btnLimpiar').show();
        document.getElementById("btnNuevo").style.display = 'none';
        document.getElementById("btnInsertar").style.display = 'none';
        document.getElementById("btnReporte").style.display = 'none';
        $('#descr_caja').removeAttr("disabled");
    });
}
function validarsololetras(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
        patron =/[A-Za-z\s]/;
        te = String.fromCharCode(tecla);
    return patron.test(te);
}
//function soloLetras(e){
//       key = e.keyCode || e.which;
//       tecla = String.fromCharCode(key).toLowerCase();
//       letras = " áéíóúabcdefghijklmnñopqrstuvwxyz";
//       especiales = "8-37-39-46";
//
//       tecla_especial = false
//       for(var i in especiales){
//            if(key == especiales[i]){
//                tecla_especial = true;
//                break;
//            }
//        }
//
//        if(letras.indexOf(tecla)==-1 && !tecla_especial){
//            return false;
//        }
//}
//<!--<input type="text" <input type="text" onkeypress="return soloLetras(event)">">-->

//function sololetras(){
//    if (event.keyCode >45 && event.keyCode  <57) event.returnValue =  false;
//}

//function seleccion() {
//    $('#miTabla tr').click(function () {
//        $('#cod_caja').val($(this).find("td").eq(0).html());
////        $('#v_estado').val($(this).find("td").eq(4).html());
//        $('#descr_caja').val($(this).find("td").eq(1).html()); /*Extrae el valor de la fila seleccionada y lo muestra en el campo
//         //         * v_nroPlanilla*/
//        var estado = $('#v_estado').val();
//        if (estado === 'PENDIENTE') {
//            document.getElementById('v_estado').style.color = "#000000";
//            document.getElementById('v_estado').style.background = "PaleGoldenrod";
//        }
//        if (estado === 'AUTORIZADO') {
//
//            document.getElementById('v_estado').style.background = "firebrick";
//            document.getElementById('v_estado').style.color = "#ffffff";
//        }
//
//    });
//}
