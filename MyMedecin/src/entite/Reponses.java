/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author DELL
 */
public class Reponses {
    private int id_reponse;
    private String r_message;
    private int id_user;
    private int id_question;

    public Reponses(int id_reponse, String r_message) {
        this.id_reponse = id_reponse;
        this.r_message = r_message;
    }

    public Reponses() {
    }

    public Reponses(String r_message, int id_user, int id_question) {
        this.r_message = r_message;
        this.id_user = id_user;
        this.id_question = id_question;
    }

    public Reponses(int id_reponse, String r_message, int id_user, int id_question) {
        this.id_reponse = id_reponse;
        this.r_message = r_message;
        this.id_user = id_user;
        this.id_question = id_question;
    }

    public int getId_reponse() {
        return id_reponse;
    }

    public String getR_message() {
        return r_message;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_question() {
        return id_question;
    }

    public void setId_reponse(int id_reponse) {
        this.id_reponse = id_reponse;
    }

    public void setR_message(String r_message) {
        this.r_message = r_message;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_question(int id_question) {
        this.id_question = id_question;
    }

    @Override
    public String toString() {
        return "Reponses{" + "id_reponse=" + id_reponse + ", r_message=" + r_message + ", id_user=" + id_user + ", id_question=" + id_question + '}';
    }
   
    
}
