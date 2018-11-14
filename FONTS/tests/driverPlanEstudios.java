package tests;

import domain.*;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import static java.lang.System.out;

public class driverPlanEstudios {

    private static Scanner in = new Scanner(System.in);

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

    public static void main (String argv[]) {
        out.print("Driver clase PlanEstudios\n");
        PlanEstudios pe = null;
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(24);
            switch (op) {
                case 1: //PlanEstudios
                    try {
                        out.println("Introduzca el nombre del Plan de Estudios:");
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
                        out.println("Introduzca el nombre del Nivel a obtener:");
                        out.println("\n(Array de niveles:)");
                        try {
                            out.println(pe.getNiveles());
                        } catch (Exception e) {
                            out.println("ERROR EN getNiveles()");
                            out.println("\n");
                            break;
                        }
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
                        out.println("Introduzca el nombre de la Asignatura a obtener:");
                        out.println("\n(Array de asignaturas:)");
                        try {
                            out.println(pe.getAsignaturas());
                        } catch (Exception e) {
                            out.println("ERROR EN getAsignaturas()");
                            out.println("\n");
                            break;
                        }
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
                        out.println("Introduzca el número de plazas para filtar las Aulas de Teoria con menor");
                        out.println("plazas que dicho número:");
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
                        out.println("Introduzca el número de plazas para filtar las Aulas de Laboratorio con menor");
                        out.println("plazas que dicho número:");
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
                        out.println("Introduzca el número de plazas para filtar las Aulas de Problemas con menor");
                        out.println("plazas que dicho número:");
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
                        out.println("Introduzca el nombre del Aula a obtener:");
                        out.println("\n(Array de aulas:)");
                        try {
                            out.println(pe.getAulas());
                        } catch (Exception e) {
                            out.println("ERROR EN getAulas()");
                            out.println("\n");
                            break;
                        }
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
                        out.println("Introduzca el número de la posición de la Restriccion en el Array");
                        out.println("de restricciones del Plan de Estudios (para obtener dicha Restriccion)");
                        out.println("\n(Array de restricciones:)");
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
                        out.println("Introduzca el nuevo nombre del Plan de Estudios:");
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
                        out.println("Niveles disponibles predeterminados:");
                        out.println("1- n1");
                        out.println("2- n2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge uno para añadirlo: ");
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
                        out.println("Asignaturas disponibles predeterminadas:");
                        out.println("1- as1");
                        out.println("2- as2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge una para añadirla: ");
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
                        out.println("Introduzca el nombre de la Asignatura a eliminar:");
                        out.println("\n(Array de asignaturas:)");
                        try {
                            out.println(pe.getAsignaturas());
                        } catch (Exception e) {
                            out.println("ERROR EN getAsignaturas()");
                            out.println("\n");
                            break;
                        }
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
                        out.println("Aulas disponibles predeterminadas:");
                        out.println("1- au1");
                        out.println("2- au2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge una para añadirla: ");
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
                        out.println("Introduzca el nombre del Aula a eliminar:");
                        out.println("\n(Array de aulas:)");
                        try {
                            out.println(pe.getAulas());
                        } catch (Exception e) {
                            out.println("ERROR EN getAulas()");
                            out.println("\n");
                            break;
                        }
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
                        out.println("Restricciones disponibles predeterminadas:");
                        out.println("1- re1");
                        out.println("2- re2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge una para añadirla: ");
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
                        out.println("Introduzca el número de la posición de la Restriccion en el Array");
                        out.println("de restricciones del Plan de Estudios (para eliminar dicha Restriccion)");
                        out.println("\n(Array de restricciones:)");
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
            if (op != 0) {
                mostrarMenu();
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
