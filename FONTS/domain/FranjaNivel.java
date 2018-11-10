package domain;

/** Imports **/

public class FranjaNivel extends Restriccion {

    /** Atributos **/

    private Nivel nivel;
    private Integer horaIni;
    private Integer horaFin;

    /** Constructoras **/

    public FranjaNivel(Nivel nivel, Integer horaIni, Integer horaFin) {
        this.nivel = nivel;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /** Métodos públicos **/

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public void setHoraIni(Integer horaIni) {
        this.horaIni = horaIni;
    }

    public void setHoraFin(Integer horaFin) {
        this.horaFin = horaFin;
    }

    /** Consultoras **/

    public Nivel getNivel() {
        return this.nivel;
    }

    public Integer getHoraIni() {
        return this.horaIni;
    }

    public Integer getHoraFin() {
        return this.horaFin;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.FranjaNivel;
    }

    @Override
    public Boolean comprovarRestriccion(Asignacion asignacion, Ocupaciones ocupaciones) {
        if (!(asignacion.tieneNivel())) return true;
        if ((asignacion.getNivel() != this.nivel)) return true;
        return ((asignacion.getHoraIni() >= this.horaIni) && (asignacion.getHoraFin() <= this.horaFin));
    }

    @Override
    public String toString() {
        return ("Franja Nivel:\n-Nivel: " + this.nivel.getNombre() + "\n-Hora inicio: " + getHoraCompleta(this.horaIni)
                + "\n-Hora fin: " + getHoraCompleta(this.horaFin));
    }

    static String getHoraCompleta(int hora) {
        if (hora == 24) return "00:00";
        else if (hora < 10) return "0" + Integer.toString(hora) + ":00";
        return Integer.toString(hora) + ":00";
    }
}