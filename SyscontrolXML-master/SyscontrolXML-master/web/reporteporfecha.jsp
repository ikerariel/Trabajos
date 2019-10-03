

<%@page import="net.sf.jasperreports.engine.*"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.io.File"%>
<%@page import="Genericos.Conexion"%>
<%@page import="java.sql.*"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.ParseException"%>

<%@page contentType="application/pdf" pageEncoding="UTF-8"%>



<%
    Integer codigo = Integer.parseInt(request.getParameter("codigo"));
    if (codigo == 1) {
        Conexion cn = new Conexion();
        File reporFile = new File(application.getRealPath("/Reportes/pagados.jasper"));
        Map parameters = new HashMap();
        parameters.put("fdesde", request.getParameter("fechadesde"));
        parameters.put("fhasta", request.getParameter("fechahasta"));
         parameters.put("vUser", request.getParameter("user"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    } else if (codigo == 2) {
      Conexion cn = new Conexion();
        File reporFile = new File(application.getRealPath("/Reportes/pagadosrecarga.jasper"));
        Map parameters = new HashMap();
        parameters.put("fdesde", request.getParameter("fechadesde"));
        parameters.put("fhasta", request.getParameter("fechahasta"));
           parameters.put("vUser", request.getParameter("user"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }else if (codigo == 3) {
        Conexion cn = new Conexion();
        File reporFile = new File(application.getRealPath("/Reportes/giros_por_fecha.jasper"));
        Map parameters = new HashMap();
        parameters.put("fdesde", request.getParameter("fechadesde"));
        parameters.put("fhasta", request.getParameter("fechahasta"));
        parameters.put("vUser", request.getParameter("user"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }else if (codigo == 4) {
        Conexion cn = new Conexion();
        File reporFile = new File(application.getRealPath("/Reportes/recargas_por_fecha.jasper"));
        Map parameters = new HashMap();
        parameters.put("fdesde", request.getParameter("fechadesde"));
        parameters.put("fhasta", request.getParameter("fechahasta"));
         parameters.put("vUser", request.getParameter("user"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }else if (codigo == 5) {
        Conexion cn = new Conexion();
        File reporFile = new File(application.getRealPath("/Reportes/pendientesgiro.jasper"));
        Map parameters = new HashMap();
        parameters.put("fdesde", request.getParameter("fechadesde"));
        parameters.put("fhasta", request.getParameter("fechahasta"));
         parameters.put("vUser", request.getParameter("user"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }
    else if (codigo == 6) {
        Conexion cn = new Conexion();
        File reporFile = new File(application.getRealPath("/Reportes/pendientesrecarga.jasper"));
        Map parameters = new HashMap();
        parameters.put("fdesde", request.getParameter("fechadesde"));
        parameters.put("fhasta", request.getParameter("fechahasta"));
         parameters.put("vUser", request.getParameter("user"));
        byte[] bytes = JasperRunManager.runReportToPdf(reporFile.getPath(), parameters, cn.getConexion());
        response.setContentLength(bytes.length);
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.flush();
        outputStream.close();
    }
    


%>


