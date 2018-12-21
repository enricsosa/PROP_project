/**CtrlEditAulas*/

/**Imports*/

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


public class CtrlEditGrupos {

    /**Instancia de EditEscenario.*/
    private EditEscenario edEsc;
    /**PlanEstudios despues de editar.*/
    private ArrayList<String> planEstudiosFinal;
    /**Asignaturas despues de editar.*/
    private HashMap<String, ArrayList<Object>> asignaturasFinal;
    /**Restriciones despues de editar.*/
    private HashMap<String, ArrayList<Object>> restriccionesFinal;
    /**Restriccion seleccionada.*/
    private char currentGr;
    /**Restriccion seleccionada.*/
    private char currentSGr;
    private String currentType;
    private Integer currentPlazas;
    /**idAsignatura1.*/
    private ArrayList<ArrayList<Object>> currentGrupos;
    /**idAsignatura2.*/
    private String currentAsignatura = null;
    /**Instancia de CtrlDomain.*/
    private CtrlDomain cd;

    /**Constructora de la clase CtrlEditAulas.*/
    public CtrlEditGrupos() {
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

    //ADD LAYOUT
    @FXML
    ChoiceBox idGadd;
    @FXML
    ChoiceBox idSadd;
    @FXML
    TextField plazasIdadd;
    @FXML
    RadioButton rbTadd;
    @FXML
    RadioButton rbPadd;
    @FXML
    RadioButton rbLadd;
    @FXML
    Button addBtn;

    /**Accion que se ejecuta al pulsar el botón añadir.*/
    public void addBtnClicked() {
        if (isInt(plazasIdadd.getText())) {
            if (cd.addGrupo(idGadd.getValue().toString(), currentAsignatura) >= 0) {
                String tipo = "";
                if (rbTadd.isSelected())
                    tipo = "Teoria";
                if (rbPadd.isSelected())
                    tipo = "Problemas";
                if (rbLadd.isSelected())
                    tipo = "Laboratorio";
                if (cd.addSubGrupo(idSadd.getValue().toString(), Integer.parseInt(plazasIdadd.getText()), tipo, idGadd.getValue().toString(), currentAsignatura) >= 0) {
                    ArrayList<Object> as = new ArrayList<>();
                    as.add(idSadd.getValue().toString());
                    as.add(Integer.parseInt(plazasIdadd.getText()));
                    as.add(tipo);
                    boolean found = false;
                    int i;
                    for (i = 0; i < currentGrupos.size() && !found; ++i) {
                        found = currentGrupos.get(i).get(0).equals(idGadd.getValue().toString());
                    }
                    if (found) {
                        currentGrupos.get(i).add(as);
                    } else {
                        ArrayList<Object> g = new ArrayList<>();
                        ArrayList<Object> subg = new ArrayList<>();
                        g.add(idGadd.getValue().toString());
                        subg.add(as);
                        g.add(subg);
                        currentGrupos.add(g);
                    }

                    listview.getItems().clear();
                    for (ArrayList<Object> g: currentGrupos) {
                        String idG = (String)g.get(0);
                        ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)g.get(1);
                        for (ArrayList<Object> sg : subgr) {
                            listview.getItems().add(idG + sg.get(0) + " " + sg.get(2));
                        }
                    }
                } else {
                    //TRANSACCION INVALIDA
                }
            } else {
                String tipo = "";
                if (rbTadd.isSelected())
                    tipo = "Teoria";
                if (rbPadd.isSelected())
                    tipo = "Problemas";
                if (rbLadd.isSelected())
                    tipo = "Laboratorio";
                if (cd.addSubGrupo(idSadd.getValue().toString(), Integer.parseInt(plazasIdadd.getText()), tipo, idGadd.getValue().toString(), currentAsignatura) >= 0) {
                    ArrayList<Object> as = new ArrayList<>();
                    as.add(idSadd.getValue().toString());
                    as.add(Integer.parseInt(plazasIdadd.getText()));
                    as.add(tipo);

                    boolean found = false;
                    int i;
                    for (i = 0; i < currentGrupos.size() && !found; ++i) {
                        found = currentGrupos.get(i).get(0).equals(idGadd.getValue().toString());
                    }
                    if (found) {
                        ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)currentGrupos.get(i-1).get(1);
                        subgr.add(as);
                        currentGrupos.get(i-1).remove(1);
                        currentGrupos.get(i-1).add(subgr);
                    }

                    listview.getItems().clear();
                    for (ArrayList<Object> g: currentGrupos) {
                        String idG = (String)g.get(0);
                        ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)g.get(1);
                        for (ArrayList<Object> sg : subgr) {
                            listview.getItems().add(idG + sg.get(0) + " " + sg.get(2));
                        }
                    }
                }
            }
        }
    }


    //EDIT LAYOUT
    @FXML
    Label idGedit;
    @FXML
    Label idSedit;
    @FXML
    Label plazasIdedit;
    @FXML
    RadioButton rbTedit;
    @FXML
    RadioButton rbPedit;
    @FXML
    RadioButton rbLedit;
    @FXML
    Button editBtn;

    /**Accion que se ejecuta al pulsar el botón añadir.*/
    public void editBtnClicked() {
        String tipo = "";
        if (rbTedit.isSelected())
            tipo = "Teoria";
        if (rbPedit.isSelected())
            tipo = "Problemas";
        if (rbLedit.isSelected())
            tipo = "Laboratorio";
        if (cd.eliminarSubGrupo(idSedit.getText(), tipo, idGedit.getText(), currentAsignatura) >= 0) {
            boolean found = false;
            int i;
            for (i = 0; i < currentGrupos.size() && !found; ++i) {
                found = currentGrupos.get(i).get(0).equals(idGadd.getValue().toString());
            }
            if (found) {
                ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)currentGrupos.get(i - 1).get(1);
                int j = 0;
                for (ArrayList<Object> sg : subgr) {
                    if (sg.get(0).equals(Character.toString(currentSGr))) {
                        subgr.remove(j);
                    }
                    ++j;
                }
                currentGrupos.get(i - 1).remove(1);
                currentGrupos.get(i - 1).add(subgr);
            }

            listview.getItems().clear();
            for (ArrayList<Object> g: currentGrupos) {
                String idG = (String)g.get(0);
                ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)g.get(1);
                for (ArrayList<Object> sg : subgr) {
                    listview.getItems().add(idG + sg.get(0) + " " + sg.get(2));
                }
            }
        } else {
            //TRANSACCIO INVALIDA
        }
    }


    //REMOVE LAYOUT
    @FXML
    Label idGrm;
    @FXML
    Label idSrm;
    @FXML
    Label plazasIdrm;
    @FXML
    RadioButton rbTrm;
    @FXML
    RadioButton rbPrm;
    @FXML
    RadioButton rbLrm;
    @FXML
    Button rmBtn;

    /**Accion que se ejecuta al pulsar el botón añadir.*/
    public void removeBtnClicked() {
        if (cd.eliminarGrupo(idGrm.getText(), currentAsignatura) >= 0) {
            boolean found = false;
            int i;
            for (i = 0; i < currentGrupos.size() && !found; ++i) {
                found = currentGrupos.get(i).get(0).equals(idGrm.getText());
            }
            if (found) {
                currentGrupos.remove(i-1);
            }

            listview.getItems().clear();
            for (ArrayList<Object> g: currentGrupos) {
                String idG = (String)g.get(0);
                ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)g.get(1);
                for (ArrayList<Object> sg : subgr) {
                    listview.getItems().add(idG + sg.get(0) + " " + sg.get(2));
                }
            }
        } else {
            //TRANSACCIO INVALIDA
        }
    }

    private void setLayout() {

        //EDIT & REMOVE
        rbTrm.setDisable(true);
        rbPrm.setDisable(true);
        rbLrm.setDisable(true);
        if (currentType.equals("Teoria")) {
            rbTedit.setSelected(true);
            rbPedit.setSelected(false);
            rbLedit.setSelected(false);
            rbTrm.setSelected(true);
            rbPrm.setSelected(false);
            rbLrm.setSelected(false);
        } else if (currentType.equals("Problemas")) {
            rbTedit.setSelected(false);
            rbPedit.setSelected(true);
            rbLedit.setSelected(false);
            rbTrm.setSelected(false);
            rbPrm.setSelected(true);
            rbLrm.setSelected(false);
        } else if (currentType.equals("Laboratorio")) {
            rbTedit.setSelected(false);
            rbPedit.setSelected(false);
            rbLedit.setSelected(true);
            rbTrm.setSelected(false);
            rbPrm.setSelected(false);
            rbLrm.setSelected(true);
        }
        if (!Character.toString(currentSGr).equals("0"))
            rmBtn.setDisable(true);
        else
            rmBtn.setDisable(false);
        idGrm.setText(Character.toString(currentGr));
        idSrm.setText(Character.toString(currentSGr));
        idGedit.setText(Character.toString(currentGr));
        idSedit.setText(Character.toString(currentSGr));
        plazasIdrm.setText(currentPlazas.toString());
        plazasIdedit.setText(currentPlazas.toString());
    }

    @FXML
    ListView listview;

    ChangeListener<String> grSelected = this::itemSelected;

    /**Asigna un objeto como objetivo de edicion.*/
    private void itemSelected(ObservableValue<? extends String> observableValue, String s, String t1) {
        if (!(t1 == null)) {

            listview.getSelectionModel().selectedItemProperty().removeListener(grSelected);
            listview.getSelectionModel().selectedItemProperty().addListener(grSelected);

            currentGr = t1.charAt(0);
            currentSGr = t1.charAt(1);
            currentType = t1.substring(t1.indexOf(" ")+1);
            for (ArrayList<Object> g: currentGrupos) {
                String idG = (String)g.get(0);
                if (idG.equals(Character.toString(currentGr))) {
                    ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)g.get(1);
                    for (ArrayList<Object> sg : subgr) {
                        if (sg.get(0).equals(Character.toString(currentSGr))) {
                            currentPlazas = (Integer) sg.get(1);
                        }
                    }
                }
            }
            setLayout();
        }
    }

    private void setRadioButtons(String rb) {
        switch (rb) {
            case "Tadd":
                rbPadd.setSelected(false);
                rbLadd.setSelected(false);
                break;
            case "Padd":
                rbTadd.setSelected(false);
                rbLadd.setSelected(false);
                break;
            case "Ladd":
                rbTadd.setSelected(false);
                rbPadd.setSelected(false);
                break;
            case "Tedit":
                rbPedit.setSelected(false);
                rbLedit.setSelected(false);
                break;
            case "Pedit":
                rbTedit.setSelected(false);
                rbLedit.setSelected(false);
                break;
            case "Ledit":
                rbTedit.setSelected(false);
                rbPedit.setSelected(false);
                break;
            default:
                break;
        }
    }

    /**Asigna un elemento a layout.*/
    public void display(boolean editing, ArrayList<ArrayList<Object>> grupos, String idAsignatura) {
        currentAsignatura = idAsignatura;
        if (editing) {
            currentGrupos = grupos;
            System.out.println(grupos);

            listview.getItems().clear();
            Integer i = 1;
            for (ArrayList<Object> g: grupos) {
                String idG = (String)g.get(0);
                ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)g.get(1);
                for (ArrayList<Object> sg : subgr) {
                    listview.getItems().add(idG + sg.get(0) + " " + sg.get(2));
                }
            }
            listview.getSelectionModel().selectedItemProperty().removeListener(grSelected);
            listview.getSelectionModel().selectedItemProperty().addListener(grSelected);
        } else {
            currentGrupos = new ArrayList<>();
        }
        idGadd.getItems().clear();
        idGadd.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
        idSadd.getItems().clear();
        idSadd.setItems(FXCollections.observableArrayList("0", "1", "2", "3", "4", "5", "6", "7", "8", "9"));

        rbTadd.setOnAction( e -> setRadioButtons("Tadd"));
        rbPadd.setOnAction( e -> setRadioButtons("Padd"));
        rbLadd.setOnAction( e -> setRadioButtons("Ladd"));
        rbTedit.setOnAction( e -> setRadioButtons("Tedit"));
        rbPedit.setOnAction( e -> setRadioButtons("Pedit"));
        rbLedit.setOnAction( e -> setRadioButtons("Ledit"));
    }

}
