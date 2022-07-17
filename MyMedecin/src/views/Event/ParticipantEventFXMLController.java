/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Event;

import dataTableView.DataEvent;
import entite.Events;
import entite.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import servise.EventsService;
import servise.LoginService;
import servise.UserService;
import views.HomeFXMLController;

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
    @FXML
    private Button btn_rdv;
    @FXML
    private ImageView img_profile;
    @FXML
    private Text infoUserConnected1;
    @FXML
    private Text infoUserConnected;

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
        this.initInfoUserConnected();
    }  
    public void  initInfoUserConnected(){
        UserService us = new UserService();
        User u = us.getById(LoginService.getUserConnected());
        if(u.getRole()==2){
            infoUserConnected1.setText("Medecine");
        }else if(u.getRole()==3){
            infoUserConnected1.setText("Pharmacien");
        }else{
            infoUserConnected1.setText("Patient");
        }
        //img_profile.set
        infoUserConnected.setText(u.toString());
        Image image = new Image("http://localhost/uploads/"+u.getPhoto_profil());
        img_profile.setImage(image);
    }
    private void initTable(ArrayList<Events> list){
        
        
        ObservableList<DataEvent> obs=FXCollections.observableArrayList();
        
        list.forEach(event_med ->{
            
            Button btnconsulter = new Button("Consulter");
            btnconsulter.getStyleClass().add("btn");
            btnconsulter.getStyleClass().add("btnadmin");
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
        date_deb.setCellValueFactory(new PropertyValueFactory<>("date_deb"));
        date_fin.setCellValueFactory(new PropertyValueFactory<>("date_fin"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        gouvernerat.setCellValueFactory(new PropertyValueFactory<>("gouvernerat"));
        participant.setCellValueFactory(new PropertyValueFactory<>("participant"));
        consulter.setCellValueFactory(new PropertyValueFactory<>("delete"));
        table_event.setItems(obs);
        table_event.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table_event.getColumns().addAll(id, event, date_deb,date_fin,specialite,gouvernerat,participant,consulter);
    }
    @FXML
    private void home(ActionEvent event) throws IOException {
        System.out.println("views.Event.EventFXMLController.home()");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../HomeFXML.fxml"));
        Parent root = loader.load();
        table_event.getScene().setRoot(root);
    }

    @FXML
    private void updateUser(ActionEvent event) throws IOException {
         UserService us = new UserService();
        int role =us.getById(LoginService.getUserConnected()).getRole();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../profiPatient.fxml"));
        if(role==2){
            loader = new FXMLLoader(getClass().getResource("../ProfilMedecin.fxml"));
        }else if(role==3){
            loader = new FXMLLoader(getClass().getResource("../ProfilPharmacie.fxml"));
        }
        Parent root = loader.load();
        infoUserConnected.getScene().setRoot(root);
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) throws IOException { 
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Event/participantEventFXML.fxml"));
        Parent root = loader.load();
        img_profile.getScene().setRoot(root);
    }

    @FXML
    private void forum(ActionEvent event) throws IOException {
         UserService us = new UserService();
        User u = us.getById(LoginService.getUserConnected());
        if(u.getRole()==1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Forum/ReponseHome.fxml"));
            Parent root = loader.load();
            img_profile.getScene().setRoot(root);
        }else if(u.getRole()==2){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Forum/BlogFXML.fxml"));
            Parent root = loader.load();
            img_profile.getScene().setRoot(root);
        }else{
            
        }
    }

    @FXML
    private void rensezvous(ActionEvent event) throws IOException {
         UserService us = new UserService();
        int role = us.getById(LoginService.getUserConnected()).getRole();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Rendezvous/RendezvousFXML.fxml"));
        if (role == 2) {
            loader = new FXMLLoader(getClass().getResource("../Rendezvous/CalanderFXML.fxml"));
        } else if (role == 3) {
            loader = new FXMLLoader(getClass().getResource("../HomeFXML.fxml"));
        }
        Parent root = loader.load();
        img_profile.getScene().setRoot(root);
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
        LoginService.setUserConnected(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../LoginFXML.fxml"));
        Parent root = loader.load();
        infoUserConnected.getScene().setRoot(root);
    }
    
}
