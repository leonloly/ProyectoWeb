package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class UsuarioPerfiles {

    private Integer codigo;
    private String nombre;
    private List<Usuarios> usuariosList;

    public UsuarioPerfiles() {
    }

    public UsuarioPerfiles(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public UsuarioPerfiles(Integer codigo) {
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

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into usuario_perfiles(codigo,nombre) values(:codigo,:nombre)";
            if (codigo != null) {
                query = "update usuario_perfiles set nombre=:nombre where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("nombre", this.nombre);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar usuario_perfiles");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<UsuarioPerfiles> list() {
        List<UsuarioPerfiles> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM usuario_perfiles";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new UsuarioPerfiles(
                    rs.getInt(1),
                    rs.getString(2)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista usuario_perfiles");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static UsuarioPerfiles find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM usuario_perfiles where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new UsuarioPerfiles(
                    rs.getInt(1),
                    rs.getString(2)
            );
        } catch (Exception e) {
            System.err.println("Error al obtener usuario_perfiles");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
