
$(document).ready(function () {
opcionCliente();
    allCliente();
    $('#btnNuevo').show();
    $('#btnReporte').show();
//    $(":text").val("");
//    nuevoListarCiudad();
});

function reportesCliente() {
    window.open("ReportesVista/reportesclientes.jsp");
}
 
 function opcionCliente(){
     $('#ruc_cliente').blur(function (){
           CalcularDV();
     });
     
   
 }

function crearJSON(id) {
    datosJSON = {
        "opcion": id,
        "cod_cli": $('#cod_cliente').val(),
        "ruc_cli": $('#ruc_cliente').val(),
        "razon_cli": $('#razon_cliente').val(),
        "tel_cli": $('#tel_cliente').val(),
        "direc_cli": $('#dire_cliente').val(),
        "correo_cli": $('#corr_cliente').val(),
        "cod_ciu": $('#codiciudad').val(),
         "cvRuc": $('#cv_cliente').val()
    };
}

function  autonumerico() {
    crearJSON(5);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/clientescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function (resp) {
            //alert(resp);
            $("#cod_cliente").val(resp);
            $("#razon_cliente").focus();
        },
        error: function () {
            alert('No se pudo obtener ultimo valor...!!!');
        }
    });
}

function  listarclientesSegunFiltro() {
    crearJSON(6);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/clientescontrol",
        data: datosJSON,
        type: 'POST',
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#ruc_cliente").val(value.cli_ruc);
                $("#razon_cliente").val(value.cli_razonsocial);
                $("#tel_cliente").val(value.cli_telefono);
                $("#dire_cliente").val(value.cli_direccion);
                $("#corr_cliente").val(value.cli_correo);
                $("#codiciudad").val(value.idciudad);
                $("#descri_ciudad").val(value.ciu_descripcion);
                //  para tirar
//                $('#codiciudad> option[value=' + value.idciudad + ']').attr('selected', 'selected');
//                $('#descri_ciudad> option[value='+value.ciu_descripcion+']').attr('selected', 'selected');
                //$('#desc_proced> option[value='+value.idprocedencia+']').attr('selected', 'selected');
                $("#razon_cliente").focus();
            });
            return true;
        },
        error: function () {
            alert('No existe registro segun codigo ingresado!!!');
            return false;
        }
    });
}
function recuperar() {
    $('#miTabla tr').click(function () {
        $('#cod_cliente').val($(this).find("td").eq(0).html());
        $('#ruc_cliente').val($(this).find("td").eq(1).html());
        $('#razon_cliente').val($(this).find("td").eq(2).html());
        $('#tel_cliente').val($(this).find("td").eq(3).html());
        $('#dire_cliente').val($(this).find("td").eq(4).html());
        $('#corr_cliente').val($(this).find("td").eq(5).html());
        $('#codiciudad').val($(this).find("td").eq(6).html());
        $('#descri_ciudad').val($(this).find("td").eq(7).html());
        $("#razon_cliente").focus();
        $('#btnModificar').show();
        $('#btnAnular').show();
        $('#btnLimpiar').show();
        document.getElementById("btnNuevo").style.display = 'none';
        document.getElementById("btnInsertar").style.display = 'none';
        document.getElementById("btnReporte").style.display = 'none';
        $('#cod_cliente').removeAttr("disabled");
        $('#ruc_cliente').removeAttr("disabled");
        $('#razon_cliente').removeAttr("disabled");
        $('#tel_cliente').removeAttr("disabled");
        $('#dire_cliente').removeAttr("disabled");
        $('#corr_cliente').removeAttr("disabled");
        $('#descri_ciudad').removeAttr("disabled");
    });
}
function abilitarInsertCli() {
    $('#btnInsertar').show();
    $('#btnLimpiar').show();
    $('#cod_cliente').removeAttr("disabled");
    $('#ruc_cliente').removeAttr("disabled");
    $('#razon_cliente').removeAttr("disabled");
    $('#tel_cliente').removeAttr("disabled");
    $('#dire_cliente').removeAttr("disabled");
    $('#corr_cliente').removeAttr("disabled");
    $('#descri_ciudad').removeAttr("disabled");
    document.getElementById("btnNuevo").style.display = 'none';
    document.getElementById("btnReporte").style.display = 'none';
    $('#razon_cliente').focus();
}

