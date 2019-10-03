/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.giroDAO;
import DAOIMPL.giroDAOIMPL;
import DTO.giroDTO;
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
public class giroSERVLETXML extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));

        giroDAO gDAO = new giroDAOIMPL();
        giroDTO gDTO = new giroDTO();

        switch (opcion) {
            case 1:
                out.println(gDAO.getoperacion());
                break;

            case 2:
                out.println(gDAO.getcliente(request.getParameter("v_cicliente")));
                break;

            case 3:
                gDTO.setNombrecliente(request.getParameter("v_nombrecliente"));
                gDTO.setCedula(request.getParameter("v_cedulacliente"));
                if (gDAO.insertarcliente(gDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                gDTO.setNrodestino(Integer.parseInt(request.getParameter("v_nrodestino")));
                gDTO.setNroorigen(Integer.parseInt(request.getParameter("v_nroorigen")));
                gDTO.setMontogirobilletera(Integer.parseInt(request.getParameter("v_montogirobilletera")));
                gDTO.setIdtipooperadora(Integer.parseInt(request.getParameter("v_codoperadora")));
                gDTO.setNrotransaccion(Integer.parseInt(request.getParameter("v_nrotransaccion")));
                gDTO.setNrooperacion(Integer.parseInt(request.getParameter("v_nrooperacion")));
                gDTO.setIdcliente(Integer.parseInt(request.getParameter("v_codcliente")));
                gDTO.setIdtipooperacion(Integer.parseInt(request.getParameter("v_codoperacion")));
                gDTO.setPorcentaje(request.getParameter("v_porcentaje"));
                gDTO.setMontoenvio(Integer.parseInt(request.getParameter("v_montoenvio")));
                gDTO.setIdestado(Integer.parseInt(request.getParameter("v_codpago")));
                gDTO.setIdusuario(Integer.parseInt(request.getParameter("v_CodIDuser")));
                if (gDAO.insertargiro(gDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 5:
                out.println(gDAO.getgiros());
                break;
            case 6:
                gDTO.setIddetallegiro(Integer.parseInt(request.getParameter("v_codgiro")));
                if (gDAO.eliminargiro(gDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 7:
                out.println(gDAO.getcliente(request.getParameter("v_codapertura")));
                break;
            case 8:
                out.println(gDAO.getconsultagiro(Integer.parseInt(request.getParameter("v_nrodes")),
                        Integer.parseInt(request.getParameter("v_nroorig"))));
                break;
            case 9:
                out.println(gDAO.getopago());
                break;
            case 10:
                out.println(gDAO.getconsultagiroci(request.getParameter("v_ci")));
                break;
            case 11:
                out.println(gDAO.getgirospendientes(Integer.parseInt(request.getParameter("v_codpago"))));
                break;
            case 12:
                Integer vCod = Integer.parseInt(request.getParameter("vCod"));
                Integer cod = 0;
                if (vCod == 1) {
                    cod = 1;
                    gDTO.setIdestado(Integer.parseInt(request.getParameter("v_estad")));
                    gDTO.setMontopagado(Integer.parseInt(request.getParameter("v_montpagado")));
                    gDTO.setIddetallegiro(Integer.parseInt(request.getParameter("v_codgiro")));
                    gDTO.setVnrooperacion(Integer.parseInt(request.getParameter("v_nrooper")));
                } else if (vCod == 2) {
                    cod = 2;
                    gDTO.setIdestado(Integer.parseInt(request.getParameter("v_estad")));
                    gDTO.setIddetallegiro(Integer.parseInt(request.getParameter("v_codgiro")));
                }

                if (gDAO.insertarpago(gDTO, cod)) {
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
