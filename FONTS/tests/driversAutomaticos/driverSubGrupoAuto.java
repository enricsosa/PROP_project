package tests.driversAutomaticos;

import domain.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverSubGrupoAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;
    private static Grupo g1 = null;
    private static Grupo g2 = null;
    private static Nivel n1 = new Nivel("n1");

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
        Asignatura as1 = new Asignatura("as1", "AS1", peAS, n1);
        Asignatura as2 = new Asignatura("as2", "AS2", peAS);
        g1 = new Grupo("Grupo1", as1);
        g2 = new Grupo("Grupo2", as2);
    }

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoSubGrupo.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase SubGrupo\n");
        SubGrupo sg = null;
        initValues();
        initFiles();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(15);
            switch (op) {
                case 1:
                    try {
                        out.print("1- SubGrupo(String, Integer, TipoClase, Grupo)\n");

                        String id = in.next();

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
                                        ni += (int) (nivel.charAt(i) - '0');
                                    } else {
                                        valid = false;
                                    }
                                }
                                if (ni == 1 || ni == 2) {
                                    if (ni == 1)
                                        sg = new SubGrupo(id, pl, TipoClase.valueOf(tipo), g1);
                                    else
                                        sg = new SubGrupo(id, pl, TipoClase.valueOf(tipo), g2);

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

                case 2: //getId
                    try {
                        out.print("2- getId()\n");

                        out.println(sg.getId());
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

                case 3: //getKey
                    try {
                        out.print("3- getKey()\n");

                        out.println(sg.getKey());
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

                case 4: //getIdCompleta
                    try {
                        out.print("4- getIdCompleta()\n");

                        out.println(sg.getIdCompleta());
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

                case 5: //getPlazas
                    try {
                        out.print("5- getPlazas()\n");

                        out.println(sg.getPlazas().toString());
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

                case 6: //getTipo
                    try {
                        out.print("6- getTipo()\n");

                        out.println(sg.getTipo().toString());
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

                case 7: //getGrupo
                    try {
                        out.print("7- getGrupo()\n");

                        out.println(sg.getGrupo().toString());
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

                case 8: //getAsignatura
                    try {
                        out.print("8- getAsignatura()\n");

                        out.println(sg.getAsignatura().toString());
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

                case 9: //tieneNivel
                    try {
                        out.print("9- tieneNivel()\n");

                        out.println("(La Asignatura as1 tiene un Nivel Asociado, la Asignatura as2 no)");
                        if (sg.tieneNivel()) {
                            out.println("Su Asignatura asociada a su Grupo tiene un Nivel asociado");
                        } else {
                            out.println("Su Asignatura asociada a su Grupo NO tiene un Nivel asociado");
                        }
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

                case 10: //getNivel
                    try {
                        out.print("10- getNivel()\n");

                        out.println(sg.getNivel().toString());
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

                case 11: //setId
                    try {
                        out.print("11- setId(String)\n");

                        String id = in.next();
                        sg.setId(id);
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

                case 12: //setPlazas
                    try {
                        out.print("12- setPlazas(Integer)\n");

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
                        sg.setPlazas(pl);
                        if (valid)
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                        else
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", plazas);
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 13: //setTipo
                    try {
                        out.print("13- setTipo(TipoClase)\n");

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
                            sg.setTipo(TipoClase.valueOf(tipo));
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

                case 14: //setAsignatura
                    try {
                        out.print("14- setGrupo(Grupo)\n");

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
                            if (ni == 1)
                                sg.setGrupo(g1);
                            else
                                sg.setGrupo(g2);

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

                case 15:
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
        out.print("1- SubGrupo(String, Integer, TipoClase, Grupo)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getId()\n");
        out.print("3- getKey()\n");
        out.print("4- getIdCompleta()\n");
        out.print("5- getPlazas()\n");
        out.print("6- getTipo()\n");
        out.print("7- getGrupo()\n");
        out.print("8- getAsignatura()\n");
        out.print("9- tieneNivel()\n");
        out.print("10- getNivel()\n");
        out.print("MODIFICADORAS:\n");
        out.print("11- setId(String)\n");
        out.print("12- setPlazas(Integer)\n");
        out.print("13- setTipo(TipoClase)\n");
        out.print("14- setGrupo(Grupo)\n");
        out.print("15- Salir\n");
        out.print("Tu opción: ");
    }

}
