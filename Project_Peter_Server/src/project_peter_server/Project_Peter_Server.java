package project_peter_server;

import com.sun.xml.internal.ws.server.ServerSchemaValidationTube;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Project_Peter_Server {
    /**
     * @param args the command line arguments
     */
    public static ArrayList<Layanan> players = new ArrayList<>();
    public static void main(String[] args) {
        ServerSocket ss;
        int port=8888;
        try {
            ss = new ServerSocket(port);
            System.out.println("Server dimulai...");
            while(true){
                System.out.println("Menunggu user...");
                Socket incoming= ss.accept();
                System.out.println("Ada user terhubung dari "+incoming.getInetAddress());
                Layanan layanan = new Layanan(incoming);
                layanan.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Project_Peter_Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
}
