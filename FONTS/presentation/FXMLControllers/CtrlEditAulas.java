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

public class CtrlEditAulas {

    private EditEscenario edEsc;
    private HashMap<String, ArrayList<Object>> aulasFinal;
    private String currentId;

    public CtrlEditAulas() {
        edEsc = EditEscenario.getInstance();
        aulasFinal = edEsc.getAulasFinal();
    }


    //ADD LAYOUT
    @FXML
    TextField textIdadd;
    @FXML
    TextField plazasIdadd;
    @FXML
    CheckBox chBtnTadd;
    @FXML
    CheckBox chBtnPadd;
    @FXML
    CheckBox chBtnLadd;
    @FXML
    Button addBtn;

    private boolean isInt(String num) {
        boolean isInt;
        try {
            Integer.parseInt(num);
            isInt = true;
        } catch (Exception e) {
            isInt = false;
        }
        return isInt;
    }

    public void addBtnClicked() {
        String id;
        if (textIdadd.getText().equals("")) {
            id = "AAA";
            System.out.println("FALTA ESPECIFICAR ID");
        } else {
            id = textIdadd.getText();
        }

        Integer plazas;
        if (isInt(plazasIdadd.getText()))
            plazas = Integer.parseInt(plazasIdadd.getText());
        else {
            System.out.println("LAS PLAZAS NO SON UN NÚMERO ENTERO");
            plazas = 1;
        }

        ArrayList<String> tipos = new ArrayList<>();
        if (chBtnTadd.isSelected())
            tipos.add("Teoria");
        if (chBtnPadd.isSelected())
            tipos.add("Problemas");
        if (chBtnLadd.isSelected())
            tipos.add("Laboratorio");

        ArrayList<Object> propiedades = new ArrayList<>();
        propiedades.add(plazas);
        propiedades.add(tipos);
        aulasFinal.put(id, propiedades);
        edEsc.setAulasFinal(aulasFinal);
    }

    //EDIT LAYOUT
    @FXML
    TextField textIdedit;
    @FXML
    TextField plazasIdedit;
    @FXML
    CheckBox chBtnTedit;
    @FXML
    CheckBox chBtnPedit;
    @FXML
    CheckBox chBtnLedit;
    @FXML
    Button editBtn;

    public void setEditLayout(String id, ArrayList<Object> attr) {
        currentId = id;
        ArrayList<Object> tipos = (ArrayList<Object>)attr.get(1);
        textIdedit.setText(id);
        plazasIdedit.setText(String.valueOf((Integer)attr.get(0)));
        chBtnTedit.setSelected(false);
        chBtnLedit.setSelected(false);
        chBtnPedit.setSelected(false);
        for (Object o : tipos) {
            if (o.toString().equals("Teoria"))
                chBtnTedit.setSelected(true);
            if (o.toString().equals("Laboratorio"))
                chBtnLedit.setSelected(true);
            if (o.toString().equals("Problemas"))
                chBtnPedit.setSelected(true);
        }
    }

    public void editBtnClicked() {

        String id;
        if (textIdedit.getText().equals("")) {
            id = "AAA";
            System.out.println("FALTA ESPECIFICAR ID");
        } else {
            id = textIdedit.getText();
        }

        Integer plazas;
        if (isInt(plazasIdedit.getText()))
            plazas = Integer.parseInt(plazasIdedit.getText());
        else {
            System.out.println("LAS PLAZAS NO SON UN NÚMERO ENTERO");
            plazas = 1;
        }
        ArrayList<String> tipos = new ArrayList<>();
        if (chBtnTedit.isSelected())
            tipos.add("Teoria");
        if (chBtnPedit.isSelected())
            tipos.add("Problemas");
        if (chBtnLedit.isSelected())
            tipos.add("Laboratorio");

        ArrayList<Object> propiedades = new ArrayList<>();
        propiedades.add(plazas);
        propiedades.add(tipos);
        aulasFinal.remove(currentId);
        aulasFinal.put(id, propiedades);
        edEsc.setAulasFinal(aulasFinal);
    }

    //EDIT LAYOUT
    @FXML
    Label labIdrm;
    @FXML
    Label labPlazasrm;
    @FXML
    CheckBox chBtnTrm;
    @FXML
    CheckBox chBtnPrm;
    @FXML
    CheckBox chBtnLrm;
    @FXML
    Button rmBtn;

    public void setRemoveLayout(String id, ArrayList<Object> attr) {
        currentId = id;
        ArrayList<Object> tipos = (ArrayList<Object>)attr.get(1);
        labIdrm.setText(id);
        labPlazasrm.setText(String.valueOf((Integer)attr.get(0)));
        chBtnTrm.setSelected(false);
        chBtnLrm.setSelected(false);
        chBtnPrm.setSelected(false);
        for (Object o : tipos) {
            if (o.toString().equals("Teoria"))
                chBtnTrm.setSelected(true);
            if (o.toString().equals("Laboratorio"))
                chBtnLrm.setSelected(true);
            if (o.toString().equals("Problemas"))
                chBtnPrm.setSelected(true);
        }
        chBtnTrm.setDisable(true);
        chBtnLrm.setDisable(true);
        chBtnPrm.setDisable(true);
    }

    public void removeBtnClicked() {
        aulasFinal.remove(currentId);
        edEsc.setAulasFinal(aulasFinal);
    }

}
