
$(document).ready(function () {
    
    allDeposit();
//    nuevoListarSucursal();   
    // nuevoListarMarcas();
    // nuevoListarProcedencia();
});

function reportesDepos (){
    window.open("ReportesVista/reportesdeposito.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod_depos": $('#cod_deposito').val(),
        "dep_descri": $('#depo_descrip').val()
    };
}

function  autonumericodepo() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/depositoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_deposito").val(resp);
            $("#depo_descrip").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listardepositoSegunFiltro() {
    // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/depositoscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#depo_descrip").val(value.dep_descripcion);
                //  para tirar
                //$('#descri_sucur> option[value=' + value.idsucursal + ']').attr('selected', 'selected');
                //$('#desc_marca> option[value='+value.idmarcas+']').attr('selected', 'selected');
                //$('#desc_proced> option[value='+value.idprocedencia+']').attr('selected', 'selected');
                $("#depo_descrip").focus();
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
        url: "http://localhost:8084/Taller_tercero/depositoscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('operacion Realizado Correctamente...!!!');
            location.reload();
        },
        error: function () {
            alert('Error Durante la Operacion del Registro...!!!');
        }
    });
}

function selecdepo() {
    $('#miTabla tr').click(function () {
        $('#cod_deposito').val($(this).find("td").eq(0).html());
        $('#depo_descrip').val($(this).find("td").eq(1).html());
    });
}
function campovaciodepo(){  
    var a = $('#depo_descrip').val();
    if(a === ""){
        alert('campo vacio');
        $('#depo_descrip').focus();
    }else{
        amb(1);
    }  
}
function controlarCampodepo() {
    var dato;
    var ciudad = $('#depo_descrip').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#depo_descrip').val(null);
            $('#depo_descrip').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampodepo(){
   $(":text").val(""); 
}

function allDeposit() {
    crearJSON(4);
//    alert("Llega alldeposito");
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/depositoscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
//            alert("resp");
            $.each(resp, function (indice, value) {
                $("#miTabla").append($("<tr>").append($("<td>" + value.iddeposito + "</td>" +
                        "<td>" + value.dep_descripcion + "</td>")));
            });
        }
    });
}


//function nuevoListarSucursal() {
//    
//    crearJSON(7);
////    alert("Llega allsucursal");
//    $.ajax({
//        url: "http://localhost:8084/Taller_tercero/depositoscontrol",
//        type: 'POST',
//        data: datosJSON,
//        cache: false,
//        success: function (data) {
//            $.each(data, function (indice, value) {
//                $("#descri_sucur").append("<option value= \"" + value.idsucursal + "\"> " + value.suc_descripcion + "</option>");
//            });
//        }
//    });
//}

//function nuevoListarMarcas() {
//    crearJSON(8);
//    $.ajax({
//        url: "http://localhost:8084/Taller_tercero/clientescontrol",
//        type: 'POST',
//        data: datosJSON,
//        cache: false,
//        success: function (data) {
//            $.each(data, function (indice, value) {
//                $("#desc_marca").append("<option value= \"" + value.idmarcas + "\"> " + value.descripcion + "</option>");
//            });
//        }
//    });
//}
//
//function nuevoListarProcedencia() {
//    crearJSON(9);
//    $.ajax({
//        url: "http://localhost:8084/Taller_tercero/clientescontrol",
//        type: 'POST',
//        data: datosJSON,
//        cache: false,
//        success: function (data) {
//            $.each(data, function (indice, value) {
//                $("#desc_proced").append("<option value= \"" + value.idprocedencia + "\"> " + value.descripcion + "</option>");
//            });
//        }
//    });
//}


