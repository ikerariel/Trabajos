/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.marcasdao;
import DAOIMPLE.marcasdaoimple;
import DTO.marcasdto;
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
@WebServlet (name = "marcascontrol", urlPatterns = {"/marcascontrol"})
public class marcascontrol extends HttpServlet {

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
        
        marcasdao marcaDAO=new marcasdaoimple();

//        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        marcasdto marcaDTO = new marcasdto();

 
        switch (opcion) {
            case 1:
                marcaDTO.setIdmarca(Integer.parseInt(request.getParameter("cod")));
                marcaDTO.setMarca_descri(request.getParameter("nomb"));
                if (marcaDAO.insertar(marcaDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                marcaDTO.setIdmarca(Integer.parseInt(request.getParameter("cod")));
                marcaDTO.setMarca_descri(request.getParameter("nomb"));
                if (marcaDAO.modificar(marcaDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (marcaDAO.eliminar(Integer.parseInt(request.getParameter("cod")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(marcaDAO.getmarca());
                break;
                
            case 5:
                System.out.println("codigo " + marcaDAO.getUltimoCodigo());
                if (marcaDAO.getUltimoCodigo() > 0) {
                    out.println(marcaDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (marcaDAO.getmarcaFiltro(Integer.parseInt(request.getParameter("cod"))) != null) {
                    out.println(marcaDAO.getmarcaFiltro(Integer.parseInt(request.getParameter("cod"))));
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
            Logger.getLogger(marcascontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(marcascontrol.class.getName()).log(Level.SEVERE, null, ex);
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
