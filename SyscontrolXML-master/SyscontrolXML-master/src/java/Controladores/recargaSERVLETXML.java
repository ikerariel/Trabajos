/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.recargaDAO;
import DAOIMPL.recargaDAOIMPL;
import DTO.recargaDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author !mX
 */
public class recargaSERVLETXML extends HttpServlet {

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

        recargaDAO rDAO = new recargaDAOIMPL();
        recargaDTO rDTO = new recargaDTO();

        switch (opcion) {
            case 1:
                rDTO.setMontorecarga(Integer.parseInt(request.getParameter("rec_montorecarga")));
                rDTO.setNrodestino(Integer.parseInt(request.getParameter("rec_nrodestino")));
                rDTO.setNrotransaccion(Integer.parseInt(request.getParameter("rec_nrotransaccion")));
                rDTO.setIdtipooperadora(Integer.parseInt(request.getParameter("rec_codoperadora")));
                rDTO.setObservacion(request.getParameter("rec_obervacion"));
                rDTO.setNrooperacion(request.getParameter("rec_nrooperacion"));
                rDTO.setIdcliente(Integer.parseInt(request.getParameter("rec_cliente")));
                rDTO.setIdestado(Integer.parseInt(request.getParameter("rec_pago")));
                rDTO.setIdusuario(Integer.parseInt(request.getParameter("rec_idusuer")));

                if (rDAO.insertarrecarga(rDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 2:
                out.println(rDAO.getconsulta(Integer.parseInt(request.getParameter("v_valor"))));
                break;
            case 3:
                out.println(rDAO.getoperadora());
                break;
            case 4:
                out.println(rDAO.getrecarga());
                break;
            case 5:
                rDTO.setIddetallerecargas(Integer.parseInt(request.getParameter("v_codrecarga")));
                if (rDAO.eliminarcarga(rDTO)) {
                    out.println("Exitos");
                }
                break;
            case 6:
                out.println(rDAO.consultarecarga(Integer.parseInt(request.getParameter("v_nroapertura"))));
                break;
            case 7:
                out.println(rDAO.getconsultaci(request.getParameter("v_valor")));
                break;
            case 8:
                out.println(rDAO.getrecargaspendientes(Integer.parseInt(request.getParameter("vCodestadorecarga"))));
                break;
            case 9:
                Integer vCod = Integer.parseInt(request.getParameter("vCodi"));
                Integer cod = 0;
                if (vCod == 1) {
                    cod = 1;
                    rDTO.setIdestado(Integer.parseInt(request.getParameter("vEstad")));
                    rDTO.setMontopagadorecarga(Integer.parseInt(request.getParameter("vMontpagado")));
                    rDTO.setIddetallerecargas(Integer.parseInt(request.getParameter("vCodrecarga")));
                    rDTO.setNrooperarecarga(Integer.parseInt(request.getParameter("vNroopracion")));
                } else if (vCod == 2) {
                    cod = 2;
                    rDTO.setIdestado(Integer.parseInt(request.getParameter("vEstad")));
                    rDTO.setIddetallerecargas(Integer.parseInt(request.getParameter("vCodrecarga")));
                }

                if (rDAO.insertarpagorecarga(rDTO, cod)) {
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
