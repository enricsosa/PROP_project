package presentation.FXMLControllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

import presentation.EditEscenario;
import domaincontrollers.CtrlDomain;

/**
 * Gestiona la edición de Restricciones Prerrequisito.
 * @author  Enric Sosa
 * @see     FXML
 * @see     ArrayList
 * @see     HashMap
 */
public class CtrlEditRprerrequisitos {

    /**Instancia de EditEscenario.*/
    private EditEscenario edEsc;
    /**PlanEstudios despues de editar.*/
    private ArrayList<String> planEstudiosFinal;
    /**Asignaturas despues de editar.*/
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    /**Restriciones despues de editar.*/
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    /**Restriccion seleccionada.*/
    private ArrayList<Object> currentRestr = null;
    /**idAsignatura1.*/
    private String currentAs1 = null;
    /**idAsignatura2.*/
    private String currentAs2 = null;
    /**Instancia de CtrlDomain.*/
    private CtrlDomain cd;

    /**Constructora de la clase CtrlEditRprerrequisitos.*/
    public CtrlEditRprerrequisitos() {
        edEsc = EditEscenario.getInstance();
        try {
            cd = CtrlDomain.getInstance();
        } catch (Exception e) {
            System.out.println("ERROR EN LA CARGA DEL CONTROLADOR DE DOMINIO");
        }
        planEstudiosFinal = edEsc.getPlanEstudiosFinal();
        asignaturasFinal = edEsc.getAsignaturasFinal();
        restriccionesFinal = edEsc.getRestriccionesFinal();
    }

    //ADD LAYOUT
    @FXML
    ChoiceBox add1;
    @FXML
    ChoiceBox add2;
    @FXML
    Button addBtn;

    //EDIT LAYOUT
    @FXML
    ChoiceBox edit1;
    @FXML
    ChoiceBox edit2;
    @FXML
    Button editBtn;

    //REMOVE LAYOUT
    @FXML
    Label remove1;
    @FXML
    Label remove2;
    @FXML
    Button removeBtn;

    /**Comprueba que no se añade un Preequsito de si mismo.*/
    private void checkSameAsigsAdd() {
        addBtn.setDisable(add1.getValue().equals(add2.getValue()));
    }

    /**Comprueba que no se añade un Preequsito de si mismo.*/
    private void checkSameAsigsEdit() {
        try {
            editBtn.setDisable(edit1.getValue().equals(edit2.getValue()));
        } catch (Exception e) {

        }
    }

    /**Prepara un layout.*/
    private void setLayout() {
        ObservableList<String> asignaturas = FXCollections.observableArrayList();
        for (Map.Entry<String, ArrayList<Object>> asig : asignaturasFinal.entrySet()) {
            asignaturas.add(asig.getKey());
        }

        //EDIT
        edit1.setItems(asignaturas);
        edit2.setItems(asignaturas);
        edit1.getSelectionModel().select(currentAs1);
        edit2.getSelectionModel().select(currentAs2);

        //REMOVE
        remove1.setText(currentAs1);
        remove2.setText(currentAs2);

        edit1.setOnAction(actionEvent -> checkSameAsigsEdit());
        edit2.setOnAction(actionEvent -> checkSameAsigsEdit());
    }

    public void addBtnClicked() {
        ArrayList<Object> prerrequisitos = (ArrayList<Object>)restriccionesFinal.get("prerrequisitos");
        currentRestr = new ArrayList<>();
        try {
            currentRestr.add(add1.getValue());
            currentRestr.add(add2.getValue());

            //CTRLDOMAIN
            cd.addPrerrequisito(add1.getValue().toString(), add2.getValue().toString());

            prerrequisitos.add(currentRestr);
            restriccionesFinal.replace("prerrequisitos", prerrequisitos);
            edEsc.setRestriccionesFinal(restriccionesFinal);

            listview.getItems().clear();
            for (Object o : prerrequisitos) {
                listview.getItems().add(o.toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR EN ALGUNO DE LOS INPUTS");
        }
    }

    /**Accion al pulsar el botón editar.*/
    public void editBtnClicked() {
        ArrayList<Object> prerrequisitos = (ArrayList<Object>)restriccionesFinal.get("prerrequisitos");
        try {
            //CTRLDOMAIN
            cd.eliminarPrerrequisito(currentRestr.get(0).toString(), currentRestr.get(1).toString());

            prerrequisitos.remove(currentRestr);

            currentRestr = new ArrayList<>();
            currentRestr.add(edit1.getValue());
            currentRestr.add(edit2.getValue());

            //CTRLDOMAIN
            cd.addPrerrequisito(edit1.getValue().toString(), edit2.getValue().toString());

            prerrequisitos.add(currentRestr);
            restriccionesFinal.replace("prerrequisitos", prerrequisitos);
            edEsc.setRestriccionesFinal(restriccionesFinal);

            listview.getItems().clear();
            for (Object o : prerrequisitos) {
                listview.getItems().add(o.toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR EN ALGUNO DE LOS INPUTS");
        }
    }

    /**Accion al pulsar el botón borrar.*/
    public void removeBtnClicked() {
        ArrayList<Object> prerrequisitos = (ArrayList<Object>)restriccionesFinal.get("prerrequisitos");
        try {
            //CTRLDOMAIN
            cd.eliminarPrerrequisito(currentRestr.get(0).toString(), currentRestr.get(1).toString());

            prerrequisitos.remove(currentRestr);
            restriccionesFinal.replace("prerrequisitos", prerrequisitos);
            edEsc.setRestriccionesFinal(restriccionesFinal);

            listview.getItems().clear();
            for (Object o : prerrequisitos) {
                listview.getItems().add(o.toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR EN ALGUNO DE LOS INPUTS");
        }
    }

    @FXML
    ListView listview;

    ChangeListener<String> preSelected = this::itemSelected;

    /**Asigna un objeto como objetivo de edicion.*/
    private void itemSelected(ObservableValue<? extends String> observableValue, String s, String t1) {
        if (!(t1 == null)) {
            listview.getSelectionModel().selectedItemProperty().removeListener(preSelected);
            listview.getSelectionModel().selectedItemProperty().addListener(preSelected);
            currentAs1 = t1.substring(t1.indexOf("[")+1, t1.indexOf(","));
            currentAs2 = t1.substring(t1.indexOf(",")+2, t1.indexOf("]"));
            currentRestr = new ArrayList<>();
            currentRestr.add(currentAs1);
            currentRestr.add(currentAs2);
            setLayout();
        }
    }

    /**Muestra un listview.*/
    public void setListview(ArrayList<Object> prerrequisitos) {
        ObservableList<String> asignaturas = FXCollections.observableArrayList();
        for (Map.Entry<String, ArrayList<Object>> asig : asignaturasFinal.entrySet()) {
            asignaturas.add(asig.getKey());
        }

        add1.setItems(asignaturas);
        add2.setItems(asignaturas);
        add1.setOnAction(actionEvent -> checkSameAsigsAdd());
        add2.setOnAction(actionEvent -> checkSameAsigsAdd());

        listview.getItems().clear();
        for (Object o : prerrequisitos) {
            listview.getItems().add(o.toString());
        }
        listview.getSelectionModel().selectedItemProperty().removeListener(preSelected);
        listview.getSelectionModel().selectedItemProperty().addListener(preSelected);
    }

}
