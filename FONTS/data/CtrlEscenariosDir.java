/**CtrlEscenariosDir*/

/**Imports*/

package data;

import java.io.*;
import java.util.*;
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
    private HashMap<Integer, HashMap<Integer, ArrayList<String>>> horario = new HashMap<>();

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
        //nomEscenarios = new ArrayList<String>();
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
                String nomH = file.getName();
                horarios.add(nomH.substring(0, nomH.length() - 4));
            }
        }
        return horarios;
    }

    private boolean checkDia(String dia) {
        boolean verify = false;
        if (dia != null) {
            switch (dia) {
                case "Lunes":
                    verify = true;
                    break;
                case "Martes":
                    verify = true;
                    break;
                case "Miercoles":
                    verify = true;
                    break;
                case "Jueves":
                    verify = true;
                    break;
                case "Viernes":
                    verify = true;
                    break;
                case "Sabado":
                    verify = true;
                    break;
                case "Domingo":
                    verify = true;
                    break;
                default:
                    verify = false;
                    break;
            }
        }
        return verify;
    }

    private boolean checkHora(String hora) {
        int num = (int) (hora.charAt(0) - '0');
        return (num >= 0 && num <= 9);
    }

    private int horaToInt(String hora) {
        int num = 0;
        String h = hora.substring(0, hora.indexOf(':'));
        for (int i = 0; i < h.length(); ++i) {
            num *= 10;
            num += (int)(h.charAt(i) - '0');
        }
        return num;
    }

    private int diaToInt(String dia) {
        int num = 0;
        if (dia != null) {
            switch (dia) {
                case "Lunes":
                    num = 1;
                    break;
                case "Martes":
                    num = 2;
                    break;
                case "Miercoles":
                    num = 3;
                    break;
                case "Jueves":
                    num = 4;
                    break;
                case "Viernes":
                    num = 5;
                    break;
                case "Sabado":
                    num = 6;
                    break;
                case "Domingo":
                    num = 7;
                    break;
                default:
                    num = 0;
                    System.out.println("ERROR: Dia erróneo");
                    break;
            }
        }
        return num;
    }

    public HashMap<Integer, ArrayList<String>> getDia(int dia) {
        return horario.get(dia);
    }

    public ArrayList<String> getHora(int dia, int hora) {
        return horario.get(dia).get(hora);
    }

    public String getSesio(int dia, int hora, String sesion) {
        ArrayList<String> h = horario.get(dia).get(hora);
        return h.get(h.indexOf(sesion));
    }

    public void addSesio(int dia, int hora, String sesion) {
        horario.get(dia).get(hora).add(sesion);
    }

    private HashMap<Integer, ArrayList<String>> initHorasDia() {
        HashMap<Integer, ArrayList<String>> dia = new HashMap<>();

        for (int i = 8; i < 20; ++i) {
            ArrayList<String> hora = new ArrayList<>();
            dia.put(i, hora);
        }

        return dia;
    }

    private void initHorario() {
        for (int i = 1; i <= 5; ++i)
            horario.put(i, initHorasDia());
    }

    public HashMap<Integer, HashMap<Integer, ArrayList<String>>> escaneaHorario(String h) throws Exception {
        horario = new HashMap<Integer, HashMap<Integer, ArrayList<String>>>();
        initHorario();
        File htxt = new File("DATA/Output/" + h + ".txt");
        BufferedReader br = new BufferedReader(new FileReader(htxt));

        String line;
        int dia, hora;

        line = br.readLine();
        while (line != null) {
            if (checkDia(line) && line != null) {
                dia = diaToInt(line);
                line = br.readLine();
                while (!checkDia(line) && line != null) {
                    if (checkHora(line) && line != null) {
                        hora = horaToInt(line);
                        line = br.readLine();
                        boolean pointLine = true;
                        while (line.charAt(0) != '-' && line != null) {
                            if (pointLine) {
                                line = br.readLine();
                                pointLine = false;
                            }
                            addSesio(dia, hora, line);
                            line = br.readLine();
                        }
                        pointLine = true;
                    } else {
                        line = br.readLine();
                    }
                }

            } else {
                line = br.readLine();
            }
        }
        return horario;
    }

    private String stringToDiaSemana(String num) {
        String dia;
        switch (num) {
            case "1":
                dia = "Lunes";
                break;
            case "2":
                dia = "Martes";
                break;
            case "3":
                dia = "Miercoles";
                break;
            case "4":
                dia = "Jueves";
                break;
            case "5":
                dia = "Viernes";
                break;
            case "6":
                dia = "Sabado";
                break;
            case "7":
                dia = "Domingo";
                break;
            default:
                dia = "ERROR EN HORARIO";
                break;
        }
        return dia;
    }

    public void writeHorarioFromMap(HashMap<Integer, HashMap<Integer, ArrayList<String>>> h, String idHorario) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("DATA/Output/" + idHorario + ".txt", "UTF-8");
        } catch (Exception e) {
            System.out.println("ERROR EN ESCRIBIR EL HORARIO: PATH NO ENCONTRADO");
        }

        String hStr = "Horario: " + idHorario + '\n';
        hStr += "";

        for (Map.Entry<Integer, HashMap<Integer, ArrayList<String>>> dia : horario.entrySet()) { //Horari
            hStr += "----------------------------------------------------------------\n";
            hStr += "----------------------------------------------------------------\n";
            hStr += (stringToDiaSemana(dia.getKey().toString()) + '\n'); //dia

            Map<Integer, ArrayList<String>> hs = new TreeMap<>(dia.getValue());
            Set s = hs.entrySet();
            Iterator it = s.iterator();
            while (it.hasNext()) {
                Map.Entry hora = (Map.Entry) it.next();
                ArrayList<String> aux = new ArrayList<String>(hs.get(hora.getKey()));
                if (!aux.isEmpty()) {
                    hStr += "----------------------------------------------------------------\n";
                    Integer horaAux = (Integer) hora.getKey();
                    Integer auxh = horaAux+1;
                    String sAux = "";
                    if (horaAux < 9) {
                        sAux = ("0" + horaAux.toString() + ":00-0" + auxh.toString() + ":00\n");
                    } else if (horaAux == 9) {
                        sAux = ("0" + horaAux.toString() + ":00-" + auxh.toString() + ":00\n");
                    } else {
                        sAux = (horaAux.toString() + ":00-" + auxh.toString() + ":00\n");
                    }

                    hStr += sAux; //hora
                    hStr += "................................................................\n";

                    for (String sesion : aux) { //Hora
                        hStr += (sesion + '\n'); //sessio
                    }
                }
            }
        }
        hStr += "----------------------------------------------------------------";
        writer.println(hStr);
        writer.close();
    }

    /**
     * Guarda un Horario en un archivo txt.
     * @param horario       String con la información de un Horario.
     * @param idHorario     Código identificador del Horario.
     * @throws IOException
     */
    public void writeHorario(String horario, String idHorario) throws IOException {
        PrintWriter writer = new PrintWriter("DATA/Output/" + idHorario + ".txt", "UTF-8");
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
