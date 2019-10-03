/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.pedidoventadao;
import DAOIMPLE.pedidoventadaoimple;
import DTO.pedidoventadto;
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
@WebServlet(name = "pedidosventacontrol", urlPatterns = {"/pedidosventacontrol"})
public class pedidoventascontrol extends HttpServlet {

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
        
        pedidoventadao ventaDAO = new pedidoventadaoimple();
        pedidoventadto ventaDTO = new pedidoventadto();
        
        switch(opcion){
            case 1:
                System.out.println("PVcodigo" + ventaDAO.getUltimoCodigoPedidoV1());
                if (ventaDAO.getUltimoCodigoPedidoV1() > 0) {
                    out.println(ventaDAO.getUltimoCodigoPedidoV1());
                    System.out.println(ventaDAO.getUltimoCodigoPedidoV1());
                }
                break;
            case 2:
                out.println(ventaDAO.ListarEstadoPedidoV2());
                break;
            case 3:
                out.println(ventaDAO.ListarClientePedidoV3());
                break;
            case 4:
                out.println(ventaDAO.ListarMercaderiaPediV4());
                break;
            case 5:
                ventaDTO.setPedi_fecha(request.getParameter("fechaPV"));
                ventaDTO.setPedi_total(Integer.parseInt(request.getParameter("totalPV")));
                ventaDTO.setIdusuario(Integer.parseInt(request.getParameter("usuarioPV")));
                ventaDTO.setIdclientes(Integer.parseInt(request.getParameter("clientePV")));
                ventaDTO.setObsevacion_pven(request.getParameter("observPV"));
                ventaDTO.setIdestado(Integer.parseInt(request.getParameter("estadoPV")));
                ventaDTO.setIddeposito(Integer.parseInt(request.getParameter("depositoPV")));
                if (ventaDAO.insertarPedidoVentas5(ventaDTO)){
                    out.print("Exitoso");
                }
                break;
            case 6:
                ventaDTO.setPedi_ven_nroD(Integer.parseInt(request.getParameter("codigoPV")));
                ventaDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaPV")));
                ventaDTO.setDetpedi_cant(Integer.parseInt(request.getParameter("cantiPV")));
                ventaDTO.setDetpedi_precio(Integer.parseInt(request.getParameter("precioPV")));
                if (ventaDAO.insertarDetallePedidoVentas6(ventaDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 7:
                out.println(ventaDAO.listarpedidoV7());
                break;
            case 8:
                ventaDTO.setIdestado(Integer.parseInt(request.getParameter("CDestado")));
                ventaDTO.setPedi_ven_nro(Integer.parseInt(request.getParameter("nroPd")));       
                if (ventaDAO.confirmarPedidoV8(ventaDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 9:
                if (ventaDAO.listarDetallePedidoV9(Integer.parseInt(request.getParameter("nropedidov"))) != null) {
                    out.println(ventaDAO.listarDetallePedidoV9(Integer.parseInt(request.getParameter("nropedidov"))));
                    System.out.println(ventaDAO.listarDetallePedidoV9(Integer.parseInt(request.getParameter("nropedidov"))));
                }
                break;
            case 10:
                if (ventaDAO.eliminarPedidoV10(Integer.parseInt(request.getParameter("codigoPV")))) {
                    out.println("Exitoso");
                }
                break; 
            case 11:
                ventaDTO.setPedi_total(Integer.parseInt(request.getParameter("totalPV")));
                ventaDTO.setObsevacion_pven(request.getParameter("observPV"));
                ventaDTO.setPedi_ven_nro(Integer.parseInt(request.getParameter("codigoPV")));
                if (ventaDAO.modificarPedidoV11(ventaDTO)){
                    out.print("Exitoso");
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
