package presentation;

import javafx.collections.ObservableList;
import presentation.FXMLControllers.*;
import domain.Correquisito;
import domaincontrollers.CtrlDomain;
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


public class setRestricciones {

    private static setRestricciones sR;
    private CtrlRestricciones cR;
    private CtrlDomain cd;
    private String horarioStr;
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();
    private ArrayList<String> restrDL;
    private ArrayList<String> restrFT;
    private ArrayList<String> restrNH;
    private ArrayList<String> restrCO;
    private ArrayList<String> restrPRE;
    private ArrayList<String> restrFA;
    private ArrayList<String> restrFN;
    HashMap<String, ArrayList<Boolean>> restrCtrlD = new HashMap<>();

    /**Constructoras*/

    private setRestricciones() {
    }

    public static setRestricciones getInstance() {
        if (sR == null)
            sR = new setRestricciones() {
            };
        return sR;
    }

    public void setAllRestricciones(ArrayList<String> restrDL, ArrayList<String> restrFT,
                                     ArrayList<String> restrNH, ArrayList<String> restrCO,
                                     ArrayList<String> restrPRE, ArrayList<String> restrFA,
                                     ArrayList<String> restrFN) {
        this.restrDL = restrDL;
        this.restrFT = restrFT;
        this.restrNH = restrNH;
        this.restrCO = restrCO;
        this.restrPRE = restrPRE;
        this.restrFA = restrFA;
        this.restrFN = restrFN;
    }

