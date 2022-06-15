/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;
import entite.Medecines;
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
public class MedecinesService implements IService<Medecines>{
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    
    public MedecinesService(){
         cnx = DataSource.getInstance().getCon();
    }
    @Override
    public void insert(Medecines t) {
        String requete = "insert into medecines "
                + "(id, id_user, adresse_cabinet, phone_fixe_cabinet, phone_fixe2_cabinet, id_specialite, id_gouvernorat, gallery_cabinet)"
                + " values (null," 
                + t.getId_user()+ ",'" 
                + t.getAdresse_cabinet()+ "','" 
                + t.getPhone_fixe_cabinet()+ "','" 
                + t.getPhone_fixe2_cabinet()+ "'," 
                + t.getId_specialite() + "," 
                + t.getId_gouvernorat() + ",'" 
                + t.getGallery_cabinet() + "')";

        try {
            ste = cnx.createStatement();
            //System.out.println(requete);
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(MedecinesService.class.getName()).log(Level.SEVERE, null, ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Medecines t) {
        
        String req="delete from medecines where id="+t.getId();
        try {
            ste=cnx.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(MedecinesService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Medecines t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Medecines> getAll() {
        ArrayList<Medecines> list=new ArrayList<>();
        String requete="select * from medecines";
        try {
            ste=cnx.createStatement();
           rs=ste.executeQuery(requete);
           while(rs.next()){
               list.add(new Medecines(
                       rs.getInt(1), 
                       rs.getString(2),
                       rs.getString(3), 
                       rs.getString(4), 
                       rs.getInt(5), 
                       rs.getInt(6), 
                       rs.getString(7)
               ));
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Medecines getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
