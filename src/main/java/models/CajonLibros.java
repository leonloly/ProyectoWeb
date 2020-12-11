package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class CajonLibros {

    private Integer codigo;
    private Libros codigoLibro;
    private Cajones codigoCajon;
    private List<MovimientoDetalles> movimientoDetallesList;

    public CajonLibros() {
    }

    public CajonLibros(Integer codigo, Libros codigoLibro, Cajones codigoCajon) {
        this.codigo = codigo;
        this.codigoLibro = codigoLibro;
        this.codigoCajon = codigoCajon;
    }

    public CajonLibros(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Libros getCodigoLibro() {
        return codigoLibro;
    }

    public void setCodigoLibro(Libros codigoLibro) {
        this.codigoLibro = codigoLibro;
    }

    public Cajones getCodigoCajon() {
        return codigoCajon;
    }

    public void setCodigoCajon(Cajones codigoCajon) {
        this.codigoCajon = codigoCajon;
    }

    public List<MovimientoDetalles> getMovimientoDetallesList() {
        if(this.movimientoDetallesList == null){
            this.movimientoDetallesList = (new MovimientoDetalles().ListbyCajonLibros(this.codigo));
        }
        return movimientoDetallesList;
    }

    public void setMovimientoDetallesList(List<MovimientoDetalles> movimientoDetallesList) {
        this.movimientoDetallesList = movimientoDetallesList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into cajon_libros(codigo,codigo_libro,codigo_cajon) values(:codigo,:codigo_libro,:codigo_cajon)";
            if (codigo != null) {
                query = "update cajon_libros set codigo_libro=:codigo_libro,codigo_cajon=:codigo_cajon where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("codigo_libro", this.codigoLibro);
            params.put("codigo_cajon", this.codigoCajon);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar cajon_libros");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<CajonLibros> List() {
        return this.fillList("SELECT * FROM cajon_libros ");
    }

    public List<CajonLibros> ListbyLibro(int id) {
        return this.fillList("SELECT * FROM cajon_libros Where codigo =" + id);
    }

    public List<CajonLibros> fillList(String sql) {
        List<CajonLibros> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
//        String sql = "SELECT * FROM cajon_libros";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new CajonLibros(
                        rs.getInt(1),
                        Libros.find(rs.getInt(2)),
                        Cajones.find(rs.getInt(3))
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista cajon_libros");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static CajonLibros find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM cajon_libros where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new CajonLibros(
                    rs.getInt(1),
                    Libros.find(rs.getInt(2)),
                    Cajones.find(rs.getInt(3))
            );
        } catch (Exception e) {
            System.err.println("Error al obtener cajon_libros");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
