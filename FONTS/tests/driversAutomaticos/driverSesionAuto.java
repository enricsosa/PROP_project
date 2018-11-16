package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverSesionAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;
    private static Asignatura as1 = new Asignatura("as1", "AS1", peAS);
    private static Asignatura as2 = new Asignatura("as2", "AS2", peAS);

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
        file = new File("FONTS/tests/juegos_de_prueba/juegoSesion.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase Asignatura\n");
        Sesion s = null;
        initValues();
        initFiles();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(8);
            switch (op) {
                case 1:
                    try {
                        out.print("1- Sesion(Integer, TipoClase, Asignatura)\n");

                        String plazas = in.next();
                        int pl = 0;
                        boolean valid = true;
                        for (int i = 0; i < plazas.length() && valid; ++i) {
                            pl *= 10;
                            if (plazas.charAt(i) >= '0' && plazas.charAt(i) <= '9') {
                                pl += (int) (plazas.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }

                        if (valid) {
                            String t = in.next();
                            String tipo;
                            if (t.length() == 1 && (t.charAt(0) == 't' || t.charAt(0) == 'l' || t.charAt(0) == 'p')) {
                                if (t.charAt(0) == 't') {
                                    tipo = "Teoria";
                                } else if (t.charAt(0) == 'l') {
                                    tipo = "Laboratorio";
                                } else {
                                    tipo = "Problemas";
                                }
                                String nivel = in.next();
                                int ni = 0;
                                valid = true;
                                for (int i = 0; i < nivel.length() && valid; ++i) {
                                    ni *= 10;
                                    if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                        ni += (int)(nivel.charAt(i) - '0');
                                    } else {
                                        valid = false;
                                    }
                                }
                                if (ni == 1 || ni == 2) {
                                    if (ni == 1)
                                        s = new Sesion(pl, TipoClase.valueOf(tipo), as1);
                                    else
                                        s = new Sesion(pl, TipoClase.valueOf(tipo), as2);

                                    out.println("HA FUNCIONADO CORRECTAMENTE");
                                } else if (ni != 3) {
                                    out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                                }
                            } else {
                                out.printf("ERROR: el parámetro \"%s\" no es válido\n", t);
                            }
                        } else {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", plazas);
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

                case 2: //getDuracion
                    try {
                        out.print("2- getDuracion()\n");

                        out.println(s.getDuracion().toString());
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

                case 3: //getTipo
                    try {
                        out.print("3- getTipo()\n");

                        out.println(s.getTipo().toString());
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

                case 4: //getAsignatura
                    try {
                        out.print("4- getAsignatura()\n");

                        out.println(s.getAsignatura().toString());
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

                case 5: //setDuracion
                    try {
                        out.print("5- setDuracion(Integer)\n");

                        String plazas = in.next();
                        int pl = 0;
                        boolean valid = true;
                        for (int i = 0; i < plazas.length() && valid; ++i) {
                            pl *= 10;
                            if (plazas.charAt(i) >= '0' && plazas.charAt(i) <= '9') {
                                pl += (int)(plazas.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (valid) {
                            s.setDuracion(pl);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", plazas);
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

                case 6: //setTipo
                    try {
                        out.print("6- setTipo(TipoClase)\n");

                        String t = in.next();
                        String tipo;
                        if (t.length() == 1 && (t.charAt(0) == 't' || t.charAt(0) == 'l' || t.charAt(0) == 'p')) {
                            if (t.charAt(0) == 't') {
                                tipo = "Teoria";
                            } else if (t.charAt(0) == 'l') {
                                tipo = "Laboratorio";
                            } else {
                                tipo = "Problemas";
                            }
                            s.setTipo(TipoClase.valueOf(tipo));
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", t);
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

                case 7: //setAsignatura
                    try {
                        out.print("7- setAsignatura(Asignatura)\n");

                        String nivel = in.next();
                        int ni = 0;
                        boolean valid = true;
                        for (int i = 0; i < nivel.length() && valid; ++i) {
                            ni *= 10;
                            if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                ni += (int)(nivel.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (ni == 1 || ni == 2) {
                            if (ni == 1)
                                s.setAsignatura(as1);
                            else
                                s.setAsignatura(as2);

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
        out.print("1- Sesion(Integer, TipoClase, Asignatura)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getDuracion()\n");
        out.print("3- getTipo()\n");
        out.print("4- getAsignatura()\n");
        out.print("MODIFICADORAS:\n");
        out.print("5- setDuracion(Integer)\n");
        out.print("6- setTipo(TipoClase)\n");
        out.print("7- setAsignatura(Asignatura)\n");
        out.print("8- Salir\n");
        out.print("Tu opción: ");
    }
}
