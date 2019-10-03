/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;


import DAO.estadodao;
import DAOIMPLE.estadodaoimple;
import DTO.estadodto;
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
@WebServlet (name = "estadocontrol", urlPatterns = {"/estadocontrol"})
public class estadocontrol extends HttpServlet {

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
        
        estadodao estadoDAO=new estadodaoimple();

//        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        estadodto estadoDTO = new estadodto();

 
        switch (opcion) {
            case 1:
                estadoDTO.setIdestado(Integer.parseInt(request.getParameter("cod")));
                estadoDTO.setDescri_estado(request.getParameter("nomb"));
                if (estadoDAO.insertar(estadoDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                estadoDTO.setIdestado(Integer.parseInt(request.getParameter("cod")));
                estadoDTO.setDescri_estado(request.getParameter("nomb"));
                if (estadoDAO.modificar(estadoDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (estadoDAO.eliminar(Integer.parseInt(request.getParameter("cod")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(estadoDAO.getestado());
                break;
                
            case 5:
                System.out.println("codigo " + estadoDAO.getUltimoCodigo());
                if (estadoDAO.getUltimoCodigo() > 0) {
                    out.println(estadoDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (estadoDAO.getestadoFiltro(Integer.parseInt(request.getParameter("cod"))) != null) {
                    out.println(estadoDAO.getestadoFiltro(Integer.parseInt(request.getParameter("cod"))));
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
            Logger.getLogger(estadocontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(estadocontrol.class.getName()).log(Level.SEVERE, null, ex);
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
