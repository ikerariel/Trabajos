/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import DAO.presupuestoventadao;
import DAOIMPLE.presupuestoventadaoimple;
import DTO.presupuestoventadto;
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
@WebServlet(name = "presupuestoventacontrol", urlPatterns = {"/presupuestoventacontrol"})
public class presupuestoventacontrol extends HttpServlet {

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
        
        presupuestoventadao presuvDAO = new presupuestoventadaoimple();
        presupuestoventadto presuvDTO = new presupuestoventadto();
        
        switch(opcion){
            case 1:
                System.out.println("codigo" + presuvDAO.getUltimoCodigoPresuVenta1());
                if (presuvDAO.getUltimoCodigoPresuVenta1() > 0) {
                    out.println(presuvDAO.getUltimoCodigoPresuVenta1());
                }
                break;
            case 2:
                out.println(presuvDAO.ListarEstadoPresuVenta2());
                break;
            case 3:
                out.println(presuvDAO.ListarClientePresuVenta3());
                break;
            case 4:
                out.println(presuvDAO.ListarPedidoPresuVenta4());
                break;
            case 5:
                if (presuvDAO.ListarDetallePedidoPresuV5(Integer.parseInt(request.getParameter("nroPedidoPresuV"))) != null) {
                    out.println(presuvDAO.ListarDetallePedidoPresuV5(Integer.parseInt(request.getParameter("nroPedidoPresuV"))));
                    System.out.println(presuvDAO.ListarDetallePedidoPresuV5(Integer.parseInt(request.getParameter("nroPedidoPresuV"))));
                }
                break;
            case 6:
                out.println(presuvDAO.ListarTipoPresuVenta6());
                break;    
            case 7:
                out.println(presuvDAO.ListarMercaderiaPresuVenta7());
                break;
            case 8:
                presuvDTO.setPres_fecha(request.getParameter("Presuventafecha"));
                presuvDTO.setPres_cantcuota(Integer.parseInt(request.getParameter("Presuventacuota")));
                presuvDTO.setPres_monto(Integer.parseInt(request.getParameter("Presuventamonto")));
                presuvDTO.setPres_intervalo(request.getParameter("Presuventaintervalo"));
                presuvDTO.setPedi_ven_nro(Integer.parseInt(request.getParameter("PresuPedidoventa")));
                presuvDTO.setIdclientes(Integer.parseInt(request.getParameter("Presuventacli")));
                presuvDTO.setIdusuario(Integer.parseInt(request.getParameter("Presuventausua")));
                presuvDTO.setIdestado(Integer.parseInt(request.getParameter("Presuventaestado")));
                presuvDTO.setTipo_codigo(Integer.parseInt(request.getParameter("Presuventatipo")));               
                presuvDTO.setIddeposito(Integer.parseInt(request.getParameter("Presuventadepos")));               
                if (presuvDAO.insertarCabeceraPresuVenta8(presuvDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 9:
                presuvDTO.setIdpresupuestoventaD(Integer.parseInt(request.getParameter("codigoP")));
                presuvDTO.setIdmercaderia(Integer.parseInt(request.getParameter("idmercaP")));
                presuvDTO.setDetpresvent_cantidad(Integer.parseInt(request.getParameter("cantiP")));
                presuvDTO.setDetpresvent_precio(Integer.parseInt(request.getParameter("precioP")));
                if (presuvDAO.insertarDetallePresuVenta9(presuvDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 10:
                if (presuvDAO.listarDetallePresuVenta10(Integer.parseInt(request.getParameter("nroPresuVenta"))) != null) {
                    out.println(presuvDAO.listarDetallePresuVenta10(Integer.parseInt(request.getParameter("nroPresuVenta"))));
                    System.out.println(presuvDAO.listarDetallePresuVenta10(Integer.parseInt(request.getParameter("nroPresuVenta"))));
                }
                break;
            case 11:
                out.println(presuvDAO.ListarPresuVenta11());
                break; 
            case 12:
                if (presuvDAO.eliminarPresuVenta12(Integer.parseInt(request.getParameter("codigoP")))) {
                    out.println("Exitoso");
                }
                break; 
            case 13:
                presuvDTO.setPres_cantcuota(Integer.parseInt(request.getParameter("cancuotaP")));
                presuvDTO.setPres_monto(Integer.parseInt(request.getParameter("montoP")));
                presuvDTO.setPres_intervalo(request.getParameter("intervaloP"));
                presuvDTO.setTipo_codigo(Integer.parseInt(request.getParameter("tipoP")));
                presuvDTO.setIdpresupuestoventa(Integer.parseInt(request.getParameter("codigoP")));
                if (presuvDAO.modificarPresuVenta13(presuvDTO)){
                    out.print("Exitoso");
                }
                break;
            case 14:
                presuvDTO.setIdestado(Integer.parseInt(request.getParameter("CambioEstadoPresuV")));
                presuvDTO.setIdpresupuestoventa(Integer.parseInt(request.getParameter("PresupuestoVNro")));
                if (presuvDAO.confirmarPresuVenta14(presuvDTO)) {
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
