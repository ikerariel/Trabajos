/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPL;

import DAO.recargaDAO;
import DTO.giroDTO;
import DTO.recargaDTO;
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

public class recargaDAOIMPL implements recargaDAO {

    private String sintaxiSql;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    @Override
    public boolean insertarrecarga(recargaDTO dto) {

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO detallerecargas(\n"
                    + "             nrodestino, montorecarga, nrotransaccion, \n"
                    + "            idtipooperadora, idapertura,fecharecarga,observacion, nrooperacion,idcliente,idestado,idusuario)\n"
                    + "VALUES (?, ?, ?, ?, (select idapertura from apertura_recarga_giro where idestado=1 order by idapertura desc limit 1), now(),?,?,?,?,?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getNrodestino());
            preparedStatement.setObject(2, dto.getMontorecarga());
            preparedStatement.setObject(3, dto.getNrotransaccion());
            preparedStatement.setObject(4, dto.getIdtipooperadora());
            preparedStatement.setObject(5, dto.getObservacion());
            preparedStatement.setObject(6, dto.getNrooperacion());
            preparedStatement.setObject(7, dto.getIdcliente());
            preparedStatement.setObject(8, dto.getIdestado());
            preparedStatement.setObject(9, dto.getIdusuario());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public String getrecarga() {
        ResultSet rs;
        ArrayList<recargaDTO> allprefijo = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.iddetallerecargas,r.nrodestino, r.montorecarga, r.nrotransaccion, \n"
                    + "       r.idtipooperadora,(o.descripcion)as operadora, r.idapertura,(a.fecha_apertura::date) as fechaApertura, r.fecharecarga,\n"
                    + "       coalesce(p.total,0) as total, r.nrotransaccion\n"
                    + "FROM detallerecargas r \n"
                    + "inner join tipooperadora o on r.idtipooperadora = o.idtipooperadora\n"
                    + "inner join apertura_recarga_giro a on r.idapertura=a.idapertura\n"
                    + "left join (SELECT r.idtipooperadora, (case when r.idtipooperadora = 1 then\n"
                    + "						sum(r.montorecarga) \n"
                    + "					    when r.idtipooperadora = 2 then\n"
                    + "						sum(r.montorecarga) \n"
                    + "					    when r.idtipooperadora = 3 then\n"
                    + "						sum(montorecarga)\n"
                    + "					    when r.idtipooperadora = 4 then\n"
                    + "						sum(r.montorecarga)\n"
                    + "					end) as total\n"
                    + "	FROM detallerecargas r \n"
                    + "	group by r.idtipooperadora) p on r.idtipooperadora = p.idtipooperadora where a.idestado=1\n"
                    + "	order by r.iddetallerecargas desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allprefijo.add(new recargaDTO(rs.getInt("iddetallerecargas"),
                        rs.getInt("idapertura"),
                        rs.getString("operadora"),
                        rs.getInt("nrodestino"),
                        rs.getInt("montorecarga"),
                        rs.getInt("nrotransaccion"),
                        rs.getString("fecharecarga")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allprefijo);
    }

    @Override
    public String getconsulta(Integer codoperadora) {
        ResultSet rs;
        ArrayList<recargaDTO> consul = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql="SELECT r.iddetallerecargas,r.nrodestino, r.montorecarga, r.nrotransaccion, r.idtipooperadora,\n" +
"(o.descripcion)as operadora, r.idapertura,(a.fecha_apertura::date) as fechaApertura, r.fecharecarga,\n" +
"                   r.nrotransaccion\n" +
"                   FROM detallerecargas r \n" +
"                   inner join tipooperadora o on r.idtipooperadora = o.idtipooperadora\n" +
"                   inner join apertura_recarga_giro a on r.idapertura=a.idapertura\n" +
"                                where r.nrodestino = ? and a.idestado in(1,2)\n" +
"                 order by r.iddetallerecargas desc";
//            sintaxiSql = " SELECT r.iddetallerecargas,r.nrodestino, r.montorecarga, r.nrotransaccion, \n" +
//"                     r.idtipooperadora,(o.descripcion)as operadora, r.idapertura,(a.fecha_apertura::date) as fechaApertura, r.fecharecarga,\n" +
//"                    coalesce(p.total,0) as total, r.nrotransaccion\n" +
//"                    FROM detallerecargas r \n" +
//"                    inner join tipooperadora o on r.idtipooperadora = o.idtipooperadora\n" +
//"                    inner join apertura_recarga_giro a on r.idapertura=a.idapertura\n" +
//"                    left join (SELECT r.idtipooperadora,\n" +
//"                    (case when r.idtipooperadora = 1 then\n" +
//"                              sum(r.montorecarga)\n" +
//"                              when r.idtipooperadora = 2 then\n" +
//"                              sum(r.montorecarga)\n" +
//"                              when r.idtipooperadora = 3 then\n" +
//"                              sum(montorecarga)\n" +
//"                              when r.idtipooperadora = 4 then\n" +
//"                              sum(r.montorecarga)\n" +
//"                              end) as total\n" +
//"                    FROM detallerecargas r\n" +
//"                    group by r.idtipooperadora) p on r.idtipooperadora = p.idtipooperadora \n" +
//"                    where r.nrodestino = ? and a.idestado in(1,2)\n" +
//"                    order by r.iddetallerecargas desc";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codoperadora);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                consul.add(new recargaDTO(rs.getInt("iddetallerecargas"),
                        rs.getString("operadora"),
                        rs.getInt("nrodestino"),
                        rs.getInt("montorecarga"),
                        rs.getInt("nrotransaccion"),
                        rs.getString("fecharecarga")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(consul);
    }

    @Override
    public String getoperadora() {
        ResultSet rs;
        ArrayList<recargaDTO> alloperadora = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idtipooperadora, descripcion\n"
                    + "  FROM tipooperadora;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alloperadora.add(new recargaDTO(rs.getInt("idtipooperadora"),
                        rs.getString("descripcion")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alloperadora);
    }

