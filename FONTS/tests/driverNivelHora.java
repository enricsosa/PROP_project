package tests;

import domain.*;

import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverNivelHora {

    private static Scanner in = new Scanner(System.in);

    private static PlanEstudios peAS = null;
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
        N1 = new Nivel("N1");
        N2 = new Nivel("N2");
        N3 = new Nivel("N3");
        N4 = new Nivel("N4");
    }

    public static void main (String argv[]) {
        out.print("Driver clase NivelHora\n");
        NivelHora nh = null;
        initValues();
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(6);
            switch (op) {
                case 1:
                    try {
                        out.println("Niveles disponibles predeterminados:");
                        out.println("1- N1");
                        out.println("2- N2");
                        out.println("3- N3");
                        out.println("4- N4");
                        out.println("5- (Cancelar)");
                        out.print("Escoge uno para asignarlo a NivelHora: ");
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
                            nh = new NivelHora(niv);
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

                case 2: //getNivel
                    try {
                        out.println(nh.getNivel().toString());
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

                case 3: //getTipoRestriccion
                    try {
                        out.println(nh.getTipoRestriccion().toString());
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
                        out.println("Para comprobar realmente la efectividad de esta función");
                        out.println("muchos datos deberían haber sido cargados -lo cual no es");
                        out.println("el caso-. Por lo tanto, esta opción sólo comprueba que la ");
                        out.println("función no provoca una excepción al llamarla");

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
                        nh.comprobarRestriccion(c,dia,hi,ocu);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 5: //setNivel
                    try {
                        out.println("Niveles disponibles predeterminados:");
                        out.println("1- N1");
                        out.println("2- N2");
                        out.println("3- N3");
                        out.println("4- N4");
                        out.println("5- (Cancelar)");
                        out.print("Escoge uno para substituirlo: ");
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
                            nh.setNivel(niv);
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

                case 6:
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
        out.print("1- NivelHora(Nivel)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getNivel()\n");
        out.print("3- getTipoRestriccion()\n");
        out.print("4- comprobarRestriccion()\n");
        out.print("MODIFICADORAS:\n");
        out.print("5- setNivel(Nivel)\n");
        out.print("6- Salir\n");
        out.print("Tu opción: ");
    }
}
