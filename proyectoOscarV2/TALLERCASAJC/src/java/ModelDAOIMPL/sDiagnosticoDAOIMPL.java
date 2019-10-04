
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelDAOIMPL;

import Genericos.Conexion;
import ModelDAO.sDiagnosticoDAO;
import ModelDTO.sDiagnosticoDTO;
import ModelDTO.sRecepcionDTO;
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
public class sDiagnosticoDAOIMPL implements sDiagnosticoDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public String getsDiagnostico() {
        ResultSet rs;
        ArrayList<sDiagnosticoDTO> alldiagnostico = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT d.id_diagnostico, d.fecha::date, r.id_cliente, d.id_estado, d.id_usuario, \n" +
"                     e.est_descripcion,u.usu_nombre,d.diagnostico,\n" +
"                      c.ruc, c.razonsocial\n" +
"                     FROM public.diagnosticos d\n" +
"                    	INNER JOIN recepciones r ON d.id_recepcion = r.id_recepcion\n" +
"                    inner join clientes c on r.id_cliente = c.id_cliente\n" +
"                     inner join usuarios u on r.id_usuario = u.id_usuario\n" +
"                    inner join estados e on d.id_estado=e.id_estado \n" +
"                     where d.id_estado in(1,3) order by d.id_diagnostico desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldiagnostico.add(new sDiagnosticoDTO(rs.getInt("id_diagnostico"),
                        rs.getString("fecha"),
                        rs.getString("razonsocial"),
                        rs.getString("est_descripcion"),
                        rs.getString("usu_nombre"),
                        rs.getInt("id_estado")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldiagnostico);
    }

    @Override
    public boolean insertarDiagnostico(sDiagnosticoDTO dto, Integer cod) {

        switch (cod) {
            case 1: //INSERTAR CABACERA
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.diagnosticos(\n"
                            + "          fecentreg, id_recepcion, id_usuario, id_estado, \n"
                            + "            diagnostico)\n"
                            + "    VALUES (now(), ?, ?, 3, \n"
                            + "            ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_recepcion());
                    preparedStatement.setObject(2, dto.getId_usuario());
                    preparedStatement.setObject(3, dto.getDiganostico());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;

            case 2: //ACTUALIZARCABECERA
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.diagnosticos\n"
                            + "   SET fecha=now(), fecentreg=now(), id_recepcion=?, id_usuario=?, \n"
                            + "       id_estado=3, diagnostico=?\n"
                            + " WHERE id_diagnostico=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_recepcion());
                    preparedStatement.setObject(2, dto.getId_usuario());
                    preparedStatement.setObject(3, dto.getDiganostico());
                    preparedStatement.setObject(4, dto.getId_diagnostico());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return false;

    }

    @Override
    public boolean insertarDetalleDiagnostico(sDiagnosticoDTO dto, Integer dDiagnostico) {

        switch (dDiagnostico) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detdiagnosticos(\n"
                            + "            id_diagnostico, cantidad, id_articulo)\n"
                            + "    VALUES ((select id_diagnostico from diagnosticos order by id_diagnostico desc limit 1), ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getCantidad());
                    preparedStatement.setObject(2, dto.getId_articulo());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "INSERT INTO public.detdiagnosticos(\n"
                            + "            id_diagnostico, cantidad, id_articulo)\n"
                            + "    VALUES (?, ?, ?);";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getId_diagnostico());
                    preparedStatement.setObject(2, dto.getCantidad());
                    preparedStatement.setObject(3, dto.getId_articulo());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
        }
        return false;

    }

    @Override
    public boolean deleteDetalleDiagnostico(sDiagnosticoDTO dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM public.detdiagnosticos\n"
                    + " WHERE id_diagnostico =?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_diagnostico());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean actualizarEstado(sDiagnosticoDTO dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE public.diagnosticos\n"
                    + "   SET  id_estado=?\n"
                    + " WHERE id_diagnostico=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getId_estado());
            preparedStatement.setObject(2, dto.getId_diagnostico());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }

        } catch (SQLException ex) {
            Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public String getdetallesDiagnostico(Integer codSrecpcion) {

        ResultSet rs;
        ArrayList<sDiagnosticoDTO> allrece = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.id_recepcion, r.fecha::date, r.id_cliente, r.id_estado, r.id_usuario, r.observacion,\n"
                    + " rd.id_articulo, a.art_descripcion, rd.cantidad, e.est_descripcion,(select id_recepcion from diagnosticos where id_recepcion=?) as nrorecepcion,\n"
                    + "c.ruc,  (c.ruc||' / '||c.razonsocial) as cliente\n"
                    + "  FROM public.recepciones r\n"
                    + "  inner join detrecepciones rd on r.id_recepcion=rd.id_recepcion\n"
                    + "  inner join clientes c on r.id_cliente = c.id_cliente\n"
                    + "  inner join articulos a on rd. id_articulo=a.id_articulo \n"
                    + "  inner join estados e on r.id_estado=e.id_estado where r.id_recepcion = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codSrecpcion);
            preparedStatement.setObject(2, codSrecpcion);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allrece.add(new sDiagnosticoDTO(rs.getInt("id_recepcion"),
                        rs.getString("fecha"),
                        rs.getString("ruc"),
                        rs.getString("cliente"),
                        rs.getString("observacion"),
                        rs.getInt("id_articulo"),
                        rs.getString("art_descripcion"),
                        rs.getInt("id_cliente"),
                        rs.getInt("id_estado"),
                        rs.getInt("nrorecepcion"),
                        rs.getInt("cantidad")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allrece);
    }

    @Override
    public Integer updatedetllaDiagnostico(Integer codDiagnostico) {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT  id_recepcion\n"
                    + "  FROM public.diagnosticos where id_diagnostico=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codDiagnostico);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_recepcion");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CargosDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    @Override
    public String getdetalleDiagnostico(Integer codDiagnostico) {
        ResultSet rs;
        ArrayList<sDiagnosticoDTO> allDiagnostico = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT d.id_diagnostico, d.fecha::date, d.fecentreg, d.id_recepcion, \n"
                    + "d.id_usuario, d.id_estado,(a.art_descripcion)  as articulo,r.id_cliente,\n"
                    + "(c.ruc||' / '||c.razonsocial) as cliente, (r.fecha::date) as fecharecepcion,\n"
                    + " d.diagnostico,dp.id_articulo, dp.cantidad\n"
                    + "  FROM public.diagnosticos d\n"
                    + "  left join detdiagnosticos dp on d.id_diagnostico=dp.id_diagnostico\n"
                    + "  left join articulos a on dp.id_articulo=a.id_articulo\n"
                    + "  left join recepciones r on d.id_recepcion=r.id_recepcion\n"
                    + "  left join clientes c on r.id_cliente=c.id_cliente\n"
                    + "where d.id_diagnostico=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, codDiagnostico);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allDiagnostico.add(new sDiagnosticoDTO(rs.getInt("id_diagnostico"),
                        rs.getString("fecha"),
                        rs.getInt("id_recepcion"),
                        rs.getString("fecharecepcion"),
                        rs.getString("cliente"),
                        rs.getString("diagnostico"),
                        rs.getInt("id_articulo"),
                        rs.getInt("cantidad"),
                        rs.getString("articulo")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(sDiagnosticoDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allDiagnostico);
    }

}
