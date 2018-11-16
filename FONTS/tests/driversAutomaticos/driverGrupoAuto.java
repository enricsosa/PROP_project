package tests.driversAutomaticos;

import domain.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import static java.lang.System.out;


/**@author Enric Sosa*/
public class driverGrupoAuto {

    private static Scanner in = null;

    private static File file = null;

    private static PlanEstudios peAS = null;
    private static Nivel n1 = new Nivel("n1");
    private static Asignatura as1 = new Asignatura("as1", "AS1", peAS, n1);
    private static Asignatura as2 = new Asignatura("as2", "AS2", peAS);

    private static SubGrupo sg1 = null;
    private static SubGrupo sg2 = null;
    private static Map<String, SubGrupo> subgs = new HashMap<String, SubGrupo>();


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
        Asignatura as = new Asignatura("as", "AS", peAS);
        Grupo g = new Grupo("Dummy", as);
        sg1 = new SubGrupo("1", 60, TipoClase.valueOf("Teoria"), g);
        sg2 = new SubGrupo("2", 30, TipoClase.valueOf("Problemas"), g);
        subgs.put(sg1.getId(), sg1);
        subgs.put(sg2.getId(), sg2);
    }

    private static void initFiles() throws FileNotFoundException {
        file = new File("FONTS/tests/juegos_de_prueba/juegoGrupo.txt");
        in = new Scanner(file);
    }

    public static void main (String argv[]) throws FileNotFoundException {
        out.print("Driver clase Grupo\n");
        Grupo g = null;
        initFiles();
        initValues();
        //mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(16);
            switch (op) {
                case 1:
                    try {
                        out.print("1- Grupo(String, Asignatura)\n");

                        String id = in.next();

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
                                g = new Grupo(id, as1);
                            else
                                g = new Grupo(id, as2);
                            out.println("HA FUNCIONADO CORRECTAMENTE");
                            out.println("(AVISO: Grupo vacío, sólo tiene un Id y una Asignatura)");
                        } else if (ni != 3) {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                        }
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL GRUPO ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 2:
                    try {
                        out.print("2- getId()\n");

                        out.println(g.getId());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL GRUPO ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 3:
                    try {
                        out.print("3- getAsignatura()\n");

                        out.println(g.getAsignatura().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL GRUPO ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 4:
                    try {
                        out.print("4- getSubGrupos()\n");

                        out.println(g.getSubGrupos().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL GRUPO ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 5:
                    try {
                        out.print("5- getSubGruposTeoria()\n");

                        out.println(g.getSubGruposTeoria().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL GRUPO ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 6:
                    try {
                        out.print("6- getSubGruposLaboratorio()\n");

                        out.println(g.getSubGruposLaboratorio().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL GRUPO ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 7:
                    try {
                        out.print("7- getSubGruposProblemas()\n");

                        out.println(g.getSubGruposProblemas().toString());
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                        out.println("\n");
                    } catch (NullPointerException e) {
                        out.println("*** NullPointerException: NO SE HA INICIALIZADO EL GRUPO ***");
                        out.println("\n");
                    } catch (Exception e) {
                        out.println("NO HA FUNCIONADO");
                        out.println("\n");
                    }
                    break;

                case 8: //getSubGrupo
                    try {
                        out.print("8- getSubGrupo(String)\n");

                        String plazas = in.next();
                        out.println(g.getSubGrupo(plazas).toString());
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
                        if (g.tieneNivel()) {
                            out.println("La Asignatura del Grupo tiene un Nivel Asociado");
                        } else {
                            out.println("La Asignatura del Grupo NO tiene un Nivel Asociado");
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

                        out.println(g.getNivel().toString());
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
                        g.setId(id);
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

                case 12: //setAsignatura
                    try {
                        out.print("12- setAsignatura(Asignatura)\n");

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
                                g.setAsignatura(as1);
                            else
                                g.setAsignatura(as2);
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
                case 13:
                    try {
                        out.print("13- setSubGrupos(Map<String, SubGrupo>)\n");

                        out.println(subgs.toString());
                        g.setSubGrupos(subgs);
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

                case 14: //addSubGrupo
                    try {
                        out.print("14- addSubGrupo(SubGrupo)\n");

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
                                g.addSubGrupo(sg1);
                            else
                                g.addSubGrupo(sg2);

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

                case 15: //eliminarSubGrupo
                    try {
                        out.print("15- eliminarSubGrupo(String)\n");

                        String plazas = in.next();
                        g.eliminarSubGrupo(plazas);
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

                case 16:
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
        out.print("1- Grupo(String, Asignatura)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- getId()\n");
        out.print("3- getAsignatura()\n");
        out.print("4- getSubGrupos()\n");
        out.print("5- getSubGruposTeoria()\n");
        out.print("6- getSubGruposLaboratorio()\n");
        out.print("7- getSubGruposProblemas()\n");
        out.print("8- getSubGrupo(String)\n");
        out.print("9- tieneNivel()\n");
        out.print("10- getNivel()\n");
        out.print("MODIFICADORAS:\n");
        out.print("11- setId(String)\n");
        out.print("12- setAsignatura(Asignatura)\n");
        out.print("13- setSubGrupos(Map<String, SubGrupo>)\n");
        out.print("14- addSubGrupo(SubGrupo)\n");
        out.print("15- eliminarSubGrupo(String)\n");
        out.print("16- Salir\n");
        out.print("Tu opción: ");
    }
}
