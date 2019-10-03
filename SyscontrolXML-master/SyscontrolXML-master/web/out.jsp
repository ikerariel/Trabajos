<%-- 
    Document   : out
    Created on : 29-jul-2019, 0:06:11
    Author     : !mX
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%session.invalidate();%>
    <head>

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="!mX">
        <script language="JavaScript">
            /* Determinamos el tiempo total en segundos */
            var totalTiempo = 5;
            /* Determinamos la url donde redireccionar */
            var url = "login.jsp";

            function updateReloj()
            {
                document.getElementById('CuentaAtras').innerHTML = "DESCONECTANDO en " + totalTiempo + " segundos";

                if (totalTiempo === 0)
                {
                    window.location = url;
                } else {
                    /* Restamos un segundo al tiempo restante */
                    totalTiempo -= 1;
                    /* Ejecutamos nuevamente la función al pasar 1000 milisegundos (1 segundo) */
                    setTimeout("updateReloj()", 1000);
                }
            }
            window.onload = updateReloj;
        </script>

        <title>Saliendo</title>
        <!-- Bootstrap CSS -->
        <link href="Recursos/css/bootstrap.min.css" rel="stylesheet">

        <style>
            .centrar {
                margin-top: 20%;
                margin-left: auto;
                margin-right: auto;
                max-width: 600px;
            }
        </style>
    </head>
    <body>
        <div class="centrar alert alert-danger alert-dismissible text-center" role="alert">
            Seras redirigido a la pagina de login.
            <strong ><h5 id='CuentaAtras'></h5></strong>
        </div>
</html>
