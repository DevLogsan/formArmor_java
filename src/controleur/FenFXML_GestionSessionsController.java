package controleur;

import java.io.IOException;
import java.sql.Date;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modele.GestionSqlSessions;
import modele.Session;

public class FenFXML_GestionSessionsController implements Initializable {

    private Stage secondaryStage;
    
    @FXML
    private TableView<Session> tvSessions;
    @FXML
    Button btnListeParticipants;
    @FXML
    private TableColumn<Session, Integer> tcID;
    @FXML
    private TableColumn<Session, String> tcLibelle;
    @FXML
    private TableColumn<Session, Date> tcDateDebut;
    @FXML
    private TableColumn<Session, Integer> tcNbPlaces;
    @FXML
    private TableColumn<Session, Integer> tcNbInscrits;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Session> lesSessions = GestionSqlSessions.getLesSessionsAVenir();
        tvSessions.setItems(lesSessions);
        
        tcID.setCellValueFactory(new PropertyValueFactory<Session, Integer>("id"));
        tcLibelle.setCellValueFactory(new PropertyValueFactory<Session, String>("libFormation"));
        tcDateDebut.setCellValueFactory(new PropertyValueFactory<Session, Date>("date_debut"));
        tcNbPlaces.setCellValueFactory(new PropertyValueFactory<Session, Integer>("nb_places"));
        tcNbInscrits.setCellValueFactory(new PropertyValueFactory<Session, Integer>("nb_inscrits"));
        
        tvSessions.getSelectionModel().selectedItemProperty().addListener((ObservableValue<? extends Session> observable, Session oldValue, Session newValue) -> {
            if (newValue == null)
            {
                btnListeParticipants.setVisible(false);
            }
            else
            {
                btnListeParticipants.setVisible(true);
            }
        });
    }
    
    @FXML
    public void handleListeParticipants()
    {
        MainApp.setMaSessionGestionSelectionnee(tvSessions.getSelectionModel().getSelectedItem());
        try
        {
            secondaryStage = new Stage();
            secondaryStage.setTitle("Gestion des sessions");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_ParticipantsSession.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            secondaryStage.setScene(scene);
            secondaryStage.show();
        }
        catch (IOException e)
        {
            System.out.println("Erreur chargement fenetre Participants Session : " + e.getMessage());
        }
    }
}
