/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.cajadao;
import DAOIMPLE.cajadaoimple;
import DTO.cajadto;
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
@WebServlet(name = "cajacontrol", urlPatterns = {"/cajacontrol"})
public class cajacontrol extends HttpServlet {

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
        //System.out.println("Opcion " + opcion);
        cajadao cajaDAO=new cajadaoimple();


//
//        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        cajadto cajaDTO = new cajadto();

 
        switch (opcion) {
            case 1:
                cajaDTO.setIdcaja(Integer.parseInt(request.getParameter("cod")));
                cajaDTO.setCaja_descripcion(request.getParameter("nomb"));
                if (cajaDAO.insertar(cajaDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                cajaDTO.setIdcaja(Integer.parseInt(request.getParameter("cod")));
                cajaDTO.setCaja_descripcion(request.getParameter("nomb"));
                if (cajaDAO.modificar(cajaDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (cajaDAO.eliminar(Integer.parseInt(request.getParameter("cod")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(cajaDAO.getcaja());
                break;
                
            case 5:
                System.out.println("codigo " + cajaDAO.getUltimoCodigo());
                if (cajaDAO.getUltimoCodigo() > 0) {
                    out.println(cajaDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (cajaDAO.getcajaFiltro(Integer.parseInt(request.getParameter("cod"))) != null) {
                    out.println(cajaDAO.getcajaFiltro(Integer.parseInt(request.getParameter("cod"))));
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
            Logger.getLogger(cajacontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(cajacontrol.class.getName()).log(Level.SEVERE, null, ex);
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
