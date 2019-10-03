
$(document).ready(function () {
    opcionesmercaderia();
    allMerca();
//    $(":text").val("");
    nuevoListarCategoria();
    nuevoListarMarcas();
    nuevoListarProcedencia();
    nuevoListarImpuesto();
});

function opcionesmercaderia(){
    $('#costo').keyup(function (){
        numeroDecimal('costo');
    });
  
}

function reportesMercad (){
    window.open("ReportesVista/reportesmercaderias.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod_merca": $('#cod_mercad').val(),
        "m_cost": $('#costo').val(),
        "m_prec": $('#precio').val(),
        "descri_mer": $('#mer_descri').val(),
        "cod_cate": $('#idcate').val(),
        "cod_marca": $('#idmarc').val(),
        "cod_proced": $('#idproceden').val(),
        "cod_impu": $('#idimpuest').val(),
        "cod_generico": $('#genericodigo').val()
    };
}

function  autonumerico() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_mercad").val(resp);
            $("#mer_descri").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarMercaderiaSegunFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#costo").val(value.mer_costo);
                $("#precio").val(value.mer_precio);
                $("#mer_descri").val(value.mer_descripcion);
                $("#genericodigo").val(value.codigogenerico);
                    //  para tirar
                $('#idcate> option[value='+value.idcategoria+']').attr('selected', 'selected');
                $('#idmarc> option[value='+value.idmarca+']').attr('selected', 'selected');
                $('#idproceden> option[value='+value.idprocedencia+']').attr('selected', 'selected');
                $('#idimpuest> option[value='+value.idimpuesto+']').attr('selected', 'selected');
                $("#mer_descri").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}

function seleccionarmerca() {
    $('#miTabla tr').click(function () {
        $('#cod_mercad').val($(this).find("td").eq(0).html());
        $('#mer_descri').val($(this).find("td").eq(1).html());
        $('#costo').val($(this).find("td").eq(2).html());
        $('#precio').val($(this).find("td").eq(3).html());
        $('#idcate').val($(this).find("td").eq(4).html());
        $('#idmarc').val($(this).find("td").eq(5).html());
        $('#idproceden').val($(this).find("td").eq(6).html());
        $('#idimpuest').val($(this).find("td").eq(7).html());
        $('#genericodigo').val($(this).find("td").eq(8).html());
        $("#mer_descri").focus();
    });
}

function campovaciomerca(){  
    var a = $('#mer_descri').val();
    var b = $('#precio').val();
    var c = $('#genericodigo').val();
    if(a === ""){
        alert('Nombre vacio');
        $('#mer_descri').focus();
    }else{
        if(b === ""){
            alert('campo de precios vacio');
            $('#precio').focus();
        }else{
            if(c === ""){
                alert('campo de codigo generico vacio');
                $('#genericodigo').focus();
            }else{
                amb(1);
            }
        }       
    } 
}
function controlarCampomerca() {
    var dato;
    var campo = $('#genericodigo').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(8).html();
        if (dato === campo) {
            alert('CODIGO GENERICO YA EXISTE');
            $('#genericodigo').val(null);
            $('#genericodigo').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampomerca(){
   $(":text").val(""); 
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Operacion Realizado Correctamente...!!!');
            location.reload();
        },
        error: function () {
            alert('Error Durante la Operacion...!!!');
        }
    });
}

function allMerca() {
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idmercaderia+"</td>"+
                        "<td>"+value.mer_descripcion+
                        "<td>"+value.mer_costo+"</td>"+"<td>"+value.mer_precio+"</td>"+
                        "</td>"+"<td>"+value.cate_descri+"</td>"+
                        "<td>"+value.marca_descri+"</td>"+"<td>"+value.proce_descri+"</td>"+
                        "<td>"+value.descri_impuesto+"</td>"+"<td>"+value.codigogenerico+"</td>")));
            });
        }
    });
}

function nuevoListarCategoria() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idcate").append("<option value= \"" + value.idcategoria + "\"> "+value.cate_descri +"</option>");
            });
        }
    });
}
function nuevoListarMarcas() {
    crearJSON(8);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idmarc").append("<option value= \"" + value.idmarca + "\"> "+value.marca_descri +"</option>");
            });
        }
    });
}

function nuevoListarProcedencia() {
    crearJSON(9);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idproceden").append("<option value= \"" + value.idprocedencia + "\"> " + value.proce_descri + "</option>");
            });
        }
    });
}

function nuevoListarImpuesto() {
    crearJSON(10);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (data) {
            $.each(data, function (indice, value) {
                $("#idimpuest").append("<option value= \"" + value.idimpuesto + "\"> " + value.descri_impuesto + "</option>");
            });
        }
    });
}
function calcularprecioventa(){
    var costo = parseInt($("#costo").val().replace(/\./g, ''));
    var precio = costo+(costo*0.50);
    $("#precio").val(precio);
     numeroDecimal('precio');
   
}

function SoloNumerosMerca(input) {
    var num = input.value.replace(/\./g, '');
//    alert("estees" +num);
    if (!isNaN(num)) {
        num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
        num = num.split('').reverse().join('').replace(/^[\.]/, '');
        input.value = num;
    } else {
        alert('Solo se permiten numeros');
        input.value = input.value.replace(/[^\d\.]*/g, '');
    }
}//--------------
//function  ambStock(id) {
//    crearJSON(id);
//    $.ajax({
//        url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
//        data: datosJSON,
//        type: 'POST',
//        dataType: 'text',
//        success: function () {
////            alert('Operacion Realizado Correctamente...!!!');
////            location.reload();
//        },
//        error: function () {
////            alert('Error Durante la Operacion...!!!');
//        }
//    });
//}
//function  ModificarStock() {
//        datosDetalleJSON = {
//            "opcion": 12,
//            "cod_stock": $('#cod_mercad').val(),
//            "descri_mer": $('#mer_descri').val()
//        };
//        $.ajax({
//            url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
//            type: 'POST',
//            data: datosDetalleJSON,
//            cache: false,
//            dataType: 'text',
//            success: function () {
////                alert("Pedido modificado correctamente.!!");
////                window.location.reload();
//            },
//            error: function () {
//            }
//        });
//}
//function  EliminarStock() {
//        datosDetalleJSON = {
//            "opcion": 13,
//            "codigoD": $('#codigo').val()
//        };
//        $.ajax({
//            url: "http://localhost:8084/Taller_tercero/mercaderiacontrol",
//            type: 'POST',
//            data: datosDetalleJSON,
//            cache: false,
//            dataType: 'text',
//            success: function () {
////                alert("Pedido modificado correctamente.!!");
////                window.location.reload();
//            },
//            error: function () {
//            }
//        });
//}


function numeroDecimal(...uno) {
    for (let numero of uno) {
        var num = document.getElementById(numero).value.replace(/\./g, '');
        if (!isNaN(num)) {
            num = num.toString().split('').reverse().join('').replace(/(?=\d*\.?)(\d{3})/g, '$1.');
            num = num.split('').reverse().join('').replace(/^[\.]/, '');
            document.getElementById(numero).value = num;
            $('#'+numero).css('font-weight', 'bold');

        } else {
            alert('Solo se permiten numeros');
            document.getElementById(numero).value = document.getElementById(numero).value.replace(/[^\d\.]*/g, '');            
        }
    }
}