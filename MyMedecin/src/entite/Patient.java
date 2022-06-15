/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author badi9
 */
public class Patient {
    private int id;
    private int id_user;
    
    public Patient(int id_user){
        this.id_user = id_user;
    }
    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
