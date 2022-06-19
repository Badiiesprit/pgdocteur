/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

/**
 *
 * @author badi9
 */
public class Medecines {
    private int id;
    private int id_user;
    private String adresse_cabinet;
    private String phone_fixe_cabinet;
    private String phone_fixe2_cabinet;
    private int id_specialite ;
    private int id_gouvernorat ;
    private String gallery_cabinet;

    public Medecines(int id,int id_user,String adresse_cabinet,String phone_fixe_cabinet,String phone_fixe2_cabinet,int id_specialite,int id_gouvernorat,String gallery_cabinet){
        this.id = id;
        this.id_user = id_user;
        this.adresse_cabinet = adresse_cabinet;
        this.phone_fixe_cabinet = phone_fixe_cabinet;
        this.phone_fixe2_cabinet = phone_fixe2_cabinet; 
        this.id_specialite = id_specialite;
        this.id_gouvernorat = id_gouvernorat;
        this.gallery_cabinet = gallery_cabinet;
    }

    public Medecines() {
    }
    public Medecines(int id_user,String adresse_cabinet,String phone_fixe_cabinet,String phone_fixe2_cabinet,int id_specialite,int id_gouvernorat,String gallery_cabinet){
        this.id_user = id_user;
        this.adresse_cabinet = adresse_cabinet;
        this.phone_fixe_cabinet = phone_fixe_cabinet;
        this.phone_fixe2_cabinet = phone_fixe2_cabinet; 
        this.id_specialite = id_specialite;
        this.id_gouvernorat = id_gouvernorat;
        this.gallery_cabinet = gallery_cabinet;
    }
    
    public int getId() {
        return id;
    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getAdresse_cabinet() {
        return adresse_cabinet;
    }

    public void setAdresse_cabinet(String adresse_cabinet) {
        this.adresse_cabinet = adresse_cabinet;
    }

    public String getPhone_fixe_cabinet() {
        return phone_fixe_cabinet;
    }

    public void setPhone_fixe_cabinet(String phone_fixe_cabinet) {
        this.phone_fixe_cabinet = phone_fixe_cabinet;
    }

    public String getPhone_fixe2_cabinet() {
        return phone_fixe2_cabinet;
    }

    public void setPhone_fixe2_cabinet(String phone_fixe2_cabinet) {
        this.phone_fixe2_cabinet = phone_fixe2_cabinet;
    }

    public int getId_specialite() {
        return id_specialite;
    }

    public void setId_specialite(int id_specialite) {
        this.id_specialite = id_specialite;
    }

    public int getId_gouvernorat() {
        return id_gouvernorat;
    }

    public void setId_gouvernorat(int id_gouvernorat) {
        this.id_gouvernorat = id_gouvernorat;
    }

    public String getGallery_cabinet() {
        return gallery_cabinet;
    }

    public void setGallery_cabinet(String gallery_cabinet) {
        this.gallery_cabinet = gallery_cabinet;
    }
}
