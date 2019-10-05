<%-- 
    Document   : menutercero
    Created on : 12/12/2017, 09:50:05 PM
    Author     : naty
--%>
<script src="Validadores/acceso_v.js"></script>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<header>

    <div class="menu_bar">
        <a href="#" class="bt-menu"><span></span>Menu</a>
    </div>
    <nav>
        <ul>
            <li><a href="menuprincipal_v.jsp"><span class="icon-home"></span>Inicio</a></li>
            <li class="submenu">
                <a style="display: none" href="#" id="menu_compras"><span class="icon-cart"  ></span>compra<span class="caret"></span></a>
                <ul class="children">
                    <li><a id="me_pedidocompras" style="display: none" href="pedidocompravista.jsp">Pedido Compra<span class="icon-file-zip"></span></a></li>
                    <li><a id="me_presucompra" style="display: none" href="presupuestocompravista.jsp">Presupuesto Compra<span class="icon-file-zip"></span></a></li>
                    <li><a id="me_ordencompra" style="display: none" href="ordencompravista.jsp">Orden Compra<span class="icon-price-tags"></span></a></li>
                    <li><a id="me_faccompra" style="display: none" href="facturacompravista.jsp">Factura Compra<span class="icon-price-tags"></span></a></li>
                    <li><a id="me_Notaremision" style="display: none" href="notaremisionvista.jsp">Nota Remision<span class="icon-price-tags"></span></a></li>
                    <li><a id="me_notacredito" style="display: none" href="notacreditodebitovista.jsp">Nota Credito<span class="icon-price-tags"></span></a></li>
                    <li><a id="me_ajuste" style="display: none" href="ajustevista.jsp">Ajuste<span class="icon-price-tags"></span></a></li>
                </ul>
            </li>
            <li class="submenu">
                <a href="#" id="menu_referencialC" style="display: none"><span class="icon-users"></span>Ref.Compras<span class="caret"></span></a>
                <ul class="children">
                    <li><a id="me_deposito" style="display: none" href="depositovista.jsp">Depositos<span class="icon-hammer"></span></a></li>
                    <li><a id="me_categoria" style="display: none" href="categoriasvista.jsp">categorias<span class="icon-hammer"></span></a></li>
                    <li><a id="me_estado" style="display: none" href="estadovista.jsp">Estado<span class="icon-hammer"></span></a></li>
                    <li><a id="me_estante" style="display: none" href="estantesvista.jsp">Estante<span class="icon-hammer"></span></a></li>
                    <li><a id="me_sector" style="display: none" href="sectorvista.jsp">Sector<span class="icon-hammer"></span></a></li>
                    <li><a id="me_impuesto" style="display: none" href="impuestovista.jsp">Impuesto<span class="icon-hammer"></span></a></li>
                    <li><a id="me_marcas" style="display: none" href="marcavista.jsp">Marcas<span class="icon-hammer"></span></a></li>
                    <li><a id="me_mercaderia" style="display: none" href="mercaderiavista.jsp">Mercaderia<span class="icon-hammer"></span></a></li>
                    <li><a id="me_motivoajuste" style="display: none" href="motivoajustevista.jsp">Motivo Ajustes<span class="icon-hammer"></span></a></li>
                    <li><a id="me_motivomerma" style="display: none" href="motivomermavista.jsp">Motiv Mermas<span class="icon-hammer"></span></a></li>
                    <li><a id="me_procedencia" style="display: none" href="procedenciavista.jsp">Procedencia<span class="icon-hammer"></span></a></li>
                </ul>
            </li>
            <li class="submenu">
                <a href="#" id="menu_referencialV" style="display: none"><span class="icon-users"></span>Ref.Ventas<span class="caret"></span></a>
                <ul class="children">
                    <li><a id="me_caja" style="display: none" href="cajavista.jsp">Cajas<span class="icon-hammer"></span></a></li>
                    <li><a id="me_cliente" style="display: none" href="clivista.jsp">Clientes<span class="icon-hammer"></span></a></li>
                    <li><a id="me_cargos" style="display: none" href="cargosvista.jsp">Cargos<span class="icon-hammer"></span></a></li>
                    <li><a id="me_funcionario" style="display: none" href="funcionariovista.jsp">Funcionario<span class="icon-hammer"></span></a></li>
                    <li><a id="me_ciudades" style="display: none" href="ciudadesvista.jsp">Ciudad<span class="icon-hammer"></span></a></li>
                    <li><a id="me_perfil" style="display: none" href="perfilvista.jsp">Perfiles<span class="icon-hammer"></span></a></li>
                    <li><a id="me_unidadmedi" style="display: none" href="unidadmedidavista.jsp">Uni. Medida<span class="icon-hammer"></span></a></li>
                    <li><a id="me_proveedor" style="display: none" href="proveedoresvista.jsp">Proveedor<span class="icon-hammer"></span></a></li>
                    <li><a id="me_sucursal" style="display: none" href="sucursalvista.jsp">Sucursal<span class="icon-hammer"></span></a></li>
                    <li><a id="me_usuario" style="display: none" href="usuariovista.jsp">Usuarios<span class="icon-hammer"></span></a></li>
                    <li><a id="me_timbrado" style="display: none" href="timbradovista.jsp">Timbrado<span class="icon-hammer"></span></a></li>
                </ul>
            </li>

            <li class="submenu">
                <a href="#" id="menu_FacturacionV" style="display: none"><span class="icon-rocket"></span>Facturacion<span class="caret"></span></a>
                <ul class="children">
                    <li><a id="me_apertura" style="display: none" href="aperturacierrevista.jsp">Apertura<span class="icon-hammer"></span></a></li>
                    <li><a id="me_pedidoventa" style="display: none" href="pedidoventavista.jsp">Pedido Ventas<span class="icon-hammer"></span></a></li>
                    <li><a id="me_presuventa" style="display: none" href="presupuestoventavista.jsp">Presupuesto Ventas<span class="icon-hammer"></span></a></li>
                    <li><a href="#">Impuestos<span class="icon-pushpin"></span></a></li>

                </ul>
            </li>

            <li class="submenu">
                <a href="#"><span class="icon-stats-dots"></span>Reportes<span class="caret"></span></a>
                <ul class="children">
                    <li><a href="ReportesVista/reportescajas.jsp">Cajas<span class="icon-stats-bars"></span></a></li>
                    <li><a href="#">Avanzados<span class="icon-stats-bars2"></span></a></li>
                </ul>
            </li>
            <li><a href="out.jsp"><span class="icon-switch"></span>Salir como ${sessionScope.sessionON}</a></li>
            <input style="display: none"type="text" id="vUser" value="${sessionScope.sessionON}"></a>
            <input style="display:none"type="text" id="CodvUser">
            <input style="display: none"type="text" value="${sessionScope.vSucursal}" id="Codsucur">
            <input style="display:none "type="text" value="${sessionScope.vDeposito}" id="Coddepo">
        </ul>
    </nav>

</header>


