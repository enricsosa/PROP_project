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

/**
 * Gestiona la edición de escenarios.
 * @author  Enric Sosa
 * @see     ChangeListener
 * @see     ObservableValue
 * @see     Stage
 * @see     Scene
 * @see     Parent
 * @see     VBox
 * @see     Modality
 * @see     FXMLLoader
 * @see     ArrayList
 * @see     Map
 */
public class EditEscenario {

    /**Instancia de si misma para Singleton*/
    private static EditEscenario edEsc;
    /**Ventana en que se muestra.*/
    private Stage window;
    /**Padre del Menú.*/
    private ctrl2 parent;

    //Menú de edición de Aulas
    private CtrlEditAulas ctrlEdAulas;
    private FXMLLoader loaderEdAulas;
    private Parent parentEdAulas;

    //Menú de edición de PlanEstudios
    private CtrlEditPlanEstudios ctrlEdPlanEstudios;
    private FXMLLoader loaderEdPlanEstudios;
    private Parent parentEdPlanEstudios;

    //Menú de edición de Asignaturas
    private CtrlEditAsignaturas ctrlEdAsignaturas;
    private FXMLLoader loaderEdAsignaturas;
    private Parent parentEdAsignaturas;

    //Menús de edición de restricciones
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

    /**PlanEstudios inicial.*/
    private ArrayList<String> planEstudios;
    /**Asignaturas inicial.*/
    private HashMap<String, ArrayList<Object>> asignaturas;
    /**Aulas inicial.*/
    private HashMap<String, ArrayList<Object>> aulas;
    /**Restricciones inicial.*/
    private HashMap<String, ArrayList<Object>> restricciones;

    /**PlanEstudios final.*/
    private ArrayList<String> planEstudiosFinal;
    /**Asignaturas final.*/
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    /**Aulas final.*/
    private HashMap<String, ArrayList<Object>> aulasFinal;
    /**Restricciones final.*/
    private HashMap<String, ArrayList<Object>> restriccionesFinal;

    /**Insica si ha cambiado PlanEstudios.*/
    private boolean peChanged;
    /**Insica si ha cambiado Asignaturas.*/
    private boolean asChanged;
    /**Insica si ha cambiado Aulas.*/
    private boolean auChanged;
    /**Insica si ha cambiado Restricciones.*/
    private boolean reChanged;

    /**
     * Devuelve planEstudiosFinal.
     * @return  planEstudiosFinal
     */
    public ArrayList<String> getPlanEstudiosFinal() {
        return planEstudiosFinal;
    }

    /**
     * Devuelve aulasFinal.
     * @return  aulasFinal
     */
    public HashMap<String, ArrayList<Object>> getAulasFinal() {
        return aulasFinal;
    }

    /**
     * Devuelve asignaturasFinal.
     * @return  asignaturasFinal
     */
    public HashMap<String, ArrayList<Object>> getAsignaturasFinal() {
        return asignaturasFinal;
    }

    /**
     * Devuelve restriccionesFinal.
     * @return  restriccionesFinal
     */
    public HashMap<String, ArrayList<Object>> getRestriccionesFinal() {
        return restriccionesFinal;
    }

    /**
     * Asigna un nuevo valor a aulasFinal.
     * @param newAulas  Valor que se asigna.
     */
    public void setAulasFinal(HashMap<String, ArrayList<Object>> newAulas) {
        aulasFinal = newAulas;
        BorderPane lay = (BorderPane)window.getScene().getRoot();
        ListView<String> left = (ListView<String>)lay.getLeft();
        VBox center = (VBox)lay.getCenter();
        Label itemTitle = (Label)center.lookup("#itemTitle");
        showAulas(itemTitle, left);
        showEdtingAulas(null, center);
    }

