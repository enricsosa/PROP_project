package presentation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.lang.reflect.Array;

import presentation.FXMLControllers.*;
import domain.Correquisito;
import domaincontrollers.CtrlDomain;

public class EditEscenario {

    private static EditEscenario edEsc;
    private Stage window;

    private CtrlEditAulas ctrlEdAulas;
    private FXMLLoader loaderEdAulas;
    private Parent parentEdAulas;

    private CtrlEditPlanEstudios ctrlEdPlanEstudios;
    private FXMLLoader loaderEdPlanEstudios;
    private Parent parentEdPlanEstudios;

    private CtrlEditAsignaturas ctrlEdAsignaturas;
    private FXMLLoader loaderEdAsignaturas;
    private Parent parentEdAsignaturas;

    private CtrlEditRfranjaTrabajo ctrlEdRfranjaTrabajo; //FT
    private FXMLLoader loaderEdRfranjaTrabajo;
    private Parent parentEdRfranjaTrabajo;
    private CtrlEditRdiaLibre ctrlEdRdiaLibre; //DL
    private FXMLLoader loaderEdRdiaLibre;
    private Parent parentEdRdiaLibre;
    private CtrlEditRnivelHora ctrlEdRnivelHora;  //NH
    private FXMLLoader loaderEdRnivelHora;
    private Parent parentEdRnivelHora;
    private CtrlEditRcorrequisitos ctrlEdRcorrequisitos;  //CO
    private FXMLLoader loaderEdRcorrequisitos;
    private Parent parentEdRcorrequisitos;
    private CtrlEditRprerrequisitos ctrlEdRprerrequisitos; //PRE
    private FXMLLoader loaderEdRprerrequisitos;
    private Parent parentEdRprerrequisitos;
    private CtrlEditRfranjaAsignatura ctrlEdRfranjaAsignatura; //FA
    private FXMLLoader loaderEdRfranjaAsignatura;
    private Parent parentEdRfranjaAsignatura;
    private CtrlEditRfranjaNivel ctrlEdRfranjaNivel; //FN
    private FXMLLoader loaderEdRfranjaNivel;
    private Parent parentEdRfranjaNivel;

    private ArrayList<String> planEstudios;
    private HashMap<String, ArrayList<Object>> asignaturas;
    private HashMap<String, ArrayList<Object>> aulas;
    private HashMap<String, ArrayList<Object>> restricciones;

    private ArrayList<String> planEstudiosFinal;
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    private HashMap<String, ArrayList<Object>> aulasFinal;
    private HashMap<String, ArrayList<Object>> restriccionesFinal;

    private boolean peChanged;
    private boolean asChanged;
    private boolean auChanged;
    private boolean reChanged;

    public ArrayList<String> getPlanEstudiosFinal() {
        return planEstudiosFinal;
    }

    public HashMap<String, ArrayList<Object>> getAulasFinal() {
        return aulasFinal;
    }

    public HashMap<String, ArrayList<Object>> getAsignaturasFinal() {
        return asignaturasFinal;
    }

    public HashMap<String, ArrayList<Object>> getRestriccionesFinal() {
        return restriccionesFinal;
    }

    public void setAulasFinal(HashMap<String, ArrayList<Object>> newAulas) {
        aulasFinal = newAulas;
        BorderPane lay = (BorderPane)window.getScene().getRoot();
        ListView<String> left = (ListView<String>)lay.getLeft();
        VBox center = (VBox)lay.getCenter();
        Label itemTitle = (Label)center.lookup("#itemTitle");
        showAulas(itemTitle, left);
        showEdtingAulas(null, center);
    }

    public void setPlanEstudiosFinal(ArrayList<String> newPlanEstudios) {
        planEstudiosFinal = newPlanEstudios;
        BorderPane lay = (BorderPane)window.getScene().getRoot();
        ListView<String> left = (ListView<String>)lay.getLeft();
        VBox center = (VBox)lay.getCenter();
        Label itemTitle = (Label)center.lookup("#itemTitle");
        showPlanEstudios(itemTitle, left);
        showEdtingPlanEstudios(null, center);
    }

    private ArrayList<String> historial = new ArrayList<>();

    private EditEscenario() {
    }

    public static EditEscenario getInstance() {
        if (edEsc == null)
            edEsc = new EditEscenario() {
            };
        return edEsc;
    }

    private void showEdtingPlanEstudios(String item, VBox parent) {
        try {
            VBox lay = (VBox)parentEdPlanEstudios;
            if (parent.getChildren().size() == 2)
                parent.getChildren().remove(1);

            if (!(item == null)) {
                ctrlEdPlanEstudios.setEditLayout(item);
                ctrlEdPlanEstudios.setRemoveLayout(item);
            }

            parent.getChildren().add(lay);
        } catch (Exception e) {
            System.out.println("ERROR EN LA CARGA DEL LAYOUT PARA EDITAR PLAN ESTUDIOS");
        }
    }

