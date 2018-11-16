package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverNivelAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;
    private static Asignatura as1 = new Asignatura("as1", "AS1", peAS);
    private static Asignatura as2 = new Asignatura("as2", "AS2", peAS);
    private static ArrayList<Asignatura> assigs = new ArrayList<Asignatura>();
    private static ArrayList<Restriccion> restr = new ArrayList<Restriccion>();

    private static Nivel n1 = new Nivel("n1");
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

    private static void initValues() {
        assigs.add(as1); assigs.add(as2);
        restr.add(re1); restr.add(re2);
    }

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoNivel.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase Nivel\n");
        Nivel nivel = null;
        initFiles();
        initValues();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(14);
            switch (op) {
                case 1:
                    try {
                        out.print("1- Nivel(String)\n");

                        String nom = in.next();
                        nivel = new Nivel(nom);
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

                case 2: //getNombre
                    try {
                        out.print("2- getNombre()\n");

                        out.println(nivel.getNombre());
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

                case 3: //getAsignaturas
                    try {
                        out.print("3- getAsignaturas()\n");

                        out.println(nivel.getAsignaturas().toString());
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

                case 4: //getAsignatura
                    try {
                        out.print("4- getAsignatura(String)\n");

                        String id = in.next();
                        out.println(nivel.getAsignatura(id).toString());
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

                case 5: //tieneAsignatura
                    try {
                        out.print("5- tieneAsignatura(Asignatura)\n");

                        String nom = in.next();
                        Asignatura a = null;
                        if (nom.equals("as1") || nom.equals("as2")) {
                            if (nom.equals("as1"))
                                a = as1;
                            else
                                a = as2;
                        } else {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                        }
                        if (nivel.tieneAsignatura(a)) {
                            out.println("El Nivel tiene la Asignatura");
                        } else {
                            out.println("El Nivel NO tiene la Asignatura");
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

                case 6: //getRestricciones
                    try {
                        out.print("6- getRestricciones()\n");

                        out.println(nivel.getRestricciones().toString());
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

                case 7: //getRestriccion
                    try {
                        out.print("7- getRestriccion(int)\n");

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
                        out.println(nivel.getRestriccion(pl).toString());
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

                case 8: //setNombre
                    try {
                        out.print("8- setNombre(String)\n");

                        String nom = in.next();
                        nivel.setNombre(nom);
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

                case 9: //addAsignatura
                    try {
                        out.print("9- addAsignatura(Asignatura)\n");

                        String as = in.next();
                        int ni = 0;
                        boolean valid = true;
                        for (int i = 0; i < as.length() && valid; ++i) {
                            ni *= 10;
                            if (as.charAt(i) >= '0' && as.charAt(i) <= '9') {
                                ni += (int)(as.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (ni == 1 || ni == 2) {
                            if (ni == 1)
                                nivel.addAsignatura(as1);
                            else
                                nivel.addAsignatura(as2);

                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else if (ni != 3) {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", as);
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

                case 10: //eliminarAsignatura
                    try {
                        out.print("10- eliminarAsignatura(String)\n");

                        String as = in.next();
                        nivel.eliminarAsignatura(as);
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

                case 11: //setRestricciones
                    try {
                        out.print("11- setRestricciones(ArrayList<Restriccion>)\n");

                        out.println("Se van a introducir las siguientes Restricciones:");
                        out.println(restr.toString());
                        nivel.setRestricciones(restr);
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

                case 12: //addRestriccion
                    try {
                        out.print("12- addRestriccion(Restriccion)\n");

                        String re = in.next();
                        int ni = 0;
                        boolean valid = true;
                        for (int i = 0; i < re.length() && valid; ++i) {
                            ni *= 10;
                            if (re.charAt(i) >= '0' && re.charAt(i) <= '9') {
                                ni += (int)(re.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (ni == 1 || ni == 2) {
                            if (ni == 1)
                                nivel.addRestriccion(re1);
                            else
                                nivel.addRestriccion(re2);

                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else if (ni != 3) {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", re);
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

                case 13: //eliminarRestriccion
                    try {
                        out.print("13- eliminarRestriccion(Integer)\n");

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
                        nivel.eliminarRestriccion(pl);
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

                case 14:
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
        out.print("1- Nivel(String)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getNombre()\n");
        out.print("3- getAsignaturas()\n");
        out.print("4- getAsignatura(String)\n");
        out.print("5- tieneAsignatura(Asignatura)\n");
        out.print("6- getRestricciones()\n");
        out.print("7- getRestriccion(int)\n");
        out.print("MODIFICADORAS:\n");
        out.print("8- setNombre(String)\n");
        out.print("9- addAsignatura(Asignatura)\n");
        out.print("10- eliminarAsignatura(String)\n");
        out.print("11- setRestricciones(ArrayList<Restriccion>)\n");
        out.print("12- addRestriccion(Restriccion)\n");
        out.print("13- eliminarRestriccion(Integer)\n");
        out.print("14- Salir\n");
        out.print("Tu opción: ");
    }

}
