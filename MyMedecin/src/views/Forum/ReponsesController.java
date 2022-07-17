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
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import servise.LoginService;
import servise.QuestionsService;
import servise.ReponsesService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ReponsesController implements Initializable {

    @FXML
    private Label txtQst;
    @FXML
    private TextArea txtRsp;
    @FXML
    private Button envoyer;
    @FXML
    private Button effacer;
    
    
    private static int id_question;
    @FXML
    private AnchorPane Reponses;
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
        
        QuestionsService srv = new QuestionsService();
        Questions qs = srv.getById(ReponsesController.getId_question());
        txtQst.setText(qs.getQ_message());
         UserService us = new UserService();
        int role = us.getById(LoginService.getUserConnected()).getRole();
        if (role == 1) {
            btn_rdv.setText("Prendre rendez-vous");
        } else if (role == 2) {
            btn_rdv.setText("Calendrier");
        } else if (role == 3) {
            btn_rdv.setText("Pharmacie");;
        }
        // TODO
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
    
    @FXML
    public void sendButtonPushed (ActionEvent event) throws Exception
    {
        String msg = txtRsp.getText();
        Reponses r = new Reponses(msg,LoginService.getUserConnected(),ReponsesController.getId_question());
        ReponsesService rsp = new ReponsesService();
        rsp.insert(r);
        
        
    }

    public static int getId_question() {
        return id_question;
    }

    public static void setId_question(int id_question) {
        ReponsesController.id_question = id_question;
    }
    @FXML
    public void clearButtonPushed (ActionEvent event) throws Exception
    {
        txtRsp.clear();
        
    }
    public void AnnulerButtonPushed (ActionEvent event) throws IOException
    {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("BlogFXML.fxml"));
        Scene homeViewScene = new Scene(homeViewParent);
        
        // cette ligne retourne les informations du Stage

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(homeViewScene);
        window.show();
    }
    public void homeButtonPushed (ActionEvent event) throws IOException
    {
        Parent homeViewParent = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
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
