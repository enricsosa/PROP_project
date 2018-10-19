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


public class CtrlAsignaturasFile {

    /** Atributos **/
    private static CtrlAsignaturasFile infoAsignaturas;

    /** Constructoras **/

    private CtrlAsignaturasFile() {
    }

    public static CtrlAsignaturasFile getInstance() {
        if (infoAsignaturas == null)
            infoAsignaturas = new CtrlAsignaturasFile() {
            };
        return infoAsignaturas;
    }

    /** Métodos públicos **/

    /** Consultoras **/

    public List<JSONObject> getAll() throws FileNotFoundException, IOException, ParseException {
        LinkedList<JSONObject> asignaturas = new LinkedList<JSONObject>();

        JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader("DATA/asignaturas.json"));
        JSONArray a = (JSONArray)obj.get("asignaturas");

        for (Object o : a) {
            JSONObject assignatura = (JSONObject) o;

            String id = (String) assignatura.get("id");
            System.out.println(id);

            asignaturas.add(assignatura);
        }

        return asignaturas;
    }

    /** Métodos redefinidos **/

}