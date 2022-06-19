/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTableView;

import javafx.scene.control.Button;

/**
 *
 * @author badi9
 */
public class DataUser {
    private int id;
    private String nom;
    private String email;
    private String phone;
    private Button status;
    private Button delete;
    private String type;

    public DataUser(int id, String nom, String email, String phone, Button status, Button delete,String type) {
        this.id = id;
        this.nom = nom;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.delete = delete;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Button getStatus() {
        return status;
    }

    public void setStatus(Button status) {
        this.status = status;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }

    public String getType() {
        if(Integer.parseInt(type)==2)return "Medecine";
        if(Integer.parseInt(type)==3)return "Pharmacie";
        return "Patient";
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    
    
}
