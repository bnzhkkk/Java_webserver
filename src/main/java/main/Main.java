package main;

import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class Main {

    public static void main(String[] args) throws Exception {
        try (ServerSocket serverSocket = new ServerSocket(5050)) {
            System.out.println("Server started");
            while (true) {
                Socket userSocket = serverSocket.accept();
                System.out.println("User connected: " + userSocket);
                SocketThread socketThread = new SocketThread(userSocket);
                socketThread.start();
            }
        }
    }

    private static class SocketThread extends Thread {
        private final Socket userSocket;

        private SocketThread(Socket userSocket) {
            this.userSocket = userSocket;
        }

        @Override
        public void run() {
            System.out.println("Run: " + this.getName());
            try (
                    PrintWriter out = new PrintWriter(userSocket.getOutputStream(), true);
                    BufferedReader in = new BufferedReader(new InputStreamReader(userSocket.getInputStream()))
            ) {
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    out.println(inputLine);

                    if (inputLine.equals("Bye.")) {
                        break;
                    }
                }

                userSocket.close();
                System.out.println("Client disconnected: " + userSocket);

            } catch (IOException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }
    }
}