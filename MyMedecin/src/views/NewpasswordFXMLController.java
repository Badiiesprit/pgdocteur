/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class NewpasswordFXMLController implements Initializable {

    @FXML
    private TextField pass;
    @FXML
    private TextField confirmpass;
    @FXML
    private TextField code;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void updatepass(ActionEvent event) throws IOException {
        
        UserService us = new UserService();
        User u=us.getByCode(code.getText());
       
        if(u.getId()>0){
            System.out.println("views.NewpasswordFXMLController.updatepass()");
            System.out.println(pass.getText().compareTo(confirmpass.getText()));
            System.out.println(pass.getText());
            System.out.println(confirmpass.getText());
            if(pass.getText().compareTo(confirmpass.getText())==0){
                System.out.println("views.NewpasswordFXMLController.updatepass()");
                u.setPassword(pass.getText());
                us.update(u);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
                Parent root = loader.load();
                code.getScene().setRoot(root);
            }
        }
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("forgotpasswordFXML.fxml"));
        Parent root = loader.load();
        code.getScene().setRoot(root);
    }
    
}
