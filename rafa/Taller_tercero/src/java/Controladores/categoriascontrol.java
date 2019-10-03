/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.categoriasdao;
import DAOIMPLE.categoriasdaoimple;
import DTO.categoriasdto;
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
@WebServlet (name = "categoriascontrol", urlPatterns = {"/categoriascontrol"})
public class categoriascontrol extends HttpServlet {

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
        
        categoriasdao categoriaDAO = new categoriasdaoimple();

//        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        categoriasdto categoriaDTO = new categoriasdto();

 
        switch (opcion) {
            case 1:
                categoriaDTO.setIdcategoria(Integer.parseInt(request.getParameter("codcate")));
                categoriaDTO.setCate_descri(request.getParameter("nombcate"));
                if (categoriaDAO.insertar(categoriaDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                categoriaDTO.setIdcategoria(Integer.parseInt(request.getParameter("codcate")));
                categoriaDTO.setCate_descri(request.getParameter("nombcate"));
                if (categoriaDAO.modificar(categoriaDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (categoriaDAO.eliminar(Integer.parseInt(request.getParameter("codcate")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(categoriaDAO.getcategoria());
                break;
                
            case 5:
                System.out.println("codigo " + categoriaDAO.getUltimoCodigo());
                if (categoriaDAO.getUltimoCodigo() > 0) {
                    out.println(categoriaDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (categoriaDAO.getcategoriaFiltro(Integer.parseInt(request.getParameter("codcate"))) != null) {
                    out.println(categoriaDAO.getcategoriaFiltro(Integer.parseInt(request.getParameter("codcate"))));
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
            Logger.getLogger(categoriascontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(categoriascontrol.class.getName()).log(Level.SEVERE, null, ex);
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
