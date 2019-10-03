/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.mercaderiadao;
import DTO.categoriasdto;
import DTO.marcasdto;
import DTO.procedenciasdto;
import DTO.impuestodto;
import DTO.mercaderiadto;
import Genericos.Conexion;
import Genericos.mierror;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Rafel
 */
public class mercaderiadaoimple implements mercaderiadao {

    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    /**
     *
     * @param mercader_Dto
     * @return
     * @throws mierror
     */
    @Override
    public boolean insertar(mercaderiadto mercader_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO mercaderias(idmercaderia, mer_costo, mer_precio, mer_descripcion, idcategoria, \n"
                    + "            idmarca, idprocedencia, idimpuesto, codigogenerico)\n"
                    + "    VALUES (?, ?, ?, ?, ?,?, ?, ?, ?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, mercader_Dto.getIdmercaderia());
            preparedStatement.setObject(2, mercader_Dto.getMer_costo());
            preparedStatement.setObject(3, mercader_Dto.getMer_precio());
            preparedStatement.setObject(4, mercader_Dto.getMer_descripcion());
            preparedStatement.setObject(5, mercader_Dto.getIdcategoria());
            preparedStatement.setObject(6, mercader_Dto.getIdmarca());
            preparedStatement.setObject(7, mercader_Dto.getIdprocedencia());
            preparedStatement.setObject(8, mercader_Dto.getIdimpuesto());
            preparedStatement.setObject(9, mercader_Dto.getCodigogenerico());
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return false;
    }

    @Override
    public boolean modificar(mercaderiadto mercader_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE mercaderias SET mer_costo=?, mer_precio=?, mer_descripcion=?, \n"
                    + "       idcategoria=?, idmarca=?, idprocedencia=?, idimpuesto=?, codigogenerico=?"
                    + " WHERE idmercaderia=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, mercader_Dto.getMer_costo());
            preparedStatement.setObject(2, mercader_Dto.getMer_precio());
            preparedStatement.setObject(3, mercader_Dto.getMer_descripcion());
            preparedStatement.setObject(4, mercader_Dto.getIdcategoria());
            preparedStatement.setObject(5, mercader_Dto.getIdmarca());
            preparedStatement.setObject(6, mercader_Dto.getIdprocedencia());
            preparedStatement.setObject(7, mercader_Dto.getIdimpuesto());
            preparedStatement.setObject(8, mercader_Dto.getCodigogenerico());
            preparedStatement.setObject(9, mercader_Dto.getIdmercaderia());
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);

        }
        return false;
    }

    @Override
    public boolean eliminar(Integer id) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "DELETE FROM mercaderias WHERE idmercaderia=?;";
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return false;
    }

    @Override
    public String getmercaderia() throws mierror {
        ResultSet rs;
        ArrayList<mercaderiadto> allMerca = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT mer.idmercaderia, mer.mer_costo, mer.mer_precio, mer.mer_descripcion, c.cate_descri, \n"
                    + "       m.marca_descri, p.proce_descri, i.descri_impuesto, mer.codigogenerico\n"
                    + "  FROM mercaderias mer, categorias c, marcas m, procedencias p, impuesto i\n"
                    + "  WHERE mer.idcategoria=c.idcategoria AND mer.idmarca=m.idmarca AND mer.idprocedencia=p.idprocedencia AND \n"
                    + "  mer.idimpuesto=i.idimpuesto ORDER BY mer.idmercaderia;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMerca.add(new mercaderiadto(rs.getInt("idmercaderia"),
                        rs.getInt("mer_costo"),
                        rs.getInt("mer_precio"),
                        rs.getString("mer_descripcion"),
                        rs.getString("cate_descri"),
                        rs.getString("marca_descri"),
                        rs.getString("proce_descri"),
                        rs.getString("descri_impuesto"),
                        rs.getString("codigogenerico")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allMerca);
    }

    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idmercaderia),0 )+ 1 as cod_mercad  FROM mercaderias;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("cod_mercad");
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return 0;
    }

    @Override
    public String getmercaderiaFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<mercaderiadto> allMerca = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idmercaderia, mer_costo, mer_precio, mer_descripcion, idcategoria, \n"
                    + "       idmarca, idprocedencia, idimpuesto, codigogenerico\n"
                    + "  FROM mercaderias WHERE idmercaderia=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allMerca.add(new mercaderiadto(rs.getInt("idmercaderia"),
                        rs.getInt("mer_costo"),
                        rs.getInt("mer_precio"),
                        rs.getString("mer_descripcion"),
                        rs.getInt("idcategoria"),
                        rs.getInt("idmarca"),
                        rs.getInt("idprocedencia"),
                        rs.getInt("idimpuesto"),
                        rs.getString("codigogenerico")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allMerca);
    }
    
    @Override
    public String listarcategorias() throws mierror {
        ResultSet rs;
        ArrayList<categoriasdto> allCateg = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idcategoria, cate_descri FROM categorias ORDER BY idcategoria;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCateg.add(new categoriasdto(rs.getInt("idcategoria"),
                        rs.getString("cate_descri")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allCateg); 
    }
    
    @Override
    public String listarmarcas() throws mierror {
        ResultSet rs;
        ArrayList<marcasdto> allMarca = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idmarca, marca_descri FROM marcas ORDER BY idmarca;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allMarca.add(new marcasdto(rs.getInt("idmarca"),
                        rs.getString("marca_descri")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allMarca); 
    }
    
    @Override
    public String listarprocedencia() throws mierror {
        ResultSet rs;
        ArrayList<procedenciasdto> allProced = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idprocedencia, proce_descri FROM procedencias ORDER BY idprocedencia;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allProced.add(new procedenciasdto(rs.getInt("idprocedencia"),
                        rs.getString("proce_descri")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allProced); 
    }
    
    @Override
    public String listarimpuestos() throws mierror {
        ResultSet rs;
        ArrayList<impuestodto> allImpu = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idimpuesto, descri_impuesto FROM impuesto ORDER BY idimpuesto;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allImpu.add(new impuestodto(rs.getInt("idimpuesto"),
                        rs.getString("descri_impuesto")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allImpu); 
    }

}
