/**Restriccion*/

/**Imports*/

package domain;

/**
 * Clase abstracta que representa una Restricción que afecta a algun aspecto de la genración de Horarios.
 * @author  Daniel Martín
 */
public abstract class Restriccion {

    /**Atributos*/

    /**Constructoras*/

    /**Constructora de la clase Restriccion.*/
    public Restriccion() {}

    /**Métodos públicos*/

    /**Consultoras*/

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
