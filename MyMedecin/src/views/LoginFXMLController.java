/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import servise.LoginService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  

    @FXML
    private void clickLogin(ActionEvent event) throws SQLException, IOException {
        LoginService ls = new LoginService();
        if(!login.getText().isEmpty() && !pass.getText().isEmpty()){
            int user_id = ls.validLogin(login.getText(), pass.getText());
            System.out.println("user_id"+user_id);
            if(user_id!=0){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
                Parent root = loader.load();
                login.getScene().setRoot(root);
            }else{
                System.out.println("non connecte");
            }  
        }
        
    }

    private void Patient() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PatientFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }
    private void Medecin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MedecinesFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }
    private void Pharmacien() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PharmacieFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }
    
    @FXML
    private void clickPatient(ActionEvent event) throws IOException {
        this.Patient();
    }

    @FXML
    private void clickMedecin(ActionEvent event) throws IOException {
        this.Medecin();
    }

    @FXML
    private void clickPharmacien(ActionEvent event) throws IOException {
        this.Pharmacien();
    }

    @FXML
    private void keyPressedPatient(KeyEvent event) throws IOException {
        this.Patient();
    }

    @FXML
    private void keyPressedMedecin(KeyEvent event) throws IOException {
        this.Medecin();
    }

    @FXML
    private void keyPressedPharmacien(KeyEvent event) throws IOException {
        this.Pharmacien();
    }
    
}
