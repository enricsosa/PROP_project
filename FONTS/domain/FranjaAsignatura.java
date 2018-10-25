package domain;

/** Imports **/

import java.time.Clock;

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
}