/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.Rendezvous;

import dataTableView.Calendrier;
import entite.Rendezvous;
//import entite.User;
//import java.awt.Color;
//import java.awt.Insets;
import java.io.IOException;
import java.net.URL;
//import java.sql.Date;
//import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//import java.util.Calendar;
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
//import javafx.scene.control.Button;
//import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
//import javafx.scene.layout.Border;
//import javafx.scene.layout.BorderStroke;
//import javafx.scene.layout.BorderStrokeStyle;
//import javafx.scene.layout.BorderWidths;
//import javafx.scene.layout.CornerRadii;
import servise.GouvernoratSpecialiteServise;
//import servise.LoginService;
//import servise.UserService;
import servise.Rvservice;

/**
 * FXML Controller class
 *
 * @author achemsi
 */
public class CalanderFXMLController implements Initializable {

    @FXML
    private TableView<Calendrier> calendrier;
    @FXML
    private TableColumn<Calendrier, String> col_date;
    @FXML
    private TableColumn<Calendrier, String> col_cre;
    @FXML
    private TableColumn<Calendrier, String> col_name;
   
    @FXML
    private TableColumn<Calendrier, String> col_rques;
    private String nameCr="1";
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
        Rvservice rv = new Rvservice();
        ArrayList<Rendezvous> list=rv.getAllbymedecin();
        System.out.println(list);
        this.initTable(list);
        
    }

    private void initTable(ArrayList<Rendezvous> list) {

         ObservableList<Calendrier> lrvs=FXCollections.observableArrayList();
         list.forEach(Rendezvous ->{
             
           /*  public void handle(ActionEvent event) {
                        Rvservice us = new Rvservice();
                        //us.delete(Rendezvous);
                        ArrayList<Rendezvous> list=us.getAll();
                        calendrier.getColumns().clear();
                        initTable(list);
                        calendrier.refresh();
                    }*/
                
             GouvernoratSpecialiteServise cr=new GouvernoratSpecialiteServise();
             SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
             String stringDate= DateFor.format(Rendezvous.getDate_plan());
             
             cr.getAllcreneau().forEach(creneau ->{
                 if (creneau.getId()==Rendezvous.getId_cre()){
                     nameCr=creneau.getCreneau();
                 }
             });
              
             
                lrvs.add(new Calendrier(
                        stringDate,
                        nameCr,
                        Rendezvous.getName(),
                        Rendezvous.getCommentaire()
                ));  
         
           
        });
             
            
         
        
        
        
        col_date.setCellValueFactory(new PropertyValueFactory<>("date_plan"));
        col_cre.setCellValueFactory(new PropertyValueFactory<>("cre"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("patient"));
        col_rques.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
        
        
        calendrier.setItems(lrvs);
        //calendrier.setBorder(new 0,10,0,10);
        
        
        //calendrier.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
     
       
       // calendrier.getColumns().addAll(col_date, col_cre, col_name, col_rques);
    }
    @FXML
    private void home(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../HomeFXML.fxml"));
        Parent root = loader.load();
        calendrier.getScene().setRoot(root);
    }

    @FXML
    private void refreshTable(ActionEvent event) {
        Rvservice Rv=new Rvservice();
        ArrayList<Rendezvous> list=Rv.getAllbymedecin();
        //calendrier.getColumns().clear();
        initTable(list);
        calendrier.refresh();
        
        
    }

    @FXML
    private void updateUser(ActionEvent event) {
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
