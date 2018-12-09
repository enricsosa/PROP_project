package presentation;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

public class CtrlFX extends Application {

    private Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("caca");
        window = primaryStage;
        window.setTitle("Caca");
        window.show();
    }
}
