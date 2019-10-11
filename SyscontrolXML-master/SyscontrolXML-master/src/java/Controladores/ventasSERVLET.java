/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.ventasDAO;
import DAOIMPL.ventasDAOIMPLE;
import DTO.ventasDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class ventasSERVLET extends HttpServlet {

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

        ventasDAO vDAO = new ventasDAOIMPLE();
        ventasDTO vDTO = new ventasDTO();

        switch (opcion) {
            case 1:
                Integer vVenta = Integer.parseInt(request.getParameter("vOpcion"));
                if (vVenta == 1) {
                    vDTO.setNumero(Integer.parseInt(request.getParameter("vNrotimbrado")));
                    vDTO.setFecha_vigencia_inicio(request.getParameter("vFechaVinicio"));
                    vDTO.setFecha_vigencia_final(request.getParameter("vFechaVfin"));
                    vDTO.setFactura_establesimiento(request.getParameter("vEstablecimiento"));
                    vDTO.setFactura_caja(request.getParameter("vCaja"));
                    vDTO.setFactura_desde(request.getParameter("vFacturadesde"));
                    vDTO.setFactura_hasta(request.getParameter("vFacturahasta"));
                    vDTO.setIdusuario(Integer.parseInt(request.getParameter("vCodUser")));
                    vDTO.setIdtipodocumento(Integer.parseInt(request.getParameter("vCodTipodocumento")));
                    vDTO.setIdsucursal(Integer.parseInt(request.getParameter("vCodSucursal")));
                }
                if (vDAO.insertartimbrado(vDTO, vVenta)) {
                    out.println("Exitoso");
                }
                break;

            case 2:
                out.println(vDAO.getTimbrados());
                break;

            case 3:
                vDTO.setNumerodocumento(request.getParameter("vNroDoc"));
                vDTO.setSecuencia(Integer.parseInt(request.getParameter("vSecuencia")));

                if (vDAO.insertarFacturas(vDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                out.println(vDAO.getCajeros());
                break;
            case 5:
                out.println(vDAO.getTipoDoc());
                break;
            case 6:
                out.println(vDAO.getFacturasPorTimbrados(Integer.parseInt(request.getParameter("vTipoDoc"))));
                break;
            case 7:
                out.println(vDAO.getAperCierreCajaVentas());
                break;
            case 8:
                out.println(vDAO.getCajas());
                break;
            case 9:

                vDTO.setMonto_apertura(Integer.parseInt(request.getParameter("aperMonto")));
                vDTO.setIdcaja(Integer.parseInt(request.getParameter("aperCaja")));
                vDTO.setIdcajero(Integer.parseInt(request.getParameter("aperCajero")));
                vDTO.setIdsucursal(Integer.parseInt(request.getParameter("aperSucursal")));
                vDTO.setIdusuario(Integer.parseInt(request.getParameter("aperUsuario")));
                vDTO.setIdestado(Integer.parseInt(request.getParameter("aperestado")));
                vDTO.setIdtimbrado(Integer.parseInt(request.getParameter("aperTimbrado")));

                if (vDAO.insertarAperturaCierreVenta(vDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 10:
                vDTO.setIdaperturacierre(Integer.parseInt(request.getParameter("codApertura")));
                if (vDAO.cerrarCaja(vDTO)) {
                    out.println("Exitoso");
                }
                break;
                
        }

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ventasSERVLET</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ventasSERVLET at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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
