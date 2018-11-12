package domain.drivers;

import domain.TipoClase;
import domain.Aula;

import java.util.*;
import static java.lang.System.out;

/** Imports **/

public class driverAula {

    public static void testConstructor() {
        Scanner sc = new Scanner(System.in);
        out.println("Test Constructor");
        out.println("Introduzca id:");
        String id = sc.nextLine();
        out.println("Introduzca plazas:");
        Integer plazas = Integer.parseInt(sc.nextLine());
        out.println("Introduzca número de tipos (entre 1 y 3):");
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
        for (int i = 0; i < n; ++i) {
            out.println("Introduzca tipo (Teoria/Laboratorio/Problemas):");
            String t = sc.nextLine();
            if (t.equals("Teoria") || t.equals("Laboratorio") || t.equals("Problemas")) {
                tipos.add(TipoClase.valueOf(t));
            }
            else {
                --i;
                out.println("No has introducido un tipo vàlido.");
            }
        }
        Aula aula = new Aula(id, plazas, tipos);
        out.println("Aula creada exitosamente.");
    }

    public static void testGetId() {
        Scanner sc = new Scanner(System.in);
        out.println("Test getId()");
        out.println("Introduzca id:");
        String id = sc.nextLine();
        Aula aula = new Aula(id, 0, null);
        if (id.equals(aula.getId())) out.println("El valor de getId() se corresponde con el id dado.");
        else out.println("El valor de getId() no se corresponde con el id dado.");
    }

    public static void testGetPlazas() {
        Scanner sc = new Scanner(System.in);
        out.println("Test getPlazas()");
        out.println("Introduzca plazas:");
        Integer plazas = Integer.parseInt(sc.nextLine());
        Aula aula = new Aula(null, plazas, null);
        if (plazas.equals(aula.getPlazas())) out.println("El valor de getPlazas() se corresponde con el número de plazas dado.");
        else out.println("El valor de getPlazas() no se corresponde con el número de plazas dado.");
    }

    public static void testGetTipos() {
        Scanner sc = new Scanner(System.in);
        out.println("Test getTipos()");
        out.println("Introduzca número de tipos (entre 1 y 3):");
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
        for (int i = 0; i < n; ++i) {
            out.println("Introduzca tipo (Teoria/Laboratorio/Problemas):");
            String t = sc.nextLine();
            if (t.equals("Teoria") || t.equals("Laboratorio") || t.equals("Problemas")) {
                tipos.add(TipoClase.valueOf(t));
            }
            else {
                --i;
                out.println("No has introducido un tipo vàlido.");
            }
        }
        Aula aula = new Aula(null, 0, tipos);
        if (tipos.equals(aula.getTipos())) out.println("El valor de getTipos() se corresponde con los tipos dados.");
        else out.println("El valor de getTipos() no se corresponde con los tipos dados.");
    }

    public static void testSetId() {
        Scanner sc = new Scanner(System.in);
        out.println("Test setId(String id)");
        Aula aula = new Aula(null, 0, null);
        out.println("Introduzca id:");
        String id = sc.nextLine();
        aula.setId(id);
        if (aula.getId().equals(id)) out.println("El valor de id ha cambiado al valor introducido.");
        else out.println("El valor de id ha no cambiado al valor introducido.");
    }

    public static void testSetPlazas() {
        Scanner sc = new Scanner(System.in);
        out.println("Test setPlazas(Integer plazas)");
        Aula aula = new Aula(null, -999, null);
        out.println("Introduzca plazas:");
        Integer plazas = Integer.parseInt(sc.nextLine());
        aula.setPlazas(plazas);
        if (aula.getPlazas().equals(plazas)) out.println("El valor de plazas ha cambiado al valor introducido.");
        else out.println("El valor de plazas ha no cambiado al valor introducido.");
    }

    public static void testSetTipos() {
        Scanner sc = new Scanner(System.in);
        out.println("Test setTipos(ArrayList<TipoClase> tipos)");
        Aula aula = new Aula(null, 0, null);
        out.println("Introduzca número de tipos (entre 1 y 3):");
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
        for (int i = 0; i < n; ++i) {
            out.println("Introduzca tipo (Teoria/Laboratorio/Problemas):");
            String t = sc.nextLine();
            if (t.equals("Teoria") || t.equals("Laboratorio") || t.equals("Problemas")) {
                tipos.add(TipoClase.valueOf(t));
            }
            else {
                --i;
                out.println("No has introducido un tipo vàlido.");
            }
        }
        aula.setTipos(tipos);
        if (aula.getTipos().equals(tipos)) out.println("tipos ha cambiado a los valores introducidos.");
        else out.println("tipos ha no cambiado a los valores introducidos.");
    }

    public static void testTieneTeoria() {
        Scanner sc = new Scanner(System.in);
        out.println("Test tieneTeoria()");
        out.println("Introduzca número de tipos (entre 1 y 3):");
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
        Boolean teoria = false;
        for (int i = 0; i < n; ++i) {
            out.println("Introduzca tipo (Teoria/Laboratorio/Problemas):");
            String t = sc.nextLine();
            if (t.equals("Teoria") || t.equals("Laboratorio") || t.equals("Problemas")) {
                if (t.equals("Teoria")) teoria = true;
                tipos.add(TipoClase.valueOf(t));
            }
            else {
                --i;
                out.println("No has introducido un tipo vàlido.");
            }
        }
        Aula aula = new Aula(null, 0, tipos);
        if (teoria == aula.tieneTeoria()) out.println("tieneTeoria() funciona correctamente.");
        else out.println("tieneTeoria() no funciona correctamente.");
    }

