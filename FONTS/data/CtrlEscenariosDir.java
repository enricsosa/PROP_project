package data;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CtrlEscenariosDir {
    private static CtrlEscenariosDir escenarios;
    private static ArrayList<String> nomEscenarios;

    /** Constructoras **/

    private CtrlEscenariosDir() {
    }

    public static CtrlEscenariosDir getInstance() {
        if (escenarios == null)
            escenarios = new CtrlEscenariosDir() {
            };
        nomEscenarios = new ArrayList<String>();
        return escenarios;
    }

    public void escanearAllEscenarios() throws NullPointerException {
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

    public void writeHorario(String horario, Integer idHorario) throws IOException {
        PrintWriter writer = new PrintWriter("DATA/Output/"+"Horario#" + idHorario.toString(), "UTF-8");
        writer.println(horario);
        writer.close();
    }

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

    public ArrayList<String> getAllEscenarios() {
        return nomEscenarios;
    }

}
