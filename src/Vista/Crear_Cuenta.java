package Vista;

import Controlador.ControladorUsuario;
import java.awt.Color;
import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import static java.lang.System.exit;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;

public class Crear_Cuenta extends javax.swing.JFrame {

    Random random = new Random();
    private int password = random.nextInt(900000) + 100000;
    private String contra = String.valueOf(password);

    private static String emailFrom = "grupo8poo@gmail.com";
    private static String passwordFrom = "txjhdnobekaytwei";
    private String emailTo;
    private String subject;
    private String content;
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public static Iniciar_Sesion xd;

    public Crear_Cuenta() {
        String contraseñas = contra;
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/iconoparaelprograma.png")).getImage());
        mProperties = new Properties();
        setLocationRelativeTo(null);
    }

    private void createEmail(String correo, String usuario) {
        final ControladorUsuario cu = new ControladorUsuario();
        int pw = cu.registrar(correo, usuario);
        String nom = txtnombresyapeCCBLACK.getText();

        emailTo = txtcorreoCCBLACK.getText().trim();
        subject = "CONTRASEÑA GENERADA PARA INGRESAR AL PROGRAMA";
        content = "¡Hola! Bienvenido a nuestro programa de registro de docentes, es un placer contar contigo estimado(a) " + nom + " la contraseña para ingresar es: " + pw;

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        txtexit = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        btnCCCCBLACK = new javax.swing.JButton();
        btnISCCBLACK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtnombresyapeCCBLACK = new javax.swing.JTextField();
        txtcorreoCCBLACK = new javax.swing.JTextField();
        txtcorreonovalido1 = new javax.swing.JLabel();
        txtcorreonovalido = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(61, 61, 61));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtexit.setFont(new java.awt.Font("Berlin Sans FB Demi", 0, 24)); // NOI18N
        txtexit.setForeground(new java.awt.Color(255, 255, 255));
        txtexit.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txtexit.setText("X");
        txtexit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtexitMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                txtexitMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                txtexitMouseExited(evt);
            }
        });
        jPanel2.add(txtexit, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 30, -1));

        jLabel2.setFont(new java.awt.Font("Stencil", 1, 50)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("Cuenta");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 310, -1, -1));

        jLabel4.setFont(new java.awt.Font("Stencil", 1, 50)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("Crear");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 250, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/45ba97ea01d71e630960e9fc8d4d58ca.gif"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-180, 0, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 370));

        jButton2.setBackground(new java.awt.Color(102, 102, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/oie_2972936R76fYXBv (1).png"))); // NOI18N
        jButton2.setBorder(null);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 40, 30));

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Crear Cuenta");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 130, 30));

        jLabel6.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Nombres y Apellidos");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, 220, 20));

        btnCCCCBLACK.setBackground(new java.awt.Color(0, 153, 0));
        btnCCCCBLACK.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        btnCCCCBLACK.setForeground(new java.awt.Color(255, 255, 255));
        btnCCCCBLACK.setText("Crear Cuenta");
        btnCCCCBLACK.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnCCCCBLACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCCCCBLACKActionPerformed(evt);
            }
        });
        jPanel1.add(btnCCCCBLACK, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 160, 40));

        btnISCCBLACK.setBackground(new java.awt.Color(51, 51, 51));
        btnISCCBLACK.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        btnISCCBLACK.setForeground(new java.awt.Color(255, 255, 255));
        btnISCCBLACK.setText("Iniciar Sesión");
        btnISCCBLACK.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        btnISCCBLACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnISCCBLACKActionPerformed(evt);
            }
        });
        jPanel1.add(btnISCCBLACK, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 230, 160, 40));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("- o -");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 230, 80, 40));

        jLabel7.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Correo electrónico");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 140, 210, 20));

        txtnombresyapeCCBLACK.setBackground(new java.awt.Color(51, 51, 51));
        txtnombresyapeCCBLACK.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        txtnombresyapeCCBLACK.setForeground(new java.awt.Color(255, 255, 255));
        txtnombresyapeCCBLACK.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtnombresyapeCCBLACK.setBorder(null);
        txtnombresyapeCCBLACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnombresyapeCCBLACKActionPerformed(evt);
            }
        });
        txtnombresyapeCCBLACK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtnombresyapeCCBLACKKeyTyped(evt);
            }
        });
        jPanel1.add(txtnombresyapeCCBLACK, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 80, 360, 40));

        txtcorreoCCBLACK.setBackground(new java.awt.Color(51, 51, 51));
        txtcorreoCCBLACK.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        txtcorreoCCBLACK.setForeground(new java.awt.Color(255, 255, 255));
        txtcorreoCCBLACK.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcorreoCCBLACK.setBorder(null);
        txtcorreoCCBLACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcorreoCCBLACKActionPerformed(evt);
            }
        });
        jPanel1.add(txtcorreoCCBLACK, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 160, 360, 40));

        txtcorreonovalido1.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        txtcorreonovalido1.setForeground(new java.awt.Color(51, 204, 0));
        txtcorreonovalido1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtcorreonovalido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 200, 410, 10));

        txtcorreonovalido.setFont(new java.awt.Font("Ebrima", 1, 12)); // NOI18N
        txtcorreonovalido.setForeground(new java.awt.Color(255, 0, 0));
        txtcorreonovalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtcorreonovalido, new org.netbeans.lib.awtextra.AbsoluteConstraints(327, 200, 410, 10));

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/copy_6461289 (1).png"))); // NOI18N
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 41, 40));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_6506388 (1).png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 40, 40));

        jLabel12.setFont(new java.awt.Font("Myanmar Text", 1, 10)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("para ello el correo debe tener activado la verificación de 2 pasos.");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 340, 400, 30));

        jLabel13.setFont(new java.awt.Font("Myanmar Text", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(153, 0, 0));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("Importante");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 280, 400, -1));

        jLabel14.setFont(new java.awt.Font("Myanmar Text", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Al momento de realizar la creación de la cuenta con un correo existente");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 400, 20));

        jLabel15.setFont(new java.awt.Font("Myanmar Text", 1, 10)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("se hará el envío de la contraseña única a su correo");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 400, 50));

        jLabel16.setFont(new java.awt.Font("Myanmar Text", 1, 10)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Al momento de realizar la creación de la cuenta con un correo existente");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 300, 400, 20));

        jLabel17.setFont(new java.awt.Font("Myanmar Text", 1, 10)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("para así tener los estándares de seguridad en el programa");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 400, 20));

        jButton3.setBackground(new java.awt.Color(0, 0, 51));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/oie_2972831QRf8q2jh.png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 40, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 749, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        //TEXTOS
        jLabel5.setForeground(Color.BLACK);
        jLabel6.setForeground(Color.BLACK);
        jLabel7.setForeground(Color.BLACK);
        txtexit.setForeground(Color.BLACK);
        jLabel12.setForeground(Color.BLACK);
        jLabel14.setForeground(Color.BLACK);
        jLabel15.setForeground(Color.BLACK);
        jLabel17.setForeground(Color.BLACK);
        jLabel12.setForeground(Color.BLACK);
        jLabel1.setForeground(Color.BLACK);
        //OTROS
        Color panel = new Color(187, 187, 187);
        jPanel1.setBackground(panel);
        txtnombresyapeCCBLACK.setBackground(Color.WHITE);
        txtnombresyapeCCBLACK.setForeground(Color.BLACK);
        txtcorreoCCBLACK.setBackground(Color.WHITE);
        txtcorreoCCBLACK.setForeground(Color.BLACK);
        jPanel2.setBackground(Color.WHITE);
        URL urlImagen = getClass().getResource("/Images/oie_259255ZJq9WP3U.gif");
        ImageIcon icono = new ImageIcon(urlImagen);
        jLabel8.setIcon(icono);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnISCCBLACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnISCCBLACKActionPerformed
        xd = new Iniciar_Sesion();
        xd.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnISCCBLACKActionPerformed

    private void txtnombresyapeCCBLACKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtnombresyapeCCBLACKKeyTyped
        char caracter = evt.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            evt.setKeyChar(Character.toUpperCase(caracter));
        }
        char validar = evt.getKeyChar();
        if (Character.isDigit(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar solo letras");
        }
    }//GEN-LAST:event_txtnombresyapeCCBLACKKeyTyped

    private void btnCCCCBLACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCCCCBLACKActionPerformed
        String usuario = txtnombresyapeCCBLACK.getText();
        String correo = txtcorreoCCBLACK.getText();

        if (usuario.equals("") || correo.equals("")) {
            JOptionPane.showMessageDialog(null, "Algunos campos están vacíos");
            return;
        }
        if (ControladorUsuario.validateEmail(correo)) {
            txtcorreonovalido.setText("");
            txtcorreonovalido1.setText("Correo valido");
        } else {
            txtcorreonovalido.setText("Correo no valido");
            txtcorreoCCBLACK.setText("");
            return;
        }
        createEmail(correo, usuario);
        sendEmail();

    }//GEN-LAST:event_btnCCCCBLACKActionPerformed

    private void txtexitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtexitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_txtexitMouseClicked

    private void txtexitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtexitMouseEntered
        txtexit.setForeground(Color.red);
    }//GEN-LAST:event_txtexitMouseEntered

    private void txtexitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtexitMouseExited
        txtexit.setForeground(Color.white);
    }//GEN-LAST:event_txtexitMouseExited

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //TEXTOS
        jLabel5.setForeground(Color.WHITE);
        jLabel6.setForeground(Color.WHITE);
        jLabel7.setForeground(Color.WHITE);
        txtexit.setForeground(Color.WHITE);
        jLabel12.setForeground(Color.WHITE);
        jLabel14.setForeground(Color.WHITE);
        jLabel15.setForeground(Color.WHITE);
        jLabel17.setForeground(Color.WHITE);
        jLabel12.setForeground(Color.WHITE);
        jLabel1.setForeground(Color.WHITE);
        //OTROS
        Color panel = new Color(61, 61, 61);
        jPanel1.setBackground(panel);
        txtnombresyapeCCBLACK.setBackground(Color.BLACK);
        txtnombresyapeCCBLACK.setForeground(Color.WHITE);
        txtcorreoCCBLACK.setBackground(Color.BLACK);
        txtcorreoCCBLACK.setForeground(Color.WHITE);
        jPanel2.setBackground(Color.BLACK);
        URL urlImagen = getClass().getResource("/Images/45ba97ea01d71e630960e9fc8d4d58ca.gif");
        ImageIcon icono = new ImageIcon(urlImagen);
        jLabel8.setIcon(icono);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtnombresyapeCCBLACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnombresyapeCCBLACKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnombresyapeCCBLACKActionPerformed

    private void txtcorreoCCBLACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcorreoCCBLACKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcorreoCCBLACKActionPerformed
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
            java.util.logging.Logger.getLogger(Crear_Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Crear_Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Crear_Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Crear_Cuenta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Crear_Cuenta().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCCCCBLACK;
    public javax.swing.JButton btnISCCBLACK;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    public javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public javax.swing.JTextField txtcorreoCCBLACK;
    private javax.swing.JLabel txtcorreonovalido;
    private javax.swing.JLabel txtcorreonovalido1;
    private javax.swing.JLabel txtexit;
    public javax.swing.JTextField txtnombresyapeCCBLACK;
    // End of variables declaration//GEN-END:variables
}
