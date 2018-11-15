package tests;

import domain.*;

import java.util.*;

import static java.lang.System.out;

/**@author Enric Sosa*/
public class driverAsignacion {

    private static Scanner in = new Scanner(System.in);

    private static PlanEstudios peAS = null;
    private static ArrayList<TipoClase> atc = new ArrayList<TipoClase>();
    private static Aula au1 = new Aula("au1", 80, atc);
    private static Aula au2 = new Aula("au2", 60, atc);

    private static SubGrupo sg1 = null;
    private static SubGrupo sg2 = null;

    private static Sesion s1 = null;
    private static Sesion s2 = null;
    private static ArrayList<Sesion> sesions = new ArrayList<Sesion>();

    private static Clase c1 = null;
    private static Clase c2 = null;

    private static Nivel n1 = new Nivel("n1");
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
        Asignatura as = new Asignatura("as", "AS", peAS);
        Grupo g = new Grupo("Dummy", as);
        sg1 = new SubGrupo("1", 60, TipoClase.valueOf("Teoria"), g);
        sg2 = new SubGrupo("2", 30, TipoClase.valueOf("Problemas"), g);

        Asignatura as1 = new Asignatura("as1", "AS1", peAS, n1);
        Asignatura as2 = new Asignatura("as2", "AS2", peAS);
        s1 = new Sesion(2, TipoClase.valueOf("Teoria"), as1);
        s2 = new Sesion(1, TipoClase.valueOf("Problemas"), as2);
        sesions.add(s1);
        sesions.add(s2);

        c1 = new Clase(sg1, s1);
        c2 = new Clase(sg2, s2);

        restr.add(re1); restr.add(re2);

