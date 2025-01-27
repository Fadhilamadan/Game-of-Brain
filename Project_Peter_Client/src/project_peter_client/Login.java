package project_peter_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {
    Socket socket;
    BufferedReader input;
    PrintStream output;
    int port = 8888;
    Music lagu = new Music();
    String alamat = "192.168.0.17";
    public Login() {
        initComponents();
        this.getRootPane().setDefaultButton(btnMasuk);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnMasuk = new javax.swing.JButton();
        btnRegis = new javax.swing.JButton();
        txtPwd = new javax.swing.JPasswordField();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login");
        setLocation(new java.awt.Point(50, 50));
        setPreferredSize(new java.awt.Dimension(905, 625));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Password :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(320, 330, 76, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Username :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(320, 290, 78, 17);

        txtUsername.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        txtUsername.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsernameActionPerformed(evt);
            }
        });
        getContentPane().add(txtUsername);
        txtUsername.setBounds(410, 280, 180, 30);

        btnMasuk.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnMasuk.setText("Masuk");
        btnMasuk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMasukActionPerformed(evt);
            }
        });
        getContentPane().add(btnMasuk);
        btnMasuk.setBounds(510, 360, 80, 30);

        btnRegis.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnRegis.setText("Registrasi");
        btnRegis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegis);
        btnRegis.setBounds(410, 360, 90, 30);

        txtPwd.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(txtPwd);
        txtPwd.setBounds(410, 320, 180, 30);

        background.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 900, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMasukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMasukActionPerformed
        boolean kondisi=true;
        
        
        String username = txtUsername.getText().trim();
        String pwd = txtPwd.getText().trim();
        if(username.isEmpty()){
            kondisi=false;
            JOptionPane.showMessageDialog(null, "Isi dulu username");
        }
        else{
            if(pwd.isEmpty()){
                kondisi=false;
                JOptionPane.showMessageDialog(null, "Isi dulu password");
            }
        }
        if(kondisi){
            try {
                socket = new Socket(alamat, port);
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintStream(socket.getOutputStream());
                output.println("/login "+username+" "+pwd);

                String message = input.readLine();
                if(message.equalsIgnoreCase("/masuk")){
                    MenuUtama menu = new MenuUtama();
                    menu.setUsername(username);
                    menu.setSocket(socket);
                    menu.setVisible(true);
                    lagu.stop();
                    this.dispose();
                }
                else if(message.equalsIgnoreCase("/gagal masuk")){
                    JOptionPane.showMessageDialog(null, "Password atau username salah");
                }
                else if(message.equalsIgnoreCase("/username has login")){
                    JOptionPane.showMessageDialog(null, "Player sudah masuk");
                }
                else 
                    JOptionPane.showMessageDialog(null, input.readLine());

            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnMasukActionPerformed

    private void btnRegisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisActionPerformed
        Registrasi menu = new Registrasi(alamat,port);
        menu.setVisible(true);
    }//GEN-LAST:event_btnRegisActionPerformed

    private void txtUsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsernameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsernameActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        lagu.login();
    }//GEN-LAST:event_formWindowOpened

    /**
     * @param args the command line arguments
     */
    
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnMasuk;
    private javax.swing.JButton btnRegis;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField txtPwd;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}
