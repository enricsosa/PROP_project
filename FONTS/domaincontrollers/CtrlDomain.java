package domaincontrollers;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

import data.CtrlAsignaturasFile;
import data.CtrlAulasFile;
import domain.Asignatura;
import domain.Aula;
import domain.TipoClase;

public class CtrlDomain {

    /** Atributos **/
    private CtrlAsignaturasFile controladorAsignaturas;
    private CtrlAulasFile controladorAulas;
    private Map<String, Asignatura> asignaturas;
    private Map<String, Aula> aulas;

    /** Constructoras **/

    public CtrlDomain() {
        controladorAsignaturas = CtrlAsignaturasFile.getInstance();
        controladorAulas = CtrlAulasFile.getInstance();
        asignaturas = new HashMap<String, Asignatura>();
        aulas = new HashMap<String, Aula>();
    }

    /** Métodos públicos **/

    public Map<String, Asignatura> cargarAllAsignaturas() throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> asignaturasData = controladorAsignaturas.getAll();
        Map<String, Asignatura> list = new HashMap<String, Asignatura>();

        for (JSONObject o : asignaturasData) {
            Asignatura asignatura = new Asignatura((String)o.get("id"), (String)o.get("nombre"));
            this.asignaturas.put(asignatura.getId(), asignatura);
            list.put(asignatura.getId(), asignatura);
        }

        return list;
    }

    public Map<String, Aula> cargarAllAulas() throws FileNotFoundException, IOException, ParseException {
        List<JSONObject> aulasData = controladorAulas.getAll();
        Map<String, Aula> list = new HashMap<String, Aula>();

        for (JSONObject o : aulasData) {
            Aula aula = new Aula((String)o.get("id"), (Integer)o.get("plazas"), (TipoClase[])o.get("tipoAula"));
            this.aulas.put(aula.getId(), aula);
            list.put(aula.getId(), aula);
        }

        return list;
    }

    /** Consultoras **/

    /** Métodos redefinidos **/
}
