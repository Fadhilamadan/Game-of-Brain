/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_peter_server;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Asus
 */
public class Soal {
    private int idSoal;
    private String isiSoal;
    private String jawabanA;
    private String jawabanB;
    private String jawabanC;
    private String jawabanD;
    private String jawaban;

    public Soal() {
    }

    
    
    /**
     * @return the idSoal
     */
    public int getIdSoal() {
        return idSoal;
    }

    /**
     * @param idSoal the idSoal to set
     */
    public void setIdSoal(int idSoal) {
        this.idSoal = idSoal;
    }

    /**
     * @return the isiSoal
     */
    public String getIsiSoal() {
        return isiSoal;
    }

    /**
     * @param isiSoal the isiSoal to set
     */
    public void setIsiSoal(String isiSoal) {
        this.isiSoal = isiSoal;
    }

    /**
     * @return the jawabanA
     */
    public String getJawabanA() {
        return jawabanA;
    }

    /**
     * @param jawabanA the jawabanA to set
     */
    public void setJawabanA(String jawabanA) {
        this.jawabanA = jawabanA;
    }

    /**
     * @return the jawabanB
     */
    public String getJawabanB() {
        return jawabanB;
    }

    /**
     * @param jawabanB the jawabanB to set
     */
    public void setJawabanB(String jawabanB) {
        this.jawabanB = jawabanB;
    }

    /**
     * @return the jawabanC
     */
    public String getJawabanC() {
        return jawabanC;
    }

    /**
     * @param jawabanC the jawabanC to set
     */
    public void setJawabanC(String jawabanC) {
        this.jawabanC = jawabanC;
    }

    /**
     * @return the jawabanD
     */
    public String getJawabanD() {
        return jawabanD;
    }

    /**
     * @param jawabanD the jawabanD to set
     */
    public void setJawabanD(String jawabanD) {
        this.jawabanD = jawabanD;
    }

    /**
     * @return the jawaban
     */
    public String getJawaban() {
        return jawaban;
    }

    /**
     * @param jawaban the jawaban to set
     */
    public void setJawaban(String jawaban) {
        this.jawaban = jawaban;
    }
    
    public Soal (int idSoal, String isi, String jwbA, String jwbB, String jwbC, String jwbD,String jwb){
        this.idSoal = idSoal;
        this.isiSoal = isi;
        this.jawabanA = jwbA;
        this.jawabanB = jwbB;
        this.jawabanC = jwbC;
        this.jawabanD = jwbD;
        this.jawaban = jwb;
    }
    
    public ArrayList<Soal> ambilSoalJawaban()
    {
        ArrayList<Soal> soals = new ArrayList<>();
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM dbsoal where selected=0 order by rand() limit 1");
            rs = stmt.executeQuery();
            while(rs.next()) {
                Soal soal = new Soal();
                soal.idSoal = rs.getInt("idsoal");
                soal.isiSoal = rs.getString("isi_soal");
                soal.jawabanA = rs.getString("jawaban_a");
                soal.jawabanB = rs.getString("jawaban_b");
                soal.jawabanC = rs.getString("jawaban_c");
                soal.jawabanD = rs.getString("jawaban_d");
                soal.jawaban = rs.getString("jawaban");
                soals.add(soal);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return soals;
    }
    public void setSelectedSoal(int noSoal)
    {
        com.mysql.jdbc.Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt = (PreparedStatement) conn.prepareStatement("update dbsoal set selected=1 where idsoal=?");
            stmt.setInt(1, noSoal);
            stmt.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public boolean cekJawaban(String jawaban,int idSoal)
    {
        boolean benar=false;
        com.mysql.jdbc.Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt = (PreparedStatement) conn.prepareStatement("select jawaban from dbsoal where idsoal=?");
            stmt.setInt(1, idSoal);
            rs=stmt.executeQuery();
            if(rs.next()){
                if(jawaban.equalsIgnoreCase(rs.getString("jawaban")))
                    benar=true;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return benar;
    
    }
    public void setDefaultSoal()
    {
        com.mysql.jdbc.Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt = (PreparedStatement) conn.prepareStatement("update dbsoal set selected=0");
            stmt.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    public int buatSoalBaru(String isiSoal,String a,String b,String c,String d,String jawaban)
    {
        int hasil=0;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("insert into dbsoal(isi_soal,jawaban_a,jawaban_b,jawaban_c,jawaban_d,jawaban,selected) values(?,?,?,?,?,?,0)");
            stmt.setString(1, isiSoal);
            stmt.setString(2, a);
            stmt.setString(3, b);
            stmt.setString(4, c);
            stmt.setString(5, d);
            stmt.setString(6, jawaban);
            hasil = stmt.executeUpdate();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        return hasil;
    }

}
