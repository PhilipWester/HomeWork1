/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client.view;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 *
 * @author philip
 */
public class Client {
  private static final int PORT = 8080;
  private static final String IPstring = "127.0.0.1";
    /**
     * @param args the command line arguments
     * @throws java.net.UnknownHostException
     */
    public static void main(String[] args) throws UnknownHostException{
        InetAddress IP = InetAddress.getByName(IPstring);
        cliController cliController = new cliController(IP, PORT);
        cliController.run();
    }
}
