/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Gouvernorat;
import entite.Pharmacie;
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
import servise.PharmacieService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class ProfilPharmacieController implements Initializable {

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
    private TextField name_pharmacie;
    @FXML
    private TextField adresse_cabinet;
    @FXML
    private TextField phone_fixe_cabinet;
    @FXML
    private TextField phone_fixe2_cabinet;
    @FXML
    private ComboBox<Gouvernorat> gouvernorat;
    @FXML
    private Text valideupdate;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        GouvernoratSpecialiteServise gss = new GouvernoratSpecialiteServise();
        ArrayList<Gouvernorat> list=gss.getAllGouvernorat();
        ObservableList<Gouvernorat> obs=FXCollections.observableArrayList(list);
        gouvernorat.setItems(obs);
        UserService us = new UserService();
        PharmacieService ps = new PharmacieService();
        User u = us.getById(LoginService.getUserConnected());
        Pharmacie p = ps.getByUser(u.getId());
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        adresse.setText(u.getAdresse());
        phone.setText(u.getPhone());
        login.setText(u.getLogin());
        name_pharmacie.setText(p.getName_pharmacie());
        adresse_cabinet.setText(p.getAdresse_pharmacie());
        phone_fixe_cabinet.setText(p.getPhone_fixe_pharmacie());
        phone_fixe2_cabinet.setText(p.getPhone_fixe2_pharmacie());
        Gouvernorat g = new Gouvernorat(p.getId_gouvernorat(), "");
        gouvernorat.setValue(g);
    }    

    @FXML
    private void Home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }

    @FXML
    private void Update(ActionEvent event) {
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
        PharmacieService ps = new PharmacieService();
        Pharmacie p = ps.getByUser(LoginService.getUserConnected());
        p.setAdresse_pharmacie(adresse_cabinet.getText());
        p.setId_gouvernorat(gouvernorat.getValue().getId());
        p.setName_pharmacie(name_pharmacie.getText());
        p.setPhone_fixe2_pharmacie(phone_fixe2_cabinet.getText());
        p.setPhone_fixe_pharmacie(phone_fixe_cabinet.getText());
        System.out.println(p);
        ps.update(p);
        valideupdate.setText("Votre profil est mis à jour avec succès");
    }
    
}
