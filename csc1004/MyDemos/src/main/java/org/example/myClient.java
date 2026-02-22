package org.example;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class myClient {
    private Socket s = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public myClient(String addr, int port) {
        try {
            s = new Socket(addr, port);
            System.out.println("connected");

            //Take input from terminal
            in = new DataInputStream(System.in);
            //Sends output to the Socket
            out = new DataOutputStream(s.getOutputStream());
        } catch (IOException i) {
            System.out.println(i);
        }
        String m = "";

        //keep reading until "Over" is input
        while (!m.equals("Over")) {
            try {
                m = in.readLine();
                //Writes a string to the underlying output stream
                out.writeUTF(m);
            } catch (IOException i) {
                System.out.println(i);
            }
        }

        try {
            in.close();
            out.close();
            s.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] args) {
        myClient c = new myClient("127.0.0.1", 5000);
    }
}


