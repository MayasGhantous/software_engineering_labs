package il.cshaifasweng.OCSFMediatorExample.client;
import static il.cshaifasweng.OCSFMediatorExample.client.MovieEditingDetailsController.go_to_screening_movie;

import com.mysql.cj.protocol.x.XMessage;
import il.cshaifasweng.OCSFMediatorExample.entities.Message;
import il.cshaifasweng.OCSFMediatorExample.entities.Movie;
import il.cshaifasweng.OCSFMediatorExample.entities.Screening;
import javafx.application.Platform;
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
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DateStringConverter;
import javafx.scene.input.MouseEvent;


import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.net.URL;
import java.sql.Time;
import java.util.*;

import static il.cshaifasweng.OCSFMediatorExample.client.MovieEditingDetailsController.go_to_screening_movie;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.Current_Message;
import static il.cshaifasweng.OCSFMediatorExample.client.SimpleClient.getClient;

public class EditScreeningController {


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
    private TextField rows_number;

    @FXML
    private TextField column_number;

    @FXML
    private TableColumn<Screening,Date> screening_time_column;

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
        Message message = new Message(10,"#RemoveScreening");
        message.setObject(current_screening);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void search(ActionEvent event) {
        Message message = new Message(8,"#SearchBranchForScreening");
        message.setObject(current_movie);
        message.setObject2(search_branch_combobox.getValue().toString());
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        Date time1 = null;
        try {
            time1 = timeFormat.parse(screening_time.getText());
        } catch (ParseException e) {
            System.out.print("aha2");
            e.printStackTrace(); // Handle the ParseException appropriately
        }

        datec.setHours(time1.getHours());
        datec.setMinutes(time1.getMinutes());

        int room = Integer.parseInt(room_number.getText());
        String theater = "";
        for(int i=0 ; i<10;i++)
            theater += "0, 0, 0, 0, 0, 0, 0, 0, 0, 0\n";
        Screening screening = new Screening(datec,room,theater,branch);
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

    @Subscribe
    public void update_event(UpdateScreeningForMovieEvent event)
    {
        Platform.runLater(()->{

            if(((Movie)event.getMessage().getObject2()).getAuto_number_movie() == current_movie.getAuto_number_movie())
            {
                get_data(event.getMessage());
            }

        });
    }

    @FXML
    void select_screening(MouseEvent event) {
        int index = table_view.getSelectionModel().getSelectedIndex();
        if(index <= -1)
            return;
        SimpleDateFormat dateFormatC = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormatC = new SimpleDateFormat("HH:mm");
        Screening_ID.setText(id_column.getCellData(index).toString());
        Branch.setValue(branch_column.getCellData(index).toString());
        Date timec = screening_time_column.getCellData(index);
        screening_time.setText(timeFormatC.format(timec));
        Date datec = date_column.getCellData(index);
        date.setText(dateFormatC.format(datec));
        room_number.setText(room_column.getCellData(index).toString());
        Message message = new Message(4,"#get_theater_and_room_map");
        message.setObject(id_column.getCellData(index).toString());
        List<String> list1 = new ArrayList<>();
        list1.add(branch_column.getCellData(index));
        list1.add(room_column.getCellData(index).toString());
        message.setObject2(list1);
        try {
            SimpleClient.getClient().sendToServer(message);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static Screening current_screening;
    @Subscribe
    public void update_boxes(UpdateScreeningBoxesEvent event)
    {
        Platform.runLater(()->{
            List<Integer> row_column =(List<Integer>) event.getMessage().getObject2();
            Screening screening = (Screening) event.getMessage().getObject();
            current_screening = screening;
            System.out.println("screening_auto_number:");
            System.out.println(screening.getAuto_number_screening());
            theater_map.setText(screening.getTheater_map());
            if (row_column != null) {
                rows_number.setText(row_column.get(0).toString());
                column_number.setText(row_column.get(1).toString());
            }
        });
    }

    @FXML
    void get_row_column(ActionEvent event) {

    }

    private void get_data(Message m)
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
        screening_time_column.setCellValueFactory(new PropertyValueFactory<>("date_time"));
        screening_time_column.setCellFactory(column -> new TextFieldTableCell<>(new DateStringConverter("HH:mm")));
        date_column.setCellValueFactory(new PropertyValueFactory<>("date_time"));
        date_column.setCellFactory(column -> new TextFieldTableCell<>(new DateStringConverter("dd/MM/yyyy")));
        room_column.setCellValueFactory(new PropertyValueFactory<>("room_number"));
        list = FXCollections.observableArrayList((List<Screening>)(m.getObject()));
        table_view.setItems(list);
    }

    private  Movie current_movie;
    @FXML
    public void initialize() {
        EventBus.getDefault().register(this);
        current_movie = go_to_screening_movie;
        Movie_name.setText(current_movie.getMovie_name());
        Branch.getItems().clear();
        Branch.getItems().add("Sakhnin");
        Branch.getItems().add("Haifa");
        Branch.getItems().add("Nazareth");
        Branch.getItems().add("Nhif");
        search_branch_combobox.getItems().clear();
        search_branch_combobox.getItems().add("");
        search_branch_combobox.getItems().add("Sakhnin");
        search_branch_combobox.getItems().add("Haifa");
        search_branch_combobox.getItems().add("Nazareth");
        search_branch_combobox.getItems().add("Nhif");
        get_data(Current_Message);

    }

}
