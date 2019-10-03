/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.aperturaDAO;
import DAOIMPL.aperturaDAOIMPL;
import DTO.aperturaDTO;
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
public class aperturaSERVLETXML extends HttpServlet {

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

        aperturaDAO aperDAO = new aperturaDAOIMPL();
        aperturaDTO aperDTO = new aperturaDTO();

        switch (opcion) {
            case 1:
                aperDTO.setComentario(request.getParameter("aper_comentario"));
                aperDTO.setIdusuario(Integer.parseInt(request.getParameter("aper_idusuario")));
                aperDTO.setIdsucursal(Integer.parseInt(request.getParameter("aper_idsucursal")));
                aperDTO.setMontotigo(Integer.parseInt(request.getParameter("aper_montotigo")));
                aperDTO.setMontoclaro(Integer.parseInt(request.getParameter("aper_montoclaro")));
                aperDTO.setMontopersonal(Integer.parseInt(request.getParameter("aper_montopersonal")));
                aperDTO.setMontovox(Integer.parseInt(request.getParameter("aper_montovox")));
                aperDTO.setGiromontotigo(Integer.parseInt(request.getParameter("aper_girotigo")));
                aperDTO.setGiromontoclaro(Integer.parseInt(request.getParameter("aper_giroclaro")));
                aperDTO.setGiromontopersonal(Integer.parseInt(request.getParameter("aper_giropersonal")));
                aperDTO.setGiromontovox(Integer.parseInt(request.getParameter("aper_girovox")));

                if (aperDAO.insertarapertura(aperDTO)) {
                    out.println("Exitoso");
                }
                break;

            case 2:
                out.println(aperDAO.getapertura());
                break;
            case 3:
                out.println(aperDAO.obtenerapertura(Integer.parseInt(request.getParameter("aper_nroapertura"))));
                break;
            case 4:
                Integer v_cod = Integer.parseInt(request.getParameter("v_valor"));
                if (v_cod == 2) {
                    aperDTO.setIdapertura(Integer.parseInt(request.getParameter("aper_codapertura")));
                } else {
                    aperDTO.setComentario(request.getParameter("aper_comentario"));
                    aperDTO.setIdusuario(Integer.parseInt(request.getParameter("aper_idusuario")));
                    aperDTO.setIdsucursal(Integer.parseInt(request.getParameter("aper_idsucursal")));
                    aperDTO.setMontotigo(Integer.parseInt(request.getParameter("aper_montotigo")));
                    aperDTO.setMontoclaro(Integer.parseInt(request.getParameter("aper_montoclaro")));
                    aperDTO.setMontopersonal(Integer.parseInt(request.getParameter("aper_montopersonal")));
                    aperDTO.setMontovox(Integer.parseInt(request.getParameter("aper_montovox")));
                    aperDTO.setGiromontotigo(Integer.parseInt(request.getParameter("aper_girotigo")));
                    aperDTO.setGiromontoclaro(Integer.parseInt(request.getParameter("aper_giroclaro")));
                    aperDTO.setGiromontopersonal(Integer.parseInt(request.getParameter("aper_giropersonal")));
                    aperDTO.setGiromontovox(Integer.parseInt(request.getParameter("aper_girovox")));
                    aperDTO.setIdapertura(Integer.parseInt(request.getParameter("aper_codapertura")));
                }

                if (aperDAO.actualizarapertura(aperDTO, v_cod)) {
                    out.println("Exitoso");
                }
                break;
            case 5:
                out.println(aperDAO.getcierre());
                break;
            case 6:
                aperDTO.setIdapertura(Integer.parseInt(request.getParameter("nroapertura")));
                if (aperDAO.cerrrarapertura(aperDTO)) {
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
