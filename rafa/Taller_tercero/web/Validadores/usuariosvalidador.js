 
$(document).ready(function () {
    allUsuario();
//    $(":text").val("");
    nuevoListarFuncionario();
    nuevoListarPerfil();
    nuevoListarSucursal();
});

function reportesUsuarios (){
    window.open("ReportesVista/reportesusuarios.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod_usu": $('#cod_usua').val(),
        "nom_usu": $('#usu_nomb').val(),
        "clav_usu": $('#usu_clav').val(),
        "cod_funci": $('#fun_descri').val(),
        "cod_perf": $('#perf_drscri').val(),
        "cod_sucu": $('#sucu_descri').val()
    };
}

function  autonumerico() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/usuarioscontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_usua").val(resp);
            $("#usu_nomb").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarUsuarioSegunFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/usuarioscontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#usu_nomb").val(value.usu_nombre);
                $("#usu_clav").val(value.usu_clave);
                    //  para tirar
                $('#fun_descri> option[value='+value.idfuncionario+']').attr('selected', 'selected');
                $('#perf_drscri> option[value='+value.idperfil+']').attr('selected', 'selected');
                $('#sucu_descri> option[value='+value.idsucursal+']').attr('selected', 'selected');
                $("#usu_nomb").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarusua() {
    $('#miTabla tr').click(function () {
        $('#cod_usua').val($(this).find("td").eq(0).html());
        $('#usu_nomb').val($(this).find("td").eq(1).html());
        $('#usu_clav').val($(this).find("td").eq(2).html());
        $('#fun_descri').val($(this).find("td").eq(3).html());
        $('#perf_drscri').val($(this).find("td").eq(4).html());
        $('#sucu_descri').val($(this).find("td").eq(5).html());
        $("#usu_nomb").focus();
    });
}

function campovaciousua(){  
    var a = $('#usu_nomb').val();
    var b = $('#usu_clav').val();
    if(a === ""){
        alert('Usuario vacio');
        $('#usu_nomb').focus();
    }else{
        if(b === ""){
            alert('Clave vacio');
            $('#usu_clav').focus();
        }else{
            amb(1);
        }       
    } 
}
function controlarCampousua() {
    var dato;
    var campo = $('#usu_nomb').val();
    var campos = $('#usu_clav').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === campo) {
            alert('EL NOMBRE YA EXISTE');
            $('#usu_nomb').val(null);
            $('#usu_nomb').focus();
        } else {
            dato = $(this).find("td").eq(2).html();
            if (dato === campos){
                alert('La Clave ya existe');
                $('#usu_clav').val(null);
                $('#usu_clav').focus();
            }else{
                
            }
//            campovacio();
        }
    });
}
function limpiarcampousua(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/usuarioscontrol",
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

function allUsuario() {
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/usuarioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idusuario+"</td>"+
                                                        "<td>"+value.usu_nombre+"</td>"+
                                                        "<td>"+value.usu_clave+"</td>"+
                                                        "<td>"+value.fun_nombres+"  "+value.fun_apellidos +"</td>"+
                                                        "<td>"+value.per_descripcion+"</td>"+
                                                        "<td>"+value.suc_descripcion+"</td>")));
            });
        }
    });
}

function nuevoListarFuncionario() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/usuarioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#fun_descri").append("<option value= \"" + value.idfuncionario + "\"> "+value.fun_nombres+"  "+value.fun_apellidos +"</option>");
            });
        }
    });
}
function nuevoListarPerfil() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/usuarioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#perf_drscri").append("<option value= \"" + value.idperfil + "\"> "+value.per_descripcion +"</option>");
            });
        }
    });
}

function nuevoListarSucursal() {
    crearJSON(9);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/usuarioscontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#sucu_descri").append("<option value= \"" + value.idsucursal + "\"> " + value.suc_descripcion + "</option>");
            });
        }
    });
}


