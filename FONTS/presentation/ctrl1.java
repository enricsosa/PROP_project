package presentation;

import domaincontrollers.CtrlDomain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;

public class ctrl1 {

    public ctrl2 c2;

    public ctrl1() {
    }

    private VBox leftPane() throws Exception {
        VBox updatedPane = new VBox();
        CtrlPresentacion CP = CtrlPresentacion.getInstance();

        ArrayList<String> escenarios = CP.escenarios();
        for (String s : escenarios) {
            Button b = new Button();
            b.setText(s);
            b.setId("esc-btn");
            b.setOnAction(event -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ctrl2.class.getResource(""));
                c2.escenarioSeleccionado(b.getText());
            });
            updatedPane.getChildren().addAll(b);
        }

        return updatedPane;
    }

    public void setEscogEsc() throws Exception {
        SceneController sc = SceneController.getInstance();

        BorderPane bP = (BorderPane)sc.getPane("prova2");
        VBox lP = leftPane();
        bP.setLeft(lP);
        sc.replacePane("prova2", bP);

        sc.activate("prova2");
    }

    public void outButtonClicked() {
        System.exit(0);
    }

    @FXML
    private void initialize() throws Exception{
        SceneController sc = SceneController.getInstance();
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("prova2.fxml"));
            Parent root = loader.load();
            c2 = loader.getController();
            sc.addScene("prova2", (BorderPane)root);
            sc.setStyleSheet("prova2", "presentation/CSS/escogerEscenarios.css");

        } catch (Exception e) {
            System.out.println("CAGADA");
        }
    }
}
