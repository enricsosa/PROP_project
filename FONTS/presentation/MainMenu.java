package presentation;

import domaincontrollers.CtrlDomain;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

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
