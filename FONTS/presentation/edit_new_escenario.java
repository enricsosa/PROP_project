package presentation;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import presentation.FXMLControllers.*;
import domain.Correquisito;
import domaincontrollers.CtrlDomain;

public class edit_new_escenario {

    private static edit_new_escenario enh;

    private edit_new_escenario() {
    }

    public static edit_new_escenario getInstance() {
        if (enh == null)
            enh = new edit_new_escenario() {
            };
        return enh;
    }

    public void display(String escenario, ArrayList<String> pE, HashMap<String, ArrayList<Object>> asigs,
                        HashMap<String, ArrayList<Object>> aulas, HashMap<String, ArrayList<Object>> restrs) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/edit_new_escenario.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            VBox lay = (VBox)root;

            //Label
            Label info = (Label)lay.lookup("#info");
            info.setText("Has seleccionado el escenario " + escenario + ". ¿Qué hacer?");

            //Btn cancelar
            HBox botButtons = (HBox)lay.lookup("#botButtons");
            Button cancelBtn = (Button)botButtons.lookup("#cancelBtn");
            cancelBtn.setOnAction(actionEvent -> {
                window.close();
            });

            //Btn editar
            Button editBtn = (Button)botButtons.lookup("#editBtn");
            editBtn.setOnAction(actionEvent -> {
                EditEscenario edEsc = EditEscenario.getInstance();
                edEsc.display(escenario, pE, asigs, aulas, restrs);
                window.close();
            });

            //Btn newEscenario
            Button newBtn = (Button)botButtons.lookup("#newBtn");
            newBtn.setOnAction(actionEvent -> {
                window.close();
            });

            window.setScene(new Scene(lay));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
