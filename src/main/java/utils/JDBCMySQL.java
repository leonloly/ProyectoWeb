package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import org.sql2o.tools.NamedParameterStatement;

public class JDBCMySQL {

    private String user = "root";
    private String password = "root";
    private String puerto = "3306";
    private String database = "biblioteca";
    private String host = "localhost";
    private Connection conexion;

    public JDBCMySQL() {
        String url = "jdbc:mysql://" + host + ":" + puerto + "/" + database + "?useSSL=false&serverTimezone=America/El_Salvador";
        try {
            conexion = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public ResultSet query(String sql, Map<String, Object> params) {
        try {
            NamedParameterStatement psm2 = new NamedParameterStatement(conexion, sql, true);
            if (params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    psm2.setString(key, String.valueOf(value));
                }
            }
            return psm2.executeQuery();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    public boolean execute(String sql, Map<String, Object> params) {
        try {
            NamedParameterStatement psm2 = new NamedParameterStatement(conexion, sql, true);
            if (params != null) {
                for (Map.Entry<String, Object> entry : params.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    psm2.setString(key, String.valueOf(value));
                }
            }
            psm2.execute();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

}
