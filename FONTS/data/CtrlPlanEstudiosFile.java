/**CtrlPlanEstudiosFile*/

/** Imports **/

package data;

//import java.io.File;
import java.io.FileReader;
import java.io.IOException;
//import java.util.LinkedList;
//import java.util.List;
//import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

/**
 * Clase para obtener datos de PlanEstudios de archivos JSON.
 * @author  Enric Sosa
 * @see     FileReader
 * @see     IOException
 * @see     JSONObject
 * @see     JSONParser
 * @see     ParseException
 * @see     FileNotFoundException
 */
public class CtrlPlanEstudiosFile {

    /**Atributos*/

    /**Información del PlanEstudios.*/
    private static CtrlPlanEstudiosFile infoPlanEstudio;

    /**Constructoras*/

    /**Constructora de la clase CtrlPlanEstudiosFile.*/
    private CtrlPlanEstudiosFile() {
    }

    /**
     * Devuelve una instancia de CtrlPlanEstudiosFile.
     * @return  Instancia de CtrlPlanEstudiosFile.
     */
    public static CtrlPlanEstudiosFile getInstance() {
        if (infoPlanEstudio == null)
            infoPlanEstudio = new CtrlPlanEstudiosFile() {
            };
        return infoPlanEstudio;
    }

    /**Métodos públicos*/

    /**Consultoras*/

    /**
     * Extrae toda la información de PlanEstudios del archivo JSON.
     * @return  Devuelve la información de las PlanEstudios en forma de JSON object.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public JSONObject getPlanEstudios() throws FileNotFoundException, IOException, ParseException {
        JSONObject plaEstudis = new JSONObject();
        plaEstudis = (JSONObject)new JSONParser().parse(new FileReader("DATA/pla_estudis.json"));

        return plaEstudis;
    }

    /**
     * Extrae toda la información de PlanEstudios del archivo JSON de un escenario.
     * @param escenario Nombre del escenario del que se quiere extraer la información.
     * @return          Devuelve la información de las PlanEstudios de un escenario en forma de JSON object.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
    public JSONObject getPlanEstudiosByEscenario(String escenario) throws FileNotFoundException, IOException, ParseException {
        JSONObject plaEstudis = new JSONObject();
        plaEstudis = (JSONObject)new JSONParser().parse(new FileReader("DATA/" + escenario + "/pla_estudis.json"));

        return plaEstudis;
    }

    /**Métodos redefinidos*/

}