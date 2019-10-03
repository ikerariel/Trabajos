
$(document).ready(function () {
    allProveedor();
//    $(":text").val("");
//    nuevoListarCiudad();
   // nuevoListarMarcas();
   // nuevoListarProcedencia();
});

function reportesProveedor (){
    window.open("ReportesVista/reportesproveedores.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod_provee": $('#cod_proveedor').val(),
        "nom_prov": $('#prov_nomb').val(),
        "dir_prov": $('#prov_direc').val(),
        "imail_prov": $('#prov_imail').val(),
        "tlef_prov": $('#prov_telef').val(),
        "ruc_prov": $('#prov_ruc').val(),
        //"cod_ciu": $('#descri_ciudad').val()
    };
}

function  autonumerico() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/proveedorescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_proveedor").val(resp);
            $("#prov_nomb").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarproveedorSegunFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/proveedorescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#prov_nomb").val(value.prov_nombre);
                $("#prov_direc").val(value.prov_direc);
                $("#prov_imail").val(value.prov_imail);
                $("#prov_telef").val(value.prov_telf);
                $("#prov_ruc").val(value.prov_ruc);
                    //  para tirar
                //$('#descri_ciudad> option[value='+value.idciudad+']').attr('selected', 'selected');
                //$('#desc_marca> option[value='+value.idmarcas+']').attr('selected', 'selected');
                //$('#desc_proced> option[value='+value.idprocedencia+']').attr('selected', 'selected');
                $("#prov_nomb").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}
function seleccionarprovee() {
    $('#miTabla tr').click(function () {
        $('#cod_proveedor').val($(this).find("td").eq(0).html());
        $('#prov_nomb').val($(this).find("td").eq(1).html());
        $('#prov_direc').val($(this).find("td").eq(2).html());
        $('#prov_imail').val($(this).find("td").eq(3).html());
        $('#prov_telef').val($(this).find("td").eq(4).html());
        $('#prov_ruc').val($(this).find("td").eq(5).html());
        $("#prov_nomb").focus();
    });
}

function campovacioprovee(){  
    var a = $('#prov_nomb').val();
    var b = $('#prov_telef').val();
    var c = $('#prov_ruc').val();
    if(a === ""){
        alert('Nombre vacio');
        $('#prov_nomb').focus();
    }else{
        if(b === ""){
            alert('numero de telefono vacio');
            $('#prov_telef').focus();
        }else{
            if(c === ""){
                alert('RUC vacio');
                $('#prov_ruc').focus();
            }else{
                amb(1);
            }
        }       
    } 
}
function controlarCampoprovee() {
    var dato;
    var campo = $('#prov_ruc').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === campo) {
            alert('EL NOMBRE YA EXISTE');
            $('#prov_ruc').val(null);
            $('#prov_ruc').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampoprovee(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/proveedorescontrol",
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

function allProveedor() {
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/proveedorescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.id_prov+"</td>"+
                        "<td>"+value.prov_nombre+"</td>"+"<td>"+value.prov_direc+"</td>"+
                        "<td>"+value.prov_imail+"</td>"+"<td>"+value.prov_telf+"</td>"+
                        "<td>"+value.prov_ruc+"</td>")));
            });
        }
    });
}

//function nuevoListarCiudad() {
//    crearJSON(7);
//    $.ajax({
//        url: "http://localhost:8084/Taller_tercero/proveedorescontrol",
//        type: 'POST',
//        data: datosJSON,
//        cache: false,
//        success: function (data) {
//            $.each(data, function (indice, value) {
//                $("#descri_ciudad").append("<option value= \"" + value.idciudad + "\"> "+value.ciu_descripcion +"</option>");
//            });
//        }
//    });
//}

//function nuevoListarMarcas() {
//    crearJSON(8);
//    $.ajax({
//        url: "http://localhost:8084/Taller_tercero/proveedorescontrol",
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


