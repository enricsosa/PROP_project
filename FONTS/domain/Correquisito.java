/**Correquisito*/

/**Imports*/

package domain;

/**
 * Correquisito contiene la información de una Restriccion asociada a 2 Asignaturas que son Correquisito.
 * @author  Daniel Martín
 */
public class Correquisito extends Restriccion {

    /**Atributos*/

    /**Asignatura que forma parte del Correquisito.*/
    private Asignatura asignatura1;
    /**Asignatura que forma parte del Correquisito.*/
    private Asignatura asignatura2;

    /**Constructoras*/

    /**
     * Constructora de la clase Correquisito.
     * @param asignatura1   1a Asignatura que forma parte del Correquisito.
     * @param asignatura2   2a Asignatura que forma parte del Correquisito.
     */
    public Correquisito(Asignatura asignatura1, Asignatura asignatura2) {
        this.asignatura1 = asignatura1;
        this.asignatura2 = asignatura2;
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva Asignatura al Correquisito como asignatura1.
     * @param asignatura1   Asignatura que se quiere poner como asignatura1.
     */
    public void setAsignatura1(Asignatura asignatura1) {
        this.asignatura1 = asignatura1;
    }

    /**
     * Asigna una nueva Asignatura al Correquisito como asignatura2.
     * @param asignatura2   Asignatura que se quiere poner como asignatura2.
     */
    public void setAsignatura2(Asignatura asignatura2) {
        this.asignatura2 = asignatura2;
    }

    /**Consultoras*/

    public Asignatura getAsignatura1() {
        return this.asignatura1;
    }

    public Asignatura getAsignatura2() {
        return this.asignatura2;
    }

    /**
     * Comprueba si una Asignatura forma parte del Correquisito.
     * @param asignatura    Asignatura que queremos comprovar si forma parte del Correquisito.
     * @return              true si asigantura forma parte del Correquisito, false en caso contrario.
     */
    public Boolean tieneAsignatura(Asignatura asignatura) {
        if (asignatura == asignatura1) return true;
        if (asignatura == asignatura2) return true;
        return false;
    }

    /**
     * Indica con un int si una Asigantura ocupa el atributo asignatura1 o asignatura2.
     * @param asignatura    Asignatura que queremos saber en que atributo está.
     * @return              1 si asignatura1 es asignatura, 2 en el otro caso.
     */
    public int getIndex(Asignatura asignatura) {
        if (asignatura == asignatura1) return 1;
        return 2;
    }

    /**Métodos redefinidos*/

    /**
     * Devuelve el TipoRestriccion correspondiente.
     * @return  TipoRestriccion.Correquisito.
     */
    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.Correquisito;
    }

    /**
     * Comprueva que clase cumple la restricción respecto a un dia, horaIni y ocupaciones.
     * @param clase         Clase de la que se comprueba la Retriccion.
     * @param dia           dia en que se comprueba la Restriccion.
     * @param horaIni       horaIni con la que se comprueba la Restriccion.
     * @param ocupaciones   Ocupaciones respecto a las cuales se comprueba la Restriccion.
     * @return              true si se cumple la Restriccion con las condiciones dadas, false en caso contrario.
     */
    @Override
    public Boolean comprobarRestriccion(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones) {
        if (!(this.tieneAsignatura(clase.getAsignatura()))) return true;
        Asignatura target;
        if (this.getIndex(clase.getAsignatura()) == 1) target = asignatura2;
        else target = asignatura1;
        for (int hora = horaIni; hora < horaIni + clase.getDuracion(); ++hora) {
            if (ocupaciones.getDia(dia).getHora(hora).tieneGrupo(target, clase.getGrupo())) return false;
        }
        return true;
    }

    /**
     * Convierte el Correquisito a un String con su información.
     * @return  String con la información del Correquisito.
     */
    @Override
    public String toString() {
        return ("Correquisito:\n- " + this.asignatura1.getId() + "\n- " + this.asignatura2.getId());
    }
}