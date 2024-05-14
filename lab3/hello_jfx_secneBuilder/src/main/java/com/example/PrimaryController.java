package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private TextField counterTF;

    @FXML
    private Button helloBtn;

    @FXML
    private TextField helloTF;

    @FXML
    void sayHello(ActionEvent event) {
        helloTF.setText("Hello World!");
        int c= Integer.parseInt(counterTF.getText());
        c++;
        counterTF.setText(String.valueOf(c));
    }

}
