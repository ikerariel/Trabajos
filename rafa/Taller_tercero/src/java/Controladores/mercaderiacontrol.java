/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.mercaderiadao;
import DAOIMPLE.mercaderiadaoimple;
import DTO.mercaderiadto;
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
@WebServlet(name = "mercaderiacontrol", urlPatterns = {"/mercaderiacontrol"})
public class mercaderiacontrol extends HttpServlet {

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
        
        mercaderiadao Merca_DAO=new mercaderiadaoimple();
        
        mercaderiadto Merca_Dto = new mercaderiadto();
        
        switch (opcion) {
            case 1:                
                Merca_Dto.setIdmercaderia(Integer.parseInt(request.getParameter("cod_merca")));
                Merca_Dto.setMer_costo(Integer.parseInt(request.getParameter("m_cost")));
                Merca_Dto.setMer_precio(Integer.parseInt(request.getParameter("m_prec")));
                Merca_Dto.setMer_descripcion(request.getParameter ("descri_mer"));
                Merca_Dto.setIdcategoria(Integer.parseInt(request.getParameter("cod_cate")));
                Merca_Dto.setIdmarca(Integer.parseInt(request.getParameter("cod_marca")));
                Merca_Dto.setIdprocedencia(Integer.parseInt(request.getParameter("cod_proced")));
                Merca_Dto.setIdimpuesto(Integer.parseInt(request.getParameter("cod_impu")));
                Merca_Dto.setCodigogenerico(request.getParameter("cod_generico"));
                if (Merca_DAO.insertar(Merca_Dto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                Merca_Dto.setIdmercaderia(Integer.parseInt(request.getParameter("cod_merca")));
                Merca_Dto.setMer_costo(Integer.parseInt(request.getParameter("m_cost")));
                Merca_Dto.setMer_precio(Integer.parseInt(request.getParameter("m_prec")));
                Merca_Dto.setMer_descripcion(request.getParameter ("descri_mer"));
                Merca_Dto.setIdcategoria(Integer.parseInt(request.getParameter("cod_cate")));
                Merca_Dto.setIdmarca(Integer.parseInt(request.getParameter("cod_marca")));
                Merca_Dto.setIdprocedencia(Integer.parseInt(request.getParameter("cod_proced")));
                Merca_Dto.setIdimpuesto(Integer.parseInt(request.getParameter("cod_impu")));
                Merca_Dto.setCodigogenerico(request.getParameter ("cod_generico"));
                if (Merca_DAO.modificar(Merca_Dto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (Merca_DAO.eliminar(Integer.parseInt(request.getParameter("cod_merca")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(Merca_DAO.getmercaderia());
                break;
                
            case 5:
                System.out.println("cod_mercad" + Merca_DAO.getUltimoCodigo());
                if (Merca_DAO.getUltimoCodigo() > 0) {
                    out.println(Merca_DAO.getUltimoCodigo());
                }
                break;
                
            case 6:              
                if (Merca_DAO.getmercaderiaFiltro(Integer.parseInt(request.getParameter("cod_merca"))) != null) {
                    out.println(Merca_DAO.getmercaderiaFiltro(Integer.parseInt(request.getParameter("cod_merca"))));
                }
                break;
                
            case 7:
                out.println(Merca_DAO.listarcategorias());
                break;
                
            case 8:
                out.println(Merca_DAO.listarmarcas());
                break;
            case 9:
                out.println(Merca_DAO.listarprocedencia());
                break;
            case 10:
                out.println(Merca_DAO.listarimpuestos());
                break;    
//            case 11: 
//                Merca_Dto.setIdmercaderia(Integer.parseInt(request.getParameter("cod_stock")));
//                Merca_Dto.setStock_merca(request.getParameter ("descri_mer"));
//                if (Merca_DAO.insertarStock(Merca_Dto)) {
//                    out.println("Exitoso");
//                }
//                break;
//            case 12:
//                Merca_Dto.setIdmercaderia(Integer.parseInt(request.getParameter("cod_stock")));
//                Merca_Dto.setStock_merca(request.getParameter ("descri_mer"));
//                if (Merca_DAO.modificarStock(Merca_Dto)) {
//                    out.println("Exitoso");
//                }
//                break; 
//            case 13:
//                if (Merca_DAO.eliminarStock(Integer.parseInt(request.getParameter("cod_stock")))) {
//                    out.println("Exitoso");
//                }
//                break;    
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
            Logger.getLogger(mercaderiacontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(mercaderiacontrol.class.getName()).log(Level.SEVERE, null, ex);
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
