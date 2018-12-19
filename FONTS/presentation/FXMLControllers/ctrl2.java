/**ctrl2*/

/**Imports*/

package presentation.FXMLControllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import presentation.*;
import domaincontrollers.CtrlDomain;

/**
 * ctrl2
 * @author  Enric Sosa
 * @see     FXML
 * @see     Button
 * @see     BorderPane
 * @see     ArrayList
 * @see     CtrlDomain
 * @see     CtrlPresentacion
 * @see     SceneController
 * @see     HashMap
 * @see     Map
 */
public class ctrl2 {

    /**SceneControler que se usa.*/
    private SceneController sc;
    /**CtrlPresentacion de ctrl2.*/
    private CtrlPresentacion cp;
    /**CtrlDomain que se usa.*/
    private CtrlDomain cd;
    /**Datos del escenario actual.*/
    private String currentEscenario;
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

    private ArrayList<String> planEstudiosCD;
    private HashMap<String, ArrayList<Object>> asignaturasCD;
    private HashMap<String, ArrayList<Object>> aulasCD;
    private HashMap<String, ArrayList<Object>> restriccionesCD;

    @FXML
    public Label escenario_label;

    @FXML
    public Button genHor;

    @FXML
    public Button editEsc;

    private TreeItem<String> doBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }

    /**
     * Convierte un numero que representa un dia de la semana en su nombre.
     * @param num   dia de la semana.
     * @return      Nombre del dia de la semana.
     */
    private String stringToDiaSemana(String num) {
        String dia;
        switch (num) {
            case "1":
                dia = "Lunes";
                break;
            case "2":
                dia = "Martes";
                break;
            case "3":
                dia = "Miércoles";
                break;
            case "4":
                dia = "Jueves";
                break;
            case "5":
                dia = "Viernes";
                break;
            case "6":
                dia = "Sábado";
                break;
            case "7":
                dia = "Domingo";
                break;
            default:
                dia = "ERROR EN JSON";
                break;
        }
        return dia;
    }

    private TreeView<String> createTreeEscenario(String s) {
        restrDL = new ArrayList<>();
        restrFT = new ArrayList<>();
        restrNH = new ArrayList<>();
        restrCO = new ArrayList<>();
        restrPRE = new ArrayList<>();
        restrFA = new ArrayList<>();
        restrFN = new ArrayList<>();

        TreeView<String> tree;
        TreeItem<String> root, planEstudios, asignaturas, aulas, restricciones;
        root = new TreeItem<>();
        root.setExpanded(true);

        //PlanEstudios
        planEstudiosCD = new ArrayList<>();
        try {
            planEstudiosCD = cd.subirPlanEstudios(s);
        } catch (Exception e) {
            System.out.println("ERROR: CARGA DEL PLAN DE ESTUDIOS FALLIDA");
        }
        planEstudios = doBranch("Plan Estudios", root);
        TreeItem<String> niveles;
        doBranch("Nombre: " + planEstudiosCD.get(0), planEstudios);
        niveles = doBranch("Niveles", planEstudios);
        for (int i = 1; i < planEstudiosCD.size(); ++i) {
            doBranch(planEstudiosCD.get(i), niveles);
        }

        //Asignaturas
        asignaturasCD = new HashMap<>();
        try {
            asignaturasCD = cd.subirAsignaturas(s);
        } catch (Exception e) {
            System.out.println("ERROR: CARGA DE LAS ASIGNATURAS FALLIDA");
        }
        asignaturas = doBranch("Asignaturas", root);
        for (Map.Entry<String, ArrayList<Object>> asig : asignaturasCD.entrySet()) {
            TreeItem<String> asignatura = doBranch(asig.getKey(), asignaturas);
            ArrayList<Object> asigProperties = asig.getValue();
            //System.out.println(asigProperties);
            doBranch("Nombre: " + (String)asigProperties.get(0), asignatura);
            doBranch("Nivel: " + (String)asigProperties.get(1), asignatura);

            TreeItem<String> sesiones = doBranch("Sesiones", asignatura);
            ArrayList<ArrayList<Object>> sesProperties = (ArrayList<ArrayList<Object>>)asigProperties.get(2);
            Integer i = 1;
            for (ArrayList<Object> ses : sesProperties) {
                TreeItem<String> sesion = doBranch("Sesion#" + i.toString(), sesiones);
                doBranch("Duración: " + ses.get(0) + "h", sesion);
                doBranch("Tipo Clase: " + ses.get(1), sesion);
                ++i;
            }

            TreeItem<String> grupos = doBranch("Grupos", asignatura);
            ArrayList<ArrayList<Object>> grProperties = (ArrayList<ArrayList<Object>>)asigProperties.get(3);
            for (ArrayList<Object> gr : grProperties) {
                TreeItem<String> grupo = doBranch((String)gr.get(0) + "0", grupos);
                ArrayList<ArrayList<Object>> subgr = (ArrayList<ArrayList<Object>>)gr.get(1);
                for (ArrayList<Object> sg : subgr) {
                    TreeItem<String> subg = doBranch((String)gr.get(0) + (String)sg.get(0), grupo);
                    doBranch("Plazas: " + sg.get(1), subg);
                    doBranch("Tipo Clase: " + sg.get(2), subg);
                }
            }
        }

        //Aulas
        aulasCD = new HashMap<>();
        try {
            aulasCD = cd.subirAulas(s);
        } catch (Exception e) {
            System.out.println("ERROR: CARGA DE LAS AULAS FALLIDA");
        }
        aulas = doBranch("Aulas", root);
        for (Map.Entry<String, ArrayList<Object>> au : aulasCD.entrySet()) {
            TreeItem<String> aula = doBranch(au.getKey(), aulas);
            ArrayList<Object> aulaProperties = au.getValue();
            doBranch("Plazas: " + aulaProperties.get(0).toString(), aula);

            TreeItem<String> tC = doBranch("Tipos Clase", aula);
            ArrayList<Object> tipos = (ArrayList<Object>)aulaProperties.get(1);
            for (Object t : tipos) {
                doBranch(t.toString(), tC);
            }
        }

        //Restricciones
        restriccionesCD = new HashMap<>();
        try {
            restriccionesCD = cd.subirRestricciones(s);
        } catch (Exception e) {
            System.out.println("ERROR: CARGA DE LAS RESTRICCIONES FALLIDA");
        }
        restricciones = doBranch("Restricciones", root);

        TreeItem<String> diaLibre, franjaTrabajo, nivelHora, correquisitos, prerrequisitos, franjaAsignatura, franjaNivel;

        diaLibre = doBranch("diaLibre", restricciones);
        ArrayList<Object> dLs = (ArrayList<Object>)restriccionesCD.get("diaLibre");
        for (Object dl : dLs) {
            doBranch(stringToDiaSemana(dl.toString()), diaLibre);
            restrDL.add(dl.toString());
        }

        franjaTrabajo = doBranch("franjaTrabajo", restricciones);
        ArrayList<Object> horasTrabajo = (ArrayList<Object>)restriccionesCD.get("franjaTrabajo");
        doBranch("Hora inicial: " + horasTrabajo.get(0).toString() + "h", franjaTrabajo);
        restrFT.add(horasTrabajo.get(0).toString());
        doBranch("Hora final: " + horasTrabajo.get(1).toString() + "h", franjaTrabajo);
        restrFT.add(horasTrabajo.get(1).toString());


        nivelHora = doBranch("nivelHora", restricciones);
        ArrayList<Object> nHs = (ArrayList<Object>)restriccionesCD.get("nivelHora");
        for (Object nh : nHs) {
            doBranch(nh.toString(), nivelHora);
            restrNH.add(nh.toString());
        }

        correquisitos = doBranch("correquisitos", restricciones);
        ArrayList<Object> corres = (ArrayList<Object>)restriccionesCD.get("correquisitos");
        for (Object co : corres) {
            doBranch(co.toString(), correquisitos);
            restrCO.add(co.toString());
        }

        prerrequisitos = doBranch("prerrequisitos", restricciones);
        ArrayList<Object> pres = (ArrayList<Object>)restriccionesCD.get("prerrequisitos");
        for (Object pr : pres) {
            String strPre = pr.toString();
            restrPRE.add(pr.toString());
            strPre = strPre.replace(",", ", Prerrequisito: ");
            doBranch(strPre, prerrequisitos);
        }

        franjaAsignatura = doBranch("franjaAsignatura", restricciones);
        ArrayList<Object> frAsigs = (ArrayList<Object>)restriccionesCD.get("franjaAsignatura");
        for (Object fa : frAsigs) {
            String faS = fa.toString();
            restrFA.add(faS);
            String id = faS.substring(faS.indexOf("[")+1, faS.indexOf(","));
            TreeItem<String> idAsig = doBranch(id, franjaAsignatura);

            String hI = faS.substring(faS.indexOf(",")+2, faS.indexOf(",", faS.indexOf(",")+1));
            doBranch("Hora inicial: " + hI + "h", idAsig);

            String hF = faS.substring(faS.indexOf(",", faS.indexOf(",")+1)+2, faS.indexOf("]"));
            doBranch("Hora final: " + hF + "h", idAsig);
        }


        franjaNivel = doBranch("franjaNivel", restricciones);
        ArrayList<Object> frNiveles = (ArrayList<Object>)restriccionesCD.get("franjaNivel");
        for (Object fn : frNiveles) {
            String fnS = fn.toString();
            restrFN.add(fnS);
            String id = fnS.substring(fnS.indexOf("[")+1, fnS.indexOf(","));
            TreeItem<String> idNivel = doBranch(id, franjaNivel);

            String hI = fnS.substring(fnS.indexOf(",")+2, fnS.indexOf(",", fnS.indexOf(",")+1));
            doBranch("Hora inicial: " + hI + "h", idNivel);

            String hF = fnS.substring(fnS.indexOf(",", fnS.indexOf(",")+1)+2, fnS.indexOf("]"));
            doBranch("Hora final: " + hF + "h", idNivel);
        }


        tree = new TreeView<>(root);
        tree.setShowRoot(false);
        return tree;
    }

    public void escenarioSeleccionado(String s) {
        genHor.setDisable(false);
        editEsc.setDisable(false);
        cd.seleccionarEscenario(s);
        currentEscenario = s;
        escenario_label.setText(s);
        TreeView<String> tree = createTreeEscenario(s);
        BorderPane bP = (BorderPane)sc.getPane("prova2");
        bP.setCenter(tree);
    }

    /**Abre la pantalla de generarHorario.*/
    public void generarHorario() {
        setRestricciones sR = setRestricciones.getInstance();
        sR.setAllRestricciones(restrDL, restrFT, restrNH, restrCO, restrPRE, restrFA, restrFN);
        sR.display(currentEscenario);
    }

    public void editarEscenario() {
        edit_new_escenario ene = edit_new_escenario.getInstance();
        ene.display(escenario_label.getText(), planEstudiosCD, asignaturasCD, aulasCD, restriccionesCD);
    }

    /**Constructora de la clase ctrl2.*/
    public ctrl2() throws Exception{
        sc = SceneController.getInstance();
        cp = CtrlPresentacion.getInstance();
        cd = CtrlDomain.getInstance();
    }

    /**Asigna un mainMenu.*/
    public void setMainMenu() throws Exception {
        sc.activate("prova1");
    }

    /**Inicializa ctrl2.*/
    @FXML
    private void initialize() {
        editEsc.setDisable(true);
        genHor.setDisable(true);
    }
}