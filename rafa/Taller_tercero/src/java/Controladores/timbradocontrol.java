/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.timbradodao;
import DAOIMPLE.timbradodaoimple;
import DTO.timbradodto;
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
@WebServlet(name = "timbradocontrol", urlPatterns = {"/timbradocontrol"})
public class timbradocontrol extends HttpServlet {

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
        
//        timbradodao timDAO = new timbradodaoimple();
        timbradodao timDAO = new timbradodaoimple();
        timbradodto timDTO = new timbradodto();
        
        switch(opcion){
            case 1:
                System.out.println("codigotimb" + timDAO.getUltimoCodigoTimbrado1());
                if (timDAO.getUltimoCodigoTimbrado1() > 0) {
                    out.println(timDAO.getUltimoCodigoTimbrado1());
                    System.out.println(timDAO.getUltimoCodigoTimbrado1());
                }
                break;
            case 2:
                out.println(timDAO.ListarEstadoTimbrado2());
                break;
            case 3:
                timDTO.setTim_numero(Integer.parseInt(request.getParameter("timNumero")));
                timDTO.setTim_fechavence(request.getParameter("timFechavenc"));
                timDTO.setIdestado(Integer.parseInt(request.getParameter("timEstado")));
                timDTO.setTim_fechainicio(request.getParameter("timFechainic"));
                timDTO.setIdusuario(Integer.parseInt(request.getParameter("timUsuario")));
                timDTO.setFac_establecimiento(request.getParameter("timEstabl"));
                timDTO.setFac_desde(request.getParameter("timDesde"));
                timDTO.setFac_hasta(request.getParameter("timAsta"));
                timDTO.setFac_caja(request.getParameter("timCaja"));
                if (timDAO.insertarTimbrado3(timDTO)){
                    out.print("Exitoso");
                }
                break;
            case 4:
                timDTO.setTim_numero(Integer.parseInt(request.getParameter("timNumero")));
                timDTO.setNumerfactura(request.getParameter("timNroFactura"));
                if (timDAO.insertarDetalleNroFactura4(timDTO)){
                    out.print("Exitoso");
                }
                break;
            case 5:
                out.println(timDAO.listarTimbrado5());
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
