/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.accesoDAO;
import DAO.ordencompradao;
import DAOIMPLE.accesoDATOIMPL;
import DAOIMPLE.ordencompradaoimple;
import DTO.accesoDTO;
import DTO.ordencompradto;
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
@WebServlet(name = "ordencompracontrol", urlPatterns = {"/ordencompracontrol"})
public class ordencompracontrol extends HttpServlet {

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

        ordencompradao ordenCDAO = new ordencompradaoimple();
        ordencompradto ordenCDTO = new ordencompradto();

        accesoDAO accDAO = new accesoDATOIMPL();
        accesoDTO accDTO = new accesoDTO();

        switch (opcion) {
            case 1:
                System.out.println("codigo" + ordenCDAO.getUltimoCodigo());
                if (ordenCDAO.getUltimoCodigo() > 0) {
                    out.println(ordenCDAO.getUltimoCodigo());
                }
                break;
            case 2:
                Integer oValor = Integer.parseInt(request.getParameter("oValor"));
                if (oValor == 1) {
                    ordenCDTO.setOrdenc_fecha(request.getParameter("fechaC"));
                    ordenCDTO.setId_prov(Integer.parseInt(request.getParameter("proveeC")));
                    ordenCDTO.setIdusuario(Integer.parseInt(request.getParameter("usuaC")));
                    ordenCDTO.setPcomp_nro(Integer.parseInt(request.getParameter("PcompC")));
                    ordenCDTO.setIdestado(Integer.parseInt(request.getParameter("estadoC")));
                } else if (oValor == 2) {
                    ordenCDTO.setOrdenc_fecha(request.getParameter("fechaC"));
                    ordenCDTO.setId_prov(Integer.parseInt(request.getParameter("proveeC")));
                    ordenCDTO.setIdusuario(Integer.parseInt(request.getParameter("usuaC")));
                    ordenCDTO.setPcomp_nro(Integer.parseInt(request.getParameter("PcompC")));
                    ordenCDTO.setOrdenc_nro(Integer.parseInt(request.getParameter("oCodOrden")));
                }

                if (ordenCDAO.insertarOrdenC(ordenCDTO, oValor)) {
                    out.println("Exitoso");
                }
                break;
            case 3:
                ordenCDTO.setOrdenc_nroD(Integer.parseInt(request.getParameter("codigoF")));
                ordenCDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaF")));
                ordenCDTO.setCant_orden(Integer.parseInt(request.getParameter("cantiF")));
                ordenCDTO.setPrecio_orden(Integer.parseInt(request.getParameter("precioF")));
                if (ordenCDAO.insertarDetalleOrdenC(ordenCDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                ordenCDTO.setOrdenc_nroD(Integer.parseInt(request.getParameter("codigoD")));
                ordenCDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaD")));
                ordenCDTO.setCant_orden(Integer.parseInt(request.getParameter("cantiD")));
                ordenCDTO.setPrecio_orden(Integer.parseInt(request.getParameter("precioD")));
                if (ordenCDAO.modificardetallesOrdenC(ordenCDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 5:
                if (ordenCDAO.getcabeseraFiltroOrdenC(Integer.parseInt(request.getParameter("codigoC"))) != null) {
                    out.println(ordenCDAO.getcabeseraFiltroOrdenC(Integer.parseInt(request.getParameter("codigoC"))));
                }
                break;
            case 6:
                if (ordenCDAO.getdetalleFiltroOrdenC(Integer.parseInt(request.getParameter("codigoD"))) != null) {
                    out.println(ordenCDAO.getdetalleFiltroOrdenC(Integer.parseInt(request.getParameter("codigoD"))));
                }
                break;
            case 7:
                out.println(ordenCDAO.listarusuario());
                break;
            case 8:
                out.println(ordenCDAO.listarestado());
                break;
            case 10:
                out.println(ordenCDAO.listarpedido());
                break;
            case 9:
                out.println(ordenCDAO.listarMercaderia());
                break;
            case 11:
                if (ordenCDAO.listarDetallePedido(Integer.parseInt(request.getParameter("nroPedido"))) != null) {
                    out.println(ordenCDAO.listarDetallePedido(Integer.parseInt(request.getParameter("nroPedido"))));
                    System.out.println(ordenCDAO.listarDetallePedido(Integer.parseInt(request.getParameter("nroPedido"))));
                }
                break;
            case 12:
                if (accDAO.getUser(request.getParameter("user")) != null) {
                    out.println(accDAO.getUser(request.getParameter("user")));
                }
                break;
            case 13:
                ordenCDTO.setIdestado(Integer.parseInt(request.getParameter("CambioEstado")));
                ordenCDTO.setOrdenc_nro(Integer.parseInt(request.getParameter("nroOrdenCo")));
                if (ordenCDAO.confirmarOrdenC(ordenCDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 14:
                out.println(ordenCDAO.listarproveedor());
                break;
            case 15:
                out.println(ordenCDAO.listarordenComp());
                break;
            case 16:
                if (ordenCDAO.listarDetalleOrdenC(Integer.parseInt(request.getParameter("nroOrdenB"))) != null) {
                    out.println(ordenCDAO.listarDetalleOrdenC(Integer.parseInt(request.getParameter("nroOrdenB"))));
                    System.out.println(ordenCDAO.listarDetalleOrdenC(Integer.parseInt(request.getParameter("nroOrdenB"))));
                }
                break;
            case 17:
                ordenCDTO.setMer_precio(Integer.parseInt(request.getParameter("Merprecio")));
                ordenCDTO.setMer_descripcion(request.getParameter("Merdescri"));
                ordenCDTO.setCodigogenerico(request.getParameter("Mergenerico"));
                if (ordenCDAO.insertarMercaderia(ordenCDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 18:
                System.out.println("codigoM" + ordenCDAO.getUltimoCodigoMercaderia());
                if (ordenCDAO.getUltimoCodigoMercaderia() > 0) {
                    out.println(ordenCDAO.getUltimoCodigoMercaderia());
                }
                break;
            case 19:
                ordenCDTO.setOrdenc_nro(Integer.parseInt(request.getParameter("oNroOrden")));
                if (ordenCDAO.deleteOrdencompra(ordenCDTO)) {
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