    private void showEdtingAsignaturas(String item, VBox parent) {
        try {
            VBox lay = (VBox)parentEdAsignaturas;
            if (parent.getChildren().size() == 2)
                parent.getChildren().remove(1);
            parent.getChildren().add(lay);
        } catch (Exception e) {
            System.out.println("ERROR EN LA CARGA DEL LAYOUT PARA EDITAR ASIGNATURAS");
        }
    }

    private void showEdtingAulas(String item, VBox parent) {
        try {
            VBox lay = (VBox)parentEdAulas;
            if (parent.getChildren().size() == 2)
                parent.getChildren().remove(1);

            if (!(item == null)) {
                ArrayList<Object> au = aulasFinal.get(item);
                ctrlEdAulas.setEditLayout(item, au);
                ctrlEdAulas.setRemoveLayout(item, au);
            }

            parent.getChildren().add(lay);
        } catch (Exception e) {
            System.out.println("ERROR EN LA CARGA DEL LAYOUT PARA EDITAR AULAS");
        }
    }

    private void showEdtingRestricciones(String item, VBox parent) {
        if (!(item == null)) {
            try {
                if (item.equals("correquisitos")) {
                    HBox lay = (HBox)parentEdRcorrequisitos;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    parent.getChildren().add(lay);

                } else if (item.equals("prerrequisitos")) {
                    HBox lay = (HBox)parentEdRprerrequisitos;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    parent.getChildren().add(lay);

                } else if (item.equals("franjaAsignatura")) {
                    HBox lay = (HBox)parentEdRfranjaAsignatura;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    parent.getChildren().add(lay);

                } else if (item.equals("franjaNivel")) {
                    HBox lay = (HBox)parentEdRfranjaNivel;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    parent.getChildren().add(lay);

                } else if (item.equals("diaLibre")) {
                    VBox lay = (VBox)parentEdRdiaLibre;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    parent.getChildren().add(lay);

                } else if (item.equals("franjaTrabajo")) {
                    VBox lay = (VBox)parentEdRfranjaTrabajo;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    parent.getChildren().add(lay);

                } else if (item.equals("nivelHora")) {
                    VBox lay = (VBox)parentEdRnivelHora;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    parent.getChildren().add(lay);
                }
            } catch (Exception e) {
                System.out.println("ERROR EN LA CARGA DEL LAYOUT PARA RESTRICCIONES");
            }
        }
    }

    private void selectedItem(Label title, String oldItem, String newItem, VBox center) {
        switch (title.getText()) {
            case "Plan estudios":
                showEdtingPlanEstudios(newItem, center);
                break;

            case "Asignaturas":
                showEdtingAsignaturas(newItem, center);
                break;

            case "Aulas":
                showEdtingAulas(newItem, center);
                break;

            case "Restricciones":
                showEdtingRestricciones(newItem, center);
                break;

            default:
                System.out.println("ERROR");
                break;
        }
    }

    private void showPlanEstudios(Label title, ListView<String> leftPane) {
        title.setText("Plan estudios");
        leftPane.getItems().clear();
        for (int i = 1; i < planEstudiosFinal.size(); ++i) {
            leftPane.getItems().add(planEstudiosFinal.get(i));
        }
    }

    private void showAsignaturas(Label title, ListView<String> leftPane) {
        title.setText("Asignaturas");
        leftPane.getItems().clear();
        for (Map.Entry<String, ArrayList<Object>> asig : asignaturasFinal.entrySet()) {
            leftPane.getItems().add(asig.getKey());
        }
    }

    private void showAulas(Label title, ListView<String> leftPane) {
        title.setText("Aulas");
        leftPane.getItems().clear();
        for (Map.Entry<String, ArrayList<Object>> aula : aulasFinal.entrySet()) {
            leftPane.getItems().add(aula.getKey());
        }
    }

    private void showRestricciones(Label title, ListView<String> leftPane) {
        title.setText("Restricciones");
        leftPane.getItems().clear();
        for (Map.Entry<String, ArrayList<Object>> restr : restriccionesFinal.entrySet()) {
            leftPane.getItems().add(restr.getKey());
        }
    }

