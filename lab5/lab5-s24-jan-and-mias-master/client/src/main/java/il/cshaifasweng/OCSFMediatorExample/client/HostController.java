package il.cshaifasweng.OCSFMediatorExample.client;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import java.awt.*;
import java.io.IOException;

public class HostController {

    @FXML
    private TextField hostTextField;
    @FXML
    private TextField portTextField;
    @FXML
    private TextField passwordTextField;
    SimpleClient client;

    @FXML
    public void submit_func(javafx.event.ActionEvent actionEvent) {
        String host = hostTextField.getText();
        int portg = Integer.parseInt(portTextField.getText());
        client =  new SimpleClient(host,portg);
        try{
            client.openConnection();
            Message message = new Message(0,passwordTextField.getText());
            client.sendToServer(message);
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
}
