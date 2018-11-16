package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverFranjaNivelAuto {

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
        as1 = new Asignatura("as1", "AS1", peAS);
        as2 = new Asignatura("as2", "AS2", peAS);
        as3 = new Asignatura("as3", "AS3", peAS);
        as4 = new Asignatura("as4", "AS4", peAS);
        N1 = new Nivel("N1");
        N2 = new Nivel("N2");
        N3 = new Nivel("N3");
        N4 = new Nivel("N4");
    }

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoFranjaNivel.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase FranjaNivel\n");
        FranjaNivel fn = null;
        initFiles();
        initValues();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(10);
            switch (op) {
                case 1:
                    try {
                        out.print("1- FranjaNivel(Nivel, Integer, Integer)\n");

                        Nivel niv = null;
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
                            if (ni == 1) {
                                niv = N1;
                            } else if (ni == 2) {
                                niv = N2;
                            } else if (ni == 3) {
                                niv = N3;
                            } else {
                                niv = N4;
                            }

                            String hI = in.next();
                            int pl = 0;
                            valid = true;
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
                                    fn = new FranjaNivel(niv, pl, plf);
                                    out.println("HA FUNCIONADO CORRECTAMENTE");
                                } else {
                                    out.printf("ERROR: el parámetro \"%s\" no es válido\n", hF);
                                }
                            } else {
                                out.printf("ERROR: el parámetro \"%s\" no es válido\n", hI);
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

                case 2: //getNivel
                    try {
                        out.print("2- getNivel()\n");

                        out.println(fn.getNivel().toString());
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

                case 3: //getHoraIni
                    try {
                        out.print("3- getHoraIni()\n");

                        out.println(fn.getHoraIni().toString());
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

                case 4: //getHoraFin
                    try {
                        out.print("4- getHoraFin()\n");

                        out.println(fn.getHoraFin().toString());
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

                case 5: //getTipoRestriccion
                    try {
                        out.print("5- getTipoRestriccion()\n");

                        out.println(fn.getTipoRestriccion().toString());
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

                case 6: //comprobarRestriccion
                    try {
                        out.print("6- comprobarRestriccion()\n");

                        Asignatura as = new Asignatura("as", "AS", peAS);
                        Grupo g = new Grupo("Dummy", as);
                        Nivel n1 = new Nivel("n1");
                        SubGrupo sg1 = new SubGrupo("1", 60, TipoClase.valueOf("Teoria"), g);

                        Asignatura as1 = new Asignatura("as1", "AS1", peAS, n1);
                        Asignatura as2 = new Asignatura("as2", "AS2", peAS);
                        Sesion s1 = new Sesion(2, TipoClase.valueOf("Teoria"), as1);

                        Clase c = new Clase(sg1, s1);
                        int dia = 2;
                        int hi = 10;
                        Ocupaciones ocu = null;
                        fn.comprobarRestriccion(c,dia,hi,ocu);
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

                case 7: //setAsignatura
                    try {
                        out.print("7- setNivel(Nivel)\n");

                        String nivel = in.next();
                        Nivel niv = null;
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
                                niv = N1;
                            } else if (ni == 2) {
                                niv = N2;
                            } else if (ni == 3) {
                                niv = N3;
                            } else {
                                niv = N4;
                            }
                            fn.setNivel(niv);
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

                case 8: //setHoraIni
                    try {
                        out.print("8- setHoraIni(Integer)\n");

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
                            fn.setHoraIni(plf);
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

                case 9: //setHoraFin
                    try {
                        out.print("9- setHoraFin(Integer)\n");

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
                            fn.setHoraFin(plf);
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

                case 10:
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
        out.print("1- FranjaNivel(Nivel, Integer, Integer)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getNivela()\n");
        out.print("3- getHoraIni()\n");
        out.print("4- getHoraFin()\n");
        out.print("5- getTipoRestriccion()\n");
        out.print("6- comprobarRestriccion()\n");
        out.print("MODIFICADORAS:\n");
        out.print("7- setNivel(Nivel)\n");
        out.print("8- setHoraIni(Integer)\n");
        out.print("9- setHoraFin(Integer)\n");
        out.print("10- Salir\n");
        out.print("Tu opción: ");
    }
}
