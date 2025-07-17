package Vista;

import Controlador.ControladorQR;
import Controlador.ControladorRegistro;
import Controlador.ControladorUsuario;
import Controlador.RegistroDocentes;
import Modelo.Conexion;
import Modelo.ConsultasRegistro;
import java.awt.FileDialog;
import java.io.ByteArrayOutputStream;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import static java.lang.System.exit;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import java.util.UUID;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

public class Central extends javax.swing.JFrame implements Runnable {

    private final Connection con = new Conexion().getConexion();
    public ImageIcon imageIcon;
    ControladorUsuario cu = new ControladorUsuario();
    public DefaultTableModel modelo = new DefaultTableModel();
    public DefaultListModel modelist = new DefaultListModel();
    String hora, minutos, segundos;
    Thread hilo;
    private static String emailFrom = "grupo8poo@gmail.com";
    private static String passwordFrom = "txjhdnobekaytwei";
    private String emailTo;
    private String subject;
    private String content;
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;
    int contador = 1;

    private String[] Factultades = {"Ciencias de la Salud", "Ciencias Empresariales", "Derecho y Humanidades", "Ingeniería y Arquitectura"};
    private String[] Carreras = {"Enfermería", "Estomatología", " Medicina", "Nutrición", "Psicología", "Tecnología Médica", "Administración", "Administración en Turismo y Hotelería", "Administración y Marketing",
        " Administración y Negocios Internacionales", "Contabilidad", "Economía", "Gestión Pública", "Arte y Diseño Gráfico Empresarial", "Ciencias de la Comunicación", "Ciencias del Deporte", "Derecho", "Educación Inicial",
        "Educación Primaria", "Traducción e Interpretación", "Arquitectura", "Ingeniería Empresarial", "Ingeniería Agroindustrial", "Ingeniería Ambiental", "Ingeniería Civil", "Ingeniería de Ciberseguridad", "Ingeniería de Minas",
        "Ingeniería de Sistemas", "Ingeniería en Ciencias de Datos", "Ingeniería Industrial", "Ingeniería Mecánica Eléctrica"};

