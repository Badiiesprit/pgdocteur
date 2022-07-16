/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Event;

import dataTableView.DataEvent;
import dataTableView.DataUser;
import entite.Events;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import servise.UserService;
import views.AdminFXMLController;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class EventFXMLController implements Initializable {

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
    private TableColumn<DataEvent, String> participant;
    @FXML
    private TableColumn<DataEvent, Button> delete;
    @FXML
    private TableColumn<DataEvent, Button> update;

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
            
            Button btnstatus = new Button("Update");
            btnstatus.getStyleClass().add("btn");
            btnstatus.getStyleClass().add("btnadmin");
            btnstatus.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    
                    UpdateEventFXMLController.setIdevent(event_med.getId());
                    
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateEventFXML.fxml"));
                        Parent root;
                        root = loader.load();
                        table_event.getScene().setRoot(root);
                    } catch (IOException ex) {
                        Logger.getLogger(EventFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            });
            Button btndelete = new Button("Delete");
            btndelete.getStyleClass().add("btn");
            btndelete.getStyleClass().add("btnadmin");
            btndelete.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    EventsService es = new EventsService();
                    es.delete(event_med);
                    ArrayList<Events> list=es.getAll();
                    table_event.getColumns().clear();
                    initTable(list);
                    table_event.refresh();
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
                    btndelete,
                    btnstatus
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
        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        update.setCellValueFactory(new PropertyValueFactory<>("update"));
        table_event.setItems(obs);
        table_event.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table_event.getColumns().addAll(id, event, description, date_deb,date_fin,specialite,gouvernerat,participant,delete,update);
    }
    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        System.out.println("views.Event.EventFXMLController.ajouter()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("insertEventFXML.fxml"));
        Parent root = loader.load();
        table_event.getScene().setRoot(root);
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        System.out.println("views.Event.EventFXMLController.home()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../AdminFXML.fxml"));
        Parent root = loader.load();
        table_event.getScene().setRoot(root);
    }

    @FXML
    private void deconnecter(ActionEvent event) {
        
    }

   
}
