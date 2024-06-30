package il.cshaifasweng.OCSFMediatorExample.client;
import static il.cshaifasweng.OCSFMediatorExample.client.MovieEditingDetailsController.go_to_screening_movie;

import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.sql.Time;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static il.cshaifasweng.OCSFMediatorExample.client.MovieEditingDetailsController.go_to_screening_movie;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.Current_Message;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.getClient;

public class EditScreeningController implements Initializable {


    @FXML
    private ComboBox<String> Branch;

    @FXML
    private Text ErrorMessage;

    @FXML
    private Text Movie_name;

    @FXML
    private TextField Screening_ID;

    @FXML
    private Button add;

    @FXML
    private TableColumn<Screening,String> branch_column;

    @FXML
    private TextField date;

    @FXML
    private TableColumn<Screening,Date> date_column;

    @FXML
    private TableColumn<Screening,Integer> id_column;

    @FXML
    private Button remove;

    @FXML
    private TableColumn<Screening,Integer> room_column;

    @FXML
    private TextField room_number;

    @FXML
    private TextField screening_time;

    @FXML
    private TableColumn<Screening,Time> screening_time_column;

    @FXML
    private ComboBox<String> search_branch_combobox;

    @FXML
    private TableView<Screening> table_view;

    @FXML
    private TextArea theater_map;

    @FXML
    private Button update;


    @FXML
    void remove_screening(ActionEvent event) {

    }

    @FXML
    void search(ActionEvent event) {

    }

    @FXML
    void update_screning(ActionEvent event) {

    }

    @FXML
    void add_screening(ActionEvent event) {
        String branch = Branch.getValue();
        SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date datec = null;
        try {
             datec = targetFormat.parse(date.getText());
        } catch (ParseException e) {
            System.out.print("aha1");
            throw new RuntimeException(e);
        }
        Time sqlTime = null;
        try {
            Date utilDate = timeFormat.parse(screening_time.getText());
            sqlTime = new Time(utilDate.getTime());
        } catch (ParseException e) {
            System.out.print("aha2");
            e.printStackTrace(); // Handle the ParseException appropriately
        }

        int room = Integer.parseInt(room_number.getText());
        String theater = "";
        for(int i=0 ; i<10;i++)
            theater += "0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n";
        Screening screening = new Screening(sqlTime,datec,room,theater,branch);
        screening.setMovie(current_movie);

        Message message = new Message(4,"#AddNewScreening");
        message.setObject(screening);

        try {
            SimpleClient.getClient().openConnection();
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            System.out.print("aha3");
            throw new RuntimeException(e);
        }


    }

    private ObservableList<Screening> list;

    private void get_data()
    {
        /*@Id
    private int auto_number_screening;
    private Time time_;
    private Date date_;
    private int room_number;
    private String theater_map;
    private String branch;
*/

        id_column.setCellValueFactory(new PropertyValueFactory<>("auto_number_screening"));
        branch_column.setCellValueFactory(new PropertyValueFactory<>("branch"));
        screening_time_column.setCellValueFactory(new PropertyValueFactory<>("time_"));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date_"));
        room_column.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        list = FXCollections.observableArrayList((List<Screening>)(Current_Message.getObject()));
        table_view.setItems(list);
    }

    private static Movie current_movie;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        current_movie = go_to_screening_movie;
        Branch.getItems().clear();
        Branch.getItems().add("Sakhnin");
        Branch.getItems().add("Haifa");
        Branch.getItems().add("Nazareth");
        Branch.getItems().add("Nhif");
        search_branch_combobox.getItems().clear();
        search_branch_combobox.getItems().add("Sakhnin");
        search_branch_combobox.getItems().add("Haifa");
        search_branch_combobox.getItems().add("Nazareth");
        search_branch_combobox.getItems().add("Nhif");

        get_data();

    }

}
