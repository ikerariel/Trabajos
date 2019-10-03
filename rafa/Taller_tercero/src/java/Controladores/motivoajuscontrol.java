/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.motivoajudao;
import DAOIMPLE.motivoajudaoimple;
import DTO.motivoajudto;
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
@WebServlet(name = "motivoajuscontrol", urlPatterns = {"/motivoajuscontrol"})
public class motivoajuscontrol extends HttpServlet {

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
        
        motivoajudao motiajDAO=new motivoajudaoimple();


//
//        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        motivoajudto motiajDTO = new motivoajudto();

 
        switch (opcion) {
            case 1:
                motiajDTO.setIdmot_ajus(Integer.parseInt(request.getParameter("cod")));
                motiajDTO.setAjustmotiv_descri(request.getParameter("nomb"));
                if (motiajDAO.insertar(motiajDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                motiajDTO.setIdmot_ajus(Integer.parseInt(request.getParameter("cod")));
                motiajDTO.setAjustmotiv_descri(request.getParameter("nomb"));
                if (motiajDAO.modificar(motiajDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (motiajDAO.eliminar(Integer.parseInt(request.getParameter("cod")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(motiajDAO.getmotiaju());
                break;
                
            case 5:
                System.out.println("codigo " + motiajDAO.getUltimoCodigo());
                if (motiajDAO.getUltimoCodigo() > 0) {
                    out.println(motiajDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (motiajDAO.getmotiajuFiltro(Integer.parseInt(request.getParameter("cod"))) != null) {
                    out.println(motiajDAO.getmotiajuFiltro(Integer.parseInt(request.getParameter("cod"))));
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
            Logger.getLogger(motivoajuscontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(motivoajuscontrol.class.getName()).log(Level.SEVERE, null, ex);
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
