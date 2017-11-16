/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server.net;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import server.net.ClientHandler;

/**
 *
 * @author philip
 */

// Creates a clientHandler for each client
public class startHandler {
    private static final int PORT = 8080;
    private static final int LINGER = 1000;
    private static final int TIMEOUT = 1000;
    
    public void run(){
        try {
            // Creates listening socket and binds it to a port
            ServerSocket socket = new ServerSocket(PORT);
            while(true){
                // Hear a client and connects to it
                Socket clientSocket = socket.accept();
                // Set linger time. Question: Do we actually need this? It seems uneccessary to me
                clientSocket.setSoLinger(true, LINGER);
                // Send the client to a handler-thread and begins over again to listen for more clients
                Thread handler = new Thread(new ClientHandler(clientSocket));
                handler.start();
            }
        } catch (IOException ex) {
            System.err.println("Error, server failed");
        }
    }
}
