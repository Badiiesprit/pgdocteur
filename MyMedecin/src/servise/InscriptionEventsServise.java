/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;

import entite.Events;
import entite.InscriptionEvents;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;

/**
 *
 * @author hp
 */
public class InscriptionEventsServise {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    public InscriptionEventsServise() {
        cnx = DataSource.getInstance().getCon();
    }

    public boolean insert(InscriptionEvents t) {
        EventsService es = new EventsService();
        Events e = es.getById(t.getId_events());
        if(e.getNb_participant()>0){
            String requete = "insert into inscription_events (id_events,id_user) "
                + "values (" + t.getId_events() + "," + t.getId_user() + ")";
            try {
                ste = cnx.createStatement();
                ste.executeUpdate(requete);
                return true;
            } catch (SQLException ex) {
                Logger.getLogger(EventsService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    
}
