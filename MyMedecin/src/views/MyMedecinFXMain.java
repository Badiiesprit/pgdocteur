/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author badi9
 */
public class MyMedecinFXMain extends Application {
    private Stage stage;
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.stage= primaryStage;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setTitle("My Medecin");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    public void resizeScene(double width, double height) {
        this.stage.setWidth(width);
        this.stage.setHeight(height);
    }
    
}
