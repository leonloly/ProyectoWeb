package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class Sucursales {

    private Integer codigo;
    private String nombre;
    private List<Estantes> estantesList;

    public Sucursales() {
    }

    public Sucursales(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public Sucursales(Integer codigo) {
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

    public List<Estantes> getEstantesList() {
        return estantesList;
    }

    public void setEstantesList(List<Estantes> estantesList) {
        this.estantesList = estantesList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into sucursales(nombre) values(:nombre)";
            if (codigo != null) {
                query = "update sucursales set nombre=:nombre where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("nombre", this.nombre);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar sucursales");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Sucursales> list() {
        List<Sucursales> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM sucursales";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Sucursales(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista sucursales");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Sucursales find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM sucursales where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Sucursales(
                    rs.getInt(1),
                    rs.getString(2));
        } catch (Exception e) {
            System.err.println("Error al obtener sucursales");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
