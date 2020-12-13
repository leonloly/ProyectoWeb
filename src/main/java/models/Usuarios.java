package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class Usuarios {

    private Integer codigo;
    private String nombre;
    private String apellido;
    private String carnet;
    private List<Movimientos> movimientosList;
    private UsuarioPerfiles codigoPerfil;
    private String passworduser;

    public Usuarios() {
    }

    public Usuarios(Integer codigo, String nombre, String apellido, String carnet, UsuarioPerfiles codigoPerfil, String passworduser) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.carnet = carnet;
        this.codigoPerfil = codigoPerfil;
        this.passworduser = passworduser;
    }

    public String getPassworduser() {
        return passworduser;
    }

    public void setPassworduser(String passworduser) {
        this.passworduser = passworduser;
    }

    public Usuarios(Integer codigo) {
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public List<Movimientos> getMovimientosList() {
        return movimientosList;
    }

    public void setMovimientosList(List<Movimientos> movimientosList) {
        this.movimientosList = movimientosList;
    }

    public UsuarioPerfiles getCodigoPerfil() {
        return codigoPerfil;
    }

    public void setCodigoPerfil(UsuarioPerfiles codigoPerfil) {
        this.codigoPerfil = codigoPerfil;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into usuarios(nombre,apellido,carnet,codigo_perfil, passworduser) values(:nombre,:apellido,:carnet,:codigo_perfil, :passworduser)";
            if (codigo != null) {
                query = "update usuarios set nombre=:nombre,apellido=:apellido,carnet=:carnet,codigo_perfil=:codigo_perfil, passworduser=:passworduser where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("nombre", this.nombre);
            params.put("apellido", this.apellido);
            params.put("carnet", this.carnet);
            params.put("codigo_perfil", this.codigoPerfil.getCodigo());
            params.put("passworduser", this.passworduser);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar usuarios");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Usuarios> list() {
        List<Usuarios> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM usuarios";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Usuarios(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        UsuarioPerfiles.find(rs.getInt(5)),
                        rs.getString(6)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista usuarios");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Usuarios find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM usuarios where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Usuarios(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    UsuarioPerfiles.find(rs.getInt(5)),
                    rs.getString(6)
            );
        } catch (Exception e) {
            System.err.println("Error al obtener usuarios");
            System.err.println(e.getMessage());
        }
        return null;
    }

    public Usuarios login(String user, String password) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM usuarios where carnet = :carnet";
        Map<String, Object> params = new HashMap<>();
        params.put("carnet", user);
        ResultSet rs = mysql.query(sql, params);
        try {
            rs.next();
            this.codigo = rs.getInt(1);
            this.nombre = rs.getString(2);
            this.apellido = rs.getString(3);
            this.carnet = rs.getString(4);
            this.codigoPerfil = UsuarioPerfiles.find(rs.getInt(5));
            this.passworduser = rs.getString(6);
            if (passworduser.equals(password)) {
                return this;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error al inciar sesion");
        }
        return null;
    }
}
