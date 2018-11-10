package data;

/** Imports **/
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

public class CtrlRestriccionesFile {

    /** Atributos **/
    private static CtrlRestriccionesFile infoRestricciones;

    /** Constructoras **/

    private CtrlRestriccionesFile() {
    }

    public static CtrlRestriccionesFile getInstance() {
        if (infoRestricciones == null)
            infoRestricciones = new CtrlRestriccionesFile() {
            };
        return infoRestricciones;
    }

    /** Métodos públicos **/

    /** Consultoras **/

    public List<JSONObject> getAll() throws FileNotFoundException, IOException, ParseException {
        LinkedList<JSONObject> restricciones = new LinkedList<JSONObject>();

        JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader("DATA/restricciones.json"));
        JSONArray r = (JSONArray)obj.get("restricciones");

        for (Object o : r) {
            JSONObject restriccion = (JSONObject) o;
            restricciones.add(restriccion);
        }

        return restricciones;
    }

    public List<JSONObject> getByEscenario(String escenario) throws FileNotFoundException, IOException, ParseException {
        LinkedList<JSONObject> restricciones = new LinkedList<JSONObject>();

        JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader("DATA/" + escenario + "/restricciones.json"));
        JSONArray r = (JSONArray)obj.get("restricciones");

        for (Object o : r) {
            JSONObject restriccion = (JSONObject) o;
            restricciones.add(restriccion);
        }

        return restricciones;
    }

    /** Métodos redefinidos **/

}
