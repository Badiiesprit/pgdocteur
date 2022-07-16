/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

/**
 *
 * @author badi9
 */
public class User {
    private int id;
    private String nom;
    private String prenom;
    private String photo_profil;
    private String adresse;
    private String phone;
    private int role;
    private String password;
    private String login;
    private int statu;
    private String code;

    public User(String nom,String prenom,String login,String password,String phone,String adresse,String photo_profil,int role) {
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.adresse = adresse;
        this.photo_profil =photo_profil;
        this.role = role;
        this.statu =0;
    }
    public User() {
        this.id = 0;
        this.nom = "";
        this.prenom = "";
        this.login = "";
        this.password = "";
        this.phone = "";
        this.adresse = "";
        this.photo_profil ="";
        this.role = 0;
        this.statu =0;
    }
    public User(int id,String nom,String prenom,String login,String password,String phone,String adresse,String photo_profil,int role,int statu) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.password = password;
        this.phone = phone;
        this.adresse = adresse;
        this.photo_profil =photo_profil;
        this.role = role;
        this.statu = statu;
    }

    public User(int aInt, String string, String string0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhoto_profil() {
        return photo_profil;
    }

    public void setPhoto_profil(String photo_profil) {
        this.photo_profil = photo_profil;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getStatu() {
        return statu;
    }

    public void setStatu(int statu) {
        this.statu = statu;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    

    @Override
    public String toString() {
        return  nom + " " + prenom;
    }
    
}
