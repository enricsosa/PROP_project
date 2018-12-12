package presentation;

import domaincontrollers.CtrlDomain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;

import domaincontrollers.CtrlDomain;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.*;
import java.util.ArrayList;

import java.util.Random;
import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import javafx.application.Application;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DataFormat;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class test2 extends Application {

    private CtrlDomain CD;
    private CtrlPresentacion CP;
    private Stage window;


    private static final String TAB_DRAG_KEY = "tab";
    private ObjectProperty<Tab> draggingTab;

    private static final String TAB_DRAG_BUT = "but";

    private final DataFormat buttonFormat = new DataFormat("com.example.myapp.formats.button");

    private Button draggingButton;


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

/*
        VBox vBox1 = new VBox();
        VBox vBox2 = new VBox();
        vBox1.setSpacing(2);
        vBox2.setSpacing(2);
        ScrollPane sp1 = new ScrollPane();
        ScrollPane sp2 = new ScrollPane();
        vBox1.prefHeightProperty().bind(sp1.heightProperty());
        vBox2.prefHeightProperty().bind(sp2.heightProperty());
        vBox1.prefWidthProperty().bind(sp1.widthProperty());
        vBox2.prefWidthProperty().bind(sp2.widthProperty());
        for (int i = 1 ; i <= 10; i++) {
            vBox1.getChildren().add(createButton("Button "+i));
            vBox2.getChildren().add(createButton("Button "+i));
        }
        addDropHandling(vBox1);
        addDropHandling(vBox2);

        sp1.setContent(vBox1);
        sp1.setPannable(true);
        sp2.setContent(vBox2);
        sp2.setPannable(true);
        GridPane gp = new GridPane();
        gp.getRowConstraints().add(new RowConstraints(100));
        gp.getRowConstraints().add(new RowConstraints(100));
        gp.add(sp1, 0, 0);
        gp.add(sp2, 1, 0);
        gp.setGridLinesVisible(true);
        Scene scene = new Scene(gp, 600, 600);
        primaryStage.setScene(scene);
        primaryStage.show();
*/
    }



    public static void main(String[] args)
    {
        launch(args);
    }

    private Button createButton(String text) {
        Button button = new Button(text);
        button.setOnDragDetected(e -> {
            Dragboard db = button.startDragAndDrop(TransferMode.MOVE);
            db.setDragView(button.snapshot(null, null));
            ClipboardContent cc = new ClipboardContent();
            cc.put(buttonFormat, "button");
            db.setContent(cc);
            draggingButton = button ;
        });
        button.setOnDragDone(e -> draggingButton = null);
        return button ;
    }

    private void addDropHandling(Pane pane) {
        pane.setOnDragOver(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)
                    && draggingButton != null
                    && draggingButton.getParent() != pane) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
        });

        pane.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasContent(buttonFormat)) {
                ((Pane)draggingButton.getParent()).getChildren().remove(draggingButton);
                pane.getChildren().add(draggingButton);
                e.setDropCompleted(true);
            }
        });
    }

}
