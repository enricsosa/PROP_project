package presentation;

import presentation.FXMLControllers.*;
import domain.Correquisito;
import domaincontrollers.CtrlDomain;
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

public class edit_new_horario {

    private static edit_new_horario snh;
    private CtrlDomain cd;
    private boolean edit;
    private String newName;

    private edit_new_horario() {
    }

    public boolean getEdit() {
        return edit;
    }

    public static edit_new_horario getInstance() {
        if (snh == null)
            snh = new edit_new_horario() {
            };
        return snh;
    }

    public String getNewName() {
        return newName;
    }

    public void display() {
        cd = CtrlPresentacion.getInstance().getCD();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/edit_new_horario.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            VBox lay = (VBox)root;

            //Btn edit
            HBox botButtons = (HBox)lay.lookup("#botButtons");
            Button editBtn = (Button)botButtons.lookup("#editBtn");
            editBtn.setOnAction(event -> {
                edit = true;
                window.close();
            });

            //newBtn
            Button newBtn = (Button)botButtons.lookup("#newBtn");
            newBtn.setOnAction(event -> {
                edit = false;
                NewName nn = NewName.getInstance();
                nn.display();
                newName = nn.getNameH();
                window.close();
            });

            window.setScene(new Scene(lay));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
