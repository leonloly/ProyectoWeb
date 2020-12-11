package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class LibroClasificaciones {

    private Integer codigo;
    private String nombre;
    private List<Libros> librosList;

    public LibroClasificaciones() {
    }

    public LibroClasificaciones(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public LibroClasificaciones(Integer codigo) {
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

    public List<Libros> getLibrosList() {
        return librosList;
    }

    public void setLibrosList(List<Libros> librosList) {
        this.librosList = librosList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into libro_clasificaciones(nombre) values(:nombre)";
            if (codigo != null) {
                query = "update libro_clasificaciones set nombre=:nombre where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("nombre", this.nombre);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar libro_clasificaciones");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<LibroClasificaciones> list() {
        List<LibroClasificaciones> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM libro_clasificaciones";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new LibroClasificaciones(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista libro_clasificaciones");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static LibroClasificaciones find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM libro_clasificaciones where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new LibroClasificaciones(
                    rs.getInt(1),
                    rs.getString(2)
            );
        } catch (Exception e) {
            System.err.println("Error al obtener libro_clasificaciones");
            System.err.println(e.getMessage());
        }
        return null;
    }

}
