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
import javafx.collections.FXCollections;
import javafx.collections.*;
import javafx.fxml.FXMLLoader;
import javax.swing.text.html.HTMLDocument;
import java.util.*;
import java.lang.reflect.Array;

import presentation.EditEscenario;
import domaincontrollers.CtrlDomain;


public class CtrlEditRfranjaTrabajo {

    private EditEscenario edEsc;
    private ArrayList<String> planEstudiosFinal;
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    private String currentId;

    public CtrlEditRfranjaTrabajo() {
        edEsc = EditEscenario.getInstance();
        planEstudiosFinal = edEsc.getPlanEstudiosFinal();
        asignaturasFinal = edEsc.getAsignaturasFinal();
        restriccionesFinal = edEsc.getRestriccionesFinal();
    }

    @FXML
    Label horaIniAnt;
    @FXML
    Label horaFinAnt;
    @FXML
    ChoiceBox horaIniPost;
    @FXML
    ChoiceBox horaFinPost;
    @FXML
    Button updateBtn;



    public void setLayout(String hI, String hF) {
        System.out.println(hI);
        System.out.println(hF);
        int hi = Integer.parseInt(hI);
        int hf = Integer.parseInt(hF);
        horaIniAnt.setText(hI + "h");
        horaFinAnt.setText(hF + "h");

        horaIniPost.setItems(FXCollections.observableArrayList(
                "00:00", "01:00", "02:00", "03:00",
                "04:00", "05:00", "06:00", "07:00",
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00",
                "20:00", "21:00", "22:00"));

        horaFinPost.setItems(FXCollections.observableArrayList(
                "01:00", "02:00", "03:00",
                "04:00", "05:00", "06:00", "07:00",
                "08:00", "09:00", "10:00", "11:00",
                "12:00", "13:00", "14:00", "15:00",
                "16:00", "17:00", "18:00", "19:00",
                "20:00", "21:00", "22:00", "23:00"));


        horaIniPost.getSelectionModel().select(hi);
        horaFinPost.getSelectionModel().select(hf-1);

        horaIniPost.setOnAction(actionEvent -> {
            ObservableList<String> aviableHoraFin = FXCollections.observableArrayList();
            try {
                String hiStr = horaIniPost.getSelectionModel().getSelectedItem().toString();
                Integer minHour = Integer.parseInt(hiStr.substring(0, hiStr.indexOf(":")));
                for(Integer i = minHour+1; i < 24; ++i) {
                    if (i.toString().length() == 1)
                        aviableHoraFin.add("0" + i.toString() + ":00");
                    else
                        aviableHoraFin.add(i.toString() + ":00");
                }
                horaFinPost.setItems(aviableHoraFin);
            } catch (Exception e) {

            }
        });
    }

    public void updateBtnClicked() {
        String hiStr = horaIniPost.getSelectionModel().getSelectedItem().toString();
        String hfStr;
        try {
            hfStr = horaFinPost.getSelectionModel().getSelectedItem().toString();
        } catch (Exception e) {
            System.out.println("ESCOGE UNA HORA FINAL PARA LA FRANJA DE TRABAJO ANTES DE EDITAR");
            System.out.println("VALOR POR DEFECTO: 23h");
            hfStr = "23:00";
        }
        Integer hI = Integer.parseInt(hiStr.substring(0, hiStr.indexOf(":")));
        Integer hF = Integer.parseInt(hfStr.substring(0, hfStr.indexOf(":")));
        horaIniAnt.setText(hI + "h");
        horaFinAnt.setText(hF + "h");

        ArrayList<Object> newHoras = new ArrayList<>();
        newHoras.add(hI);
        newHoras.add(hF);
        restriccionesFinal.replace("franjaTrabajo", newHoras);
        edEsc.setRestriccionesFinal(restriccionesFinal);
    }

    @FXML
    private void initialize() {
    }

}
