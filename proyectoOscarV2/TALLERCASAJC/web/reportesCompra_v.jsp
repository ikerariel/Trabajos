<%-- 
    Document   : reportesLlamadas
    Created on : 21/03/2018, 08:46:58 AM
    Author     : Carlos
--%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="Genericos.Conexion"%>
<%@page import="java.sql.*"%>
<%@page contentType="application/pdf" pageEncoding="UTF-8"%>

<%
        Conexion cn = new Conexion();
  Integer codigo = Integer.parseInt(request.getParameter("codigo"));
    if (codigo == 1) {
          File reporFile = new File(application.getRealPath("/reportes/porProveedor.jasper"));
        Map parameters = new HashMap();
       parameters.put("fdesde", request.getParameter("fdesde"));
        parameters.put("fhasta", request.getParameter("fhasta"));
         parameters.put("vUser", request.getParameter("vUser"));
         parameters.put("id_proveedor", Integer.parseInt(request.getParameter("id_proveedor")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }if (codigo == 2) {
  
        File reporFile = new File(application.getRealPath("/reportes/ivaCompra.jasper"));
        Map parameters = new HashMap();
        parameters.put("fdesde", request.getParameter("fdesde"));
        parameters.put("fhasta", request.getParameter("fhasta"));
         parameters.put("vUser", request.getParameter("vUser"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
        
    }if (codigo == 3) {
  
        File reporFile = new File(application.getRealPath("/reportes/porEstadoCompra.jasper"));
        Map parameters = new HashMap();
        parameters.put("fdesde", request.getParameter("fdesde"));
        parameters.put("fhasta", request.getParameter("fhasta"));
         parameters.put("vUser", request.getParameter("vUser"));
           parameters.put("id_estado", Integer.parseInt(request.getParameter("id_estado")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
        
    } if (codigo == 4) {
          File reporFile = new File(application.getRealPath("/reportes/facturaCompra.jasper"));
        Map parameters = new HashMap();
        parameters.put("id_compra" , Integer.parseInt(request.getParameter("id_compra")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }if (codigo == 5) {
          File reporFile = new File(application.getRealPath("/reportes/remisionCompra.jasper"));
        Map parameters = new HashMap();
        parameters.put("id_notaremi" , Integer.parseInt(request.getParameter("id_notaremi")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }
      
%>

