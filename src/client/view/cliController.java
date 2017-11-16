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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philip
 */
public class cliController {
    private static BufferedReader fromServer;
    private static BufferedWriter toServer;
    private static Socket cliSocket;

    public cliController(InetAddress IP, int PORT){
        connect(IP, PORT);
    }
    
    
    public void run(){
        // Starts the output-thread
        Thread clientOutput = new Thread(new clientOutput(toServer));
        clientOutput.start();
        
        // Input begins here
        boolean connected = true;
        String response = null;
        while(connected){
            try {
                response = fromServer.readLine();
            } catch (IOException ex) {
                Logger.getLogger(cliController.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(response.equals("exit")){
                try {
                    cliSocket.close();
                    connected = false;
                } catch (IOException ex) {
                    Logger.getLogger(cliController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                System.out.println(response);
            }
        }
    }
    private static void connect(InetAddress IP, int PORT){
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
