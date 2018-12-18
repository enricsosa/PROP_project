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

public class CtrlEditRnivelHora {

    private EditEscenario edEsc;
    private ArrayList<String> planEstudiosFinal;
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    private ArrayList<String> niveles;
    private String currentId;

    public CtrlEditRnivelHora() {
        edEsc = EditEscenario.getInstance();
        planEstudiosFinal = edEsc.getPlanEstudiosFinal();
        asignaturasFinal = edEsc.getAsignaturasFinal();
        restriccionesFinal = edEsc.getRestriccionesFinal();
    }

    @FXML
    FlowPane mainFPant;
    @FXML
    FlowPane mainFPpost;

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

    public void updateBtnClicked() {
        ArrayList<Object> newNivs = new ArrayList<>();
        int i = 0;
        mainFPant.getChildren().clear();
        for (Node n : mainFPpost.getChildren()) {
            CheckBox cb = (CheckBox)n;
            if (cb.isSelected()) {
                newNivs.add(niveles.get(i));
                mainFPant.getChildren().add(new Label(niveles.get(i)));
            }
            ++i;
        }
        restriccionesFinal.replace("nivelHora", newNivs);
        edEsc.setRestriccionesFinal(restriccionesFinal);
    }

}
