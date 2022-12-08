package controleur;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FenFXML_MenuController implements Initializable {

    private Stage primaryStage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void handleInscription()
    {
        try
        {
            primaryStage = new Stage();
            primaryStage.setTitle("Gestion des inscriptions aux sessions de formations");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_Inscription.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            System.out.println("Erreur chargement fenetre Inscription : " + e.getMessage());
        }
    }
    
    public void handleGestionSessions()
    {
        try
        {
            primaryStage = new Stage();
            primaryStage.setTitle("Gestion des sessions");
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/vue/FenFXML_GestionSessions.fxml"));
            AnchorPane rootLayout = (AnchorPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (IOException e)
        {
            System.out.println("Erreur chargement fenetre Gestion sessions : " + e.getMessage());
        }
    }
}
