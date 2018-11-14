package tests;

import domain.*;

import java.util.*;

import static java.lang.System.out;

public class driverClase {

    private static Scanner in = new Scanner(System.in);

    private static SubGrupo sg1 = null;
    private static SubGrupo sg2 = null;
    private static PlanEstudios peAS = null;
    private static Sesion s1 = null;
    private static Sesion s2 = null;
    private static Nivel n1 = new Nivel("n1");
    private static ArrayList<Restriccion> restr = new ArrayList<Restriccion>();
    private static Restriccion re1 = new NivelHora(n1);
    private static Restriccion re2 = new FranjaTrabajo(8, 20);
    private static Ocupaciones ocu = null;

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
        restr.add(re1); restr.add(re2);
        Asignatura as = new Asignatura("as", "AS", peAS);
        as.setRestricciones(restr);
        Asignatura as1 = new Asignatura("as1", "AS1", peAS);
        as1.setRestricciones(restr);
        Asignatura as2 = new Asignatura("as2", "AS2", peAS);
        Grupo g = new Grupo("Dummy", as);
        sg1 = new SubGrupo("1", 60, TipoClase.valueOf("Teoria"), g);
        sg2 = new SubGrupo("2", 30, TipoClase.valueOf("Problemas"), g);
        s1 = new Sesion(2, TipoClase.valueOf("Teoria"), as1);
        s2 = new Sesion(1, TipoClase.valueOf("Problemas"), as2);
        ocu = new Ocupaciones();
    }

    public static void main (String argv[]) {
        out.print("Driver clase Clase\n");
        Clase c = null;
        initValues();
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(17);
            switch (op) {
                case 1:
                    out.println("SubGrupos disponibles predeterminados:");
                    out.println("1- sg1");
                    out.println("2- sg2");
                    out.println("3- (Cancelar)");
                    out.print("Escoge uno para añadirlo: ");
                    String nivel = in.next();
                    int ni = 0;
                    boolean valid = true;
                    SubGrupo sg = null;
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
                            sg = sg1;
                        else
                            sg = sg2;
                        out.println("\nSesiones disponibles predeterminadas:");
                        out.println("1- s1");
                        out.println("2- s2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge una para añadirla: ");
                        String sesion = in.next();
                        ni = 0;
                        valid = true;
                        Sesion s = null;
                        for (int i = 0; i < sesion.length() && valid; ++i) {
                            ni *= 10;
                            if (sesion.charAt(i) >= '0' && sesion.charAt(i) <= '9') {
                                ni += (int)(sesion.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (ni == 1 || ni == 2) {
                            if (ni == 1)
                                s = s1;
                            else
                                s = s2;
                        } else if (ni != 3) {
                            out.printf("ERROR: el parámetro \"%s\" no es válido\n", sesion);
                        }
                        c = new Clase(sg,s);
                        out.println("HA FUNCIONADO CORRECTAMENTE");
                    } else if (ni != 3) {
                        out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                    }
                    out.println("\n");
                    break;

                case 2: //toStringResumido
                    try {
                        out.println(c.toStringResumido());
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

                case 3: //comprovarRestricciones
                    try {
                        out.println(c.comprovarRestricciones(0, 8, ocu).toString());
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

                case 4: //getSubGrupo
                    try {
                        out.println(c.getSubGrupo().toString());
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

                case 5: //getSesion
                    try {
                        out.println(c.getSesion().toString());
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

                case 6: //getRestricciones
                    try {
                        out.println(c.getRestricciones().toString());
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

                case 7: //getRestriccion
                    try {
                        out.println("Introduzca el número de la posición de la Restriccion en el Array");
                        out.println("de restricciones de la Clase (para obtener dicha Restriccion)");
                        out.println("\n(Array de restricciones:)");
                        try {
                            out.println(c.getRestricciones());
                        } catch (Exception e) {
                            out.println("ERROR EN getRestricciones()");
                            out.println("\n");
                            break;
                        }
                        String plazas = in.next();
                        int pl = 0;
                        valid = true;
                        for (int i = 0; i < plazas.length() && valid; ++i) {
                            pl *= 10;
                            if (plazas.charAt(i) >= '0' && plazas.charAt(i) <= '9') {
                                pl += (int)(plazas.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        out.println(c.getRestriccion(pl).toString());
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

                case 8: //getPlazas
                    try {
                        out.println(c.getPlazas());
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

                case 9: //getGrupo
                    try {
                        out.println(c.getGrupo().toString());
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

                case 10: //getTipoSesion
                    try {
                        out.println(c.getTipoSesion().toString());
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

                case 11: //getAsignatura
                    try {
                        out.println(c.getAsignatura().toString());
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

                case 12: //tieneNivel
                    try {
                        if (c.tieneNivel()) {
                            out.println("La Asignatura tiene un Nivel Asociado");
                        } else {
                            out.println("La Asignatura NO tiene un Nivel Asociado");
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

                case 13: //getNivel
                    try {
                        out.println(c.getNivel().toString());
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

                case 14: //getDuracion
                    try {
                        out.println(c.getDuracion().toString());
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

                case 15: //setSubGrupo
                    try {
                        out.println("SubGrupos disponibles predeterminados:");
                        out.println("1- sg1");
                        out.println("2- sg2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge uno para substituirlo: ");
                        nivel = in.next();
                        ni = 0;
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
                                c.setSubGrupo(sg1);
                            else
                                c.setSubGrupo(sg2);

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

                case 16: //setSesion
                    try {
                        out.println("Sesiones disponibles predeterminados:");
                        out.println("1- s1");
                        out.println("2- s2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge una para substituirla: ");
                        nivel = in.next();
                        ni = 0;
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
                                c.setSesion(s1);
                            else
                                c.setSesion(s2);

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

                case 17:
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
        out.print("1- Clase(SubGrupo, Sesion)\n");
        out.print("CONSULTORAS:\n");
        out.print("2- toStringResumido()\n");
        out.print("3- comprovarRestricciones(int, int, Ocupaciones)\n");
        out.print("4- getSubGrupo()\n");
        out.print("5- getSesion()\n");
        out.print("6- getRestricciones()\n");
        out.print("7- getRestriccion(int)\n");
        out.print("8- getPlazas()\n");
        out.print("9- getGrupo()\n");
        out.print("10- getTipoSesion()\n");
        out.print("11- getAsignatura()\n");
        out.print("12- tieneNivel()\n");
        out.print("13- getNivel()\n");
        out.print("14- getDuracion()\n");
        out.print("MODIFICADORAS:\n");
        out.print("15- setSubGrupo(SubGrupo)\n");
        out.print("16- setSesion(Sesion)\n");
        out.print("17- Salir\n");
        out.print("Tu opción: ");
    }
}
