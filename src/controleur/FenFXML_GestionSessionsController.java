package controleur;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;
import modele.Client;
import modele.GestionSqlSessions;
import modele.Session;

public class FenFXML_GestionSessionsController implements Initializable {

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
        ObservableList<Client> lesClients = GestionSqlSessions.getLesParticipants(tvSessions.getSelectionModel().getSelectedItem().getId());
        try
        {
            //GENEREATION DU PDF
            Document document = new Document();
            OutputStream outputStream = new FileOutputStream(new File("C:\\Users/E/Downloads/ListeParticipants_" + tvSessions.getSelectionModel().getSelectedItem().getLibFormation() + "_" + tvSessions.getSelectionModel().getSelectedItem().getDate_debut() + ".pdf"));
            PdfWriter.getInstance(document, outputStream);
            document.open();

            //TITRE
            Paragraph Titre = new Paragraph("Liste des participants à la session " + tvSessions.getSelectionModel().getSelectedItem().getLibFormation() + " du " + tvSessions.getSelectionModel().getSelectedItem().getDate_debut(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, Font.BOLD, BaseColor.BLACK));
            Titre.setSpacingAfter(25);
            Titre.setSpacingBefore(25);
            Titre.setAlignment(Element.ALIGN_CENTER);
            document.add(Titre);
            
            //TABLE
            PdfPTable pdfPTable = new PdfPTable(3);
            pdfPTable.setWidthPercentage(100);
            lesClients.forEach((client) -> { 
                pdfPTable.addCell(new PdfPCell(new Paragraph(client.getNom())));
                pdfPTable.addCell(new PdfPCell(new Paragraph(client.getAdresse()+", "+client.getCp()+" "+client.getVille())));
                pdfPTable.addCell(new PdfPCell(new Paragraph(client.getEmail())));
            });
            
            document.add(pdfPTable);
            
            document.close();
            outputStream.close();
            
            //FENETRE D'INFORMATION SUCCES
            System.out.println("Pdf created successfully.");
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Retour succès génération PDF");
            alert.setHeaderText(null);
            alert.setContentText("Pdf généré avec succès.");
            alert.showAndWait();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            
            //FENETRE D'INFORMATION ECHEC
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Retour échec génération PDF");
            alert.setHeaderText(null);
            alert.setContentText("Erreur lors de la génération du PDF");

            Exception ex = new FileNotFoundException("Impossible de trouver le fichier (mauvais emplacement)");

            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);
            alert.showAndWait();
        }
    }
}
