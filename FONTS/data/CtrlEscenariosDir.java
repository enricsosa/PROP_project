/**CtrlEscenariosDir*/

/**Imports*/

package data;

import java.io.*;
import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;

/**
 * CtrlEscenariosDir gestiona la carga de datos desde los directorios donde se encuentran los diversos escenarios.
 * @author  Enric Sosa
 * @see     ArrayList
 * @see     System
 */
public class CtrlEscenariosDir {
    private static CtrlEscenariosDir escenarios;
    private static ArrayList<String> nomEscenarios;

    /**Constructoras*/

    /**Constructora de la clase CtrlEscenariosDir.*/
    private CtrlEscenariosDir() {
    }

    /**
     * Devuelve una instancia de CtrlEscenariosDir.
     * @return  Instancia de CtrlEscenariosDir.
     */
    public static CtrlEscenariosDir getInstance() {
        if (escenarios == null)
            escenarios = new CtrlEscenariosDir() {
            };
        nomEscenarios = new ArrayList<String>();
        return escenarios;
    }

    /**
     * Escanea los escenarios que hay disponibles.
     * @throws NullPointerException
     */
    public void escanearAllEscenarios() throws NullPointerException {
        nomEscenarios = new ArrayList<>();
        File dir = new File("DATA/");
        File[] listEsc = dir.listFiles();
        if (listEsc != null) {
            for (File file : listEsc) {
                if (file.isDirectory() && !file.getName().equals("Output")) {
                    nomEscenarios.add(file.getName());
                }
            }
        }
    }

    /**
     * Escanea en busca de Horarios y los devuelve en forma de ArrayList de Strings.
     * @return  ArrayList de String con los Horarios guardados.
     * @throws NullPointerException
     */
    public ArrayList<String> escanearAllHorarios() throws NullPointerException {
        ArrayList<String> horarios = new ArrayList<String>();
        File dir = new File("DATA/Output/");
        File[] listHor = dir.listFiles();
        if (listHor != null) {
            for (File file : listHor) {
                horarios.add(file.getName());
            }
        }
        return horarios;
    }

    /**
     * Guarda un Horario en un archivo txt.
     * @param horario       String con la información de un Horario.
     * @param idHorario     Código identificador del Horario.
     * @throws IOException
     */
    public void writeHorario(String horario, Integer idHorario) throws IOException {
        PrintWriter writer = new PrintWriter("DATA/Output/"+"Horario#" + idHorario.toString(), "UTF-8");
        writer.println(horario);
        writer.close();
    }

    /**
     * Lee un Horario de un archivo txt.
     * @param horario                  Nombre del horario que se quiere cargar.
     * @return String con los datos de un Horario.
     * @throws FileNotFoundException
     * @throws IOException
     */
    public String readHorario(String horario) throws FileNotFoundException, IOException {
        File file = new File("DATA/Output/" + horario);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String s, ret;
        ret = "";
        while ((s = br.readLine()) != null) {
            ret += s + '\n';
        }
        return ret;
    }

    /**
     * Devuelve el nombre de todos los escenarios.
     * @return  Devuelve un ArrayList de Strings con los nombres de los escenarios.
     */
    public ArrayList<String> getAllEscenarios() {
        return nomEscenarios;
    }

}
