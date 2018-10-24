package domain;

/** Imports **/

import java.time.Duration;

public class Sesion {

    /** Atributos **/

    private Duration duracion;
    private TipoClase tipo;
    private Asignatura asignatura;

    /** Constructoras **/

    public Sesion(Duration duracion, TipoClase tipo, Asignatura asignatura) {
        this.duracion = duracion;
        this.tipo = tipo;
        this.asignatura = asignatura;
    }

    /** Métodos públicos **/

    public void setDuracion(Duration duracion) {
        this.duracion = duracion;
    }

    public void setTipo(TipoClase tipo) {
        this.tipo = tipo;
    }

    public void setAsignatura(Asignatura asignatura) {
        this.asignatura = asignatura;
    }

    /** Consultoras **/

    public Duration getDuracion() {
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