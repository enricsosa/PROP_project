package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverDiaLibreAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;

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
        file = new File("FONTS/tests/juegos_de_prueba/juegoDiaLibre.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase DiaLibre\n");
        DiaLibre dl = null;
        initFiles();
        initValues();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(6);
            switch (op) {
                case 1: //setPlazas(String)
                    try {
                        out.print("1- DiaLibre(Integer)\n");

                        String nivel = in.next();
                        int dia = 0;
                        boolean valid = true;
                        if (nivel.length() == 1) {
                            for (int i = 0; i < nivel.length() && valid; ++i) {
                                dia *= 10;
                                if (nivel.charAt(i) >= '1' && nivel.charAt(i) <= '7') {
                                    dia += (int) (nivel.charAt(i) - '0');
                                } else {
                                    valid = false;
                                }
                            }
                        } else {
                            valid = false;
                        }
                        if (valid) {
                            dl = new DiaLibre(dia);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else {
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

                case 2: //getDia
                    try {
                        out.print("2- getDia()\n");

                        out.println(dl.getDia().toString());
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

                case 3: //getTipoRestriccion
                    try {
                        out.print("3- (Abstracta)getTipoRestriccion()\n");

                        out.println(dl.getTipoRestriccion().toString());
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

                case 4: //comprobarRestriccion
                    try {
                        out.print("4- (Abstracta)comprobarRestriccion()\n");

                        Clase c = null;
                        int dia = 2;
                        int hi = 10;
                        Ocupaciones ocu = null;
                        dl.comprobarRestriccion(c,dia,hi,ocu);
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

                case 5: //setPlazas(String)
                    try {
                        out.print("5- setDia(Integer)\n");

                        String nivel = in.next();
                        int dia = 0;
                        boolean valid = true;
                        if (nivel.length() == 1) {
                            for (int i = 0; i < nivel.length() && valid; ++i) {
                                dia *= 10;
                                if (nivel.charAt(i) >= '1' && nivel.charAt(i) <= '7') {
                                    dia += (int) (nivel.charAt(i) - '0');
                                } else {
                                    valid = false;
                                }
                            }
                        } else {
                            valid = false;
                        }
                        if (valid) {
                            dl.setDia(dia);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else {
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

                case 6:
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
        out.print("1- DiaLibre(Integer)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getDia()\n");
        out.print("3- (Abstracta)getTipoRestriccion()\n");
        out.print("4- (Abstracta)comprobarRestriccion()\n");
        out.print("MODIFICADORAS:\n");
        out.print("5- setDia(Integer)\n");
        out.print("6- Salir\n");
        out.print("Tu opción: ");
    }
}
