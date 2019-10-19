/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.facturacionDAO;
import DAOIMPL.facturacionDAOIMPL;
import DTO.facturacionDTO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Usuario
 */
public class facturacionSERVLETXML extends HttpServlet {

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
        
        facturacionDAO fDAO = new facturacionDAOIMPL();
        facturacionDTO fDTO = new facturacionDTO();
        
        switch(opcion){
            case 1:
                out.println(fDAO.getFacturas(Integer.parseInt(request.getParameter("codigoCajero"))));
                
                break;
            case 2:
                out.println(fDAO.getArticulos());
                break;
            case 3:
                fDTO.setIdcliente(Integer.parseInt(request.getParameter("fidcliente")));
                fDTO.setIddocfactura(Integer.parseInt(request.getParameter("fidfactura")));
                fDTO.setIdvendedor(Integer.parseInt(request.getParameter("fidvemdedor")));
                fDTO.setIdaperturacierre(Integer.parseInt(request.getParameter("fidaperturacierre")));
                fDTO.setIdcondicionventa(Integer.parseInt(request.getParameter("fidcondventa")));
                fDTO.setMontoventa(Integer.parseInt(request.getParameter("fmontoventa")));
                 if (fDAO.insertarVenta(fDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                fDTO.setIdarticulo(Integer.parseInt(request.getParameter("fidarticulo")));
                fDTO.setCant(Integer.parseInt(request.getParameter("fcantidad")));
                fDTO.setPreciou(Integer.parseInt(request.getParameter("fpreciou")));
                fDTO.setIdimpuesto(Integer.parseInt(request.getParameter("fidimpuesto")));
                 if (fDAO.insertarVentaDetalle(fDTO)) {
                    out.println("Exitoso");
                }
                break;
                  case 5:
                out.println(fDAO.getAnular(request.getParameter("nrofac")));
                
                break;
                    case 6:
                fDTO.setIddocfactura(Integer.parseInt(request.getParameter("codFactura")));
                 if (fDAO.anularfactura(fDTO)) {
                    out.println("Exitoso");
                }
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
