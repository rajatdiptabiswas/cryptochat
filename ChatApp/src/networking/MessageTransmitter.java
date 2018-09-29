package networking;

import security.AES;

import java.io.IOException;
import java.net.Socket;

public class MessageTransmitter extends Thread {

    String message, hostname;
    int port;

    private static final String key = "1qew38jba0x4sdez";
    AES aes = new AES(key);

    public MessageTransmitter() {}

    public MessageTransmitter(String message, String hostname, int port) {
        this.message = message;
        this.hostname = hostname;
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket s = new Socket(hostname, port);
            String encryptedMessage = null;
            try {
                encryptedMessage = aes.encrpyt(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
            s.getOutputStream().write(encryptedMessage.getBytes());
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
