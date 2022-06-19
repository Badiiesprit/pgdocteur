/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Gouvernorat;
import entite.Medecines;
import entite.Specialite;
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
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import servise.GouvernoratSpecialiteServise;
import servise.LoginService;
import servise.MedecinesService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class ProfilMedecinController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField phone;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField adresse_cabinet;
    @FXML
    private TextField phone_fixe_cabinet;
    @FXML
    private TextField phone_fixe2_cabinet;
    @FXML
    private ComboBox<Gouvernorat> gouvernorat;
    @FXML
    private ComboBox<Specialite> specialite;
    @FXML
    private Text valideupdate;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox("g");initComboBox("s");
        UserService us = new UserService();
        MedecinesService ms = new MedecinesService();
        User u = us.getById(LoginService.getUserConnected());
        Medecines m = ms.getByUser(u.getId());
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        adresse.setText(u.getAdresse());
        phone.setText(u.getPhone());
        login.setText(u.getLogin());
        adresse_cabinet.setText(m.getAdresse_cabinet());
        phone_fixe_cabinet.setText(m.getPhone_fixe_cabinet());
        phone_fixe2_cabinet.setText(m.getPhone_fixe2_cabinet());
        Gouvernorat g = new Gouvernorat(m.getId_gouvernorat(), "");
        gouvernorat.setValue(g);
        Specialite s = new Specialite(m.getId_specialite(), "");
        specialite.setValue(s);
        
    }    

    @FXML
    private void clickHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }
    public void initComboBox(String type){
        GouvernoratSpecialiteServise gss = new GouvernoratSpecialiteServise();
        if(type=="g"){
            ArrayList<Gouvernorat> list=gss.getAllGouvernorat();
            ObservableList<Gouvernorat> obs=FXCollections.observableArrayList(list);
            gouvernorat.setItems(obs);
        }
        if(type=="s"){
            ArrayList<Specialite> list=gss.getAllSpecialite();
            ObservableList<Specialite> obs=FXCollections.observableArrayList(list);
            specialite.setItems(obs);
        }
    }

    @FXML
    private void update(ActionEvent event) {
        UserService us = new UserService();
        User u = us.getById(LoginService.getUserConnected());
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setAdresse(adresse.getText());
        u.setPhone(phone.getText());
        u.setLogin(login.getText());
        if(pass.getText()!=""){
            u.setPassword(pass.getText());
        }
        us.update(u);
        MedecinesService ps = new MedecinesService();
        Medecines p = ps.getByUser(LoginService.getUserConnected());
        p.setAdresse_cabinet(adresse_cabinet.getText());
        p.setId_gouvernorat(gouvernorat.getValue().getId());
        p.setId_specialite(specialite.getValue().getId());
        p.setPhone_fixe2_cabinet(phone_fixe2_cabinet.getText());
        p.setPhone_fixe_cabinet(phone_fixe_cabinet.getText());
        ps.update(p);
        valideupdate.setText("Votre profil est mis à jour avec succès");
    }
    
}
