/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Forum;

import dataTableView.DataUser;
import entite.Questions;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import servise.LoginService;
import servise.QuestionsService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class BlogFXMLController implements Initializable {

    @FXML
    private TableColumn<Questions, Integer> id_question;
    @FXML
    private TableColumn<Questions, String> question;
    @FXML
    private TextField serch;
    @FXML
    private Button rech;
    @FXML
    private TableView<Questions> table_question;
    @FXML
    private TableColumn<Questions,Button> btn_update;
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
       QuestionsService qs = new QuestionsService();
       initTable(qs.getAll());
        UserService us = new UserService();
        int role = us.getById(LoginService.getUserConnected()).getRole();
        if (role == 1) {
            btn_rdv.setText("Prendre rendez-vous");
        } else if (role == 2) {
            btn_rdv.setText("Calendrier");
        } else if (role == 3) {
            btn_rdv.setText("Pharmacie");;
        }
    initInfoUserConnected();
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
     private void initTable(ArrayList<Questions> list){
        
        
        ObservableList<Questions> obs=FXCollections.observableArrayList();
        
        list.forEach(questions ->{
                Button btnstatus = new Button("Update");
                btnstatus.getStyleClass().add("btn");
                btnstatus.getStyleClass().add("btnadmin");
                btnstatus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        /****/
                        ReponsesController.setId_question(questions.getId_question());
                         FXMLLoader loader = new FXMLLoader(getClass().getResource("Reponses.fxml"));
                         Parent root;
                        try {
                            root = loader.load();
                            serch.getScene().setRoot(root);
                        } catch (IOException ex) {
                            Logger.getLogger(BlogFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                         /****/
                    }
                });
                questions.setBtn_update(btnstatus);
                obs.add(new Questions(
                        questions.getId_question(),
                        questions.getQ_message() ,
                        questions.getBtn_update()
                ));  
           
            
        });
        id_question.setCellValueFactory(new PropertyValueFactory<>("id_question"));
        question.setCellValueFactory(new PropertyValueFactory<>("q_message"));
        btn_update.setCellValueFactory(new PropertyValueFactory<>("btn_update"));
        table_question.setItems(obs);
        table_question.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table_question.getColumns().addAll(id_question, question,btn_update);
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
    private void home(ActionEvent event) throws IOException {
        FXMLLoader  loader = new FXMLLoader(getClass().getResource("../HomeFXML.fxml"));
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
