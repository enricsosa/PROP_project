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

public class CtrlEditRprerrequisitos {

    private EditEscenario edEsc;
    private ArrayList<String> planEstudiosFinal;
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    private ArrayList<Object> currentRestr = null;
    private String currentAs1 = null;
    private String currentAs2 = null;

    public CtrlEditRprerrequisitos() {
        edEsc = EditEscenario.getInstance();
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

    private void checkSameAsigsAdd() {
        addBtn.setDisable(add1.getValue().equals(add2.getValue()));
    }

    private void checkSameAsigsEdit() {
        try {
            editBtn.setDisable(edit1.getValue().equals(edit2.getValue()));
        } catch (Exception e) {

        }
    }

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

    public void editBtnClicked() {
        ArrayList<Object> prerrequisitos = (ArrayList<Object>)restriccionesFinal.get("prerrequisitos");
        try {
            prerrequisitos.remove(currentRestr);

            currentRestr = new ArrayList<>();
            currentRestr.add(edit1.getValue());
            currentRestr.add(edit2.getValue());

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

    public void removeBtnClicked() {
        ArrayList<Object> prerrequisitos = (ArrayList<Object>)restriccionesFinal.get("prerrequisitos");
        try {
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
