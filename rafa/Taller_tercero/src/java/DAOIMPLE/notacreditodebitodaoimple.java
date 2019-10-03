/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.notacreditodebitodao;
import DTO.estadodto;
import DTO.facturacompradto;
import DTO.notacreditodebitodto;
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
public class notacreditodebitodaoimple implements notacreditodebitodao {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public Integer getUltimoCodigoNota1() {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idcred_deb),0 )+ 1 as codigo  FROM nota_credito_debito;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String listarEstadoNota2() {
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
            Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allEstad);
    }

    @Override
    public String listarUsuarioNota3() {
        ResultSet rs;
        ArrayList<usuariosdto> allUsua = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idusuario, usu_nombre, usu_clave, idfuncionario,\n"
                    + " idperfil, idsucursal FROM usuarios where idusuario=1;";
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
            Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allUsua);
    }

    @Override
    public String listarProveedorNota4() {
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
            Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allProvee);
    }

    @Override
    public String listarfacturaNota5() {
        ResultSet rs;
        ArrayList<facturacompradto> allFacturaC = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.idcompra, f.comp_nrofact, f.comp_fecha, t.tipo_descri,\n"
                    + "p.prov_nombre, u.usu_nombre, e.descri_estado\n"
                    + "FROM factura_compra f\n"
                    + "inner join tipo_documentos t on f.tipo_codigo = t.tipo_codigo\n"
                    + "inner join proveedores p on f.id_prov = p.id_prov\n"
                    + "inner join usuarios u on f.idusuario = u.idusuario\n"
                    + "inner join estado e on f.idestado = e.idestado\n"
                    + "where e.idestado = 2";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allFacturaC.add(new facturacompradto(rs.getInt("idcompra"),
                        rs.getString("comp_nrofact"),
                        rs.getString("comp_fecha"),
                        rs.getString("tipo_descri"),
                        rs.getString("prov_nombre"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allFacturaC);
    }

    @Override
    public String listarDetalleFactura6(Integer id) {
        ResultSet rs;
        ArrayList<facturacompradto> alldetalleFactura = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT f.idcompra,f.id_prov,f.tipo_codigo, f.comp_cantcuota, f.comp_monto, f.comp_nrofact, f.comp_intervalo, \n"
                    + "       f.comp_fecha, p.prov_nombre, u.usu_nombre, e.descri_estado, \n"
                    + "       o.ordenc_nro, d.idmercaderia, d.detfact_cantidad, d.detfact_precio, m.codigogenerico,\n"
                    + "       m.mer_descripcion FROM factura_compra f\n"
                    + "       left join proveedores p on f.id_prov = p.id_prov\n"
                    + "       left join usuarios u on f.idusuario = u.idusuario\n"
                    + "       left join estado e on f.idestado = e.idestado\n"
                    + "       left join orden_compra o on f.ordenc_nro = o.ordenc_nro\n"
                    + "       left join det_factura_compra d on f.idcompra = d.idcompra\n"
                    + "       left join mercaderias m on d.idmercaderia = m.idmercaderia\n"
                    + "       where f.idcompra=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleFactura.add(new facturacompradto(
                        rs.getInt("idcompra"),
                        rs.getInt("comp_cantcuota"),
                        rs.getInt("comp_monto"),
                        rs.getString("comp_nrofact"),
                        rs.getString("comp_intervalo"),
                        rs.getString("comp_fecha"),
                        rs.getString("comp_fecha"),
                        rs.getString("prov_nombre"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getInt("ordenc_nro"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("detfact_cantidad"),
                        rs.getInt("detfact_precio"),
                        rs.getString("codigogenerico"),
                        rs.getInt("id_prov"),
                        rs.getInt("tipo_codigo"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleFactura);
    }

    @Override
    public String listarMercaderiaNota7() {
        ResultSet rs;
        ArrayList<notacreditodebitodto> allMercaderia = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "Select *from mercaderias";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMercaderia.add(new notacreditodebitodto(rs.getInt("idmercaderia"),
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
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allMercaderia);
    }

    @Override
    public boolean insertarCabeceraNota8(notacreditodebitodto dto, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO nota_credito_debito(nocred_tipo, nocred_fecha, nocred_motivo, idcompra, \n"
                            + " idusuario, idestado) VALUES (?, ?::date, ?, ?,?, 1);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getNocred_tipo());
                    preparedStatement.setObject(2, dto.getNocred_fecha());
                    preparedStatement.setObject(3, dto.getNocred_motivo());
                    preparedStatement.setObject(4, dto.getIdcompra());
                    preparedStatement.setObject(5, dto.getIdusuario());
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
                    Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.nota_credito_debito\n"
                            + "   SET nocred_tipo=?, nocred_fecha=?::date, nocred_motivo=?, \n"
                            + "       idcompra=?, idusuario=?, idestado=1\n"
                            + " WHERE idcred_deb=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getNocred_tipo());
                    preparedStatement.setObject(2, dto.getNocred_fecha());
                    preparedStatement.setObject(3, dto.getNocred_motivo());
                    preparedStatement.setObject(4, dto.getIdcompra());
                    preparedStatement.setObject(5, dto.getIdusuario());
                    preparedStatement.setObject(6, dto.getIdcred_deb());
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
                    Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

        }

        return false;
    }

    @Override
    public boolean insertarDetalleNota9(notacreditodebitodto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detalle_cred_deb(idcred_deb, idmercaderia, cred_deb_cantidad,\n"
                    + " cred_deb_precio) VALUES (?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdcred_debD());
            preparedStatement.setObject(2, dto.getIdmercaderia());
            preparedStatement.setObject(3, dto.getCred_deb_cantidad());
            preparedStatement.setObject(4, dto.getCred_deb_precio());
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
            Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarNota10() {
        ResultSet rs;
        ArrayList<notacreditodebitodto> allNotacre = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT n.idcred_deb, n.nocred_tipo, n.nocred_fecha, \n"
                    + "       u.usu_nombre, e.descri_estado, p.prov_nombre FROM nota_credito_debito n\n"
                    + "       left join usuarios u on n.idusuario = u.idusuario\n"
                    + "       left join proveedores p on n.id_prov = p.id_prov\n"
                    + "       left join estado e on n.idestado = e.idestado where e.idestado in(1,2) order by idcred_deb desc;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allNotacre.add(new notacreditodebitodto(rs.getInt("idcred_deb"),
                        rs.getString("nocred_tipo"),
                        rs.getString("nocred_fecha"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getString("prov_nombre")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allNotacre);
    }

    @Override
    public boolean confirmarNota11(notacreditodebitodto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE nota_credito_debito SET idestado=? WHERE idcred_deb=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdestado());
            preparedStatement.setObject(2, dto.getIdcred_deb());
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
            Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String listarDetalleNota12(Integer id) {
        ResultSet rs;
        ArrayList<notacreditodebitodto> alldetalleNota = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT n.idcred_deb, n.nocred_tipo, n.nocred_fecha, n.nocred_motivo, f.idcompra,\n"
                    + "                     u.usu_nombre, e.descri_estado, p.prov_nombre, d.idmercaderia, d.cred_deb_cantidad,\n"
                    + "                      d.cred_deb_precio, m.codigogenerico, m.mer_descripcion\n"
                    + "                      FROM nota_credito_debito n\n"
                    + "                      left join factura_compra f on n.idcompra = f.idcompra\n"
                    + "                      left join proveedores p on n.id_prov = p.id_prov\n"
                    + "                      left join usuarios u on n.idusuario = u.idusuario\n"
                    + "                      left join estado e on n.idestado = e.idestado\n"
                    + "                      left join detalle_cred_deb d on n.idcred_deb = d.idcred_deb\n"
                    + "                      left join mercaderias m on d.idmercaderia = m.idmercaderia\n"
                    + "                      where n.idcred_deb=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetalleNota.add(new notacreditodebitodto(
                        rs.getInt("idcred_deb"),
                        rs.getString("nocred_tipo"),
                        rs.getString("nocred_fecha"),
                        rs.getString("nocred_motivo"),
                        rs.getInt("idcompra"),
                        rs.getString("usu_nombre"),
                        rs.getString("descri_estado"),
                        rs.getString("prov_nombre"),
                        rs.getInt("idmercaderia"),
                        rs.getInt("cred_deb_cantidad"),
                        rs.getInt("cred_deb_precio"),
                        rs.getString("codigogenerico"),
                        rs.getString("mer_descripcion")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(facturacompradaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetalleNota);
    }

    @Override
    public String listarfacturaNota13() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteNCD(notacreditodebitodto dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detalle_cred_deb\n"
                    + " WHERE idcred_deb=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdcred_deb());
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
            Logger.getLogger(notacreditodebitodaoimple.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}
