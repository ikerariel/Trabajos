/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.proveedoresdao;
import DAOIMPLE.proveedoresdaoimple;
import DTO.proveedoresdto;
import Genericos.mierror;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Rafel
 */
@WebServlet(name = "proveedorescontrol", urlPatterns = {"/proveedorescontrol"})
public class proveedorescontrol extends HttpServlet {

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
            throws ServletException, IOException, mierror {
        response.setContentType("application/json, charset=UTF-8");
        PrintWriter out = response.getWriter();
        Integer opcion = Integer.parseInt(request.getParameter("opcion"));
        
        proveedoresdao proveeDAO=new proveedoresdaoimple();
        
        proveedoresdto proveeDTO = new proveedoresdto();
        
        switch (opcion) {
            case 1:
                
                proveeDTO.setId_prov(Integer.parseInt(request.getParameter("cod_provee")));
                proveeDTO.setProv_nombre(request.getParameter ("nom_prov"));
                proveeDTO.setProv_direc(request.getParameter("dir_prov"));
                proveeDTO.setProv_imail(request.getParameter("imail_prov"));
                proveeDTO.setProv_telf(request.getParameter("tlef_prov"));
                proveeDTO.setProv_ruc(request.getParameter("ruc_prov"));
                if (proveeDAO.insertar(proveeDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                proveeDTO.setId_prov(Integer.parseInt(request.getParameter("cod_provee")));
                proveeDTO.setProv_nombre(request.getParameter ("nom_prov"));
                proveeDTO.setProv_direc(request.getParameter("dir_prov"));
                proveeDTO.setProv_imail(request.getParameter("imail_prov"));
                proveeDTO.setProv_telf(request.getParameter("tlef_prov"));
                proveeDTO.setProv_ruc(request.getParameter("ruc_prov"));
                if (proveeDAO.modificar(proveeDTO)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (proveeDAO.eliminar(Integer.parseInt(request.getParameter("cod_provee")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(proveeDAO.getproveedor());
                break;
                
            case 5:
                System.out.println("cod_proveedor" + proveeDAO.getUltimoCodigo());
                if (proveeDAO.getUltimoCodigo() > 0) {
                    out.println(proveeDAO.getUltimoCodigo());
                }
                break;
                
            case 6:
                
                if (proveeDAO.getproveedorFiltro(Integer.parseInt(request.getParameter("cod_provee"))) != null) {
                    out.println(proveeDAO.getproveedorFiltro(Integer.parseInt(request.getParameter("cod_provee"))));
                }
                break;
                
            ////case 7:
                //out.println(proveeDAO.listarciudad());
                //break;
            //case 8:
                //out.println(ciudDAO.listarMarcas());
               // break;
            //case 9:
               // out.println(ciudDAO.listarProcedencia());
                //break;
          
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
        try {
            processRequest(request, response);
        } catch (mierror ex) {
            Logger.getLogger(proveedorescontrol.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (mierror ex) {
            Logger.getLogger(proveedorescontrol.class.getName()).log(Level.SEVERE, null, ex);
        }
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
