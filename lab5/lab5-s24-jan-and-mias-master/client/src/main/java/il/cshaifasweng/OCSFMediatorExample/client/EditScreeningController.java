package il.cshaifasweng.OCSFMediatorExample.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class EditScreeningController {

    @FXML
    private ComboBox<?> Branch;

    @FXML
    private Text Movie_name;

    @FXML
    private TextField Screening_ID;

    @FXML
    private TextField date;

    @FXML
    private TextField room_number;

    @FXML
    private TextField screening_time;

    @FXML
    private ComboBox<String> search_branch_combobox;

    @FXML
    private TableView<?> table_view;

    @FXML
    private TextArea theater_map;

    @FXML
    void search(ActionEvent event) {

    }

}