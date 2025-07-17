package Vista;

import Controlador.ControladorVentanas;
import Controlador.ControladorUsuario;
import Modelo.ConsultasRegistro;
import Modelo.User;
import java.awt.Color;
import static java.lang.System.exit;
import java.net.URL;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

public class Iniciar_Sesion extends javax.swing.JFrame {

    public static Crear_Cuenta sr = new Crear_Cuenta();
    public static Central ce = new Central();
    ConsultasRegistro cr = new ConsultasRegistro();

    public Iniciar_Sesion() {
        setIconImage(new ImageIcon(getClass().getResource("/iconoparaelprograma.png")).getImage());
        initComponents();
        new ControladorVentanas(this, sr, ce);
        setLocationRelativeTo(null);
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
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        b1 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b0 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        bntCCISBLACK = new javax.swing.JButton();
        bntISISBLACK = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtcorreoISBLACK = new javax.swing.JTextField();
        correovacioISBL = new javax.swing.JLabel();
        contravacioISBL = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtcontraseñaISBLACK = new javax.swing.JPasswordField();
        jLabel14 = new javax.swing.JLabel();
        txtcorreonovalido1 = new javax.swing.JLabel();
        txtcorreonovalido = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(61, 61, 61));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
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
        jLabel2.setText("Sesión");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 310, -1, -1));

        jLabel4.setFont(new java.awt.Font("Stencil", 1, 50)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("INICIAR");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/45ba97ea01d71e630960e9fc8d4d58ca.gif"))); // NOI18N
        jPanel2.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(-180, 0, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 310, 370));

        jLabel3.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Contraseña");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 130, 140, 20));

        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Iniciar Sesión");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 0, 130, 30));

        jLabel6.setFont(new java.awt.Font("Britannic Bold", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Correo Electrónico");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 50, 220, 20));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0));
        jPanel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton25.setBackground(new java.awt.Color(255, 51, 51));
        jButton25.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });
        jPanel3.add(jButton25, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 30, 70));

        b1.setBackground(new java.awt.Color(255, 255, 255));
        b1.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b1.setForeground(new java.awt.Color(0, 0, 0));
        b1.setText("1");
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });
        jPanel3.add(b1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 10, 50, 30));

        b8.setBackground(new java.awt.Color(255, 255, 255));
        b8.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b8.setForeground(new java.awt.Color(0, 0, 0));
        b8.setText("8");
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });
        jPanel3.add(b8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, 30));

        b4.setBackground(new java.awt.Color(255, 255, 255));
        b4.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b4.setForeground(new java.awt.Color(0, 0, 0));
        b4.setText("4");
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });
        jPanel3.add(b4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, 50, 30));

        b9.setBackground(new java.awt.Color(255, 255, 255));
        b9.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b9.setForeground(new java.awt.Color(0, 0, 0));
        b9.setText("9");
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });
        jPanel3.add(b9, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 10, 50, 30));

        b0.setBackground(new java.awt.Color(255, 255, 255));
        b0.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b0.setForeground(new java.awt.Color(0, 0, 0));
        b0.setText("0");
        b0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b0ActionPerformed(evt);
            }
        });
        jPanel3.add(b0, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, 50, 30));

        b2.setBackground(new java.awt.Color(255, 255, 255));
        b2.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b2.setForeground(new java.awt.Color(0, 0, 0));
        b2.setText("2");
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });
        jPanel3.add(b2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 50, 50, 30));

        b7.setBackground(new java.awt.Color(255, 255, 255));
        b7.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b7.setForeground(new java.awt.Color(0, 0, 0));
        b7.setText("7");
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });
        jPanel3.add(b7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 50, 30));

        b3.setBackground(new java.awt.Color(255, 255, 255));
        b3.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b3.setForeground(new java.awt.Color(0, 0, 0));
        b3.setText("3");
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });
        jPanel3.add(b3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 50, 30));

        b5.setBackground(new java.awt.Color(255, 255, 255));
        b5.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b5.setForeground(new java.awt.Color(0, 0, 0));
        b5.setText("5");
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });
        jPanel3.add(b5, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 50, 50, 30));

        b6.setBackground(new java.awt.Color(255, 255, 255));
        b6.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        b6.setForeground(new java.awt.Color(0, 0, 0));
        b6.setText("6");
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });
        jPanel3.add(b6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 50, 30));

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 200, 310, 90));

        bntCCISBLACK.setBackground(new java.awt.Color(0, 153, 0));
        bntCCISBLACK.setFont(new java.awt.Font("Segoe UI Black", 0, 14)); // NOI18N
        bntCCISBLACK.setForeground(new java.awt.Color(255, 255, 255));
        bntCCISBLACK.setText("Crear Cuenta");
        bntCCISBLACK.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bntCCISBLACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntCCISBLACKActionPerformed(evt);
            }
        });
        jPanel1.add(bntCCISBLACK, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 160, 40));

        bntISISBLACK.setBackground(new java.awt.Color(51, 51, 51));
        bntISISBLACK.setFont(new java.awt.Font("Segoe UI Black", 1, 14)); // NOI18N
        bntISISBLACK.setForeground(new java.awt.Color(255, 255, 255));
        bntISISBLACK.setText("Iniciar Sesión");
        bntISISBLACK.setCursor(new java.awt.Cursor(java.awt.Cursor.WAIT_CURSOR));
        bntISISBLACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntISISBLACKActionPerformed(evt);
            }
        });
        jPanel1.add(bntISISBLACK, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 310, 160, 40));

        jLabel1.setFont(new java.awt.Font("Berlin Sans FB Demi", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("- o -");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 310, 80, 40));

        txtcorreoISBLACK.setBackground(new java.awt.Color(51, 51, 51));
        txtcorreoISBLACK.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        txtcorreoISBLACK.setForeground(new java.awt.Color(255, 255, 255));
        txtcorreoISBLACK.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtcorreoISBLACK.setBorder(null);
        jPanel1.add(txtcorreoISBLACK, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 360, 40));

        correovacioISBL.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        correovacioISBL.setForeground(new java.awt.Color(255, 51, 51));
        correovacioISBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(correovacioISBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 110, 400, -1));

        contravacioISBL.setFont(new java.awt.Font("Segoe UI Black", 0, 12)); // NOI18N
        contravacioISBL.setForeground(new java.awt.Color(255, 51, 51));
        contravacioISBL.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(contravacioISBL, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 190, 400, -1));

        jLabel11.setBackground(new java.awt.Color(0, 0, 0));
        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/key_9684818 (3).png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 150, 40, 40));

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/_6506388 (1).png"))); // NOI18N
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 70, 40, 40));

        txtcontraseñaISBLACK.setBackground(new java.awt.Color(51, 51, 51));
        txtcontraseñaISBLACK.setFont(new java.awt.Font("Segoe UI Emoji", 1, 16)); // NOI18N
        txtcontraseñaISBLACK.setForeground(new java.awt.Color(255, 255, 255));
        txtcontraseñaISBLACK.setBorder(null);
        txtcontraseñaISBLACK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontraseñaISBLACKActionPerformed(evt);
            }
        });
        txtcontraseñaISBLACK.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtcontraseñaISBLACKKeyTyped(evt);
            }
        });
        jPanel1.add(txtcontraseñaISBLACK, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 150, 360, 40));

        jLabel14.setFont(new java.awt.Font("Myanmar Text", 1, 10)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Usar el panel para la contraseña");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, 400, 20));

        txtcorreonovalido1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtcorreonovalido1.setForeground(new java.awt.Color(0, 153, 51));
        txtcorreonovalido1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtcorreonovalido1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 110, 370, 10));

        txtcorreonovalido.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        txtcorreonovalido.setForeground(new java.awt.Color(153, 0, 0));
        txtcorreonovalido.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(txtcorreonovalido, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 370, 10));

        jButton3.setBackground(new java.awt.Color(102, 102, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/oie_2972936R76fYXBv (1).png"))); // NOI18N
        jButton3.setBorder(null);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 10, 40, 30));

        jButton4.setBackground(new java.awt.Color(0, 0, 51));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/oie_2972831QRf8q2jh.png"))); // NOI18N
        jButton4.setBorder(null);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 10, 40, 30));

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

    private void bntCCISBLACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntCCISBLACKActionPerformed
        sr.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntCCISBLACKActionPerformed

    private void bntISISBLACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntISISBLACKActionPerformed
    }//GEN-LAST:event_bntISISBLACKActionPerformed

    private void txtcontraseñaISBLACKKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcontraseñaISBLACKKeyTyped
        char caracter = evt.getKeyChar();
        if (Character.isLowerCase(caracter)) {
            evt.setKeyChar(Character.toUpperCase(caracter));
        }
        char validar = evt.getKeyChar();
        if (Character.isDigit(validar) || Character.isAlphabetic(validar)) {
            getToolkit().beep();
            evt.consume();
            JOptionPane.showMessageDialog(null, "Ingresar la contraseña con el panel");
        }
    }//GEN-LAST:event_txtcontraseñaISBLACKKeyTyped

    private void txtexitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtexitMouseClicked
        System.exit(0);
    }//GEN-LAST:event_txtexitMouseClicked

    private void txtexitMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtexitMouseEntered
        txtexit.setForeground(Color.red);
    }//GEN-LAST:event_txtexitMouseEntered

    private void txtexitMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtexitMouseExited
        txtexit.setForeground(Color.white);
    }//GEN-LAST:event_txtexitMouseExited

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 1);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 2);
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 3);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 4);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 5);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 6);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 7);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 8);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 9);
    }//GEN-LAST:event_b9ActionPerformed

    private void b0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b0ActionPerformed
        txtcontraseñaISBLACK.setText(txtcontraseñaISBLACK.getText() + 0);
    }//GEN-LAST:event_b0ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        txtcontraseñaISBLACK.setText("");
    }//GEN-LAST:event_jButton25ActionPerformed

    private void txtcontraseñaISBLACKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontraseñaISBLACKActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontraseñaISBLACKActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //TEXTO
        jLabel5.setForeground(Color.BLACK);
        jLabel6.setForeground(Color.BLACK);
        jLabel3.setForeground(Color.BLACK);
        jLabel14.setForeground(Color.BLACK);
        jLabel1.setForeground(Color.BLACK);

        //OTROS
        txtexit.setForeground(Color.BLACK);
        Color panel = new Color(189, 189, 189);
        jPanel1.setBackground(panel);
        jPanel3.setBackground(Color.WHITE);
        txtcorreoISBLACK.setBackground(Color.WHITE);
        txtcorreoISBLACK.setForeground(Color.BLACK);
        txtcontraseñaISBLACK.setBackground(Color.WHITE);
        txtcontraseñaISBLACK.setForeground(Color.BLACK);
        jPanel2.setBackground(Color.WHITE);

        URL urlImagen = getClass().getResource("/Images/oie_259255ZJq9WP3U.gif");
        ImageIcon icono = new ImageIcon(urlImagen);
        jLabel8.setIcon(icono);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        //TEXTO
        jLabel5.setForeground(Color.WHITE);
        jLabel6.setForeground(Color.WHITE);
        jLabel3.setForeground(Color.WHITE);
        jLabel14.setForeground(Color.WHITE);
        jLabel1.setForeground(Color.WHITE);

        //OTROS
        txtexit.setForeground(Color.WHITE);
        Color panel = new Color(61, 61, 61);
        jPanel1.setBackground(panel);
        jPanel3.setBackground(Color.BLACK);
        txtcorreoISBLACK.setBackground(Color.BLACK);
        txtcorreoISBLACK.setForeground(Color.WHITE);
        txtcontraseñaISBLACK.setBackground(Color.BLACK);
        txtcontraseñaISBLACK.setForeground(Color.WHITE);
        jPanel2.setBackground(Color.BLACK);

        URL urlImagen = getClass().getResource("/Images/45ba97ea01d71e630960e9fc8d4d58ca.gif");
        ImageIcon icono = new ImageIcon(urlImagen);
        jLabel8.setIcon(icono);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(Iniciar_Sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Iniciar_Sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Iniciar_Sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Iniciar_Sesion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Iniciar_Sesion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton b0;
    public javax.swing.JButton b1;
    public javax.swing.JButton b2;
    public javax.swing.JButton b3;
    public javax.swing.JButton b4;
    public javax.swing.JButton b5;
    public javax.swing.JButton b6;
    public javax.swing.JButton b7;
    public javax.swing.JButton b8;
    public javax.swing.JButton b9;
    public javax.swing.JButton bntCCISBLACK;
    public javax.swing.JButton bntISISBLACK;
    public javax.swing.JLabel contravacioISBL;
    public javax.swing.JLabel correovacioISBL;
    public javax.swing.JButton jButton25;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    public javax.swing.JPasswordField txtcontraseñaISBLACK;
    public javax.swing.JTextField txtcorreoISBLACK;
    public javax.swing.JLabel txtcorreonovalido;
    public javax.swing.JLabel txtcorreonovalido1;
    private javax.swing.JLabel txtexit;
    // End of variables declaration//GEN-END:variables
}
