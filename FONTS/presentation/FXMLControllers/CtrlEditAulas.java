/**CtrlEditAulas*/

/**Imports*/

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
 * Se encarga de gestionar presentación de edición de Aulas.
 * @author  Enric Sosa
 * @see     FXML
 * @see     ArrayList
 * @see     HashMap
 * @see     EditEscenario
 * @see     CtrlDomain
 */
public class CtrlEditAulas {

    /**Instancia de editEscenario.*/
    private EditEscenario edEsc;
    /**Aulas después de la edición.*/
    private HashMap<String, ArrayList<Object>> aulasFinal;
    /**Id del aula seleccionada.*/
    private String currentId;
    /**Instancia de CtrlDomain.*/
    private CtrlDomain cd;

    /**Constructora de la clase CtrlEditAulas.*/
    public CtrlEditAulas() {
        edEsc = EditEscenario.getInstance();
        try {
            cd = CtrlDomain.getInstance();
        } catch (Exception e) {
            System.out.println("ERROR EN LA CARGA DEL CONTROLADOR DE DOMINIO");
        }
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

    /**
     * Devuelve si un string representa un entero.
     * @param num   string a evaluar
     * @return      true si es entero, false en caso opuesto.
     */
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

    /**Accion ejecutada al pulsar el botón añadir.*/
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

        String[] tiposArray = new String[tipos.size()];
        tiposArray = tipos.toArray(tiposArray);

        ArrayList<Object> propiedades = new ArrayList<>();
        propiedades.add(plazas);
        propiedades.add(tipos);
        //CTRLDOMAIN
        if (cd.addAula(id, plazas, tiposArray) < 0) {
            System.out.println("ERROR: EL AULA CON ID: \"" + id + "\" YA EXISTE EN EL PLAN DE ESTUDIOS");
        }
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

    /**
     * Asigan un Aula al edit layout.
     * @param id    id del Aula.
     * @param attr  atributos del Aula.
     */
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

    /**Accion que se ejecuta al pulsar el botón editar.*/
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

        String[] tiposArray = new String[tipos.size()];
        tiposArray = tipos.toArray(tiposArray);
        //CTRLDOMAIN
        cd.editarIdAula(currentId, id);
        cd.editarIdAula(id, plazas);
        cd.editarTiposAula(id, tiposArray);

        aulasFinal.remove(currentId);
        aulasFinal.put(id, propiedades);
        edEsc.setAulasFinal(aulasFinal);
    }

    //REMOVE LAYOUT
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

    /**
     * Asigan un Aula al remove layout.
     * @param id    id del Aula.
     * @param attr  atributos del Aula.
     */
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

    /**Accion que se ejecuta al pulsar el botón borrar.*/
    public void removeBtnClicked() {
        //CTRLDOMAIN
        cd.eliminarAula(currentId);
        aulasFinal.remove(currentId);
        edEsc.setAulasFinal(aulasFinal);
    }

}
