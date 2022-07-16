/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;

import entite.Events;
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
public class EventsService implements IService<Events>{
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    public EventsService() {
        cnx = DataSource.getInstance().getCon();
    }
    
    @Override
    public void insert(Events c) {
        String requete = "insert into events (name,description,date_deb,date_fin,nb_participant,id_specialite,id_gouvernerat,type_participant) "
                + "values ('" + c.getName() + "','" + c.getDescription() + "','" + c.getDate_deb()+ "','" + c.getDate_fin() + "','" + c.getNb_participant() + "','" + c.getId_gouvernorat() + "','" + c.getId_specialite() + "'," + c.getNb_participant() + ")";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(EventsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Events t) {
        String req="delete from events where id="+t.getId();
        try {
            ste=cnx.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(EventsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Events t) {
        String requete = "update events set name='"+t.getName()+"' ,description='"+t.getDescription()+"' ,date_deb='"+t.getDate_deb()+"' ,date_fin='"+t.getDate_fin()+"' where id="+t.getId()+"";
        System.out.println(requete);
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(EventsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Events> getAll() {
        ArrayList<Events> list=new ArrayList<>();
        String requete="select * from events";
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
           while(rs.next()){
               list.add(new Events(
                       rs.getInt(1),
                       rs.getString(2), 
                       rs.getString(3),
                       rs.getDate(4),
                       rs.getDate(5),
                       rs.getInt(6),
                       rs.getInt(7),
                       rs.getInt(8),
                       rs.getInt(9)
               ));
           }
        } catch (SQLException ex) {
            Logger.getLogger(EventsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Events getById(int id) {
        String requete="select * from events where id="+id;
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
           while(rs.next()){
               return new Events(
                       rs.getInt(1),
                       rs.getString(2), 
                       rs.getString(3),
                       rs.getDate(4),
                       rs.getDate(5),
                       rs.getInt(6),
                       rs.getInt(7),
                       rs.getInt(8),
                       rs.getInt(9)
               );
           }
        } catch (SQLException ex) {
            Logger.getLogger(EventsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new Events();
    }
    public void setNbParticipant(int id,int nb) {
        String requete = "update events set nb_participant='"+nb+"' where id="+id+"";
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(EventsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
