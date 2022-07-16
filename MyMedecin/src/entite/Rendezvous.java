/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package entite;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import servise.UserService;


/**
 *
 * @author LENOVO
 */
public class Rendezvous {
    private int id_plan;
    private int id_cre;
    private int id_medecin;
    private Date date_plan;
    private String Name;
    private String commentaire;
    private int id_patient;

    public Rendezvous(int id_cre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public int getId_cre() {
        return id_cre;
    }

    public void setId_cre(int id_cre) {
        this.id_cre = id_cre;
    }
    public Rendezvous(int id_cre, int id_medecin, int id_patient, Date date_plan, String Name){
        this.id_cre=id_cre;
        this.id_medecin=id_medecin;
        this.date_plan=date_plan;
        this.Name=Name;
        this.id_patient=id_patient;
       
    }

    public Rendezvous(int id_cre, int id_medecin, int id_patient,Date date_plan, String Name, String commentaire) {
        this.id_cre = id_cre;
        this.id_medecin = id_medecin;
        this.id_patient = id_patient;
        this.date_plan = date_plan;
        this.Name = Name;
        this.commentaire = commentaire;
    }

    public int getId_patient() {
        return id_patient;
    }

    public void setId_patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public Rendezvous() {
    }

    @Override
    public String toString() {
        return "Rendezvous{" + "id_plan=" + id_plan + ", id_cre=" + id_cre + ", id_medecin=" + id_medecin + ", id_patient=" + id_patient + ", date_plan=" + date_plan + ", Name=" + Name + '}';
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    public Date getDate_plan() {
        return date_plan;
    }

    public void setDate_plan(Date date_plan) {
        this.date_plan = date_plan;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
    public Rendezvous(int id_cre, int id_medecin, Date date_plan){
        this.id_cre=id_cre;
        this.id_medecin=id_medecin;
        this.date_plan=date_plan;
        
    }
    public Rendezvous(int id_cre, int id_medecin, String Name){
        this.id_cre=id_cre;
        this.id_medecin=id_medecin;
        this.Name=Name;
    }
     
}

