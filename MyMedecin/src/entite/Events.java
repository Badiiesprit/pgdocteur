/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.Date;

/**
 *
 * @author hp
 */
public class Events {
     private int id;
    private String name;
    private String description;
    private Date date_deb;
    private Date date_fin;
    private int nb_participant;
    private int id_specialite;
    private int id_gouvernorat;
    private int type_particpant;

    public Events(int id, String name, String description, Date date_deb, Date date_fin, int nb_participant, int id_specialite, int id_gouvernorat, int type_particpant) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.nb_participant = nb_participant;
        this.id_specialite = id_specialite;
        this.id_gouvernorat = id_gouvernorat;
        this.type_particpant = type_particpant;
    }

    public Events(String name, String description, Date date_deb, Date date_fin, int nb_participant, int id_specialite, int id_gouvernorat, int type_particpant) {
        this.name = name;
        this.description = description;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.nb_participant = nb_participant;
        this.id_specialite = id_specialite;
        this.id_gouvernorat = id_gouvernorat;
        this.type_particpant = type_particpant;
    }

    public Events() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public int getNb_participant() {
        return nb_participant;
    }

    public void setNb_participant(int nb_participant) {
        this.nb_participant = nb_participant;
    }

    public int getId_specialite() {
        return id_specialite;
    }

    public void setId_specialite(int id_specialite) {
        this.id_specialite = id_specialite;
    }

    public int getId_gouvernorat() {
        return id_gouvernorat;
    }

    public void setId_gouvernorat(int id_gouvernorat) {
        this.id_gouvernorat = id_gouvernorat;
    }

    public int getType_particpant() {
        return type_particpant;
    }

    public void setType_particpant(int type_particpant) {
        this.type_particpant = type_particpant;
    }

    @Override
    public String toString() {
        return  id + " - " + name ;
    }
   
    
}
