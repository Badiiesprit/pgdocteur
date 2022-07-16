/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entite;

import java.sql.SQLException;
import java.util.Objects;
import utils.DataSource;

/**
 *
 * @author achemsi
 */
public class Creneau {
    private int id;
    private String creneau;

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Creneau other = (Creneau) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.creneau, other.creneau)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return creneau ;
    }

    public int getId() {
        return id;
    }
    

    public void setId(int id) {
        this.id = id;
    }

    public String getCreneau() {
        return creneau;
    }

    public void setCreneau(String creneau) {
        this.creneau = creneau;
    }

    public Creneau(int id, String creneau) {
        this.id = id;
        this.creneau = creneau;
    }
    
}
