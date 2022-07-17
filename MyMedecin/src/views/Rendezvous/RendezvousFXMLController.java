/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Rendezvous;

import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import entite.Creneau;
import entite.Rendezvous;
import entite.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import servise.GouvernoratSpecialiteServise;
import servise.LoginService;
import servise.MedecinesService;
import servise.Rvservice;
import servise.UserService;
import static sun.security.jgss.GSSUtil.login;
import views.HomeFXMLController;
//import servise.MedecinesService;

/**
 * FXML Controller class
 *
 * @author achemsi
 */
public class RendezvousFXMLController implements Initializable {

    @FXML
    private ComboBox<User> Medecin;
    @FXML
    private TextField nameP;
    @FXML
    private DatePicker DateRv;
    @FXML
    private ComboBox<Creneau> cre;
    @FXML
    private TextArea Rque;
    @FXML
    private Text infoUserConnected1;
    @FXML
    private Label etoile1;
    @FXML
    private Label etoile2;
    @FXML
    private Label etoile3;
    @FXML
    private Label etoile4;
    @FXML
    private Text infoUserConnected;
    @FXML
    private Button btn_rdv;
    @FXML
    private ImageView img_profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService us = new UserService();
        
        nameP.setText(us.getById(LoginService.getUserConnected()).toString());
        System.out.println("Bonjour MR : " + LoginService.getUserConnected());
        //System.out.println(us.LoginService.getUserConnected(0));
        initComboBox("M");/*initComboBox("C");*/
        
        DateRv.setDayCellFactory(piker -> new DateCell(){
            public void updateItem(LocalDate dp,boolean empty){
                super.updateItem(dp, empty);
                LocalDate today = LocalDate.now();
                setDisable(empty || dp.compareTo(today)<0);
            }
        });
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
    @FXML
    private void home(ActionEvent event) throws IOException {
       
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../HomeFXML.fxml"));
        Parent root = loader.load();
        Medecin.getScene().setRoot(root);
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
    private void fixer(ActionEvent event) {
          if ((Medecin.getValue() == null)||(cre.getValue() == null)||(nameP.getText()==null)||(DateRv.getValue()==null)){
            javax.swing.JOptionPane.showMessageDialog(null,"veuillez completer les informations");
            etoile1.setVisible(true);etoile2.setVisible(true);etoile3.setVisible(true);etoile4.setVisible(true);
            
            }
          else{
       
        Date date = Date.valueOf(DateRv.getValue());
        DatePicker dp = new DatePicker();
        
        Rendezvous rv = new Rendezvous(cre.getValue().getId(), Medecin.getValue().getId(),LoginService.getUserConnected(), date, nameP.getText(), Rque.getText());
        Rvservice rvs = new Rvservice();
        rvs.insert(rv);
        Medecin.setValue(null);
        nameP.setText("");
        Rque.setText("");
        DateRv.setValue(null);
        cre.setValue(null);
        etoile1.setVisible(false);
        etoile2.setVisible(false);
        etoile3.setVisible(false);
        etoile4.setVisible(false);
        
        }
        
    }

    @FXML
    private void annuler(ActionEvent event) throws IOException {
      
        Medecin.setValue(null);
        nameP.setText("");
        Rque.setText("");
        DateRv.setValue(null);
        cre.setValue(null);
        
    }
    @FXML
    private void get_med (ActionEvent event) throws IOException {
         if(DateRv.getValue()!=null && Medecin.getValue()!=null){
            GouvernoratSpecialiteServise getc = new GouvernoratSpecialiteServise();
            ArrayList<Creneau> list = getc.getCreneaubyId(Medecin.getValue().getId(),DateRv.getValue().toString());
            ObservableList<Creneau> obs = FXCollections.observableArrayList(list);
            cre.setItems(obs);
        }
    }
    
    @FXML
    private void get_date (ActionEvent event) throws IOException {
        
        if(DateRv.getValue()!=null && Medecin.getValue()!=null){
            GouvernoratSpecialiteServise getc = new GouvernoratSpecialiteServise();
            ArrayList<Creneau> list = getc.getCreneaubyId(Medecin.getValue().getId(),DateRv.getValue().toString());
            ObservableList<Creneau> obs = FXCollections.observableArrayList(list);
            cre.setItems(obs);
        }
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
        LoginService.setUserConnected(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../LoginFXML.fxml"));
        Parent root = loader.load();
        Medecin.getScene().setRoot(root);
    }

    private void initComboBox(String type) {
        GouvernoratSpecialiteServise med = new GouvernoratSpecialiteServise();
        UserService us = new UserService();
        if (type == "M") {
            System.out.println("Test m ");
            ArrayList<User> list = us.getAllMedecines();
            System.out.println("Test 1 ");
            ObservableList<User> obs = FXCollections.observableArrayList(list);
            System.out.println("Test 1 ");
            Medecin.setItems(obs);
        }
        if (type == "C") {
            System.out.println("Test c ");
            ArrayList<Creneau> list = med.getAllcreneau();
            System.out.println("Test 1 ");
            ObservableList<Creneau> obs = FXCollections.observableArrayList(list);
            System.out.println("Test 2 ");
            cre.setItems(obs);
        }
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

}
