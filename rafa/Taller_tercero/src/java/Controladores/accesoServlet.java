/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.accesoDAO;
import DAOIMPLE.accesoDATOIMPL;
import DTO.accesoDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
@WebServlet(name = "accesoServlet", urlPatterns = {"/accesoServlet"})
public class accesoServlet extends HttpServlet {

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
            throws ServletException, IOException {

        accesoDAO acceo = new accesoDATOIMPL();
        accesoDTO accesoD = new accesoDTO();

        accesoD.setUsu_nombre(request.getParameter("usuario"));
        accesoD.setUsu_clave(request.getParameter("pass"));
        accesoD.setIdsucursal(Integer.parseInt(request.getParameter("sucursales")));
        accesoD.setIddeposito(Integer.parseInt(request.getParameter("depositos")));

        if (acceo.verificarUser(accesoD)) {
            HttpSession sessionActiva = request.getSession();
            String UsuarioLogueado = accesoD.getUsu_nombre();
            Integer Usuariodeposito = accesoD.getIddeposito();
            Integer Usuariosucursal = accesoD.getIdsucursal();
            sessionActiva.setAttribute("sessionON", UsuarioLogueado);
            sessionActiva.setAttribute("login", UsuarioLogueado);
            sessionActiva.setAttribute("vSucursal", Usuariosucursal);
            sessionActiva.setAttribute("vDeposito", Usuariodeposito);
            response.sendRedirect("/Taller_tercero/menuprincipal_v.jsp");
            System.out.println("verificacion exitosa de usuario");

        } else {
            response.sendRedirect("/Taller_tercero/acceso_v.jsp");
            
            System.out.println("error al verificar el usuario");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
