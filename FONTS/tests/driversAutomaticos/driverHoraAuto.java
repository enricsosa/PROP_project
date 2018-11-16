package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverHoraAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;
    private static ArrayList<TipoClase> atc = new ArrayList<TipoClase>();
    private static Aula au1 = new Aula("au1", 80, atc);
    private static Aula au2 = new Aula("au2", 60, atc);
    private static Aula au3 = new Aula("au3", 80, atc);
    private static Aula au4 = new Aula("au4", 60, atc);

    private static SubGrupo sg1 = null;
    private static SubGrupo sg2 = null;
    private static SubGrupo sg3 = null;
    private static SubGrupo sg4 = null;

    private static Sesion s1 = null;
    private static Sesion s2 = null;
    private static Sesion s3 = null;
    private static Sesion s4 = null;
    private static ArrayList<Sesion> sesions = new ArrayList<Sesion>();

    private static Clase c1 = null;
    private static Clase c2 = null;
    private static Clase c3 = null;
    private static Clase c4 = null;

    private static Nivel n1 = new Nivel("n1");
    private static Nivel n2 = new Nivel("n2");
    private static Nivel n3 = new Nivel("n3");
    private static Nivel n4 = new Nivel("n4");
    private static ArrayList<Restriccion> restr = new ArrayList<Restriccion>();
    private static Restriccion re1 = new NivelHora(n1);
    private static Restriccion re2 = new FranjaTrabajo(8, 20);

    private static Asignacion asign1 = null;
    private static Asignacion asign2 = null;
    private static Asignacion asign3 = null;
    private static Asignacion asign4 = null;
    private static Map<String, Asignacion> asiganciones = new HashMap<String, Asignacion>();

    private static Asignatura as = new Asignatura("as", "AS", peAS);
    private static Grupo g1 = new Grupo("Gr1", as);
    private static Grupo g2 = new Grupo("Gr2", as);
    private static Grupo g3 = new Grupo("Gr3", as);
    private static Grupo g4 = new Grupo("Gr4", as);

    private static int obtenerOp(int nOps) throws InputMismatchException {
        int op = -1;
        while (op == -1) {
            String s = in.next();
            int num = 0;
            try {
                if (s.length() == 1) {
                    num = (int)(s.charAt(0) - '0');
                } else {
                    num = (int)((int)(s.charAt(0) - '0')*10) + (int)(s.charAt(1) - '0');
                }
            } catch (Exception e) {
                out.printf("ERROR: el parámetro \"%s\" no es válido\n", s);
            }
            if (num > 0 && num <= nOps && s.length() < 3) {
                op = num;
            } else {
                out.printf("ERROR: el parámetro \"%s\" no es válido\n", s);
                out.print("Opción: ");
            }
        }
        out.print("\n");
        return op;
    }

    private static void initValues() {
        sg1 = new SubGrupo("1", 60, TipoClase.valueOf("Teoria"), g1);
        sg2 = new SubGrupo("2", 30, TipoClase.valueOf("Problemas"), g2);

        Asignatura as1 = new Asignatura("as1", "AS1", peAS, n1);
        Asignatura as2 = new Asignatura("as2", "AS2", peAS);
        s1 = new Sesion(2, TipoClase.valueOf("Teoria"), as1);
        s2 = new Sesion(1, TipoClase.valueOf("Problemas"), as2);
        sesions.add(s1);
        sesions.add(s2);

        c1 = new Clase(sg1, s1);
        c2 = new Clase(sg2, s2);

        restr.add(re1); restr.add(re2);

        as.setRestricciones(restr);

        asign1 = new Asignacion(8, 4, au1, c1);
        asign2 = new Asignacion(14, 2, au2, c2);
        asiganciones.put("asign1", asign1); asiganciones.put("asign2", asign2);
    }

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoHora.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase Hora\n");
        Hora hora = null;
        initFiles();
        initValues();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(21);
            switch (op) {
                case 1: //getTipos
                    try {
                        out.print("1- Hora()\n");

                        out.println("Hora vacía de asignaciones");
                        hora = new Hora();
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL AULA ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 2: //Hora(Hora)
                    try {
                        out.print("2- Hora(Hora)\n");

                        Hora b = null;
                        b = new Hora(hora);
                        out.println(b.toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 3:
                    try {
                        out.print("3- getNiveles()\n");

                        out.println(hora.getNiveles());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 4: //getNivel
                    try {
                        out.print("4- getNivel(String)\n");

                        String plazas = in.next();
                        hora.getNivel(plazas);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 5: //tieneNivel
                    try {
                        out.print("5- tieneNivel(Nivel)\n");

                        String plazas = in.next();

                        if (hora.tieneNivel(hora.getNivel(plazas))) {
                            out.println("Está el Nivel");
                        } else {
                            out.println("NO Está el Nivel");
                        }
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 6:
                    try {
                        out.print("6- getAsignaturas()\n");

                        out.println(hora.getAsignaturas());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 7:
                    try {
                        out.print("7- getAsignatura(String)\n");

                        String plazas = in.next();
                        hora.getAsignatura(plazas);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 8: //tieneNivel
                    try {
                        out.print("8- tieneAsignatura(Asignatura)\n");

                        String plazas = in.next();

                        if (hora.tieneAsignatura(hora.getAsignatura(plazas))) {
                            out.println("Está el Nivel");
                        } else {
                            out.println("NO Está el Nivel");
                        }
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 9:
                    try {
                        out.print("9- getAulas()\n");

                        out.println(hora.getAulas());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 10:
                    try {
                        out.print("10- getAula(String)\n");

                        String plazas = in.next();
                        hora.getAula(plazas);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 11: //tieneAula
                    try {
                        out.print("11- tieneAula(Aula)\n");

                        String plazas = in.next();

                        if (hora.tieneAula(hora.getAula(plazas))) {
                            out.println("Está el Nivel");
                        } else {
                            out.println("NO Está el Nivel");
                        }
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 12: //getGrupos
                    try {
                        out.print("12- getGrupos()\n");

                        out.println(hora.getGrupos());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 13: //tieneGrupo
                    try {
                        out.print("13- tieneGrupo(Grupo)\n");

                        String plazas = in.next();

                        Grupo g = null;
                        if (plazas.equals("1")) {
                            g = g1;
                            if (hora.tieneGrupo(g)) {
                                out.println("Está el Grupo");
                            } else {
                                out.println("NO está el Grupo");
                            }
                            out.println("HA FUNCIONADO CORRECTAMENTE");

                        } else if (plazas.equals("2")) {
                            g = g2;
                            if (hora.tieneGrupo(g)) {
                                out.println("Está el Grupo");
                            } else {
                                out.println("NO está el Grupo");
                            }
                            out.println("HA FUNCIONADO CORRECTAMENTE");

                        } else if (!plazas.equals("3")) {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", plazas);
                        }
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 14: //getSubGrupos
                    try {
                        out.print("14- getSubGrupos()\n");

                        out.println(hora.getSubGrupos());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 15: //tieneSubGrupo
                    try {
                        out.print("15- tieneSubGrupo(SubGrupo)\n");

                        String plazas = in.next();

                        SubGrupo sg = null;
                        if (plazas.equals("1")) {
                            sg = sg1;
                            if (hora.tieneSubGrupo(sg)) {
                                out.println("Está el SubGrupo");
                            } else {
                                out.println("NO está el SubGrupo");
                            }
                            out.println("HA FUNCIONADO CORRECTAMENTE");

                        } else if (plazas.equals("2")) {
                            sg = sg2;
                            if (hora.tieneSubGrupo(sg)) {
                                out.println("Está el SubGrupo");
                            } else {
                                out.println("NO está el SubGrupo");
                            }
                            out.println("HA FUNCIONADO CORRECTAMENTE");

                        } else if (!plazas.equals("3")) {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", plazas);
                        }
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 16: //getAsignaciones
                    try {
                        out.print("16- getAsignaciones()\n");

                        out.println(hora.getAsignaciones());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 17: //getAsignacion
                    try {
                        out.print("17- getAsignacion(String)\n");

                        String plazas = in.next();
                        hora.getAsignacion(plazas);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 18: //setAsignaciones
                    try {
                        out.print("18- setAsignaciones(Map<String, Asignacion>)\n");

                        out.println("Se van a introducir las siguientes Asignaciones: ");
                        out.println(asiganciones.toString());
                        hora.setAsignaciones(asiganciones);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 19: //addAsignacion
                    try {
                        out.print("19- addAsignacion(Asignacion)\n");

                        String nivel = in.next();
                        int ni = 0;
                        boolean valid = true;
                        for (int i = 0; i < nivel.length() && valid; ++i) {
                            ni *= 10;
                            if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                ni += (int)(nivel.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (ni == 1 || ni == 2) {
                            if (ni == 1)
                                hora.addAsignacion(asign1);
                            else
                                hora.addAsignacion(asign2);

                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else if (ni != 3) {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                        }
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 20: //eliminarAsignacion
                    try {
                        out.print("20- eliminarAsignacion(String)\n");

                        String plazas = in.next();
                        hora.eliminarAsignacion(plazas);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 21:
                    out.println("Saliendo...");
                    op = 0;
                    break;

                default:

                    break;
            }
        }
    }

    private static void mostrarMenu() {
        out.print("Opciones:\n");
        out.print("CONSTRUCTORAS:\n");
        out.print("1- Hora()\n");
        out.print("2- Hora(Hora)\n");
        out.print("CONSULTORAS:\n");
        out.print("3- getNiveles()\n");
        out.print("4- getNivel(String)\n");
        out.print("5- tieneNivel(Nivel)\n");
        out.print("6- getAsignaturas()\n");
        out.print("7- getAsignatura(String)\n");
        out.print("8- tieneAsignatura(Asignatura)\n");
        out.print("9- getAulas()\n");
        out.print("10- getAula(String)\n");
        out.print("11- tieneAula(Aula)\n");
        out.print("12- getGrupos()\n");
        out.print("13- tieneGrupo(Grupo)\n");
        out.print("14- getSubGrupos()\n");
        out.print("15- tieneSubGrupo(SubGrupo)\n");
        out.print("16- getAsignaciones()\n");
        out.print("17- getAsignacion(String)\n");
        out.print("MODIFICADORAS:\n");
        out.print("18- setAsignaciones(Map<String, Asignacion>)\n");
        out.print("19- addAsignacion(Asignacion)\n");
        out.print("20- eliminarAsignacion(String)\n");
        out.print("21- Salir\n");
        out.print("Tu opción: ");
    }
}
