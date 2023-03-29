/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleur;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.collections.ObservableList;
import modele.Session;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import modele.GestionSql_Rentabilite;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Lucas
 */
public class FenFXML_SessionAcheveeControleur implements Initializable 
{
    private Stage primaryStage;
    
    @FXML
    private TableView<Session> tvSession;
    @FXML
    private TableColumn<Session, Integer> tcId;
    @FXML
    private TableColumn<Session, String> tcFormation;
    @FXML
    private TableColumn<Session, Date> tcDebut;
    @FXML
    private TableColumn<Session, Integer> tcPlace;
    @FXML
    private TableColumn<Session, Integer> tcInscrit;
    @FXML
    private TextField tfDate;
    @FXML
    private Button btnDetails;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        ObservableList<Session> lesSessions = GestionSql_Rentabilite.getLesSessionsTerminee();
        tvSession.setItems(lesSessions);
        
        tcId.setCellValueFactory(new PropertyValueFactory<Session, Integer>("id"));
        tcFormation.setCellValueFactory(new PropertyValueFactory<Session, String>("libFormation"));
        tcDebut.setCellValueFactory(new PropertyValueFactory<Session, Date>("date_debut"));
        tcPlace.setCellValueFactory(new PropertyValueFactory<Session, Integer>("nb_places"));
        tcInscrit.setCellValueFactory(new PropertyValueFactory<Session, Integer>("nb_inscrits"));
        
        tvSession.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        btnDetails.setDisable(true);
    }
    
    @FXML
    public void handleBtnActif()
    {
        // Si l'utilisateur n'a pas choisis de session achevée alors on désactive le bouton 'Détails'
        if (tvSession.getSelectionModel().isEmpty())
        {
            btnDetails.setDisable(true);
        }
        // Si l'utilisateur à choisis quelque chose dans la TableView, on réactive le bouton 'Détails'
        else
        {
            btnDetails.setDisable(false);
        }
    }
    
    @FXML
    public void handleDetails()
    {
        // Si j'appuie sur le bouton "Détails", je vais enregistrer la session qui a été selectionner par l'utilisateur
        Session maSessionAchevee = tvSession.getSelectionModel().getSelectedItem();
        MainApp.setMaSessionSelectionnee(maSessionAchevee);
        // J'essaie d'ouvrir la page "FenFXML_DetailSession.fxml"
        try
        {
            primaryStage = new Stage();
            primaryStage.setTitle("Détail de la session");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_DetailSession.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
                
            primaryStage.setScene(scene);
            MainApp.setMaSessionSelectionnee(tvSession.getSelectionModel().getSelectedItem());
            primaryStage.show();
        }
        // Si j'ai une erreur dans le "try", je préviens l'utilisateur du type d'erreur ainsi que dans la console.
        catch (IOException e)
        {
            Alert erreur = new Alert(AlertType.ERROR);
            erreur.setTitle("Chargement impossible");
            erreur.setHeaderText("Erreur chargement fenetre");
            erreur.setContentText("La fenêtre 'Détail de la session' n'a pas été chargée correctement \nMessage d'erreur : " + e.getMessage() + "\nCause de l'erreur : " + e.getCause());
            
            erreur.showAndWait();
            
            System.out.println("La fenêtre 'Détail de la session' n'a pas été chargée correctement \nMessage d'erreur : " + e.getMessage() + "\nCause de l'erreur : " + e.getCause());
        }
    }
}
