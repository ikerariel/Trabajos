/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.pedidoventadao;
import DTO.clientesdto;
import DTO.estadodto;
import DTO.pedidoventadto;
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
public class pedidoventadaoimple implements pedidoventadao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoPedidoV1() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(pedi_ven_nro),0 )+ 1 as PVcodigo FROM pedido_venta;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("PVcodigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadoPedidoV2() {
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String ListarClientePedidoV3() {
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allCliente);
    }

    @Override
    public String ListarMercaderiaPediV4() {
        ResultSet rs;
        ArrayList<pedidoventadto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new pedidoventadto(rs.getInt("idmercaderia"),
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
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allMercaderia);
    }

    @Override
    public boolean insertarPedidoVentas5(pedidoventadto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO pedido_venta( pedi_fecha, pedi_total, idusuario, idclientes, \n"
                    + "obsevacion_pven, idestado, iddeposito) VALUES (?::date, ?, ?, ?,  ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getPedi_fecha());
            preparedStatement.setObject(2, dto.getPedi_total());
            preparedStatement.setObject(3, dto.getIdusuario());
            preparedStatement.setObject(4, dto.getIdclientes());
            preparedStatement.setObject(5, dto.getObsevacion_pven());
            preparedStatement.setObject(6, dto.getIdestado());
            preparedStatement.setObject(7, dto.getIddeposito());
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
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean insertarDetallePedidoVentas6(pedidoventadto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO det_ped_venta(pedi_ven_nro, idmercaderia, detpedi_cant, detpedi_precio)\n"
                    + "    VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getPedi_ven_nroD());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getDetpedi_cant());
            preparedStatement.setObject(4, dto.getDetpedi_precio());
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
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarpedidoV7() {
        ResultSet rs;
        ArrayList<pedidoventadto> allPedi = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.pedi_ven_nro, p.pedi_fecha, p.pedi_total, u.usu_nombre, c.cli_razonsocial, \n"
                    + "p.obsevacion_pven, e.descri_estado, d.dep_descripcion FROM pedido_venta p\n"
                    + "inner join usuarios u on p.idusuario=u.idusuario\n"
                    + "inner join clientes c on p.idclientes=c.idclientes\n"
                    + "inner join estado e on p.idestado=e.idestado\n"
                    + "inner join depositos d on p.iddeposito=d.iddeposito order by p.pedi_ven_nro desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPedi.add(new pedidoventadto(rs.getInt("pedi_ven_nro"),
                        rs.getString("pedi_fecha"),
                        rs.getInt("pedi_total"),
                        rs.getString("usu_nombre"),
                        rs.getString("cli_razonsocial"),
                        rs.getString("obsevacion_pven"),
                        rs.getString("descri_estado"),
                        rs.getString("dep_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }

    @Override
    public boolean confirmarPedidoV8(pedidoventadto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE pedido_venta SET idestado=? WHERE pedi_ven_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getPedi_ven_nro());

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
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String listarDetallePedidoV9(Integer id) {
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
    public boolean eliminarPedidoV10(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM det_ped_venta WHERE pedi_ven_nro=?;";
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
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificarPedidoV11(pedidoventadto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE pedido_venta SET pedi_total=?, obsevacion_pven=? WHERE pedi_ven_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getPedi_total());
            preparedStatement.setObject(2, dto.getObsevacion_pven());
            preparedStatement.setObject(3, dto.getPedi_ven_nro());
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
            Logger.getLogger(pedidoventadaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
