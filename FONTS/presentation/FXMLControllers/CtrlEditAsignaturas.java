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


/**
 * Menú de edición de Asignaturas.
 * @author  Enric Sosa
 * @see     FXML
 * @see     ArrayList
 * @see     HashMap
 */
public class CtrlEditAsignaturas {

    /**Menú de editar escenarios que lo genera.*/
    private EditEscenario edEsc;
    /**PlanEstudios final.*/
    private ArrayList<String> planEstudiosFinal;
    /**Asignaturas después de modificar.*/
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    /**Restricciones después de modificar.*/
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    /**Restriccion seleccionada.*/
    private String currentId;
    /**Restriccion seleccionada.*/
    private String currentNom;
    /**Restriccion seleccionada.*/
    private String currentNiv;
    /**idAsignatura1.*/
    private ArrayList<ArrayList<Object>> currentSesiones;
    /**idAsignatura2.*/
    private ArrayList<ArrayList<Object>> currentGrupos;
    /**idAsignatura1.*/
    private ArrayList<ArrayList<Object>> editedSesiones = new ArrayList<>();
    /**idAsignatura2.*/
    private ArrayList<ArrayList<Object>> editedGrupos = new ArrayList<>();

    /**Menú de edición de Sesiones de la Asignatura.*/
    private CtrlEditSesiones ctrlEditSesiones;
    /**Menú de edición de Grupos de la Asignatura.*/
    private CtrlEditGrupos ctrlEditGrupos;
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

    /**Cierra menú de Sesiones.*/
    public void closeSesions() {
        System.out.println(currentSesiones);
        currentSesiones = ctrlEditSesiones.getCurrentSesiones();
        System.out.println(currentSesiones);
    }

    /**Abre menú de edición de Sesiones.*/
    public void manageEditSesionesBtn() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/editSesiones.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            ctrlEditSesiones = loader.getController();
            window.setOnCloseRequest(e -> closeSesions());
            ctrlEditSesiones.display(true, currentSesiones, currentId);
            HBox hb = (HBox)root;

            window.setScene(new Scene(hb));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    /**Cierra menú de edición de Grupos.*/
    public void closeGrupos() {
        //currentGrupos = ctrlEditGrupos.getCurrentGrupos();
    }

    /**Abre menú de edición de Grupos.*/
    public void manageEditGruposBtn() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../FXML/editGrupos.fxml"));
        Parent root = null;
        try {
            System.out.println(currentId);
            root = loader.load();
            ctrlEditGrupos = loader.getController();
            window.setOnCloseRequest(e -> closeGrupos());
            ctrlEditGrupos.display(true, currentGrupos, currentId);
            HBox hb = (HBox)root;

            window.setScene(new Scene(hb));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    /**
     * Asigna un nuevo Nivel a edit layout.
     * @param id    nombre del Nivel.
     */
    public void setEditLayout(String id, ArrayList<Object> attr) {
        currentId = id;
        String nombre = (String)attr.get(0);
        String nivel = (String)attr.get(1);
        currentNom = nombre;
        currentNiv = nivel;
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
        currentNom = nombre;
        currentNiv = nivel;
        ArrayList<ArrayList<Object>> sesProperties = (ArrayList<ArrayList<Object>>)attr.get(2);
        currentSesiones = sesProperties;
        Integer i = 1;
        sesRm.getItems().clear();
        for (ArrayList<Object> ses : sesProperties) {
            sesRm.getItems().add(new MenuItem("Sesion#" + i.toString() + "  Duración: " + ses.get(0) + "h " + " TipoClase: " + ses.get(1)));
            ++i;
        }
        grRm.getItems().clear();
        ArrayList<ArrayList<Object>> grProperties = (ArrayList<ArrayList<Object>>)attr.get(3);
        currentGrupos = grProperties;
        for (ArrayList<Object> gr : grProperties) {
            ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)gr.get(1);
            for (ArrayList<Object> sg : subgr) {
                grRm.getItems().add(new MenuItem((String)gr.get(0) + (String)sg.get(0) + "  Plazas: " + sg.get(1) + "  Tipo Clase: " + sg.get(2)));
            }
        }
    }

    /**Accion al pulsar el botón añadir.*/
    public void addBtnClicked() {
        if (!idAdd.getText().equals("") && !nivAdd.getText().equals("") && !nomAdd.getText().equals("")) {
            if (cd.addAsignatura(idAdd.getText(), nomAdd.getText(), nivAdd.getText()) >= 0) {
                ArrayList<Object> asigAttr = new ArrayList<>();
                ArrayList<ArrayList<Object>> sesProperties = new ArrayList<>();
                ArrayList<ArrayList<Object>> grProperties = new ArrayList<>();
                asigAttr.add(nomAdd.getText());
                asigAttr.add(nivAdd.getText());
                asigAttr.add(sesProperties);
                asigAttr.add(grProperties);
                asignaturasFinal.put(idAdd.getText(), asigAttr);
                edEsc.setAsignaturasFinal(asignaturasFinal);
            } else {
                //TRANSACCIO INVIABLE
            }
        }
    }

    /**Accion al pulsar el botón editar.*/
    public void editBtnClicked() {
        if (!idEdit.getText().equals("") && !nivEdit.getText().equals("") && !nomEdit.getText().equals("")) {
            System.out.println(currentId);
            if (cd.eliminarAsignatura(currentId) >= 0) {
                System.out.println(currentId);
                if (cd.addAsignatura(idEdit.getText(), nomEdit.getText(), nivEdit.getText()) >= 0) {
                    System.out.println(currentId);
                    ArrayList<Object> asigAttr = new ArrayList<>();
                    asigAttr.add(nomEdit.getText());
                    asigAttr.add(nivEdit.getText());
                    asigAttr.add(editedSesiones);
                    asigAttr.add(editedGrupos);
                    asignaturasFinal.put(idAdd.getText(), asigAttr);
                    edEsc.setAsignaturasFinal(asignaturasFinal);
                } else {
                    //TRANSACCIO INVIABLE
                }
            } else {
                //TRANSACCIO INVIABLE
            }
        }
    }

    /**Accion al pulsar el botón borrar.*/
    public void removeBtnClicked() {
        if (cd.eliminarAsignatura(currentId) >= 0) {
            System.out.println("Se ha podido borrrar Asig");
            asignaturasFinal.remove(idAdd.getText());
            edEsc.setAsignaturasFinal(asignaturasFinal);
        } else {
            //TRANSACCIO INVIABLE
        }
    }

}
