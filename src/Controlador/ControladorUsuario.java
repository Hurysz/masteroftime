package Controlador;

import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import Modelo.Conexion;
import Modelo.User;
import static Vista.Iniciar_Sesion.ce;
import java.awt.event.ActionListener;

public class ControladorUsuario {

    private final Connection conexion;

    public ControladorUsuario() {
        this.conexion = new Conexion().getConexion();
    }

    public static boolean validateEmail(String email) {
        String regex = "[a-zA-Z0-9_+&*-.]+@[^-][a-zA-Z0-9-]+[^-]\\.\\w{2,7}";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(email).matches();
    }

    public User authenticate(String email, char[] password) {
        final String query = "SELECT id, nombre FROM sesiones WHERE correo=? AND password=?";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, String.valueOf(password));

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    final int id = resultSet.getInt("id");
                    final String nombre = resultSet.getString("nombre");
                    return new User(id, nombre);
                }
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public int registrar(final String correo, final String nombre) {
        final int pw = genPassword();

        final String query = "INSERT INTO sesiones (correo, nombre, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            // TODO: ENVIAR CORREO CON CONTRASEÑA

            statement.setString(1, correo);
            statement.setString(2, nombre);
            statement.setInt(3, pw);

            final int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas == 1) {
            } else {
                throw new SQLException("La inserción no fue exitosa.");
            }

        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return pw;
    }

    static private int genPassword() {
        SecureRandom random = new SecureRandom();
        return random.nextInt(9000) + 1000;
    }
}
    
