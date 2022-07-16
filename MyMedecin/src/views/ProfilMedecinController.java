/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import entite.Gouvernorat;
import entite.Medecines;
import entite.Specialite;
import entite.User;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import servise.GouvernoratSpecialiteServise;
import servise.LoginService;
import servise.MedecinesService;
import servise.UserService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class ProfilMedecinController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField adresse;
    @FXML
    private TextField phone;
    @FXML
    private TextField login;
    @FXML
    private PasswordField pass;
    @FXML
    private TextField adresse_cabinet;
    @FXML
    private TextField phone_fixe_cabinet;
    @FXML
    private TextField phone_fixe2_cabinet;
    @FXML
    private ComboBox<Gouvernorat> gouvernorat;
    @FXML
    private ComboBox<Specialite> specialite;
    @FXML
    private Text valideupdate;
    @FXML
    private ImageView img_profile;
    @FXML
    private Text infoUserConnected1;
    @FXML
    private Text infoUserConnected;
    @FXML
    private ImageView img;
    
    byte[] person_image = null;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initComboBox("g");initComboBox("s");
        UserService us = new UserService();
        MedecinesService ms = new MedecinesService();
        User u = us.getById(LoginService.getUserConnected());
        Medecines m = ms.getByUser(u.getId());
        nom.setText(u.getNom());
        prenom.setText(u.getPrenom());
        adresse.setText(u.getAdresse());
        phone.setText(u.getPhone());
        login.setText(u.getLogin());
        adresse_cabinet.setText(m.getAdresse_cabinet());
        phone_fixe_cabinet.setText(m.getPhone_fixe_cabinet());
        phone_fixe2_cabinet.setText(m.getPhone_fixe2_cabinet());
        Gouvernorat g = new Gouvernorat(m.getId_gouvernorat(), "");
        gouvernorat.setValue(g);
        Specialite s = new Specialite(m.getId_specialite(), "");
        specialite.setValue(s);
        File file = new File("C:\\\\uploads\\"+u.getPhoto_profil());
        BufferedImage bufferedImage;
        try {
            bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            img.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(ProfiPatientController.class.getName()).log(Level.SEVERE, null, ex);
        }
        configPhone(phone);
        configPhone(phone_fixe_cabinet);
        configPhone(phone_fixe2_cabinet);
        initInfoUserConnected();
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

    private void clickHome(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
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
    }

    @FXML
    private void update(ActionEvent event) throws FileNotFoundException, IOException {
        UserService us = new UserService();
        User u = us.getById(LoginService.getUserConnected());
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setAdresse(adresse.getText());
        u.setPhone(phone.getText());
        u.setLogin(login.getText());
        u.setPhoto_profil(null);
        System.out.println(person_image); 
        if(person_image!= null){
            String fileLocation = new File("c:\\uploads").getAbsolutePath() + "\\Medecine-"+u.getId()+".jpg" ;
            FileOutputStream output = new FileOutputStream(fileLocation);
            output.write(person_image);
            output.close();
            u.setPhoto_profil("Medecine-"+u.getId()+".jpg");
        }
        if(pass.getText()!=""){
            u.setPassword(pass.getText());
        }
        us.update(u);
        MedecinesService ps = new MedecinesService();
        Medecines p = ps.getByUser(LoginService.getUserConnected());
        p.setAdresse_cabinet(adresse_cabinet.getText());
        p.setId_gouvernorat(gouvernorat.getValue().getId());
        p.setId_specialite(specialite.getValue().getId());
        p.setPhone_fixe2_cabinet(phone_fixe2_cabinet.getText());
        p.setPhone_fixe_cabinet(phone_fixe_cabinet.getText());
        
        
        ps.update(p);
        valideupdate.setText("Votre profil est mis à jour avec succès");
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                valideupdate.setText("");
            }
        }, 5000L);
    }

    @FXML
    private void updateUser(ActionEvent event) {
    }

    @FXML
    private void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HomeFXML.fxml"));
        Parent root = loader.load();
        login.getScene().setRoot(root);
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
        infoUserConnected.getScene().setRoot(root);
    }

    @FXML
    private void UploadImageActionPerformed(ActionEvent event) {

        FileChooser fileChooser = new FileChooser();

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG
                = new FileChooser.ExtensionFilter("JPG files (*.JPG)", "*.JPG");
        FileChooser.ExtensionFilter extFilterjpg
                = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG
                = new FileChooser.ExtensionFilter("PNG files (*.PNG)", "*.PNG");
        FileChooser.ExtensionFilter extFilterpng
                = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
        fileChooser.getExtensionFilters()
                .addAll(extFilterJPG, extFilterjpg, extFilterPNG, extFilterpng);
        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            img.setImage(image);
            //img.setFitWidth(200);
            //img.setFitHeight(200);
            img.scaleXProperty();
            img.scaleYProperty();
            img.setSmooth(true);
            img.setCache(true);
            
            FileInputStream fin = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];

            for (int readNum; (readNum = fin.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
            File outputfile = new File("image.jpg");
            ImageIO.write(bufferedImage, "jpg", outputfile);
            person_image = bos.toByteArray();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    
}
