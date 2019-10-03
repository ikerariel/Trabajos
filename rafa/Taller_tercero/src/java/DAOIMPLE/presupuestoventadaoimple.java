/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.presupuestoventadao;
import DTO.clientesdto;
import DTO.estadodto;
import DTO.pedidoventadto;
import DTO.presupuestoventadto;
import Genericos.Conexion;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafel
 */
public class presupuestoventadaoimple implements presupuestoventadao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoPresuVenta1() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idpresupuestoventa),0 )+ 1 as presuVcodigo  FROM presupuesto_ventas;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("presuVcodigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadoPresuVenta2() {
        ResultSet rs;
        ArrayList<estadodto> allEstad = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idestado, descri_estado\n"
                    + "  FROM estado WHERE idestado=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstad.add(new estadodto(rs.getInt("idestado"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String ListarClientePresuVenta3() {
        ResultSet rs;
        ArrayList<clientesdto> allCliente = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT c.idclientes, (c.cli_ruc||'-'||c.cv) as cli_ruc, c.cli_razonsocial, c.cli_telefono, \n"
                    + "                    c.cli_direccion, c.cli_correo, ci.idciudad, ci.ciu_descripcion\n"
                    + "                    FROM clientes c, ciudades ci\n"
                    + "                    WHERE c.idciudad = ci.idciudad\n"
                    + "                    ORDER BY c.idclientes;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCliente.add(new clientesdto(rs.getInt("idclientes"),
                        rs.getString("cli_ruc"),
                        rs.getString("cli_razonsocial"),
                        rs.getString("cli_telefono"),
                        rs.getString("cli_direccion"),
                        rs.getString("cli_correo"),
                        rs.getInt("idciudad"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allCliente);
    }

    @Override
    public String ListarPedidoPresuVenta4() {
        ResultSet rs;
        ArrayList<presupuestoventadto> allPedi = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.pedi_ven_nro, p.pedi_fecha, u.usu_nombre, c.idclientes, \n"
                    + "c.cli_razonsocial, e.descri_estado FROM pedido_venta p\n"
                    + "inner join usuarios u on p.idusuario=u.idusuario\n"
                    + "inner join clientes c on p.idclientes=c.idclientes\n"
                    + "inner join estado e on p.idestado=e.idestado where e.idestado=2";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPedi.add(new presupuestoventadto(rs.getInt("pedi_ven_nro"),
                        rs.getString("pedi_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getInt("idclientes"),
                        rs.getString("cli_razonsocial"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }

    @Override
    public String ListarDetallePedidoPresuV5(Integer id) {
        ResultSet rs;
        ArrayList<pedidoventadto> alldetalle = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.pedi_fecha, p.pedi_total, u.usu_nombre, c.cli_razonsocial, \n"
                    + "p.obsevacion_pven, e.descri_estado, d.dep_descripcion, v.idmercaderia, v.detpedi_cant, \n"
                    + "v.detpedi_precio, m.codigogenerico, m.mer_descripcion FROM pedido_venta p\n"
                    + "inner join usuarios u on p.idusuario=u.idusuario\n"
                    + "inner join clientes c on p.idclientes=c.idclientes\n"
                    + "inner join estado e on p.idestado=e.idestado\n"
                    + "inner join depositos d on p.iddeposito=d.iddeposito\n"
                    + "inner join det_ped_venta v on p.pedi_ven_nro=v.pedi_ven_nro\n"
                    + "inner join mercaderias m on m.idmercaderia=v.idmercaderia\n"
                    + "where p.pedi_ven_nro=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalle.add(new pedidoventadto(
                        rs.getString("pedi_fecha"),
                        rs.getInt("pedi_total"),
                        rs.getString("usu_nombre"),
                        rs.getString("cli_razonsocial"),
                        rs.getString("obsevacion_pven"),
                        rs.getString("descri_estado"),
                        rs.getString("dep_descripcion"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("detpedi_cant"),
                        rs.getInt("detpedi_precio"),
                        rs.getString("codigogenerico"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(alldetalle);
    }

    @Override
    public String ListarTipoPresuVenta6() {
        ResultSet rs;
        ArrayList<presupuestoventadto> allTipoC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT tipo_codigo, tipo_descri FROM tipo_documentos";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allTipoC.add(new presupuestoventadto(rs.getInt("tipo_codigo"),
                        rs.getString("tipo_descri")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allTipoC);
    }

    @Override
    public String ListarMercaderiaPresuVenta7() {
        ResultSet rs;
        ArrayList<presupuestoventadto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new presupuestoventadto(rs.getInt("idmercaderia"),
                        rs.getInt("mer_costo"),
                        rs.getInt("mer_precio"),
                        rs.getString("mer_descripcion"),
                        rs.getInt("idcategoria"),
                        rs.getInt("idmarca"),
                        rs.getInt("idprocedencia"),
                        rs.getInt("idimpuesto"),
                        rs.getString("codigogenerico")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allMercaderia);
    }

    @Override
    public boolean insertarCabeceraPresuVenta8(presupuestoventadto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO presupuesto_ventas( pres_fecha, pres_cantcuota, pres_monto, pres_intervalo, \n"
                    + "    pedi_ven_nro, idclientes, idusuario, idestado, tipo_codigo, iddeposito)\n"
                    + "    VALUES (?::date, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getPres_fecha());
            preparedStatement.setObject(2, dto.getPres_cantcuota());
            preparedStatement.setObject(3, dto.getPres_monto());
            preparedStatement.setObject(4, dto.getPres_intervalo());
            preparedStatement.setObject(5, dto.getPedi_ven_nro());
            preparedStatement.setObject(6, dto.getIdclientes());
            preparedStatement.setObject(7, dto.getIdusuario());
            preparedStatement.setObject(8, dto.getIdestado());
            preparedStatement.setObject(9, dto.getTipo_codigo());
            preparedStatement.setObject(10, dto.getIddeposito());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetallePresuVenta9(presupuestoventadto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detalles_presup_venta(idpresupuestoventa, idmercaderia, detpresvent_cantidad, \n"
                    + "detpresvent_precio) VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdpresupuestoventaD());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getDetpresvent_cantidad());
            preparedStatement.setObject(4, dto.getDetpresvent_precio());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetallePresuVenta10(Integer id) {
        ResultSet rs;
        ArrayList<presupuestoventadto> alldetallePresupuesto = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT pr.idpresupuestoventa, pr.pres_fecha, pr.pres_cantcuota, pr.pres_monto, pr.pres_intervalo, \n"
                    + "p.pedi_ven_nro, c.cli_razonsocial, u.usu_nombre, e.descri_estado, t.tipo_codigo, t.tipo_descri, d.dep_descripcion,\n"
                    + "de.idmercaderia, de.detpresvent_cantidad, de.detpresvent_precio,  m.codigogenerico, m.mer_descripcion\n"
                    + "FROM presupuesto_ventas pr\n"
                    + "inner join pedido_venta p on pr.pedi_ven_nro = p.pedi_ven_nro\n"
                    + "inner join clientes c on pr.idclientes = c.idclientes\n"
                    + "inner join usuarios u on pr.idusuario = u.idusuario\n"
                    + "inner join estado e on pr.idestado = e.idestado\n"
                    + "inner join tipo_documentos t on pr.tipo_codigo = t.tipo_codigo\n"
                    + "inner join depositos d on pr.iddeposito = d.iddeposito\n"
                    + "inner join detalles_presup_venta de on pr.idpresupuestoventa = de.idpresupuestoventa\n"
                    + "inner join mercaderias m on de.idmercaderia = m.idmercaderia where pr.idpresupuestoventa=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetallePresupuesto.add(new presupuestoventadto(
                        rs.getInt("idpresupuestoventa"),
                        rs.getString("pres_fecha"),
                        rs.getInt("pres_cantcuota"),
                        rs.getInt("pres_monto"),
                        rs.getString("pres_intervalo"),
                        rs.getInt("pedi_ven_nro"),
                        rs.getString("cli_razonsocial"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getInt("tipo_codigo"),
                        rs.getString("tipo_descri"),
                        rs.getString("dep_descripcion"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("detpresvent_cantidad"),
                        rs.getInt("detpresvent_precio"),
                        rs.getString("codigogenerico"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetallePresupuesto);
    }

    @Override
    public String ListarPresuVenta11() {
        ResultSet rs;
        ArrayList<presupuestoventadto> allFacturaC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT pr.idpresupuestoventa, pr.pres_fecha, c.cli_razonsocial, u.usu_nombre, e.descri_estado, t.tipo_descri\n"
                    + "FROM presupuesto_ventas pr\n"
                    + "inner join clientes c on pr.idclientes = c.idclientes\n"
                    + "inner join usuarios u on pr.idusuario = u.idusuario\n"
                    + "inner join estado e on pr.idestado = e.idestado\n"
                    + "inner join tipo_documentos t on pr.tipo_codigo = t.tipo_codigo\n"
                    + "order by idpresupuestoventa desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allFacturaC.add(new presupuestoventadto(rs.getInt("idpresupuestoventa"),
                        rs.getString("pres_fecha"),
                        rs.getString("cli_razonsocial"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getString("tipo_descri")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allFacturaC);
    }

    @Override
    public boolean eliminarPresuVenta12(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM detalles_presup_venta WHERE idpresupuestoventa=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, id);
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();

        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarPresuVenta13(presupuestoventadto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE presupuesto_ventas SET pres_cantcuota=?, pres_monto=?, "
                    + "pres_intervalo=?, tipo_codigo=? WHERE idpresupuestoventa=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getPres_cantcuota());
            preparedStatement.setObject(2, dto.getPres_monto());
            preparedStatement.setObject(3, dto.getPres_intervalo());
            preparedStatement.setObject(4, dto.getTipo_codigo());
            preparedStatement.setObject(5, dto.getIdpresupuestoventa());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean confirmarPresuVenta14(presupuestoventadto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE presupuesto_ventas SET idestado=? WHERE idpresupuestoventa=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getIdpresupuestoventa());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                System.out.println("Comit() Realizado");
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
            conexion.desConectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(presupuestoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
