package presentation.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
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
 * Gestiona la edición de Restricciones NivelHora.
 * @author  Enric Sosa
 * @see     FXML
 * @see     ArrayList
 * @see     HashMap
 */
public class CtrlEditRnivelHora {

    /**Instancia de EditEscenario.*/
    private EditEscenario edEsc;
    /**PlanEstudios despues de editar.*/
    private ArrayList<String> planEstudiosFinal;
    /**Asignaturas despues de editar.*/
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    /**Restriciones despues de editar.*/
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    /**Id del elemento seleccionado.*/
    private String currentId;
    /**Instancia de CtrlDomain.*/
    private CtrlDomain cd;
    /**Listado de Niveles.*/
    private ArrayList<String> niveles;

    /**Constructora de la clase CtrlEditRnivelHora.*/
    public CtrlEditRnivelHora() {
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

    @FXML
    FlowPane mainFPant;
    @FXML
    FlowPane mainFPpost;

    /**
     * Asigna un NivelHora a Layout.
     * @param nHs       NivelHora involucrado.
     * @param allNiv    todos los NivelesHora.
     */
    public void setLayout(ArrayList<Object> nHs, ArrayList<String> allNiv) {
        niveles = allNiv;

        mainFPant.getChildren().clear();
        for (Object nh : nHs) {
            mainFPant.getChildren().add(new Label(nh.toString()));
        }
        mainFPpost.getChildren().clear();
        for (String nh : allNiv) {
            CheckBox cb = new CheckBox(nh);
            cb.setSelected(nHs.contains(nh));
            mainFPpost.getChildren().add(cb);
        }
    }

    /**Acción que se ejecuta al pulsar el botón actualizar.*/
    public void updateBtnClicked() {
        ArrayList<Object> newNivs = new ArrayList<>();
        int i = 0;
        mainFPant.getChildren().clear();
        for (Node n : mainFPpost.getChildren()) {
            CheckBox cb = (CheckBox)n;
            if (cb.isSelected()) {
                newNivs.add(niveles.get(i));
                cd.addNivelHora(niveles.get(i));
                mainFPant.getChildren().add(new Label(niveles.get(i)));
            } else {
                cd.eliminarNivelHora(niveles.get(i));
            }
            ++i;
        }
        restriccionesFinal.replace("nivelHora", newNivs);
        edEsc.setRestriccionesFinal(restriccionesFinal);
    }

}
