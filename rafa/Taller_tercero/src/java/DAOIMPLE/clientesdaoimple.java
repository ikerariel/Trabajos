/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOIMPLE;

import DAO.clientesdao;
import DTO.ciudadesdto;
import DTO.clientesdto;
import Genericos.Conexion;
import Genericos.mierror;
import com.google.gson.Gson;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author naty
 */
public class clientesdaoimple implements clientesdao {

    private String sintaxiSql;
    private String MsmError;
    private PreparedStatement preparedStatement;
    private ResultSet resultado;
    private Conexion conexion;
    private int filasAfectadas;

    /**
     *
     * @param cliente_Dto
     * @return
     * @throws mierror
     */
    @Override
    public boolean insertar(clientesdto cliente_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "INSERT INTO clientes(idclientes, cli_ruc, cli_razonsocial, cli_telefono, \n"
                    + "   cli_direccion, cli_correo, idciudad,cv)\n"
                    + "   VALUES (?, ?, ?, ?, ?,?, ?,?);";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, cliente_Dto.getIdclientes());
            preparedStatement.setObject(2, cliente_Dto.getCli_ruc());
            preparedStatement.setObject(3, cliente_Dto.getCli_razonsocial());
            preparedStatement.setObject(4, cliente_Dto.getCli_telefono());
            preparedStatement.setObject(5, cliente_Dto.getCli_direccion());
            preparedStatement.setObject(6, cliente_Dto.getCli_correo());
            preparedStatement.setObject(7, cliente_Dto.getIdciudad());
            preparedStatement.setObject(8, cliente_Dto.getCv());
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
    public boolean modificar(clientesdto cliente_Dto) throws mierror {
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "UPDATE clientes SET cli_ruc=?, cli_razonsocial=?, "
                    + "cli_telefono=?, cli_direccion=?, cli_correo=?, idciudad=? WHERE idclientes=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setObject(1, cliente_Dto.getCli_ruc());
            preparedStatement.setObject(2, cliente_Dto.getCli_razonsocial());
            preparedStatement.setObject(3, cliente_Dto.getCli_telefono());
            preparedStatement.setObject(4, cliente_Dto.getCli_direccion());
            preparedStatement.setObject(5, cliente_Dto.getCli_correo());
            preparedStatement.setObject(6, cliente_Dto.getIdciudad());
            preparedStatement.setObject(7, cliente_Dto.getIdclientes());
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
            sintaxiSql = "DELETE FROM clientes WHERE idclientes=?;";
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
    public String getcliente() throws mierror {
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
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allCliente);
    }

    @Override
    public Integer getUltimoCodigo() throws mierror {
        ResultSet rs;
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT coalesce(max(idclientes),0 )+ 1 as cod_cliente  FROM clientes;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return rs.getInt("cod_cliente");
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return 0;
    }

    @Override
    public String getclienteFiltro(Integer idFiltro) throws mierror {
        ResultSet rs;
        ArrayList<clientesdto> allCliente = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT c.idclientes, c.cli_ruc, c.cli_razonsocial, c.cli_telefono,\n"
                    + "			c.cli_direccion, c.cli_correo, ci.idciudad, ci.ciu_descripcion\n"
                    + "			FROM clientes c \n"
                    + "			inner join ciudades ci on ci.idciudad=c.idciudad\n"
                    + "			WHERE idclientes=?;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            preparedStatement.setInt(1, idFiltro);

            rs = preparedStatement.executeQuery();
            if (rs.next()) {
                allCliente.add(new clientesdto(rs.getInt("idclientes"),
                        rs.getString("cli_ruc"),
                        rs.getString("cli_razonsocial"),
                        rs.getString("cli_telefono"),
                        rs.getString("cli_direccion"),
                        rs.getString("cli_correo"),
                        rs.getInt("idciudad"),
                        rs.getString("ciu_descripcion")));
            } else {
                return null;
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allCliente);

    }

    @Override
    public String listarciudad() throws mierror {
        ResultSet rs;
        ArrayList<ciudadesdto> allCiud = new ArrayList<>();
        try {
            sintaxiSql = null;
            conexion = new Conexion();
            sintaxiSql = "SELECT idciudad, ciu_descripcion FROM ciudades ORDER BY idciudad;";
            preparedStatement = conexion.getConexion().prepareStatement(sintaxiSql);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                allCiud.add(new ciudadesdto(rs.getInt("idciudad"),
                        rs.getString("ciu_descripcion")));
            }
        } catch (SQLException ex) {
            MsmError = "MI mensaje propio";
            throw new mierror(MsmError, ex);
        }
        return new Gson().toJson(allCiud);
    }

    @Override
    public String codigoVerificado(String p_numero, Integer p_basemax) {
            ResultSet rs;
        ArrayList<clientesdto> allcv = new ArrayList<>();

        Integer v_total, v_resto, k, v_numero_aux, v_digit;
        String v_numero_al = "";

        for (int i = 0; i < p_numero.length(); i++) {
            char c = p_numero.charAt(i);
            if (Character.isDigit(c)) {
                v_numero_al += c;
            } else {
                v_numero_al += (int) c;
            }
        }

        k = 2;
        v_total = 0;

        for (int i = v_numero_al.length() - 1; i >= 0; i--) {
            k = k > p_basemax ? 2 : k;
            v_numero_aux = v_numero_al.charAt(i) - 48;
            v_total += v_numero_aux * k++;
        }

        v_resto = v_total % 11;
        v_digit = v_resto > 1 ? 11 - v_resto : 0;

        return new Gson().toJson(v_digit);
    }
    }


