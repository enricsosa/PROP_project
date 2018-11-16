package tests.driversAutomaticos;

import domain.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverPlanEstudiosAuto {

    private static Scanner in = null;

    private static File file = null;

    private static Nivel n1 = new Nivel("n1");
    private static Nivel n2 = new Nivel("n2");

    private static PlanEstudios peAS = null;
    private static Asignatura as1 = new Asignatura("as1", "AS1", peAS);
    private static Asignatura as2 = new Asignatura("as2", "AS2", peAS);

    private static ArrayList<TipoClase> atc = new ArrayList<TipoClase>();
    private static Aula au1 = new Aula("au1", 80, atc);
    private static Aula au2 = new Aula("au2", 60, atc);

    private static ArrayList<Restriccion> restr = new ArrayList<Restriccion>();
    private static Restriccion re1 = new NivelHora(n1);
    private static Restriccion re2 = new FranjaTrabajo(8, 20);

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

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoPlanEstudios.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase PlanEstudios\n");
        PlanEstudios pe = null;
        initFiles();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(24);
            switch (op) {
                case 1: //PlanEstudios
                    try {
                        out.print("1- PlanEstudios(String)\n");

                        String nom = in.next();
                        pe = new PlanEstudios(nom);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("(AVISO: Plan de Estudios vacío)");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL PLAN DE ESTUDIOS ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 2: //getAllClases
                    try {
                        out.print("2- getAllClases()\n");

                        out.println(pe.getAllClases().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL PLAN DE ESTUDIOS ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 3: //getAllClases
                    try {
                        out.print("3- getNombre()\n");

                        out.println(pe.getNombre().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL PLAN DE ESTUDIOS ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 4: //getNiveles
                    try {
                        out.print("4- getNiveles()\n");

                        out.println(pe.getNiveles().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL PLAN DE ESTUDIOS ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 5: //getNivel
                    try {
                        out.print("5- getNivel(String)\n");

                        String nivel = in.next();
                        out.println(pe.getNivel(nivel).toString());
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

                case 6: //getAsignaturas
                    try {
                        out.print("6- getAsignaturas()\n");

                        out.println(pe.getAsignaturas().toString());
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

                case 7: //getAsignatura
                    try {
                        out.print("7- getAsignatura(String)\n");

                        String as = in.next();
                        out.println(pe.getAsignatura(as).toString());
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

                case 8: //getAulas
                    try {
                        out.print("8- getAulas()\n");

                        out.println(pe.getAulas().toString());
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

                case 9: //getAulasTeoria
                    try {
                        out.print("9- getAulasTeoria(int)\n");

                        String plazas = in.next();
                        int pl = 0;
                        boolean valid = true;
                        for (int i = 0; i < plazas.length() && valid; ++i) {
                            pl *= 10;
                            if (plazas.charAt(i) >= '0' && plazas.charAt(i) <= '9') {
                                pl += (int)(plazas.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        pe.getAulasTeoria(pl);
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

                case 10: //getAulasLaboratorio
                    try {
                        out.print("10- getAulasLaboratorio(int)\n");

                        String plazas = in.next();
                        int pl = 0;
                        boolean valid = true;
                        for (int i = 0; i < plazas.length() && valid; ++i) {
                            pl *= 10;
                            if (plazas.charAt(i) >= '0' && plazas.charAt(i) <= '9') {
                                pl += (int)(plazas.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        pe.getAulasLaboratorio(pl);
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

                case 11: //getAulasProblemas
                    try {
                        out.print("11- getAulasProblemas(int)\n");

                        String plazas = in.next();
                        int pl = 0;
                        boolean valid = true;
                        for (int i = 0; i < plazas.length() && valid; ++i) {
                            pl *= 10;
                            if (plazas.charAt(i) >= '0' && plazas.charAt(i) <= '9') {
                                pl += (int)(plazas.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        pe.getAulasProblemas(pl);
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

                case 12: //getAula
                    try {
                        out.print("12- getAula(String)\n");

                        String au = in.next();
                        out.println(pe.getAula(au).toString());
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

                case 13: //getRestricciones
                    try {
                        out.print("13- getRestricciones()\n");

                        out.println(pe.getRestricciones().toString());
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

                case 14: //getRestriccion
                    try {
                        out.print("14- getRestriccion(int)\n");

                        try {
                            out.println(pe.getRestricciones());
                        } catch (Exception e) {
                            out.println("ERROR EN getRestricciones()");
                            out.println("\n");
                            break;
                        }
                        String plazas = in.next();
                        int pl = 0;
                        boolean valid = true;
                        for (int i = 0; i < plazas.length() && valid; ++i) {
                            pl *= 10;
                            if (plazas.charAt(i) >= '0' && plazas.charAt(i) <= '9') {
                                pl += (int)(plazas.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        out.println(pe.getRestriccion(pl).toString());
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

                case 15: //setNombre
                    try {
                        out.print("15- setNombre(String)\n");

                        String nom = in.next();
                        pe.setNombre(nom);
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

                case 16: //addNivel
                    try {
                        out.print("16- addNivel(Nivel)\n");

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
                                pe.addNivel(n1);
                            else
                                pe.addNivel(n2);
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

                case 17: //addAsignatura
                    try {
                        out.print("17- addAsignatura(Asignatura)\n");

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
                                pe.addAsignatura(as1);
                            else
                                pe.addAsignatura(as2);

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

                case 18: //eliminarAsignatura
                    try {
                        out.print("18- eliminarAsignatura(String)\n");

                        String as = in.next();
                        pe.eliminarAsignatura(as);
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

                case 19: //addAula
                    try {
                        out.print("19- addAula(Aula)\n");

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
                                pe.addAula(au1);
                            else
                                pe.addAula(au2);

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

                case 20: //eliminarAula
                    try {
                        out.print("20- eliminarAula(String)\n");

                        String au = in.next();
                        pe.eliminarAula(au);
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

                case 21: //setRestricciones
                    try {
                        out.print("21- setRestricciones(ArrayList<Restriccion>)\n");

                        out.println("Se van a introducir las siguientes Restricciones:");
                        restr.add(re1); restr.add(re2);
                        out.println(restr.toString());
                        pe.setRestricciones(restr);
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

                case 22: //addRestriccion
                    try {
                        out.print("22- addRestriccion(Restriccion)\n");

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
                                pe.addRestriccion(re1);
                            else
                                pe.addRestriccion(re2);

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

                case 23: //eliminarRestriccion
                    try {
                        out.print("23- eliminarRestriccion(Integer)\n");

                        String plazas = in.next();
                        int pl = 0;
                        boolean valid = true;
                        for (int i = 0; i < plazas.length() && valid; ++i) {
                            pl *= 10;
                            if (plazas.charAt(i) >= '0' && plazas.charAt(i) <= '9') {
                                pl += (int)(plazas.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        pe.eliminarRestriccion(pl);
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

                case 24:
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
        out.print("1- PlanEstudios(String)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getAllClases()\n");
        out.print("3- getNombre()\n");
        out.print("4- getNiveles()\n");
        out.print("5- getNivel(String)\n");
        out.print("6- getAsignaturas()\n");
        out.print("7- getAsignatura(String)\n");
        out.print("8- getAulas()\n");
        out.print("9- getAulasTeoria(int)\n");
        out.print("10- getAulasLaboratorio(int)\n");
        out.print("11- getAulasProblemas(int)\n");
        out.print("12- getAula(String)\n");
        out.print("13- getRestricciones()\n");
        out.print("14- getRestriccion(int)\n");
        out.print("MODIFICADORAS:\n");
        out.print("15- setNombre(String)\n");
        out.print("16- addNivel(Nivel)\n");
        out.print("17- addAsignatura(Asignatura)\n");
        out.print("18- eliminarAsignatura(String)\n");
        out.print("19- addAula(Aula)\n");
        out.print("20- eliminarAula(String)\n");
        out.print("21- setRestricciones(ArrayList<Restriccion>)\n");
        out.print("22- addRestriccion(Restriccion)\n");
        out.print("23- eliminarRestriccion(Integer)\n");
        out.print("24- Salir\n");
        out.print("Tu opción: ");
    }
}
