package tests;

import domain.*;

import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverDiaLibre {

    private static Scanner in = new Scanner(System.in);

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

    public static void main (String argv[]) {
        out.print("Driver clase Correquisito\n");
        DiaLibre dl = null;
        initValues();
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(6);
            switch (op) {
                case 1: //setPlazas(String)
                    try {
                        out.println("Introduzca el dia de la semana(Inetger) del Dia:");
                        out.println("([1 -> Lunes, ..., 7 -> Domingo]; No se acpetan otros números)");
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
                        out.println("Para comprobar realmente la efectividad de esta función");
                        out.println("muchos datos deberían haber sido cargados -lo cual no es");
                        out.println("el caso-. Por lo tanto, esta opción sólo comprueba que la ");
                        out.println("función no provoca una excepción al llamarla");

                        Clase c = null;
                        int dia = 2;
                        int hi = 10;
                        Horario ocu = null;
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
                        out.println("Introduzca el dia de la semana(Inetger) del Dia:");
                        out.println("([1 -> Lunes, ..., 7 -> Domingo]; No se acpetan otros números)");
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
            if (op != 0) {
                mostrarMenu();
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
