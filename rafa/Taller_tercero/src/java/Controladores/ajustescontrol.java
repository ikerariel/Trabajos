/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.ajustesdao;
import DAOIMPLE.ajustesdaoimple;
import DTO.Ajustesdto;
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
@WebServlet(name = "ajustescontrol", urlPatterns = {"/ajustescontrol"})
public class ajustescontrol extends HttpServlet {

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
        
        ajustesdao ajustDAO = new ajustesdaoimple();
        Ajustesdto ajustDTO = new Ajustesdto();
        
        switch(opcion){
            case 1:
                System.out.println("codigoAjus" + ajustDAO.getUltimoCodigoAjustes1());
                if (ajustDAO.getUltimoCodigoAjustes1() > 0) {
                    out.println(ajustDAO.getUltimoCodigoAjustes1());
                }
                break;
            case 2:
                out.println(ajustDAO.ListarEstadoAjuste2());
                break;
            case 3:
                out.println(ajustDAO.ListarUsuarioAjustes3());
                break;
            case 4:
                out.println(ajustDAO.ListarMotivoAjuste4());
                break;
            case 5:
                out.println(ajustDAO.ListarMercaderiaAjuste5());
                break;
            case 6:
                ajustDTO.setAjuste_fecha(request.getParameter("Ajustefecha"));
                ajustDTO.setIdmot_ajus(Integer.parseInt(request.getParameter("Ajustemotivo")));
                ajustDTO.setIdusuario(Integer.parseInt(request.getParameter("Ajusteusua")));
                ajustDTO.setIdestado(Integer.parseInt(request.getParameter("Ajusteestad")));
                if (ajustDAO.insertarCabeceraAjuste6(ajustDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 7:
                ajustDTO.setIdajusteA(Integer.parseInt(request.getParameter("codigoD")));
                ajustDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaD")));
                ajustDTO.setAjuste_cantidad(Integer.parseInt(request.getParameter("cantiD")));
                if (ajustDAO.insertarDetalleAjuste7(ajustDTO)) {
                    out.println("Exitoso");
                }
                break; 
            case 8:
                out.println(ajustDAO.ListarAjuste8());
                break;
            case 9:
                ajustDTO.setIdestado(Integer.parseInt(request.getParameter("CambioEstadoAjust")));
                ajustDTO.setIdajuste(Integer.parseInt(request.getParameter("AjustesNro")));
                if (ajustDAO.confirmarAjuste9(ajustDTO)) {
                    out.println("Exitoso");
                }
                break; 
            case 10:
                if (ajustDAO.listarDetalleAjuste10(Integer.parseInt(request.getParameter("nroAjustes"))) != null) {
                    out.println(ajustDAO.listarDetalleAjuste10(Integer.parseInt(request.getParameter("nroAjustes"))));
                    System.out.println(ajustDAO.listarDetalleAjuste10(Integer.parseInt(request.getParameter("nroAjustes"))));
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
