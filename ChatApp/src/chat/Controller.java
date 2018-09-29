package chat;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import networking.MessageListener;
import networking.MessageTransmitter;
import networking.WritableGUI;
import security.AES;

import java.io.IOException;

public class Controller implements WritableGUI {

    @FXML private TextField hostName;
    @FXML private TextField sourcePort;
    @FXML private TextField targetPort;
    @FXML private TextArea chatBox;
    @FXML private TextField messageTextBox;

    private static final String key = "1qew38jba0x4sdez";
    AES aes = new AES(key);

    public void sendMessage() {
        MessageTransmitter transmitter = new MessageTransmitter(messageTextBox.getText(), hostName.getText(), Integer.parseInt(targetPort.getText()));
        transmitter.start();
    }

    public void listenPort() {
        try {
            MessageListener listener = new MessageListener(this, Integer.parseInt(sourcePort.getText()));
            listener.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String str) {
        chatBox.appendText("ENCRYPTED MESSAGE\n" + str + "\n");
        try {
            chatBox.appendText("DECRYPTED MESSAGE\n" + aes.decrypt(str) + "\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
