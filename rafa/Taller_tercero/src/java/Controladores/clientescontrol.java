/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.clientesdao;
import DAOIMPLE.clientesdaoimple;
import DTO.clientesdto;
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
//@WebServlet(name = "productoscontrol", urlPatterns = {"/productoscontrol"})
@WebServlet (name = "clientescontrol", urlPatterns = {"/clientescontrol"})
public class clientescontrol extends HttpServlet {

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
        
         clientesdao clienteDAO=new clientesdaoimple();
         
         //        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        clientesdto clienteDTO = new clientesdto();
        
        switch (opcion) {
            case 1:
                
                clienteDTO.setIdclientes(Integer.parseInt(request.getParameter("cod_cli")));
                clienteDTO.setCli_ruc(request.getParameter ("ruc_cli"));
                clienteDTO.setCli_razonsocial(request.getParameter("razon_cli"));
                clienteDTO.setCli_telefono(request.getParameter("tel_cli"));
                clienteDTO.setCli_direccion(request.getParameter("direc_cli"));
                clienteDTO.setCli_correo(request.getParameter("correo_cli"));
                clienteDTO.setIdciudad(Integer.parseInt(request.getParameter("cod_ciu")));
                clienteDTO.setCv(Integer.parseInt(request.getParameter("cvRuc")));
                if (clienteDAO.insertar(clienteDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                clienteDTO.setIdclientes(Integer.parseInt(request.getParameter("cod_cli")));
                clienteDTO.setCli_ruc(request.getParameter("ruc_cli"));
                clienteDTO.setCli_razonsocial(request.getParameter("razon_cli"));
                clienteDTO.setCli_telefono(request.getParameter("tel_cli"));
                clienteDTO.setCli_direccion(request.getParameter("direc_cli"));
                clienteDTO.setCli_correo(request.getParameter("correo_cli"));
                clienteDTO.setIdciudad(Integer.parseInt(request.getParameter("cod_ciu")));
                if (clienteDAO.modificar(clienteDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (clienteDAO.eliminar(Integer.parseInt(request.getParameter("cod_cli")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(clienteDAO.getcliente());
                break;
                
            case 5:
                System.out.println("cod_cliente" + clienteDAO.getUltimoCodigo());
                if (clienteDAO.getUltimoCodigo() > 0) {
                    out.println(clienteDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (clienteDAO.getclienteFiltro(Integer.parseInt(request.getParameter("cod_cli"))) != null) {
                    out.println(clienteDAO.getclienteFiltro(Integer.parseInt(request.getParameter("cod_cli"))));
                }
                break;
                
            case 7:
                out.println(clienteDAO.listarciudad());
                break;
                     case 8:
                if (clienteDAO.codigoVerificado(request.getParameter("cedula"), Integer.parseInt(request.getParameter("basemax"))) != null) {
                    out.println(clienteDAO.codigoVerificado(request.getParameter("cedula"), Integer.parseInt(request.getParameter("basemax"))));
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
            Logger.getLogger(clientescontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(clientescontrol.class.getName()).log(Level.SEVERE, null, ex);
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
