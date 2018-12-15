/**SceneController*/

/**Imports*/

package presentation;

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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;
import java.util.HashMap;

import presentation.FXMLControllers.*;
import domaincontrollers.CtrlDomain;

/**
 * SceneController
 * @author  Enric Sosa
 * @see     Scene
 * @see     VBox
 * @see     HashMap
 */
public class SceneController {

    /**Instancia de SceneController.*/
    private static SceneController sceneController;
    /**Mapa que contiene las escenas.*/
    private HashMap<String, Pane> sceneMap = new HashMap<>();
    /**Venatana del programa.*/
    private Scene main = new Scene(new VBox(), 800, 550);

    /**Constructora de la clase SceneController.*/
    private SceneController() {
        this.main = main;
    }

    /**
     * Instanciadora de la clase SceneController.
     * @return  Instancia de SceneController.
     */
    public static SceneController getInstance() {
        if (sceneController == null)
            sceneController = new SceneController() {
            };
        return sceneController;
    }

    /**
     * Añade una Escena
     * @param name  nombre de la escena.
     * @param pane  pane de la escena.
     */
    public void addScene(String name, Pane pane) {
        sceneMap.put(name, pane);
    }

    /**
     * Elimina una Escena
     * @param name  nombre de la escena.
     * @param pane  pane de la escena.
     */
    public void rmScene(String name, Pane pane) {
        sceneMap.remove(name, pane);
    }

    /**
     * Activa una escena.
     * @param name  nombre de la escena que se activa.
     */
    public void activate(String name) {
        main.setRoot(sceneMap.get(name));
    }

    /**
     * Obtiene el pane de una escena.
     * @param name  nombre de la escena.
     * @return      pane de la escena.
     */
    public Pane getPane(String name) {
        return sceneMap.get(name);
    }

    /**
     * Asigna un styleSheet a una escena.
     * @param name      nombre de la escena.
     * @param urlCSS    dirección del styleSheet.
     */
    public void setStyleSheet(String name, String urlCSS) {
        Pane p = sceneMap.get(name);
        p.getStylesheets().add(urlCSS);
        replacePane(name, p);
    }

    /**
     * Reemplaza el pane de una escena
     * @param name  nombre de la escena.
     * @param pane  pane que se asigna a escena.
     */
    public void replacePane(String name, Pane pane) {
        sceneMap.replace(name, pane);
    }

    /**
     * Devuelve la escena que está abierta.
     * @return  escena abierta.
     */
    public Scene current() {
        return main;
    }
}
