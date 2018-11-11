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
import data.CtrlRestriccionesFile;
import data.CtrlEscenariosDir;

import static java.lang.System.out;

public class CtrlDomain {

    /** Atributos **/
    private CtrlAsignaturasFile controladorAsignaturas;
    private CtrlAulasFile controladorAulas;
    private CtrlPlanEstudiosFile controladorPlanEstudios;
    private CtrlRestriccionesFile controladorRestricciones;
    private CtrlEscenariosDir controladorEscenarios;
    private PlanEstudios planEstudios;
    private ArrayList<Restriccion> restricciones;

    /** Constructoras **/

    public CtrlDomain()throws FileNotFoundException, IOException, ParseException  {
        this.initCtrlDomain();
    }

    public void initCtrlDomain() throws FileNotFoundException, IOException, ParseException {
        controladorAsignaturas = CtrlAsignaturasFile.getInstance();
        controladorAulas = CtrlAulasFile.getInstance();
        controladorPlanEstudios = CtrlPlanEstudiosFile.getInstance();
        controladorRestricciones = CtrlRestriccionesFile.getInstance();
        this.cargarPlanEstudios("Default");
        this.cargarAllAsignaturas("Default");
        this.cargarAllAulas("Default");
        this.cargarAllRestricciones("Default");
        //this.generarHorario("Horario 1");
        //out.println(this.planEstudios.toString());
        controladorEscenarios = CtrlEscenariosDir.getInstance();
    }

    /** Métodos públicos **/

    public void cargarEscenario(String escenario) throws FileNotFoundException, IOException, ParseException {
        this.cargarPlanEstudios(escenario);
        this.cargarAllAsignaturas(escenario);
        this.cargarAllAulas(escenario);
        this.cargarAllRestricciones(escenario);
    }

    public ArrayList<String> allEscenarios() {
        controladorEscenarios.escanearAllEscenarios();
        return controladorEscenarios.getAllEscenarios();
    }

    public void cargarPlanEstudios(String escenario) throws FileNotFoundException, IOException, ParseException {
        JSONObject planEstudiosData = controladorPlanEstudios.getPlanEstudiosByEscenario(escenario);
        planEstudios = new PlanEstudios((String)planEstudiosData.get("nombre"));
        String niveles = planEstudiosData.get("niveles").toString();

        String s;
        for (int i = 0; i < niveles.length()-1; i+=5) {
            s = niveles.substring(niveles.indexOf("\"", i) + 1, niveles.indexOf("\"", i) + 3);
            Nivel nivel = new Nivel(s);
            planEstudios.addNivel(nivel);
        }
    }

