/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;
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
public class UserService implements IService<User>{
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private int lastId;
    private int size;
    public UserService(){
        cnx = DataSource.getInstance().getCon();
    }
    @Override
    public void insert(User t) {
        String requete = "insert into user "
                + "(nom,prenom,photo_profil,adresse,phone,role,password,login) "
                + "values ('" + t.getNom() 
                + "','" + t.getPrenom() 
                + "'," + t.getPhoto_profil() 
                + "'," + t.getAdresse()
                + "'," + t.getPhone()
                + "'," + t.getRole()
                + "'," + t.getPassword()
                + "'," + t.getLogin()
                + ")";

        try {
            ste = cnx.createStatement();
            ste.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int preInsert(User t) {
        String requete = "insert into user "
                + "(id,nom,prenom,photo_profil,adresse,phone,role,password,login) "
                + "values (null,'" + t.getNom() 
                + "','" + t.getPrenom() 
                + "','" + t.getPhoto_profil() 
                + "','" + t.getAdresse()
                + "','" + t.getPhone()
                + "','" + t.getRole()
                + "','" + t.getPassword()
                + "','" + t.getLogin()
                + "');";
        try {
            ste = cnx.createStatement();
            rs = ste.executeQuery("SELECT * FROM user WHERE login ='"+t.getLogin()+"' ");
            size = this.getSize(rs);
            
            System.out.println("getSize:"+size);
            if(size==0){
                System.out.println("ALL FetchSize:");
                ste.executeUpdate(requete);
                rs = ste.executeQuery("SELECT * FROM user ORDER BY id DESC LIMIT 1");
                System.out.println(rs.getFetchSize());
                while(rs.next()){
                    lastId = rs.getInt(1);
                }
                return lastId;
            }
            return -1;
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    @Override
    public void delete(User t) {
        
        String req="delete from user where id="+t.getId();
        try {
            ste=cnx.createStatement();
                    ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User t) {
        String requete = "update user set nom='"+t.getNom()+"',prenom='"+t.getPrenom()+"',adresse='"+t.getAdresse()+"',phone='"+t.getPhone()+"',login='"+t.getLogin()+"'";
                
        if( t.getPassword()!=null && t.getPassword().length()>0){
            requete += ",password='"+t.getPassword()+"'";
        }
        if(t.getPhoto_profil()!=null && t.getPhoto_profil().length()>0){
            requete += ",photo_profil='"+t.getPhoto_profil()+"'";
        }
        
        requete += "   where id="+t.getId();
        System.out.println("servise.UserService.update()");
        System.out.println(requete);
        try {
            ste=cnx.createStatement();
            ste.executeUpdate(requete);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public ArrayList<User> getAll() {
        ArrayList<User> list=new ArrayList<>();
        String requete="select * from user order by id desc";
        try {
            ste=cnx.createStatement();
           rs=ste.executeQuery(requete);
           while(rs.next()){
              
             

                       
               list.add(new User(
                       rs.getInt("id"), 
                       rs.getString("nom"),
                       rs.getString("prenom"),
                       rs.getString("login"),
                       rs.getString("password"),
                       rs.getString("phone"),
                       rs.getString("adresse"), 
                       rs.getString("photo_profil"), 
                       rs.getInt("role"),
                       rs.getInt("statu")
               ));
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ArrayList<User> getAllMedecines() {
        ArrayList<User> list=new ArrayList<>();
        String requete="select * from user where role=2 order by id desc";
        try {
            ste=cnx.createStatement();
           rs=ste.executeQuery(requete);
           while(rs.next()){
              
             

                       
               list.add(new User(
                       rs.getInt("id"), 
                       rs.getString("nom"),
                       rs.getString("prenom"),
                       rs.getString("login"),
                       rs.getString("password"),
                       rs.getString("phone"),
                       rs.getString("adresse"), 
                       rs.getString("photo_profil"), 
                       rs.getInt("role"),
                       rs.getInt("statu")
               ));
           }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public User getById(int id) {
        String requete="select * from user where id="+id;
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                return new User(
                    rs.getInt("id"), 
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("phone"), 
                    rs.getString("adresse"),
                    rs.getString("photo_profil"),
                    rs.getInt("role"),
                    rs.getInt("statu")
                );
            }
            return new User();
        } catch (SQLException ex) {
            return new User();
        }
    }
    public User getByCode(String code) {
        String requete="select * from user where code="+code;
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                return new User(
                    rs.getInt("id"), 
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("phone"), 
                    rs.getString("adresse"),
                    rs.getString("photo_profil"),
                    rs.getInt("role"),
                    rs.getInt("statu")
                );
            }
            return new User();
        } catch (SQLException ex) {
            return new User();
        }
    }
    public User getByEmail(String login) {
        String requete="select * from user where login='"+login+"'";
        try {
            ste=cnx.createStatement();
            rs=ste.executeQuery(requete);
            while(rs.next()){
                return new User(
                    rs.getInt("id"), 
                    rs.getString("nom"),
                    rs.getString("prenom"),
                    rs.getString("login"),
                    rs.getString("password"),
                    rs.getString("phone"), 
                    rs.getString("adresse"),
                    rs.getString("photo_profil"),
                    rs.getInt("role"),
                    rs.getInt("statu")
                );
            }
            return new User();
        } catch (SQLException ex) {
            return new User();
        }
    }
    private int getSize( ResultSet rs) throws SQLException{
        if(rs.next()){
            size = 1;
        }else{
            return 0;
        }
        while(rs.next()){
            size++;
        }
        return size;
    }
    
    public void setStatuUser( int statu , int id ) throws SQLException{
        String req="update user set statu=0 where id="+id;
        if(statu == 0){
            req="update user set statu=1 where id="+id;
        }
        
        try {
            ste=cnx.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setCodeValidationUser( String code , int id ) throws SQLException{
        String req="update user set code="+code+" where id="+id;
        
        try {
            ste=cnx.createStatement();
            ste.executeUpdate(req);

        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
