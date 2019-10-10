<%-- 
    Document   : login
    Created on : 21-jun-2019, 10:00:34
    Author     : !mX - Made on Earth by Humans
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="!mX">

        <title>Login - Sistema</title>
        <!-- Bootstrap CSS -->
        <link href="Recursos/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom styles for this template 
        -->
        <link href="Recursos/css/sigmin.css" rel="stylesheet">
       
        <title>Login</title>
    </head>

    <body class="text-center">
        <div class="form-signin">
            <form action="accesoSERVLETXML" method="POST">
                <img style="margin:20px auto;display:block" src="Recursos/img/5TRrpRAGc.png"/>
            
                <h1 class="h3 mb-3 font-weight-normal">Por favor, registrese</h1>
                <label for="inputUsuario" class="sr-only">Usuario</label>
                <input type="text" name="inputUsuario" id="IDinputUsuario" class="form-control" placeholder="Usuario" required="" autofocus="">
                <label for="inputPassword" class="sr-only">Contraseña</label>
                <input type="password" name="inputPassword" class="form-control" placeholder="Contraseña" required="">
                <input type="text" id="vCodUser" name="vCodUser" style="display: none">
                <button class="btn btn-lg btn-primary btn-block" type="submit">Ingresar</button>
                <p class="mt-5 mb-3 text-muted">© 2019 Powered By. www.Bitesys.com</p>
            </form>
        </div>
        <script src="Recursos/vendor/jquery/jquery.min.js"></script>
        <script src="validaciones/validacionesUser.js"></script>
    </body>
</html>