    private void setControllers(Stage window) {
        //LOADERS
        loaderEdAulas = new FXMLLoader(getClass().getResource("FXML/editAulas.fxml"));
        loaderEdPlanEstudios = new FXMLLoader(getClass().getResource("FXML/editPlanEstudios.fxml"));
        loaderEdAsignaturas = new FXMLLoader(getClass().getResource("FXML/editAsignaturas.fxml"));
        loaderEdRfranjaTrabajo = new FXMLLoader(getClass().getResource("FXML/editRfranjaTrabajo.fxml"));
        loaderEdRdiaLibre = new FXMLLoader(getClass().getResource("FXML/editRdiaLibre.fxml"));
        loaderEdRnivelHora = new FXMLLoader(getClass().getResource("FXML/editRnivelHora.fxml"));
        loaderEdRcorrequisitos = new FXMLLoader(getClass().getResource("FXML/editRcorrequisitos.fxml"));
        loaderEdRprerrequisitos = new FXMLLoader(getClass().getResource("FXML/editRprerrequisitos.fxml"));
        loaderEdRfranjaAsignatura = new FXMLLoader(getClass().getResource("FXML/editRfranjaAsignatura.fxml"));
        loaderEdRfranjaNivel = new FXMLLoader(getClass().getResource("FXML/editRfranjaNivel.fxml"));

        //PARENTS
        try {
            parentEdAulas = loaderEdAulas.load();
            parentEdPlanEstudios = loaderEdPlanEstudios.load();
            parentEdAsignaturas = loaderEdAsignaturas.load();
            parentEdRfranjaTrabajo = loaderEdRfranjaTrabajo.load();
            parentEdRdiaLibre = loaderEdRdiaLibre.load();
            parentEdRnivelHora = loaderEdRnivelHora.load();
            parentEdRcorrequisitos = loaderEdRcorrequisitos.load();
            parentEdRprerrequisitos = loaderEdRprerrequisitos.load();
            parentEdRfranjaAsignatura = loaderEdRfranjaAsignatura.load();
            parentEdRfranjaNivel = loaderEdRfranjaNivel.load();
        } catch (Exception e) {
            System.out.println("ERROR PARENTS");
        }

        //CONTROLLERS
        ctrlEdAulas = loaderEdAulas.getController();
        ctrlEdPlanEstudios = loaderEdPlanEstudios.getController();
        ctrlEdAsignaturas = loaderEdAsignaturas.getController();
        ctrlEdRfranjaTrabajo = loaderEdRfranjaTrabajo.getController();
        ctrlEdRdiaLibre = loaderEdRdiaLibre.getController();
        ctrlEdRnivelHora = loaderEdRnivelHora.getController();
        ctrlEdRcorrequisitos = loaderEdRcorrequisitos.getController();
        ctrlEdRprerrequisitos = loaderEdRprerrequisitos.getController();
        ctrlEdRfranjaAsignatura = loaderEdRfranjaAsignatura.getController();
        ctrlEdRfranjaNivel = loaderEdRfranjaNivel.getController();
    }

    public void display(String escenario, ArrayList<String> pE, HashMap<String, ArrayList<Object>> asigs,
                        HashMap<String, ArrayList<Object>> aulas, HashMap<String, ArrayList<Object>> restrs) {

        planEstudios = pE; planEstudiosFinal = pE;
        asignaturas = asigs; asignaturasFinal = asigs;
        this.aulas = aulas; this.aulasFinal = aulas;
        restricciones = restrs; restriccionesFinal = restrs;

        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/editingEscenario.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            BorderPane lay = (BorderPane)root;

            ///CENTER///
            VBox center = (VBox)lay.getCenter();
            Label itemTitle = (Label)center.lookup("#itemTitle");

            ///LEFT///
            ListView<String> left = (ListView<String>)lay.getLeft();
            left.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                    if (!(t1 == null)) {
                        selectedItem(itemTitle, s, t1, center);
                    }
                }
            });

            ///TOP///
            VBox top = (VBox)lay.getTop();
            //Title Label
            Label title = (Label)top.lookup("#title");
            title.setText(escenario);
            //top Buttons
            HBox escItems = (HBox)top.lookup("#escItems");
            //PlanEstudios Button
            Button peBtn = (Button)escItems.lookup("#peBtn");
            peBtn.setOnAction(actionEvent -> {
                showPlanEstudios(itemTitle, left);
                showEdtingPlanEstudios(null, center);
            });
            //Asignaturas Button
            Button asBtn = (Button)escItems.lookup("#asBtn");
            asBtn.setOnAction(actionEvent -> {
                showAsignaturas(itemTitle, left);
                showEdtingAsignaturas(null, center);
            });
            //Aulas Button
            Button auBtn = (Button)escItems.lookup("#auBtn");
            auBtn.setOnAction(actionEvent -> {
                showAulas(itemTitle, left);
                showEdtingAulas(null, center);
            });
            //Restricciones Button
            Button reBtn = (Button)escItems.lookup("#reBtn");
            reBtn.setOnAction(actionEvent -> {
                if (center.getChildren().size() == 2)
                    center.getChildren().remove(1);
                showRestricciones(itemTitle, left);
            });

            ///BOT///
            HBox bot = (HBox)lay.getBottom();

            setControllers(window);

            window.setScene(new Scene(lay));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }
}