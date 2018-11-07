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
}