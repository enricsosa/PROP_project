/**CtrlRestriccionesFile*/

/**Imports*/

package data;

//import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.io.FileNotFoundException;

/**
 * Clase para obtener datos de Restricciones de archivos JSON.
 * @author  Enric Sosa
 * @see     FileReader
 * @see     IOException
 * @see     LinkedList
 * @see     List
 * @see     JSONArray
 * @see     JSONObject
 * @see     JSONParser
 * @see     ParseException
 * @see     FileNotFoundException
 */
public class CtrlRestriccionesFile {

    /**Atributos*/

    /**Información de las Restricciones.*/
    private static CtrlRestriccionesFile infoRestricciones;

    /**Constructoras*/

    /**Constructora de la clase CtrlRestriccionesFile.*/
    private CtrlRestriccionesFile() {
    }

    /**
     * Devuelve una instancia de CtrlRestriccionesFile.
     * @return  Instancia de CtrlRestriccionesFile.
     */
    public static CtrlRestriccionesFile getInstance() {
        if (infoRestricciones == null)
            infoRestricciones = new CtrlRestriccionesFile() {
            };
        return infoRestricciones;
    }

    /**Métodos públicos*/

    /**Consultoras*/

    /**
     * Extrae toda la información de Restricciones del archivo JSON.
     * @return  Devuelve la información de las Restricciones en forma de JSON object.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
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

    /**
     * Extrae toda la información de Restricciones del archivo JSON de un escenario.
     * @param escenario Nombre del escenario del que se quiere extraer la información.
     * @return          Devuelve la información de las Restricciones de un escenario en forma de JSON object.
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ParseException
     */
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

    /**Métodos redefinidos*/

}
