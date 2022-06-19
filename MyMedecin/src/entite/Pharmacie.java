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
public class Pharmacie {
    private int id;
    private int id_user;
    private String adresse_pharmacie;
    private String phone_fixe_pharmacie;
    private String phone_fixe2_pharmacie;
    private String name_pharmacie;
    private String gallery_pharmacie;
    private int id_gouvernorat;						

    public Pharmacie(int id_user, String adresse_pharmacie, String phone_fixe_pharmacie, String phone_fixe2_pharmacie, String name_pharmacie, String gallery_pharmacie, int id_gouvernorat) {
        this.id_user = id_user;
        this.adresse_pharmacie = adresse_pharmacie;
        this.phone_fixe_pharmacie = phone_fixe_pharmacie;
        this.phone_fixe2_pharmacie = phone_fixe2_pharmacie;
        this.name_pharmacie = name_pharmacie;
        this.gallery_pharmacie = gallery_pharmacie;
        this.id_gouvernorat = id_gouvernorat;
    }

    public Pharmacie(int id, int id_user, String adresse_pharmacie, String phone_fixe_pharmacie, String phone_fixe2_pharmacie, String name_pharmacie, String gallery_pharmacie, int id_gouvernorat) {
        this.id = id;
        this.id_user = id_user;
        this.adresse_pharmacie = adresse_pharmacie;
        this.phone_fixe_pharmacie = phone_fixe_pharmacie;
        this.phone_fixe2_pharmacie = phone_fixe2_pharmacie;
        this.name_pharmacie = name_pharmacie;
        this.gallery_pharmacie = gallery_pharmacie;
        this.id_gouvernorat = id_gouvernorat;
    }

    public Pharmacie() {
        this.id = 0;
        this.id_user = 0;
        this.adresse_pharmacie = "";
        this.phone_fixe_pharmacie = "";
        this.phone_fixe2_pharmacie = "";
        this.name_pharmacie = "";
        this.gallery_pharmacie = "";
        this.id_gouvernorat = 0;    }

    public int getId() {
        return id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getAdresse_pharmacie() {
        return adresse_pharmacie;
    }

    public void setAdresse_pharmacie(String adresse_pharmacie) {
        this.adresse_pharmacie = adresse_pharmacie;
    }

    public String getPhone_fixe_pharmacie() {
        return phone_fixe_pharmacie;
    }

    public void setPhone_fixe_pharmacie(String phone_fixe_pharmacie) {
        this.phone_fixe_pharmacie = phone_fixe_pharmacie;
    }

    public String getPhone_fixe2_pharmacie() {
        return phone_fixe2_pharmacie;
    }

    public void setPhone_fixe2_pharmacie(String phone_fixe2_pharmacie) {
        this.phone_fixe2_pharmacie = phone_fixe2_pharmacie;
    }

    public String getName_pharmacie() {
        return name_pharmacie;
    }

    public void setName_pharmacie(String name_pharmacie) {
        this.name_pharmacie = name_pharmacie;
    }

    public String getGallery_pharmacie() {
        return gallery_pharmacie;
    }

    public void setGallery_pharmacie(String gallery_pharmacie) {
        this.gallery_pharmacie = gallery_pharmacie;
    }

    public int getId_gouvernorat() {
        return id_gouvernorat;
    }

    public void setId_gouvernorat(int id_gouvernorat) {
        this.id_gouvernorat = id_gouvernorat;
    }

    @Override
    public String toString() {
        return "Pharmacie{" + "id=" + id + ", id_user=" + id_user + ", adresse_pharmacie=" + adresse_pharmacie + ", phone_fixe_pharmacie=" + phone_fixe_pharmacie + ", phone_fixe2_pharmacie=" + phone_fixe2_pharmacie + ", name_pharmacie=" + name_pharmacie + ", gallery_pharmacie=" + gallery_pharmacie + ", id_gouvernorat=" + id_gouvernorat + '}';
    }
    
}
