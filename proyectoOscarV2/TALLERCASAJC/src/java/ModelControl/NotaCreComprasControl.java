/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.NotaCreComprasDAO;
import ModelDAOIMPL.NotaCreComprasDAOIMPL;
import ModelDTO.NotaCreComprasDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Oscar
 */
@WebServlet(name = "NotaCreComprasControl", urlPatterns = {"/NotaCreComprasControl"})
public class NotaCreComprasControl extends HttpServlet {

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

        NotaCreComprasDAO nocreDAO = new NotaCreComprasDAOIMPL();
        NotaCreComprasDTO nocreDTO = new NotaCreComprasDTO();

        switch (opcion) {
            case 1:
                Integer vvcaso = Integer.parseInt(request.getParameter("vvcaso"));
                if (vvcaso == 1) {
                    nocreDTO.setNro_nocred(Integer.parseInt(request.getParameter("_nronocred")));
                    nocreDTO.setNro_timbrado(Integer.parseInt(request.getParameter("_nrotimbrado")));
                    nocreDTO.setObs_nocred(request.getParameter("_obsnocred"));
                    nocreDTO.setId_usuario(Integer.parseInt(request.getParameter("_codusuario")));
                    nocreDTO.setId_compra(Integer.parseInt(request.getParameter("_nrofacturaC")));
                    nocreDTO.setId_deposito(Integer.parseInt(request.getParameter("_deposito")));
                } else if (vvcaso == 2) {
                    nocreDTO.setNro_nocred(Integer.parseInt(request.getParameter("_nronocred")));
                    nocreDTO.setNro_timbrado(Integer.parseInt(request.getParameter("_nrotimbrado")));
                    nocreDTO.setObs_nocred(request.getParameter("_obsnocred"));
                    nocreDTO.setId_usuario(Integer.parseInt(request.getParameter("_codusuario")));
                    nocreDTO.setId_notacrecompra(Integer.parseInt(request.getParameter("_codnotacrecompra")));
                    nocreDTO.setId_compra(Integer.parseInt(request.getParameter("_nrofacturaC")));
                     nocreDTO.setId_deposito(Integer.parseInt(request.getParameter("_depositonro")));
                }

                if (nocreDAO.insertarNotaCreCompras(nocreDTO, vvcaso)) {
                    System.out.println("Mensaje del Servler...Insert Exitoso");
                } else {
                    System.out.println("Mensaje del Servler...Insert Error");
                }

                break;
                
            case 2:
                nocreDTO.setId_articulo(Integer.parseInt(request.getParameter("_codarticulo")));
                nocreDTO.setCantidad_detnocre(Integer.parseInt(request.getParameter("_cantidad")));
                nocreDTO.setPreciouni_detnocre(Integer.parseInt(request.getParameter("_preciounitario")));
                nocreDTO.setId_notacrecompra(Integer.parseInt(request.getParameter("_codnotacrecompra")));

                if (nocreDAO.insertardetalleNotaCreCompras(nocreDTO)) {
                    System.out.println("Mensaje del Servler...Insert Exitoso");
                } else {
                    System.out.println("Mensaje del Servler...Insert Error");
                }

                break;
                
            case 3:
                out.println(nocreDAO.getNotaCreCompras());
                break;
                
            case 4:
                out.println(nocreDAO.getdetalleNotaCreCompras(Integer.parseInt(request.getParameter("nronotacrecompra"))));
                System.out.println(nocreDAO.getdetalleNotaCreCompras(Integer.parseInt(request.getParameter("nronotacrecompra"))));
                break;
                
            case 5:
                nocreDTO.setId_notacrecompra(Integer.parseInt(request.getParameter("_codnotacrecompra")));
                if (nocreDAO.deletedealleNotaCreCompras(nocreDTO)) {
                    System.out.println("Mensaje del Servler...Insert Exitoso");
                } else {
                    System.out.println("Mensaje del Servler...Insert Error");
                }
                break;
                
            case 6:
                nocreDTO.setId_estado(Integer.parseInt(request.getParameter("_estado")));
                nocreDTO.setId_notacrecompra(Integer.parseInt(request.getParameter("_notacrecompra")));
                if (nocreDAO.actualizarestado(nocreDTO)) {
                    System.out.println("Mensaje del Servler...Insert Exitoso");
                } else {
                    System.out.println("Mensaje del Servler...Insert Error");
                }
                break;
                
            case 7:
                out.println(nocreDAO.getdetalleComprasNC(Integer.parseInt(request.getParameter("nrofacturaNC"))));
                System.out.println(nocreDAO.getdetalleComprasNC(Integer.parseInt(request.getParameter("nrofacturaNC"))));
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
