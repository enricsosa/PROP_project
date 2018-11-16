/**CtrlHorario*/

/**Imports*/

package domaincontrollers;

import java.util.*;
import static java.lang.System.out;

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
 * @see     domain.Ocupaciones
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
    /**Restricciones del escenario del que se genera el Horario.*/
    private ArrayList<Restriccion> restricciones;
    /**FranjaTrabajo y diasLibres del escenario del que se genera el Horario.*/
    private LimitacionesHorario limitacionesHorario;

    /**Constructoras*/

    /**
     * Constructora de la clase CtrlHorario.
     * @param planEstudios  PlanEstudios con el que se generan los Horarios.
     * @param restricciones Restricciones con las que se generan los Horarios.
     */
    CtrlHorario(PlanEstudios planEstudios, ArrayList restricciones) {
        this.planEstudios = planEstudios;
        this.restricciones = restricciones;
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
     * Asigna un nuevo conjunto de Restricciones a CtrlHorario.
     * @param restricciones Nuevo conjunto de Restricciones que se asigna a CrtlHorario.
     */
    public void setRestricciones(ArrayList<Restriccion> restricciones) {
        this.restricciones = restricciones;
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
            if (this.planEstudios.getRestriccion(i).getTipoRestriccion() == TipoRestriccion.DiaLibre) {
                this.limitacionesHorario.setDiaLibre(((DiaLibre)(this.planEstudios.getRestriccion(i))).getDia(), true);
            }
            else if (this.planEstudios.getRestriccion(i).getTipoRestriccion() == TipoRestriccion.FranjaTrabajo) {
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
        Ocupaciones ocupaciones = new Ocupaciones();
        ArrayList<Clase> clases = this.getAllClases();
        sortClases(clases, this.limitacionesHorario, false);
        Boolean[] placed = new Boolean[clases.size()];
        Arrays.fill(placed, false);
        for (int i = 0; i < clases.size(); ++i) {
            Clase clase = clases.get(i);
            ReturnSet franja = getFranjaClase(clase, this.limitacionesHorario);
            for (int dia = 1; dia <= 7; ++dia) {
                if(!(this.limitacionesHorario.esDiaLibre(dia))) {
                    for (int horaIni = franja.getHoraIni(); (horaIni + clase.getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                        for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clase).entrySet()) {
                            Asignacion asignacion = new Asignacion(horaIni, dia, entry.getValue(), clase);
                            placed[i] = true;
                            ReturnSet returnSet = this.generarAsignaciones(asignacion, clases, new Ocupaciones(ocupaciones), placed, 0);
                            if (returnSet.getValidez()) {
                                horario.setOcupaciones(returnSet.getOcupaciones());
                                return (new ReturnSet(true, horario));
                            }
                            placed[i] = false;
                        }
                    }
                }
            }
            clases.add(i, clase);
        }
        return new ReturnSet(false);
    }

    /**
     * Añade una nueva Asignacion a ocupaciones y explora todas las opciones posibles para crear Asignaciones válidas
     * con las Clases del ArrayList clases y las Aulas del escenario cargado y las añade a ocupaciones (si es posible).
     * @param asignacion    Asignacion que se añade a ocupaciones.
     * @param clases        Clases que se intentará añadir a ocupaciones.
     * @param ocupaciones   Ocupaciones donde se intentará añadir las Clases de clases.
     * @param placed        Mantiene constancia de que clases hay ya en la solución.
     * @param step          Numero de asignaciones colocadas.
     * @return              ReturnSet con validez indicando si se ha podido crear Asignaciones válidas para todas las
     *                      Clases de clases. Si validez es true, el ReturnSet contiene también Ocupaciones con todas
     *                      las Asignaciones con las Clases de clases.
     */
    public ReturnSet generarAsignaciones(Asignacion asignacion, ArrayList<Clase> clases, Ocupaciones ocupaciones, Boolean[] placed, Integer step) {
        out.println(step);
        ocupaciones.addAsignacion(asignacion);
        //out.println("asignacion valida");
        if (!(Arrays.asList(placed).contains(false))) return new ReturnSet(true, ocupaciones);

        for (int i = 0; i < clases.size(); ++i) {
            if (placed[i] == false) {
                ReturnSet franja = getFranjaClase(clases.get(i), this.limitacionesHorario);
                if ((step % 2) == 0) {
                    for (int dia = 1; dia <= 7; ++dia) {
                        //out.println(dia);
                        if ((!(this.limitacionesHorario.esDiaLibre(dia)))
                                && (!(comprovarSubGrupoDia(clases.get(i), dia, ocupaciones)))
                                && (!(comprovarGrupoDia(clases.get(i), dia, ocupaciones)))) {
                            for (int horaIni = franja.getHoraIni(); (horaIni + clases.get(i).getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                                if (this.comprobarRestricciones(clases.get(i), dia, horaIni, ocupaciones)) {
                                    for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clases.get(i)).entrySet()) {
                                        if (!(aulaOcupada(clases.get(i), dia, horaIni, entry.getValue(), ocupaciones))) {
                                            Asignacion nextAsignacion = new Asignacion(horaIni, dia, entry.getValue(), clases.get(i));
                                            placed[i] = true;
                                            step += 1;
                                            ReturnSet returnSet = this.generarAsignaciones(nextAsignacion, clases, new Ocupaciones(ocupaciones), placed, step);
                                            if (returnSet.getValidez()) return returnSet;
                                            placed[i] = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    for (int dia = 7; dia >= 1; --dia) {
                        //out.println("CP 8");
                        //out.println(dia);
                        if ((!(this.limitacionesHorario.esDiaLibre(dia)))
                                && (!(comprovarSubGrupoDia(clases.get(i), dia, ocupaciones)))
                                && (!(comprovarGrupoDia(clases.get(i), dia, ocupaciones)))) {
                            for (int horaIni = franja.getHoraIni(); (horaIni + clases.get(i).getDuracion()) <= (franja.getHoraFin()); ++horaIni) {
                                if (this.comprobarRestricciones(clases.get(i), dia, horaIni, ocupaciones)) {
                                    for (Map.Entry<String, Aula> entry : this.getAulasAdecuadas(clases.get(i)).entrySet()) {
                                        if (!(aulaOcupada(clases.get(i), dia, horaIni, entry.getValue(), ocupaciones))) {
                                            Asignacion nextAsignacion = new Asignacion(horaIni, dia, entry.getValue(), clases.get(i));
                                            placed[i] = true;
                                            step += 1;
                                            ReturnSet returnSet = this.generarAsignaciones(nextAsignacion, clases, new Ocupaciones(ocupaciones), placed, step);
                                            if (returnSet.getValidez()) return returnSet;
                                            placed[i] = false;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
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
                if (restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaNivel) {
                    found = true;
                    horaIni = Aux.max(horaIni, ((FranjaNivel)(restricciones.get(i))).getHoraIni());
                    horaFin = Aux.min(horaFin, ((FranjaNivel)(restricciones.get(i))).getHoraFin());
                }
            }
        }
        ArrayList<Restriccion> restricciones = clase.getAsignatura().getRestricciones();
        boolean found = false;
        for (int i = 0; (i < restricciones.size()) && (!found); ++i) {
            if (restricciones.get(i).getTipoRestriccion() == TipoRestriccion.FranjaAsignatura) {
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
     * Ocupaciones, comprueba si el aula está ocupada ya por otra Asignacion.
     * @param clase         Clase de la Asignacion candidata.
     * @param dia           dia de la Asignacion candidata.
     * @param horaIni       horaIni de la Asignacion candidata.
     * @param aula          Aula de la Asignacion candidata.
     * @param ocupaciones   Ocupaciones a las que se pretende añadir una Asignacion candidata.
     * @return              true si el Aula está ocupada durante la Asignacion candidata, false en caso contrario.
     */
    public Boolean aulaOcupada(Clase clase, int dia, int horaIni,  Aula aula,  Ocupaciones ocupaciones) {
        for (int hora = horaIni; hora < (horaIni + clase.getDuracion()); ++hora) {
            if (ocupaciones.getHora(dia, hora).tieneAula(aula)) return true;
        }
        return false;
    }

    //public Boolean comprobarRestricciones(Asignacion asignacion, Ocupaciones ocupaciones) {
    //  return asignacion.comprobarRestricciones(ocupaciones);
    //}

    /**
     * Dada una candidatura de Asignacion compuesta por una Clase, un dia y una horaIni, que se quiere añadir a
     * Ocupaciones, comprueba si no se incumple ninguna Restriccion.
     * @param clase         Clase de la Asignacion candidata.
     * @param dia           dia de la Asignacion candidata.
     * @param horaIni       horaIni de la Asignacion candidata.
     * @param ocupaciones   Ocupaciones a las que se pretende añadir una Asignacion candidata.
     * @return              true si no se incumple ninguna Restriccion, false en caso contrario.
     */
    public Boolean comprobarRestricciones(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones) {
        return clase.comprobarRestricciones(dia, horaIni, ocupaciones);
    }

    /**
     * Dada una candidatura de Asignacion compuesta por una Clase, y un dia, que se quiere añadir a Ocupaciones,
     * comprueba si el SubGrupo de Clase ya participa en una Asignacion.
     * @param clase         Clase de la Asignacion candidata.
     * @param dia           dia de la Asignacion candidata.
     * @param ocupaciones   Ocupaciones a las que se pretende añadir una Asignacion candidata.
     * @return              true si no se incumple ninguna Restriccion, false en caso contrario.
     */
    public Boolean comprovarSubGrupoDia(Clase clase, int dia, Ocupaciones ocupaciones) {
        return ocupaciones.getDia(dia).tieneSubGrupo(clase.getSubGrupo());
    }

    /**
     * Dada una candidatura de Asignacion compuesta por una Clase, y un dia, que se quiere añadir a Ocupaciones,
     * comprueba si algun SubGrupo de del Grupo de Clase participa ya en una Asignacion con un SubGrupo de diferente
     * TipoClase en el dia indicado.
     * @param clase         Clase de la Asignacion candidata.
     * @param dia           dia de la Asignacion candidata.
     * @param ocupaciones   Ocupaciones a las que se pretende añadir una Asignacion candidata.
     * @return              true si algun SubGrupo de del Grupo de Clase participa ya en una Asignacion con un SubGrupo
     *                      de diferente TipoClase en el dia indicado, false en caso contrario.
     */
    public Boolean comprovarGrupoDia(Clase clase, int dia, Ocupaciones ocupaciones) {
        if (!(ocupaciones.getDia(dia).tieneGrupo(clase.getGrupo()))) return false;
        for (Map.Entry<String, SubGrupo> entry : clase.getGrupo().getSubGrupos().entrySet()) {
            if (clase.getSubGrupo() != entry.getValue()) {
                if (ocupaciones.getDia(dia).tieneSubGrupo(entry.getValue())) {
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
     * Devuelve las Restricciones de CtrlHorario.
     * @return  Restricciones de CtrlHorario.
     */
    public ArrayList<Restriccion> getRestricciones() {
        return restricciones;
    }

    /**
     * Devuelve la Restrccion del conjunto de Restricciones de CtrlHorario que se encuentra en la posicion indicada.
     * @param posicion  posicion de la Restriccion que se quiere obtener.
     * @return          Restrccion del conjunto de Restricciones de CtrlHorario que se encuentra en la posicion indicada.
     */
    public Restriccion getRestriccion(int posicion) {
        return this.restricciones.get(posicion);
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