package domain;

/** Imports **/

import java.time.Clock;

import domain.Asignatura;
import domain.TipoRestriccion;

public class FranjaAsignatura extends Restriccion {

    /** Atributos **/

    private Asignatura asignatura;
    private Clock horaIni;
    private Clock horaFin;

    /** Constructoras **/

    public FranjaAsignatura(Asignatura asignatura, Clock horaIni, Clock horaFin) {
        this.asignatura = asignatura;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    /** Métodos públicos **/

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public void setHoraIni(Clock horaIni) {
        this.horaIni = horaIni;
    }

    public void setHoraFin(Clock horaFin) {
        this.horaFin = horaFin;
    }

    /** Consultoras **/

    public Asignatura getAsignatura() {
        return this.asignatura;
    }

    public Clock getHoraIni() {
        return horaIni;
    }

    public Clock getHoraFin() {
        return this.horaFin;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.FranjaAsignatura;
    }
}