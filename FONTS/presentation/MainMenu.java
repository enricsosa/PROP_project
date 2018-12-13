package presentation;

import presentation.FXMLControllers.*;
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
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class MainMenu extends Application {

    private CtrlDomain CD;
    private CtrlPresentacion CP;
    private Stage window;


    @Override
    public void start(Stage primaryStage) throws Exception {

        CP = CtrlPresentacion.getInstance();
        CD = CP.getCD();
        window = primaryStage;
        window.setTitle("Generador de Horarios");
        SceneController sc = SceneController.getInstance();
        sc.addScene("prova1", FXMLLoader.load(getClass().getResource("FXML/prova1.fxml")));
        sc.setStyleSheet("prova1", "presentation/CSS/NightScheme.css");
        sc.activate("prova1");

        window.setScene(sc.current());
        window.show();
    }



    public static void main(String[] args)
    {
        launch(args);
    }
}
