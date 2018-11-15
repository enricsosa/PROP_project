package tests;

import domain.*;

import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverFranjaAsignatura {

    private static Scanner in = new Scanner(System.in);

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

    public static void main (String argv[]) {
        out.print("Driver clase FranjaAsignatura\n");
        FranjaAsignatura fa = null;
        initValues();
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(10);
            switch (op) {
                case 1:
                    try {
                        out.println("Asignaturas disponibles predeterminadas:");
                        out.println("1- as1");
                        out.println("2- as2");
                        out.println("3- as3");
                        out.println("4- as4");
                        out.println("5- (Cancelar)");
                        out.print("Escoge una para asignarla a FranjaAsignatura: ");
                        Asignatura asig = null;
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
                                asig = as1;
                            } else if (ni == 2) {
                                asig = as2;
                            } else if (ni == 3) {
                                asig = as3;
                            } else {
                                asig = as4;
                            }

                            out.println("Introduzca la horaInicial(Integer) de la FranjaAsignatura:");
                            out.println("(No importa que el número sea superior que 24)");
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
                                out.println("Introduzca la horaFin(Integer) de la FranjaAsignatura:");
                                out.println("(No importa que el número sea superior que 24)");
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
                                    fa = new FranjaAsignatura(asig, pl, plf);
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

                case 2: //getAsignatura
                    try {
                        out.println(fa.getAsignatura().toString());
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
                        out.println(fa.getHoraIni().toString());
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
                        out.println(fa.getHoraFin().toString());
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
                        out.println(fa.getTipoRestriccion().toString());
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
                        fa.comprobarRestriccion(c,dia,hi,ocu);
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
                        out.println("Asignaturas disponibles predeterminadas:");
                        out.println("1- as1");
                        out.println("2- as2");
                        out.println("3- as3");
                        out.println("4- as4");
                        out.println("5- (Cancelar)");
                        out.print("Escoge una para substituirla: ");
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
                            fa.setAsignatura(asig);
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
                        out.println("Introduzca la horaInicial(Integer) de la FranjaAsignatura:");
                        out.println("(No importa que el número sea superior que 24)");
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
                            fa.setHoraIni(plf);
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
                        out.println("Introduzca la horaFin(Integer) de la FranjaAsignatura:");
                        out.println("(No importa que el número sea superior que 24)");
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
                            fa.setHoraFin(plf);
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
            if (op != 0) {
                mostrarMenu();
            }
        }

    }

    private static void mostrarMenu() {
        out.print("Opciones:\n");
        out.print("CONSTRUCTORAS:\n");
        out.print("1- FranjaAsignatura(Asignatura, Integer, Integer)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getAsignatura()\n");
        out.print("3- getHoraIni()\n");
        out.print("4- getHoraFin()\n");
        out.print("5- getTipoRestriccion()\n");
        out.print("6- comprobarRestriccion()\n");
        out.print("MODIFICADORAS:\n");
        out.print("7- setAsignatura(Asignatura)\n");
        out.print("8- setHoraIni(Integer)\n");
        out.print("9- setHoraFin(Integer)\n");
        out.print("10- Salir\n");
        out.print("Tu opción: ");
    }
}
