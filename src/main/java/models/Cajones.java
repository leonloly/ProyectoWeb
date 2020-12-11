package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class Cajones {

    private Integer codigo;
    private Integer cantidad;
    private Estantes codigoEstante;
    private List<CajonLibros> cajonLibrosList;

    public Cajones() {
    }

    public Cajones(Integer codigo, Integer cantidad, Estantes codigoEstante) {
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.codigoEstante = codigoEstante;
    }

    public Cajones(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Estantes getCodigoEstante() {
        return codigoEstante;
    }

    public void setCodigoEstante(Estantes codigoEstante) {
        this.codigoEstante = codigoEstante;
    }

    public List<CajonLibros> getCajonLibrosList() {
        if(this.cajonLibrosList == null){
            this.cajonLibrosList = (new CajonLibros().ListbyLibro(this.codigo));
        }
        return cajonLibrosList;
    }

    public void setCajonLibrosList(List<CajonLibros> cajonLibrosList) {
        this.cajonLibrosList = cajonLibrosList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into cajones(cantidad,codigo_estante) values(:cantidad,:codigo_estante)";
            if (codigo != null) {
                query = "update cajones set cantidad=:cantidad,codigo_estante=:codigo_estante where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("cantidad", this.cantidad);
            params.put("codigo_estante", this.codigoEstante.getCodigo());
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar cajones");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Cajones> List() {
        return this.fillList("SELECT * FROM cajones");
    }

    public List<Cajones> ListbyCajonLibros(int id) {
        return this.fillList("SELECT * FROM cajones Where codigo =" + id);
    }

    public List<Cajones> fillList(String sql) {
        List<Cajones> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
//        String sql = "SELECT * FROM cajones";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Cajones(
                        rs.getInt(1),
                        rs.getInt(2),
                        Estantes.find(rs.getInt(3))
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista cajones");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Cajones find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM cajones where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Cajones(
                    rs.getInt(1),
                    rs.getInt(2),
                    Estantes.find(rs.getInt(3))
            );
        } catch (Exception e) {
            System.err.println("Error al obtener cajones");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
