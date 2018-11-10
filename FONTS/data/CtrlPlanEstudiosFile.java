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


public class CtrlPlanEstudiosFile {

    /** Atributos **/
    private static CtrlPlanEstudiosFile infoPlanEstudio;

    /** Constructoras **/

    private CtrlPlanEstudiosFile() {
    }

    public static CtrlPlanEstudiosFile getInstance() {
        if (infoPlanEstudio == null)
            infoPlanEstudio = new CtrlPlanEstudiosFile() {
            };
        return infoPlanEstudio;
    }

    /** Métodos públicos **/

    /** Consultoras **/

    public JSONObject getPlanEstudios() throws FileNotFoundException, IOException, ParseException {
        JSONObject plaEstudis = new JSONObject();
        plaEstudis = (JSONObject)new JSONParser().parse(new FileReader("DATA/pla_estudis.json"));

        return plaEstudis;
    }

    public JSONObject getPlanEstudiosByEscenario(String escenario) throws FileNotFoundException, IOException, ParseException {
        JSONObject plaEstudis = new JSONObject();
        plaEstudis = (JSONObject)new JSONParser().parse(new FileReader("DATA/" + escenario + "/pla_estudis.json"));

        return plaEstudis;
    }

    /** Métodos redefinidos **/

}