package domain;

/** Imports **/

public class Sesion {

    /** Atributos **/

    private Integer duracion;
    private TipoClase tipo;
    private Asignatura asignatura;

    /** Constructoras **/

    public Sesion(Integer duracion, TipoClase tipo, Asignatura asignatura) {
        this.duracion = duracion;
        this.tipo = tipo;
        this.asignatura = asignatura;
    }

    /** Métodos públicos **/

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /** Consultoras **/

    public Integer getDuracion() {
        return this.duracion;
    }

    public TipoClase getTipo() {
        return this.tipo;
    }

    public Asignatura getAsignatura() {
        return this.asignatura;
    }

    /** Métodos redefinidos **/

}