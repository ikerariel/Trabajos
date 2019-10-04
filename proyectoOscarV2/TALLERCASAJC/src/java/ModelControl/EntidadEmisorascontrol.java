/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.EntidadEmisorasdao;
import ModelDAOIMPL.EntidadEmisorasdaoimpl;
import ModelDTO.EntidadEmisorasdto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "EntidadEmisorascontrol", urlPatterns = {"/EntidadEmisorascontrol"})
public class EntidadEmisorascontrol extends HttpServlet {

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
        
        EntidadEmisorasdao ENTdao=new EntidadEmisorasdaoimpl();
        
        EntidadEmisorasdto ENTdto = new EntidadEmisorasdto();
        
        switch (opcion) {
            case 1:
                ENTdto.setId_entiemi(Integer.parseInt(request.getParameter("identiemi")));
                ENTdto.setEntiemi_descripcion(request.getParameter("descrientiemi"));
                if (ENTdao.insertarCiudades(ENTdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                ENTdto.setId_entiemi(Integer.parseInt(request.getParameter("identiemi")));
                ENTdto.setEntiemi_descripcion(request.getParameter("descrientiemi"));
                if (ENTdao.modificarEntidadEmisoras(ENTdto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (ENTdao.eliminarEntidadEmisoras(Integer.parseInt(request.getParameter("identiemi")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(ENTdao.getmostrarEntidadEmisoras());
                break;
                
            case 5:
                System.out.println("codigo " + ENTdao.getUltimoCodigoEntidadEmisoras());
                if (ENTdao.getUltimoCodigoEntidadEmisoras()> 0) {
                    out.println(ENTdao.getUltimoCodigoEntidadEmisoras());
                }
                break;
                
            case 6:
                
                if (ENTdao.getmostrarEntidadEmisorasFiltro(Integer.parseInt(request.getParameter("identiemi"))) != null) {
                    out.println(ENTdao.getmostrarEntidadEmisorasFiltro(Integer.parseInt(request.getParameter("identiemi"))));
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
