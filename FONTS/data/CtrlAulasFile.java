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


public class CtrlAulasFile {

    /** Atributos **/
    private static CtrlAulasFile infoAulas;

    /** Constructoras **/

    private CtrlAulasFile() {
    }

    public static CtrlAulasFile getInstance() {
        if (infoAulas == null)
            infoAulas = new CtrlAulasFile() {
            };
        return infoAulas;
    }

    /** Métodos públicos **/

    /** Consultoras **/

    public List<JSONObject> getAll() throws FileNotFoundException, IOException, ParseException {
        LinkedList<JSONObject> aulas = new LinkedList<JSONObject>();

        JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader("DATA/aulas.json"));
        JSONArray a = (JSONArray)obj.get("aulas");

        for (Object o : a) {
            JSONObject aula = (JSONObject) o;
            aulas.add(aula);
        }

        return aulas;
    }

    public List<JSONObject> getByEscenario(String escenario) throws FileNotFoundException, IOException, ParseException {
        LinkedList<JSONObject> aulas = new LinkedList<JSONObject>();

        JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader("DATA/" + escenario + "/aulas.json"));
        JSONArray a = (JSONArray)obj.get("aulas");

        for (Object o : a) {
            JSONObject aula = (JSONObject) o;
            aulas.add(aula);
        }

        return aulas;
    }

    /** Métodos redefinidos **/

}