    public static void testTieneLaboratorio() {
        Scanner sc = new Scanner(System.in);
        out.println("Test tieneLaboratorio()");
        out.println("Introduzca número de tipos (entre 1 y 3):");
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
        Boolean laboratorio = false;
        for (int i = 0; i < n; ++i) {
            out.println("Introduzca tipo (Teoria/Laboratorio/Problemas):");
            String t = sc.nextLine();
            if (t.equals("Teoria") || t.equals("Laboratorio") || t.equals("Problemas")) {
                if (t.equals("Laboratorio")) laboratorio = true;
                tipos.add(TipoClase.valueOf(t));
            }
            else {
                --i;
                out.println("No has introducido un tipo vàlido.");
            }
        }
        Aula aula = new Aula(null, 0, tipos);
        if (laboratorio == aula.tieneLaboratorio()) out.println("tieneLaboratorio() funciona correctamente.");
        else out.println("tieneLaboratorio() no funciona correctamente.");
    }

    public static void testTieneProblemas() {
        Scanner sc = new Scanner(System.in);
        out.println("Test tieneProblemas()");
        out.println("Introduzca número de tipos (entre 1 y 3):");
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
        Boolean problemas = false;
        for (int i = 0; i < n; ++i) {
            out.println("Introduzca tipo (Teoria/Laboratorio/Problemas):");
            String t = sc.nextLine();
            if (t.equals("Teoria") || t.equals("Laboratorio") || t.equals("Problemas")) {
                if (t.equals("Problemas")) problemas = true;
                tipos.add(TipoClase.valueOf(t));
            }
            else {
                --i;
                out.println("No has introducido un tipo vàlido.");
            }
        }
        Aula aula = new Aula(null, 0, tipos);
        if (problemas == aula.tieneProblemas()) out.println("tieneProblemas() funciona correctamente.");
        else out.println("tieneProblemas() no funciona correctamente.");
    }

    public static void testToString() {
        Scanner sc = new Scanner(System.in);
        out.println("Test toString()");
        out.println("Introduzca id:");
        String id = sc.nextLine();
        out.println("Introduzca plazas:");
        Integer plazas = Integer.parseInt(sc.nextLine());
        out.println("Introduzca número de tipos (entre 1 y 3):");
        int n = Integer.parseInt(sc.nextLine());
        ArrayList<TipoClase> tipos = new ArrayList<TipoClase>();
        for (int i = 0; i < n; ++i) {
            out.println("Introduzca tipo (Teoria/Laboratorio/Problemas):");
            String t = sc.nextLine();
            if (t.equals("Teoria") || t.equals("Laboratorio") || t.equals("Problemas")) {
                tipos.add(TipoClase.valueOf(t));
            }
            else {
                --i;
                out.println("No has introducido un tipo vàlido.");
            }
        }
        Aula aula = new Aula(id, plazas, tipos);
        out.println(aula.toString());
    }

    public static void runAllTests() {
        testConstructor();
        out.println();
        testGetId();
        out.println();
        testGetPlazas();
        out.println();
        testGetTipos();
        out.println();
        testSetId();
        out.println();
        testSetPlazas();
        out.println();
        testSetTipos();
        out.println();
        testTieneTeoria();
        out.println();
        testTieneLaboratorio();
        out.println();
        testTieneProblemas();
        out.println();
        testToString();
        out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        out.println("driverAula");
        String action = "";
        out.println("Opciones:\n    1-Test Constructor\n    2-Test getId()\n    3-Test getPlazas()\n    4-Test getTipos()\n"
                    + "    5-Test setId(String id)\n    6-Test setPlazas(Integer plazas)\n    7-Test setTipos(ArrayList<TipoClase> tipos)\n"
                    + "    8-Test tieneTeoria()\n    9-Test tieneLaboratorio()\n    10-Test tieneProblemas()\n"
                    + "    11-Test toString()\n    12-Run all tests\n    13-Salir");
        out.println("Introduce una opción:");
        action = sc.nextLine();
        while (!action.equals("13")) {
            if (action.equals("1")) {
                testConstructor();
                out.println();
            }
            else if (action.equals("2")) {
                testGetId();
                out.println();
            }
            else if (action.equals("3")) {
                testGetPlazas();
                out.println();
            }
            else if (action.equals("4")) {
                testGetTipos();
                out.println();
            }
            else if (action.equals("5")) {
                testSetId();
                out.println();
            }
            else if (action.equals("6")) {
                testSetPlazas();
                out.println();
            }
            else if (action.equals("7")) {
                testSetTipos();
                out.println();
            }
            else if (action.equals("8")) {
                testTieneTeoria();
                out.println();
            }
            else if (action.equals("9")) {
                testTieneLaboratorio();
                out.println();
            }
            else if (action.equals("10")) {
                testTieneProblemas();
                out.println();
            }
            else if (action.equals("11")) {
                testToString();
                out.println();
            }
            else if (action.equals("12")) {
                runAllTests();
            }
            action = sc.nextLine();
        }
        return;
    }
}
