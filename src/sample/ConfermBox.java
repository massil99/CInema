package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfermBox {

    private static boolean answer;

    public static boolean display(String title, String message){
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);

        Label label = new Label(message);
        label.setStyle("-fx-font-weight: 800");

        Button accept = new Button("Oui");
        accept.setOnAction(e-> {
            answer = true;
            window.close();
        });

        Button reject = new Button("Non");
        reject.setOnAction(e-> {
            answer = false;
            window.close();
        });

        HBox btns = new HBox(20);
        btns.getChildren().addAll(accept, reject);
        btns.setAlignment(Pos.CENTER);

        VBox layout = new VBox(50);
        layout.getStyleClass().add("pane");
        layout.getChildren().addAll(label, btns);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        scene.getStylesheets().add("style/Style.css");
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
