package domain;

/** Imports **/

import domain.Asignatura;
import domain.TipoRestriccion;

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
}