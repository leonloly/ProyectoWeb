package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class Proveedores {

    private Integer codigo;
    private String nombre;
    private Integer telefono;
    private String correo;
    private List<Libros> librosList;

    public Proveedores(Integer codigo, String nombre, Integer telefono, String correo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }

    public Proveedores() {
    }

    public Proveedores(Integer codigo) {
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Libros> getLibrosList() {
        if(this.librosList ==null){
            this.librosList = (new Libros().ListbyAutor(this.codigo));
        }
        return librosList;
    }

    public void setLibrosList(List<Libros> librosList) {
        this.librosList = librosList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into proveedores(nombre,telefono,correo) values(:nombre,:telefono,:correo)";
            if (codigo != null) {
                query = "update proveedores set nombre=:nombre,telefono=:telefono,correo=:correo where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("nombre", this.nombre);
            params.put("telefono", this.telefono);
            params.put("correo", this.correo);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar proveedores");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Proveedores> List() {
        return this.fillList("SELECT * FROM proveedores");
    }

    public List<Proveedores> ListbyLibros(int id) {
        return this.fillList("SELECT * FROM proveedores where codigo = " + id);
    }

    public List<Proveedores> fillList(String sql) {
        List<Proveedores> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
//        String sql = "SELECT * FROM proveedores";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Proveedores(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getString(4)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista proveedores");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Proveedores find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM proveedores where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Proveedores(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getInt(3),
                    rs.getString(4)
            );
        } catch (Exception e) {
            System.err.println("Error al obtener proveedores");
            System.err.println(e.getMessage());
        }
        return null;
    }
//    
//        public static void main(String[] args) {
//        Proveedores a = Proveedores.find(1);
//        System.out.println(a.getNombre());
//
//        for (Libros lib : a.getLibrosList()) {
//            System.out.println(lib.getTitulo());
//            System.out.println(lib.getCodigoClasificacion().getNombre());
//        }
//    }
}
