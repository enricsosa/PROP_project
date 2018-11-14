package tests;

import domain.Aula;
import domain.TipoClase;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import static java.lang.System.out;


/*
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
 */
/**@author Enric Sosa*/
public class driverAula {

    private static Scanner in = new Scanner(System.in);

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

    public static void main (String argv[]) {
        out.print("Driver clase Aula\n");
        Aula a = null;
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(11);
            switch (op) {
                case 1: //Aula(String, Integer, ArrayList<TipoClase>)
                    try {
                        out.println("Introduzca la id del Aula:");
                        String id = in.next();

                        out.println("Introduzca las plazas(Integer) del Aula:");
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
                            out.println("Introduzca los tipos que habilita el Aula:");
                            out.println("Tipos: (t)Teoria, (l)Laboratorio, (p)Problemas (t,l,p)");
                            out.println("Introduzca cualquier otro carácter para no añadir más tipos");
                            String t = in.next();
                            ArrayList<TipoClase> atc = new ArrayList<TipoClase>();
                            while (t.length() == 1 &&
                                    (t.charAt(0) == 't' || t.charAt(0) == 'l'
                                            || t.charAt(0) == 'p')) {
                                String tipo = "";
                                if (t.charAt(0) == 't') {
                                    tipo = "Teoria";
                                } else if (t.charAt(0) == 'l') {
                                    tipo = "Laboratorio";
                                } else {
                                    tipo = "Problemas";
                                }
                                if (atc.contains(TipoClase.valueOf(tipo))) {
                                    out.printf("ERROR: el tipo \"%s(%c)\" ya está en el Array\n", tipo, t.charAt(0));
                                } else {
                                    atc.add(TipoClase.valueOf(tipo));
                                }
                                t = in.next();
                            }
                            a = new Aula(id,pl,atc);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        } else {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", plazas);
                        }

                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL AULA ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 2: //getId
                    try {
                        out.println(a.getId());
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

                case 3: //getPlaszas
                    try {
                        out.println(a.getPlazas());
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

                case 4: //getTipos
                    try {
                        out.println(a.getTipos());
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

                case 5: //tieneTeoria
                    try {
                        boolean t = a.tieneTeoria();
                        if (t)
                            out.println("Tiene Teoria");
                        else
                            out.println("NO tiene Teoria");
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

                case 6: //tieneLaboratorio
                    try {
                        boolean l = a.tieneLaboratorio();
                        if (l)
                            out.println("Tiene Laboratorio");
                        else
                            out.println("NO tiene Laboratorio");
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

                case 7: //tieneProblemas
                    try {
                        boolean p = a.tieneProblemas();
                        if (p)
                            out.println("Tiene Problemas");
                        else
                            out.println("NO tiene Problemas");
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

                case 8: //setId(String)
                    try {
                        out.println("Introduzca la id del Aula:");
                        String id = in.next();
                        a.setId(id);
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

                case 9: //setPlazas(String)
                    try {
                        out.println("Introduzca las plazas(Integer) del Aula:");
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
                        a.setPlazas(pl);
                        if (valid)
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        else
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", plazas);
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL AULA ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 10: //setTipos(String)
                    try {
                        out.println("Introduzca los tipos que habilita el Aula:");
                        out.println("Tipos: (t)Teoria, (l)Laboratorio, (p)Problemas (t,l,p)");
                        out.println("Introduzca cualquier otro carácter para no añadir más tipos");
                        String plazas = in.next();
                        ArrayList<TipoClase> atc = new ArrayList<TipoClase>();
                        while (plazas.length() == 1 &&
                                (plazas.charAt(0) == 't' || plazas.charAt(0) == 'l'
                                        || plazas.charAt(0) == 'p')) {
                            String tipo = "";
                            if (plazas.charAt(0) == 't') {
                                tipo = "Teoria";
                            } else if (plazas.charAt(0) == 'l') {
                                tipo = "Laboratorio";
                            } else {
                                tipo = "Problemas";
                            }
                            if (atc.contains(TipoClase.valueOf(tipo))) {
                                out.printf("ERROR: el tipo \"%s(%c)\" ya está en el Array\n", tipo, plazas.charAt(0));
                            } else {
                                atc.add(TipoClase.valueOf(tipo));
                            }
                            plazas = in.next();
                        }
                        a.setTipos(atc);
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

                case 11:
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
        out.print("1- Aula(String, Integer, ArrayList<TipoClase>)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getId()\n");
        out.print("3- getPlazas()\n");
        out.print("4- getTipos()\n");
        out.print("5- tieneTeoria()\n");
        out.print("6- tieneLaboratorio()\n");
        out.print("7- tieneProblemas()\n");
        out.print("MODIFICADORAS:\n");
        out.print("8- setId(String)\n");
        out.print("9- setPlazas(Integer)\n");
        out.print("10- setTipos(ArrayList<TipoClase>)\n");
        out.print("11- Salir\n");
        out.print("Tu opción: ");
    }
}