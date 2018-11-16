package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverPrerequisitoAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;
    private static Asignatura as1 = null;
    private static Asignatura as2 = null;
    private static Asignatura as3 = null;
    private static Asignatura as4 = null;


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
        as1 = new Asignatura("as1", "AS1", peAS);
        as2 = new Asignatura("as2", "AS2", peAS);
        as3 = new Asignatura("as3", "AS3", peAS);
        as4 = new Asignatura("as4", "AS4", peAS);
    }

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoPrerequisito.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase Prerequisito\n");
        Prerrequisito pre = null;
        initValues();
        initFiles();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(8);
            switch (op) {
                case 1:
                    try {
                        out.print("1- Prerrequisito(Asignatura, Asignatura)\n");

                        Asignatura asig1 = null;
                        Asignatura asig2 = null;
                        String nivel = in.next();
                        int ni = 0;
                        boolean valid = true;
                        for (int i = 0; i < nivel.length() && valid; ++i) {
                            ni *= 10;
                            if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                ni += (int) (nivel.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (ni >= 1 && ni <= 4) {
                            nivel = in.next();
                            int nj = 0;
                            valid = true;
                            for (int i = 0; i < nivel.length() && valid; ++i) {
                                nj *= 10;
                                if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                    nj += (int) (nivel.charAt(i) - '0');
                                } else {
                                    valid = false;
                                }
                            }
                            if (nj >= 1 && nj <= 4) {
                                if (ni == 1) {
                                    asig1 = as1;
                                } else if (ni == 2) {
                                    asig1 = as2;
                                } else if (ni == 3) {
                                    asig1 = as3;
                                } else {
                                    asig1 = as4;
                                }

                                if (nj == 1) {
                                    asig2 = as1;
                                } else if (nj == 2) {
                                    asig2 = as2;
                                } else if (nj == 3) {
                                    asig2 = as3;
                                } else {
                                    asig2 = as4;
                                }
                                pre = new Prerrequisito(asig1, asig2);
                                out.println("HA FUNCIONADO CORRECTAMENTE");
                            } else if (nj != 5) {
                                out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                            }
                        } else if (ni != 5) {
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

                case 2: //getAsignatura
                    try {
                        out.print("2- getAsignatura()\n");

                        out.println(pre.getAsignatura().toString());
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

                case 3: //getPrerrequisito
                    try {
                        out.print("3- getPrerrequisito()\n");

                        out.println(pre.getPrerrequisito().toString());
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
                        out.print("4- (Abstracta)getTipoRestriccion()\n");

                        out.println(pre.getTipoRestriccion().toString());
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
                        out.print("5- (Abstracta)comprobarRestriccion()\n");

                        Clase c = null;
                        int dia = 2;
                        int hi = 10;
                        Ocupaciones ocu = null;
                        pre.comprobarRestriccion(c,dia,hi,ocu);
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

                case 6: //setAsignatura
                    try {
                        out.print("6- setAsignatura(Asignatura)\n");

                        String nivel = in.next();
                        Asignatura asig = null;
                        int ni = 0;
                        boolean valid = true;
                        for (int i = 0; i < nivel.length() && valid; ++i) {
                            ni *= 10;
                            if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                ni += (int) (nivel.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (ni >= 1 && ni <= 4) {
                            if (ni == 1) {
                                asig = as1;
                            } else if (ni == 2) {
                                asig = as2;
                            } else if (ni == 3) {
                                asig = as3;
                            } else {
                                asig = as4;
                            }
                            pre.setAsignatura(asig);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else if (ni != 5) {
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

                case 7: //setPrerrequisito
                    try {
                        out.print("7- setPrerrequisito(Asignatura)\n");

                        String nivel = in.next();
                        Asignatura asig = null;
                        int ni = 0;
                        boolean valid = true;
                        for (int i = 0; i < nivel.length() && valid; ++i) {
                            ni *= 10;
                            if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                ni += (int) (nivel.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (ni >= 1 && ni <= 4) {
                            if (ni == 1) {
                                asig = as1;
                            } else if (ni == 2) {
                                asig = as2;
                            } else if (ni == 3) {
                                asig = as3;
                            } else {
                                asig = as4;
                            }
                            pre.setPrerrequisito(asig);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else if (ni != 5) {
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
        out.print("1- Prerrequisito(Asignatura, Asignatura)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getAsignatura()\n");
        out.print("3- getPrerrequisito()\n");
        out.print("4- (Abstracta)getTipoRestriccion()\n");
        out.print("5- (Abstracta)comprobarRestriccion()\n");
        out.print("MODIFICADORAS:\n");
        out.print("6- setAsignatura(Asignatura)\n");
        out.print("7- setPrerrequisito(Asignatura)\n");
        out.print("8- Salir\n");
        out.print("Tu opción: ");
    }

}
