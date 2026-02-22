package org.example;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class myServer {
    /**1. ServerSocket: This Socket waits for incoming client requests.
     *    It listens for connections on a specific port.
     *
     * 2. Socket: Once a connection is established, the server used this
     *    socket to communicate with client.
     *
     * 3. Once the connection is established, you can send and receive data
     *    the socket using streams.
     *
     * 4. Once the communication is finished, it's important to close the socket
     *    and the input/output streams to free up resources.
     */

    private Socket s;
    private ServerSocket ss;
    private DataInputStream in;

    public myServer(int port) {
        try {
            ss = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            s = ss.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(
                    new BufferedInputStream(s.getInputStream()));

            String m = "";

            while (!m.equals("Over")) {
                try {
                    m = in.readUTF();
                    System.out.println(m);
                }
                catch (IOException i) {
                    System.out.println(i);
                }
            }
            System.out.println("Closing connection");

            s.close();
            in.close();
    }
        catch(IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        myServer s = new myServer(5000);
    }
}
