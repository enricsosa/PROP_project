/**FranjaAsignatura*/

/**Imports **/

package domain;

/**
 * FranjaAsignatura contiene la información de una Restriccion que indica que no se pueden dar Clases de una Asignatura
 * fuera de una franja horaria.
 * @author  Daniel Martín
 */
public class FranjaAsignatura extends Restriccion {

    /**Atributos*/

    /**Asignatura a la que se aplica la Restriccion.*/
    private Asignatura asignatura;
    /**Hora de inicio de la franja horaria.*/
    private Integer horaIni;
    /**Hora en que acaba la franja horaria.*/
    private Integer horaFin;

    /**Constructoras*/

    /**
     * Constructora de la clase FranjaAsignatura.
     * @param asignatura    Asignatura que se asociará a la Restriccion.
     * @param horaIni       horaUni que se asociará a la Restricción.
     * @param horaFin       horaFin que se asociará a la Restricción.
     */
    public FranjaAsignatura(Asignatura asignatura, Integer horaIni, Integer horaFin) {
        this.asignatura = asignatura;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
        this.setActiva(true);
    }

    /**Métodos públicos*/

    /**
     * Asigna una nueva Asignatura a la Restriccion.
     * @param asignatura    Nueva Asigantura que se asociará a la Restriccion.
     */
    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

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
     * Devuelve la Asignatura asociada a la Restriccion.
     * @return  Asignatura de la Restriccion.
     */
    public Asignatura getAsignatura() {
        return this.asignatura;
    }

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
     * @return  TipoRestriccion.FranjaAsignatura.
     */
    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.FranjaAsignatura;
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
            if (clase.getAsignatura() != this.asignatura) return true;
            if ((horaIni >= this.horaIni) && ((horaIni + clase.getDuracion()) <= this.horaFin)) return true;
            //System.out.println("Falla FranjaAsignatura");
            return false;
        }
        return true;
    }

    /**
     * Convierte FranjaAsignatura a un String con su información.
     * @return  String con la información FranjaAsignatura.
     */
    @Override
    public String toString() {
        return ("Franja Asignatura:\n-Asignatura: " + this.asignatura.getId() + "\n-Hora inicio: " + Aux.getHoraCompleta(this.horaIni)
                + "\n-Hora fin: " + Aux.getHoraCompleta(this.horaFin));
    }
}