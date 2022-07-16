/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataTableView;

import java.sql.Date;

/**
 *
 * @author achemsi
 */
public class Calendrier {

   
    
    private String date_plan;
    private String cre;
    private String patient;
    private String commentaire;

    public Calendrier(String date_plan, String cre, String patient, String commentaire) {
        this.date_plan = date_plan;
        this.cre = cre;
        this.patient = patient;
        this.commentaire = commentaire;
    }

    public String getDate_plan() {
        return date_plan;
    }

    public void setDate_plan(String date_plan) {
        this.date_plan = date_plan;
    }

    public String getCre() {
        return cre;
    }

    public void setCre(String cre) {
        this.cre = cre;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    
}
