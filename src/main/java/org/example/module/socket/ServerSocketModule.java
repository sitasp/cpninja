package org.example.module.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.component.panel.TerminalPanel;
import org.example.entity.Problem;
import org.example.module.swing.SwingUIModule;
import org.json.JSONObject;

public class ServerSocketModule {

    private SwingUIModule uiModule;

    public ServerSocketModule(SwingUIModule uiModule){
        this.uiModule = uiModule;
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(10042);
            TerminalPanel.displayMessage("Server listening on port 10042...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                TerminalPanel.displayMessage("Client connected from " + clientSocket.getInetAddress().getHostName());

                try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine;

                    StringBuilder requestBody = new StringBuilder();
                    while((inputLine = in.readLine()) != null) {
                        if(!inputLine.isEmpty()) {
                            if(inputLine.charAt(0) == '{') {
                                requestBody.append(inputLine);
                            }
                        }
                    }

//                    while(Objects.nonNull(inputLine = in.readLine())) {
//                        requestBody.append(inputLine);
//                    }
                    in.close();
                    String payload = requestBody.toString().trim();

                    if(!payload.isEmpty()) {
                        JSONObject jsonObject = new JSONObject(payload);
                        String requiredData = jsonObject.getString("name");
                        TerminalPanel.displayMessage("Received payload for problem: " + requiredData);
                        ObjectMapper objectMapper = new ObjectMapper();
                        Problem problem = objectMapper.readValue(payload, Problem.class);
                        TerminalPanel.displayMessage(problem.toString());
                    }



//                    while ((inputLine = in.readLine()) != null) {
//                        uiModule.displayMessage("Received JSON object: " + inputLine);
////                        JSONObject jsonObj = new JSONObject(inputLine);
//                        // Do something with the JSON object, such as parsing its fields
//                        // ...
//                    }
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

