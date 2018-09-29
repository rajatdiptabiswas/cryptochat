package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MessageListener extends Thread {

    ServerSocket server;
    int port;
    WritableGUI gui;

    public MessageListener(WritableGUI gui, int port) throws IOException {
        this.port = port;
        this.gui = gui;
        server = new ServerSocket(port);
    }

    public MessageListener() throws IOException {}

    @Override
    public void run() {

        Socket clientSocket;

        try {
            while ((clientSocket = server.accept()) != null) {
                InputStream is = clientSocket.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                String line = br.readLine();

                if (line != null)
                    gui.write(line);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

    }

}