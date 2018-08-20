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
public class Lobby {
    private int no_lobby;
    private String nama_lobby;
    private int kapasitas;
    private int jumlah_player;
    private int is_active;

    public Lobby() {
    }

    public Lobby(int no_lobby, String nama_lobby, int jumlah_player, int is_active) {
        this.no_lobby = no_lobby;
        this.nama_lobby = nama_lobby;
        this.jumlah_player = jumlah_player;
        this.is_active = is_active;
    }

    public void setNo_lobby(int no_lobby) {
        this.no_lobby = no_lobby;
    }

    public void setNama_lobby(String nama_lobby) {
        this.nama_lobby = nama_lobby;
    }

    public void setJumlah_player(int jumlah_player) {
        this.jumlah_player = jumlah_player;
    }

    public int getNo_lobby() {
        return no_lobby;
    }

    public String getNama_lobby() {
        return nama_lobby;
    }

    public int getKapasitas() {
        return kapasitas;
    }

    public int getJumlah_player() {
        return jumlah_player;
    }

    public int getIs_active() {
        return is_active;
    }
    
    public ArrayList<Lobby> getAllLobby()
    {
        ArrayList<Lobby> lobbies = new ArrayList<>();
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select * from lobby");
            rs= stmt.executeQuery();
            while(rs.next()){
                Lobby lobby = new Lobby();
                lobby.no_lobby=rs.getInt("no_lobby");
                lobby.nama_lobby=rs.getString("Nama_lobby");
                lobby.jumlah_player=rs.getInt("jumlah_player");
                lobby.kapasitas=rs.getInt("kapasitas");
                lobby.is_active=rs.getInt("isActive");
                lobbies.add(lobby);
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(conn!=null)
            try {
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stmt!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lobbies;
        
    }
    public Lobby setLobby(int noLobby)
    {
        Lobby lobby = new Lobby();
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select * from lobby where no_lobby=?");
            stmt.setInt(1, noLobby);
            rs= stmt.executeQuery();
            if(rs.next()){
                lobby.no_lobby=rs.getInt("no_lobby");
                lobby.nama_lobby=rs.getString("Nama_lobby");
                lobby.jumlah_player=rs.getInt("jumlah_player");
                lobby.kapasitas=rs.getInt("kapasitas");
                lobby.is_active=rs.getInt("isActive");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(conn!=null)
            try {
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stmt!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lobby;
    }
    public boolean isFull(int idLobby)
    {
        boolean penuh=false;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select jumlah_player,kapasitas from lobby where no_lobby=?");
            stmt.setInt(1, idLobby);
            rs= stmt.executeQuery();
            if(rs.next()){
                int jumlah_player=rs.getInt("jumlah_player");
                int kapasitas=rs.getInt("kapasitas");
                if(jumlah_player>=kapasitas){
                    penuh=true;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(conn!=null)
            try {
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stmt!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return penuh;
    }
    public int playerMasuk(int idLobby)
    {
        int hasil=0;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update lobby set jumlah_player=? where no_lobby=?");
            stmt.setInt(1, ambilJumlahPemainSaatIni(idLobby)+1);
            stmt.setInt(2, idLobby);
            hasil=stmt.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(conn!=null)
            try {
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stmt!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    public int playerKeluar(int idLobby)
    {
        int hasil=0;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update lobby set jumlah_player=? where no_lobby=?");
            stmt.setInt(1, ambilJumlahPemainSaatIni(idLobby)-1);
            stmt.setInt(2, idLobby);
            hasil=stmt.executeUpdate();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(conn!=null)
            try {
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stmt!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasil;
    }
    public int ambilJumlahPemainSaatIni(int idLobby)
    {
        int jumlah_player=0;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select jumlah_player from lobby where no_lobby=?");
            stmt.setInt(1, idLobby);
            rs= stmt.executeQuery();
            if(rs.next()){
                jumlah_player=rs.getInt("jumlah_player");
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(conn!=null)
            try {
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stmt!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jumlah_player;
    }
    public boolean isActive(int idLobby)
    {
        boolean aktif=false;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select isActive from lobby where no_lobby=?");
            stmt.setInt(1, idLobby);
            rs= stmt.executeQuery();
            if(rs.next()){
                int active=rs.getInt("isActive");
                if(active==1){
                    aktif=true;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if(conn!=null)
            try {
                conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(stmt!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(rs!=null)
            try {
                stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aktif;
    }
    public void setActivated(int noLobby)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update lobby set isActive=1 where no_lobby=? ");
            stmt.setInt(1, noLobby);
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
    
    }
    public void setDefaultActive(int noLobby)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update lobby set isActive=0 where no_lobby=? ");
            stmt.setInt(1, noLobby);
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
    
    }
    public int buatLobbyBaru(String namaLobby){
        int hasil=0;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("insert into lobby(Nama_lobby,jumlah_player,kapasitas,isActive) values(?,0,4,0)");
            stmt.setString(1, namaLobby);
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
