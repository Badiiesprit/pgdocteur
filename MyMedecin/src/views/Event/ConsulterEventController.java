/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Event;

import entite.Events;
import entite.Gouvernorat;
import entite.InscriptionEvents;
import entite.Specialite;
import entite.User;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javax.imageio.ImageIO;
import servise.EventsService;
import servise.GouvernoratSpecialiteServise;
import servise.InscriptionEventsServise;
import servise.LoginService;
import servise.UserService;
import views.HomeFXMLController;

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
    
    private Specialite spec;
    
    private Gouvernorat gouv;
    @FXML
    private Button btn_rdv;
    @FXML
    private ImageView img_profile;
    @FXML
    private Text infoUserConnected1;
    @FXML
    private Text infoUserConnected;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     
        EventsService es = new EventsService();
        Events e = es.getById(ConsulterEventController.getIdevent());
        name.setText(e.getName());
        desc.setText(e.getDescription());
        participant.setText(String.valueOf(e.getNb_participant()));
        GouvernoratSpecialiteServise gss = new GouvernoratSpecialiteServise();
        ArrayList<Gouvernorat> list_g=gss.getAllGouvernorat();
        ArrayList<Specialite> list_s=gss.getAllSpecialite();

        list_g.forEach(gouvernorat->{
            if(gouvernorat.getId()==e.getId_gouvernorat()){
                gouv = gouvernorat;
            }
        });
        
        list_s.forEach(specialite->{
             if(specialite.getId()==e.getId_specialite()){
                spec = specialite;
            }
        });
        specialite.setText(spec.getName());
        gouvernerat.setText(gouv.getName());
        date_d.setText(e.getDate_deb().toString());
        date_f.setText(e.getDate_fin().toString());
        this.initInfoUserConnected();
    }  
    public void  initInfoUserConnected(){
        UserService us = new UserService();
        User u = us.getById(LoginService.getUserConnected());
        if(u.getRole()==2){
            infoUserConnected1.setText("Medecine");
        }else if(u.getRole()==3){
            infoUserConnected1.setText("Pharmacien");
        }else{
            infoUserConnected1.setText("Patient");
        }
        //img_profile.set
        infoUserConnected.setText(u.toString());
        File file = new File("C:\\\\uploads\\"+u.getPhoto_profil());
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            img_profile.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(HomeFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @FXML
    private void participer(ActionEvent event) throws IOException {
        System.out.println("views.Event.ConsulterEventController.initialize()");
        System.out.println(ConsulterEventController.getIdevent());
        InscriptionEventsServise is = new InscriptionEventsServise();
        InscriptionEvents t = new InscriptionEvents(ConsulterEventController.getIdevent(), LoginService.getUserConnected());
        if(is.insert(t)){
            EventsService es = new EventsService();
            Events e = es.getById(ConsulterEventController.getIdevent());
            es.setNbParticipant(ConsulterEventController.getIdevent(),e.getNb_participant()-1);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("participantEventFXML.fxml"));
            Parent root = loader.load();
            name.getScene().setRoot(root);
        }else{
             javax.swing.JOptionPane.showMessageDialog(null,"Fin Event");
        }
    }

    @FXML
    private void updateUser(ActionEvent event) {
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
    private void deconnecter(ActionEvent event) {
    }
    
}
