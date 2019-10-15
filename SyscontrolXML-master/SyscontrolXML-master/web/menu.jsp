<%-- 
    Document   : menu
    Created on : 23-abr-2019, 23:12:51
    Author     : !mX - Made on Earth by Humans
--%>
<script>
    setInterval(function () {
        var fv = new Date();
        var meses = new Array("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre");
        var diasSemana = new Array("Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado");
        var f = new Date();
        $('#fechareal').html(diasSemana[f.getDay()] + ", " + f.getDate() + " de " + meses[f.getMonth()] + " de " + f.getFullYear() + " - " + f.getHours() + ":" + f.getMinutes() + ":" + f.getSeconds());
    }, 1000);
</script>
<div class="bg-light border-right" id="sidebar-wrapper">
    <div class="sidebar-heading">Control Recargas</div><div id="fechareal" style="font-size: 11px; font-weight: bold; text-align: center"></div> 
    <div class="list-group list-group-flush">
        <a href="inicio.jsp" class="list-group-item list-group-item-action bg-light">Inicio</a>
        <a href="caja.jsp" class="list-group-item list-group-item-action bg-light">Apertura / Cierre</a>
        <a href="recargas.jsp" class="list-group-item list-group-item-action bg-light">Recargas</a>
        <a href="giros.jsp" class="list-group-item list-group-item-action bg-light">Billeteras / Giros</a>
        <a href="consulta.jsp" class="list-group-item list-group-item-action bg-light">Consulta</a>
        <a href="pagos.jsp" class="list-group-item list-group-item-action bg-light">Pagos</a>
        <a href="reportes.jsp" class="list-group-item list-group-item-action bg-light">Reportes</a>
        <hr>
        <a href="ventas.jsp" class="list-group-item list-group-item-action bg-light">Ventas</a>
        <a href="Facturacion.jsp" class="list-group-item list-group-item-action bg-light">Facturación</a>
        <a href="Cobros.jsp" class="list-group-item list-group-item-action bg-light">Cobros</a>
        <a href="out.jsp" class="list-group-item list-group-item-action bg-light">Salir</a>
    </div>
</div>
