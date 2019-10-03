/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.pedidocompradao;
import DTO.usuariosdto;
import DTO.estadodto;
import DTO.pedidocompradto;
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
public class pedidocompradaoimple implements pedidocompradao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigo() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(pcomp_nro),0 )+ 1 as codigo  FROM pedido_compra;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean insertar(pedidocompradto dto, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO pedido_compra(pcomp_fecha,  idusuario, idestado,"
                            + " observacion, iddeposito) VALUES (?::date, ?, 1, upper(?), ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getPcomp_fecha());
                    preparedStatement.setObject(2, dto.getIdusuario());
                    preparedStatement.setObject(3, dto.getObservacion());
                    preparedStatement.setObject(4, dto.getIddeposito());
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
                    Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.pedido_compra\n"
                            + "   SET  pcomp_fecha=?::date, idusuario=?, idestado=1, observacion=upper(?), \n"
                            + "       iddeposito=?\n"
                            + " WHERE pcomp_nro=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getPcomp_fecha());
                    preparedStatement.setObject(2, dto.getIdusuario());
                    preparedStatement.setObject(3, dto.getObservacion());
                    preparedStatement.setObject(4, dto.getIddeposito());
                    preparedStatement.setObject(5, dto.getPcomp_nro());
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
                    Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }

        return false;
    }

    @Override
    public boolean insertarDetalle(pedidocompradto dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO det_pedido_compra(\n"
                    + "   pcomp_nro, idmercaderia, cantidad, precio)\n"
                    + "   VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getPcomp_nroD());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getCantidad());
            preparedStatement.setObject(4, dto.getPrecio());
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
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getcabeseraFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<pedidocompradto> allPedidoC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.pcomp_nro, p.pcomp_fecha, u.usu_nombre, e.descripcion, p.observacion\n"
                    + " FROM pedido_compra p, usuarios u, estado e\n"
                    + " WHERE p.idusuario=u.idusuario AND p.idestado=e.idestado\n"
                    + " ORDER BY p.pcomp_nro;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
//            if (rs.next()) {
//                allPedidoC.add(new pedidocompradto(rs.getInt("pcomp_nro"),
//                        rs.getString("pcomp_fecha"),
//                        rs.getString("pcomp_estado"),
//                        rs.getString("observacion"),
//                        rs.getString("usu_nombre"),
//                        rs.getString("descripcion")));
//            } else {
//                return null;
//            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedidoC);

    }

    @Override
    public String getdetalleFiltro(Integer idFiltro) {
        ResultSet rs;
        ArrayList<pedidocompradto> allPedidoD = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT pcomp_nro, idmercaderia, cantidad, precio\n"
                    + "  FROM det_pedido_compra WHERE pcomp_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allPedidoD.add(new pedidocompradto(rs.getInt("pcomp_nro"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("cantidad"),
                        rs.getInt("precio")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedidoD);
    }

    @Override
    public String listarusuario() {
        ResultSet rs;
        ArrayList<usuariosdto> allUsua = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idusuario, usu_nombre, usu_clave, idfuncionario,\n"
                    + " idperfil, idsucursal FROM usuarios where idsucursal=1";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allUsua.add(new usuariosdto(rs.getInt("idusuario"),
                        rs.getString("usu_nombre"),
                        rs.getString("usu_clave"),
                        rs.getInt("idfuncionario"),
                        rs.getInt("idperfil"),
                        rs.getInt("idsucursal")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsua);
    }

    @Override
    public String listarestado() {
        ResultSet rs;
        ArrayList<estadodto> allEstad = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idestado, descripcion\n"
                    + "  FROM estado WHERE idestado=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allEstad.add(new estadodto(rs.getInt("idestado"),
                        rs.getString("descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String listarpedido() {
        ResultSet rs;
        ArrayList<pedidocompradto> allPedi = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "select p.pcomp_nro, p.pcomp_fecha, u.usu_nombre, e.descri_estado, p.observacion\n"
                    + "from pedido_compra p\n"
                    + "inner join usuarios u on p.idusuario=u.idusuario\n"
                    + "inner join estado e on p.idestado=e.idestado and  p.idestado in(2,1) order by p.pcomp_nro desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPedi.add(new pedidocompradto(rs.getInt("pcomp_nro"),
                        rs.getString("pcomp_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getString("observacion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }

    @Override
    public String listarMercaderia() {
        ResultSet rs;
        ArrayList<pedidocompradto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new pedidocompradto(rs.getInt("idmercaderia"),
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
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allMercaderia);
    }

    @Override
    public String listarDetalle(Integer id) {
        ResultSet rs;
        ArrayList<pedidocompradto> alldetalle = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "select p.pcomp_fecha, u.usu_nombre, e.descri_estado, p.observacion,\n"
                    + "d.idmercaderia,d.cantidad,d.precio, m.codigogenerico, m.mer_descripcion\n"
                    + "from pedido_compra p\n"
                    + "inner join det_pedido_compra d on p.pcomp_nro=d.pcomp_nro\n"
                    + "inner join estado e on e.idestado=p.idestado\n"
                    + "inner join usuarios u on u.idusuario=p.idusuario\n"
                    + "inner join mercaderias m on m.idmercaderia=d.idmercaderia\n"
                    + "where p.pcomp_nro=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalle.add(new pedidocompradto(
                        rs.getString("pcomp_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getString("observacion"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("cantidad"),
                        rs.getInt("precio"),
                        rs.getString("codigogenerico"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(alldetalle);
    }

    @Override
    public boolean confirmar(pedidocompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE pedido_compra SET idestado=? WHERE pcomp_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getPcomp_nro());

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
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean eliminar(Integer id) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM det_pedido_compra WHERE pcomp_nro=?;";
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
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
