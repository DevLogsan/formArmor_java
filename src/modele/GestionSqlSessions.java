package modele;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.GregorianCalendar;
import javafx.collections.FXCollections;
import sql.GestionBdd;

import javafx.collections.ObservableList;

public class GestionSqlSessions {
    
    public static ObservableList<Session> getLesSessionsAVenir()
    {
        Connection conn;
        Statement stmt1;
        Session maSession;
        ObservableList<Session> lesSessions = FXCollections.observableArrayList();
        try
        {
            // On prévoit 2 connexions à la base
            stmt1 = GestionBdd.connexionBdd(GestionBdd.TYPE_MYSQL, "symfony5_formarmor","localhost", "root","");
            
            String req = "select s.id, f.libelle, f.niveau, date_debut, duree, nb_places, nb_inscrits, coutrevient ";
            req += "from session_formation s, formation f ";
            req += "where s.formation_id = f.id";
            req += "and s.date_debut > NOW()";
            req += "ORDER BY date_debut ASC";
            ResultSet rs = GestionBdd.envoiRequeteLMD(stmt1,req);
            while (rs.next())
            {
                // A MODIFIER
                maSession = new Session(rs.getInt("id"), rs.getString("libelle"), rs.getDate("date_debut"), rs.getInt("nb_places"), rs.getInt("nb_inscrits"));
                lesSessions.add(maSession);
            }
        }
        catch (SQLException se)
        {
            System.out.println("Erreur SQL requete getLesSessions : " + se.getMessage());
        }
        return lesSessions;
    }
}
