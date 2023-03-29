/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Session;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import modele.Client;
import modele.GestionSql_Rentabilite;
import modele.Statut;

/**
 * FXML Controller class
 *
 * @author Rabelais
 */
public class FenFXML_DetailSessionControleur implements Initializable 
{
    
    ObservableList<Session> infoSessionAchevee;

    private Session sessionSelectionnee;
    private Stage primaryStage;
    private BorderPane rootLayout;
    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField tfIdentifiant;
    @FXML
    private TextField tfLibelle;
    @FXML
    private TextField tfDate;
    @FXML
    private TextField tfPlace;
    @FXML
    private TextField tfInscrit;
    @FXML
    private TextField tfTaux;
    @FXML
    private TextField tfAbsent;
    @FXML
    private Button btnParticipant;
    @FXML
    private TableView tvParticipant;
    @FXML
    private TextField tfCout;
    @FXML
    private TableColumn <Client, String> tcEmploye;
    @FXML
    private TableColumn <Client, String> tcVille;
    @FXML
    private TableColumn <Client, Double> tcTauxHoraire;
    @FXML
    private TextField tfMarge;

    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // Je vais faire un tableau d'objet "Client" pour récupérer tous mes clients qui ont participer à la session
        // je passe donc l'ID de la session en paramètre pour identifier la session concernée.
        ObservableList<Client> lesParticipants = GestionSql_Rentabilite.getLesParticipantsSessions(MainApp.getMaSessionSelectionne().getId());
        tvParticipant.setItems(lesParticipants);
        
        // Je préremplie les informations de la sessions que l'utilisateur à choisis dans la fenetre "FenFXML_SessionAchevee.java"
        sessionSelectionnee = MainApp.getMaSessionSelectionnee();
        Double tauxRemplissage = ((Double.parseDouble(Integer.toString(sessionSelectionnee.getNb_inscrits()))- Double.parseDouble(Integer.toString(sessionSelectionnee.getNb_absents()))) / Double.parseDouble(Integer.toString(sessionSelectionnee.getNb_places())) ) * 100;
        tfLibelle.setText(sessionSelectionnee.getLibFormation());
        tfInscrit.setText(Integer.toString(sessionSelectionnee.getNb_inscrits()) + " inscrit(s)");
        tfPlace.setText(Integer.toString(sessionSelectionnee.getNb_places()) + " places");
        tfIdentifiant.setText(Integer.toString(sessionSelectionnee.getId()));
        tfDate.setText(sessionSelectionnee.getDate_debut().toString());
        tfTaux.setText(Double.toString(tauxRemplissage) + " %");
        tfAbsent.setText(Integer.toString(sessionSelectionnee.getNb_absents()) + " absents");
        // Si le nombre d'basent > 0 alors on va mettre l'écriture en orange comme un warning
        if (sessionSelectionnee.getNb_absents() > 0)
        {
            tfAbsent.setStyle("-fx-text-fill: orange");
        }
        tfCout.setText(Double.toString(sessionSelectionnee.getCoutrevient()));
        
        // Je parcours l'ensemble des utilisateurs qui ont parcitipée à la session pour faire la somme des taux horaires de tous les membres
        // de la session.
        Double tauxHoraire = 0.0;
        for (Client unParticipant : lesParticipants)
        {
            tauxHoraire += unParticipant.getTaux_horaire();
        }
        
        // Si le cout de reviens de ma session est > tauxHoraire, c'est que je n'ai pas été rentable pour ma session, ma marge est donc
        // négatif et inversement.
        if (sessionSelectionnee.getCoutrevient() > tauxHoraire)
        {
            tfMarge.setStyle("-fx-text-fill: red");
            tfMarge.setText("NEGATIF");
        }
        else
        {
            tfMarge.setStyle("-fx-text-fill: green");
            tfMarge.setText("POSITIF");
        }
  
        /*
        ObservableList est une collection permettant de "stocker" tous les statuts afin de les renseigner dans les TableView
        car les statuts ne font pas partie de l'objet <Client>
        */
        // Initialise le TableView
        tcEmploye.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
        tcVille.setCellValueFactory(new PropertyValueFactory<Client, String>("ville"));
        tcTauxHoraire.setCellValueFactory(new PropertyValueFactory<Client, Double>("taux_horaire"));
        
        
        
        // Pour redimensionner les colonnes automatiquement
        tvParticipant.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    
    }
}
