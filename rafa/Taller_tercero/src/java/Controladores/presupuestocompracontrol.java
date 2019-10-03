/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.presupuestocompradao;
import DAOIMPLE.presupuestocompradaoimple;
import DTO.presupuestocompradto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rafel
 */
@WebServlet(name = "presupuestocompracontrol", urlPatterns = {"/presupuestocompracontrol"})
public class presupuestocompracontrol extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));

        presupuestocompradao compraDAO = new presupuestocompradaoimple();
        presupuestocompradto compraDTO = new presupuestocompradto();

        switch (opcion) {
            case 1:
                System.out.println("codigo" + compraDAO.getUltimoCodigoPresuCompra());
                if (compraDAO.getUltimoCodigoPresuCompra() > 0) {
                    out.println(compraDAO.getUltimoCodigoPresuCompra());
                }
                break;
            case 2:
                out.println(compraDAO.ListarEstadoPresuCompra2());
                break;
            case 3:
                out.println(compraDAO.ListarUsuarioPresuCompra3());
                break;
            case 4:
                out.println(compraDAO.ListarProveedorPresuCompras4());
                break;
            case 5:
                out.println(compraDAO.ListarPedidoPresuCompra5());
                break;
            case 6:
                if (compraDAO.ListarDetallePedidoPresuC6(Integer.parseInt(request.getParameter("nroPedidoPresuC"))) != null) {
                    out.println(compraDAO.ListarDetallePedidoPresuC6(Integer.parseInt(request.getParameter("nroPedidoPresuC"))));
                    System.out.println(compraDAO.ListarDetallePedidoPresuC6(Integer.parseInt(request.getParameter("nroPedidoPresuC"))));
                }
                break;
            case 7:
                out.println(compraDAO.ListarSucursalPresuCompra7());
                break;
            case 8:
                out.println(compraDAO.ListarMercaderiaPresuCompra8());
                break;
            case 9:
                Integer pcValor = Integer.parseInt(request.getParameter("pcValor"));
                if (pcValor == 1) {
                    compraDTO.setPresup_fecha(request.getParameter("Presucomprafecha"));
                    compraDTO.setPresup_cantcuota(Integer.parseInt(request.getParameter("Presucompracuota")));
                    compraDTO.setPresup_monto(Integer.parseInt(request.getParameter("Presucompramonto")));
                    compraDTO.setPresup_intervalo(request.getParameter("Presucompraintervalo"));
                    compraDTO.setPcomp_nro(Integer.parseInt(request.getParameter("PresuPedidoCompra")));
                    compraDTO.setId_prov(Integer.parseInt(request.getParameter("Presucompraprovee")));
                    compraDTO.setIdusuario(Integer.parseInt(request.getParameter("Presucomprausua")));
                    compraDTO.setIdestado(Integer.parseInt(request.getParameter("Presucompraestado")));
                    compraDTO.setTipo_codigo(Integer.parseInt(request.getParameter("Presucompratipo")));
                    compraDTO.setIddeposito(Integer.parseInt(request.getParameter("coddeposito")));
                } else if (pcValor == 2) {
                    compraDTO.setPresup_fecha(request.getParameter("Presucomprafecha"));
                    compraDTO.setPresup_cantcuota(Integer.parseInt(request.getParameter("Presucompracuota")));
                    compraDTO.setPresup_monto(Integer.parseInt(request.getParameter("Presucompramonto")));
                    compraDTO.setPresup_intervalo(request.getParameter("Presucompraintervalo"));
                    compraDTO.setPcomp_nro(Integer.parseInt(request.getParameter("PresuPedidoCompra")));
                    compraDTO.setId_prov(Integer.parseInt(request.getParameter("Presucompraprovee")));
                    compraDTO.setIdusuario(Integer.parseInt(request.getParameter("Presucomprausua")));
                    compraDTO.setTipo_codigo(Integer.parseInt(request.getParameter("Presucompratipo")));
                    compraDTO.setIddeposito(Integer.parseInt(request.getParameter("coddeposito")));
                    compraDTO.setIdpresupuestocomp(Integer.parseInt(request.getParameter("codPresupuesto")));
                }

                if (compraDAO.insertarCabeceraPresuCompra9(compraDTO, pcValor)) {
                    out.println("Exitoso");
                }
                break;
            case 10:
                compraDTO.setIdpresupuestocompD(Integer.parseInt(request.getParameter("codigoD")));
                compraDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaD")));
                compraDTO.setDetprescomp_cantidad(Integer.parseInt(request.getParameter("cantiD")));
                compraDTO.setDetprescomp_precio(Integer.parseInt(request.getParameter("precioD")));
                if (compraDAO.insertarDetallePresuCompra10(compraDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 11:
                out.println(compraDAO.ListarPresuCompra11());
                break;
            case 12:
                compraDTO.setIdestado(Integer.parseInt(request.getParameter("CambioEstadoPresup")));
                compraDTO.setIdpresupuestocomp(Integer.parseInt(request.getParameter("PresupuestoCNro")));
                if (compraDAO.confirmarPresuCompra12(compraDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 13:
                if (compraDAO.listarDetallePresuCompra13(Integer.parseInt(request.getParameter("nroPresuCompra"))) != null) {
                    out.println(compraDAO.listarDetallePresuCompra13(Integer.parseInt(request.getParameter("nroPresuCompra"))));
                    System.out.println(compraDAO.listarDetallePresuCompra13(Integer.parseInt(request.getParameter("nroPresuCompra"))));
                }
                break;
            case 14:
                out.println(compraDAO.ListarTipoPresuCompras14());
                break;
            case 15:
                compraDTO.setIdpresupuestocomp(Integer.parseInt(request.getParameter("codPresupcompra")));
                if (compraDAO.deletepresucompra(compraDTO)) {
                    out.println("Exitoso");
                }
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
