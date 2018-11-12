/**DiaLibre*/

/** Imports **/

package domain;

/**
 * DiaLibre contiene la información de una Restriccion que indica que no se pueden dar Clases en un dia de la semana.
 * @author  Daniel Martín
 */
public class DiaLibre extends Restriccion {

    /**Atributos*/

    /**Integer que indica el dia de la semana en el que no se da Clase.*/
    private Integer dia;

    /**Constructoras*/

    /**
     * Constructora de la clase DiaLibre.
     * @param dia   dia de la semana que se asigna a DiaLibre.
     */
    public DiaLibre(Integer dia) {
        this.dia = dia;
    }

    /**Métodos públicos*/

    /**
     * Asigna un nuevo dia a DiaLibre.
     * @param dia   Nuevo dia que se asignará a DiaLibre.
     */
    public void setDia(Integer dia) {
        this.dia = dia;
    }

    /**Consultoras*/

    /**
     * Devuelve el dia de la semana en el que no se da Clase.
     * @return  dia de la semana en el que no se da Clase.
     */
    public Integer getDia() {
        return this.dia;
    }

    /**Métodos redefinidos*/

    /**
     * Devuelve el TipoRestriccion correspondiente.
     * @return  TipoRestriccion.DiaLibre.
     */
    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.DiaLibre;
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
    public Boolean comprovarRestriccion(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones) {
        return (this.dia != dia);
    }

    /**
     * Convierte DiaLibre a un String con su información.
     * @return  String con la información DiaLibre.
     */
    @Override
    public String toString() {
        return "Dia Libre: " + getNombreDia(this.dia);
    }

    /**
     * Obtiene el String con el nombre del dia de la Semana que corresponde a un int representando un dia.
     * @param dia   dia de la semana del que se quiere obtener el nombre.
     * @return      String con el nombre de dia.
     */
    static String getNombreDia(int dia) {
        if (dia == 1) return "Lunes";
        else if (dia == 2) return "Martes";
        else if (dia == 3) return "Miercoles";
        else if (dia == 4) return "Jueves";
        else if (dia == 5) return "Viernes";
        else if (dia == 6) return "Sábado";
        else return "Domingo";
    }

}
