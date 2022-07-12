/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author hp
 */
public class InscriptionEvents {	
    private int id;
    private int id_events;
    private int id_user;

    public InscriptionEvents(int id, int id_events, int id_user) {
        this.id = id;
        this.id_events = id_events;
        this.id_user = id_user;
    }

    public InscriptionEvents(int id_events, int id_user) {
        this.id_events = id_events;
        this.id_user = id_user;
    }

    public InscriptionEvents() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_events() {
        return id_events;
    }

    public void setId_events(int id_events) {
        this.id_events = id_events;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    @Override
    public String toString() {
        return "InscriptionEvents{" + "id=" + id + ", id_events=" + id_events + ", id_user=" + id_user + '}';
    }
    
}
