package presentation;

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

public class NewName {

    private static NewName nn;
    private CtrlDomain cd;
    private String nameH;

    private NewName() {
    }

    public String getNameH() {
        return nameH;
    }

    public static NewName getInstance() {
        if (nn == null)
            nn = new NewName() {
            };
        return nn;
    }

    public void display() {
        cd = CtrlPresentacion.getInstance().getCD();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/h_new_name.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            VBox lay = (VBox)root;

            //TextField
            TextField textField = (TextField)lay.lookup("#textField");

            //confirmBtn
            Button confirmBtn = (Button)lay.lookup("#confirmBtn");
            confirmBtn.setOnAction(event -> {
                nameH = textField.getText();
                window.close();
            });

            window.setScene(new Scene(lay));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}
