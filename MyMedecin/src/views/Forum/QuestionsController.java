/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Forum;

import entite.Gouvernorat;
import entite.Medecines;
import entite.Questions;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import servise.LoginService;
import servise.MedecinesService;
import servise.PharmacieService;
import servise.QuestionsService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class QuestionsController implements Initializable {

    @FXML
    private Button envoyer;
    @FXML
    private Button annuler;
    private TextArea message;
    @FXML
    private TextArea txtmsg;
    @FXML
    private ComboBox<User> cbmedecin;
    @FXML
    private Button btn_rdv;
    @FXML
    private ImageView img_profile;
    @FXML
    private Text infoUserConnected1;
    @FXML
    private Text infoUserConnected;

    /** Quand cette méthode est appelée, elle va nous 
     * retourner à l'écran d'acceuil
    */
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            UserService us = new UserService();
            ArrayList<User> list=us.getAllMedecines();
            System.out.println("views.QuestionsController.initialize()");
            //System.out.println(list);
            ObservableList<User> obs=FXCollections.observableArrayList(list);
            cbmedecin.setItems(obs);
         
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
   
//    @FXML
//    private void ajouterMsg(ActionEvent event) {
//        
//        
//    }
    
    
    public void homeButtonPushed (ActionEvent event) throws IOException
    {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("../HomeFXML.fxml"));
        Scene homeViewScene = new Scene(homeViewParent);
        
        // cette ligne retourne les informations du Stage

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeViewScene);
        window.show();
    }
    
    /**Quand cette méthode est appelée, elle va envoyer le message à la base de donées
     */
    @FXML
    public void sendButtonPushed (ActionEvent event) throws Exception
    {
        String msg = txtmsg.getText();
        int id_m = cbmedecin.getValue().getId();
        UserService us = new UserService();
        String mail = us.getById(id_m).getLogin();
        String objectmail="";
        String bodymail ="";
        System.out.println(id_m);
        /***/
        
        //code mail
        
        
        /***/
        Questions q = new Questions(msg, LoginService.getUserConnected(),id_m);
        QuestionsService qs = new QuestionsService();
        qs.insert(q);
        
        
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
