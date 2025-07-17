package Modelo;

import java.sql.*;
import java.sql.DriverManager;

public class Conexion {

    private String base = "BD_RegistroDocentes1";
    private String user = "root";
    private String password = "";
    private String url = "jdbc:mysql://localhost:3306/" + base;
    private Connection con = null;

    public Connection getConexion() {
        try {
            con = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Conectado");
        } catch (Exception er) {
            System.out.println(er.toString());
        }
        return con;
    }

    public static void cerrar(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
    

