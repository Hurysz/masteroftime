package Modelo;

import Controlador.RegistroDocentes;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.sql.ResultSet;

//========REGISTRAR========
public class ConsultasRegistro extends Conexion {

    public RegistroDocentes registrar(String nombre, String facultad, String carrera, String fecha, String hora, String correo) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        final String sql = "INSERT INTO registro (facultad, carrera, nombre, hora, fecha, correo) VALUES (?,?,?,?,?,?)";

        try {
            ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, facultad);
            ps.setString(2, carrera);
            ps.setString(3, nombre);
            ps.setString(4, hora);
            ps.setString(5, fecha);
            ps.setString(6, correo);

            ps.execute();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            return new RegistroDocentes(id, facultad, carrera, nombre, fecha, hora, correo);

        } catch (SQLException e) {
            System.out.println(e.toString());
            return null;
        }
    }
//========MODIFICAR========

    public boolean modificar(RegistroDocentes r) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "UPDATE registro SET facultad=?, carrera=?, nombre=?, hora=?, fecha=?, correo=? where id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, r.getFacultad());
            ps.setString(2, r.getCarrera());
            ps.setString(3, r.getNombredocente());
            ps.setString(4, r.getHoraregistrada());
            ps.setString(5, r.getFecharegistrada());
            ps.setString(6, r.getCorreo());
            ps.setInt(7, r.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
//========ELIMINAR========

    public boolean eliminar(RegistroDocentes r) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "delete from registro where id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, r.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
//========BUSCAR========

    public RegistroDocentes buscarnom(String busqueda) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from registro WHERE nombre LIKE ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + busqueda + "%");
            rs = ps.executeQuery();

            if (rs.next()) {
                final int r_id = rs.getInt("id");
                final String nombre = rs.getString("nombre");
                final String facultad = rs.getString("facultad");
                final String carrera = rs.getString("carrera");
                final String hora = rs.getString("hora");
                final String fecha = rs.getString("fecha");
                final String correo = rs.getString("correo");
                return new RegistroDocentes(r_id, facultad, carrera, nombre, fecha, hora, correo);
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public RegistroDocentes buscarPorId(int id) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        ResultSet rs = null;
        String sql = "select * from registro where id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                final int r_id = rs.getInt("id");
                final String nombre = rs.getString("nombre");
                final String facultad = rs.getString("facultad");
                final String carrera = rs.getString("carrera");
                final String hora = rs.getString("hora");
                final String fecha = rs.getString("fecha");
                final String correo = rs.getString("correo");
                return new RegistroDocentes(r_id, facultad, carrera, nombre, fecha, hora, correo);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return null;
    }

    public void eliminarporid(int id) {
        PreparedStatement ps = null;
        Connection con = getConexion();
        String sql = "delete from registro where id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());

        }

    }
}