    /**
     * Asigna un nuevo valor a restriccionesFinal.
     * @param newRestricciones  Valor que se asigna.
     */
    public void setRestriccionesFinal(HashMap<String, ArrayList<Object>> newRestricciones) {
        restriccionesFinal = newRestricciones;
        BorderPane lay = (BorderPane)window.getScene().getRoot();
        ListView<String> left = (ListView<String>)lay.getLeft();
        VBox center = (VBox)lay.getCenter();
        Label itemTitle = (Label)center.lookup("#itemTitle");
        showRestricciones(itemTitle, left);
        showEdtingRestricciones(null, center);
    }

    /**
     * Asigna un nuevo valor a planEstudiosFinal.
     * @param newPlanEstudios   Valor que se asigna.
     */
    public void setPlanEstudiosFinal(ArrayList<String> newPlanEstudios) {
        planEstudiosFinal = newPlanEstudios;
        BorderPane lay = (BorderPane)window.getScene().getRoot();
        ListView<String> left = (ListView<String>)lay.getLeft();
        VBox center = (VBox)lay.getCenter();
        Label itemTitle = (Label)center.lookup("#itemTitle");
        showPlanEstudios(itemTitle, left);
        showEdtingPlanEstudios(null, center);
    }

    /**Constructora de la clase EditEscenario.*/
    private EditEscenario() {
    }

    /**Instanciadora de la clase EditEscenario.*/
    public static EditEscenario getInstance() {
        if (edEsc == null)
            edEsc = new EditEscenario() {
            };
        return edEsc;
    }

    /**
     * Muestra menú de edición de planEstudios.
     * @param item      item a mostrar.
     * @param parent    padre del item.
     */
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

    /**
     * Muestra menú de edición de Asignaturas.
     * @param item      item a mostrar.
     * @param parent    padre del item.
     */
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

    /**
     * Muestra menú de edición de Aulas.
     * @param item      item a mostrar.
     * @param parent    padre del item.
     */
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

    /**
     * Muestra menú de edición de Restricciones.
     * @param item      item a mostrar.
     * @param parent    padre del item.
     */
    private void showEdtingRestricciones(String item, VBox parent) {
        if (!(item == null)) {
            try {
                if (item.equals("correquisitos")) {
                    HBox lay = (HBox)parentEdRcorrequisitos;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    ArrayList<Object> correquisitos = (ArrayList<Object>)restriccionesFinal.get("correquisitos");
                    ctrlEdRcorrequisitos.setListview(correquisitos);
                    parent.getChildren().add(lay);

                } else if (item.equals("prerrequisitos")) {
                    HBox lay = (HBox)parentEdRprerrequisitos;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    ArrayList<Object> prerrequisitos = (ArrayList<Object>)restriccionesFinal.get("prerrequisitos");
                    ctrlEdRprerrequisitos.setListview(prerrequisitos);
                    parent.getChildren().add(lay);

                } else if (item.equals("franjaAsignatura")) {
                    HBox lay = (HBox)parentEdRfranjaAsignatura;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    ArrayList<Object> frAsigs = (ArrayList<Object>)restriccionesFinal.get("franjaAsignatura");
                    ctrlEdRfranjaAsignatura.setListview(frAsigs);
                    parent.getChildren().add(lay);

                } else if (item.equals("franjaNivel")) {
                    HBox lay = (HBox)parentEdRfranjaNivel;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    ArrayList<Object> frNiveles = (ArrayList<Object>)restriccionesFinal.get("franjaNivel");
                    ctrlEdRfranjaNivel.setListview(frNiveles);
                    parent.getChildren().add(lay);

                } else if (item.equals("diaLibre")) {
                    VBox lay = (VBox)parentEdRdiaLibre;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    ArrayList<Object> diasLibres = (ArrayList<Object>)restriccionesFinal.get("diaLibre");
                    ctrlEdRdiaLibre.setLayout(diasLibres);
                    parent.getChildren().add(lay);

                } else if (item.equals("franjaTrabajo")) {
                    VBox lay = (VBox)parentEdRfranjaTrabajo;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    ArrayList<Object> horasTrabajo = (ArrayList<Object>)restriccionesFinal.get("franjaTrabajo");
                    ctrlEdRfranjaTrabajo.setLayout(horasTrabajo.get(0).toString(), horasTrabajo.get(1).toString());
                    parent.getChildren().add(lay);

                } else if (item.equals("nivelHora")) {
                    VBox lay = (VBox)parentEdRnivelHora;
                    if (parent.getChildren().size() == 2)
                        parent.getChildren().remove(1);
                    ArrayList<String> allNiv = new ArrayList<>();
                    for (int i = 1; i < planEstudiosFinal.size(); ++i) {
                        allNiv.add(planEstudiosFinal.get(i));
                    }
                    ArrayList<Object> nHs = (ArrayList<Object>)restriccionesFinal.get("nivelHora");
                    ctrlEdRnivelHora.setLayout(nHs, allNiv);
                    parent.getChildren().add(lay);
                }
            } catch (Exception e) {
                System.out.println("ERROR EN LA CARGA DEL LAYOUT PARA RESTRICCIONES");
            }
        }
    }

