/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelControl;

import ModelDAO.NotaRemisionDAO;
import ModelDAOIMPL.NotaRemisionDAOIMPL;
import ModelDTO.NotaRemisionDTO;
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
@WebServlet(name = "NotaRemisionServlet", urlPatterns = {"/NotaRemisionServlet"})
public class NotaRemisionServlet extends HttpServlet {

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

        NotaRemisionDAO REMIDAO = new NotaRemisionDAOIMPL();
        NotaRemisionDTO REMIDTO = new NotaRemisionDTO();

        switch (opcion) {
            case 1:
                System.out.println("codigo" + REMIDAO.getUltimoCodigoNotaRemision1());
                if (REMIDAO.getUltimoCodigoNotaRemision1() > 0) {
                    out.println(REMIDAO.getUltimoCodigoNotaRemision1());
                }
                break;
            case 2:
                out.println(REMIDAO.ListarEstadosNotaRemision2());
                break;

            case 3:
                out.println(REMIDAO.ListarUsuariosNotaRemision3());
                break;

            case 4:
                out.println(REMIDAO.ListarProveedoresNotaRemision4());
                break;

            case 5:
                out.println(REMIDAO.ListarFacturasComprasNotaRemision5());
                break;

            case 6:
                if (REMIDAO.ListarDetFacturasComprasRemision6(Integer.parseInt(request.getParameter("id_FacturacompraC"))) != null) {
                    out.println(REMIDAO.ListarDetFacturasComprasRemision6(Integer.parseInt(request.getParameter("id_FacturacompraC"))));
                    System.out.println(REMIDAO.ListarDetFacturasComprasRemision6(Integer.parseInt(request.getParameter("id_FacturacompraC"))));
                }
                break;

            case 7:
                out.println(REMIDAO.ListarSucursalesNotaRemision7());
                break;

            case 8:
                out.println(REMIDAO.ListarArticulosNotaRemision8());
                break;

            case 9:
                REMIDTO.setNro_notaremi(Integer.parseInt(request.getParameter("Nre_nro")));
                REMIDTO.setObser_notaremi(request.getParameter("Nre_obse"));
                REMIDTO.setId_usuario(Integer.parseInt(request.getParameter("Nre_usuario")));
                REMIDTO.setId_compra(Integer.parseInt(request.getParameter("Nre_factcompra")));
                if (REMIDAO.insertarCabeceraNotaRemision9(REMIDTO)) {
                    out.println("Exitoso");
                }

                break;
            case 10:
                REMIDTO.setId_notaremiD(Integer.parseInt(request.getParameter("codigoRD")));
                REMIDTO.setId_articulo(Integer.parseInt(request.getParameter("idartiRD")));
                REMIDTO.setCantinotaremi(Integer.parseInt(request.getParameter("cantiRD")));
                REMIDTO.setPrecionotaremi(Integer.parseInt(request.getParameter("precioRD")));
                if (REMIDAO.insertarDetNotaRemision11(REMIDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 11:
                out.println(REMIDAO.ListarNotaRemision12());
                break;
            case 12:
                REMIDTO.setId_estado(Integer.parseInt(request.getParameter("CambioEstadoNR")));
                REMIDTO.setId_notaremi(Integer.parseInt(request.getParameter("NotaReCNro")));
                if (REMIDAO.confirmarNotaRemision13(REMIDTO)) {
                    out.println("Exitoso");
                }
                break;
            case 13:
                if (REMIDAO.listarDetNotaRemision14(Integer.parseInt(request.getParameter("nroNotaRe"))) != null) {
                    out.println(REMIDAO.listarDetNotaRemision14(Integer.parseInt(request.getParameter("nroNotaRe"))));
                    System.out.println(REMIDAO.listarDetNotaRemision14(Integer.parseInt(request.getParameter("nroNotaRe"))));
                }
                break;
            case 14:
                REMIDTO.setNro_notaremi(Integer.parseInt(request.getParameter("Nre_nro")));
                REMIDTO.setObser_notaremi(request.getParameter("Nre_obse"));
                REMIDTO.setId_usuario(Integer.parseInt(request.getParameter("Nre_usuario")));
                REMIDTO.setId_compra(Integer.parseInt(request.getParameter("Nre_factcompra")));
                REMIDTO.setId_notaremi(Integer.parseInt(request.getParameter("Nre_notaremi")));
                if (REMIDAO.modificarCabeceraNotaRemision10(REMIDTO)) {
                    out.println("Exitoso");
                }

                break;
            case 15:
                REMIDTO.setId_notaremi(Integer.parseInt(request.getParameter("codNR")));
                if (REMIDAO.deleteNR(REMIDTO)) {
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
