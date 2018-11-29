package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/

public class driverHorarioAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;
    private static Horario ocu = new Horario("");
    private static Horario ocu2 = new Horario("");

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

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoHorario.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase Horario\n");
        Horario h = null;
        initFiles();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(10);
            switch (op) {
                case 1:
                    try {
                        out.print("1- Horario(String)\n");

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
                        out.print("2- Horario(String, Horario)\n");

                        String nom = in.next();
                        out.println("Introduciendo Horario vacías por defecto...");
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

                case 3:
                    try {
                        out.print("3- Horario(Horario)\n");

                        Horario b = null;
                        out.println(h.toString());
                        b = new Horario(h);
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
                        out.print("4- getId()\n");

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
                    /**try {
                        out.print("5- getHorario()\n");

                        out.println(h.getHorario().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }*/
                    break;

                case 6: //getDia(String)
                    try {
                        out.print("6- getDia(int)\n");

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
                        out.print("7- getHora(int, int)\n");

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
                        out.print("8- setId(String)\n");

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
                    /**try {
                        out.print("9- setHorario(Horario)\n");

                        out.println("Unas nuevas Horario vacías serán asignadas al Horario");
                        h.setHorario(ocu2);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }*/
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
        out.print("1- Horario(String)\n");
        out.print("2- Horario(String, Horario)\n");
        out.print("3- Horario(Horario)\n");
        out.print("CONSULTORAS:\n");
        out.print("4- getId()\n");
        out.print("5- getHorario()\n");
        out.print("6- getDia(int)\n");
        out.print("7- getHora(int, int)\n");
        out.print("MODIFICADORAS:\n");
        out.print("8- setId(String)\n");
        out.print("9- setHorario(Horario)\n");
        out.print("10- Salir\n");
        out.print("Tu opción: ");
    }
}
