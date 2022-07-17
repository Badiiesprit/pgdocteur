/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;

import entite.Reponses;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataSource;


public class ReponsesService implements IService<Reponses> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public ReponsesService() {
        cnx = DataSource.getInstance().getCon();
    }

    @Override
    public void insert(Reponses t) {
        String requete = "insert into reponses (r_message,id_user,id_question) values (?,?,?)";
        try {   
            pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getR_message());
            pst.setInt(2, t.getId_user());
            pst.setInt(3, t.getId_question());
            pst.executeUpdate();

            } catch (SQLException ex) {
                Logger.getLogger(QuestionsService.class.getName()).log(Level.SEVERE, null, ex);
                }
    }

    @Override
    public void delete(Reponses t) {
        String req = "delete from reponses where id=" + t.getId_reponse();
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(QuestionsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @Override
    public void update(Reponses t) {
        String req = "update reponses set id_question=" + t.getId_question() + ",message=" + t.getR_message() + ",id_user= " + t.getId_user();
        try {
            System.out.println(req);
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Reponses> getAll() {
       ArrayList<Reponses> list=new ArrayList<>();
        String requete="select * from reponses order by id_reponse desc";
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){

               list.add(new Reponses(
                       rs.getInt("id_reponse"), 
                       rs.getString("r_message"),
                       rs.getInt("id_user"),
                       rs.getInt("id_question")
            
               ));
           }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Reponses getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
