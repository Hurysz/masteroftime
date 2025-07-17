package Controlador;

import Modelo.ConsultasRegistro;
import Modelo.User;
import Vista.Central;
import com.lowagie.text.pdf.PdfWriter;
import com.mysql.cj.jdbc.Blob;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.Document;

public class ControladorRegistro implements ActionListener, ListSelectionListener {

    private ConsultasRegistro cr;
    int selectedIndex;
    private Central ce;
    private String info;

    public void valueChanged(ListSelectionEvent e) {
        ListSelectionModel lsm = (ListSelectionModel) e.getSource();
        selectedIndex = e.getFirstIndex();
    }

    public ControladorRegistro(ConsultasRegistro cr, Central ce) {
        this.cr = cr;
        this.ce = ce;

        //==========BTNs DE CENTRAL==========
        ce.btnguardar.addActionListener(this);
        ce.btnbuscar.addActionListener(this);
        ce.btneliminar1.addActionListener(this);
        ce.btneditar.addActionListener(this);
        ce.btnlimpiar.addActionListener(this);
        ce.btndescargar.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //======BTN GUARDAR======
        if (e.getSource() == ce.btnguardar) {

            String nombre = ce.txtnombredocente.getText();
            String facultad = (String) ce.cbxfacultades.getSelectedItem();
            String carrera = (String) ce.cbxcarreras.getSelectedItem();
            String fecha = ce.txtfecharegistrada.getText();
            String hora = ce.txthoraregistrada.getText();
            String correo = ce.txtcorreo.getText();

            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            try {
                Date dataFormateada = formato.parse(fecha);
                DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
                String strDate = dateFormat.format(dataFormateada);
                final RegistroDocentes rd = cr.registrar(nombre, facultad, carrera, strDate, hora, correo);
                if (rd != null) {
                    ce.GenerarCodigoQR(rd.toString());
                    JOptionPane.showMessageDialog(null, "Docente registrado");
                    limpiar();
                } else {
                    JOptionPane.showMessageDialog(null, "Error al agregar");
                    limpiar();
                }
            } catch (ParseException ex) {
                Logger.getLogger(ControladorRegistro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        //======BTNMODIFICAR======
        if (e.getSource() == ce.btneditar) {
            String ls = ce.jlista.getSelectedValue();
            int lszx = ce.jlista.getSelectedIndex();
            int id = (int) ce.modelist.getElementAt(0);
            RegistroDocentes rd = cr.buscarPorId(id);
            String input;
            switch (lszx) {
                case 0:
                    JOptionPane.showMessageDialog(null, "No se puede modificar el id");
                    break;
                case 1:
                    input = JOptionPane.showInputDialog("Ingrese nuevo valor");
                    ce.modelist.setElementAt(input, lszx);
                    rd.setNombredocente(input);
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "No se puede editar");
                    break;
                case 3:
                    JOptionPane.showMessageDialog(null, "No se puede editar");
                    break;
                case 4:
                    input = JOptionPane.showInputDialog("Ingrese nuevo valor");
                    ce.modelist.setElementAt(input, lszx);
                    rd.setFacultad(input);
                    break;
                case 5:
                    input = JOptionPane.showInputDialog("Ingrese nuevo valor");
                    ce.modelist.setElementAt(input, lszx);
                    rd.setCarrera(input);
                    break;
                case 6:
                    input = JOptionPane.showInputDialog("Ingrese nuevo valor");
                    ce.modelist.setElementAt(input, lszx);
                    rd.setCorreo(input);
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Seleccione un campo a editar");
            }
            rd.sync();
        }

        //======BTNELIMINAR======
        if (e.getSource() == ce.btneliminar1) {
            int id = (int) ce.modelist.getElementAt(0);
            int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea realemente eliminar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION);   //0=yes  1=No
            if (respuesta == 0) {
                cr.eliminarporid(id);
                JOptionPane.showMessageDialog(null, "Docente eliminado");
            }
        }

        //======BNTNBUSCAR======
        if (e.getSource() == ce.btnbuscar) {
            final String busqueda = ce.txtregistrobuscar.getText();
            final RegistroDocentes rd = cr.buscarnom(busqueda);
            ce.modelist.clear();

            if (rd != null) {
                ce.txtqr.setIcon(ControladorQR.loadImage(rd, ce.txtqr.getHeight(), ce.txtqr.getWidth()));
                ce.modelist.addElement(rd.getId());
                ce.modelist.addElement(rd.getNombredocente());
                ce.modelist.addElement(rd.getHoraregistrada());
                ce.modelist.addElement(rd.getFecharegistrada());
                ce.modelist.addElement(rd.getFacultad());
                ce.modelist.addElement(rd.getCarrera());
                ce.modelist.addElement(rd.getCorreo());

                limpiar();
            } else {
                JOptionPane.showMessageDialog(null, "Docente no encontrado");
                limpiar();
            }
        }
        if (e.getSource() == ce.btnlimpiar) {
            limpiar();
        }

        //DESCARGAR
        if (e.getSource() == ce.btndescargar) {
            final String descarga = ce.txtregistrobuscar.getText();
            final RegistroDocentes rd = cr.buscarnom(descarga);

            if (rd != null) {
                String contentToWrite = "BIENVENIDO A LA INFORMACIÓN COMPLETA Y PORTATIL DE REGISTROS DE DOCENTES"
                        + "\n-------------------------------------------------------------------------"
                        + "\n"
                        + "\n--------------"
                        + "\n= ID REGISTRO: " + rd.getId()
                        + "\n--------------"
                        + "\n"
                        + "\n--------------------------------"
                        + "\n= NOMBRE DEL DOCENTE REGISTRADO: " + rd.getNombredocente()
                        + "\n--------------------------------"
                        + "\n"
                        + "\n--------------------"
                        + "\n= CORREO REGISTRADO:" + rd.getCorreo()
                        + "\n--------------------"
                        + "\n"
                        + "\n----------------------------------"
                        + "\n= FACULTAD DEL DOCENTE REGISTRADO: " + rd.getFacultad()
                        + "\n----------------------------------"
                        + "\n"
                        + "\n---------------------------------"
                        + "\n= CARRERA DEL DOCENTE REGISTRADO:" + rd.getCarrera()
                        + "\n---------------------------------"
                        + "\n"
                        + "\n-------------------"
                        + "\n= FECHA REGISTRADA: " + rd.getFecharegistrada()
                        + "\n-------------------"
                        + "\n"
                        + "\n------------------"
                        + "\n= HORA REGISTRADA:" + rd.getHoraregistrada()
                        + "\n------------------"
                        + "\n"
                        + "\n----------------------------------------------------------"
                        + "\n GRACIAS POR USAR NUESTRO PROGRAMA DE REGISTRO DE DOCENTES.";
                String fileName = "Registro de " + rd.getNombredocente() + ".txt";
                String desktopPath = System.getProperty("user.home") + "/Desktop/" + fileName;

                // Crear el objeto Path para el escritorio
                Path destino = Path.of(desktopPath + "archivo.txt");
                System.out.println("Archivo almacenado en el escritorio correctamente.");

                ControladorDescarga.generateTxtFile(contentToWrite, desktopPath);

                JOptionPane.showMessageDialog(null, "Archivo descargado en el escritorio.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al descargar");
            }
        }
    }

    public void limpiar() {
        ce.txtregistrobuscar.setText(null);

    }

    /*@Override
    public void valueChanged(ListSelectionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }*/
}
    

