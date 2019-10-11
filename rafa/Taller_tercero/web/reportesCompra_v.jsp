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
          File reporFile = new File(application.getRealPath("/reportes/c_pedidocompra.jasper"));
        Map parameters = new HashMap();
        parameters.put("pcomp_nro" , Integer.parseInt(request.getParameter("pcomp_nro")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }if (codigo == 2) {
  
        File reporFile = new File(application.getRealPath("/reportes/libroIvaCompra.jasper"));
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
  
        File reporFile = new File(application.getRealPath("/reportes/facturacompra.jasper"));
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
        
    } if (codigo == 4) {
          File reporFile = new File(application.getRealPath("/reportes/c_ordenCompra.jasper"));
        Map parameters = new HashMap();
        parameters.put("ordenc_nro" , Integer.parseInt(request.getParameter("ordenc_nro")));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }
      
%>

