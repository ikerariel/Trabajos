/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.sOrdenTrabajoDAO;
import ModelDTO.sOrdenTrabajoDTO;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class sOrdenTrabajoDAOIMPLE implements sOrdenTrabajoDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getdetallePresupuesto(Integer codPresupuesto) {

        ResultSet rs;
        ArrayList<sOrdenTrabajoDTO> alldetallePSORDEN = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT p.id_presuserv, p.id_estado, p.id_diagnostico, p.id_usuario, p.fecha::date,\n"
                    + "p.observacion, (d.fecha::date) as fechadiagnostico, pd.id_articulo, pd.precio, pd.cantidad,\n"
                    + "(a.art_descripcion)as articulo,(select id_presuserv from ordentrabajos where id_presuserv = ?) as nropresupuesto,\n"
                    + "(c.ruc||' / '||c.razonsocial) as cliente\n"
                    + "FROM public.presupuestosservicios p\n"
                    + "left join detpresupuestosservicios pd on p.id_presuserv=pd.id_presuserv\n"
                    + "left join articulos a on pd.id_articulo=a.id_articulo\n"
                    + "left join diagnosticos d on p.id_diagnostico = d.id_diagnostico\n"
                    + "left join recepciones r on d.id_recepcion=r.id_recepcion\n"
                    + "left join clientes c on r.id_cliente=c.id_cliente\n"
                    + "where p.id_presuserv=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codPresupuesto);
            preparedStatement.setObject(2, codPresupuesto);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetallePSORDEN.add(new sOrdenTrabajoDTO(rs.getInt("id_presuserv"),
                        rs.getString("fecha"),
                        rs.getString("cliente"),
                        rs.getString("observacion"),
                        rs.getInt("nropresupuesto"),
                        rs.getInt("id_estado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sOrdenTrabajoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetallePSORDEN);
    }

    @Override
    public boolean insetarOrdentrabajo(sOrdenTrabajoDTO dto, Integer cod) {
        switch (cod) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.ordentrabajos(diagnostico, orden,  id_usuario, \n"
                            + "            id_estado, id_presuserv, fechaentrega)\n"
                            + "    VALUES (?, ?, ?, 3, ?, ?::date);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getDiagnostico());
                    preparedStatement.setObject(2, dto.getOrden());
                    preparedStatement.setObject(3, dto.getId_usuario());
                    preparedStatement.setObject(4, dto.getId_presuserv());
                    preparedStatement.setObject(5, dto.getFechaentrega());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sOrdenTrabajoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.ordentrabajos\n"
                            + "   SET  fecha=now(), diagnostico=?, orden=?,\n"
                            + "       id_usuario=?, id_estado=3, id_presuserv=?, fechaentrega=?::date\n"
                            + " WHERE id_ordenttabajo=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getDiagnostico());
                    preparedStatement.setObject(2, dto.getOrden());
                    preparedStatement.setObject(3, dto.getId_usuario());
                    preparedStatement.setObject(4, dto.getId_presuserv());
                    preparedStatement.setObject(5, dto.getFechaentrega());
                    preparedStatement.setObject(6, dto.getId_ordenttabajo());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sOrdenTrabajoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }

        return false;
    }

    @Override
    public String getdetalleOT(Integer codOT) {
        ResultSet rs;
        ArrayList<sOrdenTrabajoDTO> allOTall = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT ot.id_ordenttabajo, ot.fecha::date, ot.diagnostico, ot.orden, ot.id_garantia, ot.id_usuario, \n"
                    + "       ot.id_estado, d.diagnostico, (p.fecha) as fechapresupuesto, ot.id_presuserv, ot.fechaentrega::date, (c.ruc||' / '||c.razonsocial) as cliente\n"
                    + "  FROM public.ordentrabajos ot\n"
                    + "inner join presupuestosservicios p on ot.id_presuserv= p.id_presuserv\n"
                    + "inner join diagnosticos d on p.id_diagnostico=d.id_diagnostico \n"
                    + "inner join  recepciones r on d.id_recepcion=r.id_recepcion\n"
                    + "inner join clientes c on r.id_cliente=c.id_cliente\n"
                    + "where ot.id_ordenttabajo = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codOT);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allOTall.add(new sOrdenTrabajoDTO(rs.getInt("id_ordenttabajo"),
                        rs.getString("fecha"),
                        rs.getInt("id_presuserv"),
                        rs.getString("fechapresupuesto"),
                        rs.getString("cliente"),
                        rs.getString("fechaentrega"),
                        rs.getString("diagnostico"),
                        rs.getString("orden")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sOrdenTrabajoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOTall);
    }

    @Override
    public String getOT() {
        ResultSet rs;
        ArrayList<sOrdenTrabajoDTO> allOT = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT ot.id_ordenttabajo, ot.fecha::date, ot.id_usuario, (u.usu_nombre)as usuario,\n"
                    + "       ot.id_estado, ot.id_presuserv, ot.fechaentrega::date, (e.est_descripcion) as estado\n"
                    + "  FROM public.ordentrabajos ot\n"
                    + "  inner join usuarios u on ot.id_usuario= u.id_usuario\n"
                    + "  inner join estados e on ot.id_estado= e.id_estado and ot.id_estado in(1,3) order by ot.id_ordenttabajo desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allOT.add(new sOrdenTrabajoDTO(rs.getInt("id_ordenttabajo"),
                        rs.getString("fecha"),
                        rs.getString("fechaentrega"),
                        rs.getInt("id_presuserv"),
                        rs.getString("usuario"),
                        rs.getString("estado"),
                        rs.getInt("id_estado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sOrdenTrabajoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allOT);
    }

    @Override
    public boolean updateEstadoOT(sOrdenTrabajoDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.ordentrabajos\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE id_ordenttabajo=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_estado());
            preparedStatement.setObject(2, dto.getId_ordenttabajo());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(sOrdenTrabajoDAOIMPLE.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

}
