/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.accesoDAO;
import DAO.pedidocompradao;
import DAOIMPLE.accesoDATOIMPL;
import DAOIMPLE.pedidocompradaoimple;
import DTO.accesoDTO;
import DTO.pedidocompradto;
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
@WebServlet(name = "pedidocompracontrol", urlPatterns = {"/pedidocompracontrol"})
public class pedidocompracontrol extends HttpServlet {

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

        pedidocompradao pedidoDAO = new pedidocompradaoimple();
        pedidocompradto pedidoDTO = new pedidocompradto();

        accesoDAO accDAO = new accesoDATOIMPL();
        accesoDTO accDTO = new accesoDTO();

        switch (opcion) {
            case 1:
                System.out.println("codigo" + pedidoDAO.getUltimoCodigo());
                if (pedidoDAO.getUltimoCodigo() > 0) {
                    out.println(pedidoDAO.getUltimoCodigo());
                }
                break;

            case 2:
                Integer valor = Integer.parseInt(request.getParameter("pdValor"));
                if (valor == 1) {
                    pedidoDTO.setPcomp_fecha(request.getParameter("fechav"));
                    pedidoDTO.setIdusuario(Integer.parseInt(request.getParameter("usuariov")));
                    pedidoDTO.setObservacion(request.getParameter("observacionv"));
                    pedidoDTO.setIddeposito(Integer.parseInt(request.getParameter("depositov")));
                } else if (valor == 2) {
                    pedidoDTO.setPcomp_fecha(request.getParameter("fechav"));
                    pedidoDTO.setIdusuario(Integer.parseInt(request.getParameter("usuariov")));
                    pedidoDTO.setObservacion(request.getParameter("observacionv"));
                    pedidoDTO.setIddeposito(Integer.parseInt(request.getParameter("depositov")));
                    pedidoDTO.setPcomp_nro(Integer.parseInt(request.getParameter("nropedido")));
                }

                if (pedidoDAO.insertar(pedidoDTO, valor)) {
                    out.println("Exitoso");
                }
                break;
            case 3:
                pedidoDTO.setPcomp_nroD(Integer.parseInt(request.getParameter("codigoD")));
                pedidoDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaV")));
                pedidoDTO.setCantidad(Integer.parseInt(request.getParameter("cantidadv")));
                pedidoDTO.setPrecio(Integer.parseInt(request.getParameter("preciov")));
                if (pedidoDAO.insertarDetalle(pedidoDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 4:
                if (pedidoDAO.eliminar(Integer.parseInt(request.getParameter("codigoD")))) {
                    out.println("Exitoso");
                }
                break;
            case 5:
                if (pedidoDAO.getcabeseraFiltro(Integer.parseInt(request.getParameter("codigov"))) != null) {
                    out.println(pedidoDAO.getcabeseraFiltro(Integer.parseInt(request.getParameter("codigov"))));
                }
                break;
            case 6:
                if (pedidoDAO.getdetalleFiltro(Integer.parseInt(request.getParameter("codigoD"))) != null) {
                    out.println(pedidoDAO.getdetalleFiltro(Integer.parseInt(request.getParameter("codigoD"))));
                }
                break;
            case 7:
                out.println(pedidoDAO.listarusuario());
                break;
            case 8:
                out.println(pedidoDAO.listarestado());
                break;
            case 9:
                out.println(pedidoDAO.listarpedido());
                break;
            case 10:
                out.println(pedidoDAO.listarMercaderia());
                break;
            case 11:
                if (pedidoDAO.listarDetalle(Integer.parseInt(request.getParameter("nropedidov"))) != null) {
                    out.println(pedidoDAO.listarDetalle(Integer.parseInt(request.getParameter("nropedidov"))));
                    System.out.println(pedidoDAO.listarDetalle(Integer.parseInt(request.getParameter("nropedidov"))));
                }
                break;
            case 12:
                if (accDAO.getUser(request.getParameter("user")) != null) {
                    out.println(accDAO.getUser(request.getParameter("user")));
                }
                break;
            case 13:
                pedidoDTO.setIdestado(Integer.parseInt(request.getParameter("CDestado")));
                pedidoDTO.setPcomp_nro(Integer.parseInt(request.getParameter("nroPd")));
                if (pedidoDAO.confirmar(pedidoDTO)) {
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
