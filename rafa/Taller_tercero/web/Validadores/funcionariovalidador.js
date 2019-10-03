
$(document).ready(function () {
    allFuncionar();
//    $(":text").val("");
    nuevoListarCarg();
    nuevoListarCiudades();
    
   // nuevoListarProcedencia();
});

function reportesFuncionar (){
    window.open("ReportesVista/reportesfuncionarios.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod_func": $('#cod_funcionar').val(),
        "fun_dire": $('#direcc_fun').val(),
        "fun_corr": $('#correo_fun').val(),
        "fun_ci": $('#cedula_fun').val(),
        "fun_nomb": $('#nombre_fun').val(),
        "fum_apell": $('#apellido_fun').val(),
        "fun_telef": $('#telefono_fun').val(),
        "cod_carg": $('#desc_cargo').val(),
        "cod_ciu": $('#descri_ciudad').val()
    };
}

function  autonumerico() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/funcionariocontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_funcionar").val(resp);
            $("#nombre_fun").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarfuncionarioSegunFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/funcionariocontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#direcc_fun").val(value.fun_direccion);
                $("#correo_fun").val(value.fun_correo);
                $("#cedula_fun").val(value.fun_ci);
                $("#nombre_fun").val(value.fun_nombres);
                $("#apellido_fun").val(value.fun_apellidos);
                $("#telefono_fun").val(value.fun_telefono);
                    //  para tirar
                $('#desc_cargo> option[value='+value.idcargo+']').attr('selected', 'selected');
                $('#descri_ciudad> option[value='+value.idciudad+']').attr('selected', 'selected');
                //$('#desc_proced> option[value='+value.idprocedencia+']').attr('selected', 'selected');
                $("#nombre_fun").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarfuncio() {
    $('#miTabla tr').click(function () {
        $('#cod_funcionar').val($(this).find("td").eq(0).html());
        $('#nombre_fun').val($(this).find("td").eq(1).html());
        $('#apellido_fun').val($(this).find("td").eq(2).html());
        $('#direcc_fun').val($(this).find("td").eq(3).html());
        $('#correo_fun').val($(this).find("td").eq(4).html());
        $('#cedula_fun').val($(this).find("td").eq(5).html());
        $('#telefono_fun').val($(this).find("td").eq(6).html());
        $('#desc_cargo').val($(this).find("td").eq(7).html());
        $('#descri_ciudad').val($(this).find("td").eq(8).html());
        $("#nombre_fun").focus();
    });
}

function campovaciofuncio(){  
    var a = $('#nombre_fun').val();
    var b = $('#apellido_fun').val();
    var c = $('#cedula_fun').val();
    if(a === ""){
        alert('Nombre vacio');
        $('#nombre_fun').focus();
    }else{
        if(b === ""){
            alert('Apellido vacio');
            $('#apellido_fun').focus();
        }else{
            if(c === ""){
                alert('Numero de Cedula vacio');
                $('#cedula_fun').focus();
            }else{
                amb(1);
            }
        }       
    } 
}
function controlarCampofuncio() {
    var dato;
    var campo = $('#cedula_fun').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === campo) {
            alert('EL NOMBRE YA EXISTE');
            $('#cedula_fun').val(null);
            $('#cedula_fun').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampofuncio(){
   $(":text").val(""); 
}
function  amb(id) {
    crearJSON(id);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/funcionariocontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Operacio Realizado Correctamente...!!!');
            location.reload();
        },
        error: function () {
            alert('Error Durante la Operacion...!!!');
        }
    });
}

function allFuncionar() {
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/funcionariocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idfuncionario+"</td>"+
                                                        "<td>"+value.fun_direccion+"</td>"+
                                                        "<td>"+value.fun_correo+"</td>"+
                                                        "<td>"+value.fun_ci+"</td>"+
                                                        "<td>"+value.fun_nombres+"</td>"+
                                                        "<td>"+value.fun_apellidos+"</td>"+
                                                        "<td>"+value.fun_telefono+"</td>"+
                                                        "<td>"+value.car_descripcion+"</td>"+
                                                        "<td>"+value.ciu_descripcion+"</td>")));
            });
        }
    });
}

function nuevoListarCarg() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/funcionariocontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#desc_cargo").append("<option value= \"" + value.idcargo + "\"> "+value.car_descripcion +"</option>");
            });
        }
    });
}
function nuevoListarCiudades() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/funcionariocontrol",
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