        as.setRestricciones(restr);
    }

    public static void main (String argv[]) {
        out.print("Driver clase Asignatura\n");
        Asignacion a = null;
        initValues();
        mostrarMenu();
        int op = -1;

        while (op != 0) {
            op = obtenerOp(22);
            switch (op) {
                case 1:
                    try {
                        out.println("Introduzca la horaIni(Integer) de la Asignacion:");
                        out.println("(para este driver no importa que la hora sea mayor de 24)");
                        String nivel = in.next();
                        int hi = 0;
                        boolean valid = true;
                        for (int i = 0; i < nivel.length() && valid; ++i) {
                            hi *= 10;
                            if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                hi += (int)(nivel.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (valid) {
                            out.println("Introduzca el dia de la semana(Inetger) de la Asignacion:");
                            out.println("([1 -> Lunes, ..., 7 -> Domingo]; No se acpetan otros números)");
                            nivel = in.next();
                            int dia = 0;
                            valid = true;
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
                                out.println("Clases disponibles predeterminadas:");
                                out.println("1- c1");
                                out.println("2- c2");
                                out.println("3- (Cancelar)");
                                out.print("Escoge una para añadirla: ");
                                nivel = in.next();
                                int ni = 0;
                                valid = true;
                                Clase c = null;
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
                                        c = c1;
                                    else
                                        c = c2;

                                    out.println("Aulas disponibles predeterminadas:");
                                    out.println("1- a1");
                                    out.println("2- a2");
                                    out.println("3- (Cancelar)");
                                    out.print("Escoge una para añadirla: ");
                                    nivel = in.next();
                                    ni = 0;
                                    valid = true;
                                    Aula au = null;
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
                                            au = au1;
                                        else
                                            au = au2;
                                        a = new Asignacion(hi, dia, au, c);
                                        out.println("HA FUNCIONADO CORRECTAMENTE");

                                    } else if (ni != 3) {
                                        out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                                    }
                                } else if (ni != 3) {
                                    out.printf("ERROR: el parámetro \"%s\" no es válido\n", nivel);
                                }
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

                case 2:
                    try {
                        out.println("Asignacion b tiene valor null");
                        Asignacion b = null;
                        out.println("Asignacion a es la que tenemos ahora mismo");
                        out.println("(Debería ser diferente de null para ver la efectividad de este método)");
                        out.println(a.toString());
                        out.println("Hacemos la copia");
                        b = new Asignacion(a);
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

                case 3:
                    try {
                        out.println("Comprueba que no existe conflicto entre SubGrupo y Aula involucrados");
                        if (a.noCabeSubGrupo()) {
                            out.println("NO cabe la Clase a su Aula de la Asignacion");
                        } else {
                            out.println("Cabe la Clase a su Aula de la Asignacion");
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

                case 4:
                    try {
                        out.println(a.generateKey());
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
                        out.println(a.getHoraIni().toString());
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
                        out.println(a.getDiaSemana().toString());
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
                        out.println(a.getHoraFin().toString());
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
                        out.println(a.getAula().toString());
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
                        out.println(a.getGrupo().toString());
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
                    try {
                        out.println(a.getSubGrupo().toString());
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

                case 11:
                    try {
                        out.println(a.getClase().toString());
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

                case 12: //getRestricciones
                    try {
                        out.println("La Asignatura as del Grupo del Subrupo de la Clase de la Asignacion");
                        out.println("debe tener restricciones");
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

                case 13: //getRestriccion
                    try {
                        out.println("Introduzca el número de la posición de la Restriccion en el Array");
                        out.println("de restricciones de la Asignacion (para obtener dicha Restriccion)");
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
                        out.println(a.getRestriccion(pl).toString());
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

                case 14:
                    try {
                        out.println(a.getAsignatura().toString());
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

                case 15: //tieneNivel
                    try {
                        out.println("(La Asignatura as1 tiene un Nivel Asociado, la Asignatura as2 no)");
                        if (a.tieneNivel()) {
                            out.println("Su Asignatura asociada a su Grupo de la Clase tiene un Nivel asociado");
                        } else {
                            out.println("Su Asignatura asociada a su Grupo de la Clase NO tiene un Nivel asociado");
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

                case 16: //getNivel
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

                case 18: //setHoraIni
                    try {
                        out.println("Introduzca la horaIni(Integer) de la Asignacion:");
                        out.println("(para este driver no importa que la hora sea mayor de 24)");
                        String nivel = in.next();
                        int hi = 0;
                        boolean valid = true;
                        for (int i = 0; i < nivel.length() && valid; ++i) {
                            hi *= 10;
                            if (nivel.charAt(i) >= '0' && nivel.charAt(i) <= '9') {
                                hi += (int)(nivel.charAt(i) - '0');
                            } else {
                                valid = false;
                            }
                        }
                        if (valid) {
                            a.setHoraIni(hi);
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

                case 17: //getNivel
                    try {
                        out.println(a.toStringCompleto());
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

                case 19: //setDiaSemana
                    try {
                        out.println("Introduzca el dia de la semana(Inetger) de la Asignacion:");
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
                            a.setDiaSemana(dia);
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

                case 20: //setAula
                    try {
                        out.println("Aulas disponibles predeterminadas:");
                        out.println("1- a1");
                        out.println("2- a2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge una para añadirla: ");
                        String nivel = in.next();
                        int ni = 0;
                        boolean valid = true;
                        Aula au = null;
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
                                au = au1;
                            else
                                au = au2;
                            a.setAula(au);
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

                case 21: //setAula
                    try {
                        out.println("Clases disponibles predeterminadas:");
                        out.println("1- c1");
                        out.println("2- c2");
                        out.println("3- (Cancelar)");
                        out.print("Escoge una para añadirla: ");
                        String nivel = in.next();
                        int ni = 0;
                        boolean valid = true;
                        Clase c = null;
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
                                c = c1;
                            else
                                c = c2;
                            a.setClase(c);
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

                case 22:
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
        out.print("1- Asignacion(Integer, Integer, Aula, Clase)\n");
        out.print("2- Asignacion(Asignacion)\n");
        out.print("CONSULTORAS:\n");
        out.print("3- noCabeSubGrupo()\n");
        out.print("4- generateKey()\n");
        out.print("5- getHoraIni()\n");
        out.print("6- getDiaSemana()\n");
        out.print("7- getHoraFin()\n");
        out.print("8- getAula()\n");
        out.print("9- getGrupo()\n");
        out.print("10- getSubGrupo()\n");
        out.print("11- getClase()\n");
        out.print("12- getRestricciones()\n");
        out.print("13- getRestriccion(int)\n");
        out.print("14- getAsignatura()\n");
        out.print("15- tieneNivel()\n");
        out.print("16- getNivel()\n");
        out.print("17- toStringCompleto()\n");
        out.print("MODIFICADORAS:\n");
        out.print("18- setHoraIni(Integer)\n");
        out.print("19- setDiaSemana(Integer)\n");
        out.print("20- setAula(Aula)\n");
        out.print("21- setClase(Clase)\n");
        out.print("22- Salir\n");
        out.print("Tu opción: ");
    }

}