    private void processRestricciones(ObservableList<String> restrDL, ObservableList<String> restrFT,
                                      ObservableList<String> restrNH, ObservableList<String> restrCO,
                                      ObservableList<String> restrPRE, ObservableList<String> restrFA,
                                      ObservableList<String> restrFN) {
        ArrayList<Boolean> boolRestDL = new ArrayList<>(Collections.nCopies(this.restrDL.size(), false));
        ArrayList<Boolean> boolRestFT = new ArrayList<>(Collections.nCopies(this.restrFT.size(), false));
        ArrayList<Boolean> boolRestNH = new ArrayList<>(Collections.nCopies(this.restrNH.size(), false));
        ArrayList<Boolean> boolRestCO = new ArrayList<>(Collections.nCopies(this.restrCO.size(), false));
        ArrayList<Boolean> boolRestPRE = new ArrayList<>(Collections.nCopies(this.restrPRE.size(), false));
        ArrayList<Boolean> boolRestFA = new ArrayList<>(Collections.nCopies(this.restrFA.size(), false));
        ArrayList<Boolean> boolRestFN = new ArrayList<>(Collections.nCopies(this.restrFN.size(), false));

        ArrayList<String> arrayRestrDL = new ArrayList<>();
        for (String s: restrDL)
            arrayRestrDL.add(s);
        if (this.restrDL.equals(arrayRestrDL)) {
            Collections.fill(boolRestDL, Boolean.TRUE);
        } else {
            if (restrDL.contains(this.restrDL.get(0))) {
                boolRestDL.set(0, true);
            } else if (restrDL.contains(this.restrDL.get(1))) {
                boolRestDL.set(1, true);
            }
        }

        ArrayList<String> arrayRestrFT = new ArrayList<>();
        for (String s: restrFT)
            arrayRestrFT.add(s);
        if (this.restrFT.equals(arrayRestrFT)) {
            Collections.fill(boolRestFT, true);
        } else {
            if (restrFT.contains(this.restrFT.get(0))) {
                boolRestFT.set(0, true);
            } else if (restrFT.contains(this.restrFT.get(1))) {
                boolRestFT.set(1, true);
            }
        }

        ArrayList<String> arrayRestrNH = new ArrayList<>();
        for (String s: restrNH)
            arrayRestrNH.add(s);
        if (this.restrNH.equals(arrayRestrNH)) {
            Collections.fill(boolRestNH, true);
        } else {
            for (int i = 0; i < this.restrNH.size(); ++i) {
                if (restrNH.contains(this.restrNH.get(i)))
                    boolRestNH.set(i, true);
            }
        }

        ArrayList<String> arrayRestrCO = new ArrayList<>();
        for (String s: restrCO)
            arrayRestrCO.add(s);
        if (this.restrCO.equals(arrayRestrCO)) {
            Collections.fill(boolRestCO, true);
        } else {
            for (int i = 0; i < this.restrCO.size(); ++i) {
                if (restrCO.contains(this.restrCO.get(i)))
                    boolRestCO.set(i, true);
            }
        }

        ArrayList<String> arrayRestrPRE = new ArrayList<>();
        for (String s: restrPRE)
            arrayRestrPRE.add(s);
        if (this.restrPRE.equals(arrayRestrPRE)) {
            Collections.fill(boolRestPRE, true);
        } else {
            for (int i = 0; i < this.restrPRE.size(); ++i) {
                if (restrPRE.contains(this.restrPRE.get(i)))
                    boolRestPRE.set(i, true);
            }
        }

        ArrayList<String> arrayRestrFA = new ArrayList<>();
        for (String s: restrFA)
            arrayRestrFA.add(s);
        if (this.restrFA.equals(arrayRestrFA)) {
            Collections.fill(boolRestFA, true);
        } else {
            for (int i = 0; i < this.restrFA.size(); ++i) {
                if (restrFA.contains(this.restrFA.get(i)))
                    boolRestFA.set(i, true);
            }
        }

        ArrayList<String> arrayRestrFN = new ArrayList<>();
        for (String s: restrFN)
            arrayRestrFN.add(s);
        if (this.restrFN.equals(arrayRestrFN)) {
            Collections.fill(boolRestFN, true);
        } else {
            for (int i = 0; i < this.restrFN.size(); ++i) {
                if (restrFN.contains(this.restrFN.get(i)))
                    boolRestFN.set(i, true);
            }
        }

        restrCtrlD = new HashMap<>();
        restrCtrlD.put("diaLibre", boolRestDL);
        restrCtrlD.put("franjaTrabajo", boolRestFT);
        restrCtrlD.put("nivelHora", boolRestNH);
        restrCtrlD.put("correquisitos", boolRestCO);
        restrCtrlD.put("prerrequisitos", boolRestPRE);
        restrCtrlD.put("franjaAsignatura", boolRestFA);
        restrCtrlD.put("franjaNivel", boolRestFN);
    }

