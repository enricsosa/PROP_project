package domain;

import java.util.ArrayList;

/** Imports **/

public class Correquisito extends Restriccion {

    /** Atributos **/

    private Asignatura asignatura1;
    private Asignatura asignatura2;

    /** Constructoras **/

    public Correquisito(Asignatura asignatura1, Asignatura asignatura2) {
        this.asignatura1 = asignatura1;
        this.asignatura2 = asignatura2;
    }

    /** Métodos públicos **/

    public void setAsignatura1(Asignatura asignatura1) {
        this.asignatura1 = asignatura1;
    }

    public void setAsignatura2(Asignatura asignatura2) {
        this.asignatura2 = asignatura2;
    }

    public Boolean tieneAsignatura(Asignatura asignatura) {
        if (asignatura == asignatura1) return true;
        if (asignatura == asignatura2) return true;
        return false;
    }

    public int getIndex(Asignatura asignatura) {
        if (asignatura == asignatura1) return 1;
        return 2;
    }

    /** Consultoras **/

    public Asignatura getAsignatura1() {
        return this.asignatura1;
    }

    public Asignatura getAsignatura2() {
        return this.asignatura2;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.Correquisito;
    }

    @Override
    public Boolean comprovarRestriccion(Asignacion asignacion, Ocupaciones ocupaciones) {
        if (!(this.tieneAsignatura(asignacion.getAsignatura()))) return true;
        Asignatura target;
        if (this.getIndex(asignacion.getAsignatura()) == 1) target = asignatura2;
        else target = asignatura1;
        for (int hora = asignacion.getHoraIni(); hora < asignacion.getHoraFin(); ++hora) {
            if (ocupaciones.getDia(asignacion.getDiaSemana()).getHora(hora).tieneGrupo(target, asignacion.getGrupo())) return false;
        }
        return true;
    }
}