/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author philip
 */
class clientOutput implements Runnable {
    BufferedWriter toServer;
    public clientOutput(BufferedWriter toServer) {
        this.toServer = toServer;
    }

    @Override
    public void run(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            try {
                toServer.write(scanner.nextLine());
                toServer.newLine();
                toServer.flush();
            } catch (IOException ex) {
                Logger.getLogger(clientOutput.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
