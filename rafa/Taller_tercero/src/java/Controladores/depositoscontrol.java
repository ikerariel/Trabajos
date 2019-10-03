/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.depositodao;
import DAOIMPLE.depositodaoimple;
import DTO.depositodto;
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
@WebServlet(name = "depositoscontrol", urlPatterns = {"/depositoscontrol"})
public class depositoscontrol extends HttpServlet {

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
        
        depositodao DeposDAO=new depositodaoimple();
//        //USO PATRON DTO      
        depositodto DeposDTO = new depositodto();
        
        switch (opcion) {
            case 1:
                
                DeposDTO.setIddeposito(Integer.parseInt(request.getParameter("cod_depos")));
                DeposDTO.setDep_descripcion(request.getParameter ("dep_descri"));
                if (DeposDAO.insertar(DeposDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                DeposDTO.setIddeposito(Integer.parseInt(request.getParameter("cod_depos")));
                DeposDTO.setDep_descripcion(request.getParameter ("dep_descri"));
                if (DeposDAO.modificar(DeposDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (DeposDAO.eliminar(Integer.parseInt(request.getParameter("cod_depos")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(DeposDAO.getDeposito());
                break;
                
            case 5:
                System.out.println("cod_deposito" + DeposDAO.getUltimoCodigo());
                if (DeposDAO.getUltimoCodigo() > 0) {
                    out.println(DeposDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (DeposDAO.getDepositoFiltro(Integer.parseInt(request.getParameter("cod_depos"))) != null) {
                    out.println(DeposDAO.getDepositoFiltro(Integer.parseInt(request.getParameter("cod_depos"))));
                }
                break;
            //case 8:
                //out.println(ciudDAO.listarMarcas());
               // break;
            //case 9:
               // out.println(ciudDAO.listarProcedencia());
                //break;
          
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
            Logger.getLogger(depositoscontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(depositoscontrol.class.getName()).log(Level.SEVERE, null, ex);
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
