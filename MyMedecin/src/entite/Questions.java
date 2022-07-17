/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import javafx.scene.control.Button;

/**
 *
 * @author DELL
 */
public class Questions {
    private int id_question;
    private String q_message;
    private int id_user;
    private int id_medecin;

   
    
    /////But
    private Button btn_update;

    public Questions() {
    }

    public Questions(int id_question, String q_message, Button btn_update) {
        this.id_question = id_question;
        this.q_message = q_message;
        this.id_user = id_user;
        this.id_medecin = id_medecin;
        this.btn_update = btn_update;
    }
       public Questions(int id_question, String q_message, int id_user, int id_medecin) {
        this.id_question = id_question;
        this.q_message = q_message;
        this.id_user = id_user;
        this.id_medecin = id_medecin;
    }

    public Questions(String q_message, int id_user, int id_medecin, Button btn_update) {
        this.q_message = q_message;
        this.id_user = id_user;
        this.id_medecin = id_medecin;
        this.btn_update = btn_update;
    }

    public Questions(String q_message, int id_user, int id_medecin) {
        this.q_message = q_message;
        this.id_user = id_user;
        this.id_medecin = id_medecin;
    }




  
  
    public int getId_question() {
        return id_question;
    }

    public String getQ_message() {
        return q_message;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    public void setQ_message(String q_message) {
        this.q_message = q_message;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Button getBtn_update() {
        return btn_update;
    }

    public void setBtn_update(Button btn_update) {
        this.btn_update = btn_update;
    }
     public int getId_medecin() {
        return id_medecin;
    }

    public void setId_medecin(int id_medecin) {
        this.id_medecin = id_medecin;
    }

    @Override
    public String toString() {
        return "Questions{" + "id_question=" + id_question + ", q_message=" + q_message + ", id_user=" + id_user + ", id_medecin=" + id_medecin + ", btn_update=" + btn_update + '}';
    }

}
