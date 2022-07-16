/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Event;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class ConsulterEventController implements Initializable {

    
    private static int idevent ;

    public static int getIdevent() {
        return idevent;
    }

    public static void setIdevent(int idevent) {
        ConsulterEventController.idevent = idevent;
    }
    @FXML
    private Label gouvernerat;
    @FXML
    private Label type;
    @FXML
    private Label name;
    @FXML
    private Label desc;
    @FXML
    private Label date_d;
    @FXML
    private Label date_f;
    @FXML
    private Label participant;
    @FXML
    private Label specialite;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("views.Event.ConsulterEventController.initialize()");
        System.out.println(ConsulterEventController.getIdevent());
    }    

    @FXML
    private void participer(ActionEvent event) throws IOException {
        System.out.println("views.Event.ConsulterEventController.initialize()");
        System.out.println(ConsulterEventController.getIdevent());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("participantEventFXML.fxml"));
        Parent root = loader.load();
        name.getScene().setRoot(root);
    }
    
}
