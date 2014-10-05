package com.example.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import android.util.Log;

public class CommunicationThread extends Thread {
    private final Socket socket;
    private Scanner socketReader; 
    private final PrintWriter socketWriter; 

    
    public CommunicationThread(Socket sock) {
        socket = sock;
        Scanner tmpIn = null;
        PrintWriter tmpOut = null; 
        try {
            //---creates the inputstream and outputstream objects
            // for reading and writing through the sockets---
            tmpIn = new Scanner(socket.getInputStream());
            tmpOut = new PrintWriter(socket.getOutputStream(), true);
        } catch (IOException e) {
            Log.d("SocketChat", e.getLocalizedMessage());
        } 
        socketReader = tmpIn;
        socketWriter = tmpOut;
    }
    
    public void run() {
        //---buffer store for the stream---
        byte[] buffer = new byte[1024];

        //---bytes returned from read()---
        int bytes;  

        //---keep listening to the InputStream until an 
        // exception occurs---
        while (true) {
            try {
                //---read from the inputStream---
                                 
 				String inputMessage = socketReader.nextLine();
 				
                //---update the main activity UI---
                SocketsActivity.UIupdater.obtainMessage(0,inputMessage.length(), -1, inputMessage.getBytes()).sendToTarget();
            } catch (Exception e) {
                break;
            }
        }
    }
 
    //---call this from the main activity to 
    // send data to the remote device---
    public void write(String message) {
        try {
        	socketWriter.println(message);
        } catch (Exception e) { }
    }
 
    //---call this from the main activity to 
    // shutdown the connection--- 
    public void cancel() {
        try {
            socket.close();
        } catch (IOException e) { }
    }
}
