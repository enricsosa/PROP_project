package data;

import java.util.ArrayList;
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
        File esc = new File("DATA/");
        File[] listEsc = esc.listFiles();
        if (listEsc != null) {
            for (File file : listEsc) {
                if (file.isDirectory()) {
                    nomEscenarios.add(file.getName());
                }
            }
        }
    }

    public ArrayList<String> getAllEscenarios() {
        return nomEscenarios;
    }
}
