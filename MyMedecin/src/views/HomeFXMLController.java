/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javax.imageio.ImageIO;
import servise.LoginService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class HomeFXMLController implements Initializable {

    @FXML
    private Text infoUserConnected;
    @FXML
    private ImageView img_profile;
    @FXML
    private Text infoUserConnected1;
    private WebView webv;
    private WebEngine engine;
    @FXML
    private Button btn_rdv;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initInfoUserConnected();
        /*engine = webv.getEngine();
        engine.load("https://google.tn");
        webv.setVisible(false);*/
        UserService us = new UserService();
        int role = us.getById(LoginService.getUserConnected()).getRole();
        if (role == 1) {
            btn_rdv.setText("Prendre rendez-vous");
        } else if (role == 2) {
            btn_rdv.setText("Calendrier");
        } else if (role == 3) {
            btn_rdv.setText("Pharmacie");;
        }
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
        File file = new File("C:\\\\uploads\\"+u.getPhoto_profil());
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            img_profile.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Event/participantEventFXML.fxml"));
        Parent root = loader.load();
        img_profile.getScene().setRoot(root);
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void rensezvous(ActionEvent event) throws IOException {
        UserService us = new UserService();
        int role = us.getById(LoginService.getUserConnected()).getRole();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Rendezvous/RendezvousFXML.fxml"));
        if (role == 2) {
            loader = new FXMLLoader(getClass().getResource("Rendezvous/CalanderFXML.fxml"));
        } else if (role == 3) {
            loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
        }
        Parent root = loader.load();
        img_profile.getScene().setRoot(root);
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
        LoginService.setUserConnected(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        infoUserConnected.getScene().setRoot(root);
    }

    @FXML
    private void updateUser(ActionEvent event) throws IOException {
        
        UserService us = new UserService();
        int role =us.getById(LoginService.getUserConnected()).getRole();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("profiPatient.fxml"));
        if(role==2){
            loader = new FXMLLoader(getClass().getResource("ProfilMedecin.fxml"));
        }else if(role==3){
            loader = new FXMLLoader(getClass().getResource("ProfilPharmacie.fxml"));
        }
        Parent root = loader.load();
        infoUserConnected.getScene().setRoot(root);
        
    }
    
}
