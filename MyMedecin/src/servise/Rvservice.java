
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;

//import entite.Medecines;
import entite.Rendezvous;
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
 * @author LENOVO
 */
public class Rvservice implements IService<Rendezvous> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public Rvservice() {
        cnx = DataSource.getInstance().getCon();
    }

    @Override
    public void insert(Rendezvous Rv) {
        String requete = "insert into planification ( id_cre, id_medecin, id_patient, date_plan, Name, commentaire)"
                + " values ("
                + Rv.getId_cre() + ","
                + Rv.getId_medecin() + ","
                + Rv.getId_patient() + ",'"
                + Rv.getDate_plan() + "','"
                + Rv.getName() + "','"
                + Rv.getCommentaire() + "')";

        try {
            ste = cnx.createStatement();
            //System.out.println(requete);
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(Rvservice.class.getName()).log(Level.SEVERE, null, ex);
        }
        // throw new UnsupportedOperationException("Not supported yet."); 
        // throw new UnsupportedOperationException("Not supported yet."); 
    }

    @Override
    public void delete(Rendezvous t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Rendezvous t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Rendezvous> getAll() {
        ArrayList<Rendezvous> list = new ArrayList<>();
        String requete = "SELECT * FROM `planification` WHERE date_plan>=curdate() order by 4,2";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Rendezvous(
                        rs.getInt("id_cre"),
                        rs.getInt("id_medecin"),
                        rs.getInt("id_patient"),
                        rs.getDate("date_plan"),
                        rs.getString("Name"),
                        rs.getString("commentaire")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ArrayList<Rendezvous> getAllbymedecin() {
       
        ArrayList<Rendezvous> list = new ArrayList<>();
        String requete = "SELECT * FROM `planification` WHERE date_plan>=curdate() and id_medecin="+LoginService.getUserConnected()+ " order by date_plan,id_cre";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery(requete);
            while (rs.next()) {
                list.add(new Rendezvous(
                        rs.getInt("id_cre"),
                        rs.getInt("id_medecin"),
                        rs.getInt("id_patient"),
                        rs.getDate("date_plan"),
                        rs.getString("Name"),
                        rs.getString("commentaire")
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    //@Override
    //public ArrayList<Rendezvous> getAll() {

    //}
    @Override
    public Rendezvous getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        /* String requete="select * from creneau where id="+id;
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                return new Rendezvous(
                    rs.getInt("id_cre"), 
                    rs.getInt("id_medecin"),
                    rs.getDate("date_plan"),
                    rs.getString("Name"),
                    rs.getString("commentaire")
                    
                );
            }
            return new Rendezvous();
        } catch (SQLException ex) {
            return new Rendezvous();
        }
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.*/
    }

}
