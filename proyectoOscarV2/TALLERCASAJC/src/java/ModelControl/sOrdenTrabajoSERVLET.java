/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.sOrdenTrabajoDAO;
import ModelDAOIMPL.sOrdenTrabajoDAOIMPLE;
import ModelDTO.sOrdenTrabajoDTO;
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
public class sOrdenTrabajoSERVLET extends HttpServlet {

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

        sOrdenTrabajoDAO otDAO = new sOrdenTrabajoDAOIMPLE();
        sOrdenTrabajoDTO otDTO = new sOrdenTrabajoDTO();

        switch (opcion) {
            case 1:
                out.println(otDAO.getdetallePresupuesto(Integer.parseInt(request.getParameter("otNroPresupuesto"))));
                break;

            case 2:
                Integer otCod = Integer.parseInt(request.getParameter("otCaso"));
                if (otCod == 1) {
                    otDTO.setDiagnostico(request.getParameter("otDiagnostico"));
                    otDTO.setOrden(request.getParameter("otOrden"));
                    otDTO.setId_usuario(Integer.parseInt(request.getParameter("otUsuario")));
                    otDTO.setId_presuserv(Integer.parseInt(request.getParameter("otPresupuesto")));
                    otDTO.setFechaentrega(request.getParameter("otfechaentrega"));
                } else if (otCod == 2) {
                    otDTO.setDiagnostico(request.getParameter("otDiagnostico"));
                    otDTO.setOrden(request.getParameter("otOrden"));
                    otDTO.setId_usuario(Integer.parseInt(request.getParameter("otUsuario")));
                    otDTO.setId_presuserv(Integer.parseInt(request.getParameter("otPresupuesto")));
                    otDTO.setFechaentrega(request.getParameter("otfechaentrega"));
                    otDTO.setId_ordenttabajo(Integer.parseInt(request.getParameter("otCodOT")));
                }

                if (otDAO.insetarOrdentrabajo(otDTO, otCod)) {
                    out.println("Exitoso");
                }

            case 3:
                out.println(otDAO.getdetalleOT(Integer.parseInt(request.getParameter("otCODIGO"))));
                break;
            case 4:
                out.println(otDAO.getOT());
                break;

            case 5:
                otDTO.setId_estado(Integer.parseInt(request.getParameter("otCodEstado")));
                otDTO.setId_ordenttabajo(Integer.parseInt(request.getParameter("otCodOTrabajo")));

                if (otDAO.updateEstadoOT(otDTO)) {
                     out.println("Exitoso");
                }
                break;

        }

//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet sOrdenTrabajoSERVLET</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet sOrdenTrabajoSERVLET at " + request.getContextPath() + "</h1>");
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
