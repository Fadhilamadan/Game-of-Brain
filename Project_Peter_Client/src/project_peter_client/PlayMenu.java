package project_peter_client;

import com.mysql.jdbc.PreparedStatement;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class PlayMenu extends javax.swing.JFrame {
    private Timer timer1;
    private ActionListener act;
    int counting=10;
    int countSoal=0;
    Music lagu = new Music();
    
    public PlayMenu() {
        initComponents();
        act = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(counting > 0){
                    counting--;
                    lblWaktu.setText(String.valueOf(counting));
                }
                else
                {
                    timer1.stop();
                    counting = 10;
                    if(countSoal<10){
                        countSoal++;
                        try {
                            
                            output= new PrintStream(socket.getOutputStream());
                            output.println("/requestSoal&jawaban "+idLobby);
                            output.println("/scoreRequest");
                            btnPilihA.setEnabled(true);
                            btnPilihB.setEnabled(true);
                            btnPilihC.setEnabled(true);
                            btnPilihD.setEnabled(true);
                        } catch (IOException ex) {
                            Logger.getLogger(PlayMenu.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        timer1.start();
                    }
                    else{
                            tarSoal.setText("");
                            tarSoal.append(username+" : "+lblScore.getText()+"\n");
                            output.println("/lastScoreRequest");
                            btnPilihA.setVisible(false);
                            btnPilihB.setVisible(false);
                            btnPilihC.setVisible(false);
                            btnPilihD.setVisible(false);
                            jScrollPane1.setVisible(false);
                            tarSoal.setVisible(false);
                            tarSoal.setText("");
                            lblSelesai.setVisible(true);
                            lblNamaNilai.setVisible(true);
                            lblScoreAkhir.setVisible(true);                                
                            btnKembali.setVisible(true);
                            btnTampilSkor.setVisible(true);
                    }
                }
            }
        };
        timer1 = new Timer(1000, act);
    }
    Socket socket;
    BufferedReader input;
    PrintStream output;
    String username;
    int idLobby;
    
    
    
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIdLobby(int idLobby) {
        this.idLobby = idLobby;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tarSoal = new javax.swing.JTextArea();
        btnPilihD = new javax.swing.JButton();
        btnPilihA = new javax.swing.JButton();
        btnPilihC = new javax.swing.JButton();
        btnPilihB = new javax.swing.JButton();
        lblSelesai = new javax.swing.JLabel();
        lblWaktu = new javax.swing.JLabel();
        lblScore = new javax.swing.JLabel();
        lblNamaNilai = new javax.swing.JLabel();
        lblScoreAkhir = new javax.swing.JLabel();
        btnTampilSkor = new javax.swing.JButton();
        btnKembali = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Game of Brain");
        setLocation(new java.awt.Point(50, 50));
        setPreferredSize(new java.awt.Dimension(900, 600));
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

        tarSoal.setEditable(false);
        tarSoal.setColumns(20);
        tarSoal.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tarSoal.setRows(5);
        tarSoal.setTabSize(50);
        jScrollPane1.setViewportView(tarSoal);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(140, 180, 630, 180);

        btnPilihD.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPilihD.setText("D. Jawaban");
        btnPilihD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihDActionPerformed(evt);
            }
        });
        getContentPane().add(btnPilihD);
        btnPilihD.setBounds(470, 450, 300, 40);

        btnPilihA.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPilihA.setText("A. Jawaban");
        btnPilihA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihAActionPerformed(evt);
            }
        });
        getContentPane().add(btnPilihA);
        btnPilihA.setBounds(140, 380, 300, 40);

        btnPilihC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPilihC.setText("C. Jawaban");
        btnPilihC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihCActionPerformed(evt);
            }
        });
        getContentPane().add(btnPilihC);
        btnPilihC.setBounds(470, 380, 300, 40);

        btnPilihB.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnPilihB.setText("B. Jawaban");
        btnPilihB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilihBActionPerformed(evt);
            }
        });
        getContentPane().add(btnPilihB);
        btnPilihB.setBounds(140, 450, 300, 40);

        lblSelesai.setFont(new java.awt.Font("Tahoma", 1, 44)); // NOI18N
        lblSelesai.setText("PERMAINAN TELAH SELESAI");
        getContentPane().add(lblSelesai);
        lblSelesai.setBounds(140, 220, 640, 60);

        lblWaktu.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblWaktu.setText("10");
        getContentPane().add(lblWaktu);
        lblWaktu.setBounds(230, 150, 20, 17);

        lblScore.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblScore.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScore.setText("0");
        getContentPane().add(lblScore);
        lblScore.setBounds(740, 150, 20, 17);

        lblNamaNilai.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblNamaNilai.setText("Skor Anda:");
        getContentPane().add(lblNamaNilai);
        lblNamaNilai.setBounds(370, 310, 140, 29);

        lblScoreAkhir.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        lblScoreAkhir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblScoreAkhir.setText("0");
        getContentPane().add(lblScoreAkhir);
        lblScoreAkhir.setBounds(510, 310, 40, 30);

        btnTampilSkor.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnTampilSkor.setText("Tampil Skor Pemain Lain");
        btnTampilSkor.setEnabled(false);
        btnTampilSkor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTampilSkorActionPerformed(evt);
            }
        });
        getContentPane().add(btnTampilSkor);
        btnTampilSkor.setBounds(410, 380, 350, 50);

        btnKembali.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        btnKembali.setText("Kembali");
        btnKembali.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKembaliActionPerformed(evt);
            }
        });
        getContentPane().add(btnKembali);
        btnKembali.setBounds(150, 380, 230, 50);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Sisa Waktu:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(140, 150, 90, 17);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Skor Anda:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(660, 150, 80, 17);

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/menu.png"))); // NOI18N
        getContentPane().add(background);
        background.setBounds(0, 0, 900, 600);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void btnPilihDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihDActionPerformed
        try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/cekJawaban D");
        } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnPilihA.setEnabled(false);
        btnPilihB.setEnabled(false);
        btnPilihC.setEnabled(false);
        btnPilihD.setEnabled(false);
    }//GEN-LAST:event_btnPilihDActionPerformed

    private void btnPilihAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihAActionPerformed
        try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/cekJawaban A");
        } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnPilihA.setEnabled(false);
        btnPilihB.setEnabled(false);
        btnPilihC.setEnabled(false);
        btnPilihD.setEnabled(false);
    }//GEN-LAST:event_btnPilihAActionPerformed

    private void btnPilihCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihCActionPerformed
        try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/cekJawaban C");
        } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnPilihA.setEnabled(false);
        btnPilihB.setEnabled(false);
        btnPilihC.setEnabled(false);
        btnPilihD.setEnabled(false);
    }//GEN-LAST:event_btnPilihCActionPerformed

    private void btnPilihBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilihBActionPerformed
        try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/cekJawaban B");
            
        } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnPilihA.setEnabled(false);
        btnPilihB.setEnabled(false);
        btnPilihC.setEnabled(false);
        btnPilihD.setEnabled(false);
    }//GEN-LAST:event_btnPilihBActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        lagu.stop();
    }//GEN-LAST:event_formWindowClosing
    int counter=0;
    int jumlah=0;
    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            lagu.game();
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            output= new PrintStream(socket.getOutputStream());
            output.println("/requestSoal&jawaban "+idLobby);
            countSoal++;
            lblWaktu.setText(String.valueOf(counting));
            lblSelesai.setVisible(false);
            lblNamaNilai.setVisible(false);
            lblScoreAkhir.setVisible(false);
            btnKembali.setVisible(false);
            btnTampilSkor.setVisible(false);
             new Thread(new Runnable(){
                @Override
                public void run() {
                    while(true){
                        try {
                            Thread.sleep(10);
                            String message = input.readLine();
                            if(message.startsWith("/exitAccept"))
                            {
                                RoomGame menu = new RoomGame();
                                menu.setSocket(socket);
                                menu.setIdLobby(idLobby);
                                menu.setUsername(username);
                                menu.setVisible(true);
                                close();
                                break;
                            }
                            else if(message.startsWith("/soal;")){
                                String soal = message.split(";")[1];
                                tarSoal.setText(soal);
                            }
                            else if(message.startsWith("/jawabanA;")){
                                String jawaban = message.split(";")[1];
                                btnPilihA.setText("A. "+jawaban);
                            }
                            else if(message.startsWith("/jawabanB;")){
                                String jawaban = message.split(";")[1];
                                btnPilihB.setText("B. "+jawaban);
                            }
                            else if(message.startsWith("/jawabanC;")){
                                String jawaban = message.split(";")[1];
                                btnPilihC.setText("C. "+jawaban);
                            }
                            else if(message.startsWith("/jawabanD;")){
                                String jawaban = message.split(";")[1];
                                btnPilihD.setText("D. "+jawaban);
                            }
                            else if(message.startsWith("/score;"))
                            {
                                String score=message.split(";")[1];
                                lblScore.setText(score);
                                lblScoreAkhir.setText(score);
                            }
                            else if(message.startsWith("/allPlayerScore;"))
                            {
                                counter++;
                                String value=message.split(";")[1];
                                jumlah=Integer.valueOf(message.split(";")[2]);
                                if(jumlah>0){
                                    if(counter<=jumlah){
                                        tarSoal.append(value+"\n");
                                    }
                                }
                                else{
                                    tarSoal.append(value+"\n");
                                }
                            }
                            else if(message.startsWith("/jumlahPlayer;"))
                            {
                                jumlah=Integer.valueOf(message.split(";")[1]);
                            }
                        } catch (InterruptedException | IOException ex) {
                            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }).start();

        } catch (IOException ex) {
            Logger.getLogger(RoomGame.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!timer1.isRunning())
            timer1.start();
    }//GEN-LAST:event_formWindowOpened

    private void btnKembaliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKembaliActionPerformed
        try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/exitPlay");
        } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnKembaliActionPerformed

    private void btnTampilSkorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilSkorActionPerformed
            lblSelesai.setVisible(false);
            lblNamaNilai.setVisible(false);
            lblScoreAkhir.setVisible(false);
            jScrollPane1.setVisible(true);
            tarSoal.setVisible(true);
            Font font = new Font("calibri", 1, 32);
            tarSoal.setFont(font);
            try {
            output = new PrintStream(socket.getOutputStream());
            output.println("/allPlayerScoreRequest "+idLobby);
            } catch (IOException ex) {
            Logger.getLogger(MenuUtama.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_btnTampilSkorActionPerformed
    public void close()
    {
        lagu.stop();
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
            java.util.logging.Logger.getLogger(PlayMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PlayMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PlayMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PlayMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PlayMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel background;
    private javax.swing.JButton btnKembali;
    private javax.swing.JButton btnPilihA;
    private javax.swing.JButton btnPilihB;
    private javax.swing.JButton btnPilihC;
    private javax.swing.JButton btnPilihD;
    private javax.swing.JButton btnTampilSkor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblNamaNilai;
    private javax.swing.JLabel lblScore;
    private javax.swing.JLabel lblScoreAkhir;
    private javax.swing.JLabel lblSelesai;
    private javax.swing.JLabel lblWaktu;
    private javax.swing.JTextArea tarSoal;
    // End of variables declaration//GEN-END:variables
}
