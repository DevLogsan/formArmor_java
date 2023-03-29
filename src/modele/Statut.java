/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Lucas
 */
public class Statut 
{
    private int id;
    private String type;
    private int taux_horaire;
    
    public Statut(int id, String type, int taux_horaire)
    {
        this.id = id;
        this.type = type;
        this.taux_horaire = taux_horaire;
    }
    
    public int getId()
    {
        return id;
    }
    
    public String getType()
    {
        return type;
    }
    
    public int getTauxHoraire()
    {
        return taux_horaire;
    }
}
