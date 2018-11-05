package domaincontrollers;

import java.io.IOException;
import java.util.*;

import domain.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

import data.CtrlAsignaturasFile;
import data.CtrlAulasFile;
import data.CtrlPlanEstudiosFile;

public class CtrlDomain {

    /** Atributos **/
    private CtrlAsignaturasFile controladorAsignaturas;
    private CtrlAulasFile controladorAulas;
    private CtrlPlanEstudiosFile controladorPlanEstudios;

    private PlanEstudios planEstudios;
    //private Map<String, Asignatura> asignaturas;
    //private Map<String, Aula> aulas;

    //private ArrayList<restricciones> restricciones;

    /** Constructoras **/

    public CtrlDomain()throws FileNotFoundException, IOException, ParseException  {
        this.initCtrlDomain();
    }

    public void initCtrlDomain() throws FileNotFoundException, IOException, ParseException {
        controladorAsignaturas = CtrlAsignaturasFile.getInstance();
        controladorAulas = CtrlAulasFile.getInstance();
        controladorPlanEstudios = CtrlPlanEstudiosFile.getInstance();
        this.cargarPlanEstudios();
        this.cargarAllAsignaturas();
        this.cargarAllAulas();
    }

    /** Métodos públicos **/

    public void cargarPlanEstudios() throws FileNotFoundException, IOException, ParseException {
        JSONObject planEstudiosData = controladorPlanEstudios.getPlanEstudios();
        planEstudios = new PlanEstudios((String)planEstudiosData.get("nombre"));
        String niveles = planEstudiosData.get("niveles").toString();

        String s = new String();
        for (int i = 0; i < niveles.length()-1; i+=5) {
            s = niveles.substring(niveles.indexOf("\"", i) + 1, niveles.indexOf("\"", i) + 3);
            Nivel nivel = new Nivel(s);
            planEstudios.addNivel(nivel);
        }
    }

    public void cargarAllAsignaturas() throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> asignaturasData = controladorAsignaturas.getAll();

        for (JSONObject assig : asignaturasData) {
            Asignatura asignatura = new Asignatura((String)assig.get("id"), (String)assig.get("nombre"), this.planEstudios);

            for (JSONObject ses : (List<JSONObject>)assig.get("sesiones")) {
                Integer hora = new Integer(((Long)ses.get("horas")).intValue());
                Sesion sesion = new Sesion(hora, TipoClase.valueOf((String)ses.get("TipoClase")), asignatura);
                asignatura.addSesion(sesion);
            }

            for (JSONObject gr : (List<JSONObject>)assig.get("grupos")) {
                Grupo grupo = new Grupo((String)gr.get("id"), asignatura);

                for (JSONObject subgr : (List<JSONObject>)gr.get("subGrupos")) {
                    Integer plazas = new Integer(((Long)subgr.get("plazas")).intValue());
                    SubGrupo subg = new SubGrupo((String)subgr.get("id"), plazas, TipoClase.valueOf((String)subgr.get("tipo")), grupo);
                    grupo.addSubGrupo(subg);
                }
                asignatura.addGrupo(grupo);
            }
            planEstudios.addAsignatura(asignatura);
        }
    }

    public void cargarAllAulas() throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> aulasData = controladorAulas.getAll();

        for (JSONObject au : aulasData) {
            Integer plazas = new Integer(((Long)au.get("plazas")).intValue());
            TipoClase[] tipos = new TipoClase[3];
            int i = 0;
            for (String tipo : (List<String>)au.get("tipos")) {
                tipos[i] = TipoClase.valueOf(tipo);
                ++i;
            }
            Aula aula = new Aula((String)au.get("id"), plazas, tipos);
            planEstudios.addAula(aula);
        }

    }

    /** Consultoras **/

    /** Métodos redefinidos **/
}
