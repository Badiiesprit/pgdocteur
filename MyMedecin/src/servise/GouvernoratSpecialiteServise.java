/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;

import entite.Creneau;
import entite.Gouvernorat;
import entite.Specialite;
import entite.User;
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
 * @author badi9
 */
public class GouvernoratSpecialiteServise {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    public GouvernoratSpecialiteServise(){
        cnx = DataSource.getInstance().getCon();
    }
    public ArrayList<Gouvernorat> getAllGouvernorat() {
        ArrayList<Gouvernorat> list=new ArrayList<>();
        String requete="select * from gouvernorats";
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
               list.add(new Gouvernorat(
                       rs.getInt(1), 
                       rs.getString(2)
               ));
           }
        } catch (SQLException ex) {
            Logger.getLogger(GouvernoratSpecialiteServise.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    public ArrayList<Specialite> getAllSpecialite() {
        ArrayList<Specialite> list=new ArrayList<>();
        String requete="select * from specialites";
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
               list.add(new Specialite(
                       rs.getInt(1), 
                       rs.getString(2)
               ));
           }
        } catch (SQLException ex) {
            Logger.getLogger(GouvernoratSpecialiteServise.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    } 
    /////////////////////////////////Amine CHEMSI//////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    public ArrayList<Creneau> getAllcreneau() {
        ArrayList<Creneau> list = new ArrayList<>();
        String requete = "SELECT * FROM `creneaux`";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Creneau(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GouvernoratSpecialiteServise.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public ArrayList<Creneau> getCreneaubyId(int id_medecin,String date_plan) {
        ArrayList<Creneau> list = new ArrayList<>();
        String requete =  "select * from `creneaux` where id_cre not in(select id_cre from `planification` where id_medecin=" +id_medecin +" and date_plan='" +date_plan +"') ";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Creneau(
                        rs.getInt(1),
                        rs.getString(2)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GouvernoratSpecialiteServise.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    
    //////////////////////////////Fin Amine CHemsi /////////////////////////////////////////////////////
}
