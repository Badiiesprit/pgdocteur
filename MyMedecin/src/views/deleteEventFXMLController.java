/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dataTableView.DataEvent;
import dataTableView.DataUser;
import entite.User;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import entite.Events;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import servise.EventsService;

/**
 *
 * @author hp
 */
public class deleteEventFXMLController implements Initializable {
    @FXML
    private TableView<Events> table_event;
    @FXML
    private TableColumn<Events, Integer> id;
    @FXML
    private TableColumn<Events, String> event;
    @FXML
    private TableColumn<Events, String> description;
    @FXML
    private TableColumn<Events, String> date_deb;
    @FXML
    private TableColumn<Events, String> date_fin;
    @FXML
    private TableColumn<Events, String> specialite;
    @FXML
    private TableColumn<Events, String> gouvernerat;
    @FXML
    private TableColumn<Events, Integer> participant;
    @FXML
    private TableColumn<Events, Button> delete;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Events> obs=FXCollections.observableArrayList();
        EventsService us = new EventsService();
        ArrayList<Events> list=us.getAll();
        
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        event.setCellValueFactory(new PropertyValueFactory<>("nom"));
        description.setCellValueFactory(new PropertyValueFactory<>("description"));
        date_deb.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
         participant.setCellValueFactory(new PropertyValueFactory<>("nb_participant"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("id_specialite"));
        gouvernerat.setCellValueFactory(new PropertyValueFactory<>("id_gouvernerat"));
        table_event.setItems(obs);
        
        // this.initTable(list);
    }
    
//     private void initTable(ArrayList<Events> list){
//        
//        ObservableList<DataEvent> obs=FXCollections.observableArrayList();
//        list.forEach((Events event) ->{
//                Button btndelete = new Button("Delete");
//                obs.add(new DataEvent(
//                        event.getId(),
//                        event.getName(),
//                        event.getDescription(),
//                        event.getDate_deb().toString(),
//                        event.getDate_fin().toString(),
//                        String.valueOf(event.getId_gouvernorat()),
//                        String.valueOf(event.getId_specialite()),
//                        event.getNb_participant(),
//                        btndelete
//                ));  
//        });
//            
//       
//        id.setCellValueFactory(new PropertyValueFactory<>("id"));
//        event.setCellValueFactory(new PropertyValueFactory<>("event"));
//        description.setCellValueFactory(new PropertyValueFactory<>("description"));
//        date_deb.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
//        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
//        specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
//        gouvernerat.setCellValueFactory(new PropertyValueFactory<>("gouvernerat"));
//        participant.setCellValueFactory(new PropertyValueFactory<>("participant"));
//        delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
//        table_event.setItems(obs);
//        table_event.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        table_event.getColumns().addAll(id, event, description, date_deb,date_fin,specialite,gouvernerat,participant,delete);
//    }
//    
//}
}