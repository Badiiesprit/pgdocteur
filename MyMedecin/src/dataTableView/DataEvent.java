/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTableView;

import javafx.scene.control.Button;

/**
 *
 * @author hp
 */
public class DataEvent {
    private int id;
    private String event;
    private String description;
    private String date_deb;
    private String date_fin;
    private String specialite;
    private String gouvernerat;
    private Integer participant;
    private Button delete;

    public DataEvent(int id, String event, String description, String date_deb, String date_fin, String specialite, String gouvernerat, Integer participant, Button delete) {
        this.id = id;
        this.event = event;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.specialite = specialite;
        this.gouvernerat = gouvernerat;
        this.participant = participant;
        this.delete = delete;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(String date_deb) {
        this.date_deb = date_deb;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getGouvernerat() {
        return gouvernerat;
    }

    public void setGouvernerat(String gouvernerat) {
        this.gouvernerat = gouvernerat;
    }

    public Integer getParticipant() {
        return participant;
    }

    public void setParticipant(Integer participant) {
        this.participant = participant;
    }

    public Button getDelete() {
        return delete;
    }

    public void setDelete(Button delete) {
        this.delete = delete;
    }
    

}
