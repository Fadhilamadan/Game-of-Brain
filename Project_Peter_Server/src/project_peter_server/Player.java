package project_peter_server;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Player{
    private int ID;
    private String userName;
    private String pwd;
    private int jumlahMain;
    private int no_lobby;
    private int score;
    private float highScore;
    private int isLogin;
    public Player() {
        ID=0;
        userName="";
        pwd="";
        jumlahMain=0;
        no_lobby=0;
        score=0;
        highScore=0;
        isLogin=0;  
    }
    public Player(int ID, String userName, String pwd, int jumlahMain,int noLobby ,int score, float highScore) {
        this.ID = ID;
        this.userName = userName;
        this.pwd = pwd;
        this.jumlahMain = jumlahMain;
        this.no_lobby=noLobby;
        this.score = score;
        this.highScore = highScore;
        this.isLogin=0;
    }

    public int getID() {
        return ID;
    }

    public String getUserName() {
        return userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setNo_lobby(int no_lobby) {
        this.no_lobby = no_lobby;
    }

    public int getNo_lobby() {
        return no_lobby;
    }

    public int getJumlahMain() {
        return jumlahMain;
    }

    public int getScore() {
        return score;
    }

    public float getHighScore() {
        return highScore;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setJumlahMain(int jumlahMain) {
        this.jumlahMain = jumlahMain;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHighScore(float highScore) {
        this.highScore = highScore;
    }
    public boolean login(String userName,String pwd)
    {
        boolean ada=false;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select * from user where username=? and password =?");
            stmt.setString(1, userName);
            stmt.setString(2, pwd);
            rs= stmt.executeQuery();
            if(rs.next()){
                this.ID=rs.getInt("userid");
                this.userName=userName;
                this.pwd=pwd;
                this.jumlahMain=rs.getInt("jumlahmain");
                this.score=rs.getInt("score");
                this.highScore=rs.getFloat("highscore");
                this.isLogin=1;
                ada= true;
            }
            else
                ada=false;
            
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
        return ada;
    }
    public void setPlayer(String userName)
    {
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select * from user where username=?");
            stmt.setString(1, userName);
            rs= stmt.executeQuery();
            if(rs.next()){
                this.ID=rs.getInt("userid");
                this.userName=userName;
                this.pwd=pwd;
                this.jumlahMain=rs.getInt("jumlahmain");
                this.score=rs.getInt("score");
                this.highScore=rs.getFloat("highscore");
                this.isLogin=1;
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
    }
    
    public int createNewPlayer(String username,String pwd)
    {
        int hasil=0;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("insert into user(username,password,jumlahmain,no_lobby,score,highscore,is_login) values(?,?,0,0,0,0,0)");
            stmt.setString(1, username);
            stmt.setString(2, pwd);
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
    
    public int cekUsername(String username)
    {
        int hasil=0;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select * from user where username=?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next())
                hasil=1;
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
    public void setLogin(String username)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update user set is_login=1 where username=?");
            stmt.setString(1, username);
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
    public boolean isLogin(String username)
    {
        boolean hasil=false;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select * from user where username=? and is_login=1");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()){
                if(rs.getString("username").equalsIgnoreCase(username))
                    hasil=true;
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
        return hasil;
    }
    public void logout(String username)
    {
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update user set is_login=0 where username=?");
            stmt.setString(1, username);
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
    public ArrayList<Player> getPlayerByLobby(int noLobby)
    {
        ArrayList<Player> players = new ArrayList<>();
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select * from user where no_lobby=?");
            stmt.setInt(1, noLobby);
            rs = stmt.executeQuery();
            while(rs.next()){
                Player player = new Player();
                player.ID=rs.getInt("userid");
                player.userName=rs.getString("username");
                player.pwd=pwd;
                player.jumlahMain=rs.getInt("jumlahmain");
                player.no_lobby=noLobby;
                player.score=rs.getInt("score");
                player.highScore=rs.getFloat("highscore");
                player.isLogin=1;
                players.add(player);
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
        return players;
    }
    public void setLobbyPlayer(int idLobby,int idPlayer)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update user set no_lobby=? where userid=?");
            stmt.setInt(1, idLobby);
            stmt.setInt(2, idPlayer);
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
    public int keluarLobby(int idPlayer)
    {
        int hasil=0;
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update user set no_lobby=0 where userid=?");
            stmt.setInt(1, idPlayer);
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
    public void setScorePlayer(String username,int score)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update user set score=? where username=?");
            stmt.setInt(1, score);
            stmt.setString(2, username);
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
    public int getHighscore(String username)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select highscore from user where username=?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                hasil=rs.getInt("highscore");
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
        return hasil;
    }
    public void setHighscore(String username,int score)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update user set highscore=? where username=?");
            stmt.setInt(1, score);
            stmt.setString(2, username);
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
    public void setdefaultScore()
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update user set score=0");
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
    public ArrayList<String> getTop5()
    {
        ArrayList<String> score = new ArrayList<>();
        Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select username,highscore from user order by highscore desc LIMIT 0,5");
            rs = stmt.executeQuery();
            while(rs.next()){
                String a="";
                a=rs.getString("username")+"\t"+rs.getInt("highscore");
                score.add(a);
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
        return score;
    }
    public int getJumlahMain(String username)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("select jumlahmain from user where username=?");
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            if(rs.next()){
                hasil=rs.getInt("jumlahmain");
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
        return hasil;
    }
    public void setJumlahMain(String username)
    {
        int hasil=0;
         Connection conn=null;
        PreparedStatement stmt=null;
        ResultSet rs=null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conn = (Connection)DriverManager.getConnection("jdbc:mysql://localhost:3306/dbgame","root","");
            stmt= (PreparedStatement) conn.prepareStatement("update user set jumlahmain=? where username=?");
            stmt.setInt(1, (getJumlahMain(username)+1));
            stmt.setString(2, username);
            hasil=stmt.executeUpdate();
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
}
