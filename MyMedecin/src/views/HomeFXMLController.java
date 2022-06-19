/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import servise.LoginService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class HomeFXMLController implements Initializable {

    @FXML
    private TableView<?> tab;
    @FXML
    private AnchorPane content;
    @FXML
    private Text infoUserConnected;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        UserService us = new UserService();
        infoUserConnected.setText(us.getById(LoginService.getUserConnected()).toString());
    }    

    @FXML
    private void home(ActionEvent event) {
    }

    @FXML
    private void rechercher(ActionEvent event) {
    }

    @FXML
    private void event(ActionEvent event) {
    }

    @FXML
    private void forum(ActionEvent event) {
    }

    @FXML
    private void rensezvous(ActionEvent event) {
    }

    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
        LoginService.setUserConnected(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        content.getScene().setRoot(root);
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
        content.getScene().setRoot(root);
    }
    
}
