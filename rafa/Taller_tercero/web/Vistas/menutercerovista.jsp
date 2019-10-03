<%-- 
    Document   : menutercero
    Created on : 12/12/2017, 09:50:05 PM
    Author     : naty
--%>

<header>
        <div class="menu_bar">
            <a href="#" class="bt-menu"><span></span>Menu</a>
        </div>
        <nav>
            <ul>
                <li><a href="iniciotercero.jsp"><span class="icon-home"></span>Inicio</a></li>
                <li class="submenu">
                    <a href="#"><span class="icon-users"></span>Ref.compras<span class="caret"></span></a>
                    <ul class="children">
                        <li><a href="depositovista.jsp">Depositos<span class="icon-hammer"></span></a></li>
                        <li><a href="estadovista.jsp">Estado<span class="icon-hammer"></span></a></li>
                        <li><a href="estantevista.jsp">Estante<span class="icon-hammer"></span></a></li>
                        <li><a href="sucursalvista.jsp">Sucursales<span class="icon-hammer"></span></a></li>
                        <li><a href="impuestovista.jsp">Impuesto<span class="icon-hammer"></span></a></li>
                        <li><a href="marcasvista.jsp">Marcas<span class="icon-hammer"></span></a></li>
                        <li><a href="mercaderiavista.jsp">Mercaderia<span class="icon-hammer"></span></a></li>
                        <li><a href="motivoajuvista.jsp">Motivo Ajuste<span class="icon-hammer"></span></a></li>
                        <li><a href="procedenciasvista.jsp">Procedencias<span class="icon-hammer"></span></a></li>
                        <li><a href="proveedoresvista.jsp">Proveedores<span class="icon-hammer"></span></a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="#"><span class="icon-users"></span>Ref.Ventas<span class="caret"></span></a>
                    <ul class="children">
                        <li><a href="cajavista.jsp">Cajas<span class="icon-hammer"></span></a></li>
                        <li><a href="clientesvista.jsp">Clientes<span class="icon-hammer"></span></a></li>
                        <li><a href="cargosvista.jsp">Cargos<span class="icon-hammer"></span></a></li>
                        <li><a href="funcionariovista.jsp">Funcionario<span class="icon-hammer"></span></a></li>
                        <li><a href="ciudadesvista.jsp">Ciudad<span class="icon-hammer"></span></a></li>
                        <li><a href="perfilesvista.jsp">Perfiles<span class="icon-hammer"></span></a></li>
                        <li><a href="unimedivista.jsp">Uni. Medida<span class="icon-hammer"></span></a></li>
                        <li><a href="usuariosvista.jsp">Usuarios<span class="icon-hammer"></span></a></li>
                        <li><a href="buscadorvista.jsp"><span class="icon-search"></span>BUSCADOR</a></li>
                    </ul>
                </li>
                
                <li class="submenu">
                    <a href="#"><span class="icon-rocket"></span>Facturacion<span class="caret"></span></a>
                    <ul class="children">
                        <li><a href="ProcedenciaVista.jsp">venta<span class="icon-earth"></span></a></li>
                        <li><a href="#">Impuestos<span class="icon-pushpin"></span></a></li>

                    </ul>
                </li>
                <li class="submenu">
                    <a href="#"><span class="icon-cart"></span>compra<span class="caret"></span></a>
                    <ul class="children">
                        <li class="submenu">
                            <li><a href="pedidocompravista.jsp">Pedido Compra<span class="icon-file-empty"></span></a></li>
                        </li>
                        <li><a href="#">Tipo Pago<span class="icon-price-tags"></span></a></li>
                    </ul>
                </li>
                <li class="submenu">
                    <a href="#"><span class="icon-stats-dots"></span>Reportes<span class="caret"></span></a>
                    <ul class="children">
                        <li><a href="#">Basicos<span class="icon-stats-bars"></span></a></li>
                        <li><a href="#">Avanzados<span class="icon-stats-bars2"></span></a></li>
                    </ul>
                </li>
                <li><a href="out.jsp"><span class="icon-switch"></span>Salir como ${sessionScope.sessionON}</a></li>
            </ul>
        </nav>
    </header>
