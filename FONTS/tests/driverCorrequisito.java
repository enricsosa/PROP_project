package tests;

import domain.*;

import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverCorrequisito {

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
        out.print("Driver clase Correquisito\n");
        Correquisito cor = null;
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
                        out.print("Escoge una para asignarla como Asignatura1: ");
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
                            out.println("Asignaturas disponibles predeterminadas:");
                            out.println("1- as1");
                            out.println("2- as2");
                            out.println("3- as3");
                            out.println("4- as4");
                            out.println("5- (Cancelar)");
                            out.print("Escoge una para asignarla como Asignatura2: ");
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
                                cor = new Correquisito(asig1, asig2);
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

                case 2: //getAsignatura1
                    try {
                        out.println(cor.getAsignatura1().toString());
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

                case 3: //getAsignatura2
                    try {
                        out.println(cor.getAsignatura2().toString());
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

                case 4: //tieneAsignatura
                    try {
                        out.println("Asignaturas disponibles predeterminadas:");
                        out.println("1- as1");
                        out.println("2- as2");
                        out.println("3- as3");
                        out.println("4- as4");
                        out.println("5- (Cancelar)");
                        out.print("Escoge una para comprobar que está en el Correquisito: ");
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
                            if (cor.tieneAsignatura(asig)) {
                                out.println("El correquisito tiene la Asignatura");
                            } else {
                                out.println("El correquisito NO tiene la Asignatura");
                            }
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

                case 5: //getIndex
                    try {
                        out.println("PRECONDICIÓN: la Asignatura está en el Correquisito");
                        out.print("1- Asignatura1: ");
                        try {
                            out.println(cor.getAsignatura1().getId());
                        } catch (NullPointerException e) {
                            out.println("*** NullPointerException ***");
                            out.println("\n");
                        } catch (Exception e) {
                            out.println("ERROR EN FUNCIONES DE ASIGNATURA");
                            out.println("\n");
                        }

                        out.print("2- Asignatura2: ");
                        try {
                            out.println(cor.getAsignatura2().getId());
                        } catch (NullPointerException e) {
                            out.println("*** NullPointerException ***");
                            out.println("\n");
                        } catch (Exception e) {
                            out.println("ERROR EN FUNCIONES DE ASIGNATURA");
                            out.println("\n");
                        }
                        out.println("3- (Cancelar)");
                        Asignatura asig1 = cor.getAsignatura1();
                        Asignatura asig2 = cor.getAsignatura2();

                        out.print("Escoge una para comprobar que es la Asignatura1 o Asignatura2: ");
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
                        if (ni == 1 || ni == 2) {
                            if (ni == 1) {
                                out.println("El Index debe ser 1:");
                                out.println(cor.getIndex(asig1));

                            } else {
                                out.println("El Index debe ser 2:");
                                out.println(cor.getIndex(asig2));
                            }
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

                case 6: //getTipoRestriccion
                    try {
                        out.println(cor.getTipoRestriccion().toString());
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

                case 7: //comprobarRestriccion
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
                        Horario ocu = null;
                        cor.comprobarRestriccion(c,dia,hi,ocu);
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

                case 8: //comprobarRestriccion
                    try {
                        out.println("Asignaturas disponibles predeterminadas:");
                        out.println("1- as1");
                        out.println("2- as2");
                        out.println("3- as3");
                        out.println("4- as4");
                        out.println("5- (Cancelar)");
                        out.print("Escoge una para asignarla al Correquisito como Asignatura1: ");
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
                            cor.setAsignatura1(asig);
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

                case 9: //comprobarRestriccion
                    try {
                        out.println("Asignaturas disponibles predeterminadas:");
                        out.println("1- as1");
                        out.println("2- as2");
                        out.println("3- as3");
                        out.println("4- as4");
                        out.println("5- (Cancelar)");
                        out.print("Escoge una para asignarla al Correquisito como Asignatura2: ");
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
                            cor.setAsignatura2(asig);
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
        out.print("1- Correquisito(Asignatura, Asignatura)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getAsignatura1()\n");
        out.print("3- getAsignatura2()\n");
        out.print("4- tieneAsignatura(Asignatura)\n");
        out.print("5- getIndex(Asignatura)\n");
        out.print("6- (Abstracta)getTipoRestriccion()\n");
        out.print("7- (Abstracta)comprobarRestriccion()\n");
        out.print("MODIFICADORAS:\n");
        out.print("8- setAsignatura1(Asignatura)\n");
        out.print("9- setAsignatura2(Asignatura)\n");
        out.print("10- Salir\n");
        out.print("Tu opción: ");
    }

}
