/**FranjaTrabajo*/

/**Imports*/

package domain;

import domaincontrollers.Aux;

/**
 * FranjaTrabajo contiene la información de una Restriccion que indica que no se pueden dar Clases fuera de una franja
 * horaria.
 * @author  Daniel Martín
 */
public class FranjaTrabajo extends Restriccion {

    /**Atributos*/

    /**Hora de inicio de la franja horaria.*/
    private Integer horaIni;
    /**Hora en que acaba la franja horaria.*/
    private Integer horaFin;

    /**Constructoras*/

    /**
     * Constructora de la clase FranjaTrabajo.
     * @param horaIni   horaUni que se asociará a la Restricción.
     * @param horaFin   horaFin que se asociará a la Restricción.
     */
    public FranjaTrabajo(Integer horaIni, Integer horaFin) {
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.setActiva(true);
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva horaIni a la Restriccion.
     * @param horaIni   Nueva horaIni que se asociará a la Restriccion.
     */
    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    /**
     * Asigna una nueva horaFin a la Restriccion.
     * @param horaFin   Nueva horaFin que se asociará a la Restriccion.
     */
    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    /**Consultoras*/

    /**
     * Devuelve la horaIni de la Restriccion.
     * @return horaIni de la Restriccion.
     */
    public Integer getHoraIni() {
        return horaIni;
    }

    /**
     * Devuelve la horaFin de la Restriccion.
     * @return horaFin de la Restriccion.
     */
    public Integer getHoraFin() {
        return this.horaFin;
    }

    /**Métodos redefinidos*/

    /**
     * Devuelve el TipoRestriccion correspondiente.
     * @return  TipoRestriccion.FranjaTrabajo.
     */
    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.FranjaTrabajo;
    }

    /**
     * Comprueva que clase cumple la restricción respecto a un dia, horaIni y horario.
     * @param clase         Clase de la que se comprueba la Retriccion.
     * @param dia           dia en que se comprueba la Restriccion.
     * @param horaIni       horaIni con la que se comprueba la Restriccion.
     * @param horario       Horario respecto al cual se comprueba la Restriccion.
     * @return              true si se cumple la Restriccion con las condiciones dadas, false en caso contrario.
     */
    @Override
    public Boolean comprobarRestriccion(Clase clase, int dia, int horaIni, Horario horario) {
        if (this.getActiva()) {
            return ((horaIni >= this.horaIni) && ((horaIni + clase.getDuracion()) <= this.horaFin));
        }
        return true;
    }

    /**
     * Convierte FranjaTrabajo a un String con su información.
     * @return  String con la información FranjaTrabajo.
     */
    @Override
    public String toString() {
        String activo;
        if (this.getActiva()) activo = "Activa";
        else activo = "No activa";
        return ("Franja Trabajo (" + activo + "):\n-Hora inicio: " + Aux.getHoraCompleta(this.horaIni) + "\n-Hora fin: " + Aux.getHoraCompleta(this.horaFin));
    }
}