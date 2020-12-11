package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class MovimientoDetalles {

    private Integer codigo;
    private Movimientos codigoMovimiento;
    private CajonLibros librosCajon;
    private Date fechaEntrega;
    private Integer diasPrestamos;

    public MovimientoDetalles() {
    }

    public MovimientoDetalles(Integer codigo, Movimientos codigoMovimiento, CajonLibros librosCajon, Date fechaEntrega, Integer diasPrestamos) {
        this.codigo = codigo;
        this.codigoMovimiento = codigoMovimiento;
        this.librosCajon = librosCajon;
        this.fechaEntrega = fechaEntrega;
        this.diasPrestamos = diasPrestamos;
    }

    public MovimientoDetalles(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Integer getDiasPrestamos() {
        return diasPrestamos;
    }

    public void setDiasPrestamos(Integer diasPrestamos) {
        this.diasPrestamos = diasPrestamos;
    }

    public Movimientos getCodigoMovimiento() {
        return codigoMovimiento;
    }

    public void setCodigoMovimiento(Movimientos codigoMovimiento) {
        this.codigoMovimiento = codigoMovimiento;
    }

    public CajonLibros getLibrosCajon() {
        return librosCajon;
    }

    public void setLibrosCajon(CajonLibros librosCajon) {
        this.librosCajon = librosCajon;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into movimiento_detalles(codigo,codigo_movimiento,libros_cajon,fecha_entrega,dias_prestamos) values(:codigo,:codigo_movimiento,:libros_cajon,:fecha_entrega,:dias_prestamos)";
            if (codigo != null) {
                query = "update movimiento_detalles set codigo_movimiento=:codigo_movimiento,libros_cajon=:libros_cajon,fecha_entrega=:fecha_entrega,dias_prestamos=:dias_prestamos where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("codigo_movimiento", this.codigoMovimiento);
            params.put("libros_cajon", this.librosCajon);
            params.put("fecha_entrega", this.fechaEntrega);
            params.put("dias_prestamos", this.diasPrestamos);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar movimiento_detalles");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<MovimientoDetalles> List() {
        return this.fillList("SELECT * FROM movimiento_detalles");
    }

    public List<MovimientoDetalles> ListbyCajonLibros(int id) {
        return this.fillList("SELECT * FROM movimiento_detalles WHERE codigo=" + id);
    }

    public List<MovimientoDetalles> fillList(String sql) {
        List<MovimientoDetalles> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
//        String sql = "SELECT * FROM movimiento_detalles";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new MovimientoDetalles(
                        rs.getInt(1),
                        Movimientos.find(rs.getInt(2)),
                        CajonLibros.find(rs.getInt(3)),
                        rs.getDate(4),
                        rs.getInt(5)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista movimiento_detalles");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static MovimientoDetalles find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM movimiento_detalles where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new MovimientoDetalles(
                    rs.getInt(1),
                        Movimientos.find(rs.getInt(2)),
                        CajonLibros.find(rs.getInt(3)),
                        rs.getDate(4),
                        rs.getInt(5)
            );
        } catch (Exception e) {
            System.err.println("Error al obtener movimiento_detalles");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
