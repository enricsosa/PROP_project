/**NewName*/

/**Import*/

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
import domaincontrollers.CtrlDomain;

/**
 * NewName
 * @author  Enric Sosa
 * @see     Scene
 * @see     Parent
 * @see     Button
 * @see     VBox
 * @see     Modality
 * @see     Stage
 * @see     FXMLLoader
 * @see     CtrlDomain
 */
public class NewName {

    /**String que contiene el nuevo nombre*/
    private static NewName nn;
    /**CtrlDomain que usa.*/
    private CtrlDomain cd;
    /**Nombre del horario.*/
    private String nameH;

    /**Constructora de la clase NewName.*/
    private NewName() {
    }

    /**
     * Obtiene el atributo nameH.
     * @return  nameH*/
    public String getNameH() {
        return nameH;
    }

    /**
     * Instanciadora de la clase NewName.
     * @return  Instancia de NewName.
     */
    public static NewName getInstance() {
        if (nn == null)
            nn = new NewName() {
            };
        return nn;
    }

    /**Muestra NewName.*/
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
