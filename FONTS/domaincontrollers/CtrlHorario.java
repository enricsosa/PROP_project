/**CtrlHorario*/

/**Imports*/

package domaincontrollers;

import java.util.*;

import domain.*;

/**
 * CtrlHorario es un controlador de Dominio que se encarga de generar un Horario dado un escenario.
 * @author  Daniel Martín
 * @see     ArrayList
 * @see     Collections
 * @see     Map
 * @see     Comparator
 * @see     domain.PlanEstudios
 * @see     domain.Restriccion
 * @see     domain.TipoRestriccion
 * @see     domain.FranjaTrabajo
 * @see     domain.DiaLibre
 * @see     domain.Clase
 * @see     domain.Horario
 * @see     domain.Aula
 * @see     domain.Asignacion
 * @see     domain.Nivel
 * @see     domain.TipoRestriccion
 * @see     domain.FranjaNivel
 * @see     domain.Asignatura
 * @see     domain.Aux
 * @see     domain.FranjaAsignatura
 * @see     domain.SubGrupo
 * @see     domain.Grupo
 */
public class CtrlHorario {

    /**Atributos*/

    /**PlanEstudios del escenario del que se genera el Horario.*/
    private PlanEstudios planEstudios;
    /**FranjaTrabajo y diasLibres del escenario del que se genera el Horario.*/
    private LimitacionesHorario limitacionesHorario;

    /**Constructoras*/

    /**
     * Constructora de la clase CtrlHorario.
     * @param planEstudios  PlanEstudios con el que se generan los Horarios.
     */
    CtrlHorario(PlanEstudios planEstudios) {
        this.planEstudios = planEstudios;
        this.limitacionesHorario = new LimitacionesHorario();
        this.loadLimitacionesHorario();
    }

    /**Métodos públicos*/

    /**
     * Asigna un nuevo PlanEstudios a CtrlHorario.
     * @param planEstudios  Nuevo PlanEstudios que se asigna a CtrlHorario.
     */
    public void setPlanEstudios(PlanEstudios planEstudios) {
        this.planEstudios = planEstudios;
    }

    /**
     * Asigna un nuveo LimitacionesHorario a CtrlHorario.
     * @param limitacionesHorario   Nuevo LimitacionesHorario que se asigna a CtrlHorario.
     */
    public void setLimitacionesHorario(LimitacionesHorario limitacionesHorario) {
        this.limitacionesHorario = limitacionesHorario;
    }

    /**Actualiza limitacionesHorario a partir de las Restricciones de planEstudios.*/
    public void loadLimitacionesHorario() {
        Integer horaIni = 0;
        Integer horaFin = 24;
        for (int i = 0; i < this.planEstudios.getRestricciones().size(); ++i) {
            if (this.planEstudios.getRestriccion(i).getTipoRestriccion() == TipoRestriccion.DiaLibre && this.planEstudios.getRestriccion(i).getActiva()) {
                this.limitacionesHorario.setDiaLibre(((DiaLibre)(this.planEstudios.getRestriccion(i))).getDia(), true);
            }
            else if (this.planEstudios.getRestriccion(i).getTipoRestriccion() == TipoRestriccion.FranjaTrabajo && this.planEstudios.getRestriccion(i).getActiva()) {
                this.limitacionesHorario.setHoraIni(((FranjaTrabajo)this.planEstudios.getRestriccion(i)).getHoraIni());
                this.limitacionesHorario.setHoraFin(((FranjaTrabajo)this.planEstudios.getRestriccion(i)).getHoraFin());
            }
        }
        //out.println(limitacionesHorario.toString());
    }

    /**
     * Devuelve el conjunto de Clases que se infieren del PlanEstudios.
     * @return  Conjunto de Clases que se pueden realizar a partir del PlanEstudios.
     */
    public ArrayList<Clase> getAllClases() {
        return this.planEstudios.getAllClases();
    }

