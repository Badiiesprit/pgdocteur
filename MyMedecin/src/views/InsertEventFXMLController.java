/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Events;
import entite.Gouvernorat;
import entite.Specialite;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import servise.EventsService;

import servise.GouvernoratSpecialiteServise;

/**
 * FXML Controller class
 *
 * @author hp
 */
public class InsertEventFXMLController implements Initializable {

    @FXML
    private TextField name;
    @FXML
    private TextField nb_participant;
    @FXML
    private ComboBox<Specialite> specialite;
    @FXML
    private ComboBox<Gouvernorat> gouvernerat;
    @FXML
    private ComboBox<String> type_participant;
    @FXML
    private DatePicker date_deb;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextArea description;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox("g");initComboBox("s");initComboBox("t");
    }    
    public void initComboBox(String type){
        GouvernoratSpecialiteServise gss = new GouvernoratSpecialiteServise();
        if(type=="g"){
            ArrayList<Gouvernorat> list=gss.getAllGouvernorat();
            ObservableList<Gouvernorat> obs=FXCollections.observableArrayList(list);
            gouvernerat.setItems(obs);
        }
        if(type=="s"){
            ArrayList<Specialite> list=gss.getAllSpecialite();
            ObservableList<Specialite> obs=FXCollections.observableArrayList(list);
            specialite.setItems(obs);
        }
        if(type=="t"){
            ArrayList<String> list = new ArrayList<String>();;
            list.add("patient");
            list.add("medicin");
            ObservableList<String> obs=FXCollections.observableArrayList(list);
            type_participant.setItems(obs);
        }
    }
    @FXML
    private void insert(ActionEvent event) {
        EventsService es =new EventsService();
        Events e=new Events();
        Date date_d=new Date(0);
        e.setDate_deb(date_d);
        e.setDate_fin(date_d);
        e.setName(name.getText());
        e.setDescription(description.getText());
        e.setId_gouvernorat(gouvernerat.getValue().getId());
        e.setId_specialite(specialite.getValue().getId());
        e.setNb_participant(Integer.parseInt(nb_participant.getText()));
        int type=0;
        if (type_participant.getValue()=="patient"){
            type=1;
        }else if (type_participant.getValue()=="medecin"){
            type=2;
        }
        e.setType_particpant(type);
        
        
        es.insert(e);
    }

    @FXML
    private void liste(ActionEvent event) {
        
    }

    @FXML
    private void update(ActionEvent event) {
        
    }
    
}

