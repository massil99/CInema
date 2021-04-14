package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import sample.Strategy.MySQL_Connector;
import sample.Main;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class StatController implements Initializable {
    public Button back;
    public VBox listeF;

    /**
     * M�thode initialize
     * Chargement des statistiques pour tous les films �tant d�j� projet�s.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(LoginScreenController.u.getType().equals("Comptable"))
            back.setDisable(true);

        try {
            Connection connection =  MySQL_Connector.connect();
            Statement statement = connection.createStatement();
            String q = "SELECT films.id_film, count(id_reservation), sum(tarif) FROM seances, films, reservation WHERE" +
                    " films.id_film=seances.id_film and reservation.id_seance=seances.id_seance group by(films.id_film) ORDER by(count(id_reservation))";

            ResultSet res = statement.executeQuery(q);
            while (res.next()){
                GridPane infos = new GridPane();
                infos.setHgap(30);
                infos.getStyleClass().add("item");

                Label t = new Label("Titre");
                t.getStyleClass().add("gray-text");
                GridPane.setConstraints(t, 0, 0);
                Label titre = new Label(Controller.lf.getfilmById(res.getInt(1)).getTitre());
                GridPane.setConstraints(titre, 1, 0);

                Label r = new Label("Totale des reservation ");
                r.getStyleClass().add("gray-text");
                GridPane.setConstraints(r, 0, 1);
                Label reservation = new Label(res.getString(2));
                GridPane.setConstraints(reservation, 1, 1);

                Label s = new Label("Revenus totale");
                s.getStyleClass().add("gray-text");
                GridPane.setConstraints(s, 0, 2);
                float rev = res.getFloat(3);
                Label revenus = new Label(String.format("%.2f",rev));
                GridPane.setConstraints(revenus, 1, 2);

                infos.getChildren().addAll(t, titre, r, reservation, s, revenus);
                listeF.getChildren().add(infos);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * M�thode back
     * Retour vers l'interface FilmsM.
     * @param e
     */
    public void back(ActionEvent e){
        Main.changeWindow(e, "../xml/FilmsM.fxml");
    }
}
