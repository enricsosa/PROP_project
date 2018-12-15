/**edit_new_horario*/

/**Imports*/

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

/**
 * edit_new_horario
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
public class edit_new_horario {

    /**Instacia de edit_new_horario.*/
    private static edit_new_horario snh;
    /**CtrlDomain que se usa.*/
    private CtrlDomain cd;
    /**edit*/
    private boolean edit;
    /**Nuevo nombre del horario.*/
    private String newName;

    /**Creadora de la clase edit_new_horario.*/
    private edit_new_horario() {
    }

    /**
     * Devuelve el atributo edit.
     * @return  edit.
     */
    public boolean getEdit() {
        return edit;
    }

    /**
     * Instanciadora de la clase edit_new_horario.
     * @return  Instancia de edit_new_horario.
     */
    public static edit_new_horario getInstance() {
        if (snh == null)
            snh = new edit_new_horario() {
            };
        return snh;
    }

    /**
     * Obtiene el atributo newName.
     * @return newName.
     */
    public String getNewName() {
        return newName;
    }

    /**Muestra la pantalla edit_new_horario.*/
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
