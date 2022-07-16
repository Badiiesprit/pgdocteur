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
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import servise.PatientService;
import servise.UserService;
import utils.ControleSaisie;

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
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        phone.textProperty().addListener(new ChangeListener<String>(){
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    phone.setText(newValue.replaceAll("[^\\d]", ""));
                } 
                if (phone.getText().length() > 8) {
                    String s = phone.getText().substring(0, 8);
                    phone.setText(s);
                }
            }
            
        });
    }    

    @FXML
    private void inscription(ActionEvent event) throws IOException {
        ControleSaisie cs = new ControleSaisie();
        if(!cs.valideText(nom.getText(), 4)){
            javax.swing.JOptionPane.showMessageDialog(null,"le nom est obligatoire avec une longueur minimale de 4");
            
            return;
        }
        if(!cs.valideText(prenom.getText(), 4)){
            javax.swing.JOptionPane.showMessageDialog(null,"le prenom est obligatoire avec une longueur minimale de 4");
            return;
        }
        if(!cs.valideText(adresse.getText(), 10)){
            javax.swing.JOptionPane.showMessageDialog(null,"le adresse est obligatoire avec une longueur minimale de 10");
            return;
        }
        if(!cs.validePhone(phone.getText())){
            javax.swing.JOptionPane.showMessageDialog(null,"le phone est obligatoire avec une longueur 8");
            return;
        }
         if(!cs.valideLogin(login.getText())){
            javax.swing.JOptionPane.showMessageDialog(null,"le login est obligatoire 'aaaa@bbbb.ccc'");
            return;
        }
        if(!cs.validePassword(pass.getText())){
            javax.swing.JOptionPane.showMessageDialog(null,"le Password est obligatoire avec une longueur minimale de 4");
            return;
        }
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
            javax.swing.JOptionPane.showMessageDialog(null,"L'utilisateur '"+login.getText()+"' existe déjà");
        }
    }

    @FXML
    private void connexion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }
    
}
