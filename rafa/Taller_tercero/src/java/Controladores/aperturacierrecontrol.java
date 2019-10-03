/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.aperturacierredao;
import DAOIMPLE.aperturacierredaoimple;
import DTO.aperturacierredto;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rafel
 */
@WebServlet(name = "aperturacierrecontrol", urlPatterns = {"/aperturacierrecontrol"})
public class aperturacierrecontrol extends HttpServlet {

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
        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));
        
        aperturacierredao aperDAO = new aperturacierredaoimple();
        aperturacierredto aperDTO = new aperturacierredto();
        
        switch(opcion){
            case 1:
                System.out.println("codigoAper" + aperDAO.getUltimoCodigoAper1());
                if (aperDAO.getUltimoCodigoAper1()> 0) {
                    out.println(aperDAO.getUltimoCodigoAper1());
                }
                break;
            case 2:
                out.println(aperDAO.ListarEstadoAper2());
                break;
            case 3:
                out.println(aperDAO.ListarUsuarioAper3());
                break;
            case 4:
                out.println(aperDAO.ListarCajasAper4());
                break;
            case 5:
                aperDTO.setAper_monto(Integer.parseInt(request.getParameter("AperMonto")));
                aperDTO.setIdcaja(Integer.parseInt(request.getParameter("AperCaja")));
                aperDTO.setIdusuario(Integer.parseInt(request.getParameter("AperUsua")));
                aperDTO.setIdestado(Integer.parseInt(request.getParameter("AperEsta")));
//                aperDTO.setCierre_monto(Integer.parseInt(request.getParameter("CierMont")));
                aperDTO.setCajero(request.getParameter("AperCajero"));
                aperDTO.setIddeposito(Integer.parseInt(request.getParameter("AperDepos")));
                aperDTO.setIdtimbrado(Integer.parseInt(request.getParameter("AperTimbr")));
                aperDTO.setFecha_apertura(request.getParameter("AperFecha"));
//                aperDTO.setFecha_cierre(request.getParameter("CierreFecha"));
                if (aperDAO.insertarAperturacierre5(aperDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 6:
                out.println(aperDAO.ListarTimbradoAper6());
                break; 
            case 7:
                out.println(aperDAO.ListarAperturCierre7());
                break;
            case 8:
                if (aperDAO.RecuperarApertura8(Integer.parseInt(request.getParameter("nroApert"))) != null) {
                    out.println(aperDAO.RecuperarApertura8(Integer.parseInt(request.getParameter("nroApert"))));
                    System.out.println(aperDAO.RecuperarApertura8(Integer.parseInt(request.getParameter("nroApert"))));
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
