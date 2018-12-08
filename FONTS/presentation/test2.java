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
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import java.util.ArrayList;

public class test2 extends Application {

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
        sc.addScene("prova1", FXMLLoader.load(getClass().getResource("prova1.fxml")));
        sc.setStyleSheet("prova1", "presentation/CSS/NightScheme.css");
        sc.activate("prova1");



        window.setScene(sc.current());
        window.show();
    }

}
