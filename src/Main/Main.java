package Main;

import Controlador.ControladorRegistro;
import Controlador.RegistroDocentes;
import Modelo.Conexion;
import Modelo.ConsultasRegistro;
import Vista.Central;
import Vista.Crear_Cuenta;
import Vista.Iniciar_Sesion;

public class Main {

    public static void main(String[] args) {
        String email;
        String password;
        for (String arg : args) {
            String[] splittedArg = arg.split("=");

            switch (splittedArg[0]) {
                case "-e":
                    if (splittedArg.length > 1) {
                        email = splittedArg[1];
                    }
                    break;
                case "-p":
                    if (splittedArg.length > 1) {
                        password = splittedArg[1];
                    }
            }
        }
        Iniciar_Sesion IS = new Iniciar_Sesion();
        IS.setVisible(true);
    }
}
