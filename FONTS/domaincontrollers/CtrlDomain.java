/**CtrlDomain*/

/**Imports*/

package domaincontrollers;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;
//import static java.lang.System.out;

import domain.*;

import data.*;

/**
 * CtrlDominio es el controlador del Dominio que coordina todos los controladores de Dominio, las clases de Dominio y se comunica con las capas
 * de Presentacion y Datos.
 * @author  Enric Sosa
 * @see     IOException
 * @see     List
 * @see     ArrayList
 * @see     JSONObject
 * @see     ParseException
 * @see     FileNotFoundException
 * @see     domain.PlanEstudios
 * @see     domain.Restriccion
 * @see     domain.Nivel
 * @see     domain.Asignatura
 * @see     domain.Sesion
 * @see     domain.Grupo
 * @see     domain.SubGrupo
 * @see     domain.TipoClase
 * @see     domain.Aula
 * @see     domain.DiaLibre
 * @see     domain.FranjaTrabajo
 * @see     domain.NivelHora
 * @see     domain.Correquisito
 * @see     domain.Prerrequisito
 * @see     domain.FranjaAsignatura
 * @see     domain.FranjaNivel
 * @see     data.CtrlAsignaturasFile
 * @see     data.CtrlAulasFile
 * @see     data.CtrlPlanEstudiosFile
 * @see     data.CtrlRestriccionesFile
 * @see     data.CtrlEscenariosDir
 */
public class CtrlDomain {

    /**Atributos*/

    /**Controlador para cargar Asignaturas.*/
    private CtrlAsignaturasFile controladorAsignaturas;
    /**Controlador para cargar Aulas.*/
    private CtrlAulasFile controladorAulas;
    /**Controlador para cargar PlanEstudios.*/
    private CtrlPlanEstudiosFile controladorPlanEstudios;
    /**Controlador para cargar Restricciones.*/
    private CtrlRestriccionesFile controladorRestricciones;
    /**Controlador para gestionar escenarios.*/
    private CtrlEscenariosDir controladorEscenarios;
    /**PlanEstudios con el que trabaja CtrlDominio.*/
    private PlanEstudios planEstudios;
    /**ArrayList con todas las Restricciones del escenario.*/
    private ArrayList<Restriccion> restricciones;

    /**Constructoras*/

    /**
     * Creadora de la clase CtrlDomain.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public CtrlDomain()throws FileNotFoundException, IOException, ParseException  {
        this.initCtrlDomain();
    }

    /**
     * Ejecuta las acciones necesarias para la inicialización de CtrlDomain.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
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

    /**Métodos públicos*/

    /**
     * Carga en CtrlDominio el escenario con el nombre indicado.
     * @param escenario                 Nombre del escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public void cargarEscenario(String escenario) throws FileNotFoundException, IOException, ParseException {
        this.cargarPlanEstudios(escenario);
        this.cargarAllAsignaturas(escenario);
        this.cargarAllAulas(escenario);
        this.cargarAllRestricciones(escenario);
    }

    /**
     * Devuelve todos los escenarios disponibles.
     * @return  ArrayList de String con los nombres de todos los escenerios disponibles.
     */
    public ArrayList<String> allEscenarios() {
        controladorEscenarios.escanearAllEscenarios();
        return controladorEscenarios.getAllEscenarios();
    }

    /**
     * Devuelve todos los Horarios disponibles.
     * @return  ArrayList de String con los nombres de todos los Horarios disponibles.
     */
    public ArrayList<String> allHorarios() {
        return controladorEscenarios.escanearAllHorarios();
    }

    public HashMap<Integer, HashMap<Integer, ArrayList<String>>> escaneaHorario(String h) throws Exception {
        //System.out.println(h);
        return controladorEscenarios.escaneaHorario(h);
    }

    /**
     * Escribe un Horario por consola.
     * @param horario   String con los datos de Horario.
     * @param idHorario id del Horarioque se escribe.
     * @throws IOException
     */
    public void writeHorario(String horario, String idHorario) throws IOException {
        controladorEscenarios.writeHorario(horario, idHorario);
    }

