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

public class CtrlEditRfranjaNivel {

    private EditEscenario edEsc;
    private ArrayList<String> planEstudiosFinal;
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    private ArrayList<Object> currentRestr = null;
    private String currentId = null;
    private String currenthI = null;
    private String currenthF = null;
    private CtrlDomain cd;

    public CtrlEditRfranjaNivel() {
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
    ChoiceBox addN;
    @FXML
    ChoiceBox addI;
    @FXML
    ChoiceBox addF;
    @FXML
    Button addBtn;

    //EDIT LAYOUT
    @FXML
    ChoiceBox editN;
    @FXML
    ChoiceBox editI;
    @FXML
    ChoiceBox editF;
    @FXML
    Button editBtn;

    //REMOVE LAYOUT
    @FXML
    Label removeN;
    @FXML
    Label removeI;
    @FXML
    Label removeF;
    @FXML
    Button removeBtn;

    private void setLayout() {
        ObservableList<String> niveles = FXCollections.observableArrayList();
        for (int i = 1; i < planEstudiosFinal.size(); ++i) {
            niveles.add(planEstudiosFinal.get(i));
        }

        //EDIT
        editI.setItems(FXCollections.observableArrayList(
                "00:00", "01:00", "02:00", "03:00",
                "04:00", "05:00", "06:00", "07:00",
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00",
                "20:00", "21:00", "22:00"));

        editF.setItems(FXCollections.observableArrayList(
                "01:00", "02:00", "03:00",
                "04:00", "05:00", "06:00", "07:00",
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00",
                "20:00", "21:00", "22:00", "23:00"));

        editI.getSelectionModel().select(Integer.parseInt(currenthI));
        editF.getSelectionModel().select(Integer.parseInt(currenthF)-1);


        editN.setItems(niveles);
        editN.getSelectionModel().select(currentId);

        //REMOVE
        removeN.setText(currentId);
        removeI.setText(currenthI);
        removeF.setText(currenthF);

        editI.setOnAction(actionEvent -> {
            ObservableList<String> aviableHoraFin = FXCollections.observableArrayList();
            try {
                String hiStr = editI.getSelectionModel().getSelectedItem().toString();
                Integer minHour = Integer.parseInt(hiStr.substring(0, hiStr.indexOf(":")));
                for(Integer i = minHour+1; i < 24; ++i) {
                    if (i.toString().length() == 1)
                        aviableHoraFin.add("0" + i.toString() + ":00");
                    else
                        aviableHoraFin.add(i.toString() + ":00");
                }
                editF.setItems(aviableHoraFin);
            } catch (Exception e) {

            }
        });
    }

    public void addBtnClicked() {
        ArrayList<Object> frNiveles = (ArrayList<Object>)restriccionesFinal.get("franjaNivel");
        currentRestr = new ArrayList<>();
        try {
            String hiStr = (String) addI.getValue();
            String hfStr = (String) addF.getValue();
            Integer hI = Integer.parseInt(hiStr.substring(0, hiStr.indexOf(":")));
            Integer hF = Integer.parseInt(hfStr.substring(0, hfStr.indexOf(":")));
            //CTRLDOMAIN
            cd.addFranjaNivel(addN.getValue().toString(), hI, hF);

            currentRestr.add(addN.getValue());
            currentRestr.add(hI);
            currentRestr.add(hF);
            frNiveles.add(currentRestr);
            restriccionesFinal.replace("franjaNivel", frNiveles);
            edEsc.setRestriccionesFinal(restriccionesFinal);

            listview.getItems().clear();
            for (Object o : frNiveles) {
                listview.getItems().add(o.toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR EN ALGUNO DE LOS INPUTS");
        }
    }

    public void editBtnClicked() {
        ArrayList<Object> frNiveles = (ArrayList<Object>)restriccionesFinal.get("franjaNivel");
        try {
            String hiStr = (String) editI.getValue();
            String hfStr = (String) editF.getValue();
            Integer hI = Integer.parseInt(hiStr.substring(0, hiStr.indexOf(":")));
            Integer hF = Integer.parseInt(hfStr.substring(0, hfStr.indexOf(":")));
            frNiveles.remove(currentRestr);

            //CTRLDOMAIN
            cd.eliminarFranjaNivel(currentRestr.get(0).toString(), Integer.parseInt(currentRestr.get(1).toString()), Integer.parseInt(currentRestr.get(2).toString()));
            cd.addFranjaNivel(editN.getValue().toString(), hI, hF);

            currentRestr = new ArrayList<>();
            currentRestr.add(editN.getValue());
            currentRestr.add(hI);
            currentRestr.add(hF);
            frNiveles.add(currentRestr);
            restriccionesFinal.replace("franjaNivel", frNiveles);
            edEsc.setRestriccionesFinal(restriccionesFinal);

            listview.getItems().clear();
            for (Object o : frNiveles) {
                listview.getItems().add(o.toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR EN ALGUNO DE LOS INPUTS");
        }
    }

    public void removeBtnClicked() {
        ArrayList<Object> frNiveles = (ArrayList<Object>)restriccionesFinal.get("franjaNivel");
        try {
            //CTRLDOMAIN
            cd.eliminarFranjaNivel(currentRestr.get(0).toString(), Integer.parseInt(currentRestr.get(1).toString()), Integer.parseInt(currentRestr.get(2).toString()));

            frNiveles.remove(currentRestr);
            restriccionesFinal.replace("franjaNivel", frNiveles);
            edEsc.setRestriccionesFinal(restriccionesFinal);

            listview.getItems().clear();
            for (Object o : frNiveles) {
                listview.getItems().add(o.toString());
            }
        } catch (Exception e) {
            System.out.println("ERROR EN ALGUNO DE LOS INPUTS");
        }
    }

    @FXML
    ListView listview;

    ChangeListener<String> lvSelected = this::itemSelected;

    private void itemSelected(ObservableValue<? extends String> observableValue, String s, String t1) {
        if (!(t1 == null)) {
            listview.getSelectionModel().selectedItemProperty().removeListener(lvSelected);
            listview.getSelectionModel().selectedItemProperty().addListener(lvSelected);
            currentId = t1.substring(t1.indexOf("[")+1, t1.indexOf(","));
            currenthI = t1.substring(t1.indexOf(",")+2, t1.indexOf(",", t1.indexOf(",")+1));
            currenthF = t1.substring(t1.indexOf(",", t1.indexOf(",")+1)+2, t1.indexOf("]"));
            currentRestr = new ArrayList<>();
            currentRestr.add(currentId);
            currentRestr.add(Integer.parseInt(currenthI));
            currentRestr.add(Integer.parseInt(currenthF));
            setLayout();
        }
    }

    public void setListview(ArrayList<Object> frNiveles) {
        ObservableList<String> niveles = FXCollections.observableArrayList();
        for (int i = 1; i < planEstudiosFinal.size(); ++i) {
            niveles.add(planEstudiosFinal.get(i));
        }
        //ADD
        addN.setItems(niveles);
        addI.setItems(FXCollections.observableArrayList(
                "00:00", "01:00", "02:00", "03:00",
                "04:00", "05:00", "06:00", "07:00",
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00",
                "20:00", "21:00", "22:00"));

        addF.setItems(FXCollections.observableArrayList(
                "01:00", "02:00", "03:00",
                "04:00", "05:00", "06:00", "07:00",
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00",
                "20:00", "21:00", "22:00", "23:00"));

        addI.setOnAction(actionEvent -> {
            ObservableList<String> aviableHoraFin = FXCollections.observableArrayList();
            try {
                String hiStr = addI.getSelectionModel().getSelectedItem().toString();
                Integer minHour = Integer.parseInt(hiStr.substring(0, hiStr.indexOf(":")));
                for(Integer i = minHour+1; i < 24; ++i) {
                    if (i.toString().length() == 1)
                        aviableHoraFin.add("0" + i.toString() + ":00");
                    else
                        aviableHoraFin.add(i.toString() + ":00");
                }
                addF.setItems(aviableHoraFin);
            } catch (Exception e) {

            }
        });

        listview.getItems().clear();
        for (Object o : frNiveles) {
            listview.getItems().add(o.toString());
        }
        listview.getSelectionModel().selectedItemProperty().removeListener(lvSelected);
        listview.getSelectionModel().selectedItemProperty().addListener(lvSelected);
    }

}
