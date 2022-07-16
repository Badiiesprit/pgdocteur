/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Event;

import entite.Events;
import entite.Gouvernorat;
import entite.Specialite;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
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
public class UpdateEventFXMLController implements Initializable {

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
    @FXML
    private ComboBox<Events> liste_event;

    private static int idevent ;

    public static int getIdevent() {
        return idevent;
    }

    public static void setIdevent(int idevent) {
        UpdateEventFXMLController.idevent = idevent;
    }
      
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox("g");initComboBox("s");initComboBox("t");
        initComboBox("e");

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
        if(type=="e"){
            EventsService es = new EventsService();
            ArrayList<Events> list=es.getAll();
            System.out.println("UpdateEvent initComboBox :: "+ list);
            ObservableList<Events> obs=FXCollections.observableArrayList(list);
            liste_event.setItems(obs);
        }
    }

    @FXML
    private void update(ActionEvent event) {
        EventsService es =new EventsService();
        Events e=new Events();
        Date date_d=new Date(0);
        e.setId(UpdateEventFXMLController.getIdevent());
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
        es.update(e);
        UpdateEventFXMLController.setIdevent(0);
    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("insertEventFXML.fxml"));
        Parent root = loader.load();
        name.getScene().setRoot(root);
    }

    @FXML
    private void liste(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventFXML.fxml"));
        Parent root = loader.load();
        name.getScene().setRoot(root);
    }
    
}
