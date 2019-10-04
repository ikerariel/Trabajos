/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.facturacompradao;
import DAOIMPLE.facturacompradaoimple;
import DTO.facturacompradto;
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
@WebServlet(name = "facturacompracontrol", urlPatterns = {"/facturacompracontrol"})
public class facturacompracontrol extends HttpServlet {

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

        facturacompradao compraDAO = new facturacompradaoimple();
        facturacompradto compraDTO = new facturacompradto();

        switch (opcion) {
            case 1:
                System.out.println("codigo" + compraDAO.getUltimoCodigoCompra());
                if (compraDAO.getUltimoCodigoCompra() > 0) {
                    out.println(compraDAO.getUltimoCodigoCompra());
                }
                break;
            case 2:
                out.println(compraDAO.ListarEstadoCompra2());
                break;
            case 3:
                out.println(compraDAO.ListarUsuarioCompra3());
                break;
            case 4:
                out.println(compraDAO.ListarProveedorCompras4());
                break;
            case 5:
                out.println(compraDAO.ListarOrdenCompra5());
                break;
            case 6:
                if (compraDAO.ListarDetalleOrdenC6(Integer.parseInt(request.getParameter("nroOrdenC"))) != null) {
                    out.println(compraDAO.ListarDetalleOrdenC6(Integer.parseInt(request.getParameter("nroOrdenC"))));
                    System.out.println(compraDAO.ListarDetalleOrdenC6(Integer.parseInt(request.getParameter("nroOrdenC"))));
                }
                break;
            case 7:
                out.println(compraDAO.ListarSucursalCompra7());
                break;
            case 8:
                out.println(compraDAO.ListarMercaderiaCompra8());
                break;
            case 9:
                Integer cValor = Integer.parseInt(request.getParameter("cValor"));
                if (cValor == 1) {
                    compraDTO.setComp_cantcuota(Integer.parseInt(request.getParameter("fcompracuota")));
                    compraDTO.setComp_monto(Integer.parseInt(request.getParameter("fcompramonto")));
                    compraDTO.setComp_nrofact(request.getParameter("fcompraNfactu"));
                    compraDTO.setComp_intervalo(request.getParameter("fcompraintervalo"));
                    compraDTO.setComp_fecha(request.getParameter("fcomprafecha"));
                    compraDTO.setId_prov(Integer.parseInt(request.getParameter("fcompraprovee")));
                    compraDTO.setIdusuario(Integer.parseInt(request.getParameter("fcomprausua")));
                    compraDTO.setIdestado(Integer.parseInt(request.getParameter("fcompraestado")));
                    compraDTO.setTipo_codigo(Integer.parseInt(request.getParameter("fcompratipo")));
                    compraDTO.setOrdenc_nro(Integer.parseInt(request.getParameter("fcompraordenc")));
                    compraDTO.setIddeposito(Integer.parseInt(request.getParameter("fdeposito")));
                } else if (cValor == 2) {
                    compraDTO.setComp_cantcuota(Integer.parseInt(request.getParameter("fcompracuota")));
                    compraDTO.setComp_monto(Integer.parseInt(request.getParameter("fcompramonto")));
                    compraDTO.setComp_nrofact(request.getParameter("fcompraNfactu"));
                    compraDTO.setComp_intervalo(request.getParameter("fcompraintervalo"));
                    compraDTO.setComp_fecha(request.getParameter("fcomprafecha"));
                    compraDTO.setId_prov(Integer.parseInt(request.getParameter("fcompraprovee")));
                    compraDTO.setIdusuario(Integer.parseInt(request.getParameter("fcomprausua")));
                    compraDTO.setTipo_codigo(Integer.parseInt(request.getParameter("fcompratipo")));
                    compraDTO.setOrdenc_nro(Integer.parseInt(request.getParameter("fcompraordenc")));
                    compraDTO.setIddeposito(Integer.parseInt(request.getParameter("fdeposito")));
                    compraDTO.setIdcompra(Integer.parseInt(request.getParameter("codCompra")));
                }

                if (compraDAO.insertarCabeceraCompra9(compraDTO, cValor)) {
                    out.println("Exitoso");
                }
                break;
            case 10:
                compraDTO.setIdcompraD(Integer.parseInt(request.getParameter("codigoFa")));
                compraDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaFa")));
                compraDTO.setDetfact_cantidad(Integer.parseInt(request.getParameter("cantiFa")));
                compraDTO.setDetfact_precio(Integer.parseInt(request.getParameter("precioFa")));
                if (compraDAO.insertarDetalleCompra10(compraDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 11:
                out.println(compraDAO.ListarFacturaCompra11());
                break;
            case 12:
                compraDTO.setIdestado(Integer.parseInt(request.getParameter("CambioEstadoC")));
                compraDTO.setIdcompra(Integer.parseInt(request.getParameter("FacturaCNro")));
                if (compraDAO.confirmarFacturaCompra12(compraDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 13:
                if (compraDAO.listarDetalleCompra13(Integer.parseInt(request.getParameter("nroFacturaC"))) != null) {
                    out.println(compraDAO.listarDetalleCompra13(Integer.parseInt(request.getParameter("nroFacturaC"))));
                    System.out.println(compraDAO.listarDetalleCompra13(Integer.parseInt(request.getParameter("nroFacturaC"))));
                }
                break;
            case 14:
                out.println(compraDAO.ListarTipoCompras14());
                break;

            case 15:
                compraDTO.setIdcompra(Integer.parseInt(request.getParameter("codFacCompra")));
                if (compraDAO.deletfacturaCompra(compraDTO)) {
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
