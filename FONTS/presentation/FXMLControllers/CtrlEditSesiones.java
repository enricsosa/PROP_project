/**CtrlEditAulas*/

/**Imports*/

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


public class CtrlEditSesiones {

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

    /**Constructora de la clase CtrlEditAulas.*/
    public CtrlEditSesiones() {
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
    ChoiceBox chHadd;
    @FXML
    RadioButton rbTadd;
    @FXML
    RadioButton rbPadd;
    @FXML
    RadioButton rbLadd;
    @FXML
    Button addBtn;

    /**Accion que se ejecuta al pulsar el botón añadir.*/
    public void addBtnClicked() {

    }

    //EDIT LAYOUT
    @FXML
    ChoiceBox chHedit;
    @FXML
    RadioButton rbTedit;
    @FXML
    RadioButton rbPedit;
    @FXML
    RadioButton rbLedit;
    @FXML
    Button editBtn;

    /**Accion que se ejecuta al pulsar el botón añadir.*/
    public void editBtnClicked() {

    }

    //REMOVE LAYOUT
    @FXML
    Label labHrm;
    @FXML
    RadioButton rbTremove;
    @FXML
    RadioButton rbPremove;
    @FXML
    RadioButton rbLremove;
    @FXML
    Button rmBtn;

    /**Accion que se ejecuta al pulsar el botón añadir.*/
    public void removeBtnClicked() {

    }

    /**Asigna un elemento a layout.*/
    private void setLayout() {
        ObservableList<String> sesiones = FXCollections.observableArrayList();

    }

    @FXML
    ListView listview;

    ChangeListener<String> seSelected = this::itemSelected;

    /**Asigna un objeto como objetivo de edicion.*/
    private void itemSelected(ObservableValue<? extends String> observableValue, String s, String t1) {
        if (!(t1 == null)) {

            listview.getSelectionModel().selectedItemProperty().removeListener(seSelected);
            listview.getSelectionModel().selectedItemProperty().addListener(seSelected);
            /*
            currentAs1 = t1.substring(t1.indexOf("[")+1, t1.indexOf(","));
            currentAs2 = t1.substring(t1.indexOf(",")+2, t1.indexOf("]"));
            currentRestr = new ArrayList<>();
            currentRestr.add(currentAs1);
            currentRestr.add(currentAs2);
            setLayout();
            */
        }
    }

    /**Genera un listview*/
    public void setListview(ArrayList<Object> correquisits) {
        /*
        ObservableList<String> asignaturas = FXCollections.observableArrayList();
        for (Map.Entry<String, ArrayList<Object>> asig : asignaturasFinal.entrySet()) {
            asignaturas.add(asig.getKey());
        }

        add1.setItems(asignaturas);
        add2.setItems(asignaturas);
        add1.setOnAction(actionEvent -> checkSameAsigsAdd());
        add2.setOnAction(actionEvent -> checkSameAsigsAdd());

        listview.getItems().clear();
        for (Object o : correquisits) {
            listview.getItems().add(o.toString());
        }
        */
        listview.getSelectionModel().selectedItemProperty().removeListener(seSelected);
        listview.getSelectionModel().selectedItemProperty().addListener(seSelected);

    }

}
