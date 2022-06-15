/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Gouvernorat;
import entite.Patient;
import entite.Pharmacie;
import entite.Specialite;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import servise.GouvernoratSpecialiteServise;
import servise.PatientService;
import servise.PharmacieService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class PharmacieFXMLController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField login;
    @FXML
    private TextField phone;
    @FXML
    private TextField adresse_cabinet;
    @FXML
    private TextField phone_fixe_cabinet;
    @FXML
    private TextField phone_fixe2_cabinet;
    @FXML
    private ComboBox<Gouvernorat> gouvernorat;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField name_pharmacie;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        GouvernoratSpecialiteServise gss = new GouvernoratSpecialiteServise();
        ArrayList<Gouvernorat> list=gss.getAllGouvernorat();
        ObservableList<Gouvernorat> obs=FXCollections.observableArrayList(list);
        gouvernorat.setItems(obs);
    }    

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        PharmacieService ps = new PharmacieService();
        UserService us = new UserService();
        User u = new User(nom.getText(), prenom.getText(), login.getText(), pass.getText(), phone.getText(), adresse.getText(), "-", 3);
        Pharmacie p =new Pharmacie(us.preInsert(u),adresse_cabinet.getText(),phone_fixe_cabinet.getText(),phone_fixe2_cabinet.getText(),name_pharmacie.getText(),"-",1);      
        ps.insert(p);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root); 
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }
    
}
