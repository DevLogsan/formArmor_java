/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author Lucas
 */
public class GestionSql_Rentabilite 
{
    public static ObservableList <Session> getLesSessionsTerminee()
    /*
    Méthode retournant toutes les sessions terminées 
    Table utilisée dans le SGBDR : session_formation
    */
    {
        ObservableList<Session> lesSessionsTerminees = FXCollections.observableArrayList();
        Session uneSessionTerminee;
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String pilote = "org.gjt.mm.mysql.Driver";
        String url = new String("jdbc:mysql://localhost/symfony5_formarmor");
        String requete;
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url,"root","");
            stmt = conn.createStatement();
            requete = "SELECT session_formation.id, formation.libelle, session_formation.date_debut, session_formation.nb_places, session_formation.nb_inscrits, session_formation.nb_absents, formation.coutrevient FROM session_formation, formation WHERE session_formation.formation_id = formation.id AND session_formation.date_debut < NOW()";
            rs = stmt.executeQuery(requete);
            while (rs.next())
            {
                uneSessionTerminee = new Session(rs.getInt("id"), rs.getString("libelle"), rs.getDate("date_debut"), rs.getInt("nb_places"), rs.getInt("nb_inscrits"), rs.getInt("nb_absents"), rs.getDouble("coutrevient"));
                lesSessionsTerminees.add(uneSessionTerminee);
            }
            stmt.close();
            conn.close();
            return lesSessionsTerminees;
        }
        catch (Exception e)
        {
            System.out.println("Erreur requête SQL pour la méthode 'getLesSessionsTerminee', Voici l'erreur : ");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public static ObservableList <Client> getLesParticipantsSessions(int session_formation_id)
    {
        /*
        Méthode permettant de savoir le nombre de participant ayant participer à une formation avec le détail de chaque participant
        */
        ObservableList<Client> lesParticipants = FXCollections.observableArrayList();
        Client unParticipant;
        Connection conn;
        Statement stmt;
        ResultSet rs;
        String pilote = "org.gjt.mm.mysql.Driver";
        String url = new String("jdbc:mysql://localhost/symfony5_formarmor");
        String requete;
        try
        {
            Class.forName(pilote);
            conn = DriverManager.getConnection(url, "root", "");
            stmt = conn.createStatement();
            requete = "SELECT statut.type, nom, adresse, ville, email, statut.taux_horaire FROM statut, client, inscription WHERE client.id = statut.id AND client.id = inscription.client_id AND inscription.session_formation_id = " + session_formation_id;
            rs = stmt.executeQuery(requete);
            while (rs.next())
            {
                unParticipant = new Client(rs.getString("type"), rs.getString("nom"), rs.getString("adresse"), rs.getString("ville"), rs.getString("email"), rs.getDouble("taux_horaire"));
                lesParticipants.add(unParticipant);
            }
            stmt.close();
            conn.close();
            return lesParticipants;
        }
        catch (Exception e)
        {
            System.out.println("Erreur requête SQL pour la méthode 'getLesParticipantsSessions', Voici l'erreur : ");
            System.out.println(e.getMessage());
            return null;
        }
    }
}
