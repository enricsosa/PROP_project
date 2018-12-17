/**Restriccion*/

/**Imports*/

package domain;

/**
 * Clase abstracta que representa una Restricción que afecta a algun aspecto de la genración de Horarios.
 * @author  Daniel Martín
 */
public abstract class Restriccion {

    /**Atributos*/

    /**Indica si la Restricción está activada*/
    private Boolean activa;

    /**Constructoras*/

    /**Métodos públicos*/

    /**
     * Asigna un valor a activa.
     * @param activa    valor que se asigna a activa.
     */
    public void setActiva(Boolean activa) {
        this.activa = activa;
    }

    /**Consultoras*/

    /**
     * Indica si la Restricción está activa.
     * @return  true si la Restriccion está activa, false en caso contrario.
     */
    public Boolean getActiva() {
        return activa;
    }

    /**
     * Devuelve el TipoRestriccion correspondiente.
     * @return  TipoRestriccion de la Restriccion.
     */
    public abstract TipoRestriccion getTipoRestriccion();

    /**
     * Comprueva que clase cumple la restricción respecto a un dia, horaIni y horario.
     * @param clase         Clase de la que se comprueba la Retriccion.
     * @param dia           dia en que se comprueba la Restriccion.
     * @param horaIni       horaIni con la que se comprueba la Restriccion.
     * @param horario       Horario respecto a las cuales se comprueba la Restriccion.
     * @return              true si se cumple la Restriccion con las condiciones dadas, false en caso contrario.
     */
    public abstract Boolean comprobarRestriccion(Clase clase, int dia, int horaIni, Horario horario);

    /**Métodos redefinidos*/

}
