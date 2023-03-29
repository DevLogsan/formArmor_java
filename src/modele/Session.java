
package modele;

import java.sql.Date;

/**
 *
 * @author Philippe
 */
public class Session
{
    private int id;
    private String libFormation;
    private Date date_debut;
    private int nb_places, nb_inscrits, nb_absents;
    private double coutrevient;
    
    public Session(int id, String libFormation, Date date_debut, int nb_places, int nb_inscrits, int nb_absents, double coutrevient)
    {
        this.id = id;
        this.libFormation = libFormation;
        this.date_debut = date_debut;
        this.nb_places = nb_places;
        this.nb_inscrits = nb_inscrits;
        this.nb_absents = nb_absents;
        this.coutrevient = coutrevient;
    }

    public Session(int id, String libFormation, Date date_debut, int nb_places, int nb_inscrits)
    { //nb_absents et coutrevient initialisés à NULL
        this.id = id;
        this.libFormation = libFormation;
        this.date_debut = date_debut;
        this.nb_places = nb_places;
        this.nb_inscrits = nb_inscrits;
    }
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public String getLibFormation()
    {
        return libFormation;
    }
    public void setLibFormation(String libFormation)
    {
        this.libFormation = libFormation;
    }

    public Date getDate_debut()
    {
        return date_debut;
    }
    public void setDate_debut(Date date_debut)
    {
        this.date_debut = date_debut;
    }

    public int getNb_places()
    {
        return nb_places;
    }
    public void setNb_places(int nb_places)
    {
        this.nb_places = nb_places;
    }

    public int getNb_inscrits()
    {
        return nb_inscrits;
    }
    public void setNb_inscrits(int nb_inscrits)
    {
        this.nb_inscrits = nb_inscrits;
    }

    public int getNb_absents() {
        return nb_absents;
    }
    public void setNb_absents(int nb_absents) {
        this.nb_absents = nb_absents;
    }

    public double getCoutrevient() {
        return coutrevient;
    }
    public void setCoutrevient(double coutrevient) {
        this.coutrevient = coutrevient;
    }
}