    public Central() {
        final ConsultasRegistro cr = new ConsultasRegistro();
        Conexion con = new Conexion();
        con.getConexion();
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/iconoparaelprograma.png")).getImage());
        mProperties = new Properties();
        txtqr.setIcon(ControladorQR.generarQRAleatorio(txtqr.getWidth(), txtqr.getHeight()));
        setLocationRelativeTo(null);
        final ControladorRegistro contro = new ControladorRegistro(cr, this);
        hilo = new Thread(this);
        hilo.start();
        refrescartabla();
        jlista.setModel(modelist);
        modelo.addColumn("ID REGISTRO");
        modelo.addColumn("NOMBRE DOCENTE");
        modelo.addColumn("HORA");
        modelo.addColumn("FECHA");
        modelo.addColumn("FACULTAD");
        modelo.addColumn("CARRERA");
        modelo.addColumn("CORREO");
        cargarsugerencias();
        cargarfacultades();
        cargarcarreras();
        txtfecharegistrada.setText(fechaActual());
    }
    //ENVIAR CORREO DE ASISTENCIA
    private void createEmail() {
        final String nom = txtnombredocente.getText();
        final String fecha = txtfecharegistrada.getText();
        final String hora = txthoraregistrada.getText();

        emailTo = txtcorreo.getText().trim();
        subject = "ASISTENCIA A LA UNIVERSIDAD UCV";
        content = "¡Hola! Bienvenido a la universidad de César Vallejo los Olivos es un placer contar usted estimado(a) docente" + nom + " tuvo una fecha de asistencia de hoy " + fecha + " siendo a las "+hora;

        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        mSession = Session.getDefaultInstance(mProperties);

        mCorreo = new MimeMessage(mSession);
        try {
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content);

        } catch (AddressException ex) {
            Logger.getLogger(Crear_Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Crear_Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            JOptionPane.showMessageDialog(null, "Correo enviado");
        } catch (NoSuchProviderException ex) {
            Logger.getLogger(Crear_Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(Crear_Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public  void GenerarCodigoQR(String info){
        int anchoLabel = txtqr.getWidth();
        int altoLabel = txtqr.getHeight();
        ByteArrayOutputStream out = QRCode.from(info).to(ImageType.PNG).withSize(txtqr.getWidth(), txtqr.getHeight()).stream();
        ImageIcon imageIcon=new ImageIcon(out.toByteArray());
        this.txtqr.setIcon(imageIcon);
        
}

    private void guardarqr() {
        try {
            if (imageIcon != null) {
                FileDialog ventana = new FileDialog(Central.this, "Guardar imagen", FileDialog.SAVE);
                ventana.show();

                String fileName = ventana.getFile();
                if (fileName != null) {
                    File qr = new File(ventana.getDirectory() + fileName + ".png");
                    Image imagenqr = imageIcon.getImage();
                    ImageIO.write(toBufferedImage(imagenqr), "png", qr);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }

        BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();
        g.drawImage(img, 0, 0, null);
        g.dispose();

        return bufferedImage;
    }

    public void hora() {
        Calendar calendario = new GregorianCalendar();
        Date horaactual = new Date();
        calendario.setTime(horaactual);
        hora = calendario.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario.get(Calendar.HOUR_OF_DAY) : "0" + calendario.get(Calendar.HOUR_OF_DAY);
        minutos = calendario.get(Calendar.MINUTE) > 9 ? "" + calendario.get(Calendar.MINUTE) : "0" + calendario.get(Calendar.MINUTE);
        segundos = calendario.get(Calendar.SECOND) > 9 ? "" + calendario.get(Calendar.SECOND) : "0" + calendario.get(Calendar.SECOND);
    }

    public void run() {
        Thread current = Thread.currentThread();
        while (current == hilo) {
            hora();
            txthoraregistrada.setText(hora + ":" + minutos + ":" + segundos);
        }

    }

    private void cargarsugerencias() {
        AutoCompleteDecorator.decorate(cbxfacultades);
        AutoCompleteDecorator.decorate(cbxcarreras);
    }

    private void cargarfacultades() {
        cbxfacultades.setModel(new DefaultComboBoxModel<>(Factultades));
    }

    private void cargarcarreras() {
        cbxcarreras.setModel(new DefaultComboBoxModel<>(Carreras));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtregistrobuscar = new javax.swing.JTextField();
        btndescargar = new javax.swing.JButton();
        btnbuscar = new javax.swing.JButton();
        btneditar = new javax.swing.JButton();
        btneliminar1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        txthoraregistrada = new javax.swing.JLabel();
        cbxcarreras = new javax.swing.JComboBox<>();
        txtcorreo = new javax.swing.JTextField();
        txtfecharegistrada = new javax.swing.JTextField();
        aaaaaaaa = new javax.swing.JTextField();
        btnlimpiar = new javax.swing.JButton();
        btnguardar = new javax.swing.JButton();
        cbxfacultades = new javax.swing.JComboBox<>();
        txtnombredocente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtblregistro = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jlista = new javax.swing.JList<>();
        txtqr = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBackground(new java.awt.Color(28, 40, 51));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Buscar Docente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel3.setForeground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Britannic Bold", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("BUSCAR DOCENTE");
        jPanel3.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        txtregistrobuscar.setBackground(new java.awt.Color(46, 64, 83));
        txtregistrobuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        txtregistrobuscar.setForeground(new java.awt.Color(255, 255, 255));
        txtregistrobuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtregistrobuscarActionPerformed(evt);
            }
        });
        txtregistrobuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtregistrobuscarKeyTyped(evt);
            }
        });
        jPanel3.add(txtregistrobuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 280, 40));