    /**
     * Lee un horario guardado préviamente.
     * @param horario                   Nombre del Horario que se quiere leer.
     * @return                          String con los datos del Horario indicado.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String readHorario(String horario) throws FileNotFoundException, IOException {
        return controladorEscenarios.readHorario(horario);
    }

    /**
     * Carga el PlanEstudios del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
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

    /**
     * Carga los datos de las Asignaturas del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
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

    /**
     * Carga los datos de las Aulas del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
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

    /**
     * Carga las Restricciones del escenario indicado.
     * @param escenario                 Escenario que se quiere cargar.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
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

    public HashMap<String, ArrayList<Object>> subirAsignaturas(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> asignaturasData = controladorAsignaturas.getByEscenario(escenario);
        HashMap<String, ArrayList<Object>> asigs = new HashMap<>();

        for (JSONObject assig : asignaturasData) {
            ArrayList<Object> asigProperties = new ArrayList<>();
            asigProperties.add((String)assig.get("nombre"));
            asigProperties.add((String)assig.get("nivel"));

            ArrayList<Object> sesiones = new ArrayList<>();
            for (JSONObject ses : (List<JSONObject>)assig.get("sesiones")) {
                ArrayList<Object> sesProperties = new ArrayList<>();
                sesProperties.add(((Long)ses.get("horas")).intValue());
                sesProperties.add(TipoClase.valueOf((String)ses.get("TipoClase")));
                sesiones.add(sesProperties);
            }
            asigProperties.add(sesiones);

            ArrayList<Object> grupos = new ArrayList<>();
            for (JSONObject gr : (List<JSONObject>)assig.get("grupos")) {
                ArrayList<Object> grProperties = new ArrayList<>();
                grProperties.add((String)gr.get("id"));

                ArrayList<Object> subgrupos = new ArrayList<>();
                for (JSONObject subgr : (List<JSONObject>)gr.get("subGrupos")) {
                    ArrayList<Object> subgrProperties = new ArrayList<>();
                    subgrProperties.add((String)subgr.get("id"));
                    subgrProperties.add(((Long)subgr.get("plazas")).intValue());
                    subgrProperties.add(TipoClase.valueOf((String)subgr.get("tipo")));
                    subgrupos.add(subgrProperties);
                }
                grProperties.add(subgrupos);

                grupos.add(grProperties);
            }
            asigProperties.add(grupos);

            asigs.put((String)assig.get("id"), asigProperties);
        }

        return asigs;
    }

    public HashMap<String, ArrayList<Object>> subirAulas(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> aulasData = controladorAulas.getByEscenario(escenario);
        HashMap<String, ArrayList<Object>> aulas = new HashMap<>();

        for (JSONObject au : aulasData) {
            ArrayList<Object> auProperties = new ArrayList<>();
            auProperties.add(((Long)au.get("plazas")).intValue());

            ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
            for (String tipo : (List<String>)au.get("tipos")) {
                tipos.add(TipoClase.valueOf(tipo));
            }
            auProperties.add(tipos);

            aulas.put((String)au.get("id"), auProperties);
        }
        return aulas;
    }

    public HashMap<String, ArrayList<Object>> subirRestricciones(String escenario) throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> restriccionesData = controladorRestricciones.getByEscenario(escenario);
        HashMap<String, ArrayList<Object>> restricciones = new HashMap<>();

        //R: diaLibre
        JSONObject diaLibre = restriccionesData.get(0);
        ArrayList<Object> dlProperties = new ArrayList<>();
        for (Long dia : (List<Long>)diaLibre.get("dias")) {
            dlProperties.add(dia.intValue());
        }
        restricciones.put(diaLibre.get("nombre").toString(), dlProperties);


        //R: franjaTrabajo
        JSONObject franjaTrabajo = restriccionesData.get(1);
        ArrayList<Object> ftProperties = new ArrayList<>();
        ftProperties.add(((Long)franjaTrabajo.get("horaIni")).intValue());
        ftProperties.add(((Long)franjaTrabajo.get("horaFin")).intValue());
        restricciones.put(franjaTrabajo.get("nombre").toString(), ftProperties);

        //R: nivelHora
        JSONObject nivelHora = restriccionesData.get(2);
        ArrayList<Object> nhProperties = new ArrayList<>();
        for (String nivel : (List<String>)nivelHora.get("niveles")) {
            nhProperties.add(nivel);
        }
        restricciones.put(nivelHora.get("nombre").toString(), nhProperties);

        //R: correquisitos
        JSONObject correquisitos = restriccionesData.get(3);
        ArrayList<Object> coProperties = new ArrayList<>();
        for (JSONObject as : (List<JSONObject>)correquisitos.get("parAsigs")) {
            ArrayList<String> co = new ArrayList<>();
            co.add((String)as.get("idAsig1"));
            co.add((String)as.get("idAsig2"));
            coProperties.add(co);
        }
        restricciones.put(correquisitos.get("nombre").toString(), coProperties);

        //R: prerrequisitos
        JSONObject prerrequisitos = restriccionesData.get(4);
        ArrayList<Object> prProperties = new ArrayList<>();
        for (JSONObject as : (List<JSONObject>)prerrequisitos.get("parAsigs")) {
            ArrayList<String> pr = new ArrayList<>();
            pr.add((String)as.get("idAsig"));
            pr.add((String)as.get("idAsigPre"));
            prProperties.add(pr);
        }
        restricciones.put(prerrequisitos.get("nombre").toString(), prProperties);

        //R: franjaAsignatura
        JSONObject franjaAsignatura = restriccionesData.get(5);
        ArrayList<Object> faProperties = new ArrayList<>();
        for (JSONObject as : (List<JSONObject>)franjaAsignatura.get("asignaturas")) {
            ArrayList<Object> fa = new ArrayList<>();
            fa.add((String)as.get("idAsig"));
            fa.add(((Long)as.get("horaIni")).intValue());
            fa.add(((Long)as.get("horaFin")).intValue());
            faProperties.add(fa);
        }
        restricciones.put(franjaAsignatura.get("nombre").toString(), faProperties);

        //R: franjaNivel
        JSONObject franjaNivel = restriccionesData.get(6);
        ArrayList<Object> fnProperties = new ArrayList<>();
        for (JSONObject as : (List<JSONObject>)franjaNivel.get("niveles")) {
            ArrayList<Object> fn = new ArrayList<>();
            fn.add((String)as.get("idNivel"));
            fn.add(((Long)as.get("horaIni")).intValue());
            fn.add(((Long)as.get("horaFin")).intValue());
            fnProperties.add(fn);
        }
        restricciones.put(franjaNivel.get("nombre").toString(), fnProperties);

        return restricciones;
    }

    public ArrayList<String> subirPlanEstudios(String escenario) throws FileNotFoundException, IOException, ParseException {
        JSONObject planEstudiosData = controladorPlanEstudios.getPlanEstudiosByEscenario(escenario);
        ArrayList<String> pE = new ArrayList<>();
        pE.add((String)planEstudiosData.get("nombre"));
        String niveles = planEstudiosData.get("niveles").toString();

        String s;
        for (int i = 0; i < niveles.length()-1; i+=5) {
            s = niveles.substring(niveles.indexOf("\"", i) + 1, niveles.indexOf("\"", i) + 3);
            pE.add(s);
        }
        return pE;
    }

    public String generarHorario(String id) {
        CtrlHorario ctrlHorario = new CtrlHorario(this.planEstudios, this.restricciones);
        ReturnSet horario = ctrlHorario.generarHorario(id);
        if (horario.getValidez())
            return horario.getHorario().toString();
        else
            return "false";
    }

    /**
     * Genera una instancia de CtrlHorario con los datos de CtrlDominio.
     * @return  Instancia de CtrlHorario con los datos de CtrlDominio.
     */
    public CtrlHorario getCtrlHorario() {
        return new CtrlHorario(this.planEstudios, this.restricciones);
    }

    /**
     * Añade una Restriccion al escenario
     * @param restriccion   Restriccion que se quiere añadir.
     */
    public void addRestriccion(Restriccion restriccion) {
        restricciones.add(restriccion);
    }

    /**Consultoras*/

    /**Métodos redefinidos*/
}
