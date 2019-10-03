/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.presupuestocompradao;
import DTO.estadodto;
import DTO.pedidocompradto;
import DTO.presupuestocompradto;
import DTO.proveedoresdto;
import DTO.usuariosdto;
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
public class presupuestocompradaoimple implements presupuestocompradao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoPresuCompra() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idpresupuestocomp),0 )+ 1 as codigo  FROM presupuesto_compra;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String ListarEstadoPresuCompra2() {
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
    public String ListarUsuarioPresuCompra3() {
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsua);
    }

    @Override
    public String ListarProveedorPresuCompras4() {
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProvee);
    }

    @Override
    public String ListarPedidoPresuCompra5() {
        ResultSet rs;
        ArrayList<presupuestocompradto> allPedi = new ArrayList<>();
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
                allPedi.add(new presupuestocompradto(rs.getInt("pcomp_nro"),
                        rs.getString("pcomp_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allPedi);
    }

    @Override
    public String ListarDetallePedidoPresuC6(Integer id) {
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }

        return new Gson().toJson(alldetalle);
    }

    @Override
    public String ListarSucursalPresuCompra7() {
        ResultSet rs;
        ArrayList<presupuestocompradto> allSucursal = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idsucursal, suc_descripcion, idciudad\n"
                    + "  FROM sucursales WHERE idsucursal=1;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allSucursal.add(new presupuestocompradto(rs.getInt("idsucursal"),
                        rs.getString("suc_descripcion"),
                        rs.getInt("idciudad")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allSucursal);
    }

    @Override
    public String ListarMercaderiaPresuCompra8() {
        ResultSet rs;
        ArrayList<presupuestocompradto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new presupuestocompradto(rs.getInt("idmercaderia"),
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allMercaderia);
    }

    @Override
    public boolean insertarCabeceraPresuCompra9(presupuestocompradto dto, Integer Cod) {
        switch (Cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO presupuesto_compra(presup_fecha, presup_cantcuota, presup_monto, \n"
                            + "            presup_intervalo, pcomp_nro, id_prov, idusuario, idestado, tipo_codigo,iddeposito)\n"
                            + "   VALUES (?::date, ?, ?, ?, ?, ?, ?, ?, ?,?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getPresup_fecha());
                    preparedStatement.setObject(2, dto.getPresup_cantcuota());
                    preparedStatement.setObject(3, dto.getPresup_monto());
                    preparedStatement.setObject(4, dto.getPresup_intervalo());
                    preparedStatement.setObject(5, dto.getPcomp_nro());
                    preparedStatement.setObject(6, dto.getId_prov());
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
                    Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.presupuesto_compra\n"
                            + "   SET  presup_fecha=?::date, presup_cantcuota=?, presup_monto=?, \n"
                            + "       presup_intervalo=?, pcomp_nro=?, id_prov=?, idusuario=?, idestado=1, \n"
                            + "       tipo_codigo=?, iddeposito=?\n"
                            + " WHERE idpresupuestocomp=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getPresup_fecha());
                    preparedStatement.setObject(2, dto.getPresup_cantcuota());
                    preparedStatement.setObject(3, dto.getPresup_monto());
                    preparedStatement.setObject(4, dto.getPresup_intervalo());
                    preparedStatement.setObject(5, dto.getPcomp_nro());
                    preparedStatement.setObject(6, dto.getId_prov());
                    preparedStatement.setObject(7, dto.getIdusuario());
                    preparedStatement.setObject(8, dto.getTipo_codigo());
                    preparedStatement.setObject(9, dto.getIddeposito());
                    preparedStatement.setObject(10, dto.getIdpresupuestocomp());
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
                    Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public boolean insertarDetallePresuCompra10(presupuestocompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detalles_presup_compra( idpresupuestocomp, idmercaderia, \n"
                    + "detprescomp_cantidad, detprescomp_precio) VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdpresupuestocompD());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getDetprescomp_cantidad());
            preparedStatement.setObject(4, dto.getDetprescomp_precio());
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String ListarPresuCompra11() {
        ResultSet rs;
        ArrayList<presupuestocompradto> allFacturaC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT pc.idpresupuestocomp, pc.presup_fecha, p.prov_nombre,\n"
                    + "u.usu_nombre, e.descri_estado, t.tipo_descri FROM presupuesto_compra pc\n"
                    + "inner join proveedores p on pc.id_prov = p.id_prov\n"
                    + "inner join usuarios u on pc.idusuario = u.idusuario\n"
                    + "inner join estado e on pc.idestado = e.idestado\n"
                    + "inner join tipo_documentos t on pc.tipo_codigo = t.tipo_codigo where pc.idestado in(1,2)\n"
                    + "order by idpresupuestocomp desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allFacturaC.add(new presupuestocompradto(rs.getInt("idpresupuestocomp"),
                        rs.getString("presup_fecha"),
                        rs.getString("prov_nombre"),
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
    public boolean confirmarPresuCompra12(presupuestocompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE presupuesto_compra SET idestado=? WHERE idpresupuestocomp=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getIdpresupuestocomp());
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetallePresuCompra13(Integer id) {
        ResultSet rs;
        ArrayList<presupuestocompradto> alldetallePresupuesto = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT pc.idpresupuestocomp,pc.tipo_codigo,pc.id_prov, pc.presup_fecha, pc.presup_cantcuota, pc.presup_monto, \n"
                    + "pc.presup_intervalo, pe.pcomp_nro, p.prov_nombre, u.usu_nombre, \n"
                    + "e.descri_estado, t.tipo_descri, d.idmercaderia, d.detprescomp_cantidad, d.detprescomp_precio, \n"
                    + "m.codigogenerico, m.mer_descripcion\n"
                    + "FROM presupuesto_compra pc\n"
                    + "left join pedido_compra pe on pc.pcomp_nro = pe.pcomp_nro\n"
                    + "left join proveedores p on pc.id_prov = p.id_prov\n"
                    + "left join usuarios u on pc.idusuario = u.idusuario\n"
                    + "left join estado e on pc.idestado = e.idestado\n"
                    + "left join tipo_documentos t on pc.tipo_codigo = t.tipo_codigo\n"
                    + "left join detalles_presup_compra d on pc.idpresupuestocomp = d.idpresupuestocomp\n"
                    + "left join mercaderias m on d.idmercaderia = m.idmercaderia\n"
                    + "where pc.idpresupuestocomp=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetallePresupuesto.add(new presupuestocompradto(
                        rs.getInt("idpresupuestocomp"),
                        rs.getInt("tipo_codigo"),
                        rs.getInt("id_prov"),
                        rs.getString("presup_fecha"),
                        rs.getInt("presup_cantcuota"),
                        rs.getInt("presup_monto"),
                        rs.getString("presup_intervalo"),
                        rs.getInt("pcomp_nro"),
                        rs.getString("prov_nombre"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getString("tipo_descri"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("detprescomp_cantidad"),
                        rs.getInt("detprescomp_precio"),
                        rs.getString("codigogenerico"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetallePresupuesto);
    }

    @Override
    public String ListarTipoPresuCompras14() {
        ResultSet rs;
        ArrayList<presupuestocompradto> allTipoC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT tipo_codigo, tipo_descri FROM tipo_documentos";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allTipoC.add(new presupuestocompradto(rs.getInt("tipo_codigo"),
                        rs.getString("tipo_descri")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allTipoC);
    }

    @Override
    public boolean deletepresucompra(presupuestocompradto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detalles_presup_compra\n"
                    + " WHERE idpresupuestocomp =?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdpresupuestocomp());
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
            Logger.getLogger(presupuestocompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
