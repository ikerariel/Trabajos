<%@page import="genericos.listarReportes"%>
<%@page import="genericos.genericos"%>
<%@page import="java.util.HashMap"%>
<%@page import="genericos.modelopedido"%>
<%
    String separador = "/@/";
    String pedido_codigo = request.getParameter("pedido_codigo");
    String pk_funcionario = request.getParameter("pk_funcionario");
    String nro_pedido = request.getParameter("nro_pedido");
    String fechaPedido = request.getParameter("pedidofecha");
    String codigoProducto = request.getParameter("codigoProducto");
    String cantidaProducto = request.getParameter("cantidaProducto");
    String filtro_reporte = request.getParameter("filtro_reporte");
    String fk_Sucu = request.getParameter("fk_Sucu");
    String operacion = request.getParameter("bandera");
    int operaban = Integer.valueOf(operacion);
    modelopedido modpedido = new modelopedido();
    switch (operaban) {
        case 1:
            if (modpedido.generarAutonumerico() > 0) {
                out.println(modpedido.generarAutonumerico());
            }
            break;

        case 2:
            genericos.esRecuperado = false;
            System.out.print("Llega al caso AltaCab");
            modpedido.setPedid_codigo(Integer.parseInt(pedido_codigo.trim()));
            modpedido.setPedid_fecha(fechaPedido);
            modpedido.setFuncio_codigo(Integer.parseInt(pk_funcionario.trim()));
            modpedido.setNro_pedido(Integer.parseInt(nro_pedido.trim()));
            modpedido.setSucursal(Integer.parseInt(fk_Sucu.trim()));
            if (modpedido.altaCab() > 0) {
                out.println(separador);
                out.println("1");
            } else {
                out.println("0");
            }
            break;
        case 3:
            genericos.esRecuperado = false;
            System.out.print("Llega al detalle");
            System.out.print("pedido_codigo " + pedido_codigo);
            System.out.print("codigoProduct " + codigoProducto);
            System.out.print("cantidaProducto " + cantidaProducto);
            modpedido.setPedid_codigo(Integer.parseInt(pedido_codigo.trim()));
            modpedido.setProdu_codigo(Integer.parseInt(codigoProducto.trim()));
            modpedido.setCantidad(Integer.parseInt(cantidaProducto.trim()));

            if (modpedido.altaDeta() > 0) {
                out.println(separador);
                out.println("1");
            } else {
                out.println("0");
            }
            break;

        case 4:
            out.print(modpedido.listarProductosTabla());

            break;

        case 5:

            modpedido.setFuncio_codigo(Integer.parseInt(pk_funcionario.trim()));
            modpedido.recuperarDatosFuncionarios();
            out.println(modpedido.getFuncio_nombres());

            break;

        case 6:
            System.out.println("Llega al caso reporte");
            String pNombreReporte = "C:\\LP3\\LP3_busqueda_2\\Floreria\\web\\reportes\\ipedidocompra.jrxml";
            String pNombreExport = "C:\\LP3\\LP3_busqueda_2\\Floreria\\web\\reportes\\ipedidocompra.pdf";
            listarReportes.imprimir(pNombreReporte, pNombreExport, new HashMap());

            break;

        case 7:

            System.out.println("LLEGA AL CASO REPORTE FILTRADO");
            System.out.println("valor : " + Integer.parseInt(filtro_reporte.trim()));

            HashMap filtro = new HashMap();
            filtro.put("cliente", Integer.parseInt(filtro_reporte.trim()));
            String pNombreReportefiltro = "C:\\LP3\\LP3_busqueda_2\\Floreria\\web\\reportes\\ipedidocompra.jrxml";
            String pNombreExportfiltro = "C:\\LP3\\LP3_busqueda_2\\Floreria\\web\\reportes\\ipedidocompra.pdf";
            listarReportes.imprimir(pNombreReportefiltro, pNombreExportfiltro,filtro);

            break;

        case 8:
            out.println(modpedido.listarsucursal());
            break;

        case 9:
            System.out.println("llega a caso recuperardatopedido");
            modpedido.setPedid_codigo(Integer.parseInt(pedido_codigo.trim()));
            modpedido.recuperarDatosPedido();
            out.println(modpedido.getPedid_fecha());
            out.println(separador);
            out.println(modpedido.getFuncio_codigo());
            out.println(separador);
            out.println(modpedido.getFuncio_nombres());
            out.println(separador);
            out.println(modpedido.getNro_pedido());
            out.println(separador);
            out.println(modpedido.listarsucursalfiltro(modpedido.getSucursal()));
            break;

        case 10:
            System.out.println("llega a caso recuperardetalle");
            modpedido.setPedid_codigo(Integer.parseInt(pedido_codigo.trim()));
            modpedido.recuperarDatosDetalle();
            out.println(modpedido.getProdu_codigo());
            out.println(separador);
            out.println(modpedido.getProdu_descri());
            out.println(separador);
            out.println(modpedido.getCantidad());
            break;

        case 11:
            genericos.esRecuperado = false;
            System.out.print("Llega al caso ModificaCab");
            modpedido.setPedid_codigo(Integer.parseInt(pedido_codigo.trim()));
            modpedido.setPedid_fecha(fechaPedido);
            modpedido.setFuncio_codigo(Integer.parseInt(pk_funcionario.trim()));
            modpedido.setNro_pedido(Integer.parseInt(nro_pedido.trim()));
            modpedido.setSucursal(Integer.parseInt(fk_Sucu.trim()));
            if (modpedido.modificarCab() > 0) {
                System.out.println("pasa2");
                out.println(separador);
                out.println("1");
            } else {
                out.println("0");
            }
            modpedido.recuperarDatosPedido();

            break;

        case 12:
            genericos.esRecuperado = false;
            System.out.println("llega a modificaDet");
            modpedido.setPedid_codigo(Integer.parseInt(pedido_codigo.trim()));
            modpedido.setProdu_codigo(Integer.parseInt(codigoProducto.trim()));
            modpedido.setCantidad(Integer.parseInt(cantidaProducto.trim()));
            if (modpedido.modificarDeta() > 0) {
                System.out.println("pasa2");
                out.println(separador);
                out.println("1");
            } else {
                out.println("0");
            }
            modpedido.recuperarDatosPedido();
            break;
            
            
           case 13:
               genericos.esRecuperado = false;
            System.out.println("llega a eliminarCab");
            modpedido.setPedid_codigo(Integer.parseInt(pedido_codigo.trim()));
            if (modpedido.eliminarCab() > 0) {
                System.out.println("pasa2");
                out.println(separador);
                out.println("1");
            } else {
                out.println("0");
            }
            break;   
               
               
               case 14:
                   genericos.esRecuperado = false;
            System.out.println("llega a eliminarDet");
            modpedido.setPedid_codigo(Integer.parseInt(pedido_codigo.trim()));
             if (modpedido.eliminarDet() > 0) {
                System.out.println("pasa2");
                out.println(separador);
                out.println("1");
            } else {
                out.println("0");
            }
            break;
            
            
            
            
            
    }


%>
