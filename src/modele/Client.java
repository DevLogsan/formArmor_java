/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

public class Client
{
    private int id, statut_id, nbhcpta, nbhbur;
    private String nom, password, adresse, cp, ville, email;
  
    // Variable concernant les participants.
    private String type;
    private Double taux_horaire;
    
    public Client()
    {
    }

    public Client(int id, int statut_id, int nbhcpta, int nbhbur, String nom, String password, String adresse, String cp, String ville, String email)
    {
        this.id = id;
        this.statut_id = statut_id;
        this.nbhcpta = nbhcpta;
        this.nbhbur = nbhbur;
        this.nom = nom;
        this.password = password;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.email = email;
    }
    
    // Constructeur utilisé lors de l'appel de la page 'Participant ayant assisté à cette formation' pour compléter le tableau de la page
    public Client(String type, String nom, String adresse, String ville, String email, Double taux_horaire)
    {
        this.type = type;
        this.nom = nom;
        this.adresse = adresse;
        this.ville = ville;
        this.email = email;
        this.taux_horaire = taux_horaire;
    }
    
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

    public int getStatut_id()
    {
        return statut_id;
    }
    public void setStatut_id(int statut_id)
    {
        this.statut_id = statut_id;
    }

    public int getNbhcpta()
    {
        return nbhcpta;
    }
    public void setNbhcpta(int nbhcpta)
    {
        this.nbhcpta = nbhcpta;
    }

    public int getNbhbur()
    {
        return nbhbur;
    }
    public void setNbhbur(int nbhbur)
    {
        this.nbhbur = nbhbur;
    }

    public String getNom()
    {
        return nom;
    }
    public void setNom(String nom)
    {
        this.nom = nom;
    }

    public String getPassword()
    {
        return password;
    }
    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getAdresse()
    {
        return adresse;
    }
    public void setAdresse(String adresse)
    {
        this.adresse = adresse;
    }

    public String getCp()
    {
        return cp;
    }
    public void setCp(String cp)
    {
        this.cp = cp;
    }

    public String getVille()
    {
        return ville;
    }
    public void setVille(String ville)
    {
        this.ville = ville;
    }

    public String getEmail()
    {
        return email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    public String getType()
    {
        return type;
    }
    public void setType(String type)
    {
        this.type = type;
    }
    public Double getTaux_horaire()
    {
        return taux_horaire;
    }
    public void setTaux_horaire(Double taux_horaire)
    {
        this.taux_horaire = taux_horaire;
    }
    @Override
    public String toString()
    {
        return String.valueOf(id);
    }
}
