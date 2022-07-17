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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
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
    @FXML
    private Label error;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        login.setText("patient@gmail.com");
        pass.setText("123456");
    }  

    @FXML
    private void clickLogin(ActionEvent event) throws SQLException, IOException {
        
        LoginService ls = new LoginService();
        System.out.println(" test 1");
        //error.setText("");
        System.out.println(" test 2");
        if(!login.getText().isEmpty() && !pass.getText().isEmpty()){
            System.out.println(" test 3");
            int user_id = ls.validLogin(login.getText(), pass.getText());
            
            System.out.println("user_id"+user_id);
            if(user_id>0){
                System.out.println(" loGIN AdminFXML");
                user_id = ls.validLoginAdmin(login.getText(), pass.getText());
                if(user_id!=0){
                    System.out.println("AdminFXML");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminFXML.fxml"));
                    Parent root = loader.load();
                    login.getScene().setRoot(root);
                }else{
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
                    Parent root = loader.load();
                    login.getScene().setRoot(root);
                    
                }
                
            }else{
                System.out.println("LoginFXMLController : "+user_id);
                if(user_id == -1){
                    error.setText("Votre compte est désactivé.");
                }else{
                    error.setText("Mot de passe ou email non valide.");
                }    
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
    private void keyPressedMedecin(MouseEvent event) {
        
    }

    @FXML
    private void keyPressedPharmacien(MouseEvent event) {
    }

    @FXML
    private void keyPressedPatient(MouseEvent event) {
    }

    @FXML
    private void forgot_password(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forgotpasswordFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
    }
    
}
