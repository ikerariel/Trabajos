
$(document).ready(function () {
    allSucursal();
//    $(":text").val("");
    nuevoListarCiudad();
    nuevoListarDepo();
   // nuevoListarProcedencia();
});

function reportesSucursal (){
    window.open("ReportesVista/reportessucursales.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod_suc": $('#codi_sucursal').val(),
        "suc_desc": $('#sucur_descrip').val(),
        "cod_ciu": $('#descri_ciudad').val(),
        "cod_depo": $('#descri_deposit').val()
    };
}

function  autonumerico() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/sucursalcontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#codi_sucursal").val(resp);
            $("#sucur_descrip").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarsucursalSegunFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/sucursalcontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#sucur_descrip").val(value.suc_descripcion);
                    //  para tirar
                $('#descri_ciudad> option[value='+value.idciudad+']').attr('selected', 'selected');
                $('#descri_deposit> option[value='+value.iddeposito+']').attr('selected', 'selected');
                //$('#desc_proced> option[value='+value.idprocedencia+']').attr('selected', 'selected');
                $("#sucur_descrip").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarsucur() {
    $('#miTabla tr').click(function () {
        $('#codi_sucursal').val($(this).find("td").eq(0).html());
        $('#sucur_descrip').val($(this).find("td").eq(1).html());
        $('#descri_deposit').val($(this).find("td").eq(2).html());
    });
}
function campovaciosucur(){  
    var a = $('#sucur_descrip').val();
    if(a === ""){
        alert('campo vacio');
        $('#sucur_descrip').focus();
    }else{
        amb(1);
    }  
}
function controlarCamposucur() {
    var dato;
    var ciudad = $('#sucur_descrip').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#sucur_descrip').val(null);
            $('#sucur_descrip').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcamposucur(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/sucursalcontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Registro Insertado Correctamente...!!!');
            location.reload();
        },
        error: function () {
            alert('Error Durante la Insercion del Registro...!!!');
        }
    });
}

function allSucursal() {
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/sucursalcontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idsucursal+"</td>"+
                        "<td>"+value.suc_descripcion+"</td>"+
                        "<td>"+value.ciu_descripcion+"</td>"+
                        "<td>"+value.dep_descripcion+"</td>")));
            });
        }
    });
}

function nuevoListarCiudad() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/sucursalcontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#descri_ciudad").append("<option value= \"" + value.idciudad + "\"> "+value.ciu_descripcion +"</option>");
            });
        }
    });
}

function nuevoListarDepo() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/sucursalcontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#descri_deposit").append("<option value= \"" + value.iddeposito + "\"> "+value.dep_descripcion +"</option>");
            });
        }
    });
}
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


