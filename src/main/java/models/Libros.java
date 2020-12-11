package models;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utils.JDBCMySQL;

public class Libros {

    private Integer codigo;
    private String titulo;
    private String etiqueta;
    private Date publicado;
    private Short edicion;
    private String isbn;
    private BigDecimal precioRenta;
    private List<Temas> temasList;
    private List<Proveedores> proveedoresList;
    private List<CajonLibros> cajonLibrosList;
    private LibroClasificaciones codigoClasificacion;
    private Autores codigoAutor;
    private LibroTipos codigoTipo;

    public Libros() {
    }

    public Libros(Integer codigo, String titulo, String etiqueta, Date publicado, Short edicion, String isbn, BigDecimal precioRenta, LibroClasificaciones codigoClasificacion, Autores codigoAutor, LibroTipos codigoTipo) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.etiqueta = etiqueta;
        this.publicado = publicado;
        this.edicion = edicion;
        this.isbn = isbn;
        this.precioRenta = precioRenta;
        this.codigoClasificacion = codigoClasificacion;
        this.codigoAutor = codigoAutor;
        this.codigoTipo = codigoTipo;
    }

    public Libros(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public Date getPublicado() {
        return publicado;
    }

    public void setPublicado(Date publicado) {
        this.publicado = publicado;
    }

    public Short getEdicion() {
        return edicion;
    }

    public void setEdicion(Short edicion) {
        this.edicion = edicion;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BigDecimal getPrecioRenta() {
        return precioRenta;
    }

    public void setPrecioRenta(BigDecimal precioRenta) {
        this.precioRenta = precioRenta;
    }

    public List<Temas> getTemasList() {
        if(this.temasList ==null){
            this.temasList = (new Temas().ListbyLibro(this.codigo));
        }
        return temasList;
    }

    public void setTemasList(List<Temas> temasList) {
        this.temasList = temasList;
    }

    public List<Proveedores> getProveedoresList() {
        if (this.proveedoresList == null) {
            this.proveedoresList = (new Proveedores().ListbyLibros(this.codigo));
        }
        return proveedoresList;
    }

    public void setProveedoresList(List<Proveedores> proveedoresList) {
        this.proveedoresList = proveedoresList;
    }

    public List<CajonLibros> getCajonLibrosList() {
        if (this.cajonLibrosList == null) {
            this.cajonLibrosList = (new CajonLibros().ListbyLibro(this.codigo));
        }
        return cajonLibrosList;
    }

    public void setCajonLibrosList(List<CajonLibros> cajonLibrosList) {
        this.cajonLibrosList = cajonLibrosList;
    }

    public LibroClasificaciones getCodigoClasificacion() {
        return codigoClasificacion;
    }

    public void setCodigoClasificacion(LibroClasificaciones codigoClasificacion) {
        this.codigoClasificacion = codigoClasificacion;
    }

    public Autores getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(Autores codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public LibroTipos getCodigoTipo() {
        return codigoTipo;
    }

    public void setCodigoTipo(LibroTipos codigoTipo) {
        this.codigoTipo = codigoTipo;
    }

    public boolean save() {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String fecha = format.format(this.publicado);
            Map<String, Object> params = new HashMap<>();
            String query = "insert into libros(titulo,etiqueta,publicado,edicion,isbn,precio_renta,codigo_clasificacion,codigo_autor,codigo_tipo) values(:titulo,:etiqueta,:publicado,:edicion,:isbn,:precio_renta,:codigo_clasificacion,:codigo_autor,:codigo_tipo)";
            if (codigo != null) {
                query = "update libros set titulo=:titulo,etiqueta=:etiqueta,publicado=:publicado,edicion=:edicion,isbn=:isbn,precio_renta=:precio_renta,codigo_clasificacion=:codigo_clasificacion,codigo_autor=:codigo_autor,codigo_tipo=:codigo_tipo where codigo=:codigo";
                params.put("codigo", this.codigo);
            }
            params.put("titulo", this.titulo);
            params.put("etiqueta", this.etiqueta);
            params.put("publicado", fecha);
            params.put("edicion", this.edicion);
            params.put("isbn", this.isbn);
            params.put("precio_renta", this.precioRenta);
            params.put("codigo_clasificacion", this.codigoClasificacion.getCodigo());
            params.put("codigo_autor", this.codigoAutor.getCodigo());
            params.put("codigo_tipo", this.codigoTipo.getCodigo());
            JDBCMySQL msql = new JDBCMySQL();
            
            return msql.execute(query, params);
        } catch (Exception e) {
            System.err.println("Error al guardar libros");
            System.err.println(e.getMessage());
        }
        return false;
    }

    public List<Libros> List() {
        return this.fillList("SELECT * FROM libros");
    }

    public List<Libros> ListbyAutor(int id) {
        return this.fillList("SELECT * FROM libros where codigo_autor = " + id);
    }

    private List<Libros> fillList(String sql) {
        List<Libros> list = new ArrayList<>();
        JDBCMySQL mysql = new JDBCMySQL();
        ResultSet rs = mysql.query(sql, null);
        try {
            while (rs.next()) {
                list.add(new Libros(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getShort(5),
                        rs.getString(6),
                        rs.getBigDecimal(7),
                        LibroClasificaciones.find(rs.getInt(8)),
                        Autores.find(rs.getInt(9)),
                        LibroTipos.find(rs.getInt(10))
                ));
            }
        } catch (Exception e) {
            System.err.println("Error al obtener lista libros");
            System.err.println(e.getMessage());
        }
        return list;
    }

    public static Libros find(int id) {
        JDBCMySQL mysql = new JDBCMySQL();
        String sql = "SELECT * FROM libros where codigo = " + id;
        ResultSet rs = mysql.query(sql, null);
        try {
            rs.next();
            return new Libros(
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDate(4),
                    rs.getShort(5),
                    rs.getString(6),
                    rs.getBigDecimal(7),
                    LibroClasificaciones.find(rs.getInt(8)),
                    Autores.find(rs.getInt(9)),
                    LibroTipos.find(rs.getInt(10))
            );
        } catch (Exception e) {
            System.err.println("Error al obtener libros");
            System.err.println(e.getMessage());
        }
        return null;
    }

//    public static void main(String[] args) {
//        Libros a = Libros.find(1);
//        System.out.println(a.getTitulo());
//        System.out.println(a.getCodigoClasificacion().getNombre());
//        System.out.println(a.getCodigoTipo().getNombre());
//        System.out.println(a.getCodigoAutor().getNombre());
//
//        for (Temas lib : a.getTemasList()){
//            System.out.println(lib.getNombre());
//        }
//    }
}
