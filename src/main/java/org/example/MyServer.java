package org.example;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import org.json.JSONObject;

public class MyServer extends JFrame {

    private JTextArea messageArea;

    public MyServer() {
        super("Server");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 1000);
        setLocationRelativeTo(null);

        messageArea = new JTextArea();
        messageArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(messageArea);

        getContentPane().add(scrollPane, BorderLayout.CENTER);

        setVisible(true);

        try {
            ServerSocket serverSocket = new ServerSocket(10042);
            messageArea.append("Server listening on port 10042...\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                messageArea.append("Client connected from " + clientSocket.getInetAddress().getHostName() + "\n");

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine;
                    while ((inputLine = in.readLine()) != null) {
                        messageArea.append("Received JSON object: " + inputLine + "\n");
//                        JSONObject jsonObj = new JSONObject(inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
