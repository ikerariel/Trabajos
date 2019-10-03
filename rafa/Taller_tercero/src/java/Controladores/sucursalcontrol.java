/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.sucursaldao;
import DAOIMPLE.sucursaldaoimple;
import DTO.sucursaldto;
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
//@WebServlet(name = "productoscontrol", urlPatterns = {"/productoscontrol"})
@WebServlet(name = "sucursalcontrol", urlPatterns = {"/sucursalcontrol"})
public class sucursalcontrol extends HttpServlet {

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
           
        sucursaldao sucursalDA0 = new sucursaldaoimple();
        
        sucursaldto sucursalDTO = new sucursaldto();
        
        switch (opcion) {
            case 1:
                
                sucursalDTO.setIdsucursal(Integer.parseInt(request.getParameter("cod_suc")));
                sucursalDTO.setSuc_descripcion(request.getParameter ("suc_desc"));
                sucursalDTO.setIdciudad(Integer.parseInt(request.getParameter("cod_ciu")));
                sucursalDTO.setIddeposito(Integer.parseInt(request.getParameter("cod_depo")));
                if (sucursalDA0.insertar(sucursalDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                sucursalDTO.setIdsucursal(Integer.parseInt(request.getParameter("cod_suc")));
                sucursalDTO.setSuc_descripcion(request.getParameter ("suc_desc"));
                sucursalDTO.setIdciudad(Integer.parseInt(request.getParameter("cod_ciu")));
                sucursalDTO.setIddeposito(Integer.parseInt(request.getParameter("cod_depo")));
                if (sucursalDA0.modificar(sucursalDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (sucursalDA0.eliminar(Integer.parseInt(request.getParameter("cod_suc")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(sucursalDA0.getsucur());
                break;
                
            case 5:
                System.out.println("codi_sucursal" + sucursalDA0.getUltimoCodigo());
                if (sucursalDA0.getUltimoCodigo() > 0) {
                    out.println(sucursalDA0.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (sucursalDA0.getsucurFiltro(Integer.parseInt(request.getParameter("cod_suc"))) != null) {
                    out.println(sucursalDA0.getsucurFiltro(Integer.parseInt(request.getParameter("cod_suc"))));
                }
                break;
                
            case 7:
                out.println(sucursalDA0.listarciudad());
                break;
            case 8:
                out.println(sucursalDA0.listardeposito());
                break;
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
            Logger.getLogger(sucursalcontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(sucursalcontrol.class.getName()).log(Level.SEVERE, null, ex);
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
