package domain;

/** Imports **/

public class FranjaTrabajo extends Restriccion {

    /** Atributos **/

    private Integer horaIni;
    private Integer horaFin;

    /** Constructoras **/

    public FranjaTrabajo(Integer horaIni, Integer horaFin) {
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /** Métodos públicos **/

    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    /** Consultoras **/

    public Integer getHoraIni() {
        return horaIni;
    }

    public Integer getHoraFin() {
        return this.horaFin;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.FranjaTrabajo;
    }

    @Override
    public Boolean comprovarRestriccion(Asignacion asignacion, Ocupaciones ocupaciones) {
        return ((asignacion.getHoraIni() >= this.horaIni) && (asignacion.getHoraFin() <= this.horaFin));
    }

    @Override
    public Boolean comprovarRestriccion(Clase clase, int dia, int horaIni, Ocupaciones ocupaciones) {
        return ((horaIni >= this.horaIni) && ((horaIni + clase.getDuracion()) <= this.horaFin));
    }

    @Override
    public String toString() {
        return ("Franja Trabajo:\n-Hora inicio: " + getHoraCompleta(this.horaIni) + "\n-Hora fin: " + getHoraCompleta(this.horaFin));
    }

    static String getHoraCompleta(int hora) {
        if (hora == 24) return "00:00";
        else if (hora < 10) return "0" + Integer.toString(hora) + ":00";
        return Integer.toString(hora) + ":00";
    }
}