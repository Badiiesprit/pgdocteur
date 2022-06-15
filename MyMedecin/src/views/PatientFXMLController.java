/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Patient;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import servise.PatientService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class PatientFXMLController implements Initializable {

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
    private TextField pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        PatientService ps = new PatientService();
        UserService us = new UserService();
        User u = new User(nom.getText(), prenom.getText(), login.getText(), pass.getText(), phone.getText(), adresse.getText(), "-", 1);
        int user_id= us.preInsert(u);
        if(user_id!=-1){
            Patient p =new Patient(us.preInsert(u)); 
            ps.insert(p); 
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
            Parent root = loader.load();
            login.getScene().setRoot(root);
        }else{
            System.out.println("L'utilisateur '"+login.getText()+"' existe déjà");
        }
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }
    
}
