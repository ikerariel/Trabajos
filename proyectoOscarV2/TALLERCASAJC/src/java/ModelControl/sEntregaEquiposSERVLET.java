/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.sEntregaEquiposDAO;
import ModelDAOIMPL.sEntregaEquiposDAOIMPL;
import ModelDTO.sEntregaEquiposDTO;
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
public class sEntregaEquiposSERVLET extends HttpServlet {

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

        sEntregaEquiposDAO eqDAO = new sEntregaEquiposDAOIMPL();
        sEntregaEquiposDTO eqDTO = new sEntregaEquiposDTO();

        switch (opcion) {
            case 1:
                Integer eqCod = Integer.parseInt(request.getParameter("eqValor"));
                if (eqCod == 1) {
                    eqDTO.setId_cliente(Integer.parseInt(request.getParameter("eqCliente")));
                    eqDTO.setId_usuario(Integer.parseInt(request.getParameter("eqUsuario")));
                    eqDTO.setObservacion(request.getParameter("eqObservacion"));
                    eqDTO.setId_ordenttabajo(Integer.parseInt(request.getParameter("eqOrdenTrabajo")));
                } else if (eqCod == 2) {
                    eqDTO.setId_cliente(Integer.parseInt(request.getParameter("eqCliente")));
                    eqDTO.setId_usuario(Integer.parseInt(request.getParameter("eqUsuario")));
                    eqDTO.setObservacion(request.getParameter("eqObservacion"));
                    eqDTO.setId_ordenttabajo(Integer.parseInt(request.getParameter("eqOrdenTrabajo")));
                    eqDTO.setId_entregaequipos(Integer.parseInt(request.getParameter("eqCodEntreq")));
                }

                if (eqDAO.insertarEntrgaEquipos(eqDTO, eqCod)) {
                    out.println("Exitoso");
                }
                break;
            case 2:
                Integer eqDetalle = Integer.parseInt(request.getParameter("eqDetalle"));
                if (eqDetalle == 1) {
                    eqDTO.setId_articulo(Integer.parseInt(request.getParameter("eqArticulo")));
                    eqDTO.setCatidad(Integer.parseInt(request.getParameter("eqCantidad")));
                } else if (eqDetalle == 2) {
                    eqDTO.setId_articulo(Integer.parseInt(request.getParameter("eqArticulo")));
                    eqDTO.setCatidad(Integer.parseInt(request.getParameter("eqCantidad")));
                    eqDTO.setId_entregaequipos(Integer.parseInt(request.getParameter("eqCodEntreqD")));
                }

                if (eqDAO.insertarDetalleEntrgaEquipos(eqDTO, eqDetalle)) {
                    out.println("Exitoso");
                }
                break;

            case 3:
                eqDTO.setId_entregaequipos(Integer.parseInt(request.getParameter("eqCodEq")));
                if (eqDAO.deleteEQ(eqDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 4:
                out.println(eqDAO.getOrdenTrabajoEQ());
                break;
            case 5:
                out.println(eqDAO.getdetalleOTeq(Integer.parseInt(request.getParameter("eqCodOT"))));
                break;
            case 6:
                out.println(eqDAO.getEQ());
                break;

            case 7:
                eqDTO.setId_estado(Integer.parseInt(request.getParameter("eqEstado")));
                eqDTO.setId_entregaequipos(Integer.parseInt(request.getParameter("eqCodEequipo")));

                if (eqDAO.updateEQ(eqDTO)) {
                    out.println("Exitoso");
                }
                break;
                 case 8:
                out.println(eqDAO.getdetalleEQ(Integer.parseInt(request.getParameter("eqCodEQ"))));
                break;
        }

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet sEntregaEquiposSERVLET</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet sEntregaEquiposSERVLET at " + request.getContextPath() + "</h1>");
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
