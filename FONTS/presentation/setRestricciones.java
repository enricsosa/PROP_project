/**setRestricciones*/

/**Imports*/

package presentation;

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

import presentation.FXMLControllers.*;
import domain.Correquisito;
import domaincontrollers.CtrlDomain;

/**
 * setRestricciones
 * @author  Enric Sosa
 * @see     ObservableList
 * @see     Scene
 * @see     Parent
 * @see     Button
 * @see     VBox
 * @see     Modality
 * @see     Stage
 * @see     FXMLLoader
 * @see     ArrayList
 * @see     Map
 * @see     HashMap
 */
public class setRestricciones {

    /**Instacia de setRestricciones.*/
    private static setRestricciones sR;
    /**CtrlRestricciones que se usa.*/
    private CtrlRestricciones cR;
    /**CtrlDomain que se usa.*/
    private CtrlDomain cd;
    /**Horario en forma de String.*/
    private String horarioStr;
    /**Horario*/
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();
    /**Restricciones DiaLibre.*/
    private ArrayList<String> restrDL;
    /**Restricciones FranjaTrabajo.*/
    private ArrayList<String> restrFT;
    /**Restricciones NivelHora.*/
    private ArrayList<String> restrNH;
    /**Restricciones Correquisito.*/
    private ArrayList<String> restrCO;
    /**Restricciones Prerrequisito.*/
    private ArrayList<String> restrPRE;
    /**Restricciones FranjaAsignatura.*/
    private ArrayList<String> restrFA;
    /**Restricciones FranjaNivel.*/
    private ArrayList<String> restrFN;
    /**Indica que restricciones estan habilitadas.*/
    HashMap<String, ArrayList<Boolean>> restrCtrlD = new HashMap<>();

    /**Constructoras*/

    /**Contructora de la clase setRestricciones.*/
    private setRestricciones() {
    }

    /**
     * Instanciadora de la clase setRestricciones.
     * @return  Instancia de setRestricciones.
     */
    public static setRestricciones getInstance() {
        if (sR == null)
            sR = new setRestricciones() {
            };
        return sR;
    }

    /**
     * Asigna nuevos valores a las restricciones.
     * @param restrDL   DiaLibre
     * @param restrFT   FranjaTrabajo
     * @param restrNH   NivelHora
     * @param restrCO   Correquisito
     * @param restrPRE  Prerrequisito
     * @param restrFA   FranjaAsignatura
     * @param restrFN   FranjaNivel
     */
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

    /**
     * Procesa las Restrcciones.
     * @param restrDL   DiaLibre
     * @param restrFT   FranjaTrabajo
     * @param restrNH   NivelHora
     * @param restrCO   Correquisito
     * @param restrPRE  Prerrequisito
     * @param restrFA   FranjaAsignatura
     * @param restrFN   FranjaNivel
     */
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
        if (arrayRestrFT.size() == 0) {
            Collections.fill(boolRestFT, Boolean.FALSE);
        } else if (this.restrFT.toString().equals(arrayRestrFT.get(0))) {
            Collections.fill(boolRestFT, Boolean.TRUE);
        } else {
            Collections.fill(boolRestFT, Boolean.FALSE);
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

    /**
     * Muestra setRestricciones.
     * @param escenario escenario que se aplica.
     */
    public void display(String escenario) {
        try {
            cd = CtrlDomain.getInstance();
        }
        catch (Exception e) {
            System.out.println("Error al obtener instancia de CtrlDomain.");
        }
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
            LVfranjaTrabajo.getItems().add(restrFT.toString());
            /*
            for (String s : restrFT) {
                LVfranjaTrabajo.getItems().add(s);
            }
            */

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
                    cd.habilitarRestricciones(restrCtrlD);

                    this.horarioStr = cd.generarHorario(nomH.getText());
                    cd.writeHorario(horarioStr, nomH.getText());

                } catch (Exception e) {
                    System.out.println("ERROR: ESCRITURA A DISCO DEL HORARIO FALLIDA");
                }

                boolean extend = false;
                if (restrDL.size() >= 2 && restrFT.size() >= 2) {
                    if (restrDL.contains("6") && restrDL.contains("7")) {
                        Integer horaIni = Integer.parseInt(restrFT.get(0));
                        Integer horaFin = Integer.parseInt(restrFT.get(1));
                        if (horaIni >= 8 && horaFin <= 20) {
                            //Las restricciones iniciales lo permiten, ahora hay que comprovar si están acitvas
                            if (restrCtrlD.get("franjaTrabajo").get(0) &&
                                restrCtrlD.get("franjaTrabajo").get(1) &&
                                restrCtrlD.get("diaLibre").get(restrDL.indexOf("6")) &&
                                restrCtrlD.get("diaLibre").get(restrDL.indexOf("7"))) {
                                try {
                                    cd.writeExtension("false", nomH.getText());
                                    horario = cd.escaneaHorario(nomH.getText(), false);
                                } catch ( Exception e) {
                                    System.out.println("ERROR: CREACION DEL HORARIO FALLIDA");
                                }
                                showHorario sR = showHorario.getInstance();
                                sR.display(nomH.getText());
                            } else {
                                extend = true;
                            }
                        } else {
                            extend = true;
                        }
                    } else {
                        extend = true;
                    }
                } else {
                    extend = true;
                }
                if (extend) {
                    try {
                        cd.writeExtension("true", nomH.getText());
                        horario = cd.escaneaHorario(nomH.getText(), true);
                    } catch ( Exception e) {
                        System.out.println("ERROR: CREACION DEL HORARIO EXTENDIDO FALLIDA");
                    }
                    showExtendedHorario sER = showExtendedHorario.getInstance();
                    sER.display(nomH.getText());
                }
                window.close();
            });

            window.setScene(new Scene(vb));
            window.showAndWait();
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

}
