/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;
import entite.Pharmacie;
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
public class PharmacieService implements IService<Pharmacie>{
 private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private int lastId;
    public PharmacieService(){
        cnx = DataSource.getInstance().getCon();
    }
    @Override
    public void insert(Pharmacie t) {
        String requete = "insert into pharmacie "
                + "(id, id_user, adresse_pharmacie, phone_fixe_pharmacie, phone_fixe2_pharmacie, gallery_pharmacie, id_gouvernorat, name_pharmacie)"
                + " values (null," 
                + t.getId_user()+ ",'" 
                + t.getAdresse_pharmacie()+ "','" 
                + t.getPhone_fixe_pharmacie()+ "','" 
                + t.getPhone_fixe2_pharmacie()+ "','" 
                + t.getGallery_pharmacie() + "'," 
                + t.getId_gouvernorat() + ",'" 
                + t.getName_pharmacie() + "')";

        try {
            ste = cnx.createStatement();
            //System.out.println(requete);
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            //Logger.getLogger(MedecinesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Pharmacie t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(Pharmacie t) {
        String requete = "update pharmacie set adresse_pharmacie='"+t.getAdresse_pharmacie()+"',phone_fixe_pharmacie='"+t.getPhone_fixe_pharmacie()+"',phone_fixe2_pharmacie='"+t.getPhone_fixe2_pharmacie()+"',id_gouvernorat="+t.getId_gouvernorat()+",name_pharmacie='"+t.getName_pharmacie()+"' where id_user="+t.getId_user();

        try {
            System.out.println(requete);
            ste=cnx.createStatement();
            ste.executeUpdate(requete);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Pharmacie> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Pharmacie getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public Pharmacie getByUser(int id) {
        String requete="select * from pharmacie where id_user="+id;
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                return new Pharmacie(
                    rs.getInt("id"), 
                    rs.getInt("id_user"),
                    rs.getString("adresse_pharmacie"),
                    rs.getString("phone_fixe_pharmacie"),
                    rs.getString("phone_fixe2_pharmacie"),
                    rs.getString("name_pharmacie"), 
                    rs.getString("gallery_pharmacie"),
                    rs.getInt("id_gouvernorat")
                );
            }
            return new Pharmacie();
        } catch (SQLException ex) {
            return new Pharmacie();
        }
    }
    
}
