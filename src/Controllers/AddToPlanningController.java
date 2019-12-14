package Controllers;

import Film.Film;
import Salles.Salle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import sample.BDConnector;
import sample.Main;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ResourceBundle;


public class AddToPlanningController implements Initializable {

    public CheckBox knownFilm;
    public GridPane filmInfo;

    public TextField film;
    public TextField heureDeb;
    public TextField minDeb;
    public TextField heureFin;
    public TextField minFin;
    public TextField numSalle;

    public TextField rea;
    public ComboBox anneeS;
    public ComboBox moisS;
    public ComboBox jourS;
    public ComboBox<String> cat;
    public ComboBox anneeP;
    public ComboBox moisP;
    public ComboBox jourP;
    public TextArea desc;

    public ComboBox anneeProj;
    public ComboBox moisProj;
    public ComboBox jourProj;

    public GridPane infoS;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LocalDateTime now = LocalDateTime.now();

        anneeProj.getItems().add(now.getYear());
        anneeProj.getItems().add(now.getYear()+1);

        for(int i =  now.getYear(); i >= 1970; i--){
            anneeP.getItems().add(i);
            anneeS.getItems().add(i);
        }

        for(int i = 1; i < 13; i++){
            moisP.getItems().add(i);
            moisS.getItems().add(i);
            moisProj.getItems().add(i);
        }

        for(int i = 1; i <= 31; i++){
            jourP.getItems().add(i);
            jourS.getItems().add(i);
            jourProj.getItems().add(i);
        }

        if(knownFilm.isSelected()){
            filmInfo.setDisable(false);
        }else {
            filmInfo.setDisable(true);
        }
    }

    public void showFilm(){
        if(knownFilm.isSelected()){
            filmInfo.setDisable(false);
        }else {
            filmInfo.setDisable(true);
        }
    }

    public void back(ActionEvent e){
        Main.changeWindow(e, "../xml/FilmsM.fxml");
    }

    public void addSeance(ActionEvent e){
        boolean done = false;

        String titre = film.getText();
        String realisateur = rea.getText();
        String date_sortie = anneeS.getSelectionModel().getSelectedItem()+"-"
                +moisS.getSelectionModel().getSelectedItem()+"-"
                +jourS.getSelectionModel().getSelectedItem();
        String categorie = cat.getSelectionModel().getSelectedItem();
        String date_publi = anneeP.getSelectionModel().getSelectedItem()+"-"+
                moisP.getSelectionModel().getSelectedItem()+"-"
                +jourP.getSelectionModel().getSelectedItem();
        String description = desc.getText();

        String date = anneeProj.getSelectionModel().getSelectedItem()+"-"+
                moisProj.getSelectionModel().getSelectedItem()+"-"+jourProj.getSelectionModel().getSelectedItem();
        String heure_deb = heureDeb.getText()+":"+minDeb.getText()+":00";
        String heure_fin = heureFin.getText()+":"+minFin.getText()+":00";

        Salle s = null;
        if(!numSalle.getText().equals(""))
            s = Controller.lsl.getSalle(Integer.parseInt(numSalle.getText()));

        if(!titre.equals("") && !date.equals("--") && !heure_deb.equals("::") && !heure_fin.equals("::") && s != null){
            try {
                BDConnector.connect();

                String q;
                ResultSet res;
                if(knownFilm.isSelected()) {
                    if(!realisateur.equals("") && !date_sortie.equals("--") && !categorie.equals("") && !date_publi.equals("--") && !description.equals("")){
                        q = "SELECT * FROM films Where titre='" + titre + "'";
                        res = BDConnector.st.executeQuery(q);

                        if (!res.next()) {
                            Controller.lf.Ajout(new Film(titre, realisateur, date_sortie, categorie, date_publi, description));
                        }
                    }
                }

                if(Controller.lf.getfilm(titre) != null) {
                    Controller.ls.Ajouter(date,
                            Controller.lf.getfilm(titre),
                            s,
                            heure_deb,
                            heure_fin,
                            0);
                    done = true;
                }
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }

        }

        if(done){
            Main.changeWindow(e, "../xml/FilmsM.fxml");
        }
        else {
            Label wrongCred = new Label("Iformation invalide!");
            wrongCred.getStyleClass().add("errorMsg");
            GridPane.setConstraints(wrongCred, 0, 7);
            infoS.getChildren().add(wrongCred);
        }
    }
}
