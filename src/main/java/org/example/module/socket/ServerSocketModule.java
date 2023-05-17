package org.example.module.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.component.panel.TerminalPanel;
import org.example.entity.Problem;
import org.example.module.render.UIRender;
import org.json.JSONObject;

public class ServerSocketModule {
    public ServerSocketModule(){
    }

    public void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(10042);
            UIRender.renderMessage("Server listening on port 10042...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                UIRender.renderMessage("Client connected from " + clientSocket.getInetAddress().getHostName());

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
                    in.close();
                    String payload = requestBody.toString().trim();

                    if(!payload.isEmpty()) {
                        JSONObject jsonObject = new JSONObject(payload);
                        String requiredData = jsonObject.getString("name");
                        UIRender.renderMessage("Received payload for problem: " + requiredData);
                        ObjectMapper objectMapper = new ObjectMapper();
                        Problem problem = objectMapper.readValue(payload, Problem.class);
                        UIRender.startRendering(problem);
                        UIRender.renderMessage(problem.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    UIRender.renderMessage("Unable to parse the problem");
                }
                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

