/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import Genericos.Conexion;
import ModelDAO.sPresupuestoServiciosDAO;
import ModelDAOIMPL.sPresupuestoServiciosDAOIMPLE;
import ModelDTO.sPresupuestoServiciosDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class sPresupuestoServiciosSERVLET extends HttpServlet {

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

        sPresupuestoServiciosDAO psDAO = new sPresupuestoServiciosDAOIMPLE();
        sPresupuestoServiciosDTO psDTO = new sPresupuestoServiciosDTO();

        switch (opcion) {
            case 1:
                Integer psCod = Integer.parseInt(request.getParameter("psValor"));
                if (psCod == 1) {
                    psDTO.setId_diagnostico(Integer.parseInt(request.getParameter("spNroDiagnostico")));
                    psDTO.setId_usuario(Integer.parseInt(request.getParameter("spUsuario")));
                    psDTO.setId_condicionpago(Integer.parseInt(request.getParameter("spNcondPago")));
                    psDTO.setDiganostico(request.getParameter("spObservacion"));
                } else if (psCod == 2) {
                    psDTO.setId_diagnostico(Integer.parseInt(request.getParameter("spNroDiagnostico")));
                    psDTO.setId_usuario(Integer.parseInt(request.getParameter("spUsuario")));
                    psDTO.setId_condicionpago(Integer.parseInt(request.getParameter("spNcondPago")));
                    psDTO.setDiganostico(request.getParameter("spObservacion"));
                    psDTO.setId_presuserv(Integer.parseInt(request.getParameter("spNropresupuesto")));
                }

                if (psDAO.insertarpresupuestoservicios(psDTO, psCod)) {
                    out.println("Exitoso");
                }

                break;

            case 2:
                Integer psVr = Integer.parseInt(request.getParameter("psDetalle"));
                if (psVr == 1) {
                    psDTO.setId_articulo(Integer.parseInt(request.getParameter("psCodartic")));
                    psDTO.setCantidad(Integer.parseInt(request.getParameter("psCant")));
                    psDTO.setPrecio(Integer.parseInt(request.getParameter("psPrecio")));

                } else if (psVr == 2) {
                    psDTO.setId_presuserv(Integer.parseInt(request.getParameter("spCodigoPS")));
                    psDTO.setId_articulo(Integer.parseInt(request.getParameter("psCodartic")));
                    psDTO.setCantidad(Integer.parseInt(request.getParameter("psCant")));
                    psDTO.setPrecio(Integer.parseInt(request.getParameter("psPrecio")));
                }

                if (psDAO.insertarDetallePServicios(psDTO, psVr)) {
                    out.println("Exitoso");
                }
                break;
            case 3:
                psDTO.setId_presuserv(Integer.parseInt(request.getParameter("spCodPservicio")));
                if (psDAO.deleteDetallePServicio(psDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                out.println(psDAO.getPServicio());
                break;
            case 5:
                psDTO.setId_estado(Integer.parseInt(request.getParameter("psEstado")));
                psDTO.setId_presuserv(Integer.parseInt(request.getParameter("psNroPs")));
                if (psDAO.actualizarEstadoPServicio(psDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 6:
                out.println(psDAO.getdetallePServicio(Integer.parseInt(request.getParameter("psNroDiagnostico"))));
                break;
            case 7:
                out.println(psDAO.getdetallesPServicios(Integer.parseInt(request.getParameter("psCodPServicio"))));
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
