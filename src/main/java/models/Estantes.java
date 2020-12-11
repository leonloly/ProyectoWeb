package models;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utils.JDBCMySQL;
import java.util.List;

public class Estantes {

    private Integer codigo;
    private Integer cajones;
    private List<Cajones> cajonesList;
    private Sucursales codigoSucuarsal;

    public Estantes() {
    }

    public Estantes(Integer codigo, Integer cajones, Sucursales codigoSucuarsal) {
        this.codigo = codigo;
        this.cajones = cajones;
        this.codigoSucuarsal = codigoSucuarsal;
    }

    public Estantes(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCajones() {
        return cajones;
    }

    public void setCajones(Integer cajones) {
        this.cajones = cajones;
    }

    public List<Cajones> getCajonesList() {
        if(this.cajonesList == null){
            this.cajonesList = new Cajones().ListbyCajonLibros(1);
        }
        return cajonesList;
    }

    public void setCajonesList(List<Cajones> cajonesList) {
        this.cajonesList = cajonesList;
    }

    public Sucursales getCodigoSucuarsal() {
        return codigoSucuarsal;
    }

    public void setCodigoSucuarsal(Sucursales codigoSucuarsal) {
        this.codigoSucuarsal = codigoSucuarsal;
    }

    public boolean save() {
        try {
            Map<String, Object> params = new HashMap<>();
            String query = "insert into estantes(cajones,codigo_sucuarsal) values(:cajones,:codigo_sucuarsal)";
            if (codigo != null) {
                query = "update estantes set cajones=:cajones,codigo_sucuarsal=:codigo_sucuarsal where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("cajones", this.cajones);
            params.put("codigo_sucuarsal", this.codigoSucuarsal.getCodigo());
            JDBCMySQL msql = new JDBCMySQL();
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar estantes");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Estantes> list() {
        List<Estantes> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM estantes";
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Estantes(
                        rs.getInt(1),
                        rs.getInt(2),
                        Sucursales.find(rs.getInt(3))
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista estantes");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Estantes find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM estantes where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Estantes(
                    rs.getInt(1),
                    rs.getInt(2),
                    Sucursales.find(rs.getInt(3))
            );
        }catch(Exception e){
            System.err.println("Error al obtener estantes");
            System.err.println(e.getMessage());
        }
        return null;
    }
//
//    public static void main(String[] args){
//        Estantes a = Estantes.find(1);
//        System.out.println(a.getCajones());
//    }
}
