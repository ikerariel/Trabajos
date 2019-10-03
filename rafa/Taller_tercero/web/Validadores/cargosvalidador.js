
$(document).ready(function () {
    allCargos();
    $('#btnNuevo').show();
    $('#btnReporte').show();
    $("#descr_cargos").prop('disabled', true);
//    $(":text").val("");

});
function reportesCarg (){
    window.open("ReportesVista/reportescargos.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod": $('#cod_cargos').val(),
        "nomb": $('#descr_cargos').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/cargoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_cargos").val(resp);
            $("#descr_cargos").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarCargosSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/cargoscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_cargos").val(value.car_descripcion);
                $("#descr_cargos").val.focus();
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
        url: "http://localhost:8084/Taller_tercero/cargoscontrol",
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
function allCargos() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/cargoscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idcargo+"</td>"+
                                                        "<td>"+value.car_descripcion+"</td>")));
               
            });
        }
    });
}


function seleccionarCargos() {
    $('#miTabla tr').click(function () {
        $('#cod_cargos').val($(this).find("td").eq(0).html());
        $('#descr_cargos').val($(this).find("td").eq(1).html());
        $('#btnModificar').show();
        $('#btnAnular').show();
        $('#btnLimpiar').show();
        document.getElementById("btnNuevo").style.display = 'none';
        document.getElementById("btnInsertar").style.display = 'none';
        document.getElementById("btnReporte").style.display = 'none';
        $('#descr_cargos').removeAttr("disabled");
    });
}

function campovaciocargo(){  
    var a = $('#descr_cargos').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_cargos').focus();
    }else{
        amb(1);
    }  
}
function controlarCampocargo() {
    var dato;
    var ciudad = $('#descr_cargos').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('YA EXISTE CARGOS');
            $('#descr_cargos').val(null);
            $('#descr_cargos').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampocargo(){
   window.location.reload(); 
}
function abilitarInsertCargo(){
    $('#btnInsertar').show();
    $('#btnLimpiar').show();
    $('#descr_cargos').focus();
    $('#descr_cargos').removeAttr("disabled");
   document.getElementById("btnNuevo").style.display = 'none'; 
   document.getElementById("btnReporte").style.display = 'none'; 
}
function sololetrasCargos(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
        patron =/[A-Za-z\s]/;
        te = String.fromCharCode(tecla);
    return patron.test(te);
}


