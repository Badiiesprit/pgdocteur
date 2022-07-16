/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Gouvernorat;
import entite.Medecines;
import entite.Patient;
import entite.Specialite;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import servise.GouvernoratSpecialiteServise;
import servise.MedecinesService;
import servise.PatientService;
import servise.UserService;
import utils.ControleSaisie;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class MedecinesFXMLController implements Initializable {

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
    private TextField adresse_cabinet;
    @FXML
    private TextField phone_fixe_cabinet;
    @FXML
    private ComboBox<Specialite> specialite;
    @FXML
    private ComboBox<Gouvernorat> gouvernorat;
    @FXML
    private TextField phone_fixe2_cabinet;
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox("g");initComboBox("s");
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
        configPhone(phone);
        configPhone(phone_fixe_cabinet);
        configPhone(phone_fixe2_cabinet);
    }
    private void configPhone(TextField phone){
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
        if(!cs.valideText(adresse_cabinet.getText(), 10)){
            javax.swing.JOptionPane.showMessageDialog(null,"le adresse cabinet est obligatoire avec une longueur minimale de 10");
            return;
        }
        if(!cs.validePhone(phone_fixe_cabinet.getText())){
            javax.swing.JOptionPane.showMessageDialog(null,"le fixe est obligatoire avec une longueur 8");
            return;
        }
        if(specialite.getValue() == null){
            javax.swing.JOptionPane.showMessageDialog(null,"la specialite est obligatoire");
            return;
        }
        if(gouvernorat.getValue() == null){
            javax.swing.JOptionPane.showMessageDialog(null,"la gouvernorat est obligatoire");
            return;
        }
        error.setText("");
        MedecinesService ms = new MedecinesService();
        
        UserService us = new UserService();
        User u = new User(nom.getText(), prenom.getText(), login.getText(), pass.getText(), phone.getText(), adresse.getText(), "-", 2);
        int user_id = us.preInsert(u);
        if(user_id!=-1){
            Medecines m =new Medecines(user_id, adresse_cabinet.getText(), phone_fixe_cabinet.getText(), phone_fixe2_cabinet.getText(), specialite.getValue().getId(), gouvernorat.getValue().getId(), "-");
            ms.insert(m);
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
