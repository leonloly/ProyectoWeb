package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class Paises {

    private Integer codigo;
    private String nombre;
    private List<Autores> autoresList;

    public Paises() {
    }

    public Paises(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Paises(Integer codigo) {

        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Autores> getAutoresList() {
        return autoresList;
    }

    public void setAutoresList(List<Autores> autoresList) {
        this.autoresList = autoresList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into paises(nombre) values(:nombre)";
            if (codigo != null) {
                query = "update paises set nombre=:nombre where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("nombre", this.nombre);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar paises");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Paises> list() {
        List<Paises> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT codigo,nombre FROM paises";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Paises(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista paises");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Paises find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM paises where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Paises(rs.getInt(1), rs.getString(2));
        } catch (Exception e) {
            System.err.println("Error al obtener paises");
            System.err.println(e.getMessage());
        }
        return null;
    }

}
