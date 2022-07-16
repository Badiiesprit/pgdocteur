/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Event;

import dataTableView.DataEvent;
import entite.Events;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servise.EventsService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class ParticipantEventFXMLController implements Initializable {

     @FXML
    private TableView<DataEvent> table_event;
    @FXML
    private TableColumn<DataEvent, Integer> id;
    @FXML
    private TableColumn<DataEvent, String> event;
    @FXML
    private TableColumn<DataEvent, String> description;
    @FXML
    private TableColumn<DataEvent, String> date_deb;
    @FXML
    private TableColumn<DataEvent, String> date_fin;
    @FXML
    private TableColumn<DataEvent, String> specialite;
    @FXML
    private TableColumn<DataEvent, String> gouvernerat;
    @FXML
    private TableColumn<DataEvent, Button> consulter;
    @FXML
    private TableColumn<DataEvent, String> participant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         EventsService es = new EventsService();
        ArrayList<Events> list=es.getAll();
        System.out.println(list);
        this.initTable(list);
    }    
  private void initTable(ArrayList<Events> list){
        
        
        ObservableList<DataEvent> obs=FXCollections.observableArrayList();
        
        list.forEach(event_med ->{
            
            Button btnconsulter = new Button("Consulter");
            btnconsulter.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    
                    ConsulterEventController.setIdevent(event_med.getId());
                    
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterEvent.fxml"));
                        Parent root;
                        root = loader.load();
                        table_event.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(EventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            obs.add(new DataEvent(
                    event_med.getId(),
                    event_med.getName(),
                    event_med.getDescription(),
                    event_med.getDate_deb().toString(),
                    event_med.getDate_fin().toString(),
                    String.valueOf(event_med.getId_gouvernorat()) ,
                    String.valueOf(event_med.getId_specialite()),
                    event_med.getNb_participant(),
                    btnconsulter,
                    btnconsulter
            ));  
            
            
        });
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        event.setCellValueFactory(new PropertyValueFactory<>("event"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_deb.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        gouvernerat.setCellValueFactory(new PropertyValueFactory<>("gouvernerat"));
        participant.setCellValueFactory(new PropertyValueFactory<>("participant"));
        consulter.setCellValueFactory(new PropertyValueFactory<>("delete"));
        table_event.setItems(obs);
        table_event.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table_event.getColumns().addAll(id, event, description, date_deb,date_fin,specialite,gouvernerat,participant,consulter);
    }
    @FXML
    private void home(ActionEvent event) throws IOException {
        System.out.println("views.Event.EventFXMLController.home()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../HomeFXML.fxml"));
        Parent root = loader.load();
        table_event.getScene().setRoot(root);
    }
    
}
