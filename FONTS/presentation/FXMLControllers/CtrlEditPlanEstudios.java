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


public class CtrlEditPlanEstudios {

    private EditEscenario edEsc;
    private ArrayList<String> planEstudiosFinal;
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    private String currentId;
    private CtrlDomain cd;

    public CtrlEditPlanEstudios() {
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
    TextField textNomadd;
    @FXML
    Button addBtn;

    public void addBtnClicked() {
        String id;
        if (textNomadd.getText().equals("")) {
            id = "AAA";
            System.out.println("FALTA ESPECIFICAR ID");
        } else {
            id = textNomadd.getText();
        }
        //CTRLDOMAIN
        cd.addNivel(id);

        planEstudiosFinal.add(id);
        edEsc.setPlanEstudiosFinal(planEstudiosFinal);
    }

    //EDIT LAYOUT
    @FXML
    TextField textNomedit;
    @FXML
    Button editBtn;

    public void setEditLayout(String id) {
        currentId = id;
        textNomedit.setText(id);
    }

    public void editBtnClicked() {
        String id;
        if (textNomedit.getText().equals("")) {
            id = "AAA";
            System.out.println("FALTA ESPECIFICAR ID");
        } else {
            id = textNomedit.getText();
        }
        //CTRLDOMAIN
        cd.editarNombreNivel(currentId, id);

        planEstudiosFinal.remove(currentId);
        planEstudiosFinal.add(id);
        edEsc.setPlanEstudiosFinal(planEstudiosFinal);
    }

    //REMOVE LAYOUT
    @FXML
    Label labNomrm;
    @FXML
    Button rmBtn;

    public void setRemoveLayout(String id) {
        currentId = id;
        labNomrm.setText(id);
    }

    public void removeBtnClicked() {
        //CTRLDOMAIN
        cd.eliminarNivel(currentId);

        planEstudiosFinal.remove(currentId);
        edEsc.setPlanEstudiosFinal(planEstudiosFinal);
    }

}
