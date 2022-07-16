/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 *
 * @author badi9
 */
public class ControleSaisie {
    private Pattern pattern;
    private Matcher matcher;

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public ControleSaisie(){
        pattern = Pattern.compile(EMAIL_PATTERN);
    }

    /**
     * Validate hex with regular expression
     * @param hex hex for validation
     * @return true valid hex, false invalid hex
     */
    public boolean valideLogin(final String hex){

        matcher = pattern.matcher(hex);
        return matcher.matches();

    }
    public boolean valideText(String text ,int minlength){
        if(text.length() < minlength){
           return false; 
        }
        System.out.println("valideText : "+(!"".equals(text)));
        return !"".equals(text); 
    }
    public boolean validePassword(String text ){
        if(text.length() < 4){
           return false; 
        }
        System.out.println("validePassword : "+(!"".equals(text)));
        return !"".equals(text); 
    }
    public boolean validePhone(String text){
        System.out.println("validePhone : "+(text.length() == 8));
        return text.length() == 8; 
    }
    
}