        btndescargar.setBackground(new java.awt.Color(46, 64, 83));
        btndescargar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btndescargar.setForeground(new java.awt.Color(255, 255, 255));
        btndescargar.setText("DESCARGAR INFORMACIÓN");
        jPanel3.add(btndescargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 280, 40));

        btnbuscar.setBackground(new java.awt.Color(46, 64, 83));
        btnbuscar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar.setText("BUSCAR");
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });
        jPanel3.add(btnbuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 90, 30));

        btneditar.setBackground(new java.awt.Color(46, 64, 83));
        btneditar.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btneditar.setForeground(new java.awt.Color(255, 255, 255));
        btneditar.setText("EDITAR");
        btneditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditarActionPerformed(evt);
            }
        });
        jPanel3.add(btneditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 90, 30));

        btneliminar1.setBackground(new java.awt.Color(46, 64, 83));
        btneliminar1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btneliminar1.setForeground(new java.awt.Color(255, 255, 255));
        btneliminar1.setText("ELIMINAR");
        btneliminar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneliminar1ActionPerformed(evt);
            }
        });
        jPanel3.add(btneliminar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, 100, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 100, 320, 220));

        jPanel4.setBackground(new java.awt.Color(28, 40, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED)), "Nuevo Registro", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txthoraregistrada.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        txthoraregistrada.setForeground(new java.awt.Color(255, 255, 255));
        jPanel4.add(txthoraregistrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 160, 170, 60));

        cbxcarreras.setBackground(new java.awt.Color(46, 64, 83));
        cbxcarreras.setEditable(true);
        cbxcarreras.setForeground(new java.awt.Color(255, 255, 255));
        cbxcarreras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " Enfermería", " Estomatología", " Medicina", " Nutrición", " Psicología", " Tecnología Médica", " Administración", " Administración en Turismo y Hotelería", " Administración y Marketing", " Administración y Negocios Internacionales", " Contabilidad", " Economía", " Gestión Pública", " Arte y Diseño Gráfico Empresarial", " Ciencias de la Comunicación", " Ciencias del Deporte", " Derecho", " Educación Inicial", " Educación Primaria", " Traducción e Interpretación", " Arquitectura", " Ingeniería Empresarial", " Ingeniería Agroindustrial", " Ingeniería Ambiental", " Ingeniería Civil", " Ingeniería de Ciberseguridad", " Ingeniería de Minas", " Ingeniería de Sistemas", " Ingeniería en Ciencias de Datos", " Ingeniería Industrial", " Ingeniería Mecánica Eléctrica" }));
        cbxcarreras.setAutoscrolls(true);
        cbxcarreras.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Carrera", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        cbxcarreras.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        cbxcarreras.setOpaque(true);
        jPanel4.add(cbxcarreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 30, 180, 60));

        txtcorreo.setBackground(new java.awt.Color(46, 64, 83));
        txtcorreo.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        txtcorreo.setForeground(new java.awt.Color(255, 255, 255));
        txtcorreo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Correo", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        txtcorreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoActionPerformed(evt);
            }
        });
        jPanel4.add(txtcorreo, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 100, 180, 50));

        txtfecharegistrada.setEditable(false);
        txtfecharegistrada.setBackground(new java.awt.Color(46, 64, 83));
        txtfecharegistrada.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        txtfecharegistrada.setForeground(new java.awt.Color(255, 255, 255));
        txtfecharegistrada.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Fecha registrada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.add(txtfecharegistrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 180, 50));

        aaaaaaaa.setEditable(false);
        aaaaaaaa.setBackground(new java.awt.Color(46, 64, 83));
        aaaaaaaa.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        aaaaaaaa.setForeground(new java.awt.Color(255, 255, 255));
        aaaaaaaa.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hora registrada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel4.add(aaaaaaaa, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 180, 50));

        btnlimpiar.setBackground(new java.awt.Color(86, 101, 115));
        btnlimpiar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnlimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnlimpiar.setText("LIMPIAR");
        btnlimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnlimpiarActionPerformed(evt);
            }
        });
        jPanel4.add(btnlimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 180, 40));

        btnguardar.setBackground(new java.awt.Color(171, 178, 185));
        btnguardar.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnguardar.setForeground(new java.awt.Color(0, 0, 0));
        btnguardar.setText("GUARDAR");
        btnguardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnguardarActionPerformed(evt);
            }
        });
        jPanel4.add(btnguardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, 180, 40));

        cbxfacultades.setBackground(new java.awt.Color(46, 64, 83));
        cbxfacultades.setEditable(true);
        cbxfacultades.setForeground(new java.awt.Color(255, 255, 255));
        cbxfacultades.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ciencias de la Salud", "Ciencias Empresariales", "Derecho y Humanidades", "Ingeniería y Arquitectura" }));
        cbxfacultades.setAutoscrolls(true);
        cbxfacultades.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Facultad", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        cbxfacultades.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        cbxfacultades.setOpaque(true);
        jPanel4.add(cbxfacultades, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 180, 60));

        txtnombredocente.setBackground(new java.awt.Color(46, 64, 83));
        txtnombredocente.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        txtnombredocente.setForeground(new java.awt.Color(255, 255, 255));
        txtnombredocente.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Nombre docente", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Britannic Bold", 0, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        txtnombredocente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombredocenteActionPerformed(evt);
            }
        });
        jPanel4.add(txtnombredocente, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 180, 50));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 100, 600, 220));

        jtblregistro.setBackground(new java.awt.Color(52, 73, 94));
        jtblregistro.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jtblregistro.setForeground(new java.awt.Color(255, 255, 255));
        jtblregistro.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jtblregistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jtblregistro.setFocusable(false);
        jtblregistro.setRequestFocusEnabled(false);
        jtblregistro.setSelectionBackground(new java.awt.Color(100, 30, 22));
        jtblregistro.setShowGrid(false);
        jtblregistro.setShowHorizontalLines(true);
        jtblregistro.setShowVerticalLines(true);
        jtblregistro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jtblregistroMouseClicked(evt);
            }
        });
        jtblregistro.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jtblregistroPropertyChange(evt);
            }
        });
        jScrollPane1.setViewportView(jtblregistro);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 930, 150));

        jlista.setBackground(new java.awt.Color(52, 73, 94));
        jlista.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED)), "BUSQUEDA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N
        jlista.setForeground(new java.awt.Color(255, 255, 255));
        jScrollPane3.setViewportView(jlista);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 330, 230, 150));

        txtqr.setBackground(new java.awt.Color(153, 153, 153));
        txtqr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtqr.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "QR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Black", 0, 14), new java.awt.Color(255, 51, 51))); // NOI18N
        txtqr.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel1.add(txtqr, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 100, 230, 220));

        jButton3.setBackground(new java.awt.Color(255, 51, 51));
        jButton3.setFont(new java.awt.Font("Franklin Gothic Demi Cond", 0, 14)); // NOI18N
        jButton3.setForeground(new java.awt.Color(0, 0, 0));
        jButton3.setText("CERRAR SESIÓN");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 60, 130, 30));

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Centro de asistencia de Docentes");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 0, 300, 40));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("¡BIENVENIDO");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 130, 30));

        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("BUENA GESTIÓN!");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 170, 40));

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(40, 55, 71));
        jTextField1.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(255, 255, 255));
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jTextField1.setBorder(null);
        jPanel1.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 20, 500, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/dece5f124024167.60fb03546918b.gif"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, -220, 1170, 320));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1190, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 488, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void refrescartabla() {
        //borra los elementos del modelo para que no se dupliquen la información
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
        jtblregistro.setModel(modelo);
    }

    public static String fechaActual() {
        Date fecha = new Date();
        SimpleDateFormat FormatoFecha = new SimpleDateFormat("dd/MM/YYYY");
        return FormatoFecha.format(fecha);
    }

    private void txtregistrobuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtregistrobuscarKeyTyped
        char caracter = evt.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            evt.setKeyChar(Character.toUpperCase(caracter));
        }
        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo números");
        }
    }//GEN-LAST:event_txtregistrobuscarKeyTyped

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void btneliminar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneliminar1ActionPerformed
    }//GEN-LAST:event_btneliminar1ActionPerformed

    private void btneditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditarActionPerformed
    }//GEN-LAST:event_btneditarActionPerformed

    private void btnguardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnguardarActionPerformed
        guardarqr();
        final Object[] fila = (new Object[]{"PRIVATE", txtnombredocente.getText(), txthoraregistrada.getText(), txtfecharegistrada.getText(), cbxfacultades.getSelectedItem(), cbxcarreras.getSelectedItem(), txtcorreo.getText()});
        modelo.addRow(fila);
        createEmail();
        sendEmail();
    }//GEN-LAST:event_btnguardarActionPerformed

    private void btnlimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnlimpiarActionPerformed
        txtcorreo.setText("");
        txtnombredocente.setText("");
    }//GEN-LAST:event_btnlimpiarActionPerformed

    private void txtcorreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoActionPerformed
        String correo = txtcorreo.getText();
        if(ControladorUsuario.validateEmail(correo)){
            JOptionPane.showMessageDialog(null, "Se emvió un comprobante de asistencia a su correo");
        }else{
            JOptionPane.showMessageDialog(null, "Correo no valido, volver a intentar");
            return;
        }
        
    }//GEN-LAST:event_txtcorreoActionPerformed

    private void txtregistrobuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtregistrobuscarActionPerformed

    }//GEN-LAST:event_txtregistrobuscarActionPerformed

    private void jtblregistroPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jtblregistroPropertyChange


    }//GEN-LAST:event_jtblregistroPropertyChange

    private void jtblregistroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jtblregistroMouseClicked
    }//GEN-LAST:event_jtblregistroMouseClicked

    private void txtnombredocenteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombredocenteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombredocenteActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Iniciar_Sesion is = new Iniciar_Sesion();
        Central c = new Central();
        is.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed
    public RegistroDocentes ObtenerProducto(int posicion) {
        return new RegistroDocentes();
    }

    public int BuscarDocente(String codbuscado) {
        /*for(int i=0 ; i< docentes.size() ; i++){
            if(docentes.get(i).getCodigo_Registro().equalsIgnoreCase(codbuscado));
                return i; // retorna la posicion del objeto encontrado
        }*/
        return -1;
    }

    public void EliminarDocente(int pos) {
        //Docentes.remove(pos);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Central.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Central.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Central.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Central.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Central().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aaaaaaaa;
    public javax.swing.JButton btnbuscar;
    public javax.swing.JButton btndescargar;
    public javax.swing.JButton btneditar;
    public javax.swing.JButton btneliminar1;
    public javax.swing.JButton btnguardar;
    public javax.swing.JButton btnlimpiar;
    public javax.swing.JComboBox<String> cbxcarreras;
    public javax.swing.JComboBox<String> cbxfacultades;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    public javax.swing.JTextField jTextField1;
    public javax.swing.JList<String> jlista;
    public javax.swing.JTable jtblregistro;
    public javax.swing.JTextField txtcorreo;
    public javax.swing.JTextField txtfecharegistrada;
    public javax.swing.JLabel txthoraregistrada;
    public javax.swing.JTextField txtnombredocente;
    public javax.swing.JLabel txtqr;
    public javax.swing.JTextField txtregistrobuscar;
    // End of variables declaration//GEN-END:variables
}
