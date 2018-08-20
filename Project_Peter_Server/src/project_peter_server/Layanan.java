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
import static project_peter_server.Project_Peter_Server.players;

public class Layanan extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintStream output;
    private Player player;
    private Soal soal;
    private Lobby lobby;

    public Layanan(Socket socket) throws IOException
    {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintStream(socket.getOutputStream());
        player=new Player();
        lobby = new Lobby();
        soal = new Soal();
    }
    public void kirimPesan(String pesan) {
        output.println(pesan);
    }

    public Player getPlayer() {
        return player;
    }
    
    @Override
    public void run() {
        player.setdefaultScore();
        while(true)
       {
           try {
               Thread.sleep(10);
               String message = input.readLine();
               if(message.startsWith("/login")){
                    String username = message.split(" ")[message.split(" ").length-2];
                    String password = message.split(" ")[message.split(" ").length-1];
                    if(player.isLogin(username)==true){
                        kirimPesan("/username has login");
                        System.out.println(socket.getInetAddress()+" gagal masuk");
                    }else{
                        if(player.login(username, password)==true){
                            kirimPesan("/masuk");
                            player.setLogin(username);
                            players.add(this);
                            System.out.println(player.getUserName()+" berhasil masuk");
                        }
                        else{
                            kirimPesan("/gagal masuk");
                            System.out.println(socket.getInetAddress()+" gagal masuk");
                        }
                    }
                }
               else if(message.startsWith("/create new player;")){
                   String username = message.split(";")[1];
                   String password = message.split(";")[2];
                   System.out.println("a");
                   if(player.cekUsername(username)>0){
                       kirimPesan("/username has already used");
                   }else{
                        if(player.createNewPlayer(username, password)>0){                   
                            kirimPesan("/player created");
                        }
                        else{
                            kirimPesan("/player could'nt create");
                        }
                   }
               }
               else if(message.startsWith("/keluar"))
               {
                   player.logout(player.getUserName());
                   kirimPesan("/keluar");
                   System.out.println(socket.getInetAddress()+" "+player.getUserName()+" keluar");
                   break;
               }
               else if(message.startsWith("/exit registrasi"))
               {
                   System.out.println(socket.getInetAddress()+" keluar registrasi");
                   break;
               }
               else if(message.startsWith("/chat public")){
                   String pesan=message.split(";")[1];
                   broadcast("/chat;"+player.getUserName()+" : "+pesan+"\n");
               }
               else if(message.startsWith("/lobbyChat")){
                   String pesan=message.split(";")[1];
                   broadcastLobby("/lobbyChatAccept;"+player.getUserName()+" : "+pesan+"\n");
               }
               else if(message.startsWith("/enterLobby"))
               {
                   int noLobby = Integer.valueOf(message.split(" ")[1]);
                   if(!lobby.isActive(noLobby)){
                        if(lobby.isFull(noLobby)){
                            kirimPesan("/fullLobby");
                        }
                        else{
                            if(lobby.playerMasuk(noLobby)!=0){
                                kirimPesan("/enterLobbySuccesed");
                                this.lobby = lobby.setLobby(noLobby);
                                player.setNo_lobby(noLobby);
                                player.setLobbyPlayer(noLobby, player.getID());
                                System.out.println(player.getUserName()+" masuk lobby "+lobby.getNama_lobby());
                            }
                            else{
                                kirimPesan("/enterLobbyFailed");
                                System.out.println(noLobby);
                            }
                        }
                   }
                   else
                       kirimPesan("/lobbyIsActive");
               }
               else if(message.startsWith("/quitLobby"))
               {
                   int noLobby = Integer.valueOf(message.split(" ")[1]);
                   lobby.playerKeluar(noLobby);
                   player.keluarLobby(player.getID());
                   for(Player u:player.getPlayerByLobby(noLobby))
                   {
                       player.setNo_lobby(0);
                       for(Layanan value:Project_Peter_Server.players){
                           if(value.player.getNo_lobby()==noLobby){
                            value.kirimPesan("/namaPlayerDariLobby;"+u.getUserName());
                           }
                       }
                   }
                   kirimPesan("/quitAccept");
                   System.out.println(player.getUserName()+" keluar lobby "+lobby.getNama_lobby());
               }
               else if(message.startsWith("/nama lobby"))
               {               
                    for(Lobby value : lobby.getAllLobby())
                    {
                        kirimPesan("/combobox;"+value.getNama_lobby());
                    }
               }
               else if(message.startsWith("/ambilNamaDariLobby"))
               {
                   int noLobby = Integer.valueOf(message.split(" ")[1]);
                   for(Player u:player.getPlayerByLobby(noLobby))
                   {
                       for(Layanan value:Project_Peter_Server.players){
                           if(value.player.getNo_lobby()==noLobby){
                            value.kirimPesan("/namaPlayerDariLobby;"+u.getUserName());
                           }
                       }
                   }
               }
               else if(message.startsWith("/playReady")){
                   int noLobby = Integer.valueOf(message.split(" ")[1]);
                   player.setNo_lobby(noLobby);
                   player.setdefaultScore();
                   player.setScore(0);
                   if(lobby.ambilJumlahPemainSaatIni(noLobby)>=2){
                       lobby.setActivated(noLobby);
                       player.setJumlahMain(player.getUserName());
                       broadcastLobby("/cautionGameStarted;Game_Start()");
                       kirimPesan("/play");
                   }
                   else
                       kirimPesan("/can'tPlay");
               }
               else if(message.startsWith("/requestSoal&jawaban"))
               {
                   int noLobby = Integer.valueOf(message.split(" ")[1]);
                   for(Soal u:soal.ambilSoalJawaban()){
                       soal.setSelectedSoal(u.getIdSoal());
                       soal=u;
                       broadcastSoal("/soal;"+u.getIsiSoal());
                       broadcastSoal("/jawabanA;"+u.getJawabanA());
                       broadcastSoal("/jawabanB;"+u.getJawabanB());
                       broadcastSoal("/jawabanC;"+u.getJawabanC());
                       broadcastSoal("/jawabanD;"+u.getJawabanD());
                   }
               }
               else if(message.startsWith("/cekJawaban"))
               {
                   String jawaban = message.split(" ")[1].trim();
                   if(soal.cekJawaban(jawaban, soal.getIdSoal()))
                   {
                       player.setScore(player.getScore()+10);
                   }
               }
               else if(message.startsWith("/scoreRequest")){
                   kirimPesan("/score;"+player.getScore());
               }
               else if(message.startsWith("/exitPlay")){
                   lobby.setDefaultActive(player.getNo_lobby());
                   kirimPesan("/exitAccept");
               }
               else if(message.startsWith("/allPlayerScoreRequest"))
               {
                   int noLobby = Integer.valueOf(message.split(" ")[1]);
                   for(Layanan value:Project_Peter_Server.players){
                       for(Player u:player.getPlayerByLobby(noLobby)){
                            value.kirimPesan("/allPlayerScore;"+u.getUserName()+" = "+u.getScore()+";"+lobby.ambilJumlahPemainSaatIni(noLobby));
                       }
                   }     
               }
               else if(message.startsWith("/lastScoreRequest")){
                   soal.setDefaultSoal();
                   player.setdefaultScore();
                   player.setScorePlayer(player.getUserName(), player.getScore());
                   if(player.getScore()> player.getHighscore(player.getUserName())){
                       player.setHighscore(player.getUserName(), player.getScore());
                   }
                   kirimPesan("/score;"+player.getScore());
               }
               else if(message.startsWith("/requestTop5Player&Highscore")){
                   for(String value : player.getTop5()){
                       kirimPesan("/top5Player;"+value);
                   }
                   kirimPesan("/yourHighscore;"+player.getHighscore(player.getUserName()));
               }
               else if(message.startsWith("/MenuScore")){
                   kirimPesan("/enterScore");
               }
               else if(message.startsWith("/kembaliMenuUtama")){
                   kirimPesan("/accept");
               }
               else if(message.startsWith("/MenuTambahSoal")){
                   kirimPesan("/enterMenuSoal");
               }
               else if(message.startsWith("/simpanSoalBaru")){
                   String isiSoal = message.split(";")[1];
                   String a = message.split(";")[2];
                   String b = message.split(";")[3];
                   String c = message.split(";")[4];
                   String d = message.split(";")[5];
                   String jawaban =message.split(";")[6];
                   int hasil=soal.buatSoalBaru(isiSoal, a, b, c, d, jawaban);
                   if(hasil>0)
                       kirimPesan("/soalBaruTerbuat");
                   else
                       kirimPesan("/soalBaruGagalTerbuat");
               }
               else if(message.startsWith("/buatLobbyBaru;")){
                   String namaLobby = message.split(";")[1];
                   if(lobby.buatLobbyBaru(namaLobby)>0){
                       kirimPesan("/lobbyBaruTerbuat");
                       
                       
                         broadcast("/lobbyBaruTerbuat");
                       
                   }
                   else
                       kirimPesan("/gagalBuatLobby");
               }
           } catch (InterruptedException | IOException ex) {
               Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
               break;
           }
       }
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void broadcast(String pesan) {
        for (Layanan u : Project_Peter_Server.players) {
                if(u!=this){
                 u.kirimPesan(pesan);
                }
        }
    }
    public void broadcastLobby(String pesan) {
        for (Layanan u : Project_Peter_Server.players) {
                if(u.player.getNo_lobby()==this.player.getNo_lobby()){
                    if(u!=this)
                    u.kirimPesan(pesan);
                }
        }
    }
    public void broadcastSoal(String pesan) {
        for (Layanan u : Project_Peter_Server.players) {
                if(u.player.getNo_lobby()==this.player.getNo_lobby()){
                    u.kirimPesan(pesan);
                }
        }
    }
}
