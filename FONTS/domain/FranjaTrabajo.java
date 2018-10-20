package domain;

/** Imports **/

import java.time.Clock;

import domain.TipoRestriccion;

public class FranjaTrabajo extends Restriccion {

    /** Atributos **/

    private Clock horaIni;
    private Clock horaFin;

    /** Constructoras **/

    public FranjaTrabajo(Clock horaIni, Clock horaFin) {
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /** Métodos públicos **/

    public void setHoraIni(Clock horaIni) {
        this.horaIni = horaIni;
    }

    public void setHoraFin(Clock horaFin) {
        this.horaFin = horaFin;
    }

    /** Consultoras **/

    public Clock getHoraIni() {
        return horaIni;
    }

    public Clock getHoraFin() {
        return this.horaFin;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.FranjaTrabajo;
    }
}