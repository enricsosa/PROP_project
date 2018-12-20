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


public class CtrlEditGrupos {

    /**Instancia de EditEscenario.*/
    private EditEscenario edEsc;
    /**PlanEstudios despues de editar.*/
    private ArrayList<String> planEstudiosFinal;
    /**Asignaturas despues de editar.*/
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    /**Restriciones despues de editar.*/
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    /**Restriccion seleccionada.*/
    private Integer currentSes;
    /**Restriccion seleccionada.*/
    private ArrayList<Object> currentSesAttr;
    /**idAsignatura1.*/
    private ArrayList<ArrayList<Object>> currentSesiones;
    /**idAsignatura2.*/
    private String currentAs2 = null;
    /**Instancia de CtrlDomain.*/
    private CtrlDomain cd;

    /**Constructora de la clase CtrlEditAulas.*/
    public CtrlEditGrupos() {
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
    ChoiceBox idGadd;
    @FXML
    ChoiceBox idSadd;
    @FXML
    TextField plazasIdadd;
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
    ChoiceBox idGedit;
    @FXML
    ChoiceBox idSedit;
    @FXML
    TextField plazasIdedit;
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


    //EDIT LAYOUT
    @FXML
    Label idGrm;
    @FXML
    Label idSrm;
    @FXML
    Label plazasIdrm;
    @FXML
    RadioButton rbTrm;
    @FXML
    RadioButton rbPrm;
    @FXML
    RadioButton rbLrm;
    @FXML
    Button rmBtn;

    /**Accion que se ejecuta al pulsar el botón añadir.*/
    public void removeBtnClicked() {

    }

    private void setLayout() {

    }

    @FXML
    ListView listview;

    ChangeListener<String> grSelected = this::itemSelected;

    /**Asigna un objeto como objetivo de edicion.*/
    private void itemSelected(ObservableValue<? extends String> observableValue, String s, String t1) {
        if (!(t1 == null)) {

            //listview.getSelectionModel().selectedItemProperty().removeListener(grSelected);
            //listview.getSelectionModel().selectedItemProperty().addListener(grSelected);

            //currentSes = Integer.parseInt(t1.substring(t1.indexOf("#")+1, t1.indexOf(" ")));
            setLayout();
        }
    }

    private void setRadioButtons(String rb) {

    }

    /**Asigna un elemento a layout.*/
    public void display(boolean editing, ArrayList<ArrayList<Object>> grupos) {

    }

}
