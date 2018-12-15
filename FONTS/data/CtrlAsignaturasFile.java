/**CtrlAsignaturasFile*/

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
 * Clase para obtener datos de Asignaturas de archivos JSON.
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
public class CtrlAsignaturasFile {

    /**Atributos*/

    /**Información de las Asignaturas.*/
    private static CtrlAsignaturasFile infoAsignaturas;

    /**Constructoras*/

    /**Constructora de la clase CtrlAsignaturasFile.*/
    private CtrlAsignaturasFile() {
    }

    /**
     * Devuelve una instancia de CtrlAsignaturasFile.
     * @return  Instancia de CtrlAsignaturasFile.
     */
    public static CtrlAsignaturasFile getInstance() {
        if (infoAsignaturas == null)
            infoAsignaturas = new CtrlAsignaturasFile() {
            };
        return infoAsignaturas;
    }

    /**Métodos públicos*/

    /**Consultoras*/

    /*/**
     * Extrae toda la información de Asignaturas del archivo JSON.
     * @return  Devuelve la información de las Asignaturas en forma de JSON object.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    /*public List<JSONObject> getAll() throws FileNotFoundException, IOException, ParseException {
        LinkedList<JSONObject> asignaturas = new LinkedList<JSONObject>();

        JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader("DATA/asignaturas.json"));
        JSONArray a = (JSONArray)obj.get("asignaturas");

        for (Object o : a) {
            JSONObject assignatura = (JSONObject) o;
            asignaturas.add(assignatura);
        }

        return asignaturas;
    }*/

    /**
     * Extrae toda la información de Asignaturas del archivo JSON de un escenario.
     * @param escenario Nombre del escenario del que se quiere extraer la información.
     * @return          Devuelve la información de las Asignaturas de un escenario en forma de JSON object.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    public List<JSONObject> getByEscenario(String escenario) throws FileNotFoundException, IOException, ParseException {
        LinkedList<JSONObject> asignaturas = new LinkedList<JSONObject>();

        JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader("DATA/" + escenario + "/asignaturas.json"));
        JSONArray a = (JSONArray)obj.get("asignaturas");

        for (Object o : a) {
            JSONObject assignatura = (JSONObject) o;
            asignaturas.add(assignatura);
        }

        return asignaturas;
    }

    /**Métodos redefinidos*/

}