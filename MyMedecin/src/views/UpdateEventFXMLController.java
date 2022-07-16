/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class UpdateEventFXMLController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField nb_participant;
    @FXML
    private ComboBox<?> specialite;
    @FXML
    private ComboBox<?> gouvernerat;
    @FXML
    private ComboBox<?> type_participant;
    @FXML
    private DatePicker date_deb;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextArea description;
    @FXML
    private ComboBox<?> liste_event;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void update(ActionEvent event) {
    }

    @FXML
    private void ajouter(ActionEvent event) {
    }

    @FXML
    private void liste(ActionEvent event) {
    }
    
}