function guardarCliente() {
    var a = $('#ruc_cliente').val();
    var b = $('#razon_cliente').val();
    var c = $('#tel_cliente').val();
    if (a === "") {
        alert('ruc vacio');
        $('#ruc_cliente').focus();
    } else {
        if (b === "") {
            alert('r social vacio');
            $('#razon_cliente').focus();
        } else {
            if (c === "") {
                alert('tel vacio');
                $('#tel_cliente').focus();
            } else {
                amb(1);
            }
        }
    }
}
function controlarCampoclient() {
    var dato;
    var campo = $('#ruc_cliente').val();
//       alert(ciudad);
    $('#miTabla tr').each(function () {
        dato = $(this).find("td").eq(1).html();
        if (dato === campo) {
            alert('EL NOMBRE YA EXISTE');
            $('#ruc_cliente').val(null);
            $('#ruc_cliente').focus();
        } else {
//            campovacio();
        }
    });
}
function limpiarcampoclient() {
    window.location.reload();
}
function pasarotrocampo() {
    $('#ruc_cliente').focus();
}

function  amb(id) {
    crearJSON(id);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/clientescontrol",
        data: datosJSON,
        type: 'POST',
        dataType: 'text',
        success: function () {
            alert('Operacion realisado Correctamente...!!!');
            location.reload();
        },
        error: function () {
            alert('Error Durante la Operacion...!!!');
        }
    });
}

function allCliente() {
    crearJSON(4);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/clientescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTabla").append($("<tr>").append($("<td>" + value.idclientes + "</td>" +
                        "<td>" + value.cli_ruc + "</td>" + "<td>" + value.cli_razonsocial + "</td>" +
                        "<td>" + value.cli_telefono + "</td>" + "<td>" + value.cli_direccion + "</td>" +
                        "<td>" + value.cli_correo + "</td>" + "<td style=display:none>" + value.idciudad + "</td>" +
                        "<td>" + value.ciu_descripcion + "</td>")));
            });
        }
    });
}
function allCiudades() {
    crearJSON(7);
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/clientescontrol",
        type: 'POST',
        data: datosJSON,
        cache: false,
        success: function (resp) {
            $.each(resp, function (indice, value) {
                $("#miTablaC").append($("<tr>").append($("<td style=display:none>" + value.idciudad + "</td>" +
                        "<td>" + value.ciu_descripcion + "</td>")));
            });
        }
    });
}
function buscadorTablaCiudades() {
    var tableReg = document.getElementById('miTablaC');
    var searchText = document.getElementById('filtrarCiudad').value.toLowerCase();
    var cellsOfRow = "";
    var found = false;
    var compareWith = "";

// Recorremos todas las filas con contenido de la tabla
    for (var i = 1; i < tableReg.rows.length; i++)
    {
        cellsOfRow = tableReg.rows[i].getElementsByTagName('td');
        found = false;
// Recorremos todas las celdas
        for (var j = 0; j < cellsOfRow.length && !found; j++)
        {
            compareWith = cellsOfRow[j].innerHTML.toLowerCase();
// Buscamos el texto en el contenido de la celda
            if (searchText.length == 0 || (compareWith.indexOf(searchText) > -1))
            {
                found = true;
            }
        }
        if (found)
        {
            tableReg.rows[i].style.display = '';
        } else {
// si no ha encontrado ninguna coincidencia, esconde la
// fila de la tabla
            tableReg.rows[i].style.display = 'none';
        }
    }
}
function seleccionCiudades() {
    $('#miTablaC tr').click(function () {
        $('#codiciudad').val($(this).find("td").eq(0).html());
        $('#descri_ciudad').val($(this).find("td").eq(1).html());
        $('#grillaCiudades').modal('hide');
    });
}
function abrirCiudad() {
    if ($('#descri_ciudad').val() === "") {
        $('#grillaCiudades').modal('show');
        $('#miTablaC').find('tbody').find('tr').empty();
        allCiudades();

    } else {

    }
}
function validarEmail(valor) {
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,4})+$/.test(valor)) {
        alert("La dirección de email " + valor + " es correcta.");
    } else {
        alert("La dirección de email es incorrecta.");
    }
}

function CalcularDV() {
    jsonCV = {
        'opcion':8,
        'cedula': $('#ruc_cliente').val(),
        'basemax': 11
    };
    $.ajax({
        url: "http://localhost:8084/Taller_tercero/clientescontrol",
        type: 'POST',
        data: jsonCV,
        cache: false,
        dataType: 'text',
        success: function (resp) {
            $('#cv_cliente').val(resp);
           
        }
    });
}
