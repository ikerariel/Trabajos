/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.perfilesdao;
import DAOIMPLE.perfilesdaoimple;
import DTO.perfilesdto;
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
 * @author naty
 */
//@WebServlet(name = "cajacontrol", urlPatterns = {"/cajacontrol"})
@WebServlet (name = "perfilescontrol", urlPatterns = {"/perfilescontrol"})
public class perfilescontrol extends HttpServlet {

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
            throws ServletException, IOException, mierror{
        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));
        
        perfilesdao perfilDAO=new perfilesdaoimple();

//        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        perfilesdto perfilDTO = new perfilesdto();

 
        switch (opcion) {
            case 1:
                perfilDTO.setIdperfil(Integer.parseInt(request.getParameter("cod")));
                perfilDTO.setPer_descripcion(request.getParameter("nomb"));
                if (perfilDAO.insertar(perfilDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                perfilDTO.setIdperfil(Integer.parseInt(request.getParameter("cod")));
                perfilDTO.setPer_descripcion(request.getParameter("nomb"));
                if (perfilDAO.modificar(perfilDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (perfilDAO.eliminar(Integer.parseInt(request.getParameter("cod")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(perfilDAO.getperfil());
                break;
                
            case 5:
                System.out.println("codigo " + perfilDAO.getUltimoCodigo());
                if (perfilDAO.getUltimoCodigo() > 0) {
                    out.println(perfilDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (perfilDAO.getperfilFiltro(Integer.parseInt(request.getParameter("cod"))) != null) {
                    out.println(perfilDAO.getperfilFiltro(Integer.parseInt(request.getParameter("cod"))));
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
            Logger.getLogger(perfilescontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(perfilescontrol.class.getName()).log(Level.SEVERE, null, ex);
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
