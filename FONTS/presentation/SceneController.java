package presentation;

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
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;
import java.util.HashMap;


public class SceneController {
    private static SceneController sceneController;
    private HashMap<String, Pane> sceneMap = new HashMap<>();
    private Scene main = new Scene(new VBox(), 800, 550);

    private SceneController() {
        this.main = main;
    }

    public static SceneController getInstance() {
        if (sceneController == null)
            sceneController = new SceneController() {
            };
        return sceneController;
    }

    public void addScene(String name, Pane pane) {
        sceneMap.put(name, pane);
    }

    public void rmScene(String name, Pane pane) {
        sceneMap.remove(name, pane);
    }

    public void activate(String name) {
        main.setRoot(sceneMap.get(name));
    }

    public Pane getPane(String name) {
        return sceneMap.get(name);
    }

    public void setStyleSheet(String name, String urlCSS) {
        Pane p = sceneMap.get(name);
        p.getStylesheets().add(urlCSS);
        replacePane(name, p);
    }

    public void replacePane(String name, Pane pane) {
        sceneMap.replace(name, pane);
    }

    public Scene current() {
        return main;
    }
}
