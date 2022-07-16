/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TextField;
import servise.SendEmail;
import servise.UserService;
import utils.ControleSaisie;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class ForgotpasswordFXMLController implements Initializable {

    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void sundmail(ActionEvent event) throws SQLException, IOException {
        ControleSaisie cs = new ControleSaisie();
        if(!cs.valideLogin(email.getText())){
            javax.swing.JOptionPane.showMessageDialog(null,"le login est obligatoire 'aaaa@bbbb.ccc'");
            return;
        }
        UserService us = new UserService();
        int id=us.getByEmail(email.getText()).getId();
        System.out.println("ID:"+id);
        if(id>0){
            int random_int = (int)Math.floor(Math.random()*(9999-1000)+1000);
            String generatedCode = String.valueOf(random_int);
            generatedCode += id;
            System.out.println("Code:"+generatedCode);
            SendEmail.Send(email.getText(),"MY DOCTOR ","MY DOCTOR CODE : "+generatedCode);
            us.setCodeValidationUser(generatedCode, id);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("newpasswordFXML.fxml"));
            Parent root = loader.load();
            email.getScene().setRoot(root);
        }else{
             javax.swing.JOptionPane.showMessageDialog(null,"Erroror");
        }
    }
 
    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        email.getScene().setRoot(root);
    }   
}
