/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servise;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import utils.DataSource;

/**
 *
 * @author badi9
 */
public class LoginService {
    private Connection cnx;
    private Statement ste;
    private PreparedStatement pst;
    private ResultSet rs;
    private int size;
    private static int user_connected = 0;
    
    public LoginService(){
        cnx = DataSource.getInstance().getCon();
    }
    
    public static int getUserConnected(){
        return LoginService.user_connected;
    }

    //If you do not want to change the var ever then do not include this
    public static void setUserConnected(int user_connected){
        LoginService.user_connected = user_connected;
    }
    
    public int validLogin(String login,String password) throws SQLException{
        
        try {
            ste = cnx.createStatement();
            String sql = "SELECT * FROM user WHERE statu=1 AND login ='"+login+"' AND password = '"+password+"'";
            rs = ste.executeQuery(sql);
            while(rs.next()){
                LoginService.setUserConnected(rs.getInt(1));
                return rs.getInt(1);
            }
            LoginService.setUserConnected(0);
            return 0;
        } catch (Exception e) {
            LoginService.setUserConnected(0);
            return 0;
        }
        
    }
     public int validLoginAdmin(String login,String password) throws SQLException{
        
        try {
            ste = cnx.createStatement();
            String sql = "SELECT * FROM user WHERE statu=1 AND role=10 AND login ='"+login+"' AND password = '"+password+"'";
            rs = ste.executeQuery(sql);
            while(rs.next()){
                LoginService.setUserConnected(rs.getInt(1));
                return rs.getInt(1);
            }
            //LoginService.setUserConnected(0);
            return 0;
        } catch (Exception e) {
            //LoginService.setUserConnected(0);
            return 0;
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
    
}
