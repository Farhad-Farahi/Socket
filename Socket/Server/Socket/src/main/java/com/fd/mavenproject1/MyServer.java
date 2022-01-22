
package com.fd.mavenproject1;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author FarhadDeveloper98@gmail.com
 */
public class MyServer {
    
    public static void main(String[] args) throws IOException {
        System.out.println("The server is running at port number 5678");
        
        ServerSocket ss = new ServerSocket(5678);
        
        String msg;
        while(true){
        
            Socket s = ss.accept();
        
            DataInputStream dis = new DataInputStream(s.getInputStream());
        
            msg = dis.readUTF();
            
            dis.close();
            s.close();
            System.out.println("Message recived from android : " + msg);
            
                   
            
        }
    }
       
    
}
