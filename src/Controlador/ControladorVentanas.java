package Controlador;

import Modelo.ConsultasRegistro;
import Modelo.User;
import Vista.Central;
import Vista.Crear_Cuenta;
import Vista.Iniciar_Sesion;
import static Vista.Iniciar_Sesion.sr;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorVentanas implements ActionListener {

    private Iniciar_Sesion IS;
    private Crear_Cuenta CC;
    private Central CE;
    private ConsultasRegistro cr;

    public ControladorVentanas(Iniciar_Sesion IS, Crear_Cuenta CC, Central CE) {
        this.CC = CC;
        this.cr = cr;
        this.IS = IS;
        this.CE = CE;

        this.IS.bntISISBLACK.addActionListener(this);
        this.CC.btnCCCCBLACK.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == IS.bntISISBLACK) {
            RegistroDocentes rd = new RegistroDocentes();
            CE.GenerarCodigoQR(rd.toString());
            String user = IS.txtcorreoISBLACK.getText();
            char[] password = IS.txtcontraseñaISBLACK.getPassword();

            if (ControladorUsuario.validateEmail(user)) {
                IS.txtcorreonovalido1.setText("Correo valido");
                IS.txtcorreonovalido.setText("");

            } else {
                IS.txtcorreonovalido.setText("Correo no permitido");
                IS.txtcorreonovalido1.setText("");

                return;
            }
            ControladorUsuario cus = new ControladorUsuario();
            User us = cus.authenticate(user, password);
            CE.jTextField1.setText(us.getNombre());
            if (us != null) {
                CE.setVisible(true);
                IS.dispose();
            } else {
                IS.txtcorreonovalido.setText("Credenciales no coinciden");
            }
        }

        if (e.getSource() == IS.bntCCISBLACK) {
            CC.setVisible(true);
            IS.dispose();
        }
    }
}
