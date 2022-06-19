/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dataTableView.DataUser;
import entite.Specialite;
import entite.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import servise.UserService;
import javafx.scene.control.Button;
import servise.LoginService;

/**
 * FXML Controller class
 *
 * @author badi9
 */
public class AdminFXMLController implements Initializable {

    @FXML
    private Text titel_table;
    @FXML
    private TableView<DataUser> tab_user;
    @FXML
    private TableColumn<DataUser, Integer> col_id;
    @FXML
    private TableColumn<DataUser, String> col_name;
    @FXML
    private TableColumn<DataUser, String> col_email;
    @FXML
    private TableColumn<DataUser, String> col_phone;
    @FXML
    private TableColumn<DataUser, Button> col_status;
    @FXML
    private TableColumn<DataUser, Button> col_delete;
    @FXML
    private TableColumn<DataUser, String> col_type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        UserService us = new UserService();
        ArrayList<User> list=us.getAll();
        this.initTable(list);
                
    } 
    private void initTable(ArrayList<User> list){
        
        
        ObservableList<DataUser> obs=FXCollections.observableArrayList();
        
        System.out.println("tab_usertab_usertab_usertab_usertab_usertab_usertab_user");
        list.forEach(user ->{
            if(user.getRole()!=10){
                Button btnstatus = new Button(user.getStatu()==1?"valide":"in valide");
                btnstatus.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            UserService us = new UserService();
                            us.setStatuUser(user.getStatu(), user.getId());
                            ArrayList<User> list=us.getAll();
                            tab_user.getColumns().clear();
                            initTable(list);
                            tab_user.refresh();
                        } catch (SQLException ex) {
                            Logger.getLogger(AdminFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                });
                Button btndelete = new Button("Delete");
                btndelete.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        UserService us = new UserService();
                        us.delete(user);
                        ArrayList<User> list=us.getAll();
                        tab_user.getColumns().clear();
                        initTable(list);
                        tab_user.refresh();
                    }
                });

                obs.add(new DataUser(
                        user.getId(), 
                        user.getNom()+" "+user.getPrenom(), 
                        user.getLogin(),
                        user.getPhone(), 
                        btnstatus,
                        btndelete,
                        String.valueOf(user.getRole()) 
                ));  
            }
            
        });
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("nom"));
        col_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        col_phone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        col_status.setCellValueFactory(new PropertyValueFactory<>("status"));
        col_delete.setCellValueFactory(new PropertyValueFactory<>("delete"));
        col_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tab_user.setItems(obs);
        tab_user.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tab_user.getColumns().addAll(col_id, col_name, col_email, col_phone,col_type,col_status,col_delete);
    }
    
    @FXML
    private void refreshTable(ActionEvent event) {
        UserService us = new UserService();
        ArrayList<User> list=us.getAll();
        tab_user.getColumns().clear();
        initTable(list);
        tab_user.refresh();
    }
    
    @FXML
    private void deconnecter(ActionEvent event) throws IOException {
        LoginService.setUserConnected(0);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginFXML.fxml"));
        Parent root = loader.load();
        tab_user.getScene().setRoot(root);
    }
    
}
