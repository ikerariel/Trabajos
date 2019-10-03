
$(document).ready(function () {
    allCategoria();
    $('#btnNuevo').show();
    $('#btnReporte').show();
    $("#descr_categoria").prop('disabled', true);
});

function reportesCatego (){
    window.open("ReportesVista/reportescategorias.jsp");
}

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "codcate": $('#cod_categoria').val(),
        "nombcate": $('#descr_categoria').val()
        
    };
}

function  autonumerico() {
    crearJSON(5);
    //alert('Llega a autonumerico');
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/categoriascontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_categoria").val(resp);
            $("#descr_categoria").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarCategoriaSegunFiltro() {
   // alert('Llega listarClienteSegunFiltro ');
    crearJSON(6);
    
    $.ajax({
        // url: "clientesControl",
        url: "http://localhost:8084/Taller_tercero/categoriascontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice,value){
                $("#descr_categoria").val(value.cate_descri);
                $("#descr_categoria").val.focus();
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
        url: "http://localhost:8084/Taller_tercero/categoriascontrol",
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

function allCategoria() {
//alert("Llega allClientes");
 crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/categoriascontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,        
        success: function (resp) {
              //alert(resp);
           // var jparse=JSON.parse(resp);
           // alert(jparse);
            $.each(resp, function (indice,value){
                $("#miTabla").append($("<tr>").append($("<td>"+value.idcategoria+"</td>"+
                                                        "<td>"+value.cate_descri+"</td>")));
                
            });
          
            //$('#miTableHtml').html(resp);
        }
    });
    
    
}

function seleccioncate() {
    $('#miTabla tr').click(function () {
        $('#cod_categoria').val($(this).find("td").eq(0).html());
        $('#descr_categoria').val($(this).find("td").eq(1).html());
        $('#btnModificar').show();
        $('#btnAnular').show();
        $('#btnLimpiar').show();
        document.getElementById("btnNuevo").style.display = 'none';
        document.getElementById("btnInsertar").style.display = 'none';
        document.getElementById("btnReporte").style.display = 'none';
        $('#descr_categoria').removeAttr("disabled");
    });
}

function campovaciocate(){  
    var a = $('#descr_categoria').val();
    if(a === ""){
        alert('campo vacio');
        $('#descr_categoria').focus();
    }else{
        amb(1);
    }  
}
function controlarCampocate() {
    var dato;
    var ciudad = $('#descr_categoria').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === ciudad) {
            alert('EL NOMBRE YA EXISTE');
            $('#descr_categoria').val(null);
            $('#descr_categoria').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampocate(){
   window.location.reload(); 
}

function abilitarInsertCate(){
    $('#btnInsertar').show();
    $('#btnLimpiar').show();
    $('#descr_categoria').focus();
    $('#descr_categoria').removeAttr("disabled");
   document.getElementById("btnNuevo").style.display = 'none'; 
   document.getElementById("btnReporte").style.display = 'none'; 
}

function sololetrasCate(e) {
    tecla = (document.all) ? e.keyCode : e.which;
    if (tecla==8) return true;
        patron =/[A-Za-z\s]/;
        te = String.fromCharCode(tecla);
    return patron.test(te);
}



