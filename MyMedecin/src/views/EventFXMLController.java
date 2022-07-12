/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EventFXMLController implements Initializable {

    @FXML
    private TableView<Event> table_event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("insertEventFXML.fxml"));
        Parent root = loader.load();
        table_event.getScene().setRoot(root);
    }

    @FXML
    private void update(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateEventFXML.fxml"));
        Parent root = loader.load();
        table_event.getScene().setRoot(root);
    }
    @FXML
    private void delete(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("deleteEventFXML.fxml"));
        Parent root = loader.load();
        table_event.getScene().setRoot(root);
    }
}
