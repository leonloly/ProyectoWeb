package models;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class Movimientos {

    private Integer codigo;
    private MovimientoEstados estado;
    private Usuarios usuario;
    private Date fechaPrestamo;
    private List<MovimientoDetalles> movimientoDetallesList;

    public Movimientos() {
    }

    public Movimientos(Integer codigo, MovimientoEstados estado, Usuarios usuario, Date fechaPrestamo) {
        this.codigo = codigo;
        this.estado = estado;
        this.usuario = usuario;
        this.fechaPrestamo = fechaPrestamo;
    }

    public Movimientos(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public MovimientoEstados getEstado() {
        return estado;
    }

    public void setEstado(MovimientoEstados estado) {
        this.estado = estado;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public List<MovimientoDetalles> getMovimientoDetallesList() {
        if (this.movimientoDetallesList == null) {
            return this.movimientoDetallesList = (new MovimientoDetalles().ListbyCajonLibros(this.codigo));
        }
        return movimientoDetallesList;
    }

    public void setMovimientoDetallesList(List<MovimientoDetalles> movimientoDetallesList) {
        this.movimientoDetallesList = movimientoDetallesList;
    }

    public boolean save() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String fecha = format.format(this.fechaPrestamo);
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into movimientos(codigo,estado,usuario,fecha_prestamo) values(:codigo,:estado,:usuario,:fecha_prestamo)";
            if (codigo != null) {
                query = "update movimientos set estado=:estado,usuario=:usuario,fecha_prestamo=:fecha_prestamo where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("estado", this.estado);
            params.put("usuario", this.usuario);
            params.put("fecha_prestamo", fecha);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar movimientos");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Movimientos> List() {
        return this.fillList("SELECT * FROM movimientos");
    }

    public List<Movimientos> ListbyMovimientoDetalles(int id) {
        return this.fillList("SELECT * FROM movimientos WHERE codigo=" + id);
    }

    public List<Movimientos> fillList(String sql) {
        List<Movimientos> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
//        String sql = "SELECT * FROM movimientos";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Movimientos(
                        rs.getInt(1),
                        MovimientoEstados.find(rs.getInt(2)),
                        Usuarios.find(rs.getInt(3)),
                        rs.getDate(4)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista movimientos");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Movimientos find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM movimientos where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Movimientos(
                    rs.getInt(1),
                    MovimientoEstados.find(rs.getInt(2)),
                    Usuarios.find(rs.getInt(3)),
                    rs.getDate(4)
            );
        } catch (Exception e) {
            System.err.println("Error al obtener movimientos");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
