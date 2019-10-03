/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.notaremisiondao;
import DAOIMPLE.notaremisiondaoimple;
import DTO.notaremisiondto;
import com.sun.xml.internal.fastinfoset.EncodingConstants;
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
@WebServlet(name = "notaremisioncontrol", urlPatterns = {"/notaremisioncontrol"})
public class notaremisioncontrol extends HttpServlet {

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

        notaremisiondao notareDAO = new notaremisiondaoimple();
        notaremisiondto notareDTO = new notaremisiondto();

        switch (opcion) {
            case 1:
                System.out.println("codigoRemi" + notareDAO.getUltimoCodigoNotaRemi1());
                if (notareDAO.getUltimoCodigoNotaRemi1() > 0) {
                    out.println(notareDAO.getUltimoCodigoNotaRemi1());
                }
                break;
            case 2:
                out.println(notareDAO.listarEstadoNotaRemi2());
                break;
            case 3:
                out.println(notareDAO.listarUsuarioNotaRemi3());
                break;
            case 4:
                out.println(notareDAO.listarProveedorNotaRemi4());
                break;
            case 5:
                out.println(notareDAO.listarfacturaRemi5());
                break;
            case 6:
                if (notareDAO.listarDetalleFacturaRemi6(Integer.parseInt(request.getParameter("nroFacturaRemi"))) != null) {
                    out.println(notareDAO.listarDetalleFacturaRemi6(Integer.parseInt(request.getParameter("nroFacturaRemi"))));
                    System.out.println(notareDAO.listarDetalleFacturaRemi6(Integer.parseInt(request.getParameter("nroFacturaRemi"))));
                }
                break;
            case 7:
                out.println(notareDAO.listarMercaderiaNotaRemi7());
                break;
            case 8:
                Integer rValor = Integer.parseInt(request.getParameter("rValor"));
                if (rValor == 1) {
                    notareDTO.setObservacionremi(request.getParameter("Remiobserv"));
                    notareDTO.setFecharemi(request.getParameter("Remifecha"));
                    notareDTO.setIdestado(Integer.parseInt(request.getParameter("Remiestad")));
                    notareDTO.setIdusuario(Integer.parseInt(request.getParameter("Remiusu")));
                    notareDTO.setIdcompra(Integer.parseInt(request.getParameter("RemifactuC")));
                } else if (rValor == 2) {
                    notareDTO.setObservacionremi(request.getParameter("Remiobserv"));
                    notareDTO.setFecharemi(request.getParameter("Remifecha"));
                    notareDTO.setIdusuario(Integer.parseInt(request.getParameter("Remiusu")));
                    notareDTO.setIdcompra(Integer.parseInt(request.getParameter("RemifactuC")));
                    notareDTO.setIdnotaremi(Integer.parseInt(request.getParameter("codNotaremision")));
                }

                if (notareDAO.insertarCabeceraNotaRemi8(notareDTO, rValor)) {
                    out.println("Exitoso");
                }
                break;
            case 9:
                notareDTO.setIdnotaremiRE(Integer.parseInt(request.getParameter("codigoR")));
                notareDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaR")));
                notareDTO.setCantidadremi(request.getParameter("cantiR"));
                if (notareDAO.insertarDetalleNotaRemi9(notareDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 10:
                out.println(notareDAO.listarNotaRemi10());
                break;
            case 11:
                notareDTO.setIdestado(Integer.parseInt(request.getParameter("CambioEstadoR")));
                notareDTO.setIdnotaremi(Integer.parseInt(request.getParameter("NotaRemiNro")));
                if (notareDAO.confirmarNotaRemi11(notareDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 12:
                if (notareDAO.listarDetalleNotaRemi12(Integer.parseInt(request.getParameter("nroNotaRemi"))) != null) {
                    out.println(notareDAO.listarDetalleNotaRemi12(Integer.parseInt(request.getParameter("nroNotaRemi"))));
                    System.out.println(notareDAO.listarDetalleNotaRemi12(Integer.parseInt(request.getParameter("nroNotaRemi"))));
                }
                break;
            case 13:
                notareDTO.setIdnotaremi(Integer.parseInt(request.getParameter("codNRemision")));
                if (notareDAO.deleteNotaremision(notareDTO)) {
                    out.println("Exitoso");
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
