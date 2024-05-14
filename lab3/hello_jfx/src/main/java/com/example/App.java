package com.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Hello World!");
        TextField helloTF = new TextField("");
        Button btn = new Button();
        btn.setText("Say 'Hello'");
        btn.setOnAction((event) -> {
            helloTF.setText("Hello World!");
        });
        StackPane root = new StackPane();
        StackPane.setAlignment(helloTF, Pos.TOP_CENTER);
        root.getChildren().addAll(helloTF, btn);
        stage.setScene(new Scene(root, 300, 300));
        stage.show();

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}