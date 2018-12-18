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


public class CtrlEditRdiaLibre {

        private EditEscenario edEsc;
        private ArrayList<String> planEstudiosFinal;
        private HashMap<String, ArrayList<Object>> asignaturasFinal;
        private HashMap<String, ArrayList<Object>> restriccionesFinal;
        private String currentId;

        public CtrlEditRdiaLibre() {
            edEsc = EditEscenario.getInstance();
            planEstudiosFinal = edEsc.getPlanEstudiosFinal();
            asignaturasFinal = edEsc.getAsignaturasFinal();
            restriccionesFinal = edEsc.getRestriccionesFinal();
        }

    @FXML
    CheckBox lunesAnt;
    @FXML
    CheckBox martesAnt;
    @FXML
    CheckBox miercolesAnt;
    @FXML
    CheckBox juevesAnt;
    @FXML
    CheckBox viernesAnt;
    @FXML
    CheckBox sabadoAnt;
    @FXML
    CheckBox domingoAnt;

    @FXML
    CheckBox lunesPost;
    @FXML
    CheckBox martesPost;
    @FXML
    CheckBox miercolesPost;
    @FXML
    CheckBox juevesPost;
    @FXML
    CheckBox viernesPost;
    @FXML
    CheckBox sabadoPost;
    @FXML
    CheckBox domingoPost;

    @FXML
    Button updateBtn;

    public void setLayout(ArrayList<Object> diasLibres) {
        lunesAnt.setSelected(false);
        lunesPost.setSelected(false);
        martesAnt.setSelected(false);
        martesPost.setSelected(false);
        miercolesAnt.setSelected(false);
        miercolesPost.setSelected(false);
        juevesAnt.setSelected(false);
        juevesPost.setSelected(false);
        viernesAnt.setSelected(false);
        viernesPost.setSelected(false);
        sabadoAnt.setSelected(false);
        sabadoPost.setSelected(false);
        domingoAnt.setSelected(false);
        domingoPost.setSelected(false);
        if (diasLibres.contains(1)) {
            lunesAnt.setSelected(true);
            lunesPost.setSelected(true);
        }
        if (diasLibres.contains(2)) {
            martesAnt.setSelected(true);
            martesPost.setSelected(true);
        }
        if (diasLibres.contains(3)) {
            miercolesAnt.setSelected(true);
            miercolesPost.setSelected(true);
        }
        if (diasLibres.contains(4)) {
            juevesAnt.setSelected(true);
            juevesPost.setSelected(true);
        }
        if (diasLibres.contains(5)) {
            viernesAnt.setSelected(true);
            viernesPost.setSelected(true);
        }
        if (diasLibres.contains(6)) {
            sabadoAnt.setSelected(true);
            sabadoPost.setSelected(true);
        }
        if (diasLibres.contains(7)) {
            domingoAnt.setSelected(true);
            domingoPost.setSelected(true);
        }
        lunesAnt.setDisable(true);
        martesAnt.setDisable(true);
        miercolesAnt.setDisable(true);
        juevesAnt.setDisable(true);
        viernesAnt.setDisable(true);
        sabadoAnt.setDisable(true);
        domingoAnt.setDisable(true);
    }

    public void updateBtnClicked() {
        lunesAnt.setSelected(lunesPost.isSelected());
        martesAnt.setSelected(martesPost.isSelected());
        miercolesAnt.setSelected(miercolesPost.isSelected());
        juevesAnt.setSelected(juevesPost.isSelected());
        viernesAnt.setSelected(viernesPost.isSelected());
        sabadoAnt.setSelected(sabadoPost.isSelected());
        domingoAnt.setSelected(domingoPost.isSelected());

        ArrayList<Object> dLs = new ArrayList<>();
        if (lunesPost.isSelected())
            dLs.add(1);
        if (martesPost.isSelected())
            dLs.add(2);
        if (miercolesPost.isSelected())
            dLs.add(3);
        if (juevesPost.isSelected())
            dLs.add(4);
        if (viernesPost.isSelected())
            dLs.add(5);
        if (sabadoPost.isSelected())
            dLs.add(6);
        if (domingoPost.isSelected())
            dLs.add(7);
        restriccionesFinal.replace("diaLibre", dLs);
        edEsc.setRestriccionesFinal(restriccionesFinal);
    }

}