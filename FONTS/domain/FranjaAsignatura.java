package domain;

/** Imports **/

public class FranjaAsignatura extends Restriccion {

    /** Atributos **/

    private Asignatura asignatura;
    private Integer horaIni;
    private Integer horaFin;

    /** Constructoras **/

    public FranjaAsignatura(Asignatura asignatura, Integer horaIni, Integer horaFin) {
        this.asignatura = asignatura;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /** Métodos públicos **/

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    /** Consultoras **/

    public Asignatura getAsignatura() {
        return this.asignatura;
    }

    public Integer getHoraIni() {
        return horaIni;
    }

    public Integer getHoraFin() {
        return this.horaFin;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.FranjaAsignatura;
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
        if (clase.getAsignatura() != this.asignatura) return true;
        return ((horaIni >= this.horaIni) && ((horaIni + clase.getDuracion()) <= this.horaFin));
    }

    @Override
    public String toString() {
        return ("Franja Asignatura:\n-Asignatura: " + this.asignatura.getId() + "\n-Hora inicio: " + getHoraCompleta(this.horaIni)
                + "\n-Hora fin: " + getHoraCompleta(this.horaFin));
    }

    static String getHoraCompleta(int hora) {
        if (hora == 24) return "00:00";
        else if (hora < 10) return "0" + Integer.toString(hora) + ":00";
        return Integer.toString(hora) + ":00";
    }
}