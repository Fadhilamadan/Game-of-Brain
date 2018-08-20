package project_peter_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Registrasi extends javax.swing.JFrame {

    public Registrasi(String alamat,int port) {
        initComponents();
        this.alamat=alamat;
        this.port=port;
                try {
            socket = new Socket(alamat, port);
        } catch (IOException ex) {
            Logger.getLogger(Registrasi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    String alamat;
    int port;
    Socket socket;
    BufferedReader input;
    PrintStream output;

    private Registrasi() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public void setPort(int port) {
        this.port = port;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSimpan = new javax.swing.JButton();
        txtUsername = new javax.swing.JTextField();
        btnBatal = new javax.swing.JButton();
        txtPasword = new javax.swing.JTextField();
        txtRepassword = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registrasi");
        setLocation(new java.awt.Point(150, 85));
        setPreferredSize(new java.awt.Dimension(705, 475));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        btnSimpan.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnSimpan.setText("Daftar");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });
        getContentPane().add(btnSimpan);
        btnSimpan.setBounds(420, 370, 80, 30);

        txtUsername.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(txtUsername);
        txtUsername.setBounds(320, 250, 180, 30);

        btnBatal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnBatal.setText("Batal");
        btnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBatalActionPerformed(evt);
            }
        });
        getContentPane().add(btnBatal);
        btnBatal.setBounds(320, 370, 90, 30);

        txtPasword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(txtPasword);
        txtPasword.setBounds(320, 290, 180, 30);

        txtRepassword.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        getContentPane().add(txtRepassword);
        txtRepassword.setBounds(320, 330, 180, 30);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Username :");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(230, 260, 78, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Password :");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(230, 300, 76, 17);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Re-password :");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(210, 340, 100, 17);

        background.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/registrasi.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(-90, -20, 900, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        this.dispose();
        try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/exit registrasi");
        } catch (IOException ex) {
            Logger.getLogger(Registrasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        try{

            String pwd = txtPasword.getText().trim();
            String upwd = txtRepassword.getText().trim();
            String username = txtUsername.getText();
            if(pwd.equalsIgnoreCase(upwd)){
                input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                output = new PrintStream(socket.getOutputStream());
                output.println("/create new player;"+username+";"+pwd);
            }else{
                JOptionPane.showMessageDialog(null, "Password tidak sama");
            }
        }
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintStream(socket.getOutputStream());
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while(true){
                        try {
                            String message = input.readLine();
                        if(message.equalsIgnoreCase("/player created")){
                            JOptionPane.showMessageDialog(null, "Registrasi berhasil");
                            close();
                            output.println("/exit registrasi");
                            break;
                        }
                        else if(message.equalsIgnoreCase("/player could'nt create")){
                            JOptionPane.showMessageDialog(null, "Registrasi gagal");
                        }
                        else if(message.equalsIgnoreCase("/username has already used")){
                            JOptionPane.showMessageDialog(null, "Username sudah ada;");
                        }
                        else 
                            JOptionPane.showMessageDialog(null, input.readLine());
                    
                    }
                                 catch (IOException ex) {
                                    Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        }
            }).start();
        } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_formWindowOpened
    private void close(){
        this.dispose();
    }
    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/exit registrasi");
        } catch (IOException ex) {
            Logger.getLogger(Registrasi.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_formWindowClosing

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
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registrasi.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registrasi().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField txtPasword;
    private javax.swing.JTextField txtRepassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}