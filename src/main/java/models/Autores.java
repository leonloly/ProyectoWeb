package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class Autores {

    private Integer codigo;
    private String nombre;
    private Paises codigoPais;
    private List<Libros> librosList;

    public Autores(Integer codigo, String nombre, Paises codigoPais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.codigoPais = codigoPais;
    }

    public Autores() {
    }

    public Autores(Integer codigo) {
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

    public Paises getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(Paises codigoPais) {
        this.codigoPais = codigoPais;
    }

    public List<Libros> getLibrosList() {
        if (this.librosList == null) {
            this.librosList = (new Libros().ListbyAutor(this.codigo));
        }
        return this.librosList;
    }

    public void setLibrosList(List<Libros> librosList) {
        this.librosList = librosList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into autores(nombre,codigo_pais) values(:nombre,:codigo_pais)";
            if (this.codigo != null) {
                query = "update autores set nombre=:nombre,codigo_pais=:codigo_pais where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("nombre", this.nombre);
            params.put("codigo_pais", this.codigoPais.getCodigo());
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar autores");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Autores> list() {
        List<Autores> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM autores";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Autores(
                        rs.getInt(1),
                        rs.getString(2),
                        Paises.find(rs.getInt(3))
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista autores");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Autores find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM autores where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Autores(
                    rs.getInt(1),
                    rs.getString(2),
                    Paises.find(rs.getInt(3))
            );
        } catch (Exception e) {
            System.err.println("Error al obtener autores");
            System.err.println(e.getMessage());
        }
        return null;
    }

 

}