    /**
     * Genera un Horario posible (si lo hay) para el escenario cargado.
     * @param id    id que se dará al Horario que se genere.
     * @return      ReturnSet con validez indicando si se ha podido generar un Horario. Si se ha podido ReturnSet también contiene el Horario.
     */
    public ReturnSet generarHorario(String id) {
        Horario horario = new Horario(id);

        ArrayList<Clase> clases = this.getAllClases();
        sortClases(clases, this.limitacionesHorario, false);

        ArrayList<ReturnSet> franjas = new ArrayList<ReturnSet>();
        for (int i = 0; i < clases.size(); ++i) {
            franjas.add(getFranjaClase(clases.get(i), this.limitacionesHorario));
        }

        Boolean[] placed = new Boolean[clases.size()];
        Arrays.fill(placed, false);

        for (int i = 0; i < clases.size(); ++i) {
            Clase clase = clases.get(i);
            ReturnSet franja = franjas.get(i);

            for (int dia = 1; dia <= 7; ++dia) {
                if (!(this.limitacionesHorario.esDiaLibre(dia))) {
                    for (int horaIni = franja.getHoraIni(); (horaIni + clase.getDuracion()) <= (franja.getHoraFin()); ++horaIni) {

                        for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clase).entrySet()) {
                            Asignacion asignacion = new Asignacion(horaIni, dia, entry.getValue(), clase);
                            placed[i] = true;
                            ReturnSet returnSet = this.generarAsignaciones(asignacion, clases, horario, franjas, placed, 0);
                            if (returnSet.getValidez()) return returnSet;
                            placed[i] = false;
                        }
                    }
                }
            }
        }
        return new ReturnSet(false);
    }

    /**
     * Añade una nueva Asignacion a horario y explora todas las opciones posibles para crear Asignaciones válidas
     * con las Clases del ArrayList clases y las Aulas del escenario cargado y las añade a horario (si es posible).
     * @param asignacion    Asignacion que se añade a horario.
     * @param clases        Clases que se intentará añadir a horario.
     * @param horario       Horario donde se intentará añadir las Clases de clases.
     * @param franjas      franajas de las clases que se quiere añadir.
     * @param placed        Mantiene constancia de que clases hay ya en la solución.
     * @param step          Numero de asignaciones colocadas.
     * @return              ReturnSet con validez indicando si se ha podido crear Asignaciones válidas para todas las
     *                      Clases de clases. Si validez es true, el ReturnSet contiene también Horario con todas
     *                      las Asignaciones con las Clases de clases.
     */
    public ReturnSet generarAsignaciones(Asignacion asignacion, ArrayList<Clase> clases, Horario horario, ArrayList<ReturnSet> franjas,Boolean[] placed, int step) {

        horario.addAsignacion(asignacion);

        //System.out.println("Step: " + step + Aux.spacer() + Aux.spacer());
        //System.out.println(horario);

        if (!(Arrays.asList(placed).contains(false))) {
            //System.out.println("No hay más clases por añadir.");
            return new ReturnSet(true, horario);
        }

        for (int i = 0; i < clases.size(); ++i) {
            //System.out.println("clase:" + i);
            if (placed[i] == false) {

                //System.out.println("clase aun por colocar.");

                Clase clase = clases.get(i);
                ReturnSet franja = franjas.get(i);

                int ini, fin, inc;

                if (step % 2 == 0) {
                    ini = 1;
                    fin = 8;
                    inc = 1;
                }

                else {
                    ini = 7;
                    fin = 0;
                    inc = -1;
                }

                for (int dia = ini; dia != fin; dia += inc) {
                    if ((!(this.limitacionesHorario.esDiaLibre(dia)))
                            && (!(comprovarSubGrupoDia(clase, dia, horario)))
                            && (!(comprovarGrupoDia(clase, dia, horario)))) {

                        //System.out.println("Cumple restricciones de dia.");

                        for (int horaIni = franja.getHoraIni(); (horaIni + clase.getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                            if (this.comprobarRestricciones(clase, dia, horaIni, horario)) {

                                //System.out.println("Cumple restrcciones de hora.");
                                for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clase).entrySet()) {
                                    if (!(aulaOcupada(clase, dia, horaIni, entry.getValue(), horario))) {
                                        //System.out.println("Se ha encontrado aula adequada.");
                                        Asignacion nextAsignacion = new Asignacion(horaIni, dia, entry.getValue(), clase);
                                        placed[i] = true;
                                        ReturnSet returnSet = this.generarAsignaciones(nextAsignacion, clases, horario, franjas, placed, step + 1);
                                        if (returnSet.getValidez()) return returnSet;
                                        placed[i] = false;
                                    }
                                }
                            }
                            //else System.out.println("No se cumplen las restricciones opcionales.");
                        }
                    }
                }

            }
            //System.out.println("Clase ya colocada.");
        }
        //System.out.println("Se han probado todas las clases.");
        horario.eliminarAsignacion(asignacion);
        return new ReturnSet(false);
    }

    /**
     * Dada una Clase devuelve un ReturnSet con horaIni i horaFin del la franja horaria para las Asignaciones que
     * contengan la Clase clase en el escenario cargado.
     * @param clase                 Clase de la que se quiere saber horaIni i horaFin del la franja horaria para las
     *                              Asignaciones que la contengan.
     * @param limitacionesHorario   LimitacionesHorario del escenario cargado.
     * @return                      ReturnSet con horaIni i horaFin del la franja horaria para las Asignaciones que
     *                              contengan la Clase clase en el escenario cargado.
     */
    static ReturnSet getFranjaClase(Clase clase, LimitacionesHorario limitacionesHorario) {
        int horaIni = limitacionesHorario.getHoraIni();
        int horaFin = limitacionesHorario.getHoraFin();
        if (clase.tieneNivel()) {
            ArrayList<Restriccion> restricciones = clase.getNivel().getRestricciones();
            Boolean found = false;
            for (int i = 0; (i < restricciones.size()) && (!found); ++i) {
                if (restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaNivel && restricciones.get(i).getActiva()) {
                    found = true;
                    horaIni = Aux.max(horaIni, ((FranjaNivel)(restricciones.get(i))).getHoraIni());
                    horaFin = Aux.min(horaFin, ((FranjaNivel)(restricciones.get(i))).getHoraFin());
                }
            }
        }
        ArrayList<Restriccion> restricciones = clase.getAsignatura().getRestricciones();
        boolean found = false;
        for (int i = 0; (i < restricciones.size()) && (!found); ++i) {
            if (restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaAsignatura && restricciones.get(i).getActiva()) {
                found = true;
                horaIni = Aux.max(horaIni, ((FranjaAsignatura)(restricciones.get(i))).getHoraIni());
                horaFin = Aux.min(horaFin, ((FranjaAsignatura)(restricciones.get(i))).getHoraFin());
            }
        }
        return new ReturnSet(horaIni, horaFin);
    }

    /**
     * Ordena las Clases del ArrayList clases en función del tamaño de la franja horaria a la que son asignables de
     * manera ascendente.
     * @param clases    Clases que se quiere ordenar.
     * @param lh        LimitacionesHorario del escenario cargado.
     * @param random    Boolean que indica si se añade un factor de aleatoriedad para cuando 2 clases tienen franaj del
     *                  mismo tamaño.
     */
    static void sortClases(ArrayList<Clase> clases, LimitacionesHorario lh, Boolean random) {
        if (random) Collections.shuffle(clases);
        Collections.sort(clases, new Comparator<Clase>() {
            @Override
            public int compare(Clase c1, Clase c2)
            {
                ReturnSet rc1 = getFranjaClase(c1, lh);
                int fc1 = rc1.getHoraFin() - rc1.getHoraIni();

                ReturnSet rc2 = getFranjaClase(c2, lh);
                int fc2 = rc2.getHoraFin() - rc2.getHoraIni();

                if (fc1 == fc2) return c1.getRestricciones().size() - c2.getRestricciones().size();

                return fc1 - fc2;
            }
        });
    }

    /**
     * Dada una candidatura de Asignacion compuesta por una Clase, un dia, una horaIni y un Aula, que se quiere añadir a
     * Horario, comprueba si el aula está ocupada ya por otra Asignacion.
     * @param clase         Clase de la Asignacion candidata.
     * @param dia           dia de la Asignacion candidata.
     * @param horaIni       horaIni de la Asignacion candidata.
     * @param aula          Aula de la Asignacion candidata.
     * @param horario       Horario al que se pretende añadir una Asignacion candidata.
     * @return              true si el Aula está ocupada durante la Asignacion candidata, false en caso contrario.
     */
    public static Boolean aulaOcupada(Clase clase, int dia, int horaIni,  Aula aula,  Horario horario) {
        for (int hora = horaIni; hora < (horaIni + clase.getDuracion()); ++hora) {
            if (horario.getHora(dia, hora).tieneAula(aula)) return true;
        }
        return false;
    }

    //public Boolean comprobarRestricciones(Asignacion asignacion, Ocupaciones ocupaciones) {
    //  return asignacion.comprobarRestricciones(ocupaciones);
    //}

    /**
     * Dada una candidatura de Asignacion compuesta por una Clase, un dia y una horaIni, que se quiere añadir a
     * Horario, comprueba si no se incumple ninguna Restriccion.
     * @param clase         Clase de la Asignacion candidata.
     * @param dia           dia de la Asignacion candidata.
     * @param horaIni       horaIni de la Asignacion candidata.
     * @param horario       horario al que se pretende añadir una Asignacion candidata.
     * @return              true si no se incumple ninguna Restriccion, false en caso contrario.
     */
    public static Boolean comprobarRestricciones(Clase clase, int dia, int horaIni, Horario horario) {
        return clase.comprobarRestricciones(dia, horaIni, horario);
    }

    /**
     * Dada una candidatura de Asignacion compuesta por una Clase, y un dia, que se quiere añadir a Horario,
     * comprueba si el SubGrupo de Clase ya participa en una Asignacion.
     * @param clase         Clase de la Asignacion candidata.
     * @param dia           dia de la Asignacion candidata.
     * @param horario       horario al que se pretende añadir una Asignacion candidata.
     * @return              true si no se incumple ninguna Restriccion, false en caso contrario.
     */
    public static Boolean comprovarSubGrupoDia(Clase clase, int dia, Horario horario) {
        return horario.getDia(dia).tieneSubGrupo(clase.getSubGrupo());
    }

    /**
     * Dada una candidatura de Asignacion compuesta por una Clase, y un dia, que se quiere añadir a Horario,
     * comprueba si algun SubGrupo de del Grupo de Clase participa ya en una Asignacion con un SubGrupo de diferente
     * TipoClase en el dia indicado.
     * @param clase         Clase de la Asignacion candidata.
     * @param dia           dia de la Asignacion candidata.
     * @param horario       horario al que se pretende añadir una Asignacion candidata.
     * @return              true si algun SubGrupo de del Grupo de Clase participa ya en una Asignacion con un SubGrupo
     *                      de diferente TipoClase en el dia indicado, false en caso contrario.
     */
    public static Boolean comprovarGrupoDia(Clase clase, int dia, Horario horario) {
        if (!(horario.getDia(dia).tieneGrupo(clase.getGrupo()))) return false;
        for (Map.Entry<String, SubGrupo> entry : clase.getGrupo().getSubGrupos().entrySet()) {
            if (clase.getSubGrupo() != entry.getValue()) {
                if (horario.getDia(dia).tieneSubGrupo(entry.getValue())) {
                    if (entry.getValue().getTipo() != clase.getSubGrupo().getTipo()) return true;
                }
            }
        }
        return false;
    }

    /**
     * Dada una Clase devuelve todas las Aulas compatibles con esa Clase cargadas en el escenario.
     * @param clase Clase de la que se queire devolver las Aulas adecuadas.
     * @return      Aulas válidas para la Clase clase.
     */
    public Map<String, Aula> getAulasAdecuadas(Clase clase) {
        if (clase.getTipoSesion() == TipoClase.Teoria) return this.planEstudios.getAulasTeoria(clase.getPlazas());
        else if (clase.getTipoSesion() == TipoClase.Laboratorio) return this.planEstudios.getAulasLaboratorio(clase.getPlazas());
        else return this.planEstudios.getAulasProblemas(clase.getPlazas());
    }

    /**
     * Devuelve una copia de un ArrayList de Clases eliminando el elemento que se encuentra en la posicion indicada.
     * @param oldClases Clases que se quieren copiar.
     * @param posicion  posicion de la Clase que se quiere eliminar.
     * @return          Copia del ArrayList de Clases eliminando el elemento que se encuentra en la posicion indicada.
     */
    static ArrayList<Clase> copyRemoveClases (ArrayList<Clase> oldClases, int posicion) {
        ArrayList<Clase> clases = copyClases(oldClases);
        clases.remove(posicion);
        return clases;
    }

    /**
     * Devuelve una copia del ArrayList de Clases.
     * @param oldClases Clases de las que se genera una copia.
     * @return          Copia del ArrayList de Clases.
     */
    static ArrayList<Clase> copyClases (ArrayList<Clase> oldClases) {
        ArrayList<Clase> clases = new ArrayList<Clase>();
        clases.addAll(oldClases);
        return clases;
    }

    /**Consultoras*/

    /**
     * Devuelve el PlanEstudios de CtrlHorario.
     * @return  PlanEstudios de CtrlHorario.
     */
    public PlanEstudios getPlanEstudios() {
        return planEstudios;
    }

    /**
     * Devuelve las LimitacionesHorario de CtrlHorario.
     * @return  LimitacionesHorario de CtrlHorario.
     */
    public LimitacionesHorario getLimitacionesHorario() {
        return limitacionesHorario;
    }

    /**Métodos redefinidos*/

}