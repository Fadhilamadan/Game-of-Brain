/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_peter_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Fadhilamadan
 */
public class Score extends javax.swing.JFrame {

    Socket socket;
    BufferedReader input;
    PrintStream output;
    private String username;
    public Score() {
        initComponents();
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        lblHighscore = new javax.swing.JLabel();
        btnKembali = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tarTop5 = new javax.swing.JTextArea();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Skor");
        setLocation(new java.awt.Point(150, 85));
        setPreferredSize(new java.awt.Dimension(705, 475));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Skor Tertinggi Anda:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(120, 330, 190, 22);

        lblHighscore.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblHighscore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHighscore.setText("0");
        getContentPane().add(lblHighscore);
        lblHighscore.setBounds(310, 330, 30, 22);

        btnKembali.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnKembali);
        btnKembali.setBounds(480, 330, 110, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel2.setText("TOP 5");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(320, 100, 80, 29);

        tarTop5.setEditable(false);
        tarTop5.setColumns(20);
        tarTop5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        tarTop5.setRows(5);
        jScrollPane1.setViewportView(tarTop5);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(120, 140, 470, 180);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(-110, -20, 860, 590);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private int count =0;
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output = new PrintStream(socket.getOutputStream());
            output.println("/requestTop5Player&Highscore");
            tarTop5.append("  Nama\tScore\n\n");
            new Thread(new Runnable(){
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(100);
                            String message = input.readLine();
                            if(message.startsWith("/top5Player;")){
                                String value =message.split(";")[1];
                                count++;
                                tarTop5.append(count+". "+value+"\n");
                            }
                            else if(message.startsWith("/yourHighscore;")){
                                String score = message.split(";")[1];
                                lblHighscore.setText(score);
                            }
                            else if(message.startsWith("/accept")){
                                MenuUtama menu = new MenuUtama();
                                menu.setUsername(username);
                                menu.setSocket(socket);
                                menu.setVisible(true);
                                close();
                                break;
                            }
                        } catch (InterruptedException | IOException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();
        } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }//GEN-LAST:event_formWindowOpened

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/kembaliMenuUtama");
        } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKembaliActionPerformed
    private void close(){
        this.dispose();
    }            
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
            java.util.logging.Logger.getLogger(Score.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Score.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Score.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Score.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Score().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnKembali;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHighscore;
    private javax.swing.JTextArea tarTop5;
    // End of variables declaration//GEN-END:variables
}