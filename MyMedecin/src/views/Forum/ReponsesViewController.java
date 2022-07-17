/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Forum;

import entite.Questions;
import entite.Reponses;
import entite.User;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import servise.LoginService;
import servise.ReponsesService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ReponsesViewController implements Initializable {

    
    @FXML
    private TableView<Reponses> table_reponse;
    @FXML
    private TableColumn<Reponses, Integer> id_reponse;
    @FXML
    private TableColumn<Reponses, String> m_reponse;
    @FXML
    private Button btn_rdv;
    @FXML
    private ImageView img_profile;
    @FXML
    private Text infoUserConnected1;
    @FXML
    private Text infoUserConnected;
    @FXML
    private Text text_q;
   
 

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

           ReponsesService rep= new ReponsesService();
           initTable(rep.getAll());                    
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
    private void initTable(ArrayList<Reponses> list){
        
        
        ObservableList<Reponses> obs=FXCollections.observableArrayList();
        
        list.forEach(reponses ->{
               
                obs.add(new Reponses(
                        reponses.getId_reponse(),
                        reponses.getR_message()
                        
                ));  
           
            
        });
        id_reponse.setCellValueFactory(new PropertyValueFactory<>("id_reponse"));
        m_reponse.setCellValueFactory(new PropertyValueFactory<>("r_message"));
       
        table_reponse.setItems(obs);
        table_reponse.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table_reponse.getColumns().addAll(id_reponse, m_reponse);
    }
    @FXML
    public void AnnulerButtonPushed (ActionEvent event) throws IOException
    {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("ReponseHome.fxml"));
        Scene homeViewScene = new Scene(homeViewParent);
        
        // cette ligne retourne les informations du Stage

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeViewScene);
        window.show();
    }
    @FXML
     public void homeButtonPushed (ActionEvent event) throws IOException
    {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("../HomeFXML.fxml"));
        Scene homeViewScene = new Scene(homeViewParent);
        
        // cette ligne retourne les informations du Stage

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeViewScene);
        window.show();
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
