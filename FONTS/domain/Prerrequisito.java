package domain;

/** Imports **/

public class Prerrequisito extends Restriccion {

    /** Atributos **/

    private Asignatura asignatura;
    private Asignatura prerrequisito;

    /** Constructoras **/

    public Prerrequisito(Asignatura asignatura, Asignatura prerrequisito) {
        this.asignatura = asignatura;
        this.prerrequisito = prerrequisito;
    }

    /** Métodos públicos **/

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    public void setPrerrequisito(Asignatura prerrequisito) {
        this.prerrequisito = prerrequisito;
    }

    /** Consultoras **/

    public Asignatura getAsignatura() {
        return asignatura;
    }

    public Asignatura getPrerrequisito() {
        return prerrequisito;
    }

    /** Métodos redefinidos **/

    @Override
    public TipoRestriccion getTipoRestriccion() {
        return TipoRestriccion.Prerrequisito;
    }
}