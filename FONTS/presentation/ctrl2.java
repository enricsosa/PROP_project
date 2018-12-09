package presentation;

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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ctrl2 {

    private SceneController sc;
    private CtrlPresentacion cp;
    private CtrlDomain cd;
    private String currentEscenario;

    @FXML
    public Label escenario_label;

    private TreeItem<String> doBranch(String title, TreeItem<String> parent) {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }

    private TreeItem<Button> doBranch2(String title, TreeItem<Button> parent){
        Button b = new Button(title);
        TreeItem<Button> item = new TreeItem<Button>(b);
        item.setExpanded(false);
        parent.getChildren().add(item);
        return item;
    }

    private TreeView<Button> prova() {
        VBox vb = new VBox();
        TreeView<String> tv = new TreeView<>();
        vb.clipProperty();
        System.out.println(vb.clipProperty());
        System.out.println(vb.getClip());
        System.out.println(tv.clipProperty());
        System.out.println(tv.getClip());
        TreeView<Button> tree;
        TreeItem<Button> root, planEstudios, asignaturas, aulas, restricciones;
        root = new TreeItem<>();
        root.setExpanded(true);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);
        doBranch2("Plan Estudios", root);doBranch2("Plan Estudios", root);

        //doBranch2("planEstudios", planEstudios);

        tree = new TreeView<>(root);
        tree.setShowRoot(false);



        return tree;
    }

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
        TreeView<String> tree;
        TreeItem<String> root, planEstudios, asignaturas, aulas, restricciones;
        root = new TreeItem<>();
        root.setExpanded(true);

        //PlanEstudios
        ArrayList<String> planEstudiosCD = new ArrayList<>();
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
        HashMap<String, ArrayList<Object>> asignaturasCD = new HashMap<>();
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
        HashMap<String, ArrayList<Object>> aulasCD = new HashMap<>();
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
        HashMap<String, ArrayList<Object>> restriccionesCD = new HashMap<>();
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
        }

        franjaTrabajo = doBranch("franjaTrabajo", restricciones);
        ArrayList<Object> horasTrabajo = (ArrayList<Object>)restriccionesCD.get("franjaTrabajo");
        doBranch("Hora inicial: " + horasTrabajo.get(0).toString() + "h", franjaTrabajo);
        doBranch("Hora final: " + horasTrabajo.get(1).toString() + "h", franjaTrabajo);


        nivelHora = doBranch("nivelHora", restricciones);
        ArrayList<Object> nHs = (ArrayList<Object>)restriccionesCD.get("nivelHora");
        for (Object nh : nHs) {
            doBranch(nh.toString(), nivelHora);
        }


        correquisitos = doBranch("correquisitos", restricciones);
        ArrayList<Object> corres = (ArrayList<Object>)restriccionesCD.get("correquisitos");
        for (Object co : corres) {
            doBranch(co.toString(), correquisitos);
        }


        prerrequisitos = doBranch("prerrequisitos", restricciones);
        ArrayList<Object> pres = (ArrayList<Object>)restriccionesCD.get("prerrequisitos");
        for (Object pr : pres) {
            String strPre = pr.toString();
            strPre = strPre.replace(",", ", Prerrequisito: ");
            doBranch(strPre, prerrequisitos);
        }


        franjaAsignatura = doBranch("franjaAsignatura", restricciones);
        ArrayList<Object> frAsigs = (ArrayList<Object>)restriccionesCD.get("franjaAsignatura");
        for (Object fa : frAsigs) {
            String faS = fa.toString();
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
        currentEscenario = s;
        escenario_label.setText(s);
        TreeView<String> tree = createTreeEscenario(s);
        BorderPane bP = (BorderPane)sc.getPane("prova2");
        bP.setCenter(tree);
    }

    public void generarHorario() {
        try {
            cd.cargarEscenario(currentEscenario);
        } catch (Exception e) {
            System.out.println("ERROR: LECTURA DEL ESCENARIO FALLIDA");
        }
        setRestricciones sR = setRestricciones.getInstance();
        sR.display(currentEscenario);
    }


    public ctrl2() {
        sc = SceneController.getInstance();
        cp = CtrlPresentacion.getInstance();
        cd = cp.getCD();
    }

    public void setMainMenu() throws Exception {
        sc.activate("prova1");
    }

    @FXML
    private void initialize() {
    }
}