/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.estantesdao;
import DAOIMPLE.estantedaoimple;
import DTO.estantesdto;
import Genericos.mierror;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rafel
 */
@WebServlet(name = "estantecontrol", urlPatterns = {"/estantecontrol"})
public class estantecontrol extends HttpServlet {

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
            throws ServletException, IOException, mierror {
        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));
        
        estantesdao estanDAO=new estantedaoimple();


//
//        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        estantesdto estanDTO = new estantesdto();

 
        switch (opcion) {
            case 1:
                estanDTO.setIdestante(Integer.parseInt(request.getParameter("cod")));
                estanDTO.setEstan_descri(request.getParameter("nomb"));
                if (estanDAO.insertar(estanDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                estanDTO.setIdestante(Integer.parseInt(request.getParameter("cod")));
                estanDTO.setEstan_descri(request.getParameter("nomb"));
                if (estanDAO.modificar(estanDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (estanDAO.eliminar(Integer.parseInt(request.getParameter("cod")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(estanDAO.getEstante());
                break;
                
            case 5:
                System.out.println("codigo " + estanDAO.getUltimoCodigo());
                if (estanDAO.getUltimoCodigo() > 0) {
                    out.println(estanDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (estanDAO.getEstanteFiltro(Integer.parseInt(request.getParameter("cod"))) != null) {
                    out.println(estanDAO.getEstanteFiltro(Integer.parseInt(request.getParameter("cod"))));
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
        try {
            processRequest(request, response);
        } catch (mierror ex) {
            Logger.getLogger(estantecontrol.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (mierror ex) {
            Logger.getLogger(estantecontrol.class.getName()).log(Level.SEVERE, null, ex);
        }
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
