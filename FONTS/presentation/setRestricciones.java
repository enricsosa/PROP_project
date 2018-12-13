package presentation;

import presentation.FXMLControllers.*;
import domain.Correquisito;
import domaincontrollers.CtrlDomain;
import javafx.application.Application;
import javafx.application.Platform;
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

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class setRestricciones {

    private static setRestricciones sR;
    private CtrlRestricciones cR;
    private CtrlDomain cd;
    private String horarioStr;
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();

    /**Constructoras*/

    private setRestricciones() {
    }

    public static setRestricciones getInstance() {
        if (sR == null)
            sR = new setRestricciones() {
            };
        return sR;
    }

    public void display(String escenario) {
        cd = CtrlPresentacion.getInstance().getCD();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/confirmRestr.fxml"));
        cR = loader.getController();
        Parent root = null;
        try {
            root = loader.load();
            VBox vb = (VBox)root;

            //Label Escenario seleccionado
            Label escSel = (Label) vb.lookup("#escSel");
            escSel.setText(escSel.getText() + escenario);

            //TextField dar nombre al Horario
            HBox nomHorario = (HBox) vb.lookup("#nomHorario");
            TextField nomH = (TextField)nomHorario.lookup("#nomH");
            nomH.setText(escenario);

            //Radiobuttons

            //Boton Cancelar
            HBox botButtons = (HBox) vb.lookup("#botButtons");
            Button cancelB = (Button) botButtons.lookup("#cancelB");
            cancelB.setOnAction(event -> {
                window.close();
            });

            //Boton Confirmar
            Button confirmB = (Button) botButtons.lookup("#confirmB");
            confirmB.setOnAction(event -> {

                try {
                    this.horarioStr = cd.generarHorario(nomH.getText());
                    cd.writeHorario(horarioStr, nomH.getText());
                    horario = cd.escaneaHorario(nomH.getText());
                } catch (Exception e) {
                    System.out.println("ERROR: CARGA DEL HORARIO FALLIDA");
                }

                showHorario sR = showHorario.getInstance();
                sR.display(nomH.getText());
                window.close();
            });

            window.setScene(new Scene(vb));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

}
