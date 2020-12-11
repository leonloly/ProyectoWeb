package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import utils.JDBCMySQL;

public class MovimientoEstados {

    private Integer codigo;
    private String nombre;
    private List<Movimientos> movimientosList;

    public MovimientoEstados() {
    }

    public MovimientoEstados(Integer codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public MovimientoEstados(Integer codigo) {
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

    public List<Movimientos> getMovimientosList() {
        if(this.movimientosList == null){
            this.movimientosList = (new Movimientos().ListbyMovimientoDetalles(this.codigo));
        }
        return movimientosList;
    }

    public void setMovimientosList(List<Movimientos> movimientosList) {
        this.movimientosList = movimientosList;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into movimiento_estados(codigo,nombre) values(:codigo,:nombre)";
            if (codigo != null) {
                query = "update movimiento_estados set nombre=:nombre where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("nombre", this.nombre);
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar movimiento_estados");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<MovimientoEstados> list() {
        List<MovimientoEstados> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM movimiento_estados";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new MovimientoEstados(
                         rs.getInt(1),
                          rs.getString(2)
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista movimiento_estados");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static MovimientoEstados find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM movimiento_estados where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new MovimientoEstados(
                    rs.getInt(1),
                    rs.getString(2)
            );
        } catch (Exception e) {
            System.err.println("Error al obtener movimiento_estados");
            System.err.println(e.getMessage());
        }
        return null;
    }
}