    public void cargarAllAsignaturas(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> asignaturasData = controladorAsignaturas.getByEscenario(escenario);

        for (JSONObject assig : asignaturasData) {
            Asignatura asignatura;

            if (((String)assig.get("nivel")).length() != 0) {
                asignatura = new Asignatura((String)assig.get("id"), (String)assig.get("nombre"), this.planEstudios, this.planEstudios.getNivel((String)assig.get("nivel")));
                this.planEstudios.getNivel((String)assig.get("nivel")).addAsignatura(asignatura);
            }
            else {
                asignatura = new Asignatura((String)assig.get("id"), (String)assig.get("nombre"), this.planEstudios);
            }

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

    public void cargarAllAulas(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> aulasData = controladorAulas.getByEscenario(escenario);

        for (JSONObject au : aulasData) {
            Integer plazas = new Integer(((Long)au.get("plazas")).intValue());
            ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
            int i = 0;
            for (String tipo : (List<String>)au.get("tipos")) {
                tipos.add(TipoClase.valueOf(tipo));
                ++i;
            }
            Aula aula = new Aula((String)au.get("id"), plazas, tipos);
            planEstudios.addAula(aula);
        }

    }

    public void cargarAllRestricciones(String escenario) throws FileNotFoundException, IOException, ParseException {
        this.restricciones = new ArrayList<Restriccion>();

        List<JSONObject> restriccionesData = controladorRestricciones.getByEscenario(escenario);
        //System.out.println(restriccionesData);
        //System.out.print("\n");

        //R: diaLibre
        JSONObject diaLibre = restriccionesData.get(0);
        for (Long dia : (List<Long>)diaLibre.get("dias")) {
            Integer diaN = new Integer(dia.intValue());
            DiaLibre diaLib = new DiaLibre(diaN);
            planEstudios.addRestriccion(diaLib);
            this.addRestriccion(diaLib);
        }

        //R: franjaTrabajo
        JSONObject franjaTrabajo = restriccionesData.get(1);
        Integer horaIni = new Integer(((Long)franjaTrabajo.get("horaIni")).intValue());
        Integer horaFin = new Integer(((Long)franjaTrabajo.get("horaFin")).intValue());
        FranjaTrabajo ft = new FranjaTrabajo(horaIni, horaFin);
        planEstudios.addRestriccion(ft);
        this.addRestriccion(ft);

        //R: nivelHora
        JSONObject nivelHora = restriccionesData.get(2);
        for (String nivel : (List<String>)nivelHora.get("niveles")) {
            NivelHora nh = new NivelHora(planEstudios.getNivel(nivel));
            planEstudios.getNivel(nivel).addRestriccion(nh);
            this.addRestriccion(nh);
        }

        //R: correquisitos
        JSONObject correquisitos = restriccionesData.get(3);
        for (JSONObject as : (List<JSONObject>)correquisitos.get("parAsigs")) {
            String a1 = (String)as.get("idAsig1");
            String a2 = (String)as.get("idAsig2");
            Correquisito co = new Correquisito(planEstudios.getAsignatura(a1), planEstudios.getAsignatura(a2));
            planEstudios.getAsignatura(a1).addRestriccion(co);
            planEstudios.getAsignatura(a2).addRestriccion(co);
            this.addRestriccion(co);
        }

        //R: prerrequisitos
        JSONObject prerrequisitos = restriccionesData.get(4);
        for (JSONObject as : (List<JSONObject>)prerrequisitos.get("parAsigs")) {
            String a1 = (String)as.get("idAsig");
            String a2 = (String)as.get("idAsigPre");
            Prerrequisito pre = new Prerrequisito(planEstudios.getAsignatura(a1), planEstudios.getAsignatura(a2));
            planEstudios.getAsignatura(a1).addRestriccion(pre);
            this.addRestriccion(pre);
        }

        //R: franjaAsignatura
        JSONObject franjaAsignatura = restriccionesData.get(5);
        for (JSONObject as : (List<JSONObject>)franjaAsignatura.get("asignaturas")) {
            Integer hi = new Integer(((Long)as.get("horaIni")).intValue());
            Integer hf = new Integer(((Long)as.get("horaFin")).intValue());
            String idAs = (String)as.get("idAsig");
            FranjaAsignatura fa = new FranjaAsignatura(planEstudios.getAsignatura(idAs), hi, hf);
            planEstudios.getAsignatura(idAs).addRestriccion(fa);
            this.addRestriccion(fa);
        }

        //R: franjaNivel
        JSONObject franjaNivel = restriccionesData.get(6);
        for (JSONObject ni : (List<JSONObject>)franjaNivel.get("niveles")) {
            Integer hi = new Integer(((Long)ni.get("horaIni")).intValue());
            Integer hf = new Integer(((Long)ni.get("horaFin")).intValue());
            String idNi = (String)ni.get("idNivel");
            FranjaNivel fn = new FranjaNivel(planEstudios.getNivel(idNi), hi, hf);
            planEstudios.getNivel(idNi).addRestriccion(fn);
            this.addRestriccion(fn);
        }
    }

    public void generarHorario(String id) {
        CtrlHorario ctrlHorario = new CtrlHorario(this.planEstudios, this.restricciones);
        ReturnSet horario = ctrlHorario.generarHorario(id);
        if (horario.getValidez()) planEstudios.setHorarioGeneral(horario.getHorario());
        if (horario.getValidez()) {
            out.println(horario.getHorario().toString());
            out.println("Horario generado");
        }
        else out.println("Horario no generado");
    }

    public CtrlHorario getCtrlHorario() {
        return new CtrlHorario(this.planEstudios, this.restricciones);
    }

    public void addRestriccion(Restriccion restriccion) {
        restricciones.add(restriccion);
    }

    /** Consultoras **/

    /** Métodos redefinidos **/
}
