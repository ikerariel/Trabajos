/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import Genericos.Conexion;
import Genericos.mierror;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author naty
 */
public class buscadorimple {
    
    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement ps;
    private ResultSet rs;
    private Conexion conexion;
    
    public String listarCaja() throws mierror {
        String html = "";
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idcaja, caja_descripcion FROM cajas ORDER BY idcaja;";
            ps = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            html += ("<table class=\"table\" id=\"tabAllCaja\">");
            html += ("<thead>");
            html += ("<tr>");
            html += ("<th>CODIGO</th>");
            html += ("<th>NOMBRE</th>");
            html += ("</tr>");
            html += ("</thead>");
            html += ("<tbody class=\"buscar\">");
            while (rs.next()) {
                html += ("<tr>");
                html += ("        <th>" + rs.getInt("idcaja") + "</th>");
                html += ("        <th>" + rs.getString("caja_descripcion") + "</th>");
                html += ("</tr>");
            }
            html += ("</tbody>");
            html += ("</table>");
        } catch (SQLException ex) {
            MsmError = "Mi mensaje propiio ";
            throw new mierror(MsmError, ex);
        }
        return html;
    }

    public String listarCiudad() throws mierror {
        String html = "";
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idciudad, ciu_descripcion FROM ciudades ORDER BY idciudad;";
            ps = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            html += ("<table class=\"table\" id=\"tabAllCiudad\">");
            html += ("<thead>");
            html += ("<tr>");
            html += ("<th>CODIGO</th>");
            html += ("<th>NOMBRE</th>");
            html += ("</tr>");
            html += ("</thead>");
            html += ("<tbody class=\"buscar\">");
            while (rs.next()) {
                html += ("<tr>");
                html += ("        <th>" + rs.getInt("idciudad") + "</th>");
                html += ("        <th>" + rs.getString("ciu_descripcion") + "</th>");
                html += ("</tr>");
            }
            html += ("</tbody>");
            html += ("</table>");
        } catch (SQLException ex) {
            MsmError = "Mi mensaje propiio ";
            throw new mierror(MsmError, ex);
        }
        return html;
    }
    
    public String listarSucursal() throws mierror {
        String html = "";
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idsucursal, suc_descripcion, idciudad FROM sucursales ORDER BY idsucursal;";
            ps = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            html += ("<table class=\"table\" id=\"tabAllSucursal\">");
            html += ("<thead>");
            html += ("<tr>");
            html += ("<th>CODIGO</th>");
            html += ("<th>NOMBRE</th>");
            html += ("<th>CIUDAD</th>");
            html += ("</tr>");
            html += ("</thead>");
            html += ("<tbody class=\"buscar\">");
            while (rs.next()) {
                html += ("<tr>");
                html += ("        <th>" + rs.getInt("idsucursal") + "</th>");
                html += ("        <th>" + rs.getString("suc_descripcion") + "</th>");
                html += ("        <th>" + rs.getInt("idciudad") + "</th>");
                html += ("</tr>");
            }
            html += ("</tbody>");
            html += ("</table>");
        } catch (SQLException ex) {
            MsmError = "Mi mensaje propiio ";
            throw new mierror(MsmError, ex);
        }
        return html;
    }
    public String listarCargos() throws mierror {
        String html = "";
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idcargo, car_descripcion FROM cargos ORDER BY idcargo;";
            ps = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = ps.executeQuery();
            html += ("<table class=\"table\" id=\"tabAllCargos\">");
            html += ("<thead>");
            html += ("<tr>");
            html += ("<th>CODIGO</th>");
            html += ("<th>NOMBRE</th>");
            html += ("</tr>");
            html += ("</thead>");
            html += ("<tbody class=\"buscar\">");
            while (rs.next()) {
                html += ("<tr>");
                html += ("        <th>" + rs.getInt("idcargo") + "</th>");
                html += ("        <th>" + rs.getString("car_descripcion") + "</th>");
                html += ("</tr>");
            }
            html += ("</tbody>");
            html += ("</table>");
        } catch (SQLException ex) {
            MsmError = "Mi mensaje propiio ";
            throw new mierror(MsmError, ex);
        }
        return html;
    }
}
