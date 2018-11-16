package tests;

import domain.*;

import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/

public class driverHorario {

    private static Scanner in = new Scanner(System.in);

    private static PlanEstudios peAS = null;
    private static Ocupaciones ocu = new Ocupaciones();
    private static Ocupaciones ocu2 = new Ocupaciones();

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
        out.print("Driver clase Horario\n");
        Horario h = null;
        initValues();
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(10);
            switch (op) {
                case 1:
                    try {
                        out.println("Introduzca el nombre del Horario:");
                        String nom = in.next();
                        h = new Horario(nom);
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

                case 2:
                    try {
                        out.println("Introduzca el nombre del Horario:");
                        String nom = in.next();
                        out.println("Introduciendo Ocupaciones vacías por defecto...");
                        h = new Horario(nom, ocu);
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

                case 3:
                    try {
                        out.println("Horario b tiene valor null");
                        Horario b = null;
                        out.println("Horario h es la que tenemos ahora mismo");
                        out.println("(h debería ser diferente de null para ver la efectividad de este método)");
                        out.println(h.toString());
                        out.println("Hacemos la copia");
                        b = new Horario(h);
                        out.println("Ahora b es:");
                        out.println(b.toString());
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

                case 4:
                    try {
                        out.println(h.getId());
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

                case 5:
                    try {
                        out.println(h.getOcupaciones().toString());
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

                case 6: //getDia(String)
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
                            out.println(h.getDia(dia).toString());
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

                case 7: //getHora(int, int)
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
                            out.println("Introduzca la Hora del Dia(Inetger):");
                            out.println("No se aceptan números mayores a 24");
                            nivel = in.next();
                            int hora = 0;
                            valid = true;
                            for (int i = 0; i < nivel.length() && valid; ++i) {
                                hora *= 10;
                                if (nivel.charAt(i) >= '1' && nivel.charAt(i) <= '7') {
                                    hora += (int) (nivel.charAt(i) - '0');
                                } else {
                                    valid = false;
                                }
                            }
                            if (hora > 24)
                                valid = false;
                            if (valid) {
                                out.println(h.getHora(dia,hora).toString());
                                out.println("HA FUNCIONADO CORRECTAMENTE");
                            } else {
                                out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                            }
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

                case 8:
                    try {
                        out.println("Introduzca el nombre nuevo del Horario:");
                        String nom = in.next();
                        h.setId(nom);
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

                case 9:
                    try {
                        out.println("Unas nuevas Ocupaciones vacías serán asignadas al Horario");
                        h.setOcupaciones(ocu2);
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
        out.print("1- Horario(String)\n");
        out.print("2- Horario(String, Ocupaciones)\n");
        out.print("3- Horario(Horario)\n");
        out.print("CONSULTORAS:\n");
        out.print("4- getId()\n");
        out.print("5- getOcupaciones()\n");
        out.print("6- getDia(int)\n");
        out.print("7- getHora(int, int)\n");
        out.print("MODIFICADORAS:\n");
        out.print("8- setId(String)\n");
        out.print("9- setOcupaciones(Ocupaciones)\n");
        out.print("10- Salir\n");
        out.print("Tu opción: ");
    }
}
