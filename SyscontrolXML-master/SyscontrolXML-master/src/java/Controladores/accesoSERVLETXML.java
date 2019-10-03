/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.accesoDAO;
import DAOIMPL.accesoDAOIMPL;
import DTO.accesoDTO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author !mX
 */
public class accesoSERVLETXML extends HttpServlet {

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
        accesoDAO acceso = new accesoDAOIMPL();
        accesoDTO accesoDTO = new accesoDTO();

        accesoDTO.setUsuario(request.getParameter("inputUsuario"));
        accesoDTO.setPass(request.getParameter("inputPassword"));
        accesoDTO.setIdusuario(Integer.parseInt(request.getParameter("vCodUser")));

        if (acceso.verificarUsuario(accesoDTO)) {
            HttpSession sessionActiva = request.getSession();
            String UsuarioLogueado = accesoDTO.getUsuario();
            Integer CodUsuario = accesoDTO.getIdusuario();
            sessionActiva.setAttribute("sessionON", UsuarioLogueado);
            sessionActiva.setAttribute("sessionCod", CodUsuario);
            response.sendRedirect("/syscontrol/inicio.jsp");
            System.out.println("verificacion exitosa de usuario ServletXML");

        } else {
            response.sendRedirect("/syscontrol/login.jsp");
            System.out.println("error al verificar el usuario ServletXML");
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