    /**
     * Gestiona la seleccion de menús de edición.
     * @param title     título del menú.
     * @param oldItem   antigua menú seleccionado.
     * @param newItem   nuevo menú seleccionado.
     * @param center    VBox donde se colocan los menús.
     */
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

    /**
     * Crea el menú lateral de EditarPlanEstudios.
     * @param title     Título que se le da.
     * @param leftPane  Panel donde se muestra.
     */
    private void showPlanEstudios(Label title, ListView<String> leftPane) {
        title.setText("Plan estudios");
        leftPane.getItems().clear();
        for (int i = 1; i < planEstudiosFinal.size(); ++i) {
            leftPane.getItems().add(planEstudiosFinal.get(i));
        }
    }

    /**
     * Crea el menú lateral de EditarAsignaturas.
     * @param title     Título que se le da.
     * @param leftPane  Panel donde se muestra.
     */
    private void showAsignaturas(Label title, ListView<String> leftPane) {
        title.setText("Asignaturas");
        leftPane.getItems().clear();
        for (Map.Entry<String, ArrayList<Object>> asig : asignaturasFinal.entrySet()) {
            leftPane.getItems().add(asig.getKey());
        }
    }

    /**
     * Crea el menú lateral de EditarAulas.
     * @param title     Título que se le da.
     * @param leftPane  Panel donde se muestra.
     */
    private void showAulas(Label title, ListView<String> leftPane) {
        title.setText("Aulas");
        leftPane.getItems().clear();
        for (Map.Entry<String, ArrayList<Object>> aula : aulasFinal.entrySet()) {
            leftPane.getItems().add(aula.getKey());
        }
    }

    /**
     * Crea el menú lateral de EditarRestricciones.
     * @param title     Título que se le da.
     * @param leftPane  Panel donde se muestra.
     */
    private void showRestricciones(Label title, ListView<String> leftPane) {
        title.setText("Restricciones");
        leftPane.getItems().clear();
        for (Map.Entry<String, ArrayList<Object>> restr : restriccionesFinal.entrySet()) {
            leftPane.getItems().add(restr.getKey());
        }
    }

    /**
     * Asigna los controllers
     * @param window    Ventana en que se muestra el menú.
     */
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

    /**
     * Muestra el menú de edición de escenarios.
     * @param escenario escenario a editar.
     * @param pE        planEstudios
     * @param asigs     Asiganturas
     * @param aulas     Aulas
     * @param restrs    Restricciones
     * @param parent    Padre que ha mostrado el menú.
     */
    public void display(String escenario, ArrayList<String> pE, HashMap<String, ArrayList<Object>> asigs,
                        HashMap<String, ArrayList<Object>> aulas, HashMap<String, ArrayList<Object>> restrs, ctrl2 parent) {

        this.parent = parent;
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