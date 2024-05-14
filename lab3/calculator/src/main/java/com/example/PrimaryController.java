package com.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PrimaryController {

    @FXML
    private Button Btn_0;

    @FXML
    private Button Btn_1;

    @FXML
    private Button Btn_2;

    @FXML
    private Button Btn_3;

    @FXML
    private Button Btn_4;

    @FXML
    private Button Btn_5;

    @FXML
    private Button Btn_6;

    @FXML
    private Button Btn_7;

    @FXML
    private Button Btn_8;

    @FXML
    private Button Btn_9;

    @FXML
    private Button Btn_A;

    @FXML
    private Button Btn_B;

    @FXML
    private Button Btn_C;

    @FXML
    private Button Btn_D;

    @FXML
    private Button Btn_Div;

    @FXML
    private Button Btn_E;

    @FXML
    private Button Btn_F;

    @FXML
    private Button Btn_Mul;

    @FXML
    private Button Btn_eqauls;

    @FXML
    private Button Btn_minus;

    @FXML
    private Button Btn_plus;

    @FXML
    private ComboBox<String> baseCB;

    @FXML
    private Button clearBtn;

    @FXML
    private TextField resultTF;

    @FXML
    void Btn_click_0(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "0");

    }

    @FXML
    void Btn_click_1(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "1");

    }

    @FXML
    void Btn_click_2(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "2");

    }

    @FXML
    void Btn_click_3(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "3");

    }

    @FXML
    void Btn_click_4(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "4");

    }

    @FXML
    void Btn_click_5(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "5");

    }

    @FXML
    void Btn_click_6(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "6");

    }

    @FXML
    void Btn_click_7(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "7");

    }

    @FXML
    void Btn_click_8(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "8");

    }

    @FXML
    void Btn_click_9(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "9");

    }

    @FXML
    void Btn_click_A(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "A");

    }

    @FXML
    void Btn_click_B(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "B");

    }

    @FXML
    void Btn_click_C(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "C");

    }

    @FXML
    void Btn_click_D(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "D");

    }

    @FXML
    void Btn_click_Div(ActionEvent event) {
        resultTF.setText(resultTF.getText() + " / ");

    }

    @FXML
    void Btn_click_E(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "E");

    }

    @FXML
    void Btn_click_F(ActionEvent event) {
        resultTF.setText(resultTF.getText() + "F");

    }

    @FXML
    void Btn_click_equals(ActionEvent event) {

    }

    @FXML
    void Btn_click_minus(ActionEvent event) {
        resultTF.setText(resultTF.getText()+ " - ");

    }

    @FXML
    void Btn_click_mul(ActionEvent event) {
        resultTF.setText(resultTF.getText()+ " * ");
    }

    @FXML
    void Btn_click_plus(ActionEvent event) {
        resultTF.setText(resultTF.getText()+" + ");

    }

    @FXML
    void clear_result(ActionEvent event) {
        resultTF.clear();

    }

    @FXML
    void select_base(ActionEvent event) {
        if (!resultTF.getText().isEmpty())
        {

        }
        Btn_minus.setDisable(false);
        Btn_plus.setDisable(false);
        Btn_Mul.setDisable(false);
        Btn_Div.setDisable(false);
        Btn_eqauls.setDisable(false);
        clearBtn.setDisable(false);
        String base = baseCB.getSelectionModel().getSelectedItem();
        if(base.equals("BIN") )
        {
            Btn_0.setDisable(false);
            Btn_1.setDisable(false);
            Btn_2.setDisable(true);
            Btn_3.setDisable(true);
            Btn_4.setDisable(true);
            Btn_5.setDisable(true);
            Btn_6.setDisable(true);
            Btn_7.setDisable(true);
            Btn_8.setDisable(true);
            Btn_9.setDisable(true);
            Btn_A.setDisable(true);
            Btn_B.setDisable(true);
            Btn_C.setDisable(true);
            Btn_D.setDisable(true);
            Btn_E.setDisable(true);
            Btn_F.setDisable(true);
        } else if (base.equals("OCT")) {
            Btn_0.setDisable(false);
            Btn_1.setDisable(false);
            Btn_2.setDisable(false);
            Btn_3.setDisable(false);
            Btn_4.setDisable(false);
            Btn_5.setDisable(false);
            Btn_6.setDisable(false);
            Btn_7.setDisable(false);
            Btn_8.setDisable(true);
            Btn_9.setDisable(true);
            Btn_A.setDisable(true);
            Btn_B.setDisable(true);
            Btn_C.setDisable(true);
            Btn_D.setDisable(true);
            Btn_E.setDisable(true);
            Btn_F.setDisable(true);
        }
        else if(base.equals("DEC")) {
            Btn_0.setDisable(false);
            Btn_1.setDisable(false);
            Btn_2.setDisable(false);
            Btn_3.setDisable(false);
            Btn_4.setDisable(false);
            Btn_5.setDisable(false);
            Btn_6.setDisable(false);
            Btn_7.setDisable(false);
            Btn_8.setDisable(false);
            Btn_9.setDisable(false);
            Btn_A.setDisable(true);
            Btn_B.setDisable(true);
            Btn_C.setDisable(true);
            Btn_D.setDisable(true);
            Btn_E.setDisable(true);
            Btn_F.setDisable(true);

        }
        else if(base.equals("HEX")) {
            Btn_0.setDisable(false);
            Btn_1.setDisable(false);
            Btn_2.setDisable(false);
            Btn_3.setDisable(false);
            Btn_4.setDisable(false);
            Btn_5.setDisable(false);
            Btn_6.setDisable(false);
            Btn_7.setDisable(false);
            Btn_8.setDisable(false);
            Btn_9.setDisable(false);
            Btn_A.setDisable(false);
            Btn_B.setDisable(false);
            Btn_C.setDisable(false);
            Btn_D.setDisable(false);
            Btn_E.setDisable(false);
            Btn_F.setDisable(false);
        }

    }
    @FXML
    void initialize() {
        baseCB.getItems().add("BIN");
        baseCB.getItems().add("OCT");
        baseCB.getItems().add("DEC");
        baseCB.getItems().add("HEX");
    }
}
