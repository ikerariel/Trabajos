/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPL;

import DAO.aperturaDAO;
import DTO.aperturaDTO;
import Genericos.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import static java.lang.System.out;
import java.sql.PreparedStatement;
import com.google.gson.Gson;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carlos
 */
public class aperturaDAOIMPL implements aperturaDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    /**
     * METODO GUARDAR LA APERTURA
     */
    @Override
    public boolean insertarapertura(aperturaDTO dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO apertura_recarga_giro(\n"
                    + "            comentario, idusuario, idestado, \n"
                    + "            idsucursal)\n"
                    + "    VALUES (?, ?, 1, \n"
                    + "            ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getComentario());
            preparedStatement.setObject(2, dto.getIdusuario());
            preparedStatement.setObject(3, dto.getIdsucursal());

            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                sintaxiSql = "INSERT INTO aperturarecarga(\n"
                        + "             montotigo, montoclaro, montopersonal, montovox, \n"
                        + "            idapertura)\n"
                        + "    VALUES (?, ?, ?, ?, (select idapertura from apertura_recarga_giro order by idapertura desc limit 1))";
                preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                preparedStatement.setObject(1, dto.getMontotigo());
                preparedStatement.setObject(2, dto.getMontoclaro());
                preparedStatement.setObject(3, dto.getMontopersonal());
                preparedStatement.setObject(4, dto.getMontovox());
                filasAfectadas = preparedStatement.executeUpdate();
                if (filasAfectadas > 0) {
                    sintaxiSql = "INSERT INTO aperturagiro(\n"
                            + "             giromontotigo, giromontoclaro, giromontopersonal, giromontovox, \n"
                            + "            idapertura)\n"
                            + "    VALUES (?, ?, ?, ?, (select idapertura from apertura_recarga_giro order by idapertura desc limit 1))";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getGiromontotigo());
                    preparedStatement.setObject(2, dto.getGiromontoclaro());
                    preparedStatement.setObject(3, dto.getGiromontopersonal());
                    preparedStatement.setObject(4, dto.getGiromontovox());
                    filasAfectadas = preparedStatement.executeUpdate();
                }
                System.out.println("Comit() Realizado");
                conexion.comit();
                return true;
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(aperturaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    /**
     * METODO RECUPERAR LISTA DE APERTURAS
     */
    @Override
    public String getapertura() {
        ResultSet rs;
        ArrayList<aperturaDTO> allapertura = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.idapertura, a.fecha_apertura::date, a.comentario, \n"
                    + "a.idusuario, (u.usuario), a.idestado, (e.descripcion) as estado,\n"
                    + "a.idsucursal, (s.descripcion) as sucursal, (r.montotigo+ r.montoclaro+ r.montopersonal+ r.montovox) as totalrecarga,\n"
                    + "(g.giromontotigo+giromontoclaro+giromontopersonal+giromontovox) as totalgiro,\n"
                    + " sum(r.montotigo+ r.montoclaro+ r.montopersonal+ r.montovox+g.giromontotigo+giromontoclaro+giromontopersonal+giromontovox)"
                    + " as totalgral\n"
                    + "  FROM apertura_recarga_giro a\n"
                    + "  LEFT JOIN usuario u on a.idusuario = u.idusuario\n"
                    + "  LEFT JOIN estado e on a.idestado = e.idestado\n"
                    + "  LEFT JOIN sucursal s on a.idsucursal = s.idsucursal\n"
                    + "  LEFT JOIN aperturarecarga r on a.idapertura = r.idapertura\n"
                    + "  LEFT JOIN aperturagiro g on a.idapertura = g.idapertura\n"
                    + "	where a.idestado in(1,2)\n"
                    + " group by a.idapertura, a.fecha_apertura::date, a.comentario, \n"
                    + "a.idusuario, (u.usuario), a.idestado, (e.descripcion),\n"
                    + "a.idsucursal, (s.descripcion), (r.montotigo+ r.montoclaro+ r.montopersonal+ r.montovox),\n"
                    + "(g.giromontotigo+giromontoclaro+giromontopersonal+giromontovox)\n"
                    + " ORDER BY a.idapertura desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allapertura.add(new aperturaDTO(rs.getInt("idapertura"),
                        rs.getString("fecha_apertura"),
                        rs.getInt("totalrecarga"),
                        rs.getInt("totalgiro"),
                        rs.getInt("totalgral"),
                        rs.getString("estado"),
                        rs.getString("usuario")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allapertura);
    }

    @Override
    public String obtenerapertura(Integer idapertura) {
        ResultSet rs;
        ArrayList<aperturaDTO> obtnerapertura = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT a.idapertura, a.fecha_apertura, a.comentario, a.idusuario, a.idestado, \n"
                    + "       a.idsucursal, r.montotigo, r.montoclaro, r.montopersonal, r.montovox, g.giromontotigo,\n"
                    + "       g.giromontoclaro, g.giromontopersonal, g.giromontovox\n"
                    + "  FROM apertura_recarga_giro a\n"
                    + "  left join aperturarecarga r on a.idapertura=r.idapertura\n"
                    + "  left join aperturagiro g on a.idapertura= g.idapertura\n"
                    + "  where a.idapertura = ?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idapertura);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                obtnerapertura.add(new aperturaDTO(rs.getInt("montotigo"),
                        rs.getInt("montoclaro"),
                        rs.getInt("montopersonal"),
                        rs.getInt("montovox"),
                        rs.getInt("giromontotigo"),
                        rs.getInt("giromontoclaro"),
                        rs.getInt("giromontopersonal"),
                        rs.getInt("giromontovox"),
                        rs.getString("comentario")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(obtnerapertura);
    }

    @Override
    public boolean actualizarapertura(aperturaDTO dto, Integer v_valor) {
        switch (v_valor) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE apertura_recarga_giro\n"
                            + "   SET  fecha_apertura=now(), comentario=?, idusuario=?, idestado=1, \n"
                            + "       idsucursal=?\n"
                            + " WHERE idapertura=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getComentario());
                    preparedStatement.setObject(2, dto.getIdusuario());
                    preparedStatement.setObject(3, dto.getIdsucursal());
                    preparedStatement.setObject(4, dto.getIdapertura());

                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        sintaxiSql = "UPDATE aperturarecarga\n"
                                + "   SET montotigo=?, montoclaro=?, montopersonal=?, \n"
                                + "       montovox=?, fecha=now()\n"
                                + " WHERE idapertura=?";
                        preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                        preparedStatement.setObject(1, dto.getMontotigo());
                        preparedStatement.setObject(2, dto.getMontoclaro());
                        preparedStatement.setObject(3, dto.getMontopersonal());
                        preparedStatement.setObject(4, dto.getMontovox());
                        preparedStatement.setObject(5, dto.getIdapertura());
                        filasAfectadas = preparedStatement.executeUpdate();
                        if (filasAfectadas > 0) {
                            sintaxiSql = "UPDATE aperturagiro\n"
                                    + "   SET  giromontotigo=?, giromontoclaro=?, giromontopersonal=?, \n"
                                    + "       giromontovox=?, fecha=now()\n"
                                    + " WHERE idapertura=?";
                            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                            preparedStatement.setObject(1, dto.getGiromontotigo());
                            preparedStatement.setObject(2, dto.getGiromontoclaro());
                            preparedStatement.setObject(3, dto.getGiromontopersonal());
                            preparedStatement.setObject(4, dto.getGiromontovox());
                            preparedStatement.setObject(5, dto.getIdapertura());
                            filasAfectadas = preparedStatement.executeUpdate();
                        }
                        System.out.println("Comit() Realizado");
                        conexion.comit();
                        return true;
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(aperturaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }

                break;

            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE apertura_recarga_giro\n"
                            + "   SET  fecha_apertura=now(), idestado=3\n"
                            + "      WHERE idapertura=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getIdapertura());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(aperturaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

                }
                break;
        }

        return false;
    }

    @Override
    public String getcierre() {
        ResultSet rs;
        ArrayList<aperturaDTO> allcierre = new ArrayList<>();
        try {

            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT e.idapertura,e.idestado, (e.fecha_apertura::date) as fechaApertura,\n"
                    + "(select sum(d.montorecarga) as total from detallerecargas d\n"
                    + "left join apertura_recarga_giro e on  d.idapertura = e.idapertura\n"
                    + "where e.idestado in(1,2)),\n"
                    + "coalesce(a.claro,0) as claro,\n"
                    + "coalesce(b.personal,0) as personal,\n"
                    + "coalesce(c.vox,0) as vox,\n"
                    + "coalesce(d.tigo, 0) as tigo\n"
                    + "FROM apertura_recarga_giro e \n"
                    + "left join detallerecargas r on e.idapertura = r.idapertura\n"
                    + "left join tipooperadora o on r.idtipooperadora=o.idtipooperadora\n"
                    + "left join (select idapertura, sum(montorecarga) as claro \n"
                    + "	      from detallerecargas \n"
                    + "	     where idtipooperadora = 1 \n"
                    + "	  group by idapertura) a on a.idapertura = e.idapertura\n"
                    + "left join (select idapertura, sum(montorecarga) as personal \n"
                    + "	      from detallerecargas \n"
                    + "	     where idtipooperadora = 2 \n"
                    + "	  group by idapertura) b on b.idapertura = e.idapertura\n"
                    + "left join (select idapertura, sum(montorecarga) as vox\n"
                    + "	      from detallerecargas \n"
                    + "	     where idtipooperadora = 3 \n"
                    + "	  group by idapertura) c on c.idapertura = e.idapertura\n"
                    + "left join (select idapertura, sum(montorecarga) as tigo\n"
                    + "	      from detallerecargas \n"
                    + "	     where idtipooperadora = 4 \n"
                    + "	  group by idapertura) d on d.idapertura = e.idapertura\n"
                    + "where e.idestado in(1,2)\n"
                    + "group by e.idapertura,e.fecha_apertura, coalesce(a.claro,0), coalesce(b.personal,0), \n"
                    + "coalesce(c.vox,0), coalesce(d.tigo, 0) order by e.idapertura desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allcierre.add(new aperturaDTO(rs.getInt("idapertura"),
                        rs.getInt("idestado"),
                        rs.getString("fechaApertura"),
                        rs.getInt("tigo"),
                        rs.getInt("claro"),
                        rs.getInt("personal"),
                        rs.getInt("vox"),
                        rs.getInt("total")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(aperturaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allcierre);
    }

    @Override
    public boolean cerrrarapertura(aperturaDTO dto) {
  
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE apertura_recarga_giro\n"
                    + "   SET idestado=2,fechacierre=now()\n"
                    + " WHERE idapertura=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIdapertura());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        
        } catch (SQLException ex) {
            Logger.getLogger(aperturaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    }
}
