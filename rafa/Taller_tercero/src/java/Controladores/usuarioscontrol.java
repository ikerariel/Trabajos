/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.usuariosdao;
import DAOIMPLE.usuariosdaoimple;
import DTO.usuariosdto;
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
@WebServlet(name = "usuarioscontrol", urlPatterns = {"/usuarioscontrol"})
public class usuarioscontrol extends HttpServlet {

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
        
        usuariosdao Usua_DAO=new usuariosdaoimple();
        
        usuariosdto Usua_Dto = new usuariosdto();
        
        switch (opcion) {
            case 1:                
                Usua_Dto.setIdusuario(Integer.parseInt(request.getParameter("cod_usu")));
                Usua_Dto.setUsu_nombre(request.getParameter ("nom_usu"));
                Usua_Dto.setUsu_clave(request.getParameter ("clav_usu"));
                Usua_Dto.setIdfuncionario(Integer.parseInt(request.getParameter("cod_funci")));
                Usua_Dto.setIdperfil(Integer.parseInt(request.getParameter("cod_perf")));
                Usua_Dto.setIdsucursal(Integer.parseInt(request.getParameter("cod_sucu")));
                if (Usua_DAO.insertar(Usua_Dto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                Usua_Dto.setIdusuario(Integer.parseInt(request.getParameter("cod_usu")));
                Usua_Dto.setUsu_nombre(request.getParameter ("nom_usu"));
                Usua_Dto.setUsu_clave(request.getParameter ("clav_usu"));
                Usua_Dto.setIdfuncionario(Integer.parseInt(request.getParameter("cod_funci")));
                Usua_Dto.setIdperfil(Integer.parseInt(request.getParameter("cod_perf")));
                Usua_Dto.setIdsucursal(Integer.parseInt(request.getParameter("cod_sucu")));
                if (Usua_DAO.modificar(Usua_Dto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (Usua_DAO.eliminar(Integer.parseInt(request.getParameter("cod_usu")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(Usua_DAO.getusuario());
                break;
                
            case 5:
                System.out.println("cod_usua" + Usua_DAO.getUltimoCodigo());
                if (Usua_DAO.getUltimoCodigo() > 0) {
                    out.println(Usua_DAO.getUltimoCodigo());
                }
                break;
                
            case 6:              
                if (Usua_DAO.getusuarioFiltro(Integer.parseInt(request.getParameter("cod_usu"))) != null) {
                    out.println(Usua_DAO.getusuarioFiltro(Integer.parseInt(request.getParameter("cod_usu"))));
                }
                break;
                
            case 7:
                out.println(Usua_DAO.listarfuncionario());
                break;
                
            case 8:
                out.println(Usua_DAO.listarperfil());
                break;
            case 9:
                out.println(Usua_DAO.listarsucursal());
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
            Logger.getLogger(usuarioscontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(usuarioscontrol.class.getName()).log(Level.SEVERE, null, ex);
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
