/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.notacreditodebitodao;
import DAOIMPLE.notacreditodebitodaoimple;
import DTO.notacreditodebitodto;
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
@WebServlet(name = "notacreditodebitocontrol", urlPatterns = {"/notacreditodebitocontrol"})
public class notacreditodebitocontrol extends HttpServlet {

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

        notacreditodebitodao notaDAO = new notacreditodebitodaoimple();
        notacreditodebitodto notaDTO = new notacreditodebitodto();

        switch (opcion) {
            case 1:
                System.out.println("codigo" + notaDAO.getUltimoCodigoNota1());
                if (notaDAO.getUltimoCodigoNota1() > 0) {
                    out.println(notaDAO.getUltimoCodigoNota1());
                }
                break;
            case 2:
                out.println(notaDAO.listarEstadoNota2());
                break;
            case 3:
                out.println(notaDAO.listarUsuarioNota3());
                break;
            case 4:
                out.println(notaDAO.listarProveedorNota4());
                break;
            case 5:
                out.println(notaDAO.listarfacturaNota5());
                break;
            case 6:
                if (notaDAO.listarDetalleFactura6(Integer.parseInt(request.getParameter("nroFacturaN"))) != null) {
                    out.println(notaDAO.listarDetalleFactura6(Integer.parseInt(request.getParameter("nroFacturaN"))));
                    System.out.println(notaDAO.listarDetalleFactura6(Integer.parseInt(request.getParameter("nroFacturaN"))));
                }
                break;
            case 7:
                out.println(notaDAO.listarMercaderiaNota7());
                break;
            case 8:
                Integer dcValor = Integer.parseInt(request.getParameter("dcValor"));
                if (dcValor == 1) {
                    notaDTO.setNocred_tipo(request.getParameter("tiponota"));
                    notaDTO.setNocred_fecha(request.getParameter("fechanota"));
                    notaDTO.setNocred_motivo(request.getParameter("motivonota"));
                    notaDTO.setIdcompra(Integer.parseInt(request.getParameter("factunota")));
                    notaDTO.setIdusuario(Integer.parseInt(request.getParameter("usunota")));
                    notaDTO.setIddesposito(Integer.parseInt(request.getParameter("codDepo")));
                } else if (dcValor == 2) {
                    notaDTO.setNocred_tipo(request.getParameter("tiponota"));
                    notaDTO.setNocred_fecha(request.getParameter("fechanota"));
                    notaDTO.setNocred_motivo(request.getParameter("motivonota"));
                    notaDTO.setIdcompra(Integer.parseInt(request.getParameter("factunota")));
                    notaDTO.setIdusuario(Integer.parseInt(request.getParameter("usunota")));
                    notaDTO.setIddesposito(Integer.parseInt(request.getParameter("codDepos")));
                    notaDTO.setIdcred_deb(Integer.parseInt(request.getParameter("codND")));
                }

                if (notaDAO.insertarCabeceraNota8(notaDTO, dcValor)) {
                    out.println("Exitoso");
                }
                break;
            case 9:
//                notaDTO.setIdcred_debD(Integer.parseInt(request.getParameter("codigoD")));
                notaDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaD")));
                notaDTO.setCred_deb_cantidad(Integer.parseInt(request.getParameter("cantiD")));
                notaDTO.setCred_deb_precio(Integer.parseInt(request.getParameter("precioD")));
                if (notaDAO.insertarDetalleNota9(notaDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 10:
                out.println(notaDAO.listarNota10());
                break;
            case 11:
                notaDTO.setIdestado(Integer.parseInt(request.getParameter("CambioEstadoN")));
                notaDTO.setIdcred_deb(Integer.parseInt(request.getParameter("NotacreNro")));
                if (notaDAO.confirmarNota11(notaDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 12:
                if (notaDAO.listarDetalleNota12(Integer.parseInt(request.getParameter("nroNotaC"))) != null) {
                    out.println(notaDAO.listarDetalleNota12(Integer.parseInt(request.getParameter("nroNotaC"))));
                    System.out.println(notaDAO.listarDetalleNota12(Integer.parseInt(request.getParameter("nroNotaC"))));
                }
                break;
            case 13:
                notaDTO.setIdcred_deb(Integer.parseInt(request.getParameter("codNCD")));
                if (notaDAO.deleteNCD(notaDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 14:
                notaDTO.setNocred_motivo(request.getParameter("motivoND"));
                notaDTO.setCred_deb_cantidad(Integer.parseInt(request.getParameter("cantND")));
                notaDTO.setCred_deb_precio(Integer.parseInt(request.getParameter("precioND")));
//                notaDTO.setIdcred_deb(Integer.parseInt(request.getParameter("condND")));
                if (notaDAO.insertarDetalleND(notaDTO)) {
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
