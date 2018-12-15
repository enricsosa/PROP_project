/**CtrlAulasFile*/

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
 * Clase para obtener datos de Aulas de archivos JSON.
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
public class CtrlAulasFile {

    /**Atributos*/

    /**Información de las Aulas.*/
    private static CtrlAulasFile infoAulas;

    /**Constructoras*/

    private CtrlAulasFile() {
    }

    /**Constructora de la clase CtrlAulasFile.*/
    public static CtrlAulasFile getInstance() {
        if (infoAulas == null)
            infoAulas = new CtrlAulasFile() {
            };
        return infoAulas;
    }

    /**Métodos públicos*/

    /**Consultoras*/

    /*/**
     * Extrae toda la información de Aulas del archivo JSON.
     * @return  Devuelve la información de las Aulas en forma de JSON object.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
    /*public List<JSONObject> getAll() throws FileNotFoundException, IOException, ParseException {
        LinkedList<JSONObject> aulas = new LinkedList<JSONObject>();

        JSONObject obj = (JSONObject)new JSONParser().parse(new FileReader("DATA/aulas.json"));
        JSONArray a = (JSONArray)obj.get("aulas");

        for (Object o : a) {
            JSONObject aula = (JSONObject) o;
            aulas.add(aula);
        }

        return aulas;
    }*/

    /**
     * Extrae toda la información de Aulas del archivo JSON de un escenario.
     * @param escenario Nombre del escenario del que se quiere extraer la información.
     * @return          Devuelve la información de las Aulas de un escenario en forma de JSON object.
     * @throws FileNotFoundException    No se ha encontrado el archivo a abrir.
     * @throws IOException              Ha fallado una operación IO.
     * @throws ParseException           Ha ocurrido un error al parsear.
     */
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

    /**Métodos redefinidos*/

}