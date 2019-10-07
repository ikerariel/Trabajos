/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.ordencompradao;
import DTO.estadodto;
import DTO.ordencompradto;
import DTO.pedidocompradto;
import DTO.usuariosdto;
import DTO.proveedoresdto;
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
public class ordencompradaoimple implements ordencompradao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigo() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(ordenc_nro),0 )+ 1 as codigo  FROM orden_compra;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean insertarOrdenC(ordencompradto dto, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO orden_compra(ordenc_fecha, id_prov, idusuario, pcomp_nro, idestado)\n"
                            + "    VALUES (?::date, ?, ?, ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getOrdenc_fecha());
                    preparedStatement.setObject(2, dto.getId_prov());
                    preparedStatement.setObject(3, dto.getIdusuario());
                    preparedStatement.setObject(4, dto.getPcomp_nro());
                    preparedStatement.setObject(5, dto.getIdestado());
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
                    Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.orden_compra\n"
                            + "   SET ordenc_fecha=?::date, id_prov=?, idusuario=?, pcomp_nro=?, \n"
                            + "       idestado=1\n"
                            + " WHERE ordenc_nro=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getOrdenc_fecha());
                    preparedStatement.setObject(2, dto.getId_prov());
                    preparedStatement.setObject(3, dto.getIdusuario());
                    preparedStatement.setObject(4, dto.getPcomp_nro());
                    preparedStatement.setObject(5, dto.getOrdenc_nro());
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
                    Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean insertarDetalleOrdenC(ordencompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO det_orden_compra( ordenc_nro, idmercaderia, cant_orden, precio_orden)\n"
                    + "    VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getOrdenc_nroD());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getCant_orden());
            preparedStatement.setObject(4, dto.getPrecio_orden());
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
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getcabeseraFiltroOrdenC(Integer idFiltro) {
        ResultSet rs;
        ArrayList<ordencompradto> allOrdenC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT o.ordenc_nro, o.ordenc_fecha, p.prov_nombre, u.usu_nombre, pc.pcomp_nro, e.descripcion\n"
                    + "  FROM orden_compra o, proveedores p, usuarios u, pedido_compra pc, estado e\n"
                    + "  WHERE o.id_prov=p.id_prov AND o.idusuario=u.idusuario AND o.pcomp_nro=pc.pcomp_nro AND o.idestado=e.idestado\n"
                    + "  ORDER BY o.ordenc_nro;";
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
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOrdenC);
    }

    @Override
    public String getdetalleFiltroOrdenC(Integer idFiltro) {
        ResultSet rs;
        ArrayList<ordencompradto> allOrdenD = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT ordenc_nro, idmercaderia, cant_orden, precio_orden\n"
                    + "  FROM det_orden_compra WHERE ordenc_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allOrdenD.add(new ordencompradto(rs.getInt("ordenc_nro"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("cant_orden"),
                        rs.getInt("precio_orden")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOrdenD);
    }

    @Override
    public boolean modificarOrdenC(ordencompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE orden_compra SET ordenc_nro=?, ordenc_fecha=?, id_prov=?,"
                    + " idusuario=?, pcomp_nro=?, idestado=? WHERE ordenc_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getOrdenc_fecha());
            preparedStatement.setObject(2, dto.getId_prov());
            preparedStatement.setObject(3, dto.getIdusuario());
            preparedStatement.setObject(4, dto.getPcomp_nro());
            preparedStatement.setObject(5, dto.getIdestado());
            preparedStatement.setObject(6, dto.getOrdenc_nro());
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
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean confirmarOrdenC(ordencompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE orden_compra SET idestado=? WHERE ordenc_nro=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getOrdenc_nro());

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
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean modificardetallesOrdenC(ordencompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE det_orden_compra SET ordenc_nro=?, idmercaderia=?, "
                    + "cant_orden=?, precio_orden=? WHERE ordenc_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdmercaderia());
            preparedStatement.setObject(2, dto.getCant_orden());
            preparedStatement.setObject(3, dto.getPrecio_orden());
            preparedStatement.setObject(4, dto.getOrdenc_nroD());
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
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarordenComp() {
        ResultSet rs;
        ArrayList<ordencompradto> allOrden = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT o.ordenc_nro, o.ordenc_fecha, p.prov_nombre, u.usu_nombre, pc.pcomp_nro,"
                    + "  e.descri_estado FROM orden_compra o\n"
                    + "  left join proveedores p on o.id_prov=p.id_prov\n"
                    + "  left join usuarios u on o.idusuario=u.idusuario\n"
                    + "  left join pedido_compra pc on o.pcomp_nro=pc.pcomp_nro\n"
                    + "  left join estado e on o.idestado=e.idestado "
                    + "where o.idestado in(1,2) order by o.ordenc_nro desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allOrden.add(new ordencompradto(rs.getInt("ordenc_nro"),
                        rs.getString("ordenc_fecha"),
                        rs.getString("prov_nombre"),
                        rs.getString("usu_nombre"),
                        rs.getInt("pcomp_nro"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOrden);
    }

    @Override
    public String listarusuario() {
        ResultSet rs;
        ArrayList<usuariosdto> allUsua = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idusuario, usu_nombre, usu_clave, idfuncionario,\n"
                    + " idperfil, idsucursal FROM usuarios where idusuario=1";
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
    public String listarMercaderia() {
        ResultSet rs;
        ArrayList<ordencompradto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new ordencompradto(rs.getInt("idmercaderia"),
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
    public String listarestado() {
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
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String listarpedido() {
        ResultSet rs;
        ArrayList<ordencompradto> allPedi = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "select p.pcomp_nro, p.pcomp_fecha, u.usu_nombre, e.descri_estado\n"
                    + " from pedido_compra p\n"
                    + " inner join usuarios u on p.idusuario=u.idusuario\n"
                    + " inner join estado e on p.idestado=e.idestado where e.idestado=2";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allPedi.add(new ordencompradto(rs.getInt("pcomp_nro"),
                        rs.getString("pcomp_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }

    @Override
    public String listarDetallePedido(Integer id) {
        ResultSet rs;
        ArrayList<pedidocompradto> alldetalle = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "select p.pcomp_fecha, u.usu_nombre, e.descri_estado, p.observacion,\n"
                    + "d.idmercaderia,d.cantidad,d.precio, m.codigogenerico, m.mer_descripcion,"
                    + "(select pcomp_nro from orden_compra where pcomp_nro=?) as nropedido\n"
                    + "from pedido_compra p\n"
                    + "inner join det_pedido_compra d on p.pcomp_nro=d.pcomp_nro\n"
                    + "inner join estado e on e.idestado=p.idestado\n"
                    + "inner join usuarios u on u.idusuario=p.idusuario\n"
                    + "inner join mercaderias m on m.idmercaderia=d.idmercaderia\n"
                    + "where p.pcomp_nro=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
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
                        rs.getInt("nropedido"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(alldetalle);
    }

    @Override
    public String listarDetalleOrdenC(Integer id) {
        ResultSet rs;
        ArrayList<ordencompradto> alldetalleOrden = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT o.ordenc_fecha,o.id_prov, p.prov_nombre, u.usu_nombre, pc.pcomp_nro, e.descri_estado,\n"
                    + "       d.idmercaderia, d.cant_orden, d.precio_orden, m.codigogenerico, m.mer_descripcion\n"
                    + "       FROM orden_compra o\n"
                    + "       left join det_orden_compra d on o.ordenc_nro = d.ordenc_nro\n"
                    + "       left join proveedores p on o.id_prov = p.id_prov\n"
                    + "       left join usuarios u on o.idusuario = u.idusuario\n"
                    + "       left join pedido_compra pc on o.pcomp_nro = pc.pcomp_nro\n"
                    + "       left join estado e on o.idestado = e.idestado\n"
                    + "       left join mercaderias m on m.idmercaderia=d.idmercaderia\n"
                    + "       where o.ordenc_nro=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleOrden.add(new ordencompradto(
                        rs.getString("ordenc_fecha"),
                        rs.getString("prov_nombre"),
                        rs.getString("usu_nombre"),
                        rs.getInt("pcomp_nro"),
                        rs.getString("descri_estado"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("cant_orden"),
                        rs.getInt("precio_orden"),
                        rs.getString("codigogenerico"),
                        rs.getInt("id_prov"),
                        rs.getInt("id_prov"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleOrden);
    }

    @Override
    public String listarproveedor() {
        ResultSet rs;
        ArrayList<proveedoresdto> allProvee = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from proveedores";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allProvee.add(new proveedoresdto(rs.getInt("id_prov"),
                        rs.getString("prov_nombre"),
                        rs.getString("prov_direc"),
                        rs.getString("prov_imail"),
                        rs.getString("prov_telf"),
                        rs.getString("prov_ruc")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(pedidocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProvee);
    }

    @Override
    public boolean insertarMercaderia(ordencompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO mercaderias(mer_precio, mer_descripcion,\n"
                    + " codigogenerico) VALUES (?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getMer_precio());
            preparedStatement.setObject(2, dto.getMer_descripcion());
            preparedStatement.setObject(3, dto.getCodigogenerico());
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
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Integer getUltimoCodigoMercaderia() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idmercaderia),0 )+ 1 as codigoM  FROM mercaderias;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigoM");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public boolean deleteOrdencompra(ordencompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "delete from det_orden_compra WHERE ordenc_nro=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getOrdenc_nro());
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
            Logger.getLogger(ordencompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
