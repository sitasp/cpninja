package org.example;
import org.example.module.socket.ServerSocketModule;
import org.example.module.swing.SwingUIModule;

public class Main {
    public static void main(String[] args) {
        SwingUIModule uiModule = SwingUIModule.getInstance();
        ServerSocketModule serverModule = new ServerSocketModule(uiModule);

        new Thread(() -> {
            serverModule.startServer();
        }).start();

        uiModule.displayMessage("Server started...");
    }
}

