package domain;

/** Imports **/

import java.time.Clock;

public class FranjaNivel extends Restriccion {

    /** Atributos **/

    private Nivel nivel;
    private Clock horaIni;
    private Clock horaFin;

    /** Constructoras **/

    public FranjaNivel(Nivel nivel, Clock horaIni, Clock horaFin) {
        this.nivel = nivel;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /** Métodos públicos **/

    public void setNivel(Nivel nivel) {
        this.nivel = nivel;
    }

    public void setHoraIni(Clock horaIni) {
        this.horaIni = horaIni;
    }

    public void setHoraFin(Clock horaFin) {
        this.horaFin = horaFin;
    }

    /** Consultoras **/

    public Nivel getNivel() {
        return this.nivel;
    }

    public Clock getHoraIni() {
        return this.horaIni;
    }

    public Clock getHoraFin() {
        return this.horaFin;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.FranjaNivel;
    }
}