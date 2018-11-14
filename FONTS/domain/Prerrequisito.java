/**Prerrequisito*/

/**Imports*/

package domain;

/**
 * Prerrequsito representa la relación entre una Asignatura y su Prerrequisito.
 * @author  Daniel Martín
 */
public class Prerrequisito extends Restriccion {

    /**Atributos*/

    /**Asignatura a la que afecta el Prerrequisito.*/
    private Asignatura asignatura;
    /**Asignatura que es Prerrquisito de asignatura.*/
    private Asignatura prerrequisito;

    /**Constructoras*/

    /**
     * Constructora de la clase Prerrequisito.
     * @param asignatura    Asignatura que se asigna al atributo asignatura.
     * @param prerrequisito Asignatura que se asigna al atributo prerrequisito.
     */
    public Prerrequisito(Asignatura asignatura, Asignatura prerrequisito) {
        this.asignatura = asignatura;
        this.prerrequisito = prerrequisito;
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva Asignatura al atributo asignatura de Prerrequisito.
     * @param asignatura    Asignatura que se asigna al atributo asignatura de Prerrequisito.
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /**
     * Asigna una nueva Asignatura al atributo prerrequisito de Prerrequisito.
     * @param prerrequisito    Asignatura que se asigna al atributo prerrequisito de Prerrequisito.
     */
    public void setPrerrequisito(Asignatura prerrequisito) {
        this.prerrequisito = prerrequisito;
    }

    /**Consultoras*/

    /**
     * Se obtiene la Asignatura correspondiente al atributo asignatura de Prerrequisito.
     * @return Asignatura correspondiente al atributo asignatura de Prerrequisito.
     */
    public Asignatura getAsignatura() {
        return asignatura;
    }

    /**
     * Se obtiene la Asignatura correspondiente al atributo prerrequisito de Prerrequisito.
     * @return Asignatura correspondiente al atributo prerrequisito de Prerrequisito.
     */
    public Asignatura getPrerrequisito() {
        return prerrequisito;
    }

    /**Métodos redefinidos*/

    /**
     * Devuelve el TipoRestriccion correspondiente.
     * @return  TipoRestriccion.Prerrequisito.
     */
    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.Prerrequisito;
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
        return true;
    }

    /**
     * Convierte Prerrequisito a un String con su información.
     * @return  String con la información Prerrequisito.
     */
    @Override
    public String toString() {
        return ("Prerequisito:\n-Asignatura: " + this.asignatura.getId() + "\n-Prerrequisito: " + this.prerrequisito.getId());
    }
}