package tests;

import domain.*;

import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverAsignatura {

    private static Scanner in = new Scanner(System.in);

    private static PlanEstudios peAS = null;
    private static PlanEstudios peAS1 = null;
    private static PlanEstudios peAS2 = null;
    private static Nivel n1 = new Nivel("n1");
    private static Nivel n2 = new Nivel("n2");

    private static Grupo g1 = null;
    private static Grupo g2 = null;
    private static Grupo g3 = null;
    private static Grupo g4 = null;
    private static Map<String, Grupo> grupos = new HashMap<String, Grupo>();
    private static Sesion s1 = null;
    private static Sesion s2 = null;
    private static ArrayList<Sesion> sesions = new ArrayList<Sesion>();

    private static ArrayList<Restriccion> restr = new ArrayList<Restriccion>();
    private static Restriccion re1 = new NivelHora(n1);
    private static Restriccion re2 = new FranjaTrabajo(8, 20);

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
        Asignatura as1 = new Asignatura("as1", "AS1", peAS);
        Asignatura as2 = new Asignatura("as2", "AS2", peAS);
        Asignatura as3 = new Asignatura("as3", "AS3", peAS);
        Asignatura as4 = new Asignatura("as4", "AS4", peAS);
        g1 = new Grupo("Grupo1", as1);
        g2 = new Grupo("Grupo2", as2);
        g3 = new Grupo("Grupo1", as3);
        g4 = new Grupo("Grupo2", as4);
        grupos.put(g1.getId(), g1);
        grupos.put(g2.getId(), g2);
        s1 = new Sesion(2, TipoClase.valueOf("Teoria"), as1);
        s2 = new Sesion(1, TipoClase.valueOf("Problemas"), as2);
        sesions.add(s1);
        sesions.add(s2);
        peAS1 = new PlanEstudios("PlEs1");
        peAS2 = new PlanEstudios("PlEs2");
        restr.add(re1); restr.add(re2);
    }

    public static void main (String argv[]) {
        out.print("Driver clase Asignatura\n");
        Asignatura a = null;
        initValues();
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(29);
            switch (op) {
                case 1:
                    try {
                        out.println("Introduzca la id de la Asignatura:");
                        String id = in.next();

                        out.println("Introduzca el nombre de la Asignatura:");
                        String nom = in.next();

                        out.println("(Introducido un Plan de Estudios vacío por defecto)");
                        a = new Asignatura(id,nom,peAS);
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
                        out.println("Introduzca la id de la Asignatura:");
                        String id = in.next();

                        out.println("Introduzca el nombre de la Asignatura:");
                        String nom = in.next();

                        out.println("(Introducido un Plan de Estudios vacío por defecto)");

                        out.println("Niveles disponibles predeterminados:");
                        out.println("1- n1");
                        out.println("2- n2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge uno para añadirlo: ");
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
                                a = new Asignatura(id,nom,peAS, n1);
                            else
                                a = new Asignatura(id,nom,peAS, n2);
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

                case 4:
                    try {
                        out.println("Introduzca la id de la Asignatura:");
                        String id = in.next();

                        out.println("Introduzca el nombre de la Asignatura:");
                        String nom = in.next();

                        out.println("(Introducido un Plan de Estudios vacío por defecto)");

                        out.println("Niveles disponibles predeterminados:");
                        out.println("1- n1");
                        out.println("2- n2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge uno para añadirlo: ");
                        String nivel = in.next();
                        int ni = 0;
                        boolean valid = true;
                        Nivel n = null;
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
                                n = n1;
                            else
                                n = n2;
                            out.println("(Introducido un Array de Sesiones por defecto)");

                            out.println("(Introducido un Map de Grupos por defecto)");

                            a = new Asignatura(id, nom, peAS, n, sesions, grupos);
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

                case 3:
                    try {
                        out.println("Introduzca la id de la Asignatura:");
                        String id = in.next();

                        out.println("Introduzca el nombre de la Asignatura:");
                        String nom = in.next();

                        out.println("(Introducido un Plan de Estudios vacío por defecto)");

                        out.println("(Introducido un Array de Sesiones por defecto)");

                        out.println("(Introducido un Map de Grupos por defecto)");

                        a = new Asignatura(id, nom, peAS, sesions, grupos);
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
                        out.println(a.getId());
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

                case 6:
                    try {
                        out.println(a.getNombre());
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

                case 7:
                    try {
                        out.println(a.getPlanEstudios().toString());
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

                case 8:
                    try {
                        if (a.tieneNivel()) {
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

                case 9: //getNivel
                    try {
                        out.println(a.getNivel().toString());
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

                case 10: //getSesiones
                    try {
                        out.println(a.getSesiones().toString());
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

                case 11: //getGrupos
                    try {
                        out.println(a.getGrupos().toString());
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

                case 12: //getGrupo
                    try {
                        out.println("Introduzca el número de la posición del Grupo en el Array");
                        out.println("de grupos de la Asignatura (para obtener dicho Grupo)");
                        out.println("\n(Array de grupos:)");
                        try {
                            out.println(a.getGrupos());
                        } catch (Exception e) {
                            out.println("ERROR EN getGrupos()");
                            out.println("\n");
                            break;
                        }
                        String plazas = in.next();
                        out.println(a.getGrupo(plazas).toString());
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

                case 13: //getRestricciones
                    try {
                        out.println(a.getRestricciones().toString());
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

                case 14: //getAllClases
                    try {
                        out.println(a.getAllClases().toString());
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

                case 15: //setId
                    try {
                        out.println("Introduzca la id de la Asignatura:");
                        String id = in.next();
                        a.setId(id);
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

                case 16: //setNombre
                    try {
                        out.println("Introduzca el nombre de la Asignatura:");
                        String id = in.next();
                        a.setNombre(id);
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

                case 17: //setPlanEstudios
                    try {
                        out.println("Planes de Estudios disponibles predeterminados:");
                        out.println("1- PlEs1");
                        out.println("2- PlEs2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge uno para substituirlo: ");
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
                                a.setPlanEstudios(peAS1);
                            else
                                a.setPlanEstudios(peAS2);
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

                case 18: //setNivel
                    try {
                        out.println("Niveles disponibles predeterminados:");
                        out.println("1- n1");
                        out.println("2- n2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge uno para substituirlo: ");
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
                                a.setNivel(n1);
                            else
                                a.setNivel(n2);
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

                case 19: //setSesiones
                    try {
                        out.println("Se van a introducir las siguientes Sesiones:");
                        out.println(sesions.toString());
                        a.setSesiones(sesions);
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

                case 20: //addSesion
                    try {
                        out.println("Sesiones disponibles predeterminadas:");
                        out.println("1- s1");
                        out.println("2- s2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge uno para añadirla: ");
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
                                a.addSesion(s1);
                            else
                                a.addSesion(s2);
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

                case 21: //eliminarSesion
                    try {
                        out.println("Introduzca el número de la posición de la Sesion en el Array");
                        out.println("de sesiones de la Asignatura (para eliminar dicha Sesion)");
                        out.println("\n(Array de sesiones:)");
                        try {
                            out.println(a.getSesiones());
                        } catch (Exception e) {
                            out.println("ERROR EN getSesiones()");
                            out.println("\n");
                            break;
                        }
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
                        a.eliminarSesion(pl);
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

                case 22: //setGrupos
                    try {
                        out.println("Se van a introducir los siguientes Grupos:");
                        out.println(grupos.toString());
                        a.setGrupos(grupos);
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

                case 23: //addGrupo
                    try {
                        out.println("Grupos disponibles predeterminados:");
                        out.println("1- g1");
                        out.println("2- g2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge uno para añadirlo: ");
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
                                a.addGrupo(g1);
                            else
                                a.addGrupo(g2);
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

                case 24: //replaceGrupo
                    try {
                        out.println("Reemplaza un Grupo del conjunto de Grupos de la Asignatura con el mismo id");
                        out.println("Grupos disponibles predeterminados:");
                        out.println("1- g1, id: Grupo1");
                        out.println("2- g2, id: Grupo2");
                        out.println("3- g3, id: Grupo1");
                        out.println("4- g4, id: Grupo2");
                        out.println("5- (Cancelar)");
                        out.print("Escoge uno para substituirlo por otro presente con el mismo id: ");
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
                                a.replaceGrupo(g1);
                            else if (ni == 2)
                                a.replaceGrupo(g2);
                            else if (ni == 3)
                                a.replaceGrupo(g3);
                            else
                                a.replaceGrupo(g4);
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

                case 25: //eliminarGrupo
                    try {
                        out.println("Introduzca el id del Grupo en el Array de grupos");
                        out.println("de la Asignatura (para eliminar dicho Grupo)");
                        out.println("\n(Array de grupos:)");
                        try {
                            out.println(a.getGrupos());
                        } catch (Exception e) {
                            out.println("ERROR EN getGrupos()");
                            out.println("\n");
                            break;
                        }
                        String plazas = in.next();
                        a.eliminarGrupo(plazas);
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

                case 26: //setRestricciones
                    try {
                        out.println("Se van a introducir los siguientes Grupos:");
                        out.println(restr.toString());
                        a.setRestricciones(restr);
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

                case 27: //addRestriccion
                    try {
                        out.println("Restricciones disponibles predeterminadas:");
                        out.println("1- re1");
                        out.println("2- re2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge una para añadirla: ");
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
                                a.addRestriccion(re1);
                            else
                                a.addRestriccion(re2);

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

                case 28: //eliminarRestriccion
                    try {
                        out.println("Introduzca el número de la posición de la Restriccion en el Array");
                        out.println("de restricciones de la Asignatura (para eliminar dicha Restriccion)");
                        out.println("\n(Array de restricciones:)");
                        try {
                            out.println(a.getRestricciones());
                        } catch (Exception e) {
                            out.println("ERROR EN getRestricciones()");
                            out.println("\n");
                            break;
                        }
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
                        a.eliminarRestriccion(pl);
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

                case 29:
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
        out.print("1- Asignatura(String, String, PlanEstudios)\n");
        out.print("2- Asignatura(String, String, PlanEstudios, Nivel)\n");
        out.print("3- Asignatura(String, String, PlanEstudios, ArrayList<Sesion>, Map<String, Grupo>)\n");
        out.print("4- Asignatura(String, String, PlanEstudios, Nivel, ArrayList<Sesion>, Map<String, Grupo>)\n");
        out.print("CONSULTORAS:\n");
        out.print("5- getId()\n");
        out.print("6- getNombre()\n");
        out.print("7- getPlanEstudios()\n");
        out.print("8- tieneNivel()\n");
        out.print("9- getNivel()\n");
        out.print("10- getSesiones()\n");
        out.print("11- getGrupos()\n");
        out.print("12- getGrupo(String)\n");
        out.print("13- getRestricciones()\n");
        out.print("14- getAllClases()\n");
        out.print("MODIFICADORAS:\n");
        out.print("15- setId(String)\n");
        out.print("16- setNombre(String)\n");
        out.print("17- setPlanEstudios(PlanEstudios)\n");
        out.print("18- setNivel(Nivel)\n");
        out.print("19- setSesiones(ArrayList<Sesion>)\n");
        out.print("20- addSesion(Sesion)\n");
        out.print("21- eliminarSesion(int)\n");
        out.print("22- setGrupos(Map<String, Grupo>)\n");
        out.print("23- addGrupo(Grupo)\n");
        out.print("24- replaceGrupo(Grupo)\n");
        out.print("25- eliminarGrupo(String)\n");
        out.print("26- setRestricciones(ArrayList<Restriccion>)\n");
        out.print("27- addRestriccion(Restriccion)\n");
        out.print("28- eliminarRestriccion(Integer)\n");
        out.print("29- Salir\n");
        out.print("Tu opción: ");
    }

}