package presentation.FXMLControllers;

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


public class CtrlEditAsignaturas {

    private EditEscenario edEsc;
    private ArrayList<String> planEstudiosFinal;
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    /**Restriccion seleccionada.*/
    private String currentId;
    /**idAsignatura1.*/
    private String currentAs1 = null;
    /**idAsignatura2.*/
    private String currentAs2 = null;
    /**Instancia de CtrlDomain.*/
    private CtrlDomain cd;

    /**Constructora de la clase CtrlEditRcorrequisitos.*/
    public CtrlEditAsignaturas() {
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
    TextField idAdd;
    @FXML
    TextField nivAdd;
    @FXML
    TextField nomAdd;
    @FXML
    Button sesAdd;
    @FXML
    Button grAdd;
    @FXML
    Button addBtn;

    //EDIT LAYOUT
    @FXML
    TextField idEdit;
    @FXML
    TextField nivEdit;
    @FXML
    TextField nomEdit;
    @FXML
    Button sesEdit;
    @FXML
    Button grEdit;
    @FXML
    Button editBtn;

    //REMOVE LAYOUT
    @FXML
    Label idRm;
    @FXML
    Label nivRm;
    @FXML
    Label nomRm;
    @FXML
    MenuButton sesRm;
    @FXML
    MenuButton grRm;
    @FXML
    Button rmBtn;

    /**
     * Asigna un nuevo Nivel a edit layout.
     * @param id    nombre del Nivel.
     */
    public void setEditLayout(String id, ArrayList<Object> attr) {
        currentId = id;
        String nombre = (String)attr.get(0);
        String nivel = (String)attr.get(1);
        idEdit.setText(id);
        nomEdit.setText(nombre);
        nivEdit.setText(nivel);
    }

    /**
     * Asigna un nuevo Nivel a remove layout.
     * @param id    nombre del Nivel.
     */
    public void setRemoveLayout(String id, ArrayList<Object> attr) {
        currentId = id;
        String nombre = (String)attr.get(0);
        String nivel = (String)attr.get(1);
        idRm.setText(id);
        nomRm.setText(nombre);
        nivRm.setText(nivel);
        System.out.println(attr);
        ArrayList<ArrayList<Object>> sesProperties = (ArrayList<ArrayList<Object>>)attr.get(2);
        Integer i = 1;
        sesRm.getItems().clear();
        for (ArrayList<Object> ses : sesProperties) {
            sesRm.getItems().add(new MenuItem("Sesion#" + i.toString() + "  Duración: " + ses.get(0) + "h " + " TipoClase: " + ses.get(1)));
            ++i;
        }
        grRm.getItems().clear();
        ArrayList<ArrayList<Object>> grProperties = (ArrayList<ArrayList<Object>>)attr.get(3);
        for (ArrayList<Object> gr : grProperties) {
            ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)gr.get(1);
            for (ArrayList<Object> sg : subgr) {
                grRm.getItems().add(new MenuItem((String)gr.get(0) + (String)sg.get(0) + "  Plazas: " + sg.get(1) + "  Tipo Clase: " + sg.get(2)));
            }
        }
    }

    /**Accion al pulsar el botón añadir.*/
    public void addBtnClicked() {

    }

    /**Accion al pulsar el botón editar.*/
    public void editBtnClicked() {

    }

    /**Accion al pulsar el botón borrar.*/
    public void removeBtnClicked() {

    }

}
