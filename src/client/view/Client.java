/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philip
 */
public class Client {
  private static final int PORT = 8080;
    private static InetAddress IP;
    private static BufferedReader fromServer;
    private static BufferedWriter toServer;
    private static Socket cliSocket;
    //private static Thread cliController;
    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException{
        IP = InetAddress.getByName("127.0.0.1");
        Scanner scanner = new Scanner(System.in);        
        connect();
        run(scanner);    
    }
    //  DET ÄR JU OCKSÅ KLIENTEN SOM SKA VARA MULTI-THREADED
    
    private static void run(Scanner scanner){
        String input;
        boolean connected = true;
      try {
          System.out.println(fromServer.readLine());
      } catch (IOException ex) {
          Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
      }
        while(connected){
            input = scanner.nextLine().toUpperCase();
            if(input.equals("BYE")){
                try {
                    toServer.write(input);
                    toServer.newLine();
                    toServer.flush();
                    cliSocket.close();
                    connected = false;
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                try {
                    //Thread cliController = new Thread(new cliController());
                    toServer.write(input);
                    toServer.newLine();
                    toServer.flush();
                    System.out.println(fromServer.readLine());
                } catch (IOException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    private static void connect(){
        try {
            // Creates and connects to serverport PORT at IP
            cliSocket = new Socket(IP, PORT);
            fromServer = new BufferedReader(new InputStreamReader(cliSocket.getInputStream()));
            toServer = new BufferedWriter(new OutputStreamWriter(cliSocket.getOutputStream()));
            System.out.println("Connected");
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
