/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.procedenciasdao;
import DAOIMPLE.procedenciadaoimple;
import DTO.procedenciasdto;
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
@WebServlet (name = "procedenciascontrol", urlPatterns = {"/procedenciascontrol"})
public class procedenciascontrol extends HttpServlet {

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
        
        procedenciasdao procedenciaDAO=new procedenciadaoimple();

//        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        procedenciasdto procedenciaDTO = new procedenciasdto();

 
        switch (opcion) {
            case 1:
                procedenciaDTO.setIdprocedencia(Integer.parseInt(request.getParameter("cod")));
                procedenciaDTO.setProce_descri(request.getParameter("nomb"));
                if (procedenciaDAO.insertar(procedenciaDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                procedenciaDTO.setIdprocedencia(Integer.parseInt(request.getParameter("cod")));
                procedenciaDTO.setProce_descri(request.getParameter("nomb"));
                if (procedenciaDAO.modificar(procedenciaDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (procedenciaDAO.eliminar(Integer.parseInt(request.getParameter("cod")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(procedenciaDAO.getprocedencia());
                break;
                
            case 5:
                System.out.println("codigo " + procedenciaDAO.getUltimoCodigo());
                if (procedenciaDAO.getUltimoCodigo() > 0) {
                    out.println(procedenciaDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (procedenciaDAO.getprocedenciaFiltro(Integer.parseInt(request.getParameter("cod"))) != null) {
                    out.println(procedenciaDAO.getprocedenciaFiltro(Integer.parseInt(request.getParameter("cod"))));
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
            Logger.getLogger(procedenciascontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(procedenciascontrol.class.getName()).log(Level.SEVERE, null, ex);
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
