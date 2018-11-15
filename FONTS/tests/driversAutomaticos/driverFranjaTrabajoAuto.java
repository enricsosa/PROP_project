package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverFranjaTrabajoAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;
    private static Asignatura as1 = null;
    private static Asignatura as2 = null;
    private static Asignatura as3 = null;
    private static Asignatura as4 = null;

    private static Nivel N1 = null;
    private static Nivel N2 = null;
    private static Nivel N3 = null;
    private static Nivel N4 = null;

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
    }

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoFranjaTrabajo.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase FranjaTrabajo\n");
        FranjaTrabajo ft = null;
        initFiles();
        initValues();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(8);
            switch (op) {
                case 1:
                    try {
                        out.print("1- FranjaTrabajo(Integer, Integer)\n");

                        String hI = in.next();
                        int pl = 0;
                        boolean valid = true;
                        for (int i = 0; i < hI.length() && valid; ++i) {
                            pl *= 10;
                            if (hI.charAt(i) >= '0' && hI.charAt(i) <= '9') {
                                pl += (int)(hI.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (valid) {
                            String hF = in.next();
                            int plf = 0;
                            valid = true;
                            for (int i = 0; i < hF.length() && valid; ++i) {
                                plf *= 10;
                                if (hF.charAt(i) >= '0' && hF.charAt(i) <= '9') {
                                    plf += (int)(hF.charAt(i) - '0');
                                } else {
                                    valid = false;
                                }
                            }
                            if (valid) {
                                ft = new FranjaTrabajo(pl, plf);
                                out.println("HA FUNCIONADO CORRECTAMENTE");
                            } else {
                                out.printf("ERROR: el parámetro \"%s\" no es válido\n", hF);
                            }
                        } else {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", hI);
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

                case 2: //getHoraIni
                    try {
                        out.print("2- getHoraIni()\n");

                        out.println(ft.getHoraIni().toString());
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

                case 3: //getHoraFin
                    try {
                        out.print("3- getHoraFin()\n");

                        out.println(ft.getHoraFin().toString());
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

                case 4: //getTipoRestriccion
                    try {
                        out.print("4- getTipoRestriccion()\n");

                        out.println(ft.getTipoRestriccion().toString());
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

                case 5: //comprobarRestriccion
                    try {
                        out.print("5- comprobarRestriccion()\n");

                        Asignatura as = new Asignatura("as", "AS", peAS);
                        Grupo g = new Grupo("Dummy", as);
                        Nivel n1 = new Nivel("n1");
                        SubGrupo sg1 = new SubGrupo("1", 60, TipoClase.valueOf("Teoria"), g);

                        Asignatura as1 = new Asignatura("as1", "AS1", peAS, n1);
                        Sesion s1 = new Sesion(2, TipoClase.valueOf("Teoria"), as1);

                        Clase c = new Clase(sg1, s1);
                        int dia = 2;
                        int hi = 10;
                        Ocupaciones ocu = null;
                        ft.comprobarRestriccion(c,dia,hi,ocu);
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

                case 6: //setHoraIni
                    try {
                        out.print("6- setHoraIni(Integer)\n");

                        String hF = in.next();
                        int plf = 0;
                        boolean valid = true;
                        for (int i = 0; i < hF.length() && valid; ++i) {
                            plf *= 10;
                            if (hF.charAt(i) >= '0' && hF.charAt(i) <= '9') {
                                plf += (int)(hF.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (valid) {
                            ft.setHoraIni(plf);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", hF);
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

                case 7: //setHoraFin
                    try {
                        out.print("7- setHoraFin(Integer)\n");

                        String hF = in.next();
                        int plf = 0;
                        boolean valid = true;
                        for (int i = 0; i < hF.length() && valid; ++i) {
                            plf *= 10;
                            if (hF.charAt(i) >= '0' && hF.charAt(i) <= '9') {
                                plf += (int)(hF.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (valid) {
                            ft.setHoraFin(plf);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", hF);
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

                case 8:
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
        out.print("1- FranjaTrabajo(Integer, Integer)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getHoraIni()\n");
        out.print("3- getHoraFin()\n");
        out.print("4- getTipoRestriccion()\n");
        out.print("5- comprobarRestriccion()\n");
        out.print("MODIFICADORAS:\n");
        out.print("6- setHoraIni(Integer)\n");
        out.print("7- setHoraFin(Integer)\n");
        out.print("8- Salir\n");
        out.print("Tu opción: ");
    }
}