    public void display(String escenario) {
        cd = CtrlPresentacion.getInstance().getCD();
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setResizable(false);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/confirmRestr.fxml"));
        cR = loader.getController();
        Parent root = null;
        try {
            root = loader.load();
            VBox vb = (VBox)root;

            //Label Escenario seleccionado
            Label escSel = (Label) vb.lookup("#escSel");
            escSel.setText(escSel.getText() + escenario);

            //TextField dar nombre al Horario
            HBox nomHorario = (HBox) vb.lookup("#nomHorario");
            TextField nomH = (TextField)nomHorario.lookup("#nomH");
            nomH.setText(escenario);

            //Restricciones
            HBox HBRestricciones = (HBox) vb.lookup("#HBRestricciones");
            VBox VBdiaLibre = (VBox)HBRestricciones.lookup("#VBdiaLibre");
            ListView<String> LVdiaLibre = (ListView<String>)VBdiaLibre.lookup("#LVdiaLibre");
            LVdiaLibre.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            for (String s : restrDL) {
                LVdiaLibre.getItems().add(s);
            }

            VBox VBfranjaTrabajo = (VBox)HBRestricciones.lookup("#VBfranjaTrabajo");
            ListView<String> LVfranjaTrabajo = (ListView<String>)VBfranjaTrabajo.lookup("#LVfranjaTrabajo");
            LVfranjaTrabajo.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            for (String s : restrFT) {
                LVfranjaTrabajo.getItems().add(s);
            }

            VBox VBnivelHora = (VBox)HBRestricciones.lookup("#VBnivelHora");
            ListView<String> LVnivelHora = (ListView<String>)VBnivelHora.lookup("#LVnivelHora");
            LVnivelHora.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            for (String s : restrNH) {
                LVnivelHora.getItems().add(s);
            }

            VBox VBcorrequisitos = (VBox)HBRestricciones.lookup("#VBcorrequisitos");
            ListView<String> LVcorrequisitos = (ListView<String>)VBcorrequisitos.lookup("#LVcorrequisitos");
            LVcorrequisitos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            for (String s : restrCO) {
                LVcorrequisitos.getItems().add(s);
            }

            VBox VBprerrequisitos = (VBox)HBRestricciones.lookup("#VBprerrequisitos");
            ListView<String> LVprerrequisitos = (ListView<String>)VBprerrequisitos.lookup("#LVprerrequisitos");
            LVprerrequisitos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            for (String s : restrPRE) {
                LVprerrequisitos.getItems().add(s);
            }

            VBox VBfranjaAsignatura = (VBox)HBRestricciones.lookup("#VBfranjaAsignatura");
            ListView<String> LVfranjaAsignatura = (ListView<String>)VBfranjaAsignatura.lookup("#LVfranjaAsignatura");
            LVfranjaAsignatura.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            for (String s : restrFA) {
                LVfranjaAsignatura.getItems().add(s);
            }

            VBox VBfranjaNivel = (VBox)HBRestricciones.lookup("#VBfranjaNivel");
            ListView<String> LVfranjaNivel = (ListView<String>)VBfranjaNivel.lookup("#LVfranjaNivel");
            LVfranjaNivel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            for (String s : restrFN) {
                LVfranjaNivel.getItems().add(s);
            }


            //Boton Cancelar
            HBox botButtons = (HBox) vb.lookup("#botButtons");
            Button cancelB = (Button) botButtons.lookup("#cancelB");
            cancelB.setOnAction(event -> {
                window.close();
            });

            //Boton selecionar todas
            Button selectAllBtn = (Button) botButtons.lookup("#selectAllBtn");
            selectAllBtn.setOnAction(event -> {
                LVdiaLibre.getSelectionModel().selectAll();
                LVfranjaTrabajo.getSelectionModel().selectAll();
                LVnivelHora.getSelectionModel().selectAll();
                LVcorrequisitos.getSelectionModel().selectAll();
                LVprerrequisitos.getSelectionModel().selectAll();
                LVfranjaAsignatura.getSelectionModel().selectAll();
                LVfranjaNivel.getSelectionModel().selectAll();
            });

            //Boton Confirmar
            Button confirmB = (Button) botButtons.lookup("#confirmB");
            confirmB.setOnAction(event -> {
                processRestricciones(LVdiaLibre.getSelectionModel().getSelectedItems(),
                        LVfranjaTrabajo.getSelectionModel().getSelectedItems(),
                        LVnivelHora.getSelectionModel().getSelectedItems(),
                        LVcorrequisitos.getSelectionModel().getSelectedItems(),
                        LVprerrequisitos.getSelectionModel().getSelectedItems(),
                        LVfranjaAsignatura.getSelectionModel().getSelectedItems(),
                        LVfranjaNivel.getSelectionModel().getSelectedItems());
                try {
                    String debug = cd.genHorarioDynamicRestrictions(nomH.getText(), restrCtrlD);

                    this.horarioStr = cd.generarHorario(nomH.getText());
                    cd.writeHorario(horarioStr, nomH.getText());
                    horario = cd.escaneaHorario(nomH.getText());
                } catch (Exception e) {
                    System.out.println("ERROR: CARGA DEL HORARIO FALLIDA");
                }

                showHorario sR = showHorario.getInstance();
                sR.display(nomH.getText());
                window.close();
            });

            window.setScene(new Scene(vb));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

}
