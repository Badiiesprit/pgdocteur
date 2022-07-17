/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;

import entite.Questions;
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
 * @author DELL
 */
public class QuestionsService implements IService<Questions> {

    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;

    public QuestionsService() {
        cnx = DataSource.getInstance().getCon();
    }
    @Override
    public void insert(Questions t) {
String requete = "insert into questions (q_message,id_user,id_medecin) values (?,?,?)";
        try {
            pst = cnx.prepareStatement(requete);
            pst.setString(1, t.getQ_message());
            pst.setInt(2, t.getId_user());
            pst.setInt(3, t.getId_medecin());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(QuestionsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Questions t) {
        String req = "delete from questions where id=" + t.getId_question();
        try {
            ste = cnx.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(QuestionsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Questions t) {
        String req = "update questions set id_question=" + t.getId_question() + ",message=" + t.getQ_message();
        try {
            System.out.println(req);
            ste = cnx.createStatement();
            ste.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<Questions> getAll() {
        ArrayList<Questions> list=new ArrayList<>();
        String requete="select * from questions order by id_question desc";
        try {
            ste=cnx.createStatement();
           rs=ste.executeQuery(requete);
           while(rs.next()){

               list.add(new Questions(
                       rs.getInt("id_question"), 
                       rs.getString("q_message"),
                       rs.getInt("id_user"),
                       rs.getInt("id_medecin")
            
               ));
           }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Questions getById(int id) {
        String requete="select * from questions where id_question = "+id;
        try {
            ste=cnx.createStatement();
           rs=ste.executeQuery(requete);
           while(rs.next()){

               return new Questions(
                       rs.getInt("id_question"), 
                       rs.getString("q_message"),
                       rs.getInt("id_user"),
                       rs.getInt("id_medecin")
               );
           }
        } catch (SQLException ex) {
            Logger.getLogger(QuestionsService.class.getName()).log(Level.SEVERE, null, ex);
        }
         return null;
    }

}
