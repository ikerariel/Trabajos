<%-- 
    Document   : reportesgenericos
    Created on : 12/07/2019, 08:03:51 AM
    Author     : Carlos
  parameters.put("idapertura", Integer.parseInt(request.getParameter("idapertura")));
--%>
<%
    HttpSession sessionActiva = request.getSession();
    if (sessionActiva.getAttribute("sessionON") == null) {
        response.sendRedirect("/syscontrol/nologin.jsp");
    }
%>

<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="Genericos.Conexion"%>
<%@page import="java.sql.*"%>
<%@page contentType="application/pdf" pageEncoding="UTF-8"%>
<%
    Conexion cn = new Conexion();
    Integer codigo = Integer.parseInt(request.getParameter("vcodigo"));
    if (codigo == 1) {
        File reporFile = new File(application.getRealPath("/Reportes/trx.jasper"));
        Map parameters = new HashMap();
        parameters.put("iddetallerecargas", Integer.parseInt(request.getParameter("id")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    } else if (codigo == 2) {
        File reporFile = new File(application.getRealPath("/Reportes/trx_giro.jasper"));
        Map parameters = new HashMap();
        parameters.put("iddetallegiro", Integer.parseInt(request.getParameter("id")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    } else if (codigo == 3) {
        File reporFile = new File(application.getRealPath("/Reportes/trx_pago.jasper"));
        Map parameters = new HashMap();
        parameters.put("idpago", Integer.parseInt(request.getParameter("id")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    } else if (codigo == 4) {
        File reporFile = new File(application.getRealPath("/Reportes/trx_pagorecarga.jasper"));
        Map parameters = new HashMap();
        parameters.put("idpagorecarga", Integer.parseInt(request.getParameter("id")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }

%>