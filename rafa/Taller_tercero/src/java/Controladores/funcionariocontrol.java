/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.funcionariodao;
import DAOIMPLE.fucionariodaoimple;
import DTO.funcionariodto;
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
@WebServlet(name = "funcionariocontrol", urlPatterns = {"/funcionariocontrol"})
public class funcionariocontrol extends HttpServlet {

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
        
        funcionariodao funcion_DAO=new fucionariodaoimple();
         
         //        //////////////////////////////////////////////////////////////////
//        //USO PATRON DTO      
        funcionariodto funcion_Dto = new funcionariodto();
        
        switch (opcion) {
            case 1:
                
                funcion_Dto.setIdfuncionario(Integer.parseInt(request.getParameter("cod_func")));
                funcion_Dto.setFun_direccion(request.getParameter ("fun_dire"));
                funcion_Dto.setFun_correo(request.getParameter("fun_corr"));
                funcion_Dto.setFun_ci(request.getParameter("fun_ci"));
                funcion_Dto.setFun_nombres(request.getParameter("fun_nomb"));
                funcion_Dto.setFun_apellidos(request.getParameter("fum_apell"));
                funcion_Dto.setFun_telefono(request.getParameter("fun_telef"));
                funcion_Dto.setIdcargo(Integer.parseInt(request.getParameter("cod_carg")));
                funcion_Dto.setIdciudad(Integer.parseInt(request.getParameter("cod_ciu")));
                if (funcion_DAO.insertar(funcion_Dto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 2:
                funcion_Dto.setIdfuncionario(Integer.parseInt(request.getParameter("cod_func")));
                funcion_Dto.setFun_direccion(request.getParameter ("fun_dire"));
                funcion_Dto.setFun_correo(request.getParameter("fun_corr"));
                funcion_Dto.setFun_ci(request.getParameter("fun_ci"));
                funcion_Dto.setFun_nombres(request.getParameter("fun_nomb"));
                funcion_Dto.setFun_apellidos(request.getParameter("fum_apell"));
                funcion_Dto.setFun_telefono(request.getParameter("fun_telef"));
                funcion_Dto.setIdcargo(Integer.parseInt(request.getParameter("cod_carg")));
                funcion_Dto.setIdciudad(Integer.parseInt(request.getParameter("cod_ciu")));
                if (funcion_DAO.modificar(funcion_Dto)) {
                    out.println("Exitoso");
                }
                break;
                
            case 3:
                if (funcion_DAO.eliminar(Integer.parseInt(request.getParameter("cod_func")))) {
                    out.println("Exitoso");
                }
                break;
                
            case 4:
                out.println(funcion_DAO.getfuncionario());
                break;
                
            case 5:
                System.out.println("cod_funcionar" + funcion_DAO.getUltimoCodigo());
                if (funcion_DAO.getUltimoCodigo() > 0) {
                    out.println(funcion_DAO.getUltimoCodigo());
                }
                break;
                
            case 6:              
                if (funcion_DAO.getfuncionarioFiltro(Integer.parseInt(request.getParameter("cod_func"))) != null) {
                    out.println(funcion_DAO.getfuncionarioFiltro(Integer.parseInt(request.getParameter("cod_func"))));
                }
                break;
                
            case 7:
                out.println(funcion_DAO.listarcargos());
                break;
                
            case 8:
                out.println(funcion_DAO.listarciudad());
                break;
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
            Logger.getLogger(funcionariocontrol.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(funcionariocontrol.class.getName()).log(Level.SEVERE, null, ex);
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
