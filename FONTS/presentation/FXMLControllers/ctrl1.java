/**crtl1*/

/**Imports*/

package presentation.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.fxml.FXMLLoader;
import java.util.ArrayList;

import presentation.*;
import domaincontrollers.CtrlDomain;

/**
 * ctrl1
 * @author  Enric Sosa
 * @see     FXML
 * @see     Parent
 * @see     Button
 * @see     BorderPane
 * @see     FXMLLoader
 * @see     ArrayList
 * @see     CtrlDomain
 * @see     CtrlPresentacion
 * @see     SceneController
 */
public class ctrl1 {

    /**ctrl2 que se usa.*/
    private ctrl2 c2;
    /**escogerHorario que se usa.*/
    private escogerHorario eH;
    /**CtrlPresentacion que lo gestiona.*/
    private CtrlPresentacion cp;
    /**CtrlDomain que se usa.*/
    private CtrlDomain cd;

    /**Constructora de la clase ctrl1*/
    public ctrl1() {
    }

    private VBox leftPaneEscenarios() throws Exception {
        VBox updatedPane = new VBox();
        CtrlPresentacion CP = CtrlPresentacion.getInstance();

        ArrayList<String> escenarios = CP.escenarios();
        for (String s : escenarios) {
            Button b = new Button();
            b.setText(s);
            b.setId("esc-btn");
            b.setOnAction(event -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(ctrl2.class.getResource(""));
                c2.escenarioSeleccionado(b.getText());
            });
            updatedPane.getChildren().addAll(b);
        }

        return updatedPane;
    }

    private VBox leftPaneHorarios() throws Exception {
        VBox updatedPane = new VBox();
        updatedPane.setId("leftVB");
        CtrlPresentacion CP = CtrlPresentacion.getInstance();

        ArrayList<String> horarios = CP.horarios();
        for (String s : horarios) {
            Button b = new Button();
            b.setText(s);
            b.setId("esc-btn");
            b.setOnAction(event -> {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(escogerHorario.class.getResource(""));
                eH.horarioSeleccionado(b.getText());
            });
            updatedPane.getChildren().addAll(b);
        }

        return updatedPane;
    }

    public void setEscogEsc() throws Exception {
        SceneController sc = SceneController.getInstance();

        BorderPane bP = (BorderPane)sc.getPane("prova2");
        VBox lP = leftPaneEscenarios();
        bP.setLeft(lP);
        sc.replacePane("prova2", bP);

        sc.activate("prova2");
    }

    public void setEscogHor() throws Exception {
        SceneController sc = SceneController.getInstance();

        BorderPane bP = (BorderPane)sc.getPane("escogerHorario");
        VBox lP = leftPaneHorarios();
        bP.setLeft(lP);
        sc.replacePane("escogerHorario", bP);

        sc.activate("escogerHorario");
    }

    public void outButtonClicked() throws Exception {
        System.exit(0);
    }

    /**
     * Inicializa ctrl1.
     * @throws Exception    Ha habido un error en la inicialización.
     */
    @FXML
    private void initialize() throws Exception {
        SceneController sc = SceneController.getInstance();
        cp = CtrlPresentacion.getInstance();
        cd = CtrlDomain.getInstance();
        try {
            //Escoger escenario
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/prova2.fxml"));
            Parent root = loader.load();
            c2 = loader.getController();
            sc.addScene("prova2", (BorderPane)root);
            sc.setStyleSheet("prova2", "presentation/CSS/escogerEscenarios.css");

            //Escoger horario
            loader = new FXMLLoader(getClass().getResource("../FXML/escogerHorario.fxml"));
            root = loader.load();
            eH = loader.getController();
            sc.addScene("escogerHorario", (BorderPane)root);
            sc.setStyleSheet("escogerHorario", "presentation/CSS/escogerHorarios.css");

        } catch (Exception e) {
            System.out.println("ERROR EN LOS LOADERS INICIALES");
            System.out.println(e.toString());
        }
    }
}
