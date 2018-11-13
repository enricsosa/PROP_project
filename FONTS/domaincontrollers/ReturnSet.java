/**ReturnSet*/

/**Imports*/

package domaincontrollers;

import domain.Horario;
import domain.Ocupaciones;

/**
 * ReturnSet es una clase que se usa para devolver más de un valor de manera cómoda.
 * @author  Daniel Martín
 * @see     Horario
 * @see     Ocupaciones
 */
public class ReturnSet {

    /**Atributos*/

    /**Indica si el retorno contiene datos válidos.*/
    private Boolean validez;
    /**Ocupaciones de un Horario.*/
    private Ocupaciones ocupaciones;
    /**Horario de un PlanEstudios.*/
    private Horario horario;
    /**horaIni de una franja horaria.*/
    private int horaIni;
    /**horaFin de una franja horaria.*/
    private int horaFin;

    /**Constructoras*/

    /**
     * Constructora de la clase ReturnSet con validez.
     * @param validez   validez que se asigna a ReturnSet.
     */
    public ReturnSet(Boolean validez) {
        this.validez = validez;
    }

    /**
     * Constructora de la clase ReturnSet con validez.
     * @param validez       validez que se asigna a ReturnSet.
     * @param ocupaciones   Ocupaciones que se asigna a ReturnSet.
     */
    public ReturnSet(Boolean validez, Ocupaciones ocupaciones) {
        this.validez = validez;
        this.ocupaciones = ocupaciones;
        this.horario = null;
    }

    /**
     * Constructora de la clase ReturnSet con validez.
     * @param validez   validez que se asigna a ReturnSet.
     * @param horario   Horario que se asigna a ReturnSet.
     */
    public ReturnSet(Boolean validez, Horario horario) {
        this.validez = validez;
        this.ocupaciones = null;
        this.horario = horario;
    }

    /**
     * Constructora de la clase ReturnSet con validez.
     * @param horaIni   horaIni que se asigna a ReturnSet.
     * @param horaFin   horaFin que se asigna a ReturnSet.
     */
    public ReturnSet(int horaIni, int horaFin) {
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /**Metodos públicos*/

    /**Consultoras*/

    /**
     * Devuelve el valor de validez.
     * @return  Valor de validez.
     */
    public Boolean getValidez() {
        return this.validez;
    }

    /**
     * Devuelve el valor de ocupaciones.
     * @return  Valor de ocupaciones.
     */
    public Ocupaciones getOcupaciones() {
        return this.ocupaciones;
    }

    /**
     * Devuelve el valor de horario.
     * @return  Valor de horario.
     */
    public Horario getHorario() {
        return this.horario;
    }

    /**
     * Devuelve el valor de horaIni.
     * @return  Valor de horaIni.
     */
    public int getHoraIni() {
        return this.horaIni;
    }

    /**
     * Devuelve el valor de horaFin.
     * @return  Valor de horaFin.
     */
    public int getHoraFin() {
        return this.horaFin;
    }

    /** Métodos redefinidos **/

}