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


/**
 * Gestiona la edición de Sesiones.
 * @author  Enric Sosa
 * @see     FXML
 * @see     ArrayList
 * @see     HashMap
 */
public class CtrlEditSesiones {

    /**Instancia de EditEscenario.*/
    private EditEscenario edEsc;
    /**PlanEstudios despues de editar.*/
    private ArrayList<String> planEstudiosFinal;
    /**Asignaturas despues de editar.*/
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    /**Restriciones despues de editar.*/
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    /**Sesion seleccionada.*/
    private Integer currentSes;
    /**atributos de Sesion seleccionada.*/
    private ArrayList<Object> currentSesAttr;
    /**Sesiones disponibles.*/
    private ArrayList<ArrayList<Object>> currentSesiones;
    /**idAsignatura.*/
    private String currentAsignatura = null;
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
        Integer duracion = Integer.parseInt(chHadd.getValue().toString());
        String tipo = "";
        if (rbTadd.isSelected())
            tipo = "Teoria";
        if (rbPadd.isSelected())
            tipo = "Problemas";
        if (rbLadd.isSelected())
            tipo = "Laboratorio";
        if (cd.addSesion(duracion, tipo, currentAsignatura) >= 0) {
            ArrayList<Object> attr = new ArrayList<>();

            attr.add(duracion);
            attr.add(tipo);
            currentSesiones.add(attr);
            listview.getItems().clear();
            Integer i = 1;
            for (ArrayList<Object> s: currentSesiones) {
                listview.getItems().add((String)("Sesion#" + i.toString() + " " + s.get(1).toString().charAt(0)));
                ++i;
            }
        } else {
            //TRANSACCIO INVALIDA
        }
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
        Integer duracion = Integer.parseInt(chHedit.getValue().toString());
        String tipo = "";
        if (rbTedit.isSelected())
            tipo = "Teoria";
        if (rbPedit.isSelected())
            tipo = "Problemas";
        if (rbLedit.isSelected())
            tipo = "Laboratorio";
        Integer antDur = (Integer)currentSesAttr.get(0);
        String antTipo = (String)currentSesAttr.get(1);

        if (cd.editarSesion(antDur, antTipo, currentAsignatura, duracion, tipo) >= 0) {
            ArrayList<Object> attr = new ArrayList<>();
            currentSesiones.remove(currentSesAttr);
            attr.add(duracion);
            attr.add(tipo);
            currentSesiones.add(attr);
            listview.getItems().clear();
            Integer i = 1;
            for (ArrayList<Object> s: currentSesiones) {
                listview.getItems().add((String)("Sesion#" + i.toString() + " " + s.get(1).toString().charAt(0)));
                ++i;
            }
        } else {
            //TRANSACCIO INVALIDA
        }

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
        Integer antDur = (Integer)currentSesAttr.get(0);
        String antTipo = (String)currentSesAttr.get(1);

        if (cd.eliminarSesion(antDur, antTipo, currentAsignatura) >= 0) {
            ArrayList<Object> attr = new ArrayList<>();
            currentSesiones.remove(currentSesAttr);

            listview.getItems().clear();
            Integer i = 1;
            for (ArrayList<Object> s: currentSesiones) {
                listview.getItems().add((String)("Sesion#" + i.toString() + " " + s.get(1).toString().charAt(0)));
                ++i;
            }
        } else {
            //TRANSACCIO INVALIDA
        }
    }


    private void setLayout() {
        currentSesAttr = currentSesiones.get(currentSes-1);

        //EDIT & REMOVE
        rbTremove.setDisable(true);
        rbPremove.setDisable(true);
        rbLremove.setDisable(true);
        if (currentSesAttr.get(1).equals("Teoria")) {
            rbTedit.setSelected(true);
            rbPedit.setSelected(false);
            rbLedit.setSelected(false);
            rbTremove.setSelected(true);
            rbPremove.setSelected(false);
            rbLremove.setSelected(false);
        } else if (currentSesAttr.get(1).equals("Problemas")) {
            rbTedit.setSelected(false);
            rbPedit.setSelected(true);
            rbLedit.setSelected(false);
            rbTremove.setSelected(false);
            rbPremove.setSelected(true);
            rbLremove.setSelected(false);
        } else if (currentSesAttr.get(1).equals("Laboratorio")) {
            rbTedit.setSelected(false);
            rbPedit.setSelected(false);
            rbLedit.setSelected(true);
            rbTremove.setSelected(false);
            rbPremove.setSelected(false);
            rbLremove.setSelected(true);
        }
        labHrm.setText(currentSesAttr.get(0).toString() + "h");
        chHedit.getSelectionModel().select(Integer.parseInt(currentSesAttr.get(0).toString()) - 1);

    }



    @FXML
    ListView listview;

    ChangeListener<String> seSelected = this::itemSelected;

    /**Asigna un objeto como objetivo de edicion.*/
    private void itemSelected(ObservableValue<? extends String> observableValue, String s, String t1) {
        if (!(t1 == null)) {

            listview.getSelectionModel().selectedItemProperty().removeListener(seSelected);
            listview.getSelectionModel().selectedItemProperty().addListener(seSelected);

            currentSes = Integer.parseInt(t1.substring(t1.indexOf("#")+1, t1.indexOf(" ")));
            setLayout();
        }
    }

    private void setRadioButtons(String rb) {
        switch (rb) {
            case "Tadd":
                rbPadd.setSelected(false);
                rbLadd.setSelected(false);
                break;
            case "Padd":
                rbTadd.setSelected(false);
                rbLadd.setSelected(false);
                break;
            case "Ladd":
                rbTadd.setSelected(false);
                rbPadd.setSelected(false);
                break;
            case "Tedit":
                rbPedit.setSelected(false);
                rbLedit.setSelected(false);
                break;
            case "Pedit":
                rbTedit.setSelected(false);
                rbLedit.setSelected(false);
                break;
            case "Ledit":
                rbTedit.setSelected(false);
                rbPedit.setSelected(false);
                break;
            default:
                    break;
        }
    }

    public ArrayList<ArrayList<Object>> getCurrentSesiones() {
        return currentSesiones;
    }

    /**Asigna un elemento a layout.*/
    public void display(boolean editing, ArrayList<ArrayList<Object>> sesiones, String idAsignatura) {
        currentAsignatura = idAsignatura;
        if (editing) {
            currentSesiones = sesiones;

            listview.getItems().clear();
            Integer i = 1;
            for (ArrayList<Object> s: sesiones) {
                listview.getItems().add((String)("Sesion#" + i.toString() + " " + s.get(1).toString().charAt(0)));
                ++i;
            }
            listview.getSelectionModel().selectedItemProperty().removeListener(seSelected);
            listview.getSelectionModel().selectedItemProperty().addListener(seSelected);
        } else {
            currentSesiones = new ArrayList<>();
        }
        chHadd.getItems().clear();
        chHadd.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5"));
        chHedit.getItems().clear();
        chHedit.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5"));

        rbTadd.setOnAction( e -> setRadioButtons("Tadd"));
        rbPadd.setOnAction( e -> setRadioButtons("Padd"));
        rbLadd.setOnAction( e -> setRadioButtons("Ladd"));
        rbTedit.setOnAction( e -> setRadioButtons("Tedit"));
        rbPedit.setOnAction( e -> setRadioButtons("Pedit"));
        rbLedit.setOnAction( e -> setRadioButtons("Ledit"));
    }

}