    @Override
    public boolean eliminarcarga(recargaDTO dto) {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM detallerecargas\n"
                    + " WHERE iddetallerecargas=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, dto.getIddetallerecargas());
            filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                conexion.comit();
            } else {
                conexion.rollback();
                System.out.println("Rollback() Realizado");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);

        }
        return false;
    }

    @Override
    public String consultarecarga(Integer codoperadora) {
        ResultSet rs;
        ArrayList<recargaDTO> alldetallerecarga = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT r.iddetallerecargas\n"
                    + "  FROM apertura_recarga_giro a\n"
                    + "  left join detallerecargas r on a.idapertura=r.idapertura\n"
                    + "where r.idapertura=?";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, codoperadora);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                alldetallerecarga.add(new recargaDTO(rs.getInt("iddetallerecargas")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(alldetallerecarga);
    }

    @Override
    public String getconsultaci(String ci) {
        ResultSet rs;
        ArrayList<recargaDTO> allconsulta = new ArrayList<>();

        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT r.iddetallerecargas,r.nrodestino, r.montorecarga, r.nrotransaccion, \n" +
"                    r.idtipooperadora,(o.descripcion)as operadora, r.idapertura,(a.fecha_apertura::date) as fechaApertura, r.fecharecarga,\n" +
"                    coalesce(p.total,0) as total, r.nrotransaccion, c.idcliente\n" +
"                    FROM detallerecargas r \n" +
"                    left join tipooperadora o on r.idtipooperadora = o.idtipooperadora\n" +
"                    left join apertura_recarga_giro a on r.idapertura=a.idapertura\n" +
"                    left join cliente c on r.idcliente=c.idcliente\n" +
"                    left join (SELECT r.idtipooperadora, \n" +
"                    	(case when r.idtipooperadora = 1 then\n" +
"                              sum(r.montorecarga)\n" +
"                              when r.idtipooperadora = 2 then\n" +
"                              sum(r.montorecarga)\n" +
"                              when r.idtipooperadora = 3 then\n" +
"                             sum(montorecarga)\n" +
"                              when r.idtipooperadora = 4 then\n" +
"                              sum(r.montorecarga)\n" +
"                             end) as total\n" +
"                    FROM detallerecargas r\n" +
"                    group by r.idtipooperadora) p on r.idtipooperadora = p.idtipooperadora \n" +
"                    where c.cedula = ? and a.idestado in(1,2)\n" +
"                    order by r.iddetallerecargas desc	\n" +
"                   ";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setString(1, ci);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allconsulta.add(new recargaDTO(rs.getInt("iddetallerecargas"),
                        rs.getInt("idapertura"),
                        rs.getString("operadora"),
                        rs.getInt("nrodestino"),
                        rs.getInt("montorecarga"),
                        rs.getInt("nrotransaccion"),
                        rs.getString("fecharecarga")));

            }

        } catch (SQLException ex) {
            Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(allconsulta);
    }

    @Override
    public String getrecargaspendientes(Integer id) {
        ResultSet rs;
        ArrayList<giroDTO> obtenerpagos = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = " SELECT pg.idestado, pg.idpagorecarga, r.iddetallerecargas, concat(cl.cedula||' / '|| cl.nombrecliente) as cliente, e.idapertura,\n"
                    + "(e.fecha_apertura) as fechaApertura,e.fechacierre, r.fecharecarga, pg.fechapagorecarga, (o.descripcion) as operadora, \n"
                    + "(g.descripcion)as estado,concat('0',r.nrodestino) as nrodestino,\n"
                    + "r.montorecarga, 'RECARGAS' as operacion\n"
                    + "FROM apertura_recarga_giro e \n"
                    + "left join detallerecargas r on e.idapertura = r.idapertura\n"
                    + "left join tipooperadora o on r.idtipooperadora=o.idtipooperadora\n"
                    + "left join estado p on r.idestado=p.idestado\n"
                    + "left join cliente cl on r.idcliente=cl.idcliente \n"
                    + "left join estado g on r.idestado=g.idestado\n"
                    + "left join pagorecarga pg on r.iddetallerecargas = pg.iddetallerecargas\n"
                    + "where  pg.idestado=? and e.idestado in(1,2)";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                obtenerpagos.add(new giroDTO(rs.getInt("iddetallerecargas"),
                        rs.getString("cliente"),
                        rs.getString("operacion"),
                        rs.getInt("montorecarga"),
                        rs.getInt("idestado"),
                        rs.getString("fechapagorecarga"),
                        rs.getInt("idpagorecarga"),
                        rs.getInt("nrodestino"),
                        rs.getString("fecharecarga")));

            }
        } catch (SQLException ex) {
            Logger.getLogger(giroDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Gson().toJson(obtenerpagos);
    }

    @Override
    public boolean insertarpagorecarga(recargaDTO dto, Integer Vcodigo) {

        switch (Vcodigo) {
            case 1:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.pagorecarga\n"
                            + "   SET idestado=?, fechapagorecarga=now(), \n"
                            + "       montopagadorecarga=?,nrooperarecarga=?\n"
                            + " WHERE  iddetallerecargas=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, dto.getIdestado());
                    preparedStatement.setObject(2, dto.getMontopagadorecarga());
                    preparedStatement.setObject(3, dto.getNrooperarecarga());
                    preparedStatement.setObject(4, dto.getIddetallerecargas());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;

            case 2:
                try {
                    sintaxiSql = null;
                    conexion = new Conexion();
                    sintaxiSql = "UPDATE public.pagorecarga SET fechapagorecarga=null, montopagadorecarga=?,nrooperarecarga=?,idestado=? WHERE  iddetallerecargas=?";
                    preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
                    preparedStatement.setObject(1, 0);
                    preparedStatement.setObject(2, 0);
                    preparedStatement.setObject(3, dto.getIdestado());
                    preparedStatement.setObject(4, dto.getIddetallerecargas());
                    filasAfectadas = preparedStatement.executeUpdate();
                    if (filasAfectadas > 0) {
                        conexion.comit();
                    } else {
                        conexion.rollback();
                        System.out.println("Rollback() Realizado");
                        return false;
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(recargaDAOIMPL.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
        }
        return false;

    }

}
