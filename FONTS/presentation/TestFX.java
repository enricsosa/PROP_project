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

public class TestFX extends Application {
    @FXML
    public Button escogEscBtn;

    @FXML
    public Button atras_MMenu;

    @FXML
    public BorderPane escogEscPane;

    @FXML
    public Label escenario_label;


    @FXML
    public Button consultHorarios;

    private CtrlDomain CD;
    private CtrlPresentacion CP;


    private SceneController scCtrl;



    private Stage window;

    private Scene escogEsc;

    @Override
    public void start(Stage primaryStage) throws Exception {
        CP = CtrlPresentacion.getInstance();
        CD = CP.getCD();


        window = primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("TestFX.fxml"));
        root.getStylesheets().add("presentation/CSS/NightScheme.css");
        window.setTitle("Generador de Horarios");
        window.setScene(new Scene(root, 800, 550));
        window.show();
    }

    public void escenarioSeleccionado(Event event) {
        System.out.println(event);
        Button a = (Button) event.getSource();
        BorderPane root = new BorderPane();
        try {
            root = FXMLLoader.load(getClass().getResource("escogerEscenarios.fxml"));
        } catch (Exception e) {

        }
        a.getScene().setRoot(root);
        System.out.println(a);
        System.out.println(escenario_label);
        System.out.println(atras_MMenu);
        System.out.println(escogEscBtn);
        System.out.println(consultHorarios);
    }

    public VBox leftPane() throws Exception {
        VBox updatedPane = new VBox();
        CP = CtrlPresentacion.getInstance();

        ArrayList<String> escenarios = CP.escenarios();
        for (String s : escenarios) {
            Button b = new Button();
            b.setText(s);
            b.setId("esc-btn");
            b.setOnAction(event -> escenarioSeleccionado(event) );
            updatedPane.getChildren().addAll(b);
        }

        return updatedPane;
    }

    public void setMainMenu() throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("TestFX.fxml"));
        root.getStylesheets().add("presentation/CSS/NightScheme.css");
        atras_MMenu.getScene().setRoot(root);
    }

    public void setEscogEsc() throws Exception {
        BorderPane root = FXMLLoader.load(getClass().getResource("escogerEscenarios.fxml"));
        root.getStylesheets().add("presentation/CSS/escogerEscenarios.css");

        VBox lP = leftPane();
        root.setLeft(lP);

        escogEscBtn.getScene().setRoot(root);
    }

    public void outButtonClicked() {
        System.exit(0);
    }
